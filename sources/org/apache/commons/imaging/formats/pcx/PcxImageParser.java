package org.apache.commons.imaging.formats.pcx;

import androidx.core.view.ViewCompat;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UByte;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.bytesource.ByteSource;

/* loaded from: classes2.dex */
public class PcxImageParser extends ImageParser<PcxImagingParameters> {
    private static final String DEFAULT_EXTENSION = ImageFormats.PCX.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.PCX.getExtensions();

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, PcxImagingParameters pcxImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, PcxImagingParameters pcxImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "Pcx-Custom";
    }

    public PcxImageParser() {
        super.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public PcxImagingParameters getDefaultParameters() {
        return new PcxImagingParameters();
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
        return new ImageFormat[]{ImageFormats.PCX};
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, PcxImagingParameters pcxImagingParameters) throws ImageReadException, IOException {
        PcxHeader readPcxHeader = readPcxHeader(byteSource);
        Dimension imageSize = getImageSize(byteSource, pcxImagingParameters);
        return new ImageInfo("PCX", readPcxHeader.bitsPerPixel * readPcxHeader.nPlanes, new ArrayList(), ImageFormats.PCX, "ZSoft PCX Image", imageSize.height, "image/x-pcx", 1, readPcxHeader.vDpi, (float) Math.round(imageSize.getHeight() / readPcxHeader.vDpi), readPcxHeader.hDpi, (float) Math.round(imageSize.getWidth() / readPcxHeader.hDpi), imageSize.width, false, false, (readPcxHeader.nPlanes == 3 && readPcxHeader.bitsPerPixel == 8) ? false : true, ImageInfo.ColorType.RGB, readPcxHeader.encoding == 1 ? ImageInfo.CompressionAlgorithm.RLE : ImageInfo.CompressionAlgorithm.NONE);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, PcxImagingParameters pcxImagingParameters) throws ImageReadException, IOException {
        PcxHeader readPcxHeader = readPcxHeader(byteSource);
        int i = (readPcxHeader.xMax - readPcxHeader.xMin) + 1;
        if (i < 0) {
            throw new ImageReadException("Image width is negative");
        }
        int i2 = (readPcxHeader.yMax - readPcxHeader.yMin) + 1;
        if (i2 < 0) {
            throw new ImageReadException("Image height is negative");
        }
        return new Dimension(i, i2);
    }

    /* loaded from: classes2.dex */
    public static class PcxHeader {
        public static final int ENCODING_RLE = 1;
        public static final int ENCODING_UNCOMPRESSED = 0;
        public static final int PALETTE_INFO_COLOR = 1;
        public static final int PALETTE_INFO_GRAYSCALE = 2;
        public final int bitsPerPixel;
        public final int bytesPerLine;
        public final int[] colormap;
        public final int encoding;
        public final int hDpi;
        public final int hScreenSize;
        public final int manufacturer;
        public final int nPlanes;
        public final int paletteInfo;
        public final int reserved;
        public final int vDpi;
        public final int vScreenSize;
        public final int version;
        public final int xMax;
        public final int xMin;
        public final int yMax;
        public final int yMin;

        PcxHeader(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int[] iArr, int i11, int i12, int i13, int i14, int i15, int i16) {
            this.manufacturer = i;
            this.version = i2;
            this.encoding = i3;
            this.bitsPerPixel = i4;
            this.xMin = i5;
            this.yMin = i6;
            this.xMax = i7;
            this.yMax = i8;
            this.hDpi = i9;
            this.vDpi = i10;
            this.colormap = iArr;
            this.reserved = i11;
            this.nPlanes = i12;
            this.bytesPerLine = i13;
            this.paletteInfo = i14;
            this.hScreenSize = i15;
            this.vScreenSize = i16;
        }

        public void dump(PrintWriter printWriter) {
            printWriter.println("PcxHeader");
            printWriter.println("Manufacturer: " + this.manufacturer);
            printWriter.println("Version: " + this.version);
            printWriter.println("Encoding: " + this.encoding);
            printWriter.println("BitsPerPixel: " + this.bitsPerPixel);
            printWriter.println("xMin: " + this.xMin);
            printWriter.println("yMin: " + this.yMin);
            printWriter.println("xMax: " + this.xMax);
            printWriter.println("yMax: " + this.yMax);
            printWriter.println("hDpi: " + this.hDpi);
            printWriter.println("vDpi: " + this.vDpi);
            printWriter.print("ColorMap: ");
            for (int i = 0; i < this.colormap.length; i++) {
                if (i > 0) {
                    printWriter.print(",");
                }
                printWriter.print("(" + ((this.colormap[i] >> 16) & 255) + "," + ((this.colormap[i] >> 8) & 255) + "," + (this.colormap[i] & 255) + ")");
            }
            printWriter.println();
            printWriter.println("Reserved: " + this.reserved);
            printWriter.println("nPlanes: " + this.nPlanes);
            printWriter.println("BytesPerLine: " + this.bytesPerLine);
            printWriter.println("PaletteInfo: " + this.paletteInfo);
            printWriter.println("hScreenSize: " + this.hScreenSize);
            printWriter.println("vScreenSize: " + this.vScreenSize);
            printWriter.println();
        }
    }

    private PcxHeader readPcxHeader(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            PcxHeader readPcxHeader = readPcxHeader(inputStream, false);
            if (inputStream != null) {
                inputStream.close();
            }
            return readPcxHeader;
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

    private PcxHeader readPcxHeader(InputStream inputStream, boolean z) throws ImageReadException, IOException {
        byte[] readBytes = BinaryFunctions.readBytes("PcxHeader", inputStream, 128, "Not a Valid PCX File");
        int i = readBytes[0] & UByte.MAX_VALUE;
        int i2 = readBytes[1] & UByte.MAX_VALUE;
        int i3 = readBytes[2] & UByte.MAX_VALUE;
        int i4 = readBytes[3] & UByte.MAX_VALUE;
        int uInt16 = ByteConversions.toUInt16(readBytes, 4, getByteOrder());
        int uInt162 = ByteConversions.toUInt16(readBytes, 6, getByteOrder());
        int uInt163 = ByteConversions.toUInt16(readBytes, 8, getByteOrder());
        int uInt164 = ByteConversions.toUInt16(readBytes, 10, getByteOrder());
        int uInt165 = ByteConversions.toUInt16(readBytes, 12, getByteOrder());
        int uInt166 = ByteConversions.toUInt16(readBytes, 14, getByteOrder());
        int[] iArr = new int[16];
        int i5 = 0;
        for (int i6 = 16; i5 < i6; i6 = 16) {
            int i7 = (i5 * 3) + 16;
            iArr[i5] = ((readBytes[i7] & UByte.MAX_VALUE) << i6) | ViewCompat.MEASURED_STATE_MASK | ((readBytes[i7 + 1] & UByte.MAX_VALUE) << 8) | (readBytes[i7 + 2] & UByte.MAX_VALUE);
            i5++;
        }
        int i8 = readBytes[64] & UByte.MAX_VALUE;
        int i9 = readBytes[65] & UByte.MAX_VALUE;
        int uInt167 = ByteConversions.toUInt16(readBytes, 66, getByteOrder());
        int uInt168 = ByteConversions.toUInt16(readBytes, 68, getByteOrder());
        int uInt169 = ByteConversions.toUInt16(readBytes, 70, getByteOrder());
        int uInt1610 = ByteConversions.toUInt16(readBytes, 72, getByteOrder());
        if (i != 10) {
            throw new ImageReadException("Not a Valid PCX File: manufacturer is " + i);
        }
        if (z && uInt167 % 2 != 0) {
            throw new ImageReadException("Not a Valid PCX File: bytesPerLine is odd");
        }
        return new PcxHeader(i, i2, i3, i4, uInt16, uInt162, uInt163, uInt164, uInt165, uInt166, iArr, i8, i9, uInt167, uInt168, uInt169, uInt1610);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        readPcxHeader(byteSource).dump(printWriter);
        return true;
    }

    private int[] read256ColorPalette(InputStream inputStream) throws IOException {
        byte[] readBytes = BinaryFunctions.readBytes("Palette", inputStream, 769, "Error reading palette");
        if (readBytes[0] != 12) {
            return null;
        }
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            int i2 = (i * 3) + 1;
            iArr[i] = (readBytes[i2 + 2] & UByte.MAX_VALUE) | ((readBytes[i2] & UByte.MAX_VALUE) << 16) | ((readBytes[i2 + 1] & UByte.MAX_VALUE) << 8);
        }
        return iArr;
    }

    private int[] read256ColorPaletteFromEndOfFile(ByteSource byteSource) throws IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            BinaryFunctions.skipBytes(inputStream, (int) (byteSource.getLength() - 769));
            int[] read256ColorPalette = read256ColorPalette(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return read256ColorPalette;
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

    /* JADX WARN: Code restructure failed: missing block: B:210:0x01fc, code lost:
        if (r25.nPlanes != 1) goto L79;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.awt.image.BufferedImage readImage(org.apache.commons.imaging.formats.pcx.PcxImageParser.PcxHeader r25, java.io.InputStream r26, org.apache.commons.imaging.common.bytesource.ByteSource r27) throws org.apache.commons.imaging.ImageReadException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 774
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.pcx.PcxImageParser.readImage(org.apache.commons.imaging.formats.pcx.PcxImageParser$PcxHeader, java.io.InputStream, org.apache.commons.imaging.common.bytesource.ByteSource):java.awt.image.BufferedImage");
    }

    @Override // org.apache.commons.imaging.ImageParser
    public final BufferedImage getBufferedImage(ByteSource byteSource, PcxImagingParameters pcxImagingParameters) throws ImageReadException, IOException {
        if (pcxImagingParameters == null) {
            pcxImagingParameters = new PcxImagingParameters();
        }
        InputStream inputStream = byteSource.getInputStream();
        try {
            BufferedImage readImage = readImage(readPcxHeader(inputStream, pcxImagingParameters.isStrict()), inputStream, byteSource);
            if (inputStream != null) {
                inputStream.close();
            }
            return readImage;
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
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, PcxImagingParameters pcxImagingParameters) throws ImageWriteException, IOException {
        new PcxWriter(pcxImagingParameters).writeImage(bufferedImage, outputStream);
    }
}
