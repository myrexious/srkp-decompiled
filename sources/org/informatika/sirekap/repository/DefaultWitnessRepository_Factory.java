package org.informatika.sirekap.repository;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes2.dex */
public final class DefaultWitnessRepository_Factory implements Factory<DefaultWitnessRepository> {
    private final Provider<SirekapApiInterface> apiProvider;
    private final Provider<SirekapApiInterfaceUpload> apiUploadProvider;
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<AppDatabase> databaseProvider;
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;

    public DefaultWitnessRepository_Factory(Provider<Context> contextProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterface> apiProvider, Provider<SirekapApiInterfaceUpload> apiUploadProvider, Provider<AppExecutors> appExecutorsProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.contextProvider = contextProvider;
        this.databaseProvider = databaseProvider;
        this.apiProvider = apiProvider;
        this.apiUploadProvider = apiUploadProvider;
        this.appExecutorsProvider = appExecutorsProvider;
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public DefaultWitnessRepository get() {
        return newInstance(this.contextProvider.get(), this.databaseProvider.get(), this.apiProvider.get(), this.apiUploadProvider.get(), this.appExecutorsProvider.get(), this.encryptedSharedPreferencesProvider.get());
    }

    public static DefaultWitnessRepository_Factory create(Provider<Context> contextProvider, Provider<AppDatabase> databaseProvider, Provider<SirekapApiInterface> apiProvider, Provider<SirekapApiInterfaceUpload> apiUploadProvider, Provider<AppExecutors> appExecutorsProvider, Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new DefaultWitnessRepository_Factory(contextProvider, databaseProvider, apiProvider, apiUploadProvider, appExecutorsProvider, encryptedSharedPreferencesProvider);
    }

    public static DefaultWitnessRepository newInstance(Context context, AppDatabase database, SirekapApiInterface api, SirekapApiInterfaceUpload apiUpload, AppExecutors appExecutors, EncryptedSharedPreferences encryptedSharedPreferences) {
        return new DefaultWitnessRepository(context, database, api, apiUpload, appExecutors, encryptedSharedPreferences);
    }
}
