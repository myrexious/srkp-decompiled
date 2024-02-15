package org.bouncycastle.est;

/* loaded from: classes2.dex */
public interface TLSUniqueProvider {
    byte[] getTLSUnique();

    boolean isTLSUniqueAvailable();
}
