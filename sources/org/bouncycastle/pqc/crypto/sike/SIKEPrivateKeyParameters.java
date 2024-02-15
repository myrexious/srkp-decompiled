package org.bouncycastle.pqc.crypto.sike;

import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class SIKEPrivateKeyParameters extends SIKEKeyParameters {
    private byte[] privateKey;

    public SIKEPrivateKeyParameters(SIKEParameters sIKEParameters, byte[] bArr) {
        super(true, sIKEParameters);
        this.privateKey = Arrays.clone(bArr);
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.privateKey);
    }

    public byte[] getPrivateKey() {
        return Arrays.clone(this.privateKey);
    }
}
