package io.reactivex.internal.operators.maybe;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public final class MaybeCache<T> extends Maybe<T> implements MaybeObserver<T> {
    static final CacheDisposable[] EMPTY = new CacheDisposable[0];
    static final CacheDisposable[] TERMINATED = new CacheDisposable[0];
    Throwable error;
    final AtomicReference<CacheDisposable<T>[]> observers = new AtomicReference<>(EMPTY);
    final AtomicReference<MaybeSource<T>> source;
    T value;

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable disposable) {
    }

    public MaybeCache(MaybeSource<T> maybeSource) {
        this.source = new AtomicReference<>(maybeSource);
    }

    @Override // io.reactivex.Maybe
    protected void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        CacheDisposable<T> cacheDisposable = new CacheDisposable<>(maybeObserver, this);
        maybeObserver.onSubscribe(cacheDisposable);
        if (add(cacheDisposable)) {
            if (cacheDisposable.isDisposed()) {
                remove(cacheDisposable);
                return;
            }
            MaybeSource<T> andSet = this.source.getAndSet(null);
            if (andSet != null) {
                andSet.subscribe(this);
            }
        } else if (cacheDisposable.isDisposed()) {
        } else {
            Throwable th = this.error;
            if (th != null) {
                maybeObserver.onError(th);
                return;
            }
            Object obj = (T) this.value;
            if (obj != null) {
                maybeObserver.onSuccess(obj);
            } else {
                maybeObserver.onComplete();
            }
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T t) {
        CacheDisposable<T>[] andSet;
        this.value = t;
        for (CacheDisposable<T> cacheDisposable : this.observers.getAndSet(TERMINATED)) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.actual.onSuccess(t);
            }
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable th) {
        CacheDisposable<T>[] andSet;
        this.error = th;
        for (CacheDisposable<T> cacheDisposable : this.observers.getAndSet(TERMINATED)) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.actual.onError(th);
            }
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        CacheDisposable<T>[] andSet;
        for (CacheDisposable<T> cacheDisposable : this.observers.getAndSet(TERMINATED)) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.actual.onComplete();
            }
        }
    }

    boolean add(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable[] cacheDisposableArr2;
        do {
            cacheDisposableArr = this.observers.get();
            if (cacheDisposableArr == TERMINATED) {
                return false;
            }
            int length = cacheDisposableArr.length;
            cacheDisposableArr2 = new CacheDisposable[length + 1];
            System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr2, 0, length);
            cacheDisposableArr2[length] = cacheDisposable;
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observers, cacheDisposableArr, cacheDisposableArr2));
        return true;
    }

    void remove(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable[] cacheDisposableArr2;
        do {
            cacheDisposableArr = this.observers.get();
            int length = cacheDisposableArr.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (cacheDisposableArr[i] == cacheDisposable) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                cacheDisposableArr2 = EMPTY;
            } else {
                CacheDisposable[] cacheDisposableArr3 = new CacheDisposable[length - 1];
                System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr3, 0, i);
                System.arraycopy(cacheDisposableArr, i + 1, cacheDisposableArr3, i, (length - i) - 1);
                cacheDisposableArr2 = cacheDisposableArr3;
            }
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observers, cacheDisposableArr, cacheDisposableArr2));
    }

    /* loaded from: classes3.dex */
    public static final class CacheDisposable<T> extends AtomicReference<MaybeCache<T>> implements Disposable {
        private static final long serialVersionUID = -5791853038359966195L;
        final MaybeObserver<? super T> actual;

        CacheDisposable(MaybeObserver<? super T> maybeObserver, MaybeCache<T> maybeCache) {
            super(maybeCache);
            this.actual = maybeObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            MaybeCache<T> andSet = getAndSet(null);
            if (andSet != null) {
                andSet.remove(this);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
