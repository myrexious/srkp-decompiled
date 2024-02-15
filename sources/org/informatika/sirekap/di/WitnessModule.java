package org.informatika.sirekap.di;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.Provides;
import dagger.Module;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.repository.fake.FakeWitnessRepository;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: WitnessModule.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J8\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007¨\u0006\u0013"}, d2 = {"Lorg/informatika/sirekap/di/WitnessModule;", "", "()V", "provideWitnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "context", "Landroid/content/Context;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "api", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "apiUpload", "Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "provideWitnessRepositoryFake", "Lorg/informatika/sirekap/repository/fake/FakeWitnessRepository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public abstract class WitnessModule {
    @Provides
    @Singleton
    public final DefaultWitnessRepository provideWitnessRepository(Context context, AppDatabase database, SirekapApiInterface api, SirekapApiInterfaceUpload apiUpload, AppExecutors appExecutors, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(apiUpload, "apiUpload");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        return new DefaultWitnessRepository(context, database, api, apiUpload, appExecutors, encryptedSharedPreferences);
    }

    @Provides
    @Singleton
    public final FakeWitnessRepository provideWitnessRepositoryFake(Context context, AppDatabase database, SirekapApiInterface api, SirekapApiInterfaceUpload apiUpload, AppExecutors appExecutors, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(apiUpload, "apiUpload");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        return new FakeWitnessRepository(context, database, api, apiUpload, appExecutors, encryptedSharedPreferences);
    }
}
