package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi;

/* loaded from: classes2.dex */
public class Kyber {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.kyber.";

    /* loaded from: classes2.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyGeneratorSpi");
            KyberKeyFactorySpi kyberKeyFactorySpi = new KyberKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberCipherSpi$Base");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + BCObjectIdentifiers.pqc_kem_kyber, "KYBER");
            registerOid(configurableProvider, BCObjectIdentifiers.pqc_kem_kyber, "KYBER", kyberKeyFactorySpi);
        }
    }
}