package com.tom_roush.pdfbox.util;

import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
public class NumberFormatUtil {
    private static final int MAX_FRACTION_DIGITS = 5;
    private static final long[] POWER_OF_TENS;
    private static final int[] POWER_OF_TENS_INT;

    static {
        long[] jArr = new long[19];
        POWER_OF_TENS = jArr;
        jArr[0] = 1;
        int i = 1;
        int i2 = 1;
        while (true) {
            long[] jArr2 = POWER_OF_TENS;
            if (i2 >= jArr2.length) {
                break;
            }
            jArr2[i2] = jArr2[i2 - 1] * 10;
            i2++;
        }
        int[] iArr = new int[10];
        POWER_OF_TENS_INT = iArr;
        iArr[0] = 1;
        while (true) {
            int[] iArr2 = POWER_OF_TENS_INT;
            if (i >= iArr2.length) {
                return;
            }
            iArr2[i] = iArr2[i - 1] * 10;
            i++;
        }
    }

    private NumberFormatUtil() {
    }

    public static int formatFloatFast(float f, int i, byte[] bArr) {
        int i2;
        if (Float.isNaN(f) || Float.isInfinite(f) || f > 9.223372E18f || f <= -9.223372E18f || i > 5) {
            return -1;
        }
        long j = f;
        if (f < 0.0f) {
            bArr[0] = BuiltinOptions.GreaterEqualOptions;
            j = -j;
            i2 = 1;
        } else {
            i2 = 0;
        }
        double abs = Math.abs(f) - j;
        long j2 = POWER_OF_TENS[i];
        long j3 = (long) ((abs * j2) + 0.5d);
        if (j3 >= j2) {
            j++;
            j3 -= j2;
        }
        long j4 = j3;
        int formatPositiveNumber = formatPositiveNumber(j, getExponent(j), false, bArr, i2);
        if (j4 <= 0 || i <= 0) {
            return formatPositiveNumber;
        }
        bArr[formatPositiveNumber] = BuiltinOptions.LessEqualOptions;
        return formatPositiveNumber(j4, i - 1, true, bArr, formatPositiveNumber + 1);
    }

    private static int formatPositiveNumber(long j, int i, boolean z, byte[] bArr, int i2) {
        long j2;
        while (j > 2147483647L && (!z || j > 0)) {
            long j3 = POWER_OF_TENS[i];
            j -= j3 * (j / j3);
            bArr[i2] = (byte) (j2 + 48);
            i--;
            i2++;
        }
        int i3 = (int) j;
        while (i >= 0 && (!z || i3 > 0)) {
            int i4 = POWER_OF_TENS_INT[i];
            int i5 = i3 / i4;
            i3 -= i4 * i5;
            bArr[i2] = (byte) (i5 + 48);
            i--;
            i2++;
        }
        return i2;
    }

    private static int getExponent(long j) {
        int i = 0;
        while (true) {
            long[] jArr = POWER_OF_TENS;
            if (i < jArr.length - 1) {
                int i2 = i + 1;
                if (j < jArr[i2]) {
                    return i;
                }
                i = i2;
            } else {
                return jArr.length - 1;
            }
        }
    }
}
