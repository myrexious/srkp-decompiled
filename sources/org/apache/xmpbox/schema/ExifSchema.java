package org.apache.xmpbox.schema;

import java.util.List;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.Types;

@StructuredType(namespace = "http://ns.adobe.com/exif/1.0/", preferedPrefix = "exif")
/* loaded from: classes2.dex */
public class ExifSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String APERTURE_VALUE = "ApertureValue";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String BRIGHTNESS_VALUE = "BrightnessValue";
    @PropertyType(type = Types.CFAPattern)
    public static final String CFA_PATTERN = "CFAPattern";
    @PropertyType(type = Types.CFAPattern)
    public static final String CFA_PATTERN_TYPE = "CFAPatternType";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String COLOR_SPACE = "ColorSpace";
    @PropertyType(card = Cardinality.Seq, type = Types.Integer)
    public static final String COMPONENTS_CONFIGURATION = "ComponentsConfiguration";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String COMPRESSED_BPP = "CompressedBitsPerPixel";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String CONTRAST = "Contrast";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String CUSTOM_RENDERED = "CustomRendered";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String DATE_TIME_DIGITIZED = "DateTimeDigitized";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String DATE_TIME_ORIGINAL = "DateTimeOriginal";
    @PropertyType(type = Types.DeviceSettings)
    public static final String DEVICE_SETTING_DESCRIPTION = "DeviceSettingDescription";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String DIGITAL_ZOOM_RATIO = "DigitalZoomRatio";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String EXIF_VERSION = "ExifVersion";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String EXPOSURE_BIAS_VALUE = "ExposureBiasValue";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String EXPOSURE_INDEX = "ExposureIndex";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String EXPOSURE_MODE = "ExposureMode";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String EXPOSURE_PROGRAM = "ExposureProgram";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String EXPOSURE_TIME = "ExposureTime";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String FILE_SOURCE = "FileSource";
    @PropertyType(type = Types.Flash)
    public static final String FLASH = "Flash";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String FLASH_ENERGY = "FlashEnergy";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String FLASH_PIX_VERSION = "FlashpixVersion";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String FOCAL_LENGTH = "FocalLength";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String FOCAL_LENGTH_IN_3_5MM_FILM = "FocalLengthIn35mmFilm";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String FOCAL_PLANE_RESOLUTION_UNIT = "FocalPlaneResolutionUnit";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String FOCAL_PLANE_XRESOLUTION = "FocalPlaneXResolution";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String FOCAL_PLANE_YRESOLUTION = "FocalPlaneYResolution";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String F_NUMBER = "FNumber";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String GAIN_CONTROL = "GainControl";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPSVERSION_ID = "GPSVersionID";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String GPS_ALTITUDE = "GPSAltitude";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String GPS_ALTITUDE_REF = "GPSAltitudeRef";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_AREA_INFORMATION = "GPSAreaInformation";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String GPS_DEST_BEARING = "GPSDestBearing";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_DEST_BEARING_REF = "GPSDestBearingRef";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String GPS_DEST_DISTANCE = "GPSDestDistance";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_DEST_DISTANCE_REF = "GPSDestDistanceRef";
    @PropertyType(type = Types.GPSCoordinate)
    public static final String GPS_DEST_LATITUDE = "GPSDestLatitude";
    @PropertyType(type = Types.GPSCoordinate)
    public static final String GPS_DEST_LONGITUDE = "GPSDestLongitude";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String GPS_DIFFERENTIAL = "GPSDifferential";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String GPS_DOP = "GPSDOP";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String GPS_IMG_DIRECTION = "GPSImgDirection";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_IMG_DIRECTION_REF = "GPSImgDirectionRef";
    @PropertyType(type = Types.GPSCoordinate)
    public static final String GPS_LATITUDE = "GPSLatitude";
    @PropertyType(type = Types.GPSCoordinate)
    public static final String GPS_LONGITUDE = "GPSLongitude";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_MAP_DATUM = "GPSMapDatum";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_MEASURE_MODE = "GPSMeasureMode";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_PROCESSING_METHOD = "GPSProcessingMethod";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_SATELLITES = "GPSSatellites";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String GPS_SPEED = "GPSSpeed";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_SPEED_REF = "GPSSpeedRef";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_STATUS = "GPSStatus";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String GPS_TIME_STAMP = "GPSTimeStamp";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String GPS_TRACK = "GPSTrack";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String GPS_TRACK_REF = "GPSTrackRef";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String IMAGE_UNIQUE_ID = "ImageUniqueID";
    @PropertyType(card = Cardinality.Seq, type = Types.Integer)
    public static final String ISO_SPEED_RATINGS = "ISOSpeedRatings";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String LIGHT_SOURCE = "LightSource";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String MAX_APERTURE_VALUE = "MaxApertureValue";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String METERING_MODE = "MeteringMode";
    @PropertyType(type = Types.OECF)
    public static final String OECF = "OECF";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String PIXEL_X_DIMENSION = "PixelXDimension";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String PIXEL_Y_DIMENSION = "PixelYDimension";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String RELATED_SOUND_FILE = "RelatedSoundFile";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String SATURATION = "Saturation";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String SCENE_CAPTURE_TYPE = "SceneCaptureType";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String SCENE_TYPE = "SceneType";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String SENSING_METHOD = "SensingMethod";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String SHARPNESS = "Sharpness";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String SHUTTER_SPEED_VALUE = "ShutterSpeedValue";
    @PropertyType(type = Types.OECF)
    public static final String SPATIAL_FREQUENCY_RESPONSE = "SpatialFrequencyResponse";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String SPECTRAL_SENSITIVITY = "SpectralSensitivity";
    @PropertyType(card = Cardinality.Seq, type = Types.Integer)
    public static final String SUBJECT_AREA = "SubjectArea";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String SUBJECT_DISTANCE = "SubjectDistance";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String SUBJECT_DISTANCE_RANGE = "SubjectDistanceRange";
    @PropertyType(card = Cardinality.Seq, type = Types.Integer)
    public static final String SUBJECT_LOCATION = "SubjectLocation";
    @PropertyType(card = Cardinality.Simple, type = Types.LangAlt)
    public static final String USER_COMMENT = "UserComment";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String WHITE_BALANCE = "WhiteBalance";

    public ExifSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public ExifSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public ArrayProperty getUserCommentProperty() {
        return (ArrayProperty) getProperty("UserComment");
    }

    public List<String> getUserCommentLanguages() {
        return getUnqualifiedLanguagePropertyLanguagesValue("UserComment");
    }

    public String getUserComment(String str) {
        return getUnqualifiedLanguagePropertyValue("UserComment", str);
    }

    public String getUserComment() {
        return getUserComment(null);
    }
}
