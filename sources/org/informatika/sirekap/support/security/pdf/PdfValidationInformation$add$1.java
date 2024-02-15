package org.informatika.sirekap.support.security.pdf;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: PdfValidationInformation.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.support.security.pdf.PdfValidationInformation", f = "PdfValidationInformation.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {76, 77}, m = "add", n = {"this", "documentFile", "document", "vriBase", "ocsps", "crls", "certs", "certificateSet", "addedCertificate", "certificateChain", "this", "documentFile", "document", "vriBase", "ocsps", "crls", "certs", "certificateSet", "addedCertificate", "ocspResponse"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$10"})
/* loaded from: classes2.dex */
public final class PdfValidationInformation$add$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PdfValidationInformation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PdfValidationInformation$add$1(PdfValidationInformation pdfValidationInformation, Continuation<? super PdfValidationInformation$add$1> continuation) {
        super(continuation);
        this.this$0 = pdfValidationInformation;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.add(null, this);
    }
}
