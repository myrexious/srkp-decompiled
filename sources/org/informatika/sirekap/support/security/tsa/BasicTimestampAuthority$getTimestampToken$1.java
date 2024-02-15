package org.informatika.sirekap.support.security.tsa;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: BasicTimestampAuthority.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority", f = "BasicTimestampAuthority.kt", i = {0, 0}, l = {53}, m = "getTimestampToken$suspendImpl", n = {"$this", "request"}, s = {"L$0", "L$1"})
/* loaded from: classes2.dex */
public final class BasicTimestampAuthority$getTimestampToken$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BasicTimestampAuthority this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicTimestampAuthority$getTimestampToken$1(BasicTimestampAuthority basicTimestampAuthority, Continuation<? super BasicTimestampAuthority$getTimestampToken$1> continuation) {
        super(continuation);
        this.this$0 = basicTimestampAuthority;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BasicTimestampAuthority.getTimestampToken$suspendImpl(this.this$0, null, this);
    }
}
