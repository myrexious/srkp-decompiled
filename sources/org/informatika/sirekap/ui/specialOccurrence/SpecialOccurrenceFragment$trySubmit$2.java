package org.informatika.sirekap.ui.specialOccurrence;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.ui.BaseFragment;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SpecialOccurrenceFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment$trySubmit$2", f = "SpecialOccurrenceFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class SpecialOccurrenceFragment$trySubmit$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SpecialOccurrenceFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpecialOccurrenceFragment$trySubmit$2(SpecialOccurrenceFragment specialOccurrenceFragment, Continuation<? super SpecialOccurrenceFragment$trySubmit$2> continuation) {
        super(2, continuation);
        this.this$0 = specialOccurrenceFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SpecialOccurrenceFragment$trySubmit$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SpecialOccurrenceFragment$trySubmit$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BaseFragment.showSnackBar$default(this.this$0, "Memulai pengiriman Tanda Terima Salinan Saksi dan Kejadian Khusus", null, null, 6, null);
        return Unit.INSTANCE;
    }
}
