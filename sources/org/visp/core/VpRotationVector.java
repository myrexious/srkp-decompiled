package org.visp.core;

/* loaded from: classes4.dex */
public class VpRotationVector {
    public final long nativeObj;

    private static native long VpRotationVector_0(int i);

    private static native long VpRotationVector_1(long j);

    private static native long VpRotationVector_2();

    private static native void delete(long j);

    private static native double sumSquare_0(long j);

    private static native long t_0(long j);

    private static native String toString(long j);

    public VpRotationVector(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpRotationVector __fromPtr__(long j) {
        return new VpRotationVector(j);
    }

    public VpRotationVector(int i) {
        this.nativeObj = VpRotationVector_0(i);
    }

    public VpRotationVector(VpRotationVector vpRotationVector) {
        this.nativeObj = VpRotationVector_1(vpRotationVector.nativeObj);
    }

    public VpRotationVector() {
        this.nativeObj = VpRotationVector_2();
    }

    public double sumSquare() {
        return sumSquare_0(this.nativeObj);
    }

    public VpRowVector t() {
        return new VpRowVector(t_0(this.nativeObj));
    }

    public String toString() {
        return toString(this.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
