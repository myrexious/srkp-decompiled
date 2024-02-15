package com.tom_roush.pdfbox.pdmodel.encryption;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;

/* loaded from: classes3.dex */
public class PublicKeyDecryptionMaterial extends DecryptionMaterial {
    private final String alias;
    private final KeyStore keyStore;
    private final String password;

    public PublicKeyDecryptionMaterial(KeyStore keyStore, String str, String str2) {
        this.keyStore = keyStore;
        this.alias = str;
        this.password = str2;
    }

    public X509Certificate getCertificate() throws KeyStoreException {
        if (this.keyStore.size() == 1) {
            return (X509Certificate) this.keyStore.getCertificate(this.keyStore.aliases().nextElement());
        } else if (this.keyStore.containsAlias(this.alias)) {
            return (X509Certificate) this.keyStore.getCertificate(this.alias);
        } else {
            throw new KeyStoreException("the keystore does not contain the given alias");
        }
    }

    public String getPassword() {
        return this.password;
    }

    public Key getPrivateKey() throws KeyStoreException {
        try {
            if (this.keyStore.size() == 1) {
                return this.keyStore.getKey(this.keyStore.aliases().nextElement(), this.password.toCharArray());
            } else if (this.keyStore.containsAlias(this.alias)) {
                return this.keyStore.getKey(this.alias, this.password.toCharArray());
            } else {
                throw new KeyStoreException("the keystore does not contain the given alias");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new KeyStoreException("the algorithm necessary to recover the key is not available", e);
        } catch (UnrecoverableKeyException e2) {
            throw new KeyStoreException("the private key is not recoverable", e2);
        }
    }
}
