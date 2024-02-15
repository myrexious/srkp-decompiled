package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
class ErrorCorrection {
    ErrorCorrection() {
    }

    static short LDDecode(int i, int i2, int i3, int i4) {
        return (short) (((((g(i) + g(i2)) + g(i3)) + g(i4)) - 98312) >>> 31);
    }

    static int abs(int i) {
        int i2 = i >> 31;
        return (i ^ i2) - i2;
    }

    static int f(int[] iArr, int i, int i2, int i3) {
        int i4 = (i3 * 2730) >> 25;
        int i5 = i4 - ((12288 - (i3 - (i4 * 12289))) >> 31);
        iArr[i] = (i5 >> 1) + (i5 & 1);
        int i6 = i5 - 1;
        iArr[i2] = (i6 >> 1) + (i6 & 1);
        return abs(i3 - ((iArr[i] * 2) * 12289));
    }

    static int g(int i) {
        int i2 = (i * 2730) >> 27;
        int i3 = i2 - ((49155 - (i - (49156 * i2))) >> 31);
        return abs((((i3 >> 1) + (i3 & 1)) * 98312) - i);
    }

    public static void helpRec(short[] sArr, short[] sArr2, byte[] bArr, byte b) {
        short s = 8;
        byte[] bArr2 = new byte[8];
        bArr2[0] = b;
        byte[] bArr3 = new byte[32];
        ChaCha20.process(bArr, bArr2, bArr3, 0, 32);
        int[] iArr = new int[8];
        int i = 0;
        while (i < 256) {
            int i2 = i + 0;
            int i3 = ((bArr3[i >>> 3] >>> (i & 7)) & 1) * 4;
            int i4 = i + 256;
            int i5 = i + 512;
            int i6 = i + 768;
            int f = (24577 - (((f(iArr, 0, 4, (sArr2[i2] * s) + i3) + f(iArr, 1, 5, (sArr2[i4] * s) + i3)) + f(iArr, 2, 6, (sArr2[i5] * s) + i3)) + f(iArr, 3, 7, (sArr2[i6] * 8) + i3))) >> 31;
            int i7 = ~f;
            int i8 = (i7 & iArr[0]) ^ (iArr[4] & f);
            int i9 = (iArr[1] & i7) ^ (iArr[5] & f);
            int i10 = (iArr[2] & i7) ^ (f & iArr[6]);
            int i11 = (i7 & iArr[3]) ^ (iArr[7] & f);
            sArr[i2] = (short) ((i8 - i11) & 3);
            sArr[i4] = (short) ((i9 - i11) & 3);
            sArr[i5] = (short) ((i10 - i11) & 3);
            sArr[i6] = (short) (3 & ((-f) + (i11 * 2)));
            i++;
            s = 8;
        }
    }

    public static void rec(byte[] bArr, short[] sArr, short[] sArr2) {
        Arrays.fill(bArr, (byte) 0);
        for (int i = 0; i < 256; i++) {
            int i2 = i + 0;
            int i3 = i + 768;
            short s = sArr2[i3];
            int i4 = ((sArr[i2] * 8) + 196624) - (((sArr2[i2] * 2) + s) * 12289);
            int i5 = i + 256;
            int i6 = ((sArr[i5] * 8) + 196624) - (((sArr2[i5] * 2) + s) * 12289);
            int i7 = i + 512;
            int i8 = i >>> 3;
            bArr[i8] = (byte) ((LDDecode(i4, i6, ((sArr[i7] * 8) + 196624) - (((sArr2[i7] * 2) + s) * 12289), ((sArr[i3] * 8) + 196624) - (s * 12289)) << (i & 7)) | bArr[i8]);
        }
    }
}
