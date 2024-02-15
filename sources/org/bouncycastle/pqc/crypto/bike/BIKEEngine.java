package org.bouncycastle.pqc.crypto.bike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine;
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class BIKEEngine {
    private int L_BYTE;
    private int R_BYTE;
    private GF2mField field;
    private int hw;
    private int l;
    private int nbIter;
    private int r;
    private final PolynomialGF2mSmallM reductionPoly;
    private int t;
    private int tau;
    private int w;

    public BIKEEngine(int i, int i2, int i3, int i4, int i5, int i6) {
        this.r = i;
        this.w = i2;
        this.t = i3;
        this.l = i4;
        this.nbIter = i5;
        this.tau = i6;
        this.hw = i2 / 2;
        this.L_BYTE = i4 / 8;
        this.R_BYTE = (i + 7) / 8;
        GF2mField gF2mField = new GF2mField(1);
        this.field = gF2mField;
        this.reductionPoly = new PolynomialGF2mSmallM(gF2mField, i).addMonomial(0);
    }

    private void BFIter(byte[] bArr, byte[] bArr2, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, byte[] bArr3, byte[] bArr4) {
        int[] iArr5 = new int[this.r * 2];
        for (int i2 = 0; i2 < this.r; i2++) {
            if (ctr(iArr3, bArr, i2) >= i) {
                updateNewErrorIndex(bArr2, i2);
                iArr5[i2] = 1;
                bArr3[i2] = 1;
            } else if (ctr(iArr3, bArr, i2) >= i - this.tau) {
                bArr4[i2] = 1;
            }
        }
        for (int i3 = 0; i3 < this.r; i3++) {
            if (ctr(iArr4, bArr, i3) >= i) {
                updateNewErrorIndex(bArr2, this.r + i3);
                int i4 = this.r;
                iArr5[i4 + i3] = 1;
                bArr3[i4 + i3] = 1;
            } else if (ctr(iArr4, bArr, i3) >= i - this.tau) {
                bArr4[this.r + i3] = 1;
            }
        }
        for (int i5 = 0; i5 < this.r * 2; i5++) {
            if (iArr5[i5] == 1) {
                recomputeSyndrome(bArr, i5, iArr, iArr2);
            }
        }
    }

    private void BFMaskedIter(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int[] iArr5 = new int[this.r * 2];
        for (int i2 = 0; i2 < this.r; i2++) {
            if (ctr(iArr3, bArr, i2) >= i && bArr3[i2] == 1) {
                updateNewErrorIndex(bArr2, i2);
                iArr5[i2] = 1;
            }
        }
        for (int i3 = 0; i3 < this.r; i3++) {
            if (ctr(iArr4, bArr, i3) >= i) {
                int i4 = this.r;
                if (bArr3[i4 + i3] == 1) {
                    updateNewErrorIndex(bArr2, i4 + i3);
                    iArr5[this.r + i3] = 1;
                }
            }
        }
        for (int i5 = 0; i5 < this.r * 2; i5++) {
            if (iArr5[i5] == 1) {
                recomputeSyndrome(bArr, i5, iArr, iArr2);
            }
        }
    }

    private byte[] BGFDecoder(byte[] bArr, int[] iArr, int[] iArr2) {
        byte[] bArr2 = new byte[this.r * 2];
        int[] columnFromCompactVersion = getColumnFromCompactVersion(iArr);
        int[] columnFromCompactVersion2 = getColumnFromCompactVersion(iArr2);
        int i = 1;
        int i2 = 1;
        while (i2 <= this.nbIter) {
            int i3 = this.r;
            byte[] bArr3 = new byte[i3 * 2];
            byte[] bArr4 = new byte[i3 * 2];
            int i4 = i2;
            int i5 = i;
            BFIter(bArr, bArr2, threshold(Utils.getHammingWeight(bArr), i2, this.r), iArr, iArr2, columnFromCompactVersion, columnFromCompactVersion2, bArr3, bArr4);
            if (i4 == i5) {
                BFMaskedIter(bArr, bArr2, bArr3, ((this.hw + i5) / 2) + 1, iArr, iArr2, columnFromCompactVersion, columnFromCompactVersion2);
                BFMaskedIter(bArr, bArr2, bArr4, ((this.hw + i5) / 2) + 1, iArr, iArr2, columnFromCompactVersion, columnFromCompactVersion2);
            }
            i2 = i4 + 1;
            i = i5;
        }
        if (Utils.getHammingWeight(bArr) == 0) {
            return bArr2;
        }
        return null;
    }

    private byte[] computeSyndrome(byte[] bArr, byte[] bArr2) {
        return transpose(new PolynomialGF2mSmallM(this.field, bArr2).modKaratsubaMultiplyBigDeg(new PolynomialGF2mSmallM(this.field, bArr), this.reductionPoly).getEncoded());
    }

    private void convertToCompact(int[] iArr, byte[] bArr) {
        int i;
        int i2 = 0;
        for (int i3 = 0; i3 < this.R_BYTE; i3++) {
            for (int i4 = 0; i4 < 8 && (i = (i3 * 8) + i4) != this.r; i4++) {
                if (((bArr[i3] >> i4) & 1) == 1) {
                    iArr[i2] = i;
                    i2++;
                }
            }
        }
    }

    private int ctr(int[] iArr, byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.hw; i3++) {
            if (bArr[(iArr[i3] + i) % this.r] == 1) {
                i2++;
            }
        }
        return i2;
    }

    private byte[] functionH(byte[] bArr) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr, 0, bArr.length);
        return BIKERandomGenerator.generateRandomByteArray(this.r * 2, this.R_BYTE * 2, this.t, sHAKEDigest);
    }

    private byte[] functionK(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[48];
        byte[] bArr5 = new byte[this.L_BYTE];
        SHA3Digest sHA3Digest = new SHA3Digest((int) KyberEngine.KyberPolyBytes);
        sHA3Digest.update(bArr, 0, bArr.length);
        sHA3Digest.update(bArr2, 0, bArr2.length);
        sHA3Digest.update(bArr3, 0, bArr3.length);
        sHA3Digest.doFinal(bArr4, 0);
        System.arraycopy(bArr4, 0, bArr5, 0, this.L_BYTE);
        return bArr5;
    }

    private byte[] functionL(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[48];
        byte[] bArr4 = new byte[this.L_BYTE];
        SHA3Digest sHA3Digest = new SHA3Digest((int) KyberEngine.KyberPolyBytes);
        sHA3Digest.update(bArr, 0, bArr.length);
        sHA3Digest.update(bArr2, 0, bArr2.length);
        sHA3Digest.doFinal(bArr3, 0);
        System.arraycopy(bArr3, 0, bArr4, 0, this.L_BYTE);
        return bArr4;
    }

    private int[] getColumnFromCompactVersion(int[] iArr) {
        int[] iArr2 = new int[this.hw];
        int i = 0;
        if (iArr[0] != 0) {
            while (true) {
                int i2 = this.hw;
                if (i >= i2) {
                    break;
                }
                iArr2[i] = this.r - iArr[(i2 - 1) - i];
                i++;
            }
        } else {
            iArr2[0] = 0;
            int i3 = 1;
            while (true) {
                int i4 = this.hw;
                if (i3 >= i4) {
                    break;
                }
                iArr2[i3] = this.r - iArr[i4 - i3];
                i3++;
            }
        }
        return iArr2;
    }

    private void recomputeSyndrome(byte[] bArr, int i, int[] iArr, int[] iArr2) {
        int i2 = 0;
        if (i < this.r) {
            while (i2 < this.hw) {
                int i3 = iArr[i2];
                if (i3 <= i) {
                    int i4 = i - i3;
                    bArr[i4] = (byte) (bArr[i4] ^ 1);
                } else {
                    int i5 = (this.r + i) - i3;
                    bArr[i5] = (byte) (bArr[i5] ^ 1);
                }
                i2++;
            }
            return;
        }
        while (i2 < this.hw) {
            int i6 = iArr2[i2];
            int i7 = this.r;
            if (i6 <= i - i7) {
                int i8 = (i - i7) - i6;
                bArr[i8] = (byte) (bArr[i8] ^ 1);
            } else {
                int i9 = (i7 - i6) + (i - i7);
                bArr[i9] = (byte) (bArr[i9] ^ 1);
            }
            i2++;
        }
    }

    private int threshold(int i, int i2, int i3) {
        int i4;
        if (i3 == 12323) {
            int floor = (int) Math.floor((i * 0.0069722d) + 13.53d);
            i4 = 36;
            if (floor > 36) {
                return floor;
            }
        } else if (i3 == 24659) {
            int floor2 = (int) Math.floor((i * 0.005265d) + 15.2588d);
            i4 = 52;
            if (floor2 > 52) {
                return floor2;
            }
        } else if (i3 != 40973) {
            return 0;
        } else {
            int floor3 = (int) Math.floor((i * 0.00402312d) + 17.8785d);
            i4 = 69;
            if (floor3 > 69) {
                return floor3;
            }
        }
        return i4;
    }

    private byte[] transpose(byte[] bArr) {
        byte[] append0s = Utils.append0s(bArr, this.r);
        byte[] bArr2 = new byte[this.r];
        bArr2[0] = append0s[0];
        int i = 1;
        while (true) {
            int i2 = this.r;
            if (i >= i2) {
                return bArr2;
            }
            bArr2[i] = append0s[i2 - i];
            i++;
        }
    }

    private void updateNewErrorIndex(byte[] bArr, int i) {
        int i2;
        if (i != 0 && i != (i2 = this.r)) {
            i = i > i2 ? ((i2 * 2) - i) + i2 : i2 - i;
        }
        bArr[i] = (byte) (bArr[i] ^ 1);
    }

    public void decaps(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        int i = this.r;
        byte[] bArr7 = new byte[i];
        byte[] bArr8 = new byte[i];
        Utils.fromByteArrayToBitArray(bArr7, bArr5);
        Utils.fromByteArrayToBitArray(bArr8, bArr2);
        Utils.fromByteArrayToBitArray(new byte[this.l], bArr4);
        byte[] removeLast0Bits = Utils.removeLast0Bits(bArr7);
        byte[] removeLast0Bits2 = Utils.removeLast0Bits(bArr8);
        int i2 = this.hw;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        convertToCompact(iArr, bArr2);
        convertToCompact(iArr2, bArr3);
        byte[] BGFDecoder = BGFDecoder(computeSyndrome(removeLast0Bits, removeLast0Bits2), iArr, iArr2);
        byte[] bArr9 = new byte[this.R_BYTE * 2];
        Utils.fromBitArrayToByteArray(bArr9, BGFDecoder);
        byte[] copyOfRange = Arrays.copyOfRange(BGFDecoder, 0, this.r);
        byte[] copyOfRange2 = Arrays.copyOfRange(BGFDecoder, this.r, BGFDecoder.length);
        byte[] bArr10 = new byte[this.R_BYTE];
        Utils.fromBitArrayToByteArray(bArr10, copyOfRange);
        byte[] bArr11 = new byte[this.R_BYTE];
        Utils.fromBitArrayToByteArray(bArr11, copyOfRange2);
        byte[] xorBytes = Utils.xorBytes(bArr6, functionL(bArr10, bArr11), this.L_BYTE);
        byte[] bArr12 = new byte[this.l];
        byte[] functionK = Arrays.areEqual(bArr9, functionH(xorBytes)) ? functionK(xorBytes, bArr5, bArr6) : functionK(bArr4, bArr5, bArr6);
        System.arraycopy(functionK, 0, bArr, 0, functionK.length);
    }

    public void encaps(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, SecureRandom secureRandom) {
        byte[] bArr5 = new byte[64];
        secureRandom.nextBytes(bArr5);
        int i = this.L_BYTE;
        byte[] bArr6 = new byte[i];
        System.arraycopy(bArr5, 0, bArr6, 0, i);
        byte[] functionH = functionH(bArr6);
        int i2 = this.r * 2;
        byte[] bArr7 = new byte[i2];
        Utils.fromByteArrayToBitArray(bArr7, functionH);
        byte[] copyOfRange = Arrays.copyOfRange(bArr7, 0, this.r);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr7, this.r, i2);
        byte[] removeLast0Bits = Utils.removeLast0Bits(copyOfRange);
        byte[] removeLast0Bits2 = Utils.removeLast0Bits(copyOfRange2);
        PolynomialGF2mSmallM polynomialGF2mSmallM = new PolynomialGF2mSmallM(this.field, removeLast0Bits);
        PolynomialGF2mSmallM polynomialGF2mSmallM2 = new PolynomialGF2mSmallM(this.field, removeLast0Bits2);
        byte[] bArr8 = new byte[this.r];
        Utils.fromByteArrayToBitArray(bArr8, bArr4);
        byte[] encoded = polynomialGF2mSmallM.add(polynomialGF2mSmallM2.modKaratsubaMultiplyBigDeg(new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr8)), this.reductionPoly)).getEncoded();
        byte[] bArr9 = new byte[this.R_BYTE];
        Utils.fromBitArrayToByteArray(bArr9, encoded);
        System.arraycopy(bArr9, 0, bArr, 0, bArr.length);
        byte[] bArr10 = new byte[this.R_BYTE];
        Utils.fromBitArrayToByteArray(bArr10, copyOfRange);
        byte[] bArr11 = new byte[this.R_BYTE];
        Utils.fromBitArrayToByteArray(bArr11, copyOfRange2);
        System.arraycopy(Utils.xorBytes(bArr6, functionL(bArr10, bArr11), this.L_BYTE), 0, bArr2, 0, bArr2.length);
        byte[] functionK = functionK(bArr6, bArr, bArr2);
        System.arraycopy(functionK, 0, bArr3, 0, functionK.length);
    }

    public void genKeyPair(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, SecureRandom secureRandom) {
        byte[] bArr5 = new byte[64];
        secureRandom.nextBytes(bArr5);
        int i = this.L_BYTE;
        byte[] bArr6 = new byte[i];
        byte[] bArr7 = new byte[i];
        System.arraycopy(bArr5, 0, bArr6, 0, i);
        System.arraycopy(bArr5, i, bArr7, 0, i);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr6, 0, i);
        byte[] generateRandomByteArray = BIKERandomGenerator.generateRandomByteArray(this.r, this.R_BYTE, this.hw, sHAKEDigest);
        byte[] generateRandomByteArray2 = BIKERandomGenerator.generateRandomByteArray(this.r, this.R_BYTE, this.hw, sHAKEDigest);
        System.arraycopy(generateRandomByteArray, 0, bArr, 0, bArr.length);
        System.arraycopy(generateRandomByteArray2, 0, bArr2, 0, bArr2.length);
        int i2 = this.r;
        byte[] bArr8 = new byte[i2];
        byte[] bArr9 = new byte[i2];
        Utils.fromByteArrayToBitArray(bArr9, generateRandomByteArray);
        Utils.fromByteArrayToBitArray(bArr8, generateRandomByteArray2);
        byte[] removeLast0Bits = Utils.removeLast0Bits(bArr9);
        byte[] encoded = new PolynomialGF2mSmallM(this.field, Utils.removeLast0Bits(bArr8)).modKaratsubaMultiplyBigDeg(new PolynomialGF2mSmallM(this.field, removeLast0Bits).modInverseBigDeg(this.reductionPoly), this.reductionPoly).getEncoded();
        byte[] bArr10 = new byte[this.R_BYTE];
        Utils.fromBitArrayToByteArray(bArr10, encoded);
        System.arraycopy(bArr10, 0, bArr4, 0, bArr4.length);
        System.arraycopy(bArr7, 0, bArr3, 0, bArr3.length);
    }

    public int getSessionKeySize() {
        return this.L_BYTE;
    }
}
