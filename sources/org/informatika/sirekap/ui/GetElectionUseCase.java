package org.informatika.sirekap.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1TabulationComplete;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.livedata.AbsentLiveData;

/* compiled from: GetElectionUseCase.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0012R\u001f\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0015\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00160\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\fR\u001f\u0010\u001e\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00160\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0\b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\fR\u001f\u0010#\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010\u00160\b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\fR\u001f\u0010%\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020&\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\fR%\u0010(\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\t\u0018\u00010\u00160\b¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\fR\u001f\u0010*\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020+\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\fR%\u0010-\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\t\u0018\u00010\u00160\b¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\f¨\u00062"}, d2 = {"Lorg/informatika/sirekap/ui/GetElectionUseCase;", "", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "(Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/repository/FormC1Repository;)V", "candidates", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/Candidate;", "getCandidates", "()Landroidx/lifecycle/LiveData;", "election", "Lorg/informatika/sirekap/model/Election;", "getElection", "electionId", "Landroidx/lifecycle/MutableLiveData;", "", "getElectionId", "()Landroidx/lifecycle/MutableLiveData;", "electionResource", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getElectionResource", "electionWithRelation", "getElectionWithRelation", "formC1AdministrationComplete", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "getFormC1AdministrationComplete", "formC1AdministrationCompleteResource", "getFormC1AdministrationCompleteResource", "formC1TabulationCompleteSectionV", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "getFormC1TabulationCompleteSectionV", "formC1TabulationCompleteSectionVResource", "getFormC1TabulationCompleteSectionVResource", "formC1TabulationCompletesSectionIV", "Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "getFormC1TabulationCompletesSectionIV", "formC1TabulationCompletesSectionIVResource", "getFormC1TabulationCompletesSectionIVResource", "formC1TabulationPartaiCompletesSectionIV", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "getFormC1TabulationPartaiCompletesSectionIV", "formC1TabulationPartaiCompletesSectionIVResource", "getFormC1TabulationPartaiCompletesSectionIVResource", "setup", "", "_electionId", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GetElectionUseCase {
    private final LiveData<List<Candidate>> candidates;
    private final LiveData<Election> election;
    private final MutableLiveData<String> electionId;
    private final DefaultElectionRepository electionRepository;
    private final LiveData<Resource<ElectionWithRelation>> electionResource;
    private final LiveData<ElectionWithRelation> electionWithRelation;
    private final LiveData<FormC1AdministrationComplete> formC1AdministrationComplete;
    private final LiveData<Resource<FormC1AdministrationComplete>> formC1AdministrationCompleteResource;
    private final FormC1Repository formC1Repository;
    private final LiveData<FormC1AdministrationHal2Complete> formC1TabulationCompleteSectionV;
    private final LiveData<Resource<FormC1AdministrationHal2Complete>> formC1TabulationCompleteSectionVResource;
    private final LiveData<List<FormC1TabulationComplete>> formC1TabulationCompletesSectionIV;
    private final LiveData<Resource<List<FormC1TabulationComplete>>> formC1TabulationCompletesSectionIVResource;
    private final LiveData<List<FormC1TabulationPartaiComplete>> formC1TabulationPartaiCompletesSectionIV;
    private final LiveData<Resource<List<FormC1TabulationPartaiComplete>>> formC1TabulationPartaiCompletesSectionIVResource;

    public GetElectionUseCase(DefaultElectionRepository electionRepository, FormC1Repository formC1Repository) {
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(formC1Repository, "formC1Repository");
        this.electionRepository = electionRepository;
        this.formC1Repository = formC1Repository;
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.electionId = mutableLiveData;
        LiveData<Resource<ElectionWithRelation>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<ElectionWithRelation>>>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$electionResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<ElectionWithRelation>> invoke(String str) {
                DefaultElectionRepository defaultElectionRepository;
                if (str != null) {
                    defaultElectionRepository = GetElectionUseCase.this.electionRepository;
                    return defaultElectionRepository.getElectionById(str);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.electionResource = switchMap;
        LiveData<ElectionWithRelation> map = Transformations.map(switchMap, new Function1<Resource<ElectionWithRelation>, ElectionWithRelation>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$electionWithRelation$1
            @Override // kotlin.jvm.functions.Function1
            public final ElectionWithRelation invoke(Resource<ElectionWithRelation> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.electionWithRelation = map;
        this.election = Transformations.map(map, new Function1<ElectionWithRelation, Election>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$election$1
            @Override // kotlin.jvm.functions.Function1
            public final Election invoke(ElectionWithRelation electionWithRelation) {
                if (electionWithRelation != null) {
                    return electionWithRelation.getElection();
                }
                return null;
            }
        });
        this.candidates = Transformations.map(map, new Function1<ElectionWithRelation, List<Candidate>>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$candidates$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Candidate> invoke(ElectionWithRelation electionWithRelation) {
                if (electionWithRelation != null) {
                    return electionWithRelation.getCandidates();
                }
                return null;
            }
        });
        LiveData<Resource<FormC1AdministrationComplete>> switchMap2 = Transformations.switchMap(map, new Function1<ElectionWithRelation, LiveData<Resource<FormC1AdministrationComplete>>>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$formC1AdministrationCompleteResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1AdministrationComplete>> invoke(ElectionWithRelation electionWithRelation) {
                Object obj;
                FormC1Repository formC1Repository2;
                boolean z;
                if (electionWithRelation == null) {
                    return AbsentLiveData.Companion.create();
                }
                Iterator<T> it = electionWithRelation.getPages().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((ElectionPage) obj).getKind() == 10) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        break;
                    }
                }
                ElectionPage electionPage = (ElectionPage) obj;
                if ((electionPage != null ? electionPage.getIdImage() : null) != null) {
                    formC1Repository2 = GetElectionUseCase.this.formC1Repository;
                    String idImage = electionPage.getIdImage();
                    Intrinsics.checkNotNull(idImage);
                    return formC1Repository2.getFormC1Administration(idImage, "", false, "");
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1AdministrationCompleteResource = switchMap2;
        this.formC1AdministrationComplete = Transformations.map(switchMap2, new Function1<Resource<FormC1AdministrationComplete>, FormC1AdministrationComplete>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$formC1AdministrationComplete$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1AdministrationComplete invoke(Resource<FormC1AdministrationComplete> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        LiveData<Resource<List<FormC1TabulationComplete>>> switchMap3 = Transformations.switchMap(map, new Function1<ElectionWithRelation, LiveData<Resource<List<FormC1TabulationComplete>>>>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$formC1TabulationCompletesSectionIVResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<FormC1TabulationComplete>>> invoke(ElectionWithRelation electionWithRelation) {
                FormC1Repository formC1Repository2;
                if (electionWithRelation == null) {
                    return AbsentLiveData.Companion.create();
                }
                ArrayList arrayList = new ArrayList();
                for (Object obj : electionWithRelation.getPages()) {
                    if (((ElectionPage) obj).getKind() == 20) {
                        arrayList.add(obj);
                    }
                }
                ArrayList<ElectionPage> arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                for (ElectionPage electionPage : arrayList2) {
                    arrayList3.add(electionPage.getIdImage());
                }
                List<String> filterNotNull = CollectionsKt.filterNotNull(arrayList3);
                if (!filterNotNull.isEmpty()) {
                    formC1Repository2 = GetElectionUseCase.this.formC1Repository;
                    return formC1Repository2.getListFormC1Tabulation(filterNotNull);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1TabulationCompletesSectionIVResource = switchMap3;
        this.formC1TabulationCompletesSectionIV = Transformations.map(switchMap3, new Function1<Resource<List<FormC1TabulationComplete>>, List<FormC1TabulationComplete>>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$formC1TabulationCompletesSectionIV$1
            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1TabulationComplete> invoke(Resource<List<FormC1TabulationComplete>> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        LiveData<Resource<List<FormC1TabulationPartaiComplete>>> switchMap4 = Transformations.switchMap(map, new Function1<ElectionWithRelation, LiveData<Resource<List<FormC1TabulationPartaiComplete>>>>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$formC1TabulationPartaiCompletesSectionIVResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<FormC1TabulationPartaiComplete>>> invoke(ElectionWithRelation electionWithRelation) {
                FormC1Repository formC1Repository2;
                if (electionWithRelation == null) {
                    return AbsentLiveData.Companion.create();
                }
                ArrayList arrayList = new ArrayList();
                for (Object obj : electionWithRelation.getPages()) {
                    if (((ElectionPage) obj).getKind() == 20) {
                        arrayList.add(obj);
                    }
                }
                ArrayList<ElectionPage> arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                for (ElectionPage electionPage : arrayList2) {
                    arrayList3.add(electionPage.getIdImage());
                }
                List<String> filterNotNull = CollectionsKt.filterNotNull(arrayList3);
                if (!filterNotNull.isEmpty()) {
                    formC1Repository2 = GetElectionUseCase.this.formC1Repository;
                    return formC1Repository2.getListFormC1TabulationPartai(filterNotNull);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1TabulationPartaiCompletesSectionIVResource = switchMap4;
        this.formC1TabulationPartaiCompletesSectionIV = Transformations.map(switchMap4, new Function1<Resource<List<FormC1TabulationPartaiComplete>>, List<FormC1TabulationPartaiComplete>>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$formC1TabulationPartaiCompletesSectionIV$1
            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1TabulationPartaiComplete> invoke(Resource<List<FormC1TabulationPartaiComplete>> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        LiveData<Resource<FormC1AdministrationHal2Complete>> switchMap5 = Transformations.switchMap(map, new Function1<ElectionWithRelation, LiveData<Resource<FormC1AdministrationHal2Complete>>>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$formC1TabulationCompleteSectionVResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1AdministrationHal2Complete>> invoke(ElectionWithRelation electionWithRelation) {
                Object obj;
                FormC1Repository formC1Repository2;
                boolean z;
                if (electionWithRelation == null) {
                    return AbsentLiveData.Companion.create();
                }
                Iterator<T> it = electionWithRelation.getPages().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((ElectionPage) obj).getKind() == 30) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        break;
                    }
                }
                ElectionPage electionPage = (ElectionPage) obj;
                if ((electionPage != null ? electionPage.getIdImage() : null) != null) {
                    formC1Repository2 = GetElectionUseCase.this.formC1Repository;
                    String idImage = electionPage.getIdImage();
                    Intrinsics.checkNotNull(idImage);
                    return formC1Repository2.getFormC1AdministrationHal2(idImage, "", false, "");
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1TabulationCompleteSectionVResource = switchMap5;
        this.formC1TabulationCompleteSectionV = Transformations.map(switchMap5, new Function1<Resource<FormC1AdministrationHal2Complete>, FormC1AdministrationHal2Complete>() { // from class: org.informatika.sirekap.ui.GetElectionUseCase$formC1TabulationCompleteSectionV$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1AdministrationHal2Complete invoke(Resource<FormC1AdministrationHal2Complete> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
    }

    public final void setup(String _electionId) {
        Intrinsics.checkNotNullParameter(_electionId, "_electionId");
        if (Intrinsics.areEqual(_electionId, this.electionId.getValue())) {
            return;
        }
        this.electionId.postValue(_electionId);
    }

    public final MutableLiveData<String> getElectionId() {
        return this.electionId;
    }

    public final LiveData<Resource<ElectionWithRelation>> getElectionResource() {
        return this.electionResource;
    }

    public final LiveData<ElectionWithRelation> getElectionWithRelation() {
        return this.electionWithRelation;
    }

    public final LiveData<Election> getElection() {
        return this.election;
    }

    public final LiveData<List<Candidate>> getCandidates() {
        return this.candidates;
    }

    public final LiveData<Resource<FormC1AdministrationComplete>> getFormC1AdministrationCompleteResource() {
        return this.formC1AdministrationCompleteResource;
    }

    public final LiveData<FormC1AdministrationComplete> getFormC1AdministrationComplete() {
        return this.formC1AdministrationComplete;
    }

    public final LiveData<Resource<List<FormC1TabulationComplete>>> getFormC1TabulationCompletesSectionIVResource() {
        return this.formC1TabulationCompletesSectionIVResource;
    }

    public final LiveData<List<FormC1TabulationComplete>> getFormC1TabulationCompletesSectionIV() {
        return this.formC1TabulationCompletesSectionIV;
    }

    public final LiveData<Resource<List<FormC1TabulationPartaiComplete>>> getFormC1TabulationPartaiCompletesSectionIVResource() {
        return this.formC1TabulationPartaiCompletesSectionIVResource;
    }

    public final LiveData<List<FormC1TabulationPartaiComplete>> getFormC1TabulationPartaiCompletesSectionIV() {
        return this.formC1TabulationPartaiCompletesSectionIV;
    }

    public final LiveData<Resource<FormC1AdministrationHal2Complete>> getFormC1TabulationCompleteSectionVResource() {
        return this.formC1TabulationCompleteSectionVResource;
    }

    public final LiveData<FormC1AdministrationHal2Complete> getFormC1TabulationCompleteSectionV() {
        return this.formC1TabulationCompleteSectionV;
    }
}
