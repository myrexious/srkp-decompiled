package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/* loaded from: classes.dex */
class BigSignificand {
    private static final long LONG_MASK = 4294967295L;
    private int firstNonZeroInt;
    private final int numInts;
    private final int[] x;

    public BigSignificand(long j) {
        if (j <= 0 || j >= 2147483647L) {
            throw new IllegalArgumentException("numBits=" + j);
        }
        int i = (((int) ((j + 63) >>> 6)) + 1) << 1;
        this.numInts = i;
        this.x = new int[i];
        this.firstNonZeroInt = i;
    }

    public void add(int i) {
        if (i == 0) {
            return;
        }
        long j = i & 4294967295L;
        int i2 = this.numInts;
        while (true) {
            i2--;
            if (j != 0) {
                long x = (x(i2) & 4294967295L) + j;
                x(i2, (int) x);
                j = x >>> 32;
            } else {
                this.firstNonZeroInt = Math.min(this.firstNonZeroInt, i2 + 1);
                return;
            }
        }
    }

    public void fma(int i, int i2) {
        long j = i & 4294967295L;
        long j2 = i2;
        int i3 = this.numInts;
        while (true) {
            i3--;
            if (i3 < this.firstNonZeroInt) {
                break;
            }
            long x = ((x(i3) & 4294967295L) * j) + j2;
            x(i3, (int) x);
            j2 = x >>> 32;
        }
        if (j2 != 0) {
            x(i3, (int) j2);
            this.firstNonZeroInt = i3;
        }
    }

    public BigInteger toBigInteger() {
        byte[] bArr = new byte[this.x.length << 2];
        IntBuffer asIntBuffer = ByteBuffer.wrap(bArr).asIntBuffer();
        int i = 0;
        while (true) {
            int[] iArr = this.x;
            if (i < iArr.length) {
                asIntBuffer.put(i, iArr[i]);
                i++;
            } else {
                return new BigInteger(bArr);
            }
        }
    }

    private void x(int i, int i2) {
        this.x[i] = i2;
    }

    private int x(int i) {
        return this.x[i];
    }
}
