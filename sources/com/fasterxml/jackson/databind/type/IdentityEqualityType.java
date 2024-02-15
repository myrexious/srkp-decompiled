package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

/* loaded from: classes.dex */
public abstract class IdentityEqualityType extends TypeBase {
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JavaType
    public final boolean equals(Object obj) {
        return obj == this;
    }

    public IdentityEqualityType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, int i, Object obj, Object obj2, boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, i, obj, obj2, z);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public final int hashCode() {
        return System.identityHashCode(this);
    }
}
