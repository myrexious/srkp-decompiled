package org.informatika.sirekap.support.security.pdf;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: PdfLtv.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.support.security.pdf.PdfLtv", f = "PdfLtv.kt", i = {0, 0}, l = {42}, m = "addLtv", n = {"documentFile", "tmp"}, s = {"L$0", "L$1"})
/* loaded from: classes2.dex */
public final class PdfLtv$addLtv$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PdfLtv this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PdfLtv$addLtv$1(PdfLtv pdfLtv, Continuation<? super PdfLtv$addLtv$1> continuation) {
        super(continuation);
        this.this$0 = pdfLtv;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.addLtv(null, this);
    }
}
