package org.bouncycastle.math.ec;

import java.math.BigInteger;

/* loaded from: classes2.dex */
public interface ECMultiplier {
    ECPoint multiply(ECPoint eCPoint, BigInteger bigInteger);
}
