package org.opencv.features2d;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.utils.Converters;

@Deprecated
/* loaded from: classes4.dex */
public class DescriptorExtractor {
    public static final int AKAZE = 7;
    public static final int BRIEF = 4;
    public static final int BRISK = 5;
    public static final int FREAK = 6;
    private static final int OPPONENTEXTRACTOR = 1000;
    public static final int OPPONENT_AKAZE = 1007;
    public static final int OPPONENT_BRIEF = 1004;
    public static final int OPPONENT_BRISK = 1005;
    public static final int OPPONENT_FREAK = 1006;
    public static final int OPPONENT_ORB = 1003;
    public static final int OPPONENT_SIFT = 1001;
    public static final int OPPONENT_SURF = 1002;
    public static final int ORB = 3;
    public static final int SIFT = 1;
    public static final int SURF = 2;
    protected final long nativeObj;

    private static native void compute_0(long j, long j2, long j3, long j4);

    private static native void compute_1(long j, long j2, long j3, long j4);

    private static native long create_0(int i);

    private static native void delete(long j);

    private static native int descriptorSize_0(long j);

    private static native int descriptorType_0(long j);

    private static native boolean empty_0(long j);

    private static native void read_0(long j, String str);

    private static native void write_0(long j, String str);

    protected DescriptorExtractor(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static DescriptorExtractor __fromPtr__(long j) {
        return new DescriptorExtractor(j);
    }

    public static DescriptorExtractor create(int i) {
        return __fromPtr__(create_0(i));
    }

    public boolean empty() {
        return empty_0(this.nativeObj);
    }

    public int descriptorSize() {
        return descriptorSize_0(this.nativeObj);
    }

    public int descriptorType() {
        return descriptorType_0(this.nativeObj);
    }

    public void compute(Mat mat, MatOfKeyPoint matOfKeyPoint, Mat mat2) {
        compute_0(this.nativeObj, mat.nativeObj, matOfKeyPoint.nativeObj, mat2.nativeObj);
    }

    public void compute(List<Mat> list, List<MatOfKeyPoint> list2, List<Mat> list3) {
        Mat vector_Mat_to_Mat = Converters.vector_Mat_to_Mat(list);
        Mat vector_vector_KeyPoint_to_Mat = Converters.vector_vector_KeyPoint_to_Mat(list2, new ArrayList(list2 != null ? list2.size() : 0));
        Mat mat = new Mat();
        compute_1(this.nativeObj, vector_Mat_to_Mat.nativeObj, vector_vector_KeyPoint_to_Mat.nativeObj, mat.nativeObj);
        Converters.Mat_to_vector_vector_KeyPoint(vector_vector_KeyPoint_to_Mat, list2);
        vector_vector_KeyPoint_to_Mat.release();
        Converters.Mat_to_vector_Mat(mat, list3);
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
