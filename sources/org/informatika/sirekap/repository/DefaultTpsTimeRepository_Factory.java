package org.informatika.sirekap.repository;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class DefaultTpsTimeRepository_Factory implements Factory<DefaultTpsTimeRepository> {
    private final Provider<SirekapApiInterface> apiProvider;
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;

    public DefaultTpsTimeRepository_Factory(Provider<AppExecutors> appExecutorsProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterface> apiProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.appExecutorsProvider = appExecutorsProvider;
        this.databaseProvider = databaseProvider;
        this.apiProvider = apiProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public DefaultTpsTimeRepository get() {
        return newInstance(this.appExecutorsProvider.get(), this.databaseProvider.get(), this.apiProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static DefaultTpsTimeRepository_Factory create(Provider<AppExecutors> appExecutorsProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterface> apiProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new DefaultTpsTimeRepository_Factory(appExecutorsProvider, databaseProvider, apiProvider, encryptedSharedPreferencesProvider);
    }

    public static DefaultTpsTimeRepository newInstance(AppExecutors appExecutors, AppDatabase database, SirekapApiInterface api, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new DefaultTpsTimeRepository(appExecutors, database, api, encryptedSharedPreferences);
    }
}
