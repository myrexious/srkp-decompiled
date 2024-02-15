package io.reactivex.internal.schedulers;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.Functions;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public final class InstantPeriodicTask implements Callable<Void>, Disposable {
    static final FutureTask<Void> CANCELLED = new FutureTask<>(Functions.EMPTY_RUNNABLE, null);
    final ExecutorService executor;
    final AtomicReference<Future<?>> first = new AtomicReference<>();
    final AtomicReference<Future<?>> rest = new AtomicReference<>();
    Thread runner;
    final Runnable task;

    public InstantPeriodicTask(Runnable runnable, ExecutorService executorService) {
        this.task = runnable;
        this.executor = executorService;
    }

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        try {
            this.runner = Thread.currentThread();
            this.task.run();
            setRest(this.executor.submit(this));
            return null;
        } finally {
            this.runner = null;
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        AtomicReference<Future<?>> atomicReference = this.first;
        FutureTask<Void> futureTask = CANCELLED;
        Future<?> andSet = atomicReference.getAndSet(futureTask);
        if (andSet != null && andSet != futureTask) {
            andSet.cancel(this.runner != Thread.currentThread());
        }
        Future<?> andSet2 = this.rest.getAndSet(futureTask);
        if (andSet2 == null || andSet2 == futureTask) {
            return;
        }
        andSet2.cancel(this.runner != Thread.currentThread());
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.first.get() == CANCELLED;
    }

    public void setFirst(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.first.get();
            if (future2 == CANCELLED) {
                future.cancel(this.runner != Thread.currentThread());
            }
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.first, future2, future));
    }

    void setRest(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.rest.get();
            if (future2 == CANCELLED) {
                future.cancel(this.runner != Thread.currentThread());
            }
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.rest, future2, future));
    }
}
