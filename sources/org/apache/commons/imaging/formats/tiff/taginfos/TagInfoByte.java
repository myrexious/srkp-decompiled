package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoByte extends TagInfo {
    public byte[] encodeValue(ByteOrder byteOrder, byte b) {
        return new byte[]{b};
    }

    public TagInfoByte(String str, int i, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.BYTE, 1, tiffDirectoryType);
    }

    public TagInfoByte(String str, int i, List<FieldType> list, TiffDirectoryType tiffDirectoryType) {
        super(str, i, list, 1, tiffDirectoryType);
    }

    public TagInfoByte(String str, int i, FieldType fieldType, TiffDirectoryType tiffDirectoryType) {
        super(str, i, fieldType, 1, tiffDirectoryType);
    }
}
