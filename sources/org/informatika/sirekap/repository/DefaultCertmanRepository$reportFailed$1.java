package org.informatika.sirekap.repository;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: CertmanRepository.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.repository.DefaultCertmanRepository", f = "CertmanRepository.kt", i = {0}, l = {278}, m = "reportFailed$suspendImpl", n = {"$this"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class DefaultCertmanRepository$reportFailed$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultCertmanRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultCertmanRepository$reportFailed$1(DefaultCertmanRepository defaultCertmanRepository, Continuation<? super DefaultCertmanRepository$reportFailed$1> continuation) {
        super(continuation);
        this.this$0 = defaultCertmanRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DefaultCertmanRepository.reportFailed$suspendImpl(this.this$0, this);
    }
}
