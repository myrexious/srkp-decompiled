package org.visp.core;

/* loaded from: classes4.dex */
public class VpImagePoint {
    public final long nativeObj;

    private static native long VpImagePoint_0(double d, double d2);

    private static native long VpImagePoint_1(long j);

    private static native long VpImagePoint_2();

    private static native void delete(long j);

    private static native double distance_0(long j, long j2);

    private static native double get_i_0(long j);

    private static native double get_j_0(long j);

    private static native double get_u_0(long j);

    private static native double get_v_0(long j);

    private static native boolean inSegment_0(long j, long j2, long j3);

    private static native long nextInSegment_0(long j, long j2, long j3);

    private static native void set_i_0(long j, double d);

    private static native void set_ij_0(long j, double d, double d2);

    private static native void set_j_0(long j, double d);

    private static native void set_u_0(long j, double d);

    private static native void set_uv_0(long j, double d, double d2);

    private static native void set_v_0(long j, double d);

    private static native double sqrDistance_0(long j, long j2);

    private static native String toString(long j);

    public VpImagePoint(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpImagePoint __fromPtr__(long j) {
        return new VpImagePoint(j);
    }

    public VpImagePoint(double d, double d2) {
        this.nativeObj = VpImagePoint_0(d, d2);
    }

    public VpImagePoint(VpImagePoint vpImagePoint) {
        this.nativeObj = VpImagePoint_1(vpImagePoint.nativeObj);
    }

    public VpImagePoint() {
        this.nativeObj = VpImagePoint_2();
    }

    public boolean inSegment(VpImagePoint vpImagePoint, VpImagePoint vpImagePoint2) {
        return inSegment_0(this.nativeObj, vpImagePoint.nativeObj, vpImagePoint2.nativeObj);
    }

    public static double distance(VpImagePoint vpImagePoint, VpImagePoint vpImagePoint2) {
        return distance_0(vpImagePoint.nativeObj, vpImagePoint2.nativeObj);
    }

    public double get_i() {
        return get_i_0(this.nativeObj);
    }

    public double get_j() {
        return get_j_0(this.nativeObj);
    }

    public double get_u() {
        return get_u_0(this.nativeObj);
    }

    public double get_v() {
        return get_v_0(this.nativeObj);
    }

    public static double sqrDistance(VpImagePoint vpImagePoint, VpImagePoint vpImagePoint2) {
        return sqrDistance_0(vpImagePoint.nativeObj, vpImagePoint2.nativeObj);
    }

    public void set_i(double d) {
        set_i_0(this.nativeObj, d);
    }

    public void set_ij(double d, double d2) {
        set_ij_0(this.nativeObj, d, d2);
    }

    public void set_j(double d) {
        set_j_0(this.nativeObj, d);
    }

    public void set_u(double d) {
        set_u_0(this.nativeObj, d);
    }

    public void set_uv(double d, double d2) {
        set_uv_0(this.nativeObj, d, d2);
    }

    public void set_v(double d) {
        set_v_0(this.nativeObj, d);
    }

    public VpImagePoint nextInSegment(VpImagePoint vpImagePoint, VpImagePoint vpImagePoint2) {
        return new VpImagePoint(nextInSegment_0(this.nativeObj, vpImagePoint.nativeObj, vpImagePoint2.nativeObj));
    }

    public String toString() {
        return toString(this.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
