package org.informatika.sirekap.ui.witness.attendanceList;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import net.openid.appauth.AuthorizationRequest;
import org.informatika.sirekap.model.Attendance;
import org.informatika.sirekap.model.AttendancePage;
import org.informatika.sirekap.model.AttendanceWithPages;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.security.DeviceSecurityFacade;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.support.worker.uploadAttendancePdf.UploadAttendancePdfTask;
import org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceTask;
import org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceWorker;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel;
import org.informatika.sirekap.usecase.AuthRequestUseCase;
import org.informatika.sirekap.usecase.SendAttendancePdfUseCase;

/* compiled from: WitnessAttendanceListViewModel.kt */
@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001:\u0001XBS\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u001c\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020I0HJ\u000e\u0010)\u001a\u00020D2\u0006\u0010J\u001a\u00020\u001cJ\u000e\u0010K\u001a\u00020D2\u0006\u0010E\u001a\u00020FJ\u0006\u0010L\u001a\u00020DJ\u0006\u0010M\u001a\u00020DJ\u0006\u0010N\u001a\u00020DJ\u000e\u0010O\u001a\u00020D2\u0006\u00105\u001a\u000206J!\u0010P\u001a\u00020D2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010Q\u001a\u000206H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010RJ\u001e\u00109\u001a\u00020D2\u0006\u0010J\u001a\u00020\u001c2\u0006\u0010S\u001a\u00020\u001c2\u0006\u0010T\u001a\u00020,J\u000e\u0010U\u001a\u00020D2\u0006\u0010V\u001a\u000206J\u000e\u0010W\u001a\u00020D2\u0006\u0010E\u001a\u00020FR\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001f\u0010\u001a\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b0\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u001c\u0010\u001e\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001f0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\u0016¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0016\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010+\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020,\u0018\u00010\u001f0\u0016¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020,0\u0016¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0019R\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020,0\u0016¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0019R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020,0\u0016¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0019R\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020,0\u0016¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0019R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020,0\u0016¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0019R\u0019\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001060*¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0016\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010:0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010;\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001f0\u0016¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0019R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0*¢\u0006\b\n\u0000\u001a\u0004\b>\u00108R\u0011\u0010?\u001a\u00020@¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Y"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceListViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "witnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "uploadAttendancePdfTask", "Lorg/informatika/sirekap/support/worker/uploadAttendancePdf/UploadAttendancePdfTask;", "pdfLtvFactory", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "zipAttendanceTask", "Lorg/informatika/sirekap/support/worker/zipAttendance/ZipAttendanceTask;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/BackgroundProcessRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/repository/DefaultWitnessRepository;Lorg/informatika/sirekap/support/worker/uploadAttendancePdf/UploadAttendancePdfTask;Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;Lorg/informatika/sirekap/repository/AuthRepository;Lorg/informatika/sirekap/support/worker/zipAttendance/ZipAttendanceTask;Lorg/informatika/sirekap/repository/BackgroundProcessRepository;)V", "attendance", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/AttendanceWithPages;", "getAttendance", "()Landroidx/lifecycle/LiveData;", "attendanceList", "", "Lorg/informatika/sirekap/model/AttendancePage;", "getAttendanceList", "attendanceListResource", "Lorg/informatika/sirekap/support/Resource;", "authRepositoryUseCase", "Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "backgroundProcessZipAttendance", "Lorg/informatika/sirekap/model/BackgroundProcess;", "getBackgroundProcessZipAttendance", "captureImageUseCase", "Lorg/informatika/sirekap/ui/witness/attendanceList/CaptureAttendanceImageUseCase;", "getCaptureImageUseCase", "()Lorg/informatika/sirekap/ui/witness/attendanceList/CaptureAttendanceImageUseCase;", "deleteAttendance", "Landroidx/lifecycle/MutableLiveData;", "deleteAttendanceResource", "", "getDeleteAttendanceResource", "getEncryptedSharedPreferences", "()Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "isAnyUnverified", "isEmpty", "isLoading", "isLoadingZip", "isZipped", "kodeTps", "", "getKodeTps", "()Landroidx/lifecycle/MutableLiveData;", "moveAttendees", "Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceListViewModel$MoveAttendeesModel;", "moveAttendeesResource", "getMoveAttendeesResource", "retakeAttendancePage", "getRetakeAttendancePage", "sendAttendancePdfUseCase", "Lorg/informatika/sirekap/usecase/SendAttendancePdfUseCase;", "getSendAttendancePdfUseCase", "()Lorg/informatika/sirekap/usecase/SendAttendancePdfUseCase;", "createPdf", "", AuthorizationRequest.ResponseMode.FRAGMENT, "Landroidx/fragment/app/Fragment;", "activityResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "attendancePage", "failedAuthentication", "finishDeletingAttendance", "finishMovingAttendees", "finishTakingPicture", "finishUploadPdfOffline", "makePdfLtv", "pdfFilePath", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "switchedAttendancePage", "isUp", "setup", "_kodeTps", "startGeneratePdf", "MoveAttendeesModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessAttendanceListViewModel extends ViewModel {
    private final LiveData<AttendanceWithPages> attendance;
    private final LiveData<List<AttendancePage>> attendanceList;
    private final LiveData<Resource<AttendanceWithPages>> attendanceListResource;
    private final AuthRequestUseCase authRepositoryUseCase;
    private final LiveData<BackgroundProcess> backgroundProcessZipAttendance;
    private final CaptureAttendanceImageUseCase captureImageUseCase;
    private final MutableLiveData<AttendancePage> deleteAttendance;
    private final LiveData<Resource<Boolean>> deleteAttendanceResource;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final LiveData<Boolean> isAnyUnverified;
    private final LiveData<Boolean> isEmpty;
    private final LiveData<Boolean> isLoading;
    private final LiveData<Boolean> isLoadingZip;
    private final LiveData<Boolean> isZipped;
    private final MutableLiveData<String> kodeTps;
    private final MutableLiveData<MoveAttendeesModel> moveAttendees;
    private final LiveData<Resource<AttendancePage>> moveAttendeesResource;
    private final PdfLtv.Factory pdfLtvFactory;
    private final MutableLiveData<AttendancePage> retakeAttendancePage;
    private final SendAttendancePdfUseCase sendAttendancePdfUseCase;
    private final DefaultWitnessRepository witnessRepository;
    private final ZipAttendanceTask zipAttendanceTask;

    public final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        return this.encryptedSharedPreferences;
    }

    @Inject
    public WitnessAttendanceListViewModel(@ApplicationContext Context context, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, DefaultWitnessRepository witnessRepository, UploadAttendancePdfTask uploadAttendancePdfTask, PdfLtv.Factory pdfLtvFactory, AuthRepository authRepository, ZipAttendanceTask zipAttendanceTask, final BackgroundProcessRepository backgroundProcessRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(witnessRepository, "witnessRepository");
        Intrinsics.checkNotNullParameter(uploadAttendancePdfTask, "uploadAttendancePdfTask");
        Intrinsics.checkNotNullParameter(pdfLtvFactory, "pdfLtvFactory");
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        Intrinsics.checkNotNullParameter(zipAttendanceTask, "zipAttendanceTask");
        Intrinsics.checkNotNullParameter(backgroundProcessRepository, "backgroundProcessRepository");
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.witnessRepository = witnessRepository;
        this.pdfLtvFactory = pdfLtvFactory;
        this.zipAttendanceTask = zipAttendanceTask;
        this.captureImageUseCase = new CaptureAttendanceImageUseCase(context, encryptedSharedPreferences, electionRepository, witnessRepository);
        this.authRepositoryUseCase = new AuthRequestUseCase(authRepository);
        this.sendAttendancePdfUseCase = new SendAttendancePdfUseCase(context, uploadAttendancePdfTask, witnessRepository);
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData;
        LiveData<Resource<AttendanceWithPages>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<AttendanceWithPages>>>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$attendanceListResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<AttendanceWithPages>> invoke(String str) {
                DefaultWitnessRepository defaultWitnessRepository;
                if (str != null) {
                    defaultWitnessRepository = WitnessAttendanceListViewModel.this.witnessRepository;
                    return defaultWitnessRepository.getAttendanceByKodeTps(str);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.attendanceListResource = switchMap;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<AttendanceWithPages>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<AttendanceWithPages> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        LiveData<AttendanceWithPages> map = Transformations.map(switchMap, new Function1<Resource<AttendanceWithPages>, AttendanceWithPages>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$attendance$1
            @Override // kotlin.jvm.functions.Function1
            public final AttendanceWithPages invoke(Resource<AttendanceWithPages> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.attendance = map;
        this.isZipped = Transformations.map(map, new Function1<AttendanceWithPages, Boolean>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$isZipped$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(AttendanceWithPages attendanceWithPages) {
                Attendance attendance;
                return Boolean.valueOf((attendanceWithPages == null || (attendance = attendanceWithPages.getAttendance()) == null) ? false : attendance.isPdf());
            }
        });
        LiveData<List<AttendancePage>> map2 = Transformations.map(switchMap, new Function1<Resource<AttendanceWithPages>, List<AttendancePage>>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$attendanceList$1
            @Override // kotlin.jvm.functions.Function1
            public final List<AttendancePage> invoke(Resource<AttendanceWithPages> resource) {
                List<AttendancePage> pages;
                if (resource == null) {
                    return CollectionsKt.emptyList();
                }
                AttendanceWithPages payload = resource.getPayload();
                if (payload == null || (pages = payload.getPages()) == null) {
                    return null;
                }
                return CollectionsKt.sortedWith(pages, new Comparator() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$attendanceList$1$invoke$$inlined$sortedBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(((AttendancePage) t).getUrutan()), Integer.valueOf(((AttendancePage) t2).getUrutan()));
                    }
                });
            }
        });
        this.attendanceList = map2;
        this.isEmpty = Transformations.map(map2, new Function1<List<AttendancePage>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$isEmpty$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<AttendancePage> list) {
                List<AttendancePage> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.isAnyUnverified = Transformations.map(map2, new Function1<List<AttendancePage>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$isAnyUnverified$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<AttendancePage> list) {
                List<AttendancePage> list2 = list;
                boolean z = false;
                if (list2 == null || list2.isEmpty()) {
                    z = true;
                    break;
                }
                List<AttendancePage> list3 = list;
                if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                    for (AttendancePage attendancePage : list3) {
                        if (!attendancePage.getChecked()) {
                            z = true;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        });
        this.retakeAttendancePage = new MutableLiveData<>(null);
        MutableLiveData<AttendancePage> mutableLiveData2 = new MutableLiveData<>(null);
        this.deleteAttendance = mutableLiveData2;
        this.deleteAttendanceResource = Transformations.switchMap(mutableLiveData2, new Function1<AttendancePage, LiveData<Resource<Boolean>>>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$deleteAttendanceResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<Boolean>> invoke(AttendancePage attendancePage) {
                DefaultWitnessRepository defaultWitnessRepository;
                if (attendancePage != null) {
                    defaultWitnessRepository = WitnessAttendanceListViewModel.this.witnessRepository;
                    return defaultWitnessRepository.deleteAttendance(attendancePage);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        MutableLiveData<MoveAttendeesModel> mutableLiveData3 = new MutableLiveData<>(null);
        this.moveAttendees = mutableLiveData3;
        this.moveAttendeesResource = Transformations.switchMap(mutableLiveData3, new Function1<MoveAttendeesModel, LiveData<Resource<AttendancePage>>>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$moveAttendeesResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<AttendancePage>> invoke(WitnessAttendanceListViewModel.MoveAttendeesModel moveAttendeesModel) {
                DefaultWitnessRepository defaultWitnessRepository;
                if (moveAttendeesModel != null) {
                    defaultWitnessRepository = WitnessAttendanceListViewModel.this.witnessRepository;
                    return defaultWitnessRepository.moveAttendees(moveAttendeesModel.getAttendancePage(), moveAttendeesModel.getSwitchedAttendancePage(), moveAttendeesModel.isUp());
                }
                return AbsentLiveData.Companion.create();
            }
        });
        LiveData<BackgroundProcess> switchMap2 = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<BackgroundProcess>>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$backgroundProcessZipAttendance$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<BackgroundProcess> invoke(String str) {
                if (str == null) {
                    return AbsentLiveData.Companion.create();
                }
                return BackgroundProcessRepository.this.getById(ZipAttendanceWorker.Companion.getBackgroundProcessId(str));
            }
        });
        this.backgroundProcessZipAttendance = switchMap2;
        this.isLoadingZip = Transformations.map(switchMap2, new Function1<BackgroundProcess, Boolean>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$isLoadingZip$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BackgroundProcess backgroundProcess) {
                return Boolean.valueOf(backgroundProcess != null ? backgroundProcess.isLoading() : false);
            }
        });
    }

    public final CaptureAttendanceImageUseCase getCaptureImageUseCase() {
        return this.captureImageUseCase;
    }

    public final SendAttendancePdfUseCase getSendAttendancePdfUseCase() {
        return this.sendAttendancePdfUseCase;
    }

    public final MutableLiveData<String> getKodeTps() {
        return this.kodeTps;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final LiveData<AttendanceWithPages> getAttendance() {
        return this.attendance;
    }

    public final LiveData<Boolean> isZipped() {
        return this.isZipped;
    }

    public final LiveData<List<AttendancePage>> getAttendanceList() {
        return this.attendanceList;
    }

    public final LiveData<Boolean> isEmpty() {
        return this.isEmpty;
    }

    public final LiveData<Boolean> isAnyUnverified() {
        return this.isAnyUnverified;
    }

    public final void setup(String _kodeTps) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        this.kodeTps.postValue(_kodeTps);
    }

    public final MutableLiveData<AttendancePage> getRetakeAttendancePage() {
        return this.retakeAttendancePage;
    }

    public final void finishTakingPicture() {
        this.captureImageUseCase.finishUpdatingAttendance();
        this.captureImageUseCase.finishInsertingAttendance();
        this.retakeAttendancePage.postValue(null);
    }

    public final void deleteAttendance(AttendancePage attendancePage) {
        Intrinsics.checkNotNullParameter(attendancePage, "attendancePage");
        this.deleteAttendance.postValue(attendancePage);
    }

    public final LiveData<Resource<Boolean>> getDeleteAttendanceResource() {
        return this.deleteAttendanceResource;
    }

    public final void finishDeletingAttendance() {
        this.deleteAttendance.postValue(null);
    }

    /* compiled from: WitnessAttendanceListViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceListViewModel$MoveAttendeesModel;", "", "attendancePage", "Lorg/informatika/sirekap/model/AttendancePage;", "switchedAttendancePage", "isUp", "", "(Lorg/informatika/sirekap/model/AttendancePage;Lorg/informatika/sirekap/model/AttendancePage;Z)V", "getAttendancePage", "()Lorg/informatika/sirekap/model/AttendancePage;", "()Z", "getSwitchedAttendancePage", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class MoveAttendeesModel {
        private final AttendancePage attendancePage;
        private final boolean isUp;
        private final AttendancePage switchedAttendancePage;

        public static /* synthetic */ MoveAttendeesModel copy$default(MoveAttendeesModel moveAttendeesModel, AttendancePage attendancePage, AttendancePage attendancePage2, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                attendancePage = moveAttendeesModel.attendancePage;
            }
            if ((i & 2) != 0) {
                attendancePage2 = moveAttendeesModel.switchedAttendancePage;
            }
            if ((i & 4) != 0) {
                z = moveAttendeesModel.isUp;
            }
            return moveAttendeesModel.copy(attendancePage, attendancePage2, z);
        }

        public final AttendancePage component1() {
            return this.attendancePage;
        }

        public final AttendancePage component2() {
            return this.switchedAttendancePage;
        }

        public final boolean component3() {
            return this.isUp;
        }

        public final MoveAttendeesModel copy(AttendancePage attendancePage, AttendancePage switchedAttendancePage, boolean z) {
            Intrinsics.checkNotNullParameter(attendancePage, "attendancePage");
            Intrinsics.checkNotNullParameter(switchedAttendancePage, "switchedAttendancePage");
            return new MoveAttendeesModel(attendancePage, switchedAttendancePage, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof MoveAttendeesModel) {
                MoveAttendeesModel moveAttendeesModel = (MoveAttendeesModel) obj;
                return Intrinsics.areEqual(this.attendancePage, moveAttendeesModel.attendancePage) && Intrinsics.areEqual(this.switchedAttendancePage, moveAttendeesModel.switchedAttendancePage) && this.isUp == moveAttendeesModel.isUp;
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((this.attendancePage.hashCode() * 31) + this.switchedAttendancePage.hashCode()) * 31;
            boolean z = this.isUp;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode + i;
        }

        public String toString() {
            AttendancePage attendancePage = this.attendancePage;
            AttendancePage attendancePage2 = this.switchedAttendancePage;
            return "MoveAttendeesModel(attendancePage=" + attendancePage + ", switchedAttendancePage=" + attendancePage2 + ", isUp=" + this.isUp + ")";
        }

        public MoveAttendeesModel(AttendancePage attendancePage, AttendancePage switchedAttendancePage, boolean z) {
            Intrinsics.checkNotNullParameter(attendancePage, "attendancePage");
            Intrinsics.checkNotNullParameter(switchedAttendancePage, "switchedAttendancePage");
            this.attendancePage = attendancePage;
            this.switchedAttendancePage = switchedAttendancePage;
            this.isUp = z;
        }

        public final AttendancePage getAttendancePage() {
            return this.attendancePage;
        }

        public final AttendancePage getSwitchedAttendancePage() {
            return this.switchedAttendancePage;
        }

        public final boolean isUp() {
            return this.isUp;
        }
    }

    public final void moveAttendees(AttendancePage attendancePage, AttendancePage switchedAttendancePage, boolean z) {
        Intrinsics.checkNotNullParameter(attendancePage, "attendancePage");
        Intrinsics.checkNotNullParameter(switchedAttendancePage, "switchedAttendancePage");
        this.moveAttendees.postValue(new MoveAttendeesModel(attendancePage, switchedAttendancePage, z));
    }

    public final LiveData<Resource<AttendancePage>> getMoveAttendeesResource() {
        return this.moveAttendeesResource;
    }

    public final void finishMovingAttendees() {
        this.moveAttendees.postValue(null);
    }

    public final void startGeneratePdf(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        String value = this.kodeTps.getValue();
        if (value != null) {
            ZipAttendanceTask zipAttendanceTask = this.zipAttendanceTask;
            Context requireContext = fragment.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "fragment.requireContext()");
            zipAttendanceTask.zip(requireContext, value);
        }
    }

    public final void createPdf(final Fragment fragment, ActivityResultLauncher<Intent> activityResult) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(activityResult, "activityResult");
        DeviceSecurityFacade.INSTANCE.doLocalAuthentication(fragment, new BiometricPrompt.AuthenticationCallback() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$createPdf$1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                super.onAuthenticationSucceeded(result);
                WitnessAttendanceListViewModel.this.startGeneratePdf(fragment);
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence errString) {
                Intrinsics.checkNotNullParameter(errString, "errString");
                super.onAuthenticationError(i, errString);
                WitnessAttendanceListViewModel.this.failedAuthentication(fragment);
            }
        }, activityResult);
    }

    public final void failedAuthentication(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Toast.makeText(fragment.requireContext(), "Otentikasi gagal. Silahkan coba lagi", 0).show();
    }

    public final Object makePdfLtv(Context context, String str, Continuation<? super Unit> continuation) {
        Object addPdfVerificationInfo = SecurityFacade.INSTANCE.addPdfVerificationInfo(context, this.authRepositoryUseCase, this.pdfLtvFactory.build(context), new File(str), continuation);
        return addPdfVerificationInfo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? addPdfVerificationInfo : Unit.INSTANCE;
    }

    public final void finishUploadPdfOffline(String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WitnessAttendanceListViewModel$finishUploadPdfOffline$1(this, kodeTps, null), 2, null);
    }

    public final LiveData<BackgroundProcess> getBackgroundProcessZipAttendance() {
        return this.backgroundProcessZipAttendance;
    }

    public final LiveData<Boolean> isLoadingZip() {
        return this.isLoadingZip;
    }
}
