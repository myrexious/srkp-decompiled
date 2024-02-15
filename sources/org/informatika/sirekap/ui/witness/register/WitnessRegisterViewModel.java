package org.informatika.sirekap.ui.witness.register;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.room.RoomDatabase;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.Date;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.repository.WitnessRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessTask;
import org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessWorker;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: WitnessRegisterViewModel.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B9\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\"2\u0006\u0010'\u001a\u00020%J\u0006\u0010(\u001a\u00020\"R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00150\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "witnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/BackgroundProcessRepository;", "registerWitnessTask", "Lorg/informatika/sirekap/support/worker/registerWitness/RegisterWitnessTask;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/repository/DefaultWitnessRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/repository/BackgroundProcessRepository;Lorg/informatika/sirekap/support/worker/registerWitness/RegisterWitnessTask;)V", "backgroundProcessRegister", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/BackgroundProcess;", "getBackgroundProcessRegister", "()Landroidx/lifecycle/LiveData;", "formResultLocalResource", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/WitnessWithShare;", "getFormResultLocalResource", "isLoading", "", "submittedFormLocal", "Landroidx/lifecycle/MutableLiveData;", "Lorg/informatika/sirekap/repository/WitnessRepository$AddWitnessModel;", "witnessRegisterFormUseCase", "Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFormUseCase;", "getWitnessRegisterFormUseCase", "()Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFormUseCase;", "finishRegisterFormLocal", "", "initForm", "kodeTps", "", "register", "noHandphone", "registerLocal", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessRegisterViewModel extends ViewModel {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WitnessRegisterViewModel";
    private final LiveData<BackgroundProcess> backgroundProcessRegister;
    private final BackgroundProcessRepository backgroundProcessRepository;
    private final Context context;
    private final DefaultElectionRepository electionRepository;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final LiveData<Resource<WitnessWithShare>> formResultLocalResource;
    private final LiveData<Boolean> isLoading;
    private final RegisterWitnessTask registerWitnessTask;
    private final MutableLiveData<WitnessRepository.AddWitnessModel> submittedFormLocal;
    private final WitnessRegisterFormUseCase witnessRegisterFormUseCase;
    private final DefaultWitnessRepository witnessRepository;

    @Inject
    public WitnessRegisterViewModel(@ApplicationContext Context context, DefaultElectionRepository electionRepository, DefaultWitnessRepository witnessRepository, EncryptedSharedPreferences encryptedSharedPreferences, BackgroundProcessRepository backgroundProcessRepository, RegisterWitnessTask registerWitnessTask) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(witnessRepository, "witnessRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(backgroundProcessRepository, "backgroundProcessRepository");
        Intrinsics.checkNotNullParameter(registerWitnessTask, "registerWitnessTask");
        this.context = context;
        this.electionRepository = electionRepository;
        this.witnessRepository = witnessRepository;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.backgroundProcessRepository = backgroundProcessRepository;
        this.registerWitnessTask = registerWitnessTask;
        this.witnessRegisterFormUseCase = new WitnessRegisterFormUseCase(electionRepository, context);
        MutableLiveData<WitnessRepository.AddWitnessModel> mutableLiveData = new MutableLiveData<>(null);
        this.submittedFormLocal = mutableLiveData;
        this.formResultLocalResource = Transformations.switchMap(mutableLiveData, new Function1<WitnessRepository.AddWitnessModel, LiveData<Resource<WitnessWithShare>>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel$formResultLocalResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<WitnessWithShare>> invoke(WitnessRepository.AddWitnessModel addWitnessModel) {
                DefaultWitnessRepository defaultWitnessRepository;
                if (addWitnessModel == null) {
                    return new MutableLiveData(null);
                }
                long time = new Date().getTime() + RangesKt.random(new IntRange(100, RoomDatabase.MAX_BIND_PARAMETER_CNT), Random.Default);
                if (time > 0) {
                    time *= -1;
                }
                defaultWitnessRepository = WitnessRegisterViewModel.this.witnessRepository;
                return defaultWitnessRepository.addWitnessLocal(addWitnessModel, time);
            }
        });
        LiveData<BackgroundProcess> switchMap = Transformations.switchMap(mutableLiveData, new Function1<WitnessRepository.AddWitnessModel, LiveData<BackgroundProcess>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel$backgroundProcessRegister$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<BackgroundProcess> invoke(WitnessRepository.AddWitnessModel addWitnessModel) {
                BackgroundProcessRepository backgroundProcessRepository2;
                if (addWitnessModel != null) {
                    backgroundProcessRepository2 = WitnessRegisterViewModel.this.backgroundProcessRepository;
                    return backgroundProcessRepository2.getById(RegisterWitnessWorker.Companion.getBackgroundProcessId(addWitnessModel.getNoHandphone()));
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.backgroundProcessRegister = switchMap;
        this.isLoading = Transformations.map(switchMap, new Function1<BackgroundProcess, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BackgroundProcess backgroundProcess) {
                return Boolean.valueOf(backgroundProcess != null ? backgroundProcess.isLoading() : false);
            }
        });
    }

    public final WitnessRegisterFormUseCase getWitnessRegisterFormUseCase() {
        return this.witnessRegisterFormUseCase;
    }

    public final void initForm(String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        this.witnessRegisterFormUseCase.setup(kodeTps);
    }

    public final void registerLocal() {
        WitnessRepository.AddWitnessModel addWitnessModels;
        if (this.witnessRegisterFormUseCase.isFormValid()) {
            String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
            if (stringEncrypted == null) {
                stringEncrypted = "";
            }
            WitnessRegisterFormState form = this.witnessRegisterFormUseCase.getForm();
            if (form == null || (addWitnessModels = form.toAddWitnessModels(stringEncrypted)) == null) {
                return;
            }
            this.submittedFormLocal.postValue(addWitnessModels);
        }
    }

    public final void register(String noHandphone) {
        Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
        this.registerWitnessTask.register(this.context, noHandphone);
    }

    public final LiveData<Resource<WitnessWithShare>> getFormResultLocalResource() {
        return this.formResultLocalResource;
    }

    public final void finishRegisterFormLocal() {
        this.submittedFormLocal.postValue(null);
    }

    public final LiveData<BackgroundProcess> getBackgroundProcessRegister() {
        return this.backgroundProcessRegister;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    /* compiled from: WitnessRegisterViewModel.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterViewModel$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
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
