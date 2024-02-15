package org.bouncycastle.pqc.crypto.bike;

import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class BIKEKEMExtractor implements EncapsulatedSecretExtractor {
    private BIKEEngine engine;
    private BIKEKeyParameters key;

    public BIKEKEMExtractor(BIKEPrivateKeyParameters bIKEPrivateKeyParameters) {
        this.key = bIKEPrivateKeyParameters;
        initCipher(bIKEPrivateKeyParameters.getParameters());
    }

    private void initCipher(BIKEParameters bIKEParameters) {
        this.engine = bIKEParameters.getEngine();
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public byte[] extractSecret(byte[] bArr) {
        byte[] bArr2 = new byte[this.engine.getSessionKeySize()];
        BIKEPrivateKeyParameters bIKEPrivateKeyParameters = (BIKEPrivateKeyParameters) this.key;
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bIKEPrivateKeyParameters.getParameters().getRByte());
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, bIKEPrivateKeyParameters.getParameters().getRByte(), bArr.length);
        this.engine.decaps(bArr2, bIKEPrivateKeyParameters.getH0(), bIKEPrivateKeyParameters.getH1(), bIKEPrivateKeyParameters.getSigma(), copyOfRange, copyOfRange2);
        return Arrays.copyOfRange(bArr2, 0, this.key.getParameters().getSessionKeySize() / 8);
    }

    @Override // org.bouncycastle.crypto.EncapsulatedSecretExtractor
    public int getEncapsulationLength() {
        return this.key.getParameters().getRByte() + this.key.getParameters().getLByte();
    }
}
