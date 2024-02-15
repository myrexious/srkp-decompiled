package org.bouncycastle.operator.bc;

import org.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes2.dex */
class SEEDUtil {
    SEEDUtil() {
    }

    static AlgorithmIdentifier determineKeyEncAlg() {
        return new AlgorithmIdentifier(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap);
    }
}
