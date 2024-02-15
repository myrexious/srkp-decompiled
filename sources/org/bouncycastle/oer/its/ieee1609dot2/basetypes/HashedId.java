package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class HashedId extends ASN1Object {

    /* renamed from: id */
    private final byte[] f55id;

    public HashedId(byte[] bArr) {
        this.f55id = Arrays.clone(bArr);
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
            return java.util.Arrays.equals(this.f55id, ((HashedId) obj).f55id);
        }
        return false;
    }

    public byte[] getHashBytes() {
        return Arrays.clone(this.f55id);
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return (super.hashCode() * 31) + java.util.Arrays.hashCode(this.f55id);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DEROctetString(this.f55id);
    }
}
