package com.tom_roush.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.filter.DecodeOptions;
import com.tom_roush.pdfbox.filter.DecodeResult;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDMetadata;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.util.filetypedetector.FileType;
import com.tom_roush.pdfbox.util.filetypedetector.FileTypeDetector;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public final class PDImageXObject extends PDXObject implements PDImage {
    private SoftReference<Bitmap> cachedImage;
    private int cachedImageSubsampling;
    private PDColorSpace colorSpace;
    private final PDResources resources;

    private static int clampColor(int i) {
        if (i < 0) {
            return 0;
        }
        if (i > 255) {
            return 255;
        }
        return i;
    }

    public PDImageXObject(PDDocument pDDocument) throws IOException {
        this(new PDStream(pDDocument), null);
    }

    public PDImageXObject(PDDocument pDDocument, InputStream inputStream, COSBase cOSBase, int i, int i2, int i3, PDColorSpace pDColorSpace) throws IOException {
        super(createRawStream(pDDocument, inputStream), COSName.IMAGE);
        this.cachedImageSubsampling = Integer.MAX_VALUE;
        getCOSObject().setItem(COSName.FILTER, cOSBase);
        this.resources = null;
        this.colorSpace = null;
        setBitsPerComponent(i3);
        setWidth(i);
        setHeight(i2);
        setColorSpace(pDColorSpace);
    }

    public PDImageXObject(PDStream pDStream, PDResources pDResources) throws IOException {
        super(pDStream, COSName.IMAGE);
        COSInputStream cOSInputStream;
        this.cachedImageSubsampling = Integer.MAX_VALUE;
        this.resources = pDResources;
        List<COSName> filters = pDStream.getFilters();
        if (filters == null || filters.isEmpty()) {
            return;
        }
        boolean z = true;
        if (COSName.JPX_DECODE.equals(filters.get(filters.size() - 1))) {
            List asList = Arrays.asList(COSName.WIDTH, COSName.HEIGHT, COSName.COLORSPACE);
            COSStream cOSObject = pDStream.getCOSObject();
            Iterator it = asList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (!cOSObject.containsKey((COSName) it.next())) {
                    break;
                }
            }
            if (z) {
                try {
                    cOSInputStream = pDStream.createInputStream();
                } catch (Throwable th) {
                    th = th;
                    cOSInputStream = null;
                }
                try {
                    DecodeResult decodeResult = cOSInputStream.getDecodeResult();
                    pDStream.getCOSObject().addAll(decodeResult.getParameters());
                    this.colorSpace = decodeResult.getJPXColorSpace();
                    IOUtils.closeQuietly(cOSInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.closeQuietly(cOSInputStream);
                    throw th;
                }
            }
        }
    }

    public static PDImageXObject createThumbnail(COSStream cOSStream) throws IOException {
        return new PDImageXObject(new PDStream(cOSStream), null);
    }

    private static COSStream createRawStream(PDDocument pDDocument, InputStream inputStream) throws IOException {
        OutputStream outputStream;
        COSStream createCOSStream = pDDocument.getDocument().createCOSStream();
        try {
            outputStream = createCOSStream.createRawOutputStream();
        } catch (Throwable th) {
            th = th;
            outputStream = null;
        }
        try {
            IOUtils.copy(inputStream, outputStream);
            if (outputStream != null) {
                outputStream.close();
            }
            return createCOSStream;
        } catch (Throwable th2) {
            th = th2;
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
    }

    public static PDImageXObject createFromFile(String str, PDDocument pDDocument) throws IOException {
        return createFromFileByExtension(new File(str), pDDocument);
    }

    public static PDImageXObject createFromFileByExtension(File file, PDDocument pDDocument) throws IOException {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf == -1) {
            throw new IllegalArgumentException("Image type not supported: " + name);
        }
        String lowerCase = name.substring(lastIndexOf + 1).toLowerCase();
        if ("jpg".equals(lowerCase) || "jpeg".equals(lowerCase)) {
            FileInputStream fileInputStream = null;
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    PDImageXObject createFromStream = JPEGFactory.createFromStream(pDDocument, fileInputStream2);
                    IOUtils.closeQuietly(fileInputStream2);
                    return createFromStream;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    IOUtils.closeQuietly(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            if ("tif".equals(lowerCase) || "tiff".equals(lowerCase)) {
                try {
                    return CCITTFactory.createFromFile(pDDocument, file);
                } catch (IOException e) {
                    Log.d("PdfBox-Android", "Reading as TIFF failed, setting fileType to PNG", e);
                    lowerCase = "png";
                }
            }
            if ("gif".equals(lowerCase) || "bmp".equals(lowerCase) || "png".equals(lowerCase)) {
                return LosslessFactory.createFromImage(pDDocument, BitmapFactory.decodeFile(file.getPath()));
            }
            throw new IllegalArgumentException("Image type not supported: " + name);
        }
    }

    public static PDImageXObject createFromFileByContent(File file, PDDocument pDDocument) throws IOException {
        BufferedInputStream bufferedInputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                bufferedInputStream = new BufferedInputStream(fileInputStream);
            } catch (IOException e) {
                e = e;
                bufferedInputStream = null;
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = null;
            }
        } catch (IOException e2) {
            e = e2;
            bufferedInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
        }
        try {
            FileType detectFileType = FileTypeDetector.detectFileType(bufferedInputStream);
            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(bufferedInputStream);
            if (detectFileType == null) {
                throw new IllegalArgumentException("Image type not supported: " + file.getName());
            }
            if (detectFileType.equals(FileType.JPEG)) {
                FileInputStream fileInputStream3 = new FileInputStream(file);
                PDImageXObject createFromStream = JPEGFactory.createFromStream(pDDocument, fileInputStream3);
                fileInputStream3.close();
                return createFromStream;
            }
            if (detectFileType.equals(FileType.TIFF)) {
                try {
                    return CCITTFactory.createFromFile(pDDocument, file);
                } catch (IOException e3) {
                    Log.d("PdfBox-Android", "Reading as TIFF failed, setting fileType to PNG", e3);
                    detectFileType = FileType.PNG;
                }
            }
            if (detectFileType.equals(FileType.BMP) || detectFileType.equals(FileType.GIF) || detectFileType.equals(FileType.PNG)) {
                return LosslessFactory.createFromImage(pDDocument, BitmapFactory.decodeFile(file.getPath()));
            }
            throw new IllegalArgumentException("Image type " + detectFileType + " not supported: " + file.getName());
        } catch (IOException e4) {
            e = e4;
            fileInputStream2 = fileInputStream;
            try {
                throw new IOException("Could not determine file type: " + file.getName(), e);
            } catch (Throwable th3) {
                th = th3;
                IOUtils.closeQuietly(fileInputStream2);
                IOUtils.closeQuietly(bufferedInputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream2 = fileInputStream;
            IOUtils.closeQuietly(fileInputStream2);
            IOUtils.closeQuietly(bufferedInputStream);
            throw th;
        }
    }

    public static PDImageXObject createFromByteArray(PDDocument pDDocument, byte[] bArr, String str) throws IOException {
        try {
            FileType detectFileType = FileTypeDetector.detectFileType(bArr);
            if (detectFileType == null) {
                throw new IllegalArgumentException("Image type not supported: " + str);
            }
            if (detectFileType.equals(FileType.JPEG)) {
                return JPEGFactory.createFromByteArray(pDDocument, bArr);
            }
            if (detectFileType.equals(FileType.TIFF)) {
                try {
                    return CCITTFactory.createFromByteArray(pDDocument, bArr);
                } catch (IOException e) {
                    Log.d("PdfBox-Android", "Reading as TIFF failed, setting fileType to PNG", e);
                    detectFileType = FileType.PNG;
                }
            }
            if (detectFileType.equals(FileType.BMP) || detectFileType.equals(FileType.GIF) || detectFileType.equals(FileType.PNG)) {
                return LosslessFactory.createFromImage(pDDocument, BitmapFactory.decodeStream(new ByteArrayInputStream(bArr)));
            }
            throw new IllegalArgumentException("Image type " + detectFileType + " not supported: " + str);
        } catch (IOException e2) {
            throw new IOException("Could not determine file type: " + str, e2);
        }
    }

    public PDMetadata getMetadata() {
        COSStream cOSStream = getCOSObject().getCOSStream(COSName.METADATA);
        if (cOSStream != null) {
            return new PDMetadata(cOSStream);
        }
        return null;
    }

    public void setMetadata(PDMetadata pDMetadata) {
        getCOSObject().setItem(COSName.METADATA, pDMetadata);
    }

    public int getStructParent() {
        return getCOSObject().getInt(COSName.STRUCT_PARENT);
    }

    public void setStructParent(int i) {
        getCOSObject().setInt(COSName.STRUCT_PARENT, i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public Bitmap getImage() throws IOException {
        return getImage(null, 1);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public Bitmap getImage(Rect rect, int i) throws IOException {
        Bitmap rGBImage;
        SoftReference<Bitmap> softReference;
        Bitmap bitmap;
        if (rect != null || i != this.cachedImageSubsampling || (softReference = this.cachedImage) == null || (bitmap = softReference.get()) == null) {
            PDImageXObject softMask = getSoftMask();
            PDImageXObject mask = getMask();
            if (softMask != null) {
                rGBImage = applyMask(SampledImageReader.getRGBImage(this, rect, i, getColorKeyMask()), softMask.getOpaqueImage(), softMask.getInterpolate(), true, extractMatte(softMask));
            } else if (mask != null && mask.isStencil()) {
                rGBImage = applyMask(SampledImageReader.getRGBImage(this, rect, i, getColorKeyMask()), mask.getOpaqueImage(), mask.getInterpolate(), false, null);
            } else {
                rGBImage = SampledImageReader.getRGBImage(this, rect, i, getColorKeyMask());
            }
            if (rect == null && i <= this.cachedImageSubsampling) {
                this.cachedImageSubsampling = i;
                this.cachedImage = new SoftReference<>(rGBImage);
            }
            return rGBImage;
        }
        return bitmap;
    }

    private float[] extractMatte(PDImageXObject pDImageXObject) throws IOException {
        COSBase item = pDImageXObject.getCOSObject().getItem(COSName.MATTE);
        if (item instanceof COSArray) {
            float[] floatArray = ((COSArray) item).toFloatArray();
            if (floatArray.length < getColorSpace().getNumberOfComponents()) {
                Log.e("PdfBox-Android", "Image /Matte entry not long enough for colorspace, skipped");
                return null;
            }
            return getColorSpace().toRGB(floatArray);
        }
        return null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public Bitmap getStencilImage(Paint paint) throws IOException {
        if (!isStencil()) {
            throw new IllegalStateException("Image is not a stencil");
        }
        return SampledImageReader.getStencilImage(this, paint);
    }

    public Bitmap getOpaqueImage() throws IOException {
        return SampledImageReader.getRGBImage(this, null);
    }

    private Bitmap applyMask(Bitmap bitmap, Bitmap bitmap2, boolean z, boolean z2, float[] fArr) {
        Bitmap bitmap3 = bitmap;
        Bitmap bitmap4 = bitmap2;
        if (bitmap4 == null) {
            return bitmap3;
        }
        int max = Math.max(bitmap.getWidth(), bitmap2.getWidth());
        int max2 = Math.max(bitmap.getHeight(), bitmap2.getHeight());
        if (bitmap2.getWidth() < max || bitmap2.getHeight() < max2) {
            bitmap4 = scaleImage(bitmap4, max, max2, z);
        }
        if (bitmap4.getConfig() != Bitmap.Config.ALPHA_8 || !bitmap.isMutable()) {
            bitmap4 = bitmap4.copy(Bitmap.Config.ALPHA_8, true);
        }
        if (bitmap.getWidth() < max || bitmap.getHeight() < max2) {
            bitmap3 = scaleImage(bitmap3, max, max2, getInterpolate());
        }
        if (bitmap3.getConfig() != Bitmap.Config.ARGB_8888 || !bitmap3.isMutable()) {
            bitmap3 = bitmap3.copy(Bitmap.Config.ARGB_8888, true);
        }
        int[] iArr = new int[max];
        int[] iArr2 = new int[max];
        if (!z2 && bitmap3.getByteCount() == bitmap4.getByteCount()) {
            int i = 0;
            while (i < max2) {
                int i2 = i;
                bitmap3.getPixels(iArr, 0, max, 0, i, max, 1);
                bitmap4.getPixels(iArr2, 0, max, 0, i2, max, 1);
                int i3 = 0;
                for (int i4 = max; i4 > 0; i4--) {
                    iArr[i3] = (iArr[i3] & ViewCompat.MEASURED_SIZE_MASK) | ((~iArr2[i3]) & ViewCompat.MEASURED_STATE_MASK);
                    i3++;
                }
                bitmap3.setPixels(iArr, 0, max, 0, i2, max, 1);
                i = i2 + 1;
            }
        } else if (fArr == null) {
            for (int i5 = 0; i5 < max2; i5++) {
                int i6 = i5;
                bitmap3.getPixels(iArr, 0, max, 0, i6, max, 1);
                bitmap4.getPixels(iArr2, 0, max, 0, i6, max, 1);
                for (int i7 = 0; i7 < max; i7++) {
                    if (!z2) {
                        iArr2[i7] = ~iArr2[i7];
                    }
                    iArr[i7] = (iArr[i7] & ViewCompat.MEASURED_SIZE_MASK) | (iArr2[i7] & ViewCompat.MEASURED_STATE_MASK);
                }
                bitmap3.setPixels(iArr, 0, max, 0, i5, max, 1);
            }
        } else {
            int round = Math.round(fArr[0] * 8355840.0f) * 255;
            int round2 = Math.round(fArr[1] * 8355840.0f) * 255;
            int round3 = Math.round(fArr[2] * 8355840.0f) * 255;
            int i8 = (round / 255) + 16384;
            int i9 = (round2 / 255) + 16384;
            int i10 = (round3 / 255) + 16384;
            int i11 = 0;
            while (i11 < max2) {
                int i12 = i11;
                int i13 = i10;
                int i14 = i9;
                int i15 = i8;
                int i16 = round3;
                int i17 = round;
                bitmap3.getPixels(iArr, 0, max, 0, i12, max, 1);
                bitmap4.getPixels(iArr2, 0, max, 0, i12, max, 1);
                for (int i18 = 0; i18 < max; i18++) {
                    int alpha = Color.alpha(iArr2[i18]);
                    if (alpha == 0) {
                        iArr[i18] = iArr[i18] & ViewCompat.MEASURED_SIZE_MASK;
                    } else {
                        int i19 = iArr[i18];
                        iArr[i18] = Color.argb(alpha, clampColor(((((Color.red(i19) * 8355840) - i17) / alpha) + i15) >> 15), clampColor(((((Color.green(i19) * 8355840) - round2) / alpha) + i14) >> 15), clampColor(((((Color.blue(i19) * 8355840) - i16) / alpha) + i13) >> 15));
                    }
                }
                bitmap3.setPixels(iArr, 0, max, 0, i12, max, 1);
                i11 = i12 + 1;
                i10 = i13;
                i8 = i15;
                i9 = i14;
                round3 = i16;
                round = i17;
            }
        }
        return bitmap3;
    }

    private Bitmap scaleImage(Bitmap bitmap, int i, int i2, boolean z) {
        return Bitmap.createScaledBitmap(bitmap, i, i2, !z);
    }

    public PDImageXObject getMask() throws IOException {
        COSStream cOSStream;
        if ((getCOSObject().getDictionaryObject(COSName.MASK) instanceof COSArray) || (cOSStream = getCOSObject().getCOSStream(COSName.MASK)) == null) {
            return null;
        }
        return new PDImageXObject(new PDStream(cOSStream), null);
    }

    public COSArray getColorKeyMask() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.MASK);
        if (dictionaryObject instanceof COSArray) {
            return (COSArray) dictionaryObject;
        }
        return null;
    }

    public PDImageXObject getSoftMask() throws IOException {
        COSStream cOSStream = getCOSObject().getCOSStream(COSName.SMASK);
        if (cOSStream != null) {
            return new PDImageXObject(new PDStream(cOSStream), null);
        }
        return null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public int getBitsPerComponent() {
        if (isStencil()) {
            return 1;
        }
        return getCOSObject().getInt(COSName.BITS_PER_COMPONENT, COSName.BPC);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public void setBitsPerComponent(int i) {
        getCOSObject().setInt(COSName.BITS_PER_COMPONENT, i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public PDColorSpace getColorSpace() throws IOException {
        COSObject cOSObject;
        PDResources pDResources;
        if (this.colorSpace == null) {
            COSBase item = getCOSObject().getItem(COSName.COLORSPACE, COSName.CS);
            if (item != null) {
                if (!(item instanceof COSObject) || (pDResources = this.resources) == null || pDResources.getResourceCache() == null) {
                    cOSObject = null;
                } else {
                    cOSObject = (COSObject) item;
                    PDColorSpace colorSpace = this.resources.getResourceCache().getColorSpace(cOSObject);
                    this.colorSpace = colorSpace;
                    if (colorSpace != null) {
                        return colorSpace;
                    }
                }
                this.colorSpace = PDColorSpace.create(item, this.resources);
                if (cOSObject != null) {
                    this.resources.getResourceCache().put(cOSObject, this.colorSpace);
                }
            } else if (isStencil()) {
                return PDDeviceGray.INSTANCE;
            } else {
                throw new IOException("could not determine color space");
            }
        }
        return this.colorSpace;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public InputStream createInputStream() throws IOException {
        return getStream().createInputStream();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public InputStream createInputStream(DecodeOptions decodeOptions) throws IOException {
        return getStream().createInputStream(decodeOptions);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public InputStream createInputStream(List<String> list) throws IOException {
        return getStream().createInputStream(list);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public boolean isEmpty() {
        return getStream().getCOSObject().getLength() == 0;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public void setColorSpace(PDColorSpace pDColorSpace) {
        getCOSObject().setItem(COSName.COLORSPACE, pDColorSpace != null ? pDColorSpace.getCOSObject() : null);
        this.colorSpace = null;
        this.cachedImage = null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public int getHeight() {
        return getCOSObject().getInt(COSName.HEIGHT);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public void setHeight(int i) {
        getCOSObject().setInt(COSName.HEIGHT, i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public int getWidth() {
        return getCOSObject().getInt(COSName.WIDTH);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public void setWidth(int i) {
        getCOSObject().setInt(COSName.WIDTH, i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public boolean getInterpolate() {
        return getCOSObject().getBoolean(COSName.INTERPOLATE, false);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public void setInterpolate(boolean z) {
        getCOSObject().setBoolean(COSName.INTERPOLATE, z);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public void setDecode(COSArray cOSArray) {
        getCOSObject().setItem(COSName.DECODE, (COSBase) cOSArray);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public COSArray getDecode() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.DECODE);
        if (dictionaryObject instanceof COSArray) {
            return (COSArray) dictionaryObject;
        }
        return null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public boolean isStencil() {
        return getCOSObject().getBoolean(COSName.IMAGE_MASK, false);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public void setStencil(boolean z) {
        getCOSObject().setBoolean(COSName.IMAGE_MASK, z);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage
    public String getSuffix() {
        List<COSName> filters = getStream().getFilters();
        if (filters == null) {
            return "png";
        }
        if (filters.contains(COSName.DCT_DECODE)) {
            return "jpg";
        }
        if (filters.contains(COSName.JPX_DECODE)) {
            return "jpx";
        }
        if (filters.contains(COSName.CCITTFAX_DECODE)) {
            return "tiff";
        }
        if (filters.contains(COSName.FLATE_DECODE) || filters.contains(COSName.LZW_DECODE) || filters.contains(COSName.RUN_LENGTH_DECODE)) {
            return "png";
        }
        if (filters.contains(COSName.JBIG2_DECODE)) {
            return "jb2";
        }
        Log.w("PdfBox-Android", "getSuffix() returns null, filters: " + filters);
        return null;
    }

    public PDPropertyList getOptionalContent() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.OC);
        if (dictionaryObject instanceof COSDictionary) {
            return PDPropertyList.create((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setOptionalContent(PDPropertyList pDPropertyList) {
        getCOSObject().setItem(COSName.OC, pDPropertyList);
    }
}
