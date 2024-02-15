package org.bouncycastle.pqc.crypto.sike;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class SIDH_Compressed {
    private static final int t_points = 2;
    private SIKEEngine engine;

    public SIDH_Compressed(SIKEEngine sIKEEngine) {
        this.engine = sIKEEngine;
    }

    private int EphemeralKeyGeneration_A(byte[] bArr, byte[] bArr2) {
        int[] iArr = new int[3];
        int[] iArr2 = new int[this.engine.params.DLEN_3];
        long[] jArr = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr2 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr3 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER];
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr6 = (long[][][]) Array.newInstance(Long.TYPE, 4, 2, this.engine.params.NWORDS_FIELD);
        long[][][][] jArr7 = (long[][][][]) Array.newInstance(Long.TYPE, this.engine.params.MAX_Alice + 1, 5, 2, this.engine.params.NWORDS_FIELD);
        PointProjFull[] pointProjFullArr = new PointProjFull[2];
        FullIsogeny_A_dual(bArr, jArr7, jArr5, 0);
        BuildOrdinary3nBasis_dual(jArr5, jArr7, pointProjFullArr, iArr, iArr, 2);
        Tate3_pairings(pointProjFullArr, jArr6);
        Dlogs3_dual(jArr6, iArr2, jArr2, jArr, jArr4, jArr3);
        Compress_PKA_dual(jArr2, jArr, jArr4, jArr3, jArr5, iArr, bArr2);
        return 0;
    }

    private boolean FirstPoint_dual(PointProj pointProj, PointProjFull pointProjFull, byte[] bArr) {
        PointProjFull pointProjFull2 = new PointProjFull(this.engine.params.NWORDS_FIELD);
        PointProjFull pointProjFull3 = new PointProjFull(this.engine.params.NWORDS_FIELD);
        long[][][] jArr = (long[][][]) Array.newInstance(Long.TYPE, 2, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr2 = (long[][][]) Array.newInstance(Long.TYPE, 2, 2, this.engine.params.NWORDS_FIELD);
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        int i = this.engine.params.NWORDS_FIELD;
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 0, pointProjFull2.X[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 1, pointProjFull2.X[1]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 2, pointProjFull2.Y[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 3, pointProjFull2.Y[1]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 4, pointProjFull3.X[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 5, pointProjFull3.X[1]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 6, pointProjFull3.Y[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 7, pointProjFull3.Y[1]);
        this.engine.isogeny.CompletePoint(pointProj, pointProjFull);
        Tate3_proj(pointProjFull2, pointProjFull, jArr[0], jArr2[0]);
        Tate3_proj(pointProjFull3, pointProjFull, jArr[1], jArr2[1]);
        FinalExpo3_2way(jArr, jArr2);
        this.engine.fpx.fp2correction(jArr[0]);
        this.engine.fpx.fp2correction(jArr[1]);
        int i2 = Fpx.subarrayEquals(jArr[0][1], jArr3, i) ? 0 : Fpx.subarrayEquals(jArr[0][1], this.engine.params.g_R_S_im, i) ? 1 : 2;
        int i3 = Fpx.subarrayEquals(jArr[1][1], jArr3, i) ? 0 : Fpx.subarrayEquals(jArr[1][1], this.engine.params.g_R_S_im, i) ? 1 : 2;
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        if (i2 == 0) {
            bArr[0] = 0;
        } else if (i3 == 0) {
            bArr[0] = 1;
        } else if (i2 + i3 == 3) {
            bArr[0] = 3;
        } else {
            bArr[0] = 2;
        }
        return true;
    }

    private boolean SecondPoint_dual(PointProj pointProj, PointProjFull pointProjFull, byte[] bArr) {
        PointProjFull pointProjFull2 = new PointProjFull(this.engine.params.NWORDS_FIELD);
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        int i = this.engine.params.NWORDS_FIELD;
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, ((bArr[0] * 4) + 0) * this.engine.params.NWORDS_FIELD, pointProjFull2.X[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, ((bArr[0] * 4) + 1) * this.engine.params.NWORDS_FIELD, pointProjFull2.X[1]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, ((bArr[0] * 4) + 2) * this.engine.params.NWORDS_FIELD, pointProjFull2.Y[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, ((bArr[0] * 4) + 3) * this.engine.params.NWORDS_FIELD, pointProjFull2.Y[1]);
        this.engine.isogeny.CompletePoint(pointProj, pointProjFull);
        Tate3_proj(pointProjFull2, pointProjFull, jArr, jArr2);
        FinalExpo3(jArr, jArr2);
        this.engine.fpx.fp2correction(jArr);
        return !Fpx.subarrayEquals(jArr[1], new long[this.engine.params.NWORDS_FIELD], i);
    }

    private void Tate2_pairings(PointProj pointProj, PointProj pointProj2, PointProjFull[] pointProjFullArr, long[][][] jArr) {
        long[][][] jArr2 = jArr;
        long[][][] jArr3 = (long[][][]) Array.newInstance(Long.TYPE, 4, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4[0]);
        for (int i = 0; i < 2; i++) {
            this.engine.fpx.fp2copy(jArr4, jArr2[i]);
            this.engine.fpx.fp2copy(jArr4, jArr2[i + 2]);
        }
        long[][] jArr10 = pointProj.X;
        long[][] jArr11 = pointProj.Z;
        long[] jArr12 = this.engine.params.T_tate2_firststep_P;
        long[] jArr13 = this.engine.params.T_tate2_firststep_P;
        this.engine.fpx.fpcopy(this.engine.params.T_tate2_firststep_P, this.engine.params.NWORDS_FIELD * 2, jArr5[0]);
        this.engine.fpx.fpcopy(this.engine.params.T_tate2_firststep_P, this.engine.params.NWORDS_FIELD * 3, jArr5[1]);
        int i2 = 0;
        while (i2 < 2) {
            this.engine.fpx.fp2sub(pointProjFullArr[i2].X, jArr10, jArr6);
            this.engine.fpx.fp2sub(pointProjFullArr[i2].Y, jArr11, jArr7);
            this.engine.fpx.fp2mul_mont(jArr5, jArr6, jArr6);
            this.engine.fpx.fp2sub(jArr6, jArr7, jArr8);
            long[][] jArr14 = jArr11;
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i2].X[0], this.engine.params.T_tate2_firststep_P, 0, jArr9[0]);
            this.engine.fpx.fpcopy(pointProjFullArr[i2].X[1], 0, jArr9[1]);
            this.engine.fpx.fpnegPRIME(jArr9[1]);
            this.engine.fpx.fp2mul_mont(jArr8, jArr9, jArr8);
            Fpx fpx = this.engine.fpx;
            long[][] jArr15 = jArr2[i2];
            fpx.fp2sqr_mont(jArr15, jArr15);
            Fpx fpx2 = this.engine.fpx;
            long[][] jArr16 = jArr2[i2];
            fpx2.fp2mul_mont(jArr16, jArr8, jArr16);
            i2++;
            jArr11 = jArr14;
        }
        int i3 = this.engine.params.NWORDS_FIELD * 1;
        long[] jArr17 = jArr12;
        long[] jArr18 = jArr13;
        int i4 = 0;
        int i5 = 0;
        while (i4 < this.engine.params.OALICE_BITS - 2) {
            long[] jArr19 = this.engine.params.T_tate2_P;
            long[] jArr20 = this.engine.params.T_tate2_P;
            long[] jArr21 = this.engine.params.T_tate2_P;
            long[][][] jArr22 = jArr3;
            int i6 = i4 * 3;
            int i7 = this.engine.params.NWORDS_FIELD * (i6 + 0);
            long[][] jArr23 = jArr5;
            int i8 = this.engine.params.NWORDS_FIELD * (i6 + 1);
            int i9 = this.engine.params.NWORDS_FIELD * (i6 + 2);
            int i10 = i4;
            int i11 = 0;
            for (int i12 = 2; i11 < i12; i12 = 2) {
                int i13 = i7;
                this.engine.fpx.fpsubPRIME(jArr17, i5, pointProjFullArr[i11].X[0], jArr6[1]);
                Fpx fpx3 = this.engine.fpx;
                long[] jArr24 = jArr6[1];
                fpx3.fpmul_mont(jArr21, i9, jArr24, jArr24);
                this.engine.fpx.fpmul_mont(jArr21, i9, pointProjFullArr[i11].X[1], jArr6[0]);
                this.engine.fpx.fpsubPRIME(pointProjFullArr[i11].Y[1], jArr18, i3, jArr7[1]);
                this.engine.fpx.fpsubPRIME(jArr6[1], jArr7[1], jArr8[1]);
                int i14 = i3;
                this.engine.fpx.fpsubPRIME(jArr6[0], pointProjFullArr[i11].Y[0], jArr8[0]);
                this.engine.fpx.fpsubPRIME(pointProjFullArr[i11].X[0], jArr19, i13, jArr9[0]);
                this.engine.fpx.fpcopy(pointProjFullArr[i11].X[1], 0, jArr9[1]);
                this.engine.fpx.fpnegPRIME(jArr9[1]);
                this.engine.fpx.fp2mul_mont(jArr8, jArr9, jArr8);
                Fpx fpx4 = this.engine.fpx;
                long[][] jArr25 = jArr[i11];
                fpx4.fp2sqr_mont(jArr25, jArr25);
                Fpx fpx5 = this.engine.fpx;
                long[][] jArr26 = jArr[i11];
                fpx5.fp2mul_mont(jArr26, jArr8, jArr26);
                i11++;
                jArr2 = jArr;
                i7 = i13;
                jArr17 = jArr17;
                i3 = i14;
            }
            int i15 = i7;
            i4 = i10 + 1;
            i3 = i8;
            jArr17 = jArr19;
            jArr18 = jArr20;
            jArr3 = jArr22;
            jArr5 = jArr23;
            i5 = i15;
        }
        long[][][] jArr27 = jArr2;
        long[] jArr28 = jArr17;
        long[][][] jArr29 = jArr3;
        long[][] jArr30 = jArr5;
        for (int i16 = 0; i16 < 2; i16++) {
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i16].X[0], jArr28, i5, jArr8[0]);
            this.engine.fpx.fpcopy(pointProjFullArr[i16].X[1], 0, jArr8[1]);
            Fpx fpx6 = this.engine.fpx;
            long[][] jArr31 = jArr27[i16];
            fpx6.fp2sqr_mont(jArr31, jArr31);
            Fpx fpx7 = this.engine.fpx;
            long[][] jArr32 = jArr27[i16];
            fpx7.fp2mul_mont(jArr32, jArr8, jArr32);
        }
        long[][] jArr33 = pointProj2.X;
        long[][] jArr34 = pointProj2.Z;
        long[] jArr35 = this.engine.params.T_tate2_firststep_Q;
        long[] jArr36 = this.engine.params.T_tate2_firststep_Q;
        int i17 = this.engine.params.NWORDS_FIELD * 1;
        this.engine.fpx.fpcopy(this.engine.params.T_tate2_firststep_Q, this.engine.params.NWORDS_FIELD * 2, jArr30[0]);
        this.engine.fpx.fpcopy(this.engine.params.T_tate2_firststep_Q, this.engine.params.NWORDS_FIELD * 3, jArr30[1]);
        int i18 = 0;
        while (i18 < 2) {
            this.engine.fpx.fp2sub(pointProjFullArr[i18].X, jArr33, jArr6);
            this.engine.fpx.fp2sub(pointProjFullArr[i18].Y, jArr34, jArr7);
            this.engine.fpx.fp2mul_mont(jArr30, jArr6, jArr6);
            this.engine.fpx.fp2sub(jArr6, jArr7, jArr8);
            long[][] jArr37 = jArr34;
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i18].X[0], jArr35, 0, jArr9[0]);
            long[][] jArr38 = jArr33;
            this.engine.fpx.fpcopy(pointProjFullArr[i18].X[1], 0, jArr9[1]);
            this.engine.fpx.fpnegPRIME(jArr9[1]);
            this.engine.fpx.fp2mul_mont(jArr8, jArr9, jArr8);
            Fpx fpx8 = this.engine.fpx;
            int i19 = i18 + 2;
            long[][] jArr39 = jArr27[i19];
            fpx8.fp2sqr_mont(jArr39, jArr39);
            Fpx fpx9 = this.engine.fpx;
            long[][] jArr40 = jArr27[i19];
            fpx9.fp2mul_mont(jArr40, jArr8, jArr40);
            i18++;
            jArr34 = jArr37;
            jArr33 = jArr38;
        }
        int i20 = 0;
        int i21 = 0;
        while (i20 < this.engine.params.OALICE_BITS - 2) {
            long[] jArr41 = this.engine.params.T_tate2_Q;
            long[] jArr42 = this.engine.params.T_tate2_Q;
            long[] jArr43 = this.engine.params.T_tate2_Q;
            int i22 = i20 * 3;
            int i23 = this.engine.params.NWORDS_FIELD * (i22 + 0);
            int i24 = this.engine.params.NWORDS_FIELD * (i22 + 1);
            int i25 = i20;
            int i26 = this.engine.params.NWORDS_FIELD * (i22 + 2);
            int i27 = 0;
            for (int i28 = 2; i27 < i28; i28 = 2) {
                this.engine.fpx.fpsubPRIME(pointProjFullArr[i27].X[0], jArr35, i21, jArr6[0]);
                Fpx fpx10 = this.engine.fpx;
                long[] jArr44 = jArr6[0];
                fpx10.fpmul_mont(jArr43, i26, jArr44, jArr44);
                this.engine.fpx.fpmul_mont(jArr43, i26, pointProjFullArr[i27].X[1], jArr6[1]);
                this.engine.fpx.fpsubPRIME(pointProjFullArr[i27].Y[0], jArr36, i17, jArr7[0]);
                long[] jArr45 = jArr36;
                this.engine.fpx.fpsubPRIME(jArr6[0], jArr7[0], jArr8[0]);
                this.engine.fpx.fpsubPRIME(jArr6[1], pointProjFullArr[i27].Y[1], jArr8[1]);
                jArr41 = jArr41;
                this.engine.fpx.fpsubPRIME(pointProjFullArr[i27].X[0], jArr41, i23, jArr9[0]);
                this.engine.fpx.fpcopy(pointProjFullArr[i27].X[1], 0, jArr9[1]);
                this.engine.fpx.fpnegPRIME(jArr9[1]);
                this.engine.fpx.fp2mul_mont(jArr8, jArr9, jArr8);
                Fpx fpx11 = this.engine.fpx;
                int i29 = i27 + 2;
                long[][] jArr46 = jArr[i29];
                fpx11.fp2sqr_mont(jArr46, jArr46);
                Fpx fpx12 = this.engine.fpx;
                long[][] jArr47 = jArr[i29];
                fpx12.fp2mul_mont(jArr47, jArr8, jArr47);
                i27++;
                jArr27 = jArr;
                i17 = i17;
                i21 = i21;
                jArr36 = jArr45;
            }
            i20 = i25 + 1;
            i17 = i24;
            jArr35 = jArr41;
            i21 = i23;
            jArr36 = jArr42;
        }
        int i30 = i21;
        long[][][] jArr48 = jArr27;
        for (int i31 = 0; i31 < 2; i31++) {
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i31].X[0], jArr35, i30, jArr8[0]);
            this.engine.fpx.fpcopy(pointProjFullArr[i31].X[1], 0, jArr8[1]);
            Fpx fpx13 = this.engine.fpx;
            int i32 = i31 + 2;
            long[][] jArr49 = jArr48[i32];
            fpx13.fp2sqr_mont(jArr49, jArr49);
            Fpx fpx14 = this.engine.fpx;
            long[][] jArr50 = jArr48[i32];
            fpx14.fp2mul_mont(jArr50, jArr8, jArr50);
        }
        this.engine.fpx.mont_n_way_inv(jArr48, 4, jArr29);
        for (int i33 = 0; i33 < 4; i33++) {
            long[][] jArr51 = jArr48[i33];
            final_exponentiation_2_torsion(jArr51, jArr29[i33], jArr51);
        }
    }

    private void Tate3_pairings(PointProjFull[] pointProjFullArr, long[][][] jArr) {
        long[][][] jArr2 = jArr;
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr6 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr7 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr8 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr9 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr10 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr11 = new long[this.engine.params.NWORDS_FIELD];
        long[][][] jArr12 = (long[][][]) Array.newInstance(Long.TYPE, 2, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr13 = (long[][][]) Array.newInstance(Long.TYPE, 4, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr14 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr15 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr16 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr17 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr18 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr19 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr20 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr21 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr22 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr23 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr14[0]);
        for (int i = 0; i < 2; i++) {
            this.engine.fpx.fp2copy(jArr14, jArr2[i]);
            this.engine.fpx.fp2copy(jArr14, jArr2[i + 2]);
            this.engine.fpx.fp2sqr_mont(pointProjFullArr[i].X, jArr12[i]);
        }
        int i2 = 0;
        while (i2 < this.engine.params.OBOB_EXPON - 1) {
            int i3 = i2 * 6;
            int i4 = i2;
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 0), jArr5, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 1), jArr6, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 2), jArr7, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 3), jArr8, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 4), jArr10, 0, this.engine.params.NWORDS_FIELD);
            char c = 0;
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 5), jArr11, 0, this.engine.params.NWORDS_FIELD);
            int i5 = 0;
            int i6 = 2;
            while (i5 < i6) {
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].X[c], jArr5, jArr15[c]);
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].X[1], jArr5, jArr15[1]);
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].X[0], jArr6, jArr17[0]);
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].X[1], jArr6, jArr17[1]);
                this.engine.fpx.fpaddPRIME(jArr12[i5][0], jArr10, jArr19[0]);
                long[] jArr24 = jArr6;
                this.engine.fpx.fpcopy(jArr12[i5][1], 0, jArr19[1]);
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].X[0], jArr11, jArr20[0]);
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].X[1], jArr11, jArr20[1]);
                long[][] jArr25 = jArr16;
                this.engine.fpx.fp2sub(jArr15, pointProjFullArr[i5].Y, jArr25);
                Fpx fpx = this.engine.fpx;
                long[] jArr26 = jArr25[0];
                fpx.fpaddPRIME(jArr26, jArr7, jArr26);
                long[][] jArr27 = jArr17;
                long[][] jArr28 = jArr18;
                this.engine.fpx.fp2sub(jArr27, pointProjFullArr[i5].Y, jArr28);
                Fpx fpx2 = this.engine.fpx;
                long[] jArr29 = jArr10;
                long[] jArr30 = jArr28[0];
                fpx2.fpaddPRIME(jArr30, jArr8, jArr30);
                long[][] jArr31 = jArr21;
                this.engine.fpx.fp2mul_mont(jArr25, jArr28, jArr31);
                long[] jArr32 = jArr11;
                long[][] jArr33 = jArr19;
                long[][] jArr34 = jArr20;
                this.engine.fpx.fp2sub(jArr33, jArr34, jArr22);
                this.engine.fpx.fp2_conj(jArr22, jArr22);
                this.engine.fpx.fp2mul_mont(jArr31, jArr22, jArr31);
                long[] jArr35 = jArr5;
                this.engine.fpx.fp2sqr_mont(jArr2[i5], jArr23);
                Fpx fpx3 = this.engine.fpx;
                long[][] jArr36 = jArr2[i5];
                fpx3.fp2mul_mont(jArr36, jArr23, jArr36);
                Fpx fpx4 = this.engine.fpx;
                long[][] jArr37 = jArr2[i5];
                fpx4.fp2mul_mont(jArr37, jArr31, jArr37);
                long[][] jArr38 = jArr23;
                this.engine.fpx.fpsubPRIME(jArr15[1], pointProjFullArr[i5].Y[0], jArr25[0]);
                this.engine.fpx.fpaddPRIME(jArr15[0], pointProjFullArr[i5].Y[1], jArr25[1]);
                this.engine.fpx.fpnegPRIME(jArr25[1]);
                Fpx fpx5 = this.engine.fpx;
                long[] jArr39 = jArr25[1];
                fpx5.fpaddPRIME(jArr39, jArr7, jArr39);
                this.engine.fpx.fpsubPRIME(jArr27[1], pointProjFullArr[i5].Y[0], jArr28[0]);
                this.engine.fpx.fpaddPRIME(jArr27[0], pointProjFullArr[i5].Y[1], jArr28[1]);
                this.engine.fpx.fpnegPRIME(jArr28[1]);
                Fpx fpx6 = this.engine.fpx;
                long[] jArr40 = jArr28[1];
                fpx6.fpaddPRIME(jArr40, jArr8, jArr40);
                this.engine.fpx.fp2mul_mont(jArr25, jArr28, jArr31);
                this.engine.fpx.fp2add(jArr33, jArr34, jArr22);
                this.engine.fpx.fp2_conj(jArr22, jArr22);
                this.engine.fpx.fp2mul_mont(jArr31, jArr22, jArr31);
                int i7 = i5 + 2;
                this.engine.fpx.fp2sqr_mont(jArr[i7], jArr38);
                Fpx fpx7 = this.engine.fpx;
                long[][] jArr41 = jArr[i7];
                fpx7.fp2mul_mont(jArr41, jArr38, jArr41);
                Fpx fpx8 = this.engine.fpx;
                long[][] jArr42 = jArr[i7];
                fpx8.fp2mul_mont(jArr42, jArr31, jArr42);
                i5++;
                jArr2 = jArr;
                jArr23 = jArr38;
                jArr20 = jArr34;
                jArr11 = jArr32;
                jArr12 = jArr12;
                jArr5 = jArr35;
                jArr15 = jArr15;
                jArr6 = jArr24;
                jArr21 = jArr31;
                jArr18 = jArr28;
                jArr19 = jArr33;
                jArr17 = jArr27;
                jArr10 = jArr29;
                i6 = 2;
                c = 0;
                jArr16 = jArr25;
            }
            i2 = i4 + 1;
            jArr11 = jArr11;
            jArr12 = jArr12;
            jArr15 = jArr15;
            jArr6 = jArr6;
            jArr21 = jArr21;
            jArr18 = jArr18;
            jArr19 = jArr19;
            jArr17 = jArr17;
            jArr10 = jArr10;
            jArr16 = jArr16;
        }
        long[][] jArr43 = jArr23;
        long[] jArr44 = jArr5;
        long[][] jArr45 = jArr16;
        long[][] jArr46 = jArr17;
        long[][] jArr47 = jArr21;
        long[][] jArr48 = jArr15;
        long[][][] jArr49 = jArr2;
        for (int i8 = 0; i8 < 2; i8++) {
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (((this.engine.params.OBOB_EXPON - 1) * 6) + 0), jArr3, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (((this.engine.params.OBOB_EXPON - 1) * 6) + 1), jArr4, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (((this.engine.params.OBOB_EXPON - 1) * 6) + 2), jArr44, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (((this.engine.params.OBOB_EXPON - 1) * 6) + 3), jArr9, 0, this.engine.params.NWORDS_FIELD);
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i8].X[0], jArr3, jArr48[0]);
            this.engine.fpx.fpcopy(pointProjFullArr[i8].X[1], 0, jArr48[1]);
            this.engine.fpx.fpmul_mont(jArr44, jArr48[0], jArr45[0]);
            this.engine.fpx.fpmul_mont(jArr44, jArr48[1], jArr45[1]);
            this.engine.fpx.fp2sub(jArr45, pointProjFullArr[i8].Y, jArr46);
            Fpx fpx9 = this.engine.fpx;
            long[] jArr50 = jArr46[0];
            fpx9.fpaddPRIME(jArr50, jArr4, jArr50);
            this.engine.fpx.fp2mul_mont(jArr48, jArr46, jArr47);
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i8].X[0], jArr9, jArr22[0]);
            this.engine.fpx.fpcopy(pointProjFullArr[i8].X[1], 0, jArr22[1]);
            this.engine.fpx.fpnegPRIME(jArr22[1]);
            this.engine.fpx.fp2mul_mont(jArr47, jArr22, jArr47);
            this.engine.fpx.fp2sqr_mont(jArr49[i8], jArr43);
            Fpx fpx10 = this.engine.fpx;
            long[][] jArr51 = jArr49[i8];
            fpx10.fp2mul_mont(jArr51, jArr43, jArr51);
            Fpx fpx11 = this.engine.fpx;
            long[][] jArr52 = jArr49[i8];
            fpx11.fp2mul_mont(jArr52, jArr47, jArr52);
            this.engine.fpx.fpaddPRIME(pointProjFullArr[i8].X[0], jArr3, jArr48[0]);
            this.engine.fpx.fpmul_mont(jArr44, jArr48[0], jArr45[0]);
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i8].Y[0], jArr45[1], jArr46[0]);
            this.engine.fpx.fpaddPRIME(pointProjFullArr[i8].Y[1], jArr45[0], jArr46[1]);
            Fpx fpx12 = this.engine.fpx;
            long[] jArr53 = jArr46[1];
            fpx12.fpsubPRIME(jArr53, jArr4, jArr53);
            this.engine.fpx.fp2mul_mont(jArr48, jArr46, jArr47);
            this.engine.fpx.fpaddPRIME(pointProjFullArr[i8].X[0], jArr9, jArr22[0]);
            this.engine.fpx.fp2mul_mont(jArr47, jArr22, jArr47);
            int i9 = i8 + 2;
            this.engine.fpx.fp2sqr_mont(jArr49[i9], jArr43);
            Fpx fpx13 = this.engine.fpx;
            long[][] jArr54 = jArr49[i9];
            fpx13.fp2mul_mont(jArr54, jArr43, jArr54);
            Fpx fpx14 = this.engine.fpx;
            long[][] jArr55 = jArr49[i9];
            fpx14.fp2mul_mont(jArr55, jArr47, jArr55);
        }
        this.engine.fpx.mont_n_way_inv(jArr49, 4, jArr13);
        for (int i10 = 0; i10 < 4; i10++) {
            long[][] jArr56 = jArr49[i10];
            final_exponentiation_3_torsion(jArr56, jArr13[i10], jArr56);
        }
    }

    private void final_exponentiation_2_torsion(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4);
        this.engine.fpx.fp2_conj(jArr, jArr5);
        this.engine.fpx.fp2mul_mont(jArr5, jArr2, jArr5);
        for (int i = 0; i < this.engine.params.OBOB_EXPON; i++) {
            this.engine.fpx.cube_Fp2_cycl(jArr5, jArr4);
        }
        this.engine.fpx.fp2copy(jArr5, jArr3);
    }

    private void final_exponentiation_3_torsion(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4);
        this.engine.fpx.fp2_conj(jArr, jArr5);
        this.engine.fpx.fp2mul_mont(jArr5, jArr2, jArr5);
        for (int i = 0; i < this.engine.params.OALICE_BITS; i++) {
            this.engine.fpx.sqr_Fp2_cycl(jArr5, jArr4);
        }
        this.engine.fpx.fp2copy(jArr5, jArr3);
    }

    private void from_base(int[] iArr, long[] jArr, int i, int i2) {
        long[] jArr2 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr3 = new long[this.engine.params.NWORDS_ORDER];
        long j = i2;
        new long[this.engine.params.NWORDS_ORDER][0] = j;
        int i3 = iArr[i - 1];
        if (i3 < 0) {
            jArr2[0] = (-i3) * j;
            if ((i2 & 1) == 0) {
                this.engine.fpx.Montgomery_neg(jArr2, this.engine.params.Alice_order);
                this.engine.fpx.copy_words(jArr2, jArr, this.engine.params.NWORDS_ORDER);
            } else {
                this.engine.fpx.mp_sub(this.engine.params.Bob_order, jArr2, jArr, this.engine.params.NWORDS_ORDER);
            }
        } else {
            jArr[0] = i3 * j;
        }
        int i4 = i - 2;
        while (true) {
            Arrays.fill(jArr2, 0L);
            if (i4 < 1) {
                break;
            }
            int i5 = iArr[i4];
            if (i5 < 0) {
                jArr2[0] = -i5;
                if ((i2 & 1) == 0) {
                    this.engine.fpx.Montgomery_neg(jArr2, this.engine.params.Alice_order);
                } else {
                    this.engine.fpx.mp_sub(this.engine.params.Bob_order, jArr2, jArr2, this.engine.params.NWORDS_ORDER);
                }
            } else {
                jArr2[0] = i5;
            }
            this.engine.fpx.mp_add(jArr, jArr2, jArr, this.engine.params.NWORDS_ORDER);
            int i6 = i2 & 1;
            if (i6 != 0 && !this.engine.fpx.is_orderelm_lt(jArr, this.engine.params.Bob_order)) {
                this.engine.fpx.mp_sub(jArr, this.engine.params.Bob_order, jArr, this.engine.params.NWORDS_ORDER);
            }
            if (i6 == 0) {
                for (int i7 = i2; i7 > 1; i7 /= 2) {
                    this.engine.fpx.mp_add(jArr, jArr, jArr, this.engine.params.NWORDS_ORDER);
                }
            } else {
                for (int i8 = i2; i8 > 1; i8 /= 3) {
                    Arrays.fill(jArr3, 0L);
                    this.engine.fpx.mp_add(jArr, jArr, jArr3, this.engine.params.NWORDS_ORDER);
                    if (!this.engine.fpx.is_orderelm_lt(jArr3, this.engine.params.Bob_order)) {
                        this.engine.fpx.mp_sub(jArr3, this.engine.params.Bob_order, jArr3, this.engine.params.NWORDS_ORDER);
                    }
                    this.engine.fpx.mp_add(jArr, jArr3, jArr, this.engine.params.NWORDS_ORDER);
                    if (!this.engine.fpx.is_orderelm_lt(jArr, this.engine.params.Bob_order)) {
                        this.engine.fpx.mp_sub(jArr, this.engine.params.Bob_order, jArr, this.engine.params.NWORDS_ORDER);
                    }
                }
            }
            i4--;
        }
        int i9 = iArr[0];
        if (i9 < 0) {
            jArr2[0] = -i9;
            if ((i2 & 1) == 0) {
                this.engine.fpx.Montgomery_neg(jArr2, this.engine.params.Alice_order);
            } else {
                this.engine.fpx.mp_sub(this.engine.params.Bob_order, jArr2, jArr2, this.engine.params.NWORDS_ORDER);
            }
        } else {
            jArr2[0] = i9;
        }
        this.engine.fpx.mp_add(jArr, jArr2, jArr, this.engine.params.NWORDS_ORDER);
        if ((i2 & 1) == 0 || this.engine.fpx.is_orderelm_lt(jArr, this.engine.params.Bob_order)) {
            return;
        }
        this.engine.fpx.mp_sub(jArr, this.engine.params.Bob_order, jArr, this.engine.params.NWORDS_ORDER);
    }

    protected void BiQuad_affine(long[][] jArr, long[][] jArr2, long[][] jArr3, PointProj pointProj) {
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2add(jArr, jArr, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2sub(jArr2, jArr3, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr5);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, jArr7);
        this.engine.fpx.fpsubPRIME(jArr7[0], this.engine.params.Montgomery_one, jArr7[0]);
        this.engine.fpx.fp2sqr_mont(jArr7, jArr7);
        this.engine.fpx.fpsubPRIME(jArr2[0], this.engine.params.Montgomery_one, jArr6[0]);
        this.engine.fpx.fpcopy(jArr2[1], 0, jArr6[1]);
        this.engine.fpx.fp2sqr_mont(jArr6, jArr6);
        this.engine.fpx.fp2mul_mont(jArr4, jArr2, jArr8);
        this.engine.fpx.fp2add(jArr6, jArr8, jArr6);
        this.engine.fpx.fp2mul_mont(jArr3, jArr6, jArr6);
        this.engine.fpx.fpsubPRIME(jArr3[0], this.engine.params.Montgomery_one, jArr8[0]);
        this.engine.fpx.fpcopy(jArr3[1], 0, jArr8[1]);
        this.engine.fpx.fp2sqr_mont(jArr8, jArr8);
        this.engine.fpx.fp2mul_mont(jArr4, jArr3, jArr9);
        this.engine.fpx.fp2add(jArr8, jArr9, jArr8);
        this.engine.fpx.fp2mul_mont(jArr2, jArr8, jArr8);
        this.engine.fpx.fp2add(jArr6, jArr8, jArr6);
        this.engine.fpx.fp2add(jArr6, jArr6, jArr6);
        this.engine.fpx.fp2sqr_mont(jArr6, jArr8);
        this.engine.fpx.fp2mul_mont(jArr5, jArr7, jArr9);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr9);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr9);
        this.engine.fpx.fp2sub(jArr8, jArr9, jArr8);
        this.engine.fpx.sqrt_Fp2(jArr8, jArr8);
        make_positive(jArr8);
        this.engine.fpx.fp2add(jArr6, jArr8, pointProj.X);
        this.engine.fpx.fp2add(jArr5, jArr5, pointProj.Z);
    }

    protected void BuildEntangledXonly(long[][] jArr, PointProj[] pointProjArr, byte[] bArr, byte[] bArr2) {
        long[][] jArr2;
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        if (this.engine.fpx.is_sqr_fp2(jArr, jArr3)) {
            jArr2 = this.engine.params.table_v_qnr;
            bArr[0] = 1;
        } else {
            jArr2 = this.engine.params.table_v_qr;
            bArr[0] = 0;
        }
        bArr2[0] = 0;
        int i = 0;
        do {
            this.engine.fpx.fp2mul_mont(jArr, jArr2, i, pointProjArr[0].X);
            i += 2;
            this.engine.fpx.fp2neg(pointProjArr[0].X);
            this.engine.fpx.fp2add(pointProjArr[0].X, jArr, jArr5);
            this.engine.fpx.fp2mul_mont(pointProjArr[0].X, jArr5, jArr5);
            this.engine.fpx.fpaddPRIME(jArr5[0], this.engine.params.Montgomery_one, jArr5[0]);
            this.engine.fpx.fp2mul_mont(pointProjArr[0].X, jArr5, jArr5);
            bArr2[0] = (byte) (bArr2[0] + 1);
        } while (!this.engine.fpx.is_sqr_fp2(jArr5, jArr3));
        bArr2[0] = (byte) (bArr2[0] - 1);
        if (bArr[0] == 1) {
            this.engine.fpx.fpcopy(this.engine.params.table_r_qnr[bArr2[0]], 0, jArr4[0]);
        } else {
            this.engine.fpx.fpcopy(this.engine.params.table_r_qr[bArr2[0]], 0, jArr4[0]);
        }
        this.engine.fpx.fp2add(pointProjArr[0].X, jArr, pointProjArr[1].X);
        this.engine.fpx.fp2neg(pointProjArr[1].X);
        this.engine.fpx.fp2sub(pointProjArr[0].X, pointProjArr[1].X, pointProjArr[2].Z);
        this.engine.fpx.fp2sqr_mont(pointProjArr[2].Z, pointProjArr[2].Z);
        this.engine.fpx.fpcopy(jArr4[0], 0, jArr4[1]);
        Fpx fpx = this.engine.fpx;
        long[] jArr6 = this.engine.params.Montgomery_one;
        long[] jArr7 = jArr4[0];
        fpx.fpaddPRIME(jArr6, jArr7, jArr7);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2mul_mont(jArr5, jArr4, pointProjArr[2].X);
    }

    protected void BuildEntangledXonly_Decomp(long[][] jArr, PointProj[] pointProjArr, int i, int i2) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        Internal internal = this.engine.params;
        long[][] jArr4 = i == 1 ? internal.table_v_qnr : internal.table_v_qr;
        if (i2 >= this.engine.params.TABLE_V_LEN / 2) {
            i2 = 0;
        }
        this.engine.fpx.fp2mul_mont(jArr, jArr4, i2 * 2, pointProjArr[0].X);
        this.engine.fpx.fp2neg(pointProjArr[0].X);
        this.engine.fpx.fp2add(pointProjArr[0].X, jArr, jArr3);
        this.engine.fpx.fp2mul_mont(pointProjArr[0].X, jArr3, jArr3);
        this.engine.fpx.fpaddPRIME(jArr3[0], this.engine.params.Montgomery_one, jArr3[0]);
        this.engine.fpx.fp2mul_mont(pointProjArr[0].X, jArr3, jArr3);
        if (i == 1) {
            this.engine.fpx.fpcopy(this.engine.params.table_r_qnr[i2], 0, jArr2[0]);
        } else {
            this.engine.fpx.fpcopy(this.engine.params.table_r_qr[i2], 0, jArr2[0]);
        }
        this.engine.fpx.fp2add(pointProjArr[0].X, jArr, pointProjArr[1].X);
        this.engine.fpx.fp2neg(pointProjArr[1].X);
        this.engine.fpx.fp2sub(pointProjArr[0].X, pointProjArr[1].X, pointProjArr[2].Z);
        this.engine.fpx.fp2sqr_mont(pointProjArr[2].Z, pointProjArr[2].Z);
        this.engine.fpx.fpcopy(jArr2[0], 0, jArr2[1]);
        Fpx fpx = this.engine.fpx;
        long[] jArr5 = this.engine.params.Montgomery_one;
        long[] jArr6 = jArr2[0];
        fpx.fpaddPRIME(jArr5, jArr6, jArr6);
        this.engine.fpx.fp2sqr_mont(jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(jArr3, jArr2, pointProjArr[2].X);
    }

    protected void BuildOrdinary2nBasis_dual(long[][] jArr, long[][][][] jArr2, PointProjFull[] pointProjFullArr, byte[] bArr, byte[] bArr2) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = {new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD)};
        BuildEntangledXonly(jArr, pointProjArr, bArr, bArr2);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[0].Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[1].Z[0]);
        for (int i = 0; i < this.engine.params.MAX_Bob; i++) {
            this.engine.isogeny.eval_3_isog(pointProjArr[0], jArr2[(this.engine.params.MAX_Bob - 1) - i]);
            this.engine.isogeny.eval_3_isog(pointProjArr[1], jArr2[(this.engine.params.MAX_Bob - 1) - i]);
            this.engine.isogeny.eval_3_isog(pointProjArr[2], jArr2[(this.engine.params.MAX_Bob - 1) - i]);
        }
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4[0]);
        Fpx fpx = this.engine.fpx;
        long[] jArr5 = jArr4[0];
        fpx.fpaddPRIME(jArr5, jArr5, jArr3);
        this.engine.fpx.fpaddPRIME(jArr3, jArr3, jArr4[0]);
        Fpx fpx2 = this.engine.fpx;
        long[] jArr6 = jArr4[0];
        fpx2.fpaddPRIME(jArr6, jArr3, jArr6);
        this.engine.isogeny.CompleteMPoint(jArr4, pointProjArr[0], pointProjFullArr[0]);
        RecoverY(jArr4, pointProjArr, pointProjFullArr);
    }

    protected void BuildOrdinary3nBasis_Decomp_dual(long[][] jArr, PointProj[] pointProjArr, int[] iArr, int[] iArr2, int i) {
        int i2 = iArr2[i];
        byte[] bArr = {(byte) (i2 & 1), (byte) ((i2 >>> 1) & 1)};
        iArr[0] = iArr[0] - 1;
        Elligator2(jArr, iArr, 0, pointProjArr[0].X, bArr, 0, 1);
        iArr[1] = iArr[1] - 1;
        Elligator2(jArr, iArr, 1, pointProjArr[1].X, bArr, 1, 1);
        BiQuad_affine(jArr, pointProjArr[0].X, pointProjArr[1].X, pointProjArr[2]);
    }

    protected void BuildOrdinary3nBasis_dual(long[][] jArr, long[][][][] jArr2, PointProjFull[] pointProjFullArr, int[] iArr, int[] iArr2, int i) {
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        long[][][] jArr3 = (long[][][]) Array.newInstance(Long.TYPE, 2, 2, this.engine.params.NWORDS_FIELD);
        byte[] bArr = new byte[1];
        byte[] bArr2 = new byte[1];
        FirstPoint3n(jArr, jArr2, jArr3[0], pointProjFullArr[0], iArr, bArr, bArr2);
        iArr2[i] = bArr2[0];
        iArr[1] = iArr[0];
        SecondPoint3n(jArr, jArr2, jArr3[1], pointProjFullArr[1], iArr, bArr, bArr2);
        iArr2[i] = iArr2[i] | (bArr2[0] << 1);
        BiQuad_affine(jArr, jArr3[0], jArr3[1], pointProj);
        eval_full_dual_4_isog(jArr2, pointProj);
        makeDiff(pointProjFullArr[0], pointProjFullArr[1], pointProj);
    }

    protected void Compress_PKA_dual(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[][] jArr5, int[] iArr, byte[] bArr) {
        char c;
        char c2;
        long[][] jArr6;
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr8 = new long[this.engine.params.NWORDS_ORDER];
        long[][] jArr9 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr9);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr9);
        this.engine.fpx.fpsubPRIME(jArr9[0], this.engine.params.Montgomery_one, jArr9[0]);
        this.engine.fpx.fpsubPRIME(jArr9[0], this.engine.params.Montgomery_one, jArr9[0]);
        int mod3 = this.engine.fpx.mod3(jArr3);
        this.engine.fpx.to_Montgomery_mod_order(jArr2, jArr2, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.to_Montgomery_mod_order(jArr4, jArr4, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.to_Montgomery_mod_order(jArr, jArr, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.to_Montgomery_mod_order(jArr3, jArr3, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        if (mod3 != 0) {
            this.engine.fpx.Montgomery_inversion_mod_order_bingcd(jArr3, jArr8, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
            this.engine.fpx.Montgomery_neg(jArr, this.engine.params.Bob_order);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, 0, this.engine.params.ORDER_B_ENCODED_BYTES);
            this.engine.fpx.Montgomery_neg(jArr4, this.engine.params.Bob_order);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr4, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, this.engine.params.ORDER_B_ENCODED_BYTES, this.engine.params.ORDER_B_ENCODED_BYTES);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr2, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, this.engine.params.ORDER_B_ENCODED_BYTES * 2, this.engine.params.ORDER_B_ENCODED_BYTES);
            bArr[(this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] = 0;
            c = 2;
            jArr6 = jArr9;
            c2 = 0;
        } else {
            c = 2;
            this.engine.fpx.Montgomery_inversion_mod_order_bingcd(jArr, jArr8, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
            this.engine.fpx.Montgomery_neg(jArr3, this.engine.params.Bob_order);
            c2 = 0;
            jArr6 = jArr9;
            this.engine.fpx.Montgomery_multiply_mod_order(jArr3, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, 0, this.engine.params.ORDER_B_ENCODED_BYTES);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr4, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, this.engine.params.ORDER_B_ENCODED_BYTES, this.engine.params.ORDER_B_ENCODED_BYTES);
            this.engine.fpx.Montgomery_neg(jArr2, this.engine.params.Bob_order);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr2, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, this.engine.params.ORDER_B_ENCODED_BYTES * 2, this.engine.params.ORDER_B_ENCODED_BYTES);
            bArr[(this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] = ByteCompanionObject.MIN_VALUE;
        }
        this.engine.fpx.fp2_encode(jArr6, bArr, this.engine.params.ORDER_B_ENCODED_BYTES * 3);
        int i = (this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES;
        bArr[i] = (byte) (bArr[i] | ((byte) iArr[c2]));
        bArr[(this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES + 1] = (byte) iArr[1];
        bArr[(this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES + 2] = (byte) iArr[c];
    }

    protected void Compress_PKB_dual(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[][] jArr5, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        if ((jArr3[0] & 1) == 1) {
            this.engine.fpx.inv_mod_orderA(jArr3, jArr7);
            this.engine.fpx.Montgomery_neg(jArr, this.engine.params.Alice_order);
            this.engine.fpx.multiply(jArr, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, 0, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i = this.engine.params.ORDER_A_ENCODED_BYTES - 1;
            bArr3[i] = (byte) (bArr3[i] & this.engine.params.MASK_ALICE);
            this.engine.fpx.Montgomery_neg(jArr4, this.engine.params.Alice_order);
            this.engine.fpx.multiply(jArr4, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i2 = (this.engine.params.ORDER_A_ENCODED_BYTES * 2) - 1;
            bArr3[i2] = (byte) (bArr3[i2] & this.engine.params.MASK_ALICE);
            this.engine.fpx.multiply(jArr2, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 2, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i3 = (this.engine.params.ORDER_A_ENCODED_BYTES * 3) - 1;
            bArr3[i3] = (byte) (bArr3[i3] & this.engine.params.MASK_ALICE);
            bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] = 0;
        } else {
            this.engine.fpx.inv_mod_orderA(jArr, jArr7);
            this.engine.fpx.Montgomery_neg(jArr3, this.engine.params.Alice_order);
            this.engine.fpx.multiply(jArr3, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, 0, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i4 = this.engine.params.ORDER_A_ENCODED_BYTES - 1;
            bArr3[i4] = (byte) (bArr3[i4] & this.engine.params.MASK_ALICE);
            this.engine.fpx.multiply(jArr4, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i5 = (this.engine.params.ORDER_A_ENCODED_BYTES * 2) - 1;
            bArr3[i5] = (byte) (bArr3[i5] & this.engine.params.MASK_ALICE);
            this.engine.fpx.Montgomery_neg(jArr2, this.engine.params.Alice_order);
            this.engine.fpx.multiply(jArr2, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 2, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i6 = (this.engine.params.ORDER_A_ENCODED_BYTES * 3) - 1;
            bArr3[i6] = (byte) (bArr3[i6] & this.engine.params.MASK_ALICE);
            bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] = ByteCompanionObject.MIN_VALUE;
        }
        this.engine.fpx.fp2_encode(jArr5, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 3);
        int i7 = (this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES;
        bArr3[i7] = (byte) (bArr3[i7] | bArr[0]);
        bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES + 1] = bArr2[0];
        bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES + 2] = 0;
    }

    protected void Compress_PKB_dual_extended(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[][] jArr5, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr8 = new long[this.engine.params.NWORDS_ORDER * 2];
        long j = (-1) >>> (this.engine.params.MAXBITS_ORDER - this.engine.params.OALICE_BITS);
        this.engine.fpx.multiply(jArr2, jArr3, jArr6, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.multiply(jArr4, jArr, jArr7, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.Montgomery_neg(jArr7, this.engine.params.Alice_order);
        this.engine.fpx.mp_add(jArr6, jArr7, jArr7, this.engine.params.NWORDS_ORDER);
        int i = this.engine.params.NWORDS_ORDER - 1;
        jArr7[i] = jArr7[i] & j;
        this.engine.fpx.inv_mod_orderA(jArr7, jArr8);
        this.engine.fpx.multiply(jArr3, jArr8, jArr6, this.engine.params.NWORDS_ORDER);
        int i2 = this.engine.params.NWORDS_ORDER - 1;
        jArr6[i2] = jArr6[i2] & j;
        this.engine.fpx.encode_to_bytes(jArr6, bArr3, 0, this.engine.params.ORDER_A_ENCODED_BYTES);
        this.engine.fpx.Montgomery_neg(jArr, this.engine.params.Alice_order);
        this.engine.fpx.multiply(jArr, jArr8, jArr6, this.engine.params.NWORDS_ORDER);
        int i3 = this.engine.params.NWORDS_ORDER - 1;
        jArr6[i3] = jArr6[i3] & j;
        this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.ORDER_A_ENCODED_BYTES);
        this.engine.fpx.Montgomery_neg(jArr4, this.engine.params.Alice_order);
        this.engine.fpx.multiply(jArr4, jArr8, jArr6, this.engine.params.NWORDS_ORDER);
        int i4 = this.engine.params.NWORDS_ORDER - 1;
        jArr6[i4] = jArr6[i4] & j;
        this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 2, this.engine.params.ORDER_A_ENCODED_BYTES);
        this.engine.fpx.multiply(jArr2, jArr8, jArr6, this.engine.params.NWORDS_ORDER);
        int i5 = this.engine.params.NWORDS_ORDER - 1;
        jArr6[i5] = jArr6[i5] & j;
        this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 3, this.engine.params.ORDER_A_ENCODED_BYTES);
        this.engine.fpx.fp2_encode(jArr5, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 4);
        bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 4) + this.engine.params.FP2_ENCODED_BYTES] = bArr[0];
        bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 4) + this.engine.params.FP2_ENCODED_BYTES + 1] = bArr2[0];
    }

    protected void Dlogs2_dual(long[][][] jArr, int[] iArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5) {
        solve_dlog(jArr[0], iArr, jArr2, 2);
        solve_dlog(jArr[2], iArr, jArr3, 2);
        solve_dlog(jArr[1], iArr, jArr4, 2);
        solve_dlog(jArr[3], iArr, jArr5, 2);
        this.engine.fpx.mp_sub(this.engine.params.Alice_order, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.mp_sub(this.engine.params.Alice_order, jArr5, jArr5, this.engine.params.NWORDS_ORDER);
    }

    protected void Dlogs3_dual(long[][][] jArr, int[] iArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5) {
        solve_dlog(jArr[0], iArr, jArr2, 3);
        solve_dlog(jArr[2], iArr, jArr3, 3);
        solve_dlog(jArr[1], iArr, jArr4, 3);
        solve_dlog(jArr[3], iArr, jArr5, 3);
        this.engine.fpx.mp_sub(this.engine.params.Bob_order, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.mp_sub(this.engine.params.Bob_order, jArr5, jArr5, this.engine.params.NWORDS_ORDER);
    }

    protected void Elligator2(long[][] jArr, int[] iArr, int i, long[][] jArr2, byte[] bArr, int i2, int i3) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr6 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr7 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr8 = new long[this.engine.params.NWORDS_FIELD];
        long[][] jArr9 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr10 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr3);
        this.engine.fpx.fp2add(jArr, jArr, jArr9);
        Fpx fpx = this.engine.fpx;
        long[] jArr11 = jArr9[0];
        fpx.fpsubPRIME(jArr11, jArr3, jArr11);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr9);
        this.engine.fpx.fp2mul_mont(jArr9, this.engine.params.v_3_torsion[iArr[i]], jArr2);
        this.engine.fpx.fp2neg(jArr2);
        if (i3 != 0) {
            if (bArr[i2] == 1) {
                this.engine.fpx.fp2neg(jArr2);
                this.engine.fpx.fp2sub(jArr2, jArr9, jArr2);
                return;
            }
            return;
        }
        this.engine.fpx.fp2add(jArr9, jArr2, jArr10);
        this.engine.fpx.fp2mul_mont(jArr10, jArr2, jArr10);
        Fpx fpx2 = this.engine.fpx;
        long[] jArr12 = jArr10[0];
        fpx2.fpaddPRIME(jArr12, jArr3, jArr12);
        this.engine.fpx.fp2mul_mont(jArr2, jArr10, jArr10);
        this.engine.fpx.fpsqr_mont(jArr10[0], jArr4);
        this.engine.fpx.fpsqr_mont(jArr10[1], jArr5);
        this.engine.fpx.fpaddPRIME(jArr4, jArr5, jArr6);
        this.engine.fpx.fpcopy(jArr6, 0, jArr7);
        for (int i4 = 0; i4 < this.engine.params.OALICE_BITS - 2; i4++) {
            this.engine.fpx.fpsqr_mont(jArr7, jArr7);
        }
        for (int i5 = 0; i5 < this.engine.params.OBOB_EXPON; i5++) {
            this.engine.fpx.fpsqr_mont(jArr7, jArr8);
            this.engine.fpx.fpmul_mont(jArr7, jArr8, jArr7);
        }
        this.engine.fpx.fpsqr_mont(jArr7, jArr8);
        this.engine.fpx.fpcorrectionPRIME(jArr8);
        this.engine.fpx.fpcorrectionPRIME(jArr6);
        if (Fpx.subarrayEquals(jArr8, jArr6, this.engine.params.NWORDS_FIELD)) {
            return;
        }
        this.engine.fpx.fp2neg(jArr2);
        this.engine.fpx.fp2sub(jArr2, jArr9, jArr2);
        if (i3 == 0) {
            bArr[i2] = 1;
        }
    }

    public int EphemeralKeyGeneration_A_extended(byte[] bArr, byte[] bArr2) {
        int[] iArr = new int[3];
        int[] iArr2 = new int[this.engine.params.DLEN_3];
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][][][] jArr2 = (long[][][][]) Array.newInstance(Long.TYPE, this.engine.params.MAX_Alice + 1, 5, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr3 = (long[][][]) Array.newInstance(Long.TYPE, 4, 2, this.engine.params.NWORDS_FIELD);
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr5 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        PointProjFull[] pointProjFullArr = {new PointProjFull(this.engine.params.NWORDS_FIELD), new PointProjFull(this.engine.params.NWORDS_FIELD)};
        FullIsogeny_A_dual(bArr, jArr2, jArr, 1);
        BuildOrdinary3nBasis_dual(jArr, jArr2, pointProjFullArr, iArr, iArr, 2);
        Tate3_pairings(pointProjFullArr, jArr3);
        Dlogs3_dual(jArr3, iArr2, jArr5, jArr4, jArr7, jArr6);
        Compress_PKA_dual(jArr5, jArr4, jArr7, jArr6, jArr, iArr, bArr2);
        return 0;
    }

    protected int EphemeralKeyGeneration_B(byte[] bArr, byte[] bArr2) {
        return EphemeralKeyGeneration_B_extended(bArr, bArr2, 0);
    }

    public int EphemeralKeyGeneration_B_extended(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = new byte[1];
        byte[] bArr4 = new byte[1];
        int[] iArr = new int[this.engine.params.DLEN_2];
        long[] jArr = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr2 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr3 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER];
        long[][][][] jArr5 = (long[][][][]) Array.newInstance(Long.TYPE, this.engine.params.MAX_Bob, 2, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr6 = (long[][][]) Array.newInstance(Long.TYPE, 4, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        PointProjFull[] pointProjFullArr = {new PointProjFull(this.engine.params.NWORDS_FIELD), new PointProjFull(this.engine.params.NWORDS_FIELD)};
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        FullIsogeny_B_dual(bArr, jArr5, jArr7);
        BuildOrdinary2nBasis_dual(jArr7, jArr5, pointProjFullArr, bArr3, bArr4);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, pointProjFullArr[0].X[0], pointProjFullArr[0].X[0]);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, pointProjFullArr[0].X[0], pointProjFullArr[0].X[0]);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, pointProjFullArr[1].X[0], pointProjFullArr[1].X[0]);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, pointProjFullArr[1].X[0], pointProjFullArr[1].X[0]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 0, pointProj.X[0]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 1, pointProj.X[1]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 2, pointProj.Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 3, pointProj.Z[1]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 4, pointProj2.X[0]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 5, pointProj2.X[1]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 6, pointProj2.Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 7, pointProj2.Z[1]);
        Tate2_pairings(pointProj, pointProj2, pointProjFullArr, jArr6);
        this.engine.fpx.fp2correction(jArr6[0]);
        this.engine.fpx.fp2correction(jArr6[1]);
        this.engine.fpx.fp2correction(jArr6[2]);
        this.engine.fpx.fp2correction(jArr6[3]);
        Dlogs2_dual(jArr6, iArr, jArr2, jArr, jArr4, jArr3);
        if (i == 1) {
            Compress_PKB_dual_extended(jArr2, jArr, jArr4, jArr3, jArr7, bArr3, bArr4, bArr2);
        } else {
            Compress_PKB_dual(jArr2, jArr, jArr4, jArr3, jArr7, bArr3, bArr4, bArr2);
        }
        return 0;
    }

    int EphemeralSecretAgreement_A(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        return EphemeralSecretAgreement_A_extended(bArr, i, bArr2, bArr3, 0);
    }

    public int EphemeralSecretAgreement_A_extended(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2) {
        long[][][] jArr;
        long[][] jArr2;
        long[][] jArr3;
        long[][] jArr4;
        PointProj[] pointProjArr;
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_ALICE];
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr2 = new PointProj[this.engine.params.MAX_INT_POINTS_ALICE];
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr10 = (long[][][]) Array.newInstance(Long.TYPE, 5, 2, this.engine.params.NWORDS_FIELD);
        if (i2 == 1) {
            jArr = jArr10;
            jArr2 = jArr9;
            jArr3 = jArr8;
            jArr4 = jArr7;
            pointProjArr = pointProjArr2;
            PKBDecompression_extended(bArr, i, bArr2, pointProj, jArr2, bArr3, this.engine.params.FP2_ENCODED_BYTES);
        } else {
            jArr = jArr10;
            jArr2 = jArr9;
            jArr3 = jArr8;
            jArr4 = jArr7;
            pointProjArr = pointProjArr2;
            PKBDecompression(bArr, i, bArr2, pointProj, jArr2);
        }
        this.engine.fpx.fp2copy(jArr2, jArr3);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, this.engine.params.Montgomery_one, jArr6[0]);
        this.engine.fpx.fp2add(jArr3, jArr6, jArr5);
        Fpx fpx = this.engine.fpx;
        long[] jArr11 = jArr6[0];
        fpx.fpaddPRIME(jArr11, jArr11, jArr11);
        if (this.engine.params.OALICE_BITS % 2 == 1) {
            PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
            this.engine.isogeny.xDBLe(pointProj, pointProj2, jArr5, jArr6, this.engine.params.OALICE_BITS - 1);
            this.engine.isogeny.get_2_isog(pointProj2, jArr5, jArr6);
            this.engine.isogeny.eval_2_isog(pointProj, pointProj2);
        }
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 < this.engine.params.MAX_Alice) {
            int i7 = i5;
            while (i7 < this.engine.params.MAX_Alice - i3) {
                pointProjArr[i6] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.X, pointProjArr[i6].X);
                this.engine.fpx.fp2copy(pointProj.Z, pointProjArr[i6].Z);
                iArr[i6] = i7;
                int i8 = i4 + 1;
                int i9 = this.engine.params.strat_Alice[i4];
                this.engine.isogeny.xDBLe(pointProj, pointProj, jArr5, jArr6, i9 * 2);
                i7 += i9;
                i6++;
                i4 = i8;
            }
            long[][][] jArr12 = jArr;
            this.engine.isogeny.get_4_isog(pointProj, jArr5, jArr6, jArr12);
            for (int i10 = 0; i10 < i6; i10++) {
                this.engine.isogeny.eval_4_isog(pointProjArr[i10], jArr12);
            }
            int i11 = i6 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i11].X, pointProj.X);
            this.engine.fpx.fp2copy(pointProjArr[i11].Z, pointProj.Z);
            i5 = iArr[i11];
            i6--;
            i3++;
            jArr = jArr12;
        }
        this.engine.isogeny.get_4_isog(pointProj, jArr5, jArr6, jArr);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr5);
        this.engine.fpx.fp2sub(jArr5, jArr6, jArr5);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr5);
        long[][] jArr13 = jArr4;
        this.engine.isogeny.j_inv(jArr5, jArr6, jArr13);
        this.engine.fpx.fp2_encode(jArr13, bArr3, 0);
        return 0;
    }

    public int EphemeralSecretAgreement_B(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_BOB];
        int i = 1;
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_BOB];
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr5 = (long[][][]) Array.newInstance(Long.TYPE, 3, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        PKADecompression_dual(bArr, bArr2, pointProj, jArr6);
        this.engine.fpx.fp2copy(jArr6, jArr4);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, this.engine.params.Montgomery_one, jArr2[0]);
        this.engine.fpx.fp2add(jArr4, jArr2, jArr);
        this.engine.fpx.fp2sub(jArr4, jArr2, jArr2);
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
                int i6 = i2 + 1;
                int i7 = this.engine.params.strat_Bob[i2];
                this.engine.isogeny.xTPLe(pointProj, pointProj, jArr2, jArr, i7);
                i5 += i7;
                i4++;
                jArr5 = jArr5;
                jArr4 = jArr4;
                i2 = i6;
                jArr3 = jArr3;
            }
            long[][][] jArr7 = jArr5;
            long[][] jArr8 = jArr3;
            long[][] jArr9 = jArr4;
            this.engine.isogeny.get_3_isog(pointProj, jArr2, jArr, jArr7);
            for (int i8 = 0; i8 < i4; i8++) {
                this.engine.isogeny.eval_3_isog(pointProjArr[i8], jArr7);
            }
            int i9 = i4 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i9].X, pointProj.X);
            this.engine.fpx.fp2copy(pointProjArr[i9].Z, pointProj.Z);
            i3 = iArr[i9];
            i4--;
            i++;
            jArr5 = jArr7;
            jArr4 = jArr9;
            jArr3 = jArr8;
        }
        long[][] jArr10 = jArr3;
        long[][] jArr11 = jArr4;
        this.engine.isogeny.get_3_isog(pointProj, jArr2, jArr, jArr5);
        this.engine.fpx.fp2add(jArr, jArr2, jArr11);
        this.engine.fpx.fp2add(jArr11, jArr11, jArr11);
        this.engine.fpx.fp2sub(jArr, jArr2, jArr);
        this.engine.isogeny.j_inv(jArr11, jArr, jArr10);
        this.engine.fpx.fp2_encode(jArr10, bArr3, 0);
        return 0;
    }

    protected void FinalExpo3(long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2copy(jArr2, jArr3);
        this.engine.fpx.fpnegPRIME(jArr3[1]);
        this.engine.fpx.fp2mul_mont(jArr, jArr3, jArr3);
        this.engine.fpx.fp2inv_mont_bingcd(jArr3);
        this.engine.fpx.fpnegPRIME(jArr[1]);
        this.engine.fpx.fp2mul_mont(jArr, jArr2, jArr);
        this.engine.fpx.fp2mul_mont(jArr, jArr3, jArr);
        for (int i = 0; i < this.engine.params.OALICE_BITS; i++) {
            this.engine.fpx.fp2sqr_mont(jArr, jArr);
        }
        for (int i2 = 0; i2 < this.engine.params.OBOB_EXPON - 1; i2++) {
            this.engine.fpx.cube_Fp2_cycl(jArr, this.engine.params.Montgomery_one);
        }
    }

    protected void FinalExpo3_2way(long[][][] jArr, long[][][] jArr2) {
        long[][][] jArr3 = (long[][][]) Array.newInstance(Long.TYPE, 2, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr4 = (long[][][]) Array.newInstance(Long.TYPE, 2, 2, this.engine.params.NWORDS_FIELD);
        for (int i = 0; i < 2; i++) {
            this.engine.fpx.fp2copy(jArr2[i], jArr3[i]);
            this.engine.fpx.fpnegPRIME(jArr3[i][1]);
            Fpx fpx = this.engine.fpx;
            long[][] jArr5 = jArr[i];
            long[][] jArr6 = jArr3[i];
            fpx.fp2mul_mont(jArr5, jArr6, jArr6);
        }
        this.engine.fpx.mont_n_way_inv(jArr3, 2, jArr4);
        for (int i2 = 0; i2 < 2; i2++) {
            this.engine.fpx.fpnegPRIME(jArr[i2][1]);
            Fpx fpx2 = this.engine.fpx;
            long[][] jArr7 = jArr[i2];
            fpx2.fp2mul_mont(jArr7, jArr2[i2], jArr7);
            Fpx fpx3 = this.engine.fpx;
            long[][] jArr8 = jArr[i2];
            fpx3.fp2mul_mont(jArr8, jArr4[i2], jArr8);
            for (int i3 = 0; i3 < this.engine.params.OALICE_BITS; i3++) {
                Fpx fpx4 = this.engine.fpx;
                long[][] jArr9 = jArr[i2];
                fpx4.fp2sqr_mont(jArr9, jArr9);
            }
            for (int i4 = 0; i4 < this.engine.params.OBOB_EXPON - 1; i4++) {
                this.engine.fpx.cube_Fp2_cycl(jArr[i2], this.engine.params.Montgomery_one);
            }
        }
    }

    protected void FirstPoint3n(long[][] jArr, long[][][][] jArr2, long[][] jArr3, PointProjFull pointProjFull, int[] iArr, byte[] bArr, byte[] bArr2) {
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        iArr[0] = 0;
        boolean z = false;
        while (!z) {
            bArr2[0] = 0;
            Elligator2(jArr, iArr, 0, jArr3, bArr2, 0, 0);
            this.engine.fpx.fp2copy(jArr3, pointProj.X);
            this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj.Z[0]);
            this.engine.fpx.fpcopy(jArr4, 0, pointProj.Z[1]);
            eval_full_dual_4_isog(jArr2, pointProj);
            boolean FirstPoint_dual = FirstPoint_dual(pointProj, pointProjFull, bArr);
            iArr[0] = iArr[0] + 1;
            z = FirstPoint_dual;
        }
    }

    public void FormatPrivKey_B(byte[] bArr) {
        int i = this.engine.params.SECRETKEY_B_BYTES - 2;
        bArr[i] = (byte) (bArr[i] & this.engine.params.MASK3_BOB);
        int i2 = this.engine.params.SECRETKEY_B_BYTES - 1;
        bArr[i2] = (byte) (bArr[i2] & this.engine.params.MASK2_BOB);
        this.engine.fpx.mul3(bArr);
    }

    protected void FullIsogeny_A_dual(byte[] bArr, long[][][][] jArr, long[][] jArr2, int i) {
        long[][] jArr3;
        long[][] jArr4;
        int i2;
        PointProj[] pointProjArr;
        char c;
        long[][][] jArr5;
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr2 = new PointProj[this.engine.params.MAX_INT_POINTS_ALICE];
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr10 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr11 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr12 = (long[][][]) Array.newInstance(Long.TYPE, 5, 2, this.engine.params.NWORDS_FIELD);
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_ALICE];
        long[] jArr13 = new long[this.engine.params.NWORDS_ORDER];
        init_basis(this.engine.params.A_gen, jArr6, jArr7, jArr8);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr9[0]);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr9);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr10);
        this.engine.fpx.fp2add(jArr9, jArr10, jArr11);
        this.engine.fpx.fp2add(jArr10, jArr10, jArr9);
        this.engine.fpx.decode_to_digits(bArr, this.engine.params.MSG_BYTES, jArr13, this.engine.params.SECRETKEY_A_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.LADDER3PT(jArr6, jArr7, jArr8, jArr13, this.engine.params.ALICE, pointProj, jArr11);
        this.engine.fpx.fp2inv_mont(pointProj.Z);
        this.engine.fpx.fp2mul_mont(pointProj.X, pointProj.Z, pointProj.X);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj.Z[0]);
        this.engine.fpx.fpzero(pointProj.Z[1]);
        if (i == 1) {
            this.engine.fpx.fp2_encode(pointProj.X, bArr, this.engine.params.MSG_BYTES + this.engine.params.SECRETKEY_A_BYTES + this.engine.params.CRYPTO_PUBLICKEYBYTES);
        }
        if (this.engine.params.OALICE_BITS % 2 == 1) {
            PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
            jArr3 = jArr10;
            jArr4 = jArr9;
            i2 = 1;
            c = 2;
            jArr5 = jArr12;
            pointProjArr = pointProjArr2;
            this.engine.isogeny.xDBLe(pointProj, pointProj2, jArr4, jArr3, this.engine.params.OALICE_BITS - 1);
            this.engine.isogeny.get_2_isog(pointProj2, jArr4, jArr3);
            this.engine.isogeny.eval_2_isog(pointProj, pointProj2);
            this.engine.fpx.fp2copy(pointProj2.X, jArr[this.engine.params.MAX_Alice][2]);
            this.engine.fpx.fp2copy(pointProj2.Z, jArr[this.engine.params.MAX_Alice][3]);
        } else {
            jArr3 = jArr10;
            jArr4 = jArr9;
            i2 = 1;
            pointProjArr = pointProjArr2;
            c = 2;
            jArr5 = jArr12;
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = i2;
        while (i6 < this.engine.params.MAX_Alice) {
            int i7 = i3;
            int i8 = i4;
            int i9 = i5;
            while (i9 < this.engine.params.MAX_Alice - i6) {
                pointProjArr[i7] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.X, pointProjArr[i7].X);
                this.engine.fpx.fp2copy(pointProj.Z, pointProjArr[i7].Z);
                iArr[i7] = i9;
                int i10 = i8 + 1;
                int i11 = this.engine.params.strat_Alice[i8];
                this.engine.isogeny.xDBLe(pointProj, pointProj, jArr4, jArr3, i11 * 2);
                i9 += i11;
                i7++;
                i8 = i10;
            }
            int i12 = i6 - 1;
            this.engine.fpx.fp2copy(jArr4, jArr[i12][0]);
            this.engine.fpx.fp2copy(jArr3, jArr[i12][i2]);
            get_4_isog_dual(pointProj, jArr4, jArr3, jArr5);
            for (int i13 = 0; i13 < i7; i13++) {
                this.engine.isogeny.eval_4_isog(pointProjArr[i13], jArr5);
            }
            int i14 = i7;
            eval_dual_4_isog_shared(jArr5[c], jArr5[3], jArr5[4], jArr[i12], 2);
            int i15 = i14 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i15].X, pointProj.X);
            this.engine.fpx.fp2copy(pointProjArr[i15].Z, pointProj.Z);
            i5 = iArr[i15];
            i3 = i14 - 1;
            i6++;
            i4 = i8;
        }
        this.engine.fpx.fp2copy(jArr4, jArr[this.engine.params.MAX_Alice - i2][0]);
        this.engine.fpx.fp2copy(jArr3, jArr[this.engine.params.MAX_Alice - i2][i2]);
        get_4_isog_dual(pointProj, jArr4, jArr3, jArr5);
        eval_dual_4_isog_shared(jArr5[c], jArr5[3], jArr5[4], jArr[this.engine.params.MAX_Alice - i2], 2);
        this.engine.fpx.fp2copy(jArr4, jArr[this.engine.params.MAX_Alice][0]);
        this.engine.fpx.fp2copy(jArr3, jArr[this.engine.params.MAX_Alice][i2]);
        this.engine.fpx.fp2inv_mont_bingcd(jArr3);
        this.engine.fpx.fp2mul_mont(jArr4, jArr3, jArr2);
    }

    protected void FullIsogeny_B_dual(byte[] bArr, long[][][][] jArr, long[][] jArr2) {
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_BOB];
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr8 = (long[][][]) Array.newInstance(Long.TYPE, 3, 2, this.engine.params.NWORDS_FIELD);
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_BOB];
        long[] jArr9 = new long[this.engine.params.NWORDS_ORDER];
        init_basis(this.engine.params.B_gen, jArr3, jArr4, jArr5);
        this.engine.fpx.fpcopy(this.engine.params.XQB3, 0, pointProj2.X[0]);
        this.engine.fpx.fpcopy(this.engine.params.XQB3, this.engine.params.NWORDS_FIELD, pointProj2.X[1]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj2.Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr6[0]);
        this.engine.fpx.fp2add(jArr6, jArr6, jArr6);
        this.engine.fpx.fp2add(jArr6, jArr6, jArr7);
        this.engine.fpx.fp2add(jArr6, jArr7, jArr2);
        this.engine.fpx.fp2add(jArr7, jArr7, jArr6);
        this.engine.fpx.decode_to_digits(bArr, 0, jArr9, this.engine.params.SECRETKEY_B_BYTES, this.engine.params.NWORDS_ORDER);
        long[][] jArr10 = jArr7;
        this.engine.isogeny.LADDER3PT(jArr3, jArr4, jArr5, jArr9, this.engine.params.BOB, pointProj, jArr2);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i4 < this.engine.params.MAX_Bob) {
            int i5 = i;
            while (i5 < this.engine.params.MAX_Bob - i4) {
                pointProjArr[i2] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.X, pointProjArr[i2].X);
                this.engine.fpx.fp2copy(pointProj.Z, pointProjArr[i2].Z);
                iArr[i2] = i5;
                int i6 = this.engine.params.strat_Bob[i3];
                this.engine.isogeny.xTPLe(pointProj, pointProj, jArr10, jArr6, i6);
                i5 += i6;
                i2++;
                i3++;
            }
            long[][] jArr11 = jArr10;
            this.engine.isogeny.get_3_isog(pointProj, jArr11, jArr6, jArr8);
            for (int i7 = 0; i7 < i2; i7++) {
                this.engine.isogeny.eval_3_isog(pointProjArr[i7], jArr8);
            }
            this.engine.isogeny.eval_3_isog(pointProj2, jArr8);
            int i8 = i4 - 1;
            int i9 = i3;
            this.engine.fpx.fp2sub(pointProj2.X, pointProj2.Z, jArr[i8][0]);
            this.engine.fpx.fp2add(pointProj2.X, pointProj2.Z, jArr[i8][1]);
            int i10 = i2 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i10].X, pointProj.X);
            this.engine.fpx.fp2copy(pointProjArr[i10].Z, pointProj.Z);
            i = iArr[i10];
            i2--;
            i4++;
            i3 = i9;
            jArr10 = jArr11;
        }
        long[][] jArr12 = jArr10;
        this.engine.isogeny.get_3_isog(pointProj, jArr12, jArr6, jArr8);
        this.engine.isogeny.eval_3_isog(pointProj2, jArr8);
        this.engine.fpx.fp2sub(pointProj2.X, pointProj2.Z, jArr[this.engine.params.MAX_Bob - 1][0]);
        this.engine.fpx.fp2add(pointProj2.X, pointProj2.Z, jArr[this.engine.params.MAX_Bob - 1][1]);
        this.engine.fpx.fp2add(jArr6, jArr12, jArr2);
        this.engine.fpx.fp2sub(jArr6, jArr12, jArr6);
        this.engine.fpx.fp2inv_mont_bingcd(jArr6);
        this.engine.fpx.fp2mul_mont(jArr6, jArr2, jArr2);
        this.engine.fpx.fp2add(jArr2, jArr2, jArr2);
    }

    protected void Ladder3pt_dual(PointProj[] pointProjArr, long[] jArr, int i, PointProj pointProj, long[][] jArr2) {
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj3 = new PointProj(this.engine.params.NWORDS_FIELD);
        int i2 = i == this.engine.params.ALICE ? this.engine.params.OALICE_BITS : this.engine.params.OBOB_BITS;
        this.engine.fpx.fp2copy(pointProjArr[1].X, pointProj2.X);
        this.engine.fpx.fp2copy(pointProjArr[1].Z, pointProj2.Z);
        this.engine.fpx.fp2copy(pointProjArr[2].X, pointProj3.X);
        this.engine.fpx.fp2copy(pointProjArr[2].Z, pointProj3.Z);
        this.engine.fpx.fp2copy(pointProjArr[0].X, pointProj.X);
        this.engine.fpx.fp2copy(pointProjArr[0].Z, pointProj.Z);
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = (int) ((jArr[i3 >>> 6] >>> (i3 & 63)) & 1);
            this.engine.isogeny.swap_points(pointProj, pointProj3, 0 - (i4 ^ i5));
            this.engine.isogeny.xDBLADD(pointProj2, pointProj3, pointProj.X, jArr2);
            this.engine.fpx.fp2mul_mont(pointProj3.X, pointProj.Z, pointProj3.X);
            i3++;
            i4 = i5;
        }
        this.engine.isogeny.swap_points(pointProj, pointProj3, 0 - (i4 ^ 0));
    }

    protected void PKADecompression_dual(byte[] bArr, byte[] bArr2, PointProj pointProj, long[][] jArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = {new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD)};
        long[] jArr3 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr5 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr8 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr9 = new long[this.engine.params.NWORDS_ORDER];
        this.engine.fpx.fp2_decode(bArr2, jArr, this.engine.params.ORDER_B_ENCODED_BYTES * 3);
        jArr7[0] = 1;
        this.engine.fpx.to_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        byte b = (byte) ((bArr2[(this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] & UByte.MAX_VALUE) >> 7);
        byte[] bArr3 = new byte[3];
        System.arraycopy(bArr2, (this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES, bArr3, 0, 3);
        int i = bArr3[0] & UByte.MAX_VALUE;
        int[] iArr = {i, bArr3[1] & UByte.MAX_VALUE, bArr3[2] & UByte.MAX_VALUE};
        iArr[0] = i & 127;
        this.engine.fpx.fpaddPRIME(jArr[0], this.engine.params.Montgomery_one, jArr2[0]);
        this.engine.fpx.fpcopy(jArr[1], 0, jArr2[1]);
        this.engine.fpx.fpaddPRIME(jArr2[0], this.engine.params.Montgomery_one, jArr2[0]);
        this.engine.fpx.fp2div2(jArr2, jArr2);
        this.engine.fpx.fp2div2(jArr2, jArr2);
        BuildOrdinary3nBasis_Decomp_dual(jArr2, pointProjArr, iArr, iArr, 2);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[0].Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[1].Z[0]);
        this.engine.isogeny.swap_points(pointProjArr[0], pointProjArr[1], -b);
        this.engine.fpx.decode_to_digits(bArr, 0, jArr9, this.engine.params.SECRETKEY_B_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.to_Montgomery_mod_order(jArr9, jArr3, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.decode_to_digits(bArr2, 0, jArr8, this.engine.params.ORDER_B_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.to_Montgomery_mod_order(jArr8, jArr4, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_B_ENCODED_BYTES, jArr8, this.engine.params.ORDER_B_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.to_Montgomery_mod_order(jArr8, jArr5, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.decode_to_digits(bArr2, 2 * this.engine.params.ORDER_B_ENCODED_BYTES, jArr8, this.engine.params.ORDER_B_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.to_Montgomery_mod_order(jArr8, jArr6, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        if (b == 0) {
            this.engine.fpx.Montgomery_multiply_mod_order(jArr3, jArr5, jArr5, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.mp_add(jArr5, jArr7, jArr5, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.Montgomery_inversion_mod_order_bingcd(jArr5, jArr5, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr3, jArr6, jArr6, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.mp_add(jArr4, jArr6, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr5, jArr6, jArr5, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr5, jArr5, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            Ladder3pt_dual(pointProjArr, jArr5, this.engine.params.BOB, pointProj, jArr2);
        } else {
            this.engine.fpx.Montgomery_multiply_mod_order(jArr3, jArr6, jArr6, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.mp_add(jArr6, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.Montgomery_inversion_mod_order_bingcd(jArr6, jArr6, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr3, jArr5, jArr5, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.mp_add(jArr4, jArr5, jArr5, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr5, jArr6, jArr5, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr5, jArr5, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            Ladder3pt_dual(pointProjArr, jArr5, this.engine.params.BOB, pointProj, jArr2);
        }
        this.engine.isogeny.Double(pointProj, pointProj, jArr2, this.engine.params.OALICE_BITS);
    }

    protected void PKBDecompression(byte[] bArr, int i, byte[] bArr2, PointProj pointProj, long[][] jArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[] jArr3 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr5 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        PointProj[] pointProjArr = new PointProj[3];
        long j = (-1) >>> (this.engine.params.MAXBITS_ORDER - this.engine.params.OALICE_BITS);
        jArr5[0] = 1;
        this.engine.fpx.fp2_decode(bArr2, jArr, this.engine.params.ORDER_A_ENCODED_BYTES * 3);
        int i2 = bArr2[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] >>> 7;
        BuildEntangledXonly_Decomp(jArr, pointProjArr, bArr2[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] & 1, bArr2[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES + 1]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[0].Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[1].Z[0]);
        this.engine.fpx.fpaddPRIME(jArr[0], this.engine.params.Montgomery_one, jArr2[0]);
        this.engine.fpx.fpcopy(jArr[1], 0, jArr2[1]);
        this.engine.fpx.fpaddPRIME(jArr2[0], this.engine.params.Montgomery_one, jArr2[0]);
        this.engine.fpx.fp2div2(jArr2, jArr2);
        this.engine.fpx.fp2div2(jArr2, jArr2);
        this.engine.fpx.decode_to_digits(bArr, i, jArr6, this.engine.params.SECRETKEY_A_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.swap_points(pointProjArr[0], pointProjArr[1], 0 - i2);
        if (i2 == 0) {
            this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_A_ENCODED_BYTES, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr6, jArr7, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr3, jArr5, jArr3, this.engine.params.NWORDS_ORDER);
            int i3 = this.engine.params.NWORDS_ORDER - 1;
            jArr3[i3] = jArr3[i3] & j;
            this.engine.fpx.inv_mod_orderA(jArr3, jArr4);
            this.engine.fpx.decode_to_digits(bArr2, 2 * this.engine.params.ORDER_A_ENCODED_BYTES, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr6, jArr7, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.decode_to_digits(bArr2, 0, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr7, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr3, jArr4, jArr5, this.engine.params.NWORDS_ORDER);
            int i4 = this.engine.params.NWORDS_ORDER - 1;
            jArr5[i4] = jArr5[i4] & j;
            Ladder3pt_dual(pointProjArr, jArr5, this.engine.params.ALICE, pointProj, jArr2);
        } else {
            this.engine.fpx.decode_to_digits(bArr2, 2 * this.engine.params.ORDER_A_ENCODED_BYTES, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr6, jArr7, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr3, jArr5, jArr3, this.engine.params.NWORDS_ORDER);
            int i5 = this.engine.params.NWORDS_ORDER - 1;
            jArr3[i5] = jArr3[i5] & j;
            this.engine.fpx.inv_mod_orderA(jArr3, jArr4);
            this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_A_ENCODED_BYTES, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr6, jArr7, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.decode_to_digits(bArr2, 0, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr7, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr3, jArr4, jArr5, this.engine.params.NWORDS_ORDER);
            int i6 = this.engine.params.NWORDS_ORDER - 1;
            jArr5[i6] = jArr5[i6] & j;
            Ladder3pt_dual(pointProjArr, jArr5, this.engine.params.ALICE, pointProj, jArr2);
        }
        this.engine.fpx.fp2div2(jArr, jArr2);
        this.engine.isogeny.xTPLe_fast(pointProj, pointProj, jArr2, this.engine.params.OBOB_EXPON);
    }

    protected void PKBDecompression_extended(byte[] bArr, int i, byte[] bArr2, PointProj pointProj, long[][] jArr, byte[] bArr3, int i2) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr5 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr8 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr9 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr10 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr11 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr12 = new long[this.engine.params.NWORDS_ORDER];
        PointProj[] pointProjArr = {new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD)};
        long j = (-1) >>> (this.engine.params.MAXBITS_ORDER - this.engine.params.OALICE_BITS);
        this.engine.fpx.fp2_decode(bArr2, jArr, this.engine.params.ORDER_A_ENCODED_BYTES * 4);
        BuildEntangledXonly_Decomp(jArr, pointProjArr, bArr2[(this.engine.params.ORDER_A_ENCODED_BYTES * 4) + this.engine.params.FP2_ENCODED_BYTES] & 1, bArr2[(this.engine.params.ORDER_A_ENCODED_BYTES * 4) + this.engine.params.FP2_ENCODED_BYTES + 1]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[0].Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[1].Z[0]);
        this.engine.fpx.fpaddPRIME(jArr[0], this.engine.params.Montgomery_one, jArr2[0]);
        this.engine.fpx.fpcopy(jArr[1], 0, jArr2[1]);
        this.engine.fpx.fpaddPRIME(jArr2[0], this.engine.params.Montgomery_one, jArr2[0]);
        this.engine.fpx.fp2div2(jArr2, jArr2);
        this.engine.fpx.fp2div2(jArr2, jArr2);
        this.engine.fpx.decode_to_digits(bArr, i, jArr8, this.engine.params.SECRETKEY_A_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.decode_to_digits(bArr2, 0, jArr9, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_A_ENCODED_BYTES, jArr11, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_A_ENCODED_BYTES * 2, jArr10, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_A_ENCODED_BYTES * 3, jArr12, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        if ((jArr9[0] & 1) == 1) {
            this.engine.fpx.multiply(jArr8, jArr12, jArr4, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr4, jArr11, jArr4, this.engine.params.NWORDS_ORDER);
            int i3 = this.engine.params.NWORDS_ORDER - 1;
            jArr4[i3] = jArr4[i3] & j;
            this.engine.fpx.multiply(jArr8, jArr10, jArr5, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr5, jArr9, jArr5, this.engine.params.NWORDS_ORDER);
            int i4 = this.engine.params.NWORDS_ORDER - 1;
            jArr5[i4] = jArr5[i4] & j;
            this.engine.fpx.inv_mod_orderA(jArr5, jArr6);
            this.engine.fpx.multiply(jArr4, jArr6, jArr7, this.engine.params.NWORDS_ORDER);
            int i5 = this.engine.params.NWORDS_ORDER - 1;
            jArr7[i5] = jArr7[i5] & j;
            Ladder3pt_dual(pointProjArr, jArr7, this.engine.params.ALICE, pointProj, jArr2);
        } else {
            this.engine.fpx.multiply(jArr8, jArr10, jArr4, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr4, jArr9, jArr4, this.engine.params.NWORDS_ORDER);
            int i6 = this.engine.params.NWORDS_ORDER - 1;
            jArr4[i6] = jArr4[i6] & j;
            this.engine.fpx.multiply(jArr8, jArr12, jArr5, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr5, jArr11, jArr5, this.engine.params.NWORDS_ORDER);
            int i7 = this.engine.params.NWORDS_ORDER - 1;
            jArr5[i7] = jArr5[i7] & j;
            this.engine.fpx.inv_mod_orderA(jArr5, jArr6);
            this.engine.fpx.multiply(jArr6, jArr4, jArr7, this.engine.params.NWORDS_ORDER);
            int i8 = this.engine.params.NWORDS_ORDER - 1;
            jArr7[i8] = jArr7[i8] & j;
            this.engine.isogeny.swap_points(pointProjArr[0], pointProjArr[1], -1L);
            Ladder3pt_dual(pointProjArr, jArr7, this.engine.params.ALICE, pointProj, jArr2);
        }
        this.engine.fpx.fp2div2(jArr, jArr3);
        this.engine.isogeny.xTPLe_fast(pointProj, pointProj, jArr3, this.engine.params.OBOB_EXPON);
        this.engine.fpx.fp2_encode(pointProj.X, bArr3, i2);
        this.engine.fpx.fp2_encode(pointProj.Z, bArr3, this.engine.params.FP2_ENCODED_BYTES + i2);
        this.engine.fpx.encode_to_bytes(jArr6, bArr3, (this.engine.params.FP2_ENCODED_BYTES * 2) + i2, this.engine.params.ORDER_A_ENCODED_BYTES);
    }

    protected void RecoverY(long[][] jArr, PointProj[] pointProjArr, PointProjFull[] pointProjFullArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2mul_mont(pointProjArr[2].X, pointProjArr[1].Z, jArr2);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].X, pointProjArr[2].Z, jArr3);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].X, pointProjArr[2].X, jArr4);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].Z, pointProjArr[2].Z, jArr5);
        this.engine.fpx.fp2sqr_mont(pointProjArr[1].X, jArr6);
        this.engine.fpx.fp2sqr_mont(pointProjArr[1].Z, pointProjFullArr[1].X);
        this.engine.fpx.fp2sub(jArr4, jArr5, pointProjFullArr[1].Y);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].X, pointProjFullArr[1].Y, pointProjFullArr[1].Y);
        this.engine.fpx.fp2add(jArr6, pointProjFullArr[1].X, jArr6);
        this.engine.fpx.fp2mul_mont(pointProjArr[2].Z, jArr6, jArr6);
        this.engine.fpx.fp2mul_mont(jArr, jArr3, pointProjFullArr[1].X);
        this.engine.fpx.fp2sub(jArr2, jArr3, pointProjFullArr[1].Z);
        this.engine.fpx.fp2mul_mont(pointProjFullArr[0].X, pointProjFullArr[1].Z, jArr2);
        this.engine.fpx.fp2add(jArr4, pointProjFullArr[1].X, jArr3);
        this.engine.fpx.fp2add(jArr3, jArr3, jArr3);
        this.engine.fpx.fp2sub(jArr2, jArr3, jArr2);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].Z, jArr2, jArr2);
        this.engine.fpx.fp2sub(jArr2, jArr6, jArr2);
        this.engine.fpx.fp2mul_mont(pointProjFullArr[0].X, jArr2, jArr2);
        this.engine.fpx.fp2add(jArr2, pointProjFullArr[1].Y, pointProjFullArr[1].Y);
        this.engine.fpx.fp2mul_mont(pointProjFullArr[0].Y, jArr5, jArr2);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].X, jArr2, pointProjFullArr[1].X);
        this.engine.fpx.fp2add(pointProjFullArr[1].X, pointProjFullArr[1].X, pointProjFullArr[1].X);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].Z, jArr2, pointProjFullArr[1].Z);
        this.engine.fpx.fp2add(pointProjFullArr[1].Z, pointProjFullArr[1].Z, pointProjFullArr[1].Z);
        this.engine.fpx.fp2inv_mont_bingcd(pointProjFullArr[1].Z);
        this.engine.fpx.fp2mul_mont(pointProjFullArr[1].X, pointProjFullArr[1].Z, pointProjFullArr[1].X);
        this.engine.fpx.fp2mul_mont(pointProjFullArr[1].Y, pointProjFullArr[1].Z, pointProjFullArr[1].Y);
    }

    protected void SecondPoint3n(long[][] jArr, long[][][][] jArr2, long[][] jArr3, PointProjFull pointProjFull, int[] iArr, byte[] bArr, byte[] bArr2) {
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        boolean z = false;
        while (!z) {
            bArr2[0] = 0;
            Elligator2(jArr, iArr, 1, jArr3, bArr2, 0, 0);
            this.engine.fpx.fp2copy(jArr3, pointProj.X);
            this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj.Z[0]);
            this.engine.fpx.fpcopy(jArr4, 0, pointProj.Z[1]);
            eval_full_dual_4_isog(jArr2, pointProj);
            boolean SecondPoint_dual = SecondPoint_dual(pointProj, pointProjFull, bArr);
            iArr[1] = iArr[1] + 1;
            z = SecondPoint_dual;
        }
    }

    protected void Tate3_proj(PointProjFull pointProjFull, PointProjFull pointProjFull2, long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        TripleAndParabola_proj(pointProjFull, jArr4, jArr2);
        this.engine.fpx.fp2sub(pointProjFull2.X, pointProjFull.X, jArr);
        this.engine.fpx.fp2mul_mont(jArr4, jArr, jArr);
        this.engine.fpx.fp2sub(pointProjFull.Y, pointProjFull2.Y, jArr3);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, jArr3);
        this.engine.fpx.fp2add(jArr, jArr3, jArr);
    }

    void Traverse_w_div_e_fullsigned(long[][] jArr, int i, int i2, int i3, int[] iArr, long[] jArr2, int[] iArr2, int i4, int i5, int i6) {
        int i7;
        long[][] jArr3;
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        if (i3 > 1) {
            int i8 = iArr[i3];
            this.engine.fpx.fp2copy(jArr, jArr4);
            int i9 = 0;
            while (true) {
                i7 = i3 - i8;
                if (i9 >= i7) {
                    break;
                }
                if ((i5 & 1) == 0) {
                    for (int i10 = 0; i10 < i6; i10++) {
                        this.engine.fpx.sqr_Fp2_cycl(jArr4, this.engine.params.Montgomery_one);
                    }
                } else {
                    for (int i11 = 0; i11 < i6; i11++) {
                        this.engine.fpx.cube_Fp2_cycl(jArr4, this.engine.params.Montgomery_one);
                    }
                }
                i9++;
            }
            long[][] jArr6 = jArr5;
            Traverse_w_div_e_fullsigned(jArr4, i + i7, i2, i8, iArr, jArr2, iArr2, i4, i5, i6);
            this.engine.fpx.fp2copy(jArr, jArr4);
            int i12 = i2;
            while (true) {
                int i13 = i2 + i8;
                if (i12 >= i13) {
                    Traverse_w_div_e_fullsigned(jArr4, i, i13, i7, iArr, jArr2, iArr2, i4, i5, i6);
                    return;
                }
                int i14 = iArr2[i12];
                if (i14 == 0) {
                    jArr3 = jArr6;
                } else if (i14 < 0) {
                    jArr3 = jArr6;
                    this.engine.fpx.fp2copy(jArr2, this.engine.params.NWORDS_FIELD * (((i + i12) * (i5 / 2)) + ((-iArr2[i12]) - 1)) * 2, jArr3);
                    this.engine.fpx.fpnegPRIME(jArr3[1]);
                    this.engine.fpx.fp2mul_mont(jArr4, jArr3, jArr4);
                } else {
                    jArr3 = jArr6;
                    this.engine.fpx.fp2mul_mont(jArr4, jArr2, this.engine.params.NWORDS_FIELD * (((i + i12) * (i5 / 2)) + (iArr2[i12] - 1)) * 2, jArr4);
                }
                i12++;
                jArr6 = jArr3;
            }
        } else {
            this.engine.fpx.fp2copy(jArr, jArr4);
            this.engine.fpx.fp2correction(jArr4);
            if (this.engine.fpx.is_felm_zero(jArr4[1]) && Fpx.subarrayEquals(jArr4[0], this.engine.params.Montgomery_one, this.engine.params.NWORDS_FIELD)) {
                iArr2[i2] = 0;
                return;
            }
            int i15 = 1;
            int i16 = 2;
            while (true) {
                int i17 = i5 / 2;
                if (i15 > i17) {
                    return;
                }
                int i18 = (((i4 - 1) * i17) + (i15 - 1)) * i16;
                if (Fpx.subarrayEquals(jArr4, jArr2, this.engine.params.NWORDS_FIELD * i18, this.engine.params.NWORDS_FIELD * i16)) {
                    iArr2[i2] = -i15;
                    return;
                }
                this.engine.fpx.fp2copy(jArr2, this.engine.params.NWORDS_FIELD * i18, jArr5);
                this.engine.fpx.fpnegPRIME(jArr5[1]);
                this.engine.fpx.fpcorrectionPRIME(jArr5[1]);
                if (Fpx.subarrayEquals(jArr4, jArr5, this.engine.params.NWORDS_FIELD * 2)) {
                    iArr2[i2] = i15;
                    return;
                } else {
                    i15++;
                    i16 = 2;
                }
            }
        }
    }

    void Traverse_w_notdiv_e_fullsigned(long[][] jArr, int i, int i2, int i3, int[] iArr, long[] jArr2, long[] jArr3, int[] iArr2, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10;
        long[][] jArr4;
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        if (i3 > 1) {
            int i11 = iArr[i3];
            this.engine.fpx.fp2copy(jArr, jArr5);
            int i12 = i > 0 ? (i3 - i11) * i8 : (i9 % i8) + (((i3 - i11) - 1) * i8);
            for (int i13 = 0; i13 < i12; i13++) {
                if ((i5 & 1) == 0) {
                    this.engine.fpx.sqr_Fp2_cycl(jArr5, this.engine.params.Montgomery_one);
                } else {
                    this.engine.fpx.cube_Fp2_cycl(jArr5, this.engine.params.Montgomery_one);
                }
            }
            int i14 = i3 - i11;
            long[][] jArr7 = jArr6;
            Traverse_w_notdiv_e_fullsigned(jArr5, i + i14, i2, i11, iArr, jArr2, jArr3, iArr2, i4, i5, i6, i7, i8, i9);
            this.engine.fpx.fp2copy(jArr, jArr5);
            int i15 = i2;
            while (true) {
                int i16 = i2 + i11;
                if (i15 >= i16) {
                    Traverse_w_notdiv_e_fullsigned(jArr5, i, i16, i14, iArr, jArr2, jArr3, iArr2, i4, i5, i6, i7, i8, i9);
                    return;
                }
                int i17 = iArr2[i15];
                if (i17 == 0) {
                    jArr4 = jArr7;
                } else if (i <= 0) {
                    jArr4 = jArr7;
                    if (i17 < 0) {
                        this.engine.fpx.fp2copy(jArr2, this.engine.params.NWORDS_FIELD * (((i + i15) * (i6 / 2)) + ((-iArr2[i15]) - 1)) * 2, jArr4);
                        this.engine.fpx.fpnegPRIME(jArr4[1]);
                        this.engine.fpx.fp2mul_mont(jArr5, jArr4, jArr5);
                    } else {
                        this.engine.fpx.fp2mul_mont(jArr5, jArr2, this.engine.params.NWORDS_FIELD * (((i + i15) * (i6 / 2)) + (iArr2[i15] - 1)) * 2, jArr5);
                    }
                } else if (i17 < 0) {
                    jArr4 = jArr7;
                    this.engine.fpx.fp2copy(jArr3, this.engine.params.NWORDS_FIELD * (((i + i15) * 2 * (i6 / 2)) + (((-iArr2[i15]) - 1) * 2)), jArr4);
                    this.engine.fpx.fpnegPRIME(jArr4[1]);
                    this.engine.fpx.fp2mul_mont(jArr5, jArr4, jArr5);
                } else {
                    jArr4 = jArr7;
                    this.engine.fpx.fp2mul_mont(jArr5, jArr3, this.engine.params.NWORDS_FIELD * (((i + i15) * (i6 / 2)) + (iArr2[i15] - 1)) * 2, jArr5);
                }
                i15++;
                jArr7 = jArr4;
            }
        } else {
            this.engine.fpx.fp2copy(jArr, jArr5);
            this.engine.fpx.fp2correction(jArr5);
            if (this.engine.fpx.is_felm_zero(jArr5[1]) && Fpx.subarrayEquals(jArr5[0], this.engine.params.Montgomery_one, this.engine.params.NWORDS_FIELD)) {
                iArr2[i2] = 0;
            } else if (i == 0 && i2 == i4 - 1) {
                for (int i18 = 1; i18 <= i7 / 2; i18++) {
                    int i19 = i6 / 2;
                    int i20 = i18 - 1;
                    if (Fpx.subarrayEquals(jArr5, jArr2, this.engine.params.NWORDS_FIELD * ((i19 * 2 * i10) + (i20 * 2)), this.engine.params.NWORDS_FIELD * 2)) {
                        iArr2[i2] = -i18;
                        return;
                    }
                    this.engine.fpx.fp2copy(jArr2, this.engine.params.NWORDS_FIELD * ((i19 * i10) + i20) * 2, jArr6);
                    this.engine.fpx.fpnegPRIME(jArr6[1]);
                    this.engine.fpx.fpcorrectionPRIME(jArr6[1]);
                    if (Fpx.subarrayEquals(jArr5, jArr6, this.engine.params.NWORDS_FIELD * 2)) {
                        iArr2[i2] = i18;
                        return;
                    }
                }
            } else {
                int i21 = 1;
                while (true) {
                    int i22 = i6 / 2;
                    if (i21 > i22) {
                        return;
                    }
                    int i23 = i4 - 1;
                    int i24 = i21 - 1;
                    if (Fpx.subarrayEquals(jArr5, jArr3, this.engine.params.NWORDS_FIELD * ((i22 * 2 * i23) + (i24 * 2)), this.engine.params.NWORDS_FIELD * 2)) {
                        iArr2[i2] = -i21;
                        return;
                    }
                    this.engine.fpx.fp2copy(jArr3, this.engine.params.NWORDS_FIELD * ((i22 * i23) + i24) * 2, jArr6);
                    this.engine.fpx.fpnegPRIME(jArr6[1]);
                    this.engine.fpx.fpcorrectionPRIME(jArr6[1]);
                    if (Fpx.subarrayEquals(jArr5, jArr6, this.engine.params.NWORDS_FIELD * 2)) {
                        iArr2[i2] = i21;
                        return;
                    }
                    i21++;
                }
            }
        }
    }

    protected void TripleAndParabola_proj(PointProjFull pointProjFull, long[][] jArr, long[][] jArr2) {
        this.engine.fpx.fp2sqr_mont(pointProjFull.X, jArr2);
        this.engine.fpx.fp2add(jArr2, jArr2, jArr);
        this.engine.fpx.fp2add(jArr, jArr2, jArr);
        this.engine.fpx.fpaddPRIME(jArr[0], this.engine.params.Montgomery_one, jArr[0]);
        this.engine.fpx.fp2add(pointProjFull.Y, pointProjFull.Y, jArr2);
    }

    protected void eval_dual_2_isog(long[][] jArr, long[][] jArr2, PointProj pointProj) {
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2add(pointProj.X, pointProj.Z, jArr3);
        this.engine.fpx.fp2sub(pointProj.X, pointProj.Z, pointProj.Z);
        this.engine.fpx.fp2sqr_mont(jArr3, jArr3);
        this.engine.fpx.fp2sqr_mont(pointProj.Z, pointProj.Z);
        this.engine.fpx.fp2sub(jArr3, pointProj.Z, pointProj.Z);
        this.engine.fpx.fp2mul_mont(jArr, pointProj.Z, pointProj.Z);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, pointProj.X);
    }

    protected void eval_dual_4_isog(long[][] jArr, long[][] jArr2, long[][][] jArr3, int i, PointProj pointProj) {
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2add(pointProj.X, pointProj.Z, jArr4);
        this.engine.fpx.fp2sub(pointProj.X, pointProj.Z, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr5);
        this.engine.fpx.fp2sub(jArr4, jArr5, jArr6);
        this.engine.fpx.fp2sub(jArr2, jArr, jArr7);
        this.engine.fpx.fp2mul_mont(jArr6, jArr7, jArr7);
        this.engine.fpx.fp2mul_mont(jArr2, jArr4, jArr6);
        this.engine.fpx.fp2sub(jArr6, jArr7, jArr6);
        this.engine.fpx.fp2mul_mont(jArr6, jArr4, pointProj.X);
        this.engine.fpx.fp2mul_mont(jArr7, jArr5, pointProj.Z);
        this.engine.fpx.fp2mul_mont(jArr3[i + 0], pointProj.X, pointProj.X);
        this.engine.fpx.fp2mul_mont(jArr3[i + 1], pointProj.Z, jArr4);
        this.engine.fpx.fp2add(pointProj.X, jArr4, pointProj.X);
        this.engine.fpx.fp2mul_mont(jArr3[i + 2], pointProj.Z, pointProj.Z);
    }

    protected void eval_dual_4_isog_shared(long[][] jArr, long[][] jArr2, long[][] jArr3, long[][][] jArr4, int i) {
        this.engine.fpx.fp2sub(jArr2, jArr3, jArr4[i + 0]);
        int i2 = i + 1;
        this.engine.fpx.fp2add(jArr2, jArr3, jArr4[i2]);
        int i3 = i + 2;
        this.engine.fpx.fp2sqr_mont(jArr, jArr4[i3]);
        Fpx fpx = this.engine.fpx;
        long[][] jArr5 = jArr4[i3];
        fpx.fp2sub(jArr5, jArr4[i2], jArr5);
    }

    protected void eval_final_dual_2_isog(PointProj pointProj) {
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        this.engine.fpx.fp2add(pointProj.X, pointProj.Z, jArr);
        this.engine.fpx.fp2mul_mont(pointProj.X, pointProj.Z, jArr2);
        this.engine.fpx.fp2sqr_mont(jArr, pointProj.X);
        this.engine.fpx.fpcopy(pointProj.X[0], 0, jArr3);
        this.engine.fpx.fpcopy(pointProj.X[1], 0, pointProj.X[0]);
        this.engine.fpx.fpcopy(jArr3, 0, pointProj.X[1]);
        this.engine.fpx.fpnegPRIME(pointProj.X[1]);
        this.engine.fpx.fp2add(jArr2, jArr2, pointProj.Z);
        this.engine.fpx.fp2add(pointProj.Z, pointProj.Z, pointProj.Z);
    }

    protected void eval_full_dual_4_isog(long[][][][] jArr, PointProj pointProj) {
        for (int i = 0; i < this.engine.params.MAX_Alice; i++) {
            eval_dual_4_isog(jArr[this.engine.params.MAX_Alice - i][0], jArr[this.engine.params.MAX_Alice - i][1], jArr[(this.engine.params.MAX_Alice - i) - 1], 2, pointProj);
        }
        if (this.engine.params.OALICE_BITS % 2 == 1) {
            eval_dual_2_isog(jArr[this.engine.params.MAX_Alice][2], jArr[this.engine.params.MAX_Alice][3], pointProj);
        }
        eval_final_dual_2_isog(pointProj);
    }

    protected void get_4_isog_dual(PointProj pointProj, long[][] jArr, long[][] jArr2, long[][][] jArr3) {
        this.engine.fpx.fp2sub(pointProj.X, pointProj.Z, jArr3[1]);
        this.engine.fpx.fp2add(pointProj.X, pointProj.Z, jArr3[2]);
        this.engine.fpx.fp2sqr_mont(pointProj.Z, jArr3[4]);
        Fpx fpx = this.engine.fpx;
        long[][] jArr4 = jArr3[4];
        fpx.fp2add(jArr4, jArr4, jArr3[0]);
        this.engine.fpx.fp2sqr_mont(jArr3[0], jArr2);
        Fpx fpx2 = this.engine.fpx;
        long[][] jArr5 = jArr3[0];
        fpx2.fp2add(jArr5, jArr5, jArr5);
        this.engine.fpx.fp2sqr_mont(pointProj.X, jArr3[3]);
        Fpx fpx3 = this.engine.fpx;
        long[][] jArr6 = jArr3[3];
        fpx3.fp2add(jArr6, jArr6, jArr);
        this.engine.fpx.fp2sqr_mont(jArr, jArr);
    }

    protected void init_basis(long[] jArr, long[][] jArr2, long[][] jArr3, long[][] jArr4) {
        this.engine.fpx.fpcopy(jArr, 0, jArr2[0]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD, jArr2[1]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 2, jArr3[0]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 3, jArr3[1]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 4, jArr4[0]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 5, jArr4[1]);
    }

    protected void makeDiff(PointProjFull pointProjFull, PointProjFull pointProjFull2, PointProj pointProj) {
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        int i = this.engine.params.NWORDS_FIELD;
        this.engine.fpx.fp2sub(pointProjFull.X, pointProjFull2.X, jArr);
        this.engine.fpx.fp2sub(pointProjFull.Y, pointProjFull2.Y, jArr2);
        this.engine.fpx.fp2sqr_mont(jArr, jArr);
        this.engine.fpx.fp2sqr_mont(jArr2, jArr2);
        this.engine.fpx.fp2add(pointProjFull.X, pointProjFull2.X, jArr3);
        this.engine.fpx.fp2mul_mont(jArr, jArr3, jArr3);
        this.engine.fpx.fp2sub(jArr2, jArr3, jArr2);
        this.engine.fpx.fp2mul_mont(pointProj.Z, jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(pointProj.X, jArr, jArr);
        this.engine.fpx.fp2correction(jArr);
        this.engine.fpx.fp2correction(jArr2);
        if (Fpx.subarrayEquals(jArr[0], jArr2[0], i) && Fpx.subarrayEquals(jArr[1], jArr2[1], i)) {
            this.engine.fpx.fp2neg(pointProjFull2.Y);
        }
    }

    protected void make_positive(long[][] jArr) {
        int i = this.engine.params.NWORDS_FIELD;
        this.engine.fpx.from_fp2mont(jArr, jArr);
        if (Fpx.subarrayEquals(jArr[0], new long[this.engine.params.NWORDS_FIELD], i) ? (jArr[1][0] & 1) == 1 : (jArr[0][0] & 1) == 1) {
            this.engine.fpx.fp2neg(jArr);
        }
        this.engine.fpx.to_fp2mont(jArr, jArr);
    }

    protected void random_mod_order_A(byte[] bArr, SecureRandom secureRandom) {
        byte[] bArr2 = new byte[this.engine.params.SECRETKEY_A_BYTES];
        secureRandom.nextBytes(bArr2);
        System.arraycopy(bArr2, 0, bArr, 0, this.engine.params.SECRETKEY_A_BYTES);
        bArr[0] = (byte) (bArr[0] & 254);
        int i = this.engine.params.SECRETKEY_A_BYTES - 1;
        bArr[i] = (byte) (bArr[i] & this.engine.params.MASK_ALICE);
    }

    protected void random_mod_order_B(byte[] bArr, SecureRandom secureRandom) {
        byte[] bArr2 = new byte[this.engine.params.SECRETKEY_B_BYTES];
        secureRandom.nextBytes(bArr2);
        System.arraycopy(bArr2, 0, bArr, 0, this.engine.params.SECRETKEY_A_BYTES);
        FormatPrivKey_B(bArr);
    }

    void solve_dlog(long[][] jArr, int[] iArr, long[] jArr2, int i) {
        if (i == 2) {
            if (this.engine.params.OALICE_BITS % this.engine.params.W_2 == 0) {
                Traverse_w_div_e_fullsigned(jArr, 0, 0, this.engine.params.PLEN_2 - 1, this.engine.params.ph2_path, this.engine.params.ph2_T, iArr, this.engine.params.DLEN_2, this.engine.params.ELL2_W, this.engine.params.W_2);
            } else {
                Traverse_w_notdiv_e_fullsigned(jArr, 0, 0, this.engine.params.PLEN_2 - 1, this.engine.params.ph2_path, this.engine.params.ph2_T1, this.engine.params.ph2_T2, iArr, this.engine.params.DLEN_2, i, this.engine.params.ELL2_W, this.engine.params.ELL2_EMODW, this.engine.params.W_2, this.engine.params.OALICE_BITS);
            }
            from_base(iArr, jArr2, this.engine.params.DLEN_2, this.engine.params.ELL2_W);
        } else if (i == 3) {
            if (this.engine.params.OBOB_EXPON % this.engine.params.W_3 == 0) {
                Traverse_w_div_e_fullsigned(jArr, 0, 0, this.engine.params.PLEN_3 - 1, this.engine.params.ph3_path, this.engine.params.ph3_T, iArr, this.engine.params.DLEN_3, this.engine.params.ELL3_W, this.engine.params.W_3);
            } else {
                Traverse_w_notdiv_e_fullsigned(jArr, 0, 0, this.engine.params.PLEN_3 - 1, this.engine.params.ph3_path, this.engine.params.ph3_T1, this.engine.params.ph3_T2, iArr, this.engine.params.DLEN_3, i, this.engine.params.ELL3_W, this.engine.params.ELL3_EMODW, this.engine.params.W_3, this.engine.params.OBOB_EXPON);
            }
            from_base(iArr, jArr2, this.engine.params.DLEN_3, this.engine.params.ELL3_W);
        }
    }

    public byte validate_ciphertext(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2) {
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_BOB];
        PointProj[] pointProjArr2 = {new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD)};
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        long[][] jArr = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(Long.TYPE, 2, this.engine.params.NWORDS_FIELD);
        int[] iArr = {2, this.engine.params.NWORDS_FIELD};
        int[] iArr2 = {3, 2, this.engine.params.NWORDS_FIELD};
        int[] iArr3 = new int[this.engine.params.MAX_INT_POINTS_BOB];
        long[] jArr9 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr10 = new long[this.engine.params.NWORDS_ORDER];
        long[][][] jArr11 = (long[][][]) Array.newInstance(Long.TYPE, iArr2);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, ((long[][]) Array.newInstance(Long.TYPE, iArr))[0]);
        init_basis(this.engine.params.B_gen, jArr, jArr2, jArr3);
        this.engine.fpx.fp2_decode(bArr3, pointProjArr2[0].X, i);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr2[0].Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4[0]);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr5);
        this.engine.fpx.fp2add(jArr4, jArr5, jArr6);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr4);
        this.engine.fpx.decode_to_digits(bArr, 0, jArr10, this.engine.params.SECRETKEY_B_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.LADDER3PT(jArr, jArr2, jArr3, jArr10, this.engine.params.BOB, pointProj, jArr6);
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 < this.engine.params.MAX_Bob) {
            int i7 = i4;
            while (i7 < this.engine.params.MAX_Bob - i3) {
                pointProjArr[i5] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.X, pointProjArr[i5].X);
                this.engine.fpx.fp2copy(pointProj.Z, pointProjArr[i5].Z);
                iArr3[i5] = i7;
                int i8 = this.engine.params.strat_Bob[i6];
                this.engine.isogeny.xTPLe(pointProj, pointProj, jArr5, jArr4, i8);
                i7 += i8;
                i5++;
                i6++;
            }
            long[][][] jArr12 = jArr11;
            this.engine.isogeny.get_3_isog(pointProj, jArr5, jArr4, jArr12);
            for (int i9 = 0; i9 < i5; i9++) {
                this.engine.isogeny.eval_3_isog(pointProjArr[i9], jArr12);
            }
            this.engine.isogeny.eval_3_isog(pointProjArr2[0], jArr12);
            int i10 = i5 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i10].X, pointProj.X);
            this.engine.fpx.fp2copy(pointProjArr[i10].Z, pointProj.Z);
            i4 = iArr3[i10];
            i5--;
            i3++;
            i6 = i6;
            jArr11 = jArr12;
        }
        long[][][] jArr13 = jArr11;
        this.engine.isogeny.get_3_isog(pointProj, jArr5, jArr4, jArr13);
        this.engine.isogeny.eval_3_isog(pointProjArr2[0], jArr13);
        this.engine.fpx.fp2_decode(bArr2, jArr6, this.engine.params.ORDER_A_ENCODED_BYTES * 4);
        this.engine.fpx.fp2_decode(bArr4, pointProj2.X, i2);
        this.engine.fpx.fp2_decode(bArr4, pointProj2.Z, this.engine.params.FP2_ENCODED_BYTES + i2);
        this.engine.fpx.decode_to_digits(bArr4, i2 + (this.engine.params.FP2_ENCODED_BYTES * 2), jArr9, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.Ladder(pointProjArr2[0], jArr9, jArr6, this.engine.params.OALICE_BITS, pointProj);
        this.engine.fpx.fp2mul_mont(pointProj.X, pointProj2.Z, jArr7);
        this.engine.fpx.fp2mul_mont(pointProj.Z, pointProj2.X, jArr8);
        return this.engine.fpx.cmp_f2elm(jArr7, jArr8);
    }
}
