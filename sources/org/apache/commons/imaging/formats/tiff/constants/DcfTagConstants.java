package org.apache.commons.imaging.formats.tiff.constants;

import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.FragmentTransaction;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShortOrLong;

/* loaded from: classes2.dex */
public final class DcfTagConstants {
    public static final List<TagInfo> ALL_DCF_TAGS;
    public static final int COLOR_SPACE_VALUE_ADOBE_RGB = 2;
    public static final int COLOR_SPACE_VALUE_SRGB = 1;
    public static final int COLOR_SPACE_VALUE_UNCALIBRATED = 65535;
    public static final TagInfoShort EXIF_TAG_COLOR_SPACE;
    public static final TagInfoAscii EXIF_TAG_RELATED_IMAGE_FILE_FORMAT;
    public static final TagInfoShortOrLong EXIF_TAG_RELATED_IMAGE_LENGTH;
    public static final TagInfoShortOrLong EXIF_TAG_RELATED_IMAGE_WIDTH;

    static {
        TagInfoAscii tagInfoAscii = new TagInfoAscii("RelatedImageFileFormat", 4096, -1, TiffDirectoryType.EXIF_DIRECTORY_INTEROP_IFD);
        EXIF_TAG_RELATED_IMAGE_FILE_FORMAT = tagInfoAscii;
        TagInfoShortOrLong tagInfoShortOrLong = new TagInfoShortOrLong("RelatedImageWidth", FragmentTransaction.TRANSIT_FRAGMENT_OPEN, 1, TiffDirectoryType.EXIF_DIRECTORY_INTEROP_IFD);
        EXIF_TAG_RELATED_IMAGE_WIDTH = tagInfoShortOrLong;
        TagInfoShortOrLong tagInfoShortOrLong2 = new TagInfoShortOrLong("RelatedImageLength", InputDeviceCompat.SOURCE_TOUCHSCREEN, 1, TiffDirectoryType.EXIF_DIRECTORY_INTEROP_IFD);
        EXIF_TAG_RELATED_IMAGE_LENGTH = tagInfoShortOrLong2;
        TagInfoShort tagInfoShort = new TagInfoShort("ColorSpace", 40961, TiffDirectoryType.EXIF_DIRECTORY_EXIF_IFD);
        EXIF_TAG_COLOR_SPACE = tagInfoShort;
        ALL_DCF_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoAscii, tagInfoShortOrLong, tagInfoShortOrLong2, tagInfoShort));
    }

    private DcfTagConstants() {
    }
}
