package org.informatika.sirekap.ui.sendImage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.livedata.AbsentLiveData;

/* compiled from: GetElectionPageUseCase.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u000fR\u001f\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00190\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\nR\u001c\u0010\u001d\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u00190\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\nR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\n¨\u0006&"}, d2 = {"Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "(Lorg/informatika/sirekap/repository/ElectionRepository;)V", "candidates", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/Candidate;", "getCandidates", "()Landroidx/lifecycle/LiveData;", "election", "Lorg/informatika/sirekap/model/Election;", "getElection", "electionId", "", "getElectionId", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "getElectionPage", "electionPageId", "Landroidx/lifecycle/MutableLiveData;", "getElectionPageId", "()Landroidx/lifecycle/MutableLiveData;", "electionPageResource", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/ElectionPageWithRelation;", "electionPageWithRelation", "getElectionPageWithRelation", "electionResource", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "electionWithRelation", "getElectionWithRelation", "isPhotoLimitReached", "", "setup", "", "_electionPageId", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GetElectionPageUseCase {
    private final LiveData<List<Candidate>> candidates;
    private final LiveData<Election> election;
    private final LiveData<String> electionId;
    private final LiveData<ElectionPage> electionPage;
    private final MutableLiveData<String> electionPageId;
    private final LiveData<Resource<ElectionPageWithRelation>> electionPageResource;
    private final LiveData<ElectionPageWithRelation> electionPageWithRelation;
    private final LiveData<Resource<ElectionWithRelation>> electionResource;
    private final LiveData<ElectionWithRelation> electionWithRelation;
    private final LiveData<Boolean> isPhotoLimitReached;

    public GetElectionPageUseCase(final ElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.electionPageId = mutableLiveData;
        LiveData<Resource<ElectionPageWithRelation>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<ElectionPageWithRelation>>>() { // from class: org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase$electionPageResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<ElectionPageWithRelation>> invoke(String str) {
                if (str == null) {
                    return AbsentLiveData.Companion.create();
                }
                return ElectionRepository.this.getElectionPageById(str);
            }
        });
        this.electionPageResource = switchMap;
        LiveData<ElectionPageWithRelation> map = Transformations.map(switchMap, new Function1<Resource<ElectionPageWithRelation>, ElectionPageWithRelation>() { // from class: org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase$electionPageWithRelation$1
            @Override // kotlin.jvm.functions.Function1
            public final ElectionPageWithRelation invoke(Resource<ElectionPageWithRelation> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.electionPageWithRelation = map;
        LiveData<ElectionPage> map2 = Transformations.map(map, new Function1<ElectionPageWithRelation, ElectionPage>() { // from class: org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase$electionPage$1
            @Override // kotlin.jvm.functions.Function1
            public final ElectionPage invoke(ElectionPageWithRelation electionPageWithRelation) {
                if (electionPageWithRelation != null) {
                    return electionPageWithRelation.getElectionPage();
                }
                return null;
            }
        });
        this.electionPage = map2;
        this.isPhotoLimitReached = Transformations.map(map2, new Function1<ElectionPage, Boolean>() { // from class: org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase$isPhotoLimitReached$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ElectionPage electionPage) {
                boolean z = false;
                if (electionPage != null && electionPage.getTakePhotoAttempt() >= 5) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
        LiveData<String> map3 = Transformations.map(map2, new Function1<ElectionPage, String>() { // from class: org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase$electionId$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(ElectionPage electionPage) {
                if (electionPage != null) {
                    return electionPage.getElectionId();
                }
                return null;
            }
        });
        this.electionId = map3;
        LiveData<Resource<ElectionWithRelation>> switchMap2 = Transformations.switchMap(map3, new Function1<String, LiveData<Resource<ElectionWithRelation>>>() { // from class: org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase$electionResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<ElectionWithRelation>> invoke(String str) {
                if (str == null) {
                    return AbsentLiveData.Companion.create();
                }
                return ElectionRepository.this.getElectionById(str);
            }
        });
        this.electionResource = switchMap2;
        LiveData<ElectionWithRelation> map4 = Transformations.map(switchMap2, new Function1<Resource<ElectionWithRelation>, ElectionWithRelation>() { // from class: org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase$electionWithRelation$1
            @Override // kotlin.jvm.functions.Function1
            public final ElectionWithRelation invoke(Resource<ElectionWithRelation> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.electionWithRelation = map4;
        this.election = Transformations.map(map4, new Function1<ElectionWithRelation, Election>() { // from class: org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase$election$1
            @Override // kotlin.jvm.functions.Function1
            public final Election invoke(ElectionWithRelation electionWithRelation) {
                if (electionWithRelation != null) {
                    return electionWithRelation.getElection();
                }
                return null;
            }
        });
        this.candidates = Transformations.map(map4, new Function1<ElectionWithRelation, List<Candidate>>() { // from class: org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase$candidates$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Candidate> invoke(ElectionWithRelation electionWithRelation) {
                if (electionWithRelation != null) {
                    return electionWithRelation.getCandidates();
                }
                return null;
            }
        });
    }

    public final MutableLiveData<String> getElectionPageId() {
        return this.electionPageId;
    }

    public final LiveData<ElectionPageWithRelation> getElectionPageWithRelation() {
        return this.electionPageWithRelation;
    }

    public final LiveData<ElectionPage> getElectionPage() {
        return this.electionPage;
    }

    public final LiveData<Boolean> isPhotoLimitReached() {
        return this.isPhotoLimitReached;
    }

    public final LiveData<String> getElectionId() {
        return this.electionId;
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

    public final void setup(String _electionPageId) {
        Intrinsics.checkNotNullParameter(_electionPageId, "_electionPageId");
        if (Intrinsics.areEqual(this.electionPageId.getValue(), _electionPageId)) {
            return;
        }
        this.electionPageId.postValue(_electionPageId);
    }
}
