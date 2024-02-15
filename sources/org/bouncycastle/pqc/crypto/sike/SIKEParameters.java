package org.bouncycastle.pqc.crypto.sike;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* loaded from: classes2.dex */
public class SIKEParameters {
    private final SIKEEngine engine;
    private final String name;
    public static final SIKEParameters sikep434 = new SIKEParameters(434, false, "sikep434");
    public static final SIKEParameters sikep503 = new SIKEParameters(TypedValues.PositionType.TYPE_PERCENT_WIDTH, false, "sikep503");
    public static final SIKEParameters sikep610 = new SIKEParameters(TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, false, "sikep610");
    public static final SIKEParameters sikep751 = new SIKEParameters(751, false, "sikep751");
    public static final SIKEParameters sikep434_compressed = new SIKEParameters(434, true, "sikep434_compressed");
    public static final SIKEParameters sikep503_compressed = new SIKEParameters(TypedValues.PositionType.TYPE_PERCENT_WIDTH, true, "sikep503_compressed");
    public static final SIKEParameters sikep610_compressed = new SIKEParameters(TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, true, "sikep610_compressed");
    public static final SIKEParameters sikep751_compressed = new SIKEParameters(751, true, "sikep751_compressed");

    private SIKEParameters(int i, boolean z, String str) {
        this.name = str;
        this.engine = new SIKEEngine(i, z);
    }

    public SIKEEngine getEngine() {
        return this.engine;
    }

    public String getName() {
        return this.name;
    }

    public int getSessionKeySize() {
        return this.engine.getDefaultSessionKeySize();
    }
}
