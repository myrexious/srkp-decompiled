package org.informatika.sirekap.repository;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: PKIRepository.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.repository.DefaultPKIRepository", f = "PKIRepository.kt", i = {0}, l = {25}, m = "getTSAResponse", n = {"request"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class DefaultPKIRepository$getTSAResponse$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultPKIRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultPKIRepository$getTSAResponse$1(DefaultPKIRepository defaultPKIRepository, Continuation<? super DefaultPKIRepository$getTSAResponse$1> continuation) {
        super(continuation);
        this.this$0 = defaultPKIRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getTSAResponse(null, null, this);
    }
}
