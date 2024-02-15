package org.apache.commons.imaging.formats.tiff.taginfos;

import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;

/* loaded from: classes2.dex */
public final class TagInfoUnknown extends TagInfoByte {
    public TagInfoUnknown(String str, int i, TiffDirectoryType tiffDirectoryType) {
        super(str, i, FieldType.ANY, tiffDirectoryType);
    }
}
