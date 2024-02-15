package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigInteger;
import java.util.Map;

/* loaded from: classes.dex */
public class ParseDigitsTaskCharArray {
    static final int RECURSION_THRESHOLD = 400;

    private ParseDigitsTaskCharArray() {
    }

    static BigInteger parseDigitsIterative(char[] cArr, int i, int i2) {
        int i3 = i2 - i;
        BigSignificand bigSignificand = new BigSignificand(FastIntegerMath.estimateNumBits(i3));
        int i4 = (i3 & 7) + i;
        int tryToParseUpTo7Digits = FastDoubleSwar.tryToParseUpTo7Digits(cArr, i, i4);
        boolean z = tryToParseUpTo7Digits >= 0;
        bigSignificand.add(tryToParseUpTo7Digits);
        while (i4 < i2) {
            int tryToParseEightDigits = FastDoubleSwar.tryToParseEightDigits(cArr, i4);
            z &= tryToParseEightDigits >= 0;
            bigSignificand.fma(100000000, tryToParseEightDigits);
            i4 += 8;
        }
        if (!z) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        return bigSignificand.toBigInteger();
    }

    public static BigInteger parseDigitsRecursive(char[] cArr, int i, int i2, Map<Integer, BigInteger> map) {
        if (i2 - i <= 400) {
            return parseDigitsIterative(cArr, i, i2);
        }
        int splitFloor16 = FastIntegerMath.splitFloor16(i, i2);
        return parseDigitsRecursive(cArr, splitFloor16, i2, map).add(FftMultiplier.multiply(parseDigitsRecursive(cArr, i, splitFloor16, map), map.get(Integer.valueOf(i2 - splitFloor16))));
    }
}
