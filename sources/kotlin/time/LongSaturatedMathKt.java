package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.time.Duration;

/* compiled from: longSaturatedMath.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\"\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\"\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\n\u001a \u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a \u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a \u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\r\u0010\u0016\u001a\u00020\u0017*\u00020\u0001H\u0082\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"checkInfiniteSumDefined", "", "longNs", TypedValues.TransitionType.S_DURATION, "Lkotlin/time/Duration;", "durationNs", "checkInfiniteSumDefined-PjuGub4", "(JJJ)J", "saturatingAdd", "saturatingAdd-pTJri5U", "(JJ)J", "saturatingAddInHalves", "saturatingAddInHalves-pTJri5U", "saturatingDiff", "valueNs", "originNs", "saturatingFiniteDiff", "value1Ns", "value2Ns", "saturatingOriginsDiff", "origin1Ns", "origin2Ns", "isSaturated", "", "kotlin-stdlib"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LongSaturatedMathKt {
    private static final boolean isSaturated(long j) {
        return ((j - 1) | 1) == Long.MAX_VALUE;
    }

    /* renamed from: saturatingAdd-pTJri5U */
    public static final long m1729saturatingAddpTJri5U(long j, long j2) {
        long m1621getInWholeNanosecondsimpl = Duration.m1621getInWholeNanosecondsimpl(j2);
        if (((j - 1) | 1) == Long.MAX_VALUE) {
            return m1728checkInfiniteSumDefinedPjuGub4(j, j2, m1621getInWholeNanosecondsimpl);
        }
        if ((1 | (m1621getInWholeNanosecondsimpl - 1)) == Long.MAX_VALUE) {
            return m1730saturatingAddInHalvespTJri5U(j, j2);
        }
        long j3 = j + m1621getInWholeNanosecondsimpl;
        return ((j ^ j3) & (m1621getInWholeNanosecondsimpl ^ j3)) < 0 ? j < 0 ? Long.MIN_VALUE : Long.MAX_VALUE : j3;
    }

    /* renamed from: checkInfiniteSumDefined-PjuGub4 */
    private static final long m1728checkInfiniteSumDefinedPjuGub4(long j, long j2, long j3) {
        if (!Duration.m1633isInfiniteimpl(j2) || (j ^ j3) >= 0) {
            return j;
        }
        throw new IllegalArgumentException("Summing infinities of different signs");
    }

    /* renamed from: saturatingAddInHalves-pTJri5U */
    private static final long m1730saturatingAddInHalvespTJri5U(long j, long j2) {
        long m1604divUwyO8pc = Duration.m1604divUwyO8pc(j2, 2);
        if (((Duration.m1621getInWholeNanosecondsimpl(m1604divUwyO8pc) - 1) | 1) == Long.MAX_VALUE) {
            return (long) (j + Duration.m1644toDoubleimpl(j2, DurationUnit.NANOSECONDS));
        }
        return m1729saturatingAddpTJri5U(m1729saturatingAddpTJri5U(j, m1604divUwyO8pc), Duration.m1636minusLRDsOJo(j2, m1604divUwyO8pc));
    }

    public static final long saturatingDiff(long j, long j2) {
        if ((1 | (j2 - 1)) == Long.MAX_VALUE) {
            return Duration.m1653unaryMinusUwyO8pc(DurationKt.toDuration(j2, DurationUnit.DAYS));
        }
        return saturatingFiniteDiff(j, j2);
    }

    public static final long saturatingOriginsDiff(long j, long j2) {
        if (((j2 - 1) | 1) == Long.MAX_VALUE) {
            if (j == j2) {
                return Duration.Companion.m1703getZEROUwyO8pc();
            }
            return Duration.m1653unaryMinusUwyO8pc(DurationKt.toDuration(j2, DurationUnit.DAYS));
        }
        if ((1 | (j - 1)) == Long.MAX_VALUE) {
            return DurationKt.toDuration(j, DurationUnit.DAYS);
        }
        return saturatingFiniteDiff(j, j2);
    }

    private static final long saturatingFiniteDiff(long j, long j2) {
        long j3 = j - j2;
        if (((j3 ^ j) & (~(j3 ^ j2))) < 0) {
            long j4 = (long) DurationKt.NANOS_IN_MILLIS;
            long j5 = (j % j4) - (j2 % j4);
            Duration.Companion companion = Duration.Companion;
            long duration = DurationKt.toDuration((j / j4) - (j2 / j4), DurationUnit.MILLISECONDS);
            Duration.Companion companion2 = Duration.Companion;
            return Duration.m1637plusLRDsOJo(duration, DurationKt.toDuration(j5, DurationUnit.NANOSECONDS));
        }
        Duration.Companion companion3 = Duration.Companion;
        return DurationKt.toDuration(j3, DurationUnit.NANOSECONDS);
    }
}
