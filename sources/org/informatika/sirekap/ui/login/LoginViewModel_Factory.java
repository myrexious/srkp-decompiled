package org.informatika.sirekap.ui.login;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.CertmanRepository;
import org.informatika.sirekap.repository.UserRepository;
import org.informatika.sirekap.support.worker.login.LoginTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider;
    private final Provider<CertmanRepository> certmanRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<LoginTask> loginTaskProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<UserRepository> userRepositoryProvider;

    public LoginViewModel_Factory(Provider<AuthRepository> authRepositoryProvider, Provider<LoginTask> loginTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<UserRepository> userRepositoryProvider, Provider<CertmanRepository> certmanRepositoryProvider) {
        this.authRepositoryProvider = authRepositoryProvider;
        this.loginTaskProvider = loginTaskProvider;
        this.backgroundProcessRepositoryProvider = backgroundProcessRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.sharedPreferencesProvider = sharedPreferencesProvider;
        this.userRepositoryProvider = userRepositoryProvider;
        this.certmanRepositoryProvider = certmanRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public LoginViewModel get() {
        return newInstance(this.authRepositoryProvider.get(), this.loginTaskProvider.get(), this.backgroundProcessRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.sharedPreferencesProvider.get(), this.userRepositoryProvider.get(), this.certmanRepositoryProvider.get());
    }

    public static LoginViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider, Provider<LoginTask> loginTaskProvider, Provider<BackgroundProcessRepository> backgroundProcessRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<UserRepository> userRepositoryProvider, Provider<CertmanRepository> certmanRepositoryProvider) {
        return new LoginViewModel_Factory(authRepositoryProvider, loginTaskProvider, backgroundProcessRepositoryProvider, encryptedSharedPreferencesProvider, sharedPreferencesProvider, userRepositoryProvider, certmanRepositoryProvider);
    }

    public static LoginViewModel newInstance(AuthRepository authRepository, LoginTask loginTask, BackgroundProcessRepository backgroundProcessRepository, EncryptedSharedPreferences encryptedSharedPreferences, SharedPreferences sharedPreferences, UserRepository userRepository, CertmanRepository certmanRepository) {
        return new LoginViewModel(authRepository, loginTask, backgroundProcessRepository, encryptedSharedPreferences, sharedPreferences, userRepository, certmanRepository);
    }
}
