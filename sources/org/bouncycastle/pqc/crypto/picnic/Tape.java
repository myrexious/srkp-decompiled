package org.bouncycastle.pqc.crypto.picnic;

import java.lang.reflect.Array;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes2.dex */
public class Tape {
    private PicnicEngine engine;
    int nTapes;
    int pos = 0;
    byte[][] tapes;

    public Tape(PicnicEngine picnicEngine) {
        this.engine = picnicEngine;
        this.tapes = (byte[][]) Array.newInstance(Byte.TYPE, picnicEngine.numMPCParties, picnicEngine.andSizeBytes * 2);
        this.nTapes = picnicEngine.numMPCParties;
    }

    private void tapesToParityBits(int[] iArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            Utils.setBitInWordArray(iArr, i2, Utils.parity16(tapesToWord()));
        }
    }

    public void computeAuxTape(byte[] bArr) {
        int[] iArr = new int[16];
        int[] iArr2 = new int[16];
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[16];
        int[] iArr5 = new int[16];
        int i = 0;
        iArr5[this.engine.stateSizeWords - 1] = 0;
        tapesToParityBits(iArr5, this.engine.stateSizeBits);
        KMatricesWithPointer KMatrixInv = LowmcConstants.KMatrixInv(this.engine);
        this.engine.matrix_mul(iArr4, iArr5, KMatrixInv.getData(), KMatrixInv.getMatrixPointer());
        if (bArr != null) {
            Pack.intToLittleEndian(Arrays.copyOf(iArr4, this.engine.stateSizeWords), bArr, 0);
        }
        int i2 = this.engine.numRounds;
        while (i2 > 0) {
            KMatricesWithPointer KMatrix = LowmcConstants.KMatrix(this.engine, i2);
            this.engine.matrix_mul(iArr, iArr4, KMatrix.getData(), KMatrix.getMatrixPointer());
            PicnicEngine picnicEngine = this.engine;
            picnicEngine.xor_array(iArr2, iArr2, iArr, 0, picnicEngine.stateSizeWords);
            int i3 = i2 - 1;
            KMatricesWithPointer LMatrixInv = LowmcConstants.LMatrixInv(this.engine, i3);
            this.engine.matrix_mul(iArr3, iArr2, LMatrixInv.getData(), LMatrixInv.getMatrixPointer());
            if (i2 == 1) {
                System.arraycopy(iArr5, 0, iArr2, 0, 16);
            } else {
                this.pos = this.engine.stateSizeBits * 2 * i3;
                tapesToParityBits(iArr2, this.engine.stateSizeBits);
            }
            this.pos = (this.engine.stateSizeBits * 2 * i3) + this.engine.stateSizeBits;
            this.engine.aux_mpc_sbox(iArr2, iArr3, this);
            i2--;
            i = 0;
        }
        this.pos = i;
    }

    public void setAuxBits(byte[] bArr) {
        int i = this.engine.numMPCParties - 1;
        int i2 = this.engine.stateSizeBits;
        int i3 = 0;
        for (int i4 = 0; i4 < this.engine.numRounds; i4++) {
            int i5 = 0;
            while (i5 < i2) {
                Utils.setBit(this.tapes[i], (i2 * 2 * i4) + i2 + i5, Utils.getBit(bArr, i3));
                i5++;
                i3++;
            }
        }
    }

    public int tapesToWord() {
        byte[] bArr = new byte[4];
        for (int i = 0; i < 16; i++) {
            Utils.setBit(bArr, i, Utils.getBit(this.tapes[i], this.pos));
        }
        this.pos++;
        return Pack.littleEndianToInt(bArr, 0);
    }
}
