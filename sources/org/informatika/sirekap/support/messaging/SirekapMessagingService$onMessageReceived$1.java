package org.informatika.sirekap.support.messaging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.db.dao.SecurityDao;
import org.informatika.sirekap.model.KeyState;
import org.informatika.sirekap.model.SecurityProperties;

/* compiled from: SirekapMessagingService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.support.messaging.SirekapMessagingService$onMessageReceived$1", f = "SirekapMessagingService.kt", i = {}, l = {76, 78, 85}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class SirekapMessagingService$onMessageReceived$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SecurityDao $securityDao;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SirekapMessagingService$onMessageReceived$1(SecurityDao securityDao, Continuation<? super SirekapMessagingService$onMessageReceived$1> continuation) {
        super(2, continuation);
        this.$securityDao = securityDao;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SirekapMessagingService$onMessageReceived$1(this.$securityDao, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SirekapMessagingService$onMessageReceived$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.$securityDao.getValue(SecurityProperties.LOCAL_CERTIFICATE_STATUS, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            if (i == 2 || i == 3) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        SecurityProperties securityProperties = (SecurityProperties) obj;
        if (securityProperties == null) {
            SecurityDao securityDao = this.$securityDao;
            SecurityProperties[] securityPropertiesArr = {new SecurityProperties(SecurityProperties.LOCAL_CERTIFICATE_STATUS, KeyState.GENERATED.name())};
            this.label = 2;
            if (securityDao.insertValue(securityPropertiesArr, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (!Intrinsics.areEqual(securityProperties.getValue(), KeyState.DOWNLOADED.name())) {
            this.label = 3;
            if (this.$securityDao.updateValue(SecurityProperties.LOCAL_CERTIFICATE_STATUS, KeyState.GENERATED.name(), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
