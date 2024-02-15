package org.apache.commons.imaging.formats.tiff.taginfos;

import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public class TagInfoUndefined extends TagInfoByte {
    public TagInfoUndefined(String str, int i, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.UNDEFINED, tiffDirectoryType);
    }
}
