package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.sike.SIKEKeyFactorySpi;

/* loaded from: classes2.dex */
public class SIKE {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.sike.";

    /* loaded from: classes2.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.SIKE", "org.bouncycastle.pqc.jcajce.provider.sike.SIKEKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.SIKE", "org.bouncycastle.pqc.jcajce.provider.sike.SIKEKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.SIKE", "org.bouncycastle.pqc.jcajce.provider.sike.SIKEKeyGeneratorSpi");
            SIKEKeyFactorySpi sIKEKeyFactorySpi = new SIKEKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.SIKE", "org.bouncycastle.pqc.jcajce.provider.sike.SIKECipherSpi$Base");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher." + BCObjectIdentifiers.pqc_kem_sike, "SIKE");
            registerOid(configurableProvider, BCObjectIdentifiers.pqc_kem_sike, "SIKE", sIKEKeyFactorySpi);
        }
    }
}
