package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.support.security.pki.CRLUtility;
import org.informatika.sirekap.support.security.pki.OCSPUtility;
import org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority;

/* loaded from: classes2.dex */
public final class PdfModule_ProvidesPdfLtvFactoryFactory implements Factory<PdfLtv.Factory> {
    private final Provider<CRLUtility> crlUtilityProvider;
    private final PdfModule module;
    private final Provider<OCSPUtility> ocspUtilityProvider;
    private final Provider<BasicTimestampAuthority.Factory> timestampAuthorityProvider;

    public PdfModule_ProvidesPdfLtvFactoryFactory(PdfModule module, Provider<BasicTimestampAuthority.Factory> timestampAuthorityProvider, Provider<OCSPUtility> ocspUtilityProvider, Provider<CRLUtility> crlUtilityProvider) {
        this.module = module;
        this.timestampAuthorityProvider = timestampAuthorityProvider;
        this.ocspUtilityProvider = ocspUtilityProvider;
        this.crlUtilityProvider = crlUtilityProvider;
    }

    @Override // javax.inject.Provider
    public PdfLtv.Factory get() {
        return providesPdfLtvFactory(this.module, this.timestampAuthorityProvider.get(), this.ocspUtilityProvider.get(), this.crlUtilityProvider.get());
    }

    public static PdfModule_ProvidesPdfLtvFactoryFactory create(PdfModule module, Provider<BasicTimestampAuthority.Factory> timestampAuthorityProvider, Provider<OCSPUtility> ocspUtilityProvider, Provider<CRLUtility> crlUtilityProvider) {
        return new PdfModule_ProvidesPdfLtvFactoryFactory(module, timestampAuthorityProvider, ocspUtilityProvider, crlUtilityProvider);
    }

    public static PdfLtv.Factory providesPdfLtvFactory(PdfModule instance, BasicTimestampAuthority.Factory timestampAuthority, OCSPUtility ocspUtility, CRLUtility crlUtility) {
        return (PdfLtv.Factory) Preconditions.checkNotNullFromProvides(instance.providesPdfLtvFactory(timestampAuthority, ocspUtility, crlUtility));
    }
}
