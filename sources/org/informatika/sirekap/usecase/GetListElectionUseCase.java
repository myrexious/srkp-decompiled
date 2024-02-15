package org.informatika.sirekap.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.support.Resource;

/* compiled from: GetListElectionUseCase.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000fR\u001f\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lorg/informatika/sirekap/usecase/GetListElectionUseCase;", "", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "(Lorg/informatika/sirekap/repository/ElectionRepository;)V", "elections", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getElections", "()Landroidx/lifecycle/LiveData;", "electionsResource", "Lorg/informatika/sirekap/support/Resource;", "kodeTps", "Landroidx/lifecycle/MutableLiveData;", "", "getKodeTps", "()Landroidx/lifecycle/MutableLiveData;", "setup", "", "_kodeTps", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GetListElectionUseCase {
    private final LiveData<List<ElectionWithRelation>> elections;
    private final LiveData<Resource<List<ElectionWithRelation>>> electionsResource;
    private final MutableLiveData<String> kodeTps;

    public GetListElectionUseCase(final ElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData;
        LiveData<Resource<List<ElectionWithRelation>>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<List<ElectionWithRelation>>>>() { // from class: org.informatika.sirekap.usecase.GetListElectionUseCase$electionsResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<ElectionWithRelation>>> invoke(String str) {
                if (str == null) {
                    return null;
                }
                return ElectionRepository.this.getElectionsByKodeTps(str);
            }
        });
        this.electionsResource = switchMap;
        this.elections = Transformations.map(switchMap, new Function1<Resource<List<ElectionWithRelation>>, List<ElectionWithRelation>>() { // from class: org.informatika.sirekap.usecase.GetListElectionUseCase$elections$1
            @Override // kotlin.jvm.functions.Function1
            public final List<ElectionWithRelation> invoke(Resource<List<ElectionWithRelation>> resource) {
                if (resource == null) {
                    return CollectionsKt.emptyList();
                }
                return resource.getPayload();
            }
        });
    }

    public final void setup(String _kodeTps) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        this.kodeTps.postValue(_kodeTps);
    }

    public final MutableLiveData<String> getKodeTps() {
        return this.kodeTps;
    }

    public final LiveData<List<ElectionWithRelation>> getElections() {
        return this.elections;
    }
}
