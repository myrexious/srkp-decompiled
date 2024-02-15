package org.apache.commons.text.similarity;

/* loaded from: classes2.dex */
public class JaroWinklerDistance implements EditDistance<Double> {
    @Deprecated
    public static final int INDEX_NOT_FOUND = -1;

    @Deprecated
    protected static int[] matches(CharSequence charSequence, CharSequence charSequence2) {
        return JaroWinklerSimilarity.matches(charSequence, charSequence2);
    }

    @Override // org.apache.commons.text.similarity.EditDistance, org.apache.commons.text.similarity.SimilarityScore
    public Double apply(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        return Double.valueOf(1.0d - JaroWinklerSimilarity.INSTANCE.apply(charSequence, charSequence2).doubleValue());
    }
}
