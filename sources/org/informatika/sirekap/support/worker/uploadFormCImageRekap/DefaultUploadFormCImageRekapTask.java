package org.informatika.sirekap.support.worker.uploadFormCImageRekap;

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
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.ResourceRefType;

/* compiled from: UploadFormCImageRekapTask.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002JX\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0016\u0010\n\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/DefaultUploadFormCImageRekapTask;", "Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/UploadFormCImageRekapTask;", "()V", "upload", "", "context", "Landroid/content/Context;", "electionId", "", "jenisPemilihan", "uploadedFiles", "Lkotlin/Pair;", "kodeTps", "imageDescription", "sign", "isOffline", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultUploadFormCImageRekapTask implements UploadFormCImageRekapTask {
    @Override // org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapTask
    public void upload(Context context, String electionId, String jenisPemilihan, Pair<String, String> uploadedFiles, String kodeTps, String imageDescription, String sign, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(uploadedFiles, "uploadedFiles");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(imageDescription, "imageDescription");
        Intrinsics.checkNotNullParameter(sign, "sign");
        ArrayList arrayList = new ArrayList();
        OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(UploadFormCImageRekapWorker.class).setBackoffCriteria(BackoffPolicy.LINEAR, WorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS).setInputData(new Data.Builder().putString("electionId", electionId).putString(ResourceRefType.FILE_PATH, uploadedFiles.component1()).putString("jenisPemilihan", jenisPemilihan).putString("fileHash", uploadedFiles.component2()).putString("kodeTps", kodeTps).putString("imageDescription", imageDescription).putString("sign", sign).putBoolean("isOffline", z).build()).build();
        Intrinsics.checkNotNullExpressionValue(build, "OneTimeWorkRequestBuilde…TODO\n            .build()");
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
