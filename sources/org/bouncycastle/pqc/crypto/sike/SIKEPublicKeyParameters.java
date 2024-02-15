package org.bouncycastle.pqc.crypto.sike;

import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class SIKEPublicKeyParameters extends SIKEKeyParameters {
    private final byte[] publicKey;

    public SIKEPublicKeyParameters(SIKEParameters sIKEParameters, byte[] bArr) {
        super(false, sIKEParameters);
        this.publicKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return getPublicKey();
    }

    public byte[] getPublicKey() {
        return Arrays.clone(this.publicKey);
    }
}
