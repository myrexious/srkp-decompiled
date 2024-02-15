package org.apache.commons.text.similarity;

/* loaded from: classes2.dex */
public class JaccardDistance implements EditDistance<Double> {
    @Override // org.apache.commons.text.similarity.EditDistance, org.apache.commons.text.similarity.SimilarityScore
    public Double apply(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return Double.valueOf(1.0d - JaccardSimilarity.INSTANCE.apply(charSequence, charSequence2).doubleValue());
    }
}
