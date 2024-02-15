package org.informatika.sirekap.support.worker.uploadFormCImage;

import android.content.Context;
import androidx.work.BackoffPolicy;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.ResourceRefType;

/* compiled from: FakeUploadFormCImageTask.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u008c\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\"\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\f0\n2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0012H\u0016¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/support/worker/uploadFormCImage/FakeUploadFormCImageTask;", "Lorg/informatika/sirekap/support/worker/uploadFormCImage/UploadFormCImageTask;", "()V", "upload", "", "context", "Landroid/content/Context;", "electionPageId", "", "uploadedFiles", "", "", "Lkotlin/Pair;", "kodeTps", "noLembar", "jenisPemilihan", "imageDescription", "isValid", "", "sign", "correctedPhotoPath", "isLn", "isPos", "isOffline", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FakeUploadFormCImageTask implements UploadFormCImageTask {
    @Override // org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageTask
    public void upload(Context context, String electionPageId, Map<Integer, Pair<String, String>> uploadedFiles, String str, int i, String str2, String imageDescription, boolean z, String sign, String correctedPhotoPath, boolean z2, boolean z3, boolean z4) {
        String kodeTps = str;
        String jenisPemilihan = str2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        Intrinsics.checkNotNullParameter(uploadedFiles, "uploadedFiles");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        String str3 = "jenisPemilihan";
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(imageDescription, "imageDescription");
        Intrinsics.checkNotNullParameter(sign, "sign");
        String str4 = "correctedPhotoPath";
        Intrinsics.checkNotNullParameter(correctedPhotoPath, "correctedPhotoPath");
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Integer, Pair<String, String>>> it = uploadedFiles.entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Map.Entry<Integer, Pair<String, String>> next = it.next();
            int intValue = next.getKey().intValue();
            Pair<String, String> value = next.getValue();
            Iterator<Map.Entry<Integer, Pair<String, String>>> it2 = it;
            String component1 = value.component1();
            ArrayList arrayList2 = arrayList;
            String component2 = value.component2();
            String str5 = str4;
            String str6 = str3;
            OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(FakeUploadFormCImageWorker.class).setBackoffCriteria(BackoffPolicy.LINEAR, WorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS).setInputData(new Data.Builder().putString("imageDescription", imageDescription).putString("electionPageId", electionPageId).putString(ResourceRefType.FILE_PATH, component1).putString("kodeTps", kodeTps).putInt("jenisImage", intValue).putInt("noLembar", i).putString(str6, jenisPemilihan).putBoolean("isLastFile", i2 == uploadedFiles.size() + (-1)).putBoolean("isValid", z).putString("fileHash", component2).putString("sign", sign).putString(str5, correctedPhotoPath).putBoolean("isLn", z2).putBoolean("isPos", z3).putBoolean("isOffline", z4).build()).addTag(electionPageId).build();
            Intrinsics.checkNotNullExpressionValue(build, "OneTimeWorkRequestBuilde…\n                .build()");
            arrayList2.add(build);
            i2++;
            kodeTps = str;
            jenisPemilihan = str2;
            str3 = str6;
            it = it2;
            arrayList = arrayList2;
            str4 = str5;
        }
        ArrayList arrayList3 = arrayList;
        WorkContinuation beginWith = WorkManager.getInstance(context).beginWith((OneTimeWorkRequest) arrayList3.get(0));
        Intrinsics.checkNotNullExpressionValue(beginWith, "getInstance(context)\n   …nWith(workRequestList[0])");
        int size = arrayList3.size();
        for (int i3 = 1; i3 < size; i3++) {
            beginWith = beginWith.then((OneTimeWorkRequest) arrayList3.get(i3));
            Intrinsics.checkNotNullExpressionValue(beginWith, "workContinuation.then(workRequestList[i])");
        }
        beginWith.enqueue();
    }
}
