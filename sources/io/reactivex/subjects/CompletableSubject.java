package io.reactivex.subjects;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public final class CompletableSubject extends Completable implements CompletableObserver {
    static final CompletableDisposable[] EMPTY = new CompletableDisposable[0];
    static final CompletableDisposable[] TERMINATED = new CompletableDisposable[0];
    Throwable error;
    final AtomicBoolean once = new AtomicBoolean();
    final AtomicReference<CompletableDisposable[]> observers = new AtomicReference<>(EMPTY);

    @CheckReturnValue
    public static CompletableSubject create() {
        return new CompletableSubject();
    }

    CompletableSubject() {
    }

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        if (this.observers.get() == TERMINATED) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.CompletableObserver
    public void onError(Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.once.compareAndSet(false, true)) {
            this.error = th;
            for (CompletableDisposable completableDisposable : this.observers.getAndSet(TERMINATED)) {
                completableDisposable.actual.onError(th);
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
    public void onComplete() {
        if (this.once.compareAndSet(false, true)) {
            for (CompletableDisposable completableDisposable : this.observers.getAndSet(TERMINATED)) {
                completableDisposable.actual.onComplete();
            }
        }
    }

    @Override // io.reactivex.Completable
    protected void subscribeActual(CompletableObserver completableObserver) {
        CompletableDisposable completableDisposable = new CompletableDisposable(completableObserver, this);
        completableObserver.onSubscribe(completableDisposable);
        if (add(completableDisposable)) {
            if (completableDisposable.isDisposed()) {
                remove(completableDisposable);
                return;
            }
            return;
        }
        Throwable th = this.error;
        if (th != null) {
            completableObserver.onError(th);
        } else {
            completableObserver.onComplete();
        }
    }

    boolean add(CompletableDisposable completableDisposable) {
        CompletableDisposable[] completableDisposableArr;
        CompletableDisposable[] completableDisposableArr2;
        do {
            completableDisposableArr = this.observers.get();
            if (completableDisposableArr == TERMINATED) {
                return false;
            }
            int length = completableDisposableArr.length;
            completableDisposableArr2 = new CompletableDisposable[length + 1];
            System.arraycopy(completableDisposableArr, 0, completableDisposableArr2, 0, length);
            completableDisposableArr2[length] = completableDisposable;
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observers, completableDisposableArr, completableDisposableArr2));
        return true;
    }

    void remove(CompletableDisposable completableDisposable) {
        CompletableDisposable[] completableDisposableArr;
        CompletableDisposable[] completableDisposableArr2;
        do {
            completableDisposableArr = this.observers.get();
            int length = completableDisposableArr.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (completableDisposableArr[i] == completableDisposable) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                completableDisposableArr2 = EMPTY;
            } else {
                CompletableDisposable[] completableDisposableArr3 = new CompletableDisposable[length - 1];
                System.arraycopy(completableDisposableArr, 0, completableDisposableArr3, 0, i);
                System.arraycopy(completableDisposableArr, i + 1, completableDisposableArr3, i, (length - i) - 1);
                completableDisposableArr2 = completableDisposableArr3;
            }
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observers, completableDisposableArr, completableDisposableArr2));
    }

    public Throwable getThrowable() {
        if (this.observers.get() == TERMINATED) {
            return this.error;
        }
        return null;
    }

    public boolean hasThrowable() {
        return this.observers.get() == TERMINATED && this.error != null;
    }

    public boolean hasComplete() {
        return this.observers.get() == TERMINATED && this.error == null;
    }

    public boolean hasObservers() {
        return this.observers.get().length != 0;
    }

    int observerCount() {
        return this.observers.get().length;
    }

    /* loaded from: classes3.dex */
    public static final class CompletableDisposable extends AtomicReference<CompletableSubject> implements Disposable {
        private static final long serialVersionUID = -7650903191002190468L;
        final CompletableObserver actual;

        CompletableDisposable(CompletableObserver completableObserver, CompletableSubject completableSubject) {
            this.actual = completableObserver;
            lazySet(completableSubject);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            CompletableSubject andSet = getAndSet(null);
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
