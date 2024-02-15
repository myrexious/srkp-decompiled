package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes2.dex */
public class PKCS7TypedStream extends CMSTypedStream {
    private final ASN1Encodable content;

    public PKCS7TypedStream(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1ObjectIdentifier);
        this.content = aSN1Encodable;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.io.InputStream getContentStream(org.bouncycastle.asn1.ASN1Encodable r4) throws java.io.IOException {
        /*
            r3 = this;
            org.bouncycastle.asn1.ASN1Primitive r4 = r4.toASN1Primitive()
            java.lang.String r0 = "DER"
            byte[] r4 = r4.getEncoded(r0)
            r0 = 0
            r0 = r4[r0]
            r1 = 31
            r0 = r0 & r1
            r2 = 1
            if (r0 != r1) goto L1d
        L13:
            int r0 = r2 + 1
            r1 = r4[r2]
            r1 = r1 & 128(0x80, float:1.794E-43)
            r2 = r0
            if (r1 == 0) goto L1d
            goto L13
        L1d:
            int r0 = r2 + 1
            r1 = r4[r2]
            r2 = r1 & 128(0x80, float:1.794E-43)
            if (r2 == 0) goto L28
            r1 = r1 & 127(0x7f, float:1.78E-43)
            int r0 = r0 + r1
        L28:
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            int r2 = r4.length
            int r2 = r2 - r0
            r1.<init>(r4, r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.PKCS7TypedStream.getContentStream(org.bouncycastle.asn1.ASN1Encodable):java.io.InputStream");
    }

    @Override // org.bouncycastle.cms.CMSTypedStream
    public void drain() throws IOException {
        this.content.toASN1Primitive();
    }

    public ASN1Encodable getContent() {
        return this.content;
    }

    @Override // org.bouncycastle.cms.CMSTypedStream
    public InputStream getContentStream() {
        try {
            return getContentStream(this.content);
        } catch (IOException e) {
            throw new CMSRuntimeException("unable to convert content to stream: " + e.getMessage(), e);
        }
    }
}
