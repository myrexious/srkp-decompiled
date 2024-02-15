package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

/* loaded from: classes2.dex */
public interface FailableIntUnaryOperator<E extends Throwable> {
    public static final FailableIntUnaryOperator NOP = new FailableIntUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableIntUnaryOperator$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableIntUnaryOperator
        public final int applyAsInt(int i) {
            return FailableIntUnaryOperator.lambda$static$0(i);
        }
    };

    static /* synthetic */ int lambda$identity$1(int i) throws Throwable {
        return i;
    }

    static /* synthetic */ int lambda$static$0(int i) throws Throwable {
        return 0;
    }

    int applyAsInt(int i) throws Throwable;

    static <E extends Throwable> FailableIntUnaryOperator<E> identity() {
        return new FailableIntUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableIntUnaryOperator$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableIntUnaryOperator
            public final int applyAsInt(int i) {
                return FailableIntUnaryOperator.lambda$identity$1(i);
            }
        };
    }

    static <E extends Throwable> FailableIntUnaryOperator<E> nop() {
        return NOP;
    }

    default FailableIntUnaryOperator<E> andThen(final FailableIntUnaryOperator<E> failableIntUnaryOperator) {
        Objects.requireNonNull(failableIntUnaryOperator);
        return new FailableIntUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableIntUnaryOperator$$ExternalSyntheticLambda3
            @Override // org.apache.commons.lang3.function.FailableIntUnaryOperator
            public final int applyAsInt(int i) {
                int applyAsInt;
                applyAsInt = failableIntUnaryOperator.applyAsInt(FailableIntUnaryOperator.this.applyAsInt(i));
                return applyAsInt;
            }
        };
    }

    default FailableIntUnaryOperator<E> compose(final FailableIntUnaryOperator<E> failableIntUnaryOperator) {
        Objects.requireNonNull(failableIntUnaryOperator);
        return new FailableIntUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableIntUnaryOperator$$ExternalSyntheticLambda2
            @Override // org.apache.commons.lang3.function.FailableIntUnaryOperator
            public final int applyAsInt(int i) {
                int applyAsInt;
                applyAsInt = FailableIntUnaryOperator.this.applyAsInt(failableIntUnaryOperator.applyAsInt(i));
                return applyAsInt;
            }
        };
    }
}
