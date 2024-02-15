package org.bouncycastle.pqc.crypto.sphincsplus;

/* loaded from: classes2.dex */
class PK {
    final byte[] root;
    final byte[] seed;

    public PK(byte[] bArr, byte[] bArr2) {
        this.seed = bArr;
        this.root = bArr2;
    }
}
