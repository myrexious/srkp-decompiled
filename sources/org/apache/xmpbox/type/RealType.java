package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public class RealType extends AbstractSimpleProperty {
    private float realValue;

    public RealType(XMPMetadata xMPMetadata, String str, String str2, String str3, Object obj) {
        super(xMPMetadata, str, str2, str3, obj);
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public Float getValue() {
        return Float.valueOf(this.realValue);
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public void setValue(Object obj) {
        if (obj instanceof Float) {
            this.realValue = ((Float) obj).floatValue();
        } else if (obj instanceof String) {
            this.realValue = Float.valueOf((String) obj).floatValue();
        } else {
            throw new IllegalArgumentException("Value given is not allowed for the Real type: " + obj);
        }
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public String getStringValue() {
        return Float.toString(this.realValue);
    }
}
