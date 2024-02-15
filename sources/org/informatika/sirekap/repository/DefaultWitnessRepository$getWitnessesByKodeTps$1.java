package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;
import org.informatika.sirekap.api.response.EmptyApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.NetworkBoundResource;

/* compiled from: WitnessRepository.kt */
@Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00070\u0006H\u0014J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0006H\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0014J\u0018\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0014¨\u0006\u000f"}, d2 = {"org/informatika/sirekap/repository/DefaultWitnessRepository$getWitnessesByKodeTps$1", "Lorg/informatika/sirekap/support/NetworkBoundResource;", "", "Lorg/informatika/sirekap/model/WitnessWithShare;", "Lorg/informatika/sirekap/api/response/EmptyApiResponse;", "createCall", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/api/ApiResponse;", "loadFromDb", "saveCallResult", "", "item", "shouldFetch", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultWitnessRepository$getWitnessesByKodeTps$1 extends NetworkBoundResource<List<? extends WitnessWithShare>, EmptyApiResponse> {
    final /* synthetic */ String $kodeTps;
    final /* synthetic */ DefaultWitnessRepository this$0;

    public static final void saveCallResult$lambda$1$lambda$0() {
    }

    /* renamed from: shouldFetch */
    protected boolean shouldFetch2(List<WitnessWithShare> list) {
        return true;
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public /* bridge */ /* synthetic */ boolean shouldFetch(List<? extends WitnessWithShare> list) {
        return shouldFetch2((List<WitnessWithShare>) list);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultWitnessRepository$getWitnessesByKodeTps$1(DefaultWitnessRepository defaultWitnessRepository, String str, AppExecutors appExecutors) {
        super(appExecutors);
        this.this$0 = defaultWitnessRepository;
        this.$kodeTps = str;
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public void saveCallResult(EmptyApiResponse item) {
        AppDatabase appDatabase;
        Intrinsics.checkNotNullParameter(item, "item");
        item.getData();
        appDatabase = this.this$0.database;
        appDatabase.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$getWitnessesByKodeTps$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository$getWitnessesByKodeTps$1.saveCallResult$lambda$1$lambda$0();
            }
        });
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public LiveData<List<? extends WitnessWithShare>> loadFromDb() {
        AppDatabase appDatabase;
        appDatabase = this.this$0.database;
        return appDatabase.witnessDao().getAllByKodeTps(this.$kodeTps);
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
        return new MutableLiveData(new ApiSuccessResponse(new EmptyApiResponse(true, "", "", null), ""));
    }
}
