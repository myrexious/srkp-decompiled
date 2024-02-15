package org.informatika.sirekap.ui.electionDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelKt;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import net.openid.appauth.AuthorizationRequest;
import org.apache.commons.lang3.BooleanUtils;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.repository.TpsTimeRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.security.DeviceSecurityFacade;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapTask;
import org.informatika.sirekap.support.worker.zipElection.ZipElectionTask;
import org.informatika.sirekap.support.worker.zipElection.ZipElectionWorker;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.GetElectionUseCase;
import org.informatika.sirekap.ui.dashboard.AprilTagCheckUseCase;
import org.informatika.sirekap.ui.dashboard.CaptureImageUseCase;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.usecase.AuthRequestUseCase;
import org.informatika.sirekap.usecase.GetTpsTimeUseCase;
import org.informatika.sirekap.usecase.SendFormCImageRekapUseCase;

/* compiled from: ElectionDetailViewModel.kt */
@Metadata(d1 = {"\u0000\u0094\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001Bg\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0002\u0010\u0018J\u001c\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020h2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020k0jJ\u000e\u0010l\u001a\u00020f2\u0006\u0010m\u001a\u00020nJ\u000e\u0010o\u001a\u00020f2\u0006\u0010g\u001a\u00020hJ\u0006\u0010p\u001a\u00020fJ\u0006\u0010q\u001a\u00020fJ\u000e\u0010r\u001a\u00020f2\u0006\u00107\u001a\u000208J\u001c\u0010s\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020t\u0018\u00010?0'2\u0006\u0010u\u001a\u000208J!\u0010v\u001a\u00020f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010w\u001a\u000208H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010xJl\u0010y\u001a\u00020f2\u0006\u0010z\u001a\u00020{2\u0006\u0010Q\u001a\u0002082\u0006\u0010O\u001a\u0002082!\u0010|\u001a\u001d\u0012\u0013\u0012\u00110n¢\u0006\f\b~\u0012\b\b\u007f\u0012\u0004\b\b(m\u0012\u0004\u0012\u00020f0}2)\u0010\u0080\u0001\u001a$\u0012\u001a\u0012\u00180\u0081\u0001j\u0003`\u0082\u0001¢\u0006\r\b~\u0012\t\b\u007f\u0012\u0005\b\b(\u0083\u0001\u0012\u0004\u0012\u00020f0}J\u0010\u0010\u0084\u0001\u001a\u00020f2\u0007\u0010\u0085\u0001\u001a\u000205J\"\u0010\u0086\u0001\u001a\u00020f2\u0007\u0010\u0087\u0001\u001a\u0002082\u0007\u0010\u0088\u0001\u001a\u0002082\u0007\u0010\u0089\u0001\u001a\u000208J\u000f\u0010\u008a\u0001\u001a\u00020f2\u0006\u0010g\u001a\u00020hJ\u0018\u0010\u008b\u0001\u001a\u00020f2\u0007\u0010\u008c\u0001\u001a\u00020K2\u0006\u00107\u001a\u000208R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0'¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010+\u001a\u00020,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0019\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010100¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0019\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010500¢\u0006\b\n\u0000\u001a\u0004\b6\u00103R\u0019\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010800¢\u0006\b\n\u0000\u001a\u0004\b9\u00103R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010:\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020<\u0018\u00010;0'¢\u0006\b\n\u0000\u001a\u0004\b=\u0010*R\"\u0010>\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0;\u0018\u00010?0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010@\u001a\u00020A¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0011\u0010D\u001a\u00020E¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0011\u0010H\u001a\u00020E¢\u0006\b\n\u0000\u001a\u0004\bI\u0010GR\u001f\u0010J\u001a\u0010\u0012\f\u0012\n L*\u0004\u0018\u00010K0K00¢\u0006\b\n\u0000\u001a\u0004\bM\u00103R\u0017\u0010N\u001a\b\u0012\u0004\u0012\u00020K0'¢\u0006\b\n\u0000\u001a\u0004\bN\u0010*R\u0019\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010800¢\u0006\b\n\u0000\u001a\u0004\bP\u00103R\u0019\u0010Q\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010800¢\u0006\b\n\u0000\u001a\u0004\bR\u00103R\u001f\u0010S\u001a\u0010\u0012\f\u0012\n L*\u0004\u0018\u00010K0K00¢\u0006\b\n\u0000\u001a\u0004\bT\u00103R\u0019\u0010U\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010500¢\u0006\b\n\u0000\u001a\u0004\bV\u00103R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010W\u001a\u00020KX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u0011\u0010\\\u001a\u00020]¢\u0006\b\n\u0000\u001a\u0004\b^\u0010_R\u001f\u0010`\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020a\u0018\u00010;0'¢\u0006\b\n\u0000\u001a\u0004\bb\u0010*R%\u0010c\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020a0;\u0018\u00010?0'¢\u0006\b\n\u0000\u001a\u0004\bd\u0010*R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u008d\u0001"}, d2 = {"Lorg/informatika/sirekap/ui/electionDetail/ElectionDetailViewModel;", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel;", "context", "Landroid/content/Context;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "uploadFormC1CImageRekapTask", "Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/UploadFormCImageRekapTask;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "witnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "pdfLtvFactory", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;", "tpsTimeRepository", "Lorg/informatika/sirekap/repository/TpsTimeRepository;", "zipElectionTask", "Lorg/informatika/sirekap/support/worker/zipElection/ZipElectionTask;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/BackgroundProcessRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/UploadFormCImageRekapTask;Lorg/informatika/sirekap/repository/FormC1Repository;Lorg/informatika/sirekap/repository/AuthRepository;Lorg/informatika/sirekap/repository/DefaultWitnessRepository;Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;Lorg/informatika/sirekap/repository/TpsTimeRepository;Lorg/informatika/sirekap/support/worker/zipElection/ZipElectionTask;Lorg/informatika/sirekap/repository/BackgroundProcessRepository;)V", "aprilTagCheckUseCase", "Lorg/informatika/sirekap/ui/dashboard/AprilTagCheckUseCase;", "getAprilTagCheckUseCase", "()Lorg/informatika/sirekap/ui/dashboard/AprilTagCheckUseCase;", "aprilTagCode", "", "getAprilTagCode", "()Ljava/lang/Integer;", "setAprilTagCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "authRepositoryUseCase", "Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "backgroundProcessZipElection", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/BackgroundProcess;", "getBackgroundProcessZipElection", "()Landroidx/lifecycle/LiveData;", "captureImageUseCase", "Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase;", "getCaptureImageUseCase", "()Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase;", "confirmedElection", "Landroidx/lifecycle/MutableLiveData;", "Lorg/informatika/sirekap/model/Election;", "getConfirmedElection", "()Landroidx/lifecycle/MutableLiveData;", "confirmedPage", "Lorg/informatika/sirekap/model/ElectionPage;", "getConfirmedPage", "electionId", "", "getElectionId", "elections", "", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getElections", "electionsResource", "Lorg/informatika/sirekap/support/Resource;", "getElectionUseCase", "Lorg/informatika/sirekap/ui/GetElectionUseCase;", "getGetElectionUseCase", "()Lorg/informatika/sirekap/ui/GetElectionUseCase;", "getTpsTimeUseCasePemungutanSuara", "Lorg/informatika/sirekap/usecase/GetTpsTimeUseCase;", "getGetTpsTimeUseCasePemungutanSuara", "()Lorg/informatika/sirekap/usecase/GetTpsTimeUseCase;", "getTpsTimeUseCasePenghitunganSuara", "getGetTpsTimeUseCasePenghitunganSuara", "hideCompletedPages", "", "kotlin.jvm.PlatformType", "getHideCompletedPages", "isLoadingZip", "jenisPemilihan", "getJenisPemilihan", "kodeTps", "getKodeTps", "loadImageProcessing", "getLoadImageProcessing", "manualCaptureElectionPage", "getManualCaptureElectionPage", "saveCorrectedImage", "getSaveCorrectedImage", "()Z", "setSaveCorrectedImage", "(Z)V", "sendFormCImageRekapUseCase", "Lorg/informatika/sirekap/usecase/SendFormCImageRekapUseCase;", "getSendFormCImageRekapUseCase", "()Lorg/informatika/sirekap/usecase/SendFormCImageRekapUseCase;", "witnesses", "Lorg/informatika/sirekap/model/WitnessWithShare;", "getWitnesses", "witnessesResource", "getWitnessesResource", "createPdf", "", AuthorizationRequest.ResponseMode.FRAGMENT, "Landroidx/fragment/app/Fragment;", "activityResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "detectAprilTag", "bitmap", "Landroid/graphics/Bitmap;", "failedAuthentication", "finishDetectingTag", "finishSavingImage", "finishUploadPdfOffline", "getElectionPageStages", "Lorg/informatika/sirekap/model/ElectionPageWithRelation;", "electionPageId", "makePdfLtv", "pdfFilePath", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "savePhoto", "activity", "Landroid/app/Activity;", "onSuccess", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onError", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "setManualCaptureElectionPage", "_electionPage", "setup", "_kodeTps", "_electionId", "_jenisPemilihan", "startGeneratePdf", "updateHideCompletedPages", "newValue", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ElectionDetailViewModel extends BaseVerifyViewModel {
    private final AprilTagCheckUseCase aprilTagCheckUseCase;
    private Integer aprilTagCode;
    private final AuthRequestUseCase authRepositoryUseCase;
    private final LiveData<BackgroundProcess> backgroundProcessZipElection;
    private final CaptureImageUseCase captureImageUseCase;
    private final MutableLiveData<Election> confirmedElection;
    private final MutableLiveData<ElectionPage> confirmedPage;
    private final MutableLiveData<String> electionId;
    private final DefaultElectionRepository electionRepository;
    private final LiveData<List<ElectionWithRelation>> elections;
    private final LiveData<Resource<List<ElectionWithRelation>>> electionsResource;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final GetElectionUseCase getElectionUseCase;
    private final GetTpsTimeUseCase getTpsTimeUseCasePemungutanSuara;
    private final GetTpsTimeUseCase getTpsTimeUseCasePenghitunganSuara;
    private final MutableLiveData<Boolean> hideCompletedPages;
    private final LiveData<Boolean> isLoadingZip;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final MutableLiveData<Boolean> loadImageProcessing;
    private final MutableLiveData<ElectionPage> manualCaptureElectionPage;
    private final PdfLtv.Factory pdfLtvFactory;
    private boolean saveCorrectedImage;
    private final SendFormCImageRekapUseCase sendFormCImageRekapUseCase;
    private final LiveData<List<WitnessWithShare>> witnesses;
    private final LiveData<Resource<List<WitnessWithShare>>> witnessesResource;
    private final ZipElectionTask zipElectionTask;

    @Inject
    public ElectionDetailViewModel(@ApplicationContext Context context, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, UploadFormCImageRekapTask uploadFormC1CImageRekapTask, FormC1Repository formC1Repository, AuthRepository authRepository, final DefaultWitnessRepository witnessRepository, PdfLtv.Factory pdfLtvFactory, TpsTimeRepository tpsTimeRepository, ZipElectionTask zipElectionTask, final BackgroundProcessRepository backgroundProcessRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(uploadFormC1CImageRekapTask, "uploadFormC1CImageRekapTask");
        Intrinsics.checkNotNullParameter(formC1Repository, "formC1Repository");
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        Intrinsics.checkNotNullParameter(witnessRepository, "witnessRepository");
        Intrinsics.checkNotNullParameter(pdfLtvFactory, "pdfLtvFactory");
        Intrinsics.checkNotNullParameter(tpsTimeRepository, "tpsTimeRepository");
        Intrinsics.checkNotNullParameter(zipElectionTask, "zipElectionTask");
        Intrinsics.checkNotNullParameter(backgroundProcessRepository, "backgroundProcessRepository");
        this.electionRepository = electionRepository;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.pdfLtvFactory = pdfLtvFactory;
        this.zipElectionTask = zipElectionTask;
        CaptureImageUseCase captureImageUseCase = new CaptureImageUseCase(context, encryptedSharedPreferences, electionRepository);
        this.captureImageUseCase = captureImageUseCase;
        this.aprilTagCheckUseCase = new AprilTagCheckUseCase(context, captureImageUseCase, ViewModelKt.getViewModelScope(this));
        this.authRepositoryUseCase = new AuthRequestUseCase(authRepository);
        this.sendFormCImageRekapUseCase = new SendFormCImageRekapUseCase(context, uploadFormC1CImageRekapTask, electionRepository);
        this.hideCompletedPages = new MutableLiveData<>(false);
        this.getElectionUseCase = new GetElectionUseCase(electionRepository, formC1Repository);
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData;
        MutableLiveData<String> mutableLiveData2 = new MutableLiveData<>(null);
        this.electionId = mutableLiveData2;
        this.jenisPemilihan = new MutableLiveData<>(null);
        LiveData<Resource<List<ElectionWithRelation>>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<List<ElectionWithRelation>>>>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel$electionsResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<ElectionWithRelation>>> invoke(String str) {
                DefaultElectionRepository defaultElectionRepository;
                if (str == null) {
                    return null;
                }
                defaultElectionRepository = ElectionDetailViewModel.this.electionRepository;
                return defaultElectionRepository.getElectionsByKodeTps(str);
            }
        });
        this.electionsResource = switchMap;
        this.elections = Transformations.map(switchMap, new Function1<Resource<List<ElectionWithRelation>>, List<ElectionWithRelation>>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel$elections$1
            @Override // kotlin.jvm.functions.Function1
            public final List<ElectionWithRelation> invoke(Resource<List<ElectionWithRelation>> resource) {
                if (resource == null) {
                    return CollectionsKt.emptyList();
                }
                return resource.getPayload();
            }
        });
        LiveData<Resource<List<WitnessWithShare>>> switchMap2 = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<List<WitnessWithShare>>>>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel$witnessesResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<WitnessWithShare>>> invoke(String str) {
                if (str == null) {
                    return new MutableLiveData(null);
                }
                return DefaultWitnessRepository.this.getWitnessesByKodeTps(str);
            }
        });
        this.witnessesResource = switchMap2;
        this.witnesses = Transformations.map(switchMap2, new Function1<Resource<List<WitnessWithShare>>, List<WitnessWithShare>>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel$witnesses$1
            @Override // kotlin.jvm.functions.Function1
            public final List<WitnessWithShare> invoke(Resource<List<WitnessWithShare>> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.manualCaptureElectionPage = new MutableLiveData<>(null);
        this.loadImageProcessing = new MutableLiveData<>(false);
        this.confirmedPage = new MutableLiveData<>(null);
        this.confirmedElection = new MutableLiveData<>(null);
        this.getTpsTimeUseCasePenghitunganSuara = new GetTpsTimeUseCase(tpsTimeRepository);
        this.getTpsTimeUseCasePemungutanSuara = new GetTpsTimeUseCase(tpsTimeRepository);
        LiveData<BackgroundProcess> switchMap3 = Transformations.switchMap(mutableLiveData2, new Function1<String, LiveData<BackgroundProcess>>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel$backgroundProcessZipElection$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<BackgroundProcess> invoke(String str) {
                if (str == null) {
                    return AbsentLiveData.Companion.create();
                }
                return BackgroundProcessRepository.this.getById(ZipElectionWorker.Companion.getBackgroundProcessId(str));
            }
        });
        this.backgroundProcessZipElection = switchMap3;
        this.isLoadingZip = Transformations.map(switchMap3, new Function1<BackgroundProcess, Boolean>() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel$isLoadingZip$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BackgroundProcess backgroundProcess) {
                return Boolean.valueOf(backgroundProcess != null ? backgroundProcess.isLoading() : false);
            }
        });
    }

    public final CaptureImageUseCase getCaptureImageUseCase() {
        return this.captureImageUseCase;
    }

    public final boolean getSaveCorrectedImage() {
        return this.saveCorrectedImage;
    }

    public final void setSaveCorrectedImage(boolean z) {
        this.saveCorrectedImage = z;
    }

    public final Integer getAprilTagCode() {
        return this.aprilTagCode;
    }

    public final void setAprilTagCode(Integer num) {
        this.aprilTagCode = num;
    }

    public final AprilTagCheckUseCase getAprilTagCheckUseCase() {
        return this.aprilTagCheckUseCase;
    }

    public final SendFormCImageRekapUseCase getSendFormCImageRekapUseCase() {
        return this.sendFormCImageRekapUseCase;
    }

    public final MutableLiveData<Boolean> getHideCompletedPages() {
        return this.hideCompletedPages;
    }

    public final GetElectionUseCase getGetElectionUseCase() {
        return this.getElectionUseCase;
    }

    public final MutableLiveData<String> getKodeTps() {
        return this.kodeTps;
    }

    public final MutableLiveData<String> getElectionId() {
        return this.electionId;
    }

    public final MutableLiveData<String> getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final LiveData<List<ElectionWithRelation>> getElections() {
        return this.elections;
    }

    public final LiveData<Resource<List<WitnessWithShare>>> getWitnessesResource() {
        return this.witnessesResource;
    }

    public final LiveData<List<WitnessWithShare>> getWitnesses() {
        return this.witnesses;
    }

    public final void setup(String _kodeTps, String _electionId, String _jenisPemilihan) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        Intrinsics.checkNotNullParameter(_electionId, "_electionId");
        Intrinsics.checkNotNullParameter(_jenisPemilihan, "_jenisPemilihan");
        if (!Intrinsics.areEqual(_kodeTps, this.kodeTps.getValue())) {
            this.kodeTps.postValue(_kodeTps);
        }
        if (!Intrinsics.areEqual(_electionId, this.electionId.getValue())) {
            this.electionId.postValue(_electionId);
        }
        if (!Intrinsics.areEqual(_jenisPemilihan, this.jenisPemilihan.getValue())) {
            this.jenisPemilihan.postValue(_jenisPemilihan);
        }
        this.getElectionUseCase.setup(_electionId);
        this.hideCompletedPages.setValue(Boolean.valueOf(Intrinsics.areEqual(this.encryptedSharedPreferences.getStringEncrypted("hideElectionPages25_" + _electionId, BooleanUtils.FALSE), BooleanUtils.TRUE)));
        this.getTpsTimeUseCasePenghitunganSuara.setup(_kodeTps, 1, _jenisPemilihan);
        this.getTpsTimeUseCasePemungutanSuara.setup(_kodeTps, 0, _jenisPemilihan);
    }

    public final void updateHideCompletedPages(boolean z, String electionId) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        this.encryptedSharedPreferences.putStringEncrypted("hideElectionPages25_" + electionId, z ? BooleanUtils.TRUE : BooleanUtils.FALSE);
    }

    public final LiveData<Resource<ElectionPageWithRelation>> getElectionPageStages(String electionPageId) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        return this.electionRepository.getElectionPageStagesByElectionPageId(electionPageId);
    }

    public final void startGeneratePdf(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        ZipElectionTask zipElectionTask = this.zipElectionTask;
        Context requireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "fragment.requireContext()");
        String value = this.kodeTps.getValue();
        Intrinsics.checkNotNull(value);
        String value2 = this.electionId.getValue();
        Intrinsics.checkNotNull(value2);
        String value3 = this.jenisPemilihan.getValue();
        Intrinsics.checkNotNull(value3);
        zipElectionTask.zip(requireContext, value, value2, value3);
    }

    public final void createPdf(final Fragment fragment, ActivityResultLauncher<Intent> activityResult) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(activityResult, "activityResult");
        DeviceSecurityFacade.INSTANCE.doLocalAuthentication(fragment, new BiometricPrompt.AuthenticationCallback() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel$createPdf$1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                ElectionDetailViewModel.this.startGeneratePdf(fragment);
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence errString) {
                Intrinsics.checkNotNullParameter(errString, "errString");
                ElectionDetailViewModel.this.failedAuthentication(fragment);
            }
        }, activityResult);
    }

    public final void failedAuthentication(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Toast.makeText(fragment.requireContext(), "Otentikasi gagal. Silahkan coba lagi", 0).show();
    }

    public final MutableLiveData<ElectionPage> getManualCaptureElectionPage() {
        return this.manualCaptureElectionPage;
    }

    public final void setManualCaptureElectionPage(ElectionPage _electionPage) {
        Intrinsics.checkNotNullParameter(_electionPage, "_electionPage");
        if (Intrinsics.areEqual(_electionPage, this.manualCaptureElectionPage.getValue())) {
            return;
        }
        this.manualCaptureElectionPage.setValue(_electionPage);
    }

    public final MutableLiveData<Boolean> getLoadImageProcessing() {
        return this.loadImageProcessing;
    }

    public final void savePhoto(Activity activity, String kodeTps, String jenisPemilihan, Function1<? super Bitmap, Unit> onSuccess, Function1<? super Exception, Unit> onError) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        this.loadImageProcessing.postValue(true);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ElectionDetailViewModel$savePhoto$1(this, activity, kodeTps, jenisPemilihan, onSuccess, onError, null), 2, null);
    }

    public final void detectAprilTag(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.loadImageProcessing.postValue(true);
        this.aprilTagCheckUseCase.detect(bitmap);
    }

    public final void finishDetectingTag() {
        this.loadImageProcessing.postValue(false);
        this.aprilTagCheckUseCase.getDetectionResult().postValue(null);
    }

    public final void finishSavingImage() {
        this.loadImageProcessing.postValue(false);
        this.confirmedPage.postValue(null);
        this.confirmedElection.postValue(null);
        this.saveCorrectedImage = false;
        this.aprilTagCode = null;
    }

    public final void finishUploadPdfOffline(String electionId) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ElectionDetailViewModel$finishUploadPdfOffline$1(this, electionId, null), 2, null);
    }

    public final Object makePdfLtv(Context context, String str, Continuation<? super Unit> continuation) {
        Object addPdfVerificationInfo = SecurityFacade.INSTANCE.addPdfVerificationInfo(context, this.authRepositoryUseCase, this.pdfLtvFactory.build(context), new File(str), continuation);
        return addPdfVerificationInfo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? addPdfVerificationInfo : Unit.INSTANCE;
    }

    public final MutableLiveData<ElectionPage> getConfirmedPage() {
        return this.confirmedPage;
    }

    public final MutableLiveData<Election> getConfirmedElection() {
        return this.confirmedElection;
    }

    public final GetTpsTimeUseCase getGetTpsTimeUseCasePenghitunganSuara() {
        return this.getTpsTimeUseCasePenghitunganSuara;
    }

    public final GetTpsTimeUseCase getGetTpsTimeUseCasePemungutanSuara() {
        return this.getTpsTimeUseCasePemungutanSuara;
    }

    public final LiveData<BackgroundProcess> getBackgroundProcessZipElection() {
        return this.backgroundProcessZipElection;
    }

    public final LiveData<Boolean> isLoadingZip() {
        return this.isLoadingZip;
    }
}
