package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoSRationals extends TagInfo {
    public TagInfoSRationals(String str, int i, int i2, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.SRATIONAL, i2, tiffDirectoryType);
    }

    public RationalNumber[] getValue(ByteOrder byteOrder, byte[] bArr) {
        return ByteConversions.toRationals(bArr, byteOrder, false);
    }

    public byte[] encodeValue(ByteOrder byteOrder, RationalNumber... rationalNumberArr) {
        return ByteConversions.toBytes(rationalNumberArr, byteOrder);
    }
}
