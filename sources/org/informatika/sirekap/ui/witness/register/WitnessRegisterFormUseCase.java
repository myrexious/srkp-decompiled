package org.informatika.sirekap.ui.witness.register;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.CombinedLiveData;

/* compiled from: WitnessRegisterFormUseCase.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u001b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010D\u001a\u00020.J\u000e\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u000fJ$\u0010H\u001a\u00020F2\u0012\u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\b\b\u0002\u0010J\u001a\u00020.J$\u0010K\u001a\u00020F2\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\b\b\u0002\u0010M\u001a\u00020.J$\u0010N\u001a\u00020F2\u0012\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\b\b\u0002\u0010P\u001a\u00020.J$\u0010Q\u001a\u00020F2\u0012\u0010R\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\b\b\u0002\u0010S\u001a\u00020.J\u0014\u0010T\u001a\u00020F2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020\u000f0\tJ$\u0010V\u001a\u00020F2\u0012\u0010W\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\b\b\u0002\u0010X\u001a\u00020.J$\u0010Y\u001a\u00020F2\u0012\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\b\b\u0002\u0010[\u001a\u00020.J$\u0010\\\u001a\u00020F2\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\b\b\u0002\u0010^\u001a\u00020.J\u0014\u0010_\u001a\u00020F2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020\u000f0\tR\u001f\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR%\u0010\r\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000e0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR%\u0010\u0012\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000e0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u001f\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\fR\u001f\u0010\u0016\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\fR\u001f\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\"\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020#\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\fR\"\u0010%\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\t\u0018\u00010&0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020.00¢\u0006\b\n\u0000\u001a\u0004\b/\u00101R\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020.0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020.00¢\u0006\b\n\u0000\u001a\u0004\b3\u00101R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020.0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020.00¢\u0006\b\n\u0000\u001a\u0004\b5\u00101R\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020.0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020.00¢\u0006\b\n\u0000\u001a\u0004\b7\u00101R\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020.00¢\u0006\b\n\u0000\u001a\u0004\b8\u00101R\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020.0\b¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\fR\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020.0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010;\u001a\b\u0012\u0004\u0012\u00020.00¢\u0006\b\n\u0000\u001a\u0004\b;\u00101R\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020.0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020.00¢\u0006\b\n\u0000\u001a\u0004\b=\u00101R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020.0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010?\u001a\b\u0012\u0004\u0012\u00020.00¢\u0006\b\n\u0000\u001a\u0004\b?\u00101R\u0016\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0AX\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010B\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000e0\b¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\f¨\u0006a"}, d2 = {"Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFormUseCase;", "", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "context", "Landroid/content/Context;", "(Lorg/informatika/sirekap/repository/ElectionRepository;Landroid/content/Context;)V", "candidatesDpd", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/Candidate;", "getCandidatesDpd", "()Landroidx/lifecycle/LiveData;", "candidatesDprdk", "", "", "", "getCandidatesDprdk", "candidatesDprdp", "getCandidatesDprdp", "candidatesPilgub", "getCandidatesPilgub", "candidatesPilwalkot", "getCandidatesPilwalkot", "candidatesPresiden", "getCandidatesPresiden", "electionDpd", "Lorg/informatika/sirekap/model/Election;", "electionDpr", "electionDprdk", "electionDprdp", "electionPilgub", "electionPilwalkot", "electionPresiden", "elections", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getElections", "electionsResource", "Lorg/informatika/sirekap/support/Resource;", "form", "Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFormState;", "getForm", "()Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFormState;", "setForm", "(Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFormState;)V", "isDpdDone", "", "isDpdShown", "Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "()Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "isDprDone", "isDprShown", "isDprdkDone", "isDprdkShown", "isDprdpDone", "isDprdpShown", "isElectionDone", "isLoadingForm", "isPilgubDone", "isPilgubShown", "isPilwalkotDone", "isPilwalkotShown", "isPresidenDone", "isPresidenShown", "kodeTps", "Landroidx/lifecycle/MutableLiveData;", "partaisDpr", "getPartaisDpr", "isFormValid", "setup", "", "_kodeTps", "setupDpdOptions", "paslonDpdOptions", "isJenisPemilihanDpd", "setupDprOptions", "partaiDprOptions", "isJenisPemilihanDpr", "setupDprdkOptions", "partaiDprdkOptions", "isJenisPemilihanDprdk", "setupDprdpOptions", "partaiDprdpOptions", "isJenisPemilihanDprdp", "setupElectionTypes", "electionTypes", "setupPilgupOptions", "paslonPilgubOptions", "isJenisPemilihanPkwkp", "setupPilwalkotOptions", "paslonPilwalkotOptions", "isJenisPemilihanPkwkk", "setupPresidenOptions", "paslonPresidenOptions", "isJenisPemilihanPresiden", "setupWitnessTypeOptions", "witnessTypes", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessRegisterFormUseCase {
    private final LiveData<List<Candidate>> candidatesDpd;
    private final LiveData<Map<String, Long>> candidatesDprdk;
    private final LiveData<Map<String, Long>> candidatesDprdp;
    private final LiveData<List<Candidate>> candidatesPilgub;
    private final LiveData<List<Candidate>> candidatesPilwalkot;
    private final LiveData<List<Candidate>> candidatesPresiden;
    private final Context context;
    private final LiveData<Election> electionDpd;
    private final LiveData<Election> electionDpr;
    private final LiveData<Election> electionDprdk;
    private final LiveData<Election> electionDprdp;
    private final LiveData<Election> electionPilgub;
    private final LiveData<Election> electionPilwalkot;
    private final LiveData<Election> electionPresiden;
    private final ElectionRepository electionRepository;
    private final LiveData<List<ElectionWithRelation>> elections;
    private final LiveData<Resource<List<ElectionWithRelation>>> electionsResource;
    private WitnessRegisterFormState form;
    private final LiveData<Boolean> isDpdDone;
    private final CombinedLiveData<Boolean> isDpdShown;
    private final LiveData<Boolean> isDprDone;
    private final CombinedLiveData<Boolean> isDprShown;
    private final LiveData<Boolean> isDprdkDone;
    private final CombinedLiveData<Boolean> isDprdkShown;
    private final LiveData<Boolean> isDprdpDone;
    private final CombinedLiveData<Boolean> isDprdpShown;
    private final CombinedLiveData<Boolean> isElectionDone;
    private final LiveData<Boolean> isLoadingForm;
    private final LiveData<Boolean> isPilgubDone;
    private final CombinedLiveData<Boolean> isPilgubShown;
    private final LiveData<Boolean> isPilwalkotDone;
    private final CombinedLiveData<Boolean> isPilwalkotShown;
    private final LiveData<Boolean> isPresidenDone;
    private final CombinedLiveData<Boolean> isPresidenShown;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<Map<String, Long>> partaisDpr;

    public WitnessRegisterFormUseCase(ElectionRepository electionRepository, Context context) {
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(context, "context");
        this.electionRepository = electionRepository;
        this.context = context;
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData;
        LiveData<Resource<List<ElectionWithRelation>>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<List<ElectionWithRelation>>>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionsResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<ElectionWithRelation>>> invoke(String str) {
                ElectionRepository electionRepository2;
                if (str == null) {
                    return null;
                }
                electionRepository2 = WitnessRegisterFormUseCase.this.electionRepository;
                return electionRepository2.getElectionsByKodeTps(str);
            }
        });
        this.electionsResource = switchMap;
        this.isLoadingForm = Transformations.map(switchMap, new Function1<Resource<List<ElectionWithRelation>>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isLoadingForm$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<List<ElectionWithRelation>> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        LiveData<List<ElectionWithRelation>> map = Transformations.map(switchMap, new Function1<Resource<List<ElectionWithRelation>>, List<ElectionWithRelation>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$elections$1
            @Override // kotlin.jvm.functions.Function1
            public final List<ElectionWithRelation> invoke(Resource<List<ElectionWithRelation>> resource) {
                if (resource == null) {
                    return CollectionsKt.emptyList();
                }
                return resource.getPayload();
            }
        });
        this.elections = map;
        LiveData<List<Candidate>> map2 = Transformations.map(map, new Function1<List<ElectionWithRelation>, List<Candidate>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$candidatesPresiden$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Candidate> invoke(List<ElectionWithRelation> list) {
                Object obj;
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (Intrinsics.areEqual(((ElectionWithRelation) obj).getElection().getPemilihan(), Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                            break;
                        }
                    }
                    ElectionWithRelation electionWithRelation = (ElectionWithRelation) obj;
                    if (electionWithRelation != null) {
                        return electionWithRelation.getCandidates();
                    }
                    return null;
                }
                return null;
            }
        });
        this.candidatesPresiden = map2;
        LiveData<Election> switchMap2 = Transformations.switchMap(map, new Function1<List<ElectionWithRelation>, LiveData<Election>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionPresiden$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final androidx.lifecycle.LiveData<org.informatika.sirekap.model.Election> invoke(java.util.List<org.informatika.sirekap.model.ElectionWithRelation> r6) {
                /*
                    r5 = this;
                    java.lang.String r0 = "ppwp"
                    r1 = 0
                    if (r6 == 0) goto L38
                    r2 = r6
                    java.lang.Iterable r2 = (java.lang.Iterable) r2
                    java.util.Iterator r2 = r2.iterator()
                Lc:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L28
                    java.lang.Object r3 = r2.next()
                    r4 = r3
                    org.informatika.sirekap.model.ElectionWithRelation r4 = (org.informatika.sirekap.model.ElectionWithRelation) r4
                    org.informatika.sirekap.model.Election r4 = r4.getElection()
                    java.lang.String r4 = r4.getPemilihan()
                    boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
                    if (r4 == 0) goto Lc
                    goto L29
                L28:
                    r3 = r1
                L29:
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    if (r3 == 0) goto L38
                    org.informatika.sirekap.model.Election r2 = r3.getElection()
                    if (r2 == 0) goto L38
                    java.lang.String r2 = r2.getId()
                    goto L39
                L38:
                    r2 = r1
                L39:
                    if (r2 == 0) goto L6e
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    java.util.Iterator r6 = r6.iterator()
                L41:
                    boolean r2 = r6.hasNext()
                    if (r2 == 0) goto L5d
                    java.lang.Object r2 = r6.next()
                    r3 = r2
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    org.informatika.sirekap.model.Election r3 = r3.getElection()
                    java.lang.String r3 = r3.getPemilihan()
                    boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
                    if (r3 == 0) goto L41
                    goto L5e
                L5d:
                    r2 = r1
                L5e:
                    org.informatika.sirekap.model.ElectionWithRelation r2 = (org.informatika.sirekap.model.ElectionWithRelation) r2
                    if (r2 == 0) goto L66
                    org.informatika.sirekap.model.Election r1 = r2.getElection()
                L66:
                    androidx.lifecycle.MutableLiveData r6 = new androidx.lifecycle.MutableLiveData
                    r6.<init>(r1)
                    androidx.lifecycle.LiveData r6 = (androidx.lifecycle.LiveData) r6
                    goto L74
                L6e:
                    org.informatika.sirekap.support.livedata.AbsentLiveData$Companion r6 = org.informatika.sirekap.support.livedata.AbsentLiveData.Companion
                    androidx.lifecycle.LiveData r6 = r6.create()
                L74:
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionPresiden$1.invoke(java.util.List):androidx.lifecycle.LiveData");
            }
        });
        this.electionPresiden = switchMap2;
        LiveData<Boolean> map3 = Transformations.map(switchMap2, new Function1<Election, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isPresidenDone$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Election election) {
                return Boolean.valueOf(election != null ? election.isZipped() : true);
            }
        });
        this.isPresidenDone = map3;
        this.isPresidenShown = new CombinedLiveData<>(new LiveData[]{map2, map3}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isPresidenShown$1
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(java.util.List<? extends java.lang.Object> r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "data"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    r0 = r5
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
                    if (r0 == 0) goto L33
                L31:
                    r2 = r3
                    goto L5a
                L33:
                    java.lang.Object r0 = r5.get(r3)
                    java.util.List r0 = (java.util.List) r0
                    java.lang.Object r5 = r5.get(r2)
                    java.lang.String r1 = "null cannot be cast to non-null type kotlin.Boolean"
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r1)
                    java.lang.Boolean r5 = (java.lang.Boolean) r5
                    boolean r5 = r5.booleanValue()
                    java.util.Collection r0 = (java.util.Collection) r0
                    if (r0 == 0) goto L55
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L53
                    goto L55
                L53:
                    r0 = r3
                    goto L56
                L55:
                    r0 = r2
                L56:
                    if (r0 != 0) goto L31
                    if (r5 != 0) goto L31
                L5a:
                    java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isPresidenShown$1.invoke(java.util.List):java.lang.Boolean");
            }
        });
        LiveData<List<Candidate>> map4 = Transformations.map(map, new Function1<List<ElectionWithRelation>, List<Candidate>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$candidatesPilgub$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Candidate> invoke(List<ElectionWithRelation> list) {
                Object obj;
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (Intrinsics.areEqual(((ElectionWithRelation) obj).getElection().getPemilihan(), Election.ELECTION_PEMILIHAN_PROVINSI)) {
                            break;
                        }
                    }
                    ElectionWithRelation electionWithRelation = (ElectionWithRelation) obj;
                    if (electionWithRelation != null) {
                        return electionWithRelation.getCandidates();
                    }
                    return null;
                }
                return null;
            }
        });
        this.candidatesPilgub = map4;
        LiveData<Election> switchMap3 = Transformations.switchMap(map, new Function1<List<ElectionWithRelation>, LiveData<Election>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionPilgub$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final androidx.lifecycle.LiveData<org.informatika.sirekap.model.Election> invoke(java.util.List<org.informatika.sirekap.model.ElectionWithRelation> r6) {
                /*
                    r5 = this;
                    java.lang.String r0 = "pkwkp"
                    r1 = 0
                    if (r6 == 0) goto L38
                    r2 = r6
                    java.lang.Iterable r2 = (java.lang.Iterable) r2
                    java.util.Iterator r2 = r2.iterator()
                Lc:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L28
                    java.lang.Object r3 = r2.next()
                    r4 = r3
                    org.informatika.sirekap.model.ElectionWithRelation r4 = (org.informatika.sirekap.model.ElectionWithRelation) r4
                    org.informatika.sirekap.model.Election r4 = r4.getElection()
                    java.lang.String r4 = r4.getPemilihan()
                    boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
                    if (r4 == 0) goto Lc
                    goto L29
                L28:
                    r3 = r1
                L29:
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    if (r3 == 0) goto L38
                    org.informatika.sirekap.model.Election r2 = r3.getElection()
                    if (r2 == 0) goto L38
                    java.lang.String r2 = r2.getId()
                    goto L39
                L38:
                    r2 = r1
                L39:
                    if (r2 == 0) goto L6e
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    java.util.Iterator r6 = r6.iterator()
                L41:
                    boolean r2 = r6.hasNext()
                    if (r2 == 0) goto L5d
                    java.lang.Object r2 = r6.next()
                    r3 = r2
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    org.informatika.sirekap.model.Election r3 = r3.getElection()
                    java.lang.String r3 = r3.getPemilihan()
                    boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
                    if (r3 == 0) goto L41
                    goto L5e
                L5d:
                    r2 = r1
                L5e:
                    org.informatika.sirekap.model.ElectionWithRelation r2 = (org.informatika.sirekap.model.ElectionWithRelation) r2
                    if (r2 == 0) goto L66
                    org.informatika.sirekap.model.Election r1 = r2.getElection()
                L66:
                    androidx.lifecycle.MutableLiveData r6 = new androidx.lifecycle.MutableLiveData
                    r6.<init>(r1)
                    androidx.lifecycle.LiveData r6 = (androidx.lifecycle.LiveData) r6
                    goto L74
                L6e:
                    org.informatika.sirekap.support.livedata.AbsentLiveData$Companion r6 = org.informatika.sirekap.support.livedata.AbsentLiveData.Companion
                    androidx.lifecycle.LiveData r6 = r6.create()
                L74:
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionPilgub$1.invoke(java.util.List):androidx.lifecycle.LiveData");
            }
        });
        this.electionPilgub = switchMap3;
        LiveData<Boolean> map5 = Transformations.map(switchMap3, new Function1<Election, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isPilgubDone$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Election election) {
                return Boolean.valueOf(election != null ? election.isZipped() : true);
            }
        });
        this.isPilgubDone = map5;
        this.isPilgubShown = new CombinedLiveData<>(new LiveData[]{map4, map5}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isPilgubShown$1
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(java.util.List<? extends java.lang.Object> r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "data"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    r0 = r5
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
                    if (r0 == 0) goto L33
                L31:
                    r2 = r3
                    goto L5a
                L33:
                    java.lang.Object r0 = r5.get(r3)
                    java.util.List r0 = (java.util.List) r0
                    java.lang.Object r5 = r5.get(r2)
                    java.lang.String r1 = "null cannot be cast to non-null type kotlin.Boolean"
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r1)
                    java.lang.Boolean r5 = (java.lang.Boolean) r5
                    boolean r5 = r5.booleanValue()
                    java.util.Collection r0 = (java.util.Collection) r0
                    if (r0 == 0) goto L55
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L53
                    goto L55
                L53:
                    r0 = r3
                    goto L56
                L55:
                    r0 = r2
                L56:
                    if (r0 != 0) goto L31
                    if (r5 != 0) goto L31
                L5a:
                    java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isPilgubShown$1.invoke(java.util.List):java.lang.Boolean");
            }
        });
        LiveData<List<Candidate>> map6 = Transformations.map(map, new Function1<List<ElectionWithRelation>, List<Candidate>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$candidatesPilwalkot$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Candidate> invoke(List<ElectionWithRelation> list) {
                Object obj;
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (Intrinsics.areEqual(((ElectionWithRelation) obj).getElection().getPemilihan(), Election.ELECTION_PEMILIHAN_KOTAKAB)) {
                            break;
                        }
                    }
                    ElectionWithRelation electionWithRelation = (ElectionWithRelation) obj;
                    if (electionWithRelation != null) {
                        return electionWithRelation.getCandidates();
                    }
                    return null;
                }
                return null;
            }
        });
        this.candidatesPilwalkot = map6;
        LiveData<Election> switchMap4 = Transformations.switchMap(map, new Function1<List<ElectionWithRelation>, LiveData<Election>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionPilwalkot$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final androidx.lifecycle.LiveData<org.informatika.sirekap.model.Election> invoke(java.util.List<org.informatika.sirekap.model.ElectionWithRelation> r6) {
                /*
                    r5 = this;
                    r0 = 0
                    if (r6 == 0) goto L38
                    r1 = r6
                    java.lang.Iterable r1 = (java.lang.Iterable) r1
                    java.util.Iterator r1 = r1.iterator()
                La:
                    boolean r2 = r1.hasNext()
                    if (r2 == 0) goto L28
                    java.lang.Object r2 = r1.next()
                    r3 = r2
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    org.informatika.sirekap.model.Election r3 = r3.getElection()
                    java.lang.String r3 = r3.getPemilihan()
                    java.lang.String r4 = "pkwkk"
                    boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
                    if (r3 == 0) goto La
                    goto L29
                L28:
                    r2 = r0
                L29:
                    org.informatika.sirekap.model.ElectionWithRelation r2 = (org.informatika.sirekap.model.ElectionWithRelation) r2
                    if (r2 == 0) goto L38
                    org.informatika.sirekap.model.Election r1 = r2.getElection()
                    if (r1 == 0) goto L38
                    java.lang.String r1 = r1.getId()
                    goto L39
                L38:
                    r1 = r0
                L39:
                    if (r1 == 0) goto L70
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    java.util.Iterator r6 = r6.iterator()
                L41:
                    boolean r1 = r6.hasNext()
                    if (r1 == 0) goto L5f
                    java.lang.Object r1 = r6.next()
                    r2 = r1
                    org.informatika.sirekap.model.ElectionWithRelation r2 = (org.informatika.sirekap.model.ElectionWithRelation) r2
                    org.informatika.sirekap.model.Election r2 = r2.getElection()
                    java.lang.String r2 = r2.getPemilihan()
                    java.lang.String r3 = "pkwkp"
                    boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
                    if (r2 == 0) goto L41
                    goto L60
                L5f:
                    r1 = r0
                L60:
                    org.informatika.sirekap.model.ElectionWithRelation r1 = (org.informatika.sirekap.model.ElectionWithRelation) r1
                    if (r1 == 0) goto L68
                    org.informatika.sirekap.model.Election r0 = r1.getElection()
                L68:
                    androidx.lifecycle.MutableLiveData r6 = new androidx.lifecycle.MutableLiveData
                    r6.<init>(r0)
                    androidx.lifecycle.LiveData r6 = (androidx.lifecycle.LiveData) r6
                    goto L76
                L70:
                    org.informatika.sirekap.support.livedata.AbsentLiveData$Companion r6 = org.informatika.sirekap.support.livedata.AbsentLiveData.Companion
                    androidx.lifecycle.LiveData r6 = r6.create()
                L76:
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionPilwalkot$1.invoke(java.util.List):androidx.lifecycle.LiveData");
            }
        });
        this.electionPilwalkot = switchMap4;
        LiveData<Boolean> map7 = Transformations.map(switchMap4, new Function1<Election, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isPilwalkotDone$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Election election) {
                return Boolean.valueOf(election != null ? election.isZipped() : true);
            }
        });
        this.isPilwalkotDone = map7;
        this.isPilwalkotShown = new CombinedLiveData<>(new LiveData[]{map6, map7}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isPilwalkotShown$1
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(java.util.List<? extends java.lang.Object> r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "data"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    r0 = r5
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
                    if (r0 == 0) goto L33
                L31:
                    r2 = r3
                    goto L5a
                L33:
                    java.lang.Object r0 = r5.get(r3)
                    java.util.List r0 = (java.util.List) r0
                    java.lang.Object r5 = r5.get(r2)
                    java.lang.String r1 = "null cannot be cast to non-null type kotlin.Boolean"
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r1)
                    java.lang.Boolean r5 = (java.lang.Boolean) r5
                    boolean r5 = r5.booleanValue()
                    java.util.Collection r0 = (java.util.Collection) r0
                    if (r0 == 0) goto L55
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L53
                    goto L55
                L53:
                    r0 = r3
                    goto L56
                L55:
                    r0 = r2
                L56:
                    if (r0 != 0) goto L31
                    if (r5 != 0) goto L31
                L5a:
                    java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isPilwalkotShown$1.invoke(java.util.List):java.lang.Boolean");
            }
        });
        LiveData<Map<String, Long>> map8 = Transformations.map(map, new Function1<List<ElectionWithRelation>, Map<String, Long>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$partaisDpr$1
            @Override // kotlin.jvm.functions.Function1
            public final Map<String, Long> invoke(List<ElectionWithRelation> list) {
                Object obj;
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (Intrinsics.areEqual(((ElectionWithRelation) obj).getElection().getPemilihan(), Election.ELECTION_PEMILIHAN_DPR)) {
                            break;
                        }
                    }
                    ElectionWithRelation electionWithRelation = (ElectionWithRelation) obj;
                    if (electionWithRelation != null) {
                        return electionWithRelation.getAllPartais();
                    }
                    return null;
                }
                return null;
            }
        });
        this.partaisDpr = map8;
        LiveData<Election> switchMap5 = Transformations.switchMap(map, new Function1<List<ElectionWithRelation>, LiveData<Election>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionDpr$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final androidx.lifecycle.LiveData<org.informatika.sirekap.model.Election> invoke(java.util.List<org.informatika.sirekap.model.ElectionWithRelation> r6) {
                /*
                    r5 = this;
                    java.lang.String r0 = "pdpr"
                    r1 = 0
                    if (r6 == 0) goto L38
                    r2 = r6
                    java.lang.Iterable r2 = (java.lang.Iterable) r2
                    java.util.Iterator r2 = r2.iterator()
                Lc:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L28
                    java.lang.Object r3 = r2.next()
                    r4 = r3
                    org.informatika.sirekap.model.ElectionWithRelation r4 = (org.informatika.sirekap.model.ElectionWithRelation) r4
                    org.informatika.sirekap.model.Election r4 = r4.getElection()
                    java.lang.String r4 = r4.getPemilihan()
                    boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
                    if (r4 == 0) goto Lc
                    goto L29
                L28:
                    r3 = r1
                L29:
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    if (r3 == 0) goto L38
                    org.informatika.sirekap.model.Election r2 = r3.getElection()
                    if (r2 == 0) goto L38
                    java.lang.String r2 = r2.getId()
                    goto L39
                L38:
                    r2 = r1
                L39:
                    if (r2 == 0) goto L6e
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    java.util.Iterator r6 = r6.iterator()
                L41:
                    boolean r2 = r6.hasNext()
                    if (r2 == 0) goto L5d
                    java.lang.Object r2 = r6.next()
                    r3 = r2
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    org.informatika.sirekap.model.Election r3 = r3.getElection()
                    java.lang.String r3 = r3.getPemilihan()
                    boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
                    if (r3 == 0) goto L41
                    goto L5e
                L5d:
                    r2 = r1
                L5e:
                    org.informatika.sirekap.model.ElectionWithRelation r2 = (org.informatika.sirekap.model.ElectionWithRelation) r2
                    if (r2 == 0) goto L66
                    org.informatika.sirekap.model.Election r1 = r2.getElection()
                L66:
                    androidx.lifecycle.MutableLiveData r6 = new androidx.lifecycle.MutableLiveData
                    r6.<init>(r1)
                    androidx.lifecycle.LiveData r6 = (androidx.lifecycle.LiveData) r6
                    goto L74
                L6e:
                    org.informatika.sirekap.support.livedata.AbsentLiveData$Companion r6 = org.informatika.sirekap.support.livedata.AbsentLiveData.Companion
                    androidx.lifecycle.LiveData r6 = r6.create()
                L74:
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionDpr$1.invoke(java.util.List):androidx.lifecycle.LiveData");
            }
        });
        this.electionDpr = switchMap5;
        LiveData<Boolean> map9 = Transformations.map(switchMap5, new Function1<Election, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDprDone$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Election election) {
                return Boolean.valueOf(election != null ? election.isZipped() : true);
            }
        });
        this.isDprDone = map9;
        this.isDprShown = new CombinedLiveData<>(new LiveData[]{map8, map9}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDprShown$1
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(java.util.List<? extends java.lang.Object> r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "data"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    r0 = r5
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
                    if (r0 == 0) goto L33
                L31:
                    r2 = r3
                    goto L58
                L33:
                    java.lang.Object r0 = r5.get(r3)
                    java.util.Map r0 = (java.util.Map) r0
                    java.lang.Object r5 = r5.get(r2)
                    java.lang.String r1 = "null cannot be cast to non-null type kotlin.Boolean"
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r1)
                    java.lang.Boolean r5 = (java.lang.Boolean) r5
                    boolean r5 = r5.booleanValue()
                    if (r0 == 0) goto L53
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L51
                    goto L53
                L51:
                    r0 = r3
                    goto L54
                L53:
                    r0 = r2
                L54:
                    if (r0 != 0) goto L31
                    if (r5 != 0) goto L31
                L58:
                    java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDprShown$1.invoke(java.util.List):java.lang.Boolean");
            }
        });
        LiveData<Map<String, Long>> map10 = Transformations.map(map, new Function1<List<ElectionWithRelation>, Map<String, Long>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$candidatesDprdp$1
            @Override // kotlin.jvm.functions.Function1
            public final Map<String, Long> invoke(List<ElectionWithRelation> list) {
                Object obj;
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (Intrinsics.areEqual(((ElectionWithRelation) obj).getElection().getPemilihan(), Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                            break;
                        }
                    }
                    ElectionWithRelation electionWithRelation = (ElectionWithRelation) obj;
                    if (electionWithRelation != null) {
                        return electionWithRelation.getAllPartais();
                    }
                    return null;
                }
                return null;
            }
        });
        this.candidatesDprdp = map10;
        LiveData<Election> switchMap6 = Transformations.switchMap(map, new Function1<List<ElectionWithRelation>, LiveData<Election>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionDprdp$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final androidx.lifecycle.LiveData<org.informatika.sirekap.model.Election> invoke(java.util.List<org.informatika.sirekap.model.ElectionWithRelation> r6) {
                /*
                    r5 = this;
                    java.lang.String r0 = "pdprdp"
                    r1 = 0
                    if (r6 == 0) goto L38
                    r2 = r6
                    java.lang.Iterable r2 = (java.lang.Iterable) r2
                    java.util.Iterator r2 = r2.iterator()
                Lc:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L28
                    java.lang.Object r3 = r2.next()
                    r4 = r3
                    org.informatika.sirekap.model.ElectionWithRelation r4 = (org.informatika.sirekap.model.ElectionWithRelation) r4
                    org.informatika.sirekap.model.Election r4 = r4.getElection()
                    java.lang.String r4 = r4.getPemilihan()
                    boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
                    if (r4 == 0) goto Lc
                    goto L29
                L28:
                    r3 = r1
                L29:
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    if (r3 == 0) goto L38
                    org.informatika.sirekap.model.Election r2 = r3.getElection()
                    if (r2 == 0) goto L38
                    java.lang.String r2 = r2.getId()
                    goto L39
                L38:
                    r2 = r1
                L39:
                    if (r2 == 0) goto L6e
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    java.util.Iterator r6 = r6.iterator()
                L41:
                    boolean r2 = r6.hasNext()
                    if (r2 == 0) goto L5d
                    java.lang.Object r2 = r6.next()
                    r3 = r2
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    org.informatika.sirekap.model.Election r3 = r3.getElection()
                    java.lang.String r3 = r3.getPemilihan()
                    boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
                    if (r3 == 0) goto L41
                    goto L5e
                L5d:
                    r2 = r1
                L5e:
                    org.informatika.sirekap.model.ElectionWithRelation r2 = (org.informatika.sirekap.model.ElectionWithRelation) r2
                    if (r2 == 0) goto L66
                    org.informatika.sirekap.model.Election r1 = r2.getElection()
                L66:
                    androidx.lifecycle.MutableLiveData r6 = new androidx.lifecycle.MutableLiveData
                    r6.<init>(r1)
                    androidx.lifecycle.LiveData r6 = (androidx.lifecycle.LiveData) r6
                    goto L74
                L6e:
                    org.informatika.sirekap.support.livedata.AbsentLiveData$Companion r6 = org.informatika.sirekap.support.livedata.AbsentLiveData.Companion
                    androidx.lifecycle.LiveData r6 = r6.create()
                L74:
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionDprdp$1.invoke(java.util.List):androidx.lifecycle.LiveData");
            }
        });
        this.electionDprdp = switchMap6;
        LiveData<Boolean> map11 = Transformations.map(switchMap6, new Function1<Election, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDprdpDone$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Election election) {
                return Boolean.valueOf(election != null ? election.isZipped() : true);
            }
        });
        this.isDprdpDone = map11;
        this.isDprdpShown = new CombinedLiveData<>(new LiveData[]{map10, map11}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDprdpShown$1
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(java.util.List<? extends java.lang.Object> r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "data"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    r0 = r5
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
                    if (r0 == 0) goto L33
                L31:
                    r2 = r3
                    goto L58
                L33:
                    java.lang.Object r0 = r5.get(r3)
                    java.util.Map r0 = (java.util.Map) r0
                    java.lang.Object r5 = r5.get(r2)
                    java.lang.String r1 = "null cannot be cast to non-null type kotlin.Boolean"
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r1)
                    java.lang.Boolean r5 = (java.lang.Boolean) r5
                    boolean r5 = r5.booleanValue()
                    if (r0 == 0) goto L53
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L51
                    goto L53
                L51:
                    r0 = r3
                    goto L54
                L53:
                    r0 = r2
                L54:
                    if (r0 != 0) goto L31
                    if (r5 != 0) goto L31
                L58:
                    java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDprdpShown$1.invoke(java.util.List):java.lang.Boolean");
            }
        });
        LiveData<Map<String, Long>> map12 = Transformations.map(map, new Function1<List<ElectionWithRelation>, Map<String, Long>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$candidatesDprdk$1
            @Override // kotlin.jvm.functions.Function1
            public final Map<String, Long> invoke(List<ElectionWithRelation> list) {
                Object obj;
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (Intrinsics.areEqual(((ElectionWithRelation) obj).getElection().getPemilihan(), Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                            break;
                        }
                    }
                    ElectionWithRelation electionWithRelation = (ElectionWithRelation) obj;
                    if (electionWithRelation != null) {
                        return electionWithRelation.getAllPartais();
                    }
                    return null;
                }
                return null;
            }
        });
        this.candidatesDprdk = map12;
        LiveData<Election> switchMap7 = Transformations.switchMap(map, new Function1<List<ElectionWithRelation>, LiveData<Election>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionDprdk$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final androidx.lifecycle.LiveData<org.informatika.sirekap.model.Election> invoke(java.util.List<org.informatika.sirekap.model.ElectionWithRelation> r6) {
                /*
                    r5 = this;
                    java.lang.String r0 = "pdprdk"
                    r1 = 0
                    if (r6 == 0) goto L38
                    r2 = r6
                    java.lang.Iterable r2 = (java.lang.Iterable) r2
                    java.util.Iterator r2 = r2.iterator()
                Lc:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L28
                    java.lang.Object r3 = r2.next()
                    r4 = r3
                    org.informatika.sirekap.model.ElectionWithRelation r4 = (org.informatika.sirekap.model.ElectionWithRelation) r4
                    org.informatika.sirekap.model.Election r4 = r4.getElection()
                    java.lang.String r4 = r4.getPemilihan()
                    boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
                    if (r4 == 0) goto Lc
                    goto L29
                L28:
                    r3 = r1
                L29:
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    if (r3 == 0) goto L38
                    org.informatika.sirekap.model.Election r2 = r3.getElection()
                    if (r2 == 0) goto L38
                    java.lang.String r2 = r2.getId()
                    goto L39
                L38:
                    r2 = r1
                L39:
                    if (r2 == 0) goto L6e
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    java.util.Iterator r6 = r6.iterator()
                L41:
                    boolean r2 = r6.hasNext()
                    if (r2 == 0) goto L5d
                    java.lang.Object r2 = r6.next()
                    r3 = r2
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    org.informatika.sirekap.model.Election r3 = r3.getElection()
                    java.lang.String r3 = r3.getPemilihan()
                    boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
                    if (r3 == 0) goto L41
                    goto L5e
                L5d:
                    r2 = r1
                L5e:
                    org.informatika.sirekap.model.ElectionWithRelation r2 = (org.informatika.sirekap.model.ElectionWithRelation) r2
                    if (r2 == 0) goto L66
                    org.informatika.sirekap.model.Election r1 = r2.getElection()
                L66:
                    androidx.lifecycle.MutableLiveData r6 = new androidx.lifecycle.MutableLiveData
                    r6.<init>(r1)
                    androidx.lifecycle.LiveData r6 = (androidx.lifecycle.LiveData) r6
                    goto L74
                L6e:
                    org.informatika.sirekap.support.livedata.AbsentLiveData$Companion r6 = org.informatika.sirekap.support.livedata.AbsentLiveData.Companion
                    androidx.lifecycle.LiveData r6 = r6.create()
                L74:
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionDprdk$1.invoke(java.util.List):androidx.lifecycle.LiveData");
            }
        });
        this.electionDprdk = switchMap7;
        LiveData<Boolean> map13 = Transformations.map(switchMap7, new Function1<Election, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDprdkDone$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Election election) {
                return Boolean.valueOf(election != null ? election.isZipped() : true);
            }
        });
        this.isDprdkDone = map13;
        this.isDprdkShown = new CombinedLiveData<>(new LiveData[]{map12, map13}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDprdkShown$1
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(java.util.List<? extends java.lang.Object> r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "data"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    r0 = r5
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
                    if (r0 == 0) goto L33
                L31:
                    r2 = r3
                    goto L58
                L33:
                    java.lang.Object r0 = r5.get(r3)
                    java.util.Map r0 = (java.util.Map) r0
                    java.lang.Object r5 = r5.get(r2)
                    java.lang.String r1 = "null cannot be cast to non-null type kotlin.Boolean"
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r1)
                    java.lang.Boolean r5 = (java.lang.Boolean) r5
                    boolean r5 = r5.booleanValue()
                    if (r0 == 0) goto L53
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L51
                    goto L53
                L51:
                    r0 = r3
                    goto L54
                L53:
                    r0 = r2
                L54:
                    if (r0 != 0) goto L31
                    if (r5 != 0) goto L31
                L58:
                    java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDprdkShown$1.invoke(java.util.List):java.lang.Boolean");
            }
        });
        LiveData<List<Candidate>> map14 = Transformations.map(map, new Function1<List<ElectionWithRelation>, List<Candidate>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$candidatesDpd$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Candidate> invoke(List<ElectionWithRelation> list) {
                Object obj;
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (Intrinsics.areEqual(((ElectionWithRelation) obj).getElection().getPemilihan(), Election.ELECTION_PEMILIHAN_DPD)) {
                            break;
                        }
                    }
                    ElectionWithRelation electionWithRelation = (ElectionWithRelation) obj;
                    if (electionWithRelation != null) {
                        return electionWithRelation.getCandidates();
                    }
                    return null;
                }
                return null;
            }
        });
        this.candidatesDpd = map14;
        LiveData<Election> switchMap8 = Transformations.switchMap(map, new Function1<List<ElectionWithRelation>, LiveData<Election>>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionDpd$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final androidx.lifecycle.LiveData<org.informatika.sirekap.model.Election> invoke(java.util.List<org.informatika.sirekap.model.ElectionWithRelation> r6) {
                /*
                    r5 = this;
                    java.lang.String r0 = "pdpd"
                    r1 = 0
                    if (r6 == 0) goto L38
                    r2 = r6
                    java.lang.Iterable r2 = (java.lang.Iterable) r2
                    java.util.Iterator r2 = r2.iterator()
                Lc:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L28
                    java.lang.Object r3 = r2.next()
                    r4 = r3
                    org.informatika.sirekap.model.ElectionWithRelation r4 = (org.informatika.sirekap.model.ElectionWithRelation) r4
                    org.informatika.sirekap.model.Election r4 = r4.getElection()
                    java.lang.String r4 = r4.getPemilihan()
                    boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
                    if (r4 == 0) goto Lc
                    goto L29
                L28:
                    r3 = r1
                L29:
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    if (r3 == 0) goto L38
                    org.informatika.sirekap.model.Election r2 = r3.getElection()
                    if (r2 == 0) goto L38
                    java.lang.String r2 = r2.getId()
                    goto L39
                L38:
                    r2 = r1
                L39:
                    if (r2 == 0) goto L6e
                    java.lang.Iterable r6 = (java.lang.Iterable) r6
                    java.util.Iterator r6 = r6.iterator()
                L41:
                    boolean r2 = r6.hasNext()
                    if (r2 == 0) goto L5d
                    java.lang.Object r2 = r6.next()
                    r3 = r2
                    org.informatika.sirekap.model.ElectionWithRelation r3 = (org.informatika.sirekap.model.ElectionWithRelation) r3
                    org.informatika.sirekap.model.Election r3 = r3.getElection()
                    java.lang.String r3 = r3.getPemilihan()
                    boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
                    if (r3 == 0) goto L41
                    goto L5e
                L5d:
                    r2 = r1
                L5e:
                    org.informatika.sirekap.model.ElectionWithRelation r2 = (org.informatika.sirekap.model.ElectionWithRelation) r2
                    if (r2 == 0) goto L66
                    org.informatika.sirekap.model.Election r1 = r2.getElection()
                L66:
                    androidx.lifecycle.MutableLiveData r6 = new androidx.lifecycle.MutableLiveData
                    r6.<init>(r1)
                    androidx.lifecycle.LiveData r6 = (androidx.lifecycle.LiveData) r6
                    goto L74
                L6e:
                    org.informatika.sirekap.support.livedata.AbsentLiveData$Companion r6 = org.informatika.sirekap.support.livedata.AbsentLiveData.Companion
                    androidx.lifecycle.LiveData r6 = r6.create()
                L74:
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$electionDpd$1.invoke(java.util.List):androidx.lifecycle.LiveData");
            }
        });
        this.electionDpd = switchMap8;
        LiveData<Boolean> map15 = Transformations.map(switchMap8, new Function1<Election, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDpdDone$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Election election) {
                return Boolean.valueOf(election != null ? election.isZipped() : true);
            }
        });
        this.isDpdDone = map15;
        this.isDpdShown = new CombinedLiveData<>(new LiveData[]{map14, map15}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDpdShown$1
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(java.util.List<? extends java.lang.Object> r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "data"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    r0 = r5
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
                    if (r0 == 0) goto L33
                L31:
                    r2 = r3
                    goto L5a
                L33:
                    java.lang.Object r0 = r5.get(r3)
                    java.util.List r0 = (java.util.List) r0
                    java.lang.Object r5 = r5.get(r2)
                    java.lang.String r1 = "null cannot be cast to non-null type kotlin.Boolean"
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r1)
                    java.lang.Boolean r5 = (java.lang.Boolean) r5
                    boolean r5 = r5.booleanValue()
                    java.util.Collection r0 = (java.util.Collection) r0
                    if (r0 == 0) goto L55
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L53
                    goto L55
                L53:
                    r0 = r3
                    goto L56
                L55:
                    r0 = r2
                L56:
                    if (r0 != 0) goto L31
                    if (r5 != 0) goto L31
                L5a:
                    java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isDpdShown$1.invoke(java.util.List):java.lang.Boolean");
            }
        });
        this.isElectionDone = new CombinedLiveData<>(new LiveData[]{map3, map5, map7, map9, map11, map13, map15}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase$isElectionDone$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<? extends Object> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                boolean z = false;
                Object obj = data.get(0);
                Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
                boolean booleanValue = bool != null ? bool.booleanValue() : true;
                Object obj2 = data.get(1);
                Boolean bool2 = obj2 instanceof Boolean ? (Boolean) obj2 : null;
                boolean booleanValue2 = bool2 != null ? bool2.booleanValue() : true;
                Object obj3 = data.get(2);
                Boolean bool3 = obj3 instanceof Boolean ? (Boolean) obj3 : null;
                boolean booleanValue3 = bool3 != null ? bool3.booleanValue() : true;
                Object obj4 = data.get(3);
                Boolean bool4 = obj4 instanceof Boolean ? (Boolean) obj4 : null;
                boolean booleanValue4 = bool4 != null ? bool4.booleanValue() : true;
                Object obj5 = data.get(4);
                Boolean bool5 = obj5 instanceof Boolean ? (Boolean) obj5 : null;
                boolean booleanValue5 = bool5 != null ? bool5.booleanValue() : true;
                Object obj6 = data.get(5);
                Boolean bool6 = obj6 instanceof Boolean ? (Boolean) obj6 : null;
                boolean booleanValue6 = bool6 != null ? bool6.booleanValue() : true;
                Object obj7 = data.get(6);
                Boolean bool7 = obj7 instanceof Boolean ? (Boolean) obj7 : null;
                boolean booleanValue7 = bool7 != null ? bool7.booleanValue() : true;
                if (booleanValue && booleanValue2 && booleanValue3 && booleanValue4 && booleanValue5 && booleanValue6 && booleanValue7) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    public final WitnessRegisterFormState getForm() {
        return this.form;
    }

    public final void setForm(WitnessRegisterFormState witnessRegisterFormState) {
        this.form = witnessRegisterFormState;
    }

    public final void setup(String _kodeTps) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        this.kodeTps.postValue(_kodeTps);
        this.form = new WitnessRegisterFormState(_kodeTps);
    }

    public final boolean isFormValid() {
        WitnessRegisterFormState witnessRegisterFormState = this.form;
        return witnessRegisterFormState != null && witnessRegisterFormState.getAllValid();
    }

    public final LiveData<Boolean> isLoadingForm() {
        return this.isLoadingForm;
    }

    public final LiveData<List<ElectionWithRelation>> getElections() {
        return this.elections;
    }

    public final LiveData<List<Candidate>> getCandidatesPresiden() {
        return this.candidatesPresiden;
    }

    public final CombinedLiveData<Boolean> isPresidenShown() {
        return this.isPresidenShown;
    }

    public final LiveData<List<Candidate>> getCandidatesPilgub() {
        return this.candidatesPilgub;
    }

    public final CombinedLiveData<Boolean> isPilgubShown() {
        return this.isPilgubShown;
    }

    public final LiveData<List<Candidate>> getCandidatesPilwalkot() {
        return this.candidatesPilwalkot;
    }

    public final CombinedLiveData<Boolean> isPilwalkotShown() {
        return this.isPilwalkotShown;
    }

    public final LiveData<Map<String, Long>> getPartaisDpr() {
        return this.partaisDpr;
    }

    public final CombinedLiveData<Boolean> isDprShown() {
        return this.isDprShown;
    }

    public final LiveData<Map<String, Long>> getCandidatesDprdp() {
        return this.candidatesDprdp;
    }

    public final CombinedLiveData<Boolean> isDprdpShown() {
        return this.isDprdpShown;
    }

    public final LiveData<Map<String, Long>> getCandidatesDprdk() {
        return this.candidatesDprdk;
    }

    public final CombinedLiveData<Boolean> isDprdkShown() {
        return this.isDprdkShown;
    }

    public final LiveData<List<Candidate>> getCandidatesDpd() {
        return this.candidatesDpd;
    }

    public final CombinedLiveData<Boolean> isDpdShown() {
        return this.isDpdShown;
    }

    public final CombinedLiveData<Boolean> isElectionDone() {
        return this.isElectionDone;
    }

    public final void setupWitnessTypeOptions(List<String> witnessTypes) {
        Intrinsics.checkNotNullParameter(witnessTypes, "witnessTypes");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : witnessTypes) {
            linkedHashMap.put(Witness.Companion.getJenisPemeriksaText(this.context, str), str);
        }
        WitnessRegisterFormState witnessRegisterFormState = this.form;
        if (witnessRegisterFormState == null) {
            return;
        }
        witnessRegisterFormState.setJenisPemeriksaOptions(linkedHashMap);
    }

    public final void setupElectionTypes(List<String> electionTypes) {
        Intrinsics.checkNotNullParameter(electionTypes, "electionTypes");
        WitnessRegisterFormState witnessRegisterFormState = this.form;
        if (witnessRegisterFormState == null) {
            return;
        }
        witnessRegisterFormState.setElectionTypes(electionTypes);
    }

    public static /* synthetic */ void setupPresidenOptions$default(WitnessRegisterFormUseCase witnessRegisterFormUseCase, Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        witnessRegisterFormUseCase.setupPresidenOptions(map, z);
    }

    public final void setupPresidenOptions(Map<String, Long> paslonPresidenOptions, boolean z) {
        Intrinsics.checkNotNullParameter(paslonPresidenOptions, "paslonPresidenOptions");
        WitnessRegisterFormState witnessRegisterFormState = this.form;
        if (witnessRegisterFormState != null) {
            witnessRegisterFormState.setJenisPemilihanPresiden(z);
        }
        WitnessRegisterFormState witnessRegisterFormState2 = this.form;
        if (witnessRegisterFormState2 == null) {
            return;
        }
        witnessRegisterFormState2.setPaslonPresidenOptions(paslonPresidenOptions);
    }

    public static /* synthetic */ void setupPilgupOptions$default(WitnessRegisterFormUseCase witnessRegisterFormUseCase, Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        witnessRegisterFormUseCase.setupPilgupOptions(map, z);
    }

    public final void setupPilgupOptions(Map<String, Long> paslonPilgubOptions, boolean z) {
        Intrinsics.checkNotNullParameter(paslonPilgubOptions, "paslonPilgubOptions");
        WitnessRegisterFormState witnessRegisterFormState = this.form;
        if (witnessRegisterFormState != null) {
            witnessRegisterFormState.setJenisPemilihanPkwkp(z);
        }
        WitnessRegisterFormState witnessRegisterFormState2 = this.form;
        if (witnessRegisterFormState2 == null) {
            return;
        }
        witnessRegisterFormState2.setPaslonPilgubOptions(paslonPilgubOptions);
    }

    public static /* synthetic */ void setupPilwalkotOptions$default(WitnessRegisterFormUseCase witnessRegisterFormUseCase, Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        witnessRegisterFormUseCase.setupPilwalkotOptions(map, z);
    }

    public final void setupPilwalkotOptions(Map<String, Long> paslonPilwalkotOptions, boolean z) {
        Intrinsics.checkNotNullParameter(paslonPilwalkotOptions, "paslonPilwalkotOptions");
        WitnessRegisterFormState witnessRegisterFormState = this.form;
        if (witnessRegisterFormState != null) {
            witnessRegisterFormState.setJenisPemilihanPkwkk(z);
        }
        WitnessRegisterFormState witnessRegisterFormState2 = this.form;
        if (witnessRegisterFormState2 == null) {
            return;
        }
        witnessRegisterFormState2.setPaslonPilwalkotOptions(paslonPilwalkotOptions);
    }

    public static /* synthetic */ void setupDprOptions$default(WitnessRegisterFormUseCase witnessRegisterFormUseCase, Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        witnessRegisterFormUseCase.setupDprOptions(map, z);
    }

    public final void setupDprOptions(Map<String, Long> partaiDprOptions, boolean z) {
        Intrinsics.checkNotNullParameter(partaiDprOptions, "partaiDprOptions");
        WitnessRegisterFormState witnessRegisterFormState = this.form;
        if (witnessRegisterFormState != null) {
            witnessRegisterFormState.setJenisPemilihanDpr(z);
        }
        WitnessRegisterFormState witnessRegisterFormState2 = this.form;
        if (witnessRegisterFormState2 == null) {
            return;
        }
        witnessRegisterFormState2.setPartaiDprOptions(partaiDprOptions);
    }

    public static /* synthetic */ void setupDprdpOptions$default(WitnessRegisterFormUseCase witnessRegisterFormUseCase, Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        witnessRegisterFormUseCase.setupDprdpOptions(map, z);
    }

    public final void setupDprdpOptions(Map<String, Long> partaiDprdpOptions, boolean z) {
        Intrinsics.checkNotNullParameter(partaiDprdpOptions, "partaiDprdpOptions");
        WitnessRegisterFormState witnessRegisterFormState = this.form;
        if (witnessRegisterFormState != null) {
            witnessRegisterFormState.setJenisPemilihanDprdp(z);
        }
        WitnessRegisterFormState witnessRegisterFormState2 = this.form;
        if (witnessRegisterFormState2 == null) {
            return;
        }
        witnessRegisterFormState2.setPartaiDprdpOptions(partaiDprdpOptions);
    }

    public static /* synthetic */ void setupDprdkOptions$default(WitnessRegisterFormUseCase witnessRegisterFormUseCase, Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        witnessRegisterFormUseCase.setupDprdkOptions(map, z);
    }

    public final void setupDprdkOptions(Map<String, Long> partaiDprdkOptions, boolean z) {
        Intrinsics.checkNotNullParameter(partaiDprdkOptions, "partaiDprdkOptions");
        WitnessRegisterFormState witnessRegisterFormState = this.form;
        if (witnessRegisterFormState != null) {
            witnessRegisterFormState.setJenisPemilihanDprdk(z);
        }
        WitnessRegisterFormState witnessRegisterFormState2 = this.form;
        if (witnessRegisterFormState2 == null) {
            return;
        }
        witnessRegisterFormState2.setPartaiDprdkOptions(partaiDprdkOptions);
    }

    public static /* synthetic */ void setupDpdOptions$default(WitnessRegisterFormUseCase witnessRegisterFormUseCase, Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        witnessRegisterFormUseCase.setupDpdOptions(map, z);
    }

    public final void setupDpdOptions(Map<String, Long> paslonDpdOptions, boolean z) {
        Intrinsics.checkNotNullParameter(paslonDpdOptions, "paslonDpdOptions");
        WitnessRegisterFormState witnessRegisterFormState = this.form;
        if (witnessRegisterFormState != null) {
            witnessRegisterFormState.setJenisPemilihanDpd(z);
        }
        WitnessRegisterFormState witnessRegisterFormState2 = this.form;
        if (witnessRegisterFormState2 == null) {
            return;
        }
        witnessRegisterFormState2.setPaslonDpdOptions(paslonDpdOptions);
    }
}
