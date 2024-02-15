package org.bouncycastle.pqc.crypto.sike;

import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;

/* loaded from: classes2.dex */
public class SIKEKEMExtractor implements EncapsulatedSecretExtractor {
    private SIKEEngine engine;
    private SIKEKeyParameters key;

    public SIKEKEMExtractor(SIKEPrivateKeyParameters sIKEPrivateKeyParameters) {
        System.err.println("WARNING: the SIKE algorithm is only for research purposes, insecure");
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("SIKEKEM", 0, sIKEPrivateKeyParameters, CryptoServicePurpose.DECRYPTION));
        this.key = sIKEPrivateKeyParameters;
        initCipher(sIKEPrivateKeyParameters.getParameters());
    }

    private void initCipher(SIKEParameters sIKEParameters) {
        this.engine = sIKEParameters.getEngine();
        SIKEPrivateKeyParameters sIKEPrivateKeyParameters = (SIKEPrivateKeyParameters) this.key;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public byte[] extractSecret(byte[] bArr) {
        return extractSecret(bArr, this.engine.getDefaultSessionKeySize());
    }

    public byte[] extractSecret(byte[] bArr, int i) {
        System.err.println("WARNING: the SIKE algorithm is only for research purposes, insecure");
        byte[] bArr2 = new byte[i / 8];
        this.engine.crypto_kem_dec(bArr2, bArr, ((SIKEPrivateKeyParameters) this.key).getPrivateKey());
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public int getEncapsulationLength() {
        return this.engine.getCipherTextSize();
    }
}
