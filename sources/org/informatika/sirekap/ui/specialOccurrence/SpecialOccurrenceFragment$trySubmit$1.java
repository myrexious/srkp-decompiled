package org.informatika.sirekap.ui.specialOccurrence;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: SpecialOccurrenceFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment", f = "SpecialOccurrenceFragment.kt", i = {0, 0, 0}, l = {331, 346}, m = "trySubmit", n = {"this", "specialOccurrence", OperatorName.SET_FLATNESS}, s = {"L$0", "L$1", "I$0"})
/* loaded from: classes4.dex */
public final class SpecialOccurrenceFragment$trySubmit$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SpecialOccurrenceFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpecialOccurrenceFragment$trySubmit$1(SpecialOccurrenceFragment specialOccurrenceFragment, Continuation<? super SpecialOccurrenceFragment$trySubmit$1> continuation) {
        super(continuation);
        this.this$0 = specialOccurrenceFragment;
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
