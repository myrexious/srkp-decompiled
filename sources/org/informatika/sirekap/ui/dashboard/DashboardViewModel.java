package org.informatika.sirekap.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.lang3.BooleanUtils;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.CertmanRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.UserRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: DashboardViewModel.kt */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 c2\u00020\u0001:\u0001cBE\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u000e\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NJ\u0006\u0010O\u001a\u00020LJ\u0006\u0010P\u001a\u00020LJh\u0010Q\u001a\u00020L2\u0006\u0010R\u001a\u00020S2\u0006\u0010B\u001a\u00020C2\u0006\u0010T\u001a\u00020C2!\u0010U\u001a\u001d\u0012\u0013\u0012\u00110N¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(M\u0012\u0004\u0012\u00020L0V2%\u0010Y\u001a!\u0012\u0017\u0012\u00150Zj\u0002`[¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(\\\u0012\u0004\u0012\u00020L0VJ\u000e\u0010]\u001a\u00020L2\u0006\u0010^\u001a\u00020CJ\u000e\u0010_\u001a\u00020L2\u0006\u0010`\u001a\u00020\u0016J\u0006\u0010a\u001a\u00020LJ\u0006\u0010b\u001a\u00020LR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0%¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0019\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010*0%¢\u0006\b\n\u0000\u001a\u0004\b+\u0010(R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010,\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u00101\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000204\u0018\u00010302¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\"\u00107\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020403\u0018\u00010802X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u00020:X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u0019\u0010?\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010:02¢\u0006\b\n\u0000\u001a\u0004\b?\u00106R\u0019\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010:02¢\u0006\b\n\u0000\u001a\u0004\b@\u00106R\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020:02¢\u0006\b\n\u0000\u001a\u0004\bA\u00106R\u0019\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010C0%¢\u0006\b\n\u0000\u001a\u0004\bD\u0010(R\u001f\u0010E\u001a\u0010\u0012\f\u0012\n F*\u0004\u0018\u00010:0:0%¢\u0006\b\n\u0000\u001a\u0004\bG\u0010(R\u001a\u0010H\u001a\u00020:X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010<\"\u0004\bJ\u0010>R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006d"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "certmanRepository", "Lorg/informatika/sirekap/repository/CertmanRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "sharedPreferences", "Landroid/content/SharedPreferences;", "userRepository", "Lorg/informatika/sirekap/repository/UserRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/repository/AuthRepository;Lorg/informatika/sirekap/repository/CertmanRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Landroid/content/SharedPreferences;Lorg/informatika/sirekap/repository/UserRepository;)V", "aprilTagCheckUseCase", "Lorg/informatika/sirekap/ui/dashboard/AprilTagCheckUseCase;", "getAprilTagCheckUseCase", "()Lorg/informatika/sirekap/ui/dashboard/AprilTagCheckUseCase;", "aprilTagCode", "", "getAprilTagCode", "()Ljava/lang/Integer;", "setAprilTagCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "authRepositoryUseCase", "Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "getAuthRepositoryUseCase", "()Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "captureImageUseCase", "Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase;", "getCaptureImageUseCase", "()Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase;", "confirmedElection", "Landroidx/lifecycle/MutableLiveData;", "Lorg/informatika/sirekap/model/Election;", "getConfirmedElection", "()Landroidx/lifecycle/MutableLiveData;", "confirmedPage", "Lorg/informatika/sirekap/model/ElectionPage;", "getConfirmedPage", "currentIndex", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "elections", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getElections", "()Landroidx/lifecycle/LiveData;", "electionsResource", "Lorg/informatika/sirekap/support/Resource;", "hasUpdateVisitDashboard", "", "getHasUpdateVisitDashboard", "()Z", "setHasUpdateVisitDashboard", "(Z)V", "isAnyElectionStarted", "isAnyElectionsUnzipped", "isLoadingElections", "kodeTps", "", "getKodeTps", "loadImageProcessing", "kotlin.jvm.PlatformType", "getLoadImageProcessing", "saveCorrectedImage", "getSaveCorrectedImage", "setSaveCorrectedImage", "detectAprilTag", "", "bitmap", "Landroid/graphics/Bitmap;", "finishDetectingTag", "finishSavingImage", "savePhoto", "activity", "Landroid/app/Activity;", "jenisPemilihan", "onSuccess", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onError", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "setup", "_kodeTps", "updateCurrentIndex", "newIndex", "updateInitializationStatus", "updateVisitDashboard", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class DashboardViewModel extends ViewModel {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DashboardViewModel";
    private final AprilTagCheckUseCase aprilTagCheckUseCase;
    private Integer aprilTagCode;
    private final AuthRepository authRepository;
    private final AuthRequestUseCase authRepositoryUseCase;
    private final CaptureImageUseCase captureImageUseCase;
    private final CertmanRepository certmanRepository;
    private final MutableLiveData<Election> confirmedElection;
    private final MutableLiveData<ElectionPage> confirmedPage;
    private final Context context;
    private int currentIndex;
    private final DefaultElectionRepository electionRepository;
    private final LiveData<List<ElectionWithRelation>> elections;
    private final LiveData<Resource<List<ElectionWithRelation>>> electionsResource;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private boolean hasUpdateVisitDashboard;
    private final LiveData<Boolean> isAnyElectionStarted;
    private final LiveData<Boolean> isAnyElectionsUnzipped;
    private final LiveData<Boolean> isLoadingElections;
    private final MutableLiveData<String> kodeTps;
    private final MutableLiveData<Boolean> loadImageProcessing;
    private boolean saveCorrectedImage;
    private final SharedPreferences sharedPreferences;

    @Inject
    public DashboardViewModel(@ApplicationContext Context context, DefaultElectionRepository electionRepository, AuthRepository authRepository, CertmanRepository certmanRepository, EncryptedSharedPreferences encryptedSharedPreferences, SharedPreferences sharedPreferences, UserRepository userRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        Intrinsics.checkNotNullParameter(certmanRepository, "certmanRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        Intrinsics.checkNotNullParameter(userRepository, "userRepository");
        this.context = context;
        this.electionRepository = electionRepository;
        this.authRepository = authRepository;
        this.certmanRepository = certmanRepository;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.sharedPreferences = sharedPreferences;
        this.authRepositoryUseCase = new AuthRequestUseCase(authRepository);
        CaptureImageUseCase captureImageUseCase = new CaptureImageUseCase(context, encryptedSharedPreferences, electionRepository);
        this.captureImageUseCase = captureImageUseCase;
        this.aprilTagCheckUseCase = new AprilTagCheckUseCase(context, captureImageUseCase, ViewModelKt.getViewModelScope(this));
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData;
        LiveData<Resource<List<ElectionWithRelation>>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<List<ElectionWithRelation>>>>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardViewModel$electionsResource$1
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
                defaultElectionRepository = DashboardViewModel.this.electionRepository;
                return defaultElectionRepository.getElectionsByKodeTps(str);
            }
        });
        this.electionsResource = switchMap;
        LiveData<List<ElectionWithRelation>> map = Transformations.map(switchMap, new Function1<Resource<List<ElectionWithRelation>>, List<ElectionWithRelation>>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardViewModel$elections$1
            @Override // kotlin.jvm.functions.Function1
            public final List<ElectionWithRelation> invoke(Resource<List<ElectionWithRelation>> resource) {
                if (resource == null) {
                    return CollectionsKt.emptyList();
                }
                return resource.getPayload();
            }
        });
        this.elections = map;
        this.isLoadingElections = Transformations.map(switchMap, new Function1<Resource<List<ElectionWithRelation>>, Boolean>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardViewModel$isLoadingElections$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<List<ElectionWithRelation>> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.isAnyElectionsUnzipped = Transformations.map(map, new Function1<List<ElectionWithRelation>, Boolean>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardViewModel$isAnyElectionsUnzipped$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<ElectionWithRelation> list) {
                if (list != null) {
                    List<ElectionWithRelation> list2 = list;
                    boolean z = false;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator<T> it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            } else if (!((ElectionWithRelation) it.next()).getElection().isZipped()) {
                                z = true;
                                break;
                            }
                        }
                    }
                    return Boolean.valueOf(z);
                }
                return null;
            }
        });
        this.isAnyElectionStarted = Transformations.map(map, new Function1<List<ElectionWithRelation>, Boolean>() { // from class: org.informatika.sirekap.ui.dashboard.DashboardViewModel$isAnyElectionStarted$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<ElectionWithRelation> list) {
                boolean z;
                boolean z2;
                if (list != null) {
                    List<ElectionWithRelation> list2 = list;
                    boolean z3 = false;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator<T> it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            List<ElectionPage> pages = ((ElectionWithRelation) it.next()).getPages();
                            if (!(pages instanceof Collection) || !pages.isEmpty()) {
                                for (ElectionPage electionPage : pages) {
                                    if (electionPage.getCurrentStageIndex() > 0) {
                                        z = true;
                                        continue;
                                    } else {
                                        z = false;
                                        continue;
                                    }
                                    if (z) {
                                        z2 = true;
                                        continue;
                                        break;
                                    }
                                }
                            }
                            z2 = false;
                            continue;
                            if (z2) {
                                z3 = true;
                                break;
                            }
                        }
                    }
                    return Boolean.valueOf(z3);
                }
                return null;
            }
        });
        this.loadImageProcessing = new MutableLiveData<>(false);
        this.confirmedPage = new MutableLiveData<>(null);
        this.confirmedElection = new MutableLiveData<>(null);
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    public final void updateCurrentIndex(int i) {
        this.currentIndex = i;
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

    public final AuthRequestUseCase getAuthRepositoryUseCase() {
        return this.authRepositoryUseCase;
    }

    public final CaptureImageUseCase getCaptureImageUseCase() {
        return this.captureImageUseCase;
    }

    public final AprilTagCheckUseCase getAprilTagCheckUseCase() {
        return this.aprilTagCheckUseCase;
    }

    public final MutableLiveData<String> getKodeTps() {
        return this.kodeTps;
    }

    public final LiveData<List<ElectionWithRelation>> getElections() {
        return this.elections;
    }

    public final LiveData<Boolean> isLoadingElections() {
        return this.isLoadingElections;
    }

    public final LiveData<Boolean> isAnyElectionsUnzipped() {
        return this.isAnyElectionsUnzipped;
    }

    public final LiveData<Boolean> isAnyElectionStarted() {
        return this.isAnyElectionStarted;
    }

    public final void setup(String _kodeTps) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        this.kodeTps.postValue(_kodeTps);
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
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new DashboardViewModel$savePhoto$1(this, activity, kodeTps, jenisPemilihan, onSuccess, onError, null), 2, null);
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

    public final MutableLiveData<ElectionPage> getConfirmedPage() {
        return this.confirmedPage;
    }

    public final MutableLiveData<Election> getConfirmedElection() {
        return this.confirmedElection;
    }

    public final void updateInitializationStatus() {
        if (this.authRepository.isAuthenticated() && !Intrinsics.areEqual(this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_HAS_UPDATE_INITIALIZATION_STATUS, BooleanUtils.FALSE), BooleanUtils.TRUE)) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DashboardViewModel$updateInitializationStatus$1(this, null), 3, null);
        }
    }

    public final boolean getHasUpdateVisitDashboard() {
        return this.hasUpdateVisitDashboard;
    }

    public final void setHasUpdateVisitDashboard(boolean z) {
        this.hasUpdateVisitDashboard = z;
    }

    public final void updateVisitDashboard() {
        if (this.authRepository.isAuthenticated() && !this.hasUpdateVisitDashboard) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DashboardViewModel$updateVisitDashboard$1(this, null), 3, null);
        }
    }

    /* compiled from: DashboardViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/DashboardViewModel$Companion;", "", "()V", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
