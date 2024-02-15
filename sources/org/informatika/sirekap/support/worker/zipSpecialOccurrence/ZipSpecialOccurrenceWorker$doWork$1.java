package org.informatika.sirekap.support.worker.zipSpecialOccurrence;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: ZipSpecialOccurrenceWorker.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceWorker$doWork$1", f = "ZipSpecialOccurrenceWorker.kt", i = {}, l = {173}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class ZipSpecialOccurrenceWorker$doWork$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AuthRequestUseCase $authRequestUseCase;
    final /* synthetic */ File $pdfFile;
    final /* synthetic */ PdfLtv $pdfLtv;
    int label;
    final /* synthetic */ ZipSpecialOccurrenceWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZipSpecialOccurrenceWorker$doWork$1(ZipSpecialOccurrenceWorker zipSpecialOccurrenceWorker, AuthRequestUseCase authRequestUseCase, PdfLtv pdfLtv, File file, Continuation<? super ZipSpecialOccurrenceWorker$doWork$1> continuation) {
        super(2, continuation);
        this.this$0 = zipSpecialOccurrenceWorker;
        this.$authRequestUseCase = authRequestUseCase;
        this.$pdfLtv = pdfLtv;
        this.$pdfFile = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZipSpecialOccurrenceWorker$doWork$1(this.this$0, this.$authRequestUseCase, this.$pdfLtv, this.$pdfFile, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ZipSpecialOccurrenceWorker$doWork$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SecurityFacade securityFacade = SecurityFacade.INSTANCE;
            context = this.this$0.appContext;
            this.label = 1;
            if (securityFacade.addPdfVerificationInfo(context, this.$authRequestUseCase, this.$pdfLtv, this.$pdfFile, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
