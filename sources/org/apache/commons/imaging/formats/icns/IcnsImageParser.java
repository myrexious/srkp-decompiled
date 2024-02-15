package org.apache.commons.imaging.formats.icns;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class IcnsImageParser extends ImageParser<IcnsImagingParameters> {
    static final int ICNS_MAGIC = IcnsType.typeAsInt("icns");
    private static final String DEFAULT_EXTENSION = ImageFormats.ICNS.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.ICNS.getExtensions();

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, IcnsImagingParameters icnsImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, IcnsImagingParameters icnsImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "Apple Icon Image";
    }

    public IcnsImageParser() {
        super.setByteOrder(ByteOrder.BIG_ENDIAN);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public IcnsImagingParameters getDefaultParameters() {
        return new IcnsImagingParameters();
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
        return new ImageFormat[]{ImageFormats.ICNS};
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, IcnsImagingParameters icnsImagingParameters) throws ImageReadException, IOException {
        List<BufferedImage> decodeAllImages = IcnsDecoder.decodeAllImages(readImage(byteSource).icnsElements);
        if (decodeAllImages.isEmpty()) {
            throw new ImageReadException("No icons in ICNS file");
        }
        BufferedImage bufferedImage = decodeAllImages.get(0);
        return new ImageInfo("Icns", 32, new ArrayList(), ImageFormats.ICNS, "ICNS Apple Icon Image", bufferedImage.getHeight(), "image/x-icns", decodeAllImages.size(), 0, 0.0f, 0, 0.0f, bufferedImage.getWidth(), false, true, false, ImageInfo.ColorType.RGB, ImageInfo.CompressionAlgorithm.UNKNOWN);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, IcnsImagingParameters icnsImagingParameters) throws ImageReadException, IOException {
        List<BufferedImage> decodeAllImages = IcnsDecoder.decodeAllImages(readImage(byteSource).icnsElements);
        if (decodeAllImages.isEmpty()) {
            throw new ImageReadException("No icons in ICNS file");
        }
        BufferedImage bufferedImage = decodeAllImages.get(0);
        return new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());
    }

    /* loaded from: classes2.dex */
    public static class IcnsHeader {
        public final int fileSize;
        public final int magic;

        IcnsHeader(int i, int i2) {
            this.magic = i;
            this.fileSize = i2;
        }

        public void dump(PrintWriter printWriter) {
            printWriter.println("IcnsHeader");
            printWriter.println("Magic: 0x" + Integer.toHexString(this.magic) + " (" + IcnsType.describeType(this.magic) + ")");
            printWriter.println("FileSize: " + this.fileSize);
            printWriter.println("");
        }
    }

    private IcnsHeader readIcnsHeader(InputStream inputStream) throws ImageReadException, IOException {
        int read4Bytes = BinaryFunctions.read4Bytes("Magic", inputStream, "Not a Valid ICNS File", getByteOrder());
        int read4Bytes2 = BinaryFunctions.read4Bytes("FileSize", inputStream, "Not a Valid ICNS File", getByteOrder());
        if (read4Bytes != ICNS_MAGIC) {
            throw new ImageReadException("Not a Valid ICNS File: magic is 0x" + Integer.toHexString(read4Bytes));
        }
        return new IcnsHeader(read4Bytes, read4Bytes2);
    }

    /* loaded from: classes2.dex */
    public static class IcnsElement {
        public final byte[] data;
        public final int elementSize;
        public final int type;

        IcnsElement(int i, int i2, byte[] bArr) {
            this.type = i;
            this.elementSize = i2;
            this.data = bArr;
        }

        public void dump(PrintWriter printWriter) {
            printWriter.println("IcnsElement");
            IcnsType findAnyType = IcnsType.findAnyType(this.type);
            printWriter.println("Type: 0x" + Integer.toHexString(this.type) + " (" + IcnsType.describeType(this.type) + ")" + (findAnyType == null ? "" : StringUtils.SPACE + findAnyType.toString()));
            printWriter.println("ElementSize: " + this.elementSize);
            printWriter.println("");
        }
    }

    private IcnsElement readIcnsElement(InputStream inputStream, int i) throws IOException {
        int read4Bytes = BinaryFunctions.read4Bytes("Type", inputStream, "Not a valid ICNS file", getByteOrder());
        int read4Bytes2 = BinaryFunctions.read4Bytes("ElementSize", inputStream, "Not a valid ICNS file", getByteOrder());
        if (read4Bytes2 > i) {
            throw new IOException(String.format("Corrupted ICNS file: element size %d is greater than remaining size %d", Integer.valueOf(read4Bytes2), Integer.valueOf(i)));
        }
        return new IcnsElement(read4Bytes, read4Bytes2, BinaryFunctions.readBytes("Data", inputStream, read4Bytes2 - 8, "Not a valid ICNS file"));
    }

    /* loaded from: classes2.dex */
    public static class IcnsContents {
        public final IcnsElement[] icnsElements;
        public final IcnsHeader icnsHeader;

        IcnsContents(IcnsHeader icnsHeader, IcnsElement[] icnsElementArr) {
            this.icnsHeader = icnsHeader;
            this.icnsElements = icnsElementArr;
        }
    }

    private IcnsContents readImage(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            IcnsHeader readIcnsHeader = readIcnsHeader(inputStream);
            ArrayList arrayList = new ArrayList();
            int i = readIcnsHeader.fileSize - 8;
            while (i > 0) {
                IcnsElement readIcnsElement = readIcnsElement(inputStream, i);
                arrayList.add(readIcnsElement);
                i -= readIcnsElement.elementSize;
            }
            int size = arrayList.size();
            IcnsElement[] icnsElementArr = new IcnsElement[size];
            for (int i2 = 0; i2 < size; i2++) {
                icnsElementArr[i2] = (IcnsElement) arrayList.get(i2);
            }
            IcnsContents icnsContents = new IcnsContents(readIcnsHeader, icnsElementArr);
            if (inputStream != null) {
                inputStream.close();
            }
            return icnsContents;
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
        IcnsContents readImage = readImage(byteSource);
        readImage.icnsHeader.dump(printWriter);
        for (IcnsElement icnsElement : readImage.icnsElements) {
            icnsElement.dump(printWriter);
        }
        return true;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public final BufferedImage getBufferedImage(ByteSource byteSource, IcnsImagingParameters icnsImagingParameters) throws ImageReadException, IOException {
        List<BufferedImage> decodeAllImages = IcnsDecoder.decodeAllImages(readImage(byteSource).icnsElements);
        if (!decodeAllImages.isEmpty()) {
            return decodeAllImages.get(0);
        }
        throw new ImageReadException("No icons in ICNS file");
    }

    @Override // org.apache.commons.imaging.ImageParser
    public List<BufferedImage> getAllBufferedImages(ByteSource byteSource) throws ImageReadException, IOException {
        return IcnsDecoder.decodeAllImages(readImage(byteSource).icnsElements);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, IcnsImagingParameters icnsImagingParameters) throws ImageWriteException, IOException {
        IcnsType icnsType;
        if (bufferedImage.getWidth() == 16 && bufferedImage.getHeight() == 16) {
            icnsType = IcnsType.ICNS_16x16_32BIT_IMAGE;
        } else if (bufferedImage.getWidth() == 32 && bufferedImage.getHeight() == 32) {
            icnsType = IcnsType.ICNS_32x32_32BIT_IMAGE;
        } else if (bufferedImage.getWidth() == 48 && bufferedImage.getHeight() == 48) {
            icnsType = IcnsType.ICNS_48x48_32BIT_IMAGE;
        } else if (bufferedImage.getWidth() == 128 && bufferedImage.getHeight() == 128) {
            icnsType = IcnsType.ICNS_128x128_32BIT_IMAGE;
        } else {
            throw new ImageWriteException("Invalid/unsupported source width " + bufferedImage.getWidth() + " and height " + bufferedImage.getHeight());
        }
        BinaryOutputStream binaryOutputStream = new BinaryOutputStream(outputStream, ByteOrder.BIG_ENDIAN);
        try {
            binaryOutputStream.write4Bytes(ICNS_MAGIC);
            binaryOutputStream.write4Bytes((icnsType.getWidth() * 4 * icnsType.getHeight()) + 16 + 4 + 4 + (icnsType.getWidth() * icnsType.getHeight()));
            binaryOutputStream.write4Bytes(icnsType.getType());
            binaryOutputStream.write4Bytes((icnsType.getWidth() * 4 * icnsType.getHeight()) + 8);
            for (int i = 0; i < bufferedImage.getHeight(); i++) {
                for (int i2 = 0; i2 < bufferedImage.getWidth(); i2++) {
                    int rgb = bufferedImage.getRGB(i2, i);
                    binaryOutputStream.write(0);
                    binaryOutputStream.write(rgb >> 16);
                    binaryOutputStream.write(rgb >> 8);
                    binaryOutputStream.write(rgb);
                }
            }
            binaryOutputStream.write4Bytes(IcnsType.find8BPPMaskType(icnsType).getType());
            binaryOutputStream.write4Bytes((icnsType.getWidth() * icnsType.getWidth()) + 8);
            for (int i3 = 0; i3 < bufferedImage.getHeight(); i3++) {
                for (int i4 = 0; i4 < bufferedImage.getWidth(); i4++) {
                    binaryOutputStream.write(bufferedImage.getRGB(i4, i3) >> 24);
                }
            }
            binaryOutputStream.close();
        } catch (Throwable th) {
            try {
                binaryOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
