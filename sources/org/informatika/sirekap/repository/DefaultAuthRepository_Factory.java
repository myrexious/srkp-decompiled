package org.informatika.sirekap.repository;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.KeyCloakApiInterface;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class DefaultAuthRepository_Factory implements Factory<DefaultAuthRepository> {
    private final Provider<SirekapApiInterface> apiProvider;
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<KeyCloakApiInterface> keyCloakApiProvider;

    public DefaultAuthRepository_Factory(Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider, Provider<SirekapApiInterface> apiProvider, Provider<KeyCloakApiInterface> keyCloakApiProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.databaseProvider = databaseProvider;
        this.appExecutorsProvider = appExecutorsProvider;
        this.apiProvider = apiProvider;
        this.keyCloakApiProvider = keyCloakApiProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public DefaultAuthRepository get() {
        return newInstance(this.databaseProvider.get(), this.appExecutorsProvider.get(), this.apiProvider.get(), this.keyCloakApiProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static DefaultAuthRepository_Factory create(Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider, Provider<SirekapApiInterface> apiProvider, Provider<KeyCloakApiInterface> keyCloakApiProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new DefaultAuthRepository_Factory(databaseProvider, appExecutorsProvider, apiProvider, keyCloakApiProvider, encryptedSharedPreferencesProvider);
    }

    public static DefaultAuthRepository newInstance(AppDatabase database, AppExecutors appExecutors, SirekapApiInterface api, KeyCloakApiInterface keyCloakApi, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new DefaultAuthRepository(database, appExecutors, api, keyCloakApi, encryptedSharedPreferences);
    }
}
