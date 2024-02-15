package org.bouncycastle.pqc.jcajce.provider.picnic;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.picnic.PicnicPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicPublicKeyParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicSigner;

/* loaded from: classes2.dex */
public class SignatureSpi extends Signature {
    private Digest digest;
    private SecureRandom random;
    private PicnicSigner signer;

    /* loaded from: classes2.dex */
    public static class Base extends SignatureSpi {
        public Base() throws NoSuchAlgorithmException {
            super(new NullDigest(), new PicnicSigner());
        }
    }

    /* loaded from: classes2.dex */
    public static class withSha3512 extends SignatureSpi {
        public withSha3512() throws NoSuchAlgorithmException {
            super(new SHA3Digest(512), new PicnicSigner());
        }
    }

    /* loaded from: classes2.dex */
    public static class withSha512 extends SignatureSpi {
        public withSha512() throws NoSuchAlgorithmException {
            super(new SHA512Digest(), new PicnicSigner());
        }
    }

    /* loaded from: classes2.dex */
    public static class withShake256 extends SignatureSpi {
        public withShake256() throws NoSuchAlgorithmException {
            super(new SHAKEDigest(256), new PicnicSigner());
        }
    }

    protected SignatureSpi(Digest digest, PicnicSigner picnicSigner) {
        super("Picnic");
        this.digest = digest;
        this.signer = picnicSigner;
    }

    @Override // java.security.SignatureSpi
    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (!(privateKey instanceof BCPicnicPrivateKey)) {
            throw new InvalidKeyException("unknown private key passed to Picnic");
        }
        PicnicPrivateKeyParameters keyParams = ((BCPicnicPrivateKey) privateKey).getKeyParams();
        this.digest.reset();
        this.signer.init(true, keyParams);
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        engineInitSign(privateKey);
    }

    @Override // java.security.SignatureSpi
    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof BCPicnicPublicKey)) {
            throw new InvalidKeyException("unknown public key passed to Picnic");
        }
        PicnicPublicKeyParameters keyParams = ((BCPicnicPublicKey) publicKey).getKeyParams();
        this.digest.reset();
        this.signer.init(false, keyParams);
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
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        try {
            return this.signer.generateSignature(bArr);
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte b) throws SignatureException {
        this.digest.update(b);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte[] bArr, int i, int i2) throws SignatureException {
        this.digest.update(bArr, i, i2);
    }

    @Override // java.security.SignatureSpi
    protected boolean engineVerify(byte[] bArr) throws SignatureException {
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr2, 0);
        return this.signer.verifySignature(bArr2, bArr);
    }
}
