package org.bouncycastle.its.jcajce;

import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.its.ITSCertificate;
import org.bouncycastle.its.operator.ITSContentSigner;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class JcaITSContentSigner implements ITSContentSigner {
    private final ASN1ObjectIdentifier curveID;
    private final DigestCalculator digest;
    private final AlgorithmIdentifier digestAlgo;
    private final JcaJceHelper helper;
    private final byte[] parentData;
    private final byte[] parentDigest;
    private final ECPrivateKey privateKey;
    private final String signer;
    private final ITSCertificate signerCert;

    /* loaded from: classes2.dex */
    public static class Builder {
        private JcaJceHelper helper = new DefaultJcaJceHelper();

        public JcaITSContentSigner build(PrivateKey privateKey) {
            return new JcaITSContentSigner((ECPrivateKey) privateKey, null, this.helper);
        }

        public JcaITSContentSigner build(PrivateKey privateKey, ITSCertificate iTSCertificate) {
            return new JcaITSContentSigner((ECPrivateKey) privateKey, iTSCertificate, this.helper);
        }

        public Builder setProvider(String str) {
            this.helper = new NamedJcaJceHelper(str);
            return this;
        }

        public Builder setProvider(Provider provider) {
            this.helper = new ProviderJcaJceHelper(provider);
            return this;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private JcaITSContentSigner(java.security.interfaces.ECPrivateKey r3, org.bouncycastle.its.ITSCertificate r4, org.bouncycastle.jcajce.util.JcaJceHelper r5) {
        /*
            r2 = this;
            r2.<init>()
            r2.privateKey = r3
            r2.signerCert = r4
            r2.helper = r5
            byte[] r3 = r3.getEncoded()
            org.bouncycastle.asn1.pkcs.PrivateKeyInfo r3 = org.bouncycastle.asn1.pkcs.PrivateKeyInfo.getInstance(r3)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = r3.getPrivateKeyAlgorithm()
            org.bouncycastle.asn1.ASN1Encodable r3 = r3.getParameters()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.ASN1ObjectIdentifier.getInstance(r3)
            r2.curveID = r3
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.sec.SECObjectIdentifiers.secp256r1
            boolean r0 = r3.equals(r0)
            java.lang.String r1 = "SHA256withECDSA"
            if (r0 == 0) goto L35
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.nist.NISTObjectIdentifiers.id_sha256
            r3.<init>(r0)
        L30:
            r2.digestAlgo = r3
            r2.signer = r1
            goto L5a
        L35:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers.brainpoolP256r1
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L45
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.nist.NISTObjectIdentifiers.id_sha256
            r3.<init>(r0)
            goto L30
        L45:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers.brainpoolP384r1
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto Ld5
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.nist.NISTObjectIdentifiers.id_sha384
            r3.<init>(r0)
            r2.digestAlgo = r3
            java.lang.String r3 = "SHA384withECDSA"
            r2.signer = r3
        L5a:
            org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder r3 = new org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder     // Catch: java.lang.Exception -> Lca
            r3.<init>()     // Catch: java.lang.Exception -> Lca
            org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder r3 = r3.setHelper(r5)     // Catch: java.lang.Exception -> Lca
            org.bouncycastle.operator.DigestCalculatorProvider r3 = r3.build()     // Catch: java.lang.Exception -> Lca
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r5 = r2.digestAlgo     // Catch: org.bouncycastle.operator.OperatorCreationException -> Lae
            org.bouncycastle.operator.DigestCalculator r3 = r3.get(r5)     // Catch: org.bouncycastle.operator.OperatorCreationException -> Lae
            r2.digest = r3     // Catch: org.bouncycastle.operator.OperatorCreationException -> Lae
            if (r4 == 0) goto La4
            byte[] r4 = r4.getEncoded()     // Catch: java.io.IOException -> L8a
            r2.parentData = r4     // Catch: java.io.IOException -> L8a
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch: java.io.IOException -> L8a
            int r0 = r4.length     // Catch: java.io.IOException -> L8a
            r1 = 0
            r5.write(r4, r1, r0)     // Catch: java.io.IOException -> L8a
            r5.close()     // Catch: java.io.IOException -> L8a
            byte[] r3 = r3.getDigest()     // Catch: java.io.IOException -> L8a
            r2.parentDigest = r3     // Catch: java.io.IOException -> L8a
            goto Lad
        L8a:
            r3 = move-exception
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "signer certificate encoding failed: "
            r5.<init>(r0)
            java.lang.String r3 = r3.getMessage()
            java.lang.StringBuilder r3 = r5.append(r3)
            java.lang.String r3 = r3.toString()
            r4.<init>(r3)
            throw r4
        La4:
            r4 = 0
            r2.parentData = r4
            byte[] r3 = r3.getDigest()
            r2.parentDigest = r3
        Lad:
            return
        Lae:
            r3 = move-exception
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "cannot recognise digest type: "
            r5.<init>(r0)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r0 = r2.digestAlgo
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r0.getAlgorithm()
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5, r3)
            throw r4
        Lca:
            r3 = move-exception
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = r3.getMessage()
            r4.<init>(r5, r3)
            throw r4
        Ld5:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "unknown key type"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.its.jcajce.JcaITSContentSigner.<init>(java.security.interfaces.ECPrivateKey, org.bouncycastle.its.ITSCertificate, org.bouncycastle.jcajce.util.JcaJceHelper):void");
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public ITSCertificate getAssociatedCertificate() {
        return this.signerCert;
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public byte[] getAssociatedCertificateDigest() {
        return Arrays.clone(this.parentDigest);
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public ASN1ObjectIdentifier getCurveID() {
        return this.curveID;
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.digestAlgo;
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public OutputStream getOutputStream() {
        return this.digest.getOutputStream();
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public byte[] getSignature() {
        byte[] digest = this.digest.getDigest();
        try {
            Signature createSignature = this.helper.createSignature(this.signer);
            createSignature.initSign(this.privateKey);
            createSignature.update(digest, 0, digest.length);
            byte[] bArr = this.parentDigest;
            createSignature.update(bArr, 0, bArr.length);
            return createSignature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public boolean isForSelfSigning() {
        return this.parentData == null;
    }
}
