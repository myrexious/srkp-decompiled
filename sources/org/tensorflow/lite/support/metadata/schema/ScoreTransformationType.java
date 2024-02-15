package org.tensorflow.lite.support.metadata.schema;

/* loaded from: classes4.dex */
public final class ScoreTransformationType {
    public static final byte IDENTITY = 0;
    public static final byte INVERSE_LOGISTIC = 2;
    public static final byte LOG = 1;
    public static final String[] names = {"IDENTITY", "LOG", "INVERSE_LOGISTIC"};

    private ScoreTransformationType() {
    }

    public static String name(int i) {
        return names[i];
    }
}
