package org.informatika.sirekap.support;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;

/* compiled from: WitnessNetworkBoundResource.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u0000 (*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001(B+\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00100\u0012J$\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00140\u00122\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH%J2\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u0002J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H%J\b\u0010\u001a\u001a\u00020\u0017H\u0014J\u001b\u0010\u001b\u001a\u00028\u00012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u001dH\u0015¢\u0006\u0002\u0010\u001eJ\u0015\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00028\u0001H%¢\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u00172\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0003J\u0017\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00018\u0000H%¢\u0006\u0002\u0010'R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/informatika/sirekap/support/WitnessNetworkBoundResource;", "ResultType", "RequestType", "", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "idPaslon", "", "", "jenisPemilihan", "", "(Lorg/informatika/sirekap/support/AppExecutors;Ljava/util/List;Ljava/util/List;)V", FirebaseAnalytics.Param.INDEX, "", "result", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/informatika/sirekap/support/Resource;", "asLiveData", "Landroidx/lifecycle/LiveData;", "createCall", "Lorg/informatika/sirekap/api/ApiResponse;", "idPilihan", "fetchFromNetwork", "", "dbSource", "loadFromDb", "onFetchFailed", "processResponse", "response", "Lorg/informatika/sirekap/api/ApiSuccessResponse;", "(Lorg/informatika/sirekap/api/ApiSuccessResponse;)Ljava/lang/Object;", "saveCallResult", "item", "(Ljava/lang/Object;)V", "setValue", "newValue", "shouldFetch", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "(Ljava/lang/Object;)Z", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class WitnessNetworkBoundResource<ResultType, RequestType> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "NetworkBoundResource";
    private final AppExecutors appExecutors;
    private final List<Long> idPaslon;
    private int index;
    private final List<String> jenisPemilihan;
    private final MediatorLiveData<Resource<ResultType>> result;

    protected abstract LiveData<ApiResponse<RequestType>> createCall(long j, String str);

    public abstract LiveData<ResultType> loadFromDb();

    public void onFetchFailed() {
    }

    public abstract void saveCallResult(RequestType requesttype);

    protected abstract boolean shouldFetch(ResultType resulttype);

    public WitnessNetworkBoundResource(AppExecutors appExecutors, List<Long> idPaslon, List<String> jenisPemilihan) {
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(idPaslon, "idPaslon");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        this.appExecutors = appExecutors;
        this.idPaslon = idPaslon;
        this.jenisPemilihan = jenisPemilihan;
        MediatorLiveData<Resource<ResultType>> mediatorLiveData = new MediatorLiveData<>();
        this.result = mediatorLiveData;
        mediatorLiveData.setValue(Resource.Companion.loading(null));
        final LiveData<ResultType> loadFromDb = loadFromDb();
        mediatorLiveData.addSource(loadFromDb, new WitnessNetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>(this) { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource.1
            final /* synthetic */ WitnessNetworkBoundResource<ResultType, RequestType> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2((AnonymousClass1) obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke */
            public final void invoke2(ResultType resulttype) {
                ((WitnessNetworkBoundResource) this.this$0).result.removeSource(loadFromDb);
                if (!this.this$0.shouldFetch(resulttype)) {
                    MediatorLiveData mediatorLiveData2 = ((WitnessNetworkBoundResource) this.this$0).result;
                    LiveData<ResultType> liveData = loadFromDb;
                    final WitnessNetworkBoundResource<ResultType, RequestType> witnessNetworkBoundResource = this.this$0;
                    mediatorLiveData2.addSource(liveData, new WitnessNetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>() { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                            invoke2((C00221) obj);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke */
                        public final void invoke2(ResultType resulttype2) {
                            witnessNetworkBoundResource.setValue(Resource.Companion.success(resulttype2));
                        }
                    }));
                    return;
                }
                WitnessNetworkBoundResource<ResultType, RequestType> witnessNetworkBoundResource2 = this.this$0;
                witnessNetworkBoundResource2.fetchFromNetwork(loadFromDb, ((WitnessNetworkBoundResource) witnessNetworkBoundResource2).idPaslon, ((WitnessNetworkBoundResource) this.this$0).jenisPemilihan);
            }
        }));
    }

    public final void setValue(Resource<? extends ResultType> resource) {
        if (Intrinsics.areEqual(this.result.getValue(), resource)) {
            return;
        }
        this.result.setValue(resource);
    }

    public final void fetchFromNetwork(LiveData<ResultType> liveData, List<Long> list, List<String> list2) {
        Log.wtf("TAG", "✅ fetchFromNetwork " + list + StringUtils.SPACE + list2);
        LiveData<ApiResponse<RequestType>> createCall = createCall(list.get(this.index).longValue(), list2.get(this.index));
        this.result.addSource(liveData, new WitnessNetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>(this) { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource$fetchFromNetwork$1
            final /* synthetic */ WitnessNetworkBoundResource<ResultType, RequestType> this$0;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2((WitnessNetworkBoundResource$fetchFromNetwork$1<ResultType>) obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ResultType resulttype) {
                this.this$0.setValue(Resource.Companion.loading(resulttype));
            }
        }));
        this.result.addSource(createCall, new WitnessNetworkBoundResource$sam$androidx_lifecycle_Observer$0(new WitnessNetworkBoundResource$fetchFromNetwork$2(this, createCall, liveData, list, list2)));
    }

    public final LiveData<Resource<ResultType>> asLiveData() {
        MediatorLiveData<Resource<ResultType>> mediatorLiveData = this.result;
        Intrinsics.checkNotNull(mediatorLiveData, "null cannot be cast to non-null type androidx.lifecycle.LiveData<org.informatika.sirekap.support.Resource<ResultType of org.informatika.sirekap.support.WitnessNetworkBoundResource>?>");
        return mediatorLiveData;
    }

    public RequestType processResponse(ApiSuccessResponse<RequestType> response) {
        Intrinsics.checkNotNullParameter(response, "response");
        return response.getBody();
    }

    /* compiled from: WitnessNetworkBoundResource.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/support/WitnessNetworkBoundResource$Companion;", "", "()V", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
