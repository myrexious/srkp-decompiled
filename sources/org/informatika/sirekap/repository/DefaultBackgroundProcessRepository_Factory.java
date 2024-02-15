package org.informatika.sirekap.repository;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class DefaultBackgroundProcessRepository_Factory implements Factory<DefaultBackgroundProcessRepository> {
    private final Provider<SirekapApiInterface> apiProvider;
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;

    public DefaultBackgroundProcessRepository_Factory(Provider<SirekapApiInterface> apiProvider, Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.apiProvider = apiProvider;
        this.databaseProvider = databaseProvider;
        this.appExecutorsProvider = appExecutorsProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public DefaultBackgroundProcessRepository get() {
        return newInstance(this.apiProvider.get(), this.databaseProvider.get(), this.appExecutorsProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static DefaultBackgroundProcessRepository_Factory create(Provider<SirekapApiInterface> apiProvider, Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new DefaultBackgroundProcessRepository_Factory(apiProvider, databaseProvider, appExecutorsProvider, encryptedSharedPreferencesProvider);
    }

    public static DefaultBackgroundProcessRepository newInstance(SirekapApiInterface api, AppDatabase database, AppExecutors appExecutors, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new DefaultBackgroundProcessRepository(api, database, appExecutors, encryptedSharedPreferences);
    }
}
