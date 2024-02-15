package org.informatika.sirekap.support.security.signature;

import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import java.security.cert.X509Certificate;
import kotlin.Metadata;

/* compiled from: Signer.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lorg/informatika/sirekap/support/security/signature/Signer;", "Lcom/tom_roush/pdfbox/pdmodel/interactive/digitalsignature/SignatureInterface;", "getSignerCertificate", "Ljava/security/cert/X509Certificate;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface Signer extends SignatureInterface {
    X509Certificate getSignerCertificate();
}
