package org.bouncycastle.pqc.math.ntru;

import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHRSSParameterSet;

/* loaded from: classes2.dex */
public class HRSSPolynomial extends Polynomial {
    public HRSSPolynomial(NTRUHRSSParameterSet nTRUHRSSParameterSet) {
        super(nTRUHRSSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void lift(Polynomial polynomial) {
        int length = this.coeffs.length;
        HRSSPolynomial hRSSPolynomial = new HRSSPolynomial((NTRUHRSSParameterSet) this.params);
        short s = (short) (3 - (length % 3));
        int i = 0;
        int i2 = 2 - s;
        hRSSPolynomial.coeffs[0] = (short) ((polynomial.coeffs[0] * i2) + (polynomial.coeffs[1] * 0) + (polynomial.coeffs[2] * s));
        hRSSPolynomial.coeffs[1] = (short) ((polynomial.coeffs[1] * i2) + (polynomial.coeffs[2] * 0));
        hRSSPolynomial.coeffs[2] = (short) (polynomial.coeffs[2] * i2);
        short s2 = 0;
        for (int i3 = 3; i3 < length; i3++) {
            short[] sArr = hRSSPolynomial.coeffs;
            sArr[0] = (short) (sArr[0] + (polynomial.coeffs[i3] * ((s * 2) + s2)));
            short[] sArr2 = hRSSPolynomial.coeffs;
            int i4 = s2 + s;
            sArr2[1] = (short) (sArr2[1] + (polynomial.coeffs[i3] * i4));
            short[] sArr3 = hRSSPolynomial.coeffs;
            sArr3[2] = (short) (sArr3[2] + (polynomial.coeffs[i3] * s2));
            s2 = (short) (i4 % 3);
        }
        short[] sArr4 = hRSSPolynomial.coeffs;
        int i5 = s + s2;
        sArr4[1] = (short) (sArr4[1] + (polynomial.coeffs[0] * i5));
        short[] sArr5 = hRSSPolynomial.coeffs;
        sArr5[2] = (short) (sArr5[2] + (polynomial.coeffs[0] * s2));
        short[] sArr6 = hRSSPolynomial.coeffs;
        sArr6[2] = (short) (sArr6[2] + (polynomial.coeffs[1] * i5));
        for (int i6 = 3; i6 < length; i6++) {
            hRSSPolynomial.coeffs[i6] = (short) (hRSSPolynomial.coeffs[i6 - 3] + ((polynomial.coeffs[i6] + polynomial.coeffs[i6 - 1] + polynomial.coeffs[i6 - 2]) * 2));
        }
        hRSSPolynomial.mod3PhiN();
        hRSSPolynomial.z3ToZq();
        this.coeffs[0] = (short) (-hRSSPolynomial.coeffs[0]);
        while (i < length - 1) {
            int i7 = i + 1;
            this.coeffs[i7] = (short) (hRSSPolynomial.coeffs[i] - hRSSPolynomial.coeffs[i7]);
            i = i7;
        }
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void r2Inv(Polynomial polynomial) {
        r2Inv(polynomial, new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void rqInv(Polynomial polynomial) {
        rqInv(polynomial, new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void s3Inv(Polynomial polynomial) {
        s3Inv(polynomial, new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params), new HRSSPolynomial((NTRUHRSSParameterSet) this.params));
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        int i = 0;
        while (i < this.params.packDegree() / 8) {
            int i2 = i * 8;
            int i3 = i * 13;
            int i4 = i3 + 1;
            this.coeffs[i2 + 0] = (short) ((bArr[i3 + 0] & UByte.MAX_VALUE) | ((((short) (bArr[i4] & UByte.MAX_VALUE)) & 31) << 8));
            int i5 = ((bArr[i4] & UByte.MAX_VALUE) >>> 5) | (((short) (bArr[i3 + 2] & UByte.MAX_VALUE)) << 3);
            int i6 = i3 + 3;
            this.coeffs[i2 + 1] = (short) (i5 | ((((short) (bArr[i6] & UByte.MAX_VALUE)) & 3) << 11));
            int i7 = i3 + 4;
            this.coeffs[i2 + 2] = (short) (((bArr[i6] & UByte.MAX_VALUE) >>> 2) | ((((short) (bArr[i7] & UByte.MAX_VALUE)) & 127) << 6));
            int i8 = ((bArr[i7] & UByte.MAX_VALUE) >>> 7) | (((short) (bArr[i3 + 5] & UByte.MAX_VALUE)) << 1);
            int i9 = i3 + 6;
            this.coeffs[i2 + 3] = (short) (i8 | ((((short) (bArr[i9] & UByte.MAX_VALUE)) & 15) << 9));
            int i10 = i3 + 8;
            this.coeffs[i2 + 4] = (short) ((((short) (bArr[i3 + 7] & UByte.MAX_VALUE)) << 4) | ((bArr[i9] & UByte.MAX_VALUE) >>> 4) | ((((short) (bArr[i10] & UByte.MAX_VALUE)) & 1) << 12));
            int i11 = i3 + 9;
            this.coeffs[i2 + 5] = (short) (((bArr[i10] & UByte.MAX_VALUE) >>> 1) | ((((short) (bArr[i11] & UByte.MAX_VALUE)) & 63) << 7));
            int i12 = i3 + 11;
            this.coeffs[i2 + 6] = (short) ((((short) (bArr[i3 + 10] & UByte.MAX_VALUE)) << 2) | ((bArr[i11] & UByte.MAX_VALUE) >>> 6) | ((((short) (bArr[i12] & UByte.MAX_VALUE)) & 7) << 10));
            this.coeffs[i2 + 7] = (short) (((bArr[i12] & UByte.MAX_VALUE) >>> 3) | (((short) (bArr[i3 + 12] & UByte.MAX_VALUE)) << 5));
            i++;
        }
        int packDegree = this.params.packDegree() & 7;
        if (packDegree == 2) {
            int i13 = i * 8;
            int i14 = i * 13;
            int i15 = i14 + 1;
            this.coeffs[i13 + 0] = (short) ((bArr[i14 + 0] & UByte.MAX_VALUE) | ((((short) (bArr[i15] & UByte.MAX_VALUE)) & 31) << 8));
            this.coeffs[i13 + 1] = (short) (((((short) (bArr[i14 + 3] & UByte.MAX_VALUE)) & 3) << 11) | ((bArr[i15] & UByte.MAX_VALUE) >>> 5) | (((short) (bArr[i14 + 2] & UByte.MAX_VALUE)) << 3));
        } else if (packDegree == 4) {
            int i16 = i * 8;
            int i17 = i * 13;
            int i18 = i17 + 1;
            this.coeffs[i16 + 0] = (short) ((bArr[i17 + 0] & UByte.MAX_VALUE) | ((((short) (bArr[i18] & UByte.MAX_VALUE)) & 31) << 8));
            int i19 = ((bArr[i18] & UByte.MAX_VALUE) >>> 5) | (((short) (bArr[i17 + 2] & UByte.MAX_VALUE)) << 3);
            int i20 = i17 + 3;
            this.coeffs[i16 + 1] = (short) (i19 | ((((short) (bArr[i20] & UByte.MAX_VALUE)) & 3) << 11));
            int i21 = i17 + 4;
            this.coeffs[i16 + 2] = (short) (((bArr[i20] & UByte.MAX_VALUE) >>> 2) | ((((short) (bArr[i21] & UByte.MAX_VALUE)) & 127) << 6));
            this.coeffs[i16 + 3] = (short) (((((short) (bArr[i17 + 6] & UByte.MAX_VALUE)) & 15) << 9) | ((bArr[i21] & UByte.MAX_VALUE) >>> 7) | (((short) (bArr[i17 + 5] & UByte.MAX_VALUE)) << 1));
        }
        this.coeffs[this.params.n() - 1] = 0;
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
            int i5 = i3 * 13;
            short s2 = sArr[i2];
            bArr[i5 + 0] = (byte) (s2 & 255);
            int i6 = s2 >>> 8;
            short s3 = sArr[1];
            bArr[i5 + 1] = (byte) (i6 | ((s3 & 7) << 5));
            bArr[i5 + 2] = (byte) ((s3 >>> 3) & 255);
            int i7 = s3 >>> 11;
            short s4 = sArr[2];
            bArr[i5 + 3] = (byte) (i7 | ((s4 & 63) << 2));
            short s5 = sArr[3];
            bArr[i5 + 4] = (byte) ((s4 >>> 6) | ((s5 & 1) << 7));
            bArr[i5 + 5] = (byte) ((s5 >>> 1) & 255);
            int i8 = s5 >>> 9;
            short s6 = sArr[4];
            bArr[i5 + 6] = (byte) (i8 | ((s6 & 15) << 4));
            bArr[i5 + 7] = (byte) ((s6 >>> 4) & 255);
            short s7 = sArr[5];
            bArr[i5 + 8] = (byte) ((s6 >>> 12) | ((s7 & 127) << 1));
            int i9 = s7 >>> 7;
            short s8 = sArr[6];
            bArr[i5 + 9] = (byte) (i9 | ((s8 & 3) << 6));
            bArr[i5 + 10] = (byte) ((s8 >>> 2) & 255);
            short s9 = sArr[7];
            bArr[i5 + 11] = (byte) ((s8 >>> 10) | ((s9 & 31) << 3));
            bArr[i5 + 12] = (byte) (s9 >>> 5);
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
        int packDegree2 = this.params.packDegree() - ((this.params.packDegree() / 8) * 8);
        if (packDegree2 != 2) {
            if (packDegree2 == 4) {
                int i12 = i3 * 13;
                short s10 = sArr[0];
                bArr[i12 + 0] = (byte) (s10 & 255);
                int i13 = s10 >>> 8;
                short s11 = sArr[1];
                bArr[i12 + 1] = (byte) (i13 | ((s11 & 7) << 5));
                bArr[i12 + 2] = (byte) ((s11 >>> 3) & 255);
                int i14 = s11 >>> 11;
                short s12 = sArr[2];
                bArr[i12 + 3] = (byte) (i14 | ((s12 & 63) << 2));
                int i15 = s12 >>> 6;
                short s13 = sArr[3];
                bArr[i12 + 4] = (byte) (i15 | ((s13 & 1) << 7));
                bArr[i12 + 5] = (byte) ((s13 >>> 1) & 255);
                bArr[i12 + 6] = (byte) ((s13 >>> 9) | ((sArr[4] & 15) << 4));
            }
            return bArr;
        }
        int i16 = i3 * 13;
        short s14 = sArr[0];
        bArr[i16 + 0] = (byte) (s14 & 255);
        int i17 = s14 >>> 8;
        short s15 = sArr[1];
        bArr[i16 + 1] = (byte) (i17 | ((s15 & 7) << 5));
        bArr[i16 + 2] = (byte) ((s15 >>> 3) & 255);
        bArr[i16 + 3] = (byte) ((s15 >>> 11) | ((sArr[2] & 63) << 2));
        return bArr;
    }
}
