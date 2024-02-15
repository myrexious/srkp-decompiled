package org.tensorflow.lite.support.metadata.schema;

/* loaded from: classes4.dex */
public final class ColorSpaceType {
    public static final byte GRAYSCALE = 2;
    public static final byte RGB = 1;
    public static final byte UNKNOWN = 0;
    public static final String[] names = {"UNKNOWN", "RGB", "GRAYSCALE"};

    private ColorSpaceType() {
    }

    public static String name(int i) {
        return names[i];
    }
}
