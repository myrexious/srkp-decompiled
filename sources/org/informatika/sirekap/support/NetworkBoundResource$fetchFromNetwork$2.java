package org.informatika.sirekap.support;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.informatika.sirekap.api.ApiEmptyResponse;
import org.informatika.sirekap.api.ApiErrorResponse;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;

/* compiled from: NetworkBoundResource.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u0002H\u0003 \u0006*\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "ResultType", "RequestType", "response", "Lorg/informatika/sirekap/api/ApiResponse;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkBoundResource$fetchFromNetwork$2<RequestType> extends Lambda implements Function1<ApiResponse<RequestType>, Unit> {
    final /* synthetic */ LiveData<ApiResponse<RequestType>> $apiResponse;
    final /* synthetic */ LiveData<ResultType> $dbSource;
    final /* synthetic */ NetworkBoundResource<ResultType, RequestType> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkBoundResource$fetchFromNetwork$2(NetworkBoundResource<ResultType, RequestType> networkBoundResource, LiveData<ApiResponse<RequestType>> liveData, LiveData<ResultType> liveData2) {
        super(1);
        this.this$0 = networkBoundResource;
        this.$apiResponse = liveData;
        this.$dbSource = liveData2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
        invoke((ApiResponse) ((ApiResponse) obj));
        return Unit.INSTANCE;
    }

    public final void invoke(final ApiResponse<RequestType> apiResponse) {
        AppExecutors appExecutors;
        AppExecutors appExecutors2;
        ((NetworkBoundResource) this.this$0).result.removeSource(this.$apiResponse);
        ((NetworkBoundResource) this.this$0).result.removeSource(this.$dbSource);
        if (apiResponse instanceof ApiSuccessResponse) {
            appExecutors2 = ((NetworkBoundResource) this.this$0).appExecutors;
            Executor diskIO = appExecutors2.diskIO();
            final NetworkBoundResource<ResultType, RequestType> networkBoundResource = this.this$0;
            diskIO.execute(new Runnable() { // from class: org.informatika.sirekap.support.NetworkBoundResource$fetchFromNetwork$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    NetworkBoundResource$fetchFromNetwork$2.invoke$lambda$1(NetworkBoundResource.this, apiResponse);
                }
            });
        } else if (apiResponse instanceof ApiEmptyResponse) {
            appExecutors = ((NetworkBoundResource) this.this$0).appExecutors;
            Executor mainThread = appExecutors.mainThread();
            final NetworkBoundResource<ResultType, RequestType> networkBoundResource2 = this.this$0;
            mainThread.execute(new Runnable() { // from class: org.informatika.sirekap.support.NetworkBoundResource$fetchFromNetwork$2$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    NetworkBoundResource$fetchFromNetwork$2.invoke$lambda$2(NetworkBoundResource.this);
                }
            });
        } else if (apiResponse instanceof ApiErrorResponse) {
            Log.wtf("NetworkBoundResource", ((ApiErrorResponse) apiResponse).getError());
            this.this$0.onFetchFailed();
            MediatorLiveData mediatorLiveData = ((NetworkBoundResource) this.this$0).result;
            Object obj = this.$dbSource;
            final NetworkBoundResource<ResultType, RequestType> networkBoundResource3 = this.this$0;
            mediatorLiveData.addSource(obj, new NetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>() { // from class: org.informatika.sirekap.support.NetworkBoundResource$fetchFromNetwork$2.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj2) {
                    invoke2((AnonymousClass3<ResultType>) obj2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke */
                public final void invoke2(ResultType resulttype) {
                    networkBoundResource3.setValue(Resource.Companion.error(((ApiErrorResponse) apiResponse).getError(), resulttype));
                }
            }));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void invoke$lambda$1(final NetworkBoundResource this$0, ApiResponse response) {
        AppExecutors appExecutors;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(response, "response");
        this$0.saveCallResult(this$0.processResponse((ApiSuccessResponse) response));
        appExecutors = this$0.appExecutors;
        appExecutors.mainThread().execute(new Runnable() { // from class: org.informatika.sirekap.support.NetworkBoundResource$fetchFromNetwork$2$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                NetworkBoundResource$fetchFromNetwork$2.invoke$lambda$1$lambda$0(NetworkBoundResource.this);
            }
        });
    }

    public static final void invoke$lambda$1$lambda$0(final NetworkBoundResource this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.result.addSource(this$0.loadFromDb(), new NetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>() { // from class: org.informatika.sirekap.support.NetworkBoundResource$fetchFromNetwork$2$1$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2((NetworkBoundResource$fetchFromNetwork$2$1$1$1<ResultType>) obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ResultType resulttype) {
                this$0.setValue(Resource.Companion.success(resulttype));
            }
        }));
    }

    public static final void invoke$lambda$2(final NetworkBoundResource this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.result.addSource(this$0.loadFromDb(), new NetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>() { // from class: org.informatika.sirekap.support.NetworkBoundResource$fetchFromNetwork$2$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2((NetworkBoundResource$fetchFromNetwork$2$2$1<ResultType>) obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ResultType resulttype) {
                this$0.setValue(Resource.Companion.success(resulttype));
            }
        }));
    }
}
