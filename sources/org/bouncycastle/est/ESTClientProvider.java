package org.bouncycastle.est;

/* loaded from: classes2.dex */
public interface ESTClientProvider {
    boolean isTrusted();

    ESTClient makeClient() throws ESTException;
}
