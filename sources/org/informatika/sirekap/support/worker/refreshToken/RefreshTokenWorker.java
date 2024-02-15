package org.informatika.sirekap.support.worker.refreshToken;

import android.content.Context;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import dagger.hilt.android.EntryPointAccessors;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.DefaultAuthRepository;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: RefreshTokenWorker.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0002\u000b\fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/support/worker/refreshToken/RefreshTokenWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "getAuthRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "Companion", "RefreshTokenWorkerEntryPoint", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RefreshTokenWorker extends Worker {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "RefreshTokenWorker";
    private final Context appContext;

    /* compiled from: RefreshTokenWorker.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lorg/informatika/sirekap/support/worker/refreshToken/RefreshTokenWorker$RefreshTokenWorkerEntryPoint;", "", "authRepository", "Lorg/informatika/sirekap/repository/DefaultAuthRepository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface RefreshTokenWorkerEntryPoint {
        DefaultAuthRepository authRepository();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefreshTokenWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.appContext = appContext;
    }

    private final AuthRepository getAuthRepository() {
        return ((RefreshTokenWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, RefreshTokenWorkerEntryPoint.class)).authRepository();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        AuthRepository authRepository = getAuthRepository();
        if (!authRepository.isAuthenticated()) {
            Log.wtf(TAG, "User is not logged in");
            ListenableWorker.Result success = ListenableWorker.Result.success();
            Intrinsics.checkNotNullExpressionValue(success, "success()");
            return success;
        }
        AuthRequestUseCase authRequestUseCase = new AuthRequestUseCase(authRepository);
        if (authRequestUseCase.isLocalTokenExpired()) {
            try {
                BuildersKt__BuildersKt.runBlocking$default(null, new RefreshTokenWorker$doWork$1(authRequestUseCase, this, null), 1, null);
                Log.e(TAG, "SUCCESS!");
                ListenableWorker.Result success2 = ListenableWorker.Result.success();
                Intrinsics.checkNotNullExpressionValue(success2, "success()");
                return success2;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                ListenableWorker.Result failure = ListenableWorker.Result.failure();
                Intrinsics.checkNotNullExpressionValue(failure, "failure()");
                return failure;
            }
        }
        Log.wtf(TAG, "Token is not expired: " + authRepository.getAccessToken());
        ListenableWorker.Result success3 = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(success3, "success()");
        return success3;
    }

    /* compiled from: RefreshTokenWorker.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/support/worker/refreshToken/RefreshTokenWorker$Companion;", "", "()V", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
