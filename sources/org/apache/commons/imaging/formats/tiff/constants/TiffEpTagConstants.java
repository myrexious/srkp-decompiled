package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAsciiOrRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoBytes;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRationals;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSShorts;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShorts;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoUndefineds;

/* loaded from: classes2.dex */
public final class TiffEpTagConstants {
    public static final List<TagInfo> ALL_TIFF_EP_TAGS;
    public static final TagInfoAsciiOrRational EXIF_TAG_BATTERY_LEVEL;
    public static final TagInfoBytes EXIF_TAG_CFAPATTERN_2;
    public static final TagInfoShorts EXIF_TAG_CFAREPEAT_PATTERN_DIM;
    public static final TagInfoRationals EXIF_TAG_EXPOSURE_INDEX;
    public static final TagInfoRationals EXIF_TAG_FLASH_ENERGY;
    public static final TagInfoShort EXIF_TAG_FOCAL_PLANE_RESOLUTION_UNIT;
    public static final TagInfoRational EXIF_TAG_FOCAL_PLANE_XRESOLUTION;
    public static final TagInfoRational EXIF_TAG_FOCAL_PLANE_YRESOLUTION;
    public static final TagInfoAscii EXIF_TAG_IMAGE_HISTORY_EXIF_IFD;
    public static final TagInfoLong EXIF_TAG_IMAGE_NUMBER_EXIF_IFD;
    public static final TagInfoShort EXIF_TAG_INTERLACE;
    public static final TagInfoUndefineds EXIF_TAG_INTER_COLOR_PROFILE;
    public static final TagInfoUndefineds EXIF_TAG_NOISE_1;
    public static final TagInfoAscii EXIF_TAG_SECURITY_CLASSIFICATION_EXIF_IFD;
    public static final TagInfoShort EXIF_TAG_SELF_TIMER_MODE;
    public static final TagInfoShort EXIF_TAG_SENSING_METHOD;
    public static final TagInfoUndefineds EXIF_TAG_SPATIAL_FREQUENCY_RESPONSE_1;
    public static final TagInfoBytes EXIF_TAG_TIFF_EPSTANDARD_ID_1;
    public static final TagInfoSShorts EXIF_TAG_TIME_ZONE_OFFSET;
    public static final int FOCAL_PLANE_RESOLUTION_UNIT_VALUE_CM = 3;
    public static final int FOCAL_PLANE_RESOLUTION_UNIT_VALUE_INCHES = 2;
    public static final int FOCAL_PLANE_RESOLUTION_UNIT_VALUE_MM = 4;
    public static final int FOCAL_PLANE_RESOLUTION_UNIT_VALUE_NONE = 1;
    public static final int FOCAL_PLANE_RESOLUTION_UNIT_VALUE_UM = 5;
    public static final int SENSING_METHOD_VALUE_COLOR_SEQUENTIAL_AREA = 5;
    public static final int SENSING_METHOD_VALUE_COLOR_SEQUENTIAL_LINEAR = 8;
    public static final int SENSING_METHOD_VALUE_MONOCHROME_AREA = 1;
    public static final int SENSING_METHOD_VALUE_MONOCHROME_LINEAR = 6;
    public static final int SENSING_METHOD_VALUE_ONE_CHIP_COLOR_AREA = 2;
    public static final int SENSING_METHOD_VALUE_THREE_CHIP_COLOR_AREA = 4;
    public static final int SENSING_METHOD_VALUE_TRILINEAR = 7;
    public static final int SENSING_METHOD_VALUE_TWO_CHIP_COLOR_AREA = 3;

    static {
        TagInfoShorts tagInfoShorts = new TagInfoShorts("CFARepeatPatternDim", 33421, 2, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_CFAREPEAT_PATTERN_DIM = tagInfoShorts;
        TagInfoBytes tagInfoBytes = new TagInfoBytes("CFAPattern2", 33422, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_CFAPATTERN_2 = tagInfoBytes;
        TagInfoAsciiOrRational tagInfoAsciiOrRational = new TagInfoAsciiOrRational("BatteryLevel", 33423, -1, TiffDirectoryType.TIFF_DIRECTORY_ROOT);
        EXIF_TAG_BATTERY_LEVEL = tagInfoAsciiOrRational;
        TagInfoUndefineds tagInfoUndefineds = new TagInfoUndefineds("InterColorProfile", 34675, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_INTER_COLOR_PROFILE = tagInfoUndefineds;
        TagInfoShort tagInfoShort = new TagInfoShort("Interlace", 34857, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_INTERLACE = tagInfoShort;
        TagInfoSShorts tagInfoSShorts = new TagInfoSShorts("TimeZoneOffset", 34858, -1, TiffDirectoryType.EXIF_DIRECTORY_EXIF_IFD);
        EXIF_TAG_TIME_ZONE_OFFSET = tagInfoSShorts;
        TagInfoShort tagInfoShort2 = new TagInfoShort("SelfTimerMode", 34859, TiffDirectoryType.EXIF_DIRECTORY_EXIF_IFD);
        EXIF_TAG_SELF_TIMER_MODE = tagInfoShort2;
        TagInfoRationals tagInfoRationals = new TagInfoRationals("FlashEnergy", 37387, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_FLASH_ENERGY = tagInfoRationals;
        TagInfoUndefineds tagInfoUndefineds2 = new TagInfoUndefineds("SpatialFrequencyResponse", 37388, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_SPATIAL_FREQUENCY_RESPONSE_1 = tagInfoUndefineds2;
        TagInfoUndefineds tagInfoUndefineds3 = new TagInfoUndefineds("Noise", 37389, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_NOISE_1 = tagInfoUndefineds3;
        TagInfoRational tagInfoRational = new TagInfoRational("FocalPlaneXResolution", 37390, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_FOCAL_PLANE_XRESOLUTION = tagInfoRational;
        TagInfoRational tagInfoRational2 = new TagInfoRational("FocalPlaneYResolution", 37391, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_FOCAL_PLANE_YRESOLUTION = tagInfoRational2;
        TagInfoShort tagInfoShort3 = new TagInfoShort("FocalPlaneResolutionUnit", 37392, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_FOCAL_PLANE_RESOLUTION_UNIT = tagInfoShort3;
        TagInfoLong tagInfoLong = new TagInfoLong("ImageNumber", 37393, TiffDirectoryType.EXIF_DIRECTORY_EXIF_IFD);
        EXIF_TAG_IMAGE_NUMBER_EXIF_IFD = tagInfoLong;
        TagInfoAscii tagInfoAscii = new TagInfoAscii("SecurityClassification", 37394, -1, TiffDirectoryType.EXIF_DIRECTORY_EXIF_IFD);
        EXIF_TAG_SECURITY_CLASSIFICATION_EXIF_IFD = tagInfoAscii;
        TagInfoAscii tagInfoAscii2 = new TagInfoAscii("ImageHistory", 37395, -1, TiffDirectoryType.EXIF_DIRECTORY_EXIF_IFD);
        EXIF_TAG_IMAGE_HISTORY_EXIF_IFD = tagInfoAscii2;
        TagInfoRationals tagInfoRationals2 = new TagInfoRationals("ExposureIndex", 37397, -1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_EXPOSURE_INDEX = tagInfoRationals2;
        TagInfoBytes tagInfoBytes2 = new TagInfoBytes("TIFF/EPStandardID", 37398, 4, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_TIFF_EPSTANDARD_ID_1 = tagInfoBytes2;
        TagInfoShort tagInfoShort4 = new TagInfoShort("SensingMethod", 37399, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        EXIF_TAG_SENSING_METHOD = tagInfoShort4;
        ALL_TIFF_EP_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoShorts, tagInfoBytes, tagInfoAsciiOrRational, tagInfoUndefineds, tagInfoShort, tagInfoSShorts, tagInfoShort2, tagInfoRationals, tagInfoUndefineds2, tagInfoUndefineds3, tagInfoRational, tagInfoRational2, tagInfoShort3, tagInfoLong, tagInfoAscii, tagInfoAscii2, tagInfoRationals2, tagInfoBytes2, tagInfoShort4));
    }

    private TiffEpTagConstants() {
    }
}
