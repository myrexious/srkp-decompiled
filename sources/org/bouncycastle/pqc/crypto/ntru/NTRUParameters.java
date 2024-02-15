package org.bouncycastle.pqc.crypto.ntru;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPS2048509;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPS2048677;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPS4096821;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHRSS701;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUParameterSet;

/* loaded from: classes2.dex */
public class NTRUParameters implements CipherParameters {
    public static final NTRUParameters ntruhps2048509 = new NTRUParameters("ntruhps2048509", new NTRUHPS2048509());
    public static final NTRUParameters ntruhps2048677 = new NTRUParameters("ntruhps2048677", new NTRUHPS2048677());
    public static final NTRUParameters ntruhps4096821 = new NTRUParameters("ntruhps4096821", new NTRUHPS4096821());
    public static final NTRUParameters ntruhrss701 = new NTRUParameters("ntruhrss701", new NTRUHRSS701());
    private final String name;
    final NTRUParameterSet parameterSet;

    private NTRUParameters(String str, NTRUParameterSet nTRUParameterSet) {
        this.name = str;
        this.parameterSet = nTRUParameterSet;
    }

    public String getName() {
        return this.name;
    }

    public int getSessionKeySize() {
        return this.parameterSet.sharedKeyBytes() * 8;
    }
}
