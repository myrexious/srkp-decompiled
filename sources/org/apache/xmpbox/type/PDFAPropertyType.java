package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

@StructuredType(namespace = "http://www.aiim.org/pdfa/ns/property#", preferedPrefix = "pdfaProperty")
/* loaded from: classes2.dex */
public class PDFAPropertyType extends AbstractStructuredType {
    @PropertyType(card = Cardinality.Simple, type = Types.Choice)
    public static final String CATEGORY = "category";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String DESCRIPTION = "description";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String NAME = "name";
    @PropertyType(card = Cardinality.Simple, type = Types.Choice)
    public static final String VALUETYPE = "valueType";

    public PDFAPropertyType(XMPMetadata xMPMetadata) {
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
        ChoiceType choiceType = (ChoiceType) getProperty("valueType");
        if (choiceType == null) {
            return null;
        }
        return choiceType.getStringValue();
    }

    public String getDescription() {
        TextType textType = (TextType) getProperty("description");
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public String getCategory() {
        ChoiceType choiceType = (ChoiceType) getProperty(CATEGORY);
        if (choiceType == null) {
            return null;
        }
        return choiceType.getStringValue();
    }
}
