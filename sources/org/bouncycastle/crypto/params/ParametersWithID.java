package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes2.dex */
public class ParametersWithID implements CipherParameters {

    /* renamed from: id */
    private byte[] f45id;
    private CipherParameters parameters;

    public ParametersWithID(CipherParameters cipherParameters, byte[] bArr) {
        this.parameters = cipherParameters;
        this.f45id = bArr;
    }

    public byte[] getID() {
        return this.f45id;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
