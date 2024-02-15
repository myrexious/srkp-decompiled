package org.apache.xmpbox.schema;

import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.AbstractField;
import org.apache.xmpbox.type.Attribute;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.IntegerType;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.TextType;
import org.apache.xmpbox.type.Types;

@StructuredType(namespace = "http://www.aiim.org/pdfa/ns/id/", preferedPrefix = "pdfaid")
/* loaded from: classes2.dex */
public class PDFAIdentificationSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String AMD = "amd";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String CONFORMANCE = "conformance";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String PART = "part";

    public PDFAIdentificationSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public PDFAIdentificationSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public void setPartValueWithString(String str) {
        addProperty((IntegerType) instanciateSimple(PART, str));
    }

    public void setPartValueWithInt(int i) {
        addProperty((IntegerType) instanciateSimple(PART, Integer.valueOf(i)));
    }

    public void setPart(Integer num) {
        setPartValueWithInt(num.intValue());
    }

    public void setPartProperty(IntegerType integerType) {
        addProperty(integerType);
    }

    public void setAmd(String str) {
        addProperty(createTextType(AMD, str));
    }

    public void setAmdProperty(TextType textType) {
        addProperty(textType);
    }

    public void setConformance(String str) throws BadFieldValueException {
        if (str.equals("A") || str.equals("B") || str.equals("U")) {
            addProperty(createTextType(CONFORMANCE, str));
            return;
        }
        throw new BadFieldValueException("The property given not seems to be a PDF/A conformance level (must be A, B or U)");
    }

    public void setConformanceProperty(TextType textType) throws BadFieldValueException {
        String stringValue = textType.getStringValue();
        if (stringValue.equals("A") || stringValue.equals("B") || stringValue.equals("U")) {
            addProperty(textType);
            return;
        }
        throw new BadFieldValueException("The property given not seems to be a PDF/A conformance level (must be A, B or U)");
    }

    public Integer getPart() {
        IntegerType partProperty = getPartProperty();
        if (partProperty == null) {
            return null;
        }
        return partProperty.getValue();
    }

    public IntegerType getPartProperty() {
        AbstractField property = getProperty(PART);
        if (property instanceof IntegerType) {
            return (IntegerType) property;
        }
        return null;
    }

    public String getAmendment() {
        AbstractField property = getProperty(AMD);
        if (property instanceof TextType) {
            return ((TextType) property).getStringValue();
        }
        return null;
    }

    public TextType getAmdProperty() {
        AbstractField property = getProperty(AMD);
        if (property instanceof TextType) {
            return (TextType) property;
        }
        return null;
    }

    public String getAmd() {
        TextType amdProperty = getAmdProperty();
        if (amdProperty == null) {
            for (Attribute attribute : getAllAttributes()) {
                if (attribute.getName().equals(AMD)) {
                    return attribute.getValue();
                }
            }
            return null;
        }
        return amdProperty.getStringValue();
    }

    public TextType getConformanceProperty() {
        AbstractField property = getProperty(CONFORMANCE);
        if (property instanceof TextType) {
            return (TextType) property;
        }
        return null;
    }

    public String getConformance() {
        TextType conformanceProperty = getConformanceProperty();
        if (conformanceProperty == null) {
            for (Attribute attribute : getAllAttributes()) {
                if (attribute.getName().equals(CONFORMANCE)) {
                    return attribute.getValue();
                }
            }
            return null;
        }
        return conformanceProperty.getStringValue();
    }
}
