package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface FailableFunction<T, R, E extends Throwable> {
    public static final FailableFunction NOP = new FailableFunction() { // from class: org.apache.commons.lang3.function.FailableFunction$$ExternalSyntheticLambda2
        @Override // org.apache.commons.lang3.function.FailableFunction
        public final Object apply(Object obj) {
            return FailableFunction.lambda$static$0(obj);
        }
    };

    static /* synthetic */ Object lambda$identity$1(Object obj) throws Throwable {
        return obj;
    }

    static /* synthetic */ Object lambda$static$0(Object obj) throws Throwable {
        return null;
    }

    R apply(T t) throws Throwable;

    static <T, E extends Throwable> FailableFunction<T, T, E> identity() {
        return new FailableFunction() { // from class: org.apache.commons.lang3.function.FailableFunction$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableFunction
            public final Object apply(Object obj) {
                return FailableFunction.lambda$identity$1(obj);
            }
        };
    }

    static <T, R, E extends Throwable> FailableFunction<T, R, E> nop() {
        return NOP;
    }

    default <V> FailableFunction<T, V, E> andThen(final FailableFunction<? super R, ? extends V, E> failableFunction) {
        Objects.requireNonNull(failableFunction);
        return new FailableFunction() { // from class: org.apache.commons.lang3.function.FailableFunction$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableFunction
            public final Object apply(Object obj) {
                Object apply;
                apply = failableFunction.apply(FailableFunction.this.apply(obj));
                return apply;
            }
        };
    }

    default <V> FailableFunction<V, R, E> compose(final FailableFunction<? super V, ? extends T, E> failableFunction) {
        Objects.requireNonNull(failableFunction);
        return new FailableFunction() { // from class: org.apache.commons.lang3.function.FailableFunction$$ExternalSyntheticLambda3
            @Override // org.apache.commons.lang3.function.FailableFunction
            public final Object apply(Object obj) {
                Object apply;
                apply = FailableFunction.this.apply(failableFunction.apply(obj));
                return apply;
            }
        };
    }
}
