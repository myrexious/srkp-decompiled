package org.bouncycastle.pqc.crypto.bike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes2.dex */
public class BIKEKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private int L_BYTE;
    private int R_BYTE;
    private BIKEKeyGenerationParameters bikeKeyGenerationParameters;
    private int hw;
    private int l;
    private int nbIter;
    private int r;
    private SecureRandom random;
    private int t;
    private int tau;
    private int w;

    private AsymmetricCipherKeyPair genKeyPair() {
        BIKEEngine engine = this.bikeKeyGenerationParameters.getParameters().getEngine();
        int i = this.R_BYTE;
        byte[] bArr = new byte[i];
        byte[] bArr2 = new byte[i];
        byte[] bArr3 = new byte[i];
        byte[] bArr4 = new byte[this.L_BYTE];
        engine.genKeyPair(bArr, bArr2, bArr4, bArr3, this.random);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new BIKEPublicKeyParameters(this.bikeKeyGenerationParameters.getParameters(), bArr3), (AsymmetricKeyParameter) new BIKEPrivateKeyParameters(this.bikeKeyGenerationParameters.getParameters(), bArr, bArr2, bArr4));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.bikeKeyGenerationParameters = (BIKEKeyGenerationParameters) keyGenerationParameters;
        this.random = keyGenerationParameters.getRandom();
        this.r = this.bikeKeyGenerationParameters.getParameters().getR();
        this.w = this.bikeKeyGenerationParameters.getParameters().getW();
        this.l = this.bikeKeyGenerationParameters.getParameters().getL();
        this.t = this.bikeKeyGenerationParameters.getParameters().getT();
        this.nbIter = this.bikeKeyGenerationParameters.getParameters().getNbIter();
        this.tau = this.bikeKeyGenerationParameters.getParameters().getTau();
        this.hw = this.w / 2;
        this.L_BYTE = this.l / 8;
        this.R_BYTE = (this.r + 7) / 8;
    }
}
