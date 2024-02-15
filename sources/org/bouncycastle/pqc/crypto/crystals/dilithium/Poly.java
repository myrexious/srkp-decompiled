package org.bouncycastle.pqc.crypto.crystals.dilithium;

import kotlin.UByte;
import org.bouncycastle.crypto.digests.SHAKEDigest;

/* loaded from: classes2.dex */
public class Poly {
    private final DilithiumEngine engine;
    private final int polyUniformNBlocks;
    private final Symmetric symmetric;
    private final int dilithiumN = 256;
    private int[] coeffs = new int[256];

    public Poly(DilithiumEngine dilithiumEngine) {
        this.engine = dilithiumEngine;
        Symmetric GetSymmetric = dilithiumEngine.GetSymmetric();
        this.symmetric = GetSymmetric;
        this.polyUniformNBlocks = ((GetSymmetric.stream128BlockBytes + 768) - 1) / GetSymmetric.stream128BlockBytes;
    }

    private static int rejectEta(Poly poly, int i, int i2, byte[] bArr, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        while (i5 < i2 && i6 < i3) {
            byte b = bArr[i6];
            int i7 = b & UByte.MAX_VALUE & 15;
            i6++;
            int i8 = (b & UByte.MAX_VALUE) >> 4;
            if (i4 == 2) {
                if (i7 < 15) {
                    poly.setCoeffIndex(i + i5, 2 - (i7 - (((i7 * 205) >> 10) * 5)));
                    i5++;
                }
                if (i8 < 15 && i5 < i2) {
                    poly.setCoeffIndex(i + i5, 2 - (i8 - (((i8 * 205) >> 10) * 5)));
                    i5++;
                }
            } else if (i4 == 4) {
                if (i7 < 9) {
                    poly.setCoeffIndex(i + i5, 4 - i7);
                    i5++;
                }
                if (i8 < 9 && i5 < i2) {
                    poly.setCoeffIndex(i + i5, 4 - i8);
                    i5++;
                }
            }
        }
        return i5;
    }

    private static int rejectUniform(Poly poly, int i, int i2, byte[] bArr, int i3) {
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2 && i5 + 3 <= i3) {
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int i8 = (bArr[i5] & UByte.MAX_VALUE) | ((bArr[i6] & UByte.MAX_VALUE) << 8);
            int i9 = i7 + 1;
            int i10 = (i8 | ((bArr[i7] & UByte.MAX_VALUE) << 16)) & 8388607;
            if (i10 < 8380417) {
                poly.setCoeffIndex(i + i4, i10);
                i4++;
            }
            i5 = i9;
        }
        return i4;
    }

    private void unpackZ(byte[] bArr) {
        int i = 0;
        if (this.engine.getDilithiumGamma1() != 131072) {
            if (this.engine.getDilithiumGamma1() != 524288) {
                throw new RuntimeException("Wrong Dilithiumn Gamma1!");
            }
            while (i < this.dilithiumN / 2) {
                int i2 = i * 2;
                int i3 = i2 + 0;
                int i4 = i * 5;
                int i5 = i4 + 2;
                setCoeffIndex(i3, ((bArr[i4 + 0] & UByte.MAX_VALUE) | ((bArr[i4 + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i5] & UByte.MAX_VALUE) << 16)) & 1048575);
                int i6 = i2 + 1;
                setCoeffIndex(i6, (((bArr[i4 + 4] & UByte.MAX_VALUE) << 12) | ((bArr[i5] & UByte.MAX_VALUE) >> 4) | ((bArr[i4 + 3] & UByte.MAX_VALUE) << 4)) & 1048575);
                setCoeffIndex(i3, this.engine.getDilithiumGamma1() - getCoeffIndex(i3));
                setCoeffIndex(i6, this.engine.getDilithiumGamma1() - getCoeffIndex(i6));
                i++;
            }
            return;
        }
        while (i < this.dilithiumN / 4) {
            int i7 = i * 4;
            int i8 = i7 + 0;
            int i9 = i * 9;
            int i10 = i9 + 2;
            setCoeffIndex(i8, ((bArr[i9 + 0] & UByte.MAX_VALUE) | ((bArr[i9 + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i10] & UByte.MAX_VALUE) << 16)) & 262143);
            int i11 = i7 + 1;
            int i12 = i9 + 4;
            setCoeffIndex(i11, (((bArr[i10] & UByte.MAX_VALUE) >> 2) | ((bArr[i9 + 3] & UByte.MAX_VALUE) << 6) | ((bArr[i12] & UByte.MAX_VALUE) << 14)) & 262143);
            int i13 = i7 + 2;
            int i14 = i9 + 6;
            setCoeffIndex(i13, (((bArr[i12] & UByte.MAX_VALUE) >> 4) | ((bArr[i9 + 5] & UByte.MAX_VALUE) << 4) | ((bArr[i14] & UByte.MAX_VALUE) << 12)) & 262143);
            int i15 = i7 + 3;
            setCoeffIndex(i15, (((bArr[i9 + 8] & UByte.MAX_VALUE) << 10) | ((bArr[i14] & UByte.MAX_VALUE) >> 6) | ((bArr[i9 + 7] & UByte.MAX_VALUE) << 2)) & 262143);
            setCoeffIndex(i8, this.engine.getDilithiumGamma1() - getCoeffIndex(i8));
            setCoeffIndex(i11, this.engine.getDilithiumGamma1() - getCoeffIndex(i11));
            setCoeffIndex(i13, this.engine.getDilithiumGamma1() - getCoeffIndex(i13));
            setCoeffIndex(i15, this.engine.getDilithiumGamma1() - getCoeffIndex(i15));
            i++;
        }
    }

    public void addPoly(Poly poly) {
        for (int i = 0; i < this.dilithiumN; i++) {
            setCoeffIndex(i, getCoeffIndex(i) + poly.getCoeffIndex(i));
        }
    }

    public void challenge(byte[] bArr) {
        int i;
        int i2;
        int i3;
        int i4;
        byte[] bArr2 = new byte[this.symmetric.stream256BlockBytes];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr, 0, 32);
        sHAKEDigest.doOutput(bArr2, 0, this.symmetric.stream256BlockBytes);
        long j = 0;
        int i5 = 0;
        while (true) {
            i = 8;
            if (i5 >= 8) {
                break;
            }
            j |= (bArr2[i5] & UByte.MAX_VALUE) << (i5 * 8);
            i5++;
        }
        int i6 = 0;
        while (true) {
            i2 = this.dilithiumN;
            if (i6 >= i2) {
                break;
            }
            setCoeffIndex(i6, 0);
            i6++;
        }
        int dilithiumTau = i2 - this.engine.getDilithiumTau();
        while (dilithiumTau < this.dilithiumN) {
            while (true) {
                if (i >= this.symmetric.stream256BlockBytes) {
                    sHAKEDigest.doOutput(bArr2, 0, this.symmetric.stream256BlockBytes);
                    i = 0;
                }
                i3 = i + 1;
                i4 = bArr2[i] & UByte.MAX_VALUE;
                if (i4 <= dilithiumTau) {
                    break;
                }
                i = i3;
            }
            setCoeffIndex(dilithiumTau, getCoeffIndex(i4));
            setCoeffIndex(i4, (int) (1 - ((j & 1) * 2)));
            j >>= 1;
            dilithiumTau++;
            i = i3;
        }
    }

    public boolean checkNorm(int i) {
        if (i > 1047552) {
            return true;
        }
        for (int i2 = 0; i2 < this.dilithiumN; i2++) {
            if (getCoeffIndex(i2) - ((getCoeffIndex(i2) >> 31) & (getCoeffIndex(i2) * 2)) >= i) {
                return true;
            }
        }
        return false;
    }

    public void conditionalAddQ() {
        for (int i = 0; i < this.dilithiumN; i++) {
            setCoeffIndex(i, Reduce.conditionalAddQ(getCoeffIndex(i)));
        }
    }

    public void decompose(Poly poly) {
        for (int i = 0; i < this.dilithiumN; i++) {
            int[] decompose = Rounding.decompose(getCoeffIndex(i), this.engine.getDilithiumGamma2());
            setCoeffIndex(i, decompose[1]);
            poly.setCoeffIndex(i, decompose[0]);
        }
    }

    public int getCoeffIndex(int i) {
        return this.coeffs[i];
    }

    public int[] getCoeffs() {
        return this.coeffs;
    }

    public void invNttToMont() {
        setCoeffs(Ntt.invNttToMont(getCoeffs()));
    }

    public void pointwiseAccountMontgomery(PolyVecL polyVecL, PolyVecL polyVecL2) {
        Poly poly = new Poly(this.engine);
        pointwiseMontgomery(polyVecL.getVectorIndex(0), polyVecL2.getVectorIndex(0));
        for (int i = 1; i < this.engine.getDilithiumL(); i++) {
            poly.pointwiseMontgomery(polyVecL.getVectorIndex(i), polyVecL2.getVectorIndex(i));
            addPoly(poly);
        }
    }

    public void pointwiseMontgomery(Poly poly, Poly poly2) {
        for (int i = 0; i < this.dilithiumN; i++) {
            setCoeffIndex(i, Reduce.montgomeryReduce(poly.getCoeffIndex(i) * poly2.getCoeffIndex(i)));
        }
    }

    public byte[] polyEtaPack(byte[] bArr, int i) {
        byte[] bArr2 = new byte[8];
        if (this.engine.getDilithiumEta() == 2) {
            for (int i2 = 0; i2 < this.dilithiumN / 8; i2++) {
                int i3 = i2 * 8;
                bArr2[0] = (byte) (this.engine.getDilithiumEta() - getCoeffIndex(i3 + 0));
                bArr2[1] = (byte) (this.engine.getDilithiumEta() - getCoeffIndex(i3 + 1));
                bArr2[2] = (byte) (this.engine.getDilithiumEta() - getCoeffIndex(i3 + 2));
                bArr2[3] = (byte) (this.engine.getDilithiumEta() - getCoeffIndex(i3 + 3));
                bArr2[4] = (byte) (this.engine.getDilithiumEta() - getCoeffIndex(i3 + 4));
                bArr2[5] = (byte) (this.engine.getDilithiumEta() - getCoeffIndex(i3 + 5));
                bArr2[6] = (byte) (this.engine.getDilithiumEta() - getCoeffIndex(i3 + 6));
                bArr2[7] = (byte) (this.engine.getDilithiumEta() - getCoeffIndex(i3 + 7));
                int i4 = i + (i2 * 3);
                bArr[i4 + 0] = (byte) ((bArr2[0] >> 0) | (bArr2[1] << 3) | (bArr2[2] << 6));
                bArr[i4 + 1] = (byte) ((bArr2[3] << 1) | (bArr2[2] >> 2) | (bArr2[4] << 4) | (bArr2[5] << 7));
                bArr[i4 + 2] = (byte) ((bArr2[5] >> 1) | (bArr2[6] << 2) | (bArr2[7] << 5));
            }
        } else if (this.engine.getDilithiumEta() != 4) {
            throw new RuntimeException("Eta needs to be 2 or 4!");
        } else {
            for (int i5 = 0; i5 < this.dilithiumN / 2; i5++) {
                int i6 = i5 * 2;
                bArr2[0] = (byte) (this.engine.getDilithiumEta() - getCoeffIndex(i6 + 0));
                byte dilithiumEta = (byte) (this.engine.getDilithiumEta() - getCoeffIndex(i6 + 1));
                bArr2[1] = dilithiumEta;
                bArr[i + i5] = (byte) ((dilithiumEta << 4) | bArr2[0]);
            }
        }
        return bArr;
    }

    public void polyEtaUnpack(byte[] bArr, int i) {
        int dilithiumEta = this.engine.getDilithiumEta();
        if (this.engine.getDilithiumEta() != 2) {
            if (this.engine.getDilithiumEta() == 4) {
                for (int i2 = 0; i2 < this.dilithiumN / 2; i2++) {
                    int i3 = i2 * 2;
                    int i4 = i3 + 0;
                    int i5 = i + i2;
                    setCoeffIndex(i4, bArr[i5] & 15);
                    int i6 = i3 + 1;
                    setCoeffIndex(i6, (bArr[i5] & UByte.MAX_VALUE) >> 4);
                    setCoeffIndex(i4, dilithiumEta - getCoeffIndex(i4));
                    setCoeffIndex(i6, dilithiumEta - getCoeffIndex(i6));
                }
                return;
            }
            return;
        }
        for (int i7 = 0; i7 < this.dilithiumN / 8; i7++) {
            int i8 = i + (i7 * 3);
            int i9 = i7 * 8;
            int i10 = i9 + 0;
            int i11 = i8 + 0;
            setCoeffIndex(i10, ((bArr[i11] & UByte.MAX_VALUE) >> 0) & 7);
            int i12 = i9 + 1;
            setCoeffIndex(i12, ((bArr[i11] & UByte.MAX_VALUE) >> 3) & 7);
            int i13 = i9 + 2;
            int i14 = i8 + 1;
            setCoeffIndex(i13, ((bArr[i11] & UByte.MAX_VALUE) >> 6) | (((bArr[i14] & UByte.MAX_VALUE) << 2) & 7));
            int i15 = i9 + 3;
            setCoeffIndex(i15, ((bArr[i14] & UByte.MAX_VALUE) >> 1) & 7);
            int i16 = i9 + 4;
            setCoeffIndex(i16, ((bArr[i14] & UByte.MAX_VALUE) >> 4) & 7);
            int i17 = i9 + 5;
            int i18 = i8 + 2;
            setCoeffIndex(i17, ((bArr[i14] & UByte.MAX_VALUE) >> 7) | (((bArr[i18] & UByte.MAX_VALUE) << 1) & 7));
            int i19 = i9 + 6;
            setCoeffIndex(i19, ((bArr[i18] & UByte.MAX_VALUE) >> 2) & 7);
            int i20 = i9 + 7;
            setCoeffIndex(i20, ((bArr[i18] & UByte.MAX_VALUE) >> 5) & 7);
            setCoeffIndex(i10, dilithiumEta - getCoeffIndex(i10));
            setCoeffIndex(i12, dilithiumEta - getCoeffIndex(i12));
            setCoeffIndex(i13, dilithiumEta - getCoeffIndex(i13));
            setCoeffIndex(i15, dilithiumEta - getCoeffIndex(i15));
            setCoeffIndex(i16, dilithiumEta - getCoeffIndex(i16));
            setCoeffIndex(i17, dilithiumEta - getCoeffIndex(i17));
            setCoeffIndex(i19, dilithiumEta - getCoeffIndex(i19));
            setCoeffIndex(i20, dilithiumEta - getCoeffIndex(i20));
        }
    }

    public int polyMakeHint(Poly poly, Poly poly2) {
        int i = 0;
        for (int i2 = 0; i2 < this.dilithiumN; i2++) {
            setCoeffIndex(i2, Rounding.makeHint(poly.getCoeffIndex(i2), poly2.getCoeffIndex(i2), this.engine));
            i += getCoeffIndex(i2);
        }
        return i;
    }

    public void polyNtt() {
        setCoeffs(Ntt.ntt(this.coeffs));
    }

    public void polyUseHint(Poly poly, Poly poly2) {
        for (int i = 0; i < this.dilithiumN; i++) {
            setCoeffIndex(i, Rounding.useHint(poly.getCoeffIndex(i), poly2.getCoeffIndex(i), this.engine.getDilithiumGamma2()));
        }
    }

    public byte[] polyt0Pack(byte[] bArr, int i) {
        int[] iArr = new int[8];
        char c = 0;
        int i2 = 0;
        while (i2 < this.dilithiumN / 8) {
            int i3 = i2 * 8;
            iArr[c] = 4096 - getCoeffIndex(i3 + 0);
            iArr[1] = 4096 - getCoeffIndex(i3 + 1);
            iArr[2] = 4096 - getCoeffIndex(i3 + 2);
            iArr[3] = 4096 - getCoeffIndex(i3 + 3);
            iArr[4] = 4096 - getCoeffIndex(i3 + 4);
            iArr[5] = 4096 - getCoeffIndex(i3 + 5);
            iArr[6] = 4096 - getCoeffIndex(i3 + 6);
            int coeffIndex = 4096 - getCoeffIndex(i3 + 7);
            iArr[7] = coeffIndex;
            int i4 = i + (i2 * 13);
            int i5 = iArr[c];
            bArr[i4 + 0] = (byte) i5;
            int i6 = i4 + 1;
            byte b = (byte) (i5 >> 8);
            bArr[i6] = b;
            int i7 = iArr[1];
            bArr[i6] = (byte) (b | ((byte) (i7 << 5)));
            bArr[i4 + 2] = (byte) (i7 >> 3);
            int i8 = i4 + 3;
            byte b2 = (byte) (i7 >> 11);
            bArr[i8] = b2;
            int i9 = iArr[2];
            bArr[i8] = (byte) (b2 | ((byte) (i9 << 2)));
            int i10 = i4 + 4;
            byte b3 = (byte) (i9 >> 6);
            bArr[i10] = b3;
            int i11 = iArr[3];
            bArr[i10] = (byte) (b3 | ((byte) (i11 << 7)));
            bArr[i4 + 5] = (byte) (i11 >> 1);
            int i12 = i4 + 6;
            byte b4 = (byte) (i11 >> 9);
            bArr[i12] = b4;
            int i13 = iArr[4];
            bArr[i12] = (byte) (b4 | ((byte) (i13 << 4)));
            bArr[i4 + 7] = (byte) (i13 >> 4);
            int i14 = i4 + 8;
            byte b5 = (byte) (i13 >> 12);
            bArr[i14] = b5;
            int i15 = iArr[5];
            bArr[i14] = (byte) (b5 | ((byte) (i15 << 1)));
            int i16 = i4 + 9;
            byte b6 = (byte) (i15 >> 7);
            bArr[i16] = b6;
            int i17 = iArr[6];
            bArr[i16] = (byte) (b6 | ((byte) (i17 << 6)));
            bArr[i4 + 10] = (byte) (i17 >> 2);
            int i18 = i4 + 11;
            byte b7 = (byte) (i17 >> 10);
            bArr[i18] = b7;
            bArr[i18] = (byte) (b7 | ((byte) (coeffIndex << 3)));
            bArr[i4 + 12] = (byte) (coeffIndex >> 5);
            i2++;
            c = 0;
        }
        return bArr;
    }

    public void polyt0Unpack(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.dilithiumN / 8; i2++) {
            int i3 = (i2 * 13) + i;
            int i4 = i2 * 8;
            int i5 = i4 + 0;
            int i6 = i3 + 1;
            setCoeffIndex(i5, ((bArr[i3 + 0] & UByte.MAX_VALUE) | ((bArr[i6] & UByte.MAX_VALUE) << 8)) & 8191);
            int i7 = i4 + 1;
            int i8 = i3 + 3;
            setCoeffIndex(i7, (((bArr[i6] & UByte.MAX_VALUE) >> 5) | ((bArr[i3 + 2] & UByte.MAX_VALUE) << 3) | ((bArr[i8] & UByte.MAX_VALUE) << 11)) & 8191);
            int i9 = i4 + 2;
            int i10 = i3 + 4;
            setCoeffIndex(i9, (((bArr[i8] & UByte.MAX_VALUE) >> 2) | ((bArr[i10] & UByte.MAX_VALUE) << 6)) & 8191);
            int i11 = i4 + 3;
            int i12 = i3 + 6;
            setCoeffIndex(i11, (((bArr[i10] & UByte.MAX_VALUE) >> 7) | ((bArr[i3 + 5] & UByte.MAX_VALUE) << 1) | ((bArr[i12] & UByte.MAX_VALUE) << 9)) & 8191);
            int i13 = i4 + 4;
            int i14 = i3 + 8;
            setCoeffIndex(i13, (((bArr[i12] & UByte.MAX_VALUE) >> 4) | ((bArr[i3 + 7] & UByte.MAX_VALUE) << 4) | ((bArr[i14] & UByte.MAX_VALUE) << 12)) & 8191);
            int i15 = i4 + 5;
            int i16 = i3 + 9;
            setCoeffIndex(i15, (((bArr[i14] & UByte.MAX_VALUE) >> 1) | ((bArr[i16] & UByte.MAX_VALUE) << 7)) & 8191);
            int i17 = i4 + 6;
            int i18 = i3 + 11;
            setCoeffIndex(i17, (((bArr[i16] & UByte.MAX_VALUE) >> 6) | ((bArr[i3 + 10] & UByte.MAX_VALUE) << 2) | ((bArr[i18] & UByte.MAX_VALUE) << 10)) & 8191);
            int i19 = i4 + 7;
            setCoeffIndex(i19, (((bArr[i3 + 12] & UByte.MAX_VALUE) << 5) | ((bArr[i18] & UByte.MAX_VALUE) >> 3)) & 8191);
            setCoeffIndex(i5, 4096 - getCoeffIndex(i5));
            setCoeffIndex(i7, 4096 - getCoeffIndex(i7));
            setCoeffIndex(i9, 4096 - getCoeffIndex(i9));
            setCoeffIndex(i11, 4096 - getCoeffIndex(i11));
            setCoeffIndex(i13, 4096 - getCoeffIndex(i13));
            setCoeffIndex(i15, 4096 - getCoeffIndex(i15));
            setCoeffIndex(i17, 4096 - getCoeffIndex(i17));
            setCoeffIndex(i19, 4096 - getCoeffIndex(i19));
        }
    }

    public byte[] polyt1Pack() {
        byte[] bArr = new byte[DilithiumEngine.DilithiumPolyT1PackedBytes];
        for (int i = 0; i < this.dilithiumN / 4; i++) {
            int i2 = i * 5;
            int[] iArr = this.coeffs;
            int i3 = i * 4;
            int i4 = iArr[i3 + 0];
            bArr[i2 + 0] = (byte) (i4 >> 0);
            int i5 = iArr[i3 + 1];
            bArr[i2 + 1] = (byte) ((i4 >> 8) | (i5 << 2));
            int i6 = i5 >> 6;
            int i7 = iArr[i3 + 2];
            bArr[i2 + 2] = (byte) (i6 | (i7 << 4));
            int i8 = iArr[i3 + 3];
            bArr[i2 + 3] = (byte) ((i8 << 6) | (i7 >> 4));
            bArr[i2 + 4] = (byte) (i8 >> 2);
        }
        return bArr;
    }

    public void polyt1Unpack(byte[] bArr) {
        for (int i = 0; i < this.dilithiumN / 4; i++) {
            int i2 = i * 4;
            int i3 = i * 5;
            int i4 = i3 + 1;
            setCoeffIndex(i2 + 0, (((bArr[i3 + 0] & UByte.MAX_VALUE) >> 0) | ((bArr[i4] & UByte.MAX_VALUE) << 8)) & 1023);
            int i5 = i3 + 2;
            setCoeffIndex(i2 + 1, (((bArr[i4] & UByte.MAX_VALUE) >> 2) | ((bArr[i5] & UByte.MAX_VALUE) << 6)) & 1023);
            int i6 = i3 + 3;
            setCoeffIndex(i2 + 2, (((bArr[i5] & UByte.MAX_VALUE) >> 4) | ((bArr[i6] & UByte.MAX_VALUE) << 4)) & 1023);
            setCoeffIndex(i2 + 3, (((bArr[i6] & UByte.MAX_VALUE) >> 6) | ((bArr[i3 + 4] & UByte.MAX_VALUE) << 2)) & 1023);
        }
    }

    public void power2Round(Poly poly) {
        for (int i = 0; i < this.dilithiumN; i++) {
            int[] power2Round = Rounding.power2Round(getCoeffIndex(i));
            setCoeffIndex(i, power2Round[0]);
            poly.setCoeffIndex(i, power2Round[1]);
        }
    }

    public void reduce() {
        for (int i = 0; i < this.dilithiumN; i++) {
            setCoeffIndex(i, Reduce.reduce32(getCoeffIndex(i)));
        }
    }

    public void setCoeffIndex(int i, int i2) {
        this.coeffs[i] = i2;
    }

    public void setCoeffs(int[] iArr) {
        this.coeffs = iArr;
    }

    public void shiftLeft() {
        for (int i = 0; i < this.dilithiumN; i++) {
            setCoeffIndex(i, getCoeffIndex(i) << 13);
        }
    }

    public void subtract(Poly poly) {
        for (int i = 0; i < this.dilithiumN; i++) {
            setCoeffIndex(i, getCoeffIndex(i) - poly.getCoeffIndex(i));
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        int i = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i >= iArr.length) {
                stringBuffer.append("]");
                return stringBuffer.toString();
            }
            stringBuffer.append(iArr[i]);
            if (i != this.coeffs.length - 1) {
                stringBuffer.append(", ");
            }
            i++;
        }
    }

    public void uniformBlocks(byte[] bArr, short s) {
        int i = this.polyUniformNBlocks * this.symmetric.stream128BlockBytes;
        byte[] bArr2 = new byte[i + 2];
        this.symmetric.stream128init(bArr, s);
        this.symmetric.stream128squeezeBlocks(bArr2, 0, i);
        int rejectUniform = rejectUniform(this, 0, this.dilithiumN, bArr2, i);
        while (rejectUniform < this.dilithiumN) {
            int i2 = i % 3;
            for (int i3 = 0; i3 < i2; i3++) {
                bArr2[i3] = bArr2[(i - i2) + i3];
            }
            Symmetric symmetric = this.symmetric;
            symmetric.stream128squeezeBlocks(bArr2, i2, symmetric.stream128BlockBytes);
            i = this.symmetric.stream128BlockBytes + i2;
            rejectUniform += rejectUniform(this, rejectUniform, this.dilithiumN - rejectUniform, bArr2, i);
        }
    }

    public void uniformEta(byte[] bArr, short s) {
        int i;
        int dilithiumEta = this.engine.getDilithiumEta();
        if (this.engine.getDilithiumEta() == 2) {
            i = this.symmetric.stream256BlockBytes + 136;
        } else if (this.engine.getDilithiumEta() != 4) {
            throw new RuntimeException("Wrong Dilithium Eta!");
        } else {
            i = this.symmetric.stream256BlockBytes + 227;
        }
        int i2 = ((i - 1) / this.symmetric.stream256BlockBytes) * this.symmetric.stream256BlockBytes;
        byte[] bArr2 = new byte[i2];
        this.symmetric.stream256init(bArr, s);
        this.symmetric.stream256squeezeBlocks(bArr2, 0, i2);
        int rejectEta = rejectEta(this, 0, this.dilithiumN, bArr2, i2, dilithiumEta);
        while (rejectEta < 256) {
            Symmetric symmetric = this.symmetric;
            symmetric.stream256squeezeBlocks(bArr2, 0, symmetric.stream256BlockBytes);
            rejectEta += rejectEta(this, rejectEta, this.dilithiumN - rejectEta, bArr2, this.symmetric.stream256BlockBytes, dilithiumEta);
        }
    }

    public void uniformGamma1(byte[] bArr, short s) {
        byte[] bArr2 = new byte[this.engine.getPolyUniformGamma1NBlocks() * this.symmetric.stream256BlockBytes];
        this.symmetric.stream256init(bArr, s);
        this.symmetric.stream256squeezeBlocks(bArr2, 0, this.engine.getPolyUniformGamma1NBlocks() * this.symmetric.stream256BlockBytes);
        unpackZ(bArr2);
    }

    public byte[] w1Pack() {
        byte[] bArr = new byte[this.engine.getDilithiumPolyW1PackedBytes()];
        int i = 0;
        if (this.engine.getDilithiumGamma2() == 95232) {
            while (i < this.dilithiumN / 4) {
                int i2 = i * 3;
                int i3 = i * 4;
                int i4 = i3 + 1;
                bArr[i2 + 0] = (byte) (((byte) getCoeffIndex(i3 + 0)) | (getCoeffIndex(i4) << 6));
                int i5 = i3 + 2;
                bArr[i2 + 1] = (byte) (((byte) (getCoeffIndex(i4) >> 2)) | (getCoeffIndex(i5) << 4));
                bArr[i2 + 2] = (byte) (((byte) (getCoeffIndex(i5) >> 4)) | (getCoeffIndex(i3 + 3) << 2));
                i++;
            }
        } else if (this.engine.getDilithiumGamma2() == 261888) {
            while (i < this.dilithiumN / 2) {
                int i6 = i * 2;
                bArr[i] = (byte) ((getCoeffIndex(i6 + 1) << 4) | getCoeffIndex(i6 + 0));
                i++;
            }
        }
        return bArr;
    }

    public byte[] zPack() {
        byte[] bArr = new byte[this.engine.getDilithiumPolyZPackedBytes()];
        int[] iArr = new int[4];
        if (this.engine.getDilithiumGamma1() == 131072) {
            for (int i = 0; i < this.dilithiumN / 4; i++) {
                int i2 = i * 4;
                iArr[0] = this.engine.getDilithiumGamma1() - getCoeffIndex(i2 + 0);
                iArr[1] = this.engine.getDilithiumGamma1() - getCoeffIndex(i2 + 1);
                iArr[2] = this.engine.getDilithiumGamma1() - getCoeffIndex(i2 + 2);
                int dilithiumGamma1 = this.engine.getDilithiumGamma1() - getCoeffIndex(i2 + 3);
                iArr[3] = dilithiumGamma1;
                int i3 = i * 9;
                int i4 = iArr[0];
                bArr[i3 + 0] = (byte) i4;
                bArr[i3 + 1] = (byte) (i4 >> 8);
                int i5 = iArr[1];
                bArr[i3 + 2] = (byte) (((byte) (i4 >> 16)) | (i5 << 2));
                bArr[i3 + 3] = (byte) (i5 >> 6);
                int i6 = iArr[2];
                bArr[i3 + 4] = (byte) (((byte) (i5 >> 14)) | (i6 << 4));
                bArr[i3 + 5] = (byte) (i6 >> 4);
                bArr[i3 + 6] = (byte) (((byte) (i6 >> 12)) | (dilithiumGamma1 << 6));
                bArr[i3 + 7] = (byte) (dilithiumGamma1 >> 2);
                bArr[i3 + 8] = (byte) (dilithiumGamma1 >> 10);
            }
        } else if (this.engine.getDilithiumGamma1() != 524288) {
            throw new RuntimeException("Wrong Dilithium Gamma1!");
        } else {
            for (int i7 = 0; i7 < this.dilithiumN / 2; i7++) {
                int i8 = i7 * 2;
                iArr[0] = this.engine.getDilithiumGamma1() - getCoeffIndex(i8 + 0);
                int dilithiumGamma12 = this.engine.getDilithiumGamma1() - getCoeffIndex(i8 + 1);
                iArr[1] = dilithiumGamma12;
                int i9 = i7 * 5;
                int i10 = iArr[0];
                bArr[i9 + 0] = (byte) i10;
                bArr[i9 + 1] = (byte) (i10 >> 8);
                bArr[i9 + 2] = (byte) (((byte) (i10 >> 16)) | (dilithiumGamma12 << 4));
                bArr[i9 + 3] = (byte) (dilithiumGamma12 >> 4);
                bArr[i9 + 4] = (byte) (dilithiumGamma12 >> 12);
            }
        }
        return bArr;
    }

    public void zUnpack(byte[] bArr) {
        int i = 0;
        if (this.engine.getDilithiumGamma1() != 131072) {
            if (this.engine.getDilithiumGamma1() != 524288) {
                throw new RuntimeException("Wrong Dilithium Gamma1!");
            }
            while (i < this.dilithiumN / 2) {
                int i2 = i * 2;
                int i3 = i2 + 0;
                int i4 = i * 5;
                int i5 = i4 + 2;
                setCoeffIndex(i3, ((bArr[i4 + 0] & UByte.MAX_VALUE) | ((bArr[i4 + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i5] & UByte.MAX_VALUE) << 16)) & 1048575);
                int i6 = i2 + 1;
                setCoeffIndex(i6, (((bArr[i4 + 4] & UByte.MAX_VALUE) << 12) | ((bArr[i5] & UByte.MAX_VALUE) >>> 4) | ((bArr[i4 + 3] & UByte.MAX_VALUE) << 4)) & 1048575);
                setCoeffIndex(i3, this.engine.getDilithiumGamma1() - getCoeffIndex(i3));
                setCoeffIndex(i6, this.engine.getDilithiumGamma1() - getCoeffIndex(i6));
                i++;
            }
            return;
        }
        while (i < this.dilithiumN / 4) {
            int i7 = i * 4;
            int i8 = i7 + 0;
            int i9 = i * 9;
            int i10 = i9 + 2;
            setCoeffIndex(i8, ((bArr[i9 + 0] & UByte.MAX_VALUE) | ((bArr[i9 + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i10] & UByte.MAX_VALUE) << 16)) & 262143);
            int i11 = i7 + 1;
            int i12 = i9 + 4;
            setCoeffIndex(i11, (((bArr[i10] & UByte.MAX_VALUE) >>> 2) | ((bArr[i9 + 3] & UByte.MAX_VALUE) << 6) | ((bArr[i12] & UByte.MAX_VALUE) << 14)) & 262143);
            int i13 = i7 + 2;
            int i14 = i9 + 6;
            setCoeffIndex(i13, (((bArr[i12] & UByte.MAX_VALUE) >>> 4) | ((bArr[i9 + 5] & UByte.MAX_VALUE) << 4) | ((bArr[i14] & UByte.MAX_VALUE) << 12)) & 262143);
            int i15 = i7 + 3;
            setCoeffIndex(i15, (((bArr[i9 + 8] & UByte.MAX_VALUE) << 10) | ((bArr[i14] & UByte.MAX_VALUE) >>> 6) | ((bArr[i9 + 7] & UByte.MAX_VALUE) << 2)) & 262143);
            setCoeffIndex(i8, this.engine.getDilithiumGamma1() - getCoeffIndex(i8));
            setCoeffIndex(i11, this.engine.getDilithiumGamma1() - getCoeffIndex(i11));
            setCoeffIndex(i13, this.engine.getDilithiumGamma1() - getCoeffIndex(i13));
            setCoeffIndex(i15, this.engine.getDilithiumGamma1() - getCoeffIndex(i15));
            i++;
        }
    }
}
