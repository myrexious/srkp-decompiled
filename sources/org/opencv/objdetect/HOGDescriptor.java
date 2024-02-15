package org.opencv.objdetect;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;

/* loaded from: classes4.dex */
public class HOGDescriptor {
    public static final int DEFAULT_NLEVELS = 64;
    public static final int L2Hys = 0;
    protected final long nativeObj;

    private static native long HOGDescriptor_0(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, int i, int i2, double d9, int i3, double d10, boolean z, int i4, boolean z2);

    private static native long HOGDescriptor_1(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, int i);

    private static native long HOGDescriptor_2(String str);

    private static native long HOGDescriptor_3();

    private static native boolean checkDetectorSize_0(long j);

    private static native void computeGradient_0(long j, long j2, long j3, long j4, double d, double d2, double d3, double d4);

    private static native void computeGradient_1(long j, long j2, long j3, long j4);

    private static native void compute_0(long j, long j2, long j3, double d, double d2, double d3, double d4, long j4);

    private static native void compute_1(long j, long j2, long j3);

    private static native void delete(long j);

    private static native void detectMultiScale_0(long j, long j2, long j3, long j4, double d, double d2, double d3, double d4, double d5, double d6, double d7, boolean z);

    private static native void detectMultiScale_1(long j, long j2, long j3, long j4);

    private static native void detect_0(long j, long j2, long j3, long j4, double d, double d2, double d3, double d4, double d5, long j5);

    private static native void detect_1(long j, long j2, long j3, long j4);

    private static native long getDaimlerPeopleDetector_0();

    private static native long getDefaultPeopleDetector_0();

    private static native long getDescriptorSize_0(long j);

    private static native double getWinSigma_0(long j);

    private static native double get_L2HysThreshold_0(long j);

    private static native double[] get_blockSize_0(long j);

    private static native double[] get_blockStride_0(long j);

    private static native double[] get_cellSize_0(long j);

    private static native int get_derivAperture_0(long j);

    private static native boolean get_gammaCorrection_0(long j);

    private static native int get_histogramNormType_0(long j);

    private static native int get_nbins_0(long j);

    private static native int get_nlevels_0(long j);

    private static native boolean get_signedGradient_0(long j);

    private static native long get_svmDetector_0(long j);

    private static native double get_winSigma_0(long j);

    private static native double[] get_winSize_0(long j);

    private static native boolean load_0(long j, String str, String str2);

    private static native boolean load_1(long j, String str);

    private static native void save_0(long j, String str, String str2);

    private static native void save_1(long j, String str);

    private static native void setSVMDetector_0(long j, long j2);

    protected HOGDescriptor(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static HOGDescriptor __fromPtr__(long j) {
        return new HOGDescriptor(j);
    }

    public HOGDescriptor(Size size, Size size2, Size size3, Size size4, int i, int i2, double d, int i3, double d2, boolean z, int i4, boolean z2) {
        this.nativeObj = HOGDescriptor_0(size.width, size.height, size2.width, size2.height, size3.width, size3.height, size4.width, size4.height, i, i2, d, i3, d2, z, i4, z2);
    }

    public HOGDescriptor(Size size, Size size2, Size size3, Size size4, int i) {
        this.nativeObj = HOGDescriptor_1(size.width, size.height, size2.width, size2.height, size3.width, size3.height, size4.width, size4.height, i);
    }

    public HOGDescriptor(String str) {
        this.nativeObj = HOGDescriptor_2(str);
    }

    public HOGDescriptor() {
        this.nativeObj = HOGDescriptor_3();
    }

    public boolean checkDetectorSize() {
        return checkDetectorSize_0(this.nativeObj);
    }

    public boolean load(String str, String str2) {
        return load_0(this.nativeObj, str, str2);
    }

    public boolean load(String str) {
        return load_1(this.nativeObj, str);
    }

    public double getWinSigma() {
        return getWinSigma_0(this.nativeObj);
    }

    public long getDescriptorSize() {
        return getDescriptorSize_0(this.nativeObj);
    }

    public static MatOfFloat getDaimlerPeopleDetector() {
        return MatOfFloat.fromNativeAddr(getDaimlerPeopleDetector_0());
    }

    public static MatOfFloat getDefaultPeopleDetector() {
        return MatOfFloat.fromNativeAddr(getDefaultPeopleDetector_0());
    }

    public void compute(Mat mat, MatOfFloat matOfFloat, Size size, Size size2, MatOfPoint matOfPoint) {
        compute_0(this.nativeObj, mat.nativeObj, matOfFloat.nativeObj, size.width, size.height, size2.width, size2.height, matOfPoint.nativeObj);
    }

    public void compute(Mat mat, MatOfFloat matOfFloat) {
        compute_1(this.nativeObj, mat.nativeObj, matOfFloat.nativeObj);
    }

    public void computeGradient(Mat mat, Mat mat2, Mat mat3, Size size, Size size2) {
        computeGradient_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, size.width, size.height, size2.width, size2.height);
    }

    public void computeGradient(Mat mat, Mat mat2, Mat mat3) {
        computeGradient_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public void detect(Mat mat, MatOfPoint matOfPoint, MatOfDouble matOfDouble, double d, Size size, Size size2, MatOfPoint matOfPoint2) {
        detect_0(this.nativeObj, mat.nativeObj, matOfPoint.nativeObj, matOfDouble.nativeObj, d, size.width, size.height, size2.width, size2.height, matOfPoint2.nativeObj);
    }

    public void detect(Mat mat, MatOfPoint matOfPoint, MatOfDouble matOfDouble) {
        detect_1(this.nativeObj, mat.nativeObj, matOfPoint.nativeObj, matOfDouble.nativeObj);
    }

    public void detectMultiScale(Mat mat, MatOfRect matOfRect, MatOfDouble matOfDouble, double d, Size size, Size size2, double d2, double d3, boolean z) {
        detectMultiScale_0(this.nativeObj, mat.nativeObj, matOfRect.nativeObj, matOfDouble.nativeObj, d, size.width, size.height, size2.width, size2.height, d2, d3, z);
    }

    public void detectMultiScale(Mat mat, MatOfRect matOfRect, MatOfDouble matOfDouble) {
        detectMultiScale_1(this.nativeObj, mat.nativeObj, matOfRect.nativeObj, matOfDouble.nativeObj);
    }

    public void save(String str, String str2) {
        save_0(this.nativeObj, str, str2);
    }

    public void save(String str) {
        save_1(this.nativeObj, str);
    }

    public void setSVMDetector(Mat mat) {
        setSVMDetector_0(this.nativeObj, mat.nativeObj);
    }

    public Size get_winSize() {
        return new Size(get_winSize_0(this.nativeObj));
    }

    public Size get_blockSize() {
        return new Size(get_blockSize_0(this.nativeObj));
    }

    public Size get_blockStride() {
        return new Size(get_blockStride_0(this.nativeObj));
    }

    public Size get_cellSize() {
        return new Size(get_cellSize_0(this.nativeObj));
    }

    public int get_nbins() {
        return get_nbins_0(this.nativeObj);
    }

    public int get_derivAperture() {
        return get_derivAperture_0(this.nativeObj);
    }

    public double get_winSigma() {
        return get_winSigma_0(this.nativeObj);
    }

    public int get_histogramNormType() {
        return get_histogramNormType_0(this.nativeObj);
    }

    public double get_L2HysThreshold() {
        return get_L2HysThreshold_0(this.nativeObj);
    }

    public boolean get_gammaCorrection() {
        return get_gammaCorrection_0(this.nativeObj);
    }

    public MatOfFloat get_svmDetector() {
        return MatOfFloat.fromNativeAddr(get_svmDetector_0(this.nativeObj));
    }

    public int get_nlevels() {
        return get_nlevels_0(this.nativeObj);
    }

    public boolean get_signedGradient() {
        return get_signedGradient_0(this.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
