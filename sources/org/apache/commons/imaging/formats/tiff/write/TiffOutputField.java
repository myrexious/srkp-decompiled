package org.apache.commons.imaging.formats.tiff.write;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputItem;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes2.dex */
public class TiffOutputField {
    private static final String NEWLINE = System.getProperty(SystemProperties.LINE_SEPARATOR);
    private byte[] bytes;
    public final int count;
    public final FieldType fieldType;
    private final TiffOutputItem.Value separateValueItem;
    private int sortHint;
    public final int tag;
    public final TagInfo tagInfo;

    public TiffOutputField(TagInfo tagInfo, FieldType fieldType, int i, byte[] bArr) {
        this(tagInfo.tag, tagInfo, fieldType, i, bArr);
    }

    public TiffOutputField(int i, TagInfo tagInfo, FieldType fieldType, int i2, byte[] bArr) {
        this.sortHint = -1;
        this.tag = i;
        this.tagInfo = tagInfo;
        this.fieldType = fieldType;
        this.count = i2;
        this.bytes = bArr;
        if (isLocalValue()) {
            this.separateValueItem = null;
        } else {
            this.separateValueItem = new TiffOutputItem.Value("Field Separate value (" + tagInfo.getDescription() + ")", bArr);
        }
    }

    public static TiffOutputField createOffsetField(TagInfo tagInfo, ByteOrder byteOrder) throws ImageWriteException {
        return new TiffOutputField(tagInfo, FieldType.LONG, 1, FieldType.LONG.writeData(0, byteOrder));
    }

    public void writeField(BinaryOutputStream binaryOutputStream) throws IOException, ImageWriteException {
        binaryOutputStream.write2Bytes(this.tag);
        binaryOutputStream.write2Bytes(this.fieldType.getType());
        binaryOutputStream.write4Bytes(this.count);
        if (isLocalValue()) {
            if (this.separateValueItem != null) {
                throw new ImageWriteException("Unexpected separate value item.");
            }
            byte[] bArr = this.bytes;
            if (bArr.length > 4) {
                throw new ImageWriteException("Local value has invalid length: " + this.bytes.length);
            }
            binaryOutputStream.write(bArr);
            int length = 4 - this.bytes.length;
            for (int i = 0; i < length; i++) {
                binaryOutputStream.write(0);
            }
            return;
        }
        TiffOutputItem.Value value = this.separateValueItem;
        if (value == null) {
            throw new ImageWriteException("Missing separate value item.");
        }
        binaryOutputStream.write4Bytes((int) value.getOffset());
    }

    public TiffOutputItem getSeperateValue() {
        return this.separateValueItem;
    }

    public final boolean isLocalValue() {
        return this.bytes.length <= 4;
    }

    public boolean bytesEqual(byte[] bArr) {
        return Arrays.equals(this.bytes, bArr);
    }

    public void setData(byte[] bArr) throws ImageWriteException {
        if (this.bytes.length != bArr.length) {
            throw new ImageWriteException("Cannot change size of value.");
        }
        this.bytes = bArr;
        TiffOutputItem.Value value = this.separateValueItem;
        if (value != null) {
            value.updateValue(bArr);
        }
    }

    public String toString() {
        return toString(null);
    }

    public String toString(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(this.tagInfo);
        String str2 = NEWLINE;
        sb.append(str2);
        sb.append(str);
        sb.append("count: ");
        sb.append(this.count);
        sb.append(str2);
        sb.append(str);
        sb.append(this.fieldType);
        sb.append(str2);
        return sb.toString();
    }

    public int getSortHint() {
        return this.sortHint;
    }

    public void setSortHint(int i) {
        this.sortHint = i;
    }
}
