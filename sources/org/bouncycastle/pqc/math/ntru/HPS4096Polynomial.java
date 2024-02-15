package org.bouncycastle.pqc.math.ntru;

import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPSParameterSet;

/* loaded from: classes2.dex */
public class HPS4096Polynomial extends HPSPolynomial {
    public HPS4096Polynomial(NTRUHPSParameterSet nTRUHPSParameterSet) {
        super(nTRUHPSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.HPSPolynomial, org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        for (int i = 0; i < this.params.packDegree() / 2; i++) {
            int i2 = i * 2;
            int i3 = i * 3;
            int i4 = i3 + 1;
            this.coeffs[i2 + 0] = (short) (((bArr[i3 + 0] & UByte.MAX_VALUE) >>> 0) | ((((short) (bArr[i4] & UByte.MAX_VALUE)) & 15) << 8));
            this.coeffs[i2 + 1] = (short) (((bArr[i4] & UByte.MAX_VALUE) >>> 4) | ((((short) (bArr[i3 + 2] & UByte.MAX_VALUE)) & 255) << 4));
        }
        this.coeffs[this.params.n() - 1] = 0;
    }

    @Override // org.bouncycastle.pqc.math.ntru.HPSPolynomial, org.bouncycastle.pqc.math.ntru.Polynomial
    public byte[] sqToBytes(int i) {
        byte[] bArr = new byte[i];
        int q = this.params.q();
        for (int i2 = 0; i2 < this.params.packDegree() / 2; i2++) {
            int i3 = i2 * 3;
            int i4 = i2 * 2;
            int i5 = i4 + 0;
            bArr[i3 + 0] = (byte) (modQ(this.coeffs[i5] & UShort.MAX_VALUE, q) & 255);
            int i6 = i4 + 1;
            bArr[i3 + 1] = (byte) ((modQ(this.coeffs[i5] & UShort.MAX_VALUE, q) >>> 8) | ((modQ(this.coeffs[i6] & UShort.MAX_VALUE, q) & 15) << 4));
            bArr[i3 + 2] = (byte) (modQ(this.coeffs[i6] & UShort.MAX_VALUE, q) >>> 4);
        }
        return bArr;
    }
}
