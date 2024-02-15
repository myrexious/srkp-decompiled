package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import org.apache.commons.lang3.exception.ExceptionUtils;

/* loaded from: classes2.dex */
public class Memoizer<I, O> implements Computable<I, O> {
    private final ConcurrentMap<I, Future<O>> cache;
    private final Function<? super I, ? extends Future<O>> mappingFunction;
    private final boolean recalculate;

    public Memoizer(Computable<I, O> computable) {
        this((Computable) computable, false);
    }

    public Memoizer(final Computable<I, O> computable, boolean z) {
        this.cache = new ConcurrentHashMap();
        this.recalculate = z;
        this.mappingFunction = new Function() { // from class: org.apache.commons.lang3.concurrent.Memoizer$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Future run;
                run = FutureTasks.run(new Callable() { // from class: org.apache.commons.lang3.concurrent.Memoizer$$ExternalSyntheticLambda0
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        Object compute;
                        compute = Computable.this.compute(obj);
                        return compute;
                    }
                });
                return run;
            }
        };
    }

    public Memoizer(Function<I, O> function) {
        this((Function) function, false);
    }

    public Memoizer(final Function<I, O> function, boolean z) {
        this.cache = new ConcurrentHashMap();
        this.recalculate = z;
        this.mappingFunction = new Function() { // from class: org.apache.commons.lang3.concurrent.Memoizer$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Future run;
                run = FutureTasks.run(new Callable() { // from class: org.apache.commons.lang3.concurrent.Memoizer$$ExternalSyntheticLambda3
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        Object apply;
                        apply = r1.apply(obj);
                        return apply;
                    }
                });
                return run;
            }
        };
    }

    @Override // org.apache.commons.lang3.concurrent.Computable
    public O compute(I i) throws InterruptedException {
        while (true) {
            Future<O> computeIfAbsent = this.cache.computeIfAbsent(i, this.mappingFunction);
            try {
                return computeIfAbsent.get();
            } catch (CancellationException unused) {
                this.cache.remove(i, computeIfAbsent);
            } catch (ExecutionException e) {
                if (this.recalculate) {
                    this.cache.remove(i, computeIfAbsent);
                }
                throw launderException(e.getCause());
            }
        }
    }

    private RuntimeException launderException(Throwable th) {
        throw new IllegalStateException("Unchecked exception", (Throwable) ExceptionUtils.throwUnchecked(th));
    }
}
