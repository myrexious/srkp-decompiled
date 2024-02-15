package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;

/* loaded from: classes2.dex */
public final class GdalLibraryTagConstants {
    public static final List<TagInfo> ALL_GDAL_LIBRARY_TAGS;
    public static final TagInfoAscii EXIF_TAG_GDAL_METADATA;
    public static final TagInfoAscii EXIF_TAG_GDAL_NO_DATA;

    static {
        TagInfoAscii tagInfoAscii = new TagInfoAscii("GDALMetadata", 42112, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_GDAL_METADATA = tagInfoAscii;
        TagInfoAscii tagInfoAscii2 = new TagInfoAscii("GDALNoData", 42113, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_GDAL_NO_DATA = tagInfoAscii2;
        ALL_GDAL_LIBRARY_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoAscii, tagInfoAscii2));
    }

    private GdalLibraryTagConstants() {
    }
}
