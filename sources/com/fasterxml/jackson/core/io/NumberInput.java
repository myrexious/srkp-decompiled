package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.io.doubleparser.JavaDoubleParser;
import com.fasterxml.jackson.core.io.doubleparser.JavaFloatParser;
import java.math.BigDecimal;
import java.math.BigInteger;
import kotlin.time.DurationKt;

/* loaded from: classes.dex */
public final class NumberInput {
    static final long L_BILLION = 1000000000;
    @Deprecated
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int parseInt(char[] cArr, int i, int i2) {
        if (i2 > 0 && cArr[i] == '+') {
            i++;
            i2--;
        }
        int i3 = cArr[(i + i2) - 1] - '0';
        switch (i2) {
            case 2:
                break;
            case 3:
                i3 += (cArr[i] - '0') * 100;
                i++;
                break;
            case 4:
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                break;
            case 5:
                i3 += (cArr[i] - '0') * 10000;
                i++;
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                break;
            case 6:
                i3 += (cArr[i] - '0') * 100000;
                i++;
                i3 += (cArr[i] - '0') * 10000;
                i++;
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                break;
            case 7:
                i3 += (cArr[i] - '0') * DurationKt.NANOS_IN_MILLIS;
                i++;
                i3 += (cArr[i] - '0') * 100000;
                i++;
                i3 += (cArr[i] - '0') * 10000;
                i++;
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                break;
            case 8:
                i3 += (cArr[i] - '0') * 10000000;
                i++;
                i3 += (cArr[i] - '0') * DurationKt.NANOS_IN_MILLIS;
                i++;
                i3 += (cArr[i] - '0') * 100000;
                i++;
                i3 += (cArr[i] - '0') * 10000;
                i++;
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                break;
            case 9:
                i3 += (cArr[i] - '0') * 100000000;
                i++;
                i3 += (cArr[i] - '0') * 10000000;
                i++;
                i3 += (cArr[i] - '0') * DurationKt.NANOS_IN_MILLIS;
                i++;
                i3 += (cArr[i] - '0') * 100000;
                i++;
                i3 += (cArr[i] - '0') * 10000;
                i++;
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                break;
            default:
                return i3;
        }
        return i3 + ((cArr[i] - '0') * 10);
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0072, code lost:
        return java.lang.Integer.parseInt(r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int parseInt(java.lang.String r8) {
        /*
            r0 = 0
            char r1 = r8.charAt(r0)
            int r2 = r8.length()
            r3 = 45
            r4 = 1
            if (r1 != r3) goto Lf
            r0 = r4
        Lf:
            r3 = 10
            if (r0 == 0) goto L23
            if (r2 == r4) goto L1e
            if (r2 <= r3) goto L18
            goto L1e
        L18:
            char r1 = r8.charAt(r4)
            r4 = 2
            goto L2c
        L1e:
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        L23:
            r5 = 9
            if (r2 <= r5) goto L2c
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        L2c:
            r5 = 57
            if (r1 > r5) goto L81
            r6 = 48
            if (r1 >= r6) goto L35
            goto L81
        L35:
            int r1 = r1 - r6
            if (r4 >= r2) goto L7d
            int r7 = r4 + 1
            char r4 = r8.charAt(r4)
            if (r4 > r5) goto L78
            if (r4 >= r6) goto L43
            goto L78
        L43:
            int r1 = r1 * 10
            int r4 = r4 - r6
            int r1 = r1 + r4
            if (r7 >= r2) goto L7d
            int r4 = r7 + 1
            char r7 = r8.charAt(r7)
            if (r7 > r5) goto L73
            if (r7 >= r6) goto L54
            goto L73
        L54:
            int r1 = r1 * 10
            int r7 = r7 - r6
            int r1 = r1 + r7
            if (r4 >= r2) goto L7d
        L5a:
            int r7 = r4 + 1
            char r4 = r8.charAt(r4)
            if (r4 > r5) goto L6e
            if (r4 >= r6) goto L65
            goto L6e
        L65:
            int r1 = r1 * r3
            int r4 = r4 + (-48)
            int r1 = r1 + r4
            if (r7 < r2) goto L6c
            goto L7d
        L6c:
            r4 = r7
            goto L5a
        L6e:
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        L73:
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        L78:
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        L7d:
            if (r0 == 0) goto L80
            int r1 = -r1
        L80:
            return r1
        L81:
            int r8 = java.lang.Integer.parseInt(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.NumberInput.parseInt(java.lang.String):int");
    }

    public static long parseLong(char[] cArr, int i, int i2) {
        int i3 = i2 - 9;
        return (parseInt(cArr, i, i3) * L_BILLION) + parseInt(cArr, i + i3, 9);
    }

    public static long parseLong19(char[] cArr, int i, boolean z) {
        long j = 0;
        for (int i2 = 0; i2 < 19; i2++) {
            j = (j * 10) + (cArr[i + i2] - '0');
        }
        return z ? -j : j;
    }

    public static long parseLong(String str) {
        if (str.length() <= 9) {
            return parseInt(str);
        }
        return Long.parseLong(str);
    }

    public static boolean inLongRange(char[] cArr, int i, int i2, boolean z) {
        String str = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str.length();
        if (i2 < length) {
            return true;
        }
        if (i2 > length) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            int charAt = cArr[i + i3] - str.charAt(i3);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static boolean inLongRange(String str, boolean z) {
        String str2 = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str2.length();
        int length2 = str.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            int charAt = str.charAt(i) - str2.charAt(i);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static int parseAsInt(String str, int i) {
        String trim;
        int length;
        if (str == null || (length = (trim = str.trim()).length()) == 0) {
            return i;
        }
        int i2 = 0;
        char charAt = trim.charAt(0);
        if (charAt == '+') {
            trim = trim.substring(1);
            length = trim.length();
        } else if (charAt == '-') {
            i2 = 1;
        }
        while (i2 < length) {
            char charAt2 = trim.charAt(i2);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (int) parseDouble(trim, true);
                } catch (NumberFormatException unused) {
                    return i;
                }
            }
            i2++;
        }
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException unused2) {
            return i;
        }
    }

    public static long parseAsLong(String str, long j) {
        String trim;
        int length;
        if (str == null || (length = (trim = str.trim()).length()) == 0) {
            return j;
        }
        int i = 0;
        char charAt = trim.charAt(0);
        if (charAt == '+') {
            trim = trim.substring(1);
            length = trim.length();
        } else if (charAt == '-') {
            i = 1;
        }
        while (i < length) {
            char charAt2 = trim.charAt(i);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (long) parseDouble(trim, true);
                } catch (NumberFormatException unused) {
                    return j;
                }
            }
            i++;
        }
        try {
            return Long.parseLong(trim);
        } catch (NumberFormatException unused2) {
            return j;
        }
    }

    public static double parseAsDouble(String str, double d) {
        return parseAsDouble(str, d, false);
    }

    public static double parseAsDouble(String str, double d, boolean z) {
        if (str == null) {
            return d;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return d;
        }
        try {
            return parseDouble(trim, z);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static double parseDouble(String str) throws NumberFormatException {
        return parseDouble(str, false);
    }

    public static double parseDouble(String str, boolean z) throws NumberFormatException {
        return z ? JavaDoubleParser.parseDouble(str) : Double.parseDouble(str);
    }

    public static float parseFloat(String str) throws NumberFormatException {
        return parseFloat(str, false);
    }

    public static float parseFloat(String str, boolean z) throws NumberFormatException {
        return z ? JavaFloatParser.parseFloat(str) : Float.parseFloat(str);
    }

    public static BigDecimal parseBigDecimal(String str) throws NumberFormatException {
        return BigDecimalParser.parse(str);
    }

    public static BigDecimal parseBigDecimal(String str, boolean z) throws NumberFormatException {
        if (z) {
            return BigDecimalParser.parseWithFastParser(str);
        }
        return BigDecimalParser.parse(str);
    }

    public static BigDecimal parseBigDecimal(char[] cArr, int i, int i2) throws NumberFormatException {
        return BigDecimalParser.parse(cArr, i, i2);
    }

    public static BigDecimal parseBigDecimal(char[] cArr, int i, int i2, boolean z) throws NumberFormatException {
        if (z) {
            return BigDecimalParser.parseWithFastParser(cArr, i, i2);
        }
        return BigDecimalParser.parse(cArr, i, i2);
    }

    public static BigDecimal parseBigDecimal(char[] cArr) throws NumberFormatException {
        return BigDecimalParser.parse(cArr);
    }

    public static BigDecimal parseBigDecimal(char[] cArr, boolean z) throws NumberFormatException {
        if (z) {
            return BigDecimalParser.parseWithFastParser(cArr, 0, cArr.length);
        }
        return BigDecimalParser.parse(cArr);
    }

    public static BigInteger parseBigInteger(String str) throws NumberFormatException {
        return new BigInteger(str);
    }

    public static BigInteger parseBigInteger(String str, boolean z) throws NumberFormatException {
        if (z) {
            return BigIntegerParser.parseWithFastParser(str);
        }
        return parseBigInteger(str);
    }

    public static BigInteger parseBigIntegerWithRadix(String str, int i, boolean z) throws NumberFormatException {
        if (z) {
            return BigIntegerParser.parseWithFastParser(str, i);
        }
        return new BigInteger(str, i);
    }
}
