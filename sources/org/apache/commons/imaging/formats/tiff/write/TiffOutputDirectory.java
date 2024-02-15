package org.apache.commons.imaging.formats.tiff.write;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.JpegImageData;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffElement;
import org.apache.commons.imaging.formats.tiff.TiffImageData;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAsciiOrByte;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAsciiOrRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoByte;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoByteOrShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoBytes;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoDouble;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoDoubles;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoFloat;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoFloats;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoGpsText;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLongs;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRationals;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSByte;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSBytes;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSLongs;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSRationals;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSShorts;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShortOrLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShortOrLongOrRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShortOrRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShorts;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoXpString;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputItem;

/* loaded from: classes2.dex */
public final class TiffOutputDirectory extends TiffOutputItem {
    public static final Comparator<TiffOutputDirectory> COMPARATOR = Comparator.comparingInt(new ToIntFunction() { // from class: org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory$$ExternalSyntheticLambda0
        @Override // java.util.function.ToIntFunction
        public final int applyAsInt(Object obj) {
            int i;
            i = ((TiffOutputDirectory) obj).type;
            return i;
        }
    });
    private final ByteOrder byteOrder;
    private final List<TiffOutputField> fields = new ArrayList();
    private JpegImageData jpegImageData;
    private TiffOutputDirectory nextDirectory;
    private TiffImageData tiffImageData;
    public final int type;

    public void setNextDirectory(TiffOutputDirectory tiffOutputDirectory) {
        this.nextDirectory = tiffOutputDirectory;
    }

    public TiffOutputDirectory(int i, ByteOrder byteOrder) {
        this.type = i;
        this.byteOrder = byteOrder;
    }

    public void add(TagInfoByte tagInfoByte, byte b) throws ImageWriteException {
        if (tagInfoByte.length != 1) {
            throw new ImageWriteException("Tag expects " + tagInfoByte.length + " value(s), not 1");
        }
        byte[] encodeValue = tagInfoByte.encodeValue(this.byteOrder, b);
        add(new TiffOutputField(tagInfoByte.tag, tagInfoByte, FieldType.BYTE, encodeValue.length, encodeValue));
    }

    public void add(TagInfoBytes tagInfoBytes, byte... bArr) throws ImageWriteException {
        if (tagInfoBytes.length > 0 && tagInfoBytes.length != bArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoBytes.length + " value(s), not " + bArr.length);
        }
        add(new TiffOutputField(tagInfoBytes.tag, tagInfoBytes, FieldType.BYTE, bArr.length, tagInfoBytes.encodeValue(this.byteOrder, bArr)));
    }

    public void add(TagInfoAscii tagInfoAscii, String... strArr) throws ImageWriteException {
        byte[] encodeValue = tagInfoAscii.encodeValue(this.byteOrder, strArr);
        if (tagInfoAscii.length > 0 && tagInfoAscii.length != encodeValue.length) {
            throw new ImageWriteException("Tag expects " + tagInfoAscii.length + " byte(s), not " + strArr.length);
        }
        add(new TiffOutputField(tagInfoAscii.tag, tagInfoAscii, FieldType.ASCII, encodeValue.length, encodeValue));
    }

    public void add(TagInfoShort tagInfoShort, short s) throws ImageWriteException {
        if (tagInfoShort.length != 1) {
            throw new ImageWriteException("Tag expects " + tagInfoShort.length + " value(s), not 1");
        }
        add(new TiffOutputField(tagInfoShort.tag, tagInfoShort, FieldType.SHORT, 1, tagInfoShort.encodeValue(this.byteOrder, s)));
    }

    public void add(TagInfoShorts tagInfoShorts, short... sArr) throws ImageWriteException {
        if (tagInfoShorts.length > 0 && tagInfoShorts.length != sArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoShorts.length + " value(s), not " + sArr.length);
        }
        add(new TiffOutputField(tagInfoShorts.tag, tagInfoShorts, FieldType.SHORT, sArr.length, tagInfoShorts.encodeValue(this.byteOrder, sArr)));
    }

    public void add(TagInfoLong tagInfoLong, int i) throws ImageWriteException {
        if (tagInfoLong.length != 1) {
            throw new ImageWriteException("Tag expects " + tagInfoLong.length + " value(s), not 1");
        }
        add(new TiffOutputField(tagInfoLong.tag, tagInfoLong, FieldType.LONG, 1, tagInfoLong.encodeValue(this.byteOrder, i)));
    }

    public void add(TagInfoLongs tagInfoLongs, int... iArr) throws ImageWriteException {
        if (tagInfoLongs.length > 0 && tagInfoLongs.length != iArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoLongs.length + " value(s), not " + iArr.length);
        }
        add(new TiffOutputField(tagInfoLongs.tag, tagInfoLongs, FieldType.LONG, iArr.length, tagInfoLongs.encodeValue(this.byteOrder, iArr)));
    }

    public void add(TagInfoRational tagInfoRational, RationalNumber rationalNumber) throws ImageWriteException {
        if (tagInfoRational.length != 1) {
            throw new ImageWriteException("Tag expects " + tagInfoRational.length + " value(s), not 1");
        }
        add(new TiffOutputField(tagInfoRational.tag, tagInfoRational, FieldType.RATIONAL, 1, tagInfoRational.encodeValue(this.byteOrder, rationalNumber)));
    }

    public void add(TagInfoRationals tagInfoRationals, RationalNumber... rationalNumberArr) throws ImageWriteException {
        if (tagInfoRationals.length > 0 && tagInfoRationals.length != rationalNumberArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoRationals.length + " value(s), not " + rationalNumberArr.length);
        }
        add(new TiffOutputField(tagInfoRationals.tag, tagInfoRationals, FieldType.RATIONAL, rationalNumberArr.length, tagInfoRationals.encodeValue(this.byteOrder, rationalNumberArr)));
    }

    public void add(TagInfoSByte tagInfoSByte, byte b) throws ImageWriteException {
        if (tagInfoSByte.length != 1) {
            throw new ImageWriteException("Tag expects " + tagInfoSByte.length + " value(s), not 1");
        }
        add(new TiffOutputField(tagInfoSByte.tag, tagInfoSByte, FieldType.SBYTE, 1, tagInfoSByte.encodeValue(this.byteOrder, b)));
    }

    public void add(TagInfoSBytes tagInfoSBytes, byte... bArr) throws ImageWriteException {
        if (tagInfoSBytes.length > 0 && tagInfoSBytes.length != bArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoSBytes.length + " value(s), not " + bArr.length);
        }
        add(new TiffOutputField(tagInfoSBytes.tag, tagInfoSBytes, FieldType.SBYTE, bArr.length, tagInfoSBytes.encodeValue(this.byteOrder, bArr)));
    }

    public void add(TagInfoSShort tagInfoSShort, short s) throws ImageWriteException {
        if (tagInfoSShort.length != 1) {
            throw new ImageWriteException("Tag expects " + tagInfoSShort.length + " value(s), not 1");
        }
        add(new TiffOutputField(tagInfoSShort.tag, tagInfoSShort, FieldType.SSHORT, 1, tagInfoSShort.encodeValue(this.byteOrder, s)));
    }

    public void add(TagInfoSShorts tagInfoSShorts, short... sArr) throws ImageWriteException {
        if (tagInfoSShorts.length > 0 && tagInfoSShorts.length != sArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoSShorts.length + " value(s), not " + sArr.length);
        }
        add(new TiffOutputField(tagInfoSShorts.tag, tagInfoSShorts, FieldType.SSHORT, sArr.length, tagInfoSShorts.encodeValue(this.byteOrder, sArr)));
    }

    public void add(TagInfoSLong tagInfoSLong, int i) throws ImageWriteException {
        if (tagInfoSLong.length != 1) {
            throw new ImageWriteException("Tag expects " + tagInfoSLong.length + " value(s), not 1");
        }
        add(new TiffOutputField(tagInfoSLong.tag, tagInfoSLong, FieldType.SLONG, 1, tagInfoSLong.encodeValue(this.byteOrder, i)));
    }

    public void add(TagInfoSLongs tagInfoSLongs, int... iArr) throws ImageWriteException {
        if (tagInfoSLongs.length > 0 && tagInfoSLongs.length != iArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoSLongs.length + " value(s), not " + iArr.length);
        }
        add(new TiffOutputField(tagInfoSLongs.tag, tagInfoSLongs, FieldType.SLONG, iArr.length, tagInfoSLongs.encodeValue(this.byteOrder, iArr)));
    }

    public void add(TagInfoSRational tagInfoSRational, RationalNumber rationalNumber) throws ImageWriteException {
        if (tagInfoSRational.length != 1) {
            throw new ImageWriteException("Tag expects " + tagInfoSRational.length + " value(s), not 1");
        }
        add(new TiffOutputField(tagInfoSRational.tag, tagInfoSRational, FieldType.SRATIONAL, 1, tagInfoSRational.encodeValue(this.byteOrder, rationalNumber)));
    }

    public void add(TagInfoSRationals tagInfoSRationals, RationalNumber... rationalNumberArr) throws ImageWriteException {
        if (tagInfoSRationals.length > 0 && tagInfoSRationals.length != rationalNumberArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoSRationals.length + " value(s), not " + rationalNumberArr.length);
        }
        add(new TiffOutputField(tagInfoSRationals.tag, tagInfoSRationals, FieldType.SRATIONAL, rationalNumberArr.length, tagInfoSRationals.encodeValue(this.byteOrder, rationalNumberArr)));
    }

    public void add(TagInfoFloat tagInfoFloat, float f) throws ImageWriteException {
        if (tagInfoFloat.length != 1) {
            throw new ImageWriteException("Tag expects " + tagInfoFloat.length + " value(s), not 1");
        }
        add(new TiffOutputField(tagInfoFloat.tag, tagInfoFloat, FieldType.FLOAT, 1, tagInfoFloat.encodeValue(this.byteOrder, f)));
    }

    public void add(TagInfoFloats tagInfoFloats, float... fArr) throws ImageWriteException {
        if (tagInfoFloats.length > 0 && tagInfoFloats.length != fArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoFloats.length + " value(s), not " + fArr.length);
        }
        add(new TiffOutputField(tagInfoFloats.tag, tagInfoFloats, FieldType.FLOAT, fArr.length, tagInfoFloats.encodeValue(this.byteOrder, fArr)));
    }

    public void add(TagInfoDouble tagInfoDouble, double d) throws ImageWriteException {
        if (tagInfoDouble.length != 1) {
            throw new ImageWriteException("Tag expects " + tagInfoDouble.length + " value(s), not 1");
        }
        add(new TiffOutputField(tagInfoDouble.tag, tagInfoDouble, FieldType.DOUBLE, 1, tagInfoDouble.encodeValue(this.byteOrder, d)));
    }

    public void add(TagInfoDoubles tagInfoDoubles, double... dArr) throws ImageWriteException {
        if (tagInfoDoubles.length > 0 && tagInfoDoubles.length != dArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoDoubles.length + " value(s), not " + dArr.length);
        }
        add(new TiffOutputField(tagInfoDoubles.tag, tagInfoDoubles, FieldType.DOUBLE, dArr.length, tagInfoDoubles.encodeValue(this.byteOrder, dArr)));
    }

    public void add(TagInfoByteOrShort tagInfoByteOrShort, byte... bArr) throws ImageWriteException {
        if (tagInfoByteOrShort.length > 0 && tagInfoByteOrShort.length != bArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoByteOrShort.length + " value(s), not " + bArr.length);
        }
        add(new TiffOutputField(tagInfoByteOrShort.tag, tagInfoByteOrShort, FieldType.BYTE, bArr.length, tagInfoByteOrShort.encodeValue(this.byteOrder, bArr)));
    }

    public void add(TagInfoByteOrShort tagInfoByteOrShort, short... sArr) throws ImageWriteException {
        if (tagInfoByteOrShort.length > 0 && tagInfoByteOrShort.length != sArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoByteOrShort.length + " value(s), not " + sArr.length);
        }
        add(new TiffOutputField(tagInfoByteOrShort.tag, tagInfoByteOrShort, FieldType.SHORT, sArr.length, tagInfoByteOrShort.encodeValue(this.byteOrder, sArr)));
    }

    public void add(TagInfoShortOrLong tagInfoShortOrLong, short... sArr) throws ImageWriteException {
        if (tagInfoShortOrLong.length > 0 && tagInfoShortOrLong.length != sArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoShortOrLong.length + " value(s), not " + sArr.length);
        }
        add(new TiffOutputField(tagInfoShortOrLong.tag, tagInfoShortOrLong, FieldType.SHORT, sArr.length, tagInfoShortOrLong.encodeValue(this.byteOrder, sArr)));
    }

    public void add(TagInfoShortOrLong tagInfoShortOrLong, int... iArr) throws ImageWriteException {
        if (tagInfoShortOrLong.length > 0 && tagInfoShortOrLong.length != iArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoShortOrLong.length + " value(s), not " + iArr.length);
        }
        add(new TiffOutputField(tagInfoShortOrLong.tag, tagInfoShortOrLong, FieldType.LONG, iArr.length, tagInfoShortOrLong.encodeValue(this.byteOrder, iArr)));
    }

    public void add(TagInfoShortOrLongOrRational tagInfoShortOrLongOrRational, short... sArr) throws ImageWriteException {
        if (tagInfoShortOrLongOrRational.length > 0 && tagInfoShortOrLongOrRational.length != sArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoShortOrLongOrRational.length + " value(s), not " + sArr.length);
        }
        add(new TiffOutputField(tagInfoShortOrLongOrRational.tag, tagInfoShortOrLongOrRational, FieldType.SHORT, sArr.length, tagInfoShortOrLongOrRational.encodeValue(this.byteOrder, sArr)));
    }

    public void add(TagInfoShortOrLongOrRational tagInfoShortOrLongOrRational, int... iArr) throws ImageWriteException {
        if (tagInfoShortOrLongOrRational.length > 0 && tagInfoShortOrLongOrRational.length != iArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoShortOrLongOrRational.length + " value(s), not " + iArr.length);
        }
        add(new TiffOutputField(tagInfoShortOrLongOrRational.tag, tagInfoShortOrLongOrRational, FieldType.LONG, iArr.length, tagInfoShortOrLongOrRational.encodeValue(this.byteOrder, iArr)));
    }

    public void add(TagInfoShortOrLongOrRational tagInfoShortOrLongOrRational, RationalNumber... rationalNumberArr) throws ImageWriteException {
        if (tagInfoShortOrLongOrRational.length > 0 && tagInfoShortOrLongOrRational.length != rationalNumberArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoShortOrLongOrRational.length + " value(s), not " + rationalNumberArr.length);
        }
        add(new TiffOutputField(tagInfoShortOrLongOrRational.tag, tagInfoShortOrLongOrRational, FieldType.RATIONAL, rationalNumberArr.length, tagInfoShortOrLongOrRational.encodeValue(this.byteOrder, rationalNumberArr)));
    }

    public void add(TagInfoShortOrRational tagInfoShortOrRational, short... sArr) throws ImageWriteException {
        if (tagInfoShortOrRational.length > 0 && tagInfoShortOrRational.length != sArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoShortOrRational.length + " value(s), not " + sArr.length);
        }
        add(new TiffOutputField(tagInfoShortOrRational.tag, tagInfoShortOrRational, FieldType.SHORT, sArr.length, tagInfoShortOrRational.encodeValue(this.byteOrder, sArr)));
    }

    public void add(TagInfoShortOrRational tagInfoShortOrRational, RationalNumber... rationalNumberArr) throws ImageWriteException {
        if (tagInfoShortOrRational.length > 0 && tagInfoShortOrRational.length != rationalNumberArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoShortOrRational.length + " value(s), not " + rationalNumberArr.length);
        }
        add(new TiffOutputField(tagInfoShortOrRational.tag, tagInfoShortOrRational, FieldType.RATIONAL, rationalNumberArr.length, tagInfoShortOrRational.encodeValue(this.byteOrder, rationalNumberArr)));
    }

    public void add(TagInfoGpsText tagInfoGpsText, String str) throws ImageWriteException {
        byte[] encodeValue = tagInfoGpsText.encodeValue(FieldType.UNDEFINED, str, this.byteOrder);
        add(new TiffOutputField(tagInfoGpsText.tag, tagInfoGpsText, tagInfoGpsText.dataTypes.get(0), encodeValue.length, encodeValue));
    }

    public void add(TagInfoXpString tagInfoXpString, String str) throws ImageWriteException {
        byte[] encodeValue = tagInfoXpString.encodeValue(FieldType.BYTE, str, this.byteOrder);
        add(new TiffOutputField(tagInfoXpString.tag, tagInfoXpString, FieldType.BYTE, encodeValue.length, encodeValue));
    }

    public void add(TagInfoAsciiOrByte tagInfoAsciiOrByte, String... strArr) throws ImageWriteException {
        byte[] encodeValue = tagInfoAsciiOrByte.encodeValue(FieldType.ASCII, strArr, this.byteOrder);
        if (tagInfoAsciiOrByte.length > 0 && tagInfoAsciiOrByte.length != encodeValue.length) {
            throw new ImageWriteException("Tag expects " + tagInfoAsciiOrByte.length + " byte(s), not " + strArr.length);
        }
        add(new TiffOutputField(tagInfoAsciiOrByte.tag, tagInfoAsciiOrByte, FieldType.ASCII, encodeValue.length, encodeValue));
    }

    public void add(TagInfoAsciiOrRational tagInfoAsciiOrRational, String... strArr) throws ImageWriteException {
        byte[] encodeValue = tagInfoAsciiOrRational.encodeValue(FieldType.ASCII, strArr, this.byteOrder);
        if (tagInfoAsciiOrRational.length > 0 && tagInfoAsciiOrRational.length != encodeValue.length) {
            throw new ImageWriteException("Tag expects " + tagInfoAsciiOrRational.length + " byte(s), not " + strArr.length);
        }
        add(new TiffOutputField(tagInfoAsciiOrRational.tag, tagInfoAsciiOrRational, FieldType.ASCII, encodeValue.length, encodeValue));
    }

    public void add(TagInfoAsciiOrRational tagInfoAsciiOrRational, RationalNumber... rationalNumberArr) throws ImageWriteException {
        if (tagInfoAsciiOrRational.length > 0 && tagInfoAsciiOrRational.length != rationalNumberArr.length) {
            throw new ImageWriteException("Tag expects " + tagInfoAsciiOrRational.length + " value(s), not " + rationalNumberArr.length);
        }
        byte[] encodeValue = tagInfoAsciiOrRational.encodeValue(FieldType.RATIONAL, rationalNumberArr, this.byteOrder);
        add(new TiffOutputField(tagInfoAsciiOrRational.tag, tagInfoAsciiOrRational, FieldType.RATIONAL, encodeValue.length, encodeValue));
    }

    public void add(TiffOutputField tiffOutputField) {
        this.fields.add(tiffOutputField);
    }

    public List<TiffOutputField> getFields() {
        return new ArrayList(this.fields);
    }

    public void removeField(TagInfo tagInfo) {
        removeField(tagInfo.tag);
    }

    public void removeField(int i) {
        ArrayList arrayList = new ArrayList();
        for (TiffOutputField tiffOutputField : this.fields) {
            if (tiffOutputField.tag == i) {
                arrayList.add(tiffOutputField);
            }
        }
        this.fields.removeAll(arrayList);
    }

    public TiffOutputField findField(TagInfo tagInfo) {
        return findField(tagInfo.tag);
    }

    public TiffOutputField findField(int i) {
        for (TiffOutputField tiffOutputField : this.fields) {
            if (tiffOutputField.tag == i) {
                return tiffOutputField;
            }
        }
        return null;
    }

    public void sortFields() {
        this.fields.sort(new Comparator() { // from class: org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return TiffOutputDirectory.lambda$sortFields$1((TiffOutputField) obj, (TiffOutputField) obj2);
            }
        });
    }

    public static /* synthetic */ int lambda$sortFields$1(TiffOutputField tiffOutputField, TiffOutputField tiffOutputField2) {
        int sortHint;
        int sortHint2;
        if (tiffOutputField.tag != tiffOutputField2.tag) {
            sortHint = tiffOutputField.tag;
            sortHint2 = tiffOutputField2.tag;
        } else {
            sortHint = tiffOutputField.getSortHint();
            sortHint2 = tiffOutputField2.getSortHint();
        }
        return sortHint - sortHint2;
    }

    public String description() {
        return TiffDirectory.description(this.type);
    }

    @Override // org.apache.commons.imaging.formats.tiff.write.TiffOutputItem
    public void writeItem(BinaryOutputStream binaryOutputStream) throws IOException, ImageWriteException {
        binaryOutputStream.write2Bytes(this.fields.size());
        for (TiffOutputField tiffOutputField : this.fields) {
            tiffOutputField.writeField(binaryOutputStream);
        }
        TiffOutputDirectory tiffOutputDirectory = this.nextDirectory;
        long offset = tiffOutputDirectory != null ? tiffOutputDirectory.getOffset() : 0L;
        if (offset == -1) {
            binaryOutputStream.write4Bytes(0);
        } else {
            binaryOutputStream.write4Bytes((int) offset);
        }
    }

    public void setJpegImageData(JpegImageData jpegImageData) {
        this.jpegImageData = jpegImageData;
    }

    public JpegImageData getRawJpegImageData() {
        return this.jpegImageData;
    }

    public void setTiffImageData(TiffImageData tiffImageData) {
        this.tiffImageData = tiffImageData;
    }

    public TiffImageData getRawTiffImageData() {
        return this.tiffImageData;
    }

    @Override // org.apache.commons.imaging.formats.tiff.write.TiffOutputItem
    public int getItemLength() {
        return (this.fields.size() * 12) + 2 + 4;
    }

    @Override // org.apache.commons.imaging.formats.tiff.write.TiffOutputItem
    public String getItemDescription() {
        return "Directory: " + TiffDirectoryType.getExifDirectoryType(this.type).name + " (" + this.type + ")";
    }

    private void removeFieldIfPresent(TagInfo tagInfo) {
        TiffOutputField findField = findField(tagInfo);
        if (findField != null) {
            this.fields.remove(findField);
        }
    }

    public List<TiffOutputItem> getOutputItems(TiffOutputSummary tiffOutputSummary) throws ImageWriteException {
        TiffOutputField tiffOutputField;
        TagInfo tagInfo;
        TagInfoShortOrLong tagInfoShortOrLong;
        removeFieldIfPresent(TiffTagConstants.TIFF_TAG_JPEG_INTERCHANGE_FORMAT);
        removeFieldIfPresent(TiffTagConstants.TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
        ImageDataOffsets imageDataOffsets = null;
        if (this.jpegImageData != null) {
            tiffOutputField = new TiffOutputField(TiffTagConstants.TIFF_TAG_JPEG_INTERCHANGE_FORMAT, FieldType.LONG, 1, new byte[4]);
            add(tiffOutputField);
            add(new TiffOutputField(TiffTagConstants.TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, FieldType.LONG, 1, FieldType.LONG.writeData(Integer.valueOf(this.jpegImageData.length), tiffOutputSummary.byteOrder)));
        } else {
            tiffOutputField = null;
        }
        removeFieldIfPresent(TiffTagConstants.TIFF_TAG_STRIP_OFFSETS);
        removeFieldIfPresent(TiffTagConstants.TIFF_TAG_STRIP_BYTE_COUNTS);
        removeFieldIfPresent(TiffTagConstants.TIFF_TAG_TILE_OFFSETS);
        removeFieldIfPresent(TiffTagConstants.TIFF_TAG_TILE_BYTE_COUNTS);
        TiffImageData tiffImageData = this.tiffImageData;
        if (tiffImageData != null) {
            if (tiffImageData.stripsNotTiles()) {
                tagInfo = TiffTagConstants.TIFF_TAG_STRIP_OFFSETS;
                tagInfoShortOrLong = TiffTagConstants.TIFF_TAG_STRIP_BYTE_COUNTS;
            } else {
                tagInfo = TiffTagConstants.TIFF_TAG_TILE_OFFSETS;
                tagInfoShortOrLong = TiffTagConstants.TIFF_TAG_TILE_BYTE_COUNTS;
            }
            TiffElement.DataElement[] imageData = this.tiffImageData.getImageData();
            int length = imageData.length;
            int[] iArr = new int[length];
            int length2 = imageData.length;
            int[] iArr2 = new int[length2];
            for (int i = 0; i < imageData.length; i++) {
                iArr2[i] = imageData[i].length;
            }
            TiffOutputField tiffOutputField2 = new TiffOutputField(tagInfo, FieldType.LONG, length, FieldType.LONG.writeData(iArr, tiffOutputSummary.byteOrder));
            add(tiffOutputField2);
            add(new TiffOutputField(tagInfoShortOrLong, FieldType.LONG, length2, FieldType.LONG.writeData(iArr2, tiffOutputSummary.byteOrder)));
            imageDataOffsets = new ImageDataOffsets(imageData, iArr, tiffOutputField2);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        sortFields();
        for (TiffOutputField tiffOutputField3 : this.fields) {
            if (!tiffOutputField3.isLocalValue()) {
                arrayList.add(tiffOutputField3.getSeperateValue());
            }
        }
        if (imageDataOffsets != null) {
            Collections.addAll(arrayList, imageDataOffsets.outputItems);
            tiffOutputSummary.addTiffImageData(imageDataOffsets);
        }
        if (this.jpegImageData != null) {
            TiffOutputItem.Value value = new TiffOutputItem.Value("JPEG image data", this.jpegImageData.getData());
            arrayList.add(value);
            tiffOutputSummary.add(value, tiffOutputField);
        }
        return arrayList;
    }
}
