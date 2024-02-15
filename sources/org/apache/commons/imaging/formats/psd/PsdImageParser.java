package org.apache.commons.imaging.formats.psd;

import com.tom_roush.fontbox.afm.AFMParser;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.XmpEmbeddable;
import org.apache.commons.imaging.common.XmpImagingParameters;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.psd.dataparsers.DataParser;
import org.apache.commons.imaging.formats.psd.dataparsers.DataParserBitmap;
import org.apache.commons.imaging.formats.psd.dataparsers.DataParserCmyk;
import org.apache.commons.imaging.formats.psd.dataparsers.DataParserGrayscale;
import org.apache.commons.imaging.formats.psd.dataparsers.DataParserIndexed;
import org.apache.commons.imaging.formats.psd.dataparsers.DataParserLab;
import org.apache.commons.imaging.formats.psd.dataparsers.DataParserRgb;
import org.apache.commons.imaging.formats.psd.datareaders.CompressedDataReader;
import org.apache.commons.imaging.formats.psd.datareaders.DataReader;
import org.apache.commons.imaging.formats.psd.datareaders.UncompressedDataReader;
import org.apache.xmpbox.type.FlashType;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class PsdImageParser extends ImageParser<PsdImagingParameters> implements XmpEmbeddable {
    public static final String BLOCK_NAME_XMP = "XMP";
    private static final int COLOR_MODE_INDEXED = 2;
    public static final int IMAGE_RESOURCE_ID_ICC_PROFILE = 1039;
    public static final int IMAGE_RESOURCE_ID_XMP = 1060;
    private static final int PSD_HEADER_LENGTH = 26;
    private static final int PSD_SECTION_COLOR_MODE = 1;
    private static final int PSD_SECTION_HEADER = 0;
    private static final int PSD_SECTION_IMAGE_DATA = 4;
    private static final int PSD_SECTION_IMAGE_RESOURCES = 2;
    private static final int PSD_SECTION_LAYER_AND_MASK_DATA = 3;
    private static final String DEFAULT_EXTENSION = ImageFormats.PSD.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.PSD.getExtensions();

    private int getChannelsPerMode(int i) {
        int i2 = 1;
        if (i != 0 && i != 1) {
            i2 = 3;
            if (i != 3) {
                i2 = 4;
                if (i != 4 && i != 9) {
                    return -1;
                }
            }
        }
        return i2;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, PsdImagingParameters psdImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "PSD-Custom";
    }

    public PsdImageParser() {
        super.setByteOrder(ByteOrder.BIG_ENDIAN);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public PsdImagingParameters getDefaultParameters() {
        return new PsdImagingParameters();
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
        return new ImageFormat[]{ImageFormats.PSD};
    }

    private PsdHeaderInfo readHeader(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            PsdHeaderInfo readHeader = readHeader(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return readHeader;
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

    private PsdHeaderInfo readHeader(InputStream inputStream) throws ImageReadException, IOException {
        BinaryFunctions.readAndVerifyBytes(inputStream, new byte[]{BuiltinOptions.PowOptions, BuiltinOptions.SquareOptions, 80, BuiltinOptions.GatherNdOptions}, "Not a Valid PSD File");
        return new PsdHeaderInfo(BinaryFunctions.read2Bytes(AFMParser.VERSION, inputStream, "Not a Valid PSD File", getByteOrder()), BinaryFunctions.readBytes("Reserved", inputStream, 6, "Not a Valid PSD File"), BinaryFunctions.read2Bytes("Channels", inputStream, "Not a Valid PSD File", getByteOrder()), BinaryFunctions.read4Bytes("Rows", inputStream, "Not a Valid PSD File", getByteOrder()), BinaryFunctions.read4Bytes("Columns", inputStream, "Not a Valid PSD File", getByteOrder()), BinaryFunctions.read2Bytes("Depth", inputStream, "Not a Valid PSD File", getByteOrder()), BinaryFunctions.read2Bytes(FlashType.MODE, inputStream, "Not a Valid PSD File", getByteOrder()));
    }

    private PsdImageContents readImageContents(InputStream inputStream) throws ImageReadException, IOException {
        PsdHeaderInfo readHeader = readHeader(inputStream);
        int read4Bytes = BinaryFunctions.read4Bytes("ColorModeDataLength", inputStream, "Not a Valid PSD File", getByteOrder());
        BinaryFunctions.skipBytes(inputStream, read4Bytes);
        int read4Bytes2 = BinaryFunctions.read4Bytes("ImageResourcesLength", inputStream, "Not a Valid PSD File", getByteOrder());
        BinaryFunctions.skipBytes(inputStream, read4Bytes2);
        int read4Bytes3 = BinaryFunctions.read4Bytes("LayerAndMaskDataLength", inputStream, "Not a Valid PSD File", getByteOrder());
        BinaryFunctions.skipBytes(inputStream, read4Bytes3);
        return new PsdImageContents(readHeader, read4Bytes, read4Bytes2, read4Bytes3, BinaryFunctions.read2Bytes("Compression", inputStream, "Not a Valid PSD File", getByteOrder()));
    }

    private List<ImageResourceBlock> readImageResourceBlocks(byte[] bArr, int[] iArr, int i) throws ImageReadException, IOException {
        return readImageResourceBlocks(new ByteArrayInputStream(bArr), iArr, i, bArr.length);
    }

    private boolean keepImageResourceBlock(int i, int[] iArr) {
        if (iArr == null) {
            return true;
        }
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private List<ImageResourceBlock> readImageResourceBlocks(InputStream inputStream, int[] iArr, int i, int i2) throws ImageReadException, IOException {
        ArrayList arrayList = new ArrayList();
        while (i2 > 0) {
            BinaryFunctions.readAndVerifyBytes(inputStream, new byte[]{BuiltinOptions.PowOptions, BuiltinOptions.SquareOptions, BuiltinOptions.RangeOptions, BuiltinOptions.MirrorPadOptions}, "Not a Valid PSD File");
            int read2Bytes = BinaryFunctions.read2Bytes(OperatorName.BEGIN_INLINE_IMAGE_DATA, inputStream, "Not a Valid PSD File", getByteOrder());
            byte readByte = BinaryFunctions.readByte("NameLength", inputStream, "Not a Valid PSD File");
            byte[] readBytes = BinaryFunctions.readBytes("NameData", inputStream, readByte, "Not a Valid PSD File");
            int i3 = (((i2 - 4) - 2) - 1) - readByte;
            if ((readByte + 1) % 2 != 0) {
                BinaryFunctions.readByte("NameDiscard", inputStream, "Not a Valid PSD File");
                i3--;
            }
            int read4Bytes = BinaryFunctions.read4Bytes("Size", inputStream, "Not a Valid PSD File", getByteOrder());
            byte[] readBytes2 = BinaryFunctions.readBytes("Data", inputStream, read4Bytes, "Not a Valid PSD File");
            i2 = (i3 - 4) - read4Bytes;
            if (read4Bytes % 2 != 0) {
                BinaryFunctions.readByte("DataDiscard", inputStream, "Not a Valid PSD File");
                i2--;
            }
            if (keepImageResourceBlock(read2Bytes, iArr)) {
                arrayList.add(new ImageResourceBlock(read2Bytes, readBytes, readBytes2));
                if (i >= 0 && arrayList.size() >= i) {
                    break;
                }
            }
        }
        return arrayList;
    }

    private List<ImageResourceBlock> readImageResourceBlocks(ByteSource byteSource, int[] iArr, int i) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            InputStream inputStream2 = getInputStream(byteSource, 2);
            List<ImageResourceBlock> readImageResourceBlocks = readImageResourceBlocks(BinaryFunctions.readBytes("ImageResources", inputStream2, readImageContents(inputStream).ImageResourcesLength, "Not a Valid PSD File"), iArr, i);
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return readImageResourceBlocks;
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

    private InputStream getInputStream(ByteSource byteSource, int i) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        if (i == 0) {
            return inputStream;
        }
        BinaryFunctions.skipBytes(inputStream, 26L);
        int read4Bytes = BinaryFunctions.read4Bytes("ColorModeDataLength", inputStream, "Not a Valid PSD File", getByteOrder());
        if (i == 1) {
            return inputStream;
        }
        BinaryFunctions.skipBytes(inputStream, read4Bytes);
        int read4Bytes2 = BinaryFunctions.read4Bytes("ImageResourcesLength", inputStream, "Not a Valid PSD File", getByteOrder());
        if (i == 2) {
            return inputStream;
        }
        BinaryFunctions.skipBytes(inputStream, read4Bytes2);
        int read4Bytes3 = BinaryFunctions.read4Bytes("LayerAndMaskDataLength", inputStream, "Not a Valid PSD File", getByteOrder());
        if (i == 3) {
            return inputStream;
        }
        BinaryFunctions.skipBytes(inputStream, read4Bytes3);
        BinaryFunctions.read2Bytes("Compression", inputStream, "Not a Valid PSD File", getByteOrder());
        if (i == 4) {
            return inputStream;
        }
        if (inputStream != null) {
            inputStream.close();
        }
        throw new ImageReadException("getInputStream: Unknown Section: " + i);
    }

    private byte[] getData(ByteSource byteSource, int i) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            if (i == 0) {
                byte[] readBytes = BinaryFunctions.readBytes("Header", inputStream, 26, "Not a Valid PSD File");
                if (inputStream != null) {
                    inputStream.close();
                }
                return readBytes;
            }
            BinaryFunctions.skipBytes(inputStream, 26L);
            int read4Bytes = BinaryFunctions.read4Bytes("ColorModeDataLength", inputStream, "Not a Valid PSD File", getByteOrder());
            if (i == 1) {
                byte[] readBytes2 = BinaryFunctions.readBytes("ColorModeData", inputStream, read4Bytes, "Not a Valid PSD File");
                if (inputStream != null) {
                    inputStream.close();
                }
                return readBytes2;
            }
            BinaryFunctions.skipBytes(inputStream, read4Bytes);
            int read4Bytes2 = BinaryFunctions.read4Bytes("ImageResourcesLength", inputStream, "Not a Valid PSD File", getByteOrder());
            if (i == 2) {
                byte[] readBytes3 = BinaryFunctions.readBytes("ImageResources", inputStream, read4Bytes2, "Not a Valid PSD File");
                if (inputStream != null) {
                    inputStream.close();
                }
                return readBytes3;
            }
            BinaryFunctions.skipBytes(inputStream, read4Bytes2);
            int read4Bytes3 = BinaryFunctions.read4Bytes("LayerAndMaskDataLength", inputStream, "Not a Valid PSD File", getByteOrder());
            if (i == 3) {
                byte[] readBytes4 = BinaryFunctions.readBytes("LayerAndMaskData", inputStream, read4Bytes3, "Not a Valid PSD File");
                if (inputStream != null) {
                    inputStream.close();
                }
                return readBytes4;
            }
            BinaryFunctions.skipBytes(inputStream, read4Bytes3);
            BinaryFunctions.read2Bytes("Compression", inputStream, "Not a Valid PSD File", getByteOrder());
            if (inputStream != null) {
                inputStream.close();
            }
            throw new ImageReadException("getInputStream: Unknown Section: " + i);
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

    private PsdImageContents readImageContents(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            PsdImageContents readImageContents = readImageContents(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return readImageContents;
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
    public byte[] getICCProfileBytes(ByteSource byteSource, PsdImagingParameters psdImagingParameters) throws ImageReadException, IOException {
        byte[] bArr;
        List<ImageResourceBlock> readImageResourceBlocks = readImageResourceBlocks(byteSource, new int[]{1039}, 1);
        if (readImageResourceBlocks.isEmpty() || (bArr = readImageResourceBlocks.get(0).data) == null || bArr.length < 1) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    @Override // org.apache.commons.imaging.ImageParser
    public Dimension getImageSize(ByteSource byteSource, PsdImagingParameters psdImagingParameters) throws ImageReadException, IOException {
        PsdHeaderInfo readHeader = readHeader(byteSource);
        return new Dimension(readHeader.columns, readHeader.rows);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, PsdImagingParameters psdImagingParameters) throws ImageReadException, IOException {
        ImageInfo.CompressionAlgorithm compressionAlgorithm;
        PsdImageContents readImageContents = readImageContents(byteSource);
        PsdHeaderInfo psdHeaderInfo = readImageContents.header;
        if (psdHeaderInfo == null) {
            throw new ImageReadException("PSD: Couldn't read Header");
        }
        int i = psdHeaderInfo.columns;
        int i2 = psdHeaderInfo.rows;
        ArrayList arrayList = new ArrayList();
        int channelsPerMode = psdHeaderInfo.depth * getChannelsPerMode(psdHeaderInfo.mode);
        int i3 = channelsPerMode < 0 ? 0 : channelsPerMode;
        ImageFormats imageFormats = ImageFormats.PSD;
        float f = (float) (i / 72.0d);
        float f2 = (float) (i2 / 72.0d);
        boolean z = psdHeaderInfo.mode == 2;
        ImageInfo.ColorType colorType = ImageInfo.ColorType.UNKNOWN;
        int i4 = readImageContents.Compression;
        if (i4 == 0) {
            compressionAlgorithm = ImageInfo.CompressionAlgorithm.NONE;
        } else if (i4 == 1) {
            compressionAlgorithm = ImageInfo.CompressionAlgorithm.PSD;
        } else {
            compressionAlgorithm = ImageInfo.CompressionAlgorithm.UNKNOWN;
        }
        return new ImageInfo("Psd", i3, arrayList, imageFormats, "Photoshop", i2, "image/x-photoshop", -1, 72, f2, 72, f, i, false, false, z, colorType, compressionAlgorithm);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        printWriter.println("gif.dumpImageFile");
        ImageInfo imageInfo = getImageInfo(byteSource);
        if (imageInfo == null) {
            return false;
        }
        imageInfo.toString(printWriter, "");
        PsdImageContents readImageContents = readImageContents(byteSource);
        readImageContents.dump(printWriter);
        readImageContents.header.dump(printWriter);
        List<ImageResourceBlock> readImageResourceBlocks = readImageResourceBlocks(byteSource, (int[]) null, -1);
        printWriter.println("blocks.size(): " + readImageResourceBlocks.size());
        for (int i = 0; i < readImageResourceBlocks.size(); i++) {
            ImageResourceBlock imageResourceBlock = readImageResourceBlocks.get(i);
            printWriter.println("\t" + i + " (" + Integer.toHexString(imageResourceBlock.f34id) + ", '" + new String(imageResourceBlock.nameData, StandardCharsets.ISO_8859_1) + "' (" + imageResourceBlock.nameData.length + "),  data: " + imageResourceBlock.data.length + " type: '" + ImageResourceType.getDescription(imageResourceBlock.f34id) + "' )");
        }
        printWriter.println("");
        return true;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public BufferedImage getBufferedImage(ByteSource byteSource, PsdImagingParameters psdImagingParameters) throws ImageReadException, IOException {
        DataParser dataParserBitmap;
        DataReader uncompressedDataReader;
        PsdImageContents readImageContents = readImageContents(byteSource);
        PsdHeaderInfo psdHeaderInfo = readImageContents.header;
        if (psdHeaderInfo == null) {
            throw new ImageReadException("PSD: Couldn't read Header");
        }
        readImageResourceBlocks(byteSource, (int[]) null, -1);
        BufferedImage colorBufferedImage = getBufferedImageFactory(psdImagingParameters).getColorBufferedImage(psdHeaderInfo.columns, psdHeaderInfo.rows, false);
        int i = readImageContents.header.mode;
        if (i == 0) {
            dataParserBitmap = new DataParserBitmap();
        } else {
            if (i != 1) {
                if (i == 2) {
                    dataParserBitmap = new DataParserIndexed(getData(byteSource, 1));
                } else if (i == 3) {
                    dataParserBitmap = new DataParserRgb();
                } else if (i == 4) {
                    dataParserBitmap = new DataParserCmyk();
                } else if (i != 8) {
                    if (i == 9) {
                        dataParserBitmap = new DataParserLab();
                    } else {
                        throw new ImageReadException("Unknown Mode: " + readImageContents.header.mode);
                    }
                }
            }
            dataParserBitmap = new DataParserGrayscale();
        }
        int i2 = readImageContents.Compression;
        if (i2 == 0) {
            uncompressedDataReader = new UncompressedDataReader(dataParserBitmap);
        } else if (i2 == 1) {
            uncompressedDataReader = new CompressedDataReader(dataParserBitmap);
        } else {
            throw new ImageReadException("Unknown Compression: " + readImageContents.Compression);
        }
        InputStream inputStream = getInputStream(byteSource, 4);
        try {
            uncompressedDataReader.readData(inputStream, colorBufferedImage, readImageContents, this);
            if (inputStream != null) {
                inputStream.close();
            }
            return colorBufferedImage;
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

    @Override // org.apache.commons.imaging.common.XmpEmbeddable
    public String getXmpXml(ByteSource byteSource, XmpImagingParameters xmpImagingParameters) throws ImageReadException, IOException {
        if (readImageContents(byteSource).header == null) {
            throw new ImageReadException("PSD: Couldn't read Header");
        }
        List<ImageResourceBlock> readImageResourceBlocks = readImageResourceBlocks(byteSource, new int[]{1060}, -1);
        if (readImageResourceBlocks.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(readImageResourceBlocks);
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() > 1) {
            throw new ImageReadException("PSD contains more than one XMP block.");
        }
        ImageResourceBlock imageResourceBlock = (ImageResourceBlock) arrayList.get(0);
        return new String(imageResourceBlock.data, 0, imageResourceBlock.data.length, StandardCharsets.UTF_8);
    }
}
