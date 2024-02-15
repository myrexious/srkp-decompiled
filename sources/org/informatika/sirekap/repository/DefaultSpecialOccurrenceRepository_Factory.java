package org.informatika.sirekap.repository;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class DefaultSpecialOccurrenceRepository_Factory implements Factory<DefaultSpecialOccurrenceRepository> {
    private final Provider<SirekapApiInterfaceUpload> apiUploadProvider;
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;

    public DefaultSpecialOccurrenceRepository_Factory(Provider<AppExecutors> appExecutorsProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterfaceUpload> apiUploadProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.appExecutorsProvider = appExecutorsProvider;
        this.databaseProvider = databaseProvider;
        this.apiUploadProvider = apiUploadProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public DefaultSpecialOccurrenceRepository get() {
        return newInstance(this.appExecutorsProvider.get(), this.databaseProvider.get(), this.apiUploadProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static DefaultSpecialOccurrenceRepository_Factory create(Provider<AppExecutors> appExecutorsProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterfaceUpload> apiUploadProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new DefaultSpecialOccurrenceRepository_Factory(appExecutorsProvider, databaseProvider, apiUploadProvider, encryptedSharedPreferencesProvider);
    }

    public static DefaultSpecialOccurrenceRepository newInstance(AppExecutors appExecutors, AppDatabase database, SirekapApiInterfaceUpload apiUpload, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new DefaultSpecialOccurrenceRepository(appExecutors, database, apiUpload, encryptedSharedPreferences);
    }
}
