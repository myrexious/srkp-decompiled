package org.bouncycastle.cms.jcajce;

import java.io.OutputStream;
import java.security.AccessController;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.GCMParameters;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.jcajce.io.CipherOutputStream;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.MacCaptureStream;
import org.bouncycastle.operator.OutputAEADEncryptor;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.operator.jcajce.JceGenericKey;

/* loaded from: classes2.dex */
public class JceCMSContentEncryptorBuilder {
    private static final SecretKeySizeProvider KEY_SIZE_PROVIDER = DefaultSecretKeySizeProvider.INSTANCE;
    private AlgorithmIdentifier algorithmIdentifier;
    private AlgorithmParameters algorithmParameters;
    private final ASN1ObjectIdentifier encryptionOID;
    private EnvelopedDataHelper helper;
    private final int keySize;
    private SecureRandom random;

    /* loaded from: classes2.dex */
    private class CMSAuthOutputEncryptor implements OutputAEADEncryptor {
        private AlgorithmIdentifier algorithmIdentifier;
        private Cipher cipher;
        private SecretKey encKey;
        private MacCaptureStream macOut;

        CMSAuthOutputEncryptor(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws CMSException {
            JceCMSContentEncryptorBuilder.this = r3;
            KeyGenerator createKeyGenerator = r3.helper.createKeyGenerator(aSN1ObjectIdentifier);
            SecureRandom secureRandom2 = CryptoServicesRegistrar.getSecureRandom(secureRandom);
            if (i < 0) {
                createKeyGenerator.init(secureRandom2);
            } else {
                createKeyGenerator.init(i, secureRandom2);
            }
            this.cipher = r3.helper.createCipher(aSN1ObjectIdentifier);
            this.encKey = createKeyGenerator.generateKey();
            algorithmParameters = algorithmParameters == null ? r3.helper.generateParameters(aSN1ObjectIdentifier, this.encKey, secureRandom2) : algorithmParameters;
            try {
                this.cipher.init(1, this.encKey, algorithmParameters, secureRandom2);
                this.algorithmIdentifier = r3.helper.getAlgorithmIdentifier(aSN1ObjectIdentifier, algorithmParameters == null ? this.cipher.getParameters() : algorithmParameters);
            } catch (GeneralSecurityException e) {
                throw new CMSException("unable to initialize cipher: " + e.getMessage(), e);
            }
        }

        @Override // org.bouncycastle.operator.AADProcessor
        public OutputStream getAADStream() {
            if (JceCMSContentEncryptorBuilder.access$100()) {
                return new JceAADStream(this.cipher);
            }
            return null;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithmIdentifier;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public GenericKey getKey() {
            return new JceGenericKey(this.algorithmIdentifier, this.encKey);
        }

        @Override // org.bouncycastle.operator.AADProcessor
        public byte[] getMAC() {
            return this.macOut.getMac();
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            this.macOut = new MacCaptureStream(outputStream, GCMParameters.getInstance(this.algorithmIdentifier.getParameters()).getIcvLen());
            return new CipherOutputStream(this.macOut, this.cipher);
        }
    }

    /* loaded from: classes2.dex */
    private class CMSOutputEncryptor implements OutputEncryptor {
        private AlgorithmIdentifier algorithmIdentifier;
        private Cipher cipher;
        private SecretKey encKey;

        CMSOutputEncryptor(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws CMSException {
            JceCMSContentEncryptorBuilder.this = r3;
            KeyGenerator createKeyGenerator = r3.helper.createKeyGenerator(aSN1ObjectIdentifier);
            SecureRandom secureRandom2 = CryptoServicesRegistrar.getSecureRandom(secureRandom);
            if (i < 0) {
                createKeyGenerator.init(secureRandom2);
            } else {
                createKeyGenerator.init(i, secureRandom2);
            }
            this.cipher = r3.helper.createCipher(aSN1ObjectIdentifier);
            this.encKey = createKeyGenerator.generateKey();
            algorithmParameters = algorithmParameters == null ? r3.helper.generateParameters(aSN1ObjectIdentifier, this.encKey, secureRandom2) : algorithmParameters;
            try {
                this.cipher.init(1, this.encKey, algorithmParameters, secureRandom2);
                this.algorithmIdentifier = r3.helper.getAlgorithmIdentifier(aSN1ObjectIdentifier, algorithmParameters == null ? this.cipher.getParameters() : algorithmParameters);
            } catch (GeneralSecurityException e) {
                throw new CMSException("unable to initialize cipher: " + e.getMessage(), e);
            }
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithmIdentifier;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public GenericKey getKey() {
            return new JceGenericKey(this.algorithmIdentifier, this.encKey);
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            return new CipherOutputStream(outputStream, this.cipher);
        }
    }

    public JceCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, KEY_SIZE_PROVIDER.getKeySize(aSN1ObjectIdentifier));
    }

    public JceCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) {
        int i2;
        this.helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.encryptionOID = aSN1ObjectIdentifier;
        int keySize = KEY_SIZE_PROVIDER.getKeySize(aSN1ObjectIdentifier);
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.des_EDE3_CBC)) {
            i2 = 168;
            if (i != 168 && i != keySize) {
                throw new IllegalArgumentException("incorrect keySize for encryptionOID passed to builder.");
            }
        } else if (!aSN1ObjectIdentifier.equals((ASN1Primitive) OIWObjectIdentifiers.desCBC)) {
            if (keySize > 0 && keySize != i) {
                throw new IllegalArgumentException("incorrect keySize for encryptionOID passed to builder.");
            }
            this.keySize = i;
            return;
        } else {
            i2 = 56;
            if (i != 56 && i != keySize) {
                throw new IllegalArgumentException("incorrect keySize for encryptionOID passed to builder.");
            }
        }
        this.keySize = i2;
    }

    public JceCMSContentEncryptorBuilder(AlgorithmIdentifier algorithmIdentifier) {
        this(algorithmIdentifier.getAlgorithm(), KEY_SIZE_PROVIDER.getKeySize(algorithmIdentifier.getAlgorithm()));
        this.algorithmIdentifier = algorithmIdentifier;
    }

    static /* synthetic */ boolean access$100() {
        return checkForAEAD();
    }

    private static boolean checkForAEAD() {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    boolean z = true;
                    if (Cipher.class.getMethod("updateAAD", byte[].class) == null) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                } catch (Exception unused) {
                    return Boolean.FALSE;
                }
            }
        })).booleanValue();
    }

    public OutputEncryptor build() throws CMSException {
        ASN1Encodable parameters;
        if (this.algorithmParameters != null) {
            return this.helper.isAuthEnveloped(this.encryptionOID) ? new CMSAuthOutputEncryptor(this.encryptionOID, this.keySize, this.algorithmParameters, this.random) : new CMSOutputEncryptor(this.encryptionOID, this.keySize, this.algorithmParameters, this.random);
        }
        AlgorithmIdentifier algorithmIdentifier = this.algorithmIdentifier;
        if (algorithmIdentifier != null && (parameters = algorithmIdentifier.getParameters()) != null && !parameters.equals(DERNull.INSTANCE)) {
            try {
                AlgorithmParameters createAlgorithmParameters = this.helper.createAlgorithmParameters(this.algorithmIdentifier.getAlgorithm());
                this.algorithmParameters = createAlgorithmParameters;
                createAlgorithmParameters.init(parameters.toASN1Primitive().getEncoded());
            } catch (Exception e) {
                throw new CMSException("unable to process provided algorithmIdentifier: " + e.toString(), e);
            }
        }
        return this.helper.isAuthEnveloped(this.encryptionOID) ? new CMSAuthOutputEncryptor(this.encryptionOID, this.keySize, this.algorithmParameters, this.random) : new CMSOutputEncryptor(this.encryptionOID, this.keySize, this.algorithmParameters, this.random);
    }

    public JceCMSContentEncryptorBuilder setAlgorithmParameters(AlgorithmParameters algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
        return this;
    }

    public JceCMSContentEncryptorBuilder setProvider(String str) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        return this;
    }

    public JceCMSContentEncryptorBuilder setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceCMSContentEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
