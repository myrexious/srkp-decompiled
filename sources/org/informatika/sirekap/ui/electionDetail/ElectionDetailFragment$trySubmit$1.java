package org.informatika.sirekap.ui.electionDetail;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: ElectionDetailFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment", f = "ElectionDetailFragment.kt", i = {0, 0, 0, 0}, l = {342, 390}, m = "trySubmit", n = {"this", "election", "isOffline", OperatorName.SET_FLATNESS}, s = {"L$0", "L$1", "I$0", "I$1"})
/* loaded from: classes4.dex */
public final class ElectionDetailFragment$trySubmit$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ElectionDetailFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElectionDetailFragment$trySubmit$1(ElectionDetailFragment electionDetailFragment, Continuation<? super ElectionDetailFragment$trySubmit$1> continuation) {
        super(continuation);
        this.this$0 = electionDetailFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object trySubmit;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        trySubmit = this.this$0.trySubmit(this);
        return trySubmit;
    }
}
