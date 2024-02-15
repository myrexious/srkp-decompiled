package org.apache.commons.lang3.builder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class IDKey {

    /* renamed from: id */
    private final int f35id;
    private final Object value;

    public IDKey(Object obj) {
        this.f35id = System.identityHashCode(obj);
        this.value = obj;
    }

    public int hashCode() {
        return this.f35id;
    }

    public boolean equals(Object obj) {
        if (obj instanceof IDKey) {
            IDKey iDKey = (IDKey) obj;
            return this.f35id == iDKey.f35id && this.value == iDKey.value;
        }
        return false;
    }
}
