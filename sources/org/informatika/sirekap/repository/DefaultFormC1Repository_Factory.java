package org.informatika.sirekap.repository;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class DefaultFormC1Repository_Factory implements Factory<DefaultFormC1Repository> {
    private final Provider<SirekapApiInterface> apiProvider;
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<AppDatabase> databaseProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public DefaultFormC1Repository_Factory(Provider<Context> contextProvider, Provider<SirekapApiInterface> apiProvider, Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<SharedPreferences> sharedPreferencesProvider) {
        this.contextProvider = contextProvider;
        this.apiProvider = apiProvider;
        this.databaseProvider = databaseProvider;
        this.appExecutorsProvider = appExecutorsProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
        this.sharedPreferencesProvider = sharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public DefaultFormC1Repository get() {
        return newInstance(this.contextProvider.get(), this.apiProvider.get(), this.databaseProvider.get(), this.appExecutorsProvider.get(), this.encryptedSharedPreferencesProvider.get(), this.sharedPreferencesProvider.get());
    }

    public static DefaultFormC1Repository_Factory create(Provider<Context> contextProvider, Provider<SirekapApiInterface> apiProvider, Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider, Provider<SharedPreferences> sharedPreferencesProvider) {
        return new DefaultFormC1Repository_Factory(contextProvider, apiProvider, databaseProvider, appExecutorsProvider, encryptedSharedPreferencesProvider, sharedPreferencesProvider);
    }

    public static DefaultFormC1Repository newInstance(Context context, SirekapApiInterface api, AppDatabase database, AppExecutors appExecutors, EncryptedSharedPreferences encryptedSharedPreferences, SharedPreferences sharedPreferences) {
        return new DefaultFormC1Repository(context, api, database, appExecutors, encryptedSharedPreferences, sharedPreferences);
    }
}
