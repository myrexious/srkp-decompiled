package io.reactivex.internal.operators.flowable;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes3.dex */
public final class FlowablePublishMulticast<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final boolean delayError;
    final int prefetch;
    final Function<? super Flowable<T>, ? extends Publisher<? extends R>> selector;

    public FlowablePublishMulticast(Flowable<T> flowable, Function<? super Flowable<T>, ? extends Publisher<? extends R>> function, int i, boolean z) {
        super(flowable);
        this.selector = function;
        this.prefetch = i;
        this.delayError = z;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super R> subscriber) {
        MulticastProcessor multicastProcessor = new MulticastProcessor(this.prefetch, this.delayError);
        try {
            ((Publisher) ObjectHelper.requireNonNull(this.selector.apply(multicastProcessor), "selector returned a null Publisher")).subscribe(new OutputCanceller(subscriber, multicastProcessor));
            this.source.subscribe((FlowableSubscriber) multicastProcessor);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, subscriber);
        }
    }

    /* loaded from: classes3.dex */
    static final class OutputCanceller<R> implements FlowableSubscriber<R>, Subscription {
        final Subscriber<? super R> actual;
        final MulticastProcessor<?> processor;
        Subscription s;

        OutputCanceller(Subscriber<? super R> subscriber, MulticastProcessor<?> multicastProcessor) {
            this.actual = subscriber;
            this.processor = multicastProcessor;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(R r) {
            this.actual.onNext(r);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
            this.processor.dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.actual.onComplete();
            this.processor.dispose();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.s.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.s.cancel();
            this.processor.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class MulticastProcessor<T> extends Flowable<T> implements FlowableSubscriber<T>, Disposable {
        static final MulticastSubscription[] EMPTY = new MulticastSubscription[0];
        static final MulticastSubscription[] TERMINATED = new MulticastSubscription[0];
        int consumed;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        volatile SimpleQueue<T> queue;
        int sourceMode;
        final AtomicInteger wip = new AtomicInteger();
        final AtomicReference<Subscription> s = new AtomicReference<>();
        final AtomicReference<MulticastSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);

        MulticastProcessor(int i, boolean z) {
            this.prefetch = i;
            this.limit = i - (i >> 2);
            this.delayError = z;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.s, subscription)) {
                if (subscription instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) subscription;
                    int requestFusion = queueSubscription.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        QueueDrainHelper.request(subscription, this.prefetch);
                        return;
                    }
                }
                this.queue = QueueDrainHelper.createQueue(this.prefetch);
                QueueDrainHelper.request(subscription, this.prefetch);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            SimpleQueue<T> simpleQueue;
            SubscriptionHelper.cancel(this.s);
            if (this.wip.getAndIncrement() != 0 || (simpleQueue = this.queue) == null) {
                return;
            }
            simpleQueue.clear();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled(this.s.get());
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode == 0 && !this.queue.offer(t)) {
                this.s.get().cancel();
                onError(new MissingBackpressureException());
                return;
            }
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        boolean add(MulticastSubscription<T> multicastSubscription) {
            MulticastSubscription<T>[] multicastSubscriptionArr;
            MulticastSubscription[] multicastSubscriptionArr2;
            do {
                multicastSubscriptionArr = this.subscribers.get();
                if (multicastSubscriptionArr == TERMINATED) {
                    return false;
                }
                int length = multicastSubscriptionArr.length;
                multicastSubscriptionArr2 = new MulticastSubscription[length + 1];
                System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, length);
                multicastSubscriptionArr2[length] = multicastSubscription;
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.subscribers, multicastSubscriptionArr, multicastSubscriptionArr2));
            return true;
        }

        void remove(MulticastSubscription<T> multicastSubscription) {
            MulticastSubscription<T>[] multicastSubscriptionArr;
            MulticastSubscription[] multicastSubscriptionArr2;
            do {
                multicastSubscriptionArr = this.subscribers.get();
                if (multicastSubscriptionArr == TERMINATED || multicastSubscriptionArr == EMPTY) {
                    return;
                }
                int length = multicastSubscriptionArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        i = -1;
                        break;
                    } else if (multicastSubscriptionArr[i] == multicastSubscription) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    multicastSubscriptionArr2 = EMPTY;
                } else {
                    MulticastSubscription[] multicastSubscriptionArr3 = new MulticastSubscription[length - 1];
                    System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr3, 0, i);
                    System.arraycopy(multicastSubscriptionArr, i + 1, multicastSubscriptionArr3, i, (length - i) - 1);
                    multicastSubscriptionArr2 = multicastSubscriptionArr3;
                }
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.subscribers, multicastSubscriptionArr, multicastSubscriptionArr2));
        }

        @Override // io.reactivex.Flowable
        protected void subscribeActual(Subscriber<? super T> subscriber) {
            MulticastSubscription<T> multicastSubscription = new MulticastSubscription<>(subscriber, this);
            subscriber.onSubscribe(multicastSubscription);
            if (add(multicastSubscription)) {
                if (multicastSubscription.isCancelled()) {
                    remove(multicastSubscription);
                    return;
                } else {
                    drain();
                    return;
                }
            }
            Throwable th = this.error;
            if (th != null) {
                subscriber.onError(th);
            } else {
                subscriber.onComplete();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:186:0x00c5, code lost:
            if (r15 != 0) goto L97;
         */
        /* JADX WARN: Code restructure failed: missing block: B:188:0x00cb, code lost:
            if (isDisposed() == false) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:189:0x00cd, code lost:
            r0.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:190:0x00d0, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:191:0x00d1, code lost:
            r9 = r20.done;
         */
        /* JADX WARN: Code restructure failed: missing block: B:192:0x00d3, code lost:
            if (r9 == false) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:194:0x00d7, code lost:
            if (r20.delayError != false) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:195:0x00d9, code lost:
            r10 = r20.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:196:0x00db, code lost:
            if (r10 == null) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:197:0x00dd, code lost:
            errorAll(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:198:0x00e0, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:199:0x00e1, code lost:
            if (r9 == false) goto L97;
         */
        /* JADX WARN: Code restructure failed: missing block: B:201:0x00e7, code lost:
            if (r0.isEmpty() == false) goto L97;
         */
        /* JADX WARN: Code restructure failed: missing block: B:202:0x00e9, code lost:
            r0 = r20.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:203:0x00eb, code lost:
            if (r0 == null) goto L92;
         */
        /* JADX WARN: Code restructure failed: missing block: B:204:0x00ed, code lost:
            errorAll(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:205:0x00f1, code lost:
            completeAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:206:0x00f4, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:207:0x00f5, code lost:
            r9 = r8.length;
            r10 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:208:0x00f7, code lost:
            if (r10 >= r9) goto L100;
         */
        /* JADX WARN: Code restructure failed: missing block: B:209:0x00f9, code lost:
            io.reactivex.internal.util.BackpressureHelper.produced(r8[r10], r5);
            r10 = r10 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:241:?, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 276
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublishMulticast.MulticastProcessor.drain():void");
        }

        void errorAll(Throwable th) {
            MulticastSubscription<T>[] andSet;
            for (MulticastSubscription<T> multicastSubscription : this.subscribers.getAndSet(TERMINATED)) {
                if (multicastSubscription.get() != Long.MIN_VALUE) {
                    multicastSubscription.actual.onError(th);
                }
            }
        }

        void completeAll() {
            MulticastSubscription<T>[] andSet;
            for (MulticastSubscription<T> multicastSubscription : this.subscribers.getAndSet(TERMINATED)) {
                if (multicastSubscription.get() != Long.MIN_VALUE) {
                    multicastSubscription.actual.onComplete();
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class MulticastSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 8664815189257569791L;
        final Subscriber<? super T> actual;
        final MulticastProcessor<T> parent;

        MulticastSubscription(Subscriber<? super T> subscriber, MulticastProcessor<T> multicastProcessor) {
            this.actual = subscriber;
            this.parent = multicastProcessor;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.addCancel(this, j);
                this.parent.drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.drain();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }
    }
}