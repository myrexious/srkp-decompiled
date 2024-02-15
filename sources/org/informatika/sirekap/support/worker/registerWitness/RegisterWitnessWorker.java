package org.informatika.sirekap.support.worker.registerWitness;

import android.content.Context;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import dagger.hilt.android.EntryPointAccessors;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.repository.DefaultBackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: RegisterWitnessWorker.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000f\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/support/worker/registerWitness/RegisterWitnessWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "getAppDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "getBackgroundProcessRepository", "Lorg/informatika/sirekap/repository/DefaultBackgroundProcessRepository;", "getWitnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "Companion", "RegisterWitnessWorkerEntryPoint", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RegisterWitnessWorker extends Worker {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "RegisterWitnessWorker";
    private final Context appContext;

    /* compiled from: RegisterWitnessWorker.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/support/worker/registerWitness/RegisterWitnessWorker$RegisterWitnessWorkerEntryPoint;", "", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/DefaultBackgroundProcessRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "witnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface RegisterWitnessWorkerEntryPoint {
        AppDatabase appDatabase();

        DefaultBackgroundProcessRepository backgroundProcessRepository();

        EncryptedSharedPreferences encryptedSharedPreferences();

        DefaultWitnessRepository witnessRepository();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RegisterWitnessWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.appContext = appContext;
    }

    private final AppDatabase getAppDatabase() {
        return ((RegisterWitnessWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, RegisterWitnessWorkerEntryPoint.class)).appDatabase();
    }

    private final DefaultBackgroundProcessRepository getBackgroundProcessRepository() {
        return ((RegisterWitnessWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, RegisterWitnessWorkerEntryPoint.class)).backgroundProcessRepository();
    }

    private final DefaultWitnessRepository getWitnessRepository() {
        return ((RegisterWitnessWorkerEntryPoint) EntryPointAccessors.fromApplication(this.appContext, RegisterWitnessWorkerEntryPoint.class)).witnessRepository();
    }

    /* JADX WARN: Removed duplicated region for block: B:154:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01be  */
    @Override // androidx.work.Worker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.work.ListenableWorker.Result doWork() {
        /*
            Method dump skipped, instructions count: 510
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessWorker.doWork():androidx.work.ListenableWorker$Result");
    }

    /* compiled from: RegisterWitnessWorker.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/support/worker/registerWitness/RegisterWitnessWorker$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "getBackgroundProcessId", "noHandphone", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }

        public final String getBackgroundProcessId(String noHandphone) {
            Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
            return "register_witness_" + noHandphone;
        }
    }
}
