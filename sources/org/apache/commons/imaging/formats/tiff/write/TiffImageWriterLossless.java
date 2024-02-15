package org.apache.commons.imaging.formats.tiff.write;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.formats.tiff.JpegImageData;
import org.apache.commons.imaging.formats.tiff.TiffContents;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffElement;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.TiffImageData;
import org.apache.commons.imaging.formats.tiff.TiffImagingParameters;
import org.apache.commons.imaging.formats.tiff.TiffReader;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;

/* loaded from: classes2.dex */
public class TiffImageWriterLossless extends TiffImageWriterBase {
    private static final Comparator<TiffElement> ELEMENT_SIZE_COMPARATOR = Comparator.comparingInt(new ToIntFunction() { // from class: org.apache.commons.imaging.formats.tiff.write.TiffImageWriterLossless$$ExternalSyntheticLambda0
        @Override // java.util.function.ToIntFunction
        public final int applyAsInt(Object obj) {
            int i;
            i = ((TiffElement) obj).length;
            return i;
        }
    });
    private static final Comparator<TiffOutputItem> ITEM_SIZE_COMPARATOR = Comparator.comparingInt(new ToIntFunction() { // from class: org.apache.commons.imaging.formats.tiff.write.TiffImageWriterLossless$$ExternalSyntheticLambda1
        @Override // java.util.function.ToIntFunction
        public final int applyAsInt(Object obj) {
            return ((TiffOutputItem) obj).getItemLength();
        }
    });
    private final byte[] exifBytes;

    public TiffImageWriterLossless(byte[] bArr) {
        this.exifBytes = bArr;
    }

    public TiffImageWriterLossless(ByteOrder byteOrder, byte[] bArr) {
        super(byteOrder);
        this.exifBytes = bArr;
    }

    private List<TiffElement> analyzeOldTiff(Map<Integer, TiffOutputField> map) throws ImageWriteException, IOException {
        try {
            TiffContents readContents = new TiffReader(false).readContents(new ByteSourceArray(this.exifBytes), new TiffImagingParameters(), FormatCompliance.getDefault());
            ArrayList<TiffElement> arrayList = new ArrayList();
            for (TiffDirectory tiffDirectory : readContents.directories) {
                arrayList.add(tiffDirectory);
                for (TiffField tiffField : tiffDirectory.getDirectoryEntries()) {
                    TiffElement oversizeValueElement = tiffField.getOversizeValueElement();
                    if (oversizeValueElement != null) {
                        TiffOutputField tiffOutputField = map.get(Integer.valueOf(tiffField.getTag()));
                        if (tiffOutputField != null && tiffOutputField.getSeperateValue() != null && tiffOutputField.bytesEqual(tiffField.getByteArrayValue())) {
                            tiffOutputField.getSeperateValue().setOffset(tiffField.getOffset());
                        } else {
                            arrayList.add(oversizeValueElement);
                        }
                    }
                }
                JpegImageData jpegImageData = tiffDirectory.getJpegImageData();
                if (jpegImageData != null) {
                    arrayList.add(jpegImageData);
                }
                TiffImageData tiffImageData = tiffDirectory.getTiffImageData();
                if (tiffImageData != null) {
                    Collections.addAll(arrayList, tiffImageData.getImageData());
                }
            }
            arrayList.sort(TiffElement.COMPARATOR);
            ArrayList arrayList2 = new ArrayList();
            TiffElement tiffElement = null;
            long j = -1;
            for (TiffElement tiffElement2 : arrayList) {
                long j2 = tiffElement2.offset + tiffElement2.length;
                if (tiffElement != null) {
                    if (tiffElement2.offset - j > 3) {
                        arrayList2.add(new TiffElement.Stub(tiffElement.offset, (int) (j - tiffElement.offset)));
                    } else {
                        j = j2;
                    }
                }
                tiffElement = tiffElement2;
                j = j2;
            }
            if (tiffElement != null) {
                arrayList2.add(new TiffElement.Stub(tiffElement.offset, (int) (j - tiffElement.offset)));
            }
            return arrayList2;
        } catch (ImageReadException e) {
            throw new ImageWriteException(e.getMessage(), (Throwable) e);
        }
    }

    @Override // org.apache.commons.imaging.formats.tiff.write.TiffImageWriterBase
    public void write(OutputStream outputStream, TiffOutputSet tiffOutputSet) throws IOException, ImageWriteException {
        HashMap hashMap = new HashMap();
        TiffOutputField findField = tiffOutputSet.findField(ExifTagConstants.EXIF_TAG_MAKER_NOTE);
        if (findField != null && findField.getSeperateValue() != null) {
            hashMap.put(Integer.valueOf(ExifTagConstants.EXIF_TAG_MAKER_NOTE.tag), findField);
        }
        List<TiffElement> analyzeOldTiff = analyzeOldTiff(hashMap);
        int length = this.exifBytes.length;
        if (analyzeOldTiff.isEmpty()) {
            throw new ImageWriteException("Couldn't analyze old tiff data.");
        }
        if (analyzeOldTiff.size() == 1) {
            TiffElement tiffElement = analyzeOldTiff.get(0);
            if (tiffElement.offset == 8 && tiffElement.offset + tiffElement.length + 8 == length) {
                new TiffImageWriterLossy(this.byteOrder).write(outputStream, tiffOutputSet);
                return;
            }
        }
        HashMap hashMap2 = new HashMap();
        for (Map.Entry<Integer, TiffOutputField> entry : hashMap.entrySet()) {
            TiffOutputField value = entry.getValue();
            if (value.getSeperateValue().getOffset() != -1) {
                hashMap2.put(Long.valueOf(value.getSeperateValue().getOffset()), value);
            }
        }
        TiffOutputSummary validateDirectories = validateDirectories(tiffOutputSet);
        List<TiffOutputItem> outputItems = tiffOutputSet.getOutputItems(validateDirectories);
        ArrayList arrayList = new ArrayList();
        for (TiffOutputItem tiffOutputItem : outputItems) {
            if (!hashMap2.containsKey(Long.valueOf(tiffOutputItem.getOffset()))) {
                arrayList.add(tiffOutputItem);
            }
        }
        long updateOffsetsStep = updateOffsetsStep(analyzeOldTiff, arrayList);
        validateDirectories.updateOffsets(this.byteOrder);
        writeStep(outputStream, tiffOutputSet, analyzeOldTiff, arrayList, updateOffsetsStep);
    }

    private long updateOffsetsStep(List<TiffElement> list, List<TiffOutputItem> list2) {
        TiffElement tiffElement;
        long length = this.exifBytes.length;
        ArrayList<TiffElement> arrayList = new ArrayList(list);
        arrayList.sort(TiffElement.COMPARATOR);
        Collections.reverse(arrayList);
        while (!arrayList.isEmpty()) {
            if (((TiffElement) arrayList.get(0)).offset + tiffElement.length != length) {
                break;
            }
            length -= tiffElement.length;
            arrayList.remove(0);
        }
        arrayList.sort(ELEMENT_SIZE_COMPARATOR);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(list2);
        arrayList2.sort(ITEM_SIZE_COMPARATOR);
        Collections.reverse(arrayList2);
        while (!arrayList2.isEmpty()) {
            TiffOutputItem tiffOutputItem = (TiffOutputItem) arrayList2.remove(0);
            int itemLength = tiffOutputItem.getItemLength();
            TiffElement tiffElement2 = null;
            for (TiffElement tiffElement3 : arrayList) {
                if (tiffElement3.length < itemLength) {
                    break;
                }
                tiffElement2 = tiffElement3;
            }
            if (tiffElement2 == null) {
                if ((length & 1) != 0) {
                    length++;
                }
                tiffOutputItem.setOffset(length);
                length += itemLength;
            } else {
                long j = tiffElement2.offset;
                if ((j & 1) != 0) {
                    j++;
                }
                tiffOutputItem.setOffset(j);
                arrayList.remove(tiffElement2);
                if (tiffElement2.length > itemLength) {
                    arrayList.add(new TiffElement.Stub(tiffElement2.offset + itemLength, tiffElement2.length - itemLength));
                    arrayList.sort(ELEMENT_SIZE_COMPARATOR);
                    Collections.reverse(arrayList);
                }
            }
        }
        return length;
    }

    /* loaded from: classes2.dex */
    public static class BufferOutputStream extends OutputStream {
        private final byte[] buffer;
        private int index;

        BufferOutputStream(byte[] bArr, int i) {
            this.buffer = bArr;
            this.index = i;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            int i2 = this.index;
            byte[] bArr = this.buffer;
            if (i2 >= bArr.length) {
                throw new IOException("Buffer overflow.");
            }
            this.index = i2 + 1;
            bArr[i2] = (byte) i;
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.index;
            int i4 = i3 + i2;
            byte[] bArr2 = this.buffer;
            if (i4 > bArr2.length) {
                throw new IOException("Buffer overflow.");
            }
            System.arraycopy(bArr, i, bArr2, i3, i2);
            this.index += i2;
        }
    }

    private void writeStep(OutputStream outputStream, TiffOutputSet tiffOutputSet, List<TiffElement> list, List<TiffOutputItem> list2, long j) throws IOException, ImageWriteException {
        TiffOutputDirectory rootDirectory = tiffOutputSet.getRootDirectory();
        int i = (int) j;
        byte[] bArr = new byte[i];
        byte[] bArr2 = this.exifBytes;
        System.arraycopy(bArr2, 0, bArr, 0, Math.min(bArr2.length, i));
        writeImageFileHeader(new BinaryOutputStream(new BufferOutputStream(bArr, 0), this.byteOrder), rootDirectory.getOffset());
        for (TiffElement tiffElement : list) {
            Arrays.fill(bArr, (int) tiffElement.offset, (int) Math.min(tiffElement.offset + tiffElement.length, i), (byte) 0);
        }
        for (TiffOutputItem tiffOutputItem : list2) {
            BinaryOutputStream binaryOutputStream = new BinaryOutputStream(new BufferOutputStream(bArr, (int) tiffOutputItem.getOffset()), this.byteOrder);
            try {
                tiffOutputItem.writeItem(binaryOutputStream);
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
        outputStream.write(bArr);
    }
}
