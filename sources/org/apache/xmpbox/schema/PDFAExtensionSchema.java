package org.apache.xmpbox.schema;

import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.Types;

@StructuredType(namespace = "http://www.aiim.org/pdfa/ns/extension/", preferedPrefix = "pdfaExtension")
/* loaded from: classes2.dex */
public class PDFAExtensionSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Bag, type = Types.PDFASchema)
    public static final String SCHEMAS = "schemas";

    public PDFAExtensionSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public PDFAExtensionSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public ArrayProperty getSchemasProperty() {
        return (ArrayProperty) getProperty(SCHEMAS);
    }
}
