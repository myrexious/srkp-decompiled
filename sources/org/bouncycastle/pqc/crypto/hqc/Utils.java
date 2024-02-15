package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.util.Pack;

/* loaded from: classes2.dex */
class Utils {
    Utils() {
    }

    public static long bitMask(long j, long j2) {
        return (1 << ((int) (j % j2))) - 1;
    }

    public static void copyBytes(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        System.arraycopy(iArr, i, iArr2, i2, i3 / 2);
    }

    public static void fromBitArrayToByteArray(byte[] bArr, byte[] bArr2) {
        long length = bArr2.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 8;
            if (i3 >= bArr2.length) {
                int i4 = bArr2[i];
                for (int length2 = (bArr2.length - i) - 1; length2 >= 1; length2--) {
                    i4 |= bArr2[i + length2] << length2;
                }
                bArr[i2] = (byte) i4;
            } else {
                int i5 = bArr2[i];
                for (int i6 = 7; i6 >= 1; i6--) {
                    i5 |= bArr2[i + i6] << i6;
                }
                bArr[i2] = (byte) i5;
            }
            i2++;
            i = i3;
        }
    }

    public static void fromBitArrayToLongArray(long[] jArr, byte[] bArr) {
        long length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 64;
            if (i3 >= bArr.length) {
                long j = bArr[i];
                for (int length2 = (bArr.length - i) - 1; length2 >= 1; length2--) {
                    j |= bArr[i + length2] << length2;
                }
                jArr[i2] = j;
            } else {
                long j2 = bArr[i];
                for (int i4 = 63; i4 >= 1; i4--) {
                    j2 |= bArr[i + i4] << i4;
                }
                jArr[i2] = j2;
            }
            i2++;
            i = i3;
        }
    }

    public static void fromByte32ArrayToLongArray(long[] jArr, int[] iArr) {
        for (int i = 0; i != iArr.length; i += 2) {
            int i2 = i / 2;
            long j = iArr[i] & BodyPartID.bodyIdMax;
            jArr[i2] = j;
            jArr[i2] = j | (iArr[i + 1] << 32);
        }
    }

    public static void fromByteArrayToBitArray(byte[] bArr, byte[] bArr2) {
        int length = bArr.length / 8;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            for (int i3 = 0; i3 != 8; i3++) {
                bArr[(i2 * 8) + i3] = (byte) ((bArr2[i2] & (1 << i3)) >>> i3);
            }
        }
        if (bArr.length % 8 != 0) {
            for (int i4 = length * 8; i4 < bArr.length; i4++) {
                bArr[i4] = (byte) ((bArr2[length] & (1 << i)) >>> i);
                i++;
            }
        }
    }

    public static void fromByteArrayToLongArray(long[] jArr, byte[] bArr) {
        if (bArr.length % 8 != 0) {
            byte[] bArr2 = new byte[((bArr.length + 7) / 8) * 8];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr = bArr2;
        }
        int i = 0;
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = Pack.littleEndianToLong(bArr, i);
            i += 8;
        }
    }

    public static byte[] fromListOfPos1ToBitArray(int[] iArr, int i) {
        byte[] bArr = new byte[i];
        for (int i2 : iArr) {
            bArr[i2] = 1;
        }
        return bArr;
    }

    public static void fromLongArrayToBitArray(byte[] bArr, long[] jArr) {
        int length = bArr.length / 64;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            for (int i3 = 0; i3 != 64; i3++) {
                bArr[(i2 * 64) + i3] = (byte) ((jArr[i2] & (1 << i3)) >>> i3);
            }
        }
        if (bArr.length % 64 != 0) {
            for (int i4 = length * 64; i4 < bArr.length; i4++) {
                bArr[i4] = (byte) ((jArr[length] & (1 << i)) >>> i);
                i++;
            }
        }
    }

    public static void fromLongArrayToByte32Array(int[] iArr, long[] jArr) {
        for (int i = 0; i != jArr.length; i++) {
            int i2 = i * 2;
            long j = jArr[i];
            iArr[i2] = (int) j;
            iArr[i2 + 1] = (int) (j >> 32);
        }
    }

    public static void fromLongArrayToByteArray(byte[] bArr, long[] jArr, int i) {
        int length = bArr.length / 8;
        int i2 = 0;
        for (int i3 = 0; i3 != length; i3++) {
            Pack.longToLittleEndian(jArr[i3], bArr, i3 * 8);
        }
        if (bArr.length % 8 != 0) {
            int i4 = length * 8;
            while (i4 < bArr.length) {
                bArr[i4] = (byte) (jArr[length] >>> (i2 * 8));
                i4++;
                i2++;
            }
        }
    }

    public static int getByte64SizeFromBitSize(int i) {
        return (i + 63) / 64;
    }

    public static int getByteSizeFromBitSize(int i) {
        return (i + 7) / 8;
    }

    public static byte[] removeLast0Bits(byte[] bArr) {
        int length = bArr.length - 1;
        while (true) {
            if (length < 0) {
                length = 0;
                break;
            } else if (bArr[length] == 1) {
                break;
            } else {
                length--;
            }
        }
        int i = length + 1;
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    public static void resizeArray(long[] jArr, int i, long[] jArr2, int i2, int i3, int i4) {
        if (i >= i2) {
            System.arraycopy(jArr2, 0, jArr, 0, (i2 + 7) / 8);
            return;
        }
        int i5 = i % 64;
        int i6 = i5 != 0 ? 64 - i5 : 0;
        System.arraycopy(jArr2, 0, jArr, 0, i3);
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = i4 - 1;
            jArr[i8] = jArr[i8] & (9223372036854775807 >> i7);
        }
    }

    public static int toUnsigned16Bits(int i) {
        return i & 65535;
    }

    public static int toUnsigned8bits(int i) {
        return i & 255;
    }
}
