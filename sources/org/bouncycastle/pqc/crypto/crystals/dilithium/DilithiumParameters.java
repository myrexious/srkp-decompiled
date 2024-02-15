package org.bouncycastle.pqc.crypto.crystals.dilithium;

import java.security.SecureRandom;

/* loaded from: classes2.dex */
public class DilithiumParameters {
    public static final DilithiumParameters dilithium2 = new DilithiumParameters("dilithium2", 2, false);
    public static final DilithiumParameters dilithium2_aes = new DilithiumParameters("dilithium2-aes", 2, true);
    public static final DilithiumParameters dilithium3 = new DilithiumParameters("dilithium3", 3, false);
    public static final DilithiumParameters dilithium3_aes = new DilithiumParameters("dilithium3-aes", 3, true);
    public static final DilithiumParameters dilithium5 = new DilithiumParameters("dilithium5", 5, false);
    public static final DilithiumParameters dilithium5_aes = new DilithiumParameters("dilithium5-aes", 5, true);
    private final int k;
    private final String name;
    private final boolean usingAES;

    private DilithiumParameters(String str, int i, boolean z) {
        this.name = str;
        this.k = i;
        this.usingAES = z;
    }

    public DilithiumEngine getEngine(SecureRandom secureRandom) {
        return new DilithiumEngine(this.k, secureRandom, this.usingAES);
    }

    public String getName() {
        return this.name;
    }
}
