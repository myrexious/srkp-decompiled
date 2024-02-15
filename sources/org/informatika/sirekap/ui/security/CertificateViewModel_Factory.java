package org.informatika.sirekap.ui.security;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.CertmanRepository;
import org.informatika.sirekap.repository.UserRepository;
import org.informatika.sirekap.support.worker.security.CertificateCheckerTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class CertificateViewModel_Factory implements Factory<CertificateViewModel> {
    private final Provider<AppDatabase> appDatabaseProvider;
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<CertificateCheckerTask> certificateCheckerWorkerProvider;
    private final Provider<CertmanRepository> certmanRepositoryProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<UserRepository> userRepositoryProvider;

    public CertificateViewModel_Factory(Provider<CertmanRepository> certmanRepositoryProvider, Provider<AuthRepository> authRepositoryProvider, Provider<AppDatabase> appDatabaseProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<UserRepository> userRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<CertificateCheckerTask> certificateCheckerWorkerProvider) {
        this.certmanRepositoryProvider = certmanRepositoryProvider;
        this.authRepositoryProvider = authRepositoryProvider;
        this.appDatabaseProvider = appDatabaseProvider;
        this.sharedPreferencesProvider = sharedPreferencesProvider;
        this.userRepositoryProvider = userRepositoryProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.certificateCheckerWorkerProvider = certificateCheckerWorkerProvider;
    }

    @Override // javax.inject.Provider
    public CertificateViewModel get() {
        return newInstance(this.certmanRepositoryProvider.get(), this.authRepositoryProvider.get(), this.appDatabaseProvider.get(), this.sharedPreferencesProvider.get(), this.userRepositoryProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.certificateCheckerWorkerProvider.get());
    }

    public static CertificateViewModel_Factory create(Provider<CertmanRepository> certmanRepositoryProvider, Provider<AuthRepository> authRepositoryProvider, Provider<AppDatabase> appDatabaseProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<UserRepository> userRepositoryProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<CertificateCheckerTask> certificateCheckerWorkerProvider) {
        return new CertificateViewModel_Factory(certmanRepositoryProvider, authRepositoryProvider, appDatabaseProvider, sharedPreferencesProvider, userRepositoryProvider, encryptedSharedPreferencesProvider, certificateCheckerWorkerProvider);
    }

    public static CertificateViewModel newInstance(CertmanRepository certmanRepository, AuthRepository authRepository, AppDatabase appDatabase, SharedPreferences sharedPreferences, UserRepository userRepository, EncryptedSharedPreferences encryptedSharedPreferences, CertificateCheckerTask certificateCheckerWorker) {
        return new CertificateViewModel(certmanRepository, authRepository, appDatabase, sharedPreferences, userRepository, encryptedSharedPreferences, certificateCheckerWorker);
    }
}
