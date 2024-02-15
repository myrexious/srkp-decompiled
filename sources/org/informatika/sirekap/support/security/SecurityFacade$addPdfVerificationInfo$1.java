package org.informatika.sirekap.support.security;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: SecurityFacade.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.support.security.SecurityFacade", f = "SecurityFacade.kt", i = {0, 0}, l = {420, 422}, m = "addPdfVerificationInfo", n = {"pdfLtv", "pdfFile"}, s = {"L$0", "L$1"})
/* loaded from: classes2.dex */
public final class SecurityFacade$addPdfVerificationInfo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SecurityFacade this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecurityFacade$addPdfVerificationInfo$1(SecurityFacade securityFacade, Continuation<? super SecurityFacade$addPdfVerificationInfo$1> continuation) {
        super(continuation);
        this.this$0 = securityFacade;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.addPdfVerificationInfo(null, null, null, null, this);
    }
}
