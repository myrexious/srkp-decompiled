package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class KyberPrivateKeyParameters extends KyberKeyParameters {
    final byte[] hpk;
    final byte[] nonce;
    final byte[] rho;
    final byte[] s;
    final byte[] t;

    public KyberPrivateKeyParameters(KyberParameters kyberParameters, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        super(true, kyberParameters);
        this.s = Arrays.clone(bArr);
        this.hpk = Arrays.clone(bArr2);
        this.nonce = Arrays.clone(bArr3);
        this.t = Arrays.clone(bArr4);
        this.rho = Arrays.clone(bArr5);
    }

    public byte[] getEncoded() {
        return getPrivateKey();
    }

    public byte[] getHPK() {
        return Arrays.clone(this.hpk);
    }

    public byte[] getNonce() {
        return Arrays.clone(this.nonce);
    }

    public byte[] getPrivateKey() {
        return Arrays.concatenate(this.s, getPublicKey(), this.hpk, this.nonce);
    }

    public byte[] getPublicKey() {
        return Arrays.concatenate(this.t, this.rho);
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }

    public byte[] getS() {
        return Arrays.clone(this.s);
    }

    public byte[] getT() {
        return Arrays.clone(this.t);
    }
}
