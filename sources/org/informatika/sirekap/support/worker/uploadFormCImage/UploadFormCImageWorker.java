package org.informatika.sirekap.support.worker.uploadFormCImage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import dagger.hilt.android.EntryPointAccessors;
import java.util.Date;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.openid.appauth.AuthState;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultFormC1Repository;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.repository.UploadImageAttemptRepository;
import org.informatika.sirekap.repository.fake.FakeElectionRepository;
import org.informatika.sirekap.support.ConnectivityUtil;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.notification.NotificationUtil;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: UploadFormCImageWorker.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\b\u0016\u0018\u0000 52\u00020\u0001:\u000256B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016Jl\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u00122\u0006\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u000bH\u0004Jn\u0010\u001a\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u001b\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u00122\u0006\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0004J\b\u0010!\u001a\u00020\"H\u0004J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020&H\u0004J\b\u0010'\u001a\u00020(H\u0004J \u0010)\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J7\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00172\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0002\u0010/J#\u00100\u001a\u00020+2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0002\u00101JI\u00102\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u00103\u001a\u00020\u00172\b\u0010.\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0004¢\u0006\u0002\u00104R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lorg/informatika/sirekap/support/worker/uploadFormCImage/UploadFormCImageWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "failOrRetryWork", "electionPageId", "", "jenisImage", "", "counter", "imageDescription", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "jenisPemilihan", "correctedPhotoPath", "croppedPhotoPath", "isLn", "", "isPos", "profile", "failWork", "e", "isOfflineOverride", "getElectionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "getEncryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "getFakeElectionRepository", "Lorg/informatika/sirekap/repository/fake/FakeElectionRepository;", "getFormC1Repository", "Lorg/informatika/sirekap/repository/DefaultFormC1Repository;", "getSharedPreferences", "Landroid/content/SharedPreferences;", "getUploadImageAttemptRepository", "Lorg/informatika/sirekap/repository/UploadImageAttemptRepository;", "retryWork", "sendNotification", "", "isUploadSuccess", "errorMessage", "idImage", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "sendNotificationOffline", "(Ljava/lang/String;Ljava/lang/Integer;)V", "successWork", "isLastFile", "(Ljava/lang/String;IZLjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroidx/work/ListenableWorker$Result;", "Companion", "UploadWorkerEntryPoint", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class UploadFormCImageWorker extends Worker {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_AUTO_RETRY_WORK = 3;
    private static final String TAG = "DefaultUploadWorker";
    private final Context appContext;

    /* compiled from: UploadFormCImageWorker.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/support/worker/uploadFormCImage/UploadFormCImageWorker$UploadWorkerEntryPoint;", "", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "fakeElectionRepository", "Lorg/informatika/sirekap/repository/fake/FakeElectionRepository;", "formC1Repository", "Lorg/informatika/sirekap/repository/DefaultFormC1Repository;", "sharedPreferences", "Landroid/content/SharedPreferences;", "uploadImageAttemptRepository", "Lorg/informatika/sirekap/repository/UploadImageAttemptRepository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface UploadWorkerEntryPoint {
        DefaultElectionRepository electionRepository();

        EncryptedSharedPreferences encryptedSharedPreferences();

        FakeElectionRepository fakeElectionRepository();

        DefaultFormC1Repository formC1Repository();

        SharedPreferences sharedPreferences();

        UploadImageAttemptRepository uploadImageAttemptRepository();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadFormCImageWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.appContext = appContext;
    }

    private final DefaultFormC1Repository getFormC1Repository() {
        return ((UploadWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, UploadWorkerEntryPoint.class)).formC1Repository();
    }

    private final DefaultElectionRepository getElectionRepository() {
        return ((UploadWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, UploadWorkerEntryPoint.class)).electionRepository();
    }

    public final FakeElectionRepository getFakeElectionRepository() {
        return ((UploadWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, UploadWorkerEntryPoint.class)).fakeElectionRepository();
    }

    public final UploadImageAttemptRepository getUploadImageAttemptRepository() {
        return ((UploadWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, UploadWorkerEntryPoint.class)).uploadImageAttemptRepository();
    }

    public final SharedPreferences getSharedPreferences() {
        return ((UploadWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, UploadWorkerEntryPoint.class)).sharedPreferences();
    }

    public final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        return ((UploadWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, UploadWorkerEntryPoint.class)).encryptedSharedPreferences();
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x0122, code lost:
        if (kotlin.text.StringsKt.isBlank(r7) != false) goto L17;
     */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01d3  */
    @Override // androidx.work.Worker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.work.ListenableWorker.Result doWork() {
        /*
            Method dump skipped, instructions count: 532
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageWorker.doWork():androidx.work.ListenableWorker$Result");
    }

    public final ListenableWorker.Result successWork(String electionPageId, int i, boolean z, Integer num, String str, String jenisPemilihan, String profile) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(profile, "profile");
        getUploadImageAttemptRepository().markUploadImageAttemptAsSuccess(electionPageId, i);
        if (z) {
            if (num != null) {
                num.intValue();
                String generateIdImage = ElectionUtil.generateIdImage(num.intValue(), jenisPemilihan, profile);
                ElectionRepository.DefaultImpls.finishElectionPageSend$default(getElectionRepository(), electionPageId, generateIdImage, false, 4, null);
                getEncryptedSharedPreferences().putStringEncrypted("visionWaitUntil25_" + generateIdImage, String.valueOf(new Date().getTime() + ((long) AuthState.EXPIRY_TIME_TOLERANCE_MS)));
            }
            getUploadImageAttemptRepository().markUploadImageAttemptAsSuccess(electionPageId);
            sendNotification$default(this, true, str, null, num, 4, null);
        }
        ListenableWorker.Result success = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(success, "success()");
        return success;
    }

    public final ListenableWorker.Result failOrRetryWork(String electionPageId, int i, int i2, String str, Exception exc, String jenisPemilihan, String str2, String croppedPhotoPath, boolean z, boolean z2, String profile) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
        Intrinsics.checkNotNullParameter(profile, "profile");
        if (i2 >= 3) {
            return failWork$default(this, electionPageId, i, str, exc, jenisPemilihan, str2, croppedPhotoPath, z, z2, false, profile, 512, null);
        }
        return retryWork(electionPageId, i, i2);
    }

    private final ListenableWorker.Result retryWork(String str, int i, int i2) {
        getUploadImageAttemptRepository().insertUploadImageAttempt(str, i, i2 + 1);
        ListenableWorker.Result retry = ListenableWorker.Result.retry();
        Intrinsics.checkNotNullExpressionValue(retry, "retry()");
        return retry;
    }

    static /* synthetic */ ListenableWorker.Result failWork$default(UploadFormCImageWorker uploadFormCImageWorker, String str, int i, String str2, Exception exc, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, String str6, int i2, Object obj) {
        if (obj == null) {
            return uploadFormCImageWorker.failWork(str, i, str2, exc, str3, str4, str5, z, z2, (i2 & 512) != 0 ? false : z3, str6);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: failWork");
    }

    private final ListenableWorker.Result failWork(String str, int i, String str2, Exception exc, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, String str6) {
        String message;
        getUploadImageAttemptRepository().markUploadImageAttemptAsFailed(str, i);
        boolean z4 = false;
        if (((exc == null || (message = exc.getMessage()) == null || !StringsKt.contains$default((CharSequence) message, (CharSequence) "Unable to resolve host", false, 2, (Object) null)) ? false : true) && !ConnectivityUtil.isConnectedToNetwork(this.appContext) && !ConnectivityUtil.isUserHasInternetConnection()) {
            z4 = true;
        }
        if (z4 || z3) {
            int nextInt = new Random().nextInt();
            if (nextInt > 0) {
                nextInt *= -1;
            }
            String generateIdImage = ElectionUtil.generateIdImage(nextInt, str3, str6);
            getElectionRepository().finishElectionPageSend(str, generateIdImage, true);
            Bitmap decodeFile = BitmapFactory.decodeFile(str4);
            if (decodeFile == null) {
                decodeFile = BitmapFactory.decodeFile(str5);
            }
            Bitmap bitmap = decodeFile;
            if (i == 10) {
                Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
                FormC1Repository.DefaultImpls.createEmptyFormC1Administration$default(getFormC1Repository(), generateIdImage, str3, bitmap, z, z2, false, 32, null);
            } else if (i != 20) {
                if (i == 30) {
                    Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
                    FormC1Repository.DefaultImpls.createEmptyFormC1AdministrationHal2$default(getFormC1Repository(), generateIdImage, str3, bitmap, z, z2, false, 32, null);
                }
            } else if (Intrinsics.areEqual(str3, Election.ELECTION_PEMILIHAN_PRESIDEN) || Intrinsics.areEqual(str3, Election.ELECTION_PEMILIHAN_DPD)) {
                Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
                FormC1Repository.DefaultImpls.createEmptyFormC1Tabulation$default(getFormC1Repository(), generateIdImage, str3, bitmap, z, z2, false, 32, null);
            } else {
                Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
                FormC1Repository.DefaultImpls.createEmptyFormC1TabulationPartai$default(getFormC1Repository(), generateIdImage, str3, bitmap, z, z2, false, 32, null);
            }
            sendNotificationOffline$default(this, str2, null, 2, null);
            ListenableWorker.Result success = ListenableWorker.Result.success();
            Intrinsics.checkNotNullExpressionValue(success, "{\n            // Send im…esult.success()\n        }");
            return success;
        }
        getElectionRepository().failElectionPageSend(str);
        sendNotification$default(this, false, str2, exc != null ? exc.getMessage() : null, null, 8, null);
        ListenableWorker.Result failure = ListenableWorker.Result.failure();
        Intrinsics.checkNotNullExpressionValue(failure, "{\n            getElectio…esult.failure()\n        }");
        return failure;
    }

    static /* synthetic */ void sendNotification$default(UploadFormCImageWorker uploadFormCImageWorker, boolean z, String str, String str2, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendNotification");
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        if ((i & 8) != 0) {
            num = null;
        }
        uploadFormCImageWorker.sendNotification(z, str, str2, num);
    }

    private final void sendNotification(boolean z, String str, String str2, Integer num) {
        String string;
        String string2;
        int intValue = num != null ? num.intValue() : kotlin.random.Random.Default.nextInt();
        if (z) {
            string = this.appContext.getString(R.string.event_upload_image_success);
        } else {
            string = this.appContext.getString(R.string.event_upload_image_error);
        }
        Intrinsics.checkNotNullExpressionValue(string, "if (isUploadSuccess) app…event_upload_image_error)");
        if (z) {
            string2 = this.appContext.getString(R.string.event_upload_image_success_message, str);
        } else {
            string2 = this.appContext.getString(R.string.event_upload_image_error_message, str, str2);
        }
        Intrinsics.checkNotNullExpressionValue(string2, "if (isUploadSuccess) app…rrorMessage\n            )");
        NotificationUtil.Companion.sendNotification(this.appContext, intValue, string, string2);
    }

    static /* synthetic */ void sendNotificationOffline$default(UploadFormCImageWorker uploadFormCImageWorker, String str, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendNotificationOffline");
        }
        if ((i & 2) != 0) {
            num = null;
        }
        uploadFormCImageWorker.sendNotificationOffline(str, num);
    }

    private final void sendNotificationOffline(String str, Integer num) {
        int intValue = num != null ? num.intValue() : kotlin.random.Random.Default.nextInt();
        String string = this.appContext.getString(R.string.event_upload_image_success_offline);
        Intrinsics.checkNotNullExpressionValue(string, "appContext.getString(R.s…ad_image_success_offline)");
        String string2 = this.appContext.getString(R.string.event_upload_image_success_message_offline, str);
        Intrinsics.checkNotNullExpressionValue(string2, "appContext.getString(\n  …mageDescription\n        )");
        NotificationUtil.Companion.sendNotification(this.appContext, intValue, string, string2);
    }

    /* compiled from: UploadFormCImageWorker.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/support/worker/uploadFormCImage/UploadFormCImageWorker$Companion;", "", "()V", "MAX_AUTO_RETRY_WORK", "", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }
    }
}
