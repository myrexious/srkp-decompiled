package org.informatika.sirekap.ui.security;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: CertificateViewModel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel", f = "CertificateViewModel.kt", i = {0, 0, 1, 1}, l = {336, 337, 392}, m = "getGeneratedCertificate", n = {"this", "context", "this", "context"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes4.dex */
public final class CertificateViewModel$getGeneratedCertificate$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$getGeneratedCertificate$1(CertificateViewModel certificateViewModel, Continuation<? super CertificateViewModel$getGeneratedCertificate$1> continuation) {
        super(continuation);
        this.this$0 = certificateViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getGeneratedCertificate(null, this);
    }
}
