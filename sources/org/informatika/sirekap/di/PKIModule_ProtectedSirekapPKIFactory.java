package org.informatika.sirekap.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Interceptor;
import org.informatika.sirekap.api.PKIApiInterface;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class PKIModule_ProtectedSirekapPKIFactory implements Factory<PKIApiInterface> {
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<Interceptor> loggingInterceptorProvider;
    private final PKIModule module;

    public PKIModule_ProtectedSirekapPKIFactory(PKIModule module, Provider<Interceptor> loggingInterceptorProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.module = module;
        this.loggingInterceptorProvider = loggingInterceptorProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public PKIApiInterface get() {
        return protectedSirekapPKI(this.module, this.loggingInterceptorProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static PKIModule_ProtectedSirekapPKIFactory create(PKIModule module, Provider<Interceptor> loggingInterceptorProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new PKIModule_ProtectedSirekapPKIFactory(module, loggingInterceptorProvider, encryptedSharedPreferencesProvider);
    }

    public static PKIApiInterface protectedSirekapPKI(PKIModule instance, Interceptor loggingInterceptor, EncryptedSharedPreferences encryptedSharedPreferences) {
        return (PKIApiInterface) Preconditions.checkNotNullFromProvides(instance.protectedSirekapPKI(loggingInterceptor, encryptedSharedPreferences));
    }
}
