package org.opencv.ml;

/* loaded from: classes4.dex */
public class ANN_MLP_ANNEAL extends ANN_MLP {
    private static native void delete(long j);

    private static native double getAnnealCoolingRatio_0(long j);

    private static native double getAnnealFinalT_0(long j);

    private static native double getAnnealInitialT_0(long j);

    private static native int getAnnealItePerStep_0(long j);

    private static native void setAnnealCoolingRatio_0(long j, double d);

    private static native void setAnnealFinalT_0(long j, double d);

    private static native void setAnnealInitialT_0(long j, double d);

    private static native void setAnnealItePerStep_0(long j, int i);

    protected ANN_MLP_ANNEAL(long j) {
        super(j);
    }

    public static ANN_MLP_ANNEAL __fromPtr__(long j) {
        return new ANN_MLP_ANNEAL(j);
    }

    @Override // org.opencv.ml.ANN_MLP
    public double getAnnealCoolingRatio() {
        return getAnnealCoolingRatio_0(this.nativeObj);
    }

    @Override // org.opencv.ml.ANN_MLP
    public double getAnnealFinalT() {
        return getAnnealFinalT_0(this.nativeObj);
    }

    @Override // org.opencv.ml.ANN_MLP
    public double getAnnealInitialT() {
        return getAnnealInitialT_0(this.nativeObj);
    }

    @Override // org.opencv.ml.ANN_MLP
    public int getAnnealItePerStep() {
        return getAnnealItePerStep_0(this.nativeObj);
    }

    @Override // org.opencv.ml.ANN_MLP
    public void setAnnealCoolingRatio(double d) {
        setAnnealCoolingRatio_0(this.nativeObj, d);
    }

    @Override // org.opencv.ml.ANN_MLP
    public void setAnnealFinalT(double d) {
        setAnnealFinalT_0(this.nativeObj, d);
    }

    @Override // org.opencv.ml.ANN_MLP
    public void setAnnealInitialT(double d) {
        setAnnealInitialT_0(this.nativeObj, d);
    }

    @Override // org.opencv.ml.ANN_MLP
    public void setAnnealItePerStep(int i) {
        setAnnealItePerStep_0(this.nativeObj, i);
    }

    @Override // org.opencv.ml.ANN_MLP, org.opencv.ml.StatModel, org.opencv.core.Algorithm
    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
