package org.bouncycastle.pqc.crypto.sphincsplus;

import java.lang.reflect.Array;
import kotlin.jvm.internal.ByteCompanionObject;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
class HarakaSXof extends HarakaSBase {
    public HarakaSXof(byte[] bArr) {
        byte[] bArr2 = new byte[640];
        update(bArr, 0, bArr.length);
        doFinal(bArr2, 0, 640);
        this.haraka512_rc = (long[][]) Array.newInstance(Long.TYPE, 10, 8);
        this.haraka256_rc = (int[][]) Array.newInstance(Integer.TYPE, 10, 8);
        for (int i = 0; i < 10; i++) {
            interleaveConstant32(this.haraka256_rc[i], bArr2, i << 5);
            interleaveConstant(this.haraka512_rc[i], bArr2, i << 6);
        }
    }

    public int doFinal(byte[] bArr, int i, int i2) {
        int i3;
        byte[] bArr2 = this.buffer;
        int i4 = this.off;
        bArr2[i4] = (byte) (bArr2[i4] ^ BuiltinOptions.SequenceRNNOptions);
        byte[] bArr3 = this.buffer;
        bArr3[31] = (byte) (bArr3[31] ^ ByteCompanionObject.MIN_VALUE);
        this.off = 0;
        int i5 = i2;
        while (i5 > 0) {
            haraka512Perm(this.buffer);
            int i6 = 0;
            while (i6 < 32 && (i3 = i6 + i) < bArr.length) {
                bArr[i3] = this.buffer[i6];
                i6++;
            }
            i += i6;
            i5 -= i6;
        }
        if (i5 != 0) {
            byte[] bArr4 = new byte[64];
            haraka512Perm(bArr4);
            System.arraycopy(bArr4, 0, bArr, i, -i5);
        }
        reset();
        return i2;
    }

    public String getAlgorithmName() {
        return "Haraka-S";
    }

    public void update(byte b) {
        byte[] bArr = this.buffer;
        int i = this.off;
        this.off = i + 1;
        bArr[i] = (byte) (b ^ bArr[i]);
        if (this.off == 32) {
            haraka512Perm(this.buffer);
            this.off = 0;
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        int i3 = (this.off + i2) >> 5;
        int i4 = i;
        for (int i5 = 0; i5 < i3; i5++) {
            while (this.off < 32) {
                byte[] bArr2 = this.buffer;
                int i6 = this.off;
                this.off = i6 + 1;
                bArr2[i6] = (byte) (bArr[i4] ^ bArr2[i6]);
                i4++;
            }
            haraka512Perm(this.buffer);
            this.off = 0;
        }
        while (i4 < i + i2) {
            byte[] bArr3 = this.buffer;
            int i7 = this.off;
            this.off = i7 + 1;
            bArr3[i7] = (byte) (bArr3[i7] ^ bArr[i4]);
            i4++;
        }
    }
}
