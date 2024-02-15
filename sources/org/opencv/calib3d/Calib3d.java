package org.opencv.calib3d;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfPoint3f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.utils.Converters;

/* loaded from: classes4.dex */
public class Calib3d {
    public static final int CALIB_CB_ADAPTIVE_THRESH = 1;
    public static final int CALIB_CB_ASYMMETRIC_GRID = 2;
    public static final int CALIB_CB_CLUSTERING = 4;
    public static final int CALIB_CB_FAST_CHECK = 8;
    public static final int CALIB_CB_FILTER_QUADS = 4;
    public static final int CALIB_CB_NORMALIZE_IMAGE = 2;
    public static final int CALIB_CB_SYMMETRIC_GRID = 1;
    public static final int CALIB_CHECK_COND = 4;
    public static final int CALIB_FIX_ASPECT_RATIO = 2;
    public static final int CALIB_FIX_FOCAL_LENGTH = 16;
    public static final int CALIB_FIX_INTRINSIC = 256;
    public static final int CALIB_FIX_K1 = 16;
    public static final int CALIB_FIX_K2 = 32;
    public static final int CALIB_FIX_K3 = 64;
    public static final int CALIB_FIX_K4 = 128;
    public static final int CALIB_FIX_K5 = 4096;
    public static final int CALIB_FIX_K6 = 8192;
    public static final int CALIB_FIX_PRINCIPAL_POINT = 4;
    public static final int CALIB_FIX_S1_S2_S3_S4 = 65536;
    public static final int CALIB_FIX_SKEW = 8;
    public static final int CALIB_FIX_TANGENT_DIST = 2097152;
    public static final int CALIB_FIX_TAUX_TAUY = 524288;
    public static final int CALIB_RATIONAL_MODEL = 16384;
    public static final int CALIB_RECOMPUTE_EXTRINSIC = 2;
    public static final int CALIB_SAME_FOCAL_LENGTH = 512;
    public static final int CALIB_THIN_PRISM_MODEL = 32768;
    public static final int CALIB_TILTED_MODEL = 262144;
    public static final int CALIB_USE_EXTRINSIC_GUESS = 4194304;
    public static final int CALIB_USE_INTRINSIC_GUESS = 1;
    public static final int CALIB_USE_LU = 131072;
    public static final int CALIB_USE_QR = 1048576;
    public static final int CALIB_ZERO_DISPARITY = 1024;
    public static final int CALIB_ZERO_TANGENT_DIST = 8;
    public static final int CV_DLS = 3;
    public static final int CV_EPNP = 1;
    public static final int CV_ITERATIVE = 0;
    public static final int CV_P3P = 2;
    public static final int FM_7POINT = 1;
    public static final int FM_8POINT = 2;
    public static final int FM_LMEDS = 4;
    public static final int FM_RANSAC = 8;
    public static final int LMEDS = 4;
    public static final int RANSAC = 8;
    public static final int RHO = 16;
    public static final int SOLVEPNP_AP3P = 5;
    public static final int SOLVEPNP_DLS = 3;
    public static final int SOLVEPNP_EPNP = 1;
    public static final int SOLVEPNP_ITERATIVE = 0;
    public static final int SOLVEPNP_MAX_COUNT = 6;
    public static final int SOLVEPNP_P3P = 2;
    public static final int SOLVEPNP_UPNP = 4;

    private static native double[] RQDecomp3x3_0(long j, long j2, long j3, long j4, long j5, long j6);

    private static native double[] RQDecomp3x3_1(long j, long j2, long j3);

    private static native void Rodrigues_0(long j, long j2, long j3);

    private static native void Rodrigues_1(long j, long j2);

    private static native double calibrateCameraExtended_0(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, int i, int i2, int i3, double d3);

    private static native double calibrateCameraExtended_1(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, int i);

    private static native double calibrateCameraExtended_2(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    private static native double calibrateCamera_0(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, int i, int i2, int i3, double d3);

    private static native double calibrateCamera_1(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, int i);

    private static native double calibrateCamera_2(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6);

    private static native double calibrate_0(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, int i, int i2, int i3, double d3);

    private static native double calibrate_1(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6, int i);

    private static native double calibrate_2(long j, long j2, double d, double d2, long j3, long j4, long j5, long j6);

    private static native void calibrationMatrixValues_0(long j, double d, double d2, double d3, double d4, double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5);

    private static native void composeRT_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14);

    private static native void composeRT_1(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void computeCorrespondEpilines_0(long j, int i, long j2, long j3);

    private static native void convertPointsFromHomogeneous_0(long j, long j2);

    private static native void convertPointsToHomogeneous_0(long j, long j2);

    private static native void correctMatches_0(long j, long j2, long j3, long j4, long j5);

    private static native void decomposeEssentialMat_0(long j, long j2, long j3, long j4);

    private static native int decomposeHomographyMat_0(long j, long j2, long j3, long j4, long j5);

    private static native void decomposeProjectionMatrix_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    private static native void decomposeProjectionMatrix_1(long j, long j2, long j3, long j4);

    private static native void distortPoints_0(long j, long j2, long j3, long j4, double d);

    private static native void distortPoints_1(long j, long j2, long j3, long j4);

    private static native void drawChessboardCorners_0(long j, double d, double d2, long j2, boolean z);

    private static native long estimateAffine2D_0(long j, long j2, long j3, int i, double d, long j4, double d2, long j5);

    private static native long estimateAffine2D_1(long j, long j2);

    private static native int estimateAffine3D_0(long j, long j2, long j3, long j4, double d, double d2);

    private static native int estimateAffine3D_1(long j, long j2, long j3, long j4);

    private static native long estimateAffinePartial2D_0(long j, long j2, long j3, int i, double d, long j4, double d2, long j5);

    private static native long estimateAffinePartial2D_1(long j, long j2);

    private static native void estimateNewCameraMatrixForUndistortRectify_0(long j, long j2, double d, double d2, long j3, long j4, double d3, double d4, double d5, double d6);

    private static native void estimateNewCameraMatrixForUndistortRectify_1(long j, long j2, double d, double d2, long j3, long j4);

    private static native void filterSpeckles_0(long j, double d, int i, double d2, long j2);

    private static native void filterSpeckles_1(long j, double d, int i, double d2);

    private static native boolean findChessboardCorners_0(long j, double d, double d2, long j2, int i);

    private static native boolean findChessboardCorners_1(long j, double d, double d2, long j2);

    private static native boolean findCirclesGrid_0(long j, double d, double d2, long j2, int i);

    private static native boolean findCirclesGrid_1(long j, double d, double d2, long j2);

    private static native long findEssentialMat_0(long j, long j2, long j3, int i, double d, double d2, long j4);

    private static native long findEssentialMat_1(long j, long j2, long j3, int i, double d, double d2);

    private static native long findEssentialMat_2(long j, long j2, long j3);

    private static native long findEssentialMat_3(long j, long j2, double d, double d2, double d3, int i, double d4, double d5, long j3);

    private static native long findEssentialMat_4(long j, long j2, double d, double d2, double d3, int i, double d4, double d5);

    private static native long findEssentialMat_5(long j, long j2);

    private static native long findFundamentalMat_0(long j, long j2, int i, double d, double d2, long j3);

    private static native long findFundamentalMat_1(long j, long j2, int i, double d, double d2);

    private static native long findFundamentalMat_2(long j, long j2);

    private static native long findHomography_0(long j, long j2, int i, double d, long j3, int i2, double d2);

    private static native long findHomography_1(long j, long j2, int i, double d);

    private static native long findHomography_2(long j, long j2);

    private static native long getOptimalNewCameraMatrix_0(long j, long j2, double d, double d2, double d3, double d4, double d5, double[] dArr, boolean z);

    private static native long getOptimalNewCameraMatrix_1(long j, long j2, double d, double d2, double d3);

    private static native double[] getValidDisparityROI_0(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11);

    private static native long initCameraMatrix2D_0(long j, long j2, double d, double d2, double d3);

    private static native long initCameraMatrix2D_1(long j, long j2, double d, double d2);

    private static native void initUndistortRectifyMap_0(long j, long j2, long j3, long j4, double d, double d2, int i, long j5, long j6);

    private static native void matMulDeriv_0(long j, long j2, long j3, long j4);

    private static native void projectPoints_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d);

    private static native void projectPoints_1(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void projectPoints_2(long j, long j2, long j3, long j4, long j5, long j6, double d, long j7);

    private static native void projectPoints_3(long j, long j2, long j3, long j4, long j5, long j6);

    private static native int recoverPose_0(long j, long j2, long j3, long j4, long j5, double d, double d2, double d3, long j6);

    private static native int recoverPose_1(long j, long j2, long j3, long j4, long j5, double d, double d2, double d3);

    private static native int recoverPose_2(long j, long j2, long j3, long j4, long j5);

    private static native int recoverPose_3(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    private static native int recoverPose_4(long j, long j2, long j3, long j4, long j5, long j6);

    private static native int recoverPose_5(long j, long j2, long j3, long j4, long j5, long j6, double d, long j7, long j8);

    private static native int recoverPose_6(long j, long j2, long j3, long j4, long j5, long j6, double d);

    private static native float rectify3Collinear_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, double d, double d2, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, double d3, double d4, double d5, double[] dArr, double[] dArr2, int i);

    private static native void reprojectImageTo3D_0(long j, long j2, long j3, boolean z, int i);

    private static native void reprojectImageTo3D_1(long j, long j2, long j3, boolean z);

    private static native void reprojectImageTo3D_2(long j, long j2, long j3);

    private static native double sampsonDistance_0(long j, long j2, long j3);

    private static native int solveP3P_0(long j, long j2, long j3, long j4, long j5, long j6, int i);

    private static native boolean solvePnPRansac_0(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i, float f, double d, long j7, int i2);

    private static native boolean solvePnPRansac_1(long j, long j2, long j3, long j4, long j5, long j6);

    private static native boolean solvePnP_0(long j, long j2, long j3, long j4, long j5, long j6, boolean z, int i);

    private static native boolean solvePnP_1(long j, long j2, long j3, long j4, long j5, long j6);

    private static native double stereoCalibrateExtended_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11, long j12, int i, int i2, int i3, double d3);

    private static native double stereoCalibrateExtended_1(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11, long j12, int i);

    private static native double stereoCalibrateExtended_2(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11, long j12);

    private static native double stereoCalibrate_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11, int i, int i2, int i3, double d3);

    private static native double stereoCalibrate_1(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11, int i);

    private static native double stereoCalibrate_2(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, long j10, long j11);

    private static native double stereoCalibrate_3(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, int i, int i2, int i3, double d3);

    private static native double stereoCalibrate_4(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9, int i);

    private static native double stereoCalibrate_5(long j, long j2, long j3, long j4, long j5, long j6, long j7, double d, double d2, long j8, long j9);

    private static native boolean stereoRectifyUncalibrated_0(long j, long j2, long j3, double d, double d2, long j4, long j5, double d3);

    private static native boolean stereoRectifyUncalibrated_1(long j, long j2, long j3, double d, double d2, long j4, long j5);

    private static native void stereoRectify_0(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, double d3, double d4, double d5, double[] dArr, double[] dArr2);

    private static native void stereoRectify_1(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11);

    private static native void stereoRectify_2(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, double d3, double d4, double d5, double d6);

    private static native void stereoRectify_3(long j, long j2, long j3, long j4, double d, double d2, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i);

    private static native void triangulatePoints_0(long j, long j2, long j3, long j4, long j5);

    private static native void undistortImage_0(long j, long j2, long j3, long j4, long j5, double d, double d2);

    private static native void undistortImage_1(long j, long j2, long j3, long j4);

    private static native void undistortPoints_0(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void undistortPoints_1(long j, long j2, long j3, long j4);

    private static native void validateDisparity_0(long j, long j2, int i, int i2, int i3);

    private static native void validateDisparity_1(long j, long j2, int i, int i2);

    public static Mat estimateAffine2D(Mat mat, Mat mat2, Mat mat3, int i, double d, long j, double d2, long j2) {
        return new Mat(estimateAffine2D_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, j, d2, j2));
    }

    public static Mat estimateAffine2D(Mat mat, Mat mat2) {
        return new Mat(estimateAffine2D_1(mat.nativeObj, mat2.nativeObj));
    }

    public static Mat estimateAffinePartial2D(Mat mat, Mat mat2, Mat mat3, int i, double d, long j, double d2, long j2) {
        return new Mat(estimateAffinePartial2D_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, j, d2, j2));
    }

    public static Mat estimateAffinePartial2D(Mat mat, Mat mat2) {
        return new Mat(estimateAffinePartial2D_1(mat.nativeObj, mat2.nativeObj));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, Mat mat3, int i, double d, double d2, Mat mat4) {
        return new Mat(findEssentialMat_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, d2, mat4.nativeObj));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, Mat mat3, int i, double d, double d2) {
        return new Mat(findEssentialMat_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, i, d, d2));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, Mat mat3) {
        return new Mat(findEssentialMat_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, double d, Point point, int i, double d2, double d3, Mat mat3) {
        return new Mat(findEssentialMat_3(mat.nativeObj, mat2.nativeObj, d, point.x, point.y, i, d2, d3, mat3.nativeObj));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2, double d, Point point, int i, double d2, double d3) {
        return new Mat(findEssentialMat_4(mat.nativeObj, mat2.nativeObj, d, point.x, point.y, i, d2, d3));
    }

    public static Mat findEssentialMat(Mat mat, Mat mat2) {
        return new Mat(findEssentialMat_5(mat.nativeObj, mat2.nativeObj));
    }

    public static Mat findFundamentalMat(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d, double d2, Mat mat) {
        return new Mat(findFundamentalMat_0(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d, d2, mat.nativeObj));
    }

    public static Mat findFundamentalMat(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d, double d2) {
        return new Mat(findFundamentalMat_1(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d, d2));
    }

    public static Mat findFundamentalMat(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2) {
        return new Mat(findFundamentalMat_2(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj));
    }

    public static Mat findHomography(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d, Mat mat, int i2, double d2) {
        return new Mat(findHomography_0(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d, mat.nativeObj, i2, d2));
    }

    public static Mat findHomography(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2, int i, double d) {
        return new Mat(findHomography_1(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj, i, d));
    }

    public static Mat findHomography(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2) {
        return new Mat(findHomography_2(matOfPoint2f.nativeObj, matOfPoint2f2.nativeObj));
    }

    public static Mat getOptimalNewCameraMatrix(Mat mat, Mat mat2, Size size, double d, Size size2, Rect rect, boolean z) {
        double[] dArr = new double[4];
        Mat mat3 = new Mat(getOptimalNewCameraMatrix_0(mat.nativeObj, mat2.nativeObj, size.width, size.height, d, size2.width, size2.height, dArr, z));
        if (rect != null) {
            rect.x = (int) dArr[0];
            rect.y = (int) dArr[1];
            rect.width = (int) dArr[2];
            rect.height = (int) dArr[3];
        }
        return mat3;
    }

    public static Mat getOptimalNewCameraMatrix(Mat mat, Mat mat2, Size size, double d) {
        return new Mat(getOptimalNewCameraMatrix_1(mat.nativeObj, mat2.nativeObj, size.width, size.height, d));
    }

    public static Mat initCameraMatrix2D(List<MatOfPoint3f> list, List<MatOfPoint2f> list2, Size size, double d) {
        return new Mat(initCameraMatrix2D_0(Converters.vector_vector_Point3f_to_Mat(list, new ArrayList(list != null ? list.size() : 0)).nativeObj, Converters.vector_vector_Point2f_to_Mat(list2, new ArrayList(list2 != null ? list2.size() : 0)).nativeObj, size.width, size.height, d));
    }

    public static Mat initCameraMatrix2D(List<MatOfPoint3f> list, List<MatOfPoint2f> list2, Size size) {
        return new Mat(initCameraMatrix2D_1(Converters.vector_vector_Point3f_to_Mat(list, new ArrayList(list != null ? list.size() : 0)).nativeObj, Converters.vector_vector_Point2f_to_Mat(list2, new ArrayList(list2 != null ? list2.size() : 0)).nativeObj, size.width, size.height));
    }

    public static Rect getValidDisparityROI(Rect rect, Rect rect2, int i, int i2, int i3) {
        return new Rect(getValidDisparityROI_0(rect.x, rect.y, rect.width, rect.height, rect2.x, rect2.y, rect2.width, rect2.height, i, i2, i3));
    }

    public static double[] RQDecomp3x3(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        return RQDecomp3x3_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static double[] RQDecomp3x3(Mat mat, Mat mat2, Mat mat3) {
        return RQDecomp3x3_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static boolean findChessboardCorners(Mat mat, Size size, MatOfPoint2f matOfPoint2f, int i) {
        return findChessboardCorners_0(mat.nativeObj, size.width, size.height, matOfPoint2f.nativeObj, i);
    }

    public static boolean findChessboardCorners(Mat mat, Size size, MatOfPoint2f matOfPoint2f) {
        return findChessboardCorners_1(mat.nativeObj, size.width, size.height, matOfPoint2f.nativeObj);
    }

    public static boolean findCirclesGrid(Mat mat, Size size, Mat mat2, int i) {
        return findCirclesGrid_0(mat.nativeObj, size.width, size.height, mat2.nativeObj, i);
    }

    public static boolean findCirclesGrid(Mat mat, Size size, Mat mat2) {
        return findCirclesGrid_1(mat.nativeObj, size.width, size.height, mat2.nativeObj);
    }

    public static boolean solvePnP(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3, boolean z, int i) {
        return solvePnP_0(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj, z, i);
    }

    public static boolean solvePnP(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3) {
        return solvePnP_1(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static boolean solvePnPRansac(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3, boolean z, int i, float f, double d, Mat mat4, int i2) {
        return solvePnPRansac_0(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj, z, i, f, d, mat4.nativeObj, i2);
    }

    public static boolean solvePnPRansac(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, MatOfDouble matOfDouble, Mat mat2, Mat mat3) {
        return solvePnPRansac_1(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, matOfDouble.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static boolean stereoRectifyUncalibrated(Mat mat, Mat mat2, Mat mat3, Size size, Mat mat4, Mat mat5, double d) {
        return stereoRectifyUncalibrated_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, size.width, size.height, mat4.nativeObj, mat5.nativeObj, d);
    }

    public static boolean stereoRectifyUncalibrated(Mat mat, Mat mat2, Mat mat3, Size size, Mat mat4, Mat mat5) {
        return stereoRectifyUncalibrated_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, size.width, size.height, mat4.nativeObj, mat5.nativeObj);
    }

    public static double calibrateCameraExtended(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5, int i, TermCriteria termCriteria) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat6 = new Mat();
        Mat mat7 = new Mat();
        double calibrateCameraExtended_0 = calibrateCameraExtended_0(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size.width, size.height, mat.nativeObj, mat2.nativeObj, mat6.nativeObj, mat7.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, i, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
        Converters.Mat_to_vector_Mat(mat6, list3);
        mat6.release();
        Converters.Mat_to_vector_Mat(mat7, list4);
        mat7.release();
        return calibrateCameraExtended_0;
    }

    public static double calibrateCameraExtended(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5, int i) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat6 = new Mat();
        Mat mat7 = new Mat();
        double calibrateCameraExtended_1 = calibrateCameraExtended_1(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size.width, size.height, mat.nativeObj, mat2.nativeObj, mat6.nativeObj, mat7.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, i);
        Converters.Mat_to_vector_Mat(mat6, list3);
        mat6.release();
        Converters.Mat_to_vector_Mat(mat7, list4);
        mat7.release();
        return calibrateCameraExtended_1;
    }

    public static double calibrateCameraExtended(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, Mat mat3, Mat mat4, Mat mat5) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat6 = new Mat();
        Mat mat7 = new Mat();
        double calibrateCameraExtended_2 = calibrateCameraExtended_2(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size.width, size.height, mat.nativeObj, mat2.nativeObj, mat6.nativeObj, mat7.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
        Converters.Mat_to_vector_Mat(mat6, list3);
        mat6.release();
        Converters.Mat_to_vector_Mat(mat7, list4);
        mat7.release();
        return calibrateCameraExtended_2;
    }

    public static double calibrateCamera(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, int i, TermCriteria termCriteria) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        double calibrateCamera_0 = calibrateCamera_0(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size.width, size.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, i, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
        Converters.Mat_to_vector_Mat(mat3, list3);
        mat3.release();
        Converters.Mat_to_vector_Mat(mat4, list4);
        mat4.release();
        return calibrateCamera_0;
    }

    public static double calibrateCamera(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, int i) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        double calibrateCamera_1 = calibrateCamera_1(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size.width, size.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, i);
        Converters.Mat_to_vector_Mat(mat3, list3);
        mat3.release();
        Converters.Mat_to_vector_Mat(mat4, list4);
        mat4.release();
        return calibrateCamera_1;
    }

    public static double calibrateCamera(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        double calibrateCamera_2 = calibrateCamera_2(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size.width, size.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
        Converters.Mat_to_vector_Mat(mat3, list3);
        mat3.release();
        Converters.Mat_to_vector_Mat(mat4, list4);
        mat4.release();
        return calibrateCamera_2;
    }

    public static double sampsonDistance(Mat mat, Mat mat2, Mat mat3) {
        return sampsonDistance_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static double stereoCalibrateExtended(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, int i, TermCriteria termCriteria) {
        return stereoCalibrateExtended_0(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, i, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
    }

    public static double stereoCalibrateExtended(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, int i) {
        return stereoCalibrateExtended_1(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, i);
    }

    public static double stereoCalibrateExtended(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9) {
        return stereoCalibrateExtended_2(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj);
    }

    public static double stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, int i, TermCriteria termCriteria) {
        return stereoCalibrate_0(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, i, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
    }

    public static double stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, int i) {
        return stereoCalibrate_1(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, i);
    }

    public static double stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8) {
        return stereoCalibrate_2(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj);
    }

    public static double calibrate(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, int i, TermCriteria termCriteria) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        double calibrate_0 = calibrate_0(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size.width, size.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, i, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
        Converters.Mat_to_vector_Mat(mat3, list3);
        mat3.release();
        Converters.Mat_to_vector_Mat(mat4, list4);
        mat4.release();
        return calibrate_0;
    }

    public static double calibrate(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4, int i) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        double calibrate_1 = calibrate_1(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size.width, size.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, i);
        Converters.Mat_to_vector_Mat(mat3, list3);
        mat3.release();
        Converters.Mat_to_vector_Mat(mat4, list4);
        mat4.release();
        return calibrate_1;
    }

    public static double calibrate(List<Mat> list, List<Mat> list2, Size size, Mat mat, Mat mat2, List<Mat> list3, List<Mat> list4) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_Mat_to_Mat2 = Converters.vector_Mat_to_Mat(list2);
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        double calibrate_2 = calibrate_2(vector_Mat_to_Mat.nativeObj, vector_Mat_to_Mat2.nativeObj, size.width, size.height, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
        Converters.Mat_to_vector_Mat(mat3, list3);
        mat3.release();
        Converters.Mat_to_vector_Mat(mat4, list4);
        mat4.release();
        return calibrate_2;
    }

    public static double stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, int i, TermCriteria termCriteria) {
        return stereoCalibrate_3(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, i, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
    }

    public static double stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, int i) {
        return stereoCalibrate_4(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, i);
    }

    public static double stereoCalibrate(List<Mat> list, List<Mat> list2, List<Mat> list3, Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6) {
        return stereoCalibrate_5(Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj);
    }

    public static float rectify3Collinear(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, List<Mat> list, List<Mat> list2, Size size, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, Mat mat12, Mat mat13, Mat mat14, Mat mat15, Mat mat16, Mat mat17, double d, Size size2, Rect rect, Rect rect2, int i) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        float rectify3Collinear_0 = rectify3Collinear_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, Converters.vector_Mat_to_Mat(list2).nativeObj, size.width, size.height, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, mat12.nativeObj, mat13.nativeObj, mat14.nativeObj, mat15.nativeObj, mat16.nativeObj, mat17.nativeObj, d, size2.width, size2.height, dArr, dArr2, i);
        if (rect != null) {
            rect.x = (int) dArr[0];
            rect.y = (int) dArr[1];
            rect.width = (int) dArr[2];
            rect.height = (int) dArr[3];
        }
        if (rect2 != null) {
            rect2.x = (int) dArr2[0];
            rect2.y = (int) dArr2[1];
            rect2.width = (int) dArr2[2];
            rect2.height = (int) dArr2[3];
        }
        return rectify3Collinear_0;
    }

    public static int decomposeHomographyMat(Mat mat, Mat mat2, List<Mat> list, List<Mat> list2, List<Mat> list3) {
        Mat mat3 = new Mat();
        Mat mat4 = new Mat();
        Mat mat5 = new Mat();
        int decomposeHomographyMat_0 = decomposeHomographyMat_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
        Converters.Mat_to_vector_Mat(mat3, list);
        mat3.release();
        Converters.Mat_to_vector_Mat(mat4, list2);
        mat4.release();
        Converters.Mat_to_vector_Mat(mat5, list3);
        mat5.release();
        return decomposeHomographyMat_0;
    }

    public static int estimateAffine3D(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d, double d2) {
        return estimateAffine3D_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d, d2);
    }

    public static int estimateAffine3D(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        return estimateAffine3D_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, double d, Point point, Mat mat6) {
        return recoverPose_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, d, point.x, point.y, mat6.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, double d, Point point) {
        return recoverPose_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, d, point.x, point.y);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        return recoverPose_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7) {
        return recoverPose_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        return recoverPose_4(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, double d, Mat mat7, Mat mat8) {
        return recoverPose_5(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, d, mat7.nativeObj, mat8.nativeObj);
    }

    public static int recoverPose(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, double d) {
        return recoverPose_6(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, d);
    }

    public static int solveP3P(Mat mat, Mat mat2, Mat mat3, Mat mat4, List<Mat> list, List<Mat> list2, int i) {
        Mat mat5 = new Mat();
        Mat mat6 = new Mat();
        int solveP3P_0 = solveP3P_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, i);
        Converters.Mat_to_vector_Mat(mat5, list);
        mat5.release();
        Converters.Mat_to_vector_Mat(mat6, list2);
        mat6.release();
        return solveP3P_0;
    }

    public static void Rodrigues(Mat mat, Mat mat2, Mat mat3) {
        Rodrigues_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void Rodrigues(Mat mat, Mat mat2) {
        Rodrigues_1(mat.nativeObj, mat2.nativeObj);
    }

    public static void calibrationMatrixValues(Mat mat, Size size, double d, double d2, double[] dArr, double[] dArr2, double[] dArr3, Point point, double[] dArr4) {
        double[] dArr5 = new double[1];
        double[] dArr6 = new double[1];
        double[] dArr7 = new double[1];
        double[] dArr8 = new double[2];
        double[] dArr9 = new double[1];
        calibrationMatrixValues_0(mat.nativeObj, size.width, size.height, d, d2, dArr5, dArr6, dArr7, dArr8, dArr9);
        if (dArr != null) {
            dArr[0] = dArr5[0];
        }
        if (dArr2 != null) {
            dArr2[0] = dArr6[0];
        }
        if (dArr3 != null) {
            dArr3[0] = dArr7[0];
        }
        if (point != null) {
            point.x = dArr8[0];
            point.y = dArr8[1];
        }
        if (dArr4 != null) {
            dArr4[0] = dArr9[0];
        }
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, Mat mat12, Mat mat13, Mat mat14) {
        composeRT_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, mat12.nativeObj, mat13.nativeObj, mat14.nativeObj);
    }

    public static void composeRT(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        composeRT_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static void computeCorrespondEpilines(Mat mat, int i, Mat mat2, Mat mat3) {
        computeCorrespondEpilines_0(mat.nativeObj, i, mat2.nativeObj, mat3.nativeObj);
    }

    public static void convertPointsFromHomogeneous(Mat mat, Mat mat2) {
        convertPointsFromHomogeneous_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void convertPointsToHomogeneous(Mat mat, Mat mat2) {
        convertPointsToHomogeneous_0(mat.nativeObj, mat2.nativeObj);
    }

    public static void correctMatches(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        correctMatches_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static void decomposeEssentialMat(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        decomposeEssentialMat_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void decomposeProjectionMatrix(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6, Mat mat7, Mat mat8) {
        decomposeProjectionMatrix_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj);
    }

    public static void decomposeProjectionMatrix(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        decomposeProjectionMatrix_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void drawChessboardCorners(Mat mat, Size size, MatOfPoint2f matOfPoint2f, boolean z) {
        drawChessboardCorners_0(mat.nativeObj, size.width, size.height, matOfPoint2f.nativeObj, z);
    }

    public static void filterSpeckles(Mat mat, double d, int i, double d2, Mat mat2) {
        filterSpeckles_0(mat.nativeObj, d, i, d2, mat2.nativeObj);
    }

    public static void filterSpeckles(Mat mat, double d, int i, double d2) {
        filterSpeckles_1(mat.nativeObj, d, i, d2);
    }

    public static void matMulDeriv(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        matMulDeriv_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void projectPoints(MatOfPoint3f matOfPoint3f, Mat mat, Mat mat2, Mat mat3, MatOfDouble matOfDouble, MatOfPoint2f matOfPoint2f, Mat mat4, double d) {
        projectPoints_0(matOfPoint3f.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, matOfDouble.nativeObj, matOfPoint2f.nativeObj, mat4.nativeObj, d);
    }

    public static void projectPoints(MatOfPoint3f matOfPoint3f, Mat mat, Mat mat2, Mat mat3, MatOfDouble matOfDouble, MatOfPoint2f matOfPoint2f) {
        projectPoints_1(matOfPoint3f.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, matOfDouble.nativeObj, matOfPoint2f.nativeObj);
    }

    public static void reprojectImageTo3D(Mat mat, Mat mat2, Mat mat3, boolean z, int i) {
        reprojectImageTo3D_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, z, i);
    }

    public static void reprojectImageTo3D(Mat mat, Mat mat2, Mat mat3, boolean z) {
        reprojectImageTo3D_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, z);
    }

    public static void reprojectImageTo3D(Mat mat, Mat mat2, Mat mat3) {
        reprojectImageTo3D_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i, double d, Size size2, Rect rect, Rect rect2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        stereoRectify_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i, d, size2.width, size2.height, dArr, dArr2);
        if (rect != null) {
            rect.x = (int) dArr[0];
            rect.y = (int) dArr[1];
            rect.width = (int) dArr[2];
            rect.height = (int) dArr[3];
        }
        if (rect2 != null) {
            rect2.x = (int) dArr2[0];
            rect2.y = (int) dArr2[1];
            rect2.width = (int) dArr2[2];
            rect2.height = (int) dArr2[3];
        }
    }

    public static void stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11) {
        stereoRectify_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj);
    }

    public static void triangulatePoints(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5) {
        triangulatePoints_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj);
    }

    public static void validateDisparity(Mat mat, Mat mat2, int i, int i2, int i3) {
        validateDisparity_0(mat.nativeObj, mat2.nativeObj, i, i2, i3);
    }

    public static void validateDisparity(Mat mat, Mat mat2, int i, int i2) {
        validateDisparity_1(mat.nativeObj, mat2.nativeObj, i, i2);
    }

    public static void distortPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4, double d) {
        distortPoints_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d);
    }

    public static void distortPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        distortPoints_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void estimateNewCameraMatrixForUndistortRectify(Mat mat, Mat mat2, Size size, Mat mat3, Mat mat4, double d, Size size2, double d2) {
        estimateNewCameraMatrixForUndistortRectify_0(mat.nativeObj, mat2.nativeObj, size.width, size.height, mat3.nativeObj, mat4.nativeObj, d, size2.width, size2.height, d2);
    }

    public static void estimateNewCameraMatrixForUndistortRectify(Mat mat, Mat mat2, Size size, Mat mat3, Mat mat4) {
        estimateNewCameraMatrixForUndistortRectify_1(mat.nativeObj, mat2.nativeObj, size.width, size.height, mat3.nativeObj, mat4.nativeObj);
    }

    public static void initUndistortRectifyMap(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, int i, Mat mat5, Mat mat6) {
        initUndistortRectifyMap_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, i, mat5.nativeObj, mat6.nativeObj);
    }

    public static void projectPoints(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, Mat mat2, Mat mat3, Mat mat4, double d, Mat mat5) {
        projectPoints_2(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, d, mat5.nativeObj);
    }

    public static void projectPoints(MatOfPoint3f matOfPoint3f, MatOfPoint2f matOfPoint2f, Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        projectPoints_3(matOfPoint3f.nativeObj, matOfPoint2f.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i, Size size2, double d, double d2) {
        stereoRectify_2(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i, size2.width, size2.height, d, d2);
    }

    public static void stereoRectify(Mat mat, Mat mat2, Mat mat3, Mat mat4, Size size, Mat mat5, Mat mat6, Mat mat7, Mat mat8, Mat mat9, Mat mat10, Mat mat11, int i) {
        stereoRectify_3(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, size.width, size.height, mat5.nativeObj, mat6.nativeObj, mat7.nativeObj, mat8.nativeObj, mat9.nativeObj, mat10.nativeObj, mat11.nativeObj, i);
    }

    public static void undistortImage(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Size size) {
        undistortImage_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, size.width, size.height);
    }

    public static void undistortImage(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        undistortImage_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }

    public static void undistortPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4, Mat mat5, Mat mat6) {
        undistortPoints_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj, mat5.nativeObj, mat6.nativeObj);
    }

    public static void undistortPoints(Mat mat, Mat mat2, Mat mat3, Mat mat4) {
        undistortPoints_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, mat4.nativeObj);
    }
}
