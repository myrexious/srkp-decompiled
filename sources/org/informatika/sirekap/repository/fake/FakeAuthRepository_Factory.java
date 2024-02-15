package org.informatika.sirekap.repository.fake;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.KeyCloakApiInterface;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class FakeAuthRepository_Factory implements Factory<FakeAuthRepository> {
    private final Provider<SirekapApiInterface> apiProvider;
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<KeyCloakApiInterface> keyCloakApiInterfaceProvider;

    public FakeAuthRepository_Factory(Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider, Provider<SirekapApiInterface> apiProvider, Provider<KeyCloakApiInterface> keyCloakApiInterfaceProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.databaseProvider = databaseProvider;
        this.appExecutorsProvider = appExecutorsProvider;
        this.apiProvider = apiProvider;
        this.keyCloakApiInterfaceProvider = keyCloakApiInterfaceProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public FakeAuthRepository get() {
        return newInstance(this.databaseProvider.get(), this.appExecutorsProvider.get(), this.apiProvider.get(), this.keyCloakApiInterfaceProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static FakeAuthRepository_Factory create(Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider, Provider<SirekapApiInterface> apiProvider, Provider<KeyCloakApiInterface> keyCloakApiInterfaceProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new FakeAuthRepository_Factory(databaseProvider, appExecutorsProvider, apiProvider, keyCloakApiInterfaceProvider, encryptedSharedPreferencesProvider);
    }

    public static FakeAuthRepository newInstance(AppDatabase database, AppExecutors appExecutors, SirekapApiInterface api, KeyCloakApiInterface keyCloakApiInterface, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new FakeAuthRepository(database, appExecutors, api, keyCloakApiInterface, encryptedSharedPreferences);
    }
}
