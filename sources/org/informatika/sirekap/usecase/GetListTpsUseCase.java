package org.informatika.sirekap.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;

/* compiled from: GetListTpsUseCase.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR\u001c\u0010\u0014\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00150\u00150\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\u0017\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u00180\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\n¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/usecase/GetListTpsUseCase;", "", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "(Lorg/informatika/sirekap/repository/AuthRepository;)V", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/Tps;", "getData", "()Landroidx/lifecycle/LiveData;", "errorMessage", "", "getErrorMessage", "idWilayah", "Landroidx/lifecycle/MutableLiveData;", "isError", "", "isLoading", "isSuccess", "refreshCount", "", "kotlin.jvm.PlatformType", "resource", "Lorg/informatika/sirekap/support/Resource;", "getResource", "refresh", "", "setup", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GetListTpsUseCase {
    private final AuthRepository authRepository;
    private final LiveData<List<Tps>> data;
    private final LiveData<String> errorMessage;
    private final MutableLiveData<String> idWilayah;
    private final LiveData<Boolean> isError;
    private final LiveData<Boolean> isLoading;
    private final LiveData<Boolean> isSuccess;
    private final MutableLiveData<Integer> refreshCount;
    private final LiveData<Resource<List<Tps>>> resource;

    public GetListTpsUseCase(AuthRepository authRepository) {
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        this.authRepository = authRepository;
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(0);
        this.refreshCount = mutableLiveData;
        this.idWilayah = new MutableLiveData<>(null);
        LiveData<Resource<List<Tps>>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<Integer, LiveData<Resource<List<Tps>>>>() { // from class: org.informatika.sirekap.usecase.GetListTpsUseCase$resource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<List<Tps>>> invoke(Integer it) {
                MutableLiveData mutableLiveData2;
                AuthRepository authRepository2;
                MutableLiveData mutableLiveData3;
                mutableLiveData2 = GetListTpsUseCase.this.idWilayah;
                if (mutableLiveData2.getValue() != 0) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    if (it.intValue() >= 0) {
                        authRepository2 = GetListTpsUseCase.this.authRepository;
                        mutableLiveData3 = GetListTpsUseCase.this.idWilayah;
                        T value = mutableLiveData3.getValue();
                        Intrinsics.checkNotNull(value);
                        return authRepository2.getAllTps((String) value);
                    }
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.resource = switchMap;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<List<Tps>>, Boolean>() { // from class: org.informatika.sirekap.usecase.GetListTpsUseCase$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<List<Tps>> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.isError = Transformations.map(switchMap, new Function1<Resource<List<Tps>>, Boolean>() { // from class: org.informatika.sirekap.usecase.GetListTpsUseCase$isError$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<List<Tps>> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.ERROR);
            }
        });
        this.errorMessage = Transformations.map(switchMap, new Function1<Resource<List<Tps>>, String>() { // from class: org.informatika.sirekap.usecase.GetListTpsUseCase$errorMessage$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(Resource<List<Tps>> resource) {
                if (resource != null) {
                    return resource.getError();
                }
                return null;
            }
        });
        this.isSuccess = Transformations.map(switchMap, new Function1<Resource<List<Tps>>, Boolean>() { // from class: org.informatika.sirekap.usecase.GetListTpsUseCase$isSuccess$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<List<Tps>> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.SUCCESS);
            }
        });
        this.data = Transformations.map(switchMap, new Function1<Resource<List<Tps>>, List<Tps>>() { // from class: org.informatika.sirekap.usecase.GetListTpsUseCase$data$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Tps> invoke(Resource<List<Tps>> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
    }

    public final void setup() {
        MutableLiveData<String> mutableLiveData = this.idWilayah;
        ActiveProfile activeProfile = this.authRepository.getActiveProfile();
        mutableLiveData.postValue(activeProfile != null ? activeProfile.getKodeWilayah() : null);
        refresh();
    }

    public final void refresh() {
        Integer value = this.refreshCount.getValue();
        Intrinsics.checkNotNull(value);
        this.refreshCount.postValue(Integer.valueOf(value.intValue() + 1));
    }

    public final LiveData<Resource<List<Tps>>> getResource() {
        return this.resource;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final LiveData<Boolean> isError() {
        return this.isError;
    }

    public final LiveData<String> getErrorMessage() {
        return this.errorMessage;
    }

    public final LiveData<Boolean> isSuccess() {
        return this.isSuccess;
    }

    public final LiveData<List<Tps>> getData() {
        return this.data;
    }
}
