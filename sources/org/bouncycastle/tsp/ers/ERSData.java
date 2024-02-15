package org.bouncycastle.tsp.ers;

import org.bouncycastle.operator.DigestCalculator;

/* loaded from: classes2.dex */
public interface ERSData {
    byte[] getHash(DigestCalculator digestCalculator, byte[] bArr);
}
