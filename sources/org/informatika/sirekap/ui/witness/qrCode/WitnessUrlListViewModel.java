package org.informatika.sirekap.ui.witness.qrCode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.support.CombinedLiveData;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel;

/* compiled from: WitnessUrlListViewModel.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0015\b\u0007\u0018\u0000 G2\u00020\u0001:\u0002GHB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u00103\u001a\u0002042\u0006\u0010&\u001a\u00020\u00152\u0006\u00105\u001a\u00020\u0015J\u0016\u00106\u001a\u0002042\u0006\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u0015J\u000e\u00109\u001a\u0002042\u0006\u0010:\u001a\u00020\u0019J\u000e\u0010;\u001a\u0002042\u0006\u0010<\u001a\u00020\u0019J\u000e\u0010=\u001a\u0002042\u0006\u0010>\u001a\u00020\u0019J\u000e\u0010?\u001a\u0002042\u0006\u0010@\u001a\u00020\u0019J\u000e\u0010A\u001a\u0002042\u0006\u0010B\u001a\u00020\u0019J\u000e\u0010C\u001a\u0002042\u0006\u0010D\u001a\u00020\u0019J\u000e\u0010E\u001a\u0002042\u0006\u0010F\u001a\u00020\u0019R\u0019\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0011\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0010\u0018\u00010\u00120\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u001aR\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0019\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00190\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010*\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020+\u0018\u00010\u00120\u000f¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\b¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010/\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020+\u0018\u00010\u00100\b¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u000bR\u001c\u00101\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020+\u0018\u00010\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u00102\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\u0010\u0018\u00010\u00120\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListViewModel;", "Landroidx/lifecycle/ViewModel;", "witnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Lorg/informatika/sirekap/repository/DefaultWitnessRepository;Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "election", "Lorg/informatika/sirekap/support/CombinedLiveData;", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getElection", "()Lorg/informatika/sirekap/support/CombinedLiveData;", "getElectionRepository", "()Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "elections", "Landroidx/lifecycle/LiveData;", "", "electionsResource", "Lorg/informatika/sirekap/support/Resource;", "filterJenisPemilihan", "Landroidx/lifecycle/MutableLiveData;", "", "getFilterJenisPemilihan", "()Landroidx/lifecycle/MutableLiveData;", "isCheckedDpd", "", "()Landroidx/lifecycle/LiveData;", "isCheckedDpr", "isCheckedDprdk", "isCheckedDprdp", "isCheckedPilgub", "isCheckedPilpres", "isCheckedPilwabup", "isEmpty", "isEmptyRaw", "isLoading", "isMultipleElections", "isZipped", "jenisPemilihan", "kodeTps", "markWitnessModel", "Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListViewModel$MarkWitnessModel;", "markWitnessResource", "Lorg/informatika/sirekap/model/WitnessWithShare;", "getMarkWitnessResource", "showEmptyText", "getShowEmptyText", "witnesses", "getWitnesses", "witnessesRaw", "witnessesResource", "markWitnessAsShared", "", "witnessId", "setup", "_kodeTps", "_jenisPemilihan", "updateFilterDpd", "dpdChecked", "updateFilterDpr", "dprChecked", "updateFilterDprdk", "dprdkChecked", "updateFilterDprdp", "dprdpChecked", "updateFilterPilgub", "pilGubChecked", "updateFilterPilpres", "pilpresChecked", "updateFilterPilwabup", "pilWabupChecked", "Companion", "MarkWitnessModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessUrlListViewModel extends ViewModel {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WitnessUrlListViewModel";
    private final CombinedLiveData<ElectionWithRelation> election;
    private final DefaultElectionRepository electionRepository;
    private final LiveData<List<ElectionWithRelation>> elections;
    private final LiveData<Resource<List<ElectionWithRelation>>> electionsResource;
    private final MutableLiveData<String> filterJenisPemilihan;
    private final LiveData<Boolean> isCheckedDpd;
    private final LiveData<Boolean> isCheckedDpr;
    private final LiveData<Boolean> isCheckedDprdk;
    private final LiveData<Boolean> isCheckedDprdp;
    private final LiveData<Boolean> isCheckedPilgub;
    private final LiveData<Boolean> isCheckedPilpres;
    private final LiveData<Boolean> isCheckedPilwabup;
    private final LiveData<Boolean> isEmpty;
    private final LiveData<Boolean> isEmptyRaw;
    private final LiveData<Boolean> isLoading;
    private final LiveData<Boolean> isMultipleElections;
    private final LiveData<Boolean> isZipped;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final MutableLiveData<MarkWitnessModel> markWitnessModel;
    private final LiveData<Resource<WitnessWithShare>> markWitnessResource;
    private final CombinedLiveData<Boolean> showEmptyText;
    private final DefaultWitnessRepository witnessRepository;
    private final CombinedLiveData<List<WitnessWithShare>> witnesses;
    private final LiveData<List<WitnessWithShare>> witnessesRaw;
    private final LiveData<Resource<List<WitnessWithShare>>> witnessesResource;

    public final DefaultElectionRepository getElectionRepository() {
        return this.electionRepository;
    }

    @Inject
    public WitnessUrlListViewModel(DefaultWitnessRepository witnessRepository, DefaultElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(witnessRepository, "witnessRepository");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.witnessRepository = witnessRepository;
        this.electionRepository = electionRepository;
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData;
        this.jenisPemilihan = new MutableLiveData<>(null);
        MutableLiveData<String> mutableLiveData2 = new MutableLiveData<>(null);
        this.filterJenisPemilihan = mutableLiveData2;
        LiveData<Resource<List<ElectionWithRelation>>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<List<ElectionWithRelation>>>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$electionsResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<ElectionWithRelation>>> invoke(String str) {
                if (str == null) {
                    return null;
                }
                return WitnessUrlListViewModel.this.getElectionRepository().getElectionsByKodeTps(str);
            }
        });
        this.electionsResource = switchMap;
        LiveData<List<ElectionWithRelation>> map = Transformations.map(switchMap, new Function1<Resource<List<ElectionWithRelation>>, List<ElectionWithRelation>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$elections$1
            @Override // kotlin.jvm.functions.Function1
            public final List<ElectionWithRelation> invoke(Resource<List<ElectionWithRelation>> resource) {
                if (resource == null) {
                    return CollectionsKt.emptyList();
                }
                return resource.getPayload();
            }
        });
        this.elections = map;
        LiveData<Boolean> map2 = Transformations.map(map, new Function1<List<ElectionWithRelation>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isMultipleElections$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<ElectionWithRelation> list) {
                boolean z = false;
                if (list != null && list.size() > 1) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
        this.isMultipleElections = map2;
        LiveData<Resource<List<WitnessWithShare>>> switchMap2 = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<List<WitnessWithShare>>>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$witnessesResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<WitnessWithShare>>> invoke(String str) {
                DefaultWitnessRepository defaultWitnessRepository;
                if (str != null) {
                    defaultWitnessRepository = WitnessUrlListViewModel.this.witnessRepository;
                    return defaultWitnessRepository.getWitnessesByKodeTps(str);
                }
                return new MutableLiveData(null);
            }
        });
        this.witnessesResource = switchMap2;
        LiveData<List<WitnessWithShare>> map3 = Transformations.map(switchMap2, new Function1<Resource<List<WitnessWithShare>>, List<WitnessWithShare>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$witnessesRaw$1
            @Override // kotlin.jvm.functions.Function1
            public final List<WitnessWithShare> invoke(Resource<List<WitnessWithShare>> resource) {
                if (resource == null) {
                    return CollectionsKt.emptyList();
                }
                return resource.getPayload();
            }
        });
        this.witnessesRaw = map3;
        LiveData<Boolean> map4 = Transformations.map(map3, new Function1<List<WitnessWithShare>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isEmptyRaw$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<WitnessWithShare> list) {
                List<WitnessWithShare> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.isEmptyRaw = map4;
        CombinedLiveData<List<WitnessWithShare>> combinedLiveData = new CombinedLiveData<>(new LiveData[]{map3, mutableLiveData2}, new Function1<List<? extends Object>, List<? extends WitnessWithShare>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$witnesses$1
            @Override // kotlin.jvm.functions.Function1
            public final List<WitnessWithShare> invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        if (obj == null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (z2) {
                    return null;
                }
                List list2 = (List) data.get(0);
                String str = (String) data.get(1);
                if (list2 != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj2 : list2) {
                        WitnessWithShare witnessWithShare = (WitnessWithShare) obj2;
                        if ((str != null && witnessWithShare.isJenisPemilihan(str)) && witnessWithShare.getWitness().isSaksi()) {
                            arrayList.add(obj2);
                        }
                    }
                    return arrayList;
                }
                return null;
            }
        });
        this.witnesses = combinedLiveData;
        LiveData<Boolean> map5 = Transformations.map(combinedLiveData, new Function1<List<WitnessWithShare>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isEmpty$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<WitnessWithShare> list) {
                List<WitnessWithShare> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.isEmpty = map5;
        this.isLoading = Transformations.map(switchMap2, new Function1<Resource<List<WitnessWithShare>>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<List<WitnessWithShare>> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.showEmptyText = new CombinedLiveData<>(new LiveData[]{map2, map5, map4}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$showEmptyText$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        if (obj == null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (z2) {
                    return false;
                }
                return Intrinsics.areEqual((Object) ((Boolean) data.get(0)), (Object) true) ? (Boolean) data.get(2) : (Boolean) data.get(1);
            }
        });
        this.isCheckedPilpres = Transformations.map(mutableLiveData2, new Function1<String, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isCheckedPilpres$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String str) {
                if (str != null) {
                    return Boolean.valueOf(str.equals(Election.ELECTION_PEMILIHAN_PRESIDEN));
                }
                return null;
            }
        });
        this.isCheckedPilgub = Transformations.map(mutableLiveData2, new Function1<String, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isCheckedPilgub$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String str) {
                if (str != null) {
                    return Boolean.valueOf(str.equals(Election.ELECTION_PEMILIHAN_PROVINSI));
                }
                return null;
            }
        });
        this.isCheckedPilwabup = Transformations.map(mutableLiveData2, new Function1<String, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isCheckedPilwabup$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String str) {
                if (str != null) {
                    return Boolean.valueOf(str.equals(Election.ELECTION_PEMILIHAN_KOTAKAB));
                }
                return null;
            }
        });
        this.isCheckedDpr = Transformations.map(mutableLiveData2, new Function1<String, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isCheckedDpr$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String str) {
                if (str != null) {
                    return Boolean.valueOf(str.equals(Election.ELECTION_PEMILIHAN_DPR));
                }
                return null;
            }
        });
        this.isCheckedDprdp = Transformations.map(mutableLiveData2, new Function1<String, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isCheckedDprdp$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String str) {
                if (str != null) {
                    return Boolean.valueOf(str.equals(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI));
                }
                return null;
            }
        });
        this.isCheckedDprdk = Transformations.map(mutableLiveData2, new Function1<String, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isCheckedDprdk$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String str) {
                if (str != null) {
                    return Boolean.valueOf(str.equals(Election.ELECTION_PEMILIHAN_DPRD_KABKO));
                }
                return null;
            }
        });
        this.isCheckedDpd = Transformations.map(mutableLiveData2, new Function1<String, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isCheckedDpd$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String str) {
                if (str != null) {
                    return Boolean.valueOf(str.equals(Election.ELECTION_PEMILIHAN_DPD));
                }
                return null;
            }
        });
        MutableLiveData<MarkWitnessModel> mutableLiveData3 = new MutableLiveData<>(null);
        this.markWitnessModel = mutableLiveData3;
        this.markWitnessResource = Transformations.switchMap(mutableLiveData3, new Function1<MarkWitnessModel, LiveData<Resource<WitnessWithShare>>>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$markWitnessResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<WitnessWithShare>> invoke(WitnessUrlListViewModel.MarkWitnessModel markWitnessModel) {
                DefaultWitnessRepository defaultWitnessRepository;
                if (markWitnessModel != null) {
                    defaultWitnessRepository = WitnessUrlListViewModel.this.witnessRepository;
                    return defaultWitnessRepository.markAsShared(markWitnessModel.getWitnessId(), markWitnessModel.getJenisPemilihan());
                }
                return AbsentLiveData.Companion.create();
            }
        });
        CombinedLiveData<ElectionWithRelation> combinedLiveData2 = new CombinedLiveData<>(new LiveData[]{map, mutableLiveData2}, new Function1<List<? extends Object>, ElectionWithRelation>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$election$1
            @Override // kotlin.jvm.functions.Function1
            public final ElectionWithRelation invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        if (obj == null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                Object obj2 = null;
                if (z2) {
                    return null;
                }
                List list2 = (List) data.get(0);
                String str = (String) data.get(1);
                if (list2 != null) {
                    Iterator it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        if (Intrinsics.areEqual(((ElectionWithRelation) next).getElection().getPemilihan(), str)) {
                            obj2 = next;
                            break;
                        }
                    }
                    return (ElectionWithRelation) obj2;
                }
                return null;
            }
        });
        this.election = combinedLiveData2;
        this.isZipped = Transformations.map(combinedLiveData2, new Function1<ElectionWithRelation, Boolean>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel$isZipped$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ElectionWithRelation electionWithRelation) {
                Election election;
                return Boolean.valueOf((electionWithRelation == null || (election = electionWithRelation.getElection()) == null) ? false : election.isZipped());
            }
        });
    }

    public final MutableLiveData<String> getFilterJenisPemilihan() {
        return this.filterJenisPemilihan;
    }

    public final LiveData<Boolean> isMultipleElections() {
        return this.isMultipleElections;
    }

    public final CombinedLiveData<List<WitnessWithShare>> getWitnesses() {
        return this.witnesses;
    }

    public final LiveData<Boolean> isEmpty() {
        return this.isEmpty;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final CombinedLiveData<Boolean> getShowEmptyText() {
        return this.showEmptyText;
    }

    public final void setup(String _kodeTps, String _jenisPemilihan) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        Intrinsics.checkNotNullParameter(_jenisPemilihan, "_jenisPemilihan");
        if (!Intrinsics.areEqual(this.kodeTps.getValue(), _kodeTps)) {
            this.kodeTps.setValue(_kodeTps);
        }
        if (Intrinsics.areEqual(this.jenisPemilihan.getValue(), _jenisPemilihan)) {
            return;
        }
        this.jenisPemilihan.setValue(_jenisPemilihan);
        this.filterJenisPemilihan.postValue(_jenisPemilihan);
    }

    public final LiveData<Boolean> isCheckedPilpres() {
        return this.isCheckedPilpres;
    }

    public final LiveData<Boolean> isCheckedPilgub() {
        return this.isCheckedPilgub;
    }

    public final LiveData<Boolean> isCheckedPilwabup() {
        return this.isCheckedPilwabup;
    }

    public final LiveData<Boolean> isCheckedDpr() {
        return this.isCheckedDpr;
    }

    public final LiveData<Boolean> isCheckedDprdp() {
        return this.isCheckedDprdp;
    }

    public final LiveData<Boolean> isCheckedDprdk() {
        return this.isCheckedDprdk;
    }

    public final LiveData<Boolean> isCheckedDpd() {
        return this.isCheckedDpd;
    }

    public final void updateFilterPilpres(boolean z) {
        if (z) {
            this.filterJenisPemilihan.postValue(Election.ELECTION_PEMILIHAN_PRESIDEN);
        } else if (Intrinsics.areEqual(this.filterJenisPemilihan.getValue(), Election.ELECTION_PEMILIHAN_PRESIDEN)) {
            this.filterJenisPemilihan.postValue(null);
        }
    }

    public final void updateFilterPilgub(boolean z) {
        if (z) {
            this.filterJenisPemilihan.postValue(Election.ELECTION_PEMILIHAN_PROVINSI);
        } else if (Intrinsics.areEqual(this.filterJenisPemilihan.getValue(), Election.ELECTION_PEMILIHAN_PROVINSI)) {
            this.filterJenisPemilihan.postValue(null);
        }
    }

    public final void updateFilterPilwabup(boolean z) {
        if (z) {
            this.filterJenisPemilihan.postValue(Election.ELECTION_PEMILIHAN_KOTAKAB);
        } else if (Intrinsics.areEqual(this.filterJenisPemilihan.getValue(), Election.ELECTION_PEMILIHAN_KOTAKAB)) {
            this.filterJenisPemilihan.postValue(null);
        }
    }

    public final void updateFilterDpr(boolean z) {
        if (z) {
            this.filterJenisPemilihan.postValue(Election.ELECTION_PEMILIHAN_DPR);
        } else if (Intrinsics.areEqual(this.filterJenisPemilihan.getValue(), Election.ELECTION_PEMILIHAN_DPR)) {
            this.filterJenisPemilihan.postValue(null);
        }
    }

    public final void updateFilterDprdp(boolean z) {
        if (z) {
            this.filterJenisPemilihan.postValue(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
        } else if (Intrinsics.areEqual(this.filterJenisPemilihan.getValue(), Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
            this.filterJenisPemilihan.postValue(null);
        }
    }

    public final void updateFilterDprdk(boolean z) {
        if (z) {
            this.filterJenisPemilihan.postValue(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
        } else if (Intrinsics.areEqual(this.filterJenisPemilihan.getValue(), Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
            this.filterJenisPemilihan.postValue(null);
        }
    }

    public final void updateFilterDpd(boolean z) {
        if (z) {
            this.filterJenisPemilihan.postValue(Election.ELECTION_PEMILIHAN_DPD);
        } else if (Intrinsics.areEqual(this.filterJenisPemilihan.getValue(), Election.ELECTION_PEMILIHAN_DPD)) {
            this.filterJenisPemilihan.postValue(null);
        }
    }

    /* compiled from: WitnessUrlListViewModel.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListViewModel$MarkWitnessModel;", "", "witnessId", "", "jenisPemilihan", "(Ljava/lang/String;Ljava/lang/String;)V", "getJenisPemilihan", "()Ljava/lang/String;", "getWitnessId", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class MarkWitnessModel {
        private final String jenisPemilihan;
        private final String witnessId;

        public static /* synthetic */ MarkWitnessModel copy$default(MarkWitnessModel markWitnessModel, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = markWitnessModel.witnessId;
            }
            if ((i & 2) != 0) {
                str2 = markWitnessModel.jenisPemilihan;
            }
            return markWitnessModel.copy(str, str2);
        }

        public final String component1() {
            return this.witnessId;
        }

        public final String component2() {
            return this.jenisPemilihan;
        }

        public final MarkWitnessModel copy(String witnessId, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(witnessId, "witnessId");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new MarkWitnessModel(witnessId, jenisPemilihan);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof MarkWitnessModel) {
                MarkWitnessModel markWitnessModel = (MarkWitnessModel) obj;
                return Intrinsics.areEqual(this.witnessId, markWitnessModel.witnessId) && Intrinsics.areEqual(this.jenisPemilihan, markWitnessModel.jenisPemilihan);
            }
            return false;
        }

        public int hashCode() {
            return (this.witnessId.hashCode() * 31) + this.jenisPemilihan.hashCode();
        }

        public String toString() {
            String str = this.witnessId;
            return "MarkWitnessModel(witnessId=" + str + ", jenisPemilihan=" + this.jenisPemilihan + ")";
        }

        public MarkWitnessModel(String witnessId, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(witnessId, "witnessId");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            this.witnessId = witnessId;
            this.jenisPemilihan = jenisPemilihan;
        }

        public final String getWitnessId() {
            return this.witnessId;
        }

        public final String getJenisPemilihan() {
            return this.jenisPemilihan;
        }
    }

    public final LiveData<Resource<WitnessWithShare>> getMarkWitnessResource() {
        return this.markWitnessResource;
    }

    public final void markWitnessAsShared(String jenisPemilihan, String witnessId) {
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(witnessId, "witnessId");
        this.markWitnessModel.postValue(new MarkWitnessModel(witnessId, jenisPemilihan));
    }

    public final CombinedLiveData<ElectionWithRelation> getElection() {
        return this.election;
    }

    public final LiveData<Boolean> isZipped() {
        return this.isZipped;
    }

    /* compiled from: WitnessUrlListViewModel.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListViewModel$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
