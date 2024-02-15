package org.opencv.features2d;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.utils.Converters;

/* loaded from: classes4.dex */
public class Features2d {
    public static final int DRAW_OVER_OUTIMG = 1;
    public static final int DRAW_RICH_KEYPOINTS = 4;
    public static final int NOT_DRAW_SINGLE_POINTS = 2;

    private static native void drawKeypoints_0(long j, long j2, long j3, double d, double d2, double d3, double d4, int i);

    private static native void drawKeypoints_1(long j, long j2, long j3);

    private static native void drawMatches2_0(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, long j7, int i);

    private static native void drawMatches2_1(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void drawMatchesKnn_0(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, long j7, int i);

    private static native void drawMatchesKnn_1(long j, long j2, long j3, long j4, long j5, long j6);

    private static native void drawMatches_0(long j, long j2, long j3, long j4, long j5, long j6, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, long j7, int i);

    private static native void drawMatches_1(long j, long j2, long j3, long j4, long j5, long j6);

    public static void drawKeypoints(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, Scalar scalar, int i) {
        drawKeypoints_0(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3], i);
    }

    public static void drawKeypoints(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2) {
        drawKeypoints_1(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj);
    }

    public static void drawMatches(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, Mat mat3, Scalar scalar, Scalar scalar2, MatOfByte matOfByte, int i) {
        drawMatches_0(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, mat3.nativeObj, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3], scalar2.val[0], scalar2.val[1], scalar2.val[2], scalar2.val[3], matOfByte.nativeObj, i);
    }

    public static void drawMatches(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, Mat mat3) {
        drawMatches_1(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, mat3.nativeObj);
    }

    public static void drawMatches2(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, List<MatOfDMatch> list, Mat mat3, Scalar scalar, Scalar scalar2, List<MatOfByte> list2, int i) {
        drawMatches2_0(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, Converters.vector_vector_DMatch_to_Mat(list, new ArrayList(list != null ? list.size() : 0)).nativeObj, mat3.nativeObj, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3], scalar2.val[0], scalar2.val[1], scalar2.val[2], scalar2.val[3], Converters.vector_vector_char_to_Mat(list2, new ArrayList(list2 != null ? list2.size() : 0)).nativeObj, i);
    }

    public static void drawMatches2(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, List<MatOfDMatch> list, Mat mat3) {
        drawMatches2_1(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, Converters.vector_vector_DMatch_to_Mat(list, new ArrayList(list != null ? list.size() : 0)).nativeObj, mat3.nativeObj);
    }

    public static void drawMatchesKnn(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, List<MatOfDMatch> list, Mat mat3, Scalar scalar, Scalar scalar2, List<MatOfByte> list2, int i) {
        drawMatchesKnn_0(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, Converters.vector_vector_DMatch_to_Mat(list, new ArrayList(list != null ? list.size() : 0)).nativeObj, mat3.nativeObj, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3], scalar2.val[0], scalar2.val[1], scalar2.val[2], scalar2.val[3], Converters.vector_vector_char_to_Mat(list2, new ArrayList(list2 != null ? list2.size() : 0)).nativeObj, i);
    }

    public static void drawMatchesKnn(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2, MatOfKeyPoint matOfKeyPoint2, List<MatOfDMatch> list, Mat mat3) {
        drawMatchesKnn_1(mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj, matOfKeyPoint2.nativeObj, Converters.vector_vector_DMatch_to_Mat(list, new ArrayList(list != null ? list.size() : 0)).nativeObj, mat3.nativeObj);
    }
}
