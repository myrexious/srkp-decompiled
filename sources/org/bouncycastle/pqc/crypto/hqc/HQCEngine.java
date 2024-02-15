package org.bouncycastle.pqc.crypto.hqc;

import kotlin.UByte;
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class HQCEngine {
    private int K_BYTE;
    private int K_BYTE_64;
    private int N1N2_BYTE;
    private int N1N2_BYTE_64;
    private int N1_BYTE;
    private int N1_BYTE_64;
    private int N_BYTE;
    private int N_BYTE_64;
    private int delta;
    private int fft;
    private GF2mField field;
    private int g;
    private int[] generatorPoly;
    private int k;
    private int mulParam;
    private int n;
    private int n1;
    private int n1n2;
    private int n2;
    private PolynomialGF2mSmallM reductionPoly;
    private int rejectionThreshold;
    private int w;
    private int we;
    private int wr;
    private int SEED_SIZE = 40;
    private byte G_FCT_DOMAIN = 3;
    private byte H_FCT_DOMAIN = 4;
    private byte K_FCT_DOMAIN = 5;
    private int SHA512_BYTES = 64;

    public HQCEngine(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        this.n = i;
        this.k = i4;
        this.delta = i6;
        this.w = i7;
        this.wr = i8;
        this.we = i9;
        this.n1 = i2;
        this.n2 = i3;
        int i12 = i2 * i3;
        this.n1n2 = i12;
        this.generatorPoly = iArr;
        this.g = i5;
        this.rejectionThreshold = i10;
        this.fft = i11;
        this.mulParam = (int) Math.ceil(i3 / 128);
        this.N_BYTE = Utils.getByteSizeFromBitSize(i);
        this.K_BYTE = i4;
        this.N_BYTE_64 = Utils.getByte64SizeFromBitSize(i);
        this.K_BYTE_64 = Utils.getByteSizeFromBitSize(i4);
        this.N1_BYTE_64 = Utils.getByteSizeFromBitSize(i2);
        this.N1N2_BYTE_64 = Utils.getByte64SizeFromBitSize(i12);
        this.N1N2_BYTE = Utils.getByteSizeFromBitSize(i12);
        this.N1_BYTE = Utils.getByteSizeFromBitSize(i2);
        GF2mField gF2mField = new GF2mField(1);
        this.field = gF2mField;
        this.reductionPoly = new PolynomialGF2mSmallM(gF2mField, i).addMonomial(0);
    }

    private void decrypt(long[] jArr, long[] jArr2, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[this.n];
        Utils.fromByteArrayToBitArray(bArr4, bArr);
        byte[] bArr5 = new byte[this.n1n2];
        Utils.fromByteArrayToBitArray(bArr5, bArr2);
        Utils.fromBitArrayToLongArray(new long[this.N_BYTE_64], bArr4);
        Utils.fromBitArrayToLongArray(new long[this.N1N2_BYTE_64], bArr5);
        PolynomialGF2mSmallM add = new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr5)).add(new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr4)).modKaratsubaMultiplyBigDeg(new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr3)), this.reductionPoly));
        long[] jArr3 = new long[this.N_BYTE_64];
        Utils.fromBitArrayToLongArray(jArr3, add.getEncoded());
        long[] jArr4 = new long[this.N1_BYTE_64];
        ReedMuller.decode(jArr4, jArr3, this.n1, this.mulParam);
        ReedSolomon.decode(jArr2, jArr4, this.n1, this.fft, this.delta, this.k, this.g);
        System.arraycopy(jArr2, 0, jArr, 0, jArr.length);
    }

    private void encrypt(long[] jArr, long[] jArr2, long[] jArr3, byte[] bArr, long[] jArr4, byte[] bArr2) {
        HQCKeccakRandomGenerator hQCKeccakRandomGenerator = new HQCKeccakRandomGenerator(256);
        hQCKeccakRandomGenerator.seedExpanderInit(bArr2, this.SEED_SIZE);
        int i = this.N_BYTE_64;
        long[] jArr5 = new long[i];
        long[] jArr6 = new long[i];
        int i2 = this.wr;
        int[] iArr = new int[i2];
        generateSecretKey(jArr6, hQCKeccakRandomGenerator, i2);
        generateSecretKeyByCoordinates(iArr, hQCKeccakRandomGenerator, this.wr);
        generateSecretKey(jArr5, hQCKeccakRandomGenerator, this.we);
        byte[] bArr3 = new byte[this.n];
        Utils.fromLongArrayToBitArray(bArr3, jArr3);
        byte[] bArr4 = new byte[this.n];
        Utils.fromLongArrayToBitArray(bArr4, jArr6);
        int i3 = this.n;
        byte[] bArr5 = new byte[i3];
        byte[] fromListOfPos1ToBitArray = Utils.fromListOfPos1ToBitArray(iArr, i3);
        byte[] bArr6 = new byte[this.n];
        Utils.fromLongArrayToBitArray(bArr6, jArr5);
        byte[] bArr7 = new byte[this.n];
        Utils.fromByteArrayToBitArray(bArr7, bArr);
        PolynomialGF2mSmallM polynomialGF2mSmallM = new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr4));
        PolynomialGF2mSmallM polynomialGF2mSmallM2 = new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(fromListOfPos1ToBitArray));
        Utils.fromBitArrayToLongArray(jArr, polynomialGF2mSmallM.add(polynomialGF2mSmallM2.modKaratsubaMultiplyBigDeg(new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr3)), this.reductionPoly)).getEncoded());
        PolynomialGF2mSmallM polynomialGF2mSmallM3 = new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr7));
        PolynomialGF2mSmallM polynomialGF2mSmallM4 = new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr6));
        long[] jArr7 = new long[this.N1_BYTE_64];
        ReedSolomon.encode(jArr7, jArr4, this.K_BYTE * 8, this.n1, this.k, this.g, this.generatorPoly);
        ReedMuller.encode(jArr2, jArr7, this.n1, this.mulParam);
        byte[] bArr8 = new byte[this.n1n2];
        Utils.fromLongArrayToBitArray(bArr8, jArr2);
        PolynomialGF2mSmallM add = new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr8)).add(polynomialGF2mSmallM3.modKaratsubaMultiplyBigDeg(polynomialGF2mSmallM2, this.reductionPoly)).add(polynomialGF2mSmallM4);
        long[] jArr8 = new long[this.N_BYTE_64];
        Utils.fromBitArrayToLongArray(jArr8, add.getEncoded());
        int i4 = this.n1n2;
        int i5 = this.n;
        int i6 = this.N1N2_BYTE_64;
        Utils.resizeArray(jArr2, i4, jArr8, i5, i6, i6);
    }

    private void extractCiphertexts(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        System.arraycopy(bArr4, 0, bArr, 0, bArr.length);
        System.arraycopy(bArr4, bArr.length, bArr2, 0, bArr2.length);
        System.arraycopy(bArr4, bArr.length + bArr2.length, bArr3, 0, bArr3.length);
    }

    private void extractKeysFromSecretKeys(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = this.SEED_SIZE;
        byte[] bArr4 = new byte[i];
        System.arraycopy(bArr3, 0, bArr4, 0, i);
        HQCKeccakRandomGenerator hQCKeccakRandomGenerator = new HQCKeccakRandomGenerator(256);
        hQCKeccakRandomGenerator.seedExpanderInit(bArr4, i);
        int i2 = this.w;
        int[] iArr = new int[i2];
        generateSecretKey(new long[this.N_BYTE_64], hQCKeccakRandomGenerator, i2);
        generateSecretKeyByCoordinates(iArr, hQCKeccakRandomGenerator, this.w);
        System.arraycopy(Utils.fromListOfPos1ToBitArray(iArr, this.n), 0, bArr, 0, bArr.length);
        System.arraycopy(bArr3, this.SEED_SIZE, bArr2, 0, bArr2.length);
    }

    private void extractPublicKeys(long[] jArr, byte[] bArr, byte[] bArr2) {
        int i = this.SEED_SIZE;
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr2, 0, bArr3, 0, i);
        HQCKeccakRandomGenerator hQCKeccakRandomGenerator = new HQCKeccakRandomGenerator(256);
        hQCKeccakRandomGenerator.seedExpanderInit(bArr3, i);
        long[] jArr2 = new long[this.N_BYTE_64];
        generatePublicKeyH(jArr2, hQCKeccakRandomGenerator);
        System.arraycopy(jArr2, 0, jArr, 0, jArr.length);
        System.arraycopy(bArr2, 40, bArr, 0, bArr.length);
    }

    private void generateSecretKey(long[] jArr, HQCKeccakRandomGenerator hQCKeccakRandomGenerator, int i) {
        int[] iArr = new int[i];
        generateSecretKeyByCoordinates(iArr, hQCKeccakRandomGenerator, i);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            int i4 = i3 / 64;
            jArr[i4] = (1 << (i3 % 64)) | jArr[i4];
        }
    }

    private void generateSecretKeyByCoordinates(int[] iArr, HQCKeccakRandomGenerator hQCKeccakRandomGenerator, int i) {
        int i2;
        int i3;
        int i4 = i * 3;
        byte[] bArr = new byte[this.wr * 3];
        int i5 = i4;
        int i6 = 0;
        while (i6 < i) {
            while (true) {
                if (i5 == i4) {
                    hQCKeccakRandomGenerator.expandSeed(bArr, i4);
                    i5 = 0;
                }
                int i7 = i5 + 1;
                int i8 = (bArr[i5] & UByte.MAX_VALUE) << 16;
                iArr[i6] = i8;
                int i9 = i7 + 1;
                int i10 = i8 | ((bArr[i7] & UByte.MAX_VALUE) << 8);
                iArr[i6] = i10;
                i2 = i9 + 1;
                i3 = i10 | (bArr[i9] & UByte.MAX_VALUE);
                iArr[i6] = i3;
                if (i3 < this.rejectionThreshold) {
                    break;
                }
                i5 = i2;
            }
            iArr[i6] = i3 % this.n;
            int i11 = 1;
            for (int i12 = 0; i12 < i6; i12++) {
                if (iArr[i12] == iArr[i6]) {
                    i11 = 0;
                }
            }
            i6 += i11;
            i5 = i2;
        }
    }

    public void decaps(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4;
        byte[] bArr5 = new byte[this.n];
        byte[] bArr6 = new byte[this.N_BYTE + 40];
        extractKeysFromSecretKeys(bArr5, bArr6, bArr3);
        byte[] bArr7 = new byte[this.N_BYTE];
        byte[] bArr8 = new byte[this.N1N2_BYTE];
        byte[] bArr9 = new byte[this.SHA512_BYTES];
        extractCiphertexts(bArr7, bArr8, bArr9, bArr2);
        long[] jArr = new long[this.K_BYTE_64];
        decrypt(jArr, jArr, bArr7, bArr8, bArr5);
        int i = this.k;
        byte[] bArr10 = new byte[i];
        Utils.fromLongArrayToByteArray(bArr10, jArr, i * 8);
        byte[] bArr11 = new byte[this.SHA512_BYTES];
        HQCKeccakRandomGenerator hQCKeccakRandomGenerator = new HQCKeccakRandomGenerator(256);
        hQCKeccakRandomGenerator.SHAKE256_512_ds(bArr11, bArr10, i, new byte[]{this.G_FCT_DOMAIN});
        long[] jArr2 = new long[this.N_BYTE_64];
        byte[] bArr12 = new byte[this.N_BYTE];
        extractPublicKeys(jArr2, bArr12, bArr6);
        long[] jArr3 = new long[this.N_BYTE_64];
        long[] jArr4 = new long[this.N1N2_BYTE_64];
        encrypt(jArr3, jArr4, jArr2, bArr12, jArr, bArr11);
        byte[] bArr13 = new byte[this.N_BYTE];
        byte[] bArr14 = new byte[this.N1N2_BYTE];
        Utils.fromLongArrayToByteArray(bArr13, jArr3, this.n);
        Utils.fromLongArrayToByteArray(bArr14, jArr4, this.n1n2);
        byte[] bArr15 = new byte[this.SHA512_BYTES];
        hQCKeccakRandomGenerator.SHAKE256_512_ds(bArr15, bArr10, i, new byte[]{this.H_FCT_DOMAIN});
        byte[] bArr16 = new byte[this.K_BYTE + this.N_BYTE + this.N1N2_BYTE];
        byte[] concatenate = Arrays.concatenate(Arrays.concatenate(bArr10, bArr7), bArr8);
        hQCKeccakRandomGenerator.SHAKE256_512_ds(bArr, concatenate, concatenate.length, new byte[]{this.K_FCT_DOMAIN});
        boolean areEqual = Arrays.areEqual(bArr7, bArr13);
        if (Arrays.areEqual(bArr8, bArr14)) {
            bArr4 = bArr9;
        } else {
            bArr4 = bArr9;
            areEqual = false;
        }
        if (!Arrays.areEqual(bArr4, bArr15)) {
            areEqual = false;
        }
        if (areEqual) {
            return;
        }
        for (int i2 = 0; i2 < getSessionKeySize(); i2++) {
            bArr[i2] = 0;
        }
    }

    public void encaps(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        int i = this.K_BYTE;
        byte[] bArr7 = new byte[i];
        HQCKeccakRandomGenerator hQCKeccakRandomGenerator = new HQCKeccakRandomGenerator(256);
        hQCKeccakRandomGenerator.randomGeneratorInit(bArr6, null, bArr6.length, 0);
        hQCKeccakRandomGenerator.squeeze(new byte[this.SEED_SIZE], 40);
        hQCKeccakRandomGenerator.squeeze(new byte[this.SEED_SIZE], 40);
        hQCKeccakRandomGenerator.squeeze(bArr7, this.K_BYTE);
        long[] jArr = new long[this.K_BYTE_64];
        Utils.fromByteArrayToLongArray(jArr, bArr7);
        byte[] bArr8 = new byte[this.SHA512_BYTES];
        HQCKeccakRandomGenerator hQCKeccakRandomGenerator2 = new HQCKeccakRandomGenerator(256);
        hQCKeccakRandomGenerator2.SHAKE256_512_ds(bArr8, bArr7, i, new byte[]{this.G_FCT_DOMAIN});
        long[] jArr2 = new long[this.N_BYTE_64];
        byte[] bArr9 = new byte[this.N_BYTE];
        extractPublicKeys(jArr2, bArr9, bArr5);
        long[] jArr3 = new long[this.N_BYTE_64];
        long[] jArr4 = new long[this.N1N2_BYTE_64];
        encrypt(jArr3, jArr4, jArr2, bArr9, jArr, bArr8);
        Utils.fromLongArrayToByteArray(bArr2, jArr4, this.n1n2);
        Utils.fromLongArrayToByteArray(bArr, jArr3, this.n);
        hQCKeccakRandomGenerator2.SHAKE256_512_ds(bArr4, bArr7, i, new byte[]{this.H_FCT_DOMAIN});
        byte[] bArr10 = new byte[this.K_BYTE + this.N_BYTE + this.N1N2_BYTE];
        byte[] concatenate = Arrays.concatenate(Arrays.concatenate(bArr7, bArr), bArr2);
        hQCKeccakRandomGenerator2.SHAKE256_512_ds(bArr3, concatenate, concatenate.length, new byte[]{this.K_FCT_DOMAIN});
    }

    public void genKeyPair(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = this.SEED_SIZE;
        byte[] bArr4 = new byte[i];
        HQCKeccakRandomGenerator hQCKeccakRandomGenerator = new HQCKeccakRandomGenerator(256);
        hQCKeccakRandomGenerator.randomGeneratorInit(bArr3, null, bArr3.length, 0);
        hQCKeccakRandomGenerator.squeeze(bArr4, 40);
        HQCKeccakRandomGenerator hQCKeccakRandomGenerator2 = new HQCKeccakRandomGenerator(256);
        hQCKeccakRandomGenerator2.seedExpanderInit(bArr4, i);
        long[] jArr = new long[this.N_BYTE_64];
        int i2 = this.w;
        int[] iArr = new int[i2];
        generateSecretKey(jArr, hQCKeccakRandomGenerator2, i2);
        generateSecretKeyByCoordinates(iArr, hQCKeccakRandomGenerator2, this.w);
        byte[] fromListOfPos1ToBitArray = Utils.fromListOfPos1ToBitArray(iArr, this.n);
        byte[] bArr5 = new byte[this.n];
        Utils.fromLongArrayToBitArray(bArr5, jArr);
        int i3 = this.SEED_SIZE;
        byte[] bArr6 = new byte[i3];
        hQCKeccakRandomGenerator.squeeze(bArr6, 40);
        HQCKeccakRandomGenerator hQCKeccakRandomGenerator3 = new HQCKeccakRandomGenerator(256);
        hQCKeccakRandomGenerator3.seedExpanderInit(bArr6, i3);
        long[] jArr2 = new long[this.N_BYTE_64];
        generatePublicKeyH(jArr2, hQCKeccakRandomGenerator3);
        byte[] bArr7 = new byte[this.n];
        Utils.fromLongArrayToBitArray(bArr7, jArr2);
        byte[] encoded = new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr5)).add(new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr7)).modKaratsubaMultiplyBigDeg(new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(fromListOfPos1ToBitArray)), this.reductionPoly)).getEncoded();
        byte[] bArr8 = new byte[this.N_BYTE];
        Utils.fromBitArrayToByteArray(bArr8, encoded);
        byte[] concatenate = Arrays.concatenate(bArr6, bArr8);
        byte[] concatenate2 = Arrays.concatenate(bArr4, concatenate);
        System.arraycopy(concatenate, 0, bArr, 0, concatenate.length);
        System.arraycopy(concatenate2, 0, bArr2, 0, concatenate2.length);
    }

    void generatePublicKeyH(long[] jArr, HQCKeccakRandomGenerator hQCKeccakRandomGenerator) {
        int i = this.N_BYTE;
        byte[] bArr = new byte[i];
        hQCKeccakRandomGenerator.expandSeed(bArr, i);
        long[] jArr2 = new long[this.N_BYTE_64];
        Utils.fromByteArrayToLongArray(jArr2, bArr);
        int i2 = this.N_BYTE_64 - 1;
        jArr2[i2] = jArr2[i2] & Utils.bitMask(this.n, 64L);
        System.arraycopy(jArr2, 0, jArr, 0, jArr.length);
    }

    public int getSessionKeySize() {
        return this.SHA512_BYTES;
    }
}
