package org.informatika.sirekap.ui.security;

import androidx.lifecycle.MutableLiveData;
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
@DebugMetadata(c = "org.informatika.sirekap.ui.security.CertificateViewModel$checkCurrentStatus$2", f = "CertificateViewModel.kt", i = {}, l = {112}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class CertificateViewModel$checkCurrentStatus$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ KeyState $status;
    Object L$0;
    int label;
    final /* synthetic */ CertificateViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateViewModel$checkCurrentStatus$2(CertificateViewModel certificateViewModel, KeyState keyState, Continuation<? super CertificateViewModel$checkCurrentStatus$2> continuation) {
        super(2, continuation);
        this.this$0 = certificateViewModel;
        this.$status = keyState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CertificateViewModel$checkCurrentStatus$2(this.this$0, this.$status, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CertificateViewModel$checkCurrentStatus$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SecurityDao securityDao;
        MutableLiveData<String> mutableLiveData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableLiveData<String> errorMessage = this.this$0.getErrorMessage();
            securityDao = this.this$0.securityPropertiesDao;
            this.L$0 = errorMessage;
            this.label = 1;
            Object value = securityDao.getValue(SecurityProperties.CERTIFICATE_GENERATION_ERROR, this);
            if (value == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutableLiveData = errorMessage;
            obj = value;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            mutableLiveData = (MutableLiveData) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        SecurityProperties securityProperties = (SecurityProperties) obj;
        mutableLiveData.postValue(securityProperties != null ? securityProperties.getValue() : null);
        this.this$0.getKeyState().postValue(this.$status);
        return Unit.INSTANCE;
    }
}
