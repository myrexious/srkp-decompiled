package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoBytes;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLongOrIFD;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShort;

/* loaded from: classes2.dex */
public final class AdobePageMaker6TagConstants {
    public static final List<TagInfo> ALL_ADOBE_PAGEMAKER_6_TAGS;
    public static final int INDEXED_VALUE_INDEXED = 1;
    public static final int INDEXED_VALUE_NOT_INDEXED = 0;
    public static final int OPIPROXY_VALUE_HIGHER_RESOLUTION_IMAGE_DOES_NOT_EXIST = 0;
    public static final int OPIPROXY_VALUE_HIGHER_RESOLUTION_IMAGE_EXISTS = 1;
    public static final TagInfoBytes TIFF_TAG_CLIP_PATH;
    public static final TagInfoAscii TIFF_TAG_IMAGE_ID;
    public static final TagInfoShort TIFF_TAG_INDEXED;
    public static final TagInfoShort TIFF_TAG_OPIPROXY;
    public static final TagInfoLongOrIFD TIFF_TAG_SUB_IFD;
    public static final TagInfoLong TIFF_TAG_XCLIP_PATH_UNITS;
    public static final TagInfoLong TIFF_TAG_YCLIP_PATH_UNITS;

    static {
        TagInfoLongOrIFD tagInfoLongOrIFD = new TagInfoLongOrIFD("SubIFDs", 330, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN, true);
        TIFF_TAG_SUB_IFD = tagInfoLongOrIFD;
        TagInfoBytes tagInfoBytes = new TagInfoBytes("ClipPath", 343, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        TIFF_TAG_CLIP_PATH = tagInfoBytes;
        TagInfoLong tagInfoLong = new TagInfoLong("XClipPathUnits", 344, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        TIFF_TAG_XCLIP_PATH_UNITS = tagInfoLong;
        TagInfoLong tagInfoLong2 = new TagInfoLong("YClipPathUnits", 345, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        TIFF_TAG_YCLIP_PATH_UNITS = tagInfoLong2;
        TagInfoShort tagInfoShort = new TagInfoShort("Indexed", 346, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        TIFF_TAG_INDEXED = tagInfoShort;
        TagInfoShort tagInfoShort2 = new TagInfoShort("OPIProxy", 351, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        TIFF_TAG_OPIPROXY = tagInfoShort2;
        TagInfoAscii tagInfoAscii = new TagInfoAscii("ImageID", 32781, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        TIFF_TAG_IMAGE_ID = tagInfoAscii;
        ALL_ADOBE_PAGEMAKER_6_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoLongOrIFD, tagInfoBytes, tagInfoLong, tagInfoLong2, tagInfoShort, tagInfoShort2, tagInfoAscii));
    }

    private AdobePageMaker6TagConstants() {
    }
}
