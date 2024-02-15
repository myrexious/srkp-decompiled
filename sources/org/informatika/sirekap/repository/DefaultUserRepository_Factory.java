package org.informatika.sirekap.repository;

import dagger.internal.Factory;
import javax.inject.Provider;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.support.AppExecutors;

/* loaded from: classes2.dex */
public final class DefaultUserRepository_Factory implements Factory<DefaultUserRepository> {
    private final Provider<AppExecutors> appExecutorsProvider;
    private final Provider<AppDatabase> databaseProvider;

    public DefaultUserRepository_Factory(Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider) {
        this.databaseProvider = databaseProvider;
        this.appExecutorsProvider = appExecutorsProvider;
    }

    @Override // javax.inject.Provider
    public DefaultUserRepository get() {
        return newInstance(this.databaseProvider.get(), this.appExecutorsProvider.get());
    }

    public static DefaultUserRepository_Factory create(Provider<AppDatabase> databaseProvider, Provider<AppExecutors> appExecutorsProvider) {
        return new DefaultUserRepository_Factory(databaseProvider, appExecutorsProvider);
    }

    public static DefaultUserRepository newInstance(AppDatabase database, AppExecutors appExecutors) {
        return new DefaultUserRepository(database, appExecutors);
    }
}
