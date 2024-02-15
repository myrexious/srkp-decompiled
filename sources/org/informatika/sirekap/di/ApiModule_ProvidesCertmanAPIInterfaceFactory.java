package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Interceptor;
import org.informatika.sirekap.api.CertmanAPIInterface;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class ApiModule_ProvidesCertmanAPIInterfaceFactory implements Factory<CertmanAPIInterface> {
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<Interceptor> loggingInterceptorProvider;
    private final ApiModule module;

    public ApiModule_ProvidesCertmanAPIInterfaceFactory(ApiModule module, Provider<Interceptor> loggingInterceptorProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.module = module;
        this.loggingInterceptorProvider = loggingInterceptorProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public CertmanAPIInterface get() {
        return providesCertmanAPIInterface(this.module, this.loggingInterceptorProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static ApiModule_ProvidesCertmanAPIInterfaceFactory create(ApiModule module, Provider<Interceptor> loggingInterceptorProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new ApiModule_ProvidesCertmanAPIInterfaceFactory(module, loggingInterceptorProvider, encryptedSharedPreferencesProvider);
    }

    public static CertmanAPIInterface providesCertmanAPIInterface(ApiModule instance, Interceptor loggingInterceptor, EncryptedSharedPreferences encryptedSharedPreferences) {
        return (CertmanAPIInterface) Preconditions.checkNotNullFromProvides(instance.providesCertmanAPIInterface(loggingInterceptor, encryptedSharedPreferences));
    }
}
