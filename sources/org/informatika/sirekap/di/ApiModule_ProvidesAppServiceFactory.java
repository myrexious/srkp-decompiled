package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Interceptor;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class ApiModule_ProvidesAppServiceFactory implements Factory<SirekapApiInterface> {
    private final Provider<Interceptor> authInterceptorProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<Interceptor> loggingInterceptorProvider;
    private final ApiModule module;

    public ApiModule_ProvidesAppServiceFactory(ApiModule module, Provider<Interceptor> authInterceptorProvider, Provider<Interceptor> loggingInterceptorProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.module = module;
        this.authInterceptorProvider = authInterceptorProvider;
        this.loggingInterceptorProvider = loggingInterceptorProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public SirekapApiInterface get() {
        return providesAppService(this.module, this.authInterceptorProvider.get(), this.loggingInterceptorProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static ApiModule_ProvidesAppServiceFactory create(ApiModule module, Provider<Interceptor> authInterceptorProvider, Provider<Interceptor> loggingInterceptorProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new ApiModule_ProvidesAppServiceFactory(module, authInterceptorProvider, loggingInterceptorProvider, encryptedSharedPreferencesProvider);
    }

    public static SirekapApiInterface providesAppService(ApiModule instance, Interceptor authInterceptor, Interceptor loggingInterceptor, EncryptedSharedPreferences encryptedSharedPreferences) {
        return (SirekapApiInterface) Preconditions.checkNotNullFromProvides(instance.providesAppService(authInterceptor, loggingInterceptor, encryptedSharedPreferences));
    }
}
