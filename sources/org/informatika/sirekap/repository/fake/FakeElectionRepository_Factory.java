package org.informatika.sirekap.repository.fake;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class FakeElectionRepository_Factory implements Factory<FakeElectionRepository> {
    private final Provider<SirekapApiInterfaceUpload> apiUploadProvider;
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;

    public FakeElectionRepository_Factory(Provider<AppExecutors> appExecutorsProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterfaceUpload> apiUploadProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.appExecutorsProvider = appExecutorsProvider;
        this.databaseProvider = databaseProvider;
        this.apiUploadProvider = apiUploadProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public FakeElectionRepository get() {
        return newInstance(this.appExecutorsProvider.get(), this.databaseProvider.get(), this.apiUploadProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static FakeElectionRepository_Factory create(Provider<AppExecutors> appExecutorsProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterfaceUpload> apiUploadProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new FakeElectionRepository_Factory(appExecutorsProvider, databaseProvider, apiUploadProvider, encryptedSharedPreferencesProvider);
    }

    public static FakeElectionRepository newInstance(AppExecutors appExecutors, AppDatabase database, SirekapApiInterfaceUpload apiUpload, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new FakeElectionRepository(appExecutors, database, apiUpload, encryptedSharedPreferences);
    }
}
