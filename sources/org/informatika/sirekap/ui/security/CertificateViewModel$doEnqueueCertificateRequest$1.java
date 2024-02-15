package org.informatika.sirekap.ui.security;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.tensorflow.lite.schema.BuiltinOperator;

/* compiled from: CertificateViewModel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel", f = "CertificateViewModel.kt", i = {0, 1, 1, 2, 3}, l = {BuiltinOperator.MULTINOMIAL, BuiltinOperator.DYNAMIC_UPDATE_SLICE, 152, 154, 157}, m = "doEnqueueCertificateRequest", n = {"this", "this", "requestId", "this", "this"}, s = {"L$0", "L$0", "L$1", "L$0", "L$0"})
/* loaded from: classes4.dex */
public final class CertificateViewModel$doEnqueueCertificateRequest$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$doEnqueueCertificateRequest$1(CertificateViewModel certificateViewModel, Continuation<? super CertificateViewModel$doEnqueueCertificateRequest$1> continuation) {
        super(continuation);
        this.this$0 = certificateViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object doEnqueueCertificateRequest;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        doEnqueueCertificateRequest = this.this$0.doEnqueueCertificateRequest(null, null, null, null, this);
        return doEnqueueCertificateRequest;
    }
}
