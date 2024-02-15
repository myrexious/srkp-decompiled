package org.informatika.sirekap.support.security.keystore;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.support.security.encrypt.AsymmetricECEncrypt;
import org.informatika.sirekap.support.security.encrypt.AsymmetricRSAEncrypt;
import org.informatika.sirekap.support.security.encrypt.Encryptor;
import org.informatika.sirekap.support.security.signature.LocalSigner;

/* compiled from: PKCS12KeystoreManager.kt */
@Deprecated(level = DeprecationLevel.WARNING, message = "Moved to Android Keystore")
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u0015\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\nH\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\n2\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0016R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/informatika/sirekap/support/security/keystore/PKCS12KeystoreManager;", "Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;", "keystoreLocation", "", "passphrase", "(Ljava/lang/String;Ljava/lang/String;)V", "keystoreStream", "Ljava/io/InputStream;", "(Ljava/io/InputStream;Ljava/lang/String;)V", "aliases", "", "getAliases", "()Ljava/util/List;", "keystore", "Ljava/security/KeyStore;", "buildEncryptor", "Lorg/informatika/sirekap/support/security/encrypt/Encryptor;", "alias", "buildSigner", "Lorg/informatika/sirekap/support/security/signature/LocalSigner;", "getCertificateChain", "Ljava/security/cert/Certificate;", "getKeystore", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PKCS12KeystoreManager implements KeystoreManager {
    private final KeyStore keystore;
    private final String passphrase;

    public PKCS12KeystoreManager(InputStream keystoreStream, String passphrase) {
        Intrinsics.checkNotNullParameter(keystoreStream, "keystoreStream");
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        this.passphrase = passphrase;
        char[] charArray = passphrase.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        Intrinsics.checkNotNullExpressionValue(keyStore, "getInstance(\"PKCS12\")");
        this.keystore = keyStore;
        keyStore.load(keystoreStream, charArray);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PKCS12KeystoreManager(String keystoreLocation, String passphrase) {
        this(new FileInputStream(keystoreLocation), passphrase);
        Intrinsics.checkNotNullParameter(keystoreLocation, "keystoreLocation");
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
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
        KeyStore keyStore = this.keystore;
        char[] charArray = this.passphrase.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        return new LocalSigner(keyStore, charArray, alias);
    }

    @Override // org.informatika.sirekap.support.security.keystore.KeystoreManager
    public Encryptor buildEncryptor(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        KeyStore keyStore = this.keystore;
        char[] charArray = this.passphrase.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        Key key = keyStore.getKey(alias, charArray);
        Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.PrivateKey");
        PrivateKey privateKey = (PrivateKey) key;
        PublicKey publicKey = this.keystore.getCertificateChain(alias)[0].getPublicKey();
        String algorithm = privateKey.getAlgorithm();
        if (Intrinsics.areEqual(algorithm, "EC")) {
            return new AsymmetricECEncrypt(publicKey, privateKey);
        }
        if (Intrinsics.areEqual(algorithm, "RSA")) {
            return new AsymmetricRSAEncrypt(publicKey, privateKey);
        }
        throw new NoSuchAlgorithmException("Key algorithm " + privateKey.getAlgorithm() + " is not supported");
    }

    @Override // org.informatika.sirekap.support.security.keystore.KeystoreManager
    public Encryptor buildEncryptor() {
        return buildEncryptor(getAliases().get(0));
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
        Certificate[] certificateChain = this.keystore.getCertificateChain(getAliases().get(0));
        Intrinsics.checkNotNullExpressionValue(certificateChain, "keystore.getCertificateChain(aliases[0])");
        return ArraysKt.asList(certificateChain);
    }

    @Override // org.informatika.sirekap.support.security.keystore.KeystoreManager
    public KeyStore getKeystore() {
        return this.keystore;
    }
}
