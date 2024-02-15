package org.bouncycastle.pqc.crypto.bike;

import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Pack;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class BIKERandomGenerator {
    BIKERandomGenerator() {
    }

    private static int GetRandomInMod(int i, Xof xof) {
        int randomNumber;
        int maskNumber = maskNumber(bitScanReverse(i));
        do {
            randomNumber = getRandomNumber(xof) & maskNumber;
        } while (randomNumber >= i);
        return randomNumber;
    }

    private static int bitScanReverse(int i) {
        int i2 = 0;
        while (i != 0) {
            i >>= 1;
            i2++;
        }
        return i2;
    }

    private static int checkBit(byte[] bArr, int i) {
        return (bArr[i / 8] >> (i % 8)) & 1;
    }

    private static void generateRandomArray(byte[] bArr, int i, int i2, Xof xof) {
        int i3 = 0;
        while (i3 < i2) {
            int GetRandomInMod = GetRandomInMod(i, xof);
            if (checkBit(bArr, GetRandomInMod) == 0) {
                setBit(bArr, GetRandomInMod);
                i3++;
            }
        }
    }

    public static byte[] generateRandomByteArray(int i, int i2, int i3, Xof xof) {
        byte[] bArr = new byte[i2];
        generateRandomArray(bArr, i, i3, xof);
        return bArr;
    }

    private static int getRandomNumber(Xof xof) {
        byte[] bArr = new byte[4];
        xof.doOutput(bArr, 0, 4);
        return Pack.littleEndianToInt(bArr, 0);
    }

    private static int maskNumber(int i) {
        return (1 << i) - 1;
    }

    private static void setBit(byte[] bArr, int i) {
        int i2 = i / 8;
        bArr[i2] = (byte) ((1 << (i % 8)) | bArr[i2]);
    }
}
