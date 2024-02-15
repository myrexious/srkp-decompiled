package org.informatika.sirekap.support.worker.zipElection;

import android.content.Context;
import android.os.Environment;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.firebase.sessions.settings.RemoteSettings;
import dagger.hilt.android.EntryPointAccessors;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.DefaultAuthRepository;
import org.informatika.sirekap.repository.DefaultBackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.FileUtil;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: ZipElectionWorker.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 62\u00020\u0001:\u000267B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0083\u0001\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000f2\b\u0010%\u001a\u0004\u0018\u00010&2\u000e\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0(2\u000e\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130(2\u000e\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130(H\u0002¢\u0006\u0002\u0010+J\b\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020/H\u0002J \u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u000fH\u0002J\b\u00104\u001a\u000205H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lorg/informatika/sirekap/support/worker/zipElection/ZipElectionWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "deleteCurrentDocuments", "", "context", "electionWithRelation", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "doWork", "Landroidx/work/ListenableWorker$Result;", "formatDateTimeForApiNew", "", "date", "Ljava/util/Date;", "hour", "", "minute", "getAppDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "getAuthRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "getBackgroundProcessRepository", "Lorg/informatika/sirekap/repository/DefaultBackgroundProcessRepository;", "getElectionPageData", "", "", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "usl", "device", "deviceId", "kodeTpsFormatted", "userId", "isSesuai", "", "isSesuaiPerItem", "", "koreksiPerItem", "nilaiPerItem", "(Lorg/informatika/sirekap/model/ElectionPage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Ljava/util/Map;", "getElectionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "getEncryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "getNoLembarFinal", "noLembar", "jenisPemilihan", "kodeTpsOverride", "getPdfLtvFactory", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;", "Companion", "ZipElectionWorkerEntryPoint", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ZipElectionWorker extends Worker {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ZipElectionWorker";
    private final Context appContext;

    /* compiled from: ZipElectionWorker.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/support/worker/zipElection/ZipElectionWorker$ZipElectionWorkerEntryPoint;", "", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "authRepository", "Lorg/informatika/sirekap/repository/DefaultAuthRepository;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/DefaultBackgroundProcessRepository;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "pdfLtvFactory", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface ZipElectionWorkerEntryPoint {
        AppDatabase appDatabase();

        DefaultAuthRepository authRepository();

        DefaultBackgroundProcessRepository backgroundProcessRepository();

        DefaultElectionRepository electionRepository();

        EncryptedSharedPreferences encryptedSharedPreferences();

        PdfLtv.Factory pdfLtvFactory();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZipElectionWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.appContext = appContext;
    }

    private final AppDatabase getAppDatabase() {
        return ((ZipElectionWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipElectionWorkerEntryPoint.class)).appDatabase();
    }

    private final DefaultBackgroundProcessRepository getBackgroundProcessRepository() {
        return ((ZipElectionWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipElectionWorkerEntryPoint.class)).backgroundProcessRepository();
    }

    private final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        return ((ZipElectionWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipElectionWorkerEntryPoint.class)).encryptedSharedPreferences();
    }

    private final AuthRepository getAuthRepository() {
        return ((ZipElectionWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipElectionWorkerEntryPoint.class)).authRepository();
    }

    private final PdfLtv.Factory getPdfLtvFactory() {
        return ((ZipElectionWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipElectionWorkerEntryPoint.class)).pdfLtvFactory();
    }

    private final DefaultElectionRepository getElectionRepository() {
        return ((ZipElectionWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, ZipElectionWorkerEntryPoint.class)).electionRepository();
    }

    /* JADX WARN: Code restructure failed: missing block: B:415:0x023b, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPD) != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:422:0x0276, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) != false) goto L301;
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x0281, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) != false) goto L301;
     */
    /* JADX WARN: Code restructure failed: missing block: B:542:0x0547, code lost:
        if (r41 == null) goto L269;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:436:0x02c8 A[Catch: Exception -> 0x09e0, TRY_ENTER, TryCatch #0 {Exception -> 0x09e0, blocks: (B:401:0x01cf, B:408:0x0225, B:416:0x023d, B:417:0x0255, B:419:0x025b, B:420:0x026b, B:436:0x02c8, B:438:0x02d9, B:440:0x02e3, B:442:0x02e9, B:444:0x02f1, B:465:0x032c, B:448:0x02fd, B:449:0x0301, B:451:0x0307, B:473:0x0397, B:479:0x03ef, B:484:0x0429, B:504:0x046b, B:514:0x04a6, B:487:0x0431, B:491:0x043c, B:494:0x0447, B:411:0x022c, B:430:0x029d, B:414:0x0237, B:421:0x026e), top: B:668:0x01cf }] */
    /* JADX WARN: Removed duplicated region for block: B:455:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x032c A[Catch: Exception -> 0x09e0, TRY_LEAVE, TryCatch #0 {Exception -> 0x09e0, blocks: (B:401:0x01cf, B:408:0x0225, B:416:0x023d, B:417:0x0255, B:419:0x025b, B:420:0x026b, B:436:0x02c8, B:438:0x02d9, B:440:0x02e3, B:442:0x02e9, B:444:0x02f1, B:465:0x032c, B:448:0x02fd, B:449:0x0301, B:451:0x0307, B:473:0x0397, B:479:0x03ef, B:484:0x0429, B:504:0x046b, B:514:0x04a6, B:487:0x0431, B:491:0x043c, B:494:0x0447, B:411:0x022c, B:430:0x029d, B:414:0x0237, B:421:0x026e), top: B:668:0x01cf }] */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x03ef A[Catch: Exception -> 0x09e0, LOOP:7: B:477:0x03e9->B:479:0x03ef, LOOP_END, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x09e0, blocks: (B:401:0x01cf, B:408:0x0225, B:416:0x023d, B:417:0x0255, B:419:0x025b, B:420:0x026b, B:436:0x02c8, B:438:0x02d9, B:440:0x02e3, B:442:0x02e9, B:444:0x02f1, B:465:0x032c, B:448:0x02fd, B:449:0x0301, B:451:0x0307, B:473:0x0397, B:479:0x03ef, B:484:0x0429, B:504:0x046b, B:514:0x04a6, B:487:0x0431, B:491:0x043c, B:494:0x0447, B:411:0x022c, B:430:0x029d, B:414:0x0237, B:421:0x026e), top: B:668:0x01cf }] */
    /* JADX WARN: Removed duplicated region for block: B:483:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0429 A[Catch: Exception -> 0x09e0, TRY_ENTER, TryCatch #0 {Exception -> 0x09e0, blocks: (B:401:0x01cf, B:408:0x0225, B:416:0x023d, B:417:0x0255, B:419:0x025b, B:420:0x026b, B:436:0x02c8, B:438:0x02d9, B:440:0x02e3, B:442:0x02e9, B:444:0x02f1, B:465:0x032c, B:448:0x02fd, B:449:0x0301, B:451:0x0307, B:473:0x0397, B:479:0x03ef, B:484:0x0429, B:504:0x046b, B:514:0x04a6, B:487:0x0431, B:491:0x043c, B:494:0x0447, B:411:0x022c, B:430:0x029d, B:414:0x0237, B:421:0x026e), top: B:668:0x01cf }] */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0431 A[Catch: Exception -> 0x09e0, TryCatch #0 {Exception -> 0x09e0, blocks: (B:401:0x01cf, B:408:0x0225, B:416:0x023d, B:417:0x0255, B:419:0x025b, B:420:0x026b, B:436:0x02c8, B:438:0x02d9, B:440:0x02e3, B:442:0x02e9, B:444:0x02f1, B:465:0x032c, B:448:0x02fd, B:449:0x0301, B:451:0x0307, B:473:0x0397, B:479:0x03ef, B:484:0x0429, B:504:0x046b, B:514:0x04a6, B:487:0x0431, B:491:0x043c, B:494:0x0447, B:411:0x022c, B:430:0x029d, B:414:0x0237, B:421:0x026e), top: B:668:0x01cf }] */
    /* JADX WARN: Removed duplicated region for block: B:491:0x043c A[Catch: Exception -> 0x09e0, TryCatch #0 {Exception -> 0x09e0, blocks: (B:401:0x01cf, B:408:0x0225, B:416:0x023d, B:417:0x0255, B:419:0x025b, B:420:0x026b, B:436:0x02c8, B:438:0x02d9, B:440:0x02e3, B:442:0x02e9, B:444:0x02f1, B:465:0x032c, B:448:0x02fd, B:449:0x0301, B:451:0x0307, B:473:0x0397, B:479:0x03ef, B:484:0x0429, B:504:0x046b, B:514:0x04a6, B:487:0x0431, B:491:0x043c, B:494:0x0447, B:411:0x022c, B:430:0x029d, B:414:0x0237, B:421:0x026e), top: B:668:0x01cf }] */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0447 A[Catch: Exception -> 0x09e0, TryCatch #0 {Exception -> 0x09e0, blocks: (B:401:0x01cf, B:408:0x0225, B:416:0x023d, B:417:0x0255, B:419:0x025b, B:420:0x026b, B:436:0x02c8, B:438:0x02d9, B:440:0x02e3, B:442:0x02e9, B:444:0x02f1, B:465:0x032c, B:448:0x02fd, B:449:0x0301, B:451:0x0307, B:473:0x0397, B:479:0x03ef, B:484:0x0429, B:504:0x046b, B:514:0x04a6, B:487:0x0431, B:491:0x043c, B:494:0x0447, B:411:0x022c, B:430:0x029d, B:414:0x0237, B:421:0x026e), top: B:668:0x01cf }] */
    /* JADX WARN: Removed duplicated region for block: B:498:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x046b A[Catch: Exception -> 0x09e0, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x09e0, blocks: (B:401:0x01cf, B:408:0x0225, B:416:0x023d, B:417:0x0255, B:419:0x025b, B:420:0x026b, B:436:0x02c8, B:438:0x02d9, B:440:0x02e3, B:442:0x02e9, B:444:0x02f1, B:465:0x032c, B:448:0x02fd, B:449:0x0301, B:451:0x0307, B:473:0x0397, B:479:0x03ef, B:484:0x0429, B:504:0x046b, B:514:0x04a6, B:487:0x0431, B:491:0x043c, B:494:0x0447, B:411:0x022c, B:430:0x029d, B:414:0x0237, B:421:0x026e), top: B:668:0x01cf }] */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x04a6 A[Catch: Exception -> 0x09e0, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x09e0, blocks: (B:401:0x01cf, B:408:0x0225, B:416:0x023d, B:417:0x0255, B:419:0x025b, B:420:0x026b, B:436:0x02c8, B:438:0x02d9, B:440:0x02e3, B:442:0x02e9, B:444:0x02f1, B:465:0x032c, B:448:0x02fd, B:449:0x0301, B:451:0x0307, B:473:0x0397, B:479:0x03ef, B:484:0x0429, B:504:0x046b, B:514:0x04a6, B:487:0x0431, B:491:0x043c, B:494:0x0447, B:411:0x022c, B:430:0x029d, B:414:0x0237, B:421:0x026e), top: B:668:0x01cf }] */
    /* JADX WARN: Removed duplicated region for block: B:516:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:519:0x04de  */
    /* JADX WARN: Removed duplicated region for block: B:528:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x0523 A[Catch: Exception -> 0x0516, TRY_ENTER, TRY_LEAVE, TryCatch #7 {Exception -> 0x0516, blocks: (B:524:0x050d, B:531:0x0523, B:536:0x0533, B:541:0x0543), top: B:680:0x050d }] */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0533 A[Catch: Exception -> 0x0516, TRY_ENTER, TRY_LEAVE, TryCatch #7 {Exception -> 0x0516, blocks: (B:524:0x050d, B:531:0x0523, B:536:0x0533, B:541:0x0543), top: B:680:0x050d }] */
    /* JADX WARN: Removed duplicated region for block: B:541:0x0543 A[Catch: Exception -> 0x0516, TRY_ENTER, TRY_LEAVE, TryCatch #7 {Exception -> 0x0516, blocks: (B:524:0x050d, B:531:0x0523, B:536:0x0533, B:541:0x0543), top: B:680:0x050d }] */
    /* JADX WARN: Removed duplicated region for block: B:579:0x06b4 A[Catch: Exception -> 0x06f8, TryCatch #5 {Exception -> 0x06f8, blocks: (B:548:0x05ad, B:550:0x05b5, B:551:0x05b8, B:553:0x05c4, B:556:0x05cf, B:579:0x06b4, B:582:0x06c1, B:585:0x06ca, B:587:0x06d3, B:557:0x05fd, B:559:0x0615, B:560:0x0628, B:562:0x062e, B:563:0x063c, B:565:0x0641, B:567:0x064d, B:568:0x0660, B:570:0x0666, B:571:0x0674, B:573:0x0679, B:574:0x0696, B:576:0x069c, B:577:0x06ae, B:594:0x0716, B:599:0x0727, B:604:0x0738, B:609:0x0749, B:614:0x07c8), top: B:676:0x05ad }] */
    /* JADX WARN: Removed duplicated region for block: B:580:0x06be  */
    /* JADX WARN: Removed duplicated region for block: B:582:0x06c1 A[Catch: Exception -> 0x06f8, TryCatch #5 {Exception -> 0x06f8, blocks: (B:548:0x05ad, B:550:0x05b5, B:551:0x05b8, B:553:0x05c4, B:556:0x05cf, B:579:0x06b4, B:582:0x06c1, B:585:0x06ca, B:587:0x06d3, B:557:0x05fd, B:559:0x0615, B:560:0x0628, B:562:0x062e, B:563:0x063c, B:565:0x0641, B:567:0x064d, B:568:0x0660, B:570:0x0666, B:571:0x0674, B:573:0x0679, B:574:0x0696, B:576:0x069c, B:577:0x06ae, B:594:0x0716, B:599:0x0727, B:604:0x0738, B:609:0x0749, B:614:0x07c8), top: B:676:0x05ad }] */
    /* JADX WARN: Removed duplicated region for block: B:583:0x06c7  */
    /* JADX WARN: Removed duplicated region for block: B:585:0x06ca A[Catch: Exception -> 0x06f8, TryCatch #5 {Exception -> 0x06f8, blocks: (B:548:0x05ad, B:550:0x05b5, B:551:0x05b8, B:553:0x05c4, B:556:0x05cf, B:579:0x06b4, B:582:0x06c1, B:585:0x06ca, B:587:0x06d3, B:557:0x05fd, B:559:0x0615, B:560:0x0628, B:562:0x062e, B:563:0x063c, B:565:0x0641, B:567:0x064d, B:568:0x0660, B:570:0x0666, B:571:0x0674, B:573:0x0679, B:574:0x0696, B:576:0x069c, B:577:0x06ae, B:594:0x0716, B:599:0x0727, B:604:0x0738, B:609:0x0749, B:614:0x07c8), top: B:676:0x05ad }] */
    /* JADX WARN: Removed duplicated region for block: B:586:0x06d1  */
    /* JADX WARN: Removed duplicated region for block: B:594:0x0716 A[Catch: Exception -> 0x06f8, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x06f8, blocks: (B:548:0x05ad, B:550:0x05b5, B:551:0x05b8, B:553:0x05c4, B:556:0x05cf, B:579:0x06b4, B:582:0x06c1, B:585:0x06ca, B:587:0x06d3, B:557:0x05fd, B:559:0x0615, B:560:0x0628, B:562:0x062e, B:563:0x063c, B:565:0x0641, B:567:0x064d, B:568:0x0660, B:570:0x0666, B:571:0x0674, B:573:0x0679, B:574:0x0696, B:576:0x069c, B:577:0x06ae, B:594:0x0716, B:599:0x0727, B:604:0x0738, B:609:0x0749, B:614:0x07c8), top: B:676:0x05ad }] */
    /* JADX WARN: Removed duplicated region for block: B:596:0x0720  */
    /* JADX WARN: Removed duplicated region for block: B:599:0x0727 A[Catch: Exception -> 0x06f8, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x06f8, blocks: (B:548:0x05ad, B:550:0x05b5, B:551:0x05b8, B:553:0x05c4, B:556:0x05cf, B:579:0x06b4, B:582:0x06c1, B:585:0x06ca, B:587:0x06d3, B:557:0x05fd, B:559:0x0615, B:560:0x0628, B:562:0x062e, B:563:0x063c, B:565:0x0641, B:567:0x064d, B:568:0x0660, B:570:0x0666, B:571:0x0674, B:573:0x0679, B:574:0x0696, B:576:0x069c, B:577:0x06ae, B:594:0x0716, B:599:0x0727, B:604:0x0738, B:609:0x0749, B:614:0x07c8), top: B:676:0x05ad }] */
    /* JADX WARN: Removed duplicated region for block: B:604:0x0738 A[Catch: Exception -> 0x06f8, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x06f8, blocks: (B:548:0x05ad, B:550:0x05b5, B:551:0x05b8, B:553:0x05c4, B:556:0x05cf, B:579:0x06b4, B:582:0x06c1, B:585:0x06ca, B:587:0x06d3, B:557:0x05fd, B:559:0x0615, B:560:0x0628, B:562:0x062e, B:563:0x063c, B:565:0x0641, B:567:0x064d, B:568:0x0660, B:570:0x0666, B:571:0x0674, B:573:0x0679, B:574:0x0696, B:576:0x069c, B:577:0x06ae, B:594:0x0716, B:599:0x0727, B:604:0x0738, B:609:0x0749, B:614:0x07c8), top: B:676:0x05ad }] */
    /* JADX WARN: Removed duplicated region for block: B:609:0x0749 A[Catch: Exception -> 0x06f8, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x06f8, blocks: (B:548:0x05ad, B:550:0x05b5, B:551:0x05b8, B:553:0x05c4, B:556:0x05cf, B:579:0x06b4, B:582:0x06c1, B:585:0x06ca, B:587:0x06d3, B:557:0x05fd, B:559:0x0615, B:560:0x0628, B:562:0x062e, B:563:0x063c, B:565:0x0641, B:567:0x064d, B:568:0x0660, B:570:0x0666, B:571:0x0674, B:573:0x0679, B:574:0x0696, B:576:0x069c, B:577:0x06ae, B:594:0x0716, B:599:0x0727, B:604:0x0738, B:609:0x0749, B:614:0x07c8), top: B:676:0x05ad }] */
    /* JADX WARN: Removed duplicated region for block: B:614:0x07c8 A[Catch: Exception -> 0x06f8, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x06f8, blocks: (B:548:0x05ad, B:550:0x05b5, B:551:0x05b8, B:553:0x05c4, B:556:0x05cf, B:579:0x06b4, B:582:0x06c1, B:585:0x06ca, B:587:0x06d3, B:557:0x05fd, B:559:0x0615, B:560:0x0628, B:562:0x062e, B:563:0x063c, B:565:0x0641, B:567:0x064d, B:568:0x0660, B:570:0x0666, B:571:0x0674, B:573:0x0679, B:574:0x0696, B:576:0x069c, B:577:0x06ae, B:594:0x0716, B:599:0x0727, B:604:0x0738, B:609:0x0749, B:614:0x07c8), top: B:676:0x05ad }] */
    /* JADX WARN: Removed duplicated region for block: B:662:0x09f1  */
    /* JADX WARN: Removed duplicated region for block: B:665:0x0a19  */
    /* JADX WARN: Removed duplicated region for block: B:676:0x05ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:680:0x050d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:699:0x032f A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v18, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v54, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    @Override // androidx.work.Worker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.work.ListenableWorker.Result doWork() {
        /*
            Method dump skipped, instructions count: 2642
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.worker.zipElection.ZipElectionWorker.doWork():androidx.work.ListenableWorker$Result");
    }

    private final Map<String, Object> getElectionPageData(ElectionPage electionPage, String str, String str2, String str3, String str4, String str5, Boolean bool, List<Boolean> list, List<Integer> list2, List<Integer> list3) {
        List split$default;
        List takeLast;
        Integer num;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual((Object) ((Boolean) obj), (Object) false) && (num = list2.get(i)) != null) {
                arrayList.add(Integer.valueOf(num.intValue()));
            }
            i = i2;
        }
        int noLembarFinal = getNoLembarFinal(electionPage.getNumber(), electionPage.getJenisPemilihan(), str4);
        Pair[] pairArr = new Pair[4];
        pairArr[0] = TuplesKt.to("uploadUrl", MapsKt.mapOf(TuplesKt.to("jenisImage", Integer.valueOf(electionPage.getKind())), TuplesKt.to("noLembar", Integer.valueOf(noLembarFinal)), TuplesKt.to("usl", str), TuplesKt.to("device", str2), TuplesKt.to("kodeTpsOverride", str4)));
        Pair[] pairArr2 = new Pair[13];
        pairArr2[0] = TuplesKt.to("deviceId", str3);
        pairArr2[1] = TuplesKt.to("userIdOverride", str5);
        pairArr2[2] = TuplesKt.to("kodeTpsOverride", str4);
        pairArr2[3] = TuplesKt.to("jenisImage", Integer.valueOf(electionPage.getKind()));
        pairArr2[4] = TuplesKt.to("noLembar", Integer.valueOf(noLembarFinal));
        pairArr2[5] = TuplesKt.to("device", str2);
        pairArr2[6] = TuplesKt.to("notificationToken", "");
        pairArr2[7] = TuplesKt.to("isValid", true);
        String croppedPhotoPath = electionPage.getCroppedPhotoPath();
        pairArr2[8] = TuplesKt.to("namaFile", (croppedPhotoPath == null || (split$default = StringsKt.split$default((CharSequence) croppedPhotoPath, new String[]{RemoteSettings.FORWARD_SLASH_STRING}, false, 0, 6, (Object) null)) == null || (takeLast = CollectionsKt.takeLast(split$default, 1)) == null) ? null : (String) CollectionsKt.first((List<? extends Object>) takeLast));
        pairArr2[9] = TuplesKt.to("fileHash", electionPage.getHashDocumentCropped());
        pairArr2[10] = TuplesKt.to("sign", electionPage.getSignatureCroppedPhoto());
        pairArr2[11] = TuplesKt.to("usl", str);
        pairArr2[12] = TuplesKt.to("isDummy", BuildConfig.IS_DUMMY_VISION);
        pairArr[1] = TuplesKt.to("upload", MapsKt.mapOf(pairArr2));
        Pair[] pairArr3 = new Pair[8];
        pairArr3[0] = TuplesKt.to("deviceId", str3);
        String idImage = electionPage.getIdImage();
        pairArr3[1] = TuplesKt.to("idImage", Integer.valueOf(ElectionUtil.extractIdImageReal(idImage != null ? idImage : "")));
        pairArr3[2] = TuplesKt.to("isSesuai", bool);
        pairArr3[3] = TuplesKt.to("isSesuaiPerItem", list);
        pairArr3[4] = TuplesKt.to("komentar", null);
        pairArr3[5] = TuplesKt.to("koreksiPerItem", arrayList);
        pairArr3[6] = TuplesKt.to("kodeTps", str4);
        pairArr3[7] = TuplesKt.to("userId", BuildConfig.USER_ID);
        pairArr[2] = TuplesKt.to("kesesuaian", MapsKt.mapOf(pairArr3));
        pairArr[3] = TuplesKt.to("ocr", MapsKt.mapOf(TuplesKt.to("nilaiPerItem", list3)));
        return MapsKt.mapOf(pairArr);
    }

    private final int getNoLembarFinal(int i, String str, String str2) {
        boolean z = false;
        boolean startsWith$default = StringsKt.startsWith$default(str2, "11", false, 2, (Object) null);
        if (Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPR) || ((Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) && !startsWith$default) || (Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPRD_KABKO) && !startsWith$default))) {
            z = true;
        }
        if (i == 18 && z) {
            return 24;
        }
        return i;
    }

    private final void deleteCurrentDocuments(Context context, ElectionWithRelation electionWithRelation) {
        String kodeTps = electionWithRelation.getElection().getTps().getKodeTps();
        String pemilihan = electionWithRelation.getElection().getPemilihan();
        File externalFilesDir = context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_DOCUMENTS + RemoteSettings.FORWARD_SLASH_STRING + pemilihan + electionWithRelation.getElection().getElectionType());
        if (externalFilesDir != null) {
            FileUtil.deleteRecursive(externalFilesDir);
        }
    }

    public final String formatDateTimeForApiNew(Date date, int i, int i2) {
        Intrinsics.checkNotNullParameter(date, "date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", new Locale(JobType.ID));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, 0);
        calendar.set(14, 0);
        String format = simpleDateFormat.format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(newDate.time)");
        return format;
    }

    /* compiled from: ZipElectionWorker.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/support/worker/zipElection/ZipElectionWorker$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "getBackgroundProcessId", "electionId", "getPdfFile", "Ljava/io/File;", "context", "Landroid/content/Context;", "electionWithRelation", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }

        public final String getBackgroundProcessId(String electionId) {
            Intrinsics.checkNotNullParameter(electionId, "electionId");
            return "zip_election_" + electionId;
        }

        public final File getPdfFile(Context context, ElectionWithRelation electionWithRelation) {
            String str;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(electionWithRelation, "electionWithRelation");
            String kodeTps = electionWithRelation.getElection().getTps().getKodeTps();
            String pemilihan = electionWithRelation.getElection().getPemilihan();
            if (electionWithRelation.getElection().isPsu()) {
                str = "Salinan C Hasil " + electionWithRelation.getElection().getElectionTypeDescriptionShort(context) + StringUtils.SPACE + pemilihan + StringUtils.SPACE + kodeTps + ".pdf";
            } else {
                str = "Salinan C Hasil " + pemilihan + StringUtils.SPACE + kodeTps + ".pdf";
            }
            return new File(context.getFilesDir(), str);
        }
    }
}
