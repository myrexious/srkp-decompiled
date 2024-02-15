package org.apache.xmpbox.type;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.ExifSchema;
import org.apache.xmpbox.schema.PDFAExtensionSchema;
import org.apache.xmpbox.schema.PDFAIdentificationSchema;
import org.apache.xmpbox.schema.PhotoshopSchema;
import org.apache.xmpbox.schema.TiffSchema;
import org.apache.xmpbox.schema.XMPBasicJobTicketSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.schema.XMPMediaManagementSchema;
import org.apache.xmpbox.schema.XMPRightsManagementSchema;
import org.apache.xmpbox.schema.XMPSchema;
import org.apache.xmpbox.schema.XMPSchemaFactory;
import org.apache.xmpbox.schema.XMPageTextSchema;
import org.apache.xmpbox.schema.XmpSchemaException;

/* loaded from: classes2.dex */
public final class TypeMapping {
    private static final Class<?>[] SIMPLEPROPERTYCONSTPARAMS = {XMPMetadata.class, String.class, String.class, String.class, Object.class};
    private Map<String, PropertiesDescription> definedStructuredMappings;
    private Map<String, String> definedStructuredNamespaces;
    private final XMPMetadata metadata;
    private Map<String, XMPSchemaFactory> schemaMap;
    private Map<Types, PropertiesDescription> structuredMappings;
    private Map<String, Types> structuredNamespaces;

    public TypeMapping(XMPMetadata xMPMetadata) {
        this.metadata = xMPMetadata;
        initialize();
    }

    private void initialize() {
        Types[] values;
        this.structuredMappings = new EnumMap(Types.class);
        this.structuredNamespaces = new HashMap();
        for (Types types : Types.values()) {
            if (types.isStructured()) {
                Class<?> asSubclass = types.getImplementingClass().asSubclass(AbstractStructuredType.class);
                String namespace = ((StructuredType) asSubclass.getAnnotation(StructuredType.class)).namespace();
                PropertiesDescription initializePropMapping = initializePropMapping(asSubclass);
                this.structuredNamespaces.put(namespace, types);
                this.structuredMappings.put(types, initializePropMapping);
            }
        }
        this.definedStructuredNamespaces = new HashMap();
        this.definedStructuredMappings = new HashMap();
        this.schemaMap = new HashMap();
        addNameSpace(XMPBasicSchema.class);
        addNameSpace(DublinCoreSchema.class);
        addNameSpace(PDFAExtensionSchema.class);
        addNameSpace(XMPMediaManagementSchema.class);
        addNameSpace(AdobePDFSchema.class);
        addNameSpace(PDFAIdentificationSchema.class);
        addNameSpace(XMPRightsManagementSchema.class);
        addNameSpace(PhotoshopSchema.class);
        addNameSpace(XMPBasicJobTicketSchema.class);
        addNameSpace(ExifSchema.class);
        addNameSpace(TiffSchema.class);
        addNameSpace(XMPageTextSchema.class);
    }

    public void addToDefinedStructuredTypes(String str, String str2, PropertiesDescription propertiesDescription) {
        this.definedStructuredNamespaces.put(str2, str);
        this.definedStructuredMappings.put(str, propertiesDescription);
    }

    public PropertiesDescription getDefinedDescriptionByNamespace(String str) {
        return this.definedStructuredMappings.get(this.definedStructuredNamespaces.get(str));
    }

    public AbstractStructuredType instanciateStructuredType(Types types, String str) throws BadFieldValueException {
        try {
            AbstractStructuredType abstractStructuredType = (AbstractStructuredType) types.getImplementingClass().asSubclass(AbstractStructuredType.class).getDeclaredConstructor(XMPMetadata.class).newInstance(this.metadata);
            abstractStructuredType.setPropertyName(str);
            return abstractStructuredType;
        } catch (IllegalAccessException e) {
            throw new BadFieldValueException("Failed to instantiate structured type : " + types, e);
        } catch (IllegalArgumentException e2) {
            throw new BadFieldValueException("Failed to instantiate structured type : " + types, e2);
        } catch (InstantiationException e3) {
            throw new BadFieldValueException("Failed to instantiate structured type : " + types, e3);
        } catch (NoSuchMethodException e4) {
            throw new BadFieldValueException("Failed to instantiate structured type : " + types, e4);
        } catch (SecurityException e5) {
            throw new BadFieldValueException("Failed to instantiate structured type : " + types, e5);
        } catch (InvocationTargetException e6) {
            throw new BadFieldValueException("Failed to instantiate structured type : " + types, e6);
        }
    }

    public AbstractStructuredType instanciateDefinedType(String str, String str2) {
        return new DefinedStructuredType(this.metadata, str2, null, str);
    }

    public AbstractSimpleProperty instanciateSimpleProperty(String str, String str2, String str3, Object obj, Types types) {
        Object[] objArr = {this.metadata, str, str2, str3, obj};
        Class<? extends U> asSubclass = types.getImplementingClass().asSubclass(AbstractSimpleProperty.class);
        try {
            return (AbstractSimpleProperty) asSubclass.getDeclaredConstructor(SIMPLEPROPERTYCONSTPARAMS).newInstance(objArr);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to instantiate " + asSubclass.getSimpleName() + " property with value " + obj, e);
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException("Failed to instantiate " + asSubclass.getSimpleName() + " property with value " + obj, e2);
        } catch (InstantiationException e3) {
            throw new IllegalArgumentException("Failed to instantiate " + asSubclass.getSimpleName() + " property with value " + obj, e3);
        } catch (NoSuchMethodError e4) {
            throw new IllegalArgumentException("Failed to instantiate " + asSubclass.getSimpleName() + " property with value " + obj, e4);
        } catch (NoSuchMethodException e5) {
            throw new IllegalArgumentException("Failed to instantiate " + asSubclass.getSimpleName() + " property with value " + obj, e5);
        } catch (SecurityException e6) {
            throw new IllegalArgumentException("Failed to instantiate " + asSubclass.getSimpleName() + " property with value " + obj, e6);
        } catch (InvocationTargetException e7) {
            throw new IllegalArgumentException("Failed to instantiate " + asSubclass.getSimpleName() + " property with value " + obj, e7);
        }
    }

    public AbstractSimpleProperty instanciateSimpleField(Class<?> cls, String str, String str2, String str3, Object obj) {
        return instanciateSimpleProperty(str, str2, str3, obj, initializePropMapping(cls).getPropertyType(str3).type());
    }

    public boolean isStructuredTypeNamespace(String str) {
        return this.structuredNamespaces.containsKey(str);
    }

    public boolean isDefinedTypeNamespace(String str) {
        return this.definedStructuredNamespaces.containsKey(str);
    }

    public boolean isDefinedType(String str) {
        return this.definedStructuredMappings.containsKey(str);
    }

    private void addNameSpace(Class<? extends XMPSchema> cls) {
        String namespace = ((StructuredType) cls.getAnnotation(StructuredType.class)).namespace();
        this.schemaMap.put(namespace, new XMPSchemaFactory(namespace, cls, initializePropMapping(cls)));
    }

    public void addNewNameSpace(String str, String str2) {
        this.schemaMap.put(str, new XMPSchemaFactory(str, XMPSchema.class, new PropertiesDescription()));
    }

    public PropertiesDescription getStructuredPropMapping(Types types) {
        return this.structuredMappings.get(types);
    }

    public XMPSchema getAssociatedSchemaObject(XMPMetadata xMPMetadata, String str, String str2) throws XmpSchemaException {
        if (this.schemaMap.containsKey(str)) {
            return this.schemaMap.get(str).createXMPSchema(xMPMetadata, str2);
        }
        XMPSchemaFactory schemaFactory = getSchemaFactory(str);
        if (schemaFactory != null) {
            return schemaFactory.createXMPSchema(xMPMetadata, str2);
        }
        return null;
    }

    public XMPSchemaFactory getSchemaFactory(String str) {
        return this.schemaMap.get(str);
    }

    public boolean isDefinedSchema(String str) {
        return this.schemaMap.containsKey(str);
    }

    public boolean isDefinedNamespace(String str) {
        return isDefinedSchema(str) || isStructuredTypeNamespace(str) || isDefinedTypeNamespace(str);
    }

    public PropertyType getSpecifiedPropertyType(QName qName) throws BadFieldValueException {
        XMPSchemaFactory schemaFactory = getSchemaFactory(qName.getNamespaceURI());
        if (schemaFactory != null) {
            return schemaFactory.getPropertyType(qName.getLocalPart());
        }
        Types types = this.structuredNamespaces.get(qName.getNamespaceURI());
        if (types != null) {
            return createPropertyType(types, Cardinality.Simple);
        }
        if (this.definedStructuredNamespaces.get(qName.getNamespaceURI()) == null) {
            throw new BadFieldValueException("No descriptor found for " + qName);
        }
        return createPropertyType(Types.DefinedType, Cardinality.Simple);
    }

    public PropertiesDescription initializePropMapping(Class<?> cls) {
        Field[] fields;
        PropertiesDescription propertiesDescription = new PropertiesDescription();
        String str = null;
        for (Field field : cls.getFields()) {
            if (field.isAnnotationPresent(PropertyType.class)) {
                try {
                    str = (String) field.get(str);
                    propertiesDescription.addNewProperty(str, (PropertyType) field.getAnnotation(PropertyType.class));
                } catch (Exception e) {
                    throw new IllegalArgumentException("couldn't read one type declaration, please check accessibility and declaration of fields annotated in " + cls.getName(), e);
                }
            }
        }
        return propertiesDescription;
    }

    public BooleanType createBoolean(String str, String str2, String str3, boolean z) {
        return new BooleanType(this.metadata, str, str2, str3, Boolean.valueOf(z));
    }

    public DateType createDate(String str, String str2, String str3, Calendar calendar) {
        return new DateType(this.metadata, str, str2, str3, calendar);
    }

    public IntegerType createInteger(String str, String str2, String str3, int i) {
        return new IntegerType(this.metadata, str, str2, str3, Integer.valueOf(i));
    }

    public RealType createReal(String str, String str2, String str3, float f) {
        return new RealType(this.metadata, str, str2, str3, Float.valueOf(f));
    }

    public TextType createText(String str, String str2, String str3, String str4) {
        return new TextType(this.metadata, str, str2, str3, str4);
    }

    public ProperNameType createProperName(String str, String str2, String str3, String str4) {
        return new ProperNameType(this.metadata, str, str2, str3, str4);
    }

    public URIType createURI(String str, String str2, String str3, String str4) {
        return new URIType(this.metadata, str, str2, str3, str4);
    }

    public URLType createURL(String str, String str2, String str3, String str4) {
        return new URLType(this.metadata, str, str2, str3, str4);
    }

    public RenditionClassType createRenditionClass(String str, String str2, String str3, String str4) {
        return new RenditionClassType(this.metadata, str, str2, str3, str4);
    }

    public PartType createPart(String str, String str2, String str3, String str4) {
        return new PartType(this.metadata, str, str2, str3, str4);
    }

    public MIMEType createMIMEType(String str, String str2, String str3, String str4) {
        return new MIMEType(this.metadata, str, str2, str3, str4);
    }

    public LocaleType createLocale(String str, String str2, String str3, String str4) {
        return new LocaleType(this.metadata, str, str2, str3, str4);
    }

    public GUIDType createGUID(String str, String str2, String str3, String str4) {
        return new GUIDType(this.metadata, str, str2, str3, str4);
    }

    public ChoiceType createChoice(String str, String str2, String str3, String str4) {
        return new ChoiceType(this.metadata, str, str2, str3, str4);
    }

    public AgentNameType createAgentName(String str, String str2, String str3, String str4) {
        return new AgentNameType(this.metadata, str, str2, str3, str4);
    }

    public XPathType createXPath(String str, String str2, String str3, String str4) {
        return new XPathType(this.metadata, str, str2, str3, str4);
    }

    public ArrayProperty createArrayProperty(String str, String str2, String str3, Cardinality cardinality) {
        return new ArrayProperty(this.metadata, str, str2, str3, cardinality);
    }

    public static PropertyType createPropertyType(final Types types, final Cardinality cardinality) {
        return new PropertyType() { // from class: org.apache.xmpbox.type.TypeMapping.1
            @Override // java.lang.annotation.Annotation
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override // org.apache.xmpbox.type.PropertyType
            public Types type() {
                return types;
            }

            @Override // org.apache.xmpbox.type.PropertyType
            public Cardinality card() {
                return cardinality;
            }
        };
    }
}
