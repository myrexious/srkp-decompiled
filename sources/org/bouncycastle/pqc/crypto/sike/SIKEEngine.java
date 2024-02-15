package org.bouncycastle.pqc.crypto.sike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHAKEDigest;

/* loaded from: classes2.dex */
public class SIKEEngine {
    protected Fpx fpx;
    private boolean isCompressed;
    protected Isogeny isogeny;
    protected Internal params;
    private SIDH sidh;
    private SIDH_Compressed sidhCompressed;

    /* JADX WARN: Removed duplicated region for block: B:39:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SIKEEngine(int r2, boolean r3) {
        /*
            r1 = this;
            r1.<init>()
            r1.isCompressed = r3
            r0 = 434(0x1b2, float:6.08E-43)
            if (r2 == r0) goto L28
            r0 = 503(0x1f7, float:7.05E-43)
            if (r2 == r0) goto L22
            r0 = 610(0x262, float:8.55E-43)
            if (r2 == r0) goto L1c
            r0 = 751(0x2ef, float:1.052E-42)
            if (r2 == r0) goto L16
            goto L2f
        L16:
            org.bouncycastle.pqc.crypto.sike.P751 r2 = new org.bouncycastle.pqc.crypto.sike.P751
            r2.<init>(r3)
            goto L2d
        L1c:
            org.bouncycastle.pqc.crypto.sike.P610 r2 = new org.bouncycastle.pqc.crypto.sike.P610
            r2.<init>(r3)
            goto L2d
        L22:
            org.bouncycastle.pqc.crypto.sike.P503 r2 = new org.bouncycastle.pqc.crypto.sike.P503
            r2.<init>(r3)
            goto L2d
        L28:
            org.bouncycastle.pqc.crypto.sike.P434 r2 = new org.bouncycastle.pqc.crypto.sike.P434
            r2.<init>(r3)
        L2d:
            r1.params = r2
        L2f:
            org.bouncycastle.pqc.crypto.sike.Fpx r2 = new org.bouncycastle.pqc.crypto.sike.Fpx
            r2.<init>(r1)
            r1.fpx = r2
            org.bouncycastle.pqc.crypto.sike.Isogeny r2 = new org.bouncycastle.pqc.crypto.sike.Isogeny
            r2.<init>(r1)
            r1.isogeny = r2
            if (r3 == 0) goto L46
            org.bouncycastle.pqc.crypto.sike.SIDH_Compressed r2 = new org.bouncycastle.pqc.crypto.sike.SIDH_Compressed
            r2.<init>(r1)
            r1.sidhCompressed = r2
        L46:
            org.bouncycastle.pqc.crypto.sike.SIDH r2 = new org.bouncycastle.pqc.crypto.sike.SIDH
            r2.<init>(r1)
            r1.sidh = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.sike.SIKEEngine.<init>(int, boolean):void");
    }

    public int crypto_kem_dec(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (this.isCompressed) {
            byte[] bArr4 = new byte[this.params.SECRETKEY_B_BYTES];
            byte[] bArr5 = new byte[this.params.FP2_ENCODED_BYTES + (this.params.FP2_ENCODED_BYTES * 2) + this.params.SECRETKEY_A_BYTES];
            byte[] bArr6 = new byte[this.params.MSG_BYTES];
            byte[] bArr7 = new byte[this.params.CRYPTO_CIPHERTEXTBYTES + this.params.MSG_BYTES];
            this.sidhCompressed.EphemeralSecretAgreement_A_extended(bArr3, this.params.MSG_BYTES, bArr2, bArr5, 1);
            SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
            sHAKEDigest.update(bArr5, 0, this.params.FP2_ENCODED_BYTES);
            sHAKEDigest.doFinal(bArr6, 0, this.params.MSG_BYTES);
            for (int i = 0; i < this.params.MSG_BYTES; i++) {
                bArr7[i] = (byte) (bArr2[this.params.PARTIALLY_COMPRESSED_CHUNK_CT + i] ^ bArr6[i]);
            }
            System.arraycopy(bArr3, this.params.MSG_BYTES + this.params.SECRETKEY_A_BYTES, bArr7, this.params.MSG_BYTES, this.params.CRYPTO_PUBLICKEYBYTES);
            sHAKEDigest.update(bArr7, 0, this.params.CRYPTO_PUBLICKEYBYTES + this.params.MSG_BYTES);
            sHAKEDigest.doFinal(bArr4, 0, this.params.SECRETKEY_B_BYTES);
            this.sidhCompressed.FormatPrivKey_B(bArr4);
            this.fpx.ct_cmov(bArr7, bArr3, this.params.MSG_BYTES, this.sidhCompressed.validate_ciphertext(bArr4, bArr2, bArr3, this.params.MSG_BYTES + this.params.SECRETKEY_A_BYTES + this.params.CRYPTO_PUBLICKEYBYTES, bArr5, this.params.FP2_ENCODED_BYTES));
            System.arraycopy(bArr2, 0, bArr7, this.params.MSG_BYTES, this.params.CRYPTO_CIPHERTEXTBYTES);
            sHAKEDigest.update(bArr7, 0, this.params.CRYPTO_CIPHERTEXTBYTES + this.params.MSG_BYTES);
            sHAKEDigest.doFinal(bArr, 0, this.params.CRYPTO_BYTES);
            return 0;
        }
        byte[] bArr8 = new byte[this.params.SECRETKEY_A_BYTES];
        byte[] bArr9 = new byte[this.params.FP2_ENCODED_BYTES];
        byte[] bArr10 = new byte[this.params.MSG_BYTES];
        byte[] bArr11 = new byte[this.params.CRYPTO_PUBLICKEYBYTES];
        byte[] bArr12 = new byte[this.params.CRYPTO_CIPHERTEXTBYTES + this.params.MSG_BYTES];
        this.sidh.EphemeralSecretAgreement_B(bArr3, bArr2, bArr9);
        SHAKEDigest sHAKEDigest2 = new SHAKEDigest(256);
        sHAKEDigest2.update(bArr9, 0, this.params.FP2_ENCODED_BYTES);
        sHAKEDigest2.doFinal(bArr10, 0, this.params.MSG_BYTES);
        for (int i2 = 0; i2 < this.params.MSG_BYTES; i2++) {
            bArr12[i2] = (byte) (bArr2[this.params.CRYPTO_PUBLICKEYBYTES + i2] ^ bArr10[i2]);
        }
        System.arraycopy(bArr3, this.params.MSG_BYTES + this.params.SECRETKEY_B_BYTES, bArr12, this.params.MSG_BYTES, this.params.CRYPTO_PUBLICKEYBYTES);
        sHAKEDigest2.update(bArr12, 0, this.params.CRYPTO_PUBLICKEYBYTES + this.params.MSG_BYTES);
        sHAKEDigest2.doFinal(bArr8, 0, this.params.SECRETKEY_A_BYTES);
        int i3 = this.params.SECRETKEY_A_BYTES - 1;
        bArr8[i3] = (byte) (bArr8[i3] & this.params.MASK_ALICE);
        this.sidh.EphemeralKeyGeneration_A(bArr8, bArr11);
        this.fpx.ct_cmov(bArr12, bArr3, this.params.MSG_BYTES, this.fpx.ct_compare(bArr11, bArr2, this.params.CRYPTO_PUBLICKEYBYTES));
        System.arraycopy(bArr2, 0, bArr12, this.params.MSG_BYTES, this.params.CRYPTO_CIPHERTEXTBYTES);
        sHAKEDigest2.update(bArr12, 0, this.params.CRYPTO_CIPHERTEXTBYTES + this.params.MSG_BYTES);
        sHAKEDigest2.doFinal(bArr, 0, this.params.CRYPTO_BYTES);
        return 0;
    }

    public int crypto_kem_enc(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        SHAKEDigest sHAKEDigest;
        if (this.isCompressed) {
            byte[] bArr4 = new byte[this.params.SECRETKEY_B_BYTES];
            byte[] bArr5 = new byte[this.params.FP2_ENCODED_BYTES];
            byte[] bArr6 = new byte[this.params.MSG_BYTES];
            byte[] bArr7 = new byte[this.params.CRYPTO_CIPHERTEXTBYTES + this.params.MSG_BYTES];
            byte[] bArr8 = new byte[this.params.MSG_BYTES];
            secureRandom.nextBytes(bArr8);
            System.arraycopy(bArr8, 0, bArr7, 0, this.params.MSG_BYTES);
            System.arraycopy(bArr3, 0, bArr7, this.params.MSG_BYTES, this.params.CRYPTO_PUBLICKEYBYTES);
            sHAKEDigest = new SHAKEDigest(256);
            sHAKEDigest.update(bArr7, 0, this.params.CRYPTO_PUBLICKEYBYTES + this.params.MSG_BYTES);
            sHAKEDigest.doFinal(bArr4, 0, this.params.SECRETKEY_B_BYTES);
            this.sidhCompressed.FormatPrivKey_B(bArr4);
            this.sidhCompressed.EphemeralKeyGeneration_B_extended(bArr4, bArr, 1);
            this.sidhCompressed.EphemeralSecretAgreement_B(bArr4, bArr3, bArr5);
            sHAKEDigest.update(bArr5, 0, this.params.FP2_ENCODED_BYTES);
            sHAKEDigest.doFinal(bArr6, 0, this.params.MSG_BYTES);
            for (int i = 0; i < this.params.MSG_BYTES; i++) {
                bArr[this.params.PARTIALLY_COMPRESSED_CHUNK_CT + i] = (byte) (bArr7[i] ^ bArr6[i]);
            }
            System.arraycopy(bArr, 0, bArr7, this.params.MSG_BYTES, this.params.CRYPTO_CIPHERTEXTBYTES);
            sHAKEDigest.update(bArr7, 0, this.params.CRYPTO_CIPHERTEXTBYTES + this.params.MSG_BYTES);
        } else {
            byte[] bArr9 = new byte[this.params.SECRETKEY_A_BYTES];
            byte[] bArr10 = new byte[this.params.FP2_ENCODED_BYTES];
            byte[] bArr11 = new byte[this.params.MSG_BYTES];
            byte[] bArr12 = new byte[this.params.CRYPTO_CIPHERTEXTBYTES + this.params.MSG_BYTES];
            byte[] bArr13 = new byte[this.params.MSG_BYTES];
            secureRandom.nextBytes(bArr13);
            System.arraycopy(bArr13, 0, bArr12, 0, this.params.MSG_BYTES);
            System.arraycopy(bArr3, 0, bArr12, this.params.MSG_BYTES, this.params.CRYPTO_PUBLICKEYBYTES);
            sHAKEDigest = new SHAKEDigest(256);
            sHAKEDigest.update(bArr12, 0, this.params.CRYPTO_PUBLICKEYBYTES + this.params.MSG_BYTES);
            sHAKEDigest.doFinal(bArr9, 0, this.params.SECRETKEY_A_BYTES);
            int i2 = this.params.SECRETKEY_A_BYTES - 1;
            bArr9[i2] = (byte) (bArr9[i2] & this.params.MASK_ALICE);
            this.sidh.EphemeralKeyGeneration_A(bArr9, bArr);
            this.sidh.EphemeralSecretAgreement_A(bArr9, bArr3, bArr10);
            sHAKEDigest.update(bArr10, 0, this.params.FP2_ENCODED_BYTES);
            sHAKEDigest.doFinal(bArr11, 0, this.params.MSG_BYTES);
            for (int i3 = 0; i3 < this.params.MSG_BYTES; i3++) {
                bArr[this.params.CRYPTO_PUBLICKEYBYTES + i3] = (byte) (bArr12[i3] ^ bArr11[i3]);
            }
            System.arraycopy(bArr, 0, bArr12, this.params.MSG_BYTES, this.params.CRYPTO_CIPHERTEXTBYTES);
            sHAKEDigest.update(bArr12, 0, this.params.CRYPTO_CIPHERTEXTBYTES + this.params.MSG_BYTES);
        }
        sHAKEDigest.doFinal(bArr2, 0, this.params.CRYPTO_BYTES);
        return 0;
    }

    public int crypto_kem_keypair(byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        int i;
        int i2;
        byte[] bArr3 = new byte[this.params.MSG_BYTES];
        secureRandom.nextBytes(bArr3);
        if (this.isCompressed) {
            byte[] bArr4 = new byte[this.params.SECRETKEY_A_BYTES];
            secureRandom.nextBytes(bArr4);
            bArr4[0] = (byte) (bArr4[0] & 254);
            int i3 = this.params.SECRETKEY_A_BYTES - 1;
            bArr4[i3] = (byte) (bArr4[i3] & this.params.MASK_ALICE);
            System.arraycopy(bArr3, 0, bArr2, 0, this.params.MSG_BYTES);
            System.arraycopy(bArr4, 0, bArr2, this.params.MSG_BYTES, this.params.SECRETKEY_A_BYTES);
            this.sidhCompressed.EphemeralKeyGeneration_A_extended(bArr2, bArr);
            i = this.params.MSG_BYTES;
            i2 = this.params.SECRETKEY_A_BYTES;
        } else {
            byte[] bArr5 = new byte[this.params.SECRETKEY_B_BYTES];
            secureRandom.nextBytes(bArr5);
            int i4 = this.params.SECRETKEY_B_BYTES - 1;
            bArr5[i4] = (byte) (bArr5[i4] & this.params.MASK_BOB);
            System.arraycopy(bArr3, 0, bArr2, 0, this.params.MSG_BYTES);
            System.arraycopy(bArr5, 0, bArr2, this.params.MSG_BYTES, this.params.SECRETKEY_B_BYTES);
            this.sidh.EphemeralKeyGeneration_B(bArr2, bArr);
            i = this.params.MSG_BYTES;
            i2 = this.params.SECRETKEY_B_BYTES;
        }
        System.arraycopy(bArr, 0, bArr2, i + i2, this.params.CRYPTO_PUBLICKEYBYTES);
        return 0;
    }

    public int getCipherTextSize() {
        return this.params.CRYPTO_CIPHERTEXTBYTES;
    }

    public int getDefaultSessionKeySize() {
        return this.params.MSG_BYTES * 8;
    }

    public int getPrivateKeySize() {
        return this.params.CRYPTO_SECRETKEYBYTES;
    }

    public int getPublicKeySize() {
        return this.params.CRYPTO_PUBLICKEYBYTES;
    }
}
