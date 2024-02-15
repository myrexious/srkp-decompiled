package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Interceptor;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class ApiModule_ProvidesAppServiceUploadFactory implements Factory<SirekapApiInterfaceUpload> {
    private final Provider<Interceptor> authInterceptorProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<Interceptor> loggingInterceptorProvider;
    private final ApiModule module;

    public ApiModule_ProvidesAppServiceUploadFactory(ApiModule module, Provider<Interceptor> authInterceptorProvider, Provider<Interceptor> loggingInterceptorProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.module = module;
        this.authInterceptorProvider = authInterceptorProvider;
        this.loggingInterceptorProvider = loggingInterceptorProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public SirekapApiInterfaceUpload get() {
        return providesAppServiceUpload(this.module, this.authInterceptorProvider.get(), this.loggingInterceptorProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static ApiModule_ProvidesAppServiceUploadFactory create(ApiModule module, Provider<Interceptor> authInterceptorProvider, Provider<Interceptor> loggingInterceptorProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new ApiModule_ProvidesAppServiceUploadFactory(module, authInterceptorProvider, loggingInterceptorProvider, encryptedSharedPreferencesProvider);
    }

    public static SirekapApiInterfaceUpload providesAppServiceUpload(ApiModule instance, Interceptor authInterceptor, Interceptor loggingInterceptor, EncryptedSharedPreferences encryptedSharedPreferences) {
        return (SirekapApiInterfaceUpload) Preconditions.checkNotNullFromProvides(instance.providesAppServiceUpload(authInterceptor, loggingInterceptor, encryptedSharedPreferences));
    }
}
