package org.informatika.sirekap.support.worker.refreshToken;

import android.content.Context;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: RefreshTokenWorker.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lorg/informatika/sirekap/model/ActiveProfile;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.support.worker.refreshToken.RefreshTokenWorker$doWork$1", f = "RefreshTokenWorker.kt", i = {}, l = {68}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class RefreshTokenWorker$doWork$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ActiveProfile>, Object> {
    final /* synthetic */ AuthRequestUseCase $authRequestUseCase;
    int label;
    final /* synthetic */ RefreshTokenWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefreshTokenWorker$doWork$1(AuthRequestUseCase authRequestUseCase, RefreshTokenWorker refreshTokenWorker, Continuation<? super RefreshTokenWorker$doWork$1> continuation) {
        super(2, continuation);
        this.$authRequestUseCase = authRequestUseCase;
        this.this$0 = refreshTokenWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RefreshTokenWorker$doWork$1(this.$authRequestUseCase, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ActiveProfile> continuation) {
        return ((RefreshTokenWorker$doWork$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AuthRequestUseCase authRequestUseCase = this.$authRequestUseCase;
            context = this.this$0.appContext;
            this.label = 1;
            obj = authRequestUseCase.startRefreshToken(context, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
