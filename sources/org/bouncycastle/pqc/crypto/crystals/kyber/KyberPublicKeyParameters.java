package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class KyberPublicKeyParameters extends KyberKeyParameters {
    final byte[] rho;
    final byte[] t;

    public KyberPublicKeyParameters(KyberParameters kyberParameters, byte[] bArr) {
        super(false, kyberParameters);
        this.t = Arrays.copyOfRange(bArr, 0, bArr.length - 32);
        this.rho = Arrays.copyOfRange(bArr, bArr.length - 32, bArr.length);
    }

    public KyberPublicKeyParameters(KyberParameters kyberParameters, byte[] bArr, byte[] bArr2) {
        super(false, kyberParameters);
        this.t = Arrays.clone(bArr);
        this.rho = Arrays.clone(bArr2);
    }

    public byte[] getEncoded() {
        return getPublicKey();
    }

    public byte[] getPublicKey() {
        return Arrays.concatenate(this.t, this.rho);
    }

    public byte[] getRho() {
        return Arrays.clone(this.rho);
    }

    public byte[] getT() {
        return Arrays.clone(this.t);
    }
}
