package org.opencv.video;

/* loaded from: classes4.dex */
public class DualTVL1OpticalFlow extends DenseOpticalFlow {
    private static native long create_0(double d, double d2, double d3, int i, int i2, double d4, int i3, int i4, double d5, double d6, int i5, boolean z);

    private static native long create_1();

    private static native void delete(long j);

    private static native double getEpsilon_0(long j);

    private static native double getGamma_0(long j);

    private static native int getInnerIterations_0(long j);

    private static native double getLambda_0(long j);

    private static native int getMedianFiltering_0(long j);

    private static native int getOuterIterations_0(long j);

    private static native double getScaleStep_0(long j);

    private static native int getScalesNumber_0(long j);

    private static native double getTau_0(long j);

    private static native double getTheta_0(long j);

    private static native boolean getUseInitialFlow_0(long j);

    private static native int getWarpingsNumber_0(long j);

    private static native void setEpsilon_0(long j, double d);

    private static native void setGamma_0(long j, double d);

    private static native void setInnerIterations_0(long j, int i);

    private static native void setLambda_0(long j, double d);

    private static native void setMedianFiltering_0(long j, int i);

    private static native void setOuterIterations_0(long j, int i);

    private static native void setScaleStep_0(long j, double d);

    private static native void setScalesNumber_0(long j, int i);

    private static native void setTau_0(long j, double d);

    private static native void setTheta_0(long j, double d);

    private static native void setUseInitialFlow_0(long j, boolean z);

    private static native void setWarpingsNumber_0(long j, int i);

    protected DualTVL1OpticalFlow(long j) {
        super(j);
    }

    public static DualTVL1OpticalFlow __fromPtr__(long j) {
        return new DualTVL1OpticalFlow(j);
    }

    public static DualTVL1OpticalFlow create(double d, double d2, double d3, int i, int i2, double d4, int i3, int i4, double d5, double d6, int i5, boolean z) {
        return __fromPtr__(create_0(d, d2, d3, i, i2, d4, i3, i4, d5, d6, i5, z));
    }

    public static DualTVL1OpticalFlow create() {
        return __fromPtr__(create_1());
    }

    public boolean getUseInitialFlow() {
        return getUseInitialFlow_0(this.nativeObj);
    }

    public double getEpsilon() {
        return getEpsilon_0(this.nativeObj);
    }

    public double getGamma() {
        return getGamma_0(this.nativeObj);
    }

    public double getLambda() {
        return getLambda_0(this.nativeObj);
    }

    public double getScaleStep() {
        return getScaleStep_0(this.nativeObj);
    }

    public double getTau() {
        return getTau_0(this.nativeObj);
    }

    public double getTheta() {
        return getTheta_0(this.nativeObj);
    }

    public int getInnerIterations() {
        return getInnerIterations_0(this.nativeObj);
    }

    public int getMedianFiltering() {
        return getMedianFiltering_0(this.nativeObj);
    }

    public int getOuterIterations() {
        return getOuterIterations_0(this.nativeObj);
    }

    public int getScalesNumber() {
        return getScalesNumber_0(this.nativeObj);
    }

    public int getWarpingsNumber() {
        return getWarpingsNumber_0(this.nativeObj);
    }

    public void setEpsilon(double d) {
        setEpsilon_0(this.nativeObj, d);
    }

    public void setGamma(double d) {
        setGamma_0(this.nativeObj, d);
    }

    public void setInnerIterations(int i) {
        setInnerIterations_0(this.nativeObj, i);
    }

    public void setLambda(double d) {
        setLambda_0(this.nativeObj, d);
    }

    public void setMedianFiltering(int i) {
        setMedianFiltering_0(this.nativeObj, i);
    }

    public void setOuterIterations(int i) {
        setOuterIterations_0(this.nativeObj, i);
    }

    public void setScaleStep(double d) {
        setScaleStep_0(this.nativeObj, d);
    }

    public void setScalesNumber(int i) {
        setScalesNumber_0(this.nativeObj, i);
    }

    public void setTau(double d) {
        setTau_0(this.nativeObj, d);
    }

    public void setTheta(double d) {
        setTheta_0(this.nativeObj, d);
    }

    public void setUseInitialFlow(boolean z) {
        setUseInitialFlow_0(this.nativeObj, z);
    }

    public void setWarpingsNumber(int i) {
        setWarpingsNumber_0(this.nativeObj, i);
    }

    @Override // org.opencv.video.DenseOpticalFlow, org.opencv.core.Algorithm
    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
