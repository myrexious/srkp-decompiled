package org.informatika.sirekap.di;

import dagger.Module;
import dagger.Provides;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.support.security.pki.CRLUtility;
import org.informatika.sirekap.support.security.pki.OCSPUtility;
import org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority;

/* compiled from: PdfModule.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/di/PdfModule;", "", "()V", "providesPdfLtvFactory", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;", "timestampAuthority", "Lorg/informatika/sirekap/support/security/tsa/BasicTimestampAuthority$Factory;", "ocspUtility", "Lorg/informatika/sirekap/support/security/pki/OCSPUtility;", "crlUtility", "Lorg/informatika/sirekap/support/security/pki/CRLUtility;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public final class PdfModule {
    @Provides
    public final PdfLtv.Factory providesPdfLtvFactory(BasicTimestampAuthority.Factory timestampAuthority, OCSPUtility ocspUtility, CRLUtility crlUtility) {
        Intrinsics.checkNotNullParameter(timestampAuthority, "timestampAuthority");
        Intrinsics.checkNotNullParameter(ocspUtility, "ocspUtility");
        Intrinsics.checkNotNullParameter(crlUtility, "crlUtility");
        return new PdfLtv.Factory(timestampAuthority, crlUtility, ocspUtility, null, 8, null);
    }
}
