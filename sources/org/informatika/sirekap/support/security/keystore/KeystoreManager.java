package org.informatika.sirekap.support.security.keystore;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.support.security.encrypt.Encryptor;
import org.informatika.sirekap.support.security.signature.Signer;

/* compiled from: KeystoreManager.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;", "", "buildEncryptor", "Lorg/informatika/sirekap/support/security/encrypt/Encryptor;", "alias", "", "buildSigner", "Lorg/informatika/sirekap/support/security/signature/Signer;", "getCertificateChain", "", "Ljava/security/cert/Certificate;", "getKeystore", "Ljava/security/KeyStore;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface KeystoreManager {
    Encryptor buildEncryptor();

    Encryptor buildEncryptor(String str);

    Signer buildSigner();

    Signer buildSigner(String str);

    List<Certificate> getCertificateChain();

    List<Certificate> getCertificateChain(String str);

    KeyStore getKeystore();
}
