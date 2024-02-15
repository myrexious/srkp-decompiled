package org.informatika.sirekap.ui.security;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.model.KeyState;

/* compiled from: CertificateViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$setGenerationFailed$2", f = "CertificateViewModel.kt", i = {}, l = {136, 137, 138, 140}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class CertificateViewModel$setGenerationFailed$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $error;
    final /* synthetic */ String $properties;
    final /* synthetic */ KeyState $status;
    int label;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$setGenerationFailed$2(CertificateViewModel certificateViewModel, KeyState keyState, String str, String str2, Continuation<? super CertificateViewModel$setGenerationFailed$2> continuation) {
        super(2, continuation);
        this.this$0 = certificateViewModel;
        this.$status = keyState;
        this.$properties = str;
        this.$error = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CertificateViewModel$setGenerationFailed$2(this.this$0, this.$status, this.$properties, this.$error, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CertificateViewModel$setGenerationFailed$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0072  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 0
            r3 = 4
            r4 = 3
            r5 = 2
            java.lang.String r6 = "certificate_generation_error"
            r7 = 1
            if (r1 == 0) goto L2c
            if (r1 == r7) goto L28
            if (r1 == r5) goto L24
            if (r1 == r4) goto L20
            if (r1 != r3) goto L18
            goto L20
        L18:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L20:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L86
        L24:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L53
        L28:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L41
        L2c:
            kotlin.ResultKt.throwOnFailure(r11)
            org.informatika.sirekap.ui.security.CertificateViewModel r11 = r10.this$0
            org.informatika.sirekap.model.KeyState r1 = r10.$status
            java.lang.String r8 = r10.$properties
            r9 = r10
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r10.label = r7
            java.lang.Object r11 = org.informatika.sirekap.ui.security.CertificateViewModel.access$setCurrentStatus(r11, r1, r8, r9)
            if (r11 != r0) goto L41
            return r0
        L41:
            org.informatika.sirekap.ui.security.CertificateViewModel r11 = r10.this$0
            org.informatika.sirekap.db.dao.SecurityDao r11 = org.informatika.sirekap.ui.security.CertificateViewModel.access$getSecurityPropertiesDao$p(r11)
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r10.label = r5
            java.lang.Object r11 = r11.getValue(r6, r1)
            if (r11 != r0) goto L53
            return r0
        L53:
            if (r11 != 0) goto L72
            org.informatika.sirekap.ui.security.CertificateViewModel r11 = r10.this$0
            org.informatika.sirekap.db.dao.SecurityDao r11 = org.informatika.sirekap.ui.security.CertificateViewModel.access$getSecurityPropertiesDao$p(r11)
            org.informatika.sirekap.model.SecurityProperties[] r1 = new org.informatika.sirekap.model.SecurityProperties[r7]
            org.informatika.sirekap.model.SecurityProperties r3 = new org.informatika.sirekap.model.SecurityProperties
            java.lang.String r5 = r10.$error
            r3.<init>(r6, r5)
            r1[r2] = r3
            r3 = r10
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r10.label = r4
            java.lang.Object r11 = r11.insertValue(r1, r3)
            if (r11 != r0) goto L86
            return r0
        L72:
            org.informatika.sirekap.ui.security.CertificateViewModel r11 = r10.this$0
            org.informatika.sirekap.db.dao.SecurityDao r11 = org.informatika.sirekap.ui.security.CertificateViewModel.access$getSecurityPropertiesDao$p(r11)
            java.lang.String r1 = r10.$error
            r4 = r10
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r10.label = r3
            java.lang.Object r11 = r11.updateValue(r6, r1, r4)
            if (r11 != r0) goto L86
            return r0
        L86:
            org.informatika.sirekap.ui.security.CertificateViewModel r11 = r10.this$0
            androidx.lifecycle.MutableLiveData r11 = r11.getErrorMessage()
            java.lang.String r0 = r10.$error
            r11.postValue(r0)
            org.informatika.sirekap.ui.security.CertificateViewModel r11 = r10.this$0
            androidx.lifecycle.MutableLiveData r11 = r11.isLoading()
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            r11.postValue(r0)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.security.CertificateViewModel$setGenerationFailed$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
