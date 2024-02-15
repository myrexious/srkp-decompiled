package org.apache.commons.lang3;

/* loaded from: classes2.dex */
public final class LongRange extends NumberRange<Long> {
    private static final long serialVersionUID = 1;

    public static LongRange of(long j, long j2) {
        return of(Long.valueOf(j), Long.valueOf(j2));
    }

    public static LongRange of(Long l, Long l2) {
        return new LongRange(l, l2);
    }

    private LongRange(Long l, Long l2) {
        super(l, l2, null);
    }
}
