package org.informatika.sirekap.ui.security;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: CertificateViewModel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel", f = "CertificateViewModel.kt", i = {0, 0, 0}, l = {216, 254}, m = "continueGeneratingCertificate", n = {"this", "context", "firebaseId"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class CertificateViewModel$continueGeneratingCertificate$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$continueGeneratingCertificate$1(CertificateViewModel certificateViewModel, Continuation<? super CertificateViewModel$continueGeneratingCertificate$1> continuation) {
        super(continuation);
        this.this$0 = certificateViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object continueGeneratingCertificate;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        continueGeneratingCertificate = this.this$0.continueGeneratingCertificate(null, this);
        return continueGeneratingCertificate;
    }
}
