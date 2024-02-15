package org.apache.commons.text.similarity;

import java.util.HashSet;

/* loaded from: classes2.dex */
public class JaccardSimilarity implements SimilarityScore<Double> {
    static final JaccardSimilarity INSTANCE = new JaccardSimilarity();

    @Override // org.apache.commons.text.similarity.SimilarityScore
    public Double apply(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return calculateJaccardSimilarity(charSequence, charSequence2);
    }

    private Double calculateJaccardSimilarity(CharSequence charSequence, CharSequence charSequence2) {
        HashSet hashSet;
        int length = charSequence.length();
        int length2 = charSequence2.length();
        if (length == 0 && length2 == 0) {
            return Double.valueOf(1.0d);
        }
        if (length == 0 || length2 == 0) {
            return Double.valueOf(0.0d);
        }
        HashSet hashSet2 = new HashSet();
        for (int i = 0; i < length; i++) {
            hashSet2.add(Character.valueOf(charSequence.charAt(i)));
        }
        HashSet hashSet3 = new HashSet();
        for (int i2 = 0; i2 < length2; i2++) {
            hashSet3.add(Character.valueOf(charSequence2.charAt(i2)));
        }
        new HashSet(hashSet2).addAll(hashSet3);
        return Double.valueOf((((hashSet2.size() + hashSet3.size()) - hashSet.size()) * 1.0d) / hashSet.size());
    }
}
