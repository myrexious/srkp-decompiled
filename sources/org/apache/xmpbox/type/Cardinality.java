package org.apache.xmpbox.type;

/* loaded from: classes2.dex */
public enum Cardinality {
    Simple(false),
    Bag(true),
    Seq(true),
    Alt(true);
    
    private final boolean array;

    Cardinality(boolean z) {
        this.array = z;
    }

    public boolean isArray() {
        return this.array;
    }
}
