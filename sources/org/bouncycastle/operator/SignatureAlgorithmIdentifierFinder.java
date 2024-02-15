package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes2.dex */
public interface SignatureAlgorithmIdentifierFinder {
    AlgorithmIdentifier find(String str);
}
