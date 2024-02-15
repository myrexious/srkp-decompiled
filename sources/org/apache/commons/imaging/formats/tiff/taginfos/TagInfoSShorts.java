package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoSShorts extends TagInfo {
    public TagInfoSShorts(String str, int i, int i2, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.SSHORT, i2, tiffDirectoryType);
    }

    public short[] getValue(ByteOrder byteOrder, byte[] bArr) {
        return ByteConversions.toShorts(bArr, byteOrder);
    }

    public byte[] encodeValue(ByteOrder byteOrder, short... sArr) {
        return ByteConversions.toBytes(sArr, byteOrder);
    }
}
