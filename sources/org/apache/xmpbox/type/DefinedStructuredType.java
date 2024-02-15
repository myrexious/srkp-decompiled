package org.apache.xmpbox.type;

import java.util.HashMap;
import java.util.Map;
import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public class DefinedStructuredType extends AbstractStructuredType {
    private Map<String, PropertyType> definedProperties;

    public DefinedStructuredType(XMPMetadata xMPMetadata, String str, String str2, String str3) {
        super(xMPMetadata, str, str2, str3);
        this.definedProperties = null;
        this.definedProperties = new HashMap();
    }

    public DefinedStructuredType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
        this.definedProperties = null;
        this.definedProperties = new HashMap();
    }

    public void addProperty(String str, PropertyType propertyType) {
        this.definedProperties.put(str, propertyType);
    }

    public Map<String, PropertyType> getDefinedProperties() {
        return this.definedProperties;
    }
}
