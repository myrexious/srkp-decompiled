package org.opencv.video;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.utils.Converters;

/* loaded from: classes4.dex */
public class Video {
    private static final int CV_LKFLOW_GET_MIN_EIGENVALS = 8;
    private static final int CV_LKFLOW_INITIAL_GUESSES = 4;
    public static final int MOTION_AFFINE = 2;
    public static final int MOTION_EUCLIDEAN = 1;
    public static final int MOTION_HOMOGRAPHY = 3;
    public static final int MOTION_TRANSLATION = 0;
    public static final int OPTFLOW_FARNEBACK_GAUSSIAN = 256;
    public static final int OPTFLOW_LK_GET_MIN_EIGENVALS = 8;
    public static final int OPTFLOW_USE_INITIAL_FLOW = 4;

    private static native double[] CamShift_0(long j, int i, int i2, int i3, int i4, double[] dArr, int i5, int i6, double d);

    private static native int buildOpticalFlowPyramid_0(long j, long j2, double d, double d2, int i, boolean z, int i2, int i3, boolean z2);

    private static native int buildOpticalFlowPyramid_1(long j, long j2, double d, double d2, int i);

    private static native void calcOpticalFlowFarneback_0(long j, long j2, long j3, double d, int i, int i2, int i3, int i4, double d2, int i5);

    private static native void calcOpticalFlowPyrLK_0(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, int i, int i2, int i3, double d3, int i4, double d4);

    private static native void calcOpticalFlowPyrLK_1(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, int i);

    private static native void calcOpticalFlowPyrLK_2(long j, long j2, long j3, long j4, long j5, long j6);

    private static native long createBackgroundSubtractorKNN_0(int i, double d, boolean z);

    private static native long createBackgroundSubtractorKNN_1();

    private static native long createBackgroundSubtractorMOG2_0(int i, double d, boolean z);

    private static native long createBackgroundSubtractorMOG2_1();

    private static native long createOptFlow_DualTVL1_0();

    private static native long estimateRigidTransform_0(long j, long j2, boolean z);

    private static native double findTransformECC_0(long j, long j2, long j3, int i, int i2, int i3, double d, long j4);

    private static native double findTransformECC_1(long j, long j2, long j3, int i);

    private static native double findTransformECC_2(long j, long j2, long j3);

    private static native int meanShift_0(long j, int i, int i2, int i3, int i4, double[] dArr, int i5, int i6, double d);

    public static Mat estimateRigidTransform(Mat mat, Mat mat2, boolean z) {
        return new Mat(estimateRigidTransform_0(mat.nativeObj, mat2.nativeObj, z));
    }

    public static BackgroundSubtractorKNN createBackgroundSubtractorKNN(int i, double d, boolean z) {
        return BackgroundSubtractorKNN.__fromPtr__(createBackgroundSubtractorKNN_0(i, d, z));
    }

    public static BackgroundSubtractorKNN createBackgroundSubtractorKNN() {
        return BackgroundSubtractorKNN.__fromPtr__(createBackgroundSubtractorKNN_1());
    }

    public static BackgroundSubtractorMOG2 createBackgroundSubtractorMOG2(int i, double d, boolean z) {
        return BackgroundSubtractorMOG2.__fromPtr__(createBackgroundSubtractorMOG2_0(i, d, z));
    }

    public static BackgroundSubtractorMOG2 createBackgroundSubtractorMOG2() {
        return BackgroundSubtractorMOG2.__fromPtr__(createBackgroundSubtractorMOG2_1());
    }

    public static DualTVL1OpticalFlow createOptFlow_DualTVL1() {
        return DualTVL1OpticalFlow.__fromPtr__(createOptFlow_DualTVL1_0());
    }

    public static RotatedRect CamShift(Mat mat, Rect rect, TermCriteria termCriteria) {
        double[] dArr = new double[4];
        RotatedRect rotatedRect = new RotatedRect(CamShift_0(mat.nativeObj, rect.x, rect.y, rect.width, rect.height, dArr, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon));
        if (rect != null) {
            rect.x = (int) dArr[0];
            rect.y = (int) dArr[1];
            rect.width = (int) dArr[2];
            rect.height = (int) dArr[3];
        }
        return rotatedRect;
    }

    public static double findTransformECC(Mat mat, Mat mat2, Mat mat3, int i, TermCriteria termCriteria, Mat mat4) {
        return findTransformECC_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon, mat4.nativeObj);
    }

    public static double findTransformECC(Mat mat, Mat mat2, Mat mat3, int i) {
        return findTransformECC_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i);
    }

    public static double findTransformECC(Mat mat, Mat mat2, Mat mat3) {
        return findTransformECC_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static int buildOpticalFlowPyramid(Mat mat, List<Mat> list, Size size, int i, boolean z, int i2, int i3, boolean z2) {
        Mat mat2 = new Mat();
        int buildOpticalFlowPyramid_0 = buildOpticalFlowPyramid_0(mat.nativeObj, mat2.nativeObj, size.width, size.height, i, z, i2, i3, z2);
        Converters.Mat_to_vector_Mat(mat2, list);
        mat2.release();
        return buildOpticalFlowPyramid_0;
    }

    public static int buildOpticalFlowPyramid(Mat mat, List<Mat> list, Size size, int i) {
        Mat mat2 = new Mat();
        int buildOpticalFlowPyramid_1 = buildOpticalFlowPyramid_1(mat.nativeObj, mat2.nativeObj, size.width, size.height, i);
        Converters.Mat_to_vector_Mat(mat2, list);
        mat2.release();
        return buildOpticalFlowPyramid_1;
    }

    public static int meanShift(Mat mat, Rect rect, TermCriteria termCriteria) {
        double[] dArr = new double[4];
        int meanShift_0 = meanShift_0(mat.nativeObj, rect.x, rect.y, rect.width, rect.height, dArr, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
        if (rect != null) {
            rect.x = (int) dArr[0];
            rect.y = (int) dArr[1];
            rect.width = (int) dArr[2];
            rect.height = (int) dArr[3];
        }
        return meanShift_0;
    }

    public static void calcOpticalFlowFarneback(Mat mat, Mat mat2, Mat mat3, double d, int i, int i2, int i3, int i4, double d2, int i5) {
        calcOpticalFlowFarneback_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, i, i2, i3, i4, d2, i5);
    }

    public static void calcOpticalFlowPyrLK(Mat mat, Mat mat2, MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, MatOfByte matOfByte, MatOfFloat matOfFloat, Size size, int i, TermCriteria termCriteria, int i2, double d) {
        calcOpticalFlowPyrLK_0(mat.nativeObj, mat2.nativeObj, matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, matOfByte.nativeObj, matOfFloat.nativeObj, size.width, size.height, i, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon, i2, d);
    }

    public static void calcOpticalFlowPyrLK(Mat mat, Mat mat2, MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, MatOfByte matOfByte, MatOfFloat matOfFloat, Size size, int i) {
        calcOpticalFlowPyrLK_1(mat.nativeObj, mat2.nativeObj, matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, matOfByte.nativeObj, matOfFloat.nativeObj, size.width, size.height, i);
    }

    public static void calcOpticalFlowPyrLK(Mat mat, Mat mat2, MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, MatOfByte matOfByte, MatOfFloat matOfFloat) {
        calcOpticalFlowPyrLK_2(mat.nativeObj, mat2.nativeObj, matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, matOfByte.nativeObj, matOfFloat.nativeObj);
    }
}
