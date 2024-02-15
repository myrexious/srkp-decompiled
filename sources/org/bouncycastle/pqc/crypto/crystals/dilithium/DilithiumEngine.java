package org.bouncycastle.pqc.crypto.crystals.dilithium;

import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class DilithiumEngine {
    public static final int CrhBytes = 64;
    public static final int DilithiumD = 13;
    public static final int DilithiumN = 256;
    public static final int DilithiumPolyT0PackedBytes = 416;
    public static final int DilithiumPolyT1PackedBytes = 320;
    public static final int DilithiumQ = 8380417;
    public static final int DilithiumQinv = 58728449;
    public static final int DilithiumRootOfUnity = 1753;
    public static final int SeedBytes = 32;
    private final int CryptoBytes;
    private final int CryptoPublicKeyBytes;
    private final int CryptoSecretKeyBytes;
    private final int DilithiumBeta;
    private final int DilithiumEta;
    private final int DilithiumGamma1;
    private final int DilithiumGamma2;
    private final int DilithiumK;
    private final int DilithiumL;
    private final int DilithiumMode;
    private final int DilithiumOmega;
    private final int DilithiumPolyEtaPackedBytes;
    private final int DilithiumPolyVecHPackedBytes;
    private final int DilithiumPolyW1PackedBytes;
    private final int DilithiumPolyZPackedBytes;
    private final int DilithiumTau;
    private final int PolyUniformGamma1NBlocks;
    private final SecureRandom random;
    private final SHAKEDigest shake128Digest = new SHAKEDigest(128);
    private final SHAKEDigest shake256Digest = new SHAKEDigest(256);
    private final Symmetric symmetric;

    /* JADX WARN: Removed duplicated region for block: B:39:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public DilithiumEngine(int r12, java.security.SecureRandom r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumEngine.<init>(int, java.security.SecureRandom, boolean):void");
    }

    public Symmetric GetSymmetric() {
        return this.symmetric;
    }

    public byte[][] generateKeyPair() {
        byte[] bArr = new byte[32];
        byte[] bArr2 = new byte[128];
        byte[] bArr3 = new byte[32];
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[64];
        byte[] bArr6 = new byte[32];
        PolyVecMatrix polyVecMatrix = new PolyVecMatrix(this);
        PolyVecL polyVecL = new PolyVecL(this);
        PolyVecK polyVecK = new PolyVecK(this);
        PolyVecK polyVecK2 = new PolyVecK(this);
        PolyVecK polyVecK3 = new PolyVecK(this);
        this.random.nextBytes(bArr);
        this.shake256Digest.update(bArr, 0, 32);
        this.shake256Digest.doFinal(bArr2, 0, 128);
        System.arraycopy(bArr2, 0, bArr4, 0, 32);
        System.arraycopy(bArr2, 32, bArr5, 0, 64);
        System.arraycopy(bArr2, 96, bArr6, 0, 32);
        polyVecMatrix.expandMatrix(bArr4);
        polyVecL.uniformEta(bArr5, (short) 0);
        polyVecK.uniformEta(bArr5, (short) this.DilithiumL);
        PolyVecL polyVecL2 = new PolyVecL(this);
        polyVecL.copyPolyVecL(polyVecL2);
        polyVecL2.polyVecNtt();
        polyVecMatrix.pointwiseMontgomery(polyVecK2, polyVecL2);
        polyVecK2.reduce();
        polyVecK2.invNttToMont();
        polyVecK2.addPolyVecK(polyVecK);
        polyVecK2.conditionalAddQ();
        polyVecK2.power2Round(polyVecK3);
        byte[] packPublicKey = Packing.packPublicKey(polyVecK2, this);
        this.shake256Digest.update(bArr4, 0, 32);
        this.shake256Digest.update(packPublicKey, 0, packPublicKey.length);
        this.shake256Digest.doFinal(bArr3, 0, 32);
        byte[][] packSecretKey = Packing.packSecretKey(bArr4, bArr3, bArr6, polyVecK3, polyVecL, polyVecK, this);
        return new byte[][]{packSecretKey[0], packSecretKey[1], packSecretKey[2], packSecretKey[3], packSecretKey[4], packSecretKey[5], packPublicKey};
    }

    public int getCryptoBytes() {
        return this.CryptoBytes;
    }

    public int getCryptoPublicKeyBytes() {
        return this.CryptoPublicKeyBytes;
    }

    public int getCryptoSecretKeyBytes() {
        return this.CryptoSecretKeyBytes;
    }

    public int getDilithiumBeta() {
        return this.DilithiumBeta;
    }

    public int getDilithiumEta() {
        return this.DilithiumEta;
    }

    public int getDilithiumGamma1() {
        return this.DilithiumGamma1;
    }

    public int getDilithiumGamma2() {
        return this.DilithiumGamma2;
    }

    public int getDilithiumK() {
        return this.DilithiumK;
    }

    public int getDilithiumL() {
        return this.DilithiumL;
    }

    public int getDilithiumMode() {
        return this.DilithiumMode;
    }

    public int getDilithiumOmega() {
        return this.DilithiumOmega;
    }

    public int getDilithiumPolyEtaPackedBytes() {
        return this.DilithiumPolyEtaPackedBytes;
    }

    public int getDilithiumPolyVecHPackedBytes() {
        return this.DilithiumPolyVecHPackedBytes;
    }

    public int getDilithiumPolyW1PackedBytes() {
        return this.DilithiumPolyW1PackedBytes;
    }

    public int getDilithiumPolyZPackedBytes() {
        return this.DilithiumPolyZPackedBytes;
    }

    public int getDilithiumTau() {
        return this.DilithiumTau;
    }

    public int getPolyUniformGamma1NBlocks() {
        return this.PolyUniformGamma1NBlocks;
    }

    public SHAKEDigest getShake128Digest() {
        return this.shake128Digest;
    }

    public SHAKEDigest getShake256Digest() {
        return this.shake256Digest;
    }

    public byte[] sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7) {
        return signSignature(bArr, i, bArr2, bArr3, bArr4, bArr5, bArr6, bArr7);
    }

    public boolean signOpen(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        return signVerify(bArr2, i, bArr, bArr.length, bArr3, bArr4);
    }

    public byte[] signSignature(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7) {
        PolyVecK polyVecK;
        PolyVecK polyVecK2;
        int i2;
        byte[] bArr8 = new byte[this.CryptoBytes + i];
        byte[] bArr9 = new byte[64];
        byte[] bArr10 = new byte[64];
        PolyVecL polyVecL = new PolyVecL(this);
        PolyVecL polyVecL2 = new PolyVecL(this);
        PolyVecL polyVecL3 = new PolyVecL(this);
        PolyVecK polyVecK3 = new PolyVecK(this);
        PolyVecK polyVecK4 = new PolyVecK(this);
        PolyVecK polyVecK5 = new PolyVecK(this);
        PolyVecK polyVecK6 = new PolyVecK(this);
        PolyVecK polyVecK7 = new PolyVecK(this);
        Poly poly = new Poly(this);
        PolyVecMatrix polyVecMatrix = new PolyVecMatrix(this);
        Poly poly2 = poly;
        PolyVecK polyVecK8 = polyVecK7;
        PolyVecK polyVecK9 = polyVecK6;
        PolyVecK polyVecK10 = polyVecK5;
        PolyVecK polyVecK11 = polyVecK4;
        PolyVecK polyVecK12 = polyVecK3;
        Packing.unpackSecretKey(polyVecK3, polyVecL, polyVecK4, bArr5, bArr6, bArr7, this);
        int i3 = 0;
        this.shake256Digest.update(bArr4, 0, 32);
        this.shake256Digest.update(bArr, 0, i);
        this.shake256Digest.doFinal(bArr9, 0, 64);
        SecureRandom secureRandom = this.random;
        if (secureRandom != null) {
            secureRandom.nextBytes(bArr10);
        } else {
            byte[] copyOf = Arrays.copyOf(bArr3, 96);
            System.arraycopy(bArr9, 0, copyOf, 32, 64);
            this.shake256Digest.update(copyOf, 0, 96);
            this.shake256Digest.doFinal(bArr10, 0, 64);
        }
        polyVecMatrix.expandMatrix(bArr2);
        polyVecL.polyVecNtt();
        polyVecK11.polyVecNtt();
        polyVecK12.polyVecNtt();
        int i4 = 0;
        short s = 0;
        while (i4 < 1000) {
            int i5 = i4 + 1;
            short s2 = (short) (s + 1);
            polyVecL2.uniformGamma1(bArr10, s);
            polyVecL2.copyPolyVecL(polyVecL3);
            polyVecL3.polyVecNtt();
            PolyVecK polyVecK13 = polyVecK10;
            polyVecMatrix.pointwiseMontgomery(polyVecK13, polyVecL3);
            polyVecK13.reduce();
            polyVecK13.invNttToMont();
            polyVecK13.conditionalAddQ();
            PolyVecK polyVecK14 = polyVecK9;
            polyVecK13.decompose(polyVecK14);
            System.arraycopy(polyVecK13.packW1(), i3, bArr8, i3, this.DilithiumK * this.DilithiumPolyW1PackedBytes);
            this.shake256Digest.update(bArr9, i3, 64);
            this.shake256Digest.update(bArr8, i3, this.DilithiumK * this.DilithiumPolyW1PackedBytes);
            this.shake256Digest.doFinal(bArr8, i3, 32);
            Poly poly3 = poly2;
            poly3.challenge(Arrays.copyOfRange(bArr8, i3, 32));
            poly3.polyNtt();
            polyVecL3.pointwisePolyMontgomery(poly3, polyVecL);
            polyVecL3.invNttToMont();
            polyVecL3.addPolyVecL(polyVecL2);
            polyVecL3.reduce();
            if (polyVecL3.checkNorm(this.DilithiumGamma1 - this.DilithiumBeta)) {
                polyVecK = polyVecK11;
                i2 = i5;
                polyVecK2 = polyVecK8;
            } else {
                polyVecK = polyVecK11;
                polyVecK2 = polyVecK8;
                polyVecK2.pointwisePolyMontgomery(poly3, polyVecK);
                polyVecK2.invNttToMont();
                polyVecK14.subtract(polyVecK2);
                polyVecK14.reduce();
                i2 = i5;
                if (polyVecK14.checkNorm(this.DilithiumGamma2 - this.DilithiumBeta)) {
                    continue;
                } else {
                    PolyVecK polyVecK15 = polyVecK12;
                    polyVecK2.pointwisePolyMontgomery(poly3, polyVecK15);
                    polyVecK2.invNttToMont();
                    polyVecK2.reduce();
                    if (polyVecK2.checkNorm(this.DilithiumGamma2)) {
                        polyVecK12 = polyVecK15;
                    } else {
                        polyVecK14.addPolyVecK(polyVecK2);
                        polyVecK14.conditionalAddQ();
                        polyVecK12 = polyVecK15;
                        if (polyVecK2.makeHint(polyVecK14, polyVecK13) <= this.DilithiumOmega) {
                            return Packing.packSignature(bArr8, polyVecL3, polyVecK2, this);
                        }
                    }
                }
            }
            i4 = i2;
            polyVecK11 = polyVecK;
            polyVecK8 = polyVecK2;
            polyVecK10 = polyVecK13;
            s = s2;
            polyVecK9 = polyVecK14;
            poly2 = poly3;
            i3 = 0;
        }
        return null;
    }

    public boolean signVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = new byte[64];
        byte[] bArr6 = new byte[32];
        Poly poly = new Poly(this);
        PolyVecMatrix polyVecMatrix = new PolyVecMatrix(this);
        PolyVecL polyVecL = new PolyVecL(this);
        PolyVecK polyVecK = new PolyVecK(this);
        PolyVecK polyVecK2 = new PolyVecK(this);
        PolyVecK polyVecK3 = new PolyVecK(this);
        if (i != this.CryptoBytes) {
            return false;
        }
        PolyVecK unpackPublicKey = Packing.unpackPublicKey(polyVecK, bArr4, this);
        if (Packing.unpackSignature(polyVecL, polyVecK3, bArr, this)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 32);
            if (polyVecL.checkNorm(getDilithiumGamma1() - getDilithiumBeta())) {
                return false;
            }
            this.shake256Digest.update(bArr3, 0, bArr3.length);
            this.shake256Digest.update(bArr4, 0, bArr4.length);
            this.shake256Digest.doFinal(bArr5, 0, 32);
            this.shake256Digest.update(bArr5, 0, 32);
            this.shake256Digest.update(bArr2, 0, i2);
            this.shake256Digest.doFinal(bArr5, 0);
            poly.challenge(copyOfRange);
            polyVecMatrix.expandMatrix(bArr3);
            polyVecL.polyVecNtt();
            polyVecMatrix.pointwiseMontgomery(polyVecK2, polyVecL);
            poly.polyNtt();
            unpackPublicKey.shiftLeft();
            unpackPublicKey.polyVecNtt();
            unpackPublicKey.pointwisePolyMontgomery(poly, unpackPublicKey);
            polyVecK2.subtract(unpackPublicKey);
            polyVecK2.reduce();
            polyVecK2.invNttToMont();
            polyVecK2.conditionalAddQ();
            polyVecK2.useHint(polyVecK2, polyVecK3);
            byte[] packW1 = polyVecK2.packW1();
            SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
            sHAKEDigest.update(bArr5, 0, 64);
            sHAKEDigest.update(packW1, 0, this.DilithiumK * this.DilithiumPolyW1PackedBytes);
            sHAKEDigest.doFinal(bArr6, 0, 32);
            for (int i3 = 0; i3 < 32; i3++) {
                if (copyOfRange[i3] != bArr6[i3]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
