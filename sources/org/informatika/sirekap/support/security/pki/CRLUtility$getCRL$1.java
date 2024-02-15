package org.informatika.sirekap.support.security.pki;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: CRLUtility.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.support.security.pki.CRLUtility", f = "CRLUtility.kt", i = {0, 0, 0, 0, 0, 0}, l = {24}, m = "getCRL", n = {"this", "certificateChain", "resultList", "certificate", "issuer", OperatorName.SET_FLATNESS}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0"})
/* loaded from: classes2.dex */
public final class CRLUtility$getCRL$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CRLUtility this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CRLUtility$getCRL$1(CRLUtility cRLUtility, Continuation<? super CRLUtility$getCRL$1> continuation) {
        super(continuation);
        this.this$0 = cRLUtility;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getCRL(null, this);
    }
}
