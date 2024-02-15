package org.informatika.sirekap.di;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.informatika.sirekap.db.AppDatabase;

/* loaded from: classes2.dex */
public final class DatabaseModule_ProvideRoomDatabaseFactory implements Factory<AppDatabase> {
    private final Provider<Context> contextProvider;

    public DatabaseModule_ProvideRoomDatabaseFactory(Provider<Context> contextProvider) {
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public AppDatabase get() {
        return provideRoomDatabase(this.contextProvider.get());
    }

    public static DatabaseModule_ProvideRoomDatabaseFactory create(Provider<Context> contextProvider) {
        return new DatabaseModule_ProvideRoomDatabaseFactory(contextProvider);
    }

    public static AppDatabase provideRoomDatabase(Context context) {
        return (AppDatabase) Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRoomDatabase(context));
    }
}
