package org.apache.commons.text.similarity;

/* loaded from: classes2.dex */
public class CosineDistance implements EditDistance<Double> {
    @Override // org.apache.commons.text.similarity.EditDistance, org.apache.commons.text.similarity.SimilarityScore
    public Double apply(CharSequence charSequence, CharSequence charSequence2) {
        CharSequence[] apply = RegexTokenizer.INSTANCE.apply(charSequence);
        CharSequence[] apply2 = RegexTokenizer.INSTANCE.apply(charSequence2);
        return Double.valueOf(1.0d - CosineSimilarity.INSTANCE.cosineSimilarity(Counter.of(apply), Counter.of(apply2)).doubleValue());
    }
}
