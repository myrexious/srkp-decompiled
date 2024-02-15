package org.apache.xmpbox.schema;

import java.util.List;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.ProperNameType;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.Types;

@StructuredType(namespace = "http://ns.adobe.com/tiff/1.0/", preferedPrefix = "tiff")
/* loaded from: classes2.dex */
public class TiffSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Simple, type = Types.ProperName)
    public static final String ARTIST = "Artist";
    @PropertyType(card = Cardinality.Seq, type = Types.Integer)
    public static final String BITS_PER_SAMPLE = "BitsPerSample";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String COMPRESSION = "Compression";
    @PropertyType(card = Cardinality.Simple, type = Types.LangAlt)
    public static final String COPYRIGHT = "Copyright";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String DATE_TIME = "DateTime";
    @PropertyType(card = Cardinality.Simple, type = Types.LangAlt)
    public static final String IMAGE_DESCRIPTION = "ImageDescription";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String IMAGE_LENGHT = "ImageLength";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String IMAGE_WIDTH = "ImageWidth";
    @PropertyType(card = Cardinality.Simple, type = Types.ProperName)
    public static final String MAKE = "Make";
    @PropertyType(card = Cardinality.Simple, type = Types.ProperName)
    public static final String MODEL = "Model";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String ORIENTATION = "Orientation";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String PHOTOMETRIC_INTERPRETATION = "PhotometricInterpretation";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String PLANAR_CONFIGURATION = "PlanarConfiguration";
    @PropertyType(card = Cardinality.Seq, type = Types.Rational)
    public static final String PRIMARY_CHROMATICITIES = "PrimaryChromaticities";
    @PropertyType(card = Cardinality.Seq, type = Types.Rational)
    public static final String REFERENCE_BLACK_WHITE = "ReferenceBlackWhite";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String RESOLUTION_UNIT = "ResolutionUnit";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String SAMPLES_PER_PIXEL = "SamplesPerPixel";
    @PropertyType(card = Cardinality.Simple, type = Types.AgentName)
    public static final String SOFTWARE = "Software";
    @PropertyType(card = Cardinality.Seq, type = Types.Integer)
    public static final String TRANSFER_FUNCTION = "TransferFunction";
    @PropertyType(card = Cardinality.Seq, type = Types.Rational)
    public static final String WHITE_POINT = "WhitePoint";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String XRESOLUTION = "XResolution";
    @PropertyType(card = Cardinality.Seq, type = Types.Rational)
    public static final String YCB_CR_COEFFICIENTS = "YCbCrCoefficients";
    @PropertyType(card = Cardinality.Seq, type = Types.Integer)
    public static final String YCB_CR_POSITIONING = "YCbCrPositioning";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String YCB_CR_SUB_SAMPLING = "YCbCrSubSampling";
    @PropertyType(card = Cardinality.Simple, type = Types.Rational)
    public static final String YRESOLUTION = "YResolution";

    public TiffSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public TiffSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public ProperNameType getArtistProperty() {
        return (ProperNameType) getProperty("Artist");
    }

    public String getArtist() {
        ProperNameType properNameType = (ProperNameType) getProperty("Artist");
        if (properNameType == null) {
            return null;
        }
        return properNameType.getStringValue();
    }

    public void setArtist(String str) {
        addProperty(createTextType("Artist", str));
    }

    public ArrayProperty getImageDescriptionProperty() {
        return (ArrayProperty) getProperty("ImageDescription");
    }

    public List<String> getImageDescriptionLanguages() {
        return getUnqualifiedLanguagePropertyLanguagesValue("ImageDescription");
    }

    public String getImageDescription(String str) {
        return getUnqualifiedLanguagePropertyValue("ImageDescription", str);
    }

    public String getImageDescription() {
        return getImageDescription(null);
    }

    public void addImageDescription(String str, String str2) {
        setUnqualifiedLanguagePropertyValue("ImageDescription", str, str2);
    }

    public ArrayProperty getCopyRightProperty() {
        return (ArrayProperty) getProperty("Copyright");
    }

    public List<String> getCopyRightLanguages() {
        return getUnqualifiedLanguagePropertyLanguagesValue("Copyright");
    }

    public String getCopyRight(String str) {
        return getUnqualifiedLanguagePropertyValue("Copyright", str);
    }

    public String getCopyRight() {
        return getCopyRight(null);
    }

    public void addCopyright(String str, String str2) {
        setUnqualifiedLanguagePropertyValue("Copyright", str, str2);
    }
}
