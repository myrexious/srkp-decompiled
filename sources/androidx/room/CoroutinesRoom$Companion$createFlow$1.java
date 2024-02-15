package androidx.room;

import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CoroutinesRoom.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1", f = "CoroutinesRoom.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class CoroutinesRoom$Companion$createFlow$1<R> extends SuspendLambda implements Function2<FlowCollector<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Callable<R> $callable;
    final /* synthetic */ RoomDatabase $db;
    final /* synthetic */ boolean $inTransaction;
    final /* synthetic */ String[] $tableNames;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutinesRoom$Companion$createFlow$1(boolean z, RoomDatabase roomDatabase, String[] strArr, Callable<R> callable, Continuation<? super CoroutinesRoom$Companion$createFlow$1> continuation) {
        super(2, continuation);
        this.$inTransaction = z;
        this.$db = roomDatabase;
        this.$tableNames = strArr;
        this.$callable = callable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CoroutinesRoom$Companion$createFlow$1 coroutinesRoom$Companion$createFlow$1 = new CoroutinesRoom$Companion$createFlow$1(this.$inTransaction, this.$db, this.$tableNames, this.$callable, continuation);
        coroutinesRoom$Companion$createFlow$1.L$0 = obj;
        return coroutinesRoom$Companion$createFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return invoke((FlowCollector) ((FlowCollector) obj), continuation);
    }

    public final Object invoke(FlowCollector<? super R> flowCollector, Continuation<? super Unit> continuation) {
        return ((CoroutinesRoom$Companion$createFlow$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: CoroutinesRoom.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1", f = "CoroutinesRoom.kt", i = {}, l = {136}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1 */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FlowCollector<R> $$this$flow;
        final /* synthetic */ Callable<R> $callable;
        final /* synthetic */ RoomDatabase $db;
        final /* synthetic */ boolean $inTransaction;
        final /* synthetic */ String[] $tableNames;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(boolean z, RoomDatabase roomDatabase, FlowCollector<? super R> flowCollector, String[] strArr, Callable<R> callable, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$inTransaction = z;
            this.$db = roomDatabase;
            this.$$this$flow = flowCollector;
            this.$tableNames = strArr;
            this.$callable = callable;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$inTransaction, this.$db, this.$$this$flow, this.$tableNames, this.$callable, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r7v0, types: [androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Channel Channel$default = ChannelKt.Channel$default(-1, null, null, 6, null);
                ?? r7 = new InvalidationTracker.Observer(Channel$default, this.$tableNames) { // from class: androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1
                    final /* synthetic */ Channel<Unit> $observerChannel;
                    final /* synthetic */ String[] $tableNames;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(strArr);
                        this.$tableNames = strArr;
                    }

                    @Override // androidx.room.InvalidationTracker.Observer
                    public void onInvalidated(Set<String> tables) {
                        Intrinsics.checkNotNullParameter(tables, "tables");
                        this.$observerChannel.mo1782trySendJP2dKIU(Unit.INSTANCE);
                    }
                };
                Channel$default.mo1782trySendJP2dKIU(Unit.INSTANCE);
                TransactionElement transactionElement = (TransactionElement) coroutineScope.getCoroutineContext().get(TransactionElement.Key);
                CoroutineDispatcher transactionDispatcher$room_ktx_release = transactionElement == null ? null : transactionElement.getTransactionDispatcher$room_ktx_release();
                if (transactionDispatcher$room_ktx_release == null) {
                    transactionDispatcher$room_ktx_release = this.$inTransaction ? CoroutinesRoomKt.getTransactionDispatcher(this.$db) : CoroutinesRoomKt.getQueryDispatcher(this.$db);
                }
                Channel Channel$default2 = ChannelKt.Channel$default(0, null, null, 7, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, transactionDispatcher$room_ktx_release, null, new C00071(this.$db, r7, Channel$default, this.$callable, Channel$default2, null), 2, null);
                this.label = 1;
                if (FlowKt.emitAll(this.$$this$flow, Channel$default2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* compiled from: CoroutinesRoom.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
        @DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1", f = "CoroutinesRoom.kt", i = {}, l = {127, 129}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1 */
        /* loaded from: classes.dex */
        public static final class C00071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Callable<R> $callable;
            final /* synthetic */ RoomDatabase $db;
            final /* synthetic */ CoroutinesRoom$Companion$createFlow$1$1$observer$1 $observer;
            final /* synthetic */ Channel<Unit> $observerChannel;
            final /* synthetic */ Channel<R> $resultChannel;
            Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00071(RoomDatabase roomDatabase, CoroutinesRoom$Companion$createFlow$1$1$observer$1 coroutinesRoom$Companion$createFlow$1$1$observer$1, Channel<Unit> channel, Callable<R> callable, Channel<R> channel2, Continuation<? super C00071> continuation) {
                super(2, continuation);
                this.$db = roomDatabase;
                this.$observer = coroutinesRoom$Companion$createFlow$1$1$observer$1;
                this.$observerChannel = channel;
                this.$callable = callable;
                this.$resultChannel = channel2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00071(this.$db, this.$observer, this.$observerChannel, this.$callable, this.$resultChannel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00071) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:57:0x004d A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:58:0x004e  */
            /* JADX WARN: Removed duplicated region for block: B:61:0x0059 A[Catch: all -> 0x0086, TRY_LEAVE, TryCatch #0 {all -> 0x0086, blocks: (B:55:0x0040, B:59:0x0051, B:61:0x0059), top: B:73:0x0040 }] */
            /* JADX WARN: Removed duplicated region for block: B:65:0x0076  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:0x0074 -> B:73:0x0040). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                /*
                    r8 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r8.label
                    r2 = 2
                    r3 = 1
                    if (r1 == 0) goto L29
                    if (r1 == r3) goto L1f
                    if (r1 != r2) goto L17
                    java.lang.Object r1 = r8.L$0
                    kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L88
                    r9 = r1
                    goto L3f
                L17:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r0)
                    throw r9
                L1f:
                    java.lang.Object r1 = r8.L$0
                    kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L88
                    r4 = r1
                    r1 = r8
                    goto L51
                L29:
                    kotlin.ResultKt.throwOnFailure(r9)
                    androidx.room.RoomDatabase r9 = r8.$db
                    androidx.room.InvalidationTracker r9 = r9.getInvalidationTracker()
                    androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1 r1 = r8.$observer
                    androidx.room.InvalidationTracker$Observer r1 = (androidx.room.InvalidationTracker.Observer) r1
                    r9.addObserver(r1)
                    kotlinx.coroutines.channels.Channel<kotlin.Unit> r9 = r8.$observerChannel     // Catch: java.lang.Throwable -> L88
                    kotlinx.coroutines.channels.ChannelIterator r9 = r9.iterator()     // Catch: java.lang.Throwable -> L88
                L3f:
                    r1 = r8
                L40:
                    r4 = r1
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.lang.Throwable -> L86
                    r1.L$0 = r9     // Catch: java.lang.Throwable -> L86
                    r1.label = r3     // Catch: java.lang.Throwable -> L86
                    java.lang.Object r4 = r9.hasNext(r4)     // Catch: java.lang.Throwable -> L86
                    if (r4 != r0) goto L4e
                    return r0
                L4e:
                    r7 = r4
                    r4 = r9
                    r9 = r7
                L51:
                    java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L86
                    boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L86
                    if (r9 == 0) goto L76
                    r4.next()     // Catch: java.lang.Throwable -> L86
                    kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L86
                    java.util.concurrent.Callable<R> r9 = r1.$callable     // Catch: java.lang.Throwable -> L86
                    java.lang.Object r9 = r9.call()     // Catch: java.lang.Throwable -> L86
                    kotlinx.coroutines.channels.Channel<R> r5 = r1.$resultChannel     // Catch: java.lang.Throwable -> L86
                    r6 = r1
                    kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Throwable -> L86
                    r1.L$0 = r4     // Catch: java.lang.Throwable -> L86
                    r1.label = r2     // Catch: java.lang.Throwable -> L86
                    java.lang.Object r9 = r5.send(r9, r6)     // Catch: java.lang.Throwable -> L86
                    if (r9 != r0) goto L74
                    return r0
                L74:
                    r9 = r4
                    goto L40
                L76:
                    androidx.room.RoomDatabase r9 = r1.$db
                    androidx.room.InvalidationTracker r9 = r9.getInvalidationTracker()
                    androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1 r0 = r1.$observer
                    androidx.room.InvalidationTracker$Observer r0 = (androidx.room.InvalidationTracker.Observer) r0
                    r9.removeObserver(r0)
                    kotlin.Unit r9 = kotlin.Unit.INSTANCE
                    return r9
                L86:
                    r9 = move-exception
                    goto L8a
                L88:
                    r9 = move-exception
                    r1 = r8
                L8a:
                    androidx.room.RoomDatabase r0 = r1.$db
                    androidx.room.InvalidationTracker r0 = r0.getInvalidationTracker()
                    androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1 r1 = r1.$observer
                    androidx.room.InvalidationTracker$Observer r1 = (androidx.room.InvalidationTracker.Observer) r1
                    r0.removeObserver(r1)
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.room.CoroutinesRoom$Companion$createFlow$1.AnonymousClass1.C00071.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z = this.$inTransaction;
            RoomDatabase roomDatabase = this.$db;
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(z, roomDatabase, (FlowCollector) this.L$0, this.$tableNames, this.$callable, null), this) == coroutine_suspended) {
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
