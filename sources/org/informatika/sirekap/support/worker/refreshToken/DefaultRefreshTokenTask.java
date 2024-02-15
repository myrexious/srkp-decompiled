package org.informatika.sirekap.support.worker.refreshToken;

import android.content.Context;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RefreshTokenTask.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/support/worker/refreshToken/DefaultRefreshTokenTask;", "Lorg/informatika/sirekap/support/worker/refreshToken/RefreshTokenTask;", "()V", "start", "", "context", "Landroid/content/Context;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultRefreshTokenTask implements RefreshTokenTask {
    @Override // org.informatika.sirekap.support.worker.refreshToken.RefreshTokenTask
    public void start(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        WorkManager.getInstance(context).cancelAllWorkByTag("refresh-token");
        PeriodicWorkRequest build = new PeriodicWorkRequest.Builder(RefreshTokenWorker.class, 5L, TimeUnit.MINUTES).addTag("refresh-token").build();
        Intrinsics.checkNotNullExpressionValue(build, "PeriodicWorkRequestBuild…en\")\n            .build()");
        WorkManager.getInstance(context).enqueue(build);
    }
}
