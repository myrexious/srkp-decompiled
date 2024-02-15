package org.bouncycastle.tsp.ers;

import org.bouncycastle.asn1.tsp.PartialHashtree;
import org.bouncycastle.operator.DigestCalculator;

/* loaded from: classes2.dex */
public interface ERSRootNodeCalculator {
    PartialHashtree[] computePathToRoot(DigestCalculator digestCalculator, PartialHashtree partialHashtree, int i);

    byte[] computeRootHash(DigestCalculator digestCalculator, PartialHashtree[] partialHashtreeArr);

    byte[] recoverRootHash(DigestCalculator digestCalculator, PartialHashtree[] partialHashtreeArr);
}
