package org.tensorflow.lite.support.metadata.schema;

/* loaded from: classes4.dex */
public final class BoundingBoxType {
    public static final byte BOUNDARIES = 1;
    public static final byte CENTER = 3;
    public static final byte UNKNOWN = 0;
    public static final byte UPPER_LEFT = 2;
    public static final String[] names = {"UNKNOWN", "BOUNDARIES", "UPPER_LEFT", "CENTER"};

    private BoundingBoxType() {
    }

    public static String name(int i) {
        return names[i];
    }
}
