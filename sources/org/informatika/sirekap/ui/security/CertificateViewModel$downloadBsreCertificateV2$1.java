package org.informatika.sirekap.ui.security;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: CertificateViewModel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel", f = "CertificateViewModel.kt", i = {0, 0, 1, 1, 2, 2, 3, 4}, l = {TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO, 616, 613, 618, 658}, m = "downloadBsreCertificateV2", n = {"this", "context", "this", "context", "this", "context", "this", "this"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$0"})
/* loaded from: classes4.dex */
public final class CertificateViewModel$downloadBsreCertificateV2$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$downloadBsreCertificateV2$1(CertificateViewModel certificateViewModel, Continuation<? super CertificateViewModel$downloadBsreCertificateV2$1> continuation) {
        super(continuation);
        this.this$0 = certificateViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.downloadBsreCertificateV2(null, this);
    }
}
