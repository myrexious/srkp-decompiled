package org.informatika.sirekap.support.worker.zipAttendance;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import dagger.hilt.android.EntryPointAccessors;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.AttendanceWithPages;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.UserInformation;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.DefaultAuthRepository;
import org.informatika.sirekap.repository.DefaultBackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.support.FileUtil;
import org.informatika.sirekap.support.SecurityUtil;
import org.informatika.sirekap.support.notification.NotificationUtil;
import org.informatika.sirekap.support.pdf.PdfFacade;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.exceptions.ImageValidationException;
import org.informatika.sirekap.support.security.keystore.KeystoreManager;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: ZipAttendanceWorker.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\rH\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/support/worker/zipAttendance/ZipAttendanceWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "deleteCurrentDocuments", "", "context", "kodeTps", "", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "doWork", "Landroidx/work/ListenableWorker$Result;", "getAppDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "getAuthRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "getBackgroundProcessRepository", "Lorg/informatika/sirekap/repository/DefaultBackgroundProcessRepository;", "getEncryptedSharedPreferences", "getPdfLtvFactory", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;", "getWitnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "Companion", "ZipAttendanceWorkerEntryPoint", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ZipAttendanceWorker extends Worker {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ZipAttendanceWorker";
    private final Context appContext;

    /* compiled from: ZipAttendanceWorker.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/support/worker/zipAttendance/ZipAttendanceWorker$ZipAttendanceWorkerEntryPoint;", "", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "authRepository", "Lorg/informatika/sirekap/repository/DefaultAuthRepository;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/DefaultBackgroundProcessRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "pdfLtvFactory", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;", "witnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface ZipAttendanceWorkerEntryPoint {
        AppDatabase appDatabase();

        DefaultAuthRepository authRepository();

        DefaultBackgroundProcessRepository backgroundProcessRepository();

        EncryptedSharedPreferences encryptedSharedPreferences();

        PdfLtv.Factory pdfLtvFactory();

        DefaultWitnessRepository witnessRepository();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZipAttendanceWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.appContext = appContext;
    }

    private final AppDatabase getAppDatabase() {
        return ((ZipAttendanceWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipAttendanceWorkerEntryPoint.class)).appDatabase();
    }

    private final DefaultBackgroundProcessRepository getBackgroundProcessRepository() {
        return ((ZipAttendanceWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipAttendanceWorkerEntryPoint.class)).backgroundProcessRepository();
    }

    private final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        return ((ZipAttendanceWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipAttendanceWorkerEntryPoint.class)).encryptedSharedPreferences();
    }

    private final DefaultWitnessRepository getWitnessRepository() {
        return ((ZipAttendanceWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipAttendanceWorkerEntryPoint.class)).witnessRepository();
    }

    private final AuthRepository getAuthRepository() {
        return ((ZipAttendanceWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipAttendanceWorkerEntryPoint.class)).authRepository();
    }

    private final PdfLtv.Factory getPdfLtvFactory() {
        return ((ZipAttendanceWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipAttendanceWorkerEntryPoint.class)).pdfLtvFactory();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        String str;
        String str2;
        String str3;
        String string = getInputData().getString("kodeTps");
        Intrinsics.checkNotNull(string);
        AppDatabase appDatabase = getAppDatabase();
        DefaultBackgroundProcessRepository backgroundProcessRepository = getBackgroundProcessRepository();
        EncryptedSharedPreferences encryptedSharedPreferences = getEncryptedSharedPreferences();
        DefaultWitnessRepository witnessRepository = getWitnessRepository();
        AuthRepository authRepository = getAuthRepository();
        PdfLtv.Factory pdfLtvFactory = getPdfLtvFactory();
        Companion companion = Companion;
        backgroundProcessRepository.deleteById(companion.getBackgroundProcessId(string));
        backgroundProcessRepository.insertAll(CollectionsKt.listOf(new BackgroundProcess(companion.getBackgroundProcessId(string), new Date().getTime(), null, null, null, 28, null)));
        AttendanceWithPages byKodeTpsSync = appDatabase.attendanceDao().getByKodeTpsSync(string);
        AuthRequestUseCase authRequestUseCase = new AuthRequestUseCase(authRepository);
        PdfLtv build = pdfLtvFactory.build(this.appContext);
        try {
            try {
                KeystoreManager buildKeystoreManager = SecurityFacade.INSTANCE.buildKeystoreManager(this.appContext);
                deleteCurrentDocuments(this.appContext, string, encryptedSharedPreferences);
                File pdfFile = companion.getPdfFile(this.appContext, string, encryptedSharedPreferences);
                List<Integer> availablePageNums = byKodeTpsSync.getAvailablePageNums();
                List<String> availableImages = byKodeTpsSync.getAvailableImages();
                try {
                    str2 = "Gambar pada halaman ";
                    try {
                        SecurityFacade.INSTANCE.validateImages(this.appContext, availableImages);
                        List<ElectionWithRelation> allByKodeTpsSync = appDatabase.electionDao().getAllByKodeTpsSync(string);
                        PdfFacade pdfFacade = PdfFacade.INSTANCE;
                        Context context = this.appContext;
                        Tps tps = ((ElectionWithRelation) CollectionsKt.first((List<? extends Object>) allByKodeTpsSync)).getElection().getTps();
                        ArrayList<String> arrayList = new ArrayList<>(availableImages);
                        UserInformation userInformation = authRepository.getUserInformation();
                        pdfFacade.generatePdfDaftarHadir(context, pdfFile, arrayList, buildKeystoreManager, tps, (userInformation == null || (r2 = userInformation.getName()) == null) ? "Tidak Diketahui" : "Tidak Diketahui");
                        SecurityUtil securityUtil = new SecurityUtil();
                        String absolutePath = pdfFile.getAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath, "pdfFile.absolutePath");
                        String hashDocument = securityUtil.hashDocument(absolutePath);
                        SecurityFacade.INSTANCE.signPdf(buildKeystoreManager, pdfFile);
                        try {
                            str3 = hashDocument;
                            try {
                                BuildersKt__BuildersKt.runBlocking$default(null, new ZipAttendanceWorker$doWork$1(this, authRequestUseCase, build, pdfFile, null), 1, null);
                            } catch (Exception e) {
                                e = e;
                                FirebaseCrashlytics.getInstance().recordException(e);
                                NotificationUtil.Companion.sendNotification(this.appContext, 1, "Penguncian Dokumen Daftar Hadir", "Saat ini PDF belum ditambahkan informasi verifikasi");
                                String absolutePath2 = pdfFile.getAbsolutePath();
                                Intrinsics.checkNotNullExpressionValue(absolutePath2, "pdfFile.absolutePath");
                                witnessRepository.finishCreatePdf(string, absolutePath2, str3);
                                getBackgroundProcessRepository().markAsSuccess(Companion.getBackgroundProcessId(string), new Date().getTime());
                                ListenableWorker.Result success = ListenableWorker.Result.success();
                                Intrinsics.checkNotNullExpressionValue(success, "success()");
                                return success;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            str3 = hashDocument;
                        }
                        String absolutePath22 = pdfFile.getAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath22, "pdfFile.absolutePath");
                        witnessRepository.finishCreatePdf(string, absolutePath22, str3);
                        getBackgroundProcessRepository().markAsSuccess(Companion.getBackgroundProcessId(string), new Date().getTime());
                        ListenableWorker.Result success2 = ListenableWorker.Result.success();
                        Intrinsics.checkNotNullExpressionValue(success2, "success()");
                        return success2;
                    } catch (ImageValidationException e3) {
                        e = e3;
                        e.printStackTrace();
                        String message = e.getMessage();
                        if (message == null) {
                            message = "-";
                        }
                        Log.wtf(TAG, message);
                        FirebaseCrashlytics.getInstance().recordException(new Exception("ZipAttendanceWorker: " + e.getMessage()));
                        getBackgroundProcessRepository().markAsFailed(Companion.getBackgroundProcessId(string), new Date().getTime(), str2 + availablePageNums.get(e.getIndex()) + " tidak valid. Silakan ulangi pengambilan foto halaman tersebut.");
                        ListenableWorker.Result failure = ListenableWorker.Result.failure();
                        str = "failure()";
                        try {
                            Intrinsics.checkNotNullExpressionValue(failure, str);
                            return failure;
                        } catch (Exception e4) {
                            e = e4;
                            e.printStackTrace();
                            String message2 = e.getMessage();
                            if (message2 == null) {
                                message2 = "-";
                            }
                            Log.wtf(TAG, message2);
                            FirebaseCrashlytics.getInstance().recordException(new Exception("ZipAttendanceWorker: " + e.getMessage()));
                            DefaultBackgroundProcessRepository backgroundProcessRepository2 = getBackgroundProcessRepository();
                            String backgroundProcessId = Companion.getBackgroundProcessId(string);
                            long time = new Date().getTime();
                            String message3 = e.getMessage();
                            backgroundProcessRepository2.markAsFailed(backgroundProcessId, time, message3 == null ? "-" : message3);
                            ListenableWorker.Result failure2 = ListenableWorker.Result.failure();
                            Intrinsics.checkNotNullExpressionValue(failure2, str);
                            return failure2;
                        }
                    }
                } catch (ImageValidationException e5) {
                    e = e5;
                    str2 = "Gambar pada halaman ";
                }
            } catch (Exception e6) {
                e = e6;
                str = "failure()";
            }
        } catch (Exception e7) {
            e = e7;
            str = "failure()";
        }
    }

    private final void deleteCurrentDocuments(Context context, String str, EncryptedSharedPreferences encryptedSharedPreferences) {
        String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
        String str2 = stringEncrypted;
        File externalFilesDir = context.getExternalFilesDir(str + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_DOCUMENTS + "/daftar_hadir" + (str2 == null || StringsKt.isBlank(str2) ? "R" : stringEncrypted));
        if (externalFilesDir != null) {
            FileUtil.deleteRecursive(externalFilesDir);
        }
    }

    /* compiled from: ZipAttendanceWorker.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rR\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/support/worker/zipAttendance/ZipAttendanceWorker$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "getBackgroundProcessId", "kodeTps", "getPdfFile", "Ljava/io/File;", "context", "Landroid/content/Context;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }

        public final String getBackgroundProcessId(String kodeTps) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            return "zip_attendance_" + kodeTps;
        }

        public final File getPdfFile(Context context, String kodeTps, EncryptedSharedPreferences encryptedSharedPreferences) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
            String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
            String str = stringEncrypted;
            return new File(context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_DOCUMENTS + "/daftar_hadir" + (str == null || StringsKt.isBlank(str) ? "R" : stringEncrypted)), "Salinan Daftar Hadir " + kodeTps + ".pdf");
        }
    }
}
