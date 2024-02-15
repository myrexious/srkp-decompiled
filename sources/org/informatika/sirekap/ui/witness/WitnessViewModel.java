package org.informatika.sirekap.ui.witness;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Collection;
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

/* compiled from: WitnessViewModel.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0012\b\u0007\u0018\u0000 <2\u00020\u0001:\u0001<B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000fJ\u000e\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020\u0013J\u000e\u00100\u001a\u00020+2\u0006\u00101\u001a\u00020\u0013J\u000e\u00102\u001a\u00020+2\u0006\u00103\u001a\u00020\u0013J\u000e\u00104\u001a\u00020+2\u0006\u00105\u001a\u00020\u0013J\u000e\u00106\u001a\u00020+2\u0006\u00107\u001a\u00020\u0013J\u000e\u00108\u001a\u00020+2\u0006\u00109\u001a\u00020\u0013J\u000e\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\f0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\t0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0014R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00130\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010%\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020&\u0018\u00010\t0\"¢\u0006\b\n\u0000\u001a\u0004\b'\u0010$R\u001c\u0010(\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020&\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010)\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\t\u0018\u00010\f0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessViewModel;", "Landroidx/lifecycle/ViewModel;", "witnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Lorg/informatika/sirekap/repository/DefaultWitnessRepository;Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "elections", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "electionsResource", "Lorg/informatika/sirekap/support/Resource;", "filterJenisPemilihan", "Landroidx/lifecycle/MutableLiveData;", "", "getFilterJenisPemilihan", "()Landroidx/lifecycle/MutableLiveData;", "isCheckedDpd", "", "()Landroidx/lifecycle/LiveData;", "isCheckedDpr", "isCheckedDprdk", "isCheckedDprdp", "isCheckedPilgub", "isCheckedPilpres", "isCheckedPilwabup", "isEmpty", "isEmptyRaw", "isLoading", "isMultipleElections", "jenisPemilihan", "kodeTps", "showEmptyText", "Lorg/informatika/sirekap/support/CombinedLiveData;", "getShowEmptyText", "()Lorg/informatika/sirekap/support/CombinedLiveData;", "witnesses", "Lorg/informatika/sirekap/model/WitnessWithShare;", "getWitnesses", "witnessesRaw", "witnessesResource", "setup", "", "_kodeTps", "_jenisPemilihan", "updateFilterDpd", "dpdChecked", "updateFilterDpr", "dprChecked", "updateFilterDprdk", "dprdkChecked", "updateFilterDprdp", "dprdpChecked", "updateFilterPilgub", "pilGubChecked", "updateFilterPilpres", "pilpresChecked", "updateFilterPilwabup", "pilWabupChecked", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessViewModel extends ViewModel {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WitnessViewModel";
    private final DefaultElectionRepository electionRepository;
    private final LiveData<List<ElectionWithRelation>> elections;
    private final LiveData<Resource<List<ElectionWithRelation>>> electionsResource;
    private final MutableLiveData<List<String>> filterJenisPemilihan;
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
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final CombinedLiveData<Boolean> showEmptyText;
    private final DefaultWitnessRepository witnessRepository;
    private final CombinedLiveData<List<WitnessWithShare>> witnesses;
    private final LiveData<List<WitnessWithShare>> witnessesRaw;
    private final LiveData<Resource<List<WitnessWithShare>>> witnessesResource;

    @Inject
    public WitnessViewModel(DefaultWitnessRepository witnessRepository, DefaultElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(witnessRepository, "witnessRepository");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.witnessRepository = witnessRepository;
        this.electionRepository = electionRepository;
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData;
        this.jenisPemilihan = new MutableLiveData<>(null);
        MutableLiveData<List<String>> mutableLiveData2 = new MutableLiveData<>(CollectionsKt.emptyList());
        this.filterJenisPemilihan = mutableLiveData2;
        LiveData<Resource<List<ElectionWithRelation>>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<List<ElectionWithRelation>>>>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$electionsResource$1
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
                defaultElectionRepository = WitnessViewModel.this.electionRepository;
                return defaultElectionRepository.getElectionsByKodeTps(str);
            }
        });
        this.electionsResource = switchMap;
        LiveData<List<ElectionWithRelation>> map = Transformations.map(switchMap, new Function1<Resource<List<ElectionWithRelation>>, List<ElectionWithRelation>>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$elections$1
            @Override // kotlin.jvm.functions.Function1
            public final List<ElectionWithRelation> invoke(Resource<List<ElectionWithRelation>> resource) {
                if (resource == null) {
                    return CollectionsKt.emptyList();
                }
                return resource.getPayload();
            }
        });
        this.elections = map;
        LiveData<Boolean> map2 = Transformations.map(map, new Function1<List<ElectionWithRelation>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isMultipleElections$1
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
        LiveData<Resource<List<WitnessWithShare>>> switchMap2 = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<List<WitnessWithShare>>>>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$witnessesResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<WitnessWithShare>>> invoke(String str) {
                DefaultWitnessRepository defaultWitnessRepository;
                if (str != null) {
                    defaultWitnessRepository = WitnessViewModel.this.witnessRepository;
                    return defaultWitnessRepository.getWitnessesByKodeTps(str);
                }
                return new MutableLiveData(null);
            }
        });
        this.witnessesResource = switchMap2;
        LiveData<List<WitnessWithShare>> map3 = Transformations.map(switchMap2, new Function1<Resource<List<WitnessWithShare>>, List<WitnessWithShare>>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$witnessesRaw$1
            @Override // kotlin.jvm.functions.Function1
            public final List<WitnessWithShare> invoke(Resource<List<WitnessWithShare>> resource) {
                if (resource == null) {
                    return CollectionsKt.emptyList();
                }
                return resource.getPayload();
            }
        });
        this.witnessesRaw = map3;
        LiveData<Boolean> map4 = Transformations.map(map3, new Function1<List<WitnessWithShare>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isEmptyRaw$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<WitnessWithShare> list) {
                List<WitnessWithShare> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.isEmptyRaw = map4;
        CombinedLiveData<List<WitnessWithShare>> combinedLiveData = new CombinedLiveData<>(new LiveData[]{map3, mutableLiveData2}, new Function1<List<? extends Object>, List<? extends WitnessWithShare>>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$witnesses$1
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.util.List<org.informatika.sirekap.model.WitnessWithShare> invoke(java.util.List<? extends java.lang.Object> r9) {
                /*
                    r8 = this;
                    java.lang.String r0 = "data"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                    r0 = r9
                    java.lang.Iterable r0 = (java.lang.Iterable) r0
                    boolean r1 = r0 instanceof java.util.Collection
                    r2 = 1
                    r3 = 0
                    if (r1 == 0) goto L19
                    r1 = r0
                    java.util.Collection r1 = (java.util.Collection) r1
                    boolean r1 = r1.isEmpty()
                    if (r1 == 0) goto L19
                L17:
                    r0 = r3
                    goto L2f
                L19:
                    java.util.Iterator r0 = r0.iterator()
                L1d:
                    boolean r1 = r0.hasNext()
                    if (r1 == 0) goto L17
                    java.lang.Object r1 = r0.next()
                    if (r1 != 0) goto L2b
                    r1 = r2
                    goto L2c
                L2b:
                    r1 = r3
                L2c:
                    if (r1 == 0) goto L1d
                    r0 = r2
                L2f:
                    r1 = 0
                    if (r0 == 0) goto L34
                    goto Laa
                L34:
                    java.lang.Object r0 = r9.get(r3)
                    java.util.List r0 = (java.util.List) r0
                    java.lang.Object r9 = r9.get(r2)
                    java.util.List r9 = (java.util.List) r9
                    if (r0 == 0) goto Laa
                    java.lang.Iterable r0 = (java.lang.Iterable) r0
                    java.util.ArrayList r1 = new java.util.ArrayList
                    r1.<init>()
                    java.util.Collection r1 = (java.util.Collection) r1
                    java.util.Iterator r0 = r0.iterator()
                L4f:
                    boolean r4 = r0.hasNext()
                    if (r4 == 0) goto La8
                    java.lang.Object r4 = r0.next()
                    r5 = r4
                    org.informatika.sirekap.model.WitnessWithShare r5 = (org.informatika.sirekap.model.WitnessWithShare) r5
                    org.informatika.sirekap.model.Witness r6 = r5.getWitness()
                    boolean r6 = r6.isSaksi()
                    if (r6 == 0) goto La1
                    org.informatika.sirekap.model.Witness r6 = r5.getWitness()
                    boolean r6 = r6.isSaksi()
                    if (r6 == 0) goto L9f
                    if (r9 == 0) goto L9b
                    r6 = r9
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    boolean r7 = r6 instanceof java.util.Collection
                    if (r7 == 0) goto L83
                    r7 = r6
                    java.util.Collection r7 = (java.util.Collection) r7
                    boolean r7 = r7.isEmpty()
                    if (r7 == 0) goto L83
                    goto L9b
                L83:
                    java.util.Iterator r6 = r6.iterator()
                L87:
                    boolean r7 = r6.hasNext()
                    if (r7 == 0) goto L9b
                    java.lang.Object r7 = r6.next()
                    java.lang.String r7 = (java.lang.String) r7
                    boolean r7 = r5.isJenisPemilihan(r7)
                    if (r7 == 0) goto L87
                    r5 = r2
                    goto L9c
                L9b:
                    r5 = r3
                L9c:
                    if (r5 == 0) goto L9f
                    goto La1
                L9f:
                    r5 = r3
                    goto La2
                La1:
                    r5 = r2
                La2:
                    if (r5 == 0) goto L4f
                    r1.add(r4)
                    goto L4f
                La8:
                    java.util.List r1 = (java.util.List) r1
                Laa:
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.WitnessViewModel$witnesses$1.invoke(java.util.List):java.util.List");
            }
        });
        this.witnesses = combinedLiveData;
        LiveData<Boolean> map5 = Transformations.map(combinedLiveData, new Function1<List<WitnessWithShare>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isEmpty$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<WitnessWithShare> list) {
                List<WitnessWithShare> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.isEmpty = map5;
        this.isLoading = Transformations.map(switchMap2, new Function1<Resource<List<WitnessWithShare>>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<List<WitnessWithShare>> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.showEmptyText = new CombinedLiveData<>(new LiveData[]{map2, map5, map4}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$showEmptyText$1
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
        this.isCheckedPilpres = Transformations.map(mutableLiveData2, new Function1<List<String>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isCheckedPilpres$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<String> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(Election.ELECTION_PEMILIHAN_PRESIDEN));
                }
                return null;
            }
        });
        this.isCheckedPilgub = Transformations.map(mutableLiveData2, new Function1<List<String>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isCheckedPilgub$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<String> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(Election.ELECTION_PEMILIHAN_PROVINSI));
                }
                return null;
            }
        });
        this.isCheckedPilwabup = Transformations.map(mutableLiveData2, new Function1<List<String>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isCheckedPilwabup$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<String> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(Election.ELECTION_PEMILIHAN_KOTAKAB));
                }
                return null;
            }
        });
        this.isCheckedDpr = Transformations.map(mutableLiveData2, new Function1<List<String>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isCheckedDpr$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<String> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(Election.ELECTION_PEMILIHAN_DPR));
                }
                return null;
            }
        });
        this.isCheckedDprdp = Transformations.map(mutableLiveData2, new Function1<List<String>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isCheckedDprdp$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<String> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI));
                }
                return null;
            }
        });
        this.isCheckedDprdk = Transformations.map(mutableLiveData2, new Function1<List<String>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isCheckedDprdk$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<String> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(Election.ELECTION_PEMILIHAN_DPRD_KABKO));
                }
                return null;
            }
        });
        this.isCheckedDpd = Transformations.map(mutableLiveData2, new Function1<List<String>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.WitnessViewModel$isCheckedDpd$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<String> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(Election.ELECTION_PEMILIHAN_DPD));
                }
                return null;
            }
        });
    }

    public final MutableLiveData<List<String>> getFilterJenisPemilihan() {
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
        this.filterJenisPemilihan.postValue(CollectionsKt.listOf(_jenisPemilihan));
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
        List<String> value = this.filterJenisPemilihan.getValue();
        if (value != null) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PRESIDEN);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_KOTAKAB)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_KOTAKAB);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPR)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPR);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPD)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPD);
            }
            this.filterJenisPemilihan.postValue(arrayList);
        }
    }

    public final void updateFilterPilgub(boolean z) {
        List<String> value = this.filterJenisPemilihan.getValue();
        if (value != null) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PRESIDEN);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_KOTAKAB)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_KOTAKAB);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPR)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPR);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPD)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPD);
            }
            this.filterJenisPemilihan.postValue(arrayList);
        }
    }

    public final void updateFilterPilwabup(boolean z) {
        List<String> value = this.filterJenisPemilihan.getValue();
        if (value != null) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                arrayList.add(Election.ELECTION_PEMILIHAN_KOTAKAB);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PRESIDEN);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPR)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPR);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPD)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPD);
            }
            this.filterJenisPemilihan.postValue(arrayList);
        }
    }

    public final void updateFilterDpr(boolean z) {
        List<String> value = this.filterJenisPemilihan.getValue();
        if (value != null) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPR);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_KOTAKAB)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_KOTAKAB);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PRESIDEN);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPD)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPD);
            }
            this.filterJenisPemilihan.postValue(arrayList);
        }
    }

    public final void updateFilterDprdp(boolean z) {
        List<String> value = this.filterJenisPemilihan.getValue();
        if (value != null) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_KOTAKAB)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_KOTAKAB);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPR)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPR);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PRESIDEN);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPD)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPD);
            }
            this.filterJenisPemilihan.postValue(arrayList);
        }
    }

    public final void updateFilterDprdk(boolean z) {
        List<String> value = this.filterJenisPemilihan.getValue();
        if (value != null) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_KOTAKAB)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_KOTAKAB);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPR)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPR);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PRESIDEN);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPD)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPD);
            }
            this.filterJenisPemilihan.postValue(arrayList);
        }
    }

    public final void updateFilterDpd(boolean z) {
        List<String> value = this.filterJenisPemilihan.getValue();
        if (value != null) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPD);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_KOTAKAB)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_KOTAKAB);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPR)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPR);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
            }
            if (value.contains(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                arrayList.add(Election.ELECTION_PEMILIHAN_PRESIDEN);
            }
            this.filterJenisPemilihan.postValue(arrayList);
        }
    }

    /* compiled from: WitnessViewModel.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/WitnessViewModel$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
