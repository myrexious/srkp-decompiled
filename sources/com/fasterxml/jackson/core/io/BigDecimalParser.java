package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.io.doubleparser.JavaBigDecimalParser;
import java.math.BigDecimal;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class BigDecimalParser {
    static final int MAX_CHARS_TO_REPORT = 1000;

    private BigDecimalParser() {
    }

    public static BigDecimal parse(String str) {
        return parse(str.toCharArray());
    }

    public static BigDecimal parse(char[] cArr, int i, int i2) {
        String str;
        try {
            if (i2 < 500) {
                return new BigDecimal(cArr, i, i2);
            }
            return parseBigDecimal(cArr, i, i2, i2 / 10);
        } catch (ArithmeticException | NumberFormatException e) {
            String message = e.getMessage();
            if (message == null) {
                message = "Not a valid number representation";
            }
            if (i2 <= 1000) {
                str = new String(cArr, i, i2);
            } else {
                str = new String(Arrays.copyOfRange(cArr, i, 1000)) + "(truncated, full length is " + cArr.length + " chars)";
            }
            throw new NumberFormatException("Value \"" + str + "\" can not be represented as `java.math.BigDecimal`, reason: " + message);
        }
    }

    public static BigDecimal parse(char[] cArr) {
        return parse(cArr, 0, cArr.length);
    }

    public static BigDecimal parseWithFastParser(String str) {
        try {
            return JavaBigDecimalParser.parseBigDecimal(str);
        } catch (NumberFormatException e) {
            if (str.length() > 1000) {
                str = str.substring(0, 1000) + " [truncated]";
            }
            throw new NumberFormatException("Value \"" + str + "\" can not be represented as `java.math.BigDecimal`, reason: " + e.getMessage());
        }
    }

    public static BigDecimal parseWithFastParser(char[] cArr, int i, int i2) {
        try {
            return JavaBigDecimalParser.parseBigDecimal(cArr, i, i2);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Value \"" + (i2 <= 1000 ? new String(cArr, i, i2) : new String(cArr, i, 1000).concat(" [truncated]")) + "\" can not be represented as `java.math.BigDecimal`, reason: " + e.getMessage());
        }
    }

    private static BigDecimal parseBigDecimal(char[] cArr, int i, int i2, int i3) {
        int i4;
        int i5;
        BigDecimal bigDecimalRec;
        int i6 = i + i2;
        int i7 = i;
        int i8 = i7;
        int i9 = -1;
        int i10 = -1;
        int i11 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (i7 < i6) {
            char c = cArr[i7];
            if (c != '+') {
                if (c == 'E' || c == 'e') {
                    if (i9 >= 0) {
                        throw new NumberFormatException("Multiple exponent markers");
                    }
                    i9 = i7;
                } else if (c != '-') {
                    if (c != '.') {
                        if (i10 >= 0 && i9 == -1) {
                            i11++;
                        }
                    } else if (i10 >= 0) {
                        throw new NumberFormatException("Multiple decimal points");
                    } else {
                        i10 = i7;
                    }
                } else if (i9 >= 0) {
                    if (z2) {
                        throw new NumberFormatException("Multiple signs in exponent");
                    }
                    z2 = true;
                } else if (z) {
                    throw new NumberFormatException("Multiple signs in number");
                } else {
                    i8 = i7 + 1;
                    z = true;
                    z3 = true;
                }
            } else if (i9 >= 0) {
                if (z2) {
                    throw new NumberFormatException("Multiple signs in exponent");
                }
                z2 = true;
            } else if (z) {
                throw new NumberFormatException("Multiple signs in number");
            } else {
                i8 = i7 + 1;
                z = true;
            }
            i7++;
        }
        if (i9 >= 0) {
            i4 = 1;
            i5 = Integer.parseInt(new String(cArr, i9 + 1, (i6 - i9) - 1));
            i11 = adjustScale(i11, i5);
            i6 = i9;
        } else {
            i4 = 1;
            i5 = 0;
        }
        if (i10 >= 0) {
            int i12 = (i6 - i10) - i4;
            bigDecimalRec = toBigDecimalRec(cArr, i8, i10 - i8, i5, i3).add(toBigDecimalRec(cArr, i10 + i4, i12, i5 - i12, i3));
        } else {
            bigDecimalRec = toBigDecimalRec(cArr, i8, i6 - i8, i5, i3);
        }
        if (i11 != 0) {
            bigDecimalRec = bigDecimalRec.setScale(i11);
        }
        return z3 ? bigDecimalRec.negate() : bigDecimalRec;
    }

    private static int adjustScale(int i, long j) {
        long j2 = i - j;
        if (j2 > 2147483647L || j2 < -2147483648L) {
            throw new NumberFormatException("Scale out of range: " + j2 + " while adjusting scale " + i + " to exponent " + j);
        }
        return (int) j2;
    }

    private static BigDecimal toBigDecimalRec(char[] cArr, int i, int i2, int i3, int i4) {
        if (i2 > i4) {
            int i5 = i2 / 2;
            return toBigDecimalRec(cArr, i, i5, (i3 + i2) - i5, i4).add(toBigDecimalRec(cArr, i + i5, i2 - i5, i3, i4));
        } else if (i2 == 0) {
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal(cArr, i, i2).scaleByPowerOfTen(i3);
        }
    }
}
