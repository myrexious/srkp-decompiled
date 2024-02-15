package org.bouncycastle.pqc.crypto.sike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* loaded from: classes2.dex */
public class SIKEKeyGenerationParameters extends KeyGenerationParameters {
    private SIKEParameters params;

    public SIKEKeyGenerationParameters(SecureRandom secureRandom, SIKEParameters sIKEParameters) {
        super(secureRandom, 256);
        this.params = sIKEParameters;
    }

    public SIKEParameters getParameters() {
        return this.params;
    }
}
