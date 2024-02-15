package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoBytes;

/* loaded from: classes2.dex */
public final class WangTagConstants {
    public static final List<TagInfo> ALL_WANG_TAGS;
    public static final TagInfoBytes EXIF_TAG_WANG_ANNOTATION;

    static {
        TagInfoBytes tagInfoBytes = new TagInfoBytes("WangAnnotation", 32932, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_WANG_ANNOTATION = tagInfoBytes;
        ALL_WANG_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoBytes));
    }

    private WangTagConstants() {
    }
}
