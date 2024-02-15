package org.tensorflow.lite.schema;

/* loaded from: classes4.dex */
public final class SparseIndexVector {
    public static final byte Int32Vector = 1;
    public static final byte NONE = 0;
    public static final byte Uint16Vector = 2;
    public static final byte Uint8Vector = 3;
    public static final String[] names = {"NONE", "Int32Vector", "Uint16Vector", "Uint8Vector"};

    private SparseIndexVector() {
    }

    public static String name(int i) {
        return names[i];
    }
}
