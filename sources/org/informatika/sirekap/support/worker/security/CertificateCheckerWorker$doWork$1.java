package org.informatika.sirekap.support.worker.security;

import androidx.work.ListenableWorker;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.db.dao.SecurityDao;
import org.informatika.sirekap.repository.DefaultCertmanRepository;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: CertificateChekerWorker.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.support.worker.security.CertificateCheckerWorker$doWork$1", f = "CertificateChekerWorker.kt", i = {}, l = {74, 78, 77}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class CertificateCheckerWorker$doWork$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
    final /* synthetic */ AuthRequestUseCase $authUseCase;
    final /* synthetic */ DefaultCertmanRepository $certmanRepository;
    final /* synthetic */ SecurityDao $securityPropertiesDao;
    Object L$0;
    int label;
    final /* synthetic */ CertificateCheckerWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateCheckerWorker$doWork$1(AuthRequestUseCase authRequestUseCase, CertificateCheckerWorker certificateCheckerWorker, DefaultCertmanRepository defaultCertmanRepository, SecurityDao securityDao, Continuation<? super CertificateCheckerWorker$doWork$1> continuation) {
        super(2, continuation);
        this.$authUseCase = authRequestUseCase;
        this.this$0 = certificateCheckerWorker;
        this.$certmanRepository = defaultCertmanRepository;
        this.$securityPropertiesDao = securityDao;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CertificateCheckerWorker$doWork$1(this.$authUseCase, this.this$0, this.$certmanRepository, this.$securityPropertiesDao, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
        return ((CertificateCheckerWorker$doWork$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x0076 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0099  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L29
            if (r1 == r4) goto L25
            if (r1 == r3) goto L1d
            if (r1 != r2) goto L15
            kotlin.ResultKt.throwOnFailure(r7)
            goto L77
        L15:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1d:
            java.lang.Object r1 = r6.L$0
            org.informatika.sirekap.repository.DefaultCertmanRepository r1 = (org.informatika.sirekap.repository.DefaultCertmanRepository) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L5c
        L25:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L48
        L29:
            kotlin.ResultKt.throwOnFailure(r7)
            org.informatika.sirekap.usecase.AuthRequestUseCase r7 = r6.$authUseCase
            boolean r7 = r7.isLocalTokenExpired()
            if (r7 == 0) goto L48
            org.informatika.sirekap.usecase.AuthRequestUseCase r7 = r6.$authUseCase
            org.informatika.sirekap.support.worker.security.CertificateCheckerWorker r1 = r6.this$0
            android.content.Context r1 = org.informatika.sirekap.support.worker.security.CertificateCheckerWorker.access$getAppContext$p(r1)
            r5 = r6
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6.label = r4
            java.lang.Object r7 = r7.startRefreshToken(r1, r5)
            if (r7 != r0) goto L48
            return r0
        L48:
            org.informatika.sirekap.repository.DefaultCertmanRepository r1 = r6.$certmanRepository
            org.informatika.sirekap.db.dao.SecurityDao r7 = r6.$securityPropertiesDao
            r5 = r6
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6.L$0 = r1
            r6.label = r3
            java.lang.String r3 = "certificate_request_id"
            java.lang.Object r7 = r7.getValue(r3, r5)
            if (r7 != r0) goto L5c
            return r0
        L5c:
            org.informatika.sirekap.model.SecurityProperties r7 = (org.informatika.sirekap.model.SecurityProperties) r7
            if (r7 == 0) goto L66
            java.lang.String r7 = r7.getValue()
            if (r7 != 0) goto L68
        L66:
            java.lang.String r7 = ""
        L68:
            r3 = r6
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r5 = 0
            r6.L$0 = r5
            r6.label = r2
            java.lang.Object r7 = r1.getCertificateGenerationUrl(r7, r3)
            if (r7 != r0) goto L77
            return r0
        L77:
            java.lang.String r7 = (java.lang.String) r7
            r0 = r7
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L86
            int r0 = r0.length()
            if (r0 != 0) goto L85
            goto L86
        L85:
            r4 = 0
        L86:
            if (r4 != 0) goto L99
            org.informatika.sirekap.support.messaging.CertificateGenerationMessage r0 = org.informatika.sirekap.support.messaging.CertificateGenerationMessage.INSTANCE
            org.informatika.sirekap.support.messaging.CertificateGenerationResponse r1 = new org.informatika.sirekap.support.messaging.CertificateGenerationResponse
            java.lang.String r2 = "success"
            r1.<init>(r2, r7)
            r0.changeState(r1)
            androidx.work.ListenableWorker$Result r7 = androidx.work.ListenableWorker.Result.success()
            return r7
        L99:
            androidx.work.ListenableWorker$Result r7 = androidx.work.ListenableWorker.Result.retry()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.worker.security.CertificateCheckerWorker$doWork$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
