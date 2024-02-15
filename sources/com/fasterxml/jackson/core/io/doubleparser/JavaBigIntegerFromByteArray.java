package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigInteger;

/* loaded from: classes.dex */
class JavaBigIntegerFromByteArray extends AbstractNumberParser {
    private static final int MAX_DECIMAL_DIGITS = 646456993;
    private static final int MAX_HEX_DIGITS = 536870912;
    public static final int MAX_INPUT_LENGTH = 1292782622;

    /* JADX WARN: Removed duplicated region for block: B:60:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0042 A[Catch: ArithmeticException -> 0x0057, TryCatch #0 {ArithmeticException -> 0x0057, blocks: (B:43:0x0006, B:47:0x000e, B:62:0x0030, B:64:0x003d, B:66:0x0042, B:56:0x0020, B:68:0x0047, B:69:0x004e, B:70:0x004f, B:71:0x0056), top: B:75:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigInteger parseBigIntegerLiteral(byte[] r5, int r6, int r7, int r8) throws java.lang.NumberFormatException {
        /*
            r4 = this;
            int r0 = r6 + r7
            if (r6 < 0) goto L4f
            if (r0 < r6) goto L4f
            int r1 = r5.length     // Catch: java.lang.ArithmeticException -> L57
            if (r0 > r1) goto L4f
            r1 = 1292782622(0x4d0e4c1e, float:1.49209568E8)
            if (r7 > r1) goto L4f
            r1 = r5[r6]     // Catch: java.lang.ArithmeticException -> L57
            r2 = 45
            if (r1 != r2) goto L16
            r2 = 1
            goto L17
        L16:
            r2 = 0
        L17:
            if (r2 != 0) goto L20
            r3 = 43
            if (r1 != r3) goto L1e
            goto L20
        L1e:
            r1 = r6
            goto L28
        L20:
            int r1 = r6 + 1
            byte r3 = charAt(r5, r1, r0)     // Catch: java.lang.ArithmeticException -> L57
            if (r3 == 0) goto L47
        L28:
            r3 = 10
            if (r8 == r3) goto L42
            r3 = 16
            if (r8 == r3) goto L3d
            java.math.BigInteger r0 = new java.math.BigInteger     // Catch: java.lang.ArithmeticException -> L57
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.ArithmeticException -> L57
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.ISO_8859_1     // Catch: java.lang.ArithmeticException -> L57
            r1.<init>(r5, r6, r7, r2)     // Catch: java.lang.ArithmeticException -> L57
            r0.<init>(r1, r8)     // Catch: java.lang.ArithmeticException -> L57
            return r0
        L3d:
            java.math.BigInteger r5 = r4.parseHexDigits(r5, r1, r0, r2)     // Catch: java.lang.ArithmeticException -> L57
            return r5
        L42:
            java.math.BigInteger r5 = r4.parseDecDigits(r5, r1, r0, r2)     // Catch: java.lang.ArithmeticException -> L57
            return r5
        L47:
            java.lang.NumberFormatException r5 = new java.lang.NumberFormatException     // Catch: java.lang.ArithmeticException -> L57
            java.lang.String r6 = "illegal syntax"
            r5.<init>(r6)     // Catch: java.lang.ArithmeticException -> L57
            throw r5     // Catch: java.lang.ArithmeticException -> L57
        L4f:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch: java.lang.ArithmeticException -> L57
            java.lang.String r6 = "offset < 0 or length > str.length"
            r5.<init>(r6)     // Catch: java.lang.ArithmeticException -> L57
            throw r5     // Catch: java.lang.ArithmeticException -> L57
        L57:
            r5 = move-exception
            java.lang.NumberFormatException r6 = new java.lang.NumberFormatException
            java.lang.String r7 = "value exceeds limits"
            r6.<init>(r7)
            r6.initCause(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.doubleparser.JavaBigIntegerFromByteArray.parseBigIntegerLiteral(byte[], int, int, int):java.math.BigInteger");
    }

    private BigInteger parseDecDigits(byte[] bArr, int i, int i2, boolean z) {
        int i3 = i2 - i;
        if (i3 > 18) {
            return parseManyDecDigits(bArr, i, i2, z);
        }
        int i4 = (i3 & 7) + i;
        long tryToParseUpTo7Digits = FastDoubleSwar.tryToParseUpTo7Digits(bArr, i, i4);
        boolean z2 = tryToParseUpTo7Digits >= 0;
        while (i4 < i2) {
            int tryToParseEightDigitsUtf8 = FastDoubleSwar.tryToParseEightDigitsUtf8(bArr, i4);
            z2 &= tryToParseEightDigitsUtf8 >= 0;
            tryToParseUpTo7Digits = (tryToParseUpTo7Digits * 100000000) + tryToParseEightDigitsUtf8;
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

    private BigInteger parseHexDigits(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        boolean z2;
        int skipZeroes = skipZeroes(bArr, i, i2);
        int i4 = i2 - skipZeroes;
        if (i4 <= 0) {
            return BigInteger.ZERO;
        }
        if (i4 > 536870912) {
            throw new NumberFormatException(AbstractNumberParser.VALUE_EXCEEDS_LIMITS);
        }
        byte[] bArr2 = new byte[((i4 + 1) >> 1) + 1];
        if ((i4 & 1) != 0) {
            int i5 = skipZeroes + 1;
            byte b = bArr[skipZeroes];
            byte b2 = b < 0 ? (byte) -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[b];
            bArr2[1] = b2;
            i3 = 2;
            z2 = b2 < 0;
            skipZeroes = i5;
        } else {
            i3 = 1;
            z2 = false;
        }
        int i6 = ((i2 - skipZeroes) & 7) + skipZeroes;
        while (skipZeroes < i6) {
            byte b3 = bArr[skipZeroes];
            byte b4 = bArr[skipZeroes + 1];
            byte b5 = b3 < 0 ? (byte) -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[b3];
            byte b6 = b4 < 0 ? (byte) -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[b4];
            int i7 = i3 + 1;
            bArr2[i3] = (byte) ((b5 << 4) | b6);
            z2 |= b5 < 0 || b6 < 0;
            skipZeroes += 2;
            i3 = i7;
        }
        while (skipZeroes < i2) {
            long tryToParseEightHexDigits = FastDoubleSwar.tryToParseEightHexDigits(bArr, skipZeroes);
            FastDoubleSwar.writeIntBE(bArr2, i3, (int) tryToParseEightHexDigits);
            z2 |= tryToParseEightHexDigits < 0;
            skipZeroes += 8;
            i3 += 4;
        }
        if (z2) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        BigInteger bigInteger = new BigInteger(bArr2);
        return z ? bigInteger.negate() : bigInteger;
    }

    private BigInteger parseManyDecDigits(byte[] bArr, int i, int i2, boolean z) {
        int skipZeroes = skipZeroes(bArr, i, i2);
        if (i2 - skipZeroes > MAX_DECIMAL_DIGITS) {
            throw new NumberFormatException(AbstractNumberParser.VALUE_EXCEEDS_LIMITS);
        }
        BigInteger parseDigitsRecursive = ParseDigitsTaskByteArray.parseDigitsRecursive(bArr, skipZeroes, i2, FastIntegerMath.fillPowersOf10Floor16(skipZeroes, i2));
        return z ? parseDigitsRecursive.negate() : parseDigitsRecursive;
    }

    private int skipZeroes(byte[] bArr, int i, int i2) {
        while (i < i2 - 8 && FastDoubleSwar.isEightZeroes(bArr, i)) {
            i += 8;
        }
        while (i < i2 && bArr[i] == 48) {
            i++;
        }
        return i;
    }
}
