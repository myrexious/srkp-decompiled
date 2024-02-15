package org.apache.xmpbox.type;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ComplexPropertyContainer {
    private final List<AbstractField> properties = new ArrayList();

    public AbstractField getFirstEquivalentProperty(String str, Class<? extends AbstractField> cls) {
        List<AbstractField> propertiesByLocalName = getPropertiesByLocalName(str);
        if (propertiesByLocalName != null) {
            for (AbstractField abstractField : propertiesByLocalName) {
                if (abstractField.getClass().equals(cls)) {
                    return abstractField;
                }
            }
            return null;
        }
        return null;
    }

    public void addProperty(AbstractField abstractField) {
        removeProperty(abstractField);
        this.properties.add(abstractField);
    }

    public List<AbstractField> getAllProperties() {
        return this.properties;
    }

    public List<AbstractField> getPropertiesByLocalName(String str) {
        List<AbstractField> allProperties = getAllProperties();
        if (allProperties != null) {
            ArrayList arrayList = new ArrayList();
            for (AbstractField abstractField : allProperties) {
                if (abstractField.getPropertyName().equals(str)) {
                    arrayList.add(abstractField);
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }
        return null;
    }

    public boolean isSameProperty(AbstractField abstractField, AbstractField abstractField2) {
        if (abstractField.getClass().equals(abstractField2.getClass())) {
            String propertyName = abstractField.getPropertyName();
            String propertyName2 = abstractField2.getPropertyName();
            if (propertyName == null) {
                return propertyName2 == null;
            } else if (propertyName.equals(propertyName2)) {
                return abstractField.equals(abstractField2);
            }
        }
        return false;
    }

    public boolean containsProperty(AbstractField abstractField) {
        for (AbstractField abstractField2 : getAllProperties()) {
            if (isSameProperty(abstractField2, abstractField)) {
                return true;
            }
        }
        return false;
    }

    public void removeProperty(AbstractField abstractField) {
        this.properties.remove(abstractField);
    }

    public void removePropertiesByName(String str) {
        List<AbstractField> propertiesByLocalName;
        if (this.properties.isEmpty() || (propertiesByLocalName = getPropertiesByLocalName(str)) == null) {
            return;
        }
        for (AbstractField abstractField : propertiesByLocalName) {
            this.properties.remove(abstractField);
        }
    }
}
