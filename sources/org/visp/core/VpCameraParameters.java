package org.visp.core;

import java.util.List;
import org.visp.utils.Converters;

/* loaded from: classes4.dex */
public class VpCameraParameters {
    public final long nativeObj;

    private static native long VpCameraParameters_0(double d, double d2, double d3, double d4, double d5, double d6);

    private static native long VpCameraParameters_1(double d, double d2, double d3, double d4, double[] dArr);

    private static native long VpCameraParameters_2(double d, double d2, double d3, double d4);

    private static native long VpCameraParameters_3(long j);

    private static native long VpCameraParameters_4();

    private static native void computeFov_0(long j, int i, int i2);

    private static native void delete(long j);

    private static native long[] getFovNormals_0(long j);

    private static native double getHorizontalFovAngle_0(long j);

    private static native double[] getKannalaBrandtDistortionCoefficients_2();

    private static native double getVerticalFovAngle_0(long j);

    private static native long get_K_0(long j);

    private static native long get_K_inverse_0(long j);

    private static native double get_kdu_0(long j);

    private static native double get_kud_0(long j);

    private static native double get_px_0(long j);

    private static native double get_px_inverse_0(long j);

    private static native double get_py_0(long j);

    private static native double get_py_inverse_0(long j);

    private static native double get_u0_0(long j);

    private static native double get_v0_0(long j);

    private static native void initFromCalibrationMatrix_0(long j, long j2);

    private static native void initFromFov_0(long j, int i, int i2, double d, double d2);

    private static native void initPersProjWithDistortion_0(long j, double d, double d2, double d3, double d4, double d5, double d6);

    private static native void initPersProjWithoutDistortion_0(long j, double d, double d2, double d3, double d4);

    private static native void initProjWithKannalaBrandtDistortion_0(long j, double d, double d2, double d3, double d4, double[] dArr);

    private static native void init_0(long j, long j2);

    private static native void init_1(long j);

    private static native boolean isFovComputed_0(long j);

    private static native void printParameters_0(long j);

    private static native String toString(long j);

    public VpCameraParameters(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpCameraParameters __fromPtr__(long j) {
        return new VpCameraParameters(j);
    }

    public VpCameraParameters(double d, double d2, double d3, double d4, double d5, double d6) {
        this.nativeObj = VpCameraParameters_0(d, d2, d3, d4, d5, d6);
    }

    public VpCameraParameters(double d, double d2, double d3, double d4, double[] dArr) {
        this.nativeObj = VpCameraParameters_1(d, d2, d3, d4, dArr);
    }

    public VpCameraParameters(double d, double d2, double d3, double d4) {
        this.nativeObj = VpCameraParameters_2(d, d2, d3, d4);
    }

    public VpCameraParameters(VpCameraParameters vpCameraParameters) {
        this.nativeObj = VpCameraParameters_3(vpCameraParameters.nativeObj);
    }

    public VpCameraParameters() {
        this.nativeObj = VpCameraParameters_4();
    }

    public boolean isFovComputed() {
        return isFovComputed_0(this.nativeObj);
    }

    public double getHorizontalFovAngle() {
        return getHorizontalFovAngle_0(this.nativeObj);
    }

    public double getVerticalFovAngle() {
        return getVerticalFovAngle_0(this.nativeObj);
    }

    public double get_kdu() {
        return get_kdu_0(this.nativeObj);
    }

    public double get_kud() {
        return get_kud_0(this.nativeObj);
    }

    public double get_px() {
        return get_px_0(this.nativeObj);
    }

    public double get_px_inverse() {
        return get_px_inverse_0(this.nativeObj);
    }

    public double get_py() {
        return get_py_0(this.nativeObj);
    }

    public double get_py_inverse() {
        return get_py_inverse_0(this.nativeObj);
    }

    public double get_u0() {
        return get_u0_0(this.nativeObj);
    }

    public double get_v0() {
        return get_v0_0(this.nativeObj);
    }

    public List<VpColVector> getFovNormals() {
        return Converters.Array_to_vector_vpColVector(getFovNormals_0(this.nativeObj));
    }

    public void computeFov(int i, int i2) {
        computeFov_0(this.nativeObj, i, i2);
    }

    public void init(VpCameraParameters vpCameraParameters) {
        init_0(this.nativeObj, vpCameraParameters.nativeObj);
    }

    public void init() {
        init_1(this.nativeObj);
    }

    public void initFromCalibrationMatrix(VpMatrix vpMatrix) {
        initFromCalibrationMatrix_0(this.nativeObj, vpMatrix.nativeObj);
    }

    public void initFromFov(int i, int i2, double d, double d2) {
        initFromFov_0(this.nativeObj, i, i2, d, d2);
    }

    public void initPersProjWithDistortion(double d, double d2, double d3, double d4, double d5, double d6) {
        initPersProjWithDistortion_0(this.nativeObj, d, d2, d3, d4, d5, d6);
    }

    public void initPersProjWithoutDistortion(double d, double d2, double d3, double d4) {
        initPersProjWithoutDistortion_0(this.nativeObj, d, d2, d3, d4);
    }

    public void initProjWithKannalaBrandtDistortion(double d, double d2, double d3, double d4, double[] dArr) {
        initProjWithKannalaBrandtDistortion_0(this.nativeObj, d, d2, d3, d4, dArr);
    }

    public void printParameters() {
        printParameters_0(this.nativeObj);
    }

    public VpMatrix get_K() {
        return new VpMatrix(get_K_0(this.nativeObj));
    }

    public VpMatrix get_K_inverse() {
        return new VpMatrix(get_K_inverse_0(this.nativeObj));
    }

    public double[] getKannalaBrandtDistortionCoefficients() {
        return getKannalaBrandtDistortionCoefficients_2();
    }

    public String toString() {
        return toString(this.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
