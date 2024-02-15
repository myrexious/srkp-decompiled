package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Interceptor;
import org.informatika.sirekap.api.KeyCloakApiInterface;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class ApiModule_ProvidesKeyCloakApiFactory implements Factory<KeyCloakApiInterface> {
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<Interceptor> loggingInterceptorProvider;
    private final ApiModule module;

    public ApiModule_ProvidesKeyCloakApiFactory(ApiModule module, Provider<Interceptor> loggingInterceptorProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.module = module;
        this.loggingInterceptorProvider = loggingInterceptorProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public KeyCloakApiInterface get() {
        return providesKeyCloakApi(this.module, this.loggingInterceptorProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static ApiModule_ProvidesKeyCloakApiFactory create(ApiModule module, Provider<Interceptor> loggingInterceptorProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new ApiModule_ProvidesKeyCloakApiFactory(module, loggingInterceptorProvider, encryptedSharedPreferencesProvider);
    }

    public static KeyCloakApiInterface providesKeyCloakApi(ApiModule instance, Interceptor loggingInterceptor, EncryptedSharedPreferences encryptedSharedPreferences) {
        return (KeyCloakApiInterface) Preconditions.checkNotNullFromProvides(instance.providesKeyCloakApi(loggingInterceptor, encryptedSharedPreferences));
    }
}
