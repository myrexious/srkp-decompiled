package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.informatika.sirekap.api.PKIApiInterface;
import org.informatika.sirekap.repository.PKIRepository;

/* loaded from: classes2.dex */
public final class PKIModule_ProvidePublicRepositoryFactory implements Factory<PKIRepository> {
    private final Provider<PKIApiInterface> apiProvider;
    private final PKIModule module;

    public PKIModule_ProvidePublicRepositoryFactory(PKIModule module, Provider<PKIApiInterface> apiProvider) {
        this.module = module;
        this.apiProvider = apiProvider;
    }

    @Override // javax.inject.Provider
    public PKIRepository get() {
        return providePublicRepository(this.module, this.apiProvider.get());
    }

    public static PKIModule_ProvidePublicRepositoryFactory create(PKIModule module, Provider<PKIApiInterface> apiProvider) {
        return new PKIModule_ProvidePublicRepositoryFactory(module, apiProvider);
    }

    public static PKIRepository providePublicRepository(PKIModule instance, PKIApiInterface api) {
        return (PKIRepository) Preconditions.checkNotNullFromProvides(instance.providePublicRepository(api));
    }
}
