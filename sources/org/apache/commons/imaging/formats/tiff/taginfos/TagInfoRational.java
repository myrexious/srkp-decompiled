package org.apache.commons.imaging.formats.tiff.taginfos;

import java.nio.ByteOrder;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoRational extends TagInfo {
    public TagInfoRational(String str, int i, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.RATIONAL, 1, tiffDirectoryType);
    }

    public RationalNumber getValue(ByteOrder byteOrder, byte[] bArr) {
        return ByteConversions.toRational(bArr, byteOrder, true);
    }

    public byte[] encodeValue(ByteOrder byteOrder, RationalNumber rationalNumber) {
        return ByteConversions.toBytes(rationalNumber, byteOrder);
    }
}
