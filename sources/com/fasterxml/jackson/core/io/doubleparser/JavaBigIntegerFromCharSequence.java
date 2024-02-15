package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class JavaBigIntegerFromCharSequence extends AbstractNumberParser {
    private static final int MAX_DECIMAL_DIGITS = 646456993;
    private static final int MAX_HEX_DIGITS = 536870912;
    public static final int MAX_INPUT_LENGTH = 1292782622;

    /* JADX WARN: Removed duplicated region for block: B:60:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0048 A[Catch: ArithmeticException -> 0x005d, TryCatch #0 {ArithmeticException -> 0x005d, blocks: (B:43:0x0006, B:47:0x0011, B:62:0x0035, B:64:0x0043, B:66:0x0048, B:56:0x0025, B:68:0x004d, B:69:0x0054, B:70:0x0055, B:71:0x005c), top: B:75:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigInteger parseBigIntegerLiteral(java.lang.CharSequence r5, int r6, int r7, int r8) throws java.lang.NumberFormatException {
        /*
            r4 = this;
            int r0 = r6 + r7
            if (r6 < 0) goto L55
            if (r0 < r6) goto L55
            int r1 = r5.length()     // Catch: java.lang.ArithmeticException -> L5d
            if (r0 > r1) goto L55
            r1 = 1292782622(0x4d0e4c1e, float:1.49209568E8)
            if (r7 > r1) goto L55
            char r1 = r5.charAt(r6)     // Catch: java.lang.ArithmeticException -> L5d
            r2 = 45
            if (r1 != r2) goto L1b
            r2 = 1
            goto L1c
        L1b:
            r2 = 0
        L1c:
            if (r2 != 0) goto L25
            r3 = 43
            if (r1 != r3) goto L23
            goto L25
        L23:
            r1 = r6
            goto L2d
        L25:
            int r1 = r6 + 1
            char r3 = charAt(r5, r1, r0)     // Catch: java.lang.ArithmeticException -> L5d
            if (r3 == 0) goto L4d
        L2d:
            r3 = 10
            if (r8 == r3) goto L48
            r3 = 16
            if (r8 == r3) goto L43
            java.math.BigInteger r0 = new java.math.BigInteger     // Catch: java.lang.ArithmeticException -> L5d
            java.lang.CharSequence r5 = r5.subSequence(r6, r7)     // Catch: java.lang.ArithmeticException -> L5d
            java.lang.String r5 = r5.toString()     // Catch: java.lang.ArithmeticException -> L5d
            r0.<init>(r5, r8)     // Catch: java.lang.ArithmeticException -> L5d
            return r0
        L43:
            java.math.BigInteger r5 = r4.parseHexDigits(r5, r1, r0, r2)     // Catch: java.lang.ArithmeticException -> L5d
            return r5
        L48:
            java.math.BigInteger r5 = r4.parseDecDigits(r5, r1, r0, r2)     // Catch: java.lang.ArithmeticException -> L5d
            return r5
        L4d:
            java.lang.NumberFormatException r5 = new java.lang.NumberFormatException     // Catch: java.lang.ArithmeticException -> L5d
            java.lang.String r6 = "illegal syntax"
            r5.<init>(r6)     // Catch: java.lang.ArithmeticException -> L5d
            throw r5     // Catch: java.lang.ArithmeticException -> L5d
        L55:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch: java.lang.ArithmeticException -> L5d
            java.lang.String r6 = "offset < 0 or length > str.length"
            r5.<init>(r6)     // Catch: java.lang.ArithmeticException -> L5d
            throw r5     // Catch: java.lang.ArithmeticException -> L5d
        L5d:
            r5 = move-exception
            java.lang.NumberFormatException r6 = new java.lang.NumberFormatException
            java.lang.String r7 = "value exceeds limits"
            r6.<init>(r7)
            r6.initCause(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.doubleparser.JavaBigIntegerFromCharSequence.parseBigIntegerLiteral(java.lang.CharSequence, int, int, int):java.math.BigInteger");
    }

    private BigInteger parseDecDigits(CharSequence charSequence, int i, int i2, boolean z) {
        int i3 = i2 - i;
        if (i3 > 18) {
            return parseManyDecDigits(charSequence, i, i2, z);
        }
        int i4 = (i3 & 7) + i;
        long tryToParseUpTo7Digits = FastDoubleSwar.tryToParseUpTo7Digits(charSequence, i, i4);
        boolean z2 = tryToParseUpTo7Digits >= 0;
        while (i4 < i2) {
            int tryToParseEightDigits = FastDoubleSwar.tryToParseEightDigits(charSequence, i4);
            z2 &= tryToParseEightDigits >= 0;
            tryToParseUpTo7Digits = (tryToParseUpTo7Digits * 100000000) + tryToParseEightDigits;
            i4 += 8;
        }
        if (!z2) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (z) {
            tryToParseUpTo7Digits = -tryToParseUpTo7Digits;
        }
        return BigInteger.valueOf(tryToParseUpTo7Digits);
    }

    private BigInteger parseHexDigits(CharSequence charSequence, int i, int i2, boolean z) {
        int i3;
        boolean z2;
        int skipZeroes = skipZeroes(charSequence, i, i2);
        int i4 = i2 - skipZeroes;
        if (i4 <= 0) {
            return BigInteger.ZERO;
        }
        if (i4 > 536870912) {
            throw new NumberFormatException(AbstractNumberParser.VALUE_EXCEEDS_LIMITS);
        }
        byte[] bArr = new byte[((i4 + 1) >> 1) + 1];
        if ((i4 & 1) != 0) {
            int i5 = skipZeroes + 1;
            int lookupHex = lookupHex(charSequence.charAt(skipZeroes));
            bArr[1] = (byte) lookupHex;
            i3 = 2;
            z2 = lookupHex < 0;
            skipZeroes = i5;
        } else {
            i3 = 1;
            z2 = false;
        }
        int i6 = ((i2 - skipZeroes) & 7) + skipZeroes;
        while (skipZeroes < i6) {
            char charAt = charSequence.charAt(skipZeroes);
            char charAt2 = charSequence.charAt(skipZeroes + 1);
            int lookupHex2 = lookupHex(charAt);
            int lookupHex3 = lookupHex(charAt2);
            int i7 = i3 + 1;
            bArr[i3] = (byte) ((lookupHex2 << 4) | lookupHex3);
            z2 |= lookupHex3 < 0 || lookupHex2 < 0;
            skipZeroes += 2;
            i3 = i7;
        }
        while (skipZeroes < i2) {
            long tryToParseEightHexDigits = FastDoubleSwar.tryToParseEightHexDigits(charSequence, skipZeroes);
            FastDoubleSwar.writeIntBE(bArr, i3, (int) tryToParseEightHexDigits);
            z2 |= tryToParseEightHexDigits < 0;
            skipZeroes += 8;
            i3 += 4;
        }
        if (z2) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        BigInteger bigInteger = new BigInteger(bArr);
        return z ? bigInteger.negate() : bigInteger;
    }

    private BigInteger parseManyDecDigits(CharSequence charSequence, int i, int i2, boolean z) {
        int skipZeroes = skipZeroes(charSequence, i, i2);
        if (i2 - skipZeroes > MAX_DECIMAL_DIGITS) {
            throw new NumberFormatException(AbstractNumberParser.VALUE_EXCEEDS_LIMITS);
        }
        BigInteger parseDigitsRecursive = ParseDigitsTaskCharSequence.parseDigitsRecursive(charSequence, skipZeroes, i2, FastIntegerMath.fillPowersOf10Floor16(skipZeroes, i2));
        return z ? parseDigitsRecursive.negate() : parseDigitsRecursive;
    }

    private int skipZeroes(CharSequence charSequence, int i, int i2) {
        while (i < i2 && charSequence.charAt(i) == '0') {
            i++;
        }
        return i;
    }
}
