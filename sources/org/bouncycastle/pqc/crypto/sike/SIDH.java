package org.bouncycastle.pqc.crypto.sike;

import java.lang.reflect.Array;

/* loaded from: classes2.dex */
public class SIDH {
    private SIKEEngine engine;

    public SIDH(SIKEEngine sIKEEngine) {
        this.engine = sIKEEngine;
    }

    public void EphemeralKeyGeneration_A(byte[] bArr, byte[] bArr2) {
        long[][] jArr;
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj3 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj4 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_ALICE];
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr8 = (long[][][]) Array.newInstance(Long.TYPE, 3, 2, this.engine.params.NWORDS_FIELD);
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_ALICE];
        long[] jArr9 = new long[this.engine.params.NWORDS_ORDER];
        init_basis(this.engine.params.A_gen, jArr2, jArr3, jArr4);
        long[][][] jArr10 = jArr8;
        init_basis(this.engine.params.B_gen, pointProj2.X, pointProj3.X, pointProj4.X);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj2.Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj3.Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj4.Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr5[0]);
        this.engine.fpx.mp2_add(jArr5, jArr5, jArr5);
        this.engine.fpx.mp2_add(jArr5, jArr5, jArr6);
        this.engine.fpx.mp2_add(jArr5, jArr6, jArr7);
        this.engine.fpx.mp2_add(jArr6, jArr6, jArr5);
        this.engine.fpx.decode_to_digits(bArr, 0, jArr9, this.engine.params.SECRETKEY_A_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.LADDER3PT(jArr2, jArr3, jArr4, jArr9, this.engine.params.ALICE, pointProj, jArr7);
        if (this.engine.params.OALICE_BITS % 2 == 1) {
            PointProj pointProj5 = new PointProj(this.engine.params.NWORDS_FIELD);
            this.engine.isogeny.xDBLe(pointProj, pointProj5, jArr5, jArr6, this.engine.params.OALICE_BITS - 1);
            jArr = jArr6;
            this.engine.isogeny.get_2_isog(pointProj5, jArr5, jArr);
            this.engine.isogeny.eval_2_isog(pointProj2, pointProj5);
            this.engine.isogeny.eval_2_isog(pointProj3, pointProj5);
            this.engine.isogeny.eval_2_isog(pointProj4, pointProj5);
            this.engine.isogeny.eval_2_isog(pointProj, pointProj5);
        } else {
            jArr = jArr6;
        }
        int i = 1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < this.engine.params.MAX_Alice) {
            while (i2 < this.engine.params.MAX_Alice - i) {
                pointProjArr[i3] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.X, pointProjArr[i3].X);
                this.engine.fpx.fp2copy(pointProj.Z, pointProjArr[i3].Z);
                iArr[i3] = i2;
                int i5 = this.engine.params.strat_Alice[i4];
                this.engine.isogeny.xDBLe(pointProj, pointProj, jArr5, jArr, i5 * 2);
                i2 += i5;
                i3++;
                i4++;
            }
            long[][][] jArr11 = jArr10;
            this.engine.isogeny.get_4_isog(pointProj, jArr5, jArr, jArr11);
            for (int i6 = 0; i6 < i3; i6++) {
                this.engine.isogeny.eval_4_isog(pointProjArr[i6], jArr11);
            }
            this.engine.isogeny.eval_4_isog(pointProj2, jArr11);
            this.engine.isogeny.eval_4_isog(pointProj3, jArr11);
            this.engine.isogeny.eval_4_isog(pointProj4, jArr11);
            int i7 = i3 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i7].X, pointProj.X);
            this.engine.fpx.fp2copy(pointProjArr[i7].Z, pointProj.Z);
            i2 = iArr[i7];
            i3--;
            i++;
            jArr10 = jArr11;
        }
        long[][][] jArr12 = jArr10;
        this.engine.isogeny.get_4_isog(pointProj, jArr5, jArr, jArr12);
        this.engine.isogeny.eval_4_isog(pointProj2, jArr12);
        this.engine.isogeny.eval_4_isog(pointProj3, jArr12);
        this.engine.isogeny.eval_4_isog(pointProj4, jArr12);
        this.engine.isogeny.inv_3_way(pointProj2.Z, pointProj3.Z, pointProj4.Z);
        this.engine.fpx.fp2mul_mont(pointProj2.X, pointProj2.Z, pointProj2.X);
        this.engine.fpx.fp2mul_mont(pointProj3.X, pointProj3.Z, pointProj3.X);
        this.engine.fpx.fp2mul_mont(pointProj4.X, pointProj4.Z, pointProj4.X);
        this.engine.fpx.fp2_encode(pointProj2.X, bArr2, 0);
        this.engine.fpx.fp2_encode(pointProj3.X, bArr2, this.engine.params.FP2_ENCODED_BYTES);
        this.engine.fpx.fp2_encode(pointProj4.X, bArr2, this.engine.params.FP2_ENCODED_BYTES * 2);
    }

    public void EphemeralKeyGeneration_B(byte[] bArr, byte[] bArr2) {
        Isogeny isogeny;
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj3 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj4 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_BOB];
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        int[] iArr = {3, 2, this.engine.params.NWORDS_FIELD};
        int[] iArr2 = new int[this.engine.params.MAX_INT_POINTS_BOB];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        init_basis(this.engine.params.B_gen, jArr, jArr2, jArr3);
        long[][][] jArr8 = (long[][][]) Array.newInstance(Long.TYPE, iArr);
        init_basis(this.engine.params.A_gen, pointProj2.X, pointProj3.X, pointProj4.X);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj2.Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj3.Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj4.Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4[0]);
        this.engine.fpx.mp2_add(jArr4, jArr4, jArr4);
        this.engine.fpx.mp2_add(jArr4, jArr4, jArr5);
        this.engine.fpx.mp2_add(jArr4, jArr5, jArr6);
        this.engine.fpx.mp2_add(jArr5, jArr5, jArr4);
        this.engine.fpx.decode_to_digits(bArr, this.engine.params.MSG_BYTES, jArr7, this.engine.params.SECRETKEY_B_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.LADDER3PT(jArr, jArr2, jArr3, jArr7, this.engine.params.BOB, pointProj, jArr6);
        int i = 1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < this.engine.params.MAX_Bob) {
            while (i4 < this.engine.params.MAX_Bob - i) {
                pointProjArr[i2] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.X, pointProjArr[i2].X);
                this.engine.fpx.fp2copy(pointProj.Z, pointProjArr[i2].Z);
                int i5 = i2 + 1;
                iArr2[i2] = i4;
                int i6 = this.engine.params.strat_Bob[i3];
                this.engine.isogeny.xTPLe(pointProj, pointProj, jArr5, jArr4, i6);
                i4 += i6;
                i2 = i5;
                i3++;
            }
            long[][][] jArr9 = jArr8;
            this.engine.isogeny.get_3_isog(pointProj, jArr5, jArr4, jArr9);
            int i7 = 0;
            while (true) {
                isogeny = this.engine.isogeny;
                if (i7 < i2) {
                    isogeny.eval_3_isog(pointProjArr[i7], jArr9);
                    i7++;
                }
            }
            isogeny.eval_3_isog(pointProj2, jArr9);
            this.engine.isogeny.eval_3_isog(pointProj3, jArr9);
            this.engine.isogeny.eval_3_isog(pointProj4, jArr9);
            int i8 = i2 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i8].X, pointProj.X);
            this.engine.fpx.fp2copy(pointProjArr[i8].Z, pointProj.Z);
            i4 = iArr2[i8];
            i2--;
            i++;
            jArr8 = jArr9;
        }
        long[][][] jArr10 = jArr8;
        this.engine.isogeny.get_3_isog(pointProj, jArr5, jArr4, jArr10);
        this.engine.isogeny.eval_3_isog(pointProj2, jArr10);
        this.engine.isogeny.eval_3_isog(pointProj3, jArr10);
        this.engine.isogeny.eval_3_isog(pointProj4, jArr10);
        this.engine.isogeny.inv_3_way(pointProj2.Z, pointProj3.Z, pointProj4.Z);
        this.engine.fpx.fp2mul_mont(pointProj2.X, pointProj2.Z, pointProj2.X);
        this.engine.fpx.fp2mul_mont(pointProj3.X, pointProj3.Z, pointProj3.X);
        this.engine.fpx.fp2mul_mont(pointProj4.X, pointProj4.Z, pointProj4.X);
        this.engine.fpx.fp2_encode(pointProj2.X, bArr2, 0);
        this.engine.fpx.fp2_encode(pointProj3.X, bArr2, this.engine.params.FP2_ENCODED_BYTES);
        this.engine.fpx.fp2_encode(pointProj4.X, bArr2, this.engine.params.FP2_ENCODED_BYTES * 2);
    }

    public void EphemeralSecretAgreement_A(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        long[][] jArr;
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_ALICE];
        long[][][] jArr2 = (long[][][]) Array.newInstance(Long.TYPE, 3, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr3 = (long[][][]) Array.newInstance(Long.TYPE, 3, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_ALICE];
        long[] jArr8 = new long[this.engine.params.NWORDS_ORDER];
        this.engine.fpx.fp2_decode(bArr2, jArr2[0], 0);
        this.engine.fpx.fp2_decode(bArr2, jArr2[1], this.engine.params.FP2_ENCODED_BYTES);
        this.engine.fpx.fp2_decode(bArr2, jArr2[2], this.engine.params.FP2_ENCODED_BYTES * 2);
        this.engine.isogeny.get_A(jArr2[0], jArr2[1], jArr2[2], jArr7);
        this.engine.fpx.mp_add(this.engine.params.Montgomery_one, this.engine.params.Montgomery_one, jArr6[0], this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_add(jArr7, jArr6, jArr5);
        Fpx fpx = this.engine.fpx;
        long[] jArr9 = jArr6[0];
        fpx.mp_add(jArr9, jArr9, jArr9, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.decode_to_digits(bArr, 0, jArr8, this.engine.params.SECRETKEY_A_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.LADDER3PT(jArr2[0], jArr2[1], jArr2[2], jArr8, this.engine.params.ALICE, pointProj, jArr7);
        if (this.engine.params.OALICE_BITS % 2 == 1) {
            PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
            this.engine.isogeny.xDBLe(pointProj, pointProj2, jArr5, jArr6, this.engine.params.OALICE_BITS - 1);
            jArr = jArr5;
            this.engine.isogeny.get_2_isog(pointProj2, jArr, jArr6);
            this.engine.isogeny.eval_2_isog(pointProj, pointProj2);
        } else {
            jArr = jArr5;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 1; i4 < this.engine.params.MAX_Alice; i4++) {
            int i5 = i2;
            while (i5 < this.engine.params.MAX_Alice - i4) {
                pointProjArr[i3] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.X, pointProjArr[i3].X);
                this.engine.fpx.fp2copy(pointProj.Z, pointProjArr[i3].Z);
                iArr[i3] = i5;
                int i6 = this.engine.params.strat_Alice[i];
                this.engine.isogeny.xDBLe(pointProj, pointProj, jArr, jArr6, i6 * 2);
                i5 += i6;
                i3++;
                i++;
            }
            this.engine.isogeny.get_4_isog(pointProj, jArr, jArr6, jArr3);
            for (int i7 = 0; i7 < i3; i7++) {
                this.engine.isogeny.eval_4_isog(pointProjArr[i7], jArr3);
            }
            int i8 = i3 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i8].X, pointProj.X);
            this.engine.fpx.fp2copy(pointProjArr[i8].Z, pointProj.Z);
            i2 = iArr[i8];
            i3--;
        }
        this.engine.isogeny.get_4_isog(pointProj, jArr, jArr6, jArr3);
        this.engine.fpx.mp2_add(jArr, jArr, jArr);
        this.engine.fpx.fp2sub(jArr, jArr6, jArr);
        this.engine.fpx.fp2add(jArr, jArr, jArr);
        this.engine.isogeny.j_inv(jArr, jArr6, jArr4);
        this.engine.fpx.fp2_encode(jArr4, bArr3, 0);
    }

    public void EphemeralSecretAgreement_B(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_BOB];
        long[][][] jArr = (long[][][]) Array.newInstance(Long.TYPE, 3, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr2 = (long[][][]) Array.newInstance(Long.TYPE, 3, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_BOB];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        this.engine.fpx.fp2_decode(bArr2, jArr2[0], 0);
        this.engine.fpx.fp2_decode(bArr2, jArr2[1], this.engine.params.FP2_ENCODED_BYTES);
        this.engine.fpx.fp2_decode(bArr2, jArr2[2], this.engine.params.FP2_ENCODED_BYTES * 2);
        this.engine.isogeny.get_A(jArr2[0], jArr2[1], jArr2[2], jArr6);
        this.engine.fpx.mp_add(this.engine.params.Montgomery_one, this.engine.params.Montgomery_one, jArr5[0], this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_add(jArr6, jArr5, jArr4);
        this.engine.fpx.mp2_sub_p2(jArr6, jArr5, jArr5);
        this.engine.fpx.decode_to_digits(bArr, this.engine.params.MSG_BYTES, jArr7, this.engine.params.SECRETKEY_B_BYTES, this.engine.params.NWORDS_ORDER);
        int i = 1;
        long[][] jArr8 = jArr5;
        this.engine.isogeny.LADDER3PT(jArr2[0], jArr2[1], jArr2[2], jArr7, this.engine.params.BOB, pointProj, jArr6);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < this.engine.params.MAX_Bob) {
            int i5 = i3;
            while (i5 < this.engine.params.MAX_Bob - i) {
                pointProjArr[i4] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.X, pointProjArr[i4].X);
                this.engine.fpx.fp2copy(pointProj.Z, pointProjArr[i4].Z);
                iArr[i4] = i5;
                int i6 = this.engine.params.strat_Bob[i2];
                this.engine.isogeny.xTPLe(pointProj, pointProj, jArr8, jArr4, i6);
                i5 += i6;
                i4++;
                i2++;
            }
            long[][] jArr9 = jArr8;
            this.engine.isogeny.get_3_isog(pointProj, jArr9, jArr4, jArr);
            for (int i7 = 0; i7 < i4; i7++) {
                this.engine.isogeny.eval_3_isog(pointProjArr[i7], jArr);
            }
            int i8 = i4 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i8].X, pointProj.X);
            this.engine.fpx.fp2copy(pointProjArr[i8].Z, pointProj.Z);
            i3 = iArr[i8];
            i4--;
            i++;
            jArr8 = jArr9;
        }
        long[][] jArr10 = jArr8;
        this.engine.isogeny.get_3_isog(pointProj, jArr10, jArr4, jArr);
        this.engine.fpx.fp2add(jArr4, jArr10, jArr6);
        this.engine.fpx.fp2add(jArr6, jArr6, jArr6);
        this.engine.fpx.fp2sub(jArr4, jArr10, jArr4);
        this.engine.isogeny.j_inv(jArr6, jArr4, jArr3);
        this.engine.fpx.fp2_encode(jArr3, bArr3, 0);
    }

    protected void init_basis(long[] jArr, long[][] jArr2, long[][] jArr3, long[][] jArr4) {
        this.engine.fpx.fpcopy(jArr, 0, jArr2[0]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD, jArr2[1]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 2, jArr3[0]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 3, jArr3[1]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 4, jArr4[0]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 5, jArr4[1]);
    }
}
