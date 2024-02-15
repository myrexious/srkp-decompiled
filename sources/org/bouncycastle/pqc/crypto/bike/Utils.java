package org.bouncycastle.pqc.crypto.bike;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Utils {
    Utils() {
    }

    public static byte[] append0s(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
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

    public static int getHammingWeight(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i += b;
        }
        return i;
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

    public static byte[] xorBytes(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
        return bArr3;
    }
}
