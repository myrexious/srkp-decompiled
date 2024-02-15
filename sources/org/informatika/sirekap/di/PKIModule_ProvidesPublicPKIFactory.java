package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Interceptor;
import org.informatika.sirekap.api.PKIApiInterface;

/* loaded from: classes2.dex */
public final class PKIModule_ProvidesPublicPKIFactory implements Factory<PKIApiInterface> {
    private final Provider<Interceptor> loggingInterceptorProvider;
    private final PKIModule module;

    public PKIModule_ProvidesPublicPKIFactory(PKIModule module, Provider<Interceptor> loggingInterceptorProvider) {
        this.module = module;
        this.loggingInterceptorProvider = loggingInterceptorProvider;
    }

    @Override // javax.inject.Provider
    public PKIApiInterface get() {
        return providesPublicPKI(this.module, this.loggingInterceptorProvider.get());
    }

    public static PKIModule_ProvidesPublicPKIFactory create(PKIModule module, Provider<Interceptor> loggingInterceptorProvider) {
        return new PKIModule_ProvidesPublicPKIFactory(module, loggingInterceptorProvider);
    }

    public static PKIApiInterface providesPublicPKI(PKIModule instance, Interceptor loggingInterceptor) {
        return (PKIApiInterface) Preconditions.checkNotNullFromProvides(instance.providesPublicPKI(loggingInterceptor));
    }
}
