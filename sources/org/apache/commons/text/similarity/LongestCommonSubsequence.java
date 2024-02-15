package org.apache.commons.text.similarity;

import java.lang.reflect.Array;

/* loaded from: classes2.dex */
public class LongestCommonSubsequence implements SimilarityScore<Integer> {
    static final LongestCommonSubsequence INSTANCE = new LongestCommonSubsequence();

    private static int[] algorithmB(CharSequence charSequence, CharSequence charSequence2) {
        int length = charSequence.length();
        int length2 = charSequence2.length();
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, 2, length2 + 1);
        for (int i = 1; i <= length; i++) {
            int[] iArr2 = iArr[0];
            iArr[0] = iArr[1];
            iArr[1] = iArr2;
            for (int i2 = 1; i2 <= length2; i2++) {
                int i3 = i2 - 1;
                if (charSequence.charAt(i - 1) == charSequence2.charAt(i3)) {
                    iArr[1][i2] = iArr[0][i3] + 1;
                } else {
                    int[] iArr3 = iArr[1];
                    iArr3[i2] = Math.max(iArr3[i3], iArr[0][i2]);
                }
            }
        }
        return iArr[1];
    }

    private static String algorithmC(CharSequence charSequence, CharSequence charSequence2) {
        int length = charSequence.length();
        int length2 = charSequence2.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (length == 1) {
            char charAt = charSequence.charAt(0);
            while (true) {
                if (i >= length2) {
                    break;
                } else if (charAt == charSequence2.charAt(i)) {
                    sb.append(charAt);
                    break;
                } else {
                    i++;
                }
            }
        } else if (length2 > 0 && length > 1) {
            int i2 = length / 2;
            CharSequence subSequence = charSequence.subSequence(0, i2);
            CharSequence subSequence2 = charSequence.subSequence(i2, length);
            int[] algorithmB = algorithmB(subSequence, charSequence2);
            int[] algorithmB2 = algorithmB(reverse(subSequence2), reverse(charSequence2));
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 <= length2; i5++) {
                int i6 = algorithmB[i5] + algorithmB2[length2 - i5];
                if (i4 < i6) {
                    i3 = i5;
                    i4 = i6;
                }
            }
            sb.append(algorithmC(subSequence, charSequence2.subSequence(0, i3)));
            sb.append(algorithmC(subSequence2, charSequence2.subSequence(i3, length2)));
        }
        return sb.toString();
    }

    private static String reverse(CharSequence charSequence) {
        return new StringBuilder(charSequence).reverse().toString();
    }

    @Override // org.apache.commons.text.similarity.SimilarityScore
    public Integer apply(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Inputs must not be null");
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        if (length == 0 || length2 == 0) {
            return 0;
        }
        if (length < length2) {
            return Integer.valueOf(algorithmB(charSequence2, charSequence)[length]);
        }
        return Integer.valueOf(algorithmB(charSequence, charSequence2)[length2]);
    }

    @Deprecated
    public CharSequence logestCommonSubsequence(CharSequence charSequence, CharSequence charSequence2) {
        return longestCommonSubsequence(charSequence, charSequence2);
    }

    public CharSequence longestCommonSubsequence(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Inputs must not be null");
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        if (length == 0 || length2 == 0) {
            return "";
        }
        if (length < length2) {
            return algorithmC(charSequence2, charSequence);
        }
        return algorithmC(charSequence, charSequence2);
    }

    @Deprecated
    public int[][] longestCommonSubstringLengthArray(CharSequence charSequence, CharSequence charSequence2) {
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, charSequence.length() + 1, charSequence2.length() + 1);
        for (int i = 0; i < charSequence.length(); i++) {
            for (int i2 = 0; i2 < charSequence2.length(); i2++) {
                if (i == 0) {
                    iArr[i][i2] = 0;
                }
                if (i2 == 0) {
                    iArr[i][i2] = 0;
                }
                if (charSequence.charAt(i) == charSequence2.charAt(i2)) {
                    iArr[i + 1][i2 + 1] = iArr[i][i2] + 1;
                } else {
                    int[] iArr2 = iArr[i + 1];
                    int i3 = i2 + 1;
                    iArr2[i3] = Math.max(iArr2[i2], iArr[i][i3]);
                }
            }
        }
        return iArr;
    }
}
