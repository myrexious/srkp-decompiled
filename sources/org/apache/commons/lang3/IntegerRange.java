package org.apache.commons.lang3;

/* loaded from: classes2.dex */
public final class IntegerRange extends NumberRange<Integer> {
    private static final long serialVersionUID = 1;

    public static IntegerRange of(int i, int i2) {
        return of(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static IntegerRange of(Integer num, Integer num2) {
        return new IntegerRange(num, num2);
    }

    private IntegerRange(Integer num, Integer num2) {
        super(num, num2, null);
    }
}
