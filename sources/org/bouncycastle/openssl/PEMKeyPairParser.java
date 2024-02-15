package org.bouncycastle.openssl;

import java.io.IOException;

/* loaded from: classes2.dex */
interface PEMKeyPairParser {
    PEMKeyPair parse(byte[] bArr) throws IOException;
}
