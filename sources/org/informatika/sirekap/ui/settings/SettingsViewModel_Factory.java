package org.informatika.sirekap.ui.settings;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
    private final Provider<AppDatabase> appDatabaseProvider;
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;

    public SettingsViewModel_Factory(Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<AuthRepository> authRepositoryProvider, Provider<AppDatabase> appDatabaseProvider) {
        this.electionRepositoryProvider = electionRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.authRepositoryProvider = authRepositoryProvider;
        this.appDatabaseProvider = appDatabaseProvider;
    }

    @Override // javax.inject.Provider
    public SettingsViewModel get() {
        return newInstance(this.electionRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.authRepositoryProvider.get(), this.appDatabaseProvider.get());
    }

    public static SettingsViewModel_Factory create(Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<AuthRepository> authRepositoryProvider, Provider<AppDatabase> appDatabaseProvider) {
        return new SettingsViewModel_Factory(electionRepositoryProvider, encryptedSharedPreferencesProvider, authRepositoryProvider, appDatabaseProvider);
    }

    public static SettingsViewModel newInstance(DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, AuthRepository authRepository, AppDatabase appDatabase) {
        return new SettingsViewModel(electionRepository, encryptedSharedPreferences, authRepository, appDatabase);
    }
}
