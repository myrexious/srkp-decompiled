package org.informatika.sirekap.support.livedata;

import androidx.lifecycle.LiveData;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* compiled from: LiveDataCallAdapterFactory.kt */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001J\b\u0010\u0005\u001a\u00020\u0006H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"org/informatika/sirekap/support/livedata/LiveDataCallAdapterFactory$LiveDataCallAdapter$adapt$1", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/api/ApiResponse;", "started", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onActive", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LiveDataCallAdapterFactory$LiveDataCallAdapter$adapt$1<R> extends LiveData<ApiResponse<R>> {
    final /* synthetic */ Call<R> $call;
    private AtomicBoolean started = new AtomicBoolean(false);

    public LiveDataCallAdapterFactory$LiveDataCallAdapter$adapt$1(Call<R> call) {
        this.$call = call;
    }

    @Override // androidx.lifecycle.LiveData
    public void onActive() {
        super.onActive();
        if (this.started.compareAndSet(false, true)) {
            this.$call.enqueue(new Callback<R>(this) { // from class: org.informatika.sirekap.support.livedata.LiveDataCallAdapterFactory$LiveDataCallAdapter$adapt$1$onActive$1
                final /* synthetic */ LiveDataCallAdapterFactory$LiveDataCallAdapter$adapt$1<R> this$0;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.this$0 = this;
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<R> call, Response<R> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    this.this$0.postValue(ApiResponse.Companion.create(response));
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<R> call, Throwable throwable) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(throwable, "throwable");
                    this.this$0.postValue(ApiResponse.Companion.create(throwable));
                }
            });
        }
    }
}
