package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigDecimal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class JavaBigDecimalFromCharSequence extends AbstractNumberParser {
    private static final int MANY_DIGITS_THRESHOLD = 32;
    private static final int MAX_DIGIT_COUNT = 1292782621;
    private static final long MAX_EXPONENT_NUMBER = 2147483647L;
    public static final int MAX_INPUT_LENGTH = 1292782635;

    /* JADX WARN: Code restructure failed: missing block: B:115:0x002d, code lost:
        r1 = r1 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigDecimal parseBigDecimalString(java.lang.CharSequence r31, int r32, int r33) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.doubleparser.JavaBigDecimalFromCharSequence.parseBigDecimalString(java.lang.CharSequence, int, int):java.math.BigDecimal");
    }

    BigDecimal parseBigDecimalStringWithManyDigits(CharSequence charSequence, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z;
        long j;
        int i7;
        int i8;
        boolean z2;
        int i9;
        long j2;
        int i10 = i;
        if (i2 > 1292782635) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        int i11 = i2 + i10;
        char charAt = charAt(charSequence, i10, i11);
        boolean z3 = charAt == '-';
        if ((z3 || charAt == '+') && (charAt = charAt(charSequence, (i10 = i10 + 1), i11)) == 0) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        int i12 = i10;
        while (true) {
            i3 = i11 - 8;
            if (i12 >= i3 || !FastDoubleSwar.isEightZeroes(charSequence, i12)) {
                break;
            }
            i12 += 8;
        }
        while (i12 < i11 && charSequence.charAt(i12) == '0') {
            i12++;
        }
        int i13 = i12;
        while (i13 < i3 && FastDoubleSwar.isEightDigits(charSequence, i13)) {
            i13 += 8;
        }
        while (i13 < i11) {
            charAt = charSequence.charAt(i13);
            if (!FastDoubleSwar.isDigit(charAt)) {
                break;
            }
            i13++;
        }
        if (charAt == '.') {
            int i14 = i13 + 1;
            while (i14 < i3 && FastDoubleSwar.isEightZeroes(charSequence, i14)) {
                i14 += 8;
            }
            while (i14 < i11 && charSequence.charAt(i14) == '0') {
                i14++;
            }
            int i15 = i14;
            while (i15 < i3 && FastDoubleSwar.isEightDigits(charSequence, i15)) {
                i15 += 8;
            }
            while (i15 < i11) {
                charAt = charSequence.charAt(i15);
                if (!FastDoubleSwar.isDigit(charAt)) {
                    break;
                }
                i15++;
            }
            i5 = i14;
            i4 = i13;
            i13 = i15;
        } else {
            i4 = -1;
            i5 = -1;
        }
        long j3 = 0;
        if (i4 < 0) {
            z = z3;
            i6 = i13 - i12;
            i5 = i13;
            i4 = i5;
            j = 0;
        } else {
            i6 = i12 == i4 ? i13 - i5 : (i13 - i12) - 1;
            z = z3;
            j = (i4 - i13) + 1;
        }
        if ((charAt | ' ') == 101) {
            int i16 = i13 + 1;
            char charAt2 = charAt(charSequence, i16, i11);
            boolean z4 = charAt2 == '-';
            if (z4 || charAt2 == '+') {
                i16++;
                charAt2 = charAt(charSequence, i16, i11);
            }
            boolean z5 = !FastDoubleSwar.isDigit(charAt2);
            while (true) {
                if (j3 < MAX_EXPONENT_NUMBER) {
                    i9 = i13;
                    i7 = i4;
                    j3 = ((j3 * 10) + charAt2) - 48;
                } else {
                    i9 = i13;
                    i7 = i4;
                }
                j2 = j3;
                i16++;
                char charAt3 = charAt(charSequence, i16, i11);
                if (!FastDoubleSwar.isDigit(charAt3)) {
                    break;
                }
                charAt2 = charAt3;
                j3 = j2;
                i4 = i7;
                i13 = i9;
            }
            if (z4) {
                j2 = -j2;
            }
            j += j2;
            i8 = i9;
            i13 = i16;
            z2 = z5;
        } else {
            i7 = i4;
            i8 = i11;
            z2 = false;
        }
        if (z2 || i13 < i11) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (i8 - i10 != 0) {
            if (j < -2147483648L || j > MAX_EXPONENT_NUMBER || i6 > MAX_DIGIT_COUNT) {
                throw new NumberFormatException(AbstractNumberParser.VALUE_EXCEEDS_LIMITS);
            }
            return valueOfBigDecimalString(charSequence, i12, i7, i5, i8, z, (int) j);
        }
        throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.math.BigDecimal valueOfBigDecimalString(java.lang.CharSequence r6, int r7, int r8, int r9, int r10, boolean r11, int r12) {
        /*
            r5 = this;
            int r0 = r10 - r8
            int r0 = r0 + (-1)
            int r1 = r10 - r9
            int r2 = r8 - r7
            r3 = 400(0x190, float:5.6E-43)
            r4 = 0
            if (r2 <= 0) goto L20
            if (r2 <= r3) goto L1b
            java.util.NavigableMap r2 = com.fasterxml.jackson.core.io.doubleparser.FastIntegerMath.createPowersOfTenFloor16Map()
            com.fasterxml.jackson.core.io.doubleparser.FastIntegerMath.fillPowersOfNFloor16Recursive(r2, r7, r8)
            java.math.BigInteger r7 = com.fasterxml.jackson.core.io.doubleparser.ParseDigitsTaskCharSequence.parseDigitsRecursive(r6, r7, r8, r2)
            goto L23
        L1b:
            java.math.BigInteger r7 = com.fasterxml.jackson.core.io.doubleparser.ParseDigitsTaskCharSequence.parseDigitsRecursive(r6, r7, r8, r4)
            goto L22
        L20:
            java.math.BigInteger r7 = java.math.BigInteger.ZERO
        L22:
            r2 = r4
        L23:
            if (r0 <= 0) goto L4d
            if (r1 <= r3) goto L35
            if (r2 != 0) goto L2d
            java.util.NavigableMap r2 = com.fasterxml.jackson.core.io.doubleparser.FastIntegerMath.createPowersOfTenFloor16Map()
        L2d:
            com.fasterxml.jackson.core.io.doubleparser.FastIntegerMath.fillPowersOfNFloor16Recursive(r2, r9, r10)
            java.math.BigInteger r6 = com.fasterxml.jackson.core.io.doubleparser.ParseDigitsTaskCharSequence.parseDigitsRecursive(r6, r9, r10, r2)
            goto L39
        L35:
            java.math.BigInteger r6 = com.fasterxml.jackson.core.io.doubleparser.ParseDigitsTaskCharSequence.parseDigitsRecursive(r6, r9, r10, r4)
        L39:
            int r8 = r7.signum()
            if (r8 != 0) goto L40
            goto L4c
        L40:
            java.math.BigInteger r8 = com.fasterxml.jackson.core.io.doubleparser.FastIntegerMath.computePowerOfTen(r2, r0)
            java.math.BigInteger r7 = com.fasterxml.jackson.core.io.doubleparser.FftMultiplier.multiply(r7, r8)
            java.math.BigInteger r6 = r7.add(r6)
        L4c:
            r7 = r6
        L4d:
            java.math.BigDecimal r6 = new java.math.BigDecimal
            if (r11 == 0) goto L55
            java.math.BigInteger r7 = r7.negate()
        L55:
            int r8 = -r12
            r6.<init>(r7, r8)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.doubleparser.JavaBigDecimalFromCharSequence.valueOfBigDecimalString(java.lang.CharSequence, int, int, int, int, boolean, int):java.math.BigDecimal");
    }
}
