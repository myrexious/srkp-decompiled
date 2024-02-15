package org.bouncycastle.openssl.jcajce;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.openssl.EncryptionException;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.util.Integers;

/* loaded from: classes2.dex */
class PEMUtilities {
    private static final Map CIPHER_NAMES;
    private static final Map KEYSIZES;
    private static final Map KEY_NAMES;
    private static final Set PKCS5_SCHEME_1;
    private static final Set PKCS5_SCHEME_2;
    private static final Map PRFS;
    private static final Map PRFS_SALT;

    static {
        HashMap hashMap = new HashMap();
        KEYSIZES = hashMap;
        HashSet hashSet = new HashSet();
        PKCS5_SCHEME_1 = hashSet;
        HashSet hashSet2 = new HashSet();
        PKCS5_SCHEME_2 = hashSet2;
        HashMap hashMap2 = new HashMap();
        PRFS = hashMap2;
        HashMap hashMap3 = new HashMap();
        PRFS_SALT = hashMap3;
        HashMap hashMap4 = new HashMap();
        CIPHER_NAMES = hashMap4;
        HashMap hashMap5 = new HashMap();
        KEY_NAMES = hashMap5;
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC);
        hashSet2.add(PKCSObjectIdentifiers.id_PBES2);
        hashSet2.add(PKCSObjectIdentifiers.des_EDE3_CBC);
        hashSet2.add(NISTObjectIdentifiers.id_aes128_CBC);
        hashSet2.add(NISTObjectIdentifiers.id_aes192_CBC);
        hashSet2.add(NISTObjectIdentifiers.id_aes256_CBC);
        hashMap.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), Integers.valueOf(192));
        hashMap.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), Integers.valueOf(128));
        hashMap.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), Integers.valueOf(192));
        hashMap.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), Integers.valueOf(256));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4.getId(), Integers.valueOf(128));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4, Integers.valueOf(40));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC, Integers.valueOf(128));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, Integers.valueOf(192));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC, Integers.valueOf(128));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, Integers.valueOf(40));
        hashMap2.put(PKCSObjectIdentifiers.id_hmacWithSHA1, "PBKDF2withHMACSHA1");
        hashMap2.put(PKCSObjectIdentifiers.id_hmacWithSHA256, "PBKDF2withHMACSHA256");
        hashMap2.put(PKCSObjectIdentifiers.id_hmacWithSHA512, "PBKDF2withHMACSHA512");
        hashMap2.put(PKCSObjectIdentifiers.id_hmacWithSHA224, "PBKDF2withHMACSHA224");
        hashMap2.put(PKCSObjectIdentifiers.id_hmacWithSHA384, "PBKDF2withHMACSHA384");
        hashMap2.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, "PBKDF2withHMACSHA3-224");
        hashMap2.put(NISTObjectIdentifiers.id_hmacWithSHA3_256, "PBKDF2withHMACSHA3-256");
        hashMap2.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, "PBKDF2withHMACSHA3-384");
        hashMap2.put(NISTObjectIdentifiers.id_hmacWithSHA3_512, "PBKDF2withHMACSHA3-512");
        hashMap2.put(CryptoProObjectIdentifiers.gostR3411Hmac, "PBKDF2withHMACGOST3411");
        hashMap3.put(PKCSObjectIdentifiers.id_hmacWithSHA1, Integers.valueOf(20));
        hashMap3.put(PKCSObjectIdentifiers.id_hmacWithSHA256, Integers.valueOf(32));
        hashMap3.put(PKCSObjectIdentifiers.id_hmacWithSHA512, Integers.valueOf(64));
        hashMap3.put(PKCSObjectIdentifiers.id_hmacWithSHA224, Integers.valueOf(28));
        hashMap3.put(PKCSObjectIdentifiers.id_hmacWithSHA384, Integers.valueOf(48));
        hashMap3.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, Integers.valueOf(28));
        hashMap3.put(NISTObjectIdentifiers.id_hmacWithSHA3_256, Integers.valueOf(32));
        hashMap3.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, Integers.valueOf(48));
        hashMap3.put(NISTObjectIdentifiers.id_hmacWithSHA3_512, Integers.valueOf(64));
        hashMap3.put(CryptoProObjectIdentifiers.gostR3411Hmac, Integers.valueOf(32));
        hashMap4.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESEDE/CBC/PKCS5Padding");
        hashMap4.put(NISTObjectIdentifiers.id_aes128_CBC, "AES/CBC/PKCS7Padding");
        hashMap4.put(NISTObjectIdentifiers.id_aes192_CBC, "AES/CBC/PKCS7Padding");
        hashMap4.put(NISTObjectIdentifiers.id_aes256_CBC, "AES/CBC/PKCS7Padding");
        hashMap5.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), "DESEDE");
        hashMap5.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), "AES");
        hashMap5.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), "AES");
        hashMap5.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), "AES");
    }

    PEMUtilities() {
    }

    public static byte[] crypt(boolean z, JcaJceHelper jcaJceHelper, byte[] bArr, char[] cArr, String str, byte[] bArr2) throws PEMException {
        String str2;
        String str3;
        String str4;
        String str5;
        byte[] bArr3;
        String str6;
        SecretKey key;
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        if (str.endsWith("-CFB")) {
            str2 = "CFB";
            str3 = "NoPadding";
        } else {
            str2 = "CBC";
            str3 = "PKCS5Padding";
        }
        if (str.endsWith("-ECB") || "DES-EDE".equals(str) || "DES-EDE3".equals(str)) {
            str2 = "ECB";
            ivParameterSpec = null;
        }
        RC2ParameterSpec rC2ParameterSpec = ivParameterSpec;
        if (str.endsWith("-OFB")) {
            str5 = "OFB";
            str4 = "NoPadding";
        } else {
            str4 = str3;
            str5 = str2;
        }
        int i = 1;
        if (str.startsWith("DES-EDE")) {
            str6 = "DESede";
            key = getKey(jcaJceHelper, cArr, "DESede", 24, bArr2, !str.startsWith("DES-EDE3"));
        } else if (str.startsWith("DES-")) {
            str6 = "DES";
            key = getKey(jcaJceHelper, cArr, "DES", 8, bArr2);
        } else if (str.startsWith("BF-")) {
            str6 = "Blowfish";
            key = getKey(jcaJceHelper, cArr, "Blowfish", 16, bArr2);
        } else {
            int i2 = 128;
            if (str.startsWith("RC2-")) {
                if (str.startsWith("RC2-40-")) {
                    i2 = 40;
                } else if (str.startsWith("RC2-64-")) {
                    i2 = 64;
                }
                str6 = "RC2";
                key = getKey(jcaJceHelper, cArr, "RC2", i2 / 8, bArr2);
                rC2ParameterSpec = rC2ParameterSpec == null ? new RC2ParameterSpec(i2) : new RC2ParameterSpec(i2, bArr2);
            } else if (!str.startsWith("AES-")) {
                throw new EncryptionException("unknown encryption with private key");
            } else {
                if (bArr2.length > 8) {
                    bArr3 = new byte[8];
                    System.arraycopy(bArr2, 0, bArr3, 0, 8);
                } else {
                    bArr3 = bArr2;
                }
                if (!str.startsWith("AES-128-")) {
                    if (str.startsWith("AES-192-")) {
                        i2 = 192;
                    } else if (!str.startsWith("AES-256-")) {
                        throw new EncryptionException("unknown AES encryption with private key");
                    } else {
                        i2 = 256;
                    }
                }
                str6 = "AES";
                key = getKey(jcaJceHelper, cArr, "AES", i2 / 8, bArr3);
            }
        }
        try {
            Cipher createCipher = jcaJceHelper.createCipher(str6 + RemoteSettings.FORWARD_SLASH_STRING + str5 + RemoteSettings.FORWARD_SLASH_STRING + str4);
            if (!z) {
                i = 2;
            }
            if (rC2ParameterSpec == null) {
                createCipher.init(i, key);
            } else {
                createCipher.init(i, key, rC2ParameterSpec);
            }
            return createCipher.doFinal(bArr);
        } catch (Exception e) {
            throw new EncryptionException("exception using cipher - please check password and data.", e);
        }
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(JcaJceHelper jcaJceHelper, String str, char[] cArr, byte[] bArr, int i) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        return new SecretKeySpec(jcaJceHelper.createSecretKeyFactory("PBKDF2with8BIT").generateSecret(new PBEKeySpec(cArr, bArr, i, getKeySize(str))).getEncoded(), getAlgorithmName(str));
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(JcaJceHelper jcaJceHelper, String str, char[] cArr, byte[] bArr, int i, AlgorithmIdentifier algorithmIdentifier) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        String str2 = (String) PRFS.get(algorithmIdentifier.getAlgorithm());
        if (str2 != null) {
            return new SecretKeySpec(jcaJceHelper.createSecretKeyFactory(str2).generateSecret(new PBEKeySpec(cArr, bArr, i, getKeySize(str))).getEncoded(), str);
        }
        throw new NoSuchAlgorithmException("unknown PRF in PKCS#2: " + algorithmIdentifier.getAlgorithm());
    }

    static String getAlgorithmName(String str) {
        String str2 = (String) KEY_NAMES.get(str);
        return str2 != null ? str2 : str;
    }

    public static String getCipherName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) CIPHER_NAMES.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }

    private static SecretKey getKey(JcaJceHelper jcaJceHelper, char[] cArr, String str, int i, byte[] bArr) throws PEMException {
        return getKey(jcaJceHelper, cArr, str, i, bArr, false);
    }

    private static SecretKey getKey(JcaJceHelper jcaJceHelper, char[] cArr, String str, int i, byte[] bArr, boolean z) throws PEMException {
        try {
            byte[] encoded = jcaJceHelper.createSecretKeyFactory("PBKDF-OpenSSL").generateSecret(new PBEKeySpec(cArr, bArr, 1, i * 8)).getEncoded();
            if (z && encoded.length >= 24) {
                System.arraycopy(encoded, 0, encoded, 16, 8);
            }
            return new SecretKeySpec(encoded, str);
        } catch (GeneralSecurityException e) {
            throw new PEMException("Unable to create OpenSSL PBDKF: " + e.getMessage(), e);
        }
    }

    static int getKeySize(String str) {
        Map map = KEYSIZES;
        if (map.containsKey(str)) {
            return ((Integer) map.get(str)).intValue();
        }
        throw new IllegalStateException("no key size for algorithm: " + str);
    }

    public static int getSaltSize(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Map map = PRFS_SALT;
        if (map.containsKey(aSN1ObjectIdentifier)) {
            return ((Integer) map.get(aSN1ObjectIdentifier)).intValue();
        }
        throw new IllegalStateException("no salt size for algorithm: " + aSN1ObjectIdentifier);
    }

    public static boolean isHmacSHA1(AlgorithmIdentifier algorithmIdentifier) {
        return algorithmIdentifier == null || algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) PKCSObjectIdentifiers.id_hmacWithSHA1);
    }

    public static boolean isPKCS12(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.getId().startsWith(PKCSObjectIdentifiers.pkcs_12PbeIds.getId());
    }

    public static boolean isPKCS5Scheme1(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_1.contains(aSN1ObjectIdentifier);
    }

    public static boolean isPKCS5Scheme2(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_2.contains(aSN1ObjectIdentifier);
    }
}
