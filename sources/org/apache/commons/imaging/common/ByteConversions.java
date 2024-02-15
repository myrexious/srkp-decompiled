package org.apache.commons.imaging.common;

import java.nio.ByteOrder;
import kotlin.UByte;

/* loaded from: classes2.dex */
public final class ByteConversions {
    private ByteConversions() {
    }

    public static byte[] toBytes(short s, ByteOrder byteOrder) {
        byte[] bArr = new byte[2];
        toBytes(s, byteOrder, bArr, 0);
        return bArr;
    }

    public static byte[] toBytes(short[] sArr, ByteOrder byteOrder) {
        return toBytes(sArr, 0, sArr.length, byteOrder);
    }

    private static byte[] toBytes(short[] sArr, int i, int i2, ByteOrder byteOrder) {
        byte[] bArr = new byte[i2 * 2];
        for (int i3 = 0; i3 < i2; i3++) {
            toBytes(sArr[i + i3], byteOrder, bArr, i3 * 2);
        }
        return bArr;
    }

    private static void toBytes(short s, ByteOrder byteOrder, byte[] bArr, int i) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            bArr[i + 0] = (byte) (s >> 8);
            bArr[i + 1] = (byte) (s >> 0);
            return;
        }
        bArr[i + 1] = (byte) (s >> 8);
        bArr[i + 0] = (byte) (s >> 0);
    }

    public static byte[] toBytes(int i, ByteOrder byteOrder) {
        byte[] bArr = new byte[4];
        toBytes(i, byteOrder, bArr, 0);
        return bArr;
    }

    public static byte[] toBytes(int[] iArr, ByteOrder byteOrder) {
        return toBytes(iArr, 0, iArr.length, byteOrder);
    }

    private static byte[] toBytes(int[] iArr, int i, int i2, ByteOrder byteOrder) {
        byte[] bArr = new byte[i2 * 4];
        for (int i3 = 0; i3 < i2; i3++) {
            toBytes(iArr[i + i3], byteOrder, bArr, i3 * 4);
        }
        return bArr;
    }

    private static void toBytes(int i, ByteOrder byteOrder, byte[] bArr, int i2) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            bArr[i2 + 0] = (byte) (i >> 24);
            bArr[i2 + 1] = (byte) (i >> 16);
            bArr[i2 + 2] = (byte) (i >> 8);
            bArr[i2 + 3] = (byte) (i >> 0);
            return;
        }
        bArr[i2 + 3] = (byte) (i >> 24);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 0] = (byte) (i >> 0);
    }

    public static byte[] toBytes(float f, ByteOrder byteOrder) {
        byte[] bArr = new byte[4];
        toBytes(f, byteOrder, bArr, 0);
        return bArr;
    }

    public static byte[] toBytes(float[] fArr, ByteOrder byteOrder) {
        return toBytes(fArr, 0, fArr.length, byteOrder);
    }

    private static byte[] toBytes(float[] fArr, int i, int i2, ByteOrder byteOrder) {
        byte[] bArr = new byte[i2 * 4];
        for (int i3 = 0; i3 < i2; i3++) {
            toBytes(fArr[i + i3], byteOrder, bArr, i3 * 4);
        }
        return bArr;
    }

    private static void toBytes(float f, ByteOrder byteOrder, byte[] bArr, int i) {
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            bArr[i + 0] = (byte) ((floatToRawIntBits >> 0) & 255);
            bArr[i + 1] = (byte) ((floatToRawIntBits >> 8) & 255);
            bArr[i + 2] = (byte) ((floatToRawIntBits >> 16) & 255);
            bArr[i + 3] = (byte) ((floatToRawIntBits >> 24) & 255);
            return;
        }
        bArr[i + 3] = (byte) ((floatToRawIntBits >> 0) & 255);
        bArr[i + 2] = (byte) ((floatToRawIntBits >> 8) & 255);
        bArr[i + 1] = (byte) ((floatToRawIntBits >> 16) & 255);
        bArr[i + 0] = (byte) ((floatToRawIntBits >> 24) & 255);
    }

    public static byte[] toBytes(double d, ByteOrder byteOrder) {
        byte[] bArr = new byte[8];
        toBytes(d, byteOrder, bArr, 0);
        return bArr;
    }

    public static byte[] toBytes(double[] dArr, ByteOrder byteOrder) {
        return toBytes(dArr, 0, dArr.length, byteOrder);
    }

    private static byte[] toBytes(double[] dArr, int i, int i2, ByteOrder byteOrder) {
        byte[] bArr = new byte[i2 * 8];
        for (int i3 = 0; i3 < i2; i3++) {
            toBytes(dArr[i + i3], byteOrder, bArr, i3 * 8);
        }
        return bArr;
    }

    private static void toBytes(double d, ByteOrder byteOrder, byte[] bArr, int i) {
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            bArr[i + 0] = (byte) ((doubleToRawLongBits >> 0) & 255);
            bArr[i + 1] = (byte) ((doubleToRawLongBits >> 8) & 255);
            bArr[i + 2] = (byte) ((doubleToRawLongBits >> 16) & 255);
            bArr[i + 3] = (byte) ((doubleToRawLongBits >> 24) & 255);
            bArr[i + 4] = (byte) ((doubleToRawLongBits >> 32) & 255);
            bArr[i + 5] = (byte) ((doubleToRawLongBits >> 40) & 255);
            bArr[i + 6] = (byte) ((doubleToRawLongBits >> 48) & 255);
            bArr[i + 7] = (byte) ((doubleToRawLongBits >> 56) & 255);
            return;
        }
        bArr[i + 7] = (byte) ((doubleToRawLongBits >> 0) & 255);
        bArr[i + 6] = (byte) ((doubleToRawLongBits >> 8) & 255);
        bArr[i + 5] = (byte) ((doubleToRawLongBits >> 16) & 255);
        bArr[i + 4] = (byte) ((doubleToRawLongBits >> 24) & 255);
        bArr[i + 3] = (byte) ((doubleToRawLongBits >> 32) & 255);
        bArr[i + 2] = (byte) ((doubleToRawLongBits >> 40) & 255);
        bArr[i + 1] = (byte) ((doubleToRawLongBits >> 48) & 255);
        bArr[i + 0] = (byte) ((doubleToRawLongBits >> 56) & 255);
    }

    public static byte[] toBytes(RationalNumber rationalNumber, ByteOrder byteOrder) {
        byte[] bArr = new byte[8];
        toBytes(rationalNumber, byteOrder, bArr, 0);
        return bArr;
    }

    public static byte[] toBytes(RationalNumber[] rationalNumberArr, ByteOrder byteOrder) {
        return toBytes(rationalNumberArr, 0, rationalNumberArr.length, byteOrder);
    }

    private static byte[] toBytes(RationalNumber[] rationalNumberArr, int i, int i2, ByteOrder byteOrder) {
        byte[] bArr = new byte[i2 * 8];
        for (int i3 = 0; i3 < i2; i3++) {
            toBytes(rationalNumberArr[i + i3], byteOrder, bArr, i3 * 8);
        }
        return bArr;
    }

    private static void toBytes(RationalNumber rationalNumber, ByteOrder byteOrder, byte[] bArr, int i) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            bArr[i + 0] = (byte) (rationalNumber.numerator >> 24);
            bArr[i + 1] = (byte) (rationalNumber.numerator >> 16);
            bArr[i + 2] = (byte) (rationalNumber.numerator >> 8);
            bArr[i + 3] = (byte) (rationalNumber.numerator >> 0);
            bArr[i + 4] = (byte) (rationalNumber.divisor >> 24);
            bArr[i + 5] = (byte) (rationalNumber.divisor >> 16);
            bArr[i + 6] = (byte) (rationalNumber.divisor >> 8);
            bArr[i + 7] = (byte) (rationalNumber.divisor >> 0);
            return;
        }
        bArr[i + 3] = (byte) (rationalNumber.numerator >> 24);
        bArr[i + 2] = (byte) (rationalNumber.numerator >> 16);
        bArr[i + 1] = (byte) (rationalNumber.numerator >> 8);
        bArr[i + 0] = (byte) (rationalNumber.numerator >> 0);
        bArr[i + 7] = (byte) (rationalNumber.divisor >> 24);
        bArr[i + 6] = (byte) (rationalNumber.divisor >> 16);
        bArr[i + 5] = (byte) (rationalNumber.divisor >> 8);
        bArr[i + 4] = (byte) (rationalNumber.divisor >> 0);
    }

    public static short toShort(byte[] bArr, ByteOrder byteOrder) {
        return toShort(bArr, 0, byteOrder);
    }

    private static short toShort(byte[] bArr, int i, ByteOrder byteOrder) {
        return (short) toUInt16(bArr, i, byteOrder);
    }

    public static short[] toShorts(byte[] bArr, ByteOrder byteOrder) {
        return toShorts(bArr, 0, bArr.length, byteOrder);
    }

    private static short[] toShorts(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        int i3 = i2 / 2;
        short[] sArr = new short[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            sArr[i4] = toShort(bArr, (i4 * 2) + i, byteOrder);
        }
        return sArr;
    }

    public static int toUInt16(byte[] bArr, ByteOrder byteOrder) {
        return toUInt16(bArr, 0, byteOrder);
    }

    public static int toUInt16(byte[] bArr, int i, ByteOrder byteOrder) {
        int i2 = bArr[i + 0] & UByte.MAX_VALUE;
        int i3 = bArr[i + 1] & UByte.MAX_VALUE;
        return byteOrder == ByteOrder.BIG_ENDIAN ? i3 | (i2 << 8) : (i3 << 8) | i2;
    }

    public static int[] toUInt16s(byte[] bArr, ByteOrder byteOrder) {
        return toUInt16s(bArr, 0, bArr.length, byteOrder);
    }

    private static int[] toUInt16s(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        int i3 = i2 / 2;
        int[] iArr = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i4] = toUInt16(bArr, (i4 * 2) + i, byteOrder);
        }
        return iArr;
    }

    public static int toInt(byte[] bArr, ByteOrder byteOrder) {
        return toInt(bArr, 0, byteOrder);
    }

    public static int toInt(byte[] bArr, int i, ByteOrder byteOrder) {
        int i2 = bArr[i + 0] & UByte.MAX_VALUE;
        int i3 = bArr[i + 1] & UByte.MAX_VALUE;
        int i4 = bArr[i + 2] & UByte.MAX_VALUE;
        int i5 = bArr[i + 3] & UByte.MAX_VALUE;
        return byteOrder == ByteOrder.BIG_ENDIAN ? i5 | (i2 << 24) | (i3 << 16) | (i4 << 8) : (i5 << 24) | (i4 << 16) | (i3 << 8) | i2;
    }

    public static int[] toInts(byte[] bArr, ByteOrder byteOrder) {
        return toInts(bArr, 0, bArr.length, byteOrder);
    }

    private static int[] toInts(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        int i3 = i2 / 4;
        int[] iArr = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i4] = toInt(bArr, (i4 * 4) + i, byteOrder);
        }
        return iArr;
    }

    public static float toFloat(byte[] bArr, ByteOrder byteOrder) {
        return toFloat(bArr, 0, byteOrder);
    }

    private static float toFloat(byte[] bArr, int i, ByteOrder byteOrder) {
        int i2;
        int i3 = bArr[i + 0] & UByte.MAX_VALUE;
        int i4 = bArr[i + 1] & UByte.MAX_VALUE;
        int i5 = bArr[i + 2] & UByte.MAX_VALUE;
        int i6 = bArr[i + 3] & UByte.MAX_VALUE;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            i2 = (i6 << 0) | (i3 << 24) | (i4 << 16) | (i5 << 8);
        } else {
            i2 = (i6 << 24) | (i5 << 16) | (i4 << 8) | (i3 << 0);
        }
        return Float.intBitsToFloat(i2);
    }

    public static float[] toFloats(byte[] bArr, ByteOrder byteOrder) {
        return toFloats(bArr, 0, bArr.length, byteOrder);
    }

    private static float[] toFloats(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        int i3 = i2 / 4;
        float[] fArr = new float[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            fArr[i4] = toFloat(bArr, (i4 * 4) + i, byteOrder);
        }
        return fArr;
    }

    public static double toDouble(byte[] bArr, ByteOrder byteOrder) {
        return toDouble(bArr, 0, byteOrder);
    }

    private static double toDouble(byte[] bArr, int i, ByteOrder byteOrder) {
        long j;
        long j2 = bArr[i + 0] & 255;
        long j3 = bArr[i + 1] & 255;
        long j4 = bArr[i + 2] & 255;
        long j5 = bArr[i + 3] & 255;
        long j6 = bArr[i + 4] & 255;
        long j7 = bArr[i + 5] & 255;
        long j8 = bArr[i + 6] & 255;
        long j9 = 255 & bArr[i + 7];
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            j = (j2 << 56) | (j3 << 48) | (j4 << 40) | (j5 << 32) | (j6 << 24) | (j7 << 16) | (j8 << 8) | (j9 << 0);
        } else {
            j = (j2 << 0) | (j9 << 56) | (j8 << 48) | (j7 << 40) | (j6 << 32) | (j5 << 24) | (j4 << 16) | (j3 << 8);
        }
        return Double.longBitsToDouble(j);
    }

    public static double[] toDoubles(byte[] bArr, ByteOrder byteOrder) {
        return toDoubles(bArr, 0, bArr.length, byteOrder);
    }

    private static double[] toDoubles(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        int i3 = i2 / 8;
        double[] dArr = new double[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            dArr[i4] = toDouble(bArr, (i4 * 8) + i, byteOrder);
        }
        return dArr;
    }

    public static RationalNumber toRational(byte[] bArr, ByteOrder byteOrder, boolean z) {
        return toRational(bArr, 0, byteOrder, z);
    }

    private static RationalNumber toRational(byte[] bArr, int i, ByteOrder byteOrder, boolean z) {
        int i2;
        int i3;
        int i4 = bArr[i + 0] & UByte.MAX_VALUE;
        int i5 = bArr[i + 1] & UByte.MAX_VALUE;
        int i6 = bArr[i + 2] & UByte.MAX_VALUE;
        int i7 = bArr[i + 3] & UByte.MAX_VALUE;
        int i8 = bArr[i + 4] & UByte.MAX_VALUE;
        int i9 = bArr[i + 5] & UByte.MAX_VALUE;
        int i10 = bArr[i + 6] & UByte.MAX_VALUE;
        int i11 = bArr[i + 7] & UByte.MAX_VALUE;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            i2 = (i4 << 24) | (i5 << 16) | (i6 << 8) | i7;
            i3 = i11 | (i8 << 24) | (i9 << 16) | (i10 << 8);
        } else {
            i2 = (i7 << 24) | (i6 << 16) | (i5 << 8) | i4;
            i3 = (i11 << 24) | (i10 << 16) | (i9 << 8) | i8;
        }
        return new RationalNumber(i2, i3, z);
    }

    public static RationalNumber[] toRationals(byte[] bArr, ByteOrder byteOrder, boolean z) {
        return toRationals(bArr, 0, bArr.length, byteOrder, z);
    }

    private static RationalNumber[] toRationals(byte[] bArr, int i, int i2, ByteOrder byteOrder, boolean z) {
        int i3 = i2 / 8;
        RationalNumber[] rationalNumberArr = new RationalNumber[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            rationalNumberArr[i4] = toRational(bArr, (i4 * 8) + i, byteOrder, z);
        }
        return rationalNumberArr;
    }
}
