package org.informatika.sirekap.support.worker.login;

import android.content.Context;
import androidx.work.BackoffPolicy;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoginTask.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/worker/login/DefaultLoginTask;", "Lorg/informatika/sirekap/support/worker/login/LoginTask;", "()V", "login", "", "context", "Landroid/content/Context;", "kodeTps", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultLoginTask implements LoginTask {
    @Override // org.informatika.sirekap.support.worker.login.LoginTask
    public void login(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList();
        OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(LoginWorker.class).setBackoffCriteria(BackoffPolicy.LINEAR, WorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS).setInputData(new Data.Builder().putString("kodeTps", str).build()).build();
        Intrinsics.checkNotNullExpressionValue(build, "OneTimeWorkRequestBuilde…   )\n            .build()");
        arrayList.add(build);
        WorkContinuation beginWith = WorkManager.getInstance(context).beginWith((OneTimeWorkRequest) arrayList.get(0));
        Intrinsics.checkNotNullExpressionValue(beginWith, "getInstance(context)\n   …nWith(workRequestList[0])");
        int size = arrayList.size();
        for (int i = 1; i < size; i++) {
            beginWith = beginWith.then((OneTimeWorkRequest) arrayList.get(i));
            Intrinsics.checkNotNullExpressionValue(beginWith, "workContinuation.then(workRequestList[i])");
        }
        beginWith.enqueue();
    }
}
