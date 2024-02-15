package org.bouncycastle.pqc.jcajce.provider.falcon;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.falcon.FalconPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconSigner;

/* loaded from: classes2.dex */
public class SignatureSpi extends Signature {
    private ByteArrayOutputStream bOut;
    private SecureRandom random;
    private FalconSigner signer;

    /* loaded from: classes2.dex */
    public static class Base extends SignatureSpi {
        public Base() throws NoSuchAlgorithmException {
            super(new FalconSigner());
        }
    }

    protected SignatureSpi(FalconSigner falconSigner) {
        super("Falcon");
        this.bOut = new ByteArrayOutputStream();
        this.signer = falconSigner;
    }

    @Override // java.security.SignatureSpi
    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (!(privateKey instanceof BCFalconPrivateKey)) {
            throw new InvalidKeyException("unknown private key passed to Falcon");
        }
        FalconPrivateKeyParameters keyParams = ((BCFalconPrivateKey) privateKey).getKeyParams();
        if (this.random != null) {
            this.signer.init(true, new ParametersWithRandom(keyParams, this.random));
        } else {
            this.signer.init(true, keyParams);
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        engineInitSign(privateKey);
    }

    @Override // java.security.SignatureSpi
    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof BCFalconPublicKey)) {
            throw new InvalidKeyException("unknown public key passed to Falcon");
        }
        this.signer.init(false, ((BCFalconPublicKey) publicKey).getKeyParams());
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected byte[] engineSign() throws SignatureException {
        try {
            byte[] byteArray = this.bOut.toByteArray();
            this.bOut.reset();
            return this.signer.generateSignature(byteArray);
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte b) throws SignatureException {
        this.bOut.write(b);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte[] bArr, int i, int i2) throws SignatureException {
        this.bOut.write(bArr, i, i2);
    }

    @Override // java.security.SignatureSpi
    protected boolean engineVerify(byte[] bArr) throws SignatureException {
        byte[] byteArray = this.bOut.toByteArray();
        this.bOut.reset();
        return this.signer.verifySignature(byteArray, bArr);
    }
}
