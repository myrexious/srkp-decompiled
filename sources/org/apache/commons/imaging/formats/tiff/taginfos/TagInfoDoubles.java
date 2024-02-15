package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoDoubles extends TagInfo {
    public TagInfoDoubles(String str, int i, int i2, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.DOUBLE, i2, tiffDirectoryType);
    }

    public double[] getValue(ByteOrder byteOrder, byte[] bArr) {
        return ByteConversions.toDoubles(bArr, byteOrder);
    }

    public byte[] encodeValue(ByteOrder byteOrder, double... dArr) {
        return ByteConversions.toBytes(dArr, byteOrder);
    }
}
