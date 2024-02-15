package org.apache.xmpbox.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class PropertiesDescription {
    private final Map<String, PropertyType> types = new HashMap();

    public List<String> getPropertiesName() {
        return new ArrayList(this.types.keySet());
    }

    public void addNewProperty(String str, PropertyType propertyType) {
        this.types.put(str, propertyType);
    }

    public PropertyType getPropertyType(String str) {
        return this.types.get(str);
    }
}
