package org.visp.core;

/* loaded from: classes4.dex */
public class VpPoint {
    public final long nativeObj;

    private static native long VpPoint_0(double d, double d2, double d3);

    private static native long VpPoint_1(double[] dArr);

    private static native long VpPoint_2(long j);

    private static native long VpPoint_3();

    private static native void changeFrame_0(long j, long j2, long j3);

    private static native void changeFrame_1(long j, long j2);

    private static native void delete(long j);

    private static native void display_0(long j, long j2, long j3, int i);

    private static native void display_1(long j, long j2, long j3);

    private static native void display_2(long j, long j2, long j3, long j4, int i);

    private static native void display_3(long j, long j2, long j3, long j4);

    private static native void display_4(long j, long j2, long j3, int i);

    private static native void display_5(long j, long j2, long j3);

    private static native void display_6(long j, long j2, long j3, long j4, int i);

    private static native void display_7(long j, long j2, long j3, long j4);

    private static native long getWorldCoordinates_2(long j);

    private static native double get_W_0(long j);

    private static native double get_X_0(long j);

    private static native double get_Y_0(long j);

    private static native double get_Z_0(long j);

    private static native double get_oW_0(long j);

    private static native double get_oX_0(long j);

    private static native double get_oY_0(long j);

    private static native double get_oZ_0(long j);

    private static native double get_w_0(long j);

    private static native double get_x_0(long j);

    private static native double get_y_0(long j);

    private static native void projection_0(long j, long j2, long j3);

    private static native void projection_1(long j);

    private static native void setWorldCoordinates_0(long j, double d, double d2, double d3);

    private static native void setWorldCoordinates_1(long j, double[] dArr);

    private static native void setWorldCoordinates_2(long j, long j2);

    private static native void set_W_0(long j, double d);

    private static native void set_X_0(long j, double d);

    private static native void set_Y_0(long j, double d);

    private static native void set_Z_0(long j, double d);

    private static native void set_oW_0(long j, double d);

    private static native void set_oX_0(long j, double d);

    private static native void set_oY_0(long j, double d);

    private static native void set_oZ_0(long j, double d);

    private static native void set_w_0(long j, double d);

    private static native void set_x_0(long j, double d);

    private static native void set_y_0(long j, double d);

    private static native String toString(long j);

    public VpPoint(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpPoint __fromPtr__(long j) {
        return new VpPoint(j);
    }

    public VpPoint(double d, double d2, double d3) {
        this.nativeObj = VpPoint_0(d, d2, d3);
    }

    public VpPoint(double[] dArr) {
        this.nativeObj = VpPoint_1(dArr);
    }

    public VpPoint(VpColVector vpColVector) {
        this.nativeObj = VpPoint_2(vpColVector.nativeObj);
    }

    public VpPoint() {
        this.nativeObj = VpPoint_3();
    }

    public double get_W() {
        return get_W_0(this.nativeObj);
    }

    public double get_X() {
        return get_X_0(this.nativeObj);
    }

    public double get_Y() {
        return get_Y_0(this.nativeObj);
    }

    public double get_Z() {
        return get_Z_0(this.nativeObj);
    }

    public double get_oW() {
        return get_oW_0(this.nativeObj);
    }

    public double get_oX() {
        return get_oX_0(this.nativeObj);
    }

    public double get_oY() {
        return get_oY_0(this.nativeObj);
    }

    public double get_oZ() {
        return get_oZ_0(this.nativeObj);
    }

    public double get_w() {
        return get_w_0(this.nativeObj);
    }

    public double get_x() {
        return get_x_0(this.nativeObj);
    }

    public double get_y() {
        return get_y_0(this.nativeObj);
    }

    public void changeFrame(VpHomogeneousMatrix vpHomogeneousMatrix, VpColVector vpColVector) {
        changeFrame_0(this.nativeObj, vpHomogeneousMatrix.nativeObj, vpColVector.nativeObj);
    }

    public void changeFrame(VpHomogeneousMatrix vpHomogeneousMatrix) {
        changeFrame_1(this.nativeObj, vpHomogeneousMatrix.nativeObj);
    }

    public void display(VpImageUChar vpImageUChar, VpCameraParameters vpCameraParameters, int i) {
        display_0(this.nativeObj, vpImageUChar.nativeObj, vpCameraParameters.nativeObj, i);
    }

    public void display(VpImageUChar vpImageUChar, VpCameraParameters vpCameraParameters) {
        display_1(this.nativeObj, vpImageUChar.nativeObj, vpCameraParameters.nativeObj);
    }

    public void display(VpImageUChar vpImageUChar, VpHomogeneousMatrix vpHomogeneousMatrix, VpCameraParameters vpCameraParameters, int i) {
        display_2(this.nativeObj, vpImageUChar.nativeObj, vpHomogeneousMatrix.nativeObj, vpCameraParameters.nativeObj, i);
    }

    public void display(VpImageUChar vpImageUChar, VpHomogeneousMatrix vpHomogeneousMatrix, VpCameraParameters vpCameraParameters) {
        display_3(this.nativeObj, vpImageUChar.nativeObj, vpHomogeneousMatrix.nativeObj, vpCameraParameters.nativeObj);
    }

    public void display(VpImageRGBa vpImageRGBa, VpCameraParameters vpCameraParameters, int i) {
        display_4(this.nativeObj, vpImageRGBa.nativeObj, vpCameraParameters.nativeObj, i);
    }

    public void display(VpImageRGBa vpImageRGBa, VpCameraParameters vpCameraParameters) {
        display_5(this.nativeObj, vpImageRGBa.nativeObj, vpCameraParameters.nativeObj);
    }

    public void display(VpImageRGBa vpImageRGBa, VpHomogeneousMatrix vpHomogeneousMatrix, VpCameraParameters vpCameraParameters, int i) {
        display_6(this.nativeObj, vpImageRGBa.nativeObj, vpHomogeneousMatrix.nativeObj, vpCameraParameters.nativeObj, i);
    }

    public void display(VpImageRGBa vpImageRGBa, VpHomogeneousMatrix vpHomogeneousMatrix, VpCameraParameters vpCameraParameters) {
        display_7(this.nativeObj, vpImageRGBa.nativeObj, vpHomogeneousMatrix.nativeObj, vpCameraParameters.nativeObj);
    }

    public void projection(VpColVector vpColVector, VpColVector vpColVector2) {
        projection_0(this.nativeObj, vpColVector.nativeObj, vpColVector2.nativeObj);
    }

    public void projection() {
        projection_1(this.nativeObj);
    }

    public void setWorldCoordinates(double d, double d2, double d3) {
        setWorldCoordinates_0(this.nativeObj, d, d2, d3);
    }

    public void setWorldCoordinates(double[] dArr) {
        setWorldCoordinates_1(this.nativeObj, dArr);
    }

    public void setWorldCoordinates(VpColVector vpColVector) {
        setWorldCoordinates_2(this.nativeObj, vpColVector.nativeObj);
    }

    public void set_W(double d) {
        set_W_0(this.nativeObj, d);
    }

    public void set_X(double d) {
        set_X_0(this.nativeObj, d);
    }

    public void set_Y(double d) {
        set_Y_0(this.nativeObj, d);
    }

    public void set_Z(double d) {
        set_Z_0(this.nativeObj, d);
    }

    public void set_oW(double d) {
        set_oW_0(this.nativeObj, d);
    }

    public void set_oX(double d) {
        set_oX_0(this.nativeObj, d);
    }

    public void set_oY(double d) {
        set_oY_0(this.nativeObj, d);
    }

    public void set_oZ(double d) {
        set_oZ_0(this.nativeObj, d);
    }

    public void set_w(double d) {
        set_w_0(this.nativeObj, d);
    }

    public void set_x(double d) {
        set_x_0(this.nativeObj, d);
    }

    public void set_y(double d) {
        set_y_0(this.nativeObj, d);
    }

    public VpColVector getWorldCoordinates() {
        return new VpColVector(getWorldCoordinates_2(this.nativeObj));
    }

    public String toString() {
        return toString(this.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
