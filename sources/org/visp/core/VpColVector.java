package org.visp.core;

/* loaded from: classes4.dex */
public class VpColVector {
    public final long nativeObj;

    private static native long VpColVector_0(int i, double d);

    private static native long VpColVector_1(int i);

    private static native long VpColVector_2(double[] dArr);

    private static native long VpColVector_3(float[] fArr);

    private static native long VpColVector_4(long j, int i, int i2);

    private static native long VpColVector_5(long j);

    private static native long VpColVector_6(long j, int i);

    private static native long VpColVector_7(long j);

    private static native long VpColVector_8(long j);

    private static native long VpColVector_9();

    private static native void clear_0(long j);

    private static native long crossProd_0(long j, long j2);

    private static native long cross_0(long j, long j2);

    private static native long deg2rad_0(long j);

    private static native void delete(long j);

    private static native double dotProd_0(long j, long j2);

    private static native long extract_0(long j, int i, int i2);

    private static native double frobeniusNorm_0(long j);

    private static native long hadamard_0(long j, long j2);

    private static native double infinityNorm_0(long j);

    private static native void init_0(long j, long j2, int i, int i2);

    private static native long invSort_0(long j);

    private static native double mean_0(long j);

    private static native double median_0(long j);

    private static native long normalize_0(long j, long j2);

    private static native long normalize_1(long j);

    private static native long rad2deg_0(long j);

    private static native void reshape_0(long j, long j2, int i, int i2);

    private static native long reshape_1(long j, int i, int i2);

    private static native void resize_0(long j, int i, boolean z);

    private static native void resize_1(long j, int i);

    private static native void resize_2(long j, int i, int i2, boolean z);

    private static native long skew_0(long j);

    private static native long sort_0(long j);

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

    public VpColVector(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpColVector __fromPtr__(long j) {
        return new VpColVector(j);
    }

    public VpColVector(int i, double d) {
        this.nativeObj = VpColVector_0(i, d);
    }

    public VpColVector(int i) {
        this.nativeObj = VpColVector_1(i);
    }

    public VpColVector(double[] dArr) {
        this.nativeObj = VpColVector_2(dArr);
    }

    public VpColVector(float[] fArr) {
        this.nativeObj = VpColVector_3(fArr);
    }

    public VpColVector(VpColVector vpColVector, int i, int i2) {
        this.nativeObj = VpColVector_4(vpColVector.nativeObj, i, i2);
    }

    public VpColVector(VpColVector vpColVector) {
        this.nativeObj = VpColVector_5(vpColVector.nativeObj);
    }

    public VpColVector(VpMatrix vpMatrix, int i) {
        this.nativeObj = VpColVector_6(vpMatrix.nativeObj, i);
    }

    public VpColVector(VpMatrix vpMatrix) {
        this.nativeObj = VpColVector_7(vpMatrix.nativeObj);
    }

    public VpColVector(VpRotationVector vpRotationVector) {
        this.nativeObj = VpColVector_8(vpRotationVector.nativeObj);
    }

    public VpColVector() {
        this.nativeObj = VpColVector_9();
    }

    public static double dotProd(VpColVector vpColVector, VpColVector vpColVector2) {
        return dotProd_0(vpColVector.nativeObj, vpColVector2.nativeObj);
    }

    public double frobeniusNorm() {
        return frobeniusNorm_0(this.nativeObj);
    }

    public double infinityNorm() {
        return infinityNorm_0(this.nativeObj);
    }

    public static double mean(VpColVector vpColVector) {
        return mean_0(vpColVector.nativeObj);
    }

    public static double median(VpColVector vpColVector) {
        return median_0(vpColVector.nativeObj);
    }

    public static double stdev(VpColVector vpColVector, boolean z) {
        return stdev_0(vpColVector.nativeObj, z);
    }

    public static double stdev(VpColVector vpColVector) {
        return stdev_1(vpColVector.nativeObj);
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

    public void init(VpColVector vpColVector, int i, int i2) {
        init_0(this.nativeObj, vpColVector.nativeObj, i, i2);
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

    public static void stack(VpColVector vpColVector, VpColVector vpColVector2, VpColVector vpColVector3) {
        stack_1(vpColVector.nativeObj, vpColVector2.nativeObj, vpColVector3.nativeObj);
    }

    public void stack(VpColVector vpColVector) {
        stack_2(this.nativeObj, vpColVector.nativeObj);
    }

    public void transpose(VpRowVector vpRowVector) {
        transpose_0(this.nativeObj, vpRowVector.nativeObj);
    }

    public static VpColVector cross(VpColVector vpColVector, VpColVector vpColVector2) {
        return new VpColVector(cross_0(vpColVector.nativeObj, vpColVector2.nativeObj));
    }

    public static VpColVector crossProd(VpColVector vpColVector, VpColVector vpColVector2) {
        return new VpColVector(crossProd_0(vpColVector.nativeObj, vpColVector2.nativeObj));
    }

    public VpColVector deg2rad() {
        return new VpColVector(deg2rad_0(this.nativeObj));
    }

    public VpColVector extract(int i, int i2) {
        return new VpColVector(extract_0(this.nativeObj, i, i2));
    }

    public VpColVector hadamard(VpColVector vpColVector) {
        return new VpColVector(hadamard_0(this.nativeObj, vpColVector.nativeObj));
    }

    public static VpColVector invSort(VpColVector vpColVector) {
        return new VpColVector(invSort_0(vpColVector.nativeObj));
    }

    public VpColVector normalize(VpColVector vpColVector) {
        return new VpColVector(normalize_0(this.nativeObj, vpColVector.nativeObj));
    }

    public VpColVector normalize() {
        return new VpColVector(normalize_1(this.nativeObj));
    }

    public VpColVector rad2deg() {
        return new VpColVector(rad2deg_0(this.nativeObj));
    }

    public static VpColVector sort(VpColVector vpColVector) {
        return new VpColVector(sort_0(vpColVector.nativeObj));
    }

    public static VpColVector stack(VpColVector vpColVector, VpColVector vpColVector2) {
        return new VpColVector(stack_3(vpColVector.nativeObj, vpColVector2.nativeObj));
    }

    public VpMatrix reshape(int i, int i2) {
        return new VpMatrix(reshape_1(this.nativeObj, i, i2));
    }

    public static VpMatrix skew(VpColVector vpColVector) {
        return new VpMatrix(skew_0(vpColVector.nativeObj));
    }

    public VpRowVector t() {
        return new VpRowVector(t_0(this.nativeObj));
    }

    public VpRowVector transpose() {
        return new VpRowVector(transpose_1(this.nativeObj));
    }

    public String toString() {
        return toString(this.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
