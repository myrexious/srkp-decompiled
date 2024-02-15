package org.apache.commons.lang3.function;

import java.util.Objects;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v);

    default TriConsumer<T, U, V> andThen(final TriConsumer<? super T, ? super U, ? super V> triConsumer) {
        Objects.requireNonNull(triConsumer);
        return new TriConsumer() { // from class: org.apache.commons.lang3.function.TriConsumer$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.TriConsumer
            public final void accept(Object obj, Object obj2, Object obj3) {
                TriConsumer.lambda$andThen$0(TriConsumer.this, triConsumer, obj, obj2, obj3);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$0(TriConsumer _this, TriConsumer triConsumer, Object obj, Object obj2, Object obj3) {
        _this.accept(obj, obj2, obj3);
        triConsumer.accept(obj, obj2, obj3);
    }
}
