package org.informatika.sirekap.ui.witness.attendanceList;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.repository.DefaultWitnessRepository;

/* compiled from: WitnessAttendanceListViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel$finishUploadPdfOffline$1", f = "WitnessAttendanceListViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class WitnessAttendanceListViewModel$finishUploadPdfOffline$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $kodeTps;
    int label;
    final /* synthetic */ WitnessAttendanceListViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WitnessAttendanceListViewModel$finishUploadPdfOffline$1(WitnessAttendanceListViewModel witnessAttendanceListViewModel, String str, Continuation<? super WitnessAttendanceListViewModel$finishUploadPdfOffline$1> continuation) {
        super(2, continuation);
        this.this$0 = witnessAttendanceListViewModel;
        this.$kodeTps = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WitnessAttendanceListViewModel$finishUploadPdfOffline$1(this.this$0, this.$kodeTps, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WitnessAttendanceListViewModel$finishUploadPdfOffline$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        DefaultWitnessRepository defaultWitnessRepository;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            defaultWitnessRepository = this.this$0.witnessRepository;
            defaultWitnessRepository.finishUploadPdf(this.$kodeTps, true);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
