package org.apache.commons.lang3;

import java.util.concurrent.ThreadLocalRandom;

@Deprecated
/* loaded from: classes2.dex */
public class RandomUtils {
    public static boolean nextBoolean() {
        return random().nextBoolean();
    }

    public static byte[] nextBytes(int i) {
        Validate.isTrue(i >= 0, "Count cannot be negative.", new Object[0]);
        byte[] bArr = new byte[i];
        random().nextBytes(bArr);
        return bArr;
    }

    public static double nextDouble() {
        return nextDouble(0.0d, Double.MAX_VALUE);
    }

    public static double nextDouble(double d, double d2) {
        Validate.isTrue(d2 >= d, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(d >= 0.0d, "Both range values must be non-negative.", new Object[0]);
        return d == d2 ? d : d + ((d2 - d) * random().nextDouble());
    }

    public static float nextFloat() {
        return nextFloat(0.0f, Float.MAX_VALUE);
    }

    public static float nextFloat(float f, float f2) {
        Validate.isTrue(f2 >= f, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(f >= 0.0f, "Both range values must be non-negative.", new Object[0]);
        return f == f2 ? f : f + ((f2 - f) * random().nextFloat());
    }

    public static int nextInt() {
        return nextInt(0, Integer.MAX_VALUE);
    }

    public static int nextInt(int i, int i2) {
        Validate.isTrue(i2 >= i, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(i >= 0, "Both range values must be non-negative.", new Object[0]);
        return i == i2 ? i : i + random().nextInt(i2 - i);
    }

    public static long nextLong() {
        return nextLong(Long.MAX_VALUE);
    }

    private static long nextLong(long j) {
        long nextLong;
        long j2;
        do {
            nextLong = random().nextLong() >>> 1;
            j2 = nextLong % j;
        } while ((nextLong - j2) + (j - 1) < 0);
        return j2;
    }

    public static long nextLong(long j, long j2) {
        Validate.isTrue(j2 >= j, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(j >= 0, "Both range values must be non-negative.", new Object[0]);
        return j == j2 ? j : j + nextLong(j2 - j);
    }

    private static ThreadLocalRandom random() {
        return ThreadLocalRandom.current();
    }
}
