package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

@StructuredType(namespace = "http://www.aiim.org/pdfa/ns/schema#", preferedPrefix = "pdfaSchema")
/* loaded from: classes2.dex */
public class PDFASchemaType extends AbstractStructuredType {
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String NAMESPACE_URI = "namespaceURI";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String PREFIX = "prefix";
    @PropertyType(card = Cardinality.Seq, type = Types.PDFAProperty)
    public static final String PROPERTY = "property";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String SCHEMA = "schema";
    @PropertyType(card = Cardinality.Seq, type = Types.PDFAType)
    public static final String VALUE_TYPE = "valueType";

    public PDFASchemaType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public String getNamespaceURI() {
        URIType uRIType = (URIType) getProperty("namespaceURI");
        if (uRIType == null) {
            return null;
        }
        return uRIType.getStringValue();
    }

    public String getPrefixValue() {
        TextType textType = (TextType) getProperty("prefix");
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public ArrayProperty getProperty() {
        return getArrayProperty(PROPERTY);
    }

    public ArrayProperty getValueType() {
        return getArrayProperty("valueType");
    }
}
