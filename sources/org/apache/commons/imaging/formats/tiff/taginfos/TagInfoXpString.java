package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoXpString extends TagInfo {
    public TagInfoXpString(String str, int i, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.BYTE, -1, tiffDirectoryType);
    }

    @Override // org.apache.commons.imaging.formats.tiff.taginfos.TagInfo
    public byte[] encodeValue(FieldType fieldType, Object obj, ByteOrder byteOrder) throws ImageWriteException {
        if (!(obj instanceof String)) {
            throw new ImageWriteException("Text value not String", obj);
        }
        byte[] bytes = ((String) obj).getBytes(StandardCharsets.UTF_16LE);
        byte[] bArr = new byte[bytes.length + 2];
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        return bArr;
    }

    @Override // org.apache.commons.imaging.formats.tiff.taginfos.TagInfo
    public String getValue(TiffField tiffField) throws ImageReadException {
        int length;
        if (tiffField.getFieldType() != FieldType.BYTE) {
            throw new ImageReadException("Text field not encoded as bytes.");
        }
        byte[] byteArrayValue = tiffField.getByteArrayValue();
        if (byteArrayValue.length >= 2 && byteArrayValue[byteArrayValue.length - 1] == 0 && byteArrayValue[byteArrayValue.length - 2] == 0) {
            length = byteArrayValue.length - 2;
        } else {
            length = byteArrayValue.length;
        }
        return new String(byteArrayValue, 0, length, StandardCharsets.UTF_16LE);
    }
}
