package org.tensorflow.lite.support.metadata.schema;

/* loaded from: classes4.dex */
public final class CoordinateType {
    public static final byte PIXEL = 1;
    public static final byte RATIO = 0;
    public static final String[] names = {"RATIO", "PIXEL"};

    private CoordinateType() {
    }

    public static String name(int i) {
        return names[i];
    }
}
