package org.informatika.sirekap.support.security.signature;

import android.content.Context;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tom_roush.pdfbox.pdmodel.encryption.SecurityProvider;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.Time;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.informatika.sirekap.support.security.PKIFacade;

/* compiled from: LocalVerifier.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J&\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/support/security/signature/LocalVerifier;", "Lorg/informatika/sirekap/support/security/signature/Verifier;", "()V", "verify", "", "context", "Landroid/content/Context;", "signature", "", FirebaseAnalytics.Param.CONTENT, "verifyCertificate", "certs", "", "Ljava/security/cert/X509Certificate;", "signTime", "Lorg/bouncycastle/asn1/cms/Time;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LocalVerifier implements Verifier {
    private final void verifyCertificate(Context context, List<? extends X509Certificate> list, Time time) {
        for (X509Certificate x509Certificate : list) {
            x509Certificate.checkValidity(time.getDate());
        }
        PKIFacade.INSTANCE.checkCertificateChainOnly(PKIFacade.INSTANCE.getTrustAnchor(context), list);
    }

    @Override // org.informatika.sirekap.support.security.signature.Verifier
    public void verify(Context context, byte[] signature, byte[] content) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(content, "content");
        CMSSignedData cMSSignedData = new CMSSignedData(new CMSProcessableByteArray(content), signature);
        Collection<SignerInformation> signers = cMSSignedData.getSignerInfos().getSigners();
        if (signers.isEmpty()) {
            throw new VerifyError("No signers found in signature");
        }
        Collection<X509CertificateHolder> certificateHolder = cMSSignedData.getCertificates().getMatches(null);
        if (certificateHolder.isEmpty()) {
            throw new VerifyError("No certificates found in signature");
        }
        SignerInformation next = signers.iterator().next();
        Intrinsics.checkNotNullExpressionValue(certificateHolder, "certificateHolder");
        Collection<X509CertificateHolder> collection = certificateHolder;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        for (X509CertificateHolder x509CertificateHolder : collection) {
            arrayList.add(new JcaX509CertificateConverter().getCertificate(x509CertificateHolder));
        }
        ArrayList arrayList2 = arrayList;
        AttributeTable signedAttributes = next.getSignedAttributes();
        if (signedAttributes == null) {
            throw new VerifyError("Signature attribute is not found");
        }
        Attribute attribute = signedAttributes.get(CMSAttributes.signingTime);
        if (attribute == null) {
            throw new VerifyError("Time of signing is required");
        }
        Time time = Time.getInstance(attribute.getAttrValues().getObjectAt(0));
        Intrinsics.checkNotNullExpressionValue(time, "getInstance(signTime.attrValues.getObjectAt(0))");
        verifyCertificate(context, arrayList2, time);
        if (!next.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider(SecurityProvider.getProvider()).build((X509Certificate) CollectionsKt.first((List<? extends Object>) arrayList2)))) {
            throw new VerifyError("Document was tampered");
        }
    }
}
