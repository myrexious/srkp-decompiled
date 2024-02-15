package org.visp.core;

/* loaded from: classes4.dex */
public class VpRowVector {
    public final long nativeObj;

    private static native long VpRowVector_0(int i, double d);

    private static native long VpRowVector_1(int i);

    private static native long VpRowVector_2(double[] dArr);

    private static native long VpRowVector_3(float[] fArr);

    private static native long VpRowVector_4(long j, int i);

    private static native long VpRowVector_5(long j);

    private static native long VpRowVector_6(long j, int i, int i2);

    private static native long VpRowVector_7(long j);

    private static native long VpRowVector_8();

    private static native void clear_0(long j);

    private static native void deg2rad_0(long j);

    private static native void delete(long j);

    private static native long extract_0(long j, int i, int i2);

    private static native double frobeniusNorm_0(long j);

    private static native void init_0(long j, long j2, int i, int i2);

    private static native void insert_0(long j, int i, long j2);

    private static native double mean_0(long j);

    private static native double median_0(long j);

    private static native long normalize_0(long j, long j2);

    private static native long normalize_1(long j);

    private static native void rad2deg_0(long j);

    private static native void reshape_0(long j, long j2, int i, int i2);

    private static native long reshape_1(long j, int i, int i2);

    private static native void resize_0(long j, int i, boolean z);

    private static native void resize_1(long j, int i);

    private static native void resize_2(long j, int i, int i2, boolean z);

    private static native void stack_0(long j, double d);

    private static native void stack_1(long j, long j2, long j3);

    private static native void stack_2(long j, long j2);

    private static native long stack_3(long j, long j2);

    private static native double stdev_0(long j, boolean z);

    private static native double stdev_1(long j);

    private static native double sumSquare_0(long j);

    private static native double sum_0(long j);

    private static native long t_0(long j);

    private static native String toString(long j);

    private static native void transpose_0(long j, long j2);

    private static native long transpose_1(long j);

    public VpRowVector(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpRowVector __fromPtr__(long j) {
        return new VpRowVector(j);
    }

    public VpRowVector(int i, double d) {
        this.nativeObj = VpRowVector_0(i, d);
    }

    public VpRowVector(int i) {
        this.nativeObj = VpRowVector_1(i);
    }

    public VpRowVector(double[] dArr) {
        this.nativeObj = VpRowVector_2(dArr);
    }

    public VpRowVector(float[] fArr) {
        this.nativeObj = VpRowVector_3(fArr);
    }

    public VpRowVector(VpMatrix vpMatrix, int i) {
        this.nativeObj = VpRowVector_4(vpMatrix.nativeObj, i);
    }

    public VpRowVector(VpMatrix vpMatrix) {
        this.nativeObj = VpRowVector_5(vpMatrix.nativeObj);
    }

    public VpRowVector(VpRowVector vpRowVector, int i, int i2) {
        this.nativeObj = VpRowVector_6(vpRowVector.nativeObj, i, i2);
    }

    public VpRowVector(VpRowVector vpRowVector) {
        this.nativeObj = VpRowVector_7(vpRowVector.nativeObj);
    }

    public VpRowVector() {
        this.nativeObj = VpRowVector_8();
    }

    public double frobeniusNorm() {
        return frobeniusNorm_0(this.nativeObj);
    }

    public static double mean(VpRowVector vpRowVector) {
        return mean_0(vpRowVector.nativeObj);
    }

    public static double median(VpRowVector vpRowVector) {
        return median_0(vpRowVector.nativeObj);
    }

    public static double stdev(VpRowVector vpRowVector, boolean z) {
        return stdev_0(vpRowVector.nativeObj, z);
    }

    public static double stdev(VpRowVector vpRowVector) {
        return stdev_1(vpRowVector.nativeObj);
    }

    public double sum() {
        return sum_0(this.nativeObj);
    }

    public double sumSquare() {
        return sumSquare_0(this.nativeObj);
    }

    public void clear() {
        clear_0(this.nativeObj);
    }

    public void deg2rad() {
        deg2rad_0(this.nativeObj);
    }

    public void init(VpRowVector vpRowVector, int i, int i2) {
        init_0(this.nativeObj, vpRowVector.nativeObj, i, i2);
    }

    public void insert(int i, VpRowVector vpRowVector) {
        insert_0(this.nativeObj, i, vpRowVector.nativeObj);
    }

    public void rad2deg() {
        rad2deg_0(this.nativeObj);
    }

    public void reshape(VpMatrix vpMatrix, int i, int i2) {
        reshape_0(this.nativeObj, vpMatrix.nativeObj, i, i2);
    }

    public void resize(int i, boolean z) {
        resize_0(this.nativeObj, i, z);
    }

    public void resize(int i) {
        resize_1(this.nativeObj, i);
    }

    public void resize(int i, int i2, boolean z) {
        resize_2(this.nativeObj, i, i2, z);
    }

    public void stack(double d) {
        stack_0(this.nativeObj, d);
    }

    public static void stack(VpRowVector vpRowVector, VpRowVector vpRowVector2, VpRowVector vpRowVector3) {
        stack_1(vpRowVector.nativeObj, vpRowVector2.nativeObj, vpRowVector3.nativeObj);
    }

    public void stack(VpRowVector vpRowVector) {
        stack_2(this.nativeObj, vpRowVector.nativeObj);
    }

    public void transpose(VpColVector vpColVector) {
        transpose_0(this.nativeObj, vpColVector.nativeObj);
    }

    public VpColVector t() {
        return new VpColVector(t_0(this.nativeObj));
    }

    public VpColVector transpose() {
        return new VpColVector(transpose_1(this.nativeObj));
    }

    public VpMatrix reshape(int i, int i2) {
        return new VpMatrix(reshape_1(this.nativeObj, i, i2));
    }

    public VpRowVector extract(int i, int i2) {
        return new VpRowVector(extract_0(this.nativeObj, i, i2));
    }

    public VpRowVector normalize(VpRowVector vpRowVector) {
        return new VpRowVector(normalize_0(this.nativeObj, vpRowVector.nativeObj));
    }

    public VpRowVector normalize() {
        return new VpRowVector(normalize_1(this.nativeObj));
    }

    public static VpRowVector stack(VpRowVector vpRowVector, VpRowVector vpRowVector2) {
        return new VpRowVector(stack_3(vpRowVector.nativeObj, vpRowVector2.nativeObj));
    }

    public String toString() {
        return toString(this.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
