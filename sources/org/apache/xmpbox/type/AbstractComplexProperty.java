package org.apache.xmpbox.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public abstract class AbstractComplexProperty extends AbstractField {
    private final ComplexPropertyContainer container;
    private final Map<String, String> namespaceToPrefix;

    public AbstractComplexProperty(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
        this.container = new ComplexPropertyContainer();
        this.namespaceToPrefix = new HashMap();
    }

    public void addNamespace(String str, String str2) {
        this.namespaceToPrefix.put(str, str2);
    }

    public String getNamespacePrefix(String str) {
        return this.namespaceToPrefix.get(str);
    }

    public Map<String, String> getAllNamespacesWithPrefix() {
        return this.namespaceToPrefix;
    }

    public final void addProperty(AbstractField abstractField) {
        if (!(this instanceof ArrayProperty)) {
            this.container.removePropertiesByName(abstractField.getPropertyName());
        }
        this.container.addProperty(abstractField);
    }

    public final void removeProperty(AbstractField abstractField) {
        this.container.removeProperty(abstractField);
    }

    public final ComplexPropertyContainer getContainer() {
        return this.container;
    }

    public final List<AbstractField> getAllProperties() {
        return this.container.getAllProperties();
    }

    public final AbstractField getProperty(String str) {
        List<AbstractField> propertiesByLocalName = this.container.getPropertiesByLocalName(str);
        if (propertiesByLocalName == null) {
            return null;
        }
        return propertiesByLocalName.get(0);
    }

    public final ArrayProperty getArrayProperty(String str) {
        List<AbstractField> propertiesByLocalName = this.container.getPropertiesByLocalName(str);
        if (propertiesByLocalName == null) {
            return null;
        }
        return (ArrayProperty) propertiesByLocalName.get(0);
    }

    public final AbstractField getFirstEquivalentProperty(String str, Class<? extends AbstractField> cls) {
        return this.container.getFirstEquivalentProperty(str, cls);
    }
}
