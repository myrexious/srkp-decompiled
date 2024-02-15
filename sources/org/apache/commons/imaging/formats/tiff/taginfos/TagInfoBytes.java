package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoBytes extends TagInfo {
    public byte[] encodeValue(ByteOrder byteOrder, byte... bArr) {
        return bArr;
    }

    public TagInfoBytes(String str, int i, int i2, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.BYTE, i2, tiffDirectoryType);
    }

    public TagInfoBytes(String str, int i, List<FieldType> list, int i2, TiffDirectoryType tiffDirectoryType) {
        super(str, i, list, i2, tiffDirectoryType);
    }

    public TagInfoBytes(String str, int i, FieldType fieldType, int i2, TiffDirectoryType tiffDirectoryType) {
        super(str, i, fieldType, i2, tiffDirectoryType);
    }
}
