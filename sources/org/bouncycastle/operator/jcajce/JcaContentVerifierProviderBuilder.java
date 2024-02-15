package org.bouncycastle.operator.jcajce;

import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.jcajce.CompositePublicKey;
import org.bouncycastle.jcajce.io.OutputStreamFactory;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.RawContentVerifier;
import org.bouncycastle.operator.RuntimeOperatorException;
import org.bouncycastle.util.io.TeeOutputStream;

/* loaded from: classes2.dex */
public class JcaContentVerifierProviderBuilder {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());

    /* loaded from: classes2.dex */
    public static class CompositeVerifier implements ContentVerifier {
        private Signature[] sigs;
        private OutputStream stream;

        public CompositeVerifier(Signature[] signatureArr) throws OperatorCreationException {
            this.sigs = signatureArr;
            int i = 0;
            while (i < signatureArr.length && signatureArr[i] == null) {
                i++;
            }
            if (i == signatureArr.length) {
                throw new OperatorCreationException("no matching signature found in composite");
            }
            OutputStream createStream = OutputStreamFactory.createStream(signatureArr[i]);
            while (true) {
                this.stream = createStream;
                do {
                    i++;
                    if (i == signatureArr.length) {
                        return;
                    }
                } while (signatureArr[i] == null);
                createStream = new TeeOutputStream(this.stream, OutputStreamFactory.createStream(signatureArr[i]));
            }
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return new AlgorithmIdentifier(MiscObjectIdentifiers.id_alg_composite);
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public OutputStream getOutputStream() {
            return this.stream;
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public boolean verify(byte[] bArr) {
            try {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(bArr);
                boolean z = false;
                for (int i = 0; i != aSN1Sequence.size(); i++) {
                    Signature signature = this.sigs[i];
                    if (signature != null && !signature.verify(DERBitString.getInstance((Object) aSN1Sequence.getObjectAt(i)).getBytes())) {
                        z = true;
                    }
                }
                return !z;
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class RawSigVerifier extends SigVerifier implements RawContentVerifier {
        private Signature rawSignature;

        RawSigVerifier(AlgorithmIdentifier algorithmIdentifier, Signature signature, Signature signature2) {
            super(algorithmIdentifier, signature);
            this.rawSignature = signature2;
        }

        @Override // org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder.SigVerifier, org.bouncycastle.operator.ContentVerifier
        public boolean verify(byte[] bArr) {
            try {
                return super.verify(bArr);
            } finally {
                try {
                    this.rawSignature.verify(bArr);
                } catch (Exception unused) {
                }
            }
        }

        @Override // org.bouncycastle.operator.RawContentVerifier
        public boolean verify(byte[] bArr, byte[] bArr2) {
            try {
                try {
                    this.rawSignature.update(bArr);
                    boolean verify = this.rawSignature.verify(bArr2);
                    try {
                        this.rawSignature.verify(bArr2);
                    } catch (Exception unused) {
                    }
                    return verify;
                } catch (SignatureException e) {
                    throw new RuntimeOperatorException("exception obtaining raw signature: " + e.getMessage(), e);
                }
            } catch (Throwable th) {
                try {
                    this.rawSignature.verify(bArr2);
                } catch (Exception unused2) {
                }
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class SigVerifier implements ContentVerifier {
        private final AlgorithmIdentifier algorithm;
        private final Signature signature;
        protected final OutputStream stream;

        SigVerifier(AlgorithmIdentifier algorithmIdentifier, Signature signature) {
            this.algorithm = algorithmIdentifier;
            this.signature = signature;
            this.stream = OutputStreamFactory.createStream(signature);
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithm;
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public OutputStream getOutputStream() {
            OutputStream outputStream = this.stream;
            if (outputStream != null) {
                return outputStream;
            }
            throw new IllegalStateException("verifier not initialised");
        }

        @Override // org.bouncycastle.operator.ContentVerifier
        public boolean verify(byte[] bArr) {
            try {
                return this.signature.verify(bArr);
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
            }
        }
    }

    public ContentVerifier createCompositeVerifier(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) throws OperatorCreationException {
        int i = 0;
        if (!(publicKey instanceof CompositePublicKey)) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(algorithmIdentifier.getParameters());
            Signature[] signatureArr = new Signature[aSN1Sequence.size()];
            while (i != aSN1Sequence.size()) {
                try {
                    signatureArr[i] = createSignature(AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i)), publicKey);
                } catch (Exception unused) {
                    signatureArr[i] = null;
                }
                i++;
            }
            return new CompositeVerifier(signatureArr);
        }
        List<PublicKey> publicKeys = ((CompositePublicKey) publicKey).getPublicKeys();
        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(algorithmIdentifier.getParameters());
        Signature[] signatureArr2 = new Signature[aSN1Sequence2.size()];
        while (i != aSN1Sequence2.size()) {
            AlgorithmIdentifier algorithmIdentifier2 = AlgorithmIdentifier.getInstance(aSN1Sequence2.getObjectAt(i));
            if (publicKeys.get(i) != null) {
                signatureArr2[i] = createSignature(algorithmIdentifier2, publicKeys.get(i));
            } else {
                signatureArr2[i] = null;
            }
            i++;
        }
        return new CompositeVerifier(signatureArr2);
    }

    public Signature createRawSig(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        try {
            Signature createRawSignature = this.helper.createRawSignature(algorithmIdentifier);
            if (createRawSignature != null) {
                createRawSignature.initVerify(publicKey);
                return createRawSignature;
            }
            return createRawSignature;
        } catch (Exception unused) {
            return null;
        }
    }

    public Signature createSignature(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) throws OperatorCreationException {
        try {
            Signature createSignature = this.helper.createSignature(algorithmIdentifier);
            createSignature.initVerify(publicKey);
            return createSignature;
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("exception on setup: " + e, e);
        }
    }

    public ContentVerifierProvider build(final PublicKey publicKey) throws OperatorCreationException {
        return new ContentVerifierProvider() { // from class: org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder.2
            @Override // org.bouncycastle.operator.ContentVerifierProvider
            public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                if (algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) MiscObjectIdentifiers.id_alg_composite)) {
                    return JcaContentVerifierProviderBuilder.this.createCompositeVerifier(algorithmIdentifier, publicKey);
                }
                PublicKey publicKey2 = publicKey;
                if (!(publicKey2 instanceof CompositePublicKey)) {
                    Signature createSignature = JcaContentVerifierProviderBuilder.this.createSignature(algorithmIdentifier, publicKey2);
                    Signature createRawSig = JcaContentVerifierProviderBuilder.this.createRawSig(algorithmIdentifier, publicKey);
                    return createRawSig != null ? new RawSigVerifier(algorithmIdentifier, createSignature, createRawSig) : new SigVerifier(algorithmIdentifier, createSignature);
                }
                List<PublicKey> publicKeys = ((CompositePublicKey) publicKey2).getPublicKeys();
                for (int i = 0; i != publicKeys.size(); i++) {
                    try {
                        Signature createSignature2 = JcaContentVerifierProviderBuilder.this.createSignature(algorithmIdentifier, publicKeys.get(i));
                        Signature createRawSig2 = JcaContentVerifierProviderBuilder.this.createRawSig(algorithmIdentifier, publicKeys.get(i));
                        return createRawSig2 != null ? new RawSigVerifier(algorithmIdentifier, createSignature2, createRawSig2) : new SigVerifier(algorithmIdentifier, createSignature2);
                    } catch (OperatorCreationException unused) {
                    }
                }
                throw new OperatorCreationException("no matching algorithm found for key");
            }

            @Override // org.bouncycastle.operator.ContentVerifierProvider
            public X509CertificateHolder getAssociatedCertificate() {
                return null;
            }

            @Override // org.bouncycastle.operator.ContentVerifierProvider
            public boolean hasAssociatedCertificate() {
                return false;
            }
        };
    }

    public ContentVerifierProvider build(final X509Certificate x509Certificate) throws OperatorCreationException {
        try {
            final JcaX509CertificateHolder jcaX509CertificateHolder = new JcaX509CertificateHolder(x509Certificate);
            return new ContentVerifierProvider() { // from class: org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder.1
                @Override // org.bouncycastle.operator.ContentVerifierProvider
                public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                    if (algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) MiscObjectIdentifiers.id_alg_composite)) {
                        return JcaContentVerifierProviderBuilder.this.createCompositeVerifier(algorithmIdentifier, x509Certificate.getPublicKey());
                    }
                    try {
                        Signature createSignature = JcaContentVerifierProviderBuilder.this.helper.createSignature(algorithmIdentifier);
                        createSignature.initVerify(x509Certificate.getPublicKey());
                        Signature createRawSig = JcaContentVerifierProviderBuilder.this.createRawSig(algorithmIdentifier, x509Certificate.getPublicKey());
                        return createRawSig != null ? new RawSigVerifier(algorithmIdentifier, createSignature, createRawSig) : new SigVerifier(algorithmIdentifier, createSignature);
                    } catch (GeneralSecurityException e) {
                        throw new OperatorCreationException("exception on setup: " + e, e);
                    }
                }

                @Override // org.bouncycastle.operator.ContentVerifierProvider
                public X509CertificateHolder getAssociatedCertificate() {
                    return jcaX509CertificateHolder;
                }

                @Override // org.bouncycastle.operator.ContentVerifierProvider
                public boolean hasAssociatedCertificate() {
                    return true;
                }
            };
        } catch (CertificateEncodingException e) {
            throw new OperatorCreationException("cannot process certificate: " + e.getMessage(), e);
        }
    }

    public ContentVerifierProvider build(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        return build(this.helper.convertPublicKey(subjectPublicKeyInfo));
    }

    public ContentVerifierProvider build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
        return build(this.helper.convertCertificate(x509CertificateHolder));
    }

    public JcaContentVerifierProviderBuilder setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JcaContentVerifierProviderBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }
}
