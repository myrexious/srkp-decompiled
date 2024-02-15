package org.tensorflow.lite.schema;

/* loaded from: classes4.dex */
public final class QuantizationDetails {
    public static final byte CustomQuantization = 1;
    public static final byte NONE = 0;
    public static final String[] names = {"NONE", "CustomQuantization"};

    private QuantizationDetails() {
    }

    public static String name(int i) {
        return names[i];
    }
}
