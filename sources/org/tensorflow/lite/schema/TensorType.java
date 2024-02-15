package org.tensorflow.lite.schema;

/* loaded from: classes4.dex */
public final class TensorType {
    public static final byte BOOL = 6;
    public static final byte COMPLEX128 = 11;
    public static final byte COMPLEX64 = 8;
    public static final byte FLOAT16 = 1;
    public static final byte FLOAT32 = 0;
    public static final byte FLOAT64 = 10;
    public static final byte INT16 = 7;
    public static final byte INT32 = 2;
    public static final byte INT64 = 4;
    public static final byte INT8 = 9;
    public static final byte RESOURCE = 13;
    public static final byte STRING = 5;
    public static final byte UINT16 = 16;
    public static final byte UINT32 = 15;
    public static final byte UINT64 = 12;
    public static final byte UINT8 = 3;
    public static final byte VARIANT = 14;
    public static final String[] names = {"FLOAT32", "FLOAT16", "INT32", "UINT8", "INT64", "STRING", "BOOL", "INT16", "COMPLEX64", "INT8", "FLOAT64", "COMPLEX128", "UINT64", "RESOURCE", "VARIANT", "UINT32", "UINT16"};

    private TensorType() {
    }

    public static String name(int i) {
        return names[i];
    }
}
