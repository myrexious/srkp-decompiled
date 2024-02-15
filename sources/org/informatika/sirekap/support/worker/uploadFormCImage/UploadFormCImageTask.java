package org.informatika.sirekap.support.worker.uploadFormCImage;

import android.content.Context;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;

/* compiled from: UploadFormCImageTask.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u008c\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\"\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b0\t2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0011H&¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/support/worker/uploadFormCImage/UploadFormCImageTask;", "", "upload", "", "context", "Landroid/content/Context;", "electionPageId", "", "uploadedFiles", "", "", "Lkotlin/Pair;", "kodeTps", "noLembar", "jenisPemilihan", "imageDescription", "isValid", "", "sign", "correctedPhotoPath", "isLn", "isPos", "isOffline", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UploadFormCImageTask {
    void upload(Context context, String str, Map<Integer, Pair<String, String>> map, String str2, int i, String str3, String str4, boolean z, String str5, String str6, boolean z2, boolean z3, boolean z4);
}
