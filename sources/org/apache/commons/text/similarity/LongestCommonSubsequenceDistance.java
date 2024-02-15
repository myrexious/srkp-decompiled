package org.apache.commons.text.similarity;

/* loaded from: classes2.dex */
public class LongestCommonSubsequenceDistance implements EditDistance<Integer> {
    @Override // org.apache.commons.text.similarity.EditDistance, org.apache.commons.text.similarity.SimilarityScore
    public Integer apply(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Inputs must not be null");
        }
        return Integer.valueOf((charSequence.length() + charSequence2.length()) - (LongestCommonSubsequence.INSTANCE.apply(charSequence, charSequence2).intValue() * 2));
    }
}
