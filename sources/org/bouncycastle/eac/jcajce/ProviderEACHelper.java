package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/* loaded from: classes2.dex */
class ProviderEACHelper implements EACHelper {
    private final Provider provider;

    public ProviderEACHelper(Provider provider) {
        this.provider = provider;
    }

    @Override // org.bouncycastle.eac.jcajce.EACHelper
    public KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(str, this.provider);
    }
}
