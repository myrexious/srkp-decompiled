package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UArraySorting.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UArraySortingKt {
    /* renamed from: partition-4UcCI2c */
    private static final int m731partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte m351getw2LRezQ = UByteArray.m351getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m351getw2LRezQ2 = UByteArray.m351getw2LRezQ(bArr, i) & UByte.MAX_VALUE;
                i3 = m351getw2LRezQ & UByte.MAX_VALUE;
                if (Intrinsics.compare(m351getw2LRezQ2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m351getw2LRezQ(bArr, i2) & UByte.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte m351getw2LRezQ3 = UByteArray.m351getw2LRezQ(bArr, i);
                UByteArray.m356setVurrAj0(bArr, i, UByteArray.m351getw2LRezQ(bArr, i2));
                UByteArray.m356setVurrAj0(bArr, i2, m351getw2LRezQ3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c */
    private static final void m735quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int m731partition4UcCI2c = m731partition4UcCI2c(bArr, i, i2);
        int i3 = m731partition4UcCI2c - 1;
        if (i < i3) {
            m735quickSort4UcCI2c(bArr, i, i3);
        }
        if (m731partition4UcCI2c < i2) {
            m735quickSort4UcCI2c(bArr, m731partition4UcCI2c, i2);
        }
    }

    /* renamed from: partition-Aa5vz7o */
    private static final int m732partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short m614getMh2AYeg = UShortArray.m614getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m614getMh2AYeg2 = UShortArray.m614getMh2AYeg(sArr, i) & UShort.MAX_VALUE;
                i3 = m614getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(m614getMh2AYeg2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m614getMh2AYeg(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short m614getMh2AYeg3 = UShortArray.m614getMh2AYeg(sArr, i);
                UShortArray.m619set01HTLdE(sArr, i, UShortArray.m614getMh2AYeg(sArr, i2));
                UShortArray.m619set01HTLdE(sArr, i2, m614getMh2AYeg3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o */
    private static final void m736quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int m732partitionAa5vz7o = m732partitionAa5vz7o(sArr, i, i2);
        int i3 = m732partitionAa5vz7o - 1;
        if (i < i3) {
            m736quickSortAa5vz7o(sArr, i, i3);
        }
        if (m732partitionAa5vz7o < i2) {
            m736quickSortAa5vz7o(sArr, m732partitionAa5vz7o, i2);
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:25:0x0012 */
    /* JADX WARN: Incorrect condition in loop: B:28:0x001f */
    /* renamed from: partition-oBK06Vg */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final int m733partitionoBK06Vg(int[] r3, int r4, int r5) {
        /*
            int r0 = r4 + r5
            int r0 = r0 / 2
            int r0 = kotlin.UIntArray.m430getpVg5ArA(r3, r0)
        L8:
            if (r4 > r5) goto L39
        La:
            int r1 = kotlin.UIntArray.m430getpVg5ArA(r3, r4)
            int r1 = kotlin.UByte$$ExternalSyntheticBackport0.m$2(r1, r0)
            if (r1 >= 0) goto L17
            int r4 = r4 + 1
            goto La
        L17:
            int r1 = kotlin.UIntArray.m430getpVg5ArA(r3, r5)
            int r1 = kotlin.UByte$$ExternalSyntheticBackport0.m$2(r1, r0)
            if (r1 <= 0) goto L24
            int r5 = r5 + (-1)
            goto L17
        L24:
            if (r4 > r5) goto L8
            int r1 = kotlin.UIntArray.m430getpVg5ArA(r3, r4)
            int r2 = kotlin.UIntArray.m430getpVg5ArA(r3, r5)
            kotlin.UIntArray.m435setVXSXFK8(r3, r4, r2)
            kotlin.UIntArray.m435setVXSXFK8(r3, r5, r1)
            int r4 = r4 + 1
            int r5 = r5 + (-1)
            goto L8
        L39:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.UArraySortingKt.m733partitionoBK06Vg(int[], int, int):int");
    }

    /* renamed from: quickSort-oBK06Vg */
    private static final void m737quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int m733partitionoBK06Vg = m733partitionoBK06Vg(iArr, i, i2);
        int i3 = m733partitionoBK06Vg - 1;
        if (i < i3) {
            m737quickSortoBK06Vg(iArr, i, i3);
        }
        if (m733partitionoBK06Vg < i2) {
            m737quickSortoBK06Vg(iArr, m733partitionoBK06Vg, i2);
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:25:0x0012 */
    /* JADX WARN: Incorrect condition in loop: B:28:0x001f */
    /* renamed from: partition--nroSd4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final int m730partitionnroSd4(long[] r6, int r7, int r8) {
        /*
            int r0 = r7 + r8
            int r0 = r0 / 2
            long r0 = kotlin.ULongArray.m509getsVKNKU(r6, r0)
        L8:
            if (r7 > r8) goto L39
        La:
            long r2 = kotlin.ULongArray.m509getsVKNKU(r6, r7)
            int r2 = kotlin.UByte$$ExternalSyntheticBackport0.m(r2, r0)
            if (r2 >= 0) goto L17
            int r7 = r7 + 1
            goto La
        L17:
            long r2 = kotlin.ULongArray.m509getsVKNKU(r6, r8)
            int r2 = kotlin.UByte$$ExternalSyntheticBackport0.m(r2, r0)
            if (r2 <= 0) goto L24
            int r8 = r8 + (-1)
            goto L17
        L24:
            if (r7 > r8) goto L8
            long r2 = kotlin.ULongArray.m509getsVKNKU(r6, r7)
            long r4 = kotlin.ULongArray.m509getsVKNKU(r6, r8)
            kotlin.ULongArray.m514setk8EXiF4(r6, r7, r4)
            kotlin.ULongArray.m514setk8EXiF4(r6, r8, r2)
            int r7 = r7 + 1
            int r8 = r8 + (-1)
            goto L8
        L39:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.UArraySortingKt.m730partitionnroSd4(long[], int, int):int");
    }

    /* renamed from: quickSort--nroSd4 */
    private static final void m734quickSortnroSd4(long[] jArr, int i, int i2) {
        int m730partitionnroSd4 = m730partitionnroSd4(jArr, i, i2);
        int i3 = m730partitionnroSd4 - 1;
        if (i < i3) {
            m734quickSortnroSd4(jArr, i, i3);
        }
        if (m730partitionnroSd4 < i2) {
            m734quickSortnroSd4(jArr, m730partitionnroSd4, i2);
        }
    }

    /* renamed from: sortArray-4UcCI2c */
    public static final void m739sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m735quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* renamed from: sortArray-Aa5vz7o */
    public static final void m740sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m736quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* renamed from: sortArray-oBK06Vg */
    public static final void m741sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m737quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* renamed from: sortArray--nroSd4 */
    public static final void m738sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m734quickSortnroSd4(array, i, i2 - 1);
    }
}
