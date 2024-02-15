package org.bouncycastle.pkix;

import java.io.IOException;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.imaging.formats.jpeg.iptc.IptcConstants;
import org.apache.commons.lang3.BooleanUtils;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9FieldID;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.math.Primes;
import org.bouncycastle.util.Strings;

/* loaded from: classes2.dex */
public class SubjectPublicKeyInfoChecker {
    private static final Cache validatedQs = new Cache();
    private static final Cache validatedMods = new Cache();
    private static final BigInteger SMALL_PRIMES_PRODUCT = new BigInteger("8138e8a0fcf3a4e84a771d40fd305d7f4aa59306d7251de54d98af8fe95729a1f73d893fa424cd2edc8636a6c3285e022b0e3866a565ae8108eed8591cd4fe8d2ce86165a978d719ebf647f362d33fca29cd179fb42401cbaf3df0c614056f9c8f3cfd51e474afb6bc6974f78db8aba8e9e517fded658591ab7502bd41849462f", 16);
    private static final BigInteger ONE = BigInteger.valueOf(1);

    /* loaded from: classes2.dex */
    private static class Cache {
        private final BigInteger[] preserve;
        private int preserveCounter;
        private final Map<BigInteger, Boolean> values;

        private Cache() {
            this.values = new WeakHashMap();
            this.preserve = new BigInteger[8];
            this.preserveCounter = 0;
        }

        public synchronized void add(BigInteger bigInteger) {
            this.values.put(bigInteger, Boolean.TRUE);
            BigInteger[] bigIntegerArr = this.preserve;
            int i = this.preserveCounter;
            bigIntegerArr[i] = bigInteger;
            this.preserveCounter = (i + 1) % bigIntegerArr.length;
        }

        public synchronized void clear() {
            this.values.clear();
            int i = 0;
            while (true) {
                BigInteger[] bigIntegerArr = this.preserve;
                if (i != bigIntegerArr.length) {
                    bigIntegerArr[i] = null;
                    i++;
                }
            }
        }

        public synchronized boolean contains(BigInteger bigInteger) {
            return this.values.containsKey(bigInteger);
        }

        public synchronized int size() {
            return this.values.size();
        }
    }

    /* loaded from: classes2.dex */
    public static class Properties {
        private static final ThreadLocal threadProperties = new ThreadLocal();

        private Properties() {
        }

        static int asInteger(String str, int i) {
            String propertyValue = getPropertyValue(str);
            return propertyValue != null ? Integer.parseInt(propertyValue) : i;
        }

        static String getPropertyValue(final String str) {
            String str2;
            String str3 = (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.pkix.SubjectPublicKeyInfoChecker.Properties.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    return Security.getProperty(str);
                }
            });
            if (str3 != null) {
                return str3;
            }
            Map map = (Map) threadProperties.get();
            return (map == null || (str2 = (String) map.get(str)) == null) ? (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.pkix.SubjectPublicKeyInfoChecker.Properties.2
                @Override // java.security.PrivilegedAction
                public Object run() {
                    return System.getProperty(str);
                }
            }) : str2;
        }

        static boolean isOverrideSet(String str) {
            try {
                return isSetTrue(getPropertyValue(str));
            } catch (AccessControlException unused) {
                return false;
            }
        }

        private static boolean isSetTrue(String str) {
            if (str == null || str.length() != 4) {
                return false;
            }
            if (str.charAt(0) == 't' || str.charAt(0) == 'T') {
                if (str.charAt(1) == 'r' || str.charAt(1) == 'R') {
                    if (str.charAt(2) == 'u' || str.charAt(2) == 'U') {
                        return str.charAt(3) == 'e' || str.charAt(3) == 'E';
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        static boolean removeThreadOverride(String str) {
            String str2;
            ThreadLocal threadLocal = threadProperties;
            Map map = (Map) threadLocal.get();
            if (map == null || (str2 = (String) map.remove(str)) == null) {
                return false;
            }
            if (map.isEmpty()) {
                threadLocal.remove();
            }
            return BooleanUtils.TRUE.equals(Strings.toLowerCase(str2));
        }

        static boolean setThreadOverride(String str, boolean z) {
            boolean isOverrideSet = isOverrideSet(str);
            ThreadLocal threadLocal = threadProperties;
            Map map = (Map) threadLocal.get();
            if (map == null) {
                map = new HashMap();
                threadLocal.set(map);
            }
            map.put(str, z ? BooleanUtils.TRUE : BooleanUtils.FALSE);
            return isOverrideSet;
        }
    }

    public static void checkInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        Cache cache;
        BigInteger modulus;
        ASN1ObjectIdentifier algorithm = subjectPublicKeyInfo.getAlgorithm().getAlgorithm();
        if (X9ObjectIdentifiers.id_ecPublicKey.equals((ASN1Primitive) algorithm)) {
            X962Parameters x962Parameters = X962Parameters.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
            if (x962Parameters.isImplicitlyCA() || x962Parameters.isNamedCurve()) {
                return;
            }
            X9FieldID x9FieldID = X9FieldID.getInstance(ASN1Sequence.getInstance(x962Parameters.getParameters()).getObjectAt(1));
            if (!x9FieldID.getIdentifier().equals((ASN1Primitive) X9FieldID.prime_field)) {
                return;
            }
            modulus = ASN1Integer.getInstance(x9FieldID.getParameters()).getValue();
            cache = validatedQs;
            if (cache.contains(modulus)) {
                return;
            }
            int asInteger = Properties.asInteger("org.bouncycastle.ec.fp_max_size", IptcConstants.IMAGE_RESOURCE_BLOCK_EFFECTS_VISIBLE);
            int asInteger2 = Properties.asInteger("org.bouncycastle.ec.fp_certainty", 100);
            int bitLength = modulus.bitLength();
            if (asInteger < bitLength) {
                throw new IllegalArgumentException("Fp q value out of range");
            }
            if (Primes.hasAnySmallFactors(modulus) || !Primes.isMRProbablePrime(modulus, CryptoServicesRegistrar.getSecureRandom(), getNumberOfIterations(bitLength, asInteger2))) {
                throw new IllegalArgumentException("Fp q value not prime");
            }
        } else if (!PKCSObjectIdentifiers.rsaEncryption.equals((ASN1Primitive) algorithm) && !X509ObjectIdentifiers.id_ea_rsa.equals((ASN1Primitive) algorithm) && !PKCSObjectIdentifiers.id_RSAES_OAEP.equals((ASN1Primitive) algorithm) && !PKCSObjectIdentifiers.id_RSASSA_PSS.equals((ASN1Primitive) algorithm)) {
            return;
        } else {
            try {
                RSAPublicKey rSAPublicKey = RSAPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
                if ((rSAPublicKey.getPublicExponent().intValue() & 1) == 0) {
                    throw new IllegalArgumentException("RSA publicExponent is even");
                }
                cache = validatedMods;
                if (cache.contains(rSAPublicKey.getModulus())) {
                    return;
                }
                validate(rSAPublicKey.getModulus());
                modulus = rSAPublicKey.getModulus();
            } catch (IOException unused) {
                throw new IllegalArgumentException("unable to parse RSA key");
            }
        }
        cache.add(modulus);
    }

    private static int getNumberOfIterations(int i, int i2) {
        if (i >= 1536) {
            if (i2 <= 100) {
                return 3;
            }
            if (i2 <= 128) {
                return 4;
            }
            return 4 + (((i2 - 128) + 1) / 2);
        } else if (i >= 1024) {
            if (i2 <= 100) {
                return 4;
            }
            if (i2 <= 112) {
                return 5;
            }
            return (((i2 - 112) + 1) / 2) + 5;
        } else if (i < 512) {
            if (i2 <= 80) {
                return 40;
            }
            return 40 + (((i2 - 80) + 1) / 2);
        } else if (i2 <= 80) {
            return 5;
        } else {
            if (i2 <= 100) {
                return 7;
            }
            return 7 + (((i2 - 100) + 1) / 2);
        }
    }

    public static boolean removeThreadOverride(String str) {
        return Properties.removeThreadOverride(str);
    }

    public static boolean setThreadOverride(String str, boolean z) {
        return Properties.setThreadOverride(str, z);
    }

    private static void validate(BigInteger bigInteger) {
        if ((bigInteger.intValue() & 1) == 0) {
            throw new IllegalArgumentException("RSA modulus is even");
        }
        if (Properties.isOverrideSet("org.bouncycastle.rsa.allow_unsafe_mod")) {
            return;
        }
        if (Properties.asInteger("org.bouncycastle.rsa.max_size", 15360) < bigInteger.bitLength()) {
            throw new IllegalArgumentException("modulus value out of range");
        }
        if (!bigInteger.gcd(SMALL_PRIMES_PRODUCT).equals(ONE)) {
            throw new IllegalArgumentException("RSA modulus has a small prime factor");
        }
        int bitLength = bigInteger.bitLength() / 2;
        if (!Primes.enhancedMRProbablePrimeTest(bigInteger, CryptoServicesRegistrar.getSecureRandom(), bitLength >= 1536 ? 3 : bitLength >= 1024 ? 4 : bitLength >= 512 ? 7 : 50).isProvablyComposite()) {
            throw new IllegalArgumentException("RSA modulus is not composite");
        }
    }
}
