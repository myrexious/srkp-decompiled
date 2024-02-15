package org.informatika.sirekap.support.security.key;

import java.io.StringWriter;
import java.security.PrivateKey;
import java.security.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

/* compiled from: CSRGenerator.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\tR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/support/security/key/CSRGenerator;", "", "privateKey", "Ljava/security/PrivateKey;", "publicKey", "Ljava/security/PublicKey;", "x500Name", "Lorg/bouncycastle/asn1/x500/X500Name;", "algorithm", "", "(Ljava/security/PrivateKey;Ljava/security/PublicKey;Lorg/bouncycastle/asn1/x500/X500Name;Ljava/lang/String;)V", "writePem", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CSRGenerator {
    private final String algorithm;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final X500Name x500Name;

    public CSRGenerator(PrivateKey privateKey, PublicKey publicKey, X500Name x500Name, String algorithm) {
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(x500Name, "x500Name");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.x500Name = x500Name;
        this.algorithm = algorithm;
    }

    public final String writePem() {
        PKCS10CertificationRequest build = new JcaPKCS10CertificationRequestBuilder(this.x500Name, this.publicKey).build(new JcaContentSignerBuilder(this.algorithm).build(this.privateKey));
        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(stringWriter);
        jcaPEMWriter.writeObject(build);
        jcaPEMWriter.flush();
        jcaPEMWriter.close();
        String stringWriter2 = stringWriter.toString();
        Intrinsics.checkNotNullExpressionValue(stringWriter2, "writter.toString()");
        return stringWriter2;
    }
}
