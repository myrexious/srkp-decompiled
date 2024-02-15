package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public abstract class AbstractSimpleProperty extends AbstractField {
    private final String namespace;
    private final String prefix;
    private final Object rawValue;

    public abstract String getStringValue();

    public abstract Object getValue();

    public abstract void setValue(Object obj);

    public AbstractSimpleProperty(XMPMetadata xMPMetadata, String str, String str2, String str3, Object obj) {
        super(xMPMetadata, str3);
        setValue(obj);
        this.namespace = str;
        this.prefix = str2;
        this.rawValue = obj;
    }

    public Object getRawValue() {
        return this.rawValue;
    }

    public String toString() {
        return "[" + getClass().getSimpleName() + ":" + getStringValue() + "]";
    }

    @Override // org.apache.xmpbox.type.AbstractField
    public final String getNamespace() {
        return this.namespace;
    }

    @Override // org.apache.xmpbox.type.AbstractField
    public String getPrefix() {
        return this.prefix;
    }
}
