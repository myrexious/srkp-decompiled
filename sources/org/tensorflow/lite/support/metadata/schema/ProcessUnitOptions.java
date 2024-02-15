package org.tensorflow.lite.support.metadata.schema;

/* loaded from: classes4.dex */
public final class ProcessUnitOptions {
    public static final byte BertTokenizerOptions = 4;
    public static final byte NONE = 0;
    public static final byte NormalizationOptions = 1;
    public static final byte RegexTokenizerOptions = 6;
    public static final byte ScoreCalibrationOptions = 2;
    public static final byte ScoreThresholdingOptions = 3;
    public static final byte SentencePieceTokenizerOptions = 5;
    public static final String[] names = {"NONE", "NormalizationOptions", "ScoreCalibrationOptions", "ScoreThresholdingOptions", "BertTokenizerOptions", "SentencePieceTokenizerOptions", "RegexTokenizerOptions"};

    private ProcessUnitOptions() {
    }

    public static String name(int i) {
        return names[i];
    }
}
