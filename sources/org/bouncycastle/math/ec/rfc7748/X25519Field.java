package org.bouncycastle.math.ec.rfc7748;

import kotlin.UByte;
import org.bouncycastle.math.raw.Mod;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public abstract class X25519Field {
    private static final int M24 = 16777215;
    private static final int M25 = 33554431;
    private static final int M26 = 67108863;
    private static final int[] P32 = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    private static final int[] ROOT_NEG_ONE = {34513072, 59165138, 4688974, 3500415, 6194736, 33281959, 54535759, 32551604, 163342, 5703241};
    public static final int SIZE = 10;

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i = 0; i < 10; i++) {
            iArr3[i] = iArr[i] + iArr2[i];
        }
    }

    public static void addOne(int[] iArr) {
        iArr[0] = iArr[0] + 1;
    }

    public static void addOne(int[] iArr, int i) {
        iArr[i] = iArr[i] + 1;
    }

    public static void apm(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        for (int i = 0; i < 10; i++) {
            int i2 = iArr[i];
            int i3 = iArr2[i];
            iArr3[i] = i2 + i3;
            iArr4[i] = i2 - i3;
        }
    }

    public static int areEqual(int[] iArr, int[] iArr2) {
        int i = 0;
        for (int i2 = 0; i2 < 10; i2++) {
            i |= iArr[i2] ^ iArr2[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static boolean areEqualVar(int[] iArr, int[] iArr2) {
        return areEqual(iArr, iArr2) != 0;
    }

    public static void carry(int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = i3 + (i2 >> 26);
        int i12 = i2 & M26;
        int i13 = i5 + (i4 >> 26);
        int i14 = i4 & M26;
        int i15 = i8 + (i7 >> 26);
        int i16 = i7 & M26;
        int i17 = i10 + (i9 >> 26);
        int i18 = i9 & M26;
        int i19 = i14 + (i11 >> 25);
        int i20 = i11 & M25;
        int i21 = i6 + (i13 >> 25);
        int i22 = i13 & M25;
        int i23 = i18 + (i15 >> 25);
        int i24 = i15 & M25;
        int i25 = i + ((i17 >> 25) * 38);
        int i26 = i17 & M25;
        int i27 = i12 + (i25 >> 26);
        int i28 = i25 & M26;
        int i29 = i16 + (i21 >> 26);
        int i30 = i21 & M26;
        int i31 = i20 + (i27 >> 26);
        int i32 = i27 & M26;
        int i33 = i22 + (i19 >> 26);
        int i34 = i19 & M26;
        int i35 = i24 + (i29 >> 26);
        int i36 = i29 & M26;
        int i37 = i23 & M26;
        iArr[0] = i28;
        iArr[1] = i32;
        iArr[2] = i31;
        iArr[3] = i34;
        iArr[4] = i33;
        iArr[5] = i30;
        iArr[6] = i36;
        iArr[7] = i35;
        iArr[8] = i37;
        iArr[9] = i26 + (i23 >> 26);
    }

    public static void cmov(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 0; i4 < 10; i4++) {
            int i5 = i3 + i4;
            int i6 = iArr2[i5];
            iArr2[i5] = i6 ^ ((iArr[i2 + i4] ^ i6) & i);
        }
    }

    public static void cnegate(int i, int[] iArr) {
        int i2 = 0 - i;
        for (int i3 = 0; i3 < 10; i3++) {
            iArr[i3] = (iArr[i3] ^ i2) - i2;
        }
    }

    public static void copy(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 0; i3 < 10; i3++) {
            iArr2[i2 + i3] = iArr[i + i3];
        }
    }

    public static int[] create() {
        return new int[10];
    }

    public static int[] createTable(int i) {
        return new int[i * 10];
    }

    public static void cswap(int i, int[] iArr, int[] iArr2) {
        int i2 = 0 - i;
        for (int i3 = 0; i3 < 10; i3++) {
            int i4 = iArr[i3];
            int i5 = iArr2[i3];
            int i6 = (i4 ^ i5) & i2;
            iArr[i3] = i4 ^ i6;
            iArr2[i3] = i5 ^ i6;
        }
    }

    public static void decode(byte[] bArr, int i, int[] iArr) {
        decode128(bArr, i, iArr, 0);
        decode128(bArr, i + 16, iArr, 5);
        iArr[9] = iArr[9] & 16777215;
    }

    public static void decode(int[] iArr, int i, int[] iArr2) {
        decode128(iArr, i, iArr2, 0);
        decode128(iArr, i + 4, iArr2, 5);
        iArr2[9] = iArr2[9] & 16777215;
    }

    private static void decode128(byte[] bArr, int i, int[] iArr, int i2) {
        int decode32 = decode32(bArr, i + 0);
        int decode322 = decode32(bArr, i + 4);
        int decode323 = decode32(bArr, i + 8);
        int decode324 = decode32(bArr, i + 12);
        iArr[i2 + 0] = decode32 & M26;
        iArr[i2 + 1] = ((decode32 >>> 26) | (decode322 << 6)) & M26;
        iArr[i2 + 2] = ((decode323 << 12) | (decode322 >>> 20)) & M25;
        iArr[i2 + 3] = ((decode324 << 19) | (decode323 >>> 13)) & M26;
        iArr[i2 + 4] = decode324 >>> 7;
    }

    private static void decode128(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = iArr[i + 0];
        int i4 = iArr[i + 1];
        int i5 = iArr[i + 2];
        int i6 = iArr[i + 3];
        iArr2[i2 + 0] = i3 & M26;
        iArr2[i2 + 1] = ((i3 >>> 26) | (i4 << 6)) & M26;
        iArr2[i2 + 2] = ((i5 << 12) | (i4 >>> 20)) & M25;
        iArr2[i2 + 3] = ((i6 << 19) | (i5 >>> 13)) & M26;
        iArr2[i2 + 4] = i6 >>> 7;
    }

    private static int decode32(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] << BuiltinOptions.BatchToSpaceNDOptions) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i2] & UByte.MAX_VALUE) << 8) | ((bArr[i3] & UByte.MAX_VALUE) << 16);
    }

    public static void encode(int[] iArr, byte[] bArr, int i) {
        encode128(iArr, 0, bArr, i);
        encode128(iArr, 5, bArr, i + 16);
    }

    public static void encode(int[] iArr, int[] iArr2, int i) {
        encode128(iArr, 0, iArr2, i);
        encode128(iArr, 5, iArr2, i + 4);
    }

    private static void encode128(int[] iArr, int i, byte[] bArr, int i2) {
        int i3 = iArr[i + 0];
        int i4 = iArr[i + 1];
        int i5 = iArr[i + 2];
        int i6 = iArr[i + 3];
        int i7 = iArr[i + 4];
        encode32((i4 << 26) | i3, bArr, i2 + 0);
        encode32((i4 >>> 6) | (i5 << 20), bArr, i2 + 4);
        encode32((i5 >>> 12) | (i6 << 13), bArr, i2 + 8);
        encode32((i7 << 7) | (i6 >>> 19), bArr, i2 + 12);
    }

    private static void encode128(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = iArr[i + 0];
        int i4 = iArr[i + 1];
        int i5 = iArr[i + 2];
        int i6 = iArr[i + 3];
        int i7 = iArr[i + 4];
        iArr2[i2 + 0] = i3 | (i4 << 26);
        iArr2[i2 + 1] = (i4 >>> 6) | (i5 << 20);
        iArr2[i2 + 2] = (i5 >>> 12) | (i6 << 13);
        iArr2[i2 + 3] = (i7 << 7) | (i6 >>> 19);
    }

    private static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    public static void inv(int[] iArr, int[] iArr2) {
        int[] create = create();
        int[] iArr3 = new int[8];
        copy(iArr, 0, create, 0);
        normalize(create);
        encode(create, iArr3, 0);
        Mod.modOddInverse(P32, iArr3, iArr3);
        decode(iArr3, 0, iArr2);
    }

    public static void invVar(int[] iArr, int[] iArr2) {
        int[] create = create();
        int[] iArr3 = new int[8];
        copy(iArr, 0, create, 0);
        normalize(create);
        encode(create, iArr3, 0);
        Mod.modOddInverseVar(P32, iArr3, iArr3);
        decode(iArr3, 0, iArr2);
    }

    public static int isOne(int[] iArr) {
        int i = iArr[0] ^ 1;
        for (int i2 = 1; i2 < 10; i2++) {
            i |= iArr[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static boolean isOneVar(int[] iArr) {
        return isOne(iArr) != 0;
    }

    public static int isZero(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 10; i2++) {
            i |= iArr[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static boolean isZeroVar(int[] iArr) {
        return isZero(iArr) != 0;
    }

    public static void mul(int[] iArr, int i, int[] iArr2) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        long j = i;
        long j2 = i4 * j;
        int i12 = ((int) j2) & M25;
        long j3 = i6 * j;
        int i13 = ((int) j3) & M25;
        long j4 = i9 * j;
        int i14 = ((int) j4) & M25;
        long j5 = i11 * j;
        int i15 = ((int) j5) & M25;
        long j6 = ((j5 >> 25) * 38) + (i2 * j);
        iArr2[0] = ((int) j6) & M26;
        long j7 = (j3 >> 25) + (i7 * j);
        iArr2[5] = ((int) j7) & M26;
        long j8 = (j6 >> 26) + (i3 * j);
        iArr2[1] = ((int) j8) & M26;
        long j9 = (j2 >> 25) + (i5 * j);
        iArr2[3] = ((int) j9) & M26;
        long j10 = (j7 >> 26) + (i8 * j);
        iArr2[6] = ((int) j10) & M26;
        long j11 = (j4 >> 25) + (i10 * j);
        iArr2[8] = ((int) j11) & M26;
        iArr2[2] = i12 + ((int) (j8 >> 26));
        iArr2[4] = i13 + ((int) (j9 >> 26));
        iArr2[7] = i14 + ((int) (j10 >> 26));
        iArr2[9] = i15 + ((int) (j11 >> 26));
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = iArr[0];
        int i2 = iArr2[0];
        int i3 = iArr[1];
        int i4 = iArr2[1];
        int i5 = iArr[2];
        int i6 = iArr2[2];
        int i7 = iArr[3];
        int i8 = iArr2[3];
        int i9 = iArr[4];
        int i10 = iArr2[4];
        int i11 = iArr[5];
        int i12 = iArr2[5];
        int i13 = iArr[6];
        int i14 = iArr2[6];
        int i15 = iArr[7];
        int i16 = iArr2[7];
        int i17 = iArr[8];
        int i18 = iArr2[8];
        int i19 = iArr[9];
        int i20 = iArr2[9];
        long j = i;
        long j2 = i2;
        long j3 = j * j2;
        long j4 = i4;
        long j5 = i3;
        long j6 = i6;
        long j7 = i5;
        long j8 = (j * j6) + (j5 * j4) + (j7 * j2);
        long j9 = i8;
        long j10 = j * j9;
        long j11 = i7;
        long j12 = (((j5 * j6) + (j7 * j4)) << 1) + j10 + (j11 * j2);
        long j13 = i10;
        long j14 = i9;
        long j15 = ((j7 * j6) << 1) + (j * j13) + (j5 * j9) + (j11 * j4) + (j2 * j14);
        long j16 = ((((j5 * j13) + (j7 * j9)) + (j11 * j6)) + (j14 * j4)) << 1;
        long j17 = (((j7 * j13) + (j14 * j6)) << 1) + (j11 * j9);
        long j18 = (j11 * j13) + (j14 * j9);
        long j19 = (j14 * j13) << 1;
        long j20 = i11;
        long j21 = i12;
        long j22 = i14;
        long j23 = i13;
        long j24 = i16;
        long j25 = i15;
        long j26 = (j20 * j24) + (j23 * j22) + (j25 * j21);
        long j27 = i18;
        long j28 = i17;
        long j29 = (((j23 * j24) + (j25 * j22)) << 1) + (j20 * j27) + (j28 * j21);
        long j30 = i20;
        long j31 = i19;
        long j32 = ((j25 * j24) << 1) + (j20 * j30) + (j23 * j27) + (j28 * j22) + (j21 * j31);
        long j33 = j3 - (((((j23 * j30) + (j25 * j27)) + (j28 * j24)) + (j31 * j22)) * 76);
        long j34 = ((j * j4) + (j5 * j2)) - (((((j25 * j30) + (j31 * j24)) << 1) + (j28 * j27)) * 38);
        long j35 = j8 - (((j28 * j30) + (j27 * j31)) * 38);
        long j36 = j12 - ((j31 * j30) * 76);
        long j37 = j16 - (j20 * j21);
        long j38 = j17 - ((j20 * j22) + (j23 * j21));
        long j39 = j18 - j26;
        long j40 = j19 - j29;
        int i21 = i + i11;
        int i22 = i3 + i13;
        int i23 = i5 + i15;
        int i24 = i6 + i16;
        int i25 = i7 + i17;
        int i26 = i9 + i19;
        long j41 = i21;
        long j42 = i2 + i12;
        long j43 = i4 + i14;
        long j44 = i22;
        long j45 = (j41 * j43) + (j44 * j42);
        long j46 = i24;
        long j47 = i23;
        long j48 = (j41 * j46) + (j44 * j43) + (j47 * j42);
        long j49 = i8 + i18;
        long j50 = i25;
        long j51 = i10 + i20;
        long j52 = i26;
        long j53 = (((j47 * j51) + (j52 * j46)) << 1) + (j50 * j49);
        long j54 = j40 + (((((j44 * j46) + (j47 * j43)) << 1) + ((j41 * j49) + (j50 * j42))) - j36);
        int i27 = ((int) j54) & M26;
        long j55 = (j54 >> 26) + (((((j47 * j46) << 1) + ((((j41 * j51) + (j44 * j49)) + (j50 * j43)) + (j42 * j52))) - j15) - j32);
        int i28 = ((int) j55) & M25;
        long j56 = j33 + ((((j55 >> 25) + (((((j44 * j51) + (j47 * j49)) + (j50 * j46)) + (j52 * j43)) << 1)) - j37) * 38);
        iArr3[0] = ((int) j56) & M26;
        long j57 = (j56 >> 26) + j34 + ((j53 - j38) * 38);
        iArr3[1] = ((int) j57) & M26;
        long j58 = (j57 >> 26) + j35 + ((((j50 * j51) + (j52 * j49)) - j39) * 38);
        iArr3[2] = ((int) j58) & M25;
        long j59 = (j58 >> 25) + j36 + ((((j52 * j51) << 1) - j40) * 38);
        iArr3[3] = ((int) j59) & M26;
        long j60 = (j59 >> 26) + j15 + (j32 * 38);
        iArr3[4] = ((int) j60) & M25;
        long j61 = (j60 >> 25) + j37 + ((j41 * j42) - j33);
        iArr3[5] = ((int) j61) & M26;
        long j62 = (j61 >> 26) + j38 + (j45 - j34);
        iArr3[6] = ((int) j62) & M26;
        long j63 = (j62 >> 26) + j39 + (j48 - j35);
        iArr3[7] = ((int) j63) & M25;
        long j64 = (j63 >> 25) + i27;
        iArr3[8] = ((int) j64) & M26;
        iArr3[9] = i28 + ((int) (j64 >> 26));
    }

    public static void negate(int[] iArr, int[] iArr2) {
        for (int i = 0; i < 10; i++) {
            iArr2[i] = -iArr[i];
        }
    }

    public static void normalize(int[] iArr) {
        int i = (iArr[9] >>> 23) & 1;
        reduce(iArr, i);
        reduce(iArr, -i);
    }

    public static void one(int[] iArr) {
        iArr[0] = 1;
        for (int i = 1; i < 10; i++) {
            iArr[i] = 0;
        }
    }

    private static void powPm5d8(int[] iArr, int[] iArr2, int[] iArr3) {
        sqr(iArr, iArr2);
        mul(iArr, iArr2, iArr2);
        int[] create = create();
        sqr(iArr2, create);
        mul(iArr, create, create);
        sqr(create, 2, create);
        mul(iArr2, create, create);
        int[] create2 = create();
        sqr(create, 5, create2);
        mul(create, create2, create2);
        int[] create3 = create();
        sqr(create2, 5, create3);
        mul(create, create3, create3);
        sqr(create3, 10, create);
        mul(create2, create, create);
        sqr(create, 25, create2);
        mul(create, create2, create2);
        sqr(create2, 25, create3);
        mul(create, create3, create3);
        sqr(create3, 50, create);
        mul(create2, create, create);
        sqr(create, 125, create2);
        mul(create, create2, create2);
        sqr(create2, 2, create);
        mul(create, iArr, iArr3);
    }

    private static void reduce(int[] iArr, int i) {
        int i2 = iArr[9];
        long j = (((i2 >> 24) + i) * 19) + iArr[0];
        iArr[0] = ((int) j) & M26;
        long j2 = (j >> 26) + iArr[1];
        iArr[1] = ((int) j2) & M26;
        long j3 = (j2 >> 26) + iArr[2];
        iArr[2] = ((int) j3) & M25;
        long j4 = (j3 >> 25) + iArr[3];
        iArr[3] = ((int) j4) & M26;
        long j5 = (j4 >> 26) + iArr[4];
        iArr[4] = ((int) j5) & M25;
        long j6 = (j5 >> 25) + iArr[5];
        iArr[5] = ((int) j6) & M26;
        long j7 = (j6 >> 26) + iArr[6];
        iArr[6] = ((int) j7) & M26;
        long j8 = (j7 >> 26) + iArr[7];
        iArr[7] = M25 & ((int) j8);
        long j9 = (j8 >> 25) + iArr[8];
        iArr[8] = M26 & ((int) j9);
        iArr[9] = (16777215 & i2) + ((int) (j9 >> 26));
    }

    public static void sqr(int[] iArr, int i, int[] iArr2) {
        sqr(iArr, iArr2);
        while (true) {
            i--;
            if (i <= 0) {
                return;
            }
            sqr(iArr2, iArr2);
        }
    }

    public static void sqr(int[] iArr, int[] iArr2) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        long j = i;
        long j2 = j * j;
        long j3 = i2 * 2;
        long j4 = j * j3;
        long j5 = i3 * 2;
        long j6 = i2;
        long j7 = (j * j5) + (j6 * j6);
        long j8 = i4 * 2;
        long j9 = i5 * 2;
        long j10 = (i3 * j5) + (j * j9) + (j6 * j8);
        long j11 = (j3 * j9) + (j8 * j5);
        long j12 = i4;
        long j13 = (j5 * j9) + (j12 * j12);
        long j14 = j12 * j9;
        long j15 = i5 * j9;
        int i11 = i10 * 2;
        long j16 = i6;
        long j17 = j16 * j16;
        long j18 = i7 * 2;
        long j19 = j16 * j18;
        long j20 = i8 * 2;
        long j21 = i7;
        long j22 = (j16 * j20) + (j21 * j21);
        long j23 = i9 * 2;
        long j24 = i11;
        long j25 = (i8 * j20) + (j16 * j24) + (j21 * j23);
        long j26 = i9;
        long j27 = j2 - (((j18 * j24) + (j23 * j20)) * 38);
        long j28 = j4 - (((j20 * j24) + (j26 * j26)) * 38);
        long j29 = j7 - ((j26 * j24) * 38);
        long j30 = ((j3 * j5) + (j * j8)) - ((i10 * j24) * 38);
        long j31 = j11 - j17;
        long j32 = j13 - j19;
        long j33 = j14 - j22;
        long j34 = j15 - ((j18 * j20) + (j16 * j23));
        int i12 = i2 + i7;
        int i13 = i3 + i8;
        int i14 = i4 + i9;
        int i15 = i5 + i10;
        long j35 = i + i6;
        long j36 = j35 * j35;
        long j37 = i12 * 2;
        long j38 = j35 * j37;
        long j39 = i13 * 2;
        long j40 = i12;
        long j41 = (j35 * j39) + (j40 * j40);
        long j42 = i14 * 2;
        long j43 = i15 * 2;
        long j44 = (j37 * j43) + (j42 * j39);
        long j45 = i14;
        long j46 = (j39 * j43) + (j45 * j45);
        long j47 = j45 * j43;
        long j48 = i15 * j43;
        long j49 = j34 + (((j37 * j39) + (j35 * j42)) - j30);
        int i16 = ((int) j49) & M26;
        long j50 = (j49 >> 26) + (((((i13 * j39) + (j35 * j43)) + (j40 * j42)) - j10) - j25);
        int i17 = ((int) j50) & M25;
        long j51 = j27 + ((((j50 >> 25) + j44) - j31) * 38);
        iArr2[0] = ((int) j51) & M26;
        long j52 = (j51 >> 26) + j28 + ((j46 - j32) * 38);
        iArr2[1] = ((int) j52) & M26;
        long j53 = (j52 >> 26) + j29 + ((j47 - j33) * 38);
        iArr2[2] = ((int) j53) & M25;
        long j54 = (j53 >> 25) + j30 + ((j48 - j34) * 38);
        iArr2[3] = ((int) j54) & M26;
        long j55 = (j54 >> 26) + j10 + (38 * j25);
        iArr2[4] = ((int) j55) & M25;
        long j56 = (j55 >> 25) + j31 + (j36 - j27);
        iArr2[5] = ((int) j56) & M26;
        long j57 = (j56 >> 26) + j32 + (j38 - j28);
        iArr2[6] = ((int) j57) & M26;
        long j58 = (j57 >> 26) + j33 + (j41 - j29);
        iArr2[7] = ((int) j58) & M25;
        long j59 = (j58 >> 25) + i16;
        iArr2[8] = ((int) j59) & M26;
        iArr2[9] = i17 + ((int) (j59 >> 26));
    }

    public static boolean sqrtRatioVar(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = create();
        int[] create2 = create();
        mul(iArr, iArr2, create);
        sqr(iArr2, create2);
        mul(create, create2, create);
        sqr(create2, create2);
        mul(create2, create, create2);
        int[] create3 = create();
        int[] create4 = create();
        powPm5d8(create2, create3, create4);
        mul(create4, create, create4);
        int[] create5 = create();
        sqr(create4, create5);
        mul(create5, iArr2, create5);
        sub(create5, iArr, create3);
        normalize(create3);
        if (isZeroVar(create3)) {
            copy(create4, 0, iArr3, 0);
            return true;
        }
        add(create5, iArr, create3);
        normalize(create3);
        if (isZeroVar(create3)) {
            mul(create4, ROOT_NEG_ONE, iArr3);
            return true;
        }
        return false;
    }

    public static void sub(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i = 0; i < 10; i++) {
            iArr3[i] = iArr[i] - iArr2[i];
        }
    }

    public static void subOne(int[] iArr) {
        iArr[0] = iArr[0] - 1;
    }

    public static void zero(int[] iArr) {
        for (int i = 0; i < 10; i++) {
            iArr[i] = 0;
        }
    }
}
