package org.informatika.sirekap.repository;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;

/* loaded from: classes2.dex */
public final class DefaultUploadFileAttemptRepository_Factory implements Factory<DefaultUploadFileAttemptRepository> {
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;

    public DefaultUploadFileAttemptRepository_Factory(Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider) {
        this.databaseProvider = databaseProvider;
        this.appExecutorsProvider = appExecutorsProvider;
    }

    @Override // javax.inject.Provider
    public DefaultUploadFileAttemptRepository get() {
        return newInstance(this.databaseProvider.get(), this.appExecutorsProvider.get());
    }

    public static DefaultUploadFileAttemptRepository_Factory create(Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider) {
        return new DefaultUploadFileAttemptRepository_Factory(databaseProvider, appExecutorsProvider);
    }

    public static DefaultUploadFileAttemptRepository newInstance(AppDatabase database, AppExecutors appExecutors) {
        return new DefaultUploadFileAttemptRepository(database, appExecutors);
    }
}
