package org.informatika.sirekap.di;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.db.AppDatabase;

/* compiled from: DatabaseModule.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/di/DatabaseModule;", "", "()V", "DB_NAME", "", "provideRoomDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "context", "Landroid/content/Context;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public final class DatabaseModule {
    private static final String DB_NAME = "sirekap-db";
    public static final DatabaseModule INSTANCE = new DatabaseModule();

    private DatabaseModule() {
    }

    @Provides
    @Singleton
    public final AppDatabase provideRoomDatabase(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        RoomDatabase build = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        Intrinsics.checkNotNullExpressionValue(build, "databaseBuilder(\n       …uctiveMigration().build()");
        return (AppDatabase) build;
    }
}
