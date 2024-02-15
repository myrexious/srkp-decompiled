package org.apache.commons.lang3.function;

import java.util.function.Consumer;
import java.util.function.Function;

/* loaded from: classes2.dex */
public class Consumers {
    private static final Consumer NOP;

    static {
        final Function identity = Function.identity();
        identity.getClass();
        NOP = new Consumer() { // from class: org.apache.commons.lang3.function.Consumers$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                identity.apply(obj);
            }
        };
    }

    private Consumers() {
    }

    public static <T> Consumer<T> nop() {
        return NOP;
    }
}
