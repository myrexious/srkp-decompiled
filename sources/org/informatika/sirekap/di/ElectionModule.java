package org.informatika.sirekap.di;

import com.google.android.datatransport.runtime.dagger.Provides;
import dagger.Module;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.fake.FakeElectionRepository;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: ElectionModule.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/di/ElectionModule;", "", "()V", "provideElectionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "apiUpload", "Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "provideElectionRepositoryFake", "Lorg/informatika/sirekap/repository/fake/FakeElectionRepository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public abstract class ElectionModule {
    @Provides
    @Singleton
    public final DefaultElectionRepository provideElectionRepository(AppExecutors appExecutors, AppDatabase database, SirekapApiInterfaceUpload apiUpload, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(apiUpload, "apiUpload");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        return new DefaultElectionRepository(appExecutors, database, apiUpload, encryptedSharedPreferences);
    }

    @Provides
    @Singleton
    public final FakeElectionRepository provideElectionRepositoryFake(AppExecutors appExecutors, AppDatabase database, SirekapApiInterfaceUpload apiUpload, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(apiUpload, "apiUpload");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        return new FakeElectionRepository(appExecutors, database, apiUpload, encryptedSharedPreferences);
    }
}
