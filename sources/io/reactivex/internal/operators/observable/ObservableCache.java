package io.reactivex.internal.operators.observable;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.LinkedArrayList;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public final class ObservableCache<T> extends AbstractObservableWithUpstream<T, T> {
    final AtomicBoolean once;
    final CacheState<T> state;

    public static <T> Observable<T> from(Observable<T> observable) {
        return from(observable, 16);
    }

    public static <T> Observable<T> from(Observable<T> observable, int i) {
        ObjectHelper.verifyPositive(i, "capacityHint");
        return RxJavaPlugins.onAssembly(new ObservableCache(observable, new CacheState(observable, i)));
    }

    private ObservableCache(Observable<T> observable, CacheState<T> cacheState) {
        super(observable);
        this.state = cacheState;
        this.once = new AtomicBoolean();
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        ReplayDisposable<T> replayDisposable = new ReplayDisposable<>(observer, this.state);
        observer.onSubscribe(replayDisposable);
        this.state.addChild(replayDisposable);
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
            this.state.connect();
        }
        replayDisposable.replay();
    }

    boolean isConnected() {
        return this.state.isConnected;
    }

    boolean hasObservers() {
        return this.state.observers.get().length != 0;
    }

    int cachedEventCount() {
        return this.state.size();
    }

    /* loaded from: classes3.dex */
    public static final class CacheState<T> extends LinkedArrayList implements Observer<T> {
        static final ReplayDisposable[] EMPTY = new ReplayDisposable[0];
        static final ReplayDisposable[] TERMINATED = new ReplayDisposable[0];
        final SequentialDisposable connection;
        volatile boolean isConnected;
        final AtomicReference<ReplayDisposable<T>[]> observers;
        final Observable<? extends T> source;
        boolean sourceDone;

        CacheState(Observable<? extends T> observable, int i) {
            super(i);
            this.source = observable;
            this.observers = new AtomicReference<>(EMPTY);
            this.connection = new SequentialDisposable();
        }

        public boolean addChild(ReplayDisposable<T> replayDisposable) {
            ReplayDisposable<T>[] replayDisposableArr;
            ReplayDisposable[] replayDisposableArr2;
            do {
                replayDisposableArr = this.observers.get();
                if (replayDisposableArr == TERMINATED) {
                    return false;
                }
                int length = replayDisposableArr.length;
                replayDisposableArr2 = new ReplayDisposable[length + 1];
                System.arraycopy(replayDisposableArr, 0, replayDisposableArr2, 0, length);
                replayDisposableArr2[length] = replayDisposable;
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observers, replayDisposableArr, replayDisposableArr2));
            return true;
        }

        public void removeChild(ReplayDisposable<T> replayDisposable) {
            ReplayDisposable<T>[] replayDisposableArr;
            ReplayDisposable[] replayDisposableArr2;
            do {
                replayDisposableArr = this.observers.get();
                int length = replayDisposableArr.length;
                if (length == 0) {
                    return;
                }
                int i = 0;
                while (true) {
                    if (i >= length) {
                        i = -1;
                        break;
                    } else if (replayDisposableArr[i].equals(replayDisposable)) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    replayDisposableArr2 = EMPTY;
                } else {
                    ReplayDisposable[] replayDisposableArr3 = new ReplayDisposable[length - 1];
                    System.arraycopy(replayDisposableArr, 0, replayDisposableArr3, 0, i);
                    System.arraycopy(replayDisposableArr, i + 1, replayDisposableArr3, i, (length - i) - 1);
                    replayDisposableArr2 = replayDisposableArr3;
                }
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observers, replayDisposableArr, replayDisposableArr2));
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.connection.update(disposable);
        }

        public void connect() {
            this.source.subscribe(this);
            this.isConnected = true;
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.sourceDone) {
                return;
            }
            add(NotificationLite.next(t));
            for (ReplayDisposable<T> replayDisposable : this.observers.get()) {
                replayDisposable.replay();
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.sourceDone) {
                return;
            }
            this.sourceDone = true;
            add(NotificationLite.error(th));
            this.connection.dispose();
            for (ReplayDisposable<T> replayDisposable : this.observers.getAndSet(TERMINATED)) {
                replayDisposable.replay();
            }
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.sourceDone) {
                return;
            }
            this.sourceDone = true;
            add(NotificationLite.complete());
            this.connection.dispose();
            for (ReplayDisposable<T> replayDisposable : this.observers.getAndSet(TERMINATED)) {
                replayDisposable.replay();
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class ReplayDisposable<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 7058506693698832024L;
        volatile boolean cancelled;
        final Observer<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        int index;
        final CacheState<T> state;

        ReplayDisposable(Observer<? super T> observer, CacheState<T> cacheState) {
            this.child = observer;
            this.state = cacheState;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.state.removeChild(this);
        }

        public void replay() {
            if (getAndIncrement() != 0) {
                return;
            }
            Observer<? super T> observer = this.child;
            int i = 1;
            while (!this.cancelled) {
                int size = this.state.size();
                if (size != 0) {
                    Object[] objArr = this.currentBuffer;
                    if (objArr == null) {
                        objArr = this.state.head();
                        this.currentBuffer = objArr;
                    }
                    int length = objArr.length - 1;
                    int i2 = this.index;
                    int i3 = this.currentIndexInBuffer;
                    while (i2 < size) {
                        if (this.cancelled) {
                            return;
                        }
                        if (i3 == length) {
                            objArr = (Object[]) objArr[length];
                            i3 = 0;
                        }
                        if (NotificationLite.accept(objArr[i3], observer)) {
                            return;
                        }
                        i3++;
                        i2++;
                    }
                    if (this.cancelled) {
                        return;
                    }
                    this.index = i2;
                    this.currentIndexInBuffer = i3;
                    this.currentBuffer = objArr;
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }
    }
}