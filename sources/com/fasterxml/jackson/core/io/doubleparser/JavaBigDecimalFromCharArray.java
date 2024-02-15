package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigDecimal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class JavaBigDecimalFromCharArray extends AbstractNumberParser {
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
    public java.math.BigDecimal parseBigDecimalString(char[] r31, int r32, int r33) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.doubleparser.JavaBigDecimalFromCharArray.parseBigDecimalString(char[], int, int):java.math.BigDecimal");
    }

    BigDecimal parseBigDecimalStringWithManyDigits(char[] cArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        boolean z;
        long j;
        int i6;
        int i7;
        boolean z2;
        boolean z3;
        long j2;
        int i8 = i;
        if (i2 > 1292782635) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        int i9 = i2 + i8;
        char charAt = charAt(cArr, i8, i9);
        boolean z4 = charAt == '-';
        if ((z4 || charAt == '+') && (charAt = charAt(cArr, (i8 = i8 + 1), i9)) == 0) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        int min = Math.min(i9 - 8, 1073741824);
        int i10 = i8;
        while (i10 < min && FastDoubleSwar.isEightZeroes(cArr, i10)) {
            i10 += 8;
        }
        while (i10 < i9 && cArr[i10] == '0') {
            i10++;
        }
        int i11 = i10;
        while (i11 < min && FastDoubleSwar.isEightDigits(cArr, i11)) {
            i11 += 8;
        }
        while (i11 < i9) {
            charAt = cArr[i11];
            if (!FastDoubleSwar.isDigit(charAt)) {
                break;
            }
            i11++;
        }
        if (charAt == '.') {
            int i12 = i11 + 1;
            while (i12 < min && FastDoubleSwar.isEightZeroes(cArr, i12)) {
                i12 += 8;
            }
            while (i12 < i9 && cArr[i12] == '0') {
                i12++;
            }
            int i13 = i12;
            while (i13 < min && FastDoubleSwar.isEightDigits(cArr, i13)) {
                i13 += 8;
            }
            while (i13 < i9) {
                charAt = cArr[i13];
                if (!FastDoubleSwar.isDigit(charAt)) {
                    break;
                }
                i13++;
            }
            i4 = i12;
            i3 = i11;
            i11 = i13;
        } else {
            i3 = -1;
            i4 = -1;
        }
        long j3 = 0;
        if (i3 < 0) {
            z = z4;
            i5 = i11 - i10;
            i4 = i11;
            i3 = i4;
            j = 0;
        } else {
            i5 = i10 == i3 ? i11 - i4 : (i11 - i10) - 1;
            z = z4;
            j = (i3 - i11) + 1;
        }
        if ((charAt | ' ') == 101) {
            int i14 = i11 + 1;
            char charAt2 = charAt(cArr, i14, i9);
            boolean z5 = charAt2 == '-';
            if (z5 || charAt2 == '+') {
                i14++;
                charAt2 = charAt(cArr, i14, i9);
            }
            boolean z6 = !FastDoubleSwar.isDigit(charAt2);
            while (true) {
                if (j3 < MAX_EXPONENT_NUMBER) {
                    z3 = z6;
                    i6 = i4;
                    j3 = ((j3 * 10) + charAt2) - 48;
                } else {
                    z3 = z6;
                    i6 = i4;
                }
                j2 = j3;
                i14++;
                char charAt3 = charAt(cArr, i14, i9);
                if (!FastDoubleSwar.isDigit(charAt3)) {
                    break;
                }
                charAt2 = charAt3;
                j3 = j2;
                z6 = z3;
                i4 = i6;
            }
            if (z5) {
                j2 = -j2;
            }
            j += j2;
            i7 = i11;
            z2 = z3;
            i11 = i14;
        } else {
            i6 = i4;
            i7 = i9;
            z2 = false;
        }
        if (z2 || i11 < i9) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (i7 - i8 != 0) {
            if (j < -2147483648L || j > MAX_EXPONENT_NUMBER || i5 > MAX_DIGIT_COUNT) {
                throw new NumberFormatException(AbstractNumberParser.VALUE_EXCEEDS_LIMITS);
            }
            return valueOfBigDecimalString(cArr, i10, i3, i6, i7, z, (int) j);
        }
        throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.math.BigDecimal valueOfBigDecimalString(char[] r5, int r6, int r7, int r8, int r9, boolean r10, int r11) {
        /*
            r4 = this;
            int r0 = r9 - r7
            int r0 = r0 + (-1)
            int r8 = r9 - r8
            int r1 = r7 - r6
            r2 = 400(0x190, float:5.6E-43)
            r3 = 0
            if (r1 <= 0) goto L20
            if (r1 <= r2) goto L1b
            java.util.NavigableMap r1 = com.fasterxml.jackson.core.io.doubleparser.FastIntegerMath.createPowersOfTenFloor16Map()
            com.fasterxml.jackson.core.io.doubleparser.FastIntegerMath.fillPowersOfNFloor16Recursive(r1, r6, r7)
            java.math.BigInteger r6 = com.fasterxml.jackson.core.io.doubleparser.ParseDigitsTaskCharArray.parseDigitsRecursive(r5, r6, r7, r1)
            goto L23
        L1b:
            java.math.BigInteger r6 = com.fasterxml.jackson.core.io.doubleparser.ParseDigitsTaskCharArray.parseDigitsRecursive(r5, r6, r7, r3)
            goto L22
        L20:
            java.math.BigInteger r6 = java.math.BigInteger.ZERO
        L22:
            r1 = r3
        L23:
            if (r8 <= 0) goto L51
            if (r8 <= r2) goto L37
            if (r1 != 0) goto L2d
            java.util.NavigableMap r1 = com.fasterxml.jackson.core.io.doubleparser.FastIntegerMath.createPowersOfTenFloor16Map()
        L2d:
            int r7 = r7 + 1
            com.fasterxml.jackson.core.io.doubleparser.FastIntegerMath.fillPowersOfNFloor16Recursive(r1, r7, r9)
            java.math.BigInteger r5 = com.fasterxml.jackson.core.io.doubleparser.ParseDigitsTaskCharArray.parseDigitsRecursive(r5, r7, r9, r1)
            goto L3d
        L37:
            int r7 = r7 + 1
            java.math.BigInteger r5 = com.fasterxml.jackson.core.io.doubleparser.ParseDigitsTaskCharArray.parseDigitsRecursive(r5, r7, r9, r3)
        L3d:
            int r7 = r6.signum()
            if (r7 != 0) goto L44
            goto L50
        L44:
            java.math.BigInteger r7 = com.fasterxml.jackson.core.io.doubleparser.FastIntegerMath.computePowerOfTen(r1, r0)
            java.math.BigInteger r6 = com.fasterxml.jackson.core.io.doubleparser.FftMultiplier.multiply(r6, r7)
            java.math.BigInteger r5 = r6.add(r5)
        L50:
            r6 = r5
        L51:
            java.math.BigDecimal r5 = new java.math.BigDecimal
            if (r10 == 0) goto L59
            java.math.BigInteger r6 = r6.negate()
        L59:
            int r7 = -r11
            r5.<init>(r6, r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.doubleparser.JavaBigDecimalFromCharArray.valueOfBigDecimalString(char[], int, int, int, int, boolean, int):java.math.BigDecimal");
    }
}
