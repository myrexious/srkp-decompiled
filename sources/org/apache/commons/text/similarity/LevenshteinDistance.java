package org.apache.commons.text.similarity;

import java.util.Arrays;

/* loaded from: classes2.dex */
public class LevenshteinDistance implements EditDistance<Integer> {
    private static final LevenshteinDistance INSTANCE = new LevenshteinDistance();
    private final Integer threshold;

    public static LevenshteinDistance getDefaultInstance() {
        return INSTANCE;
    }

    private static int limitedCompare(CharSequence charSequence, CharSequence charSequence2, int i) {
        int i2;
        int i3;
        CharSequence charSequence3;
        CharSequence charSequence4;
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        int i4 = -1;
        if (length == 0) {
            if (length2 <= i) {
                return length2;
            }
            return -1;
        } else if (length2 == 0) {
            if (length <= i) {
                return length;
            }
            return -1;
        } else {
            if (length > length2) {
                i3 = charSequence.length();
                i2 = length2;
                charSequence4 = charSequence;
                charSequence3 = charSequence2;
            } else {
                i2 = length;
                i3 = length2;
                charSequence3 = charSequence;
                charSequence4 = charSequence2;
            }
            if (i3 - i2 > i) {
                return -1;
            }
            int i5 = i2 + 1;
            int[] iArr = new int[i5];
            int[] iArr2 = new int[i5];
            int min = Math.min(i2, i) + 1;
            char c = 0;
            for (int i6 = 0; i6 < min; i6++) {
                iArr[i6] = i6;
            }
            int i7 = Integer.MAX_VALUE;
            Arrays.fill(iArr, min, i5, Integer.MAX_VALUE);
            Arrays.fill(iArr2, Integer.MAX_VALUE);
            int i8 = 1;
            while (i8 <= i3) {
                char charAt = charSequence4.charAt(i8 - 1);
                iArr2[c] = i8;
                int max = Math.max(1, i8 - i);
                int min2 = i8 > i7 - i ? i2 : Math.min(i2, i8 + i);
                if (max > 1) {
                    iArr2[max - 1] = i7;
                }
                int i9 = i7;
                while (max <= min2) {
                    int i10 = max - 1;
                    if (charSequence3.charAt(i10) == charAt) {
                        iArr2[max] = iArr[i10];
                    } else {
                        iArr2[max] = Math.min(Math.min(iArr2[i10], iArr[max]), iArr[i10]) + 1;
                    }
                    i9 = Math.min(i9, iArr2[max]);
                    max++;
                }
                if (i9 > i) {
                    return -1;
                }
                i4 = -1;
                i8++;
                c = 0;
                i7 = Integer.MAX_VALUE;
                int[] iArr3 = iArr2;
                iArr2 = iArr;
                iArr = iArr3;
            }
            int i11 = iArr[i2];
            return i11 <= i ? i11 : i4;
        }
    }

    private static int unlimitedCompare(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("CharSequences must not be null");
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        if (length == 0) {
            return length2;
        }
        if (length2 == 0) {
            return length;
        }
        if (length > length2) {
            length2 = charSequence.length();
            length = length2;
        } else {
            charSequence2 = charSequence;
            charSequence = charSequence2;
        }
        int[] iArr = new int[length + 1];
        for (int i = 0; i <= length; i++) {
            iArr[i] = i;
        }
        for (int i2 = 1; i2 <= length2; i2++) {
            int i3 = iArr[0];
            char charAt = charSequence.charAt(i2 - 1);
            iArr[0] = i2;
            int i4 = 1;
            while (i4 <= length) {
                int i5 = iArr[i4];
                int i6 = i4 - 1;
                iArr[i4] = Math.min(Math.min(iArr[i6] + 1, iArr[i4] + 1), i3 + (charSequence2.charAt(i6) == charAt ? 0 : 1));
                i4++;
                i3 = i5;
            }
        }
        return iArr[length];
    }

    public LevenshteinDistance() {
        this(null);
    }

    public LevenshteinDistance(Integer num) {
        if (num != null && num.intValue() < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        this.threshold = num;
    }

    @Override // org.apache.commons.text.similarity.EditDistance, org.apache.commons.text.similarity.SimilarityScore
    public Integer apply(CharSequence charSequence, CharSequence charSequence2) {
        Integer num = this.threshold;
        if (num != null) {
            return Integer.valueOf(limitedCompare(charSequence, charSequence2, num.intValue()));
        }
        return Integer.valueOf(unlimitedCompare(charSequence, charSequence2));
    }

    public Integer getThreshold() {
        return this.threshold;
    }
}
