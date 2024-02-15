package org.bouncycastle.pqc.crypto.sike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.EncapsulatedSecretGenerator;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.util.SecretWithEncapsulationImpl;

/* loaded from: classes2.dex */
public class SIKEKEMGenerator implements EncapsulatedSecretGenerator {
    private final SecureRandom sr;

    public SIKEKEMGenerator(SecureRandom secureRandom) {
        this.sr = secureRandom;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretGenerator
    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter) {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("SIKEKEM", 0, asymmetricKeyParameter, CryptoServicePurpose.ENCRYPTION));
        return generateEncapsulated(asymmetricKeyParameter, ((SIKEPublicKeyParameters) asymmetricKeyParameter).getParameters().getEngine().getDefaultSessionKeySize());
    }

    public SecretWithEncapsulation generateEncapsulated(AsymmetricKeyParameter asymmetricKeyParameter, int i) {
        System.err.println("WARNING: the SIKE algorithm is only for research purposes, insecure");
        SIKEPublicKeyParameters sIKEPublicKeyParameters = (SIKEPublicKeyParameters) asymmetricKeyParameter;
        SIKEEngine engine = sIKEPublicKeyParameters.getParameters().getEngine();
        byte[] bArr = new byte[engine.getCipherTextSize()];
        byte[] bArr2 = new byte[i / 8];
        engine.crypto_kem_enc(bArr, bArr2, sIKEPublicKeyParameters.getPublicKey(), this.sr);
        return new SecretWithEncapsulationImpl(bArr2, bArr);
    }
}
