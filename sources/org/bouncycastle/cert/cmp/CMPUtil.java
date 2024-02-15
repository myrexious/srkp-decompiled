package org.bouncycastle.cert.cmp;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Object;

/* loaded from: classes2.dex */
class CMPUtil {
    CMPUtil() {
    }

    public static void derEncodeToStream(ASN1Object aSN1Object, OutputStream outputStream) {
        try {
            aSN1Object.encodeTo(outputStream, ASN1Encoding.DER);
            outputStream.close();
        } catch (IOException e) {
            throw new CMPRuntimeException("unable to DER encode object: " + e.getMessage(), e);
        }
    }
}
