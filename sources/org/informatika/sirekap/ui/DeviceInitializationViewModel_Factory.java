package org.informatika.sirekap.ui;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.repository.CertmanRepository;

/* loaded from: classes4.dex */
public final class DeviceInitializationViewModel_Factory implements Factory<DeviceInitializationViewModel> {
    private final Provider<AppDatabase> appDatabaseProvider;
    private final Provider<CertmanRepository> certmanRepositoryProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public DeviceInitializationViewModel_Factory(Provider<SharedPreferences> sharedPreferencesProvider, Provider<AppDatabase> appDatabaseProvider, Provider<CertmanRepository> certmanRepositoryProvider) {
        this.sharedPreferencesProvider = sharedPreferencesProvider;
        this.appDatabaseProvider = appDatabaseProvider;
        this.certmanRepositoryProvider = certmanRepositoryProvider;
    }

    @Override // javax.inject.Provider
    public DeviceInitializationViewModel get() {
        return newInstance(this.sharedPreferencesProvider.get(), this.appDatabaseProvider.get(), this.certmanRepositoryProvider.get());
    }

    public static DeviceInitializationViewModel_Factory create(Provider<SharedPreferences> sharedPreferencesProvider, Provider<AppDatabase> appDatabaseProvider, Provider<CertmanRepository> certmanRepositoryProvider) {
        return new DeviceInitializationViewModel_Factory(sharedPreferencesProvider, appDatabaseProvider, certmanRepositoryProvider);
    }

    public static DeviceInitializationViewModel newInstance(SharedPreferences sharedPreferences, AppDatabase appDatabase, CertmanRepository certmanRepository) {
        return new DeviceInitializationViewModel(sharedPreferences, appDatabase, certmanRepository);
    }
}
