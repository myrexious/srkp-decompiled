package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.util.Strings;

/* loaded from: classes2.dex */
public class DilithiumParameterSpec implements AlgorithmParameterSpec {
    public static final DilithiumParameterSpec dilithium2;
    public static final DilithiumParameterSpec dilithium2_aes;
    public static final DilithiumParameterSpec dilithium3;
    public static final DilithiumParameterSpec dilithium3_aes;
    public static final DilithiumParameterSpec dilithium5;
    public static final DilithiumParameterSpec dilithium5_aes;
    private static Map parameters;
    private final String name;

    static {
        DilithiumParameterSpec dilithiumParameterSpec = new DilithiumParameterSpec(DilithiumParameters.dilithium2);
        dilithium2 = dilithiumParameterSpec;
        DilithiumParameterSpec dilithiumParameterSpec2 = new DilithiumParameterSpec(DilithiumParameters.dilithium3);
        dilithium3 = dilithiumParameterSpec2;
        DilithiumParameterSpec dilithiumParameterSpec3 = new DilithiumParameterSpec(DilithiumParameters.dilithium5);
        dilithium5 = dilithiumParameterSpec3;
        DilithiumParameterSpec dilithiumParameterSpec4 = new DilithiumParameterSpec(DilithiumParameters.dilithium2_aes);
        dilithium2_aes = dilithiumParameterSpec4;
        DilithiumParameterSpec dilithiumParameterSpec5 = new DilithiumParameterSpec(DilithiumParameters.dilithium3_aes);
        dilithium3_aes = dilithiumParameterSpec5;
        DilithiumParameterSpec dilithiumParameterSpec6 = new DilithiumParameterSpec(DilithiumParameters.dilithium5_aes);
        dilithium5_aes = dilithiumParameterSpec6;
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put("dilithium2", dilithiumParameterSpec);
        parameters.put("dilithium3", dilithiumParameterSpec2);
        parameters.put("dilithium5", dilithiumParameterSpec3);
        parameters.put("dilithium2-aes", dilithiumParameterSpec4);
        parameters.put("dilithium3-aes", dilithiumParameterSpec5);
        parameters.put("dilithium5-aes", dilithiumParameterSpec6);
    }

    private DilithiumParameterSpec(DilithiumParameters dilithiumParameters) {
        this.name = dilithiumParameters.getName();
    }

    public static DilithiumParameterSpec fromName(String str) {
        return (DilithiumParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
