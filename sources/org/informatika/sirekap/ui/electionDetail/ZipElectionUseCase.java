package org.informatika.sirekap.ui.electionDetail;

import android.content.Context;
import android.os.Environment;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1TabulationComplete;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.support.FileUtil;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: ZipElectionUseCase.kt */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 :2\u00020\u0001:\u0002:;B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJx\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001e2\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u001e2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u001e2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010-J\u0018\u0010/\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!H\u0002J\u0006\u00100\u001a\u00020\u0018J\u001e\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206J\u0016\u00108\u001a\u0002092\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\r\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u001f\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011¨\u0006<"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ZipElectionUseCase;", "", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "pdfLtv", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv;", "authRequestUseCase", "Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "(Lorg/informatika/sirekap/repository/ElectionRepository;Lorg/informatika/sirekap/support/security/pdf/PdfLtv;Lorg/informatika/sirekap/usecase/AuthRequestUseCase;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/repository/AuthRepository;)V", "isLoading", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "()Landroidx/lifecycle/MutableLiveData;", "result", "Lorg/informatika/sirekap/ui/electionDetail/ZipElectionUseCase$CreatePdfModel;", "getResult", "shouldRelogin", "getShouldRelogin", "createPdf", "", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "witnesses", "", "Lorg/informatika/sirekap/model/WitnessWithShare;", "electionWithRelation", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "candidates", "Lorg/informatika/sirekap/model/Candidate;", "formC1AdministrationComplete", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "formC1TabulationCompletesSectionIV", "Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "formC1TabulationPartaiCompletesSectionIV", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "formC1TabulationCompleteSectionV", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "tpsTimePenghitunganSuara", "Lorg/informatika/sirekap/model/TpsTime;", "tpsTimePemungutanSuara", "deleteCurrentDocuments", "finishCreatePdf", "formatDateTimeForApiNew", "", "date", "Ljava/util/Date;", "hour", "", "minute", "getPdfFile", "Ljava/io/File;", "Companion", "CreatePdfModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ZipElectionUseCase {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ZipElectionUseCase";
    private final AuthRepository authRepository;
    private final AuthRequestUseCase authRequestUseCase;
    private final ElectionRepository electionRepository;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final MutableLiveData<Boolean> isLoading;
    private final PdfLtv pdfLtv;
    private final MutableLiveData<CreatePdfModel> result;
    private final MutableLiveData<Boolean> shouldRelogin;

    public ZipElectionUseCase(ElectionRepository electionRepository, PdfLtv pdfLtv, AuthRequestUseCase authRequestUseCase, EncryptedSharedPreferences encryptedSharedPreferences, AuthRepository authRepository) {
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(pdfLtv, "pdfLtv");
        Intrinsics.checkNotNullParameter(authRequestUseCase, "authRequestUseCase");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        this.electionRepository = electionRepository;
        this.pdfLtv = pdfLtv;
        this.authRequestUseCase = authRequestUseCase;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.authRepository = authRepository;
        this.isLoading = new MutableLiveData<>(false);
        this.result = new MutableLiveData<>(null);
        this.shouldRelogin = new MutableLiveData<>(false);
    }

    /* compiled from: ZipElectionUseCase.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ZipElectionUseCase$CreatePdfModel;", "", "isSuccess", "", "errorMessage", "", "(ZLjava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "()Z", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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

    public final MutableLiveData<Boolean> getShouldRelogin() {
        return this.shouldRelogin;
    }

    public final void createPdf(CoroutineScope viewModelScope, Context context, List<WitnessWithShare> witnesses, ElectionWithRelation electionWithRelation, List<Candidate> candidates, FormC1AdministrationComplete formC1AdministrationComplete, List<FormC1TabulationComplete> formC1TabulationCompletesSectionIV, List<FormC1TabulationPartaiComplete> formC1TabulationPartaiCompletesSectionIV, FormC1AdministrationHal2Complete formC1TabulationCompleteSectionV, TpsTime tpsTimePenghitunganSuara, TpsTime tpsTime) {
        Intrinsics.checkNotNullParameter(viewModelScope, "viewModelScope");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(witnesses, "witnesses");
        Intrinsics.checkNotNullParameter(electionWithRelation, "electionWithRelation");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        Intrinsics.checkNotNullParameter(formC1AdministrationComplete, "formC1AdministrationComplete");
        Intrinsics.checkNotNullParameter(formC1TabulationCompletesSectionIV, "formC1TabulationCompletesSectionIV");
        Intrinsics.checkNotNullParameter(formC1TabulationPartaiCompletesSectionIV, "formC1TabulationPartaiCompletesSectionIV");
        Intrinsics.checkNotNullParameter(formC1TabulationCompleteSectionV, "formC1TabulationCompleteSectionV");
        Intrinsics.checkNotNullParameter(tpsTimePenghitunganSuara, "tpsTimePenghitunganSuara");
        this.isLoading.postValue(true);
        BuildersKt__Builders_commonKt.launch$default(viewModelScope, Dispatchers.getIO(), null, new ZipElectionUseCase$createPdf$1(context, this, electionWithRelation, formC1AdministrationComplete, formC1TabulationCompletesSectionIV, formC1TabulationPartaiCompletesSectionIV, formC1TabulationCompleteSectionV, witnesses, tpsTime, tpsTimePenghitunganSuara, candidates, null), 2, null);
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

    public final void finishCreatePdf() {
        this.result.postValue(null);
    }

    public final void deleteCurrentDocuments(Context context, ElectionWithRelation electionWithRelation) {
        String kodeTps = electionWithRelation.getElection().getTps().getKodeTps();
        String pemilihan = electionWithRelation.getElection().getPemilihan();
        File externalFilesDir = context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_DOCUMENTS + RemoteSettings.FORWARD_SLASH_STRING + pemilihan + electionWithRelation.getElection().getElectionType());
        if (externalFilesDir != null) {
            FileUtil.deleteRecursive(externalFilesDir);
        }
    }

    public final File getPdfFile(Context context, ElectionWithRelation electionWithRelation) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionWithRelation, "electionWithRelation");
        String kodeTps = electionWithRelation.getElection().getTps().getKodeTps();
        String pemilihan = electionWithRelation.getElection().getPemilihan();
        electionWithRelation.getElection().getElectionType();
        if (electionWithRelation.getElection().isPsu()) {
            str = "Salinan C Hasil " + electionWithRelation.getElection().getElectionTypeDescriptionShort(context) + StringUtils.SPACE + pemilihan + StringUtils.SPACE + kodeTps + ".pdf";
        } else {
            str = "Salinan C Hasil " + pemilihan + StringUtils.SPACE + kodeTps + ".pdf";
        }
        return new File(context.getFilesDir(), str);
    }

    /* compiled from: ZipElectionUseCase.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ZipElectionUseCase$Companion;", "", "()V", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
