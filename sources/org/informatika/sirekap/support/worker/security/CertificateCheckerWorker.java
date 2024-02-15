package org.informatika.sirekap.support.worker.security;

import android.content.Context;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import dagger.hilt.android.EntryPointAccessors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.db.dao.SecurityDao;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.DefaultAuthRepository;
import org.informatika.sirekap.repository.DefaultCertmanRepository;
import org.informatika.sirekap.support.messaging.CertificateGenerationMessage;
import org.informatika.sirekap.support.messaging.CertificateGenerationResponse;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: CertificateChekerWorker.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/support/worker/security/CertificateCheckerWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "getAuthRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "getCertmanRepository", "Lorg/informatika/sirekap/repository/DefaultCertmanRepository;", "getSecurityPropertiesDao", "Lorg/informatika/sirekap/db/dao/SecurityDao;", "CertificateCheckerWorkerEntryPoint", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CertificateCheckerWorker extends Worker {
    private final Context appContext;

    /* compiled from: CertificateChekerWorker.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/support/worker/security/CertificateCheckerWorker$CertificateCheckerWorkerEntryPoint;", "", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "authRepository", "Lorg/informatika/sirekap/repository/DefaultAuthRepository;", "certmanRepository", "Lorg/informatika/sirekap/repository/DefaultCertmanRepository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface CertificateCheckerWorkerEntryPoint {
        AppDatabase appDatabase();

        DefaultAuthRepository authRepository();

        DefaultCertmanRepository certmanRepository();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateCheckerWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.appContext = appContext;
    }

    private final AuthRepository getAuthRepository() {
        return ((CertificateCheckerWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, CertificateCheckerWorkerEntryPoint.class)).authRepository();
    }

    private final DefaultCertmanRepository getCertmanRepository() {
        return ((CertificateCheckerWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, CertificateCheckerWorkerEntryPoint.class)).certmanRepository();
    }

    private final SecurityDao getSecurityPropertiesDao() {
        return ((CertificateCheckerWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, CertificateCheckerWorkerEntryPoint.class)).appDatabase().securityDao();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        Object runBlocking$default;
        Log.d("CertificateCheckerWorker", "Checking certificate");
        try {
            runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new CertificateCheckerWorker$doWork$1(new AuthRequestUseCase(getAuthRepository()), this, getCertmanRepository(), getSecurityPropertiesDao(), null), 1, null);
            Intrinsics.checkNotNullExpressionValue(runBlocking$default, "override fun doWork(): R…success()\n        }\n    }");
            return (ListenableWorker.Result) runBlocking$default;
        } catch (Exception e) {
            if (Intrinsics.areEqual("Saat ini server sedang sibuk, silahkan coba beberapa saat lagi", e.getMessage())) {
                Log.d("CertificateCheckerWorker", e.getMessage(), e);
                ListenableWorker.Result retry = ListenableWorker.Result.retry();
                Intrinsics.checkNotNullExpressionValue(retry, "retry()");
                return retry;
            }
            CertificateGenerationMessage.INSTANCE.changeState(new CertificateGenerationResponse("failed", null));
            ListenableWorker.Result success = ListenableWorker.Result.success();
            Intrinsics.checkNotNullExpressionValue(success, "success()");
            return success;
        }
    }
}
