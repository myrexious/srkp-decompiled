package org.tensorflow.lite.support.metadata.schema;

/* loaded from: classes4.dex */
public final class AssociatedFileType {
    public static final byte DESCRIPTIONS = 1;
    public static final byte SCANN_INDEX_FILE = 6;
    public static final byte TENSOR_AXIS_LABELS = 2;
    public static final byte TENSOR_AXIS_SCORE_CALIBRATION = 4;
    public static final byte TENSOR_VALUE_LABELS = 3;
    public static final byte UNKNOWN = 0;
    public static final byte VOCABULARY = 5;
    public static final String[] names = {"UNKNOWN", "DESCRIPTIONS", "TENSOR_AXIS_LABELS", "TENSOR_VALUE_LABELS", "TENSOR_AXIS_SCORE_CALIBRATION", "VOCABULARY", "SCANN_INDEX_FILE"};

    private AssociatedFileType() {
    }

    public static String name(int i) {
        return names[i];
    }
}
