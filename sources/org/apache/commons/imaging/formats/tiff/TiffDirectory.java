package org.apache.commons.imaging.formats.tiff;

import com.tom_roush.fontbox.ttf.OpenTypeScript;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoByte;
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
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShorts;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoXpString;

/* loaded from: classes2.dex */
public class TiffDirectory extends TiffElement {
    public final List<TiffField> entries;
    private final ByteOrder headerByteOrder;
    private JpegImageData jpegImageData;
    public final long nextDirectoryOffset;
    private TiffImageData tiffImageData;
    public final int type;

    public static String description(int i) {
        switch (i) {
            case -4:
                return "Interoperability";
            case -3:
                return "Gps";
            case -2:
                return "Exif";
            case -1:
                return OpenTypeScript.UNKNOWN;
            case 0:
                return "Root";
            case 1:
                return "Sub";
            case 2:
                return "Thumbnail";
            default:
                return "Bad Type";
        }
    }

    public TiffDirectory(int i, List<TiffField> list, long j, long j2, ByteOrder byteOrder) {
        super(j, (list.size() * 12) + 2 + 4);
        this.type = i;
        this.entries = Collections.unmodifiableList(list);
        this.nextDirectoryOffset = j2;
        this.headerByteOrder = byteOrder;
    }

    public ByteOrder getByteOrder() {
        return this.headerByteOrder;
    }

    public String description() {
        return description(this.type);
    }

    @Override // org.apache.commons.imaging.formats.tiff.TiffElement
    public String getElementDescription() {
        long j = this.offset + 2;
        StringBuilder sb = new StringBuilder();
        for (TiffField tiffField : this.entries) {
            sb.append(String.format("\t[%d]: %s (%d, 0x%x), %s, %d: %s%n", Long.valueOf(j), tiffField.getTagInfo().name, Integer.valueOf(tiffField.getTag()), Integer.valueOf(tiffField.getTag()), tiffField.getFieldType().getName(), Integer.valueOf(tiffField.getBytesLength()), tiffField.getValueDescription()));
            j += 12;
        }
        return sb.toString();
    }

    public List<TiffField> getDirectoryEntries() {
        return new ArrayList(this.entries);
    }

    public void dump() {
        for (TiffField tiffField : this.entries) {
            tiffField.dump();
        }
    }

    public boolean hasJpegImageData() throws ImageReadException {
        return findField(TiffTagConstants.TIFF_TAG_JPEG_INTERCHANGE_FORMAT) != null;
    }

    public boolean hasTiffImageData() throws ImageReadException {
        return (findField(TiffTagConstants.TIFF_TAG_TILE_OFFSETS) == null && findField(TiffTagConstants.TIFF_TAG_STRIP_OFFSETS) == null) ? false : true;
    }

    public BufferedImage getTiffImage() throws ImageReadException, IOException {
        if (this.tiffImageData == null) {
            return null;
        }
        return new TiffImageParser().getBufferedImage(this, this.headerByteOrder, null);
    }

    public BufferedImage getTiffImage(TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        if (this.tiffImageData == null) {
            return null;
        }
        return new TiffImageParser().getBufferedImage(this, this.headerByteOrder, tiffImagingParameters);
    }

    public BufferedImage getTiffImage(ByteOrder byteOrder) throws ImageReadException, IOException {
        return getTiffImage(byteOrder, new TiffImagingParameters());
    }

    public BufferedImage getTiffImage(ByteOrder byteOrder, TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        if (this.tiffImageData == null) {
            return null;
        }
        return new TiffImageParser().getBufferedImage(this, byteOrder, tiffImagingParameters);
    }

    public TiffField findField(TagInfo tagInfo) throws ImageReadException {
        return findField(tagInfo, false);
    }

    public TiffField findField(TagInfo tagInfo, boolean z) throws ImageReadException {
        List<TiffField> list = this.entries;
        if (list == null) {
            return null;
        }
        for (TiffField tiffField : list) {
            if (tiffField.getTag() == tagInfo.tag) {
                return tiffField;
            }
        }
        if (z) {
            throw new ImageReadException("Missing expected field: " + tagInfo.getDescription());
        }
        return null;
    }

    public Object getFieldValue(TagInfo tagInfo) throws ImageReadException {
        TiffField findField = findField(tagInfo);
        if (findField == null) {
            return null;
        }
        return findField.getValue();
    }

    public String getSingleFieldValue(TagInfoAscii tagInfoAscii) throws ImageReadException {
        String[] fieldValue = getFieldValue(tagInfoAscii, true);
        if (fieldValue.length != 1) {
            throw new ImageReadException("Field \"" + tagInfoAscii.name + "\" has incorrect length " + fieldValue.length);
        }
        return fieldValue[0];
    }

    public int getSingleFieldValue(TagInfoShortOrLong tagInfoShortOrLong) throws ImageReadException {
        int[] fieldValue = getFieldValue(tagInfoShortOrLong, true);
        if (fieldValue.length != 1) {
            throw new ImageReadException("Field \"" + tagInfoShortOrLong.name + "\" has incorrect length " + fieldValue.length);
        }
        return fieldValue[0];
    }

    public byte getFieldValue(TagInfoByte tagInfoByte) throws ImageReadException {
        TiffField findField = findField(tagInfoByte);
        if (findField == null) {
            throw new ImageReadException("Required field \"" + tagInfoByte.name + "\" is missing");
        }
        if (!tagInfoByte.dataTypes.contains(findField.getFieldType())) {
            throw new ImageReadException("Required field \"" + tagInfoByte.name + "\" has incorrect type " + findField.getFieldType().getName());
        }
        if (findField.getCount() != 1) {
            throw new ImageReadException("Field \"" + tagInfoByte.name + "\" has wrong count " + findField.getCount());
        }
        return findField.getByteArrayValue()[0];
    }

    public byte[] getFieldValue(TagInfoBytes tagInfoBytes, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoBytes);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoBytes.name + "\" is missing");
            }
            return null;
        } else if (tagInfoBytes.dataTypes.contains(findField.getFieldType())) {
            return findField.getByteArrayValue();
        } else {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoBytes.name + "\" has incorrect type " + findField.getFieldType().getName());
            }
            return null;
        }
    }

    public String[] getFieldValue(TagInfoAscii tagInfoAscii, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoAscii);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoAscii.name + "\" is missing");
            }
            return null;
        } else if (tagInfoAscii.dataTypes.contains(findField.getFieldType())) {
            return tagInfoAscii.getValue(findField.getByteOrder(), findField.getByteArrayValue());
        } else if (z) {
            throw new ImageReadException("Required field \"" + tagInfoAscii.name + "\" has incorrect type " + findField.getFieldType().getName());
        } else {
            return null;
        }
    }

    public short getFieldValue(TagInfoShort tagInfoShort) throws ImageReadException {
        TiffField findField = findField(tagInfoShort);
        if (findField == null) {
            throw new ImageReadException("Required field \"" + tagInfoShort.name + "\" is missing");
        }
        if (!tagInfoShort.dataTypes.contains(findField.getFieldType())) {
            throw new ImageReadException("Required field \"" + tagInfoShort.name + "\" has incorrect type " + findField.getFieldType().getName());
        }
        if (findField.getCount() != 1) {
            throw new ImageReadException("Field \"" + tagInfoShort.name + "\" has wrong count " + findField.getCount());
        }
        return tagInfoShort.getValue(findField.getByteOrder(), findField.getByteArrayValue());
    }

    public short[] getFieldValue(TagInfoShorts tagInfoShorts, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoShorts);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoShorts.name + "\" is missing");
            }
            return null;
        } else if (tagInfoShorts.dataTypes.contains(findField.getFieldType())) {
            return tagInfoShorts.getValue(findField.getByteOrder(), findField.getByteArrayValue());
        } else if (z) {
            throw new ImageReadException("Required field \"" + tagInfoShorts.name + "\" has incorrect type " + findField.getFieldType().getName());
        } else {
            return null;
        }
    }

    public int getFieldValue(TagInfoLong tagInfoLong) throws ImageReadException {
        TiffField findField = findField(tagInfoLong);
        if (findField == null) {
            throw new ImageReadException("Required field \"" + tagInfoLong.name + "\" is missing");
        }
        if (!tagInfoLong.dataTypes.contains(findField.getFieldType())) {
            throw new ImageReadException("Required field \"" + tagInfoLong.name + "\" has incorrect type " + findField.getFieldType().getName());
        }
        if (findField.getCount() != 1) {
            throw new ImageReadException("Field \"" + tagInfoLong.name + "\" has wrong count " + findField.getCount());
        }
        return tagInfoLong.getValue(findField.getByteOrder(), findField.getByteArrayValue());
    }

    public int[] getFieldValue(TagInfoLongs tagInfoLongs, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoLongs);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoLongs.name + "\" is missing");
            }
            return null;
        } else if (tagInfoLongs.dataTypes.contains(findField.getFieldType())) {
            return tagInfoLongs.getValue(findField.getByteOrder(), findField.getByteArrayValue());
        } else if (z) {
            throw new ImageReadException("Required field \"" + tagInfoLongs.name + "\" has incorrect type " + findField.getFieldType().getName());
        } else {
            return null;
        }
    }

    public int[] getFieldValue(TagInfoShortOrLong tagInfoShortOrLong, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoShortOrLong);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoShortOrLong.name + "\" is missing");
            }
            return null;
        } else if (!tagInfoShortOrLong.dataTypes.contains(findField.getFieldType())) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoShortOrLong.name + "\" has incorrect type " + findField.getFieldType().getName());
            }
            return null;
        } else {
            byte[] byteArrayValue = findField.getByteArrayValue();
            if (findField.getFieldType() == FieldType.SHORT) {
                return ByteConversions.toUInt16s(byteArrayValue, findField.getByteOrder());
            }
            return ByteConversions.toInts(byteArrayValue, findField.getByteOrder());
        }
    }

    public RationalNumber getFieldValue(TagInfoRational tagInfoRational) throws ImageReadException {
        TiffField findField = findField(tagInfoRational);
        if (findField == null) {
            throw new ImageReadException("Required field \"" + tagInfoRational.name + "\" is missing");
        }
        if (!tagInfoRational.dataTypes.contains(findField.getFieldType())) {
            throw new ImageReadException("Required field \"" + tagInfoRational.name + "\" has incorrect type " + findField.getFieldType().getName());
        }
        if (findField.getCount() != 1) {
            throw new ImageReadException("Field \"" + tagInfoRational.name + "\" has wrong count " + findField.getCount());
        }
        return tagInfoRational.getValue(findField.getByteOrder(), findField.getByteArrayValue());
    }

    public RationalNumber[] getFieldValue(TagInfoRationals tagInfoRationals, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoRationals);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoRationals.name + "\" is missing");
            }
            return null;
        } else if (tagInfoRationals.dataTypes.contains(findField.getFieldType())) {
            return tagInfoRationals.getValue(findField.getByteOrder(), findField.getByteArrayValue());
        } else if (z) {
            throw new ImageReadException("Required field \"" + tagInfoRationals.name + "\" has incorrect type " + findField.getFieldType().getName());
        } else {
            return null;
        }
    }

    public byte getFieldValue(TagInfoSByte tagInfoSByte) throws ImageReadException {
        TiffField findField = findField(tagInfoSByte);
        if (findField == null) {
            throw new ImageReadException("Required field \"" + tagInfoSByte.name + "\" is missing");
        }
        if (!tagInfoSByte.dataTypes.contains(findField.getFieldType())) {
            throw new ImageReadException("Required field \"" + tagInfoSByte.name + "\" has incorrect type " + findField.getFieldType().getName());
        }
        if (findField.getCount() != 1) {
            throw new ImageReadException("Field \"" + tagInfoSByte.name + "\" has wrong count " + findField.getCount());
        }
        return findField.getByteArrayValue()[0];
    }

    public byte[] getFieldValue(TagInfoSBytes tagInfoSBytes, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoSBytes);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoSBytes.name + "\" is missing");
            }
            return null;
        } else if (tagInfoSBytes.dataTypes.contains(findField.getFieldType())) {
            return findField.getByteArrayValue();
        } else {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoSBytes.name + "\" has incorrect type " + findField.getFieldType().getName());
            }
            return null;
        }
    }

    public short getFieldValue(TagInfoSShort tagInfoSShort) throws ImageReadException {
        TiffField findField = findField(tagInfoSShort);
        if (findField == null) {
            throw new ImageReadException("Required field \"" + tagInfoSShort.name + "\" is missing");
        }
        if (!tagInfoSShort.dataTypes.contains(findField.getFieldType())) {
            throw new ImageReadException("Required field \"" + tagInfoSShort.name + "\" has incorrect type " + findField.getFieldType().getName());
        }
        if (findField.getCount() != 1) {
            throw new ImageReadException("Field \"" + tagInfoSShort.name + "\" has wrong count " + findField.getCount());
        }
        return tagInfoSShort.getValue(findField.getByteOrder(), findField.getByteArrayValue());
    }

    public short[] getFieldValue(TagInfoSShorts tagInfoSShorts, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoSShorts);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoSShorts.name + "\" is missing");
            }
            return null;
        } else if (tagInfoSShorts.dataTypes.contains(findField.getFieldType())) {
            return tagInfoSShorts.getValue(findField.getByteOrder(), findField.getByteArrayValue());
        } else if (z) {
            throw new ImageReadException("Required field \"" + tagInfoSShorts.name + "\" has incorrect type " + findField.getFieldType().getName());
        } else {
            return null;
        }
    }

    public int getFieldValue(TagInfoSLong tagInfoSLong) throws ImageReadException {
        TiffField findField = findField(tagInfoSLong);
        if (findField == null) {
            throw new ImageReadException("Required field \"" + tagInfoSLong.name + "\" is missing");
        }
        if (!tagInfoSLong.dataTypes.contains(findField.getFieldType())) {
            throw new ImageReadException("Required field \"" + tagInfoSLong.name + "\" has incorrect type " + findField.getFieldType().getName());
        }
        if (findField.getCount() != 1) {
            throw new ImageReadException("Field \"" + tagInfoSLong.name + "\" has wrong count " + findField.getCount());
        }
        return tagInfoSLong.getValue(findField.getByteOrder(), findField.getByteArrayValue());
    }

    public int[] getFieldValue(TagInfoSLongs tagInfoSLongs, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoSLongs);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoSLongs.name + "\" is missing");
            }
            return null;
        } else if (tagInfoSLongs.dataTypes.contains(findField.getFieldType())) {
            return tagInfoSLongs.getValue(findField.getByteOrder(), findField.getByteArrayValue());
        } else if (z) {
            throw new ImageReadException("Required field \"" + tagInfoSLongs.name + "\" has incorrect type " + findField.getFieldType().getName());
        } else {
            return null;
        }
    }

    public RationalNumber getFieldValue(TagInfoSRational tagInfoSRational) throws ImageReadException {
        TiffField findField = findField(tagInfoSRational);
        if (findField == null) {
            throw new ImageReadException("Required field \"" + tagInfoSRational.name + "\" is missing");
        }
        if (!tagInfoSRational.dataTypes.contains(findField.getFieldType())) {
            throw new ImageReadException("Required field \"" + tagInfoSRational.name + "\" has incorrect type " + findField.getFieldType().getName());
        }
        if (findField.getCount() != 1) {
            throw new ImageReadException("Field \"" + tagInfoSRational.name + "\" has wrong count " + findField.getCount());
        }
        return tagInfoSRational.getValue(findField.getByteOrder(), findField.getByteArrayValue());
    }

    public RationalNumber[] getFieldValue(TagInfoSRationals tagInfoSRationals, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoSRationals);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoSRationals.name + "\" is missing");
            }
            return null;
        } else if (tagInfoSRationals.dataTypes.contains(findField.getFieldType())) {
            return tagInfoSRationals.getValue(findField.getByteOrder(), findField.getByteArrayValue());
        } else if (z) {
            throw new ImageReadException("Required field \"" + tagInfoSRationals.name + "\" has incorrect type " + findField.getFieldType().getName());
        } else {
            return null;
        }
    }

    public float getFieldValue(TagInfoFloat tagInfoFloat) throws ImageReadException {
        TiffField findField = findField(tagInfoFloat);
        if (findField == null) {
            throw new ImageReadException("Required field \"" + tagInfoFloat.name + "\" is missing");
        }
        if (!tagInfoFloat.dataTypes.contains(findField.getFieldType())) {
            throw new ImageReadException("Required field \"" + tagInfoFloat.name + "\" has incorrect type " + findField.getFieldType().getName());
        }
        if (findField.getCount() != 1) {
            throw new ImageReadException("Field \"" + tagInfoFloat.name + "\" has wrong count " + findField.getCount());
        }
        return tagInfoFloat.getValue(findField.getByteOrder(), findField.getByteArrayValue());
    }

    public float[] getFieldValue(TagInfoFloats tagInfoFloats, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoFloats);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoFloats.name + "\" is missing");
            }
            return null;
        } else if (tagInfoFloats.dataTypes.contains(findField.getFieldType())) {
            return tagInfoFloats.getValue(findField.getByteOrder(), findField.getByteArrayValue());
        } else if (z) {
            throw new ImageReadException("Required field \"" + tagInfoFloats.name + "\" has incorrect type " + findField.getFieldType().getName());
        } else {
            return null;
        }
    }

    public double getFieldValue(TagInfoDouble tagInfoDouble) throws ImageReadException {
        TiffField findField = findField(tagInfoDouble);
        if (findField == null) {
            throw new ImageReadException("Required field \"" + tagInfoDouble.name + "\" is missing");
        }
        if (!tagInfoDouble.dataTypes.contains(findField.getFieldType())) {
            throw new ImageReadException("Required field \"" + tagInfoDouble.name + "\" has incorrect type " + findField.getFieldType().getName());
        }
        if (findField.getCount() != 1) {
            throw new ImageReadException("Field \"" + tagInfoDouble.name + "\" has wrong count " + findField.getCount());
        }
        return tagInfoDouble.getValue(findField.getByteOrder(), findField.getByteArrayValue());
    }

    public double[] getFieldValue(TagInfoDoubles tagInfoDoubles, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoDoubles);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoDoubles.name + "\" is missing");
            }
            return null;
        } else if (tagInfoDoubles.dataTypes.contains(findField.getFieldType())) {
            return tagInfoDoubles.getValue(findField.getByteOrder(), findField.getByteArrayValue());
        } else if (z) {
            throw new ImageReadException("Required field \"" + tagInfoDoubles.name + "\" has incorrect type " + findField.getFieldType().getName());
        } else {
            return null;
        }
    }

    public String getFieldValue(TagInfoGpsText tagInfoGpsText, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoGpsText);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoGpsText.name + "\" is missing");
            }
            return null;
        }
        return tagInfoGpsText.getValue(findField);
    }

    public String getFieldValue(TagInfoXpString tagInfoXpString, boolean z) throws ImageReadException {
        TiffField findField = findField(tagInfoXpString);
        if (findField == null) {
            if (z) {
                throw new ImageReadException("Required field \"" + tagInfoXpString.name + "\" is missing");
            }
            return null;
        }
        return tagInfoXpString.getValue(findField);
    }

    /* loaded from: classes2.dex */
    public static final class ImageDataElement extends TiffElement {
        @Override // org.apache.commons.imaging.formats.tiff.TiffElement
        public String getElementDescription() {
            return "ImageDataElement";
        }

        public ImageDataElement(long j, int i) {
            super(j, i);
        }
    }

    private List<ImageDataElement> getRawImageDataElements(TiffField tiffField, TiffField tiffField2) throws ImageReadException {
        int[] intArrayValue = tiffField.getIntArrayValue();
        int[] intArrayValue2 = tiffField2.getIntArrayValue();
        if (intArrayValue.length != intArrayValue2.length) {
            throw new ImageReadException("offsets.length(" + intArrayValue.length + ") != byteCounts.length(" + intArrayValue2.length + ")");
        }
        ArrayList arrayList = new ArrayList(intArrayValue.length);
        for (int i = 0; i < intArrayValue.length; i++) {
            arrayList.add(new ImageDataElement(intArrayValue[i], intArrayValue2[i]));
        }
        return arrayList;
    }

    public List<ImageDataElement> getTiffRawImageDataElements() throws ImageReadException {
        TiffField findField = findField(TiffTagConstants.TIFF_TAG_TILE_OFFSETS);
        TiffField findField2 = findField(TiffTagConstants.TIFF_TAG_TILE_BYTE_COUNTS);
        TiffField findField3 = findField(TiffTagConstants.TIFF_TAG_STRIP_OFFSETS);
        TiffField findField4 = findField(TiffTagConstants.TIFF_TAG_STRIP_BYTE_COUNTS);
        if (findField == null || findField2 == null) {
            if (findField3 != null && findField4 != null) {
                return getRawImageDataElements(findField3, findField4);
            }
            throw new ImageReadException("Couldn't find image data.");
        }
        return getRawImageDataElements(findField, findField2);
    }

    public boolean imageDataInStrips() throws ImageReadException {
        TiffField findField = findField(TiffTagConstants.TIFF_TAG_TILE_OFFSETS);
        TiffField findField2 = findField(TiffTagConstants.TIFF_TAG_TILE_BYTE_COUNTS);
        TiffField findField3 = findField(TiffTagConstants.TIFF_TAG_STRIP_OFFSETS);
        TiffField findField4 = findField(TiffTagConstants.TIFF_TAG_STRIP_BYTE_COUNTS);
        if (findField == null || findField2 == null) {
            if (findField3 == null || findField4 == null) {
                throw new ImageReadException("Couldn't find image data.");
            }
            return true;
        }
        return false;
    }

    public ImageDataElement getJpegRawImageDataElement() throws ImageReadException {
        TiffField findField = findField(TiffTagConstants.TIFF_TAG_JPEG_INTERCHANGE_FORMAT);
        TiffField findField2 = findField(TiffTagConstants.TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
        if (findField != null && findField2 != null) {
            return new ImageDataElement(findField.getIntArrayValue()[0], findField2.getIntArrayValue()[0]);
        }
        throw new ImageReadException("Couldn't find image data.");
    }

    public void setTiffImageData(TiffImageData tiffImageData) {
        this.tiffImageData = tiffImageData;
    }

    public TiffImageData getTiffImageData() {
        return this.tiffImageData;
    }

    public void setJpegImageData(JpegImageData jpegImageData) {
        this.jpegImageData = jpegImageData;
    }

    public JpegImageData getJpegImageData() {
        return this.jpegImageData;
    }

    public TiffRasterData getRasterData(TiffImagingParameters tiffImagingParameters) throws ImageReadException, IOException {
        return new TiffImageParser().getRasterData(this, this.headerByteOrder, tiffImagingParameters);
    }

    public boolean hasTiffFloatingPointRasterData() throws ImageReadException {
        short[] fieldValue;
        return hasTiffImageData() && (fieldValue = getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, false)) != null && fieldValue.length > 0 && fieldValue[0] == 3;
    }

    public boolean hasTiffRasterData() throws ImageReadException {
        short[] fieldValue;
        if (hasTiffImageData() && (fieldValue = getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, false)) != null && fieldValue.length > 0) {
            short s = fieldValue[0];
            return s == 3 || s == 2;
        }
        return false;
    }
}
