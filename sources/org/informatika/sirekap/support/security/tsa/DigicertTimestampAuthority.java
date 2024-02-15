package org.informatika.sirekap.support.security.tsa;

import android.content.Context;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.repository.PKIRepository;
import org.informatika.sirekap.support.security.PKIFacade;
import org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority;

/* compiled from: DigicertTimestampAuthority.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/support/security/tsa/DigicertTimestampAuthority;", "Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority;", "context", "Landroid/content/Context;", "pkiRepository", "Lorg/informatika/sirekap/repository/PKIRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/PKIRepository;)V", "certificateChain", "", "Ljava/security/cert/Certificate;", "trustAnchor", "Ljava/security/cert/TrustAnchor;", "validateCertificateChain", "", "certificate", "Ljava/security/cert/X509Certificate;", "Factory", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DigicertTimestampAuthority extends BasicTimestampAuthority {
    private final List<Certificate> certificateChain;
    private final List<TrustAnchor> trustAnchor;

    @Override // org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority
    protected void validateCertificateChain(X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "certificate");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DigicertTimestampAuthority(Context context, PKIRepository pkiRepository) {
        super("http://timestamp.digicert.com/", pkiRepository);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pkiRepository, "pkiRepository");
        List<Certificate> certificateChain = PKIFacade.INSTANCE.getCertificateChain(context, "trustca/digicert.tsa.pem");
        this.certificateChain = certificateChain;
        List<Certificate> list = certificateChain;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Certificate certificate : list) {
            PKIFacade pKIFacade = PKIFacade.INSTANCE;
            Intrinsics.checkNotNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            arrayList.add(pKIFacade.generateTrustAnchor((X509Certificate) certificate));
        }
        this.trustAnchor = arrayList;
    }

    /* compiled from: DigicertTimestampAuthority.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/security/tsa/DigicertTimestampAuthority$Factory;", "Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority$Factory;", "pkiRepository", "Lorg/informatika/sirekap/repository/PKIRepository;", "(Lorg/informatika/sirekap/repository/PKIRepository;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority;", "context", "Landroid/content/Context;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Factory extends BasicTimestampAuthority.Factory {
        private final PKIRepository pkiRepository;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Factory(PKIRepository pkiRepository) {
            super(pkiRepository);
            Intrinsics.checkNotNullParameter(pkiRepository, "pkiRepository");
            this.pkiRepository = pkiRepository;
        }

        @Override // org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority.Factory
        public BasicTimestampAuthority build(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new DigicertTimestampAuthority(context, this.pkiRepository);
        }
    }
}
