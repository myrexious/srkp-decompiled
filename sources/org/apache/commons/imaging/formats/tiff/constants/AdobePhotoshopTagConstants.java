package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoUndefineds;

/* loaded from: classes2.dex */
public final class AdobePhotoshopTagConstants {
    public static final List<TagInfo> ALL_ADOBE_PHOTOSHOP_TAGS;
    public static final TagInfoUndefineds EXIF_TAG_IMAGE_SOURCE_DATA;
    public static final TagInfoUndefineds EXIF_TAG_JPEGTABLES;

    static {
        TagInfoUndefineds tagInfoUndefineds = new TagInfoUndefineds("JPEGTables", 347, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_JPEGTABLES = tagInfoUndefineds;
        TagInfoUndefineds tagInfoUndefineds2 = new TagInfoUndefineds("ImageSourceData", 37724, -1, TiffDirectoryType.EXIF_DIRECTORY_IFD0);
        EXIF_TAG_IMAGE_SOURCE_DATA = tagInfoUndefineds2;
        ALL_ADOBE_PHOTOSHOP_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoUndefineds, tagInfoUndefineds2));
    }

    private AdobePhotoshopTagConstants() {
    }
}
