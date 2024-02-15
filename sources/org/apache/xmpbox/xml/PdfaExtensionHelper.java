package org.apache.xmpbox.xml;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.util.List;
import java.util.Map;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.PDFAExtensionSchema;
import org.apache.xmpbox.schema.XMPSchema;
import org.apache.xmpbox.schema.XMPSchemaFactory;
import org.apache.xmpbox.type.AbstractField;
import org.apache.xmpbox.type.AbstractStructuredType;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.DefinedStructuredType;
import org.apache.xmpbox.type.PDFAFieldType;
import org.apache.xmpbox.type.PDFAPropertyType;
import org.apache.xmpbox.type.PDFASchemaType;
import org.apache.xmpbox.type.PDFATypeType;
import org.apache.xmpbox.type.PropertiesDescription;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.TypeMapping;
import org.apache.xmpbox.type.Types;
import org.apache.xmpbox.xml.XmpParsingException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/* loaded from: classes2.dex */
public final class PdfaExtensionHelper {
    public static final String CLOSED_CHOICE = "closed Choice of ";
    public static final String OPEN_CHOICE = "open Choice of ";

    private PdfaExtensionHelper() {
    }

    public static void validateNaming(XMPMetadata xMPMetadata, Element element) throws XmpParsingException {
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Attr attr = (Attr) attributes.item(i);
            checkNamespaceDeclaration(attr, PDFAExtensionSchema.class);
            checkNamespaceDeclaration(attr, PDFAFieldType.class);
            checkNamespaceDeclaration(attr, PDFAPropertyType.class);
            checkNamespaceDeclaration(attr, PDFASchemaType.class);
            checkNamespaceDeclaration(attr, PDFATypeType.class);
        }
    }

    private static void checkNamespaceDeclaration(Attr attr, Class<? extends AbstractStructuredType> cls) throws XmpParsingException {
        String localName = attr.getLocalName();
        String value = attr.getValue();
        String preferedPrefix = ((StructuredType) cls.getAnnotation(StructuredType.class)).preferedPrefix();
        String namespace = ((StructuredType) cls.getAnnotation(StructuredType.class)).namespace();
        if (preferedPrefix.equals(localName) && !namespace.equals(value)) {
            throw new XmpParsingException(XmpParsingException.ErrorType.InvalidPdfaSchema, "Invalid PDF/A namespace definition, prefix: " + localName + ", namespace: " + value);
        }
        if (namespace.equals(value) && !preferedPrefix.equals(localName)) {
            throw new XmpParsingException(XmpParsingException.ErrorType.InvalidPdfaSchema, "Invalid PDF/A namespace definition, prefix: " + localName + ", namespace: " + value);
        }
    }

    public static void populateSchemaMapping(XMPMetadata xMPMetadata) throws XmpParsingException {
        List<XMPSchema> allSchemas = xMPMetadata.getAllSchemas();
        TypeMapping typeMapping = xMPMetadata.getTypeMapping();
        StructuredType structuredType = (StructuredType) PDFAExtensionSchema.class.getAnnotation(StructuredType.class);
        for (XMPSchema xMPSchema : allSchemas) {
            if (xMPSchema.getNamespace().equals(structuredType.namespace())) {
                if (!xMPSchema.getPrefix().equals(structuredType.preferedPrefix())) {
                    throw new XmpParsingException(XmpParsingException.ErrorType.InvalidPrefix, "Found invalid prefix for PDF/A extension, found '" + xMPSchema.getPrefix() + "', should be '" + structuredType.preferedPrefix() + OperatorName.SHOW_TEXT_LINE);
                }
                for (AbstractField abstractField : ((PDFAExtensionSchema) xMPSchema).getSchemasProperty().getAllProperties()) {
                    if (abstractField instanceof PDFASchemaType) {
                        populatePDFASchemaType(xMPMetadata, (PDFASchemaType) abstractField, typeMapping);
                    }
                }
            }
        }
    }

    private static void populatePDFASchemaType(XMPMetadata xMPMetadata, PDFASchemaType pDFASchemaType, TypeMapping typeMapping) throws XmpParsingException {
        String trim = pDFASchemaType.getNamespaceURI().trim();
        String prefixValue = pDFASchemaType.getPrefixValue();
        ArrayProperty property = pDFASchemaType.getProperty();
        ArrayProperty valueType = pDFASchemaType.getValueType();
        XMPSchemaFactory schemaFactory = typeMapping.getSchemaFactory(trim);
        if (schemaFactory == null) {
            typeMapping.addNewNameSpace(trim, prefixValue);
            schemaFactory = typeMapping.getSchemaFactory(trim);
        }
        if (valueType != null) {
            for (AbstractField abstractField : valueType.getAllProperties()) {
                if (abstractField instanceof PDFATypeType) {
                    populatePDFAType(xMPMetadata, (PDFATypeType) abstractField, typeMapping);
                }
            }
        }
        if (property == null) {
            throw new XmpParsingException(XmpParsingException.ErrorType.RequiredProperty, "Missing pdfaSchema:property in type definition");
        }
        for (AbstractField abstractField2 : property.getAllProperties()) {
            if (abstractField2 instanceof PDFAPropertyType) {
                populatePDFAPropertyType((PDFAPropertyType) abstractField2, typeMapping, schemaFactory);
            }
        }
    }

    private static void populatePDFAPropertyType(PDFAPropertyType pDFAPropertyType, TypeMapping typeMapping, XMPSchemaFactory xMPSchemaFactory) throws XmpParsingException {
        String name = pDFAPropertyType.getName();
        String valueType = pDFAPropertyType.getValueType();
        String description = pDFAPropertyType.getDescription();
        String category = pDFAPropertyType.getCategory();
        if (name == null || valueType == null || description == null || category == null) {
            throw new XmpParsingException(XmpParsingException.ErrorType.RequiredProperty, "Missing field in property definition");
        }
        PropertyType transformValueType = transformValueType(typeMapping, valueType);
        if (transformValueType == null) {
            throw new XmpParsingException(XmpParsingException.ErrorType.NoValueType, "Unknown property value type : " + valueType);
        }
        if (transformValueType.type() == null) {
            throw new XmpParsingException(XmpParsingException.ErrorType.NoValueType, "Type not defined : " + valueType);
        }
        if (transformValueType.type().isSimple() || transformValueType.type().isStructured() || transformValueType.type() == Types.DefinedType) {
            xMPSchemaFactory.getPropertyDefinition().addNewProperty(name, transformValueType);
            return;
        }
        throw new XmpParsingException(XmpParsingException.ErrorType.NoValueType, "Type not defined : " + valueType);
    }

    private static void populatePDFAType(XMPMetadata xMPMetadata, PDFATypeType pDFATypeType, TypeMapping typeMapping) throws XmpParsingException {
        String type = pDFATypeType.getType();
        String namespaceURI = pDFATypeType.getNamespaceURI();
        String prefixValue = pDFATypeType.getPrefixValue();
        String description = pDFATypeType.getDescription();
        ArrayProperty fields = pDFATypeType.getFields();
        if (type == null || namespaceURI == null || prefixValue == null || description == null) {
            throw new XmpParsingException(XmpParsingException.ErrorType.RequiredProperty, "Missing field in type definition");
        }
        DefinedStructuredType definedStructuredType = new DefinedStructuredType(xMPMetadata, namespaceURI, prefixValue, null);
        if (fields != null) {
            for (AbstractField abstractField : fields.getAllProperties()) {
                if (abstractField instanceof PDFAFieldType) {
                    populatePDFAFieldType((PDFAFieldType) abstractField, definedStructuredType);
                }
            }
        }
        PropertiesDescription propertiesDescription = new PropertiesDescription();
        for (Map.Entry<String, PropertyType> entry : definedStructuredType.getDefinedProperties().entrySet()) {
            propertiesDescription.addNewProperty(entry.getKey(), entry.getValue());
        }
        typeMapping.addToDefinedStructuredTypes(type, namespaceURI, propertiesDescription);
    }

    private static void populatePDFAFieldType(PDFAFieldType pDFAFieldType, DefinedStructuredType definedStructuredType) throws XmpParsingException {
        String name = pDFAFieldType.getName();
        String description = pDFAFieldType.getDescription();
        String valueType = pDFAFieldType.getValueType();
        if (name == null || description == null || valueType == null) {
            throw new XmpParsingException(XmpParsingException.ErrorType.RequiredProperty, "Missing field in field definition");
        }
        try {
            definedStructuredType.addProperty(name, TypeMapping.createPropertyType(Types.valueOf(valueType), Cardinality.Simple));
        } catch (IllegalArgumentException e) {
            throw new XmpParsingException(XmpParsingException.ErrorType.NoValueType, "Type not defined : " + valueType, e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [org.apache.xmpbox.type.Types] */
    /* JADX WARN: Type inference failed for: r2v3, types: [org.apache.xmpbox.type.Types] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r4v4, types: [org.apache.xmpbox.type.Types] */
    /* JADX WARN: Type inference failed for: r4v6, types: [org.apache.xmpbox.type.Types] */
    private static PropertyType transformValueType(TypeMapping typeMapping, String str) {
        if ("Lang Alt".equals(str)) {
            return TypeMapping.createPropertyType(Types.LangAlt, Cardinality.Simple);
        }
        if (str.startsWith(CLOSED_CHOICE)) {
            str = str.substring(17);
        } else if (str.startsWith(OPEN_CHOICE)) {
            str = str.substring(15);
        }
        int indexOf = str.indexOf(32);
        Cardinality cardinality = Cardinality.Simple;
        ?? r2 = null;
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            if ("seq".equals(substring)) {
                cardinality = Cardinality.Seq;
            } else if ("bag".equals(substring)) {
                cardinality = Cardinality.Bag;
            } else if (!"alt".equals(substring)) {
                return null;
            } else {
                cardinality = Cardinality.Alt;
            }
        }
        String substring2 = str.substring(indexOf + 1);
        try {
            typeMapping = indexOf < 0 ? Types.valueOf(str) : Types.valueOf(substring2);
            r2 = typeMapping;
        } catch (IllegalArgumentException unused) {
            if (typeMapping.isDefinedType(substring2)) {
                r2 = Types.DefinedType;
            }
        }
        return TypeMapping.createPropertyType(r2, cardinality);
    }
}
