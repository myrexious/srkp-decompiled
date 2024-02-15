package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface FailableDoubleToIntFunction<E extends Throwable> {
    public static final FailableDoubleToIntFunction NOP = new FailableDoubleToIntFunction() { // from class: org.apache.commons.lang3.function.FailableDoubleToIntFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableDoubleToIntFunction
        public final int applyAsInt(double d) {
            return FailableDoubleToIntFunction.lambda$static$0(d);
        }
    };

    static /* synthetic */ int lambda$static$0(double d) throws Throwable {
        return 0;
    }

    int applyAsInt(double d) throws Throwable;

    static <E extends Throwable> FailableDoubleToIntFunction<E> nop() {
        return NOP;
    }
}
