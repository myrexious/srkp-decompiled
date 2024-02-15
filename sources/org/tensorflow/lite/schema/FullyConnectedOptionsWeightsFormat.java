package org.tensorflow.lite.schema;

/* loaded from: classes4.dex */
public final class FullyConnectedOptionsWeightsFormat {
    public static final byte DEFAULT = 0;
    public static final byte SHUFFLED4x16INT8 = 1;
    public static final String[] names = {"DEFAULT", "SHUFFLED4x16INT8"};

    private FullyConnectedOptionsWeightsFormat() {
    }

    public static String name(int i) {
        return names[i];
    }
}
