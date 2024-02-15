package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.dilithium.DilithiumKeyFactorySpi;

/* loaded from: classes2.dex */
public class Dilithium {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.dilithium.";

    /* loaded from: classes2.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.DILITHIUM", "org.bouncycastle.pqc.jcajce.provider.dilithium.DilithiumKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.DILITHIUM", "org.bouncycastle.pqc.jcajce.provider.dilithium.DilithiumKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.DILITHIUM", "org.bouncycastle.pqc.jcajce.provider.dilithium.DilithiumKeyGeneratorSpi");
            addSignatureAlgorithm(configurableProvider, "DILITHIUM", "org.bouncycastle.pqc.jcajce.provider.dilithium.SignatureSpi$Base", BCObjectIdentifiers.dilithium);
            addSignatureAlias(configurableProvider, "DILITHIUM", BCObjectIdentifiers.dilithium2);
            addSignatureAlias(configurableProvider, "DILITHIUM", BCObjectIdentifiers.dilithium3);
            addSignatureAlias(configurableProvider, "DILITHIUM", BCObjectIdentifiers.dilithium5);
            addSignatureAlias(configurableProvider, "DILITHIUM", BCObjectIdentifiers.dilithium2_aes);
            addSignatureAlias(configurableProvider, "DILITHIUM", BCObjectIdentifiers.dilithium3_aes);
            addSignatureAlias(configurableProvider, "DILITHIUM", BCObjectIdentifiers.dilithium5_aes);
            DilithiumKeyFactorySpi dilithiumKeyFactorySpi = new DilithiumKeyFactorySpi();
            registerOid(configurableProvider, BCObjectIdentifiers.dilithium2, "DILITHIUM", dilithiumKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.dilithium3, "DILITHIUM", dilithiumKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.dilithium5, "DILITHIUM", dilithiumKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.dilithium2_aes, "DILITHIUM", dilithiumKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.dilithium3_aes, "DILITHIUM", dilithiumKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.dilithium5_aes, "DILITHIUM", dilithiumKeyFactorySpi);
        }
    }
}
