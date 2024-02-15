package org.opencv.video;

/* loaded from: classes4.dex */
public class FarnebackOpticalFlow extends DenseOpticalFlow {
    private static native long create_0(int i, double d, boolean z, int i2, int i3, int i4, double d2, int i5);

    private static native long create_1();

    private static native void delete(long j);

    private static native boolean getFastPyramids_0(long j);

    private static native int getFlags_0(long j);

    private static native int getNumIters_0(long j);

    private static native int getNumLevels_0(long j);

    private static native int getPolyN_0(long j);

    private static native double getPolySigma_0(long j);

    private static native double getPyrScale_0(long j);

    private static native int getWinSize_0(long j);

    private static native void setFastPyramids_0(long j, boolean z);

    private static native void setFlags_0(long j, int i);

    private static native void setNumIters_0(long j, int i);

    private static native void setNumLevels_0(long j, int i);

    private static native void setPolyN_0(long j, int i);

    private static native void setPolySigma_0(long j, double d);

    private static native void setPyrScale_0(long j, double d);

    private static native void setWinSize_0(long j, int i);

    protected FarnebackOpticalFlow(long j) {
        super(j);
    }

    public static FarnebackOpticalFlow __fromPtr__(long j) {
        return new FarnebackOpticalFlow(j);
    }

    public static FarnebackOpticalFlow create(int i, double d, boolean z, int i2, int i3, int i4, double d2, int i5) {
        return __fromPtr__(create_0(i, d, z, i2, i3, i4, d2, i5));
    }

    public static FarnebackOpticalFlow create() {
        return __fromPtr__(create_1());
    }

    public boolean getFastPyramids() {
        return getFastPyramids_0(this.nativeObj);
    }

    public double getPolySigma() {
        return getPolySigma_0(this.nativeObj);
    }

    public double getPyrScale() {
        return getPyrScale_0(this.nativeObj);
    }

    public int getFlags() {
        return getFlags_0(this.nativeObj);
    }

    public int getNumIters() {
        return getNumIters_0(this.nativeObj);
    }

    public int getNumLevels() {
        return getNumLevels_0(this.nativeObj);
    }

    public int getPolyN() {
        return getPolyN_0(this.nativeObj);
    }

    public int getWinSize() {
        return getWinSize_0(this.nativeObj);
    }

    public void setFastPyramids(boolean z) {
        setFastPyramids_0(this.nativeObj, z);
    }

    public void setFlags(int i) {
        setFlags_0(this.nativeObj, i);
    }

    public void setNumIters(int i) {
        setNumIters_0(this.nativeObj, i);
    }

    public void setNumLevels(int i) {
        setNumLevels_0(this.nativeObj, i);
    }

    public void setPolyN(int i) {
        setPolyN_0(this.nativeObj, i);
    }

    public void setPolySigma(double d) {
        setPolySigma_0(this.nativeObj, d);
    }

    public void setPyrScale(double d) {
        setPyrScale_0(this.nativeObj, d);
    }

    public void setWinSize(int i) {
        setWinSize_0(this.nativeObj, i);
    }

    @Override // org.opencv.video.DenseOpticalFlow, org.opencv.core.Algorithm
    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
