package org.informatika.sirekap.ui.witness.attendanceList;

import android.content.Context;
import android.os.Environment;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.WitnessRepository;
import org.informatika.sirekap.support.FileUtil;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: ZipAttendanceUseCase.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001,B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ[\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001e2\u0006\u0010%\u001a\u00020&H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010'J\u0018\u0010(\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001fH\u0002J\u0006\u0010)\u001a\u00020\u0018J\u0016\u0010*\u001a\u00020+2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001fR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\r\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u001f\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/ZipAttendanceUseCase;", "", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "witnessRepository", "Lorg/informatika/sirekap/repository/WitnessRepository;", "pdfLtv", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv;", "authRequestUseCase", "Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "(Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/repository/WitnessRepository;Lorg/informatika/sirekap/support/security/pdf/PdfLtv;Lorg/informatika/sirekap/usecase/AuthRequestUseCase;Lorg/informatika/sirekap/repository/AuthRepository;)V", "isLoading", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "()Landroidx/lifecycle/MutableLiveData;", "result", "Lorg/informatika/sirekap/ui/witness/attendanceList/ZipAttendanceUseCase$CreatePdfModel;", "getResult", "shouldRelogin", "getShouldRelogin", "createPdf", "", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "images", "", "", "kodeTps", "witnesses", "Lorg/informatika/sirekap/model/WitnessWithShare;", "pages", "", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "(Lkotlinx/coroutines/CoroutineScope;Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lorg/informatika/sirekap/db/AppDatabase;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCurrentDocuments", "finishCreatePdf", "getPdfFile", "Ljava/io/File;", "CreatePdfModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ZipAttendanceUseCase {
    private final AuthRepository authRepository;
    private final AuthRequestUseCase authRequestUseCase;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final MutableLiveData<Boolean> isLoading;
    private final PdfLtv pdfLtv;
    private final MutableLiveData<CreatePdfModel> result;
    private final MutableLiveData<Boolean> shouldRelogin;
    private final WitnessRepository witnessRepository;

    public ZipAttendanceUseCase(EncryptedSharedPreferences encryptedSharedPreferences, WitnessRepository witnessRepository, PdfLtv pdfLtv, AuthRequestUseCase authRequestUseCase, AuthRepository authRepository) {
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(witnessRepository, "witnessRepository");
        Intrinsics.checkNotNullParameter(pdfLtv, "pdfLtv");
        Intrinsics.checkNotNullParameter(authRequestUseCase, "authRequestUseCase");
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.witnessRepository = witnessRepository;
        this.pdfLtv = pdfLtv;
        this.authRequestUseCase = authRequestUseCase;
        this.authRepository = authRepository;
        this.shouldRelogin = new MutableLiveData<>(false);
        this.isLoading = new MutableLiveData<>(false);
        this.result = new MutableLiveData<>(null);
    }

    public final MutableLiveData<Boolean> getShouldRelogin() {
        return this.shouldRelogin;
    }

    /* compiled from: ZipAttendanceUseCase.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/ZipAttendanceUseCase$CreatePdfModel;", "", "isSuccess", "", "errorMessage", "", "(ZLjava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "()Z", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class CreatePdfModel {
        private final String errorMessage;
        private final boolean isSuccess;

        public CreatePdfModel(boolean z, String str) {
            this.isSuccess = z;
            this.errorMessage = str;
        }

        public /* synthetic */ CreatePdfModel(boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? null : str);
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }

        public final String getErrorMessage() {
            return this.errorMessage;
        }
    }

    public final MutableLiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final MutableLiveData<CreatePdfModel> getResult() {
        return this.result;
    }

    public final Object createPdf(CoroutineScope coroutineScope, Context context, List<String> list, String str, List<WitnessWithShare> list2, List<Integer> list3, AppDatabase appDatabase, Continuation<? super Unit> continuation) {
        this.isLoading.postValue(Boxing.boxBoolean(true));
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new ZipAttendanceUseCase$createPdf$2(context, this, str, list, list3, appDatabase, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void finishCreatePdf() {
        this.result.postValue(null);
    }

    public final void deleteCurrentDocuments(Context context, String str) {
        String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
        String str2 = stringEncrypted;
        File externalFilesDir = context.getExternalFilesDir(str + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_DOCUMENTS + "/daftar_hadir" + (str2 == null || StringsKt.isBlank(str2) ? "R" : stringEncrypted));
        if (externalFilesDir != null) {
            FileUtil.deleteRecursive(externalFilesDir);
        }
    }

    public final File getPdfFile(Context context, String kodeTps) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
        String str = stringEncrypted;
        return new File(context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_DOCUMENTS + "/daftar_hadir" + (str == null || StringsKt.isBlank(str) ? "R" : stringEncrypted)), "Salinan Daftar Hadir " + kodeTps + ".pdf");
    }
}
