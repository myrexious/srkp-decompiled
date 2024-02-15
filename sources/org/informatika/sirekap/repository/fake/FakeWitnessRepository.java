package org.informatika.sirekap.repository.fake;

import android.content.Context;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: FakeWitnessRepository.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B9\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/repository/fake/FakeWitnessRepository;", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "context", "Landroid/content/Context;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "api", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "apiUpload", "Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Landroid/content/Context;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/api/SirekapApiInterface;Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FakeWitnessRepository extends DefaultWitnessRepository {
    private final SirekapApiInterface api;
    private final SirekapApiInterfaceUpload apiUpload;
    private final AppExecutors appExecutors;
    private final Context context;
    private final AppDatabase database;
    private final EncryptedSharedPreferences encryptedSharedPreferences;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Inject
    public FakeWitnessRepository(@ApplicationContext Context context, AppDatabase database, SirekapApiInterface api, SirekapApiInterfaceUpload apiUpload, AppExecutors appExecutors, EncryptedSharedPreferences encryptedSharedPreferences) {
        super(context, database, api, apiUpload, appExecutors, encryptedSharedPreferences);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(apiUpload, "apiUpload");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.context = context;
        this.database = database;
        this.api = api;
        this.apiUpload = apiUpload;
        this.appExecutors = appExecutors;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
    }
}
