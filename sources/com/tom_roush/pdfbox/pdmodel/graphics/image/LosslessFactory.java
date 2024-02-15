package com.tom_roush.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageOutputStream;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.filter.Filter;
import com.tom_roush.pdfbox.filter.FilterFactory;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import kotlin.UByte;

/* loaded from: classes3.dex */
public final class LosslessFactory {
    static boolean usePredictorEncoder = false;

    private LosslessFactory() {
    }

    public static PDImageXObject createFromImage(PDDocument pDDocument, Bitmap bitmap) throws IOException {
        PDImageXObject encode;
        if (isGrayImage(bitmap)) {
            return createFromGrayImage(bitmap, pDDocument);
        }
        if (usePredictorEncoder && (encode = new PredictorEncoder(pDDocument, bitmap).encode()) != null) {
            if (encode.getColorSpace() == PDDeviceRGB.INSTANCE && encode.getBitsPerComponent() < 16 && bitmap.getWidth() * bitmap.getHeight() <= 2500) {
                PDImageXObject createFromRGBImage = createFromRGBImage(bitmap, pDDocument);
                if (createFromRGBImage.getCOSObject().getLength() < encode.getCOSObject().getLength()) {
                    Log.e("PdfBox-Android", "Return classic");
                    encode.getCOSObject().close();
                    return createFromRGBImage;
                }
                Log.e("PdfBox-Android", "Return predictor");
                createFromRGBImage.getCOSObject().close();
            }
            return encode;
        }
        return createFromRGBImage(bitmap, pDDocument);
    }

    private static boolean isGrayImage(Bitmap bitmap) {
        return bitmap.getConfig() == Bitmap.Config.ALPHA_8;
    }

    private static PDImageXObject createFromGrayImage(Bitmap bitmap, PDDocument pDDocument) throws IOException {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int[] iArr = new int[width];
        int i = width * 8;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(((i / 8) + (i % 8 != 0 ? 1 : 0)) * height);
        MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(byteArrayOutputStream);
        for (int i2 = 0; i2 < height; i2++) {
            bitmap.getPixels(iArr, 0, width, 0, i2, width, 1);
            for (int i3 = 0; i3 < width; i3++) {
                memoryCacheImageOutputStream.writeBits(iArr[i3] & 255, 8);
            }
            int bitOffset = memoryCacheImageOutputStream.getBitOffset();
            if (bitOffset != 0) {
                memoryCacheImageOutputStream.writeBits(0L, 8 - bitOffset);
            }
        }
        memoryCacheImageOutputStream.flush();
        memoryCacheImageOutputStream.close();
        return prepareImageXObject(pDDocument, byteArrayOutputStream.toByteArray(), bitmap.getWidth(), bitmap.getHeight(), 8, PDDeviceGray.INSTANCE);
    }

    private static PDImageXObject createFromRGBImage(Bitmap bitmap, PDDocument pDDocument) throws IOException {
        byte[] bArr;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int[] iArr = new int[width];
        PDDeviceRGB pDDeviceRGB = PDDeviceRGB.INSTANCE;
        byte[] bArr2 = new byte[width * height * 3];
        if (bitmap.hasAlpha()) {
            int i = width * 8;
            bArr = new byte[((i / 8) + (i % 8 != 0 ? 1 : 0)) * height];
        } else {
            bArr = new byte[0];
        }
        byte[] bArr3 = bArr;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < height) {
            int i5 = i2;
            bitmap.getPixels(iArr, 0, width, 0, i2, width, 1);
            for (int i6 = 0; i6 < width; i6++) {
                int i7 = iArr[i6];
                int i8 = i3 + 1;
                bArr2[i3] = (byte) ((i7 >> 16) & 255);
                int i9 = i8 + 1;
                bArr2[i8] = (byte) ((i7 >> 8) & 255);
                i3 = i9 + 1;
                bArr2[i9] = (byte) (i7 & 255);
                if (bitmap.hasAlpha()) {
                    bArr3[i4] = (byte) ((i7 >> 24) & 255);
                    i4++;
                }
            }
            i2 = i5 + 1;
        }
        PDImageXObject prepareImageXObject = prepareImageXObject(pDDocument, bArr2, bitmap.getWidth(), bitmap.getHeight(), 8, pDDeviceRGB);
        if (bitmap.hasAlpha()) {
            prepareImageXObject.getCOSObject().setItem(COSName.SMASK, prepareImageXObject(pDDocument, bArr3, bitmap.getWidth(), bitmap.getHeight(), 8, PDDeviceGray.INSTANCE));
        }
        return prepareImageXObject;
    }

    static PDImageXObject prepareImageXObject(PDDocument pDDocument, byte[] bArr, int i, int i2, int i3, PDColorSpace pDColorSpace) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length / 2);
        FilterFactory.INSTANCE.getFilter(COSName.FLATE_DECODE).encode(new ByteArrayInputStream(bArr), byteArrayOutputStream, new COSDictionary(), 0);
        return new PDImageXObject(pDDocument, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.FLATE_DECODE, i, i2, i3, pDColorSpace);
    }

    /* loaded from: classes3.dex */
    public static class PredictorEncoder {
        final byte[] aValues;
        final byte[] alphaImageData;
        final byte[] bValues;
        private final int bytesPerComponent = 1;
        private final int bytesPerPixel;
        final byte[] cValues;
        private final byte[] dataRawRowAverage;
        private final byte[] dataRawRowNone;
        private final byte[] dataRawRowPaeth;
        private final byte[] dataRawRowSub;
        private final byte[] dataRawRowUp;
        private final PDDocument document;
        final boolean hasAlpha;
        private final int height;
        private final Bitmap image;
        final Bitmap.Config imageType;
        final byte[] tmpResultValues;
        private final int width;
        final byte[] xValues;

        private static byte pngFilterSub(int i, int i2) {
            return (byte) ((i & 255) - (i2 & 255));
        }

        PredictorEncoder(PDDocument pDDocument, Bitmap bitmap) {
            this.document = pDDocument;
            this.image = bitmap;
            int i = 1 * 3;
            this.bytesPerPixel = i;
            int height = bitmap.getHeight();
            this.height = height;
            int width = bitmap.getWidth();
            this.width = width;
            this.imageType = bitmap.getConfig();
            boolean hasAlpha = bitmap.hasAlpha();
            this.hasAlpha = hasAlpha;
            this.alphaImageData = hasAlpha ? new byte[height * width * 1] : null;
            int i2 = (width * i) + 1;
            byte[] bArr = new byte[i2];
            this.dataRawRowNone = bArr;
            byte[] bArr2 = new byte[i2];
            this.dataRawRowSub = bArr2;
            byte[] bArr3 = new byte[i2];
            this.dataRawRowUp = bArr3;
            byte[] bArr4 = new byte[i2];
            this.dataRawRowAverage = bArr4;
            byte[] bArr5 = new byte[i2];
            this.dataRawRowPaeth = bArr5;
            bArr[0] = 0;
            bArr2[0] = 1;
            bArr3[0] = 2;
            bArr4[0] = 3;
            bArr5[0] = 4;
            this.aValues = new byte[i];
            this.cValues = new byte[i];
            this.bValues = new byte[i];
            this.xValues = new byte[i];
            this.tmpResultValues = new byte[i];
        }

        PDImageXObject encode() throws IOException {
            int i = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[this.imageType.ordinal()];
            int i2 = 1;
            if (i == 1 || i == 2) {
                int i3 = this.width;
                int i4 = i3 * 1;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(((this.height * this.width) * this.bytesPerPixel) / 2);
                Deflater deflater = new Deflater(Filter.getCompressionLevel());
                DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
                byte b = 0;
                int[] iArr = new int[i3 * 1];
                int[] iArr2 = new int[i3 * 1];
                int i5 = 0;
                int i6 = 0;
                while (i6 < this.height) {
                    Bitmap bitmap = this.image;
                    int i7 = this.width;
                    bitmap.getPixels(iArr2, 0, i7, 0, i6, i7, 1);
                    Arrays.fill(this.aValues, b);
                    Arrays.fill(this.cValues, b);
                    int i8 = i5;
                    int i9 = i2;
                    int i10 = b;
                    while (i10 < i4) {
                        int i11 = i10;
                        int i12 = i6;
                        copyIntToBytes(iArr2, i11, this.xValues, this.alphaImageData, i8);
                        copyIntToBytes(iArr, i11, this.bValues, null, 0);
                        int length = this.xValues.length;
                        for (int i13 = b; i13 < length; i13++) {
                            int i14 = this.xValues[i13] & UByte.MAX_VALUE;
                            int i15 = this.aValues[i13] & UByte.MAX_VALUE;
                            int i16 = this.bValues[i13] & UByte.MAX_VALUE;
                            int i17 = this.cValues[i13] & UByte.MAX_VALUE;
                            this.dataRawRowNone[i9] = (byte) i14;
                            this.dataRawRowSub[i9] = pngFilterSub(i14, i15);
                            this.dataRawRowUp[i9] = pngFilterUp(i14, i16);
                            this.dataRawRowAverage[i9] = pngFilterAverage(i14, i15, i16);
                            this.dataRawRowPaeth[i9] = pngFilterPaeth(i14, i15, i16, i17);
                            i9++;
                        }
                        System.arraycopy(this.xValues, 0, this.aValues, 0, this.bytesPerPixel);
                        System.arraycopy(this.bValues, 0, this.cValues, 0, this.bytesPerPixel);
                        i10++;
                        i8 += this.bytesPerComponent;
                        b = 0;
                        i6 = i12;
                    }
                    byte[] chooseDataRowToWrite = chooseDataRowToWrite();
                    deflaterOutputStream.write(chooseDataRowToWrite, b, chooseDataRowToWrite.length);
                    i6++;
                    i5 = i8;
                    i2 = 1;
                    int[] iArr3 = iArr;
                    iArr = iArr2;
                    iArr2 = iArr3;
                }
                deflaterOutputStream.close();
                deflater.end();
                return preparePredictorPDImage(byteArrayOutputStream, this.bytesPerComponent * 8);
            }
            return null;
        }

        private void copyIntToBytes(int[] iArr, int i, byte[] bArr, byte[] bArr2, int i2) {
            int i3 = iArr[i];
            byte blue = (byte) Color.blue(i3);
            byte green = (byte) Color.green(i3);
            byte red = (byte) Color.red(i3);
            int i4 = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[this.imageType.ordinal()];
            if (i4 != 1) {
                if (i4 != 2) {
                    return;
                }
                bArr[0] = red;
                bArr[1] = green;
                bArr[2] = blue;
                return;
            }
            bArr[0] = red;
            bArr[1] = green;
            bArr[2] = blue;
            if (bArr2 != null) {
                bArr2[i2] = (byte) Color.alpha(i3);
            }
        }

        private PDImageXObject preparePredictorPDImage(ByteArrayOutputStream byteArrayOutputStream, int i) throws IOException {
            int height = this.image.getHeight();
            int width = this.image.getWidth();
            PDImageXObject pDImageXObject = new PDImageXObject(this.document, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.FLATE_DECODE, width, height, i, PDDeviceRGB.INSTANCE);
            COSDictionary cOSDictionary = new COSDictionary();
            cOSDictionary.setItem(COSName.BITS_PER_COMPONENT, (COSBase) COSInteger.get(i));
            cOSDictionary.setItem(COSName.PREDICTOR, (COSBase) COSInteger.get(15L));
            cOSDictionary.setItem(COSName.COLUMNS, (COSBase) COSInteger.get(width));
            cOSDictionary.setItem(COSName.COLORS, (COSBase) COSInteger.get(3L));
            pDImageXObject.getCOSObject().setItem(COSName.DECODE_PARMS, (COSBase) cOSDictionary);
            if (this.hasAlpha) {
                pDImageXObject.getCOSObject().setItem(COSName.SMASK, LosslessFactory.prepareImageXObject(this.document, this.alphaImageData, this.image.getWidth(), this.image.getHeight(), this.bytesPerComponent * 8, PDDeviceGray.INSTANCE));
            }
            return pDImageXObject;
        }

        private byte[] chooseDataRowToWrite() {
            byte[] bArr = this.dataRawRowNone;
            long estCompressSum = estCompressSum(bArr);
            long estCompressSum2 = estCompressSum(this.dataRawRowSub);
            long estCompressSum3 = estCompressSum(this.dataRawRowUp);
            long estCompressSum4 = estCompressSum(this.dataRawRowAverage);
            long estCompressSum5 = estCompressSum(this.dataRawRowPaeth);
            if (estCompressSum > estCompressSum2) {
                bArr = this.dataRawRowSub;
                estCompressSum = estCompressSum2;
            }
            if (estCompressSum > estCompressSum3) {
                bArr = this.dataRawRowUp;
            } else {
                estCompressSum3 = estCompressSum;
            }
            if (estCompressSum3 > estCompressSum4) {
                bArr = this.dataRawRowAverage;
            } else {
                estCompressSum4 = estCompressSum3;
            }
            return estCompressSum4 > estCompressSum5 ? this.dataRawRowPaeth : bArr;
        }

        private static byte pngFilterUp(int i, int i2) {
            return pngFilterSub(i, i2);
        }

        private static byte pngFilterAverage(int i, int i2, int i3) {
            return (byte) (i - ((i3 + i2) / 2));
        }

        private static byte pngFilterPaeth(int i, int i2, int i3, int i4) {
            int i5 = (i2 + i3) - i4;
            int abs = Math.abs(i5 - i2);
            int abs2 = Math.abs(i5 - i3);
            int abs3 = Math.abs(i5 - i4);
            if (abs > abs2 || abs > abs3) {
                i2 = abs2 <= abs3 ? i3 : i4;
            }
            return (byte) (i - i2);
        }

        private static long estCompressSum(byte[] bArr) {
            long j = 0;
            for (byte b : bArr) {
                j += Math.abs((int) b);
            }
            return j;
        }
    }

    /* renamed from: com.tom_roush.pdfbox.pdmodel.graphics.image.LosslessFactory$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            $SwitchMap$android$graphics$Bitmap$Config = iArr;
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
