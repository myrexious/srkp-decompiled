package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoSByte extends TagInfo {
    public byte[] encodeValue(ByteOrder byteOrder, byte b) {
        return new byte[]{b};
    }

    public TagInfoSByte(String str, int i, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.SBYTE, 1, tiffDirectoryType);
    }
}
