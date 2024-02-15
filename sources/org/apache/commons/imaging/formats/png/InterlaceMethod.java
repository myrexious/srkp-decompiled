package org.apache.commons.imaging.formats.png;

/* loaded from: classes2.dex */
public enum InterlaceMethod {
    NONE(false),
    ADAM7(true);
    
    private final boolean progressive;

    InterlaceMethod(boolean z) {
        this.progressive = z;
    }

    public boolean isProgressive() {
        return this.progressive;
    }
}
