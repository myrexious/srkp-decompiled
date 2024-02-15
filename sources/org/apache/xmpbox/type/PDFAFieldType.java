package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

@StructuredType(namespace = "http://www.aiim.org/pdfa/ns/field#", preferedPrefix = "pdfaField")
/* loaded from: classes2.dex */
public class PDFAFieldType extends AbstractStructuredType {
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String DESCRIPTION = "description";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String NAME = "name";
    @PropertyType(card = Cardinality.Simple, type = Types.Choice)
    public static final String VALUETYPE = "valueType";

    public PDFAFieldType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public String getName() {
        TextType textType = (TextType) getProperty("name");
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public String getValueType() {
        TextType textType = (TextType) getProperty("valueType");
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
}
