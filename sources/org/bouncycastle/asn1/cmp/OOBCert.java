package org.bouncycastle.asn1.cmp;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.x509.AttributeCertificate;
import org.bouncycastle.asn1.x509.Certificate;

/* loaded from: classes2.dex */
public class OOBCert extends CMPCertificate {
    public OOBCert(int i, ASN1Object aSN1Object) {
        super(i, aSN1Object);
    }

    public OOBCert(AttributeCertificate attributeCertificate) {
        super(attributeCertificate);
    }

    public OOBCert(Certificate certificate) {
        super(certificate);
    }

    public static OOBCert getInstance(Object obj) {
        if (obj == null || (obj instanceof OOBCert)) {
            return (OOBCert) obj;
        }
        if (obj instanceof CMPCertificate) {
            try {
                return getInstance((Object) ((CMPCertificate) obj).getEncoded());
            } catch (IOException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        }
        if (obj instanceof byte[]) {
            try {
                obj = ASN1Primitive.fromByteArray((byte[]) obj);
            } catch (IOException unused) {
                throw new IllegalArgumentException("Invalid encoding in OOBCert");
            }
        }
        if (obj instanceof ASN1Sequence) {
            return new OOBCert(Certificate.getInstance(obj));
        }
        if (obj instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) obj;
            return new OOBCert(aSN1TaggedObject.getTagNo(), aSN1TaggedObject.getObject());
        }
        throw new IllegalArgumentException("Invalid object: " + obj.getClass().getName());
    }

    public static OOBCert getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (aSN1TaggedObject != null) {
            if (z) {
                return getInstance((Object) aSN1TaggedObject.getObject());
            }
            throw new IllegalArgumentException("tag must be explicit");
        }
        return null;
    }
}
