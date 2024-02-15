package org.informatika.sirekap.ui.witness.qrCode;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.BackgroundProcess;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessTask;
import org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessWorker;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel;

/* compiled from: WitnessQrCodeViewModel.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001:\u0001?B9\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u000e\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020/J\u0006\u0010;\u001a\u000209J\u000e\u0010<\u001a\u0002092\u0006\u0010=\u001a\u00020/J\u000e\u0010>\u001a\u0002092\u0006\u00106\u001a\u00020/R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u001a\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u0011\u0010$\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0011\u0010&\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010!R\u0011\u0010(\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0011\u0010*\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010!R\u0011\u0010,\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010!R\u0019\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010/0\u0018¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0019\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0010¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0013R\u0019\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0010¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0013R\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020/0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u00107\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "witnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "registerWitnessTask", "Lorg/informatika/sirekap/support/worker/registerWitness/RegisterWitnessTask;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/BackgroundProcessRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/DefaultWitnessRepository;Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/support/worker/registerWitness/RegisterWitnessTask;Lorg/informatika/sirekap/repository/BackgroundProcessRepository;)V", "backgroundProcessRegister", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/BackgroundProcess;", "getBackgroundProcessRegister", "()Landroidx/lifecycle/LiveData;", "isLoading", "", "isSyncingLocalWitness", "markWitnessModel", "Landroidx/lifecycle/MutableLiveData;", "Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeViewModel$MarkWitnessModel;", "markWitnessResource", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/WitnessWithShare;", "getMarkWitnessResource", "shareDpd", "Lorg/informatika/sirekap/ui/witness/qrCode/ShareWitnessUrlUseCase;", "getShareDpd", "()Lorg/informatika/sirekap/ui/witness/qrCode/ShareWitnessUrlUseCase;", "shareDpr", "getShareDpr", "shareDprdk", "getShareDprdk", "shareDprdp", "getShareDprdp", "sharePilpres", "getSharePilpres", "sharePkwkk", "getSharePkwkk", "sharePkwkp", "getSharePkwkp", "syncLocalWitnessId", "", "getSyncLocalWitnessId", "()Landroidx/lifecycle/MutableLiveData;", "witness", "getWitness", "witnessEncrypted", "getWitnessEncrypted", "witnessId", "witnessResource", "markWitnessAsShared", "", "jenisPemilihan", "refresh", "setup", "_witnessId", "syncWitness2", "MarkWitnessModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessQrCodeViewModel extends ViewModel {
    private final LiveData<BackgroundProcess> backgroundProcessRegister;
    private final BackgroundProcessRepository backgroundProcessRepository;
    private final Context context;
    private final LiveData<Boolean> isLoading;
    private final LiveData<Boolean> isSyncingLocalWitness;
    private final MutableLiveData<MarkWitnessModel> markWitnessModel;
    private final LiveData<Resource<WitnessWithShare>> markWitnessResource;
    private final RegisterWitnessTask registerWitnessTask;
    private final ShareWitnessUrlUseCase shareDpd;
    private final ShareWitnessUrlUseCase shareDpr;
    private final ShareWitnessUrlUseCase shareDprdk;
    private final ShareWitnessUrlUseCase shareDprdp;
    private final ShareWitnessUrlUseCase sharePilpres;
    private final ShareWitnessUrlUseCase sharePkwkk;
    private final ShareWitnessUrlUseCase sharePkwkp;
    private final MutableLiveData<String> syncLocalWitnessId;
    private final LiveData<WitnessWithShare> witness;
    private final LiveData<WitnessWithShare> witnessEncrypted;
    private final MutableLiveData<String> witnessId;
    private final DefaultWitnessRepository witnessRepository;
    private final LiveData<Resource<WitnessWithShare>> witnessResource;

    @Inject
    public WitnessQrCodeViewModel(@ApplicationContext Context context, DefaultWitnessRepository witnessRepository, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, RegisterWitnessTask registerWitnessTask, BackgroundProcessRepository backgroundProcessRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(witnessRepository, "witnessRepository");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(registerWitnessTask, "registerWitnessTask");
        Intrinsics.checkNotNullParameter(backgroundProcessRepository, "backgroundProcessRepository");
        this.context = context;
        this.witnessRepository = witnessRepository;
        this.registerWitnessTask = registerWitnessTask;
        this.backgroundProcessRepository = backgroundProcessRepository;
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.witnessId = mutableLiveData;
        LiveData<Resource<WitnessWithShare>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<WitnessWithShare>>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel$witnessResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<WitnessWithShare>> invoke(String str) {
                DefaultWitnessRepository defaultWitnessRepository;
                if (str != null) {
                    defaultWitnessRepository = WitnessQrCodeViewModel.this.witnessRepository;
                    return defaultWitnessRepository.getWitnessById(str);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.witnessResource = switchMap;
        LiveData<WitnessWithShare> map = Transformations.map(switchMap, new Function1<Resource<WitnessWithShare>, WitnessWithShare>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel$witnessEncrypted$1
            @Override // kotlin.jvm.functions.Function1
            public final WitnessWithShare invoke(Resource<WitnessWithShare> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.witnessEncrypted = map;
        LiveData<WitnessWithShare> map2 = Transformations.map(map, new Function1<WitnessWithShare, WitnessWithShare>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel$witness$1
            @Override // kotlin.jvm.functions.Function1
            public final WitnessWithShare invoke(WitnessWithShare witnessWithShare) {
                if (witnessWithShare != null) {
                    return witnessWithShare.toDecrypted();
                }
                return null;
            }
        });
        this.witness = map2;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<WitnessWithShare>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<WitnessWithShare> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        DefaultElectionRepository defaultElectionRepository = electionRepository;
        this.sharePkwkk = new ShareWitnessUrlUseCase(context, defaultElectionRepository, encryptedSharedPreferences, map2, Election.ELECTION_PEMILIHAN_KOTAKAB);
        this.sharePkwkp = new ShareWitnessUrlUseCase(context, defaultElectionRepository, encryptedSharedPreferences, map2, Election.ELECTION_PEMILIHAN_PROVINSI);
        this.sharePilpres = new ShareWitnessUrlUseCase(context, defaultElectionRepository, encryptedSharedPreferences, map2, Election.ELECTION_PEMILIHAN_PRESIDEN);
        this.shareDpr = new ShareWitnessUrlUseCase(context, defaultElectionRepository, encryptedSharedPreferences, map2, Election.ELECTION_PEMILIHAN_DPR);
        this.shareDprdp = new ShareWitnessUrlUseCase(context, defaultElectionRepository, encryptedSharedPreferences, map2, Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
        this.shareDprdk = new ShareWitnessUrlUseCase(context, defaultElectionRepository, encryptedSharedPreferences, map2, Election.ELECTION_PEMILIHAN_DPRD_KABKO);
        this.shareDpd = new ShareWitnessUrlUseCase(context, defaultElectionRepository, encryptedSharedPreferences, map2, Election.ELECTION_PEMILIHAN_DPD);
        MutableLiveData<MarkWitnessModel> mutableLiveData2 = new MutableLiveData<>(null);
        this.markWitnessModel = mutableLiveData2;
        this.markWitnessResource = Transformations.switchMap(mutableLiveData2, new Function1<MarkWitnessModel, LiveData<Resource<WitnessWithShare>>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel$markWitnessResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<WitnessWithShare>> invoke(WitnessQrCodeViewModel.MarkWitnessModel markWitnessModel) {
                DefaultWitnessRepository defaultWitnessRepository;
                if (markWitnessModel != null) {
                    defaultWitnessRepository = WitnessQrCodeViewModel.this.witnessRepository;
                    return defaultWitnessRepository.markAsShared(markWitnessModel.getNoHandphone(), markWitnessModel.getJenisPemilihan());
                }
                return AbsentLiveData.Companion.create();
            }
        });
        MutableLiveData<String> mutableLiveData3 = new MutableLiveData<>(null);
        this.syncLocalWitnessId = mutableLiveData3;
        LiveData<BackgroundProcess> switchMap2 = Transformations.switchMap(mutableLiveData3, new Function1<String, LiveData<BackgroundProcess>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel$backgroundProcessRegister$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<BackgroundProcess> invoke(String str) {
                BackgroundProcessRepository backgroundProcessRepository2;
                if (str != null) {
                    backgroundProcessRepository2 = WitnessQrCodeViewModel.this.backgroundProcessRepository;
                    return backgroundProcessRepository2.getById(RegisterWitnessWorker.Companion.getBackgroundProcessId(str));
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.backgroundProcessRegister = switchMap2;
        this.isSyncingLocalWitness = Transformations.map(switchMap2, new Function1<BackgroundProcess, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel$isSyncingLocalWitness$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BackgroundProcess backgroundProcess) {
                return Boolean.valueOf(backgroundProcess != null ? backgroundProcess.isLoading() : false);
            }
        });
    }

    public final LiveData<WitnessWithShare> getWitnessEncrypted() {
        return this.witnessEncrypted;
    }

    public final LiveData<WitnessWithShare> getWitness() {
        return this.witness;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final ShareWitnessUrlUseCase getSharePkwkk() {
        return this.sharePkwkk;
    }

    public final ShareWitnessUrlUseCase getSharePkwkp() {
        return this.sharePkwkp;
    }

    public final ShareWitnessUrlUseCase getSharePilpres() {
        return this.sharePilpres;
    }

    public final ShareWitnessUrlUseCase getShareDpr() {
        return this.shareDpr;
    }

    public final ShareWitnessUrlUseCase getShareDprdp() {
        return this.shareDprdp;
    }

    public final ShareWitnessUrlUseCase getShareDprdk() {
        return this.shareDprdk;
    }

    public final ShareWitnessUrlUseCase getShareDpd() {
        return this.shareDpd;
    }

    public final void setup(String _witnessId) {
        Intrinsics.checkNotNullParameter(_witnessId, "_witnessId");
        if (Intrinsics.areEqual(this.witnessId.getValue(), _witnessId)) {
            return;
        }
        this.witnessId.postValue(_witnessId);
    }

    public final void refresh() {
        String value = this.witnessId.getValue();
        if (value != null) {
            this.witnessId.setValue(value);
        }
    }

    /* compiled from: WitnessQrCodeViewModel.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessQrCodeViewModel$MarkWitnessModel;", "", "noHandphone", "", "jenisPemilihan", "(Ljava/lang/String;Ljava/lang/String;)V", "getJenisPemilihan", "()Ljava/lang/String;", "getNoHandphone", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class MarkWitnessModel {
        private final String jenisPemilihan;
        private final String noHandphone;

        public static /* synthetic */ MarkWitnessModel copy$default(MarkWitnessModel markWitnessModel, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = markWitnessModel.noHandphone;
            }
            if ((i & 2) != 0) {
                str2 = markWitnessModel.jenisPemilihan;
            }
            return markWitnessModel.copy(str, str2);
        }

        public final String component1() {
            return this.noHandphone;
        }

        public final String component2() {
            return this.jenisPemilihan;
        }

        public final MarkWitnessModel copy(String noHandphone, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new MarkWitnessModel(noHandphone, jenisPemilihan);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof MarkWitnessModel) {
                MarkWitnessModel markWitnessModel = (MarkWitnessModel) obj;
                return Intrinsics.areEqual(this.noHandphone, markWitnessModel.noHandphone) && Intrinsics.areEqual(this.jenisPemilihan, markWitnessModel.jenisPemilihan);
            }
            return false;
        }

        public int hashCode() {
            return (this.noHandphone.hashCode() * 31) + this.jenisPemilihan.hashCode();
        }

        public String toString() {
            String str = this.noHandphone;
            return "MarkWitnessModel(noHandphone=" + str + ", jenisPemilihan=" + this.jenisPemilihan + ")";
        }

        public MarkWitnessModel(String noHandphone, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            this.noHandphone = noHandphone;
            this.jenisPemilihan = jenisPemilihan;
        }

        public final String getNoHandphone() {
            return this.noHandphone;
        }

        public final String getJenisPemilihan() {
            return this.jenisPemilihan;
        }
    }

    public final LiveData<Resource<WitnessWithShare>> getMarkWitnessResource() {
        return this.markWitnessResource;
    }

    public final void markWitnessAsShared(String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        String value = this.witnessId.getValue();
        if (value != null) {
            this.markWitnessModel.postValue(new MarkWitnessModel(value, jenisPemilihan));
        }
    }

    public final void syncWitness2(String witnessId) {
        Intrinsics.checkNotNullParameter(witnessId, "witnessId");
        this.syncLocalWitnessId.setValue(witnessId);
        this.registerWitnessTask.register(this.context, witnessId);
    }

    public final MutableLiveData<String> getSyncLocalWitnessId() {
        return this.syncLocalWitnessId;
    }

    public final LiveData<BackgroundProcess> getBackgroundProcessRegister() {
        return this.backgroundProcessRegister;
    }

    public final LiveData<Boolean> isSyncingLocalWitness() {
        return this.isSyncingLocalWitness;
    }
}
