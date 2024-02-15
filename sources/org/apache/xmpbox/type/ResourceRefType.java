package org.apache.xmpbox.type;

import java.util.Calendar;
import java.util.List;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.XmpConstants;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/sType/ResourceRef#", preferedPrefix = "stRef")
/* loaded from: classes2.dex */
public class ResourceRefType extends AbstractStructuredType {
    public static final String ALTERNATE_PATHS = "alternatePaths";
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String DOCUMENT_ID = "documentID";
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String FILE_PATH = "filePath";
    @PropertyType(card = Cardinality.Simple, type = Types.Part)
    public static final String FROM_PART = "fromPart";
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String INSTANCE_ID = "instanceID";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String LAST_MODIFY_DATE = "lastModifyDate";
    @PropertyType(card = Cardinality.Simple, type = Types.AgentName)
    public static final String MANAGER = "manager";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String MANAGER_VARIANT = "managerVariant";
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String MANAGE_TO = "manageTo";
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String MANAGE_UI = "manageUI";
    @PropertyType(card = Cardinality.Simple, type = Types.Choice)
    public static final String MASK_MARKERS = "maskMarkers";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String PART_MAPPING = "partMapping";
    @PropertyType(card = Cardinality.Simple, type = Types.RenditionClass)
    public static final String RENDITION_CLASS = "renditionClass";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String RENDITION_PARAMS = "renditionParams";
    @PropertyType(card = Cardinality.Simple, type = Types.Part)
    public static final String TO_PART = "toPart";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String VERSION_ID = "versionID";

    public ResourceRefType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
        addNamespace(getNamespace(), getPreferedPrefix());
    }

    public String getDocumentID() {
        TextType textType = (TextType) getFirstEquivalentProperty(DOCUMENT_ID, URIType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setDocumentID(String str) {
        addSimpleProperty(DOCUMENT_ID, str);
    }

    public String getFilePath() {
        TextType textType = (TextType) getFirstEquivalentProperty(FILE_PATH, URIType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setFilePath(String str) {
        addSimpleProperty(FILE_PATH, str);
    }

    public String getInstanceID() {
        TextType textType = (TextType) getFirstEquivalentProperty("instanceID", URIType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setInstanceID(String str) {
        addSimpleProperty("instanceID", str);
    }

    public Calendar getLastModifyDate() {
        DateType dateType = (DateType) getFirstEquivalentProperty(LAST_MODIFY_DATE, DateType.class);
        if (dateType != null) {
            return dateType.getValue();
        }
        return null;
    }

    public void setLastModifyDate(Calendar calendar) {
        addSimpleProperty(LAST_MODIFY_DATE, calendar);
    }

    public String getManageUI() {
        TextType textType = (TextType) getFirstEquivalentProperty(MANAGE_UI, URIType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setManageUI(String str) {
        addSimpleProperty(MANAGE_UI, str);
    }

    public String getManageTo() {
        TextType textType = (TextType) getFirstEquivalentProperty(MANAGE_TO, URIType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setManageTo(String str) {
        addSimpleProperty(MANAGE_TO, str);
    }

    public String getManager() {
        TextType textType = (TextType) getFirstEquivalentProperty(MANAGER, AgentNameType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setManager(String str) {
        addSimpleProperty(MANAGER, str);
    }

    public String getManagerVariant() {
        TextType textType = (TextType) getFirstEquivalentProperty(MANAGER_VARIANT, TextType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setManagerVariant(String str) {
        addSimpleProperty(MANAGER_VARIANT, str);
    }

    public String getPartMapping() {
        TextType textType = (TextType) getFirstEquivalentProperty(PART_MAPPING, TextType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setPartMapping(String str) {
        addSimpleProperty(PART_MAPPING, str);
    }

    public String getRenditionParams() {
        TextType textType = (TextType) getFirstEquivalentProperty(RENDITION_PARAMS, TextType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setRenditionParams(String str) {
        addSimpleProperty(RENDITION_PARAMS, str);
    }

    public String getVersionID() {
        TextType textType = (TextType) getFirstEquivalentProperty(VERSION_ID, TextType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setVersionID(String str) {
        addSimpleProperty(VERSION_ID, str);
    }

    public String getMaskMarkers() {
        TextType textType = (TextType) getFirstEquivalentProperty(MASK_MARKERS, ChoiceType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setMaskMarkers(String str) {
        addSimpleProperty(MASK_MARKERS, str);
    }

    public String getRenditionClass() {
        TextType textType = (TextType) getFirstEquivalentProperty(RENDITION_CLASS, RenditionClassType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setRenditionClass(String str) {
        addSimpleProperty(RENDITION_CLASS, str);
    }

    public String getFromPart() {
        TextType textType = (TextType) getFirstEquivalentProperty(FROM_PART, PartType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setFromPart(String str) {
        addSimpleProperty(FROM_PART, str);
    }

    public String getToPart() {
        TextType textType = (TextType) getFirstEquivalentProperty(TO_PART, PartType.class);
        if (textType != null) {
            return textType.getStringValue();
        }
        return null;
    }

    public void setToPart(String str) {
        addSimpleProperty(TO_PART, str);
    }

    public void addAlternatePath(String str) {
        ArrayProperty arrayProperty = (ArrayProperty) getFirstEquivalentProperty(ALTERNATE_PATHS, ArrayProperty.class);
        if (arrayProperty == null) {
            arrayProperty = getMetadata().getTypeMapping().createArrayProperty(null, getPreferedPrefix(), ALTERNATE_PATHS, Cardinality.Seq);
            addProperty(arrayProperty);
        }
        arrayProperty.addProperty((TextType) getMetadata().getTypeMapping().instanciateSimpleProperty(null, XmpConstants.DEFAULT_RDF_PREFIX, XmpConstants.LIST_NAME, str, Types.Text));
    }

    public ArrayProperty getAlternatePathsProperty() {
        return (ArrayProperty) getFirstEquivalentProperty(ALTERNATE_PATHS, ArrayProperty.class);
    }

    public List<String> getAlternatePaths() {
        ArrayProperty arrayProperty = (ArrayProperty) getFirstEquivalentProperty(ALTERNATE_PATHS, ArrayProperty.class);
        if (arrayProperty != null) {
            return arrayProperty.getElementsAsString();
        }
        return null;
    }
}
