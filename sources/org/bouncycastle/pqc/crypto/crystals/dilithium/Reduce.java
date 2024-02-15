package org.bouncycastle.pqc.crypto.crystals.dilithium;

/* loaded from: classes2.dex */
class Reduce {
    Reduce() {
    }

    public static int conditionalAddQ(int i) {
        return i + ((i >> 31) & DilithiumEngine.DilithiumQ);
    }

    public static int montgomeryReduce(long j) {
        return (int) ((j - (((int) (58728449 * j)) * 8380417)) >>> 32);
    }

    public static int reduce32(int i) {
        return i - (((4194304 + i) >> 23) * DilithiumEngine.DilithiumQ);
    }
}
