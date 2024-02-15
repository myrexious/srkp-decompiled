package org.tensorflow.lite.schema;

/* loaded from: classes4.dex */
public final class LSHProjectionType {
    public static final byte DENSE = 2;
    public static final byte SPARSE = 1;
    public static final byte UNKNOWN = 0;
    public static final String[] names = {"UNKNOWN", "SPARSE", "DENSE"};

    private LSHProjectionType() {
    }

    public static String name(int i) {
        return names[i];
    }
}
