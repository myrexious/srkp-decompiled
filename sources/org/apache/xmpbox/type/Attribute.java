package org.apache.xmpbox.type;

import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public class Attribute {
    private String name;
    private String nsURI;
    private String value;

    public Attribute(String str, String str2, String str3) {
        this.nsURI = str;
        this.name = str2;
        this.value = str3;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getNamespace() {
        return this.nsURI;
    }

    public void setNsURI(String str) {
        this.nsURI = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        return "[attr:{" + this.nsURI + StringSubstitutor.DEFAULT_VAR_END + this.name + "=" + this.value + "]";
    }
}
