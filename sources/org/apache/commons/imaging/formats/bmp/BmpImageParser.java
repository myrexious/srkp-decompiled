package org.apache.commons.imaging.formats.bmp;

import com.tom_roush.fontbox.ttf.OpenTypeScript;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.UByte;
import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.PixelDensity;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.bmp.BmpHeaderInfo;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.apache.commons.imaging.palette.SimplePalette;
import org.apache.xmpbox.type.ThumbnailType;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class BmpImageParser extends ImageParser<BmpImagingParameters> {
    private static final int BITMAP_FILE_HEADER_SIZE = 14;
    private static final int BITMAP_INFO_HEADER_SIZE = 40;
    private static final int BI_BITFIELDS = 3;
    private static final int BI_RGB = 0;
    private static final int BI_RLE4 = 2;
    private static final int BI_RLE8 = 1;
    private static final Logger LOGGER = Logger.getLogger(BmpImageParser.class.getName());
    private static final String DEFAULT_EXTENSION = ImageFormats.BMP.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.BMP.getExtensions();
    private static final byte[] BMP_HEADER_SIGNATURE = {BuiltinOptions.SquareOptions, BuiltinOptions.MirrorPadOptions};

    private String getBmpTypeDescription(int i, int i2) {
        return (i == 66 && i2 == 77) ? "Windows 3.1x, 95, NT," : (i == 66 && i2 == 65) ? "OS/2 Bitmap Array" : (i == 67 && i2 == 73) ? "OS/2 Color Icon" : (i == 67 && i2 == 80) ? "OS/2 Color Pointer" : (i == 73 && i2 == 67) ? "OS/2 Icon" : (i == 80 && i2 == 84) ? "OS/2 Pointer" : OpenTypeScript.UNKNOWN;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, BmpImagingParameters bmpImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, BmpImagingParameters bmpImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "Bmp-Custom";
    }

    public BmpImageParser() {
        super.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public BmpImagingParameters getDefaultParameters() {
        return new BmpImagingParameters();
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String[] getAcceptedExtensions() {
        return ACCEPTED_EXTENSIONS;
    }

    @Override // org.apache.commons.imaging.ImageParser
    protected ImageFormat[] getAcceptedTypes() {
        return new ImageFormat[]{ImageFormats.BMP};
    }

    private BmpHeaderInfo readBmpHeaderInfo(InputStream inputStream, FormatCompliance formatCompliance) throws ImageReadException, IOException {
        int read4Bytes;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        byte readByte = BinaryFunctions.readByte("Identifier1", inputStream, "Not a Valid BMP File");
        byte readByte2 = BinaryFunctions.readByte("Identifier2", inputStream, "Not a Valid BMP File");
        if (formatCompliance != null) {
            formatCompliance.compareBytes("Signature", BMP_HEADER_SIGNATURE, new byte[]{readByte, readByte2});
        }
        int read4Bytes2 = BinaryFunctions.read4Bytes("File Size", inputStream, "Not a Valid BMP File", getByteOrder());
        int read4Bytes3 = BinaryFunctions.read4Bytes("Reserved", inputStream, "Not a Valid BMP File", getByteOrder());
        int read4Bytes4 = BinaryFunctions.read4Bytes("Bitmap Data Offset", inputStream, "Not a Valid BMP File", getByteOrder());
        int read4Bytes5 = BinaryFunctions.read4Bytes("Bitmap Header Size", inputStream, "Not a Valid BMP File", getByteOrder());
        BmpHeaderInfo.ColorSpace colorSpace = new BmpHeaderInfo.ColorSpace();
        colorSpace.red = new BmpHeaderInfo.ColorSpaceCoordinate();
        colorSpace.green = new BmpHeaderInfo.ColorSpaceCoordinate();
        colorSpace.blue = new BmpHeaderInfo.ColorSpaceCoordinate();
        if (read4Bytes5 < 40) {
            throw new ImageReadException("Invalid/unsupported BMP file");
        }
        int read4Bytes6 = BinaryFunctions.read4Bytes("Width", inputStream, "Not a Valid BMP File", getByteOrder());
        int read4Bytes7 = BinaryFunctions.read4Bytes("Height", inputStream, "Not a Valid BMP File", getByteOrder());
        int read2Bytes = BinaryFunctions.read2Bytes("Planes", inputStream, "Not a Valid BMP File", getByteOrder());
        int read2Bytes2 = BinaryFunctions.read2Bytes("Bits Per Pixel", inputStream, "Not a Valid BMP File", getByteOrder());
        int read4Bytes8 = BinaryFunctions.read4Bytes("Compression", inputStream, "Not a Valid BMP File", getByteOrder());
        int read4Bytes9 = BinaryFunctions.read4Bytes("Bitmap Data Size", inputStream, "Not a Valid BMP File", getByteOrder());
        int read4Bytes10 = BinaryFunctions.read4Bytes("HResolution", inputStream, "Not a Valid BMP File", getByteOrder());
        int read4Bytes11 = BinaryFunctions.read4Bytes("VResolution", inputStream, "Not a Valid BMP File", getByteOrder());
        int read4Bytes12 = BinaryFunctions.read4Bytes("ColorsUsed", inputStream, "Not a Valid BMP File", getByteOrder());
        int read4Bytes13 = BinaryFunctions.read4Bytes("ColorsImportant", inputStream, "Not a Valid BMP File", getByteOrder());
        if (read4Bytes5 >= 52 || read4Bytes8 == 3) {
            int read4Bytes14 = BinaryFunctions.read4Bytes("RedMask", inputStream, "Not a Valid BMP File", getByteOrder());
            int read4Bytes15 = BinaryFunctions.read4Bytes("GreenMask", inputStream, "Not a Valid BMP File", getByteOrder());
            read4Bytes = BinaryFunctions.read4Bytes("BlueMask", inputStream, "Not a Valid BMP File", getByteOrder());
            i = read4Bytes14;
            i2 = read4Bytes15;
        } else {
            i = 0;
            i2 = 0;
            read4Bytes = 0;
        }
        if (read4Bytes5 >= 56) {
            i3 = i;
            i4 = BinaryFunctions.read4Bytes("AlphaMask", inputStream, "Not a Valid BMP File", getByteOrder());
        } else {
            i3 = i;
            i4 = 0;
        }
        if (read4Bytes5 >= 108) {
            i5 = i4;
            int read4Bytes16 = BinaryFunctions.read4Bytes("ColorSpaceType", inputStream, "Not a Valid BMP File", getByteOrder());
            i6 = read4Bytes8;
            colorSpace.red.x = BinaryFunctions.read4Bytes("ColorSpaceRedX", inputStream, "Not a Valid BMP File", getByteOrder());
            colorSpace.red.y = BinaryFunctions.read4Bytes("ColorSpaceRedY", inputStream, "Not a Valid BMP File", getByteOrder());
            colorSpace.red.z = BinaryFunctions.read4Bytes("ColorSpaceRedZ", inputStream, "Not a Valid BMP File", getByteOrder());
            colorSpace.green.x = BinaryFunctions.read4Bytes("ColorSpaceGreenX", inputStream, "Not a Valid BMP File", getByteOrder());
            colorSpace.green.y = BinaryFunctions.read4Bytes("ColorSpaceGreenY", inputStream, "Not a Valid BMP File", getByteOrder());
            colorSpace.green.z = BinaryFunctions.read4Bytes("ColorSpaceGreenZ", inputStream, "Not a Valid BMP File", getByteOrder());
            colorSpace.blue.x = BinaryFunctions.read4Bytes("ColorSpaceBlueX", inputStream, "Not a Valid BMP File", getByteOrder());
            colorSpace.blue.y = BinaryFunctions.read4Bytes("ColorSpaceBlueY", inputStream, "Not a Valid BMP File", getByteOrder());
            colorSpace.blue.z = BinaryFunctions.read4Bytes("ColorSpaceBlueZ", inputStream, "Not a Valid BMP File", getByteOrder());
            int read4Bytes17 = BinaryFunctions.read4Bytes("GammaRed", inputStream, "Not a Valid BMP File", getByteOrder());
            int read4Bytes18 = BinaryFunctions.read4Bytes("GammaGreen", inputStream, "Not a Valid BMP File", getByteOrder());
            i10 = BinaryFunctions.read4Bytes("GammaBlue", inputStream, "Not a Valid BMP File", getByteOrder());
            i7 = read4Bytes16;
            i8 = read4Bytes17;
            i9 = read4Bytes18;
        } else {
            i5 = i4;
            i6 = read4Bytes8;
            i7 = 0;
            i8 = 0;
            i9 = 0;
            i10 = 0;
        }
        if (read4Bytes5 >= 124) {
            i11 = i8;
            int read4Bytes19 = BinaryFunctions.read4Bytes("Intent", inputStream, "Not a Valid BMP File", getByteOrder());
            int read4Bytes20 = BinaryFunctions.read4Bytes("ProfileData", inputStream, "Not a Valid BMP File", getByteOrder());
            int read4Bytes21 = BinaryFunctions.read4Bytes("ProfileSize", inputStream, "Not a Valid BMP File", getByteOrder());
            i14 = BinaryFunctions.read4Bytes("Reserved", inputStream, "Not a Valid BMP File", getByteOrder());
            i15 = read4Bytes21;
            i12 = read4Bytes19;
            i13 = read4Bytes20;
        } else {
            i11 = i8;
            i12 = 0;
            i13 = 0;
            i14 = 0;
            i15 = 0;
        }
        int i30 = i14;
        if (LOGGER.isLoggable(Level.FINE)) {
            debugNumber("identifier1", readByte, 1);
            debugNumber("identifier2", readByte2, 1);
            debugNumber("fileSize", read4Bytes2, 4);
            debugNumber("reserved", read4Bytes3, 4);
            debugNumber("bitmapDataOffset", read4Bytes4, 4);
            debugNumber("bitmapHeaderSize", read4Bytes5, 4);
            debugNumber(ThumbnailType.WIDTH, read4Bytes6, 4);
            debugNumber(ThumbnailType.HEIGHT, read4Bytes7, 4);
            i17 = read4Bytes7;
            debugNumber("planes", read2Bytes, 2);
            debugNumber("bitsPerPixel", read2Bytes2, 2);
            int i31 = i6;
            debugNumber("compression", i31, 4);
            i25 = read4Bytes6;
            debugNumber("bitmapDataSize", read4Bytes9, 4);
            debugNumber("hResolution", read4Bytes10, 4);
            debugNumber("vResolution", read4Bytes11, 4);
            debugNumber("colorsUsed", read4Bytes12, 4);
            debugNumber("colorsImportant", read4Bytes13, 4);
            if (read4Bytes5 >= 52 || i31 == 3) {
                i22 = i31;
                debugNumber("redMask", i3, 4);
                i19 = read4Bytes13;
                i26 = i2;
                debugNumber("greenMask", i26, 4);
                i27 = read4Bytes;
                debugNumber("blueMask", i27, 4);
            } else {
                i19 = read4Bytes13;
                i22 = i31;
                i26 = i2;
                i27 = read4Bytes;
            }
            if (read4Bytes5 >= 56) {
                i23 = i27;
                i20 = i5;
                debugNumber("alphaMask", i20, 4);
            } else {
                i23 = i27;
                i20 = i5;
            }
            if (read4Bytes5 >= 108) {
                debugNumber("colorSpaceType", i7, 4);
                i2 = i26;
                debugNumber("colorSpace.red.x", colorSpace.red.x, 1);
                debugNumber("colorSpace.red.y", colorSpace.red.y, 1);
                debugNumber("colorSpace.red.z", colorSpace.red.z, 1);
                debugNumber("colorSpace.green.x", colorSpace.green.x, 1);
                debugNumber("colorSpace.green.y", colorSpace.green.y, 1);
                debugNumber("colorSpace.green.z", colorSpace.green.z, 1);
                debugNumber("colorSpace.blue.x", colorSpace.blue.x, 1);
                debugNumber("colorSpace.blue.y", colorSpace.blue.y, 1);
                debugNumber("colorSpace.blue.z", colorSpace.blue.z, 1);
                i28 = 4;
                debugNumber("gammaRed", i11, 4);
                i29 = i9;
                debugNumber("gammaGreen", i29, 4);
                i16 = i7;
                i24 = i10;
                debugNumber("gammaBlue", i24, 4);
            } else {
                i16 = i7;
                i2 = i26;
                i24 = i10;
                i28 = 4;
                i29 = i9;
            }
            if (read4Bytes5 >= 124) {
                debugNumber("intent", i12, i28);
                debugNumber("profileData", i13, i28);
                debugNumber("profileSize", i15, i28);
                i21 = i29;
                i18 = i30;
                debugNumber("reservedV5", i18, i28);
            } else {
                i21 = i29;
                i18 = i30;
            }
        } else {
            i16 = i7;
            i17 = read4Bytes7;
            i18 = i30;
            i19 = read4Bytes13;
            i20 = i5;
            i21 = i9;
            i22 = i6;
            i23 = read4Bytes;
            i24 = i10;
            i25 = read4Bytes6;
        }
        return new BmpHeaderInfo(readByte, readByte2, read4Bytes2, read4Bytes3, read4Bytes4, read4Bytes5, i25, i17, read2Bytes, read2Bytes2, i22, read4Bytes9, read4Bytes10, read4Bytes11, read4Bytes12, i19, i3, i2, i23, i20, i16, colorSpace, i11, i21, i24, i12, i13, i15, i18);
    }

    private byte[] getRLEBytes(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean z = false;
        while (!z) {
            int readByte = BinaryFunctions.readByte("RLE a", inputStream, "BMP: Bad RLE") & UByte.MAX_VALUE;
            byteArrayOutputStream.write(readByte);
            int readByte2 = BinaryFunctions.readByte("RLE b", inputStream, "BMP: Bad RLE") & UByte.MAX_VALUE;
            byteArrayOutputStream.write(readByte2);
            if (readByte == 0 && readByte2 != 0) {
                if (readByte2 == 1) {
                    z = true;
                } else if (readByte2 == 2) {
                    byteArrayOutputStream.write(BinaryFunctions.readByte("RLE c", inputStream, "BMP: Bad RLE") & UByte.MAX_VALUE);
                    byteArrayOutputStream.write(BinaryFunctions.readByte("RLE d", inputStream, "BMP: Bad RLE") & UByte.MAX_VALUE);
                } else {
                    int i2 = readByte2 / i;
                    if (readByte2 % i > 0) {
                        i2++;
                    }
                    if (i2 % 2 != 0) {
                        i2++;
                    }
                    byteArrayOutputStream.write(BinaryFunctions.readBytes("bytes", inputStream, i2, "RLE: Absolute Mode"));
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARN: Code restructure failed: missing block: B:115:0x005c, code lost:
        if (r2.bitsPerPixel <= 8) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x00a5, code lost:
        if (r2.bitsPerPixel <= 8) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x00a7, code lost:
        r3 = r3 * 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x00a9, code lost:
        r3 = 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:136:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x01ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.apache.commons.imaging.formats.bmp.BmpImageContents readImageContents(java.io.InputStream r17, org.apache.commons.imaging.FormatCompliance r18) throws org.apache.commons.imaging.ImageReadException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 514
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.bmp.BmpImageParser.readImageContents(java.io.InputStream, org.apache.commons.imaging.FormatCompliance):org.apache.commons.imaging.formats.bmp.BmpImageContents");
    }

    private BmpHeaderInfo readBmpHeaderInfo(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            BmpHeaderInfo readBmpHeaderInfo = readBmpHeaderInfo(inputStream, null);
            if (inputStream != null) {
                inputStream.close();
            }
            return readBmpHeaderInfo;
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
    public Dimension getImageSize(ByteSource byteSource, BmpImagingParameters bmpImagingParameters) throws ImageReadException, IOException {
        BmpHeaderInfo readBmpHeaderInfo = readBmpHeaderInfo(byteSource);
        return new Dimension(readBmpHeaderInfo.width, readBmpHeaderInfo.height);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, BmpImagingParameters bmpImagingParameters) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            BmpImageContents readImageContents = readImageContents(inputStream, FormatCompliance.getDefault());
            if (inputStream != null) {
                inputStream.close();
            }
            BmpHeaderInfo bmpHeaderInfo = readImageContents.bhi;
            byte[] bArr = readImageContents.colorTable;
            if (bmpHeaderInfo == null) {
                throw new ImageReadException("BMP: couldn't read header");
            }
            int i = bmpHeaderInfo.height;
            int i2 = bmpHeaderInfo.width;
            ArrayList arrayList = new ArrayList();
            int i3 = bmpHeaderInfo.bitsPerPixel;
            ImageFormats imageFormats = ImageFormats.BMP;
            int round = (int) Math.round(bmpHeaderInfo.hResolution * 0.0254d);
            float f = (float) (i2 / round);
            int round2 = (int) Math.round(bmpHeaderInfo.vResolution * 0.0254d);
            return new ImageInfo("Bmp (" + ((char) bmpHeaderInfo.identifier1) + ((char) bmpHeaderInfo.identifier2) + ": " + getBmpTypeDescription(bmpHeaderInfo.identifier1, bmpHeaderInfo.identifier2) + ")", i3, arrayList, imageFormats, "BMP Windows Bitmap", i, "image/x-ms-bmp", -1, round2, (float) (i / round2), round, f, i2, false, false, bArr != null, ImageInfo.ColorType.RGB, ImageInfo.CompressionAlgorithm.RLE);
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
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        printWriter.println("bmp.dumpImageFile");
        getImageInfo(byteSource, (BmpImagingParameters) null).toString(printWriter, "");
        printWriter.println("");
        return true;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public FormatCompliance getFormatCompliance(ByteSource byteSource) throws ImageReadException, IOException {
        FormatCompliance formatCompliance = new FormatCompliance(byteSource.getDescription());
        InputStream inputStream = byteSource.getInputStream();
        try {
            readImageContents(inputStream, formatCompliance);
            if (inputStream != null) {
                inputStream.close();
            }
            return formatCompliance;
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
    public BufferedImage getBufferedImage(ByteSource byteSource, BmpImagingParameters bmpImagingParameters) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            BufferedImage bufferedImage = getBufferedImage(inputStream, bmpImagingParameters);
            if (inputStream != null) {
                inputStream.close();
            }
            return bufferedImage;
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

    public BufferedImage getBufferedImage(InputStream inputStream, BmpImagingParameters bmpImagingParameters) throws ImageReadException, IOException {
        BmpImageContents readImageContents = readImageContents(inputStream, FormatCompliance.getDefault());
        BmpHeaderInfo bmpHeaderInfo = readImageContents.bhi;
        int i = bmpHeaderInfo.width;
        int i2 = bmpHeaderInfo.height;
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("width: " + i);
            logger.fine("height: " + i2);
            int i3 = i * i2;
            logger.fine("width*height: " + i3);
            logger.fine("width*height*4: " + (i3 * 4));
        }
        PixelParser pixelParser = readImageContents.pixelParser;
        ImageBuilder imageBuilder = new ImageBuilder(i, i2, true);
        pixelParser.processImage(imageBuilder);
        return imageBuilder.getBufferedImage();
    }

    @Override // org.apache.commons.imaging.ImageParser
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, BmpImagingParameters bmpImagingParameters) throws ImageWriteException, IOException {
        BmpWriter bmpWriterPalette;
        if (bmpImagingParameters == null) {
            bmpImagingParameters = new BmpImagingParameters();
        }
        PixelDensity pixelDensity = bmpImagingParameters.getPixelDensity();
        SimplePalette makeExactRgbPaletteSimple = new PaletteFactory().makeExactRgbPaletteSimple(bufferedImage, 256);
        if (makeExactRgbPaletteSimple == null) {
            bmpWriterPalette = new BmpWriterRgb();
        } else {
            bmpWriterPalette = new BmpWriterPalette(makeExactRgbPaletteSimple);
        }
        byte[] imageData = bmpWriterPalette.getImageData(bufferedImage);
        BinaryOutputStream binaryOutputStream = new BinaryOutputStream(outputStream, ByteOrder.LITTLE_ENDIAN);
        outputStream.write(66);
        outputStream.write(77);
        binaryOutputStream.write4Bytes((bmpWriterPalette.getPaletteSize() * 4) + 54 + imageData.length);
        binaryOutputStream.write4Bytes(0);
        binaryOutputStream.write4Bytes((bmpWriterPalette.getPaletteSize() * 4) + 54);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        binaryOutputStream.write4Bytes(40);
        binaryOutputStream.write4Bytes(width);
        binaryOutputStream.write4Bytes(height);
        binaryOutputStream.write2Bytes(1);
        binaryOutputStream.write2Bytes(bmpWriterPalette.getBitsPerPixel());
        binaryOutputStream.write4Bytes(0);
        binaryOutputStream.write4Bytes(imageData.length);
        binaryOutputStream.write4Bytes(pixelDensity != null ? (int) Math.round(pixelDensity.horizontalDensityMetres()) : 0);
        binaryOutputStream.write4Bytes(pixelDensity != null ? (int) Math.round(pixelDensity.verticalDensityMetres()) : 0);
        if (makeExactRgbPaletteSimple == null) {
            binaryOutputStream.write4Bytes(0);
        } else {
            binaryOutputStream.write4Bytes(makeExactRgbPaletteSimple.length());
        }
        binaryOutputStream.write4Bytes(0);
        bmpWriterPalette.writePalette(binaryOutputStream);
        binaryOutputStream.write(imageData);
    }
}
