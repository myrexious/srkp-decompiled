package org.informatika.sirekap.support.security.tsa;

import android.content.Context;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.security.cert.X509Certificate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.repository.PKIRepository;
import org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority;

/* compiled from: SectigoTimestampAuthority.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/support/security/tsa/SectigoTimestampAuthority;", "Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority;", "context", "Landroid/content/Context;", "pkiRepository", "Lorg/informatika/sirekap/repository/PKIRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/PKIRepository;)V", "validateCertificateChain", "", "certificate", "Ljava/security/cert/X509Certificate;", "Factory", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SectigoTimestampAuthority extends BasicTimestampAuthority {
    @Override // org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority
    protected void validateCertificateChain(X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "certificate");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SectigoTimestampAuthority(Context context, PKIRepository pkiRepository) {
        super("http://timestamp.sectigo.com/", pkiRepository);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pkiRepository, "pkiRepository");
    }

    /* compiled from: SectigoTimestampAuthority.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/security/tsa/SectigoTimestampAuthority$Factory;", "Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority$Factory;", "pkiRepository", "Lorg/informatika/sirekap/repository/PKIRepository;", "(Lorg/informatika/sirekap/repository/PKIRepository;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority;", "context", "Landroid/content/Context;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
            return new SectigoTimestampAuthority(context, this.pkiRepository);
        }
    }
}
