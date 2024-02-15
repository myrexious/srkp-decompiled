package org.informatika.sirekap.repository;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class DefaultElectionRepository_Factory implements Factory<DefaultElectionRepository> {
    private final Provider<SirekapApiInterfaceUpload> apiUploadProvider;
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;

    public DefaultElectionRepository_Factory(Provider<AppExecutors> appExecutorsProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterfaceUpload> apiUploadProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.appExecutorsProvider = appExecutorsProvider;
        this.databaseProvider = databaseProvider;
        this.apiUploadProvider = apiUploadProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public DefaultElectionRepository get() {
        return newInstance(this.appExecutorsProvider.get(), this.databaseProvider.get(), this.apiUploadProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static DefaultElectionRepository_Factory create(Provider<AppExecutors> appExecutorsProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterfaceUpload> apiUploadProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new DefaultElectionRepository_Factory(appExecutorsProvider, databaseProvider, apiUploadProvider, encryptedSharedPreferencesProvider);
    }

    public static DefaultElectionRepository newInstance(AppExecutors appExecutors, AppDatabase database, SirekapApiInterfaceUpload apiUpload, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new DefaultElectionRepository(appExecutors, database, apiUpload, encryptedSharedPreferences);
    }
}
