package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.informatika.sirekap.repository.PKIRepository;
import org.informatika.sirekap.support.security.pki.CRLUtility;

/* loaded from: classes2.dex */
public final class PKIModule_ProvideCRLUtilityFactory implements Factory<CRLUtility> {
    private final Provider<PKIRepository> apiProvider;
    private final PKIModule module;

    public PKIModule_ProvideCRLUtilityFactory(PKIModule module, Provider<PKIRepository> apiProvider) {
        this.module = module;
        this.apiProvider = apiProvider;
    }

    @Override // javax.inject.Provider
    public CRLUtility get() {
        return provideCRLUtility(this.module, this.apiProvider.get());
    }

    public static PKIModule_ProvideCRLUtilityFactory create(PKIModule module, Provider<PKIRepository> apiProvider) {
        return new PKIModule_ProvideCRLUtilityFactory(module, apiProvider);
    }

    public static CRLUtility provideCRLUtility(PKIModule instance, PKIRepository api) {
        return (CRLUtility) Preconditions.checkNotNullFromProvides(instance.provideCRLUtility(api));
    }
}
