package org.apache.xmpbox.type;

import com.tom_roush.fontbox.afm.AFMParser;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.xmpbox.schema.XMPMediaManagementSchema;

/* loaded from: classes2.dex */
public final class Types {
    private static final /* synthetic */ Types[] $VALUES;
    public static final Types AgentName;
    public static final Types Boolean;
    public static final Types CFAPattern;
    public static final Types Choice;
    public static final Types Date;
    public static final Types DefinedType;
    public static final Types DeviceSettings;
    public static final Types Dimensions;
    public static final Types Flash;
    public static final Types GPSCoordinate;
    public static final Types GUID;
    public static final Types Integer;
    public static final Types Job;
    public static final Types LangAlt;
    public static final Types Layer;
    public static final Types Locale;
    public static final Types MIMEType;
    public static final Types OECF;
    public static final Types PDFAField;
    public static final Types PDFAProperty;
    public static final Types PDFASchema;
    public static final Types PDFAType;
    public static final Types Part;
    public static final Types ProperName;
    public static final Types Rational;
    public static final Types Real;
    public static final Types RenditionClass;
    public static final Types ResourceEvent;
    public static final Types ResourceRef;
    public static final Types Structured;
    public static final Types Text;
    public static final Types Thumbnail;
    public static final Types URI;
    public static final Types URL;
    public static final Types Version;
    public static final Types XPath;
    private final Types basic;
    private final Class<? extends AbstractField> clz;
    private final boolean simple;

    public static Types valueOf(String str) {
        return (Types) Enum.valueOf(Types.class, str);
    }

    public static Types[] values() {
        return (Types[]) $VALUES.clone();
    }

    static {
        Types types = new Types("Structured", 0, false, null, null);
        Structured = types;
        Types types2 = new Types("DefinedType", 1, false, null, null);
        DefinedType = types2;
        Types types3 = new Types("Text", 2, true, null, TextType.class);
        Text = types3;
        Types types4 = new Types("Date", 3, true, null, DateType.class);
        Date = types4;
        Types types5 = new Types("Boolean", 4, true, null, BooleanType.class);
        Boolean = types5;
        Types types6 = new Types("Integer", 5, true, null, IntegerType.class);
        Integer = types6;
        Types types7 = new Types("Real", 6, true, null, RealType.class);
        Real = types7;
        Types types8 = new Types("GPSCoordinate", 7, true, types3, TextType.class);
        GPSCoordinate = types8;
        Types types9 = new Types("ProperName", 8, true, types3, ProperNameType.class);
        ProperName = types9;
        Types types10 = new Types("Locale", 9, true, types3, LocaleType.class);
        Locale = types10;
        Types types11 = new Types("AgentName", 10, true, types3, AgentNameType.class);
        AgentName = types11;
        Types types12 = new Types("GUID", 11, true, types3, GUIDType.class);
        GUID = types12;
        Types types13 = new Types("XPath", 12, true, types3, XPathType.class);
        XPath = types13;
        Types types14 = new Types(StandardStructureTypes.PART, 13, true, types3, PartType.class);
        Part = types14;
        Types types15 = new Types("URL", 14, true, types3, URLType.class);
        URL = types15;
        Types types16 = new Types(PDActionURI.SUB_TYPE, 15, true, types3, URIType.class);
        URI = types16;
        Types types17 = new Types("Choice", 16, true, types3, ChoiceType.class);
        Choice = types17;
        Types types18 = new Types("MIMEType", 17, true, types3, MIMEType.class);
        MIMEType = types18;
        Types types19 = new Types("LangAlt", 18, true, types3, TextType.class);
        LangAlt = types19;
        Types types20 = new Types(XMPMediaManagementSchema.RENDITIONCLASS, 19, true, types3, RenditionClassType.class);
        RenditionClass = types20;
        Types types21 = new Types("Rational", 20, true, types3, RationalType.class);
        Rational = types21;
        Types types22 = new Types("Layer", 21, false, types, LayerType.class);
        Layer = types22;
        Types types23 = new Types("Thumbnail", 22, false, types, ThumbnailType.class);
        Thumbnail = types23;
        Types types24 = new Types("ResourceEvent", 23, false, types, ResourceEventType.class);
        ResourceEvent = types24;
        Types types25 = new Types("ResourceRef", 24, false, types, ResourceRefType.class);
        ResourceRef = types25;
        Types types26 = new Types(AFMParser.VERSION, 25, false, types, VersionType.class);
        Version = types26;
        Types types27 = new Types("PDFASchema", 26, false, types, PDFASchemaType.class);
        PDFASchema = types27;
        Types types28 = new Types("PDFAField", 27, false, types, PDFAFieldType.class);
        PDFAField = types28;
        Types types29 = new Types("PDFAProperty", 28, false, types, PDFAPropertyType.class);
        PDFAProperty = types29;
        Types types30 = new Types("PDFAType", 29, false, types, PDFATypeType.class);
        PDFAType = types30;
        Types types31 = new Types("Job", 30, false, types, JobType.class);
        Job = types31;
        Types types32 = new Types("OECF", 31, false, types, OECFType.class);
        OECF = types32;
        Types types33 = new Types("CFAPattern", 32, false, types, CFAPatternType.class);
        CFAPattern = types33;
        Types types34 = new Types("DeviceSettings", 33, false, types, DeviceSettingsType.class);
        DeviceSettings = types34;
        Types types35 = new Types("Flash", 34, false, types, FlashType.class);
        Flash = types35;
        Types types36 = new Types("Dimensions", 35, false, types, DimensionsType.class);
        Dimensions = types36;
        $VALUES = new Types[]{types, types2, types3, types4, types5, types6, types7, types8, types9, types10, types11, types12, types13, types14, types15, types16, types17, types18, types19, types20, types21, types22, types23, types24, types25, types26, types27, types28, types29, types30, types31, types32, types33, types34, types35, types36};
    }

    private Types(String str, int i, boolean z, Types types, Class cls) {
        super(str, i);
        this.simple = z;
        this.basic = types;
        this.clz = cls;
    }

    public boolean isSimple() {
        return this.simple;
    }

    public boolean isBasic() {
        return this.basic == null;
    }

    public boolean isStructured() {
        return this.basic == Structured;
    }

    public boolean isDefined() {
        return this == DefinedType;
    }

    public Types getBasic() {
        return this.basic;
    }

    public Class<? extends AbstractField> getImplementingClass() {
        return this.clz;
    }
}
