package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoSRational extends TagInfo {
    public TagInfoSRational(String str, int i, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.SRATIONAL, 1, tiffDirectoryType);
    }

    public RationalNumber getValue(ByteOrder byteOrder, byte[] bArr) {
        return ByteConversions.toRational(bArr, byteOrder, false);
    }

    public byte[] encodeValue(ByteOrder byteOrder, RationalNumber rationalNumber) {
        return ByteConversions.toBytes(rationalNumber, byteOrder);
    }
}
