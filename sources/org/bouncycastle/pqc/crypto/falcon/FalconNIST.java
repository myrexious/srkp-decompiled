package org.bouncycastle.pqc.crypto.falcon;

import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
class FalconNIST {
    int CRYPTO_BYTES;
    private int CRYPTO_PUBLICKEYBYTES;
    private int CRYPTO_SECRETKEYBYTES;
    int LOGN;
    private int N;
    int NONCELEN;
    private FalconCodec codec = new FalconCodec();
    private SecureRandom rand;

    public FalconNIST(int i, int i2, SecureRandom secureRandom) {
        int i3;
        int i4;
        this.rand = secureRandom;
        this.LOGN = i;
        this.NONCELEN = i2;
        int i5 = 1 << i;
        this.N = i5;
        this.CRYPTO_PUBLICKEYBYTES = ((i5 * 14) / 8) + 1;
        if (i == 10) {
            this.CRYPTO_SECRETKEYBYTES = 2305;
            this.CRYPTO_BYTES = 1330;
            return;
        }
        if (i == 9 || i == 8) {
            i3 = i5 * 6;
        } else if (i != 7 && i != 6) {
            i4 = i5 * 2;
            this.CRYPTO_SECRETKEYBYTES = i4 + 1 + i5;
            this.CRYPTO_BYTES = 690;
        } else {
            i3 = i5 * 7;
        }
        i4 = (i3 * 2) / 8;
        this.CRYPTO_SECRETKEYBYTES = i4 + 1 + i5;
        this.CRYPTO_BYTES = 690;
    }

    public byte[] crypto_sign(byte[] bArr, byte[] bArr2, int i, int i2, byte[] bArr3, int i3) {
        int i4 = this.N;
        byte[] bArr4 = new byte[i4];
        byte[] bArr5 = new byte[i4];
        byte[] bArr6 = new byte[i4];
        byte[] bArr7 = new byte[i4];
        short[] sArr = new short[i4];
        short[] sArr2 = new short[i4];
        byte[] bArr8 = new byte[48];
        int i5 = this.NONCELEN;
        byte[] bArr9 = new byte[i5];
        int i6 = (this.CRYPTO_BYTES - 2) - i5;
        byte[] bArr10 = new byte[i6];
        SHAKE256 shake256 = new SHAKE256();
        FalconSign falconSign = new FalconSign();
        FalconVrfy falconVrfy = new FalconVrfy();
        FalconCommon falconCommon = new FalconCommon();
        FalconCodec falconCodec = this.codec;
        int trim_i8_decode = falconCodec.trim_i8_decode(bArr4, 0, this.LOGN, falconCodec.max_fg_bits[this.LOGN], bArr3, i3 + 0, this.CRYPTO_SECRETKEYBYTES + 0);
        if (trim_i8_decode != 0) {
            int i7 = trim_i8_decode + 0;
            FalconCodec falconCodec2 = this.codec;
            int trim_i8_decode2 = falconCodec2.trim_i8_decode(bArr5, 0, this.LOGN, falconCodec2.max_fg_bits[this.LOGN], bArr3, i3 + i7, this.CRYPTO_SECRETKEYBYTES - i7);
            if (trim_i8_decode2 != 0) {
                int i8 = i7 + trim_i8_decode2;
                FalconCodec falconCodec3 = this.codec;
                int trim_i8_decode3 = falconCodec3.trim_i8_decode(bArr6, 0, this.LOGN, falconCodec3.max_FG_bits[this.LOGN], bArr3, i3 + i8, this.CRYPTO_SECRETKEYBYTES - i8);
                if (trim_i8_decode3 != 0) {
                    if (i8 + trim_i8_decode3 == this.CRYPTO_SECRETKEYBYTES - 1) {
                        if (falconVrfy.complete_private(bArr7, 0, bArr4, 0, bArr5, 0, bArr6, 0, this.LOGN, new short[this.N * 2], 0) != 0) {
                            this.rand.nextBytes(bArr9);
                            shake256.inner_shake256_init();
                            shake256.inner_shake256_inject(bArr9, 0, this.NONCELEN);
                            shake256.inner_shake256_inject(bArr2, i, i2);
                            shake256.i_shake256_flip();
                            falconCommon.hash_to_point_vartime(shake256, sArr2, 0, this.LOGN);
                            this.rand.nextBytes(bArr8);
                            shake256.inner_shake256_init();
                            shake256.inner_shake256_inject(bArr8, 0, 48);
                            shake256.i_shake256_flip();
                            falconSign.sign_dyn(sArr, 0, shake256, bArr4, 0, bArr5, 0, bArr6, 0, bArr7, 0, sArr2, 0, this.LOGN, new FalconFPR[this.N * 10], 0);
                            int i9 = this.LOGN;
                            bArr10[0] = (byte) (i9 + 32);
                            int comp_encode = this.codec.comp_encode(bArr10, 1, i6 - 1, sArr, 0, i9);
                            if (comp_encode != 0) {
                                int i10 = comp_encode + 1;
                                bArr[0] = (byte) (this.LOGN + 48);
                                System.arraycopy(bArr9, 0, bArr, 1, this.NONCELEN);
                                System.arraycopy(bArr10, 0, bArr, this.NONCELEN + 1, i10);
                                return Arrays.copyOfRange(bArr, 0, this.NONCELEN + 1 + i10);
                            }
                            throw new IllegalStateException("signature failed to generate");
                        }
                        throw new IllegalStateException("complete_private failed");
                    }
                    throw new IllegalStateException("full key not used");
                }
                throw new IllegalArgumentException("F decode failed");
            }
            throw new IllegalStateException("g decode failed");
        }
        throw new IllegalStateException("f decode failed");
    }

    public byte[][] crypto_sign_keypair(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = this.N;
        byte[] bArr3 = new byte[i3];
        byte[] bArr4 = new byte[i3];
        byte[] bArr5 = new byte[i3];
        short[] sArr = new short[i3];
        byte[] bArr6 = new byte[48];
        SHAKE256 shake256 = new SHAKE256();
        FalconKeyGen falconKeyGen = new FalconKeyGen();
        this.rand.nextBytes(bArr6);
        shake256.inner_shake256_init();
        shake256.inner_shake256_inject(bArr6, 0, 48);
        shake256.i_shake256_flip();
        falconKeyGen.keygen(shake256, bArr3, 0, bArr4, 0, bArr5, 0, null, 0, sArr, 0, this.LOGN);
        int i4 = this.LOGN;
        bArr2[i2 + 0] = (byte) (i4 + 80);
        FalconCodec falconCodec = this.codec;
        int i5 = i2 + 1;
        int trim_i8_encode = falconCodec.trim_i8_encode(bArr2, i5, this.CRYPTO_SECRETKEYBYTES - 1, bArr3, 0, i4, falconCodec.max_fg_bits[this.LOGN]);
        if (trim_i8_encode != 0) {
            int i6 = trim_i8_encode + 1;
            byte[] copyOfRange = Arrays.copyOfRange(bArr2, i5, i6);
            FalconCodec falconCodec2 = this.codec;
            int i7 = i2 + i6;
            int trim_i8_encode2 = falconCodec2.trim_i8_encode(bArr2, i7, this.CRYPTO_SECRETKEYBYTES - i6, bArr4, 0, this.LOGN, falconCodec2.max_fg_bits[this.LOGN]);
            if (trim_i8_encode2 != 0) {
                int i8 = i6 + trim_i8_encode2;
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr2, i7, i8);
                FalconCodec falconCodec3 = this.codec;
                int i9 = i2 + i8;
                int trim_i8_encode3 = falconCodec3.trim_i8_encode(bArr2, i9, this.CRYPTO_SECRETKEYBYTES - i8, bArr5, 0, this.LOGN, falconCodec3.max_FG_bits[this.LOGN]);
                if (trim_i8_encode3 != 0) {
                    int i10 = i8 + trim_i8_encode3;
                    byte[] copyOfRange3 = Arrays.copyOfRange(bArr2, i9, i10);
                    if (i10 == this.CRYPTO_SECRETKEYBYTES) {
                        int i11 = this.LOGN;
                        bArr[i + 0] = (byte) (i11 + 0);
                        if (this.codec.modq_encode(bArr, i + 1, this.CRYPTO_PUBLICKEYBYTES - 1, sArr, 0, i11) == this.CRYPTO_PUBLICKEYBYTES - 1) {
                            return new byte[][]{Arrays.copyOfRange(bArr, 1, bArr.length), copyOfRange, copyOfRange2, copyOfRange3};
                        }
                        throw new IllegalStateException("public key encoding failed");
                    }
                    throw new IllegalStateException("secret key encoding failed");
                }
                throw new IllegalStateException("F encode failed");
            }
            throw new IllegalStateException("g encode failed");
        }
        throw new IllegalStateException("f encode failed");
    }

    public int crypto_sign_open(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i) {
        int i2;
        int i3 = this.N;
        short[] sArr = new short[i3];
        short[] sArr2 = new short[i3];
        short[] sArr3 = new short[i3];
        SHAKE256 shake256 = new SHAKE256();
        FalconVrfy falconVrfy = new FalconVrfy();
        FalconCommon falconCommon = new FalconCommon();
        if (this.codec.modq_decode(sArr, 0, this.LOGN, bArr4, i, this.CRYPTO_PUBLICKEYBYTES - 1) != this.CRYPTO_PUBLICKEYBYTES - 1) {
            return -1;
        }
        falconVrfy.to_ntt_monty(sArr, 0, this.LOGN);
        int length = bArr.length;
        int length2 = bArr3.length;
        if (length >= 1) {
            byte b = bArr[0];
            int i4 = this.LOGN;
            if (b == ((byte) (i4 + 32)) && this.codec.comp_decode(sArr3, 0, i4, bArr, 1, i2) == (i2 = length - 1)) {
                shake256.inner_shake256_init();
                shake256.inner_shake256_inject(bArr2, 0, this.NONCELEN);
                shake256.inner_shake256_inject(bArr3, 0, length2);
                shake256.i_shake256_flip();
                falconCommon.hash_to_point_vartime(shake256, sArr2, 0, this.LOGN);
                return falconVrfy.verify_raw(sArr2, 0, sArr3, 0, sArr, 0, this.LOGN, new short[this.N], 0) == 0 ? -1 : 0;
            }
            return -1;
        }
        return -1;
    }
}
