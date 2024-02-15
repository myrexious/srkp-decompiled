package org.informatika.sirekap.support;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;

/* compiled from: NetworkBoundResource.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u0000  *\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001 B\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0014\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r0\u000bH%J\u0016\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0002J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH%J\b\u0010\u0012\u001a\u00020\u000fH\u0014J\u001b\u0010\u0013\u001a\u00028\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u0015H\u0015¢\u0006\u0002\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00028\u0001H%¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u000f2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0003J\u0017\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00018\u0000H%¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/informatika/sirekap/support/NetworkBoundResource;", "ResultType", "RequestType", "", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "(Lorg/informatika/sirekap/support/AppExecutors;)V", "result", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/informatika/sirekap/support/Resource;", "asLiveData", "Landroidx/lifecycle/LiveData;", "createCall", "Lorg/informatika/sirekap/api/ApiResponse;", "fetchFromNetwork", "", "dbSource", "loadFromDb", "onFetchFailed", "processResponse", "response", "Lorg/informatika/sirekap/api/ApiSuccessResponse;", "(Lorg/informatika/sirekap/api/ApiSuccessResponse;)Ljava/lang/Object;", "saveCallResult", "item", "(Ljava/lang/Object;)V", "setValue", "newValue", "shouldFetch", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "(Ljava/lang/Object;)Z", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class NetworkBoundResource<ResultType, RequestType> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "NetworkBoundResource";
    private final AppExecutors appExecutors;
    private final MediatorLiveData<Resource<ResultType>> result;

    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    public abstract LiveData<ResultType> loadFromDb();

    public void onFetchFailed() {
    }

    public abstract void saveCallResult(RequestType requesttype);

    protected abstract boolean shouldFetch(ResultType resulttype);

    public NetworkBoundResource(AppExecutors appExecutors) {
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        this.appExecutors = appExecutors;
        MediatorLiveData<Resource<ResultType>> mediatorLiveData = new MediatorLiveData<>();
        this.result = mediatorLiveData;
        mediatorLiveData.setValue(Resource.Companion.loading(null));
        final LiveData<ResultType> loadFromDb = loadFromDb();
        mediatorLiveData.addSource(loadFromDb, new NetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>(this) { // from class: org.informatika.sirekap.support.NetworkBoundResource.1
            final /* synthetic */ NetworkBoundResource<ResultType, RequestType> this$0;

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
                ((NetworkBoundResource) this.this$0).result.removeSource(loadFromDb);
                if (this.this$0.shouldFetch(resulttype)) {
                    this.this$0.fetchFromNetwork(loadFromDb);
                    return;
                }
                MediatorLiveData mediatorLiveData2 = ((NetworkBoundResource) this.this$0).result;
                LiveData<ResultType> liveData = loadFromDb;
                final NetworkBoundResource<ResultType, RequestType> networkBoundResource = this.this$0;
                mediatorLiveData2.addSource(liveData, new NetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>() { // from class: org.informatika.sirekap.support.NetworkBoundResource.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                        invoke2((C00211) obj);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke */
                    public final void invoke2(ResultType resulttype2) {
                        networkBoundResource.setValue(Resource.Companion.success(resulttype2));
                    }
                }));
            }
        }));
    }

    public final void setValue(Resource<? extends ResultType> resource) {
        if (Intrinsics.areEqual(this.result.getValue(), resource)) {
            return;
        }
        this.result.setValue(resource);
    }

    public final void fetchFromNetwork(LiveData<ResultType> liveData) {
        LiveData<ApiResponse<RequestType>> createCall = createCall();
        this.result.addSource(liveData, new NetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>(this) { // from class: org.informatika.sirekap.support.NetworkBoundResource$fetchFromNetwork$1
            final /* synthetic */ NetworkBoundResource<ResultType, RequestType> this$0;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2((NetworkBoundResource$fetchFromNetwork$1<ResultType>) obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ResultType resulttype) {
                this.this$0.setValue(Resource.Companion.loading(resulttype));
            }
        }));
        this.result.addSource(createCall, new NetworkBoundResource$sam$androidx_lifecycle_Observer$0(new NetworkBoundResource$fetchFromNetwork$2(this, createCall, liveData)));
    }

    public final LiveData<Resource<ResultType>> asLiveData() {
        MediatorLiveData<Resource<ResultType>> mediatorLiveData = this.result;
        Intrinsics.checkNotNull(mediatorLiveData, "null cannot be cast to non-null type androidx.lifecycle.LiveData<org.informatika.sirekap.support.Resource<ResultType of org.informatika.sirekap.support.NetworkBoundResource>?>");
        return mediatorLiveData;
    }

    public RequestType processResponse(ApiSuccessResponse<RequestType> response) {
        Intrinsics.checkNotNullParameter(response, "response");
        return response.getBody();
    }

    /* compiled from: NetworkBoundResource.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/support/NetworkBoundResource$Companion;", "", "()V", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
