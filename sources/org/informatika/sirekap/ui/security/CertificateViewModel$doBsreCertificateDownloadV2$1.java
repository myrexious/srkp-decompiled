package org.informatika.sirekap.ui.security;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: CertificateViewModel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel", f = "CertificateViewModel.kt", i = {0, 0, 0, 1, 1, 2}, l = {520, 521, 537}, m = "doBsreCertificateDownloadV2", n = {"this", "context", "payload", "this", "context", "this"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0"})
/* loaded from: classes4.dex */
public final class CertificateViewModel$doBsreCertificateDownloadV2$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$doBsreCertificateDownloadV2$1(CertificateViewModel certificateViewModel, Continuation<? super CertificateViewModel$doBsreCertificateDownloadV2$1> continuation) {
        super(continuation);
        this.this$0 = certificateViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doBsreCertificateDownloadV2(null, null, this);
    }
}
