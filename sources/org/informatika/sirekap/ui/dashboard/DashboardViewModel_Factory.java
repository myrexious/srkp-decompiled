package org.informatika.sirekap.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.CertmanRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.UserRepository;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<CertmanRepository> certmanRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DefaultElectionRepository> electionRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<UserRepository> userRepositoryProvider;

    public DashboardViewModel_Factory(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<AuthRepository> authRepositoryProvider, Provider<CertmanRepository> certmanRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<UserRepository> userRepositoryProvider) {
        this.contextProvider = contextProvider;
        this.electionRepositoryProvider = electionRepositoryProvider;
        this.authRepositoryProvider = authRepositoryProvider;
        this.certmanRepositoryProvider = certmanRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.sharedPreferencesProvider = sharedPreferencesProvider;
        this.userRepositoryProvider = userRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public DashboardViewModel get() {
        return newInstance(this.contextProvider.get(), this.electionRepositoryProvider.get(), this.authRepositoryProvider.get(), this.certmanRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.sharedPreferencesProvider.get(), this.userRepositoryProvider.get());
    }

    public static DashboardViewModel_Factory create(Provider<Context> contextProvider, Provider<DefaultElectionRepository> electionRepositoryProvider, Provider<AuthRepository> authRepositoryProvider, Provider<CertmanRepository> certmanRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<UserRepository> userRepositoryProvider) {
        return new DashboardViewModel_Factory(contextProvider, electionRepositoryProvider, authRepositoryProvider, certmanRepositoryProvider, encryptedSharedPreferencesProvider, sharedPreferencesProvider, userRepositoryProvider);
    }

    public static DashboardViewModel newInstance(Context context, DefaultElectionRepository electionRepository, AuthRepository authRepository, CertmanRepository certmanRepository, EncryptedSharedPreferences encryptedSharedPreferences, SharedPreferences sharedPreferences, UserRepository userRepository) {
        return new DashboardViewModel(context, electionRepository, authRepository, certmanRepository, encryptedSharedPreferences, sharedPreferences, userRepository);
    }
}
