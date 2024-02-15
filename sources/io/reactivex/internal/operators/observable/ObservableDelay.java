package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public final class ObservableDelay<T> extends AbstractObservableWithUpstream<T, T> {
    final long delay;
    final boolean delayError;
    final Scheduler scheduler;
    final TimeUnit unit;

    public ObservableDelay(ObservableSource<T> observableSource, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        super(observableSource);
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.delayError = z;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new DelayObserver(this.delayError ? observer : new SerializedObserver(observer), this.delay, this.unit, this.scheduler.createWorker(), this.delayError));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class DelayObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        final long delay;
        final boolean delayError;
        Disposable s;
        final TimeUnit unit;
        final Scheduler.Worker w;

        DelayObserver(Observer<? super T> observer, long j, TimeUnit timeUnit, Scheduler.Worker worker, boolean z) {
            this.actual = observer;
            this.delay = j;
            this.unit = timeUnit;
            this.w = worker;
            this.delayError = z;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.w.schedule(new OnNext(t), this.delay, this.unit);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.w.schedule(new OnError(th), this.delayError ? this.delay : 0L, this.unit);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.w.schedule(new OnComplete(), this.delay, this.unit);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.s.dispose();
            this.w.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.w.isDisposed();
        }

        /* loaded from: classes3.dex */
        final class OnNext implements Runnable {
            private final T t;

            OnNext(T t) {
                DelayObserver.this = r1;
                this.t = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                DelayObserver.this.actual.onNext((T) this.t);
            }
        }

        /* loaded from: classes3.dex */
        final class OnError implements Runnable {
            private final Throwable throwable;

            OnError(Throwable th) {
                DelayObserver.this = r1;
                this.throwable = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    DelayObserver.this.actual.onError(this.throwable);
                } finally {
                    DelayObserver.this.w.dispose();
                }
            }
        }

        /* loaded from: classes3.dex */
        final class OnComplete implements Runnable {
            OnComplete() {
                DelayObserver.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    DelayObserver.this.actual.onComplete();
                } finally {
                    DelayObserver.this.w.dispose();
                }
            }
        }
    }
}