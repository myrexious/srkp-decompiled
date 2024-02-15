package org.informatika.sirekap.repository;

import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.UploadFileAttempt;
import org.informatika.sirekap.support.AppExecutors;

/* compiled from: UploadFileAttemptRepository.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultUploadFileAttemptRepository;", "Lorg/informatika/sirekap/repository/UploadFileAttemptRepository;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "(Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/support/AppExecutors;)V", "getUploadFileAttempt", "Lorg/informatika/sirekap/model/UploadFileAttempt;", JobType.ID, "", "type", "insertUploadFileAttempt", "", "counter", "", "markUploadFileAttemptAsFailed", "markUploadFileAttemptAsSuccess", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultUploadFileAttemptRepository implements UploadFileAttemptRepository {
    private final AppExecutors appExecutors;
    private final AppDatabase database;

    @Inject
    public DefaultUploadFileAttemptRepository(AppDatabase database, AppExecutors appExecutors) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        this.database = database;
        this.appExecutors = appExecutors;
    }

    @Override // org.informatika.sirekap.repository.UploadFileAttemptRepository
    public UploadFileAttempt getUploadFileAttempt(String id2, String type) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        return this.database.uploadFileAttemptDao().getBy(id2, type);
    }

    @Override // org.informatika.sirekap.repository.UploadFileAttemptRepository
    public void insertUploadFileAttempt(final String id2, final String type, final int i) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadFileAttemptRepository.insertUploadFileAttempt$lambda$1(DefaultUploadFileAttemptRepository.this, id2, type, i);
            }
        });
    }

    public static final void insertUploadFileAttempt$lambda$1(DefaultUploadFileAttemptRepository this$0, final String id2, final String type, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        Intrinsics.checkNotNullParameter(type, "$type");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadFileAttemptRepository.insertUploadFileAttempt$lambda$1$lambda$0(DefaultUploadFileAttemptRepository.this, id2, type, i);
            }
        });
    }

    public static final void insertUploadFileAttempt$lambda$1$lambda$0(DefaultUploadFileAttemptRepository this$0, String id2, String type, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        Intrinsics.checkNotNullParameter(type, "$type");
        this$0.database.uploadFileAttemptDao().insertAll(CollectionsKt.listOf(new UploadFileAttempt(id2, type, i, false, 8, null)));
    }

    @Override // org.informatika.sirekap.repository.UploadFileAttemptRepository
    public void markUploadFileAttemptAsSuccess(final String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadFileAttemptRepository.markUploadFileAttemptAsSuccess$lambda$3(DefaultUploadFileAttemptRepository.this, id2);
            }
        });
    }

    public static final void markUploadFileAttemptAsSuccess$lambda$3(DefaultUploadFileAttemptRepository this$0, final String id2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadFileAttemptRepository.markUploadFileAttemptAsSuccess$lambda$3$lambda$2(DefaultUploadFileAttemptRepository.this, id2);
            }
        });
    }

    public static final void markUploadFileAttemptAsSuccess$lambda$3$lambda$2(DefaultUploadFileAttemptRepository this$0, String id2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        this$0.database.uploadFileAttemptDao().deleteBy(id2);
    }

    @Override // org.informatika.sirekap.repository.UploadFileAttemptRepository
    public void markUploadFileAttemptAsSuccess(final String id2, final String type) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadFileAttemptRepository.markUploadFileAttemptAsSuccess$lambda$5(DefaultUploadFileAttemptRepository.this, id2, type);
            }
        });
    }

    public static final void markUploadFileAttemptAsSuccess$lambda$5(DefaultUploadFileAttemptRepository this$0, final String id2, final String type) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        Intrinsics.checkNotNullParameter(type, "$type");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadFileAttemptRepository.markUploadFileAttemptAsSuccess$lambda$5$lambda$4(DefaultUploadFileAttemptRepository.this, id2, type);
            }
        });
    }

    public static final void markUploadFileAttemptAsSuccess$lambda$5$lambda$4(DefaultUploadFileAttemptRepository this$0, String id2, String type) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        Intrinsics.checkNotNullParameter(type, "$type");
        this$0.database.uploadFileAttemptDao().markAsSuccess(id2, type);
    }

    @Override // org.informatika.sirekap.repository.UploadFileAttemptRepository
    public void markUploadFileAttemptAsFailed(final String id2, final String type) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadFileAttemptRepository.markUploadFileAttemptAsFailed$lambda$7(DefaultUploadFileAttemptRepository.this, id2, type);
            }
        });
    }

    public static final void markUploadFileAttemptAsFailed$lambda$7(DefaultUploadFileAttemptRepository this$0, final String id2, final String type) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        Intrinsics.checkNotNullParameter(type, "$type");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadFileAttemptRepository.markUploadFileAttemptAsFailed$lambda$7$lambda$6(DefaultUploadFileAttemptRepository.this, id2, type);
            }
        });
    }

    public static final void markUploadFileAttemptAsFailed$lambda$7$lambda$6(DefaultUploadFileAttemptRepository this$0, String id2, String type) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        Intrinsics.checkNotNullParameter(type, "$type");
        this$0.database.uploadFileAttemptDao().deleteBy(id2, type);
    }

    @Override // org.informatika.sirekap.repository.UploadFileAttemptRepository
    public void markUploadFileAttemptAsFailed(final String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadFileAttemptRepository.markUploadFileAttemptAsFailed$lambda$9(DefaultUploadFileAttemptRepository.this, id2);
            }
        });
    }

    public static final void markUploadFileAttemptAsFailed$lambda$9(DefaultUploadFileAttemptRepository this$0, final String id2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadFileAttemptRepository.markUploadFileAttemptAsFailed$lambda$9$lambda$8(DefaultUploadFileAttemptRepository.this, id2);
            }
        });
    }

    public static final void markUploadFileAttemptAsFailed$lambda$9$lambda$8(DefaultUploadFileAttemptRepository this$0, String id2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id2, "$id");
        this$0.database.uploadFileAttemptDao().deleteBy(id2);
    }
}
