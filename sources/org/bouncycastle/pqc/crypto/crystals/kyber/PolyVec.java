package org.bouncycastle.pqc.crypto.crystals.kyber;

import kotlin.UByte;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class PolyVec {
    private KyberEngine engine;
    private int kyberK;
    private int polyVecBytes;
    Poly[] vec;

    public PolyVec() throws Exception {
        throw new Exception("Requires Parameter");
    }

    public PolyVec(KyberEngine kyberEngine) {
        this.engine = kyberEngine;
        this.kyberK = kyberEngine.getKyberK();
        this.polyVecBytes = kyberEngine.getKyberPolyVecBytes();
        this.vec = new Poly[this.kyberK];
        for (int i = 0; i < this.kyberK; i++) {
            this.vec[i] = new Poly(kyberEngine);
        }
    }

    public static void pointwiseAccountMontgomery(Poly poly, PolyVec polyVec, PolyVec polyVec2, KyberEngine kyberEngine) {
        Poly poly2 = new Poly(kyberEngine);
        Poly.baseMultMontgomery(poly, polyVec.getVectorIndex(0), polyVec2.getVectorIndex(0));
        for (int i = 1; i < kyberEngine.getKyberK(); i++) {
            Poly.baseMultMontgomery(poly2, polyVec.getVectorIndex(i), polyVec2.getVectorIndex(i));
            poly.addCoeffs(poly2);
        }
        poly.reduce();
    }

    public void addPoly(PolyVec polyVec) {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).addCoeffs(polyVec.getVectorIndex(i));
        }
    }

    public byte[] compressPolyVec() {
        conditionalSubQ();
        byte[] bArr = new byte[this.engine.getKyberPolyVecCompressedBytes()];
        short s = 8;
        if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * DilithiumEngine.DilithiumPolyT1PackedBytes) {
            short[] sArr = new short[4];
            int i = 0;
            for (int i2 = 0; i2 < this.kyberK; i2++) {
                for (int i3 = 0; i3 < 64; i3++) {
                    for (int i4 = 0; i4 < 4; i4++) {
                        sArr[i4] = (short) ((((getVectorIndex(i2).getCoeffIndex((i3 * 4) + i4) << 10) + 1664) / KyberEngine.KyberQ) & 1023);
                    }
                    short s2 = sArr[0];
                    bArr[i + 0] = (byte) (s2 >> 0);
                    short s3 = sArr[1];
                    bArr[i + 1] = (byte) ((s2 >> 8) | (s3 << 2));
                    int i5 = s3 >> 6;
                    short s4 = sArr[2];
                    bArr[i + 2] = (byte) (i5 | (s4 << 4));
                    int i6 = s4 >> 4;
                    short s5 = sArr[3];
                    bArr[i + 3] = (byte) (i6 | (s5 << 6));
                    bArr[i + 4] = (byte) (s5 >> 2);
                    i += 5;
                }
            }
        } else if (this.engine.getKyberPolyVecCompressedBytes() != this.kyberK * 352) {
            throw new RuntimeException("Kyber PolyVecCompressedBytes neither 320 * KyberK or 352 * KyberK!");
        } else {
            short[] sArr2 = new short[8];
            int i7 = 0;
            int i8 = 0;
            while (i7 < this.kyberK) {
                int i9 = 0;
                while (i9 < 32) {
                    for (int i10 = 0; i10 < s; i10++) {
                        sArr2[i10] = (short) ((((getVectorIndex(i7).getCoeffIndex((i9 * 8) + i10) << 11) + 1664) / KyberEngine.KyberQ) & 2047);
                    }
                    short s6 = sArr2[0];
                    bArr[i8 + 0] = (byte) (s6 >> 0);
                    short s7 = sArr2[1];
                    bArr[i8 + 1] = (byte) ((s6 >> s) | (s7 << 3));
                    short s8 = sArr2[2];
                    bArr[i8 + 2] = (byte) ((s7 >> 5) | (s8 << 6));
                    bArr[i8 + 3] = (byte) (s8 >> 2);
                    int i11 = s8 >> 10;
                    short s9 = sArr2[3];
                    bArr[i8 + 4] = (byte) (i11 | (s9 << 1));
                    short s10 = sArr2[4];
                    bArr[i8 + 5] = (byte) ((s9 >> 7) | (s10 << 4));
                    short s11 = sArr2[5];
                    bArr[i8 + 6] = (byte) ((s10 >> 4) | (s11 << 7));
                    bArr[i8 + 7] = (byte) (s11 >> 1);
                    int i12 = s11 >> 9;
                    short s12 = sArr2[6];
                    bArr[i8 + 8] = (byte) (i12 | (s12 << 2));
                    int i13 = s12 >> 6;
                    short s13 = sArr2[7];
                    bArr[i8 + 9] = (byte) (i13 | (s13 << 5));
                    bArr[i8 + 10] = (byte) (s13 >> 3);
                    i8 += 11;
                    i9++;
                    s = 8;
                }
                i7++;
                s = 8;
            }
        }
        return bArr;
    }

    public void conditionalSubQ() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).conditionalSubQ();
        }
    }

    public void decompressPolyVec(byte[] bArr) {
        if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * DilithiumEngine.DilithiumPolyT1PackedBytes) {
            short[] sArr = new short[4];
            int i = 0;
            for (int i2 = 0; i2 < this.kyberK; i2++) {
                for (int i3 = 0; i3 < 64; i3++) {
                    byte b = bArr[i + 1];
                    sArr[0] = (short) (((bArr[i] & UByte.MAX_VALUE) >> 0) | ((short) ((b & UByte.MAX_VALUE) << 8)));
                    byte b2 = bArr[i + 2];
                    sArr[1] = (short) (((b & UByte.MAX_VALUE) >> 2) | ((short) ((b2 & UByte.MAX_VALUE) << 6)));
                    byte b3 = bArr[i + 3];
                    sArr[2] = (short) (((b2 & UByte.MAX_VALUE) >> 4) | ((short) ((b3 & UByte.MAX_VALUE) << 4)));
                    sArr[3] = (short) (((b3 & UByte.MAX_VALUE) >> 6) | ((short) ((bArr[i + 4] & UByte.MAX_VALUE) << 2)));
                    i += 5;
                    for (int i4 = 0; i4 < 4; i4++) {
                        this.vec[i2].setCoeffIndex((i3 * 4) + i4, (short) ((((sArr[i4] & 1023) * KyberEngine.KyberQ) + 512) >> 10));
                    }
                }
            }
        } else if (this.engine.getKyberPolyVecCompressedBytes() != this.kyberK * 352) {
            throw new RuntimeException("Kyber PolyVecCompressedBytes neither 320 * KyberK or 352 * KyberK!");
        } else {
            short[] sArr2 = new short[8];
            int i5 = 0;
            for (int i6 = 0; i6 < this.kyberK; i6++) {
                for (int i7 = 0; i7 < 32; i7++) {
                    byte b4 = bArr[i5 + 1];
                    sArr2[0] = (short) (((bArr[i5] & UByte.MAX_VALUE) >> 0) | (((short) (b4 & UByte.MAX_VALUE)) << 8));
                    byte b5 = bArr[i5 + 2];
                    sArr2[1] = (short) (((b4 & UByte.MAX_VALUE) >> 3) | (((short) (b5 & UByte.MAX_VALUE)) << 5));
                    int i8 = ((b5 & UByte.MAX_VALUE) >> 6) | (((short) (bArr[i5 + 3] & UByte.MAX_VALUE)) << 2);
                    byte b6 = bArr[i5 + 4];
                    sArr2[2] = (short) (i8 | ((short) ((b6 & UByte.MAX_VALUE) << 10)));
                    byte b7 = bArr[i5 + 5];
                    sArr2[3] = (short) (((b6 & UByte.MAX_VALUE) >> 1) | (((short) (b7 & UByte.MAX_VALUE)) << 7));
                    byte b8 = bArr[i5 + 6];
                    sArr2[4] = (short) (((b7 & UByte.MAX_VALUE) >> 4) | (((short) (b8 & UByte.MAX_VALUE)) << 4));
                    int i9 = ((b8 & UByte.MAX_VALUE) >> 7) | (((short) (bArr[i5 + 7] & UByte.MAX_VALUE)) << 1);
                    byte b9 = bArr[i5 + 8];
                    sArr2[5] = (short) (i9 | ((short) ((b9 & UByte.MAX_VALUE) << 9)));
                    byte b10 = bArr[i5 + 9];
                    sArr2[6] = (short) (((b9 & UByte.MAX_VALUE) >> 2) | (((short) (b10 & UByte.MAX_VALUE)) << 6));
                    sArr2[7] = (short) (((b10 & UByte.MAX_VALUE) >> 5) | (((short) (bArr[i5 + 10] & UByte.MAX_VALUE)) << 3));
                    i5 += 11;
                    for (int i10 = 0; i10 < 8; i10++) {
                        this.vec[i6].setCoeffIndex((i7 * 8) + i10, (short) ((((sArr2[i10] & 2047) * KyberEngine.KyberQ) + 1024) >> 11));
                    }
                }
            }
        }
    }

    public void fromBytes(byte[] bArr) {
        int i = 0;
        while (i < this.kyberK) {
            Poly vectorIndex = getVectorIndex(i);
            int i2 = i * KyberEngine.KyberPolyBytes;
            i++;
            vectorIndex.fromBytes(Arrays.copyOfRange(bArr, i2, i * KyberEngine.KyberPolyBytes));
        }
    }

    public Poly getVectorIndex(int i) {
        return this.vec[i];
    }

    public void polyVecInverseNttToMont() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).polyInverseNttToMont();
        }
    }

    public void polyVecNtt() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).polyNtt();
        }
    }

    public void reducePoly() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).reduce();
        }
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[this.polyVecBytes];
        for (int i = 0; i < this.kyberK; i++) {
            System.arraycopy(this.vec[i].toBytes(), 0, bArr, i * KyberEngine.KyberPolyBytes, KyberEngine.KyberPolyBytes);
        }
        return bArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        for (int i = 0; i < this.kyberK; i++) {
            stringBuffer.append(this.vec[i].toString());
            if (i != this.kyberK - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
