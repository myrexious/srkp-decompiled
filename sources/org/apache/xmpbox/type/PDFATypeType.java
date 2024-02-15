package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

@StructuredType(namespace = "http://www.aiim.org/pdfa/ns/type#", preferedPrefix = "pdfaType")
/* loaded from: classes2.dex */
public class PDFATypeType extends AbstractStructuredType {
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String DESCRIPTION = "description";
    @PropertyType(card = Cardinality.Seq, type = Types.PDFAField)
    public static final String FIELD = "field";
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String NS_URI = "namespaceURI";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String PREFIX = "prefix";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String TYPE = "type";

    public PDFATypeType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public String getNamespaceURI() {
        URIType uRIType = (URIType) getProperty("namespaceURI");
        if (uRIType == null) {
            return null;
        }
        return uRIType.getStringValue();
    }

    public String getType() {
        TextType textType = (TextType) getProperty("type");
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public String getPrefixValue() {
        TextType textType = (TextType) getProperty("prefix");
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public String getDescription() {
        TextType textType = (TextType) getProperty("description");
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public ArrayProperty getFields() {
        return getArrayProperty(FIELD);
    }
}
