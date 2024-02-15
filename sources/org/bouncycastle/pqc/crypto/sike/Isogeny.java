package org.bouncycastle.pqc.crypto.sike;

import java.lang.reflect.Array;

/* loaded from: classes2.dex */
public class Isogeny {
    SIKEEngine engine;

    public Isogeny(SIKEEngine sIKEEngine) {
        this.engine = sIKEEngine;
    }

    private void xDBLADD_proj(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2add(pointProj.X, pointProj.Z, jArr4);
        this.engine.fpx.fp2sub(pointProj.X, pointProj.Z, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr4, pointProj.X);
        this.engine.fpx.fp2sub(pointProj2.X, pointProj2.Z, jArr6);
        this.engine.fpx.fp2correction(jArr6);
        this.engine.fpx.fp2add(pointProj2.X, pointProj2.Z, pointProj2.X);
        this.engine.fpx.fp2mul_mont(jArr4, jArr6, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr5, pointProj.Z);
        this.engine.fpx.fp2mul_mont(jArr5, pointProj2.X, jArr5);
        this.engine.fpx.fp2sub(pointProj.X, pointProj.Z, jArr6);
        this.engine.fpx.fp2mul_mont(pointProj.X, pointProj.Z, pointProj.X);
        this.engine.fpx.fp2mul_mont(jArr6, jArr3, pointProj2.X);
        this.engine.fpx.fp2sub(jArr4, jArr5, pointProj2.Z);
        this.engine.fpx.fp2add(pointProj2.X, pointProj.Z, pointProj.Z);
        this.engine.fpx.fp2add(jArr4, jArr5, pointProj2.X);
        this.engine.fpx.fp2mul_mont(pointProj.Z, jArr6, pointProj.Z);
        this.engine.fpx.fp2sqr_mont(pointProj2.Z, pointProj2.Z);
        this.engine.fpx.fp2sqr_mont(pointProj2.X, pointProj2.X);
        this.engine.fpx.fp2mul_mont(pointProj2.X, jArr2, pointProj2.X);
        this.engine.fpx.fp2mul_mont(pointProj2.Z, jArr, pointProj2.Z);
    }

    private void xDBL_e(PointProj pointProj, PointProj pointProj2, long[][] jArr, int i) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2copy(pointProj.X, pointProj2.X);
        this.engine.fpx.fp2copy(pointProj.Z, pointProj2.Z);
        for (int i2 = 0; i2 < i; i2++) {
            this.engine.fpx.fp2add(pointProj2.X, pointProj2.Z, jArr3);
            this.engine.fpx.fp2sub(pointProj2.X, pointProj2.Z, jArr4);
            this.engine.fpx.fp2sqr_mont(jArr3, jArr6);
            this.engine.fpx.fp2sqr_mont(jArr4, jArr7);
            this.engine.fpx.fp2sub(jArr6, jArr7, jArr5);
            this.engine.fpx.fp2mul_mont(jArr6, jArr7, pointProj2.X);
            this.engine.fpx.fp2mul_mont(jArr, jArr5, jArr2);
            this.engine.fpx.fp2add(jArr2, jArr7, jArr2);
            this.engine.fpx.fp2mul_mont(jArr5, jArr2, pointProj2.Z);
        }
    }

    private void xTPL(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_sub_p2(pointProj.X, pointProj.Z, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr3, jArr5);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.Z, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr6);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.X, jArr7);
        this.engine.fpx.mp2_add(pointProj.Z, pointProj.Z, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr7, jArr4);
        this.engine.fpx.mp2_sub_p2(jArr4, jArr6, jArr4);
        this.engine.fpx.mp2_sub_p2(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2mul_mont(jArr2, jArr6, jArr8);
        this.engine.fpx.fp2mul_mont(jArr6, jArr8, jArr6);
        this.engine.fpx.fp2mul_mont(jArr, jArr5, jArr9);
        this.engine.fpx.fp2mul_mont(jArr5, jArr9, jArr5);
        this.engine.fpx.mp2_sub_p2(jArr5, jArr6, jArr6);
        this.engine.fpx.mp2_sub_p2(jArr8, jArr9, jArr5);
        this.engine.fpx.fp2mul_mont(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2add(jArr6, jArr4, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr5);
        this.engine.fpx.fp2mul_mont(jArr7, jArr5, pointProj2.X);
        this.engine.fpx.fp2sub(jArr6, jArr4, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2mul_mont(jArr3, jArr4, pointProj2.Z);
    }

    private void xTPL_fast(PointProj pointProj, PointProj pointProj2, long[][] jArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2sqr_mont(pointProj.X, jArr2);
        this.engine.fpx.fp2sqr_mont(pointProj.Z, jArr3);
        this.engine.fpx.fp2add(jArr2, jArr3, jArr4);
        this.engine.fpx.fp2add(pointProj.X, pointProj.Z, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr5);
        this.engine.fpx.fp2sub(jArr5, jArr4, jArr5);
        this.engine.fpx.fp2mul_mont(jArr, jArr5, jArr5);
        this.engine.fpx.fp2add(jArr4, jArr5, jArr5);
        this.engine.fpx.fp2sub(jArr2, jArr3, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2mul_mont(jArr2, jArr5, jArr2);
        this.engine.fpx.fp2shl(jArr2, 2, jArr2);
        this.engine.fpx.fp2sub(jArr2, jArr4, jArr2);
        this.engine.fpx.fp2sqr_mont(jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(jArr3, jArr5, jArr3);
        this.engine.fpx.fp2shl(jArr3, 2, jArr3);
        this.engine.fpx.fp2sub(jArr3, jArr4, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr3, jArr3);
        this.engine.fpx.fp2mul_mont(pointProj.X, jArr3, pointProj2.X);
        this.engine.fpx.fp2mul_mont(pointProj.Z, jArr2, pointProj2.Z);
    }

    public void CompleteMPoint(long[][] jArr, PointProj pointProj, PointProjFull pointProjFull) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr10 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr3[0]);
        if (Fpx.subarrayEquals(pointProj.Z[0], jArr2[0], this.engine.params.NWORDS_FIELD) && Fpx.subarrayEquals(pointProj.Z[1], jArr2[1], this.engine.params.NWORDS_FIELD)) {
            this.engine.fpx.fp2copy(jArr2, pointProjFull.X);
            this.engine.fpx.fp2copy(jArr3, pointProjFull.Y);
            this.engine.fpx.fp2copy(jArr2, pointProjFull.Z);
            return;
        }
        this.engine.fpx.fp2mul_mont(pointProj.X, pointProj.Z, jArr4);
        this.engine.fpx.fpsubPRIME(pointProj.X[0], pointProj.Z[1], jArr9[0]);
        this.engine.fpx.fpaddPRIME(pointProj.X[1], pointProj.Z[0], jArr9[1]);
        this.engine.fpx.fpaddPRIME(pointProj.X[0], pointProj.Z[1], jArr10[0]);
        this.engine.fpx.fpsubPRIME(pointProj.X[1], pointProj.Z[0], jArr10[1]);
        this.engine.fpx.fp2mul_mont(jArr9, jArr10, jArr6);
        this.engine.fpx.fp2mul_mont(jArr, jArr4, jArr9);
        this.engine.fpx.fp2add(jArr9, jArr6, jArr10);
        this.engine.fpx.fp2mul_mont(jArr4, jArr10, jArr7);
        this.engine.fpx.sqrt_Fp2(jArr7, jArr5);
        this.engine.fpx.fp2copy(pointProj.Z, jArr8);
        this.engine.fpx.fp2inv_mont_bingcd(jArr8);
        this.engine.fpx.fp2mul_mont(pointProj.X, jArr8, pointProjFull.X);
        this.engine.fpx.fp2sqr_mont(jArr8, jArr9);
        this.engine.fpx.fp2mul_mont(jArr5, jArr9, pointProjFull.Y);
        this.engine.fpx.fp2copy(jArr3, pointProjFull.Z);
    }

    public void CompletePoint(PointProj pointProj, PointProjFull pointProjFull) {
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr8[0]);
        this.engine.fpx.fp2mul_mont(pointProj.X, pointProj.Z, jArr);
        this.engine.fpx.fpsubPRIME(pointProj.X[0], pointProj.Z[1], jArr6[0]);
        this.engine.fpx.fpaddPRIME(pointProj.X[1], pointProj.Z[0], jArr6[1]);
        this.engine.fpx.fpaddPRIME(pointProj.X[0], pointProj.Z[1], jArr7[0]);
        this.engine.fpx.fpsubPRIME(pointProj.X[1], pointProj.Z[0], jArr7[1]);
        this.engine.fpx.fp2mul_mont(jArr6, jArr7, jArr2);
        this.engine.fpx.fp2mul_mont(jArr, jArr2, jArr3);
        this.engine.fpx.sqrt_Fp2(jArr3, jArr4);
        this.engine.fpx.fp2copy(pointProj.Z, jArr5);
        this.engine.fpx.fp2inv_mont_bingcd(jArr5);
        this.engine.fpx.fp2mul_mont(pointProj.X, jArr5, pointProjFull.X);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr6);
        this.engine.fpx.fp2mul_mont(jArr4, jArr6, pointProjFull.Y);
        this.engine.fpx.fp2copy(jArr8, pointProjFull.Z);
    }

    public void Double(PointProj pointProj, PointProj pointProj2, long[][] jArr, int i) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2copy(pointProj.X, pointProj2.X);
        this.engine.fpx.fp2copy(pointProj.Z, pointProj2.Z);
        for (int i2 = 0; i2 < i; i2++) {
            this.engine.fpx.fp2add(pointProj2.X, pointProj2.Z, jArr3);
            this.engine.fpx.fp2sub(pointProj2.X, pointProj2.Z, jArr4);
            this.engine.fpx.fp2sqr_mont(jArr3, jArr6);
            this.engine.fpx.fp2sqr_mont(jArr4, jArr7);
            this.engine.fpx.fp2sub(jArr6, jArr7, jArr5);
            this.engine.fpx.fp2mul_mont(jArr6, jArr7, pointProj2.X);
            this.engine.fpx.fp2mul_mont(jArr, jArr5, jArr2);
            this.engine.fpx.fp2add(jArr2, jArr7, jArr2);
            this.engine.fpx.fp2mul_mont(jArr5, jArr2, pointProj2.Z);
        }
    }

    public void LADDER3PT(long[][] jArr, long[][] jArr2, long[][] jArr3, long[] jArr4, int i, PointProj pointProj, long[][] jArr5) {
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj3 = new PointProj(this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        int i2 = i == this.engine.params.ALICE ? this.engine.params.OALICE_BITS : this.engine.params.OBOB_BITS - 1;
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr6[0]);
        this.engine.fpx.mp2_add(jArr6, jArr6, jArr6);
        this.engine.fpx.mp2_add(jArr5, jArr6, jArr6);
        this.engine.fpx.fp2div2(jArr6, jArr6);
        this.engine.fpx.fp2div2(jArr6, jArr6);
        this.engine.fpx.fp2copy(jArr2, pointProj2.X);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj2.Z[0]);
        this.engine.fpx.fp2copy(jArr3, pointProj3.X);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj3.Z[0]);
        this.engine.fpx.fp2copy(jArr, pointProj.X);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj.Z[0]);
        this.engine.fpx.fpzero(pointProj.Z[1]);
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = (int) ((jArr4[i3 >>> 6] >>> (i3 & 63)) & 1);
            swap_points(pointProj, pointProj3, 0 - (i4 ^ i5));
            xDBLADD(pointProj2, pointProj3, pointProj.X, jArr6);
            this.engine.fpx.fp2mul_mont(pointProj3.X, pointProj.Z, pointProj3.X);
            i3++;
            i4 = i5;
        }
        swap_points(pointProj, pointProj3, 0 - (i4 ^ 0));
    }

    public void Ladder(PointProj pointProj, long[] jArr, long[][] jArr2, int i, PointProj pointProj2) {
        PointProj pointProj3 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj4 = new PointProj(this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr3[0]);
        Fpx fpx = this.engine.fpx;
        long[] jArr4 = jArr3[0];
        fpx.fpaddPRIME(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2add(jArr2, jArr3, jArr3);
        this.engine.fpx.fp2div2(jArr3, jArr3);
        this.engine.fpx.fp2div2(jArr3, jArr3);
        int i2 = i - 1;
        long j = jArr[i2 >> 6] >>> (i2 & 63);
        while (((int) (j & 1)) == 0) {
            i2--;
            j = jArr[i2 >> 6] >>> (i2 & 63);
        }
        this.engine.fpx.fp2copy(pointProj.X, pointProj3.X);
        this.engine.fpx.fp2copy(pointProj.Z, pointProj3.Z);
        xDBL_e(pointProj, pointProj4, jArr3, 1);
        int i3 = i2 - 1;
        int i4 = 0;
        while (i3 >= 0) {
            int i5 = (int) ((jArr[i3 >> 6] >>> (i3 & 63)) & 1);
            swap_points(pointProj3, pointProj4, 0 - (i4 ^ i5));
            xDBLADD_proj(pointProj3, pointProj4, pointProj.X, pointProj.Z, jArr3);
            i3--;
            i4 = i5;
        }
        swap_points(pointProj3, pointProj4, 0 - (i4 ^ 0));
        this.engine.fpx.fp2copy(pointProj3.X, pointProj2.X);
        this.engine.fpx.fp2copy(pointProj3.Z, pointProj2.Z);
    }

    public void eval_2_isog(PointProj pointProj, PointProj pointProj2) {
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_add(pointProj2.X, pointProj2.Z, jArr);
        this.engine.fpx.mp2_sub_p2(pointProj2.X, pointProj2.Z, jArr2);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.Z, jArr3);
        this.engine.fpx.mp2_sub_p2(pointProj.X, pointProj.Z, jArr4);
        this.engine.fpx.fp2mul_mont(jArr, jArr4, jArr);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, jArr2);
        this.engine.fpx.mp2_add(jArr, jArr2, jArr3);
        this.engine.fpx.mp2_sub_p2(jArr, jArr2, jArr4);
        this.engine.fpx.fp2mul_mont(pointProj.X, jArr3, pointProj.X);
        this.engine.fpx.fp2mul_mont(pointProj.Z, jArr4, pointProj.Z);
    }

    public void eval_3_isog(PointProj pointProj, long[][][] jArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.Z, jArr2);
        this.engine.fpx.mp2_sub_p2(pointProj.X, pointProj.Z, jArr3);
        this.engine.fpx.fp2mul_mont(jArr[0], jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(jArr[1], jArr3, jArr3);
        this.engine.fpx.mp2_add(jArr2, jArr3, jArr4);
        this.engine.fpx.mp2_sub_p2(jArr3, jArr2, jArr2);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(pointProj.X, jArr4, pointProj.X);
        this.engine.fpx.fp2mul_mont(pointProj.Z, jArr2, pointProj.Z);
    }

    public void eval_4_isog(PointProj pointProj, long[][][] jArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.Z, jArr2);
        this.engine.fpx.mp2_sub_p2(pointProj.X, pointProj.Z, jArr3);
        this.engine.fpx.fp2mul_mont(jArr2, jArr[1], pointProj.X);
        this.engine.fpx.fp2mul_mont(jArr3, jArr[2], pointProj.Z);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, jArr2);
        this.engine.fpx.fp2mul_mont(jArr[0], jArr2, jArr2);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.Z, jArr3);
        this.engine.fpx.mp2_sub_p2(pointProj.X, pointProj.Z, pointProj.Z);
        this.engine.fpx.fp2sqr_mont(jArr3, jArr3);
        this.engine.fpx.fp2sqr_mont(pointProj.Z, pointProj.Z);
        this.engine.fpx.mp2_add(jArr3, jArr2, pointProj.X);
        this.engine.fpx.mp2_sub_p2(pointProj.Z, jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(pointProj.X, jArr3, pointProj.X);
        this.engine.fpx.fp2mul_mont(pointProj.Z, jArr2, pointProj.Z);
    }

    public void get_2_isog(PointProj pointProj, long[][] jArr, long[][] jArr2) {
        this.engine.fpx.fp2sqr_mont(pointProj.X, jArr);
        this.engine.fpx.fp2sqr_mont(pointProj.Z, jArr2);
        this.engine.fpx.mp2_sub_p2(jArr2, jArr, jArr);
    }

    public void get_3_isog(PointProj pointProj, long[][] jArr, long[][] jArr2, long[][][] jArr3) {
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_sub_p2(pointProj.X, pointProj.Z, jArr3[0]);
        this.engine.fpx.fp2sqr_mont(jArr3[0], jArr4);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.Z, jArr3[1]);
        this.engine.fpx.fp2sqr_mont(jArr3[1], jArr5);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.X, jArr7);
        this.engine.fpx.fp2sqr_mont(jArr7, jArr7);
        this.engine.fpx.fp2sub(jArr7, jArr4, jArr6);
        this.engine.fpx.fp2sub(jArr7, jArr5, jArr7);
        this.engine.fpx.mp2_add(jArr4, jArr7, jArr8);
        this.engine.fpx.mp2_add(jArr8, jArr8, jArr8);
        this.engine.fpx.mp2_add(jArr5, jArr8, jArr8);
        this.engine.fpx.fp2mul_mont(jArr6, jArr8, jArr);
        this.engine.fpx.mp2_add(jArr5, jArr6, jArr8);
        this.engine.fpx.mp2_add(jArr8, jArr8, jArr8);
        this.engine.fpx.mp2_add(jArr4, jArr8, jArr8);
        this.engine.fpx.fp2mul_mont(jArr7, jArr8, jArr2);
    }

    public void get_4_isog(PointProj pointProj, long[][] jArr, long[][] jArr2, long[][][] jArr3) {
        this.engine.fpx.mp2_sub_p2(pointProj.X, pointProj.Z, jArr3[1]);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.Z, jArr3[2]);
        this.engine.fpx.fp2sqr_mont(pointProj.Z, jArr3[0]);
        Fpx fpx = this.engine.fpx;
        long[][] jArr4 = jArr3[0];
        fpx.mp2_add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr3[0], jArr2);
        Fpx fpx2 = this.engine.fpx;
        long[][] jArr5 = jArr3[0];
        fpx2.mp2_add(jArr5, jArr5, jArr5);
        this.engine.fpx.fp2sqr_mont(pointProj.X, jArr);
        this.engine.fpx.mp2_add(jArr, jArr, jArr);
        this.engine.fpx.fp2sqr_mont(jArr, jArr);
    }

    public void get_A(long[][] jArr, long[][] jArr2, long[][] jArr3, long[][] jArr4) {
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr7[0]);
        this.engine.fpx.fp2add(jArr, jArr2, jArr6);
        this.engine.fpx.fp2mul_mont(jArr, jArr2, jArr5);
        this.engine.fpx.fp2mul_mont(jArr3, jArr6, jArr4);
        this.engine.fpx.fp2add(jArr5, jArr4, jArr4);
        this.engine.fpx.fp2mul_mont(jArr5, jArr3, jArr5);
        this.engine.fpx.fp2sub(jArr4, jArr7, jArr4);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr5);
        this.engine.fpx.fp2add(jArr6, jArr3, jArr6);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2inv_mont(jArr5);
        this.engine.fpx.fp2mul_mont(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2sub(jArr4, jArr6, jArr4);
    }

    public void inv_3_way(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2mul_mont(jArr, jArr2, jArr4);
        this.engine.fpx.fp2mul_mont(jArr3, jArr4, jArr5);
        this.engine.fpx.fp2inv_mont(jArr5);
        this.engine.fpx.fp2mul_mont(jArr3, jArr5, jArr6);
        this.engine.fpx.fp2mul_mont(jArr6, jArr2, jArr7);
        this.engine.fpx.fp2mul_mont(jArr6, jArr, jArr2);
        this.engine.fpx.fp2mul_mont(jArr4, jArr5, jArr3);
        this.engine.fpx.fp2copy(jArr7, jArr);
    }

    public void j_inv(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2sqr_mont(jArr, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr2, jArr5);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr4);
        this.engine.fpx.fp2sub(jArr3, jArr4, jArr4);
        this.engine.fpx.fp2sub(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2sub(jArr4, jArr5, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr5);
        this.engine.fpx.fp2mul_mont(jArr3, jArr5, jArr3);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr5);
        this.engine.fpx.fp2mul_mont(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2inv_mont(jArr3);
        this.engine.fpx.fp2mul_mont(jArr3, jArr4, jArr3);
    }

    public void swap_points(PointProj pointProj, PointProj pointProj2, long j) {
        for (int i = 0; i < this.engine.params.NWORDS_FIELD; i++) {
            long j2 = (pointProj.X[0][i] ^ pointProj2.X[0][i]) & j;
            pointProj.X[0][i] = j2 ^ pointProj.X[0][i];
            pointProj2.X[0][i] = j2 ^ pointProj2.X[0][i];
            long j3 = (pointProj.X[1][i] ^ pointProj2.X[1][i]) & j;
            pointProj.X[1][i] = j3 ^ pointProj.X[1][i];
            pointProj2.X[1][i] = j3 ^ pointProj2.X[1][i];
            long j4 = (pointProj.Z[0][i] ^ pointProj2.Z[0][i]) & j;
            pointProj.Z[0][i] = j4 ^ pointProj.Z[0][i];
            pointProj2.Z[0][i] = j4 ^ pointProj2.Z[0][i];
            long j5 = (pointProj.Z[1][i] ^ pointProj2.Z[1][i]) & j;
            pointProj.Z[1][i] = j5 ^ pointProj.Z[1][i];
            pointProj2.Z[1][i] = j5 ^ pointProj2.Z[1][i];
        }
    }

    protected void xDBL(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_sub_p2(pointProj.X, pointProj.Z, jArr3);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.Z, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr3, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, pointProj2.Z);
        this.engine.fpx.fp2mul_mont(jArr4, pointProj2.Z, pointProj2.X);
        this.engine.fpx.mp2_sub_p2(jArr4, jArr3, jArr4);
        this.engine.fpx.fp2mul_mont(jArr, jArr4, jArr3);
        this.engine.fpx.mp2_add(pointProj2.Z, jArr3, pointProj2.Z);
        this.engine.fpx.fp2mul_mont(pointProj2.Z, jArr4, pointProj2.Z);
    }

    public void xDBLADD(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_add(pointProj.X, pointProj.Z, jArr3);
        this.engine.fpx.mp2_sub_p2(pointProj.X, pointProj.Z, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr3, pointProj.X);
        this.engine.fpx.mp2_sub_p2(pointProj2.X, pointProj2.Z, jArr5);
        this.engine.fpx.mp2_add(pointProj2.X, pointProj2.Z, pointProj2.X);
        this.engine.fpx.fp2mul_mont(jArr3, jArr5, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr4, pointProj.Z);
        this.engine.fpx.fp2mul_mont(jArr4, pointProj2.X, jArr4);
        this.engine.fpx.mp2_sub_p2(pointProj.X, pointProj.Z, jArr5);
        this.engine.fpx.fp2mul_mont(pointProj.X, pointProj.Z, pointProj.X);
        this.engine.fpx.fp2mul_mont(jArr2, jArr5, pointProj2.X);
        this.engine.fpx.mp2_sub_p2(jArr3, jArr4, pointProj2.Z);
        this.engine.fpx.mp2_add(pointProj2.X, pointProj.Z, pointProj.Z);
        this.engine.fpx.mp2_add(jArr3, jArr4, pointProj2.X);
        this.engine.fpx.fp2mul_mont(pointProj.Z, jArr5, pointProj.Z);
        this.engine.fpx.fp2sqr_mont(pointProj2.Z, pointProj2.Z);
        this.engine.fpx.fp2sqr_mont(pointProj2.X, pointProj2.X);
        this.engine.fpx.fp2mul_mont(pointProj2.Z, jArr, pointProj2.Z);
    }

    public void xDBLe(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2, int i) {
        this.engine.fpx.copy_words(pointProj, pointProj2);
        for (int i2 = 0; i2 < i; i2++) {
            xDBL(pointProj2, pointProj2, jArr, jArr2);
        }
    }

    public void xTPLe(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2, int i) {
        this.engine.fpx.copy_words(pointProj, pointProj2);
        for (int i2 = 0; i2 < i; i2++) {
            xTPL(pointProj2, pointProj2, jArr, jArr2);
        }
    }

    public void xTPLe_fast(PointProj pointProj, PointProj pointProj2, long[][] jArr, int i) {
        PointProj pointProj3 = new PointProj(this.engine.params.NWORDS_FIELD);
        this.engine.fpx.copy_words(pointProj, pointProj3);
        for (int i2 = 0; i2 < i; i2++) {
            xTPL_fast(pointProj3, pointProj3, jArr);
        }
        this.engine.fpx.copy_words(pointProj3, pointProj2);
    }
}
