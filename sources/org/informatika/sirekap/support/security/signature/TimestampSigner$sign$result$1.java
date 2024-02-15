package org.informatika.sirekap.support.security.signature;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.bouncycastle.tsp.TimeStampToken;
import org.informatika.sirekap.support.security.tsa.TimestampAuthority;

/* compiled from: TimestampSigner.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lorg/bouncycastle/tsp/TimeStampToken;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.support.security.signature.TimestampSigner$sign$result$1", f = "TimestampSigner.kt", i = {}, l = {11}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class TimestampSigner$sign$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TimeStampToken>, Object> {
    final /* synthetic */ InputStream $content;
    int label;
    final /* synthetic */ TimestampSigner this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimestampSigner$sign$result$1(TimestampSigner timestampSigner, InputStream inputStream, Continuation<? super TimestampSigner$sign$result$1> continuation) {
        super(2, continuation);
        this.this$0 = timestampSigner;
        this.$content = inputStream;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TimestampSigner$sign$result$1(this.this$0, this.$content, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TimeStampToken> continuation) {
        return ((TimestampSigner$sign$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        TimestampAuthority timestampAuthority;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            timestampAuthority = this.this$0.timestamp;
            this.label = 1;
            obj = timestampAuthority.getTimestampToken(this.$content, this);
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
