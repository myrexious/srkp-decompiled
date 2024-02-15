package org.informatika.sirekap.support.security;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProtection;
import com.google.android.gms.stats.CodePackage;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.crypto.KeyGenerator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.informatika.sirekap.support.security.entity.DistinguishableName;
import org.informatika.sirekap.support.security.key.CSRGenerator;

/* compiled from: AndroidKeystoreFacade.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u000e\u001a\u00020\u0004J\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0007J\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u000e\u001a\u00020\u0004J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u000e\u001a\u00020\u0004J\u0010\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\u0015J!\u0010\u001e\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180 ¢\u0006\u0002\u0010!J3\u0010\"\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010#\u001a\u00020$2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180 H\u0007¢\u0006\u0002\u0010%R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/informatika/sirekap/support/security/AndroidKeystoreFacade;", "", "()V", "BSRE_ALIAS_NAME", "", "COOLDOWN_TIME_SECOND", "", "LOCAL_ALIAS_NAME", "SYMETRIC_ALIAS_NAME", "keystore", "Ljava/security/KeyStore;", "kotlin.jvm.PlatformType", "deleteEntry", "", "alias", "generateCSR", "dn", "Lorg/informatika/sirekap/support/security/entity/DistinguishableName;", "generatePairKey", "Ljava/security/KeyPair;", "context", "Landroid/content/Context;", "generateSymmetricKey", "getCertificate", "Ljava/security/cert/Certificate;", "getKey", "Ljava/security/Key;", "isKeyEntryExist", "", "isSymetricKeyExist", "setCertificate", "certificate", "", "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "setPrivateKeyEntry", "privateKey", "Ljava/security/PrivateKey;", "(Landroid/content/Context;Ljava/lang/String;Ljava/security/PrivateKey;[Ljava/security/cert/Certificate;)V", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AndroidKeystoreFacade {
    public static final String BSRE_ALIAS_NAME = "bssn-sign";
    public static final int COOLDOWN_TIME_SECOND = 43200;
    public static final AndroidKeystoreFacade INSTANCE = new AndroidKeystoreFacade();
    public static final String LOCAL_ALIAS_NAME = "sirekap-sign";
    public static final String SYMETRIC_ALIAS_NAME = "sirekap-symetric";
    private static final KeyStore keystore;

    private AndroidKeystoreFacade() {
    }

    static {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        keystore = keyStore;
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.addProvider(new BouncyCastleProvider());
    }

    public static /* synthetic */ Key getKey$default(AndroidKeystoreFacade androidKeystoreFacade, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = LOCAL_ALIAS_NAME;
        }
        return androidKeystoreFacade.getKey(str);
    }

    public final Key getKey(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        return keystore.getKey(alias, null);
    }

    public static /* synthetic */ boolean isKeyEntryExist$default(AndroidKeystoreFacade androidKeystoreFacade, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = LOCAL_ALIAS_NAME;
        }
        return androidKeystoreFacade.isKeyEntryExist(str);
    }

    public final boolean isKeyEntryExist(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        return keystore.isKeyEntry(alias);
    }

    public final void generateSymmetricKey(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            Intrinsics.checkNotNullExpressionValue(keyGenerator, "getInstance(\n           …idKeyStore\"\n            )");
            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(SYMETRIC_ALIAS_NAME, 3);
            builder.setBlockModes(CodePackage.GCM);
            builder.setEncryptionPaddings("NoPadding");
            KeyGenParameterSpec build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(\n               …    build()\n            }");
            keyGenerator.init(build);
            keyGenerator.generateKey();
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            throw e;
        }
    }

    public final boolean isSymetricKeyExist(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isKeyEntryExist(SYMETRIC_ALIAS_NAME);
    }

    public static /* synthetic */ KeyPair generatePairKey$default(AndroidKeystoreFacade androidKeystoreFacade, Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = LOCAL_ALIAS_NAME;
        }
        return androidKeystoreFacade.generatePairKey(context, str);
    }

    public final KeyPair generatePairKey(Context context, String alias) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(alias, "alias");
        try {
            keystore.deleteEntry(alias);
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "AndroidKeyStore");
            Intrinsics.checkNotNullExpressionValue(keyPairGenerator, "getInstance(\n           …idKeyStore\"\n            )");
            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(alias, 12);
            builder.setDigests("SHA-256");
            KeyGenParameterSpec build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(\n               …    build()\n            }");
            keyPairGenerator.initialize(build);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            throw e;
        }
    }

    public static /* synthetic */ String generateCSR$default(AndroidKeystoreFacade androidKeystoreFacade, DistinguishableName distinguishableName, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = LOCAL_ALIAS_NAME;
        }
        return androidKeystoreFacade.generateCSR(distinguishableName, str);
    }

    public final String generateCSR(DistinguishableName dn, String alias) {
        Intrinsics.checkNotNullParameter(dn, "dn");
        Intrinsics.checkNotNullParameter(alias, "alias");
        try {
            X500NameBuilder x500NameBuilder = new X500NameBuilder();
            x500NameBuilder.addRDN(BCStyle.CN, dn.getCommonName());
            x500NameBuilder.addRDN(BCStyle.L, dn.getLocality());
            x500NameBuilder.addRDN(BCStyle.UID, dn.getUserId());
            X500Name dn2 = x500NameBuilder.build();
            KeyStore keyStore = keystore;
            Key key = keyStore.getKey(alias, null);
            Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.PrivateKey");
            PublicKey publicKey = keyStore.getCertificate(alias).getPublicKey();
            Intrinsics.checkNotNull(publicKey, "null cannot be cast to non-null type java.security.PublicKey");
            Intrinsics.checkNotNullExpressionValue(dn2, "dn");
            return new CSRGenerator((PrivateKey) key, publicKey, dn2, "SHA256WithECDSA").writePem();
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            throw e;
        }
    }

    public final void setCertificate(String alias, Certificate[] certificate) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        try {
            PKIFacade pKIFacade = PKIFacade.INSTANCE;
            ArrayList arrayList = new ArrayList(certificate.length);
            for (Certificate certificate2 : certificate) {
                Intrinsics.checkNotNull(certificate2, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                arrayList.add((X509Certificate) certificate2);
            }
            X509Certificate[] x509CertificateArr = (X509Certificate[]) pKIFacade.repairChain(arrayList).toArray(new X509Certificate[0]);
            KeyStore keyStore = keystore;
            if (keyStore.isKeyEntry(alias)) {
                keyStore.setKeyEntry(alias, keyStore.getKey(alias, null), null, x509CertificateArr);
            } else {
                keyStore.setCertificateEntry(alias, x509CertificateArr[0]);
            }
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            throw e;
        }
    }

    public final void setPrivateKeyEntry(Context context, String alias, PrivateKey privateKey, Certificate[] certificate) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(alias, "alias");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        try {
            PKIFacade pKIFacade = PKIFacade.INSTANCE;
            ArrayList arrayList = new ArrayList(certificate.length);
            for (Certificate certificate2 : certificate) {
                Intrinsics.checkNotNull(certificate2, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                arrayList.add((X509Certificate) certificate2);
            }
            X509Certificate[] x509CertificateArr = (X509Certificate[]) pKIFacade.repairChain(arrayList).toArray(new X509Certificate[0]);
            KeyProtection.Builder builder = new KeyProtection.Builder(12);
            builder.setDigests("SHA-256");
            KeyProtection build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(KeyProperties.PU…    build()\n            }");
            try {
                keystore.setEntry(alias, new KeyStore.PrivateKeyEntry(privateKey, x509CertificateArr), build);
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
                PKIFacade pKIFacade2 = PKIFacade.INSTANCE;
                ArrayList arrayList2 = new ArrayList(certificate.length);
                for (Certificate certificate3 : certificate) {
                    Certificate certificate4 = certificate3;
                    Intrinsics.checkNotNull(certificate3, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                    arrayList2.add((X509Certificate) certificate3);
                }
                keystore.setKeyEntry(alias, privateKey, null, (X509Certificate[]) pKIFacade2.repairChain(arrayList2).toArray(new X509Certificate[0]));
            }
        } catch (Exception e2) {
            FirebaseCrashlytics.getInstance().recordException(e2);
            throw e2;
        }
    }

    public final Certificate getCertificate(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        return keystore.getCertificate(alias);
    }

    public final void deleteEntry(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        keystore.deleteEntry(alias);
    }
}
