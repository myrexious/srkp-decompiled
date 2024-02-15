package org.bouncycastle.pqc.crypto.cmce;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class GF13 extends GF {
    public GF13(int i) {
        super(i);
    }

    private short gf_sq2mul(short s, short s2) {
        long[] jArr = {2301339409586323456L, 4494803534348288L, 8778913153024L, 17146314752L, 33423360, 122880};
        long j = s;
        long j2 = s2;
        long j3 = (j2 << 18) * (64 & j);
        long j4 = j ^ (j << 21);
        long j5 = ((j2 * (j4 & 8589934624L)) << 15) ^ (((((j3 ^ ((268435457 & j4) * j2)) ^ (((536870914 & j4) * j2) << 3)) ^ (((1073741828 & j4) * j2) << 6)) ^ (((2147483656L & j4) * j2) << 9)) ^ (((4294967312L & j4) * j2) << 12));
        for (int i = 0; i < 6; i++) {
            long j6 = jArr[i] & j5;
            j5 ^= (j6 >> 13) ^ (((j6 >> 9) ^ (j6 >> 10)) ^ (j6 >> 12));
        }
        return (short) (j5 & this.GFMASK);
    }

    private short gf_sqmul(short s, short s2) {
        long[] jArr = {137170518016L, 267911168, 516096};
        long j = s;
        long j2 = s2;
        long j3 = (j2 << 6) * (64 & j);
        long j4 = j ^ (j << 7);
        long j5 = ((j2 * (j4 & 524320)) << 5) ^ (((((j3 ^ ((16385 & j4) * j2)) ^ (((32770 & j4) * j2) << 1)) ^ (((65540 & j4) * j2) << 2)) ^ (((131080 & j4) * j2) << 3)) ^ (((262160 & j4) * j2) << 4));
        for (int i = 0; i < 3; i++) {
            long j6 = jArr[i] & j5;
            j5 ^= (j6 >> 13) ^ (((j6 >> 9) ^ (j6 >> 10)) ^ (j6 >> 12));
        }
        return (short) (j5 & this.GFMASK);
    }

    @Override // org.bouncycastle.pqc.crypto.cmce.GF
    public short gf_frac(short s, short s2) {
        short gf_sqmul = gf_sqmul(s, s);
        short gf_sq2mul = gf_sq2mul(gf_sqmul, gf_sqmul);
        return gf_sqmul(gf_sq2mul(gf_sq2(gf_sq2mul(gf_sq2(gf_sq2mul), gf_sq2mul)), gf_sq2mul), s2);
    }

    @Override // org.bouncycastle.pqc.crypto.cmce.GF
    public short gf_inv(short s) {
        return gf_frac(s, (short) 1);
    }

    @Override // org.bouncycastle.pqc.crypto.cmce.GF
    public short gf_mul(short s, short s2) {
        long j;
        long j2 = s;
        long j3 = s2;
        long j4 = (1 & j3) * j2;
        for (int i = 1; i < this.GFBITS; i++) {
            j4 ^= ((1 << i) & j3) * j2;
        }
        long j5 = 33488896 & j4;
        long j6 = 57344 & (((j5 >> 13) ^ (((j5 >> 9) ^ (j5 >> 10)) ^ (j5 >> 12))) ^ j4);
        return (short) ((j ^ ((((j6 >> 10) ^ (j6 >> 9)) ^ (j6 >> 12)) ^ (j6 >> 13))) & this.GFMASK);
    }

    protected short gf_sq2(short s) {
        long[] jArr = {1229782938247303441L, 217020518514230019L, 4222189076152335L, 1095216660735L};
        long[] jArr2 = {561850441793536L, 1097364144128L, 2143289344, 4186112};
        long j = s;
        long j2 = (j | (j << 24)) & jArr[3];
        long j3 = (j2 | (j2 << 12)) & jArr[2];
        long j4 = (j3 | (j3 << 6)) & jArr[1];
        long j5 = (j4 | (j4 << 3)) & jArr[0];
        for (int i = 0; i < 4; i++) {
            long j6 = jArr2[i] & j5;
            j5 ^= (j6 >> 13) ^ (((j6 >> 9) ^ (j6 >> 10)) ^ (j6 >> 12));
        }
        return (short) (this.GFMASK & j5);
    }
}
