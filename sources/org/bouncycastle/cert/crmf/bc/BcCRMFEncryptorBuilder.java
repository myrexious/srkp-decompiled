package org.bouncycastle.cert.crmf.bc;

import java.io.OutputStream;
import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.util.CipherFactory;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;

/* loaded from: classes2.dex */
public class BcCRMFEncryptorBuilder {
    private final ASN1ObjectIdentifier encryptionOID;
    private CRMFHelper helper;
    private final int keySize;
    private SecureRandom random;

    /* loaded from: classes2.dex */
    private class CRMFOutputEncryptor implements OutputEncryptor {
        private AlgorithmIdentifier algorithmIdentifier;
        private Object cipher;
        private KeyParameter encKey;

        CRMFOutputEncryptor(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, SecureRandom secureRandom) throws CRMFException {
            BcCRMFEncryptorBuilder.this = r2;
            SecureRandom secureRandom2 = CryptoServicesRegistrar.getSecureRandom(secureRandom);
            this.encKey = new KeyParameter(r2.helper.createKeyGenerator(aSN1ObjectIdentifier, secureRandom2).generateKey());
            this.algorithmIdentifier = r2.helper.generateEncryptionAlgID(aSN1ObjectIdentifier, this.encKey, secureRandom2);
            CRMFHelper unused = r2.helper;
            this.cipher = CRMFHelper.createContentCipher(true, this.encKey, this.algorithmIdentifier);
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithmIdentifier;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public GenericKey getKey() {
            return new GenericKey(this.algorithmIdentifier, this.encKey.getKey());
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            return CipherFactory.createOutputStream(outputStream, this.cipher);
        }
    }

    public BcCRMFEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, -1);
    }

    public BcCRMFEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) {
        this.helper = new CRMFHelper();
        this.encryptionOID = aSN1ObjectIdentifier;
        this.keySize = i;
    }

    public OutputEncryptor build() throws CRMFException {
        return new CRMFOutputEncryptor(this.encryptionOID, this.keySize, this.random);
    }

    public BcCRMFEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
