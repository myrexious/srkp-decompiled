package org.apache.commons.imaging.formats.gif;

import androidx.core.view.ViewCompat;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.XmpEmbeddable;
import org.apache.commons.imaging.common.XmpImagingParameters;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.mylzw.MyLzwCompressor;
import org.apache.commons.imaging.common.mylzw.MyLzwDecompressor;
import org.apache.commons.imaging.palette.Palette;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.apache.xmpbox.type.VersionType;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class GifImageParser extends ImageParser<GifImagingParameters> implements XmpEmbeddable {
    private static final int APPLICATION_EXTENSION_LABEL = 255;
    private static final int COMMENT_EXTENSION = 254;
    private static final int EXTENSION_CODE = 33;
    private static final int GRAPHIC_CONTROL_EXTENSION = 8697;
    private static final int IMAGE_SEPARATOR = 44;
    private static final int INTERLACE_FLAG_MASK = 64;
    private static final int LOCAL_COLOR_TABLE_FLAG_MASK = 128;
    private static final int PLAIN_TEXT_EXTENSION = 1;
    private static final int SORT_FLAG_MASK = 32;
    private static final int TERMINATOR_BYTE = 59;
    private static final int XMP_COMPLETE_CODE = 8703;
    private static final int XMP_EXTENSION = 255;
    private static final Logger LOGGER = Logger.getLogger(GifImageParser.class.getName());
    private static final String DEFAULT_EXTENSION = ImageFormats.GIF.getDefaultExtension();
    private static final String[] ACCEPTED_EXTENSIONS = ImageFormats.GIF.getExtensions();
    private static final byte[] GIF_HEADER_SIGNATURE = {BuiltinOptions.UnidirectionalSequenceLSTMOptions, BuiltinOptions.RangeOptions, BuiltinOptions.BidirectionalSequenceRNNOptions};
    private static final byte[] XMP_APPLICATION_ID_AND_AUTH_CODE = {BuiltinOptions.MatrixDiagOptions, BuiltinOptions.MirrorPadOptions, 80, 32, BuiltinOptions.FillOptions, BuiltinOptions.ScatterNdOptions, BuiltinOptions.GeluOptions, BuiltinOptions.ScatterNdOptions, BuiltinOptions.MatrixDiagOptions, BuiltinOptions.MirrorPadOptions, 80};

    private int simplePow(int i, int i2) {
        int i3 = 1;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 *= i;
        }
        return i3;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public byte[] getICCProfileBytes(ByteSource byteSource, GifImagingParameters gifImagingParameters) throws ImageReadException, IOException {
        return null;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public String getName() {
        return "Graphics Interchange Format";
    }

    public GifImageParser() {
        super.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public GifImagingParameters getDefaultParameters() {
        return new GifImagingParameters();
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
        return new ImageFormat[]{ImageFormats.GIF};
    }

    private GifHeaderInfo readHeader(InputStream inputStream, FormatCompliance formatCompliance) throws ImageReadException, IOException {
        byte b;
        int i;
        byte b2;
        boolean z;
        byte readByte = BinaryFunctions.readByte("identifier1", inputStream, "Not a Valid GIF File");
        byte readByte2 = BinaryFunctions.readByte("identifier2", inputStream, "Not a Valid GIF File");
        byte readByte3 = BinaryFunctions.readByte("identifier3", inputStream, "Not a Valid GIF File");
        byte readByte4 = BinaryFunctions.readByte("version1", inputStream, "Not a Valid GIF File");
        byte readByte5 = BinaryFunctions.readByte("version2", inputStream, "Not a Valid GIF File");
        byte readByte6 = BinaryFunctions.readByte("version3", inputStream, "Not a Valid GIF File");
        if (formatCompliance != null) {
            formatCompliance.compareBytes("Signature", GIF_HEADER_SIGNATURE, new byte[]{readByte, readByte2, readByte3});
            formatCompliance.compare(VersionType.VERSION, 56, readByte4);
            formatCompliance.compare(VersionType.VERSION, new int[]{55, 57}, readByte5);
            formatCompliance.compare(VersionType.VERSION, 97, readByte6);
        }
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            BinaryFunctions.printCharQuad("identifier: ", (readByte << 16) | (readByte2 << 8) | (readByte3 << 0));
            BinaryFunctions.printCharQuad("version: ", (readByte4 << 16) | (readByte5 << 8) | (readByte6 << 0));
        }
        int read2Bytes = BinaryFunctions.read2Bytes("Logical Screen Width", inputStream, "Not a Valid GIF File", getByteOrder());
        int read2Bytes2 = BinaryFunctions.read2Bytes("Logical Screen Height", inputStream, "Not a Valid GIF File", getByteOrder());
        if (formatCompliance != null) {
            formatCompliance.checkBounds("Width", 1, Integer.MAX_VALUE, read2Bytes);
            formatCompliance.checkBounds("Height", 1, Integer.MAX_VALUE, read2Bytes2);
        }
        byte readByte7 = BinaryFunctions.readByte("Packed Fields", inputStream, "Not a Valid GIF File");
        byte readByte8 = BinaryFunctions.readByte("Background Color Index", inputStream, "Not a Valid GIF File");
        byte readByte9 = BinaryFunctions.readByte("Pixel Aspect Ratio", inputStream, "Not a Valid GIF File");
        if (logger.isLoggable(Level.FINEST)) {
            BinaryFunctions.printByteBits("PackedFields bits", readByte7);
        }
        boolean z2 = (readByte7 & ByteCompanionObject.MIN_VALUE) > 0;
        if (logger.isLoggable(Level.FINEST)) {
            b = readByte9;
            logger.finest("GlobalColorTableFlag: " + z2);
        } else {
            b = readByte9;
        }
        byte b3 = (byte) ((readByte7 >> 4) & 7);
        if (logger.isLoggable(Level.FINEST)) {
            i = read2Bytes2;
            logger.finest("ColorResolution: " + ((int) b3));
        } else {
            i = read2Bytes2;
        }
        boolean z3 = (readByte7 & 8) > 0;
        if (logger.isLoggable(Level.FINEST)) {
            b2 = b3;
            logger.finest("SortFlag: " + z3);
        } else {
            b2 = b3;
        }
        byte b4 = (byte) (readByte7 & 7);
        if (logger.isLoggable(Level.FINEST)) {
            z = z3;
            logger.finest("SizeofGlobalColorTable: " + ((int) b4));
        } else {
            z = z3;
        }
        if (formatCompliance != null && z2 && readByte8 != -1) {
            formatCompliance.checkBounds("Background Color Index", 0, convertColorTableSize(b4), readByte8);
        }
        return new GifHeaderInfo(readByte, readByte2, readByte3, readByte4, readByte5, readByte6, read2Bytes, i, readByte7, readByte8, b, z2, b2, z, b4);
    }

    private GraphicControlExtension readGraphicControlExtension(int i, InputStream inputStream) throws IOException {
        BinaryFunctions.readByte("block_size", inputStream, "GIF: corrupt GraphicControlExt");
        byte readByte = BinaryFunctions.readByte("packed fields", inputStream, "GIF: corrupt GraphicControlExt");
        int i2 = (readByte & BuiltinOptions.SubOptions) >> 2;
        boolean z = (readByte & 1) != 0;
        int read2Bytes = BinaryFunctions.read2Bytes("delay in milliseconds", inputStream, "GIF: corrupt GraphicControlExt", getByteOrder());
        int readByte2 = BinaryFunctions.readByte("transparent color index", inputStream, "GIF: corrupt GraphicControlExt") & UByte.MAX_VALUE;
        BinaryFunctions.readByte("block terminator", inputStream, "GIF: corrupt GraphicControlExt");
        return new GraphicControlExtension(i, readByte, i2, z, read2Bytes, readByte2);
    }

    private byte[] readSubBlock(InputStream inputStream) throws IOException {
        return BinaryFunctions.readBytes("block", inputStream, BinaryFunctions.readByte("block_size", inputStream, "GIF: corrupt block") & UByte.MAX_VALUE, "GIF: corrupt block");
    }

    private GenericGifBlock readGenericGIFBlock(InputStream inputStream, int i) throws IOException {
        return readGenericGIFBlock(inputStream, i, null);
    }

    private GenericGifBlock readGenericGIFBlock(InputStream inputStream, int i, byte[] bArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (bArr != null) {
            arrayList.add(bArr);
        }
        while (true) {
            byte[] readSubBlock = readSubBlock(inputStream);
            if (readSubBlock.length >= 1) {
                arrayList.add(readSubBlock);
            } else {
                return new GenericGifBlock(i, arrayList);
            }
        }
    }

    private List<GifBlock> readBlocks(GifHeaderInfo gifHeaderInfo, InputStream inputStream, boolean z, FormatCompliance formatCompliance) throws ImageReadException, IOException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                throw new ImageReadException("GIF: unexpected end of data");
            }
            if (read != 0) {
                if (read == 33) {
                    int read2 = inputStream.read();
                    int i = ((read & 255) << 8) | (read2 & 255);
                    if (read2 != 1) {
                        if (read2 == 249) {
                            arrayList.add(readGraphicControlExtension(i, inputStream));
                        } else if (read2 != COMMENT_EXTENSION) {
                            if (read2 == 255) {
                                byte[] readSubBlock = readSubBlock(inputStream);
                                if (formatCompliance != null) {
                                    formatCompliance.addComment("Unknown Application Extension (" + new String(readSubBlock, StandardCharsets.US_ASCII) + ")", i);
                                }
                                if (readSubBlock.length > 0) {
                                    arrayList.add(readGenericGIFBlock(inputStream, i, readSubBlock));
                                }
                            } else {
                                if (formatCompliance != null) {
                                    formatCompliance.addComment("Unknown block", i);
                                }
                                arrayList.add(readGenericGIFBlock(inputStream, i));
                            }
                        }
                    }
                    arrayList.add(readGenericGIFBlock(inputStream, i));
                } else if (read != 44) {
                    if (read == 59) {
                        return arrayList;
                    }
                    throw new ImageReadException("GIF: unknown code: " + read);
                } else {
                    arrayList.add(readImageDescriptor(gifHeaderInfo, read, inputStream, z, formatCompliance));
                }
            }
        }
    }

    private ImageDescriptor readImageDescriptor(GifHeaderInfo gifHeaderInfo, int i, InputStream inputStream, boolean z, FormatCompliance formatCompliance) throws ImageReadException, IOException {
        byte[] bArr;
        int read2Bytes = BinaryFunctions.read2Bytes("Image Left Position", inputStream, "Not a Valid GIF File", getByteOrder());
        int read2Bytes2 = BinaryFunctions.read2Bytes("Image Top Position", inputStream, "Not a Valid GIF File", getByteOrder());
        int read2Bytes3 = BinaryFunctions.read2Bytes("Image Width", inputStream, "Not a Valid GIF File", getByteOrder());
        int read2Bytes4 = BinaryFunctions.read2Bytes("Image Height", inputStream, "Not a Valid GIF File", getByteOrder());
        byte readByte = BinaryFunctions.readByte("Packed Fields", inputStream, "Not a Valid GIF File");
        if (formatCompliance != null) {
            formatCompliance.checkBounds("Width", 1, gifHeaderInfo.logicalScreenWidth, read2Bytes3);
            formatCompliance.checkBounds("Height", 1, gifHeaderInfo.logicalScreenHeight, read2Bytes4);
            formatCompliance.checkBounds("Left Position", 0, gifHeaderInfo.logicalScreenWidth - read2Bytes3, read2Bytes);
            formatCompliance.checkBounds("Top Position", 0, gifHeaderInfo.logicalScreenHeight - read2Bytes4, read2Bytes2);
        }
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            BinaryFunctions.printByteBits("PackedFields bits", readByte);
        }
        boolean z2 = ((readByte >> 7) & 1) > 0;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("LocalColorTableFlag: " + z2);
        }
        boolean z3 = ((readByte >> 6) & 1) > 0;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Interlace Flag: " + z3);
        }
        boolean z4 = ((readByte >> 5) & 1) > 0;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Sort Flag: " + z4);
        }
        byte b = (byte) (readByte & 7);
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("SizeofLocalColorTable: " + ((int) b));
        }
        byte[] readColorTable = z2 ? readColorTable(inputStream, b) : null;
        if (!z) {
            bArr = new MyLzwDecompressor(inputStream.read(), ByteOrder.LITTLE_ENDIAN).decompress(new ByteArrayInputStream(readGenericGIFBlock(inputStream, -1).appendSubBlocks()), read2Bytes3 * read2Bytes4);
        } else {
            int read = inputStream.read();
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest("LZWMinimumCodeSize: " + read);
            }
            readGenericGIFBlock(inputStream, -1);
            bArr = null;
        }
        return new ImageDescriptor(i, read2Bytes, read2Bytes2, read2Bytes3, read2Bytes4, readByte, z2, z3, z4, b, readColorTable, bArr);
    }

    private int convertColorTableSize(int i) {
        return simplePow(2, i + 1) * 3;
    }

    private byte[] readColorTable(InputStream inputStream, int i) throws IOException {
        return BinaryFunctions.readBytes("block", inputStream, convertColorTableSize(i), "GIF: corrupt Color Table");
    }

    private GifBlock findBlock(List<GifBlock> list, int i) {
        for (GifBlock gifBlock : list) {
            if (gifBlock.blockCode == i) {
                return gifBlock;
            }
        }
        return null;
    }

    private <T extends GifBlock> List<T> findAllBlocks(List<GifBlock> list, int i) {
        ArrayList arrayList = new ArrayList();
        for (GifBlock gifBlock : list) {
            if (gifBlock.blockCode == i) {
                arrayList.add(gifBlock);
            }
        }
        return arrayList;
    }

    private GifImageContents readFile(ByteSource byteSource, boolean z) throws ImageReadException, IOException {
        return readFile(byteSource, z, FormatCompliance.getDefault());
    }

    private GifImageContents readFile(ByteSource byteSource, boolean z, FormatCompliance formatCompliance) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            GifHeaderInfo readHeader = readHeader(inputStream, formatCompliance);
            GifImageContents gifImageContents = new GifImageContents(readHeader, readHeader.globalColorTableFlag ? readColorTable(inputStream, readHeader.sizeOfGlobalColorTable) : null, readBlocks(readHeader, inputStream, z, formatCompliance));
            if (inputStream != null) {
                inputStream.close();
            }
            return gifImageContents;
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
    public Dimension getImageSize(ByteSource byteSource, GifImagingParameters gifImagingParameters) throws ImageReadException, IOException {
        GifHeaderInfo gifHeaderInfo = readFile(byteSource, false).gifHeaderInfo;
        if (gifHeaderInfo == null) {
            throw new ImageReadException("GIF: Couldn't read Header");
        }
        return new Dimension(gifHeaderInfo.logicalScreenWidth, gifHeaderInfo.logicalScreenHeight);
    }

    static DisposalMethod createDisposalMethodFromIntValue(int i) throws ImageReadException {
        switch (i) {
            case 0:
                return DisposalMethod.UNSPECIFIED;
            case 1:
                return DisposalMethod.DO_NOT_DISPOSE;
            case 2:
                return DisposalMethod.RESTORE_TO_BACKGROUND;
            case 3:
                return DisposalMethod.RESTORE_TO_PREVIOUS;
            case 4:
                return DisposalMethod.TO_BE_DEFINED_1;
            case 5:
                return DisposalMethod.TO_BE_DEFINED_2;
            case 6:
                return DisposalMethod.TO_BE_DEFINED_3;
            case 7:
                return DisposalMethod.TO_BE_DEFINED_4;
            default:
                throw new ImageReadException("GIF: Invalid parsing of disposal method");
        }
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageMetadata getMetadata(ByteSource byteSource, GifImagingParameters gifImagingParameters) throws ImageReadException, IOException {
        GifImageContents readFile = readFile(byteSource, false);
        GifHeaderInfo gifHeaderInfo = readFile.gifHeaderInfo;
        if (gifHeaderInfo == null) {
            throw new ImageReadException("GIF: Couldn't read Header");
        }
        List<GifImageData> findAllImageData = findAllImageData(readFile);
        ArrayList arrayList = new ArrayList(findAllImageData.size());
        for (GifImageData gifImageData : findAllImageData) {
            arrayList.add(new GifImageMetadataItem(gifImageData.gce.delay, gifImageData.descriptor.imageLeftPosition, gifImageData.descriptor.imageTopPosition, createDisposalMethodFromIntValue(gifImageData.gce.dispose)));
        }
        return new GifImageMetadata(gifHeaderInfo.logicalScreenWidth, gifHeaderInfo.logicalScreenHeight, arrayList);
    }

    private List<String> getComments(List<GifBlock> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (GifBlock gifBlock : list) {
            if (gifBlock.blockCode == 8702) {
                arrayList.add(new String(((GenericGifBlock) gifBlock).appendSubBlocks(), StandardCharsets.US_ASCII));
            }
        }
        return arrayList;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public ImageInfo getImageInfo(ByteSource byteSource, GifImagingParameters gifImagingParameters) throws ImageReadException, IOException {
        GifImageContents readFile = readFile(byteSource, false);
        GifHeaderInfo gifHeaderInfo = readFile.gifHeaderInfo;
        if (gifHeaderInfo == null) {
            throw new ImageReadException("GIF: Couldn't read Header");
        }
        ImageDescriptor imageDescriptor = (ImageDescriptor) findBlock(readFile.blocks, 44);
        if (imageDescriptor == null) {
            throw new ImageReadException("GIF: Couldn't read ImageDescriptor");
        }
        GraphicControlExtension graphicControlExtension = (GraphicControlExtension) findBlock(readFile.blocks, GRAPHIC_CONTROL_EXTENSION);
        int i = gifHeaderInfo.logicalScreenHeight;
        int i2 = gifHeaderInfo.logicalScreenWidth;
        List<String> comments = getComments(readFile.blocks);
        return new ImageInfo("Gif " + ((char) readFile.gifHeaderInfo.version1) + ((char) readFile.gifHeaderInfo.version2) + ((char) readFile.gifHeaderInfo.version3), gifHeaderInfo.colorResolution + 1, comments, ImageFormats.GIF, "GIF Graphics Interchange Format", i, "image/gif", findAllBlocks(readFile.blocks, 44).size(), 72, (float) (i / 72.0d), 72, (float) (i2 / 72.0d), i2, imageDescriptor.interlaceFlag, graphicControlExtension != null && graphicControlExtension.transparency, true, ImageInfo.ColorType.RGB, ImageInfo.CompressionAlgorithm.LZW);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public boolean dumpImageFile(PrintWriter printWriter, ByteSource byteSource) throws ImageReadException, IOException {
        printWriter.println("gif.dumpImageFile");
        ImageInfo imageInfo = getImageInfo(byteSource);
        if (imageInfo == null) {
            return false;
        }
        imageInfo.toString(printWriter, "");
        GifImageContents readFile = readFile(byteSource, false);
        printWriter.println("gif.blocks: " + readFile.blocks.size());
        for (int i = 0; i < readFile.blocks.size(); i++) {
            GifBlock gifBlock = readFile.blocks.get(i);
            debugNumber(printWriter, "\t" + i + " (" + gifBlock.getClass().getName() + ")", gifBlock.blockCode, 4);
        }
        printWriter.println("");
        return true;
    }

    private int[] getColorTable(byte[] bArr) throws ImageReadException {
        if (bArr.length % 3 != 0) {
            throw new ImageReadException("Bad Color Table Length: " + bArr.length);
        }
        int length = bArr.length / 3;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 3;
            iArr[i] = ((bArr[i2 + 2] & UByte.MAX_VALUE) << 0) | ((bArr[i2 + 0] & UByte.MAX_VALUE) << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[i2 + 1] & UByte.MAX_VALUE) << 8);
        }
        return iArr;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public FormatCompliance getFormatCompliance(ByteSource byteSource) throws ImageReadException, IOException {
        FormatCompliance formatCompliance = new FormatCompliance(byteSource.getDescription());
        readFile(byteSource, false, formatCompliance);
        return formatCompliance;
    }

    private List<GifImageData> findAllImageData(GifImageContents gifImageContents) throws ImageReadException {
        List findAllBlocks = findAllBlocks(gifImageContents.blocks, 44);
        if (findAllBlocks.isEmpty()) {
            throw new ImageReadException("GIF: Couldn't read Image Descriptor");
        }
        List findAllBlocks2 = findAllBlocks(gifImageContents.blocks, GRAPHIC_CONTROL_EXTENSION);
        if (!findAllBlocks2.isEmpty() && findAllBlocks2.size() != findAllBlocks.size()) {
            throw new ImageReadException("GIF: Invalid amount of Graphic Control Extensions");
        }
        ArrayList arrayList = new ArrayList(findAllBlocks.size());
        for (int i = 0; i < findAllBlocks.size(); i++) {
            ImageDescriptor imageDescriptor = (ImageDescriptor) findAllBlocks.get(i);
            if (imageDescriptor == null) {
                throw new ImageReadException(String.format("GIF: Couldn't read Image Descriptor of image number %d", Integer.valueOf(i)));
            }
            arrayList.add(new GifImageData(imageDescriptor, findAllBlocks2.isEmpty() ? null : (GraphicControlExtension) findAllBlocks2.get(i)));
        }
        return arrayList;
    }

    private GifImageData findFirstImageData(GifImageContents gifImageContents) throws ImageReadException {
        ImageDescriptor imageDescriptor = (ImageDescriptor) findBlock(gifImageContents.blocks, 44);
        if (imageDescriptor == null) {
            throw new ImageReadException("GIF: Couldn't read Image Descriptor");
        }
        return new GifImageData(imageDescriptor, (GraphicControlExtension) findBlock(gifImageContents.blocks, GRAPHIC_CONTROL_EXTENSION));
    }

    private BufferedImage getBufferedImage(GifHeaderInfo gifHeaderInfo, GifImageData gifImageData, byte[] bArr) throws ImageReadException {
        int[] colorTable;
        int i;
        ImageDescriptor imageDescriptor = gifImageData.descriptor;
        GraphicControlExtension graphicControlExtension = gifImageData.gce;
        int i2 = imageDescriptor.imageWidth;
        int i3 = imageDescriptor.imageHeight;
        int i4 = 1;
        boolean z = graphicControlExtension != null && graphicControlExtension.transparency;
        ImageBuilder imageBuilder = new ImageBuilder(i2, i3, z);
        if (imageDescriptor.localColorTable != null) {
            colorTable = getColorTable(imageDescriptor.localColorTable);
        } else if (bArr != null) {
            colorTable = getColorTable(bArr);
        } else {
            throw new ImageReadException("Gif: No Color Table");
        }
        int i5 = (graphicControlExtension == null || !z) ? -1 : graphicControlExtension.transparentColorIndex;
        int i6 = (i3 + 7) / 8;
        int i7 = (i3 + 3) / 8;
        int i8 = (i3 + 1) / 4;
        int i9 = i3 / 2;
        int i10 = 0;
        int i11 = 0;
        while (i10 < i3) {
            if (!imageDescriptor.interlaceFlag) {
                i = i10;
            } else if (i10 < i6) {
                i = i10 * 8;
            } else {
                int i12 = i10 - i6;
                if (i12 < i7) {
                    i = (i12 * 8) + 4;
                } else {
                    int i13 = i12 - i7;
                    if (i13 < i8) {
                        i = (i13 * 4) + 2;
                    } else {
                        int i14 = i13 - i8;
                        if (i14 >= i9) {
                            throw new ImageReadException("Gif: Strange Row");
                        }
                        i = (i14 * 2) + i4;
                    }
                }
            }
            int i15 = i11;
            int i16 = 0;
            while (i16 < i2) {
                if (i15 >= imageDescriptor.imageData.length) {
                    throw new ImageReadException(String.format("Invalid GIF image data length [%d], greater than the image data length [%d]", Integer.valueOf(imageDescriptor.imageData.length), Integer.valueOf(i2)));
                }
                int i17 = i15 + 1;
                int i18 = imageDescriptor.imageData[i15] & UByte.MAX_VALUE;
                if (i18 >= colorTable.length) {
                    throw new ImageReadException(String.format("Invalid GIF color table index [%d], greater than the color table length [%d]", Integer.valueOf(i18), Integer.valueOf(colorTable.length)));
                }
                int i19 = colorTable[i18];
                if (i5 == i18) {
                    i19 = 0;
                }
                imageBuilder.setRGB(i16, i, i19);
                i16++;
                i15 = i17;
            }
            i10++;
            i11 = i15;
            i4 = 1;
        }
        return imageBuilder.getBufferedImage();
    }

    @Override // org.apache.commons.imaging.ImageParser
    public List<BufferedImage> getAllBufferedImages(ByteSource byteSource) throws ImageReadException, IOException {
        GifImageContents readFile = readFile(byteSource, false);
        GifHeaderInfo gifHeaderInfo = readFile.gifHeaderInfo;
        if (gifHeaderInfo == null) {
            throw new ImageReadException("GIF: Couldn't read Header");
        }
        List<GifImageData> findAllImageData = findAllImageData(readFile);
        ArrayList arrayList = new ArrayList(findAllImageData.size());
        for (GifImageData gifImageData : findAllImageData) {
            arrayList.add(getBufferedImage(gifHeaderInfo, gifImageData, readFile.globalColorTable));
        }
        return arrayList;
    }

    @Override // org.apache.commons.imaging.ImageParser
    public BufferedImage getBufferedImage(ByteSource byteSource, GifImagingParameters gifImagingParameters) throws ImageReadException, IOException {
        GifImageContents readFile = readFile(byteSource, false);
        GifHeaderInfo gifHeaderInfo = readFile.gifHeaderInfo;
        if (gifHeaderInfo == null) {
            throw new ImageReadException("GIF: Couldn't read Header");
        }
        return getBufferedImage(gifHeaderInfo, findFirstImageData(readFile), readFile.globalColorTable);
    }

    private void writeAsSubBlocks(OutputStream outputStream, byte[] bArr) throws IOException {
        int i = 0;
        while (i < bArr.length) {
            int min = Math.min(bArr.length - i, 255);
            outputStream.write(min);
            outputStream.write(bArr, i, min);
            i += min;
        }
        outputStream.write(0);
    }

    @Override // org.apache.commons.imaging.ImageParser
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, GifImagingParameters gifImagingParameters) throws ImageWriteException, IOException {
        int paletteIndex;
        String xmpXml = (gifImagingParameters == null ? new GifImagingParameters() : gifImagingParameters).getXmpXml();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        boolean hasTransparency = new PaletteFactory().hasTransparency(bufferedImage);
        int i = hasTransparency ? 255 : 256;
        Palette makeExactRgbPaletteSimple = new PaletteFactory().makeExactRgbPaletteSimple(bufferedImage, i);
        if (makeExactRgbPaletteSimple == null) {
            makeExactRgbPaletteSimple = new PaletteFactory().makeQuantizedRgbPalette(bufferedImage, i);
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine("quantizing");
            }
        } else {
            Logger logger2 = LOGGER;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.fine("exact palette");
            }
        }
        if (makeExactRgbPaletteSimple == null) {
            throw new ImageWriteException("Gif: can't write images with more than 256 colors");
        }
        int length = makeExactRgbPaletteSimple.length() + (hasTransparency ? 1 : 0);
        BinaryOutputStream binaryOutputStream = new BinaryOutputStream(outputStream, ByteOrder.LITTLE_ENDIAN);
        outputStream.write(71);
        outputStream.write(73);
        outputStream.write(70);
        outputStream.write(56);
        outputStream.write(57);
        outputStream.write(97);
        binaryOutputStream.write2Bytes(width);
        binaryOutputStream.write2Bytes(height);
        int i2 = length > 128 ? 7 : length > 64 ? 6 : length > 32 ? 5 : length > 16 ? 4 : length > 8 ? 3 : length > 4 ? 2 : length > 2 ? 1 : 0;
        int i3 = i2 + 1;
        int i4 = 1 << i3;
        binaryOutputStream.write((((byte) i2) & 7) * 16);
        binaryOutputStream.write(0);
        binaryOutputStream.write(0);
        binaryOutputStream.write(33);
        binaryOutputStream.write(-7);
        binaryOutputStream.write(4);
        binaryOutputStream.write(hasTransparency ? (byte) 1 : (byte) 0);
        binaryOutputStream.write(0);
        binaryOutputStream.write(0);
        binaryOutputStream.write((byte) (hasTransparency ? makeExactRgbPaletteSimple.length() : 0));
        binaryOutputStream.write(0);
        if (xmpXml != null) {
            binaryOutputStream.write(33);
            binaryOutputStream.write(255);
            byte[] bArr = XMP_APPLICATION_ID_AND_AUTH_CODE;
            binaryOutputStream.write(bArr.length);
            binaryOutputStream.write(bArr);
            binaryOutputStream.write(xmpXml.getBytes(StandardCharsets.UTF_8));
            int i5 = 0;
            for (int i6 = 255; i5 <= i6; i6 = 255) {
                binaryOutputStream.write(255 - i5);
                i5++;
            }
            binaryOutputStream.write(0);
        }
        binaryOutputStream.write(44);
        binaryOutputStream.write2Bytes(0);
        binaryOutputStream.write2Bytes(0);
        binaryOutputStream.write2Bytes(width);
        binaryOutputStream.write2Bytes(height);
        binaryOutputStream.write((i2 & 7) | 128);
        for (int i7 = 0; i7 < i4; i7++) {
            if (i7 < makeExactRgbPaletteSimple.length()) {
                int entry = makeExactRgbPaletteSimple.getEntry(i7);
                binaryOutputStream.write((entry >> 16) & 255);
                binaryOutputStream.write((entry >> 8) & 255);
                binaryOutputStream.write((entry >> 0) & 255);
            } else {
                binaryOutputStream.write(0);
                binaryOutputStream.write(0);
                binaryOutputStream.write(0);
            }
        }
        int i8 = i3 < 2 ? 2 : i3;
        binaryOutputStream.write(i8);
        MyLzwCompressor myLzwCompressor = new MyLzwCompressor(i8, ByteOrder.LITTLE_ENDIAN, false);
        byte[] bArr2 = new byte[width * height];
        for (int i9 = 0; i9 < height; i9++) {
            for (int i10 = 0; i10 < width; i10++) {
                int rgb = bufferedImage.getRGB(i10, i9);
                int i11 = 16777215 & rgb;
                if (!hasTransparency) {
                    paletteIndex = makeExactRgbPaletteSimple.getPaletteIndex(i11);
                } else if (((rgb >> 24) & 255) < 255) {
                    paletteIndex = makeExactRgbPaletteSimple.length();
                } else {
                    paletteIndex = makeExactRgbPaletteSimple.getPaletteIndex(i11);
                }
                bArr2[(i9 * width) + i10] = (byte) paletteIndex;
            }
        }
        writeAsSubBlocks(binaryOutputStream, myLzwCompressor.compress(bArr2));
        binaryOutputStream.write(59);
        binaryOutputStream.close();
        outputStream.close();
    }

    @Override // org.apache.commons.imaging.common.XmpEmbeddable
    public String getXmpXml(ByteSource byteSource, XmpImagingParameters xmpImagingParameters) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            GifHeaderInfo readHeader = readHeader(inputStream, null);
            if (readHeader.globalColorTableFlag) {
                readColorTable(inputStream, readHeader.sizeOfGlobalColorTable);
            }
            List<GifBlock> readBlocks = readBlocks(readHeader, inputStream, true, null);
            ArrayList arrayList = new ArrayList();
            for (GifBlock gifBlock : readBlocks) {
                if (gifBlock.blockCode == XMP_COMPLETE_CODE) {
                    byte[] appendSubBlocks = ((GenericGifBlock) gifBlock).appendSubBlocks(true);
                    int length = appendSubBlocks.length;
                    byte[] bArr = XMP_APPLICATION_ID_AND_AUTH_CODE;
                    if (length >= bArr.length && BinaryFunctions.compareBytes(appendSubBlocks, 0, bArr, 0, bArr.length)) {
                        byte[] bArr2 = new byte[256];
                        for (int i = 0; i <= 255; i++) {
                            bArr2[i] = (byte) (255 - i);
                        }
                        int length2 = appendSubBlocks.length;
                        byte[] bArr3 = XMP_APPLICATION_ID_AND_AUTH_CODE;
                        if (length2 >= bArr3.length + 256) {
                            if (!BinaryFunctions.compareBytes(appendSubBlocks, appendSubBlocks.length - 256, bArr2, 0, 256)) {
                                throw new ImageReadException("XMP block in GIF missing magic trailer.");
                            }
                            arrayList.add(new String(appendSubBlocks, bArr3.length, appendSubBlocks.length - (bArr3.length + 256), StandardCharsets.UTF_8));
                        }
                    }
                }
            }
            if (arrayList.isEmpty()) {
                if (inputStream != null) {
                    inputStream.close();
                }
                return null;
            } else if (arrayList.size() > 1) {
                throw new ImageReadException("More than one XMP Block in GIF.");
            } else {
                String str = (String) arrayList.get(0);
                if (inputStream != null) {
                    inputStream.close();
                }
                return str;
            }
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
}
