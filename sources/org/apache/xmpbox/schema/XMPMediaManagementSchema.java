package org.apache.xmpbox.schema;

import java.util.List;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.AgentNameType;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.IntegerType;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.RenditionClassType;
import org.apache.xmpbox.type.ResourceRefType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.TextType;
import org.apache.xmpbox.type.Types;
import org.apache.xmpbox.type.URIType;
import org.apache.xmpbox.type.URLType;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/mm/", preferedPrefix = "xmpMM")
/* loaded from: classes2.dex */
public class XMPMediaManagementSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Simple, type = Types.ResourceRef)
    public static final String DERIVED_FROM = "DerivedFrom";
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String DOCUMENTID = "DocumentID";
    @PropertyType(card = Cardinality.Seq, type = Types.ResourceEvent)
    public static final String HISTORY = "History";
    @PropertyType(card = Cardinality.Bag, type = Types.Text)
    public static final String INGREDIENTS = "Ingredients";
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String INSTANCEID = "InstanceID";
    @PropertyType(card = Cardinality.Simple, type = Types.URL)
    public static final String LAST_URL = "LastURL";
    @PropertyType(card = Cardinality.Simple, type = Types.ResourceRef)
    public static final String MANAGED_FROM = "ManagedFrom";
    @PropertyType(card = Cardinality.Simple, type = Types.AgentName)
    public static final String MANAGER = "Manager";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String MANAGERVARIANT = "ManagerVariant";
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String MANAGETO = "ManageTo";
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String MANAGEUI = "ManageUI";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String ORIGINALDOCUMENTID = "OriginalDocumentID";
    @PropertyType(card = Cardinality.Simple, type = Types.RenditionClass)
    public static final String RENDITIONCLASS = "RenditionClass";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String RENDITIONPARAMS = "RenditionParams";
    @PropertyType(card = Cardinality.Simple, type = Types.ResourceRef)
    public static final String RENDITION_OF = "RenditionOf";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String SAVE_ID = "SaveID";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String VERSIONID = "VersionID";
    @PropertyType(card = Cardinality.Seq, type = Types.Version)
    public static final String VERSIONS = "Versions";

    public XMPMediaManagementSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public XMPMediaManagementSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public void setDerivedFromProperty(ResourceRefType resourceRefType) {
        addProperty(resourceRefType);
    }

    public ResourceRefType getResourceRefProperty() {
        return (ResourceRefType) getProperty(DERIVED_FROM);
    }

    public void setDocumentID(String str) {
        setDocumentIDProperty((URIType) instanciateSimple(DOCUMENTID, str));
    }

    public void setDocumentIDProperty(URIType uRIType) {
        addProperty(uRIType);
    }

    public TextType getDocumentIDProperty() {
        return (TextType) getProperty(DOCUMENTID);
    }

    public String getDocumentID() {
        TextType documentIDProperty = getDocumentIDProperty();
        if (documentIDProperty != null) {
            return documentIDProperty.getStringValue();
        }
        return null;
    }

    public void setLastURL(String str) {
        setLastURLProperty((URLType) instanciateSimple(LAST_URL, str));
    }

    public void setLastURLProperty(URLType uRLType) {
        addProperty(uRLType);
    }

    public URLType getLastURLProperty() {
        return (URLType) getProperty(LAST_URL);
    }

    public String getLastURL() {
        URLType lastURLProperty = getLastURLProperty();
        if (lastURLProperty != null) {
            return lastURLProperty.getStringValue();
        }
        return null;
    }

    public void setSaveId(Integer num) {
        setSaveIDProperty((IntegerType) instanciateSimple(SAVE_ID, num));
    }

    public void setSaveIDProperty(IntegerType integerType) {
        addProperty(integerType);
    }

    public IntegerType getSaveIDProperty() {
        return (IntegerType) getProperty(SAVE_ID);
    }

    public Integer getSaveID() {
        IntegerType saveIDProperty = getSaveIDProperty();
        if (saveIDProperty != null) {
            return saveIDProperty.getValue();
        }
        return null;
    }

    public void setManager(String str) {
        setManagerProperty((AgentNameType) instanciateSimple(MANAGER, str));
    }

    public void setManagerProperty(AgentNameType agentNameType) {
        addProperty(agentNameType);
    }

    public TextType getManagerProperty() {
        return (TextType) getProperty(MANAGER);
    }

    public String getManager() {
        TextType managerProperty = getManagerProperty();
        if (managerProperty != null) {
            return managerProperty.getStringValue();
        }
        return null;
    }

    public void setManageTo(String str) {
        setManageToProperty((URIType) instanciateSimple(MANAGETO, str));
    }

    public void setManageToProperty(URIType uRIType) {
        addProperty(uRIType);
    }

    public TextType getManageToProperty() {
        return (TextType) getProperty(MANAGETO);
    }

    public String getManageTo() {
        TextType manageToProperty = getManageToProperty();
        if (manageToProperty != null) {
            return manageToProperty.getStringValue();
        }
        return null;
    }

    public void setManageUI(String str) {
        setManageUIProperty((URIType) instanciateSimple(MANAGEUI, str));
    }

    public void setManageUIProperty(URIType uRIType) {
        addProperty(uRIType);
    }

    public TextType getManageUIProperty() {
        return (TextType) getProperty(MANAGEUI);
    }

    public String getManageUI() {
        TextType manageUIProperty = getManageUIProperty();
        if (manageUIProperty != null) {
            return manageUIProperty.getStringValue();
        }
        return null;
    }

    public void setManagerVariant(String str) {
        setManagerVariantProperty((TextType) instanciateSimple(MANAGERVARIANT, str));
    }

    public void setManagerVariantProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getManagerVariantProperty() {
        return (TextType) getProperty(MANAGERVARIANT);
    }

    public String getManagerVariant() {
        TextType managerVariantProperty = getManagerVariantProperty();
        if (managerVariantProperty != null) {
            return managerVariantProperty.getStringValue();
        }
        return null;
    }

    public void setInstanceID(String str) {
        setInstanceIDProperty((URIType) instanciateSimple(INSTANCEID, str));
    }

    public void setInstanceIDProperty(URIType uRIType) {
        addProperty(uRIType);
    }

    public TextType getInstanceIDProperty() {
        return (TextType) getProperty(INSTANCEID);
    }

    public String getInstanceID() {
        TextType instanceIDProperty = getInstanceIDProperty();
        if (instanceIDProperty != null) {
            return instanceIDProperty.getStringValue();
        }
        return null;
    }

    public void setManagedFromProperty(ResourceRefType resourceRefType) {
        addProperty(resourceRefType);
    }

    public ResourceRefType getManagedFromProperty() {
        return (ResourceRefType) getProperty(MANAGED_FROM);
    }

    public void setOriginalDocumentID(String str) {
        setOriginalDocumentIDProperty((TextType) instanciateSimple(ORIGINALDOCUMENTID, str));
    }

    public void setOriginalDocumentIDProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getOriginalDocumentIDProperty() {
        return (TextType) getProperty(ORIGINALDOCUMENTID);
    }

    public String getOriginalDocumentID() {
        TextType originalDocumentIDProperty = getOriginalDocumentIDProperty();
        if (originalDocumentIDProperty != null) {
            return originalDocumentIDProperty.getStringValue();
        }
        return null;
    }

    public void setRenditionClass(String str) {
        setRenditionClassProperty((RenditionClassType) instanciateSimple(RENDITIONCLASS, str));
    }

    public void setRenditionClassProperty(RenditionClassType renditionClassType) {
        addProperty(renditionClassType);
    }

    public TextType getRenditionClassProperty() {
        return (TextType) getProperty(RENDITIONCLASS);
    }

    public String getRenditionClass() {
        TextType renditionClassProperty = getRenditionClassProperty();
        if (renditionClassProperty != null) {
            return renditionClassProperty.getStringValue();
        }
        return null;
    }

    public void setRenditionParams(String str) {
        setRenditionParamsProperty((TextType) instanciateSimple(RENDITIONPARAMS, str));
    }

    public void setRenditionParamsProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getRenditionParamsProperty() {
        return (TextType) getProperty(RENDITIONPARAMS);
    }

    public String getRenditionParams() {
        TextType renditionParamsProperty = getRenditionParamsProperty();
        if (renditionParamsProperty != null) {
            return renditionParamsProperty.getStringValue();
        }
        return null;
    }

    public void setVersionID(String str) {
        setVersionIDProperty((TextType) instanciateSimple(VERSIONID, str));
    }

    public void setVersionIDProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getVersionIDProperty() {
        return (TextType) getProperty(VERSIONID);
    }

    public String getVersionID() {
        TextType versionIDProperty = getVersionIDProperty();
        if (versionIDProperty != null) {
            return versionIDProperty.getStringValue();
        }
        return null;
    }

    public void addVersions(String str) {
        addQualifiedBagValue(VERSIONS, str);
    }

    public ArrayProperty getVersionsProperty() {
        return (ArrayProperty) getProperty(VERSIONS);
    }

    public List<String> getVersions() {
        return getUnqualifiedBagValueList(VERSIONS);
    }

    public void addHistory(String str) {
        addUnqualifiedSequenceValue("History", str);
    }

    public ArrayProperty getHistoryProperty() {
        return (ArrayProperty) getProperty("History");
    }

    public List<String> getHistory() {
        return getUnqualifiedSequenceValueList("History");
    }

    public void addIngredients(String str) {
        addQualifiedBagValue(INGREDIENTS, str);
    }

    public ArrayProperty getIngredientsProperty() {
        return (ArrayProperty) getProperty(INGREDIENTS);
    }

    public List<String> getIngredients() {
        return getUnqualifiedBagValueList(INGREDIENTS);
    }
}
