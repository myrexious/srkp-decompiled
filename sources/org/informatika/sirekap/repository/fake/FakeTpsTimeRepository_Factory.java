package org.informatika.sirekap.repository.fake;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class FakeTpsTimeRepository_Factory implements Factory<FakeTpsTimeRepository> {
    private final Provider<SirekapApiInterface> apiProvider;
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;

    public FakeTpsTimeRepository_Factory(Provider<AppExecutors> appExecutorsProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterface> apiProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.appExecutorsProvider = appExecutorsProvider;
        this.databaseProvider = databaseProvider;
        this.apiProvider = apiProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public FakeTpsTimeRepository get() {
        return newInstance(this.appExecutorsProvider.get(), this.databaseProvider.get(), this.apiProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static FakeTpsTimeRepository_Factory create(Provider<AppExecutors> appExecutorsProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterface> apiProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new FakeTpsTimeRepository_Factory(appExecutorsProvider, databaseProvider, apiProvider, encryptedSharedPreferencesProvider);
    }

    public static FakeTpsTimeRepository newInstance(AppExecutors appExecutors, AppDatabase database, SirekapApiInterface api, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new FakeTpsTimeRepository(appExecutors, database, api, encryptedSharedPreferences);
    }
}
