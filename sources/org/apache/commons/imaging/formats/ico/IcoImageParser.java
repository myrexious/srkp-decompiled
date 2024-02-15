package org.apache.commons.imaging.formats.ico;

import androidx.core.view.ViewCompat;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.PixelDensity;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.bmp.BmpImageParser;
import org.apache.commons.imaging.formats.bmp.BmpImagingParameters;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.apache.commons.imaging.palette.SimplePalette;
import org.apache.xmpbox.type.ThumbnailType;

/* loaded from: classes2.dex */
public class IcoImageParser extends ImageParser<IcoImagingParameters> {
    private static final String DEFAULT_EXTENSION = ImageFormats.ICO.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.ICO.getExtensions();

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, IcoImagingParameters icoImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, IcoImagingParameters icoImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, IcoImagingParameters icoImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, IcoImagingParameters icoImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "ico-Custom";
    }

    public IcoImageParser() {
        super.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public IcoImagingParameters getDefaultParameters() {
        return new IcoImagingParameters();
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
        return new ImageFormat[]{ImageFormats.ICO};
    }

    /* loaded from: classes2.dex */
    public static class FileHeader {
        public final int iconCount;
        public final int iconType;
        public final int reserved;

        FileHeader(int i, int i2, int i3) {
            this.reserved = i;
            this.iconType = i2;
            this.iconCount = i3;
        }

        public void dump(PrintWriter printWriter) {
            printWriter.println("FileHeader");
            printWriter.println("Reserved: " + this.reserved);
            printWriter.println("IconType: " + this.iconType);
            printWriter.println("IconCount: " + this.iconCount);
            printWriter.println();
        }
    }

    private FileHeader readFileHeader(InputStream inputStream) throws ImageReadException, IOException {
        int read2Bytes = BinaryFunctions.read2Bytes("Reserved", inputStream, "Not a Valid ICO File", getByteOrder());
        int read2Bytes2 = BinaryFunctions.read2Bytes("IconType", inputStream, "Not a Valid ICO File", getByteOrder());
        int read2Bytes3 = BinaryFunctions.read2Bytes("IconCount", inputStream, "Not a Valid ICO File", getByteOrder());
        if (read2Bytes == 0) {
            if (read2Bytes2 != 1 && read2Bytes2 != 2) {
                throw new ImageReadException("Not a Valid ICO File: icon type is " + read2Bytes2);
            }
            return new FileHeader(read2Bytes, read2Bytes2, read2Bytes3);
        }
        throw new ImageReadException("Not a Valid ICO File: reserved is " + read2Bytes);
    }

    /* loaded from: classes2.dex */
    public static class IconInfo {
        public final int bitCount;
        public final byte colorCount;
        public final byte height;
        public final int imageOffset;
        public final int imageSize;
        public final int planes;
        public final byte reserved;
        public final byte width;

        IconInfo(byte b, byte b2, byte b3, byte b4, int i, int i2, int i3, int i4) {
            this.width = b;
            this.height = b2;
            this.colorCount = b3;
            this.reserved = b4;
            this.planes = i;
            this.bitCount = i2;
            this.imageSize = i3;
            this.imageOffset = i4;
        }

        public void dump(PrintWriter printWriter) {
            printWriter.println("IconInfo");
            printWriter.println("Width: " + ((int) this.width));
            printWriter.println("Height: " + ((int) this.height));
            printWriter.println("ColorCount: " + ((int) this.colorCount));
            printWriter.println("Reserved: " + ((int) this.reserved));
            printWriter.println("Planes: " + this.planes);
            printWriter.println("BitCount: " + this.bitCount);
            printWriter.println("ImageSize: " + this.imageSize);
            printWriter.println("ImageOffset: " + this.imageOffset);
        }
    }

    private IconInfo readIconInfo(InputStream inputStream) throws IOException {
        return new IconInfo(BinaryFunctions.readByte("Width", inputStream, "Not a Valid ICO File"), BinaryFunctions.readByte("Height", inputStream, "Not a Valid ICO File"), BinaryFunctions.readByte("ColorCount", inputStream, "Not a Valid ICO File"), BinaryFunctions.readByte("Reserved", inputStream, "Not a Valid ICO File"), BinaryFunctions.read2Bytes("Planes", inputStream, "Not a Valid ICO File", getByteOrder()), BinaryFunctions.read2Bytes("BitCount", inputStream, "Not a Valid ICO File", getByteOrder()), BinaryFunctions.read4Bytes("ImageSize", inputStream, "Not a Valid ICO File", getByteOrder()), BinaryFunctions.read4Bytes("ImageOffset", inputStream, "Not a Valid ICO File", getByteOrder()));
    }

    /* loaded from: classes2.dex */
    public static class BitmapHeader {
        public final int bitCount;
        public final int colorsImportant;
        public final int colorsUsed;
        public final int compression;
        public final int height;
        public final int planes;
        public final int size;
        public final int sizeImage;
        public final int width;
        public final int xPelsPerMeter;
        public final int yPelsPerMeter;

        BitmapHeader(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
            this.size = i;
            this.width = i2;
            this.height = i3;
            this.planes = i4;
            this.bitCount = i5;
            this.compression = i6;
            this.sizeImage = i7;
            this.xPelsPerMeter = i8;
            this.yPelsPerMeter = i9;
            this.colorsUsed = i10;
            this.colorsImportant = i11;
        }

        public void dump(PrintWriter printWriter) {
            printWriter.println("BitmapHeader");
            printWriter.println("Size: " + this.size);
            printWriter.println("Width: " + this.width);
            printWriter.println("Height: " + this.height);
            printWriter.println("Planes: " + this.planes);
            printWriter.println("BitCount: " + this.bitCount);
            printWriter.println("Compression: " + this.compression);
            printWriter.println("SizeImage: " + this.sizeImage);
            printWriter.println("XPelsPerMeter: " + this.xPelsPerMeter);
            printWriter.println("YPelsPerMeter: " + this.yPelsPerMeter);
            printWriter.println("ColorsUsed: " + this.colorsUsed);
            printWriter.println("ColorsImportant: " + this.colorsImportant);
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class IconData {
        public final IconInfo iconInfo;

        protected abstract void dumpSubclass(PrintWriter printWriter);

        public abstract BufferedImage readBufferedImage() throws ImageReadException;

        IconData(IconInfo iconInfo) {
            this.iconInfo = iconInfo;
        }

        public void dump(PrintWriter printWriter) {
            this.iconInfo.dump(printWriter);
            printWriter.println();
            dumpSubclass(printWriter);
        }
    }

    /* loaded from: classes2.dex */
    public static class BitmapIconData extends IconData {
        public final BufferedImage bufferedImage;
        public final BitmapHeader header;

        BitmapIconData(IconInfo iconInfo, BitmapHeader bitmapHeader, BufferedImage bufferedImage) {
            super(iconInfo);
            this.header = bitmapHeader;
            this.bufferedImage = bufferedImage;
        }

        @Override // org.apache.commons.imaging.formats.ico.IcoImageParser.IconData
        public BufferedImage readBufferedImage() throws ImageReadException {
            return this.bufferedImage;
        }

        @Override // org.apache.commons.imaging.formats.ico.IcoImageParser.IconData
        protected void dumpSubclass(PrintWriter printWriter) {
            printWriter.println("BitmapIconData");
            this.header.dump(printWriter);
            printWriter.println();
        }
    }

    /* loaded from: classes2.dex */
    public static class PNGIconData extends IconData {
        public final BufferedImage bufferedImage;

        PNGIconData(IconInfo iconInfo, BufferedImage bufferedImage) {
            super(iconInfo);
            this.bufferedImage = bufferedImage;
        }

        @Override // org.apache.commons.imaging.formats.ico.IcoImageParser.IconData
        public BufferedImage readBufferedImage() {
            return this.bufferedImage;
        }

        @Override // org.apache.commons.imaging.formats.ico.IcoImageParser.IconData
        protected void dumpSubclass(PrintWriter printWriter) {
            printWriter.println("PNGIconData");
            printWriter.println();
        }
    }

    private IconData readBitmapIconData(byte[] bArr, IconInfo iconInfo) throws ImageReadException, IOException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z;
        int i10;
        int i11;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        int read4Bytes = BinaryFunctions.read4Bytes("size", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        int read4Bytes2 = BinaryFunctions.read4Bytes(ThumbnailType.WIDTH, byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        int read4Bytes3 = BinaryFunctions.read4Bytes(ThumbnailType.HEIGHT, byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        int read2Bytes = BinaryFunctions.read2Bytes("planes", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        int read2Bytes2 = BinaryFunctions.read2Bytes("bitCount", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        int read4Bytes4 = BinaryFunctions.read4Bytes("compression", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        int read4Bytes5 = BinaryFunctions.read4Bytes("sizeImage", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        int read4Bytes6 = BinaryFunctions.read4Bytes("xPelsPerMeter", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        int read4Bytes7 = BinaryFunctions.read4Bytes("yPelsPerMeter", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        int read4Bytes8 = BinaryFunctions.read4Bytes("colorsUsed", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        int read4Bytes9 = BinaryFunctions.read4Bytes("ColorsImportant", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        if (read4Bytes4 == 3) {
            i3 = BinaryFunctions.read4Bytes("redMask", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
            i2 = BinaryFunctions.read4Bytes("greenMask", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
            i = BinaryFunctions.read4Bytes("blueMask", byteArrayInputStream, "Not a Valid ICO File", getByteOrder());
        } else {
            i = 0;
            i2 = 0;
            i3 = 0;
        }
        int i12 = i;
        byte[] readBytes = BinaryFunctions.readBytes("RestOfFile", byteArrayInputStream, byteArrayInputStream.available());
        if (read4Bytes == 40) {
            if (read2Bytes != 1) {
                throw new ImageReadException("Not a Valid ICO File: Planes can't be " + read2Bytes);
            }
            if (read4Bytes4 == 0 && read2Bytes2 == 32) {
                i6 = 16711680;
                i8 = 65280;
                i4 = -16777216;
                i5 = 3;
                i7 = 255;
            } else {
                i4 = 0;
                int i13 = i2;
                i5 = read4Bytes4;
                i6 = i3;
                i7 = i12;
                i8 = i13;
            }
            int i14 = i6;
            int i15 = i5;
            BitmapHeader bitmapHeader = new BitmapHeader(read4Bytes, read4Bytes2, read4Bytes3, read2Bytes, read2Bytes2, i15, read4Bytes5, read4Bytes6, read4Bytes7, read4Bytes8, read4Bytes9);
            int i16 = (((read4Bytes8 != 0 || read2Bytes2 > 8) ? read4Bytes8 : 1 << read2Bytes2) * 4) + 70;
            int length = readBytes.length + 70;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
            BinaryOutputStream binaryOutputStream = new BinaryOutputStream(byteArrayOutputStream, ByteOrder.LITTLE_ENDIAN);
            try {
                binaryOutputStream.write(66);
                binaryOutputStream.write(77);
                binaryOutputStream.write4Bytes(length);
                binaryOutputStream.write4Bytes(0);
                binaryOutputStream.write4Bytes(i16);
                binaryOutputStream.write4Bytes(56);
                binaryOutputStream.write4Bytes(read4Bytes2);
                binaryOutputStream.write4Bytes(read4Bytes3 / 2);
                binaryOutputStream.write2Bytes(read2Bytes);
                binaryOutputStream.write2Bytes(read2Bytes2);
                binaryOutputStream.write4Bytes(i15);
                binaryOutputStream.write4Bytes(read4Bytes5);
                binaryOutputStream.write4Bytes(read4Bytes6);
                binaryOutputStream.write4Bytes(read4Bytes7);
                binaryOutputStream.write4Bytes(read4Bytes8);
                binaryOutputStream.write4Bytes(read4Bytes9);
                binaryOutputStream.write4Bytes(i14);
                binaryOutputStream.write4Bytes(i8);
                binaryOutputStream.write4Bytes(i7);
                binaryOutputStream.write4Bytes(i4);
                binaryOutputStream.write(readBytes);
                binaryOutputStream.flush();
                binaryOutputStream.close();
                ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                byte[] bArr2 = null;
                BufferedImage bufferedImage = new BmpImageParser().getBufferedImage(byteArrayInputStream2, (BmpImagingParameters) null);
                int i17 = (read4Bytes2 + 7) / 8;
                int i18 = i17 % 4;
                if (i18 != 0) {
                    i17 += 4 - i18;
                }
                try {
                    bArr2 = BinaryFunctions.readBytes("transparency_map", byteArrayInputStream2, (read4Bytes3 / 2) * i17, "Not a Valid ICO File");
                    i9 = 32;
                } catch (IOException e) {
                    i9 = 32;
                    if (read2Bytes2 != 32) {
                        throw e;
                    }
                }
                if (read2Bytes2 == i9) {
                    z = true;
                    for (int i19 = 0; z && i19 < bufferedImage.getHeight(); i19++) {
                        int i20 = 0;
                        while (true) {
                            if (i20 >= bufferedImage.getWidth()) {
                                break;
                            } else if ((bufferedImage.getRGB(i20, i19) & ViewCompat.MEASURED_STATE_MASK) != 0) {
                                z = false;
                                break;
                            } else {
                                i20++;
                            }
                        }
                    }
                } else {
                    z = true;
                }
                if (z) {
                    BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 2);
                    for (int i21 = 0; i21 < bufferedImage2.getHeight(); i21++) {
                        for (int i22 = 0; i22 < bufferedImage2.getWidth(); i22++) {
                            if (bArr2 != null) {
                                i10 = 255;
                                if ((((bArr2[(((bufferedImage.getHeight() - i21) - 1) * i17) + (i22 / 8)] & UByte.MAX_VALUE) >> (7 - (i22 % 8))) & 1) != 0) {
                                    i11 = 0;
                                    bufferedImage2.setRGB(i22, i21, (i11 << 24) | (16777215 & bufferedImage.getRGB(i22, i21)));
                                }
                            } else {
                                i10 = 255;
                            }
                            i11 = i10;
                            bufferedImage2.setRGB(i22, i21, (i11 << 24) | (16777215 & bufferedImage.getRGB(i22, i21)));
                        }
                    }
                    bufferedImage = bufferedImage2;
                }
                return new BitmapIconData(iconInfo, bitmapHeader, bufferedImage);
            } catch (Throwable th) {
                try {
                    binaryOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        throw new ImageReadException("Not a Valid ICO File: Wrong bitmap header size " + read4Bytes);
    }

    private IconData readIconData(byte[] bArr, IconInfo iconInfo) throws ImageReadException, IOException {
        if (Imaging.guessFormat(bArr).equals(ImageFormats.PNG)) {
            return new PNGIconData(iconInfo, Imaging.getBufferedImage(bArr));
        }
        return readBitmapIconData(bArr, iconInfo);
    }

    /* loaded from: classes2.dex */
    public static class ImageContents {
        public final FileHeader fileHeader;
        public final IconData[] iconDatas;

        ImageContents(FileHeader fileHeader, IconData[] iconDataArr) {
            this.fileHeader = fileHeader;
            this.iconDatas = iconDataArr;
        }
    }

    private ImageContents readImage(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            FileHeader readFileHeader = readFileHeader(inputStream);
            IconInfo[] iconInfoArr = new IconInfo[readFileHeader.iconCount];
            for (int i = 0; i < readFileHeader.iconCount; i++) {
                iconInfoArr[i] = readIconInfo(inputStream);
            }
            IconData[] iconDataArr = new IconData[readFileHeader.iconCount];
            for (int i2 = 0; i2 < readFileHeader.iconCount; i2++) {
                iconDataArr[i2] = readIconData(byteSource.getBlock(iconInfoArr[i2].imageOffset, iconInfoArr[i2].imageSize), iconInfoArr[i2]);
            }
            ImageContents imageContents = new ImageContents(readFileHeader, iconDataArr);
            if (inputStream != null) {
                inputStream.close();
            }
            return imageContents;
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
        ImageContents readImage = readImage(byteSource);
        readImage.fileHeader.dump(printWriter);
        for (IconData iconData : readImage.iconDatas) {
            iconData.dump(printWriter);
        }
        return true;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public final BufferedImage getBufferedImage(ByteSource byteSource, IcoImagingParameters icoImagingParameters) throws ImageReadException, IOException {
        ImageContents readImage = readImage(byteSource);
        if (readImage.fileHeader.iconCount > 0) {
            return readImage.iconDatas[0].readBufferedImage();
        }
        throw new ImageReadException("No icons in ICO file");
    }

    @Override // org.apache.commons.imaging.ImageParser
    public List<BufferedImage> getAllBufferedImages(ByteSource byteSource) throws ImageReadException, IOException {
        ImageContents readImage = readImage(byteSource);
        FileHeader fileHeader = readImage.fileHeader;
        ArrayList arrayList = new ArrayList(fileHeader.iconCount);
        for (int i = 0; i < fileHeader.iconCount; i++) {
            arrayList.add(readImage.iconDatas[i].readBufferedImage());
        }
        return arrayList;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, IcoImagingParameters icoImagingParameters) throws ImageWriteException, IOException {
        int i;
        PixelDensity pixelDensity = (icoImagingParameters == null ? new IcoImagingParameters() : icoImagingParameters).getPixelDensity();
        PaletteFactory paletteFactory = new PaletteFactory();
        SimplePalette makeExactRgbPaletteSimple = paletteFactory.makeExactRgbPaletteSimple(bufferedImage, 256);
        if (makeExactRgbPaletteSimple == null) {
            i = paletteFactory.hasTransparency(bufferedImage) ? 32 : 24;
        } else if (makeExactRgbPaletteSimple.length() <= 2) {
            i = 1;
        } else {
            i = makeExactRgbPaletteSimple.length() <= 16 ? 4 : 8;
        }
        BinaryOutputStream binaryOutputStream = new BinaryOutputStream(outputStream, ByteOrder.LITTLE_ENDIAN);
        int width = ((bufferedImage.getWidth() * i) + 7) / 8;
        int i2 = width % 4;
        if (i2 != 0) {
            width += 4 - i2;
        }
        int width2 = (bufferedImage.getWidth() + 7) / 8;
        int i3 = width2 % 4;
        if (i3 != 0) {
            width2 += 4 - i3;
        }
        int height = ((i <= 8 ? 1 << i : 0) * 4) + 40 + (bufferedImage.getHeight() * width) + (bufferedImage.getHeight() * width2);
        binaryOutputStream.write2Bytes(0);
        binaryOutputStream.write2Bytes(1);
        binaryOutputStream.write2Bytes(1);
        int width3 = bufferedImage.getWidth();
        int height2 = bufferedImage.getHeight();
        if (width3 > 255 || height2 > 255) {
            height2 = 0;
            width3 = 0;
        }
        binaryOutputStream.write(width3);
        binaryOutputStream.write(height2);
        binaryOutputStream.write(i >= 8 ? 0 : 1 << i);
        binaryOutputStream.write(0);
        binaryOutputStream.write2Bytes(1);
        binaryOutputStream.write2Bytes(i);
        binaryOutputStream.write4Bytes(height);
        binaryOutputStream.write4Bytes(22);
        binaryOutputStream.write4Bytes(40);
        binaryOutputStream.write4Bytes(bufferedImage.getWidth());
        binaryOutputStream.write4Bytes(bufferedImage.getHeight() * 2);
        binaryOutputStream.write2Bytes(1);
        binaryOutputStream.write2Bytes(i);
        binaryOutputStream.write4Bytes(0);
        binaryOutputStream.write4Bytes(0);
        binaryOutputStream.write4Bytes(pixelDensity == null ? 0 : (int) Math.round(pixelDensity.horizontalDensityMetres()));
        binaryOutputStream.write4Bytes(pixelDensity == null ? 0 : (int) Math.round(pixelDensity.horizontalDensityMetres()));
        binaryOutputStream.write4Bytes(0);
        binaryOutputStream.write4Bytes(0);
        if (makeExactRgbPaletteSimple != null) {
            for (int i4 = 0; i4 < (1 << i); i4++) {
                if (i4 < makeExactRgbPaletteSimple.length()) {
                    binaryOutputStream.write3Bytes(makeExactRgbPaletteSimple.getEntry(i4));
                    binaryOutputStream.write(0);
                } else {
                    binaryOutputStream.write4Bytes(0);
                }
            }
        }
        int width4 = width - (((bufferedImage.getWidth() * i) + 7) / 8);
        int i5 = 0;
        int i6 = 0;
        for (int height3 = bufferedImage.getHeight() - 1; height3 >= 0; height3--) {
            for (int i7 = 0; i7 < bufferedImage.getWidth(); i7++) {
                int rgb = bufferedImage.getRGB(i7, height3);
                if (makeExactRgbPaletteSimple == null) {
                    if (i == 24) {
                        binaryOutputStream.write3Bytes(rgb);
                    } else if (i == 32) {
                        binaryOutputStream.write4Bytes(rgb);
                    }
                } else if (i < 8) {
                    i5 = (i5 << i) | makeExactRgbPaletteSimple.getPaletteIndex(rgb & ViewCompat.MEASURED_SIZE_MASK);
                    i6 += i;
                    if (i6 >= 8) {
                        binaryOutputStream.write(i5 & 255);
                        i5 = 0;
                        i6 = 0;
                    }
                } else if (i == 8) {
                    binaryOutputStream.write(makeExactRgbPaletteSimple.getPaletteIndex(rgb & ViewCompat.MEASURED_SIZE_MASK) & 255);
                }
            }
            if (i6 > 0) {
                binaryOutputStream.write((i5 << (8 - i6)) & 255);
                i5 = 0;
                i6 = 0;
            }
            for (int i8 = 0; i8 < width4; i8++) {
                binaryOutputStream.write(0);
            }
        }
        int width5 = width2 - ((bufferedImage.getWidth() + 7) / 8);
        for (int height4 = bufferedImage.getHeight() - 1; height4 >= 0; height4--) {
            for (int i9 = 0; i9 < bufferedImage.getWidth(); i9++) {
                i5 <<= 1;
                if (((bufferedImage.getRGB(i9, height4) >> 24) & 255) == 0) {
                    i5 |= 1;
                }
                i6++;
                if (i6 >= 8) {
                    binaryOutputStream.write(i5 & 255);
                    i5 = 0;
                    i6 = 0;
                }
            }
            if (i6 > 0) {
                binaryOutputStream.write((i5 << (8 - i6)) & 255);
                i5 = 0;
                i6 = 0;
            }
            for (int i10 = 0; i10 < width5; i10++) {
                binaryOutputStream.write(0);
            }
        }
        binaryOutputStream.close();
    }
}
