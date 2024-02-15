package org.informatika.sirekap.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.repository.TpsTimeRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;

/* compiled from: AddTpsTimeUseCase.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u000eR\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00120\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\t¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/usecase/AddTpsTimeUseCase;", "", "tpsTimeRepository", "Lorg/informatika/sirekap/repository/TpsTimeRepository;", "(Lorg/informatika/sirekap/repository/TpsTimeRepository;)V", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/TpsTime;", "getData", "()Landroidx/lifecycle/LiveData;", "isLoading", "", "request", "Landroidx/lifecycle/MutableLiveData;", "Lorg/informatika/sirekap/usecase/AddTpsTimeRequest;", "getRequest", "()Landroidx/lifecycle/MutableLiveData;", "resource", "Lorg/informatika/sirekap/support/Resource;", "getResource", "finish", "", "submit", "_request", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AddTpsTimeUseCase {
    private final LiveData<TpsTime> data;
    private final LiveData<Boolean> isLoading;
    private final MutableLiveData<AddTpsTimeRequest> request;
    private final LiveData<Resource<TpsTime>> resource;

    public AddTpsTimeUseCase(final TpsTimeRepository tpsTimeRepository) {
        Intrinsics.checkNotNullParameter(tpsTimeRepository, "tpsTimeRepository");
        MutableLiveData<AddTpsTimeRequest> mutableLiveData = new MutableLiveData<>(null);
        this.request = mutableLiveData;
        LiveData<Resource<TpsTime>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<AddTpsTimeRequest, LiveData<Resource<TpsTime>>>() { // from class: org.informatika.sirekap.usecase.AddTpsTimeUseCase$resource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<TpsTime>> invoke(AddTpsTimeRequest addTpsTimeRequest) {
                if (addTpsTimeRequest == null) {
                    return AbsentLiveData.Companion.create();
                }
                return TpsTimeRepository.this.addTpsTime(addTpsTimeRequest.getKodeTps(), addTpsTimeRequest.getStartDate(), addTpsTimeRequest.getStartTime(), addTpsTimeRequest.getEndDate(), addTpsTimeRequest.getEndTime(), addTpsTimeRequest.getJenisWaktu(), addTpsTimeRequest.getJenisPemilihan(), addTpsTimeRequest.getDeviceId(), addTpsTimeRequest.isOffline());
            }
        });
        this.resource = switchMap;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<TpsTime>, Boolean>() { // from class: org.informatika.sirekap.usecase.AddTpsTimeUseCase$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<TpsTime> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.data = Transformations.map(switchMap, new Function1<Resource<TpsTime>, TpsTime>() { // from class: org.informatika.sirekap.usecase.AddTpsTimeUseCase$data$1
            @Override // kotlin.jvm.functions.Function1
            public final TpsTime invoke(Resource<TpsTime> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
    }

    public final MutableLiveData<AddTpsTimeRequest> getRequest() {
        return this.request;
    }

    public final void submit(AddTpsTimeRequest _request) {
        Intrinsics.checkNotNullParameter(_request, "_request");
        this.request.setValue(_request);
    }

    public final void finish() {
        this.request.setValue(null);
    }

    public final LiveData<Resource<TpsTime>> getResource() {
        return this.resource;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final LiveData<TpsTime> getData() {
        return this.data;
    }
}
