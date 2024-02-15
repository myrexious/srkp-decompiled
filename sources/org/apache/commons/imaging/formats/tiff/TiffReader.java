package org.apache.commons.imaging.formats.tiff;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte;
import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFileParser;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffImageData;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes2.dex */
public class TiffReader extends BinaryFileParser {
    private final boolean strict;

    /* loaded from: classes2.dex */
    public interface Listener {
        boolean addDirectory(TiffDirectory tiffDirectory);

        boolean addField(TiffField tiffField);

        boolean readImageData();

        boolean readOffsetDirectories();

        boolean setTiffHeader(TiffHeader tiffHeader);
    }

    public TiffReader(boolean z) {
        this.strict = z;
    }

    private TiffHeader readTiffHeader(ByteSource byteSource) throws ImageReadException, IOException {
        InputStream inputStream = byteSource.getInputStream();
        try {
            TiffHeader readTiffHeader = readTiffHeader(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return readTiffHeader;
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

    private ByteOrder getTiffByteOrder(int i) throws ImageReadException {
        if (i == 73) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (i == 77) {
            return ByteOrder.BIG_ENDIAN;
        }
        throw new ImageReadException("Invalid TIFF byte order " + (i & 255));
    }

    private TiffHeader readTiffHeader(InputStream inputStream) throws ImageReadException, IOException {
        byte readByte = BinaryFunctions.readByte("BYTE_ORDER_1", inputStream, "Not a Valid TIFF File");
        byte readByte2 = BinaryFunctions.readByte("BYTE_ORDER_2", inputStream, "Not a Valid TIFF File");
        if (readByte != readByte2) {
            throw new ImageReadException("Byte Order bytes don't match (" + ((int) readByte) + ", " + ((int) readByte2) + ").");
        }
        ByteOrder tiffByteOrder = getTiffByteOrder(readByte);
        setByteOrder(tiffByteOrder);
        int read2Bytes = BinaryFunctions.read2Bytes("tiffVersion", inputStream, "Not a Valid TIFF File", getByteOrder());
        if (read2Bytes != 42) {
            throw new ImageReadException("Unknown Tiff Version: " + read2Bytes);
        }
        long read4Bytes = BinaryFunctions.read4Bytes("offsetToFirstIFD", inputStream, "Not a Valid TIFF File", getByteOrder()) & BodyPartID.bodyIdMax;
        BinaryFunctions.skipBytes(inputStream, read4Bytes - 8, "Not a Valid TIFF File: couldn't find IFDs");
        return new TiffHeader(tiffByteOrder, read2Bytes, read4Bytes);
    }

    private void readDirectories(ByteSource byteSource, FormatCompliance formatCompliance, Listener listener) throws ImageReadException, IOException {
        TiffHeader readTiffHeader = readTiffHeader(byteSource);
        if (listener.setTiffHeader(readTiffHeader)) {
            readDirectory(byteSource, readTiffHeader.offsetToFirstIFD, 0, formatCompliance, listener, new ArrayList());
        }
    }

    private boolean readDirectory(ByteSource byteSource, long j, int i, FormatCompliance formatCompliance, Listener listener, List<Number> list) throws ImageReadException, IOException {
        return readDirectory(byteSource, j, i, formatCompliance, listener, false, list);
    }

    /* JADX WARN: Removed duplicated region for block: B:192:0x01d0 A[Catch: all -> 0x0217, TryCatch #0 {all -> 0x0217, blocks: (B:126:0x0023, B:132:0x0033, B:133:0x003b, B:137:0x004a, B:141:0x008b, B:142:0x008f, B:146:0x00a0, B:149:0x00ab, B:150:0x00b1, B:153:0x00b6, B:154:0x00e6, B:155:0x00e7, B:161:0x0112, B:163:0x0139, B:165:0x013f, B:166:0x0146, B:168:0x014c, B:169:0x0153, B:174:0x015f, B:176:0x0165, B:178:0x0187, B:180:0x018f, B:182:0x01b5, B:192:0x01d0, B:195:0x01e0, B:188:0x01c8, B:193:0x01d6, B:197:0x01ee, B:199:0x01f4, B:204:0x020c, B:209:0x0216), top: B:219:0x0023, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:238:0x01e0 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean readDirectory(org.apache.commons.imaging.common.bytesource.ByteSource r30, long r31, int r33, org.apache.commons.imaging.FormatCompliance r34, org.apache.commons.imaging.formats.tiff.TiffReader.Listener r35, boolean r36, java.util.List<java.lang.Number> r37) throws org.apache.commons.imaging.ImageReadException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 549
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.tiff.TiffReader.readDirectory(org.apache.commons.imaging.common.bytesource.ByteSource, long, int, org.apache.commons.imaging.FormatCompliance, org.apache.commons.imaging.formats.tiff.TiffReader$Listener, boolean, java.util.List):boolean");
    }

    /* loaded from: classes2.dex */
    public static class Collector implements Listener {
        private final List<TiffDirectory> directories;
        private final List<TiffField> fields;
        private final boolean readThumbnails;
        private TiffHeader tiffHeader;

        @Override // org.apache.commons.imaging.formats.tiff.TiffReader.Listener
        public boolean readOffsetDirectories() {
            return true;
        }

        Collector() {
            this(new TiffImagingParameters());
        }

        Collector(TiffImagingParameters tiffImagingParameters) {
            this.directories = new ArrayList();
            this.fields = new ArrayList();
            this.readThumbnails = tiffImagingParameters.isReadThumbnails();
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffReader.Listener
        public boolean setTiffHeader(TiffHeader tiffHeader) {
            this.tiffHeader = tiffHeader;
            return true;
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffReader.Listener
        public boolean addDirectory(TiffDirectory tiffDirectory) {
            this.directories.add(tiffDirectory);
            return true;
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffReader.Listener
        public boolean addField(TiffField tiffField) {
            this.fields.add(tiffField);
            return true;
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffReader.Listener
        public boolean readImageData() {
            return this.readThumbnails;
        }

        public TiffContents getContents() {
            return new TiffContents(this.tiffHeader, this.directories, this.fields);
        }
    }

    /* loaded from: classes2.dex */
    public static class FirstDirectoryCollector extends Collector {
        private final boolean readImageData;

        FirstDirectoryCollector(boolean z) {
            this.readImageData = z;
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffReader.Collector, org.apache.commons.imaging.formats.tiff.TiffReader.Listener
        public boolean addDirectory(TiffDirectory tiffDirectory) {
            super.addDirectory(tiffDirectory);
            return false;
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffReader.Collector, org.apache.commons.imaging.formats.tiff.TiffReader.Listener
        public boolean readImageData() {
            return this.readImageData;
        }
    }

    public TiffContents readFirstDirectory(ByteSource byteSource, boolean z, FormatCompliance formatCompliance) throws ImageReadException, IOException {
        FirstDirectoryCollector firstDirectoryCollector = new FirstDirectoryCollector(z);
        read(byteSource, formatCompliance, firstDirectoryCollector);
        TiffContents contents = firstDirectoryCollector.getContents();
        if (contents.directories.isEmpty()) {
            throw new ImageReadException("Image did not contain any directories.");
        }
        return contents;
    }

    public TiffContents readDirectories(ByteSource byteSource, boolean z, FormatCompliance formatCompliance) throws ImageReadException, IOException {
        TiffImagingParameters tiffImagingParameters = new TiffImagingParameters();
        tiffImagingParameters.setReadThumbnails(z);
        Collector collector = new Collector(tiffImagingParameters);
        readDirectories(byteSource, formatCompliance, collector);
        TiffContents contents = collector.getContents();
        if (contents.directories.isEmpty()) {
            throw new ImageReadException("Image did not contain any directories.");
        }
        return contents;
    }

    public TiffContents readContents(ByteSource byteSource, TiffImagingParameters tiffImagingParameters, FormatCompliance formatCompliance) throws ImageReadException, IOException {
        Collector collector = new Collector(tiffImagingParameters);
        read(byteSource, formatCompliance, collector);
        return collector.getContents();
    }

    public void read(ByteSource byteSource, FormatCompliance formatCompliance, Listener listener) throws ImageReadException, IOException {
        readDirectories(byteSource, formatCompliance, listener);
    }

    private TiffImageData getTiffRawImageData(ByteSource byteSource, TiffDirectory tiffDirectory) throws ImageReadException, IOException {
        int intValue;
        List<TiffDirectory.ImageDataElement> tiffRawImageDataElements = tiffDirectory.getTiffRawImageDataElements();
        TiffImageData.Data[] dataArr = new TiffImageData.Data[tiffRawImageDataElements.size()];
        int i = 0;
        if (byteSource instanceof ByteSourceFile) {
            ByteSourceFile byteSourceFile = (ByteSourceFile) byteSource;
            while (i < tiffRawImageDataElements.size()) {
                TiffDirectory.ImageDataElement imageDataElement = tiffRawImageDataElements.get(i);
                dataArr[i] = new TiffImageData.ByteSourceData(imageDataElement.offset, imageDataElement.length, byteSourceFile);
                i++;
            }
        } else {
            while (i < tiffRawImageDataElements.size()) {
                TiffDirectory.ImageDataElement imageDataElement2 = tiffRawImageDataElements.get(i);
                dataArr[i] = new TiffImageData.Data(imageDataElement2.offset, imageDataElement2.length, byteSource.getBlock(imageDataElement2.offset, imageDataElement2.length));
                i++;
            }
        }
        if (tiffDirectory.imageDataInStrips()) {
            TiffField findField = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_ROWS_PER_STRIP);
            if (findField != null) {
                intValue = findField.getIntValue();
            } else {
                TiffField findField2 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH);
                intValue = findField2 != null ? findField2.getIntValue() : Integer.MAX_VALUE;
            }
            return new TiffImageData.Strips(dataArr, intValue);
        }
        TiffField findField3 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_TILE_WIDTH);
        if (findField3 == null) {
            throw new ImageReadException("Can't find tile width field.");
        }
        int intValue2 = findField3.getIntValue();
        TiffField findField4 = tiffDirectory.findField(TiffTagConstants.TIFF_TAG_TILE_LENGTH);
        if (findField4 == null) {
            throw new ImageReadException("Can't find tile length field.");
        }
        return new TiffImageData.Tiles(dataArr, intValue2, findField4.getIntValue());
    }

    private JpegImageData getJpegRawImageData(ByteSource byteSource, TiffDirectory tiffDirectory) throws ImageReadException, IOException {
        TiffDirectory.ImageDataElement jpegRawImageDataElement = tiffDirectory.getJpegRawImageDataElement();
        long j = jpegRawImageDataElement.offset;
        int i = jpegRawImageDataElement.length;
        if (i + j > byteSource.getLength()) {
            i = (int) (byteSource.getLength() - j);
        }
        byte[] block = byteSource.getBlock(j, i);
        if (this.strict && (i < 2 || (((block[block.length - 2] & UByte.MAX_VALUE) << 8) | (block[block.length - 1] & UByte.MAX_VALUE)) != 65497)) {
            throw new ImageReadException("JPEG EOI marker could not be found at expected location");
        }
        return new JpegImageData(j, i, block);
    }
}
