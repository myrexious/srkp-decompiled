package org.bouncycastle.pqc.math.ntru;

import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPSParameterSet;

/* loaded from: classes2.dex */
public class HPSPolynomial extends Polynomial {
    public HPSPolynomial(NTRUHPSParameterSet nTRUHPSParameterSet) {
        super(nTRUHPSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void lift(Polynomial polynomial) {
        System.arraycopy(polynomial.coeffs, 0, this.coeffs, 0, this.coeffs.length);
        z3ToZq();
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void r2Inv(Polynomial polynomial) {
        r2Inv(polynomial, new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void rqInv(Polynomial polynomial) {
        rqInv(polynomial, new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void s3Inv(Polynomial polynomial) {
        s3Inv(polynomial, new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params), new HPSPolynomial((NTRUHPSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        int length = this.coeffs.length;
        int i = 0;
        while (i < this.params.packDegree() / 8) {
            int i2 = i * 8;
            int i3 = i * 11;
            int i4 = i3 + 1;
            this.coeffs[i2 + 0] = (short) (((bArr[i3 + 0] & UByte.MAX_VALUE) >>> 0) | ((((short) (bArr[i4] & UByte.MAX_VALUE)) & 7) << 8));
            int i5 = i3 + 2;
            this.coeffs[i2 + 1] = (short) (((bArr[i4] & UByte.MAX_VALUE) >>> 3) | ((((short) (bArr[i5] & UByte.MAX_VALUE)) & 63) << 5));
            int i6 = ((bArr[i5] & UByte.MAX_VALUE) >>> 6) | ((((short) (bArr[i3 + 3] & UByte.MAX_VALUE)) & 255) << 2);
            int i7 = i3 + 4;
            this.coeffs[i2 + 2] = (short) (i6 | ((((short) (bArr[i7] & UByte.MAX_VALUE)) & 1) << 10));
            int i8 = i3 + 5;
            this.coeffs[i2 + 3] = (short) (((bArr[i7] & UByte.MAX_VALUE) >>> 1) | ((((short) (bArr[i8] & UByte.MAX_VALUE)) & 15) << 7));
            int i9 = i3 + 6;
            this.coeffs[i2 + 4] = (short) (((((short) (bArr[i9] & UByte.MAX_VALUE)) & 127) << 4) | ((bArr[i8] & UByte.MAX_VALUE) >>> 4));
            int i10 = i3 + 8;
            this.coeffs[i2 + 5] = (short) (((bArr[i9] & UByte.MAX_VALUE) >>> 7) | ((((short) (bArr[i3 + 7] & UByte.MAX_VALUE)) & 255) << 1) | ((((short) (bArr[i10] & UByte.MAX_VALUE)) & 3) << 9));
            int i11 = i3 + 9;
            this.coeffs[i2 + 6] = (short) (((bArr[i10] & UByte.MAX_VALUE) >>> 2) | ((((short) (bArr[i11] & UByte.MAX_VALUE)) & 31) << 6));
            this.coeffs[i2 + 7] = (short) (((bArr[i11] & UByte.MAX_VALUE) >>> 5) | ((((short) (bArr[i3 + 10] & UByte.MAX_VALUE)) & 255) << 3));
            i++;
        }
        int packDegree = this.params.packDegree() & 7;
        if (packDegree == 2) {
            int i12 = i * 8;
            int i13 = i * 11;
            int i14 = i13 + 1;
            this.coeffs[i12 + 0] = (short) (((bArr[i13 + 0] & UByte.MAX_VALUE) >>> 0) | ((((short) (bArr[i14] & UByte.MAX_VALUE)) & 7) << 8));
            this.coeffs[i12 + 1] = (short) (((((short) (bArr[i13 + 2] & UByte.MAX_VALUE)) & 63) << 5) | ((bArr[i14] & UByte.MAX_VALUE) >>> 3));
        } else if (packDegree == 4) {
            int i15 = i * 8;
            int i16 = i * 11;
            int i17 = i16 + 1;
            this.coeffs[i15 + 0] = (short) (((bArr[i16 + 0] & UByte.MAX_VALUE) >>> 0) | ((((short) (bArr[i17] & UByte.MAX_VALUE)) & 7) << 8));
            int i18 = i16 + 2;
            this.coeffs[i15 + 1] = (short) (((bArr[i17] & UByte.MAX_VALUE) >>> 3) | ((((short) (bArr[i18] & UByte.MAX_VALUE)) & 63) << 5));
            int i19 = i16 + 4;
            this.coeffs[i15 + 2] = (short) (((((short) (bArr[i16 + 3] & UByte.MAX_VALUE)) & 255) << 2) | ((bArr[i18] & UByte.MAX_VALUE) >>> 6) | ((((short) (bArr[i19] & UByte.MAX_VALUE)) & 1) << 10));
            this.coeffs[i15 + 3] = (short) (((((short) (bArr[i16 + 5] & UByte.MAX_VALUE)) & 15) << 7) | ((bArr[i19] & UByte.MAX_VALUE) >>> 1));
        }
        this.coeffs[length - 1] = 0;
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public byte[] sqToBytes(int i) {
        byte[] bArr = new byte[i];
        short[] sArr = new short[8];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int packDegree = this.params.packDegree() / 8;
            short s = UShort.MAX_VALUE;
            if (i3 >= packDegree) {
                break;
            }
            int i4 = i2;
            while (i4 < 8) {
                sArr[i4] = (short) modQ(this.coeffs[(i3 * 8) + i4] & s, this.params.q());
                i4++;
                s = UShort.MAX_VALUE;
            }
            int i5 = i3 * 11;
            short s2 = sArr[i2];
            bArr[i5 + 0] = (byte) (s2 & 255);
            int i6 = s2 >>> 8;
            short s3 = sArr[1];
            bArr[i5 + 1] = (byte) (i6 | ((s3 & 31) << 3));
            int i7 = s3 >>> 5;
            short s4 = sArr[2];
            bArr[i5 + 2] = (byte) (i7 | ((s4 & 3) << 6));
            bArr[i5 + 3] = (byte) ((s4 >>> 2) & 255);
            int i8 = s4 >>> 10;
            short s5 = sArr[3];
            bArr[i5 + 4] = (byte) (i8 | ((s5 & 127) << 1));
            short s6 = sArr[4];
            bArr[i5 + 5] = (byte) ((s5 >>> 7) | ((s6 & 15) << 4));
            short s7 = sArr[5];
            bArr[i5 + 6] = (byte) ((s6 >>> 4) | ((s7 & 1) << 7));
            bArr[i5 + 7] = (byte) ((s7 >>> 1) & 255);
            int i9 = s7 >>> 9;
            short s8 = sArr[6];
            bArr[i5 + 8] = (byte) (i9 | ((s8 & 63) << 2));
            short s9 = sArr[7];
            bArr[i5 + 9] = (byte) ((s8 >>> 6) | ((s9 & 7) << 5));
            bArr[i5 + 10] = (byte) (s9 >>> 3);
            i3++;
            i2 = 0;
        }
        int i10 = 0;
        while (true) {
            int i11 = i3 * 8;
            if (i10 >= this.params.packDegree() - i11) {
                break;
            }
            sArr[i10] = (short) modQ(this.coeffs[i11 + i10] & UShort.MAX_VALUE, this.params.q());
            i10++;
        }
        while (i10 < 8) {
            sArr[i10] = 0;
            i10++;
        }
        int packDegree2 = this.params.packDegree() & 7;
        if (packDegree2 == 2) {
            int i12 = i3 * 11;
            short s10 = sArr[0];
            bArr[i12 + 0] = (byte) (s10 & 255);
            int i13 = s10 >>> 8;
            short s11 = sArr[1];
            bArr[i12 + 1] = (byte) (i13 | ((s11 & 31) << 3));
            bArr[i12 + 2] = (byte) ((s11 >>> 5) | ((sArr[2] & 3) << 6));
        } else if (packDegree2 == 4) {
            int i14 = i3 * 11;
            short s12 = sArr[0];
            bArr[i14 + 0] = (byte) (s12 & 255);
            int i15 = s12 >>> 8;
            short s13 = sArr[1];
            bArr[i14 + 1] = (byte) (i15 | ((s13 & 31) << 3));
            int i16 = s13 >>> 5;
            short s14 = sArr[2];
            bArr[i14 + 2] = (byte) (i16 | ((s14 & 3) << 6));
            bArr[i14 + 3] = (byte) ((s14 >>> 2) & 255);
            int i17 = s14 >>> 10;
            short s15 = sArr[3];
            bArr[i14 + 4] = (byte) (i17 | ((s15 & 127) << 1));
            bArr[i14 + 5] = (byte) ((s15 >>> 7) | ((sArr[4] & 15) << 4));
        }
        return bArr;
    }
}
