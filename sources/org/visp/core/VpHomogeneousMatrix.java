package org.visp.core;

import java.util.List;
import org.visp.utils.Converters;

/* loaded from: classes4.dex */
public class VpHomogeneousMatrix {
    public final long nativeObj;

    private static native long VpHomogeneousMatrix_0(double d, double d2, double d3, double d4, double d5, double d6);

    private static native long VpHomogeneousMatrix_1(double[] dArr);

    private static native long VpHomogeneousMatrix_2(float[] fArr);

    private static native long VpHomogeneousMatrix_3(long j);

    private static native long VpHomogeneousMatrix_4();

    private static native void buildFrom_0(long j, double d, double d2, double d3, double d4, double d5, double d6);

    private static native void buildFrom_1(long j, double[] dArr);

    private static native void buildFrom_2(long j, float[] fArr);

    private static native void convert_0(long j, double[] dArr);

    private static native void convert_1(long j, float[] fArr);

    private static native void delete(long j);

    private static native void eye_0(long j);

    private static native long getCol_0(long j, int i);

    private static native void inverse_0(long j, long j2);

    private static native long inverse_1(long j);

    private static native boolean isAnHomogeneousMatrix_0(long j, double d);

    private static native boolean isAnHomogeneousMatrix_1(long j);

    private static native boolean isValid_0(long j);

    private static native long mean_0(long[] jArr);

    private static native void orthogonalizeRotation_0(long j);

    private static native void print_0(long j);

    private static native void resize_0(long j, int i, int i2, boolean z);

    private static native void resize_1(long j, int i, int i2);

    private static native String toString(long j);

    public VpHomogeneousMatrix(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpHomogeneousMatrix __fromPtr__(long j) {
        return new VpHomogeneousMatrix(j);
    }

    public VpHomogeneousMatrix(double d, double d2, double d3, double d4, double d5, double d6) {
        this.nativeObj = VpHomogeneousMatrix_0(d, d2, d3, d4, d5, d6);
    }

    public VpHomogeneousMatrix(double[] dArr) {
        this.nativeObj = VpHomogeneousMatrix_1(dArr);
    }

    public VpHomogeneousMatrix(float[] fArr) {
        this.nativeObj = VpHomogeneousMatrix_2(fArr);
    }

    public VpHomogeneousMatrix(VpHomogeneousMatrix vpHomogeneousMatrix) {
        this.nativeObj = VpHomogeneousMatrix_3(vpHomogeneousMatrix.nativeObj);
    }

    public VpHomogeneousMatrix() {
        this.nativeObj = VpHomogeneousMatrix_4();
    }

    public boolean isAnHomogeneousMatrix(double d) {
        return isAnHomogeneousMatrix_0(this.nativeObj, d);
    }

    public boolean isAnHomogeneousMatrix() {
        return isAnHomogeneousMatrix_1(this.nativeObj);
    }

    public boolean isValid() {
        return isValid_0(this.nativeObj);
    }

    public void buildFrom(double d, double d2, double d3, double d4, double d5, double d6) {
        buildFrom_0(this.nativeObj, d, d2, d3, d4, d5, d6);
    }

    public void buildFrom(double[] dArr) {
        buildFrom_1(this.nativeObj, dArr);
    }

    public void buildFrom(float[] fArr) {
        buildFrom_2(this.nativeObj, fArr);
    }

    public void convert(double[] dArr) {
        convert_0(this.nativeObj, dArr);
    }

    public void convert(float[] fArr) {
        convert_1(this.nativeObj, fArr);
    }

    public void eye() {
        eye_0(this.nativeObj);
    }

    public void inverse(VpHomogeneousMatrix vpHomogeneousMatrix) {
        inverse_0(this.nativeObj, vpHomogeneousMatrix.nativeObj);
    }

    public void orthogonalizeRotation() {
        orthogonalizeRotation_0(this.nativeObj);
    }

    public void print() {
        print_0(this.nativeObj);
    }

    public void resize(int i, int i2, boolean z) {
        resize_0(this.nativeObj, i, i2, z);
    }

    public void resize(int i, int i2) {
        resize_1(this.nativeObj, i, i2);
    }

    public VpColVector getCol(int i) {
        return new VpColVector(getCol_0(this.nativeObj, i));
    }

    public VpHomogeneousMatrix inverse() {
        return new VpHomogeneousMatrix(inverse_1(this.nativeObj));
    }

    public static VpHomogeneousMatrix mean(List<VpHomogeneousMatrix> list) {
        return new VpHomogeneousMatrix(mean_0(Converters.vector_vpHomogeneousMatrix_to_Array(list)));
    }

    public String toString() {
        return toString(this.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
