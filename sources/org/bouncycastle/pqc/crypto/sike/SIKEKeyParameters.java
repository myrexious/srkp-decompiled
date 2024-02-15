package org.bouncycastle.pqc.crypto.sike;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes2.dex */
public class SIKEKeyParameters extends AsymmetricKeyParameter {
    private SIKEParameters params;

    public SIKEKeyParameters(boolean z, SIKEParameters sIKEParameters) {
        super(z);
        this.params = sIKEParameters;
    }

    public SIKEParameters getParameters() {
        return this.params;
    }
}
