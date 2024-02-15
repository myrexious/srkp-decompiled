package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShort;

/* loaded from: classes2.dex */
public final class Tiff4TagConstants {
    public static final List<TagInfo> ALL_TIFF_4_TAGS;
    public static final int COLOR_RESPONSE_UNIT_VALUE_0_00001 = 5;
    public static final int COLOR_RESPONSE_UNIT_VALUE_0_0001 = 4;
    public static final int COLOR_RESPONSE_UNIT_VALUE_0_001 = 3;
    public static final int COLOR_RESPONSE_UNIT_VALUE_0_01 = 2;
    public static final int COLOR_RESPONSE_UNIT_VALUE_0_1 = 1;
    public static final TagInfoShort TIFF_TAG_COLOR_RESPONSE_UNIT;

    static {
        TagInfoShort tagInfoShort = new TagInfoShort("ColorResponseUnit", 300, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        TIFF_TAG_COLOR_RESPONSE_UNIT = tagInfoShort;
        ALL_TIFF_4_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoShort));
    }

    private Tiff4TagConstants() {
    }
}
