package org.apache.xmpbox.schema;

import java.util.List;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.BooleanType;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.TextType;
import org.apache.xmpbox.type.Types;
import org.apache.xmpbox.type.URLType;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/rights/", preferedPrefix = "xmpRights")
/* loaded from: classes2.dex */
public class XMPRightsManagementSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Simple, type = Types.URL)
    public static final String CERTIFICATE = "Certificate";
    @PropertyType(card = Cardinality.Simple, type = Types.Boolean)
    public static final String MARKED = "Marked";
    @PropertyType(card = Cardinality.Bag, type = Types.ProperName)
    public static final String OWNER = "Owner";
    @PropertyType(card = Cardinality.Simple, type = Types.LangAlt)
    public static final String USAGETERMS = "UsageTerms";
    @PropertyType(card = Cardinality.Simple, type = Types.URL)
    public static final String WEBSTATEMENT = "WebStatement";

    public XMPRightsManagementSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public XMPRightsManagementSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public void addOwner(String str) {
        addQualifiedBagValue(OWNER, str);
    }

    public void removeOwner(String str) {
        removeUnqualifiedBagValue(OWNER, str);
    }

    public ArrayProperty getOwnersProperty() {
        return (ArrayProperty) getProperty(OWNER);
    }

    public List<String> getOwners() {
        return getUnqualifiedBagValueList(OWNER);
    }

    public void setMarked(Boolean bool) {
        setMarkedProperty((BooleanType) instanciateSimple(MARKED, bool.booleanValue() ? BooleanType.TRUE : BooleanType.FALSE));
    }

    public void setMarkedProperty(BooleanType booleanType) {
        addProperty(booleanType);
    }

    public BooleanType getMarkedProperty() {
        return (BooleanType) getProperty(MARKED);
    }

    public Boolean getMarked() {
        BooleanType booleanType = (BooleanType) getProperty(MARKED);
        if (booleanType == null) {
            return null;
        }
        return booleanType.getValue();
    }

    public void addUsageTerms(String str, String str2) {
        setUnqualifiedLanguagePropertyValue(USAGETERMS, str, str2);
    }

    public void setUsageTerms(String str) {
        addUsageTerms(null, str);
    }

    public ArrayProperty getUsageTermsProperty() {
        return (ArrayProperty) getProperty(USAGETERMS);
    }

    public List<String> getUsageTermsLanguages() {
        return getUnqualifiedLanguagePropertyLanguagesValue(USAGETERMS);
    }

    public String getUsageTerms(String str) {
        return getUnqualifiedLanguagePropertyValue(USAGETERMS, str);
    }

    public String getUsageTerms() {
        return getUsageTerms(null);
    }

    public TextType getWebStatementProperty() {
        return (TextType) getProperty(WEBSTATEMENT);
    }

    public String getWebStatement() {
        TextType textType = (TextType) getProperty(WEBSTATEMENT);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setWebStatement(String str) {
        setWebStatementProperty((URLType) instanciateSimple(WEBSTATEMENT, str));
    }

    public void setWebStatementProperty(URLType uRLType) {
        addProperty(uRLType);
    }

    public TextType getCertificateProperty() {
        return (TextType) getProperty(CERTIFICATE);
    }

    public String getCertificate() {
        TextType textType = (TextType) getProperty(CERTIFICATE);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setCertificate(String str) {
        setCertificateProperty((URLType) instanciateSimple(CERTIFICATE, str));
    }

    public void setCertificateProperty(URLType uRLType) {
        addProperty(uRLType);
    }
}
