package org.bouncycastle.cert.ocsp;

import java.io.OutputStream;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.DigestCalculator;

/* loaded from: classes2.dex */
public class RespID {
    public static final AlgorithmIdentifier HASH_SHA1 = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);

    /* renamed from: id */
    ResponderID f44id;

    public RespID(ResponderID responderID) {
        this.f44id = responderID;
    }

    public RespID(X500Name x500Name) {
        this.f44id = new ResponderID(x500Name);
    }

    public RespID(SubjectPublicKeyInfo subjectPublicKeyInfo, DigestCalculator digestCalculator) throws OCSPException {
        try {
            if (!digestCalculator.getAlgorithmIdentifier().equals(HASH_SHA1)) {
                throw new IllegalArgumentException("only SHA-1 can be used with RespID - found: " + digestCalculator.getAlgorithmIdentifier().getAlgorithm());
            }
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(subjectPublicKeyInfo.getPublicKeyData().getBytes());
            outputStream.close();
            this.f44id = new ResponderID(new DEROctetString(digestCalculator.getDigest()));
        } catch (Exception e) {
            throw new OCSPException("problem creating ID: " + e, e);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof RespID) {
            return this.f44id.equals(((RespID) obj).f44id);
        }
        return false;
    }

    public int hashCode() {
        return this.f44id.hashCode();
    }

    public ResponderID toASN1Primitive() {
        return this.f44id;
    }
}
