package org.bouncycastle.crypto;

/* loaded from: classes2.dex */
public interface EncapsulatedSecretExtractor {
    byte[] extractSecret(byte[] bArr);

    int getEncapsulationLength();
}
