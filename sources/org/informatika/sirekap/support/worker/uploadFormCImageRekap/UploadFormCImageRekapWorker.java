package org.informatika.sirekap.support.worker.uploadFormCImageRekap;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import dagger.hilt.android.EntryPointAccessors;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import org.apache.xmpbox.type.ResourceRefType;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.R;
import org.informatika.sirekap.api.response.FormCImageUploadUrlApiResponse;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.UploadFileAttempt;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.repository.UploadFileAttemptRepository;
import org.informatika.sirekap.support.device.DeviceUtil;
import org.informatika.sirekap.support.notification.NotificationUtil;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: UploadFormCImageRekapWorker.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u0000 !2\u00020\u0001:\u0002!\"B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J2\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u000f\u001a\n\u0018\u00010\u0010j\u0004\u0018\u0001`\u0011H\u0002J*\u0010\u0012\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0013\u001a\n\u0018\u00010\u0010j\u0004\u0018\u0001`\u0011H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J&\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u000bH\u0002J\u001a\u0010 \u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/UploadFormCImageRekapWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "failOrRetryWork", "electionId", "", "counter", "", "imageDescription", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "failWork", "e", "getElectionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "getEncryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "getUploadFileAttemptRepository", "Lorg/informatika/sirekap/repository/UploadFileAttemptRepository;", "retryWork", "sendNotification", "", "isUploadSuccess", "", "errorMessage", "successWork", "Companion", "UploadFormCImageRekapWorkerEntryPoint", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class UploadFormCImageRekapWorker extends Worker {
    public static final Companion Companion = new Companion(null);
    public static final String FILE_TYPE = "form_c_rekap";
    public static final int MAX_AUTO_RETRY_WORK = 3;
    private static final String TAG = "DefaultUploadWorkerRekap";
    private final Context appContext;

    /* compiled from: UploadFormCImageRekapWorker.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/UploadFormCImageRekapWorker$UploadFormCImageRekapWorkerEntryPoint;", "", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "sharedPreferences", "Landroid/content/SharedPreferences;", "uploadFileAttemptRepository", "Lorg/informatika/sirekap/repository/UploadFileAttemptRepository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface UploadFormCImageRekapWorkerEntryPoint {
        DefaultElectionRepository electionRepository();

        EncryptedSharedPreferences encryptedSharedPreferences();

        SharedPreferences sharedPreferences();

        UploadFileAttemptRepository uploadFileAttemptRepository();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadFormCImageRekapWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.appContext = appContext;
    }

    private final UploadFileAttemptRepository getUploadFileAttemptRepository() {
        return ((UploadFormCImageRekapWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, UploadFormCImageRekapWorkerEntryPoint.class)).uploadFileAttemptRepository();
    }

    private final DefaultElectionRepository getElectionRepository() {
        return ((UploadFormCImageRekapWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, UploadFormCImageRekapWorkerEntryPoint.class)).electionRepository();
    }

    private final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        return ((UploadFormCImageRekapWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, UploadFormCImageRekapWorkerEntryPoint.class)).encryptedSharedPreferences();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        String str = "R";
        String string = getInputData().getString("electionId");
        Intrinsics.checkNotNull(string);
        String string2 = getInputData().getString("imageDescription");
        Intrinsics.checkNotNull(string2);
        UploadFileAttempt uploadFileAttempt = getUploadFileAttemptRepository().getUploadFileAttempt(string, "form_c_rekap");
        int attempt = uploadFileAttempt != null ? uploadFileAttempt.getAttempt() : 1;
        if (uploadFileAttempt == null) {
            getUploadFileAttemptRepository().insertUploadFileAttempt(string, "form_c_rekap", attempt);
        }
        try {
            String string3 = getInputData().getString("jenisPemilihan");
            Intrinsics.checkNotNull(string3);
            String string4 = getInputData().getString(ResourceRefType.FILE_PATH);
            Intrinsics.checkNotNull(string4);
            String string5 = getInputData().getString("fileHash");
            Intrinsics.checkNotNull(string5);
            String string6 = getInputData().getString("kodeTps");
            Intrinsics.checkNotNull(string6);
            String string7 = getInputData().getString("sign");
            Intrinsics.checkNotNull(string7);
            boolean z = false;
            boolean z2 = getInputData().getBoolean("isOffline", false);
            String deviceInformation = DeviceUtil.getDeviceInformation();
            String stringEncrypted = getEncryptedSharedPreferences().getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
            String str2 = stringEncrypted;
            if (!((str2 == null || StringsKt.isBlank(str2)) ? true : true)) {
                str = stringEncrypted;
            }
            FormCImageUploadUrlApiResponse.FormCImageUploadUrlApiResponseDetail formCImageRekapUploadLink = getElectionRepository().formCImageRekapUploadLink(string3, str, string6);
            getElectionRepository().formCImageRekapUploadProvider(formCImageRekapUploadLink.getUploadUrl(), string4, formCImageRekapUploadLink.getFilename());
            getElectionRepository().formCImageRekapUpload(string3, SecurityFacade.INSTANCE.getDeviceId(this.appContext), BuildConfig.USER_ID, deviceInformation, formCImageRekapUploadLink.getFilename(), string5, string7, str, string6, (String) CollectionsKt.first((List<? extends Object>) CollectionsKt.takeLast(StringsKt.split$default((CharSequence) string4, new String[]{RemoteSettings.FORWARD_SLASH_STRING}, false, 0, 6, (Object) null), 1)), z2, formCImageRekapUploadLink.getMac());
            return successWork(string, string2);
        } catch (Exception e) {
            e.printStackTrace();
            String message = e.getMessage();
            if (message == null) {
                message = "-";
            }
            Log.e(TAG, message);
            FirebaseCrashlytics.getInstance().recordException(new Exception("DefaultUploadWorkerRekap: " + e.getMessage()));
            return failOrRetryWork(string, attempt, string2, e);
        }
    }

    private final ListenableWorker.Result successWork(String str, String str2) {
        getUploadFileAttemptRepository().markUploadFileAttemptAsSuccess(str, "form_c_rekap");
        ElectionRepository.DefaultImpls.finishUploadPdf$default(getElectionRepository(), str, false, 2, null);
        getUploadFileAttemptRepository().markUploadFileAttemptAsSuccess(str);
        sendNotification$default(this, true, str2, null, 4, null);
        ListenableWorker.Result success = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(success, "success()");
        return success;
    }

    private final ListenableWorker.Result failOrRetryWork(String str, int i, String str2, Exception exc) {
        if (i >= 3) {
            return failWork(str, str2, exc);
        }
        return retryWork(str, i);
    }

    private final ListenableWorker.Result retryWork(String str, int i) {
        getUploadFileAttemptRepository().insertUploadFileAttempt(str, "form_c_rekap", i + 1);
        ListenableWorker.Result retry = ListenableWorker.Result.retry();
        Intrinsics.checkNotNullExpressionValue(retry, "retry()");
        return retry;
    }

    private final ListenableWorker.Result failWork(String str, String str2, Exception exc) {
        getUploadFileAttemptRepository().markUploadFileAttemptAsFailed(str, "form_c_rekap");
        getElectionRepository().failUploadPdf(str);
        sendNotification(false, str2, exc != null ? exc.getMessage() : null);
        ListenableWorker.Result failure = ListenableWorker.Result.failure();
        Intrinsics.checkNotNullExpressionValue(failure, "failure()");
        return failure;
    }

    static /* synthetic */ void sendNotification$default(UploadFormCImageRekapWorker uploadFormCImageRekapWorker, boolean z, String str, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendNotification");
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        uploadFormCImageRekapWorker.sendNotification(z, str, str2);
    }

    private final void sendNotification(boolean z, String str, String str2) {
        String string;
        String string2;
        if (z) {
            string = this.appContext.getString(R.string.event_upload_file_success);
        } else {
            string = this.appContext.getString(R.string.event_upload_file_error);
        }
        Intrinsics.checkNotNullExpressionValue(string, "if (isUploadSuccess) app….event_upload_file_error)");
        if (z) {
            string2 = this.appContext.getString(R.string.event_upload_file_success_message, str);
        } else {
            string2 = this.appContext.getString(R.string.event_upload_file_error_message, str, str2);
        }
        Intrinsics.checkNotNullExpressionValue(string2, "if (isUploadSuccess) app…rrorMessage\n            )");
        NotificationUtil.Companion.sendNotification(this.appContext, Random.Default.nextInt(), string, string2);
    }

    /* compiled from: UploadFormCImageRekapWorker.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/UploadFormCImageRekapWorker$Companion;", "", "()V", "FILE_TYPE", "", "MAX_AUTO_RETRY_WORK", "", "TAG", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
