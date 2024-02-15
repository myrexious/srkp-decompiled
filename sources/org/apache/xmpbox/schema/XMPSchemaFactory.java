package org.apache.xmpbox.schema;

import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.PropertiesDescription;
import org.apache.xmpbox.type.PropertyType;

/* loaded from: classes2.dex */
public class XMPSchemaFactory {
    private final String namespace;
    private String nsName;
    private final PropertiesDescription propDef;
    private final Class<? extends XMPSchema> schemaClass;

    public XMPSchemaFactory(String str, Class<? extends XMPSchema> cls, PropertiesDescription propertiesDescription) {
        this.namespace = str;
        this.schemaClass = cls;
        this.propDef = propertiesDescription;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public PropertyType getPropertyType(String str) {
        return this.propDef.getPropertyType(str);
    }

    public XMPSchema createXMPSchema(XMPMetadata xMPMetadata, String str) throws XmpSchemaException {
        Class<?>[] clsArr;
        Object[] objArr;
        if (this.schemaClass == XMPSchema.class) {
            clsArr = new Class[]{XMPMetadata.class, String.class, String.class};
            objArr = new Object[]{xMPMetadata, this.namespace, this.nsName};
        } else if (str == null || "".equals(str)) {
            clsArr = new Class[]{XMPMetadata.class};
            objArr = new Object[]{xMPMetadata};
        } else {
            clsArr = new Class[]{XMPMetadata.class, String.class};
            objArr = new Object[]{xMPMetadata, str};
        }
        try {
            XMPSchema newInstance = this.schemaClass.getDeclaredConstructor(clsArr).newInstance(objArr);
            xMPMetadata.addSchema(newInstance);
            return newInstance;
        } catch (Exception e) {
            throw new XmpSchemaException("Cannot instantiate specified object schema", e);
        }
    }

    public PropertiesDescription getPropertyDefinition() {
        return this.propDef;
    }
}
