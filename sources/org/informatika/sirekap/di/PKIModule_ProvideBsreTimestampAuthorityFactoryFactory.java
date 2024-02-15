package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.informatika.sirekap.repository.PKIRepository;
import org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority;

/* loaded from: classes2.dex */
public final class PKIModule_ProvideBsreTimestampAuthorityFactoryFactory implements Factory<BasicTimestampAuthority.Factory> {
    private final Provider<PKIRepository> apiProvider;
    private final PKIModule module;

    public PKIModule_ProvideBsreTimestampAuthorityFactoryFactory(PKIModule module, Provider<PKIRepository> apiProvider) {
        this.module = module;
        this.apiProvider = apiProvider;
    }

    @Override // javax.inject.Provider
    public BasicTimestampAuthority.Factory get() {
        return provideBsreTimestampAuthorityFactory(this.module, this.apiProvider.get());
    }

    public static PKIModule_ProvideBsreTimestampAuthorityFactoryFactory create(PKIModule module, Provider<PKIRepository> apiProvider) {
        return new PKIModule_ProvideBsreTimestampAuthorityFactoryFactory(module, apiProvider);
    }

    public static BasicTimestampAuthority.Factory provideBsreTimestampAuthorityFactory(PKIModule instance, PKIRepository api) {
        return (BasicTimestampAuthority.Factory) Preconditions.checkNotNullFromProvides(instance.provideBsreTimestampAuthorityFactory(api));
    }
}
