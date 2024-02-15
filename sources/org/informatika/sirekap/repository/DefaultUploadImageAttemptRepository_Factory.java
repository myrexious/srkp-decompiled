package org.informatika.sirekap.repository;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;

/* loaded from: classes2.dex */
public final class DefaultUploadImageAttemptRepository_Factory implements Factory<DefaultUploadImageAttemptRepository> {
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;

    public DefaultUploadImageAttemptRepository_Factory(Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider) {
        this.databaseProvider = databaseProvider;
        this.appExecutorsProvider = appExecutorsProvider;
    }

    @Override // javax.inject.Provider
    public DefaultUploadImageAttemptRepository get() {
        return newInstance(this.databaseProvider.get(), this.appExecutorsProvider.get());
    }

    public static DefaultUploadImageAttemptRepository_Factory create(Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider) {
        return new DefaultUploadImageAttemptRepository_Factory(databaseProvider, appExecutorsProvider);
    }

    public static DefaultUploadImageAttemptRepository newInstance(AppDatabase database, AppExecutors appExecutors) {
        return new DefaultUploadImageAttemptRepository(database, appExecutors);
    }
}
