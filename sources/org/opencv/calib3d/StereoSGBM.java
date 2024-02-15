package org.opencv.calib3d;

/* loaded from: classes4.dex */
public class StereoSGBM extends StereoMatcher {
    public static final int MODE_HH = 1;
    public static final int MODE_HH4 = 3;
    public static final int MODE_SGBM = 0;
    public static final int MODE_SGBM_3WAY = 2;

    private static native long create_0(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11);

    private static native long create_1();

    private static native void delete(long j);

    private static native int getMode_0(long j);

    private static native int getP1_0(long j);

    private static native int getP2_0(long j);

    private static native int getPreFilterCap_0(long j);

    private static native int getUniquenessRatio_0(long j);

    private static native void setMode_0(long j, int i);

    private static native void setP1_0(long j, int i);

    private static native void setP2_0(long j, int i);

    private static native void setPreFilterCap_0(long j, int i);

    private static native void setUniquenessRatio_0(long j, int i);

    protected StereoSGBM(long j) {
        super(j);
    }

    public static StereoSGBM __fromPtr__(long j) {
        return new StereoSGBM(j);
    }

    public static StereoSGBM create(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        return __fromPtr__(create_0(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11));
    }

    public static StereoSGBM create() {
        return __fromPtr__(create_1());
    }

    public int getMode() {
        return getMode_0(this.nativeObj);
    }

    public int getP1() {
        return getP1_0(this.nativeObj);
    }

    public int getP2() {
        return getP2_0(this.nativeObj);
    }

    public int getPreFilterCap() {
        return getPreFilterCap_0(this.nativeObj);
    }

    public int getUniquenessRatio() {
        return getUniquenessRatio_0(this.nativeObj);
    }

    public void setMode(int i) {
        setMode_0(this.nativeObj, i);
    }

    public void setP1(int i) {
        setP1_0(this.nativeObj, i);
    }

    public void setP2(int i) {
        setP2_0(this.nativeObj, i);
    }

    public void setPreFilterCap(int i) {
        setPreFilterCap_0(this.nativeObj, i);
    }

    public void setUniquenessRatio(int i) {
        setUniquenessRatio_0(this.nativeObj, i);
    }

    @Override // org.opencv.calib3d.StereoMatcher, org.opencv.core.Algorithm
    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
