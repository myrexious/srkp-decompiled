package org.apache.xmpbox.schema;

import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.AbstractField;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.TextType;
import org.apache.xmpbox.type.Types;

@StructuredType(namespace = "http://ns.adobe.com/pdf/1.3/", preferedPrefix = "pdf")
/* loaded from: classes2.dex */
public class AdobePDFSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String KEYWORDS = "Keywords";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String PDF_VERSION = "PDFVersion";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String PRODUCER = "Producer";

    public AdobePDFSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public AdobePDFSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public void setKeywords(String str) {
        addProperty(createTextType(KEYWORDS, str));
    }

    public void setKeywordsProperty(TextType textType) {
        addProperty(textType);
    }

    public void setPDFVersion(String str) {
        addProperty(createTextType(PDF_VERSION, str));
    }

    public void setPDFVersionProperty(TextType textType) {
        addProperty(textType);
    }

    public void setProducer(String str) {
        addProperty(createTextType(PRODUCER, str));
    }

    public void setProducerProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getKeywordsProperty() {
        AbstractField property = getProperty(KEYWORDS);
        if (property instanceof TextType) {
            return (TextType) property;
        }
        return null;
    }

    public String getKeywords() {
        AbstractField property = getProperty(KEYWORDS);
        if (property instanceof TextType) {
            return ((TextType) property).getStringValue();
        }
        return null;
    }

    public TextType getPDFVersionProperty() {
        AbstractField property = getProperty(PDF_VERSION);
        if (property instanceof TextType) {
            return (TextType) property;
        }
        return null;
    }

    public String getPDFVersion() {
        AbstractField property = getProperty(PDF_VERSION);
        if (property instanceof TextType) {
            return ((TextType) property).getStringValue();
        }
        return null;
    }

    public TextType getProducerProperty() {
        AbstractField property = getProperty(PRODUCER);
        if (property instanceof TextType) {
            return (TextType) property;
        }
        return null;
    }

    public String getProducer() {
        AbstractField property = getProperty(PRODUCER);
        if (property instanceof TextType) {
            return ((TextType) property).getStringValue();
        }
        return null;
    }
}
