package com.fasterxml.jackson.core.io.doubleparser;

import androidx.work.WorkRequest;
import java.math.BigInteger;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes.dex */
class FastIntegerMath {
    public static final BigInteger FIVE = BigInteger.valueOf(5);
    static final BigInteger TEN_POW_16 = BigInteger.valueOf(10000000000000000L);
    static final BigInteger FIVE_POW_16 = BigInteger.valueOf(152587890625L);
    private static final BigInteger[] SMALL_POWERS_OF_TEN = {BigInteger.ONE, BigInteger.TEN, BigInteger.valueOf(100), BigInteger.valueOf(1000), BigInteger.valueOf(WorkRequest.MIN_BACKOFF_MILLIS), BigInteger.valueOf(100000), BigInteger.valueOf(1000000), BigInteger.valueOf(10000000), BigInteger.valueOf(100000000), BigInteger.valueOf(1000000000), BigInteger.valueOf(10000000000L), BigInteger.valueOf(100000000000L), BigInteger.valueOf(1000000000000L), BigInteger.valueOf(10000000000000L), BigInteger.valueOf(100000000000000L), BigInteger.valueOf(1000000000000000L)};

    public static long estimateNumBits(long j) {
        return ((j * 3402) >>> 10) + 1;
    }

    public static int splitFloor16(int i, int i2) {
        return i2 - ((((i2 - ((i + i2) >>> 1)) + 15) >> 4) << 4);
    }

    private FastIntegerMath() {
    }

    public static BigInteger computePowerOfTen(NavigableMap<Integer, BigInteger> navigableMap, int i) {
        BigInteger[] bigIntegerArr = SMALL_POWERS_OF_TEN;
        if (i < bigIntegerArr.length) {
            return bigIntegerArr[i];
        }
        if (navigableMap != null) {
            Map.Entry<Integer, BigInteger> floorEntry = navigableMap.floorEntry(Integer.valueOf(i));
            Integer key = floorEntry.getKey();
            if (key.intValue() == i) {
                return floorEntry.getValue();
            }
            return FftMultiplier.multiply(floorEntry.getValue(), computePowerOfTen(navigableMap, i - key.intValue()));
        }
        return FIVE.pow(i).shiftLeft(i);
    }

    static BigInteger computeTenRaisedByNFloor16Recursive(NavigableMap<Integer, BigInteger> navigableMap, int i) {
        int i2 = i & (-16);
        Map.Entry<Integer, BigInteger> floorEntry = navigableMap.floorEntry(Integer.valueOf(i2));
        int intValue = floorEntry.getKey().intValue();
        BigInteger value = floorEntry.getValue();
        if (intValue == i2) {
            return value;
        }
        int i3 = i2 - intValue;
        BigInteger bigInteger = (BigInteger) navigableMap.get(Integer.valueOf(i3));
        if (bigInteger == null) {
            bigInteger = computeTenRaisedByNFloor16Recursive(navigableMap, i3);
            navigableMap.put(Integer.valueOf(i3), bigInteger);
        }
        return FftMultiplier.multiply(value, bigInteger);
    }

    public static NavigableMap<Integer, BigInteger> createPowersOfTenFloor16Map() {
        TreeMap treeMap = new TreeMap();
        treeMap.put(0, BigInteger.ONE);
        treeMap.put(16, TEN_POW_16);
        return treeMap;
    }

    public static NavigableMap<Integer, BigInteger> fillPowersOf10Floor16(int i, int i2) {
        TreeMap treeMap = new TreeMap();
        treeMap.put(0, BigInteger.valueOf(5L));
        treeMap.put(16, FIVE_POW_16);
        fillPowersOfNFloor16Recursive(treeMap, i, i2);
        for (Map.Entry entry : treeMap.entrySet()) {
            entry.setValue(((BigInteger) entry.getValue()).shiftLeft(((Integer) entry.getKey()).intValue()));
        }
        return treeMap;
    }

    public static void fillPowersOfNFloor16Recursive(NavigableMap<Integer, BigInteger> navigableMap, int i, int i2) {
        if (i2 - i <= 18) {
            return;
        }
        int splitFloor16 = splitFloor16(i, i2);
        int i3 = i2 - splitFloor16;
        if (navigableMap.containsKey(Integer.valueOf(i3))) {
            return;
        }
        fillPowersOfNFloor16Recursive(navigableMap, i, splitFloor16);
        fillPowersOfNFloor16Recursive(navigableMap, splitFloor16, i2);
        navigableMap.put(Integer.valueOf(i3), computeTenRaisedByNFloor16Recursive(navigableMap, i3));
    }

    public static UInt128 fullMultiplication(long j, long j2) {
        long j3 = j & BodyPartID.bodyIdMax;
        long j4 = j >>> 32;
        long j5 = j2 & BodyPartID.bodyIdMax;
        long j6 = j2 >>> 32;
        long j7 = j4 * j6;
        long j8 = j6 * j3;
        long j9 = j3 * j5;
        long j10 = (j4 * j5) + (j9 >>> 32) + (j8 & BodyPartID.bodyIdMax);
        return new UInt128(j7 + (j10 >>> 32) + (j8 >>> 32), (j10 << 32) | (BodyPartID.bodyIdMax & j9));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class UInt128 {
        final long high;
        final long low;

        private UInt128(long j, long j2) {
            this.high = j;
            this.low = j2;
        }
    }
}
