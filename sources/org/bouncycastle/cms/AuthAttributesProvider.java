package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface AuthAttributesProvider {
    ASN1Set getAuthAttributes();

    boolean isAead();
}
