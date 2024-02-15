package org.tensorflow.lite.schema;

/* loaded from: classes4.dex */
public final class DimensionType {
    public static final byte DENSE = 0;
    public static final byte SPARSE_CSR = 1;
    public static final String[] names = {"DENSE", "SPARSE_CSR"};

    private DimensionType() {
    }

    public static String name(int i) {
        return names[i];
    }
}
