package org.bouncycastle.pqc.crypto.cmce;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class GF12 extends GF {
    public GF12(int i) {
        super(i);
    }

    @Override // org.bouncycastle.pqc.crypto.cmce.GF
    public short gf_frac(short s, short s2) {
        return gf_mul(gf_inv(s), s2);
    }

    @Override // org.bouncycastle.pqc.crypto.cmce.GF
    public short gf_inv(short s) {
        short gf_mul = gf_mul(gf_sq(s), s);
        short gf_mul2 = gf_mul(gf_sq(gf_sq(gf_mul)), gf_mul);
        return gf_sq(gf_mul(gf_sq(gf_mul(gf_sq(gf_sq(gf_mul(gf_sq(gf_sq(gf_sq(gf_sq(gf_mul2)))), gf_mul2))), gf_mul)), s));
    }

    @Override // org.bouncycastle.pqc.crypto.cmce.GF
    public short gf_mul(short s, short s2) {
        int i = (s2 & 1) * s;
        for (int i2 = 1; i2 < this.GFBITS; i2++) {
            i ^= ((1 << i2) & s2) * s;
        }
        int i3 = 8372224 & i;
        int i4 = (i3 >> 12) ^ ((i3 >> 9) ^ i);
        int i5 = i4 & 12288;
        return (short) (((i4 ^ (i5 >> 9)) ^ (i5 >> 12)) & ((1 << this.GFBITS) - 1));
    }

    protected short gf_sq(short s) {
        int[] iArr = {1431655765, 858993459, 252645135, 16711935};
        int i = (s | (s << 8)) & iArr[3];
        int i2 = (i | (i << 4)) & iArr[2];
        int i3 = (i2 | (i2 << 2)) & iArr[1];
        int i4 = (i3 | (i3 << 1)) & iArr[0];
        int i5 = 8372224 & i4;
        int i6 = (i4 ^ (i5 >> 9)) ^ (i5 >> 12);
        int i7 = i6 & 12288;
        return (short) (((i6 ^ (i7 >> 9)) ^ (i7 >> 12)) & ((1 << this.GFBITS) - 1));
    }
}
