package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.informatika.sirekap.repository.PKIRepository;
import org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority;

/* loaded from: classes2.dex */
public final class PKIModule_ProvideSectigoTimestampAuthorityFactoryFactory implements Factory<BasicTimestampAuthority.Factory> {
    private final Provider<PKIRepository> apiProvider;
    private final PKIModule module;

    public PKIModule_ProvideSectigoTimestampAuthorityFactoryFactory(PKIModule module, Provider<PKIRepository> apiProvider) {
        this.module = module;
        this.apiProvider = apiProvider;
    }

    @Override // javax.inject.Provider
    public BasicTimestampAuthority.Factory get() {
        return provideSectigoTimestampAuthorityFactory(this.module, this.apiProvider.get());
    }

    public static PKIModule_ProvideSectigoTimestampAuthorityFactoryFactory create(PKIModule module, Provider<PKIRepository> apiProvider) {
        return new PKIModule_ProvideSectigoTimestampAuthorityFactoryFactory(module, apiProvider);
    }

    public static BasicTimestampAuthority.Factory provideSectigoTimestampAuthorityFactory(PKIModule instance, PKIRepository api) {
        return (BasicTimestampAuthority.Factory) Preconditions.checkNotNullFromProvides(instance.provideSectigoTimestampAuthorityFactory(api));
    }
}
