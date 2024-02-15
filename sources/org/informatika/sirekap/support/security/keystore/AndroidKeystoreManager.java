package org.informatika.sirekap.support.security.keystore;

import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.support.security.AndroidKeystoreFacade;
import org.informatika.sirekap.support.security.encrypt.AsymmetricECEncrypt;
import org.informatika.sirekap.support.security.encrypt.Encryptor;
import org.informatika.sirekap.support.security.signature.LocalSigner;

/* compiled from: AndroidKeystoreManager.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0004H\u0016J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00042\u0006\u0010\f\u001a\u00020\u0005H\u0016J\b\u0010\u0011\u001a\u00020\tH\u0016J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0005R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/informatika/sirekap/support/security/keystore/AndroidKeystoreManager;", "Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;", "()V", "aliases", "", "", "getAliases", "()Ljava/util/List;", "keystore", "Ljava/security/KeyStore;", "buildEncryptor", "Lorg/informatika/sirekap/support/security/encrypt/Encryptor;", "alias", "buildSigner", "Lorg/informatika/sirekap/support/security/signature/LocalSigner;", "getCertificateChain", "Ljava/security/cert/Certificate;", "getKeystore", "getPrivateKey", "Ljava/security/PrivateKey;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AndroidKeystoreManager implements KeystoreManager {
    private final KeyStore keystore;

    public AndroidKeystoreManager() {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        Intrinsics.checkNotNullExpressionValue(keyStore, "getInstance(\"AndroidKeyS…\n        load(null)\n    }");
        this.keystore = keyStore;
    }

    public final List<String> getAliases() {
        Enumeration<String> aliases = this.keystore.aliases();
        Intrinsics.checkNotNullExpressionValue(aliases, "keystore.aliases()");
        ArrayList list = Collections.list(aliases);
        Intrinsics.checkNotNullExpressionValue(list, "list(this)");
        return list;
    }

    @Override // org.informatika.sirekap.support.security.keystore.KeystoreManager
    public LocalSigner buildSigner() {
        return buildSigner(getAliases().get(0));
    }

    @Override // org.informatika.sirekap.support.security.keystore.KeystoreManager
    public LocalSigner buildSigner(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        Key key = this.keystore.getKey(alias, null);
        Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.PrivateKey");
        return new LocalSigner((PrivateKey) key, getCertificateChain(alias), "SHA256withECDSA");
    }

    @Override // org.informatika.sirekap.support.security.keystore.KeystoreManager
    public Encryptor buildEncryptor(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        Key key = this.keystore.getKey(alias, null);
        Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.PrivateKey");
        return new AsymmetricECEncrypt(this.keystore.getCertificateChain(alias)[0].getPublicKey(), (PrivateKey) key);
    }

    @Override // org.informatika.sirekap.support.security.keystore.KeystoreManager
    public Encryptor buildEncryptor() {
        return buildEncryptor(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
    }

    @Override // org.informatika.sirekap.support.security.keystore.KeystoreManager
    public List<Certificate> getCertificateChain(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        Certificate[] certificateChain = this.keystore.getCertificateChain(alias);
        Intrinsics.checkNotNullExpressionValue(certificateChain, "keystore.getCertificateChain(alias)");
        return ArraysKt.asList(certificateChain);
    }

    @Override // org.informatika.sirekap.support.security.keystore.KeystoreManager
    public List<Certificate> getCertificateChain() {
        Certificate[] certificateChain = this.keystore.getCertificateChain(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
        Intrinsics.checkNotNullExpressionValue(certificateChain, "keystore.getCertificateC…eFacade.LOCAL_ALIAS_NAME)");
        return ArraysKt.asList(certificateChain);
    }

    @Override // org.informatika.sirekap.support.security.keystore.KeystoreManager
    public KeyStore getKeystore() {
        return this.keystore;
    }

    public final PrivateKey getPrivateKey(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        Key key = this.keystore.getKey(alias, null);
        Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.PrivateKey");
        return (PrivateKey) key;
    }
}
