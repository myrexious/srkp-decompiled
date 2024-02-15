package org.apache.commons.imaging.formats.png;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.GenericImageMetadata;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.XmpEmbeddable;
import org.apache.commons.imaging.common.XmpImagingParameters;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.png.chunks.PngChunk;
import org.apache.commons.imaging.formats.png.chunks.PngChunkGama;
import org.apache.commons.imaging.formats.png.chunks.PngChunkIccp;
import org.apache.commons.imaging.formats.png.chunks.PngChunkIdat;
import org.apache.commons.imaging.formats.png.chunks.PngChunkIhdr;
import org.apache.commons.imaging.formats.png.chunks.PngChunkItxt;
import org.apache.commons.imaging.formats.png.chunks.PngChunkPhys;
import org.apache.commons.imaging.formats.png.chunks.PngChunkPlte;
import org.apache.commons.imaging.formats.png.chunks.PngChunkScal;
import org.apache.commons.imaging.formats.png.chunks.PngChunkText;
import org.apache.commons.imaging.formats.png.chunks.PngChunkZtxt;
import org.apache.commons.imaging.formats.png.chunks.PngTextChunk;
import org.apache.commons.imaging.formats.png.transparencyfilters.TransparencyFilter;
import org.apache.commons.imaging.formats.png.transparencyfilters.TransparencyFilterGrayscale;
import org.apache.commons.imaging.formats.png.transparencyfilters.TransparencyFilterIndexedColor;
import org.apache.commons.imaging.formats.png.transparencyfilters.TransparencyFilterTrueColor;

/* loaded from: classes2.dex */
public class PngImageParser extends ImageParser<PngImagingParameters> implements XmpEmbeddable {
    private static final Logger LOGGER = Logger.getLogger(PngImageParser.class.getName());
    private static final String DEFAULT_EXTENSION = ImageFormats.PNG.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.PNG.getExtensions();

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "Png-Custom";
    }

    @Override // org.apache.commons.imaging.ImageParser
    public PngImagingParameters getDefaultParameters() {
        return new PngImagingParameters();
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String[] getAcceptedExtensions() {
        return (String[]) ACCEPTED_EXTENSIONS.clone();
    }

    @Override // org.apache.commons.imaging.ImageParser
    protected ImageFormat[] getAcceptedTypes() {
        return new ImageFormat[]{ImageFormats.PNG};
    }

    public static String getChunkTypeName(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) ((i >> 24) & 255));
        sb.append((char) ((i >> 16) & 255));
        sb.append((char) ((i >> 8) & 255));
        sb.append((char) ((i >> 0) & 255));
        return sb.toString();
    }

    public List<String> getChunkTypes(InputStream inputStream) throws ImageReadException, IOException {
        List<PngChunk> readChunks = readChunks(inputStream, (ChunkType[]) null, false);
        ArrayList arrayList = new ArrayList(readChunks.size());
        for (PngChunk pngChunk : readChunks) {
            arrayList.add(getChunkTypeName(pngChunk.chunkType));
        }
        return arrayList;
    }

    public boolean hasChunkType(ByteSource byteSource, ChunkType chunkType) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            readSignature(inputStream);
            boolean z = !readChunks(inputStream, new ChunkType[]{chunkType}, true).isEmpty();
            if (inputStream != null) {
                inputStream.close();
            }
            return z;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private boolean keepChunk(int i, ChunkType[] chunkTypeArr) {
        if (chunkTypeArr == null) {
            return true;
        }
        for (ChunkType chunkType : chunkTypeArr) {
            if (chunkType.value == i) {
                return true;
            }
        }
        return false;
    }

    private List<PngChunk> readChunks(InputStream inputStream, ChunkType[] chunkTypeArr, boolean z) throws ImageReadException, IOException {
        int read4Bytes;
        byte[] bArr;
        ArrayList arrayList = new ArrayList();
        do {
            int read4Bytes2 = BinaryFunctions.read4Bytes("Length", inputStream, "Not a Valid PNG File", getByteOrder());
            if (read4Bytes2 < 0) {
                throw new ImageReadException("Invalid PNG chunk length: " + read4Bytes2);
            }
            read4Bytes = BinaryFunctions.read4Bytes("ChunkType", inputStream, "Not a Valid PNG File", getByteOrder());
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINEST)) {
                BinaryFunctions.printCharQuad("ChunkType", read4Bytes);
                debugNumber("Length", read4Bytes2, 4);
            }
            boolean keepChunk = keepChunk(read4Bytes, chunkTypeArr);
            if (keepChunk) {
                bArr = BinaryFunctions.readBytes("Chunk Data", inputStream, read4Bytes2, "Not a Valid PNG File: Couldn't read Chunk Data.");
            } else {
                BinaryFunctions.skipBytes(inputStream, read4Bytes2, "Not a Valid PNG File");
                bArr = null;
            }
            if (logger.isLoggable(Level.FINEST) && bArr != null) {
                debugNumber("bytes", bArr.length, 4);
            }
            int read4Bytes3 = BinaryFunctions.read4Bytes("CRC", inputStream, "Not a Valid PNG File", getByteOrder());
            if (keepChunk) {
                if (read4Bytes == ChunkType.iCCP.value) {
                    arrayList.add(new PngChunkIccp(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                } else if (read4Bytes == ChunkType.tEXt.value) {
                    arrayList.add(new PngChunkText(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                } else if (read4Bytes == ChunkType.zTXt.value) {
                    arrayList.add(new PngChunkZtxt(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                } else if (read4Bytes == ChunkType.IHDR.value) {
                    arrayList.add(new PngChunkIhdr(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                } else if (read4Bytes == ChunkType.PLTE.value) {
                    arrayList.add(new PngChunkPlte(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                } else if (read4Bytes == ChunkType.pHYs.value) {
                    arrayList.add(new PngChunkPhys(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                } else if (read4Bytes == ChunkType.sCAL.value) {
                    arrayList.add(new PngChunkScal(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                } else if (read4Bytes == ChunkType.IDAT.value) {
                    arrayList.add(new PngChunkIdat(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                } else if (read4Bytes == ChunkType.gAMA.value) {
                    arrayList.add(new PngChunkGama(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                } else if (read4Bytes == ChunkType.iTXt.value) {
                    arrayList.add(new PngChunkItxt(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                } else {
                    arrayList.add(new PngChunk(read4Bytes2, read4Bytes, read4Bytes3, bArr));
                }
                if (z) {
                    return arrayList;
                }
            }
        } while (read4Bytes != ChunkType.IEND.value);
        return arrayList;
    }

    public void readSignature(InputStream inputStream) throws ImageReadException, IOException {
        BinaryFunctions.readAndVerifyBytes(inputStream, PngConstants.PNG_SIGNATURE, "Not a Valid PNG Segment: Incorrect Signature");
    }

    private List<PngChunk> readChunks(ByteSource byteSource, ChunkType[] chunkTypeArr, boolean z) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            readSignature(inputStream);
            List<PngChunk> readChunks = readChunks(inputStream, chunkTypeArr, z);
            if (inputStream != null) {
                inputStream.close();
            }
            return readChunks;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, PngImagingParameters pngImagingParameters) throws ImageReadException, IOException {
        List<PngChunk> readChunks = readChunks(byteSource, new ChunkType[]{ChunkType.iCCP}, true);
        if (readChunks.isEmpty()) {
            return null;
        }
        if (readChunks.size() > 1) {
            throw new ImageReadException("PNG contains more than one ICC Profile ");
        }
        return ((PngChunkIccp) readChunks.get(0)).getUncompressedProfile();
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, PngImagingParameters pngImagingParameters) throws ImageReadException, IOException {
        List<PngChunk> readChunks = readChunks(byteSource, new ChunkType[]{ChunkType.IHDR}, true);
        if (readChunks.isEmpty()) {
            throw new ImageReadException("Png: No chunks");
        }
        if (readChunks.size() > 1) {
            throw new ImageReadException("PNG contains more than one Header");
        }
        PngChunkIhdr pngChunkIhdr = (PngChunkIhdr) readChunks.get(0);
        return new Dimension(pngChunkIhdr.width, pngChunkIhdr.height);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, PngImagingParameters pngImagingParameters) throws ImageReadException, IOException {
        List<PngChunk> readChunks = readChunks(byteSource, new ChunkType[]{ChunkType.tEXt, ChunkType.zTXt}, false);
        if (readChunks.isEmpty()) {
            return null;
        }
        GenericImageMetadata genericImageMetadata = new GenericImageMetadata();
        Iterator<PngChunk> it = readChunks.iterator();
        while (it.hasNext()) {
            PngTextChunk pngTextChunk = (PngTextChunk) it.next();
            genericImageMetadata.add(pngTextChunk.getKeyword(), pngTextChunk.getText());
        }
        return genericImageMetadata;
    }

    private List<PngChunk> filterChunks(List<PngChunk> list, ChunkType chunkType) {
        ArrayList arrayList = new ArrayList();
        for (PngChunk pngChunk : list) {
            if (pngChunk.chunkType == chunkType.value) {
                arrayList.add(pngChunk);
            }
        }
        return arrayList;
    }

    private TransparencyFilter getTransparencyFilter(PngColorType pngColorType, PngChunk pngChunk) throws ImageReadException, IOException {
        int i = AnonymousClass1.$SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[pngColorType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return new TransparencyFilterIndexedColor(pngChunk.getBytes());
                }
                throw new ImageReadException("Simple Transparency not compatible with ColorType: " + pngColorType);
            }
            return new TransparencyFilterTrueColor(pngChunk.getBytes());
        }
        return new TransparencyFilterGrayscale(pngChunk.getBytes());
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, PngImagingParameters pngImagingParameters) throws ImageReadException, IOException {
        ArrayList arrayList;
        float f;
        int i;
        float f2;
        int i2;
        ImageInfo.ColorType colorType;
        List<PngChunk> readChunks = readChunks(byteSource, new ChunkType[]{ChunkType.IHDR, ChunkType.pHYs, ChunkType.sCAL, ChunkType.tEXt, ChunkType.zTXt, ChunkType.tRNS, ChunkType.PLTE, ChunkType.iTXt}, false);
        if (readChunks.isEmpty()) {
            throw new ImageReadException("PNG: no chunks");
        }
        List<PngChunk> filterChunks = filterChunks(readChunks, ChunkType.IHDR);
        if (filterChunks.size() != 1) {
            throw new ImageReadException("PNG contains more than one Header");
        }
        PngChunkIhdr pngChunkIhdr = (PngChunkIhdr) filterChunks.get(0);
        boolean hasAlpha = !filterChunks(readChunks, ChunkType.tRNS).isEmpty() ? true : pngChunkIhdr.pngColorType.hasAlpha();
        List<PngChunk> filterChunks2 = filterChunks(readChunks, ChunkType.pHYs);
        if (filterChunks2.size() > 1) {
            throw new ImageReadException("PNG contains more than one pHYs: " + filterChunks2.size());
        }
        PngChunkPhys pngChunkPhys = filterChunks2.size() == 1 ? (PngChunkPhys) filterChunks2.get(0) : null;
        PhysicalScale physicalScale = PhysicalScale.UNDEFINED;
        List<PngChunk> filterChunks3 = filterChunks(readChunks, ChunkType.sCAL);
        if (filterChunks3.size() > 1) {
            throw new ImageReadException("PNG contains more than one sCAL:" + filterChunks3.size());
        }
        if (filterChunks3.size() == 1) {
            PngChunkScal pngChunkScal = (PngChunkScal) filterChunks3.get(0);
            if (pngChunkScal.unitSpecifier == 1) {
                physicalScale = PhysicalScale.createFromMeters(pngChunkScal.unitsPerPixelXAxis, pngChunkScal.unitsPerPixelYAxis);
            } else {
                physicalScale = PhysicalScale.createFromRadians(pngChunkScal.unitsPerPixelXAxis, pngChunkScal.unitsPerPixelYAxis);
            }
        }
        PhysicalScale physicalScale2 = physicalScale;
        List<PngChunk> filterChunks4 = filterChunks(readChunks, ChunkType.tEXt);
        List<PngChunk> filterChunks5 = filterChunks(readChunks, ChunkType.zTXt);
        List<PngChunk> filterChunks6 = filterChunks(readChunks, ChunkType.iTXt);
        int size = filterChunks4.size() + filterChunks5.size() + filterChunks6.size();
        ArrayList arrayList2 = new ArrayList(size);
        ArrayList arrayList3 = new ArrayList(size);
        Iterator<PngChunk> it = filterChunks4.iterator();
        while (it.hasNext()) {
            PngChunkText pngChunkText = (PngChunkText) it.next();
            arrayList2.add(pngChunkText.keyword + ": " + pngChunkText.text);
            arrayList3.add(pngChunkText.getContents());
        }
        Iterator<PngChunk> it2 = filterChunks5.iterator();
        while (it2.hasNext()) {
            PngChunkZtxt pngChunkZtxt = (PngChunkZtxt) it2.next();
            arrayList2.add(pngChunkZtxt.keyword + ": " + pngChunkZtxt.text);
            arrayList3.add(pngChunkZtxt.getContents());
        }
        Iterator<PngChunk> it3 = filterChunks6.iterator();
        while (it3.hasNext()) {
            PngChunkItxt pngChunkItxt = (PngChunkItxt) it3.next();
            arrayList2.add(pngChunkItxt.keyword + ": " + pngChunkItxt.text);
            arrayList3.add(pngChunkItxt.getContents());
        }
        int samplesPerPixel = pngChunkIhdr.bitDepth * pngChunkIhdr.pngColorType.getSamplesPerPixel();
        ImageFormats imageFormats = ImageFormats.PNG;
        int i3 = pngChunkIhdr.height;
        int i4 = pngChunkIhdr.width;
        boolean isProgressive = pngChunkIhdr.interlaceMethod.isProgressive();
        if (pngChunkPhys == null || pngChunkPhys.unitSpecifier != 1) {
            arrayList = arrayList2;
            f = -1.0f;
            i = -1;
            f2 = -1.0f;
            i2 = -1;
        } else {
            arrayList = arrayList2;
            f = (float) (i3 / (pngChunkPhys.pixelsPerUnitYAxis * 0.0254d));
            i2 = (int) Math.round(pngChunkPhys.pixelsPerUnitYAxis * 0.0254d);
            i = (int) Math.round(pngChunkPhys.pixelsPerUnitXAxis * 0.0254d);
            f2 = (float) (i4 / (pngChunkPhys.pixelsPerUnitXAxis * 0.0254d));
        }
        boolean z = filterChunks(readChunks, ChunkType.PLTE).size() > 1;
        int i5 = AnonymousClass1.$SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[pngChunkIhdr.pngColorType.ordinal()];
        if (i5 != 1) {
            if (i5 != 2 && i5 != 3) {
                if (i5 != 4) {
                    if (i5 != 5) {
                        throw new ImageReadException("Png: Unknown ColorType: " + pngChunkIhdr.pngColorType);
                    }
                }
            }
            colorType = ImageInfo.ColorType.RGB;
            return new PngImageInfo("Png", samplesPerPixel, arrayList, imageFormats, "PNG Portable Network Graphics", i3, "image/png", 1, i2, f, i, f2, i4, isProgressive, hasAlpha, z, colorType, ImageInfo.CompressionAlgorithm.PNG_FILTER, arrayList3, physicalScale2);
        }
        colorType = ImageInfo.ColorType.GRAYSCALE;
        return new PngImageInfo("Png", samplesPerPixel, arrayList, imageFormats, "PNG Portable Network Graphics", i3, "image/png", 1, i2, f, i, f2, i4, isProgressive, hasAlpha, z, colorType, ImageInfo.CompressionAlgorithm.PNG_FILTER, arrayList3, physicalScale2);
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x01ea  */
    @Override // org.apache.commons.imaging.ImageParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.awt.image.BufferedImage getBufferedImage(org.apache.commons.imaging.common.bytesource.ByteSource r22, org.apache.commons.imaging.formats.png.PngImagingParameters r23) throws org.apache.commons.imaging.ImageReadException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 569
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.png.PngImageParser.getBufferedImage(org.apache.commons.imaging.common.bytesource.ByteSource, org.apache.commons.imaging.formats.png.PngImagingParameters):java.awt.image.BufferedImage");
    }

    /* renamed from: org.apache.commons.imaging.formats.png.PngImageParser$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$imaging$formats$png$InterlaceMethod;
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType;

        static {
            int[] iArr = new int[InterlaceMethod.values().length];
            $SwitchMap$org$apache$commons$imaging$formats$png$InterlaceMethod = iArr;
            try {
                iArr[InterlaceMethod.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$InterlaceMethod[InterlaceMethod.ADAM7.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[PngColorType.values().length];
            $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType = iArr2;
            try {
                iArr2[PngColorType.GREYSCALE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[PngColorType.TRUE_COLOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[PngColorType.INDEXED_COLOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[PngColorType.GREYSCALE_WITH_ALPHA.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[PngColorType.TRUE_COLOR_WITH_ALPHA.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // org.apache.commons.imaging.ImageParser
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        ImageInfo imageInfo = getImageInfo(byteSource);
        if (imageInfo == null) {
            return false;
        }
        imageInfo.toString(printWriter, "");
        List<PngChunk> readChunks = readChunks(byteSource, (ChunkType[]) null, false);
        List<PngChunk> filterChunks = filterChunks(readChunks, ChunkType.IHDR);
        if (filterChunks.size() != 1) {
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest("PNG contains more than one Header");
            }
            return false;
        }
        printWriter.println("Color: " + ((PngChunkIhdr) filterChunks.get(0)).pngColorType.name());
        printWriter.println("chunks: " + readChunks.size());
        if (readChunks.isEmpty()) {
            return false;
        }
        for (int i = 0; i < readChunks.size(); i++) {
            BinaryFunctions.printCharQuad(printWriter, "\t" + i + ": ", readChunks.get(i).chunkType);
        }
        printWriter.println("");
        printWriter.flush();
        return true;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, PngImagingParameters pngImagingParameters) throws ImageWriteException, IOException {
        new PngWriter().writeImage(bufferedImage, outputStream, pngImagingParameters);
    }

    @Override // org.apache.commons.imaging.common.XmpEmbeddable
    public String getXmpXml(ByteSource byteSource, XmpImagingParameters xmpImagingParameters) throws ImageReadException, IOException {
        List<PngChunk> readChunks = readChunks(byteSource, new ChunkType[]{ChunkType.iTXt}, false);
        if (readChunks.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<PngChunk> it = readChunks.iterator();
        while (it.hasNext()) {
            PngChunkItxt pngChunkItxt = (PngChunkItxt) it.next();
            if (pngChunkItxt.getKeyword().equals(PngConstants.XMP_KEYWORD)) {
                arrayList.add(pngChunkItxt);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() > 1) {
            throw new ImageReadException("PNG contains more than one XMP chunk.");
        }
        return ((PngChunkItxt) arrayList.get(0)).getText();
    }
}
