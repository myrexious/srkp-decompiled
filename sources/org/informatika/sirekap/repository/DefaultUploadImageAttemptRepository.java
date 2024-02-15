package org.informatika.sirekap.repository;

import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.UploadImageAttempt;
import org.informatika.sirekap.support.AppExecutors;

/* compiled from: UploadImageAttemptRepository.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultUploadImageAttemptRepository;", "Lorg/informatika/sirekap/repository/UploadImageAttemptRepository;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "(Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/support/AppExecutors;)V", "getUploadImageAttempt", "Lorg/informatika/sirekap/model/UploadImageAttempt;", "electionPageId", "", "jenisImage", "", "insertUploadImageAttempt", "", "counter", "markUploadImageAttemptAsFailed", "markUploadImageAttemptAsSuccess", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultUploadImageAttemptRepository implements UploadImageAttemptRepository {
    private final AppExecutors appExecutors;
    private final AppDatabase database;

    @Inject
    public DefaultUploadImageAttemptRepository(AppDatabase database, AppExecutors appExecutors) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        this.database = database;
        this.appExecutors = appExecutors;
    }

    @Override // org.informatika.sirekap.repository.UploadImageAttemptRepository
    public UploadImageAttempt getUploadImageAttempt(String electionPageId, int i) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        return this.database.uploadImageAttemptDao().getBy(electionPageId, i);
    }

    @Override // org.informatika.sirekap.repository.UploadImageAttemptRepository
    public void insertUploadImageAttempt(final String electionPageId, final int i, final int i2) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadImageAttemptRepository.insertUploadImageAttempt$lambda$1(DefaultUploadImageAttemptRepository.this, electionPageId, i, i2);
            }
        });
    }

    public static final void insertUploadImageAttempt$lambda$1(DefaultUploadImageAttemptRepository this$0, final String electionPageId, final int i, final int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadImageAttemptRepository.insertUploadImageAttempt$lambda$1$lambda$0(DefaultUploadImageAttemptRepository.this, electionPageId, i, i2);
            }
        });
    }

    public static final void insertUploadImageAttempt$lambda$1$lambda$0(DefaultUploadImageAttemptRepository this$0, String electionPageId, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.uploadImageAttemptDao().insertAll(CollectionsKt.listOf(new UploadImageAttempt(electionPageId, i, i2, false, 8, null)));
    }

    @Override // org.informatika.sirekap.repository.UploadImageAttemptRepository
    public void markUploadImageAttemptAsSuccess(final String electionPageId) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadImageAttemptRepository.markUploadImageAttemptAsSuccess$lambda$3(DefaultUploadImageAttemptRepository.this, electionPageId);
            }
        });
    }

    public static final void markUploadImageAttemptAsSuccess$lambda$3(DefaultUploadImageAttemptRepository this$0, final String electionPageId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadImageAttemptRepository.markUploadImageAttemptAsSuccess$lambda$3$lambda$2(DefaultUploadImageAttemptRepository.this, electionPageId);
            }
        });
    }

    public static final void markUploadImageAttemptAsSuccess$lambda$3$lambda$2(DefaultUploadImageAttemptRepository this$0, String electionPageId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.uploadImageAttemptDao().deleteBy(electionPageId);
    }

    @Override // org.informatika.sirekap.repository.UploadImageAttemptRepository
    public void markUploadImageAttemptAsSuccess(final String electionPageId, final int i) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadImageAttemptRepository.markUploadImageAttemptAsSuccess$lambda$5(DefaultUploadImageAttemptRepository.this, electionPageId, i);
            }
        });
    }

    public static final void markUploadImageAttemptAsSuccess$lambda$5(DefaultUploadImageAttemptRepository this$0, final String electionPageId, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadImageAttemptRepository.markUploadImageAttemptAsSuccess$lambda$5$lambda$4(DefaultUploadImageAttemptRepository.this, electionPageId, i);
            }
        });
    }

    public static final void markUploadImageAttemptAsSuccess$lambda$5$lambda$4(DefaultUploadImageAttemptRepository this$0, String electionPageId, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.uploadImageAttemptDao().markAsSuccess(electionPageId, i);
    }

    @Override // org.informatika.sirekap.repository.UploadImageAttemptRepository
    public void markUploadImageAttemptAsFailed(final String electionPageId, final int i) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadImageAttemptRepository.markUploadImageAttemptAsFailed$lambda$7(DefaultUploadImageAttemptRepository.this, electionPageId, i);
            }
        });
    }

    public static final void markUploadImageAttemptAsFailed$lambda$7(DefaultUploadImageAttemptRepository this$0, final String electionPageId, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadImageAttemptRepository.markUploadImageAttemptAsFailed$lambda$7$lambda$6(DefaultUploadImageAttemptRepository.this, electionPageId, i);
            }
        });
    }

    public static final void markUploadImageAttemptAsFailed$lambda$7$lambda$6(DefaultUploadImageAttemptRepository this$0, String electionPageId, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.uploadImageAttemptDao().deleteBy(electionPageId, i);
    }

    @Override // org.informatika.sirekap.repository.UploadImageAttemptRepository
    public void markUploadImageAttemptAsFailed(final String electionPageId) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadImageAttemptRepository.markUploadImageAttemptAsFailed$lambda$9(DefaultUploadImageAttemptRepository.this, electionPageId);
            }
        });
    }

    public static final void markUploadImageAttemptAsFailed$lambda$9(DefaultUploadImageAttemptRepository this$0, final String electionPageId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                DefaultUploadImageAttemptRepository.markUploadImageAttemptAsFailed$lambda$9$lambda$8(DefaultUploadImageAttemptRepository.this, electionPageId);
            }
        });
    }

    public static final void markUploadImageAttemptAsFailed$lambda$9$lambda$8(DefaultUploadImageAttemptRepository this$0, String electionPageId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.uploadImageAttemptDao().deleteBy(electionPageId);
    }
}
