package com.fasterxml.jackson.core.io.schubfach;

import com.google.firebase.crashlytics.internal.common.IdManager;

/* loaded from: classes.dex */
public final class DoubleToDecimal {
    private static final int BQ_MASK = 2047;
    private static final long C_MIN = 4503599627370496L;
    static final long C_TINY = 3;
    static final int E_MAX = 309;
    static final int E_MIN = -323;
    static final int H = 17;
    static final int K_MAX = 292;
    static final int K_MIN = -324;
    private static final int MASK_28 = 268435455;
    private static final long MASK_63 = Long.MAX_VALUE;
    private static final int MINUS_INF = 4;
    private static final int MINUS_ZERO = 2;
    private static final int NAN = 5;
    private static final int NON_SPECIAL = 0;
    static final int P = 53;
    private static final int PLUS_INF = 3;
    private static final int PLUS_ZERO = 1;
    static final int Q_MAX = 971;
    static final int Q_MIN = -1074;
    private static final long T_MASK = 4503599627370495L;
    private static final int W = 11;
    public final int MAX_CHARS = 24;
    private final byte[] bytes = new byte[24];
    private int index;

    private DoubleToDecimal() {
    }

    public static String toString(double d) {
        return new DoubleToDecimal().toDecimalString(d);
    }

    private String toDecimalString(double d) {
        int decimal = toDecimal(d);
        if (decimal != 0) {
            return decimal != 1 ? decimal != 2 ? decimal != 3 ? decimal != 4 ? "NaN" : "-Infinity" : "Infinity" : "-0.0" : IdManager.DEFAULT_VERSION_NAME;
        }
        return charsToString();
    }

    private int toDecimal(double d) {
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        long j = T_MASK & doubleToRawLongBits;
        int i = ((int) (doubleToRawLongBits >>> 52)) & BQ_MASK;
        if (i >= BQ_MASK) {
            if (j != 0) {
                return 5;
            }
            return doubleToRawLongBits > 0 ? 3 : 4;
        }
        this.index = -1;
        int i2 = (doubleToRawLongBits > 0L ? 1 : (doubleToRawLongBits == 0L ? 0 : -1));
        if (i2 < 0) {
            append(45);
        }
        if (i == 0) {
            if (j == 0) {
                return i2 == 0 ? 1 : 2;
            } else if (j < C_TINY) {
                return toDecimal(Q_MIN, j * 10, -1);
            } else {
                return toDecimal(Q_MIN, j, 0);
            }
        }
        int i3 = 1075 - i;
        long j2 = j | C_MIN;
        if ((i3 < 53) & (i3 > 0)) {
            long j3 = j2 >> i3;
            if ((j3 << i3) == j2) {
                return toChars(j3, 0);
            }
        }
        return toDecimal(-i3, j2, 0);
    }

    private int toDecimal(int i, long j, int i2) {
        long j2;
        int flog10threeQuartersPow2;
        int i3 = ((int) j) & 1;
        long j3 = j << 2;
        long j4 = j3 + 2;
        if ((j != C_MIN) | (i == Q_MIN)) {
            j2 = j3 - 2;
            flog10threeQuartersPow2 = MathUtils.flog10pow2(i);
        } else {
            j2 = j3 - 1;
            flog10threeQuartersPow2 = MathUtils.flog10threeQuartersPow2(i);
        }
        int flog2pow10 = i + MathUtils.flog2pow10(-flog10threeQuartersPow2) + 2;
        long g1 = MathUtils.g1(flog10threeQuartersPow2);
        long g0 = MathUtils.g0(flog10threeQuartersPow2);
        long rop = rop(g1, g0, j3 << flog2pow10);
        long rop2 = rop(g1, g0, j2 << flog2pow10);
        long rop3 = rop(g1, g0, j4 << flog2pow10);
        long j5 = rop >> 2;
        if (j5 >= 100) {
            long multiplyHigh = MathUtils.multiplyHigh(j5, 1844674407370955168L) * 10;
            long j6 = multiplyHigh + 10;
            int i4 = flog10threeQuartersPow2;
            long j7 = i3;
            boolean z = rop2 + j7 <= (multiplyHigh << 2);
            if (z != ((j6 << 2) + j7 <= rop3)) {
                if (!z) {
                    multiplyHigh = j6;
                }
                return toChars(multiplyHigh, i4);
            }
            flog10threeQuartersPow2 = i4;
        }
        long j8 = j5 + 1;
        long j9 = i3;
        boolean z2 = rop2 + j9 <= (j5 << 2);
        if (z2 != ((j8 << 2) + j9 <= rop3)) {
            if (!z2) {
                j5 = j8;
            }
            return toChars(j5, flog10threeQuartersPow2 + i2);
        }
        int i5 = ((rop - ((j5 + j8) << 1)) > 0L ? 1 : ((rop - ((j5 + j8) << 1)) == 0L ? 0 : -1));
        if (i5 >= 0 && (i5 != 0 || (1 & j5) != 0)) {
            j5 = j8;
        }
        return toChars(j5, flog10threeQuartersPow2 + i2);
    }

    private static long rop(long j, long j2, long j3) {
        long multiplyHigh = MathUtils.multiplyHigh(j2, j3);
        long j4 = j * j3;
        long multiplyHigh2 = MathUtils.multiplyHigh(j, j3);
        long j5 = (j4 >>> 1) + multiplyHigh;
        return (multiplyHigh2 + (j5 >>> 63)) | (((j5 & Long.MAX_VALUE) + Long.MAX_VALUE) >>> 63);
    }

    private int toChars(long j, int i) {
        int flog10pow2 = MathUtils.flog10pow2(64 - Long.numberOfLeadingZeros(j));
        if (j >= MathUtils.pow10(flog10pow2)) {
            flog10pow2++;
        }
        long pow10 = j * MathUtils.pow10(17 - flog10pow2);
        int i2 = i + flog10pow2;
        long multiplyHigh = MathUtils.multiplyHigh(pow10, 193428131138340668L) >>> 20;
        int i3 = (int) (pow10 - (100000000 * multiplyHigh));
        int i4 = (int) ((1441151881 * multiplyHigh) >>> 57);
        int i5 = (int) (multiplyHigh - (100000000 * i4));
        if (i2 <= 0 || i2 > 7) {
            if (-3 < i2 && i2 <= 0) {
                return toChars2(i4, i5, i3, i2);
            }
            return toChars3(i4, i5, i3, i2);
        }
        return toChars1(i4, i5, i3, i2);
    }

    private int toChars1(int i, int i2, int i3, int i4) {
        appendDigit(i);
        int y = y(i2);
        int i5 = 1;
        while (i5 < i4) {
            int i6 = y * 10;
            appendDigit(i6 >>> 28);
            y = i6 & MASK_28;
            i5++;
        }
        append(46);
        while (i5 <= 8) {
            int i7 = y * 10;
            appendDigit(i7 >>> 28);
            y = i7 & MASK_28;
            i5++;
        }
        lowDigits(i3);
        return 0;
    }

    private int toChars2(int i, int i2, int i3, int i4) {
        appendDigit(0);
        append(46);
        while (i4 < 0) {
            appendDigit(0);
            i4++;
        }
        appendDigit(i);
        append8Digits(i2);
        lowDigits(i3);
        return 0;
    }

    private int toChars3(int i, int i2, int i3, int i4) {
        appendDigit(i);
        append(46);
        append8Digits(i2);
        lowDigits(i3);
        exponent(i4 - 1);
        return 0;
    }

    private void lowDigits(int i) {
        if (i != 0) {
            append8Digits(i);
        }
        removeTrailingZeroes();
    }

    private void append8Digits(int i) {
        int y = y(i);
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = y * 10;
            appendDigit(i3 >>> 28);
            y = i3 & MASK_28;
        }
    }

    private void removeTrailingZeroes() {
        int i;
        byte b;
        while (true) {
            byte[] bArr = this.bytes;
            i = this.index;
            b = bArr[i];
            if (b != 48) {
                break;
            }
            this.index = i - 1;
        }
        if (b == 46) {
            this.index = i + 1;
        }
    }

    private int y(int i) {
        return ((int) (MathUtils.multiplyHigh((i + 1) << 28, 193428131138340668L) >>> 20)) - 1;
    }

    private void exponent(int i) {
        append(69);
        if (i < 0) {
            append(45);
            i = -i;
        }
        if (i < 10) {
            appendDigit(i);
            return;
        }
        if (i >= 100) {
            int i2 = (i * 1311) >>> 17;
            appendDigit(i2);
            i -= i2 * 100;
        }
        int i3 = (i * 103) >>> 10;
        appendDigit(i3);
        appendDigit(i - (i3 * 10));
    }

    private void append(int i) {
        byte[] bArr = this.bytes;
        int i2 = this.index + 1;
        this.index = i2;
        bArr[i2] = (byte) i;
    }

    private void appendDigit(int i) {
        byte[] bArr = this.bytes;
        int i2 = this.index + 1;
        this.index = i2;
        bArr[i2] = (byte) (i + 48);
    }

    private String charsToString() {
        return new String(this.bytes, 0, 0, this.index + 1);
    }
}
