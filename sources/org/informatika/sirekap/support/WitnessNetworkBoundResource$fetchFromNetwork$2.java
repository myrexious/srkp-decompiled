package org.informatika.sirekap.support;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import java.util.List;
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

/* compiled from: WitnessNetworkBoundResource.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u0002H\u0003 \u0006*\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "ResultType", "RequestType", "response", "Lorg/informatika/sirekap/api/ApiResponse;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WitnessNetworkBoundResource$fetchFromNetwork$2<RequestType> extends Lambda implements Function1<ApiResponse<RequestType>, Unit> {
    final /* synthetic */ LiveData<ApiResponse<RequestType>> $apiResponse;
    final /* synthetic */ LiveData<ResultType> $dbSource;
    final /* synthetic */ List<Long> $idPilihan;
    final /* synthetic */ List<String> $jenisPemilihan;
    final /* synthetic */ WitnessNetworkBoundResource<ResultType, RequestType> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WitnessNetworkBoundResource$fetchFromNetwork$2(WitnessNetworkBoundResource<ResultType, RequestType> witnessNetworkBoundResource, LiveData<ApiResponse<RequestType>> liveData, LiveData<ResultType> liveData2, List<Long> list, List<String> list2) {
        super(1);
        this.this$0 = witnessNetworkBoundResource;
        this.$apiResponse = liveData;
        this.$dbSource = liveData2;
        this.$idPilihan = list;
        this.$jenisPemilihan = list2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
        invoke((ApiResponse) ((ApiResponse) obj));
        return Unit.INSTANCE;
    }

    public final void invoke(final ApiResponse<RequestType> apiResponse) {
        int i;
        AppExecutors appExecutors;
        int i2;
        AppExecutors appExecutors2;
        AppExecutors appExecutors3;
        ((WitnessNetworkBoundResource) this.this$0).result.removeSource(this.$apiResponse);
        ((WitnessNetworkBoundResource) this.this$0).result.removeSource(this.$dbSource);
        WitnessNetworkBoundResource<ResultType, RequestType> witnessNetworkBoundResource = this.this$0;
        i = ((WitnessNetworkBoundResource) witnessNetworkBoundResource).index;
        ((WitnessNetworkBoundResource) witnessNetworkBoundResource).index = i + 1;
        if (apiResponse instanceof ApiSuccessResponse) {
            i2 = ((WitnessNetworkBoundResource) this.this$0).index;
            if (i2 >= this.$idPilihan.size()) {
                appExecutors3 = ((WitnessNetworkBoundResource) this.this$0).appExecutors;
                Executor diskIO = appExecutors3.diskIO();
                final WitnessNetworkBoundResource<ResultType, RequestType> witnessNetworkBoundResource2 = this.this$0;
                diskIO.execute(new Runnable() { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource$fetchFromNetwork$2$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        WitnessNetworkBoundResource$fetchFromNetwork$2.invoke$lambda$1(WitnessNetworkBoundResource.this, apiResponse);
                    }
                });
                return;
            }
            appExecutors2 = ((WitnessNetworkBoundResource) this.this$0).appExecutors;
            Executor diskIO2 = appExecutors2.diskIO();
            final WitnessNetworkBoundResource<ResultType, RequestType> witnessNetworkBoundResource3 = this.this$0;
            final LiveData<ResultType> liveData = this.$dbSource;
            final List<Long> list = this.$idPilihan;
            final List<String> list2 = this.$jenisPemilihan;
            diskIO2.execute(new Runnable() { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource$fetchFromNetwork$2$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    WitnessNetworkBoundResource$fetchFromNetwork$2.invoke$lambda$3(WitnessNetworkBoundResource.this, apiResponse, liveData, list, list2);
                }
            });
        } else if (apiResponse instanceof ApiEmptyResponse) {
            appExecutors = ((WitnessNetworkBoundResource) this.this$0).appExecutors;
            Executor mainThread = appExecutors.mainThread();
            final WitnessNetworkBoundResource<ResultType, RequestType> witnessNetworkBoundResource4 = this.this$0;
            mainThread.execute(new Runnable() { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource$fetchFromNetwork$2$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    WitnessNetworkBoundResource$fetchFromNetwork$2.invoke$lambda$4(WitnessNetworkBoundResource.this);
                }
            });
        } else if (apiResponse instanceof ApiErrorResponse) {
            Log.wtf("NetworkBoundResource", ((ApiErrorResponse) apiResponse).getError());
            this.this$0.onFetchFailed();
            MediatorLiveData mediatorLiveData = ((WitnessNetworkBoundResource) this.this$0).result;
            Object obj = this.$dbSource;
            final WitnessNetworkBoundResource<ResultType, RequestType> witnessNetworkBoundResource5 = this.this$0;
            mediatorLiveData.addSource(obj, new WitnessNetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>() { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource$fetchFromNetwork$2.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj2) {
                    invoke2((AnonymousClass4<ResultType>) obj2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke */
                public final void invoke2(ResultType resulttype) {
                    witnessNetworkBoundResource5.setValue(Resource.Companion.error(((ApiErrorResponse) apiResponse).getError(), resulttype));
                }
            }));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void invoke$lambda$1(final WitnessNetworkBoundResource this$0, ApiResponse response) {
        AppExecutors appExecutors;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(response, "response");
        this$0.saveCallResult(this$0.processResponse((ApiSuccessResponse) response));
        appExecutors = this$0.appExecutors;
        appExecutors.mainThread().execute(new Runnable() { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource$fetchFromNetwork$2$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                WitnessNetworkBoundResource$fetchFromNetwork$2.invoke$lambda$1$lambda$0(WitnessNetworkBoundResource.this);
            }
        });
    }

    public static final void invoke$lambda$1$lambda$0(final WitnessNetworkBoundResource this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.result.addSource(this$0.loadFromDb(), new WitnessNetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>() { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource$fetchFromNetwork$2$1$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2((WitnessNetworkBoundResource$fetchFromNetwork$2$1$1$1<ResultType>) obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ResultType resulttype) {
                this$0.setValue(Resource.Companion.success(resulttype));
            }
        }));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void invoke$lambda$3(final WitnessNetworkBoundResource this$0, ApiResponse response, final LiveData dbSource, final List idPilihan, final List jenisPemilihan) {
        AppExecutors appExecutors;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dbSource, "$dbSource");
        Intrinsics.checkNotNullParameter(idPilihan, "$idPilihan");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        Intrinsics.checkNotNullExpressionValue(response, "response");
        this$0.saveCallResult(this$0.processResponse((ApiSuccessResponse) response));
        appExecutors = this$0.appExecutors;
        appExecutors.mainThread().execute(new Runnable() { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource$fetchFromNetwork$2$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WitnessNetworkBoundResource$fetchFromNetwork$2.invoke$lambda$3$lambda$2(WitnessNetworkBoundResource.this, dbSource, idPilihan, jenisPemilihan);
            }
        });
    }

    public static final void invoke$lambda$3$lambda$2(WitnessNetworkBoundResource this$0, LiveData dbSource, List idPilihan, List jenisPemilihan) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dbSource, "$dbSource");
        Intrinsics.checkNotNullParameter(idPilihan, "$idPilihan");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        this$0.fetchFromNetwork(dbSource, idPilihan, jenisPemilihan);
    }

    public static final void invoke$lambda$4(final WitnessNetworkBoundResource this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.result.addSource(this$0.loadFromDb(), new WitnessNetworkBoundResource$sam$androidx_lifecycle_Observer$0(new Function1<ResultType, Unit>() { // from class: org.informatika.sirekap.support.WitnessNetworkBoundResource$fetchFromNetwork$2$3$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2((WitnessNetworkBoundResource$fetchFromNetwork$2$3$1<ResultType>) obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ResultType resulttype) {
                this$0.setValue(Resource.Companion.success(resulttype));
            }
        }));
    }
}
