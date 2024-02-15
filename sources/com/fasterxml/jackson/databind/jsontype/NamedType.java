package com.fasterxml.jackson.databind.jsontype;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes.dex */
public final class NamedType implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<?> _class;
    protected final int _hashCode;
    protected String _name;

    public NamedType(Class<?> cls) {
        this(cls, null);
    }

    public NamedType(Class<?> cls, String str) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode() + (str == null ? 0 : str.hashCode());
        setName(str);
    }

    public Class<?> getType() {
        return this._class;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String str) {
        this._name = (str == null || str.isEmpty()) ? null : null;
    }

    public boolean hasName() {
        return this._name != null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            NamedType namedType = (NamedType) obj;
            return this._class == namedType._class && Objects.equals(this._name, namedType._name);
        }
        return false;
    }

    public int hashCode() {
        return this._hashCode;
    }

    public String toString() {
        return "[NamedType, class " + this._class.getName() + ", name: " + (this._name == null ? "null" : OperatorName.SHOW_TEXT_LINE + this._name + OperatorName.SHOW_TEXT_LINE) + "]";
    }
}
