package org.informatika.sirekap;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.UserRepository;
import org.informatika.sirekap.support.worker.refreshToken.RefreshTokenTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class MainViewModel_Factory implements Factory<MainViewModel> {
    private final Provider<AppDatabase> appDatabaseProvider;
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<RefreshTokenTask> refreshTokenTaskProvider;
    private final Provider<UserRepository> userRepositoryProvider;

    public MainViewModel_Factory(Provider<AuthRepository> authRepositoryProvider, Provider<UserRepository> userRepositoryProvider, Provider<RefreshTokenTask> refreshTokenTaskProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<AppDatabase> appDatabaseProvider) {
        this.authRepositoryProvider = authRepositoryProvider;
        this.userRepositoryProvider = userRepositoryProvider;
        this.refreshTokenTaskProvider = refreshTokenTaskProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.appDatabaseProvider = appDatabaseProvider;
    }

    @Override // javax.inject.Provider
    public MainViewModel get() {
        return newInstance(this.authRepositoryProvider.get(), this.userRepositoryProvider.get(), this.refreshTokenTaskProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.appDatabaseProvider.get());
    }

    public static MainViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider, Provider<UserRepository> userRepositoryProvider, Provider<RefreshTokenTask> refreshTokenTaskProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<AppDatabase> appDatabaseProvider) {
        return new MainViewModel_Factory(authRepositoryProvider, userRepositoryProvider, refreshTokenTaskProvider, encryptedSharedPreferencesProvider, appDatabaseProvider);
    }

    public static MainViewModel newInstance(AuthRepository authRepository, UserRepository userRepository, RefreshTokenTask refreshTokenTask, EncryptedSharedPreferences encryptedSharedPreferences, AppDatabase appDatabase) {
        return new MainViewModel(authRepository, userRepository, refreshTokenTask, encryptedSharedPreferences, appDatabase);
    }
}
