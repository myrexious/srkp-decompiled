package org.apache.commons.imaging.formats.jpeg.iptc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.ImagingConstants;
import org.apache.commons.imaging.ImagingParameters;
import org.apache.commons.imaging.common.BinaryFileParser;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.formats.jpeg.JpegConstants;
import org.apache.commons.imaging.internal.Debug;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class IptcParser extends BinaryFileParser {
    private static final int ENV_TAG_CODED_CHARACTER_SET = 90;
    private static final Logger LOGGER = Logger.getLogger(IptcParser.class.getName());
    private static final ByteOrder APP13_BYTE_ORDER = ByteOrder.BIG_ENDIAN;
    private static final List<Integer> PHOTOSHOP_IGNORED_BLOCK_TYPE = Arrays.asList(1084, 1085, 1086, 1087);
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;
    private static final byte[] CHARACTER_ESCAPE_SEQUENCE = {BuiltinOptions.ReducerOptions, BuiltinOptions.CastOptions, BuiltinOptions.UnidirectionalSequenceLSTMOptions};

    public IptcParser() {
        setByteOrder(ByteOrder.BIG_ENDIAN);
    }

    public boolean isPhotoshopJpegSegment(byte[] bArr) {
        if (BinaryFunctions.startsWith(bArr, JpegConstants.PHOTOSHOP_IDENTIFICATION_STRING)) {
            int size = JpegConstants.PHOTOSHOP_IDENTIFICATION_STRING.size();
            return size + 4 <= bArr.length && ByteConversions.toInt(bArr, size, APP13_BYTE_ORDER) == JpegConstants.CONST_8BIM;
        }
        return false;
    }

    public PhotoshopApp13Data parsePhotoshopSegment(byte[] bArr, ImagingParameters imagingParameters) throws ImageReadException, IOException {
        return parsePhotoshopSegment(bArr, imagingParameters != null && imagingParameters.isStrict());
    }

    public PhotoshopApp13Data parsePhotoshopSegment(byte[] bArr, boolean z) throws ImageReadException, IOException {
        ArrayList arrayList = new ArrayList();
        List<IptcBlock> parseAllBlocks = parseAllBlocks(bArr, z);
        for (IptcBlock iptcBlock : parseAllBlocks) {
            if (iptcBlock.isIPTCBlock()) {
                arrayList.addAll(parseIPTCBlock(iptcBlock.getBlockData()));
            }
        }
        return new PhotoshopApp13Data(arrayList, parseAllBlocks);
    }

    protected List<IptcRecord> parseIPTCBlock(byte[] bArr) {
        Charset charset = DEFAULT_CHARSET;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i2 >= bArr.length) {
                return arrayList;
            }
            int i3 = bArr[i] & UByte.MAX_VALUE;
            Debug.debug("tagMarker: " + i3 + " (0x" + Integer.toHexString(i3) + ")");
            if (i3 != 28) {
                Logger logger = LOGGER;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine("Unexpected record tag marker in IPTC data.");
                }
                return arrayList;
            }
            int i4 = i2 + 1;
            int i5 = bArr[i2] & UByte.MAX_VALUE;
            Debug.debug("recordNumber: " + i5 + " (0x" + Integer.toHexString(i5) + ")");
            int i6 = bArr[i4] & UByte.MAX_VALUE;
            Debug.debug("recordType: " + i6 + " (0x" + Integer.toHexString(i6) + ")");
            int i7 = i4 + 1;
            int uInt16 = ByteConversions.toUInt16(bArr, i7, getByteOrder());
            int i8 = i7 + 2;
            boolean z = uInt16 > 32767;
            int i9 = uInt16 & IptcConstants.IPTC_NON_EXTENDED_RECORD_MAXIMUM_SIZE;
            if (z) {
                Debug.debug("extendedDataset. dataFieldCountLength: " + i9);
            }
            if (z) {
                return arrayList;
            }
            byte[] slice = BinaryFunctions.slice(bArr, i8, uInt16);
            i = i8 + uInt16;
            if (i5 == 1 && i6 == 90) {
                charset = findCharset(slice);
            } else if (i5 == 2) {
                if (i6 == 0) {
                    Logger logger2 = LOGGER;
                    if (logger2.isLoggable(Level.FINE)) {
                        logger2.fine("ignore record version record! " + arrayList.size());
                    }
                } else {
                    arrayList.add(new IptcRecord(IptcTypeLookup.getIptcType(i6), new String(slice, charset)));
                }
            }
        }
    }

    protected List<IptcBlock> parseAllBlocks(byte[] bArr, boolean z) throws ImageReadException, IOException {
        byte[] bArr2;
        ArrayList arrayList = new ArrayList();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            if (!JpegConstants.PHOTOSHOP_IDENTIFICATION_STRING.equals(BinaryFunctions.readBytes("", byteArrayInputStream, JpegConstants.PHOTOSHOP_IDENTIFICATION_STRING.size(), "App13 Segment missing identification string"))) {
                throw new ImageReadException("Not a Photoshop App13 Segment");
            }
            while (true) {
                try {
                    ByteOrder byteOrder = APP13_BYTE_ORDER;
                    if (BinaryFunctions.read4Bytes("", byteArrayInputStream, "Image Resource Block missing identification string", byteOrder) != JpegConstants.CONST_8BIM) {
                        throw new ImageReadException("Invalid Image Resource Block Signature");
                    }
                    int read2Bytes = BinaryFunctions.read2Bytes("", byteArrayInputStream, "Image Resource Block missing type", byteOrder);
                    Debug.debug("blockType: " + read2Bytes + " (0x" + Integer.toHexString(read2Bytes) + ")");
                    if (PHOTOSHOP_IGNORED_BLOCK_TYPE.contains(Integer.valueOf(read2Bytes))) {
                        Debug.debug("Skipping blockType: " + read2Bytes + " (0x" + Integer.toHexString(read2Bytes) + ")");
                        BinaryFunctions.searchQuad(JpegConstants.CONST_8BIM, byteArrayInputStream);
                    } else {
                        byte readByte = BinaryFunctions.readByte("Name length", byteArrayInputStream, "Image Resource Block missing name length");
                        if (readByte > 0) {
                            Debug.debug("blockNameLength: " + ((int) readByte) + " (0x" + Integer.toHexString(readByte) + ")");
                        }
                        if (readByte == 0) {
                            BinaryFunctions.readByte("Block name bytes", byteArrayInputStream, "Image Resource Block has invalid name");
                            bArr2 = ImagingConstants.EMPTY_BYTE_ARRAY;
                        } else {
                            try {
                                byte[] readBytes = BinaryFunctions.readBytes("", byteArrayInputStream, readByte, "Invalid Image Resource Block name");
                                if (readByte % 2 == 0) {
                                    BinaryFunctions.readByte("Padding byte", byteArrayInputStream, "Image Resource Block missing padding byte");
                                }
                                bArr2 = readBytes;
                            } catch (IOException e) {
                                if (z) {
                                    throw e;
                                }
                                byteArrayInputStream.close();
                                return arrayList;
                            }
                        }
                        int read4Bytes = BinaryFunctions.read4Bytes("", byteArrayInputStream, "Image Resource Block missing size", byteOrder);
                        Debug.debug("blockSize: " + read4Bytes + " (0x" + Integer.toHexString(read4Bytes) + ")");
                        if (read4Bytes > bArr.length) {
                            throw new ImageReadException("Invalid Block Size : " + read4Bytes + " > " + bArr.length);
                        }
                        try {
                            arrayList.add(new IptcBlock(read2Bytes, bArr2, BinaryFunctions.readBytes("", byteArrayInputStream, read4Bytes, "Invalid Image Resource Block data")));
                            if (read4Bytes % 2 != 0) {
                                BinaryFunctions.readByte("Padding byte", byteArrayInputStream, "Image Resource Block missing padding byte");
                            }
                        } catch (IOException e2) {
                            if (z) {
                                throw e2;
                            }
                            byteArrayInputStream.close();
                            return arrayList;
                        }
                    }
                } catch (IOException unused) {
                }
            }
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public byte[] writePhotoshopApp13Segment(PhotoshopApp13Data photoshopApp13Data) throws IOException, ImageWriteException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryOutputStream binaryOutputStream = new BinaryOutputStream(byteArrayOutputStream);
        JpegConstants.PHOTOSHOP_IDENTIFICATION_STRING.writeTo(binaryOutputStream);
        for (IptcBlock iptcBlock : photoshopApp13Data.getRawBlocks()) {
            binaryOutputStream.write4Bytes(JpegConstants.CONST_8BIM);
            if (iptcBlock.getBlockType() < 0 || iptcBlock.getBlockType() > 65535) {
                throw new ImageWriteException("Invalid IPTC block type.");
            }
            binaryOutputStream.write2Bytes(iptcBlock.getBlockType());
            byte[] blockNameBytes = iptcBlock.getBlockNameBytes();
            if (blockNameBytes.length > 255) {
                throw new ImageWriteException("IPTC block name is too long: " + blockNameBytes.length);
            }
            binaryOutputStream.write(blockNameBytes.length);
            binaryOutputStream.write(blockNameBytes);
            if (blockNameBytes.length % 2 == 0) {
                binaryOutputStream.write(0);
            }
            byte[] blockData = iptcBlock.getBlockData();
            if (blockData.length > 32767) {
                throw new ImageWriteException("IPTC block data is too long: " + blockData.length);
            }
            binaryOutputStream.write4Bytes(blockData.length);
            binaryOutputStream.write(blockData);
            if (blockData.length % 2 == 1) {
                binaryOutputStream.write(0);
            }
        }
        binaryOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] writeIPTCBlock(List<IptcRecord> list) throws ImageWriteException, IOException {
        Charset charset = DEFAULT_CHARSET;
        Iterator<IptcRecord> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            IptcRecord next = it.next();
            if (!new String(next.getValue().getBytes(charset), charset).equals(next.getValue())) {
                charset = StandardCharsets.UTF_8;
                break;
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryOutputStream binaryOutputStream = new BinaryOutputStream(byteArrayOutputStream, getByteOrder());
        try {
            if (!charset.equals(DEFAULT_CHARSET)) {
                binaryOutputStream.write(28);
                binaryOutputStream.write(1);
                binaryOutputStream.write(90);
                byte[] bArr = CHARACTER_ESCAPE_SEQUENCE;
                binaryOutputStream.write2Bytes(bArr.length);
                binaryOutputStream.write(bArr);
            }
            binaryOutputStream.write(28);
            binaryOutputStream.write(2);
            binaryOutputStream.write(IptcTypes.RECORD_VERSION.type);
            binaryOutputStream.write2Bytes(2);
            binaryOutputStream.write2Bytes(2);
            ArrayList<IptcRecord> arrayList = new ArrayList(list);
            arrayList.sort(new Comparator() { // from class: org.apache.commons.imaging.formats.jpeg.iptc.IptcParser$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return IptcParser.lambda$writeIPTCBlock$0((IptcRecord) obj, (IptcRecord) obj2);
                }
            });
            for (IptcRecord iptcRecord : arrayList) {
                if (iptcRecord.iptcType != IptcTypes.RECORD_VERSION) {
                    binaryOutputStream.write(28);
                    binaryOutputStream.write(2);
                    if (iptcRecord.iptcType.getType() < 0 || iptcRecord.iptcType.getType() > 255) {
                        throw new ImageWriteException("Invalid record type: " + iptcRecord.iptcType.getType());
                    }
                    binaryOutputStream.write(iptcRecord.iptcType.getType());
                    byte[] bytes = iptcRecord.getValue().getBytes(charset);
                    binaryOutputStream.write2Bytes(bytes.length);
                    binaryOutputStream.write(bytes);
                }
            }
            binaryOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                binaryOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static /* synthetic */ int lambda$writeIPTCBlock$0(IptcRecord iptcRecord, IptcRecord iptcRecord2) {
        return iptcRecord2.iptcType.getType() - iptcRecord.iptcType.getType();
    }

    private Charset findCharset(byte[] bArr) {
        String str = new String(bArr, StandardCharsets.ISO_8859_1);
        try {
            if (Charset.isSupported(str)) {
                return Charset.forName(str);
            }
        } catch (IllegalArgumentException unused) {
        }
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (byte b : bArr) {
            if (b != 32) {
                bArr2[i] = b;
                i++;
            }
        }
        if (Objects.deepEquals(bArr2, CHARACTER_ESCAPE_SEQUENCE)) {
            return StandardCharsets.UTF_8;
        }
        return DEFAULT_CHARSET;
    }
}
