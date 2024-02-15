package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLong;

/* loaded from: classes2.dex */
public final class HylaFaxTagConstants {
    public static final List<TagInfo> ALL_HYLAFAX_TAGS;
    public static final TagInfoAscii EXIF_TAG_FAX_DCS;
    public static final TagInfoLong EXIF_TAG_FAX_RECV_PARAMS;
    public static final TagInfoLong EXIF_TAG_FAX_RECV_TIME;
    public static final TagInfoAscii EXIF_TAG_FAX_SUB_ADDRESS;

    static {
        TagInfoLong tagInfoLong = new TagInfoLong("FaxRecvParams", 34908, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_FAX_RECV_PARAMS = tagInfoLong;
        TagInfoAscii tagInfoAscii = new TagInfoAscii("FaxSubAddress", 34909, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_FAX_SUB_ADDRESS = tagInfoAscii;
        TagInfoLong tagInfoLong2 = new TagInfoLong("FaxRecvTime", 34910, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_FAX_RECV_TIME = tagInfoLong2;
        TagInfoAscii tagInfoAscii2 = new TagInfoAscii("FaxDCS", 34911, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_FAX_DCS = tagInfoAscii2;
        ALL_HYLAFAX_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoLong, tagInfoAscii, tagInfoLong2, tagInfoAscii2));
    }

    private HylaFaxTagConstants() {
    }
}
