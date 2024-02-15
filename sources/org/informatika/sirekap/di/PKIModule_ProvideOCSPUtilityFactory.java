package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.informatika.sirekap.repository.PKIRepository;
import org.informatika.sirekap.support.security.pki.OCSPUtility;

/* loaded from: classes2.dex */
public final class PKIModule_ProvideOCSPUtilityFactory implements Factory<OCSPUtility> {
    private final Provider<PKIRepository> apiProvider;
    private final PKIModule module;

    public PKIModule_ProvideOCSPUtilityFactory(PKIModule module, Provider<PKIRepository> apiProvider) {
        this.module = module;
        this.apiProvider = apiProvider;
    }

    @Override // javax.inject.Provider
    public OCSPUtility get() {
        return provideOCSPUtility(this.module, this.apiProvider.get());
    }

    public static PKIModule_ProvideOCSPUtilityFactory create(PKIModule module, Provider<PKIRepository> apiProvider) {
        return new PKIModule_ProvideOCSPUtilityFactory(module, apiProvider);
    }

    public static OCSPUtility provideOCSPUtility(PKIModule instance, PKIRepository api) {
        return (OCSPUtility) Preconditions.checkNotNullFromProvides(instance.provideOCSPUtility(api));
    }
}
