package org.informatika.sirekap.support.security.signature;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.EllipticCurve;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;

/* compiled from: LocalSigner.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007B%\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB\u001f\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\t¢\u0006\u0002\u0010\u0010J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/support/security/signature/LocalSigner;", "Lorg/informatika/sirekap/support/security/signature/Signer;", "privateKey", "Ljava/security/PrivateKey;", "certificateChain", "", "Ljava/security/cert/Certificate;", "(Ljava/security/PrivateKey;Ljava/util/List;)V", "algorithm", "", "(Ljava/security/PrivateKey;Ljava/util/List;Ljava/lang/String;)V", "keystore", "Ljava/security/KeyStore;", "passkey", "", "alias", "(Ljava/security/KeyStore;[CLjava/lang/String;)V", "signatureAlgorithm", "checkValidity", "", "getAlgorithm", "getSignerCertificate", "Ljava/security/cert/X509Certificate;", "sign", "", FirebaseAnalytics.Param.CONTENT, "Ljava/io/InputStream;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LocalSigner implements Signer {
    private final List<Certificate> certificateChain;
    private final PrivateKey privateKey;
    private String signatureAlgorithm;

    /* JADX WARN: Multi-variable type inference failed */
    public LocalSigner(PrivateKey privateKey, List<? extends Certificate> certificateChain) {
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(certificateChain, "certificateChain");
        this.privateKey = privateKey;
        this.certificateChain = certificateChain;
        getAlgorithm();
        checkValidity();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LocalSigner(PrivateKey privateKey, List<? extends Certificate> certificateChain, String algorithm) {
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(certificateChain, "certificateChain");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.privateKey = privateKey;
        this.certificateChain = certificateChain;
        this.signatureAlgorithm = algorithm;
        checkValidity();
    }

    public LocalSigner(KeyStore keystore, char[] passkey, String alias) {
        Intrinsics.checkNotNullParameter(keystore, "keystore");
        Intrinsics.checkNotNullParameter(passkey, "passkey");
        Intrinsics.checkNotNullParameter(alias, "alias");
        Key key = keystore.getKey(alias, passkey);
        Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.PrivateKey");
        this.privateKey = (PrivateKey) key;
        Certificate[] certificateChain = keystore.getCertificateChain(alias);
        Intrinsics.checkNotNullExpressionValue(certificateChain, "keystore.getCertificateChain(alias)");
        this.certificateChain = ArraysKt.asList(certificateChain);
        getAlgorithm();
        checkValidity();
    }

    private final void getAlgorithm() {
        String algorithm = this.privateKey.getAlgorithm();
        if (Intrinsics.areEqual(algorithm, "EC")) {
            PrivateKey privateKey = this.privateKey;
            Intrinsics.checkNotNull(privateKey, "null cannot be cast to non-null type java.security.interfaces.ECPrivateKey");
            EllipticCurve curve = ((ECPrivateKey) privateKey).getParams().getCurve();
            int fieldSize = curve.getField().getFieldSize();
            if (fieldSize == 224) {
                this.signatureAlgorithm = "SHA256withECDSA";
            } else if (fieldSize == 256) {
                this.signatureAlgorithm = "SHA256withECDSA";
            } else if (fieldSize == 384) {
                this.signatureAlgorithm = "SHA384withECDSA";
            } else if (fieldSize == 512) {
                this.signatureAlgorithm = "SHA512withECDSA";
            } else {
                throw new IllegalArgumentException("unsupported eliptic curve, found size " + curve.getField().getFieldSize());
            }
        } else if (Intrinsics.areEqual(algorithm, "RSA")) {
            PrivateKey privateKey2 = this.privateKey;
            Intrinsics.checkNotNull(privateKey2, "null cannot be cast to non-null type java.security.interfaces.RSAPrivateKey");
            RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) privateKey2;
            int bitLength = rSAPrivateKey.getModulus().bitLength() / 8;
            if (bitLength == 224) {
                this.signatureAlgorithm = "SHA224withRSA";
            } else if (bitLength == 256) {
                this.signatureAlgorithm = "SHA256WithRSA";
            } else if (bitLength == 384) {
                this.signatureAlgorithm = "SHA384withRSA";
            } else if (bitLength == 512) {
                this.signatureAlgorithm = "SHA512WithRSA";
            } else {
                throw new IllegalArgumentException("unsupported RSA type, found size " + rSAPrivateKey.getModulus().bitLength());
            }
        } else {
            throw new UnsupportedOperationException("algorithm " + this.privateKey.getAlgorithm() + " is not supported");
        }
    }

    private final void checkValidity() {
        for (Certificate certificate : this.certificateChain) {
            if (certificate instanceof X509Certificate) {
                ((X509Certificate) certificate).checkValidity();
            }
        }
    }

    @Override // org.informatika.sirekap.support.security.signature.Signer
    public X509Certificate getSignerCertificate() {
        Certificate certificate = this.certificateChain.get(0);
        Intrinsics.checkNotNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
        return (X509Certificate) certificate;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface
    public byte[] sign(InputStream inputStream) {
        if (inputStream == null) {
            throw new IOException("input stream is empty");
        }
        CMSSignedDataGenerator cMSSignedDataGenerator = new CMSSignedDataGenerator();
        String str = this.signatureAlgorithm;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("signatureAlgorithm");
            str = null;
        }
        cMSSignedDataGenerator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().build()).build(new JcaContentSignerBuilder(str).build(this.privateKey), getSignerCertificate()));
        cMSSignedDataGenerator.addCertificates(new JcaCertStore(this.certificateChain));
        byte[] encoded = cMSSignedDataGenerator.generate(new CMSProcessableByteArray(ByteStreamsKt.readBytes(inputStream))).getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "signedData.encoded");
        return encoded;
    }
}
