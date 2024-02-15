package org.bouncycastle.jcajce.provider.asymmetric;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.internal.asn1.bsi.BSIObjectIdentifiers;
import org.bouncycastle.internal.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.internal.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.util.Properties;

/* loaded from: classes2.dex */
public class EC {
    private static final String PREFIX = "org.bouncycastle.jcajce.provider.asymmetric.ec.";
    private static final Map<String, String> generalEcAttributes;

    /* loaded from: classes2.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("AlgorithmParameters.EC", "org.bouncycastle.jcajce.provider.asymmetric.ec.AlgorithmParametersSpi");
            configurableProvider.addAlgorithm("KeyAgreement.ECDH", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DH", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECDHC", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDH", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHU", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECDHWITHSHA1KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA1KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA1KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA1KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECDHWITHSHA224KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA224KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA224KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA224KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECDHWITHSHA256KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA256KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA256KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA256KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECDHWITHSHA384KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA384KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA384KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA384KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECDHWITHSHA512KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA512KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA512KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA512KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA1KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", X9ObjectIdentifiers.dhSinglePass_cofactorDH_sha1kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA1KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", SECObjectIdentifiers.dhSinglePass_stdDH_sha224kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA224KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", SECObjectIdentifiers.dhSinglePass_cofactorDH_sha224kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA224KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", SECObjectIdentifiers.dhSinglePass_stdDH_sha256kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA256KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", SECObjectIdentifiers.dhSinglePass_cofactorDH_sha256kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA256KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", SECObjectIdentifiers.dhSinglePass_stdDH_sha384kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA384KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", SECObjectIdentifiers.dhSinglePass_cofactorDH_sha384kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA384KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", SECObjectIdentifiers.dhSinglePass_stdDH_sha512kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA512KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", SECObjectIdentifiers.dhSinglePass_cofactorDH_sha512kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA512KDFAndSharedInfo", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA1CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA1CKDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA256CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA256CKDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA384CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA384CKDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA512CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA512CKDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHUWITHSHA1CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUwithSHA1CKDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHUWITHSHA224CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUwithSHA224CKDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHUWITHSHA256CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUwithSHA256CKDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHUWITHSHA384CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUwithSHA384CKDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHUWITHSHA512CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUwithSHA512CKDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHUWITHSHA1KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUwithSHA1KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHUWITHSHA224KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUwithSHA224KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHUWITHSHA256KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUwithSHA256KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHUWITHSHA384KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUwithSHA384KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHUWITHSHA512KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHUwithSHA512KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECKAEGWITHSHA1KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithSHA1KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECKAEGWITHSHA224KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithSHA224KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECKAEGWITHSHA256KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithSHA256KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECKAEGWITHSHA384KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithSHA384KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECKAEGWITHSHA512KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithSHA512KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", BSIObjectIdentifiers.ecka_eg_X963kdf_SHA1, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithSHA1KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", BSIObjectIdentifiers.ecka_eg_X963kdf_SHA224, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithSHA224KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", BSIObjectIdentifiers.ecka_eg_X963kdf_SHA256, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithSHA256KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", BSIObjectIdentifiers.ecka_eg_X963kdf_SHA384, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithSHA384KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", BSIObjectIdentifiers.ecka_eg_X963kdf_SHA512, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithSHA512KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement", BSIObjectIdentifiers.ecka_eg_X963kdf_RIPEMD160, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithRIPEMD160KDF", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECKAEGWITHRIPEMD160KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$ECKAEGwithRIPEMD160KDF", EC.generalEcAttributes);
            registerOid(configurableProvider, X9ObjectIdentifiers.id_ecPublicKey, "EC", new KeyFactorySpi.EC());
            registerOid(configurableProvider, X9ObjectIdentifiers.dhSinglePass_cofactorDH_sha1kdf_scheme, "EC", new KeyFactorySpi.EC());
            registerOid(configurableProvider, X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
            registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha224kdf_scheme, "EC", new KeyFactorySpi.EC());
            registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha224kdf_scheme, "EC", new KeyFactorySpi.EC());
            registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha256kdf_scheme, "EC", new KeyFactorySpi.EC());
            registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha256kdf_scheme, "EC", new KeyFactorySpi.EC());
            registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha384kdf_scheme, "EC", new KeyFactorySpi.EC());
            registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha384kdf_scheme, "EC", new KeyFactorySpi.EC());
            registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha512kdf_scheme, "EC", new KeyFactorySpi.EC());
            registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha512kdf_scheme, "EC", new KeyFactorySpi.EC());
            registerOidAlgorithmParameters(configurableProvider, X9ObjectIdentifiers.id_ecPublicKey, "EC");
            registerOidAlgorithmParameters(configurableProvider, X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, "EC");
            registerOidAlgorithmParameters(configurableProvider, X9ObjectIdentifiers.dhSinglePass_cofactorDH_sha1kdf_scheme, "EC");
            registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha224kdf_scheme, "EC");
            registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha224kdf_scheme, "EC");
            registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha256kdf_scheme, "EC");
            registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha256kdf_scheme, "EC");
            registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha384kdf_scheme, "EC");
            registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha384kdf_scheme, "EC");
            registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha512kdf_scheme, "EC");
            registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha512kdf_scheme, "EC");
            if (!Properties.isOverrideSet("org.bouncycastle.ec.disable_mqv")) {
                configurableProvider.addAlgorithm("KeyAgreement.ECMQV", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQV", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA1CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA1CKDF", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA224CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA224CKDF", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA256CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA256CKDF", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA384CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA384CKDF", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA512CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA512CKDF", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA1KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA1KDF", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA224KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA224KDF", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA256KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA256KDF", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA384KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA384KDF", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA512KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA512KDF", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement." + X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA1KDFAndSharedInfo", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA224KDFAndSharedInfo", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA256KDFAndSharedInfo", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA384KDFAndSharedInfo", EC.generalEcAttributes);
                configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA512KDFAndSharedInfo", EC.generalEcAttributes);
                registerOid(configurableProvider, X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, "EC", new KeyFactorySpi.EC());
                registerOidAlgorithmParameters(configurableProvider, X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, "EC");
                registerOid(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
                registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme, "EC");
                registerOid(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
                registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme, "EC");
                registerOid(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
                registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme, "EC");
                registerOid(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
                registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme, "EC");
                configurableProvider.addAlgorithm("KeyFactory.ECMQV", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECMQV");
                configurableProvider.addAlgorithm("KeyPairGenerator.ECMQV", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECMQV");
            }
            configurableProvider.addAlgorithm("KeyFactory.EC", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$EC");
            configurableProvider.addAlgorithm("KeyFactory.ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDSA");
            configurableProvider.addAlgorithm("KeyFactory.ECDH", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDH");
            configurableProvider.addAlgorithm("KeyFactory.ECDHC", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDHC");
            configurableProvider.addAlgorithm("KeyPairGenerator.EC", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$EC");
            configurableProvider.addAlgorithm("KeyPairGenerator.ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDSA");
            configurableProvider.addAlgorithm("KeyPairGenerator.ECDH", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDH");
            configurableProvider.addAlgorithm("KeyPairGenerator.ECDHWITHSHA1KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDH");
            configurableProvider.addAlgorithm("KeyPairGenerator.ECDHC", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDHC");
            configurableProvider.addAlgorithm("KeyPairGenerator.ECIES", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDH");
            configurableProvider.addAlgorithm("Cipher.ECIES", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIES", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA1", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIES", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA1", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIES", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA256", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA256", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA256", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA256", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA384", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA384", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA384", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA384", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA512", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA512", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA512", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA512", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAESCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAESCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA1andAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAESCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA1ANDAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAESCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA256andAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA256andAESCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA256ANDAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA256andAESCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA384andAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA384andAESCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA384ANDAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA384andAESCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA512andAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA512andAESCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA512ANDAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA512andAESCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESedeCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESedeCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA1andDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESedeCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA1ANDDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESedeCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA256andDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA256andDESedeCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA256ANDDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA256andDESedeCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA384andDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA384andDESedeCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA384ANDDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA384andDESedeCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESwithSHA512andDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA512andDESedeCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ECIESWITHSHA512ANDDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithSHA512andDESedeCBC", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Cipher.ETSIKEMWITHSHA256", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESKEMCipher$KEMwithSHA256", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.NONEwithECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSAnone", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1withECDSA", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.ECDSAwithSHA1", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1WITHECDSA", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.ECDSAWITHSHA1", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1WithECDSA", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.ECDSAWithSHA1", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.1.2.840.10045.4.1", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature." + TeleTrusTObjectIdentifiers.ecSignWithSha1, "ECDSA");
            configurableProvider.addAlgorithm("Signature.ECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA1WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA224WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA224", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA256WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA256", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA384WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA384", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA512WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA512", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA3-224WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSASha3_224", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA3-256WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSASha3_256", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA3-384WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSASha3_384", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA3-512WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSASha3_512", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Alg.Alias.Signature.DETECDSA", "ECDDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1WITHDETECDSA", "SHA1WITHECDDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA224WITHDETECDSA", "SHA224WITHECDDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA256WITHDETECDSA", "SHA256WITHECDDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA384WITHDETECDSA", "SHA384WITHECDDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA512WITHDETECDSA", "SHA512WITHECDDSA");
            addSignatureAlgorithm(configurableProvider, "SHA224", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA224", X9ObjectIdentifiers.ecdsa_with_SHA224, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA256", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA256", X9ObjectIdentifiers.ecdsa_with_SHA256, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA384", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA384", X9ObjectIdentifiers.ecdsa_with_SHA384, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA512", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA512", X9ObjectIdentifiers.ecdsa_with_SHA512, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, MessageDigestAlgorithms.SHA3_224, "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSASha3_224", NISTObjectIdentifiers.id_ecdsa_with_sha3_224, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA3-256", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSASha3_256", NISTObjectIdentifiers.id_ecdsa_with_sha3_256, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, MessageDigestAlgorithms.SHA3_384, "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSASha3_384", NISTObjectIdentifiers.id_ecdsa_with_sha3_384, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, MessageDigestAlgorithms.SHA3_512, "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSASha3_512", NISTObjectIdentifiers.id_ecdsa_with_sha3_512, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHAKE128", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSAShake128", CMSObjectIdentifiers.id_ecdsa_with_shake128, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHAKE256", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSAShake256", CMSObjectIdentifiers.id_ecdsa_with_shake256, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "RIPEMD160", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSARipeMD160", TeleTrusTObjectIdentifiers.ecSignWithRipemd160, EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA1WITHECNR", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA224WITHECNR", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR224", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA256WITHECNR", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR256", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA384WITHECNR", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR384", EC.generalEcAttributes);
            configurableProvider.addAlgorithm("Signature.SHA512WITHECNR", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR512", EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA1", "CVC-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_1, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA224", "CVC-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA224", EACObjectIdentifiers.id_TA_ECDSA_SHA_224, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA256", "CVC-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA256", EACObjectIdentifiers.id_TA_ECDSA_SHA_256, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA384", "CVC-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA384", EACObjectIdentifiers.id_TA_ECDSA_SHA_384, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA512", "CVC-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA512", EACObjectIdentifiers.id_TA_ECDSA_SHA_512, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA1", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA", BSIObjectIdentifiers.ecdsa_plain_SHA1, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA224", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA224", BSIObjectIdentifiers.ecdsa_plain_SHA224, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA256", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA256", BSIObjectIdentifiers.ecdsa_plain_SHA256, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA384", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA384", BSIObjectIdentifiers.ecdsa_plain_SHA384, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA512", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA512", BSIObjectIdentifiers.ecdsa_plain_SHA512, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "RIPEMD160", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecPlainDSARP160", BSIObjectIdentifiers.ecdsa_plain_RIPEMD160, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, MessageDigestAlgorithms.SHA3_224, "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA3_224", BSIObjectIdentifiers.ecdsa_plain_SHA3_224, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, "SHA3-256", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA3_256", BSIObjectIdentifiers.ecdsa_plain_SHA3_256, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, MessageDigestAlgorithms.SHA3_384, "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA3_384", BSIObjectIdentifiers.ecdsa_plain_SHA3_384, EC.generalEcAttributes);
            addSignatureAlgorithm(configurableProvider, MessageDigestAlgorithms.SHA3_512, "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA3_512", BSIObjectIdentifiers.ecdsa_plain_SHA3_512, EC.generalEcAttributes);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        generalEcAttributes = hashMap;
        hashMap.put("SupportedKeyClasses", "java.security.interfaces.ECPublicKey|java.security.interfaces.ECPrivateKey");
        hashMap.put("SupportedKeyFormats", "PKCS#8|X.509");
    }
}
