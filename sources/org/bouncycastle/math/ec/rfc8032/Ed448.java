package org.bouncycastle.math.ec.rfc8032;

import java.security.SecureRandom;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.math.ec.rfc7748.X448;
import org.bouncycastle.math.ec.rfc7748.X448Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public abstract class Ed448 {
    private static final int COORD_INTS = 14;
    private static final int C_d = -39081;
    private static final int L4_0 = 43969588;
    private static final int L4_1 = 30366549;
    private static final int L4_2 = 163752818;
    private static final int L4_3 = 258169998;
    private static final int L4_4 = 96434764;
    private static final int L4_5 = 227822194;
    private static final int L4_6 = 149865618;
    private static final int L4_7 = 550336261;
    private static final int L_0 = 78101261;
    private static final int L_1 = 141809365;
    private static final int L_2 = 175155932;
    private static final int L_3 = 64542499;
    private static final int L_4 = 158326419;
    private static final int L_5 = 191173276;
    private static final int L_6 = 104575268;
    private static final int L_7 = 137584065;
    private static final long M26L = 67108863;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 57;
    private static final int PRECOMP_BLOCKS = 5;
    private static final int PRECOMP_MASK = 15;
    private static final int PRECOMP_POINTS = 16;
    private static final int PRECOMP_RANGE = 450;
    private static final int PRECOMP_SPACING = 18;
    private static final int PRECOMP_TEETH = 5;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 57;
    private static final int SCALAR_BYTES = 57;
    private static final int SCALAR_INTS = 14;
    public static final int SECRET_KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    private static final int WNAF_WIDTH = 5;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM4_PREFIX = {BuiltinOptions.GatherNdOptions, BuiltinOptions.Rfft2dOptions, BuiltinOptions.CallOnceOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, BuiltinOptions.SegmentSumOptions, 52, 52, BuiltinOptions.PowOptions};
    private static final int[] P = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    private static final int[] L = {-1420278541, 595116690, -1916432555, 560775794, -1361693040, -1001465015, 2093622249, -1, -1, -1, -1, -1, -1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK};
    private static final int[] B_x = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    private static final int[] B_y = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    private static final Object PRECOMP_LOCK = new Object();
    private static PointAffine[] PRECOMP_BASE_WNAF = null;
    private static int[] PRECOMP_BASE_COMB = null;

    /* loaded from: classes2.dex */
    public static final class Algorithm {
        public static final int Ed448 = 0;
        public static final int Ed448ph = 1;
    }

    /* loaded from: classes2.dex */
    private static class F extends X448Field {
        private F() {
        }
    }

    /* loaded from: classes2.dex */
    public static class PointAffine {
        int[] x;
        int[] y;

        private PointAffine() {
            this.x = F.create();
            this.y = F.create();
        }
    }

    /* loaded from: classes2.dex */
    public static class PointProjective {
        int[] x;
        int[] y;
        int[] z;

        private PointProjective() {
            this.x = F.create();
            this.y = F.create();
            this.z = F.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[28];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[14];
        decodeScalar(bArr3, 0, iArr3);
        Nat.mulAddTo(14, iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[114];
        for (int i = 0; i < 28; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr) {
        return bArr != null && bArr.length < 256;
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        F.sqr(iArr, create2);
        F.sqr(iArr2, create3);
        F.mul(create2, create3, create);
        F.add(create2, create3, create2);
        F.mul(create, 39081, create);
        F.subOne(create);
        F.add(create, create2, create);
        F.normalize(create);
        return F.isZero(create);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        F.sqr(iArr, create2);
        F.sqr(iArr2, create3);
        F.sqr(iArr3, create4);
        F.mul(create2, create3, create);
        F.add(create2, create3, create2);
        F.mul(create2, create4, create2);
        F.sqr(create4, create4);
        F.mul(create, 39081, create);
        F.sub(create, create4, create);
        F.add(create, create2, create);
        F.normalize(create);
        return F.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        if ((bArr[56] & ByteCompanionObject.MAX_VALUE) != 0) {
            return false;
        }
        int[] iArr = new int[14];
        decode32(bArr, 0, iArr, 0, 14);
        return !Nat.gte(14, iArr, P);
    }

    private static boolean checkScalarVar(byte[] bArr, int[] iArr) {
        if (bArr[56] != 0) {
            return false;
        }
        decodeScalar(bArr, 0, iArr);
        return !Nat.gte(14, iArr, L);
    }

    private static byte[] copy(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static Xof createPrehash() {
        return createXof();
    }

    private static Xof createXof() {
        return new SHAKEDigest(256);
    }

    private static int decode16(byte[] bArr, int i) {
        return ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | (bArr[i] & UByte.MAX_VALUE);
    }

    private static int decode24(byte[] bArr, int i) {
        int i2 = i + 1;
        return ((bArr[i2 + 1] & UByte.MAX_VALUE) << 16) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i2] & UByte.MAX_VALUE) << 8);
    }

    private static int decode32(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] << BuiltinOptions.BatchToSpaceNDOptions) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i2] & UByte.MAX_VALUE) << 8) | ((bArr[i3] & UByte.MAX_VALUE) << 16);
    }

    private static void decode32(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = decode32(bArr, (i4 * 4) + i);
        }
    }

    private static boolean decodePointVar(byte[] bArr, int i, boolean z, PointProjective pointProjective) {
        byte[] copy = copy(bArr, i, 57);
        if (checkPointVar(copy)) {
            byte b = copy[56];
            int i2 = (b & ByteCompanionObject.MIN_VALUE) >>> 7;
            copy[56] = (byte) (b & ByteCompanionObject.MAX_VALUE);
            F.decode(copy, 0, pointProjective.y);
            int[] create = F.create();
            int[] create2 = F.create();
            F.sqr(pointProjective.y, create);
            F.mul(create, 39081, create2);
            F.negate(create, create);
            F.addOne(create);
            F.addOne(create2);
            if (F.sqrtRatioVar(create, create2, pointProjective.x)) {
                F.normalize(pointProjective.x);
                if (i2 == 1 && F.isZeroVar(pointProjective.x)) {
                    return false;
                }
                if (z ^ (i2 != (pointProjective.x[0] & 1))) {
                    F.negate(pointProjective.x, pointProjective.x);
                }
                F.one(pointProjective.z);
                return true;
            }
            return false;
        }
        return false;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 14);
    }

    private static void dom4(Xof xof, byte b, byte[] bArr) {
        byte[] bArr2 = DOM4_PREFIX;
        int length = bArr2.length;
        int i = length + 2;
        int length2 = bArr.length + i;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr2, 0, bArr3, 0, length);
        bArr3[length] = b;
        bArr3[length + 1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr3, i, bArr.length);
        xof.update(bArr3, 0, length2);
    }

    private static void encode24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        bArr[i3 + 1] = (byte) (i >>> 16);
    }

    private static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    private static void encode56(long j, byte[] bArr, int i) {
        encode32((int) j, bArr, i);
        encode24((int) (j >>> 32), bArr, i + 4);
    }

    private static int encodePoint(PointProjective pointProjective, byte[] bArr, int i) {
        int[] create = F.create();
        int[] create2 = F.create();
        F.inv(pointProjective.z, create2);
        F.mul(pointProjective.x, create2, create);
        F.mul(pointProjective.y, create2, create2);
        F.normalize(create);
        F.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        F.encode(create2, bArr, i);
        bArr[(i + 57) - 1] = (byte) ((create[0] & 1) << 7);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Xof createXof = createXof();
        byte[] bArr3 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr3, 0, 114);
        byte[] bArr4 = new byte[57];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] iArr, int i) {
        int[] iArr2 = new int[28];
        int i2 = 0;
        int i3 = 14;
        int i4 = 28;
        int i5 = 0;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            int i6 = iArr[i3];
            int i7 = i4 - 1;
            iArr2[i7] = (i5 << 16) | (i6 >>> 16);
            i4 = i7 - 1;
            iArr2[i4] = i6;
            i5 = i6;
        }
        byte[] bArr = new byte[447];
        int i8 = 32 - i;
        int i9 = 0;
        int i10 = 0;
        while (i2 < 28) {
            int i11 = iArr2[i2];
            while (i9 < 16) {
                int i12 = i11 >>> i9;
                if ((i12 & 1) == i10) {
                    i9++;
                } else {
                    int i13 = (i12 | 1) << i8;
                    bArr[(i2 << 4) + i9] = (byte) (i13 >> i8);
                    i9 += i;
                    i10 = i13 >>> 31;
                }
            }
            i2++;
            i9 -= 16;
        }
        return bArr;
    }

    private static void implSign(Xof xof, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom4(xof, b, bArr4);
        xof.update(bArr, 57, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom4(xof, b, bArr4);
        xof.update(bArr7, 0, 57);
        xof.update(bArr3, i, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 57);
        System.arraycopy(calculateS, 0, bArr6, i4 + 57, 57);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof createXof = createXof();
        byte[] bArr5 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr5, 0, 114);
        byte[] bArr6 = new byte[57];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(createXof, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof createXof = createXof();
        byte[] bArr6 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr6, 0, 114);
        byte[] bArr7 = new byte[57];
        pruneScalar(bArr6, 0, bArr7);
        implSign(createXof, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
    }

    private static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (checkContextVar(bArr3)) {
            byte[] copy = copy(bArr, i, 57);
            byte[] copy2 = copy(bArr, i + 57, 57);
            if (checkPointVar(copy)) {
                int[] iArr = new int[14];
                if (checkScalarVar(copy2, iArr)) {
                    PointProjective pointProjective = new PointProjective();
                    if (decodePointVar(bArr2, i2, true, pointProjective)) {
                        Xof createXof = createXof();
                        byte[] bArr5 = new byte[114];
                        dom4(createXof, b, bArr3);
                        createXof.update(copy, 0, 57);
                        createXof.update(bArr2, i2, 57);
                        createXof.update(bArr4, i3, i4);
                        createXof.doFinal(bArr5, 0, 114);
                        int[] iArr2 = new int[14];
                        decodeScalar(reduceScalar(bArr5), 0, iArr2);
                        PointProjective pointProjective2 = new PointProjective();
                        scalarMultStrausVar(iArr, iArr2, pointProjective, pointProjective2);
                        byte[] bArr6 = new byte[57];
                        return encodePoint(pointProjective2, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copy);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static void invertZs(PointProjective[] pointProjectiveArr) {
        int length = pointProjectiveArr.length;
        int[] createTable = F.createTable(length);
        int[] create = F.create();
        F.copy(pointProjectiveArr[0].z, 0, create, 0);
        F.copy(create, 0, createTable, 0);
        int i = 0;
        while (true) {
            i++;
            if (i >= length) {
                break;
            }
            F.mul(create, pointProjectiveArr[i].z, create);
            F.copy(create, 0, createTable, i * 16);
        }
        F.invVar(create, create);
        int i2 = i - 1;
        int[] create2 = F.create();
        while (i2 > 0) {
            int i3 = i2 - 1;
            F.copy(createTable, i3 * 16, create2, 0);
            F.mul(create2, create, create2);
            F.mul(create, pointProjectiveArr[i2].z, create);
            F.copy(create2, 0, pointProjectiveArr[i2].z, 0);
            i2 = i3;
        }
        F.copy(create, 0, pointProjectiveArr[0].z, 0);
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2, int[] iArr3) {
        return F.isZeroVar(iArr) && F.areEqualVar(iArr2, iArr3);
    }

    private static void pointAdd(PointAffine pointAffine, PointProjective pointProjective) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        int[] create5 = F.create();
        int[] create6 = F.create();
        int[] create7 = F.create();
        F.sqr(pointProjective.z, create);
        F.mul(pointAffine.x, pointProjective.x, create2);
        F.mul(pointAffine.y, pointProjective.y, create3);
        F.mul(create2, create3, create4);
        F.mul(create4, 39081, create4);
        F.add(create, create4, create5);
        F.sub(create, create4, create6);
        F.add(pointAffine.y, pointAffine.x, create7);
        F.add(pointProjective.y, pointProjective.x, create4);
        F.mul(create7, create4, create7);
        F.add(create3, create2, create);
        F.sub(create3, create2, create4);
        F.carry(create);
        F.sub(create7, create, create7);
        F.mul(create7, pointProjective.z, create7);
        F.mul(create4, pointProjective.z, create4);
        F.mul(create5, create7, pointProjective.x);
        F.mul(create4, create6, pointProjective.y);
        F.mul(create5, create6, pointProjective.z);
    }

    private static void pointAdd(PointProjective pointProjective, PointProjective pointProjective2) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        int[] create5 = F.create();
        int[] create6 = F.create();
        int[] create7 = F.create();
        int[] create8 = F.create();
        F.mul(pointProjective.z, pointProjective2.z, create);
        F.sqr(create, create2);
        F.mul(pointProjective.x, pointProjective2.x, create3);
        F.mul(pointProjective.y, pointProjective2.y, create4);
        F.mul(create3, create4, create5);
        F.mul(create5, 39081, create5);
        F.add(create2, create5, create6);
        F.sub(create2, create5, create7);
        F.add(pointProjective.y, pointProjective.x, create8);
        F.add(pointProjective2.y, pointProjective2.x, create5);
        F.mul(create8, create5, create8);
        F.add(create4, create3, create2);
        F.sub(create4, create3, create5);
        F.carry(create2);
        F.sub(create8, create2, create8);
        F.mul(create8, create, create8);
        F.mul(create5, create, create5);
        F.mul(create6, create8, pointProjective2.x);
        F.mul(create5, create7, pointProjective2.y);
        F.mul(create6, create7, pointProjective2.z);
    }

    private static void pointAddVar(boolean z, PointAffine pointAffine, PointProjective pointProjective) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        int[] create5 = F.create();
        int[] create6 = F.create();
        int[] create7 = F.create();
        if (z) {
            F.sub(pointAffine.y, pointAffine.x, create7);
            iArr2 = create;
            iArr = create4;
            iArr4 = create5;
            iArr3 = create6;
        } else {
            F.add(pointAffine.y, pointAffine.x, create7);
            iArr = create;
            iArr2 = create4;
            iArr3 = create5;
            iArr4 = create6;
        }
        F.sqr(pointProjective.z, create);
        F.mul(pointAffine.x, pointProjective.x, create2);
        F.mul(pointAffine.y, pointProjective.y, create3);
        F.mul(create2, create3, create4);
        F.mul(create4, 39081, create4);
        F.add(create, create4, iArr3);
        F.sub(create, create4, iArr4);
        F.add(pointProjective.y, pointProjective.x, create4);
        F.mul(create7, create4, create7);
        F.add(create3, create2, iArr);
        F.sub(create3, create2, iArr2);
        F.carry(iArr);
        F.sub(create7, create, create7);
        F.mul(create7, pointProjective.z, create7);
        F.mul(create4, pointProjective.z, create4);
        F.mul(create5, create7, pointProjective.x);
        F.mul(create4, create6, pointProjective.y);
        F.mul(create5, create6, pointProjective.z);
    }

    private static void pointAddVar(boolean z, PointProjective pointProjective, PointProjective pointProjective2) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        int[] create5 = F.create();
        int[] create6 = F.create();
        int[] create7 = F.create();
        int[] create8 = F.create();
        if (z) {
            F.sub(pointProjective.y, pointProjective.x, create8);
            iArr2 = create2;
            iArr = create5;
            iArr4 = create6;
            iArr3 = create7;
        } else {
            F.add(pointProjective.y, pointProjective.x, create8);
            iArr = create2;
            iArr2 = create5;
            iArr3 = create6;
            iArr4 = create7;
        }
        F.mul(pointProjective.z, pointProjective2.z, create);
        F.sqr(create, create2);
        F.mul(pointProjective.x, pointProjective2.x, create3);
        F.mul(pointProjective.y, pointProjective2.y, create4);
        F.mul(create3, create4, create5);
        F.mul(create5, 39081, create5);
        F.add(create2, create5, iArr3);
        F.sub(create2, create5, iArr4);
        F.add(pointProjective2.y, pointProjective2.x, create5);
        F.mul(create8, create5, create8);
        F.add(create4, create3, iArr);
        F.sub(create4, create3, iArr2);
        F.carry(iArr);
        F.sub(create8, create2, create8);
        F.mul(create8, create, create8);
        F.mul(create5, create, create5);
        F.mul(create6, create8, pointProjective2.x);
        F.mul(create5, create7, pointProjective2.y);
        F.mul(create6, create7, pointProjective2.z);
    }

    private static void pointCopy(PointProjective pointProjective, PointProjective pointProjective2) {
        F.copy(pointProjective.x, 0, pointProjective2.x, 0);
        F.copy(pointProjective.y, 0, pointProjective2.y, 0);
        F.copy(pointProjective.z, 0, pointProjective2.z, 0);
    }

    private static void pointDouble(PointProjective pointProjective) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        int[] create5 = F.create();
        int[] create6 = F.create();
        F.add(pointProjective.x, pointProjective.y, create);
        F.sqr(create, create);
        F.sqr(pointProjective.x, create2);
        F.sqr(pointProjective.y, create3);
        F.add(create2, create3, create4);
        F.carry(create4);
        F.sqr(pointProjective.z, create5);
        F.add(create5, create5, create5);
        F.carry(create5);
        F.sub(create4, create5, create6);
        F.sub(create, create4, create);
        F.sub(create2, create3, create2);
        F.mul(create, create6, pointProjective.x);
        F.mul(create4, create2, pointProjective.y);
        F.mul(create4, create6, pointProjective.z);
    }

    private static void pointLookup(int i, int i2, PointAffine pointAffine) {
        int i3 = i * 16 * 2 * 16;
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            F.cmov(i5, PRECOMP_BASE_COMB, i3, pointAffine.x, 0);
            int i6 = i3 + 16;
            F.cmov(i5, PRECOMP_BASE_COMB, i6, pointAffine.y, 0);
            i3 = i6 + 16;
        }
    }

    private static void pointLookup(int[] iArr, int i, int[] iArr2, PointProjective pointProjective) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            F.cmov(i6, iArr2, i4, pointProjective.x, 0);
            int i7 = i4 + 16;
            F.cmov(i6, iArr2, i7, pointProjective.y, 0);
            int i8 = i7 + 16;
            F.cmov(i6, iArr2, i8, pointProjective.z, 0);
            i4 = i8 + 16;
        }
        F.cnegate(i2, pointProjective.x);
    }

    private static void pointLookup15(int[] iArr, PointProjective pointProjective) {
        F.copy(iArr, 336, pointProjective.x, 0);
        F.copy(iArr, 352, pointProjective.y, 0);
        F.copy(iArr, 368, pointProjective.z, 0);
    }

    private static int[] pointPrecompute(PointProjective pointProjective, int i) {
        PointProjective pointProjective2 = new PointProjective();
        pointCopy(pointProjective, pointProjective2);
        PointProjective pointProjective3 = new PointProjective();
        pointCopy(pointProjective2, pointProjective3);
        pointDouble(pointProjective3);
        int[] createTable = F.createTable(i * 3);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            F.copy(pointProjective2.x, 0, createTable, i2);
            int i4 = i2 + 16;
            F.copy(pointProjective2.y, 0, createTable, i4);
            int i5 = i4 + 16;
            F.copy(pointProjective2.z, 0, createTable, i5);
            i2 = i5 + 16;
            i3++;
            if (i3 == i) {
                return createTable;
            }
            pointAdd(pointProjective3, pointProjective2);
        }
    }

    private static void pointPrecomputeVar(PointProjective pointProjective, PointProjective[] pointProjectiveArr, int i) {
        PointProjective pointProjective2 = new PointProjective();
        pointCopy(pointProjective, pointProjective2);
        pointDouble(pointProjective2);
        PointProjective pointProjective3 = new PointProjective();
        pointProjectiveArr[0] = pointProjective3;
        pointCopy(pointProjective, pointProjective3);
        for (int i2 = 1; i2 < i; i2++) {
            PointProjective pointProjective4 = new PointProjective();
            pointProjectiveArr[i2] = pointProjective4;
            pointCopy(pointProjectiveArr[i2 - 1], pointProjective4);
            pointAdd(pointProjective2, pointProjectiveArr[i2]);
        }
    }

    private static void pointSetNeutral(PointProjective pointProjective) {
        F.zero(pointProjective.x);
        F.one(pointProjective.y);
        F.one(pointProjective.z);
    }

    public static void precompute() {
        synchronized (PRECOMP_LOCK) {
            if (PRECOMP_BASE_WNAF == null || PRECOMP_BASE_COMB == null) {
                PointProjective[] pointProjectiveArr = new PointProjective[112];
                PointProjective pointProjective = new PointProjective();
                F.copy(B_x, 0, pointProjective.x, 0);
                F.copy(B_y, 0, pointProjective.y, 0);
                F.one(pointProjective.z);
                pointPrecomputeVar(pointProjective, pointProjectiveArr, 32);
                PointProjective[] pointProjectiveArr2 = new PointProjective[5];
                for (int i = 0; i < 5; i++) {
                    pointProjectiveArr2[i] = new PointProjective();
                }
                int i2 = 32;
                int i3 = 0;
                for (int i4 = 5; i3 < i4; i4 = 5) {
                    int i5 = i2 + 1;
                    PointProjective pointProjective2 = new PointProjective();
                    pointProjectiveArr[i2] = pointProjective2;
                    int i6 = 0;
                    while (true) {
                        if (i6 >= i4) {
                            break;
                        }
                        if (i6 == 0) {
                            pointCopy(pointProjective, pointProjective2);
                        } else {
                            pointAdd(pointProjective, pointProjective2);
                        }
                        pointDouble(pointProjective);
                        pointCopy(pointProjective, pointProjectiveArr2[i6]);
                        if (i3 + i6 != 8) {
                            for (int i7 = 1; i7 < 18; i7++) {
                                pointDouble(pointProjective);
                            }
                        }
                        i6++;
                    }
                    F.negate(pointProjective2.x, pointProjective2.x);
                    i2 = i5;
                    for (int i8 = 0; i8 < 4; i8++) {
                        int i9 = 1 << i8;
                        int i10 = 0;
                        while (i10 < i9) {
                            PointProjective pointProjective3 = new PointProjective();
                            pointProjectiveArr[i2] = pointProjective3;
                            pointCopy(pointProjectiveArr[i2 - i9], pointProjective3);
                            pointAdd(pointProjectiveArr2[i8], pointProjectiveArr[i2]);
                            i10++;
                            i2++;
                        }
                    }
                    i3++;
                }
                invertZs(pointProjectiveArr);
                PRECOMP_BASE_WNAF = new PointAffine[32];
                for (int i11 = 0; i11 < 32; i11++) {
                    PointProjective pointProjective4 = pointProjectiveArr[i11];
                    PointAffine[] pointAffineArr = PRECOMP_BASE_WNAF;
                    PointAffine pointAffine = new PointAffine();
                    pointAffineArr[i11] = pointAffine;
                    F.mul(pointProjective4.x, pointProjective4.z, pointAffine.x);
                    F.normalize(pointAffine.x);
                    F.mul(pointProjective4.y, pointProjective4.z, pointAffine.y);
                    F.normalize(pointAffine.y);
                }
                PRECOMP_BASE_COMB = F.createTable(160);
                int i12 = 0;
                for (int i13 = 32; i13 < 112; i13++) {
                    PointProjective pointProjective5 = pointProjectiveArr[i13];
                    F.mul(pointProjective5.x, pointProjective5.z, pointProjective5.x);
                    F.normalize(pointProjective5.x);
                    F.mul(pointProjective5.y, pointProjective5.z, pointProjective5.y);
                    F.normalize(pointProjective5.y);
                    F.copy(pointProjective5.x, 0, PRECOMP_BASE_COMB, i12);
                    int i14 = i12 + 16;
                    F.copy(pointProjective5.y, 0, PRECOMP_BASE_COMB, i14);
                    i12 = i14 + 16;
                }
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 56);
        bArr2[0] = (byte) (bArr2[0] & 252);
        bArr2[55] = (byte) (bArr2[55] | ByteCompanionObject.MIN_VALUE);
        bArr2[56] = 0;
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode24 = (decode24(bArr, 4) << 4) & 4294967295L;
        long decode32 = decode32(bArr, 7) & 4294967295L;
        long decode242 = (decode24(bArr, 11) << 4) & 4294967295L;
        long decode322 = decode32(bArr, 14) & 4294967295L;
        long decode243 = (decode24(bArr, 18) << 4) & 4294967295L;
        long decode323 = decode32(bArr, 21) & 4294967295L;
        long decode324 = decode32(bArr, 28) & 4294967295L;
        long decode244 = (decode24(bArr, 32) << 4) & 4294967295L;
        long decode325 = decode32(bArr, 35) & 4294967295L;
        long decode245 = (decode24(bArr, 39) << 4) & 4294967295L;
        long decode326 = decode32(bArr, 42) & 4294967295L;
        long decode246 = (decode24(bArr, 46) << 4) & 4294967295L;
        long decode327 = decode32(bArr, 49) & 4294967295L;
        long decode247 = (decode24(bArr, 53) << 4) & 4294967295L;
        long decode248 = (decode24(bArr, 74) << 4) & 4294967295L;
        long decode328 = decode32(bArr, 77) & 4294967295L;
        long decode249 = (decode24(bArr, 81) << 4) & 4294967295L;
        long decode329 = decode32(bArr, 84) & 4294967295L;
        long decode2410 = (decode24(bArr, 88) << 4) & 4294967295L;
        long decode3210 = decode32(bArr, 91) & 4294967295L;
        long decode2411 = (decode24(bArr, 95) << 4) & 4294967295L;
        long decode3211 = decode32(bArr, 98) & 4294967295L;
        long decode2412 = (decode24(bArr, 102) << 4) & 4294967295L;
        long decode3212 = decode32(bArr, 105) & 4294967295L;
        long decode2413 = (decode24(bArr, 109) << 4) & 4294967295L;
        long decode16 = decode16(bArr, 112) & 4294967295L;
        long j = decode2413 + (decode3212 >>> 28);
        long j2 = decode3212 & M28L;
        long decode3213 = (decode32(bArr, 56) & 4294967295L) + (decode16 * 43969588) + (j * 30366549);
        long decode2414 = ((decode24(bArr, 60) << 4) & 4294967295L) + (decode16 * 30366549) + (j * 163752818);
        long decode3214 = (decode32(bArr, 63) & 4294967295L) + (decode16 * 163752818) + (j * 258169998);
        long decode2415 = ((decode24(bArr, 67) << 4) & 4294967295L) + (decode16 * 258169998) + (j * 96434764);
        long j3 = decode328 + (decode16 * 149865618) + (j * 550336261);
        long j4 = decode327 + (j2 * 43969588);
        long j5 = decode2412 + (decode3211 >>> 28);
        long j6 = decode3211 & M28L;
        long decode3215 = (decode32(bArr, 70) & 4294967295L) + (decode16 * 96434764) + (j * 227822194) + (j2 * 149865618) + (j5 * 550336261);
        long j7 = decode326 + (j6 * 43969588);
        long j8 = decode2411 + (decode3210 >>> 28);
        long j9 = decode3210 & M28L;
        long j10 = decode3214 + (j2 * 96434764) + (j5 * 227822194) + (j6 * 149865618) + (j8 * 550336261);
        long j11 = decode325 + (j9 * 43969588);
        long j12 = decode2414 + (j2 * 258169998) + (j5 * 96434764) + (j6 * 227822194) + (j8 * 149865618) + (j9 * 550336261);
        long j13 = decode2410 + (decode329 >>> 28);
        long j14 = decode329 & M28L;
        long j15 = decode248 + (decode16 * 227822194) + (j * 149865618) + (j2 * 550336261) + (decode3215 >>> 28);
        long j16 = decode3215 & M28L;
        long j17 = j3 + (j15 >>> 28);
        long j18 = j15 & M28L;
        long j19 = decode249 + (decode16 * 550336261) + (j17 >>> 28);
        long j20 = j17 & M28L;
        long j21 = j14 + (j19 >>> 28);
        long j22 = j19 & M28L;
        long decode2416 = ((decode24(bArr, 25) << 4) & 4294967295L) + (j22 * 43969588);
        long j23 = decode324 + (j21 * 43969588) + (j22 * 30366549);
        long j24 = decode244 + (j13 * 43969588) + (j21 * 30366549) + (j22 * 163752818);
        long j25 = j11 + (j13 * 30366549) + (j21 * 163752818) + (j22 * 258169998);
        long j26 = decode245 + (j8 * 43969588) + (j9 * 30366549) + (j13 * 163752818) + (j21 * 258169998) + (j22 * 96434764);
        long j27 = j7 + (j8 * 30366549) + (j9 * 163752818) + (j13 * 258169998) + (j21 * 96434764) + (j22 * 227822194);
        long j28 = j4 + (j5 * 30366549) + (j6 * 163752818) + (j8 * 258169998) + (j9 * 96434764) + (j13 * 227822194) + (j21 * 149865618) + (j22 * 550336261);
        long j29 = decode323 + (j20 * 43969588);
        long j30 = j10 + (j12 >>> 28);
        long j31 = j12 & M28L;
        long j32 = decode2415 + (j2 * 227822194) + (j5 * 149865618) + (j6 * 550336261) + (j30 >>> 28);
        long j33 = j30 & M28L;
        long j34 = j16 + (j32 >>> 28);
        long j35 = j32 & M28L;
        long j36 = j18 + (j34 >>> 28);
        long j37 = j34 & M28L;
        long j38 = j26 + (j20 * 227822194) + (j36 * 149865618) + (j37 * 550336261);
        long j39 = decode322 + (j37 * 43969588) + (j35 * 30366549);
        long j40 = decode243 + (j36 * 43969588) + (j37 * 30366549) + (j35 * 163752818);
        long j41 = j29 + (j36 * 30366549) + (j37 * 163752818) + (j35 * 258169998);
        long j42 = decode2416 + (j20 * 30366549) + (j36 * 163752818) + (j37 * 258169998) + (j35 * 96434764);
        long j43 = j23 + (j20 * 163752818) + (j36 * 258169998) + (j37 * 96434764) + (j35 * 227822194);
        long j44 = j24 + (j20 * 258169998) + (j36 * 96434764) + (j37 * 227822194) + (j35 * 149865618);
        long j45 = j25 + (j20 * 96434764) + (j36 * 227822194) + (j37 * 149865618) + (j35 * 550336261);
        long j46 = decode247 + (j * 43969588) + (j2 * 30366549) + (j5 * 163752818) + (j6 * 258169998) + (j8 * 96434764) + (j9 * 227822194) + (j13 * 149865618) + (j21 * 550336261) + (j28 >>> 28);
        long j47 = j28 & M28L;
        long j48 = decode3213 + (j2 * 163752818) + (j5 * 258169998) + (j6 * 96434764) + (j8 * 227822194) + (j9 * 149865618) + (j13 * 550336261) + (j46 >>> 28);
        long j49 = j46 & M28L;
        long j50 = j31 + (j48 >>> 28);
        long j51 = j48 & M28L;
        long j52 = j33 + (j50 >>> 28);
        long j53 = j50 & M28L;
        long j54 = decode32 + (j52 * 43969588);
        long j55 = decode242 + (j35 * 43969588) + (j52 * 30366549);
        long j56 = j39 + (j52 * 163752818);
        long j57 = j40 + (j52 * 258169998);
        long j58 = j41 + (j52 * 96434764);
        long j59 = j42 + (j52 * 227822194);
        long j60 = j44 + (j52 * 550336261);
        long j61 = j49 & M26L;
        long j62 = (j51 * 4) + (j49 >>> 26) + 1;
        long decode3216 = (decode32(bArr, 0) & 4294967295L) + (78101261 * j62);
        long j63 = j54 + (30366549 * j53) + (175155932 * j62);
        long j64 = j55 + (163752818 * j53) + (64542499 * j62);
        long j65 = j56 + (258169998 * j53) + (158326419 * j62);
        long j66 = j57 + (96434764 * j53) + (191173276 * j62);
        long j67 = j59 + (149865618 * j53) + (j62 * 137584065);
        long j68 = decode24 + (43969588 * j53) + (141809365 * j62) + (decode3216 >>> 28);
        long j69 = decode3216 & M28L;
        long j70 = j63 + (j68 >>> 28);
        long j71 = j68 & M28L;
        long j72 = j64 + (j70 >>> 28);
        long j73 = j70 & M28L;
        long j74 = j65 + (j72 >>> 28);
        long j75 = j72 & M28L;
        long j76 = j66 + (j74 >>> 28);
        long j77 = j74 & M28L;
        long j78 = j58 + (227822194 * j53) + (104575268 * j62) + (j76 >>> 28);
        long j79 = j76 & M28L;
        long j80 = j67 + (j78 >>> 28);
        long j81 = j78 & M28L;
        long j82 = j43 + (j52 * 149865618) + (j53 * 550336261) + (j80 >>> 28);
        long j83 = j80 & M28L;
        long j84 = j60 + (j82 >>> 28);
        long j85 = j82 & M28L;
        long j86 = j45 + (j84 >>> 28);
        long j87 = j84 & M28L;
        long j88 = j38 + (j86 >>> 28);
        long j89 = j86 & M28L;
        long j90 = j27 + (j20 * 149865618) + (j36 * 550336261) + (j88 >>> 28);
        long j91 = j88 & M28L;
        long j92 = decode246 + (j5 * 43969588) + (j6 * 30366549) + (j8 * 163752818) + (j9 * 258169998) + (j13 * 96434764) + (j21 * 227822194) + (j22 * 149865618) + (j20 * 550336261) + (j90 >>> 28);
        long j93 = j90 & M28L;
        long j94 = j47 + (j92 >>> 28);
        long j95 = j92 & M28L;
        long j96 = j61 + (j94 >>> 28);
        long j97 = j94 & M28L;
        long j98 = j96 & M26L;
        long j99 = (j96 >>> 26) - 1;
        long j100 = j69 - (j99 & 78101261);
        long j101 = (j71 - (j99 & 141809365)) + (j100 >> 28);
        long j102 = j100 & M28L;
        long j103 = (j73 - (j99 & 175155932)) + (j101 >> 28);
        long j104 = j101 & M28L;
        long j105 = (j75 - (j99 & 64542499)) + (j103 >> 28);
        long j106 = j103 & M28L;
        long j107 = (j77 - (j99 & 158326419)) + (j105 >> 28);
        long j108 = j105 & M28L;
        long j109 = (j79 - (j99 & 191173276)) + (j107 >> 28);
        long j110 = j107 & M28L;
        long j111 = (j81 - (j99 & 104575268)) + (j109 >> 28);
        long j112 = j109 & M28L;
        long j113 = (j83 - (j99 & 137584065)) + (j111 >> 28);
        long j114 = j111 & M28L;
        long j115 = j85 + (j113 >> 28);
        long j116 = j113 & M28L;
        long j117 = j87 + (j115 >> 28);
        long j118 = j115 & M28L;
        long j119 = j89 + (j117 >> 28);
        long j120 = j117 & M28L;
        long j121 = j91 + (j119 >> 28);
        long j122 = j119 & M28L;
        long j123 = j93 + (j121 >> 28);
        long j124 = j121 & M28L;
        long j125 = j95 + (j123 >> 28);
        long j126 = j123 & M28L;
        long j127 = j97 + (j125 >> 28);
        long j128 = j125 & M28L;
        long j129 = j127 & M28L;
        byte[] bArr2 = new byte[57];
        encode56((j104 << 28) | j102, bArr2, 0);
        encode56((j108 << 28) | j106, bArr2, 7);
        encode56(j110 | (j112 << 28), bArr2, 14);
        encode56(j114 | (j116 << 28), bArr2, 21);
        encode56(j118 | (j120 << 28), bArr2, 28);
        encode56(j122 | (j124 << 28), bArr2, 35);
        encode56(j126 | (j128 << 28), bArr2, 42);
        encode56(((j98 + (j127 >> 28)) << 28) | j129, bArr2, 49);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointProjective pointProjective, PointProjective pointProjective2) {
        int[] iArr = new int[14];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBit(14, iArr, Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr));
        int[] pointPrecompute = pointPrecompute(pointProjective, 8);
        PointProjective pointProjective3 = new PointProjective();
        pointLookup15(pointPrecompute, pointProjective2);
        pointAdd(pointProjective, pointProjective2);
        int i = 111;
        while (true) {
            pointLookup(iArr, i, pointPrecompute, pointProjective3);
            pointAdd(pointProjective3, pointProjective2);
            i--;
            if (i < 0) {
                return;
            }
            for (int i2 = 0; i2 < 4; i2++) {
                pointDouble(pointProjective2);
            }
        }
    }

    private static void scalarMultBase(byte[] bArr, PointProjective pointProjective) {
        precompute();
        int[] iArr = new int[15];
        decodeScalar(bArr, 0, iArr);
        iArr[14] = Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr) + 4;
        Nat.shiftDownBit(15, iArr, 0);
        PointAffine pointAffine = new PointAffine();
        pointSetNeutral(pointProjective);
        int i = 17;
        while (true) {
            int i2 = i;
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = 0;
                for (int i5 = 0; i5 < 5; i5++) {
                    i4 = (i4 & (~(1 << i5))) ^ ((iArr[i2 >>> 5] >>> (i2 & 31)) << i5);
                    i2 += 18;
                }
                int i6 = (i4 >>> 4) & 1;
                pointLookup(i3, ((-i6) ^ i4) & 15, pointAffine);
                F.cnegate(i6, pointAffine.x);
                pointAdd(pointAffine, pointProjective);
            }
            i--;
            if (i < 0) {
                return;
            }
            pointDouble(pointProjective);
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointProjective pointProjective = new PointProjective();
        scalarMultBase(bArr, pointProjective);
        if (encodePoint(pointProjective, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseXY(X448.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by X448");
        }
        byte[] bArr2 = new byte[57];
        pruneScalar(bArr, i, bArr2);
        PointProjective pointProjective = new PointProjective();
        scalarMultBase(bArr2, pointProjective);
        if (checkPoint(pointProjective.x, pointProjective.y, pointProjective.z) == 0) {
            throw new IllegalStateException();
        }
        F.copy(pointProjective.x, 0, iArr, 0);
        F.copy(pointProjective.y, 0, iArr2, 0);
    }

    private static void scalarMultOrderVar(PointProjective pointProjective, PointProjective pointProjective2) {
        byte[] wnafVar = getWnafVar(L, 5);
        PointProjective[] pointProjectiveArr = new PointProjective[8];
        pointPrecomputeVar(pointProjective, pointProjectiveArr, 8);
        pointSetNeutral(pointProjective2);
        int i = 446;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> BuiltinOptions.SequenceRNNOptions;
                pointAddVar(i2 != 0, pointProjectiveArr[(b ^ i2) >>> 1], pointProjective2);
            }
            i--;
            if (i < 0) {
                return;
            }
            pointDouble(pointProjective2);
        }
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointProjective pointProjective, PointProjective pointProjective2) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointProjective[] pointProjectiveArr = new PointProjective[8];
        pointPrecomputeVar(pointProjective, pointProjectiveArr, 8);
        pointSetNeutral(pointProjective2);
        int i = 446;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> BuiltinOptions.SequenceRNNOptions;
                pointAddVar(i2 != 0, PRECOMP_BASE_WNAF[(b ^ i2) >>> 1], pointProjective2);
            }
            byte b2 = wnafVar2[i];
            if (b2 != 0) {
                int i3 = b2 >> BuiltinOptions.SequenceRNNOptions;
                pointAddVar(i3 != 0, pointProjectiveArr[(b2 ^ i3) >>> 1], pointProjective2);
            }
            i--;
            if (i < 0) {
                return;
            }
            pointDouble(pointProjective2);
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 != xof.doFinal(bArr5, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, 64, bArr4, i3);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Xof xof, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 != xof.doFinal(bArr4, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, 64, bArr3, i2);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int i) {
        PointProjective pointProjective = new PointProjective();
        if (decodePointVar(bArr, i, false, pointProjective)) {
            F.normalize(pointProjective.x);
            F.normalize(pointProjective.y);
            F.normalize(pointProjective.z);
            if (isNeutralElementVar(pointProjective.x, pointProjective.y, pointProjective.z)) {
                return false;
            }
            PointProjective pointProjective2 = new PointProjective();
            scalarMultOrderVar(pointProjective, pointProjective2);
            F.normalize(pointProjective2.x);
            F.normalize(pointProjective2.y);
            F.normalize(pointProjective2.z);
            return isNeutralElementVar(pointProjective2.x, pointProjective2.y, pointProjective2.z);
        }
        return false;
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int i) {
        return decodePointVar(bArr, i, false, new PointProjective());
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
