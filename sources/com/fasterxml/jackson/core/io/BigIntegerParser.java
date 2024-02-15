package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.io.doubleparser.JavaBigIntegerParser;
import java.math.BigInteger;

/* loaded from: classes.dex */
public final class BigIntegerParser {
    private BigIntegerParser() {
    }

    public static BigInteger parseWithFastParser(String str) {
        try {
            return JavaBigIntegerParser.parseBigInteger(str);
        } catch (NumberFormatException e) {
            if (str.length() > 1000) {
                str = str.substring(0, 1000) + " [truncated]";
            }
            throw new NumberFormatException("Value \"" + str + "\" can not be represented as `java.math.BigInteger`, reason: " + e.getMessage());
        }
    }

    public static BigInteger parseWithFastParser(String str, int i) {
        try {
            return JavaBigIntegerParser.parseBigInteger(str, i);
        } catch (NumberFormatException e) {
            if (str.length() > 1000) {
                str = str.substring(0, 1000) + " [truncated]";
            }
            throw new NumberFormatException("Value \"" + str + "\" can not be represented as `java.math.BigInteger` with radix " + i + ", reason: " + e.getMessage());
        }
    }
}
