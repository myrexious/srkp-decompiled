package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoSLong extends TagInfo {
    public TagInfoSLong(String str, int i, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.SLONG, 1, tiffDirectoryType);
    }

    public int getValue(ByteOrder byteOrder, byte[] bArr) {
        return ByteConversions.toInt(bArr, byteOrder);
    }

    public byte[] encodeValue(ByteOrder byteOrder, int i) {
        return ByteConversions.toBytes(i, byteOrder);
    }
}
