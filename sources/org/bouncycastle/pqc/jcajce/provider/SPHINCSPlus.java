package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyFactorySpi;

/* loaded from: classes2.dex */
public class SPHINCSPlus {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.sphincsplus.";

    /* loaded from: classes2.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.SPHINCSPLUS", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.SPHINCSPLUS", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("Alg.Alias.KeyFactory.SPHINCS+", "SPHINCSPLUS");
            configurableProvider.addAlgorithm("Alg.Alias.KeyPairGenerator.SPHINCS+", "SPHINCSPLUS");
            addSignatureAlgorithm(configurableProvider, "SPHINCSPLUS", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SignatureSpi$Direct", BCObjectIdentifiers.sphincsPlus);
            addSignatureAlias(configurableProvider, "SPHINCSPLUS", BCObjectIdentifiers.sphincsPlus_shake_256);
            addSignatureAlias(configurableProvider, "SPHINCSPLUS", BCObjectIdentifiers.sphincsPlus_sha_256);
            addSignatureAlias(configurableProvider, "SPHINCSPLUS", BCObjectIdentifiers.sphincsPlus_sha_512);
            addSignatureAlias(configurableProvider, "SPHINCSPLUS", BCObjectIdentifiers.sphincsPlus_haraka);
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SPHINCS+", "SPHINCSPLUS");
            SPHINCSPlusKeyFactorySpi sPHINCSPlusKeyFactorySpi = new SPHINCSPlusKeyFactorySpi();
            registerOid(configurableProvider, BCObjectIdentifiers.sphincsPlus, "SPHINCSPLUS", sPHINCSPlusKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.sphincsPlus_shake_256, "SPHINCSPLUS", sPHINCSPlusKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.sphincsPlus_sha_256, "SPHINCSPLUS", sPHINCSPlusKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.sphincsPlus_sha_512, "SPHINCSPLUS", sPHINCSPlusKeyFactorySpi);
            registerOid(configurableProvider, BCObjectIdentifiers.sphincsPlus_haraka, "SPHINCSPLUS", sPHINCSPlusKeyFactorySpi);
        }
    }
}