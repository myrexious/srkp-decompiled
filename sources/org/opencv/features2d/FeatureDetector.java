package org.opencv.features2d;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.utils.Converters;

@Deprecated
/* loaded from: classes4.dex */
public class FeatureDetector {
    public static final int AKAZE = 12;
    public static final int BRISK = 11;
    public static final int DENSE = 10;
    private static final int DYNAMICDETECTOR = 3000;
    public static final int DYNAMIC_AKAZE = 3012;
    public static final int DYNAMIC_BRISK = 3011;
    public static final int DYNAMIC_DENSE = 3010;
    public static final int DYNAMIC_FAST = 3001;
    public static final int DYNAMIC_GFTT = 3007;
    public static final int DYNAMIC_HARRIS = 3008;
    public static final int DYNAMIC_MSER = 3006;
    public static final int DYNAMIC_ORB = 3005;
    public static final int DYNAMIC_SIFT = 3003;
    public static final int DYNAMIC_SIMPLEBLOB = 3009;
    public static final int DYNAMIC_STAR = 3002;
    public static final int DYNAMIC_SURF = 3004;
    public static final int FAST = 1;
    public static final int GFTT = 7;
    private static final int GRIDDETECTOR = 1000;
    public static final int GRID_AKAZE = 1012;
    public static final int GRID_BRISK = 1011;
    public static final int GRID_DENSE = 1010;
    public static final int GRID_FAST = 1001;
    public static final int GRID_GFTT = 1007;
    public static final int GRID_HARRIS = 1008;
    public static final int GRID_MSER = 1006;
    public static final int GRID_ORB = 1005;
    public static final int GRID_SIFT = 1003;
    public static final int GRID_SIMPLEBLOB = 1009;
    public static final int GRID_STAR = 1002;
    public static final int GRID_SURF = 1004;
    public static final int HARRIS = 8;
    public static final int MSER = 6;
    public static final int ORB = 5;
    private static final int PYRAMIDDETECTOR = 2000;
    public static final int PYRAMID_AKAZE = 2012;
    public static final int PYRAMID_BRISK = 2011;
    public static final int PYRAMID_DENSE = 2010;
    public static final int PYRAMID_FAST = 2001;
    public static final int PYRAMID_GFTT = 2007;
    public static final int PYRAMID_HARRIS = 2008;
    public static final int PYRAMID_MSER = 2006;
    public static final int PYRAMID_ORB = 2005;
    public static final int PYRAMID_SIFT = 2003;
    public static final int PYRAMID_SIMPLEBLOB = 2009;
    public static final int PYRAMID_STAR = 2002;
    public static final int PYRAMID_SURF = 2004;
    public static final int SIFT = 3;
    public static final int SIMPLEBLOB = 9;
    public static final int STAR = 2;
    public static final int SURF = 4;
    protected final long nativeObj;

    private static native long create_0(int i);

    private static native void delete(long j);

    private static native void detect_0(long j, long j2, long j3, long j4);

    private static native void detect_1(long j, long j2, long j3);

    private static native void detect_2(long j, long j2, long j3, long j4);

    private static native void detect_3(long j, long j2, long j3);

    private static native boolean empty_0(long j);

    private static native void read_0(long j, String str);

    private static native void write_0(long j, String str);

    protected FeatureDetector(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static FeatureDetector __fromPtr__(long j) {
        return new FeatureDetector(j);
    }

    @Deprecated
    public static FeatureDetector create(int i) {
        return __fromPtr__(create_0(i));
    }

    public boolean empty() {
        return empty_0(this.nativeObj);
    }

    public void detect(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2) {
        detect_0(this.nativeObj, mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj);
    }

    public void detect(Mat mat, MatOfKeyPoint matOfKeyPoint) {
        detect_1(this.nativeObj, mat.nativeObj, matOfKeyPoint.nativeObj);
    }

    public void detect(List<Mat> list, List<MatOfKeyPoint> list2, List<Mat> list3) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat = new Mat();
        detect_2(this.nativeObj, vector_Mat_to_Mat.nativeObj, mat.nativeObj, Converters.vector_Mat_to_Mat(list3).nativeObj);
        Converters.Mat_to_vector_vector_KeyPoint(mat, list2);
        mat.release();
    }

    public void detect(List<Mat> list, List<MatOfKeyPoint> list2) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat mat = new Mat();
        detect_3(this.nativeObj, vector_Mat_to_Mat.nativeObj, mat.nativeObj);
        Converters.Mat_to_vector_vector_KeyPoint(mat, list2);
        mat.release();
    }

    public void read(String str) {
        read_0(this.nativeObj, str);
    }

    public void write(String str) {
        write_0(this.nativeObj, str);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
