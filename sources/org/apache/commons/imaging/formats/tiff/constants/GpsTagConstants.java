package org.apache.commons.imaging.formats.tiff.constants;

import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoByte;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoBytes;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoGpsText;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRationals;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShort;

/* loaded from: classes2.dex */
public final class GpsTagConstants {
    public static final List<TagInfo> ALL_GPS_TAGS;
    public static final TagInfoRational GPS_TAG_GPS_ALTITUDE;
    public static final TagInfoByte GPS_TAG_GPS_ALTITUDE_REF;
    public static final int GPS_TAG_GPS_ALTITUDE_REF_VALUE_ABOVE_SEA_LEVEL = 0;
    public static final int GPS_TAG_GPS_ALTITUDE_REF_VALUE_BELOW_SEA_LEVEL = 1;
    public static final TagInfoGpsText GPS_TAG_GPS_AREA_INFORMATION;
    public static final TagInfoAscii GPS_TAG_GPS_DATE_STAMP;
    public static final TagInfoRational GPS_TAG_GPS_DEST_BEARING;
    public static final TagInfoAscii GPS_TAG_GPS_DEST_BEARING_REF;
    public static final String GPS_TAG_GPS_DEST_BEARING_REF_VALUE_MAGNETIC_NORTH = "M";
    public static final String GPS_TAG_GPS_DEST_BEARING_REF_VALUE_TRUE_NORTH = "T";
    public static final TagInfoRational GPS_TAG_GPS_DEST_DISTANCE;
    public static final TagInfoAscii GPS_TAG_GPS_DEST_DISTANCE_REF;
    public static final String GPS_TAG_GPS_DEST_DISTANCE_REF_VALUE_KILOMETERS = "K";
    public static final String GPS_TAG_GPS_DEST_DISTANCE_REF_VALUE_MILES = "M";
    public static final String GPS_TAG_GPS_DEST_DISTANCE_REF_VALUE_NAUTICAL_MILES = "N";
    public static final TagInfoRationals GPS_TAG_GPS_DEST_LATITUDE;
    public static final TagInfoAscii GPS_TAG_GPS_DEST_LATITUDE_REF;
    public static final String GPS_TAG_GPS_DEST_LATITUDE_REF_VALUE_NORTH = "N";
    public static final String GPS_TAG_GPS_DEST_LATITUDE_REF_VALUE_SOUTH = "S";
    public static final TagInfoRationals GPS_TAG_GPS_DEST_LONGITUDE;
    public static final TagInfoAscii GPS_TAG_GPS_DEST_LONGITUDE_REF;
    public static final String GPS_TAG_GPS_DEST_LONGITUDE_REF_VALUE_EAST = "E";
    public static final String GPS_TAG_GPS_DEST_LONGITUDE_REF_VALUE_WEST = "W";
    public static final TagInfoShort GPS_TAG_GPS_DIFFERENTIAL;
    public static final int GPS_TAG_GPS_DIFFERENTIAL_VALUE_DIFFERENTIAL_CORRECTED = 1;
    public static final int GPS_TAG_GPS_DIFFERENTIAL_VALUE_NO_CORRECTION = 0;
    public static final TagInfoRational GPS_TAG_GPS_DOP;
    public static final TagInfoRational GPS_TAG_GPS_IMG_DIRECTION;
    public static final TagInfoAscii GPS_TAG_GPS_IMG_DIRECTION_REF;
    public static final String GPS_TAG_GPS_IMG_DIRECTION_REF_VALUE_MAGNETIC_NORTH = "M";
    public static final String GPS_TAG_GPS_IMG_DIRECTION_REF_VALUE_TRUE_NORTH = "T";
    public static final TagInfoRationals GPS_TAG_GPS_LATITUDE;
    public static final TagInfoAscii GPS_TAG_GPS_LATITUDE_REF;
    public static final String GPS_TAG_GPS_LATITUDE_REF_VALUE_NORTH = "N";
    public static final String GPS_TAG_GPS_LATITUDE_REF_VALUE_SOUTH = "S";
    public static final TagInfoRationals GPS_TAG_GPS_LONGITUDE;
    public static final TagInfoAscii GPS_TAG_GPS_LONGITUDE_REF;
    public static final String GPS_TAG_GPS_LONGITUDE_REF_VALUE_EAST = "E";
    public static final String GPS_TAG_GPS_LONGITUDE_REF_VALUE_WEST = "W";
    public static final TagInfoAscii GPS_TAG_GPS_MAP_DATUM;
    public static final TagInfoAscii GPS_TAG_GPS_MEASURE_MODE;
    public static final int GPS_TAG_GPS_MEASURE_MODE_VALUE_2_DIMENSIONAL_MEASUREMENT = 2;
    public static final int GPS_TAG_GPS_MEASURE_MODE_VALUE_3_DIMENSIONAL_MEASUREMENT = 3;
    public static final TagInfoGpsText GPS_TAG_GPS_PROCESSING_METHOD;
    public static final TagInfoAscii GPS_TAG_GPS_SATELLITES;
    public static final TagInfoRational GPS_TAG_GPS_SPEED;
    public static final TagInfoAscii GPS_TAG_GPS_SPEED_REF;
    public static final String GPS_TAG_GPS_SPEED_REF_VALUE_KMPH = "K";
    public static final String GPS_TAG_GPS_SPEED_REF_VALUE_KNOTS = "N";
    public static final String GPS_TAG_GPS_SPEED_REF_VALUE_MPH = "M";
    public static final TagInfoAscii GPS_TAG_GPS_STATUS;
    public static final String GPS_TAG_GPS_STATUS_VALUE_MEASUREMENT_INTEROPERABILITY = "V";
    public static final String GPS_TAG_GPS_STATUS_VALUE_MEASUREMENT_IN_PROGRESS = "A";
    public static final TagInfoRationals GPS_TAG_GPS_TIME_STAMP;
    public static final TagInfoRational GPS_TAG_GPS_TRACK;
    public static final TagInfoAscii GPS_TAG_GPS_TRACK_REF;
    public static final String GPS_TAG_GPS_TRACK_REF_VALUE_MAGNETIC_NORTH = "M";
    public static final String GPS_TAG_GPS_TRACK_REF_VALUE_TRUE_NORTH = "T";
    public static final TagInfoBytes GPS_TAG_GPS_VERSION_ID;
    private static final byte[] GPS_VERSION;

    static {
        TagInfoBytes tagInfoBytes = new TagInfoBytes("GPSVersionID", 0, 4, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_VERSION_ID = tagInfoBytes;
        GPS_VERSION = new byte[]{2, 3, 0, 0};
        TagInfoAscii tagInfoAscii = new TagInfoAscii(ExifInterface.TAG_GPS_LATITUDE_REF, 1, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_LATITUDE_REF = tagInfoAscii;
        TagInfoRationals tagInfoRationals = new TagInfoRationals("GPSLatitude", 2, 3, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_LATITUDE = tagInfoRationals;
        TagInfoAscii tagInfoAscii2 = new TagInfoAscii(ExifInterface.TAG_GPS_LONGITUDE_REF, 3, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_LONGITUDE_REF = tagInfoAscii2;
        TagInfoRationals tagInfoRationals2 = new TagInfoRationals("GPSLongitude", 4, 3, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_LONGITUDE = tagInfoRationals2;
        TagInfoByte tagInfoByte = new TagInfoByte("GPSAltitudeRef", 5, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_ALTITUDE_REF = tagInfoByte;
        TagInfoRational tagInfoRational = new TagInfoRational("GPSAltitude", 6, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_ALTITUDE = tagInfoRational;
        TagInfoRationals tagInfoRationals3 = new TagInfoRationals("GPSTimeStamp", 7, 3, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_TIME_STAMP = tagInfoRationals3;
        TagInfoAscii tagInfoAscii3 = new TagInfoAscii("GPSSatellites", 8, -1, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_SATELLITES = tagInfoAscii3;
        TagInfoAscii tagInfoAscii4 = new TagInfoAscii("GPSStatus", 9, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_STATUS = tagInfoAscii4;
        TagInfoAscii tagInfoAscii5 = new TagInfoAscii("GPSMeasureMode", 10, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_MEASURE_MODE = tagInfoAscii5;
        TagInfoRational tagInfoRational2 = new TagInfoRational("GPSDOP", 11, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DOP = tagInfoRational2;
        TagInfoAscii tagInfoAscii6 = new TagInfoAscii("GPSSpeedRef", 12, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_SPEED_REF = tagInfoAscii6;
        TagInfoRational tagInfoRational3 = new TagInfoRational("GPSSpeed", 13, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_SPEED = tagInfoRational3;
        TagInfoAscii tagInfoAscii7 = new TagInfoAscii("GPSTrackRef", 14, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_TRACK_REF = tagInfoAscii7;
        TagInfoRational tagInfoRational4 = new TagInfoRational("GPSTrack", 15, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_TRACK = tagInfoRational4;
        TagInfoAscii tagInfoAscii8 = new TagInfoAscii("GPSImgDirectionRef", 16, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_IMG_DIRECTION_REF = tagInfoAscii8;
        TagInfoRational tagInfoRational5 = new TagInfoRational("GPSImgDirection", 17, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_IMG_DIRECTION = tagInfoRational5;
        TagInfoAscii tagInfoAscii9 = new TagInfoAscii("GPSMapDatum", 18, -1, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_MAP_DATUM = tagInfoAscii9;
        TagInfoAscii tagInfoAscii10 = new TagInfoAscii(ExifInterface.TAG_GPS_DEST_LATITUDE_REF, 19, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DEST_LATITUDE_REF = tagInfoAscii10;
        TagInfoRationals tagInfoRationals4 = new TagInfoRationals("GPSDestLatitude", 20, 3, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DEST_LATITUDE = tagInfoRationals4;
        TagInfoAscii tagInfoAscii11 = new TagInfoAscii(ExifInterface.TAG_GPS_DEST_LONGITUDE_REF, 21, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DEST_LONGITUDE_REF = tagInfoAscii11;
        TagInfoRationals tagInfoRationals5 = new TagInfoRationals("GPSDestLongitude", 22, 3, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DEST_LONGITUDE = tagInfoRationals5;
        TagInfoAscii tagInfoAscii12 = new TagInfoAscii("GPSDestBearingRef", 23, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DEST_BEARING_REF = tagInfoAscii12;
        TagInfoRational tagInfoRational6 = new TagInfoRational("GPSDestBearing", 24, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DEST_BEARING = tagInfoRational6;
        TagInfoAscii tagInfoAscii13 = new TagInfoAscii("GPSDestDistanceRef", 25, 2, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DEST_DISTANCE_REF = tagInfoAscii13;
        TagInfoRational tagInfoRational7 = new TagInfoRational("GPSDestDistance", 26, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DEST_DISTANCE = tagInfoRational7;
        TagInfoGpsText tagInfoGpsText = new TagInfoGpsText("GPSProcessingMethod", 27, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_PROCESSING_METHOD = tagInfoGpsText;
        TagInfoGpsText tagInfoGpsText2 = new TagInfoGpsText("GPSAreaInformation", 28, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_AREA_INFORMATION = tagInfoGpsText2;
        TagInfoAscii tagInfoAscii14 = new TagInfoAscii(ExifInterface.TAG_GPS_DATESTAMP, 29, 11, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DATE_STAMP = tagInfoAscii14;
        TagInfoShort tagInfoShort = new TagInfoShort("GPSDifferential", 30, TiffDirectoryType.EXIF_DIRECTORY_GPS);
        GPS_TAG_GPS_DIFFERENTIAL = tagInfoShort;
        ALL_GPS_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoBytes, tagInfoAscii, tagInfoRationals, tagInfoAscii2, tagInfoRationals2, tagInfoByte, tagInfoRational, tagInfoRationals3, tagInfoAscii3, tagInfoAscii4, tagInfoAscii5, tagInfoRational2, tagInfoAscii6, tagInfoRational3, tagInfoAscii7, tagInfoRational4, tagInfoAscii8, tagInfoRational5, tagInfoAscii9, tagInfoAscii10, tagInfoRationals4, tagInfoAscii11, tagInfoRationals5, tagInfoAscii12, tagInfoRational6, tagInfoAscii13, tagInfoRational7, tagInfoGpsText, tagInfoGpsText2, tagInfoAscii14, tagInfoShort));
    }

    public static byte[] gpsVersion() {
        return (byte[]) GPS_VERSION.clone();
    }

    private GpsTagConstants() {
    }
}