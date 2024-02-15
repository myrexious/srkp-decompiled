package org.informatika.sirekap.ui.security;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.db.dao.SecurityDao;
import org.informatika.sirekap.model.KeyState;
import org.informatika.sirekap.model.SecurityProperties;

/* compiled from: CertificateViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$setCurrentStatus$2", f = "CertificateViewModel.kt", i = {}, l = {125, 126, 128}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class CertificateViewModel$setCurrentStatus$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $properties;
    final /* synthetic */ KeyState $status;
    int label;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$setCurrentStatus$2(CertificateViewModel certificateViewModel, String str, KeyState keyState, Continuation<? super CertificateViewModel$setCurrentStatus$2> continuation) {
        super(2, continuation);
        this.this$0 = certificateViewModel;
        this.$properties = str;
        this.$status = keyState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CertificateViewModel$setCurrentStatus$2(this.this$0, this.$properties, this.$status, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CertificateViewModel$setCurrentStatus$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SecurityDao securityDao;
        SecurityDao securityDao2;
        SecurityDao securityDao3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            securityDao = this.this$0.securityPropertiesDao;
            this.label = 1;
            obj = securityDao.getValue(this.$properties, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            if (i == 2 || i == 3) {
                ResultKt.throwOnFailure(obj);
                this.this$0.getKeyState().postValue(this.$status);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        if (obj == null) {
            securityDao3 = this.this$0.securityPropertiesDao;
            SecurityProperties[] securityPropertiesArr = {new SecurityProperties(this.$properties, this.$status.name())};
            this.label = 2;
            if (securityDao3.insertValue(securityPropertiesArr, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            securityDao2 = this.this$0.securityPropertiesDao;
            this.label = 3;
            if (securityDao2.updateValue(this.$properties, this.$status.name(), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        this.this$0.getKeyState().postValue(this.$status);
        return Unit.INSTANCE;
    }
}
