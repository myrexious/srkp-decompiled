package org.tensorflow.lite.schema;

/* loaded from: classes4.dex */
public final class CombinerType {
    public static final byte MEAN = 1;
    public static final byte SQRTN = 2;
    public static final byte SUM = 0;
    public static final String[] names = {"SUM", "MEAN", "SQRTN"};

    private CombinerType() {
    }

    public static String name(int i) {
        return names[i];
    }
}
