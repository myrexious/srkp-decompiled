package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.messaging.Constants;
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
@Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0014\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00060\u0005H\u0014J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0014J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H\u0014J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0014¨\u0006\u000e"}, d2 = {"org/informatika/sirekap/repository/DefaultWitnessRepository$markAsShared$1", "Lorg/informatika/sirekap/support/NetworkBoundResource;", "Lorg/informatika/sirekap/model/WitnessWithShare;", "Lorg/informatika/sirekap/api/response/EmptyApiResponse;", "createCall", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/api/ApiResponse;", "loadFromDb", "saveCallResult", "", "item", "shouldFetch", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultWitnessRepository$markAsShared$1 extends NetworkBoundResource<WitnessWithShare, EmptyApiResponse> {
    final /* synthetic */ String $jenisPemilihan;
    final /* synthetic */ String $noHandphone;
    final /* synthetic */ DefaultWitnessRepository this$0;

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public boolean shouldFetch(WitnessWithShare witnessWithShare) {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultWitnessRepository$markAsShared$1(DefaultWitnessRepository defaultWitnessRepository, String str, String str2, AppExecutors appExecutors) {
        super(appExecutors);
        this.this$0 = defaultWitnessRepository;
        this.$noHandphone = str;
        this.$jenisPemilihan = str2;
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public void saveCallResult(EmptyApiResponse item) {
        AppDatabase appDatabase;
        Intrinsics.checkNotNullParameter(item, "item");
        appDatabase = this.this$0.database;
        final DefaultWitnessRepository defaultWitnessRepository = this.this$0;
        final String str = this.$noHandphone;
        final String str2 = this.$jenisPemilihan;
        appDatabase.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$markAsShared$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository$markAsShared$1.saveCallResult$lambda$0(DefaultWitnessRepository.this, str, str2);
            }
        });
    }

    public static final void saveCallResult$lambda$0(DefaultWitnessRepository this$0, String noHandphone, String jenisPemilihan) {
        AppDatabase appDatabase;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noHandphone, "$noHandphone");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        appDatabase = this$0.database;
        appDatabase.witnessDao().markAsSharedNoHandphone(noHandphone, jenisPemilihan);
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public LiveData<WitnessWithShare> loadFromDb() {
        AppDatabase appDatabase;
        appDatabase = this.this$0.database;
        return appDatabase.witnessDao().getByNoHandphone(this.$noHandphone);
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
        return new MutableLiveData(new ApiSuccessResponse(new EmptyApiResponse(true, "", "", null), ""));
    }
}
