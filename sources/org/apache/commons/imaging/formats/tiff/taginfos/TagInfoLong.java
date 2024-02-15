package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import java.util.List;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoLong extends TagInfo {
    public TagInfoLong(String str, int i, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.LONG, 1, tiffDirectoryType);
    }

    public TagInfoLong(String str, int i, TiffDirectoryType tiffDirectoryType, boolean z) {
        super(str, i, FieldType.LONG, 1, tiffDirectoryType, z);
    }

    public TagInfoLong(String str, int i, List<FieldType> list, int i2, TiffDirectoryType tiffDirectoryType, boolean z) {
        super(str, i, list, i2, tiffDirectoryType, z);
    }

    public int getValue(ByteOrder byteOrder, byte[] bArr) {
        return ByteConversions.toInt(bArr, byteOrder);
    }

    public byte[] encodeValue(ByteOrder byteOrder, int i) {
        return ByteConversions.toBytes(i, byteOrder);
    }
}
