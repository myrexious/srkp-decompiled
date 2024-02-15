package org.informatika.sirekap.ui.dashboard;

import android.app.Activity;
import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DashboardViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.dashboard.DashboardViewModel$savePhoto$1", f = "DashboardViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class DashboardViewModel$savePhoto$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ String $jenisPemilihan;
    final /* synthetic */ String $kodeTps;
    final /* synthetic */ Function1<Exception, Unit> $onError;
    final /* synthetic */ Function1<Bitmap, Unit> $onSuccess;
    int label;
    final /* synthetic */ DashboardViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DashboardViewModel$savePhoto$1(DashboardViewModel dashboardViewModel, Activity activity, String str, String str2, Function1<? super Bitmap, Unit> function1, Function1<? super Exception, Unit> function12, Continuation<? super DashboardViewModel$savePhoto$1> continuation) {
        super(2, continuation);
        this.this$0 = dashboardViewModel;
        this.$activity = activity;
        this.$kodeTps = str;
        this.$jenisPemilihan = str2;
        this.$onSuccess = function1;
        this.$onError = function12;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashboardViewModel$savePhoto$1(this.this$0, this.$activity, this.$kodeTps, this.$jenisPemilihan, this.$onSuccess, this.$onError, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashboardViewModel$savePhoto$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.getCaptureImageUseCase().saveOriginalPhoto(this.$activity, this.$kodeTps, this.$jenisPemilihan, this.$onSuccess, this.$onError);
        return Unit.INSTANCE;
    }
}
