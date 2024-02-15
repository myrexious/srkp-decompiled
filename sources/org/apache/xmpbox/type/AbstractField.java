package org.apache.xmpbox.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public abstract class AbstractField {
    private final Map<String, Attribute> attributes = new HashMap();
    private final XMPMetadata metadata;
    private String propertyName;

    public abstract String getNamespace();

    public abstract String getPrefix();

    public AbstractField(XMPMetadata xMPMetadata, String str) {
        this.metadata = xMPMetadata;
        this.propertyName = str;
    }

    public final String getPropertyName() {
        return this.propertyName;
    }

    public final void setPropertyName(String str) {
        this.propertyName = str;
    }

    public final void setAttribute(Attribute attribute) {
        this.attributes.put(attribute.getName(), attribute);
    }

    public final boolean containsAttribute(String str) {
        return this.attributes.containsKey(str);
    }

    public final Attribute getAttribute(String str) {
        return this.attributes.get(str);
    }

    public final List<Attribute> getAllAttributes() {
        return new ArrayList(this.attributes.values());
    }

    public final void removeAttribute(String str) {
        this.attributes.remove(str);
    }

    public final XMPMetadata getMetadata() {
        return this.metadata;
    }
}
