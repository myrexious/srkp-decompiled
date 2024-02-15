package androidx.datastore.core;

import androidx.core.app.NotificationCompat;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ClosedSendChannelException;

/* compiled from: SimpleActor.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002Bf\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u001a\u0010\t\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\n\u0012\"\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0013\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0002\u0010\u0015R/\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Landroidx/datastore/core/SimpleActor;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "onComplete", "Lkotlin/Function1;", "", "", "onUndeliveredElement", "Lkotlin/Function2;", "consumeMessage", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "messageQueue", "Lkotlinx/coroutines/channels/Channel;", "remainingMessages", "Ljava/util/concurrent/atomic/AtomicInteger;", "offer", NotificationCompat.CATEGORY_MESSAGE, "(Ljava/lang/Object;)V", "datastore-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SimpleActor<T> {
    private final Function2<T, Continuation<? super Unit>, Object> consumeMessage;
    private final Channel<T> messageQueue;
    private final AtomicInteger remainingMessages;
    private final CoroutineScope scope;

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleActor(CoroutineScope scope, final Function1<? super Throwable, Unit> onComplete, final Function2<? super T, ? super Throwable, Unit> onUndeliveredElement, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> consumeMessage) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onUndeliveredElement, "onUndeliveredElement");
        Intrinsics.checkNotNullParameter(consumeMessage, "consumeMessage");
        this.scope = scope;
        this.consumeMessage = consumeMessage;
        this.messageQueue = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
        this.remainingMessages = new AtomicInteger(0);
        Job job = (Job) scope.getCoroutineContext().get(Job.Key);
        if (job == null) {
            return;
        }
        job.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: androidx.datastore.core.SimpleActor.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke */
            public final void invoke2(Throwable th) {
                Unit unit;
                onComplete.invoke(th);
                ((SimpleActor) this).messageQueue.close(th);
                do {
                    Object m1792getOrNullimpl = ChannelResult.m1792getOrNullimpl(((SimpleActor) this).messageQueue.mo1781tryReceivePtdJZtk());
                    if (m1792getOrNullimpl == null) {
                        unit = null;
                        continue;
                    } else {
                        onUndeliveredElement.invoke(m1792getOrNullimpl, th);
                        unit = Unit.INSTANCE;
                        continue;
                    }
                } while (unit != null);
            }
        });
    }

    public final void offer(T t) {
        Object obj = this.messageQueue.mo1782trySendJP2dKIU(t);
        if (obj instanceof ChannelResult.Closed) {
            Throwable m1791exceptionOrNullimpl = ChannelResult.m1791exceptionOrNullimpl(obj);
            if (m1791exceptionOrNullimpl != null) {
                throw m1791exceptionOrNullimpl;
            }
            throw new ClosedSendChannelException("Channel was closed normally");
        } else if (!ChannelResult.m1797isSuccessimpl(obj)) {
            throw new IllegalStateException("Check failed.".toString());
        } else {
            if (this.remainingMessages.getAndIncrement() == 0) {
                BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new SimpleActor$offer$2(this, null), 3, null);
            }
        }
    }
}
