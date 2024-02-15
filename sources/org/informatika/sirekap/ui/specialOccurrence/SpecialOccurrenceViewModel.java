package org.informatika.sirekap.ui.specialOccurrence;

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
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.SpecialOccurrence;
import org.informatika.sirekap.model.SpecialOccurrencePage;
import org.informatika.sirekap.model.SpecialOccurrenceWithPages;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.SpecialOccurrenceRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.security.DeviceSecurityFacade;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.support.worker.uploadSpecialOccurrence.UploadSpecialOccurrenceTask;
import org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceTask;
import org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceWorker;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.usecase.AuthRequestUseCase;
import org.informatika.sirekap.usecase.SendSpecialOccurrencePdfUseCase;

/* compiled from: SpecialOccurrenceViewModel.kt */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001BS\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u001c\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020E0DJ\u000e\u0010F\u001a\u00020@2\u0006\u0010G\u001a\u00020\"J\u000e\u0010H\u001a\u00020@2\u0006\u0010A\u001a\u00020BJ\u0006\u0010I\u001a\u00020@J\u0006\u0010J\u001a\u00020@J\u000e\u0010K\u001a\u00020@2\u0006\u0010.\u001a\u00020/J!\u0010L\u001a\u00020@2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010M\u001a\u00020/H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u000e\u0010O\u001a\u00020@2\u0006\u0010P\u001a\u00020/J\u000e\u0010Q\u001a\u00020@2\u0006\u0010A\u001a\u00020BR\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010#\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020%\u0018\u00010$0\u0018¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020%0\u0018¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020%0\u0018¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001bR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020%0\u0018¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001bR\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020%0\u0018¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001bR\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020%0\u0018¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001bR\u0019\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010/0!¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0!¢\u0006\b\n\u0000\u001a\u0004\b3\u00101R\u0011\u00104\u001a\u000205¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0019\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090\u0018¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u001bR\u001f\u0010;\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\"\u0018\u00010<0\u0018¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u001bR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010>\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000209\u0018\u00010$0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006R"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/SpecialOccurrenceViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "uploadTask", "Lorg/informatika/sirekap/support/worker/uploadSpecialOccurrence/UploadSpecialOccurrenceTask;", "specialOccurrenceRepository", "Lorg/informatika/sirekap/repository/SpecialOccurrenceRepository;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "pdfLtvFactory", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;", "zipSpecialOccurrenceTask", "Lorg/informatika/sirekap/support/worker/zipSpecialOccurrence/ZipSpecialOccurrenceTask;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/BackgroundProcessRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/support/worker/uploadSpecialOccurrence/UploadSpecialOccurrenceTask;Lorg/informatika/sirekap/repository/SpecialOccurrenceRepository;Lorg/informatika/sirekap/repository/AuthRepository;Lorg/informatika/sirekap/support/security/pdf/PdfLtv$Factory;Lorg/informatika/sirekap/support/worker/zipSpecialOccurrence/ZipSpecialOccurrenceTask;Lorg/informatika/sirekap/repository/BackgroundProcessRepository;)V", "authRepositoryUseCase", "Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "backgroundProcessZipSpecialOccurrence", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/BackgroundProcess;", "getBackgroundProcessZipSpecialOccurrence", "()Landroidx/lifecycle/LiveData;", "captureImageUseCase", "Lorg/informatika/sirekap/ui/specialOccurrence/CaptureSpecialOccurrenceImageUseCase;", "getCaptureImageUseCase", "()Lorg/informatika/sirekap/ui/specialOccurrence/CaptureSpecialOccurrenceImageUseCase;", "deleteSpecialOccurrencePage", "Landroidx/lifecycle/MutableLiveData;", "Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "deleteSpecialOccurrenceResource", "Lorg/informatika/sirekap/support/Resource;", "", "getDeleteSpecialOccurrenceResource", "getEncryptedSharedPreferences", "()Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "isAnyUnverified", "isEmpty", "isLoading", "isLoadingZip", "isZipped", "kodeTps", "", "getKodeTps", "()Landroidx/lifecycle/MutableLiveData;", "retakeSpecialOccurrencePage", "getRetakeSpecialOccurrencePage", "sendSpecialOccurrencePdfUseCase", "Lorg/informatika/sirekap/usecase/SendSpecialOccurrencePdfUseCase;", "getSendSpecialOccurrencePdfUseCase", "()Lorg/informatika/sirekap/usecase/SendSpecialOccurrencePdfUseCase;", "specialOccurrence", "Lorg/informatika/sirekap/model/SpecialOccurrenceWithPages;", "getSpecialOccurrence", "specialOccurrenceList", "", "getSpecialOccurrenceList", "specialOccurrenceResource", "createPdf", "", AuthorizationRequest.ResponseMode.FRAGMENT, "Landroidx/fragment/app/Fragment;", "activityResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "deleteSpecialOccurrence", "specialOccurrencePage", "failedAuthentication", "finishDeletingSpecialOccurrencePage", "finishTakingPicture", "finishUploadPdfOffline", "makePdfLtv", "pdfFilePath", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setup", "_kodeTps", "startGeneratePdf", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SpecialOccurrenceViewModel extends ViewModel {
    private final AuthRequestUseCase authRepositoryUseCase;
    private final LiveData<BackgroundProcess> backgroundProcessZipSpecialOccurrence;
    private final CaptureSpecialOccurrenceImageUseCase captureImageUseCase;
    private final MutableLiveData<SpecialOccurrencePage> deleteSpecialOccurrencePage;
    private final LiveData<Resource<Boolean>> deleteSpecialOccurrenceResource;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final LiveData<Boolean> isAnyUnverified;
    private final LiveData<Boolean> isEmpty;
    private final LiveData<Boolean> isLoading;
    private final LiveData<Boolean> isLoadingZip;
    private final LiveData<Boolean> isZipped;
    private final MutableLiveData<String> kodeTps;
    private final PdfLtv.Factory pdfLtvFactory;
    private final MutableLiveData<SpecialOccurrencePage> retakeSpecialOccurrencePage;
    private final SendSpecialOccurrencePdfUseCase sendSpecialOccurrencePdfUseCase;
    private final LiveData<SpecialOccurrenceWithPages> specialOccurrence;
    private final LiveData<List<SpecialOccurrencePage>> specialOccurrenceList;
    private final SpecialOccurrenceRepository specialOccurrenceRepository;
    private final LiveData<Resource<SpecialOccurrenceWithPages>> specialOccurrenceResource;
    private final ZipSpecialOccurrenceTask zipSpecialOccurrenceTask;

    public final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        return this.encryptedSharedPreferences;
    }

    @Inject
    public SpecialOccurrenceViewModel(@ApplicationContext Context context, final DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, UploadSpecialOccurrenceTask uploadTask, SpecialOccurrenceRepository specialOccurrenceRepository, AuthRepository authRepository, PdfLtv.Factory pdfLtvFactory, ZipSpecialOccurrenceTask zipSpecialOccurrenceTask, final BackgroundProcessRepository backgroundProcessRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(uploadTask, "uploadTask");
        Intrinsics.checkNotNullParameter(specialOccurrenceRepository, "specialOccurrenceRepository");
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        Intrinsics.checkNotNullParameter(pdfLtvFactory, "pdfLtvFactory");
        Intrinsics.checkNotNullParameter(zipSpecialOccurrenceTask, "zipSpecialOccurrenceTask");
        Intrinsics.checkNotNullParameter(backgroundProcessRepository, "backgroundProcessRepository");
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.specialOccurrenceRepository = specialOccurrenceRepository;
        this.pdfLtvFactory = pdfLtvFactory;
        this.zipSpecialOccurrenceTask = zipSpecialOccurrenceTask;
        this.captureImageUseCase = new CaptureSpecialOccurrenceImageUseCase(context, encryptedSharedPreferences, electionRepository);
        this.authRepositoryUseCase = new AuthRequestUseCase(authRepository);
        this.sendSpecialOccurrencePdfUseCase = new SendSpecialOccurrencePdfUseCase(context, uploadTask, specialOccurrenceRepository);
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData;
        LiveData<Resource<SpecialOccurrenceWithPages>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<SpecialOccurrenceWithPages>>>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$specialOccurrenceResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<SpecialOccurrenceWithPages>> invoke(String str) {
                if (str == null) {
                    return AbsentLiveData.Companion.create();
                }
                return DefaultElectionRepository.this.getSpecialOccurrenceByKodeTps(str);
            }
        });
        this.specialOccurrenceResource = switchMap;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<SpecialOccurrenceWithPages>, Boolean>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<SpecialOccurrenceWithPages> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        LiveData<SpecialOccurrenceWithPages> map = Transformations.map(switchMap, new Function1<Resource<SpecialOccurrenceWithPages>, SpecialOccurrenceWithPages>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$specialOccurrence$1
            @Override // kotlin.jvm.functions.Function1
            public final SpecialOccurrenceWithPages invoke(Resource<SpecialOccurrenceWithPages> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.specialOccurrence = map;
        this.isZipped = Transformations.map(map, new Function1<SpecialOccurrenceWithPages, Boolean>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$isZipped$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(SpecialOccurrenceWithPages specialOccurrenceWithPages) {
                SpecialOccurrence specialOccurrence;
                return Boolean.valueOf((specialOccurrenceWithPages == null || (specialOccurrence = specialOccurrenceWithPages.getSpecialOccurrence()) == null) ? false : specialOccurrence.isPdf());
            }
        });
        LiveData<List<SpecialOccurrencePage>> map2 = Transformations.map(switchMap, new Function1<Resource<SpecialOccurrenceWithPages>, List<SpecialOccurrencePage>>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$specialOccurrenceList$1
            @Override // kotlin.jvm.functions.Function1
            public final List<SpecialOccurrencePage> invoke(Resource<SpecialOccurrenceWithPages> resource) {
                if (resource == null) {
                    return CollectionsKt.emptyList();
                }
                SpecialOccurrenceWithPages payload = resource.getPayload();
                if (payload != null) {
                    return payload.getPages();
                }
                return null;
            }
        });
        this.specialOccurrenceList = map2;
        this.isEmpty = Transformations.map(map2, new Function1<List<SpecialOccurrencePage>, Boolean>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$isEmpty$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<SpecialOccurrencePage> list) {
                List<SpecialOccurrencePage> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.isAnyUnverified = Transformations.map(map2, new Function1<List<SpecialOccurrencePage>, Boolean>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$isAnyUnverified$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<SpecialOccurrencePage> list) {
                List<SpecialOccurrencePage> list2 = list;
                boolean z = false;
                if (list2 == null || list2.isEmpty()) {
                    z = true;
                    break;
                }
                List<SpecialOccurrencePage> list3 = list;
                if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                    for (SpecialOccurrencePage specialOccurrencePage : list3) {
                        if (!specialOccurrencePage.getChecked()) {
                            z = true;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        });
        this.retakeSpecialOccurrencePage = new MutableLiveData<>(null);
        MutableLiveData<SpecialOccurrencePage> mutableLiveData2 = new MutableLiveData<>(null);
        this.deleteSpecialOccurrencePage = mutableLiveData2;
        this.deleteSpecialOccurrenceResource = Transformations.switchMap(mutableLiveData2, new Function1<SpecialOccurrencePage, LiveData<Resource<Boolean>>>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$deleteSpecialOccurrenceResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<Boolean>> invoke(SpecialOccurrencePage specialOccurrencePage) {
                if (specialOccurrencePage == null) {
                    return AbsentLiveData.Companion.create();
                }
                return DefaultElectionRepository.this.deleteSpecialOccurrence(specialOccurrencePage);
            }
        });
        LiveData<BackgroundProcess> switchMap2 = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<BackgroundProcess>>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$backgroundProcessZipSpecialOccurrence$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<BackgroundProcess> invoke(String str) {
                if (str == null) {
                    return AbsentLiveData.Companion.create();
                }
                return BackgroundProcessRepository.this.getById(ZipSpecialOccurrenceWorker.Companion.getBackgroundProcessId(str));
            }
        });
        this.backgroundProcessZipSpecialOccurrence = switchMap2;
        this.isLoadingZip = Transformations.map(switchMap2, new Function1<BackgroundProcess, Boolean>() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$isLoadingZip$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BackgroundProcess backgroundProcess) {
                return Boolean.valueOf(backgroundProcess != null ? backgroundProcess.isLoading() : false);
            }
        });
    }

    public final CaptureSpecialOccurrenceImageUseCase getCaptureImageUseCase() {
        return this.captureImageUseCase;
    }

    public final SendSpecialOccurrencePdfUseCase getSendSpecialOccurrencePdfUseCase() {
        return this.sendSpecialOccurrencePdfUseCase;
    }

    public final MutableLiveData<String> getKodeTps() {
        return this.kodeTps;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final LiveData<SpecialOccurrenceWithPages> getSpecialOccurrence() {
        return this.specialOccurrence;
    }

    public final LiveData<Boolean> isZipped() {
        return this.isZipped;
    }

    public final LiveData<List<SpecialOccurrencePage>> getSpecialOccurrenceList() {
        return this.specialOccurrenceList;
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

    public final MutableLiveData<SpecialOccurrencePage> getRetakeSpecialOccurrencePage() {
        return this.retakeSpecialOccurrencePage;
    }

    public final void finishTakingPicture() {
        this.captureImageUseCase.finishUpdatingSpecialOccurrence();
        this.captureImageUseCase.finishInsertingSpecialOccurrence();
        this.retakeSpecialOccurrencePage.postValue(null);
    }

    public final void deleteSpecialOccurrence(SpecialOccurrencePage specialOccurrencePage) {
        Intrinsics.checkNotNullParameter(specialOccurrencePage, "specialOccurrencePage");
        this.deleteSpecialOccurrencePage.postValue(specialOccurrencePage);
    }

    public final LiveData<Resource<Boolean>> getDeleteSpecialOccurrenceResource() {
        return this.deleteSpecialOccurrenceResource;
    }

    public final void finishDeletingSpecialOccurrencePage() {
        this.deleteSpecialOccurrencePage.postValue(null);
    }

    public final void startGeneratePdf(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        String value = this.kodeTps.getValue();
        if (value != null) {
            ZipSpecialOccurrenceTask zipSpecialOccurrenceTask = this.zipSpecialOccurrenceTask;
            Context requireContext = fragment.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "fragment.requireContext()");
            zipSpecialOccurrenceTask.zip(requireContext, value);
        }
    }

    public final void createPdf(final Fragment fragment, ActivityResultLauncher<Intent> activityResult) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(activityResult, "activityResult");
        DeviceSecurityFacade.INSTANCE.doLocalAuthentication(fragment, new BiometricPrompt.AuthenticationCallback() { // from class: org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel$createPdf$1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                super.onAuthenticationSucceeded(result);
                SpecialOccurrenceViewModel.this.startGeneratePdf(fragment);
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int i, CharSequence errString) {
                Intrinsics.checkNotNullParameter(errString, "errString");
                super.onAuthenticationError(i, errString);
                SpecialOccurrenceViewModel.this.failedAuthentication(fragment);
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
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new SpecialOccurrenceViewModel$finishUploadPdfOffline$1(this, kodeTps, null), 2, null);
    }

    public final LiveData<BackgroundProcess> getBackgroundProcessZipSpecialOccurrence() {
        return this.backgroundProcessZipSpecialOccurrence;
    }

    public final LiveData<Boolean> isLoadingZip() {
        return this.isLoadingZip;
    }
}
