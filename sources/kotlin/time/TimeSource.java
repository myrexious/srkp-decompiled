package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;

/* compiled from: TimeSource.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u00042\u00020\u0001:\u0003\u0004\u0005\u0006J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lkotlin/time/TimeSource;", "", "markNow", "Lkotlin/time/TimeMark;", "Companion", "Monotonic", "WithComparableMarks", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface TimeSource {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: TimeSource.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lkotlin/time/TimeSource$WithComparableMarks;", "Lkotlin/time/TimeSource;", "markNow", "Lkotlin/time/ComparableTimeMark;", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface WithComparableMarks extends TimeSource {
        @Override // kotlin.time.TimeSource
        ComparableTimeMark markNow();
    }

    TimeMark markNow();

    /* compiled from: TimeSource.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016\u0082\u0002\b\n\u0002\b!\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/time/TimeSource$Monotonic;", "Lkotlin/time/TimeSource$WithComparableMarks;", "()V", "markNow", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "markNow-z9LOYto", "()J", "toString", "", "ValueTimeMark", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Monotonic implements WithComparableMarks {
        public static final Monotonic INSTANCE = new Monotonic();

        private Monotonic() {
        }

        @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
        public /* bridge */ /* synthetic */ ComparableTimeMark markNow() {
            return ValueTimeMark.m1740boximpl(m1739markNowz9LOYto());
        }

        @Override // kotlin.time.TimeSource
        public /* bridge */ /* synthetic */ TimeMark markNow() {
            return ValueTimeMark.m1740boximpl(m1739markNowz9LOYto());
        }

        /* renamed from: markNow-z9LOYto */
        public long m1739markNowz9LOYto() {
            return MonotonicTimeSource.INSTANCE.m1734markNowz9LOYto();
        }

        public String toString() {
            return MonotonicTimeSource.INSTANCE.toString();
        }

        /* compiled from: TimeSource.kt */
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u0018\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\u00020\rH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u0006J\u001a\u0010\u000f\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0018\u0010\u0016J\u0010\u0010\u0019\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001e\u0010\u001c\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0001H\u0096\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\rH\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u001b\u0010\u001c\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\"\u0010!J\u001b\u0010#\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\rH\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b$\u0010!J\u0010\u0010%\u001a\u00020&HÖ\u0001¢\u0006\u0004\b'\u0010(R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00060\u0003j\u0002`\u0004ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006)"}, d2 = {"Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "Lkotlin/time/ComparableTimeMark;", "reading", "", "Lkotlin/time/ValueTimeMarkReading;", "constructor-impl", "(J)J", "compareTo", "", "other", "compareTo-6eNON_k", "(JJ)I", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "hasNotPassedNow", "hasNotPassedNow-impl", "(J)Z", "hasPassedNow", "hasPassedNow-impl", "hashCode", "hashCode-impl", "(J)I", "minus", "minus-UwyO8pc", "(JLkotlin/time/ComparableTimeMark;)J", TypedValues.TransitionType.S_DURATION, "minus-LRDsOJo", "(JJ)J", "minus-6eNON_k", "plus", "plus-LRDsOJo", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
        @JvmInline
        /* loaded from: classes3.dex */
        public static final class ValueTimeMark implements ComparableTimeMark {
            private final long reading;

            /* renamed from: box-impl */
            public static final /* synthetic */ ValueTimeMark m1740boximpl(long j) {
                return new ValueTimeMark(j);
            }

            /* renamed from: constructor-impl */
            public static long m1743constructorimpl(long j) {
                return j;
            }

            /* renamed from: equals-impl */
            public static boolean m1745equalsimpl(long j, Object obj) {
                return (obj instanceof ValueTimeMark) && j == ((ValueTimeMark) obj).m1757unboximpl();
            }

            /* renamed from: equals-impl0 */
            public static final boolean m1746equalsimpl0(long j, long j2) {
                return j == j2;
            }

            /* renamed from: hashCode-impl */
            public static int m1749hashCodeimpl(long j) {
                return Long.hashCode(j);
            }

            /* renamed from: toString-impl */
            public static String m1754toStringimpl(long j) {
                return "ValueTimeMark(reading=" + j + ')';
            }

            @Override // kotlin.time.ComparableTimeMark
            public boolean equals(Object obj) {
                return m1745equalsimpl(this.reading, obj);
            }

            @Override // kotlin.time.ComparableTimeMark
            public int hashCode() {
                return m1749hashCodeimpl(this.reading);
            }

            public String toString() {
                return m1754toStringimpl(this.reading);
            }

            /* renamed from: unbox-impl */
            public final /* synthetic */ long m1757unboximpl() {
                return this.reading;
            }

            @Override // java.lang.Comparable
            public int compareTo(ComparableTimeMark comparableTimeMark) {
                return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* renamed from: minus-LRDsOJo */
            public /* bridge */ /* synthetic */ ComparableTimeMark mo1591minusLRDsOJo(long j) {
                return m1740boximpl(m1755minusLRDsOJo(j));
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: minus-LRDsOJo */
            public /* bridge */ /* synthetic */ TimeMark mo1591minusLRDsOJo(long j) {
                return m1740boximpl(m1755minusLRDsOJo(j));
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* renamed from: plus-LRDsOJo */
            public /* bridge */ /* synthetic */ ComparableTimeMark mo1593plusLRDsOJo(long j) {
                return m1740boximpl(m1756plusLRDsOJo(j));
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: plus-LRDsOJo */
            public /* bridge */ /* synthetic */ TimeMark mo1593plusLRDsOJo(long j) {
                return m1740boximpl(m1756plusLRDsOJo(j));
            }

            private /* synthetic */ ValueTimeMark(long j) {
                this.reading = j;
            }

            /* renamed from: compareTo-impl */
            public static int m1742compareToimpl(long j, ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m1740boximpl(j).compareTo(other);
            }

            /* renamed from: elapsedNow-UwyO8pc */
            public static long m1744elapsedNowUwyO8pc(long j) {
                return MonotonicTimeSource.INSTANCE.m1733elapsedFrom6eNON_k(j);
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: elapsedNow-UwyO8pc */
            public long mo1590elapsedNowUwyO8pc() {
                return m1744elapsedNowUwyO8pc(this.reading);
            }

            /* renamed from: plus-LRDsOJo */
            public static long m1753plusLRDsOJo(long j, long j2) {
                return MonotonicTimeSource.INSTANCE.m1731adjustReading6QKq23U(j, j2);
            }

            /* renamed from: plus-LRDsOJo */
            public long m1756plusLRDsOJo(long j) {
                return m1753plusLRDsOJo(this.reading, j);
            }

            /* renamed from: minus-LRDsOJo */
            public static long m1751minusLRDsOJo(long j, long j2) {
                return MonotonicTimeSource.INSTANCE.m1731adjustReading6QKq23U(j, Duration.m1653unaryMinusUwyO8pc(j2));
            }

            /* renamed from: minus-LRDsOJo */
            public long m1755minusLRDsOJo(long j) {
                return m1751minusLRDsOJo(this.reading, j);
            }

            /* renamed from: hasPassedNow-impl */
            public static boolean m1748hasPassedNowimpl(long j) {
                return !Duration.m1634isNegativeimpl(m1744elapsedNowUwyO8pc(j));
            }

            @Override // kotlin.time.TimeMark
            public boolean hasPassedNow() {
                return m1748hasPassedNowimpl(this.reading);
            }

            /* renamed from: hasNotPassedNow-impl */
            public static boolean m1747hasNotPassedNowimpl(long j) {
                return Duration.m1634isNegativeimpl(m1744elapsedNowUwyO8pc(j));
            }

            @Override // kotlin.time.TimeMark
            public boolean hasNotPassedNow() {
                return m1747hasNotPassedNowimpl(this.reading);
            }

            @Override // kotlin.time.ComparableTimeMark
            /* renamed from: minus-UwyO8pc */
            public long mo1592minusUwyO8pc(ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m1752minusUwyO8pc(this.reading, other);
            }

            /* renamed from: minus-UwyO8pc */
            public static long m1752minusUwyO8pc(long j, ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                if (!(other instanceof ValueTimeMark)) {
                    throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + ((Object) m1754toStringimpl(j)) + " and " + other);
                }
                return m1750minus6eNON_k(j, ((ValueTimeMark) other).m1757unboximpl());
            }

            /* renamed from: minus-6eNON_k */
            public static final long m1750minus6eNON_k(long j, long j2) {
                return MonotonicTimeSource.INSTANCE.m1732differenceBetweenfRLX17w(j, j2);
            }

            /* renamed from: compareTo-6eNON_k */
            public static final int m1741compareTo6eNON_k(long j, long j2) {
                return Duration.m1600compareToLRDsOJo(m1750minus6eNON_k(j, j2), Duration.Companion.m1703getZEROUwyO8pc());
            }
        }
    }

    /* compiled from: TimeSource.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lkotlin/time/TimeSource$Companion;", "", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
