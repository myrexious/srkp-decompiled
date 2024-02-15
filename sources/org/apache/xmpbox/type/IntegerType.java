package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public class IntegerType extends AbstractSimpleProperty {
    private int integerValue;

    public IntegerType(XMPMetadata xMPMetadata, String str, String str2, String str3, Object obj) {
        super(xMPMetadata, str, str2, str3, obj);
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public Integer getValue() {
        return Integer.valueOf(this.integerValue);
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public void setValue(Object obj) {
        if (obj instanceof Integer) {
            this.integerValue = ((Integer) obj).intValue();
        } else if (obj instanceof String) {
            this.integerValue = Integer.parseInt((String) obj);
        } else {
            throw new IllegalArgumentException("Value given is not allowed for the Integer type: " + obj);
        }
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public String getStringValue() {
        return Integer.toString(this.integerValue);
    }
}
