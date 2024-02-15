package org.tensorflow.lite.schema;

/* loaded from: classes4.dex */
public final class Padding {
    public static final byte SAME = 0;
    public static final byte VALID = 1;
    public static final String[] names = {"SAME", "VALID"};

    private Padding() {
    }

    public static String name(int i) {
        return names[i];
    }
}
