package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.util.Strings;

/* loaded from: classes2.dex */
public class KyberParameterSpec implements AlgorithmParameterSpec {
    public static final KyberParameterSpec kyber1024;
    public static final KyberParameterSpec kyber1024_aes;
    public static final KyberParameterSpec kyber512;
    public static final KyberParameterSpec kyber512_aes;
    public static final KyberParameterSpec kyber768;
    public static final KyberParameterSpec kyber768_aes;
    private static Map parameters;
    private final String name;

    static {
        KyberParameterSpec kyberParameterSpec = new KyberParameterSpec(KyberParameters.kyber512);
        kyber512 = kyberParameterSpec;
        KyberParameterSpec kyberParameterSpec2 = new KyberParameterSpec(KyberParameters.kyber768);
        kyber768 = kyberParameterSpec2;
        KyberParameterSpec kyberParameterSpec3 = new KyberParameterSpec(KyberParameters.kyber1024);
        kyber1024 = kyberParameterSpec3;
        KyberParameterSpec kyberParameterSpec4 = new KyberParameterSpec(KyberParameters.kyber512_aes);
        kyber512_aes = kyberParameterSpec4;
        KyberParameterSpec kyberParameterSpec5 = new KyberParameterSpec(KyberParameters.kyber768_aes);
        kyber768_aes = kyberParameterSpec5;
        KyberParameterSpec kyberParameterSpec6 = new KyberParameterSpec(KyberParameters.kyber1024_aes);
        kyber1024_aes = kyberParameterSpec6;
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put("kyber512", kyberParameterSpec);
        parameters.put("kyber768", kyberParameterSpec2);
        parameters.put("kyber1024", kyberParameterSpec3);
        parameters.put("kyber512-aes", kyberParameterSpec4);
        parameters.put("kyber768-aes", kyberParameterSpec5);
        parameters.put("kyber1024-aes", kyberParameterSpec6);
    }

    private KyberParameterSpec(KyberParameters kyberParameters) {
        this.name = kyberParameters.getName();
    }

    public static KyberParameterSpec fromName(String str) {
        return (KyberParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
