package org.tensorflow.lite.schema;

/* loaded from: classes4.dex */
public final class ActivationFunctionType {
    public static final byte NONE = 0;
    public static final byte RELU = 1;
    public static final byte RELU6 = 3;
    public static final byte RELU_N1_TO_1 = 2;
    public static final byte SIGN_BIT = 5;
    public static final byte TANH = 4;
    public static final String[] names = {"NONE", "RELU", "RELU_N1_TO_1", "RELU6", "TANH", "SIGN_BIT"};

    private ActivationFunctionType() {
    }

    public static String name(int i) {
        return names[i];
    }
}
