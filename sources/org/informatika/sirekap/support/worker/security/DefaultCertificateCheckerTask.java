package org.informatika.sirekap.support.worker.security;

import android.content.Context;
import android.util.Log;
import androidx.work.BackoffPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CertificateCheckerTask.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/support/worker/security/DefaultCertificateCheckerTask;", "Lorg/informatika/sirekap/support/worker/security/CertificateCheckerTask;", "()V", "start", "", "context", "Landroid/content/Context;", "stop", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultCertificateCheckerTask implements CertificateCheckerTask {
    @Override // org.informatika.sirekap.support.worker.security.CertificateCheckerTask
    public void start(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.d("CertificateCheckerTask", "Starting certificate checker worker");
        WorkManager.getInstance(context).cancelAllWorkByTag("certificate-check");
        OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(CertificateCheckerWorker.class).addTag("certificate-check").setBackoffCriteria(BackoffPolicy.LINEAR, 5L, TimeUnit.SECONDS).build();
        Intrinsics.checkNotNullExpressionValue(build, "OneTimeWorkRequestBuilde…NDS)\n            .build()");
        WorkManager.getInstance(context).enqueue(build);
    }

    @Override // org.informatika.sirekap.support.worker.security.CertificateCheckerTask
    public void stop(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.d("CertificateCheckerTask", "Stopping certificate checker");
        WorkManager.getInstance(context).cancelAllWorkByTag("certificate-check");
    }
}
