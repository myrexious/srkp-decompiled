package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: BackgroundProcessRepository.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0016\u0010\u0012\u001a\u00020\f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0014H\u0016J \u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000eH\u0016J\u0018\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultBackgroundProcessRepository;", "Lorg/informatika/sirekap/repository/BackgroundProcessRepository;", "api", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Lorg/informatika/sirekap/api/SirekapApiInterface;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "deleteById", "", JobType.ID, "", "getById", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/BackgroundProcess;", "insertAll", "backgroundProcesses", "", "markAsFailed", "endedAt", "", "errorMessage", "markAsSuccess", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultBackgroundProcessRepository implements BackgroundProcessRepository {
    private final SirekapApiInterface api;
    private final AppExecutors appExecutors;
    private final AppDatabase database;
    private final EncryptedSharedPreferences encryptedSharedPreferences;

    @Inject
    public DefaultBackgroundProcessRepository(SirekapApiInterface api, AppDatabase database, AppExecutors appExecutors, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.api = api;
        this.database = database;
        this.appExecutors = appExecutors;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
    }

    @Override // org.informatika.sirekap.repository.BackgroundProcessRepository
    public void insertAll(final List<BackgroundProcess> backgroundProcesses) {
        Intrinsics.checkNotNullParameter(backgroundProcesses, "backgroundProcesses");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultBackgroundProcessRepository$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                DefaultBackgroundProcessRepository.insertAll$lambda$1(DefaultBackgroundProcessRepository.this, backgroundProcesses);
            }
        });
    }

    public static final void insertAll$lambda$1(DefaultBackgroundProcessRepository this$0, final List backgroundProcesses) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(backgroundProcesses, "$backgroundProcesses");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultBackgroundProcessRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DefaultBackgroundProcessRepository.insertAll$lambda$1$lambda$0(DefaultBackgroundProcessRepository.this, backgroundProcesses);
            }
        });
    }

    public static final void insertAll$lambda$1$lambda$0(DefaultBackgroundProcessRepository this$0, List backgroundProcesses) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(backgroundProcesses, "$backgroundProcesses");
        this$0.database.backgroundProcessDao().insertAll(backgroundProcesses);
    }

    @Override // org.informatika.sirekap.repository.BackgroundProcessRepository
    public LiveData<BackgroundProcess> getById(String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        return this.database.backgroundProcessDao().getById(id2);
    }

    @Override // org.informatika.sirekap.repository.BackgroundProcessRepository
    public void deleteById(final String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultBackgroundProcessRepository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DefaultBackgroundProcessRepository.deleteById$lambda$3(DefaultBackgroundProcessRepository.this, id2);
            }
        });
    }

    public static final void deleteById$lambda$3(DefaultBackgroundProcessRepository this$0, final String id2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultBackgroundProcessRepository$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                DefaultBackgroundProcessRepository.deleteById$lambda$3$lambda$2(DefaultBackgroundProcessRepository.this, id2);
            }
        });
    }

    public static final void deleteById$lambda$3$lambda$2(DefaultBackgroundProcessRepository this$0, String id2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        this$0.database.backgroundProcessDao().deleteById(id2);
    }

    @Override // org.informatika.sirekap.repository.BackgroundProcessRepository
    public void markAsSuccess(final String id2, final long j) {
        Intrinsics.checkNotNullParameter(id2, "id");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultBackgroundProcessRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DefaultBackgroundProcessRepository.markAsSuccess$lambda$5(DefaultBackgroundProcessRepository.this, id2, j);
            }
        });
    }

    public static final void markAsSuccess$lambda$5(DefaultBackgroundProcessRepository this$0, final String id2, final long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultBackgroundProcessRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DefaultBackgroundProcessRepository.markAsSuccess$lambda$5$lambda$4(DefaultBackgroundProcessRepository.this, id2, j);
            }
        });
    }

    public static final void markAsSuccess$lambda$5$lambda$4(DefaultBackgroundProcessRepository this$0, String id2, long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        this$0.database.backgroundProcessDao().markAsSuccess(id2, j);
    }

    @Override // org.informatika.sirekap.repository.BackgroundProcessRepository
    public void markAsFailed(final String id2, final long j, final String errorMessage) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultBackgroundProcessRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultBackgroundProcessRepository.markAsFailed$lambda$7(DefaultBackgroundProcessRepository.this, id2, j, errorMessage);
            }
        });
    }

    public static final void markAsFailed$lambda$7(DefaultBackgroundProcessRepository this$0, final String id2, final long j, final String errorMessage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        Intrinsics.checkNotNullParameter(errorMessage, "$errorMessage");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultBackgroundProcessRepository$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DefaultBackgroundProcessRepository.markAsFailed$lambda$7$lambda$6(DefaultBackgroundProcessRepository.this, id2, j, errorMessage);
            }
        });
    }

    public static final void markAsFailed$lambda$7$lambda$6(DefaultBackgroundProcessRepository this$0, String id2, long j, String errorMessage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        Intrinsics.checkNotNullParameter(errorMessage, "$errorMessage");
        this$0.database.backgroundProcessDao().markAsFailed(id2, j, errorMessage);
    }
}
