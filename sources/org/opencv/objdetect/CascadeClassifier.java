package org.opencv.objdetect;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;

/* loaded from: classes4.dex */
public class CascadeClassifier {
    protected final long nativeObj;

    private static native long CascadeClassifier_0(String str);

    private static native long CascadeClassifier_1();

    private static native boolean convert_0(String str, String str2);

    private static native void delete(long j);

    private static native void detectMultiScale2_0(long j, long j2, long j3, long j4, double d, int i, int i2, double d2, double d3, double d4, double d5);

    private static native void detectMultiScale2_1(long j, long j2, long j3, long j4);

    private static native void detectMultiScale3_0(long j, long j2, long j3, long j4, long j5, double d, int i, int i2, double d2, double d3, double d4, double d5, boolean z);

    private static native void detectMultiScale3_1(long j, long j2, long j3, long j4, long j5);

    private static native void detectMultiScale_0(long j, long j2, long j3, double d, int i, int i2, double d2, double d3, double d4, double d5);

    private static native void detectMultiScale_1(long j, long j2, long j3);

    private static native boolean empty_0(long j);

    private static native int getFeatureType_0(long j);

    private static native double[] getOriginalWindowSize_0(long j);

    private static native boolean isOldFormatCascade_0(long j);

    private static native boolean load_0(long j, String str);

    protected CascadeClassifier(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static CascadeClassifier __fromPtr__(long j) {
        return new CascadeClassifier(j);
    }

    public CascadeClassifier(String str) {
        this.nativeObj = CascadeClassifier_0(str);
    }

    public CascadeClassifier() {
        this.nativeObj = CascadeClassifier_1();
    }

    public Size getOriginalWindowSize() {
        return new Size(getOriginalWindowSize_0(this.nativeObj));
    }

    public static boolean convert(String str, String str2) {
        return convert_0(str, str2);
    }

    public boolean empty() {
        return empty_0(this.nativeObj);
    }

    public boolean isOldFormatCascade() {
        return isOldFormatCascade_0(this.nativeObj);
    }

    public boolean load(String str) {
        return load_0(this.nativeObj, str);
    }

    public int getFeatureType() {
        return getFeatureType_0(this.nativeObj);
    }

    public void detectMultiScale(Mat mat, MatOfRect matOfRect, double d, int i, int i2, Size size, Size size2) {
        detectMultiScale_0(this.nativeObj, mat.nativeObj, matOfRect.nativeObj, d, i, i2, size.width, size.height, size2.width, size2.height);
    }

    public void detectMultiScale(Mat mat, MatOfRect matOfRect) {
        detectMultiScale_1(this.nativeObj, mat.nativeObj, matOfRect.nativeObj);
    }

    public void detectMultiScale2(Mat mat, MatOfRect matOfRect, MatOfInt matOfInt, double d, int i, int i2, Size size, Size size2) {
        detectMultiScale2_0(this.nativeObj, mat.nativeObj, matOfRect.nativeObj, matOfInt.nativeObj, d, i, i2, size.width, size.height, size2.width, size2.height);
    }

    public void detectMultiScale2(Mat mat, MatOfRect matOfRect, MatOfInt matOfInt) {
        detectMultiScale2_1(this.nativeObj, mat.nativeObj, matOfRect.nativeObj, matOfInt.nativeObj);
    }

    public void detectMultiScale3(Mat mat, MatOfRect matOfRect, MatOfInt matOfInt, MatOfDouble matOfDouble, double d, int i, int i2, Size size, Size size2, boolean z) {
        detectMultiScale3_0(this.nativeObj, mat.nativeObj, matOfRect.nativeObj, matOfInt.nativeObj, matOfDouble.nativeObj, d, i, i2, size.width, size.height, size2.width, size2.height, z);
    }

    public void detectMultiScale3(Mat mat, MatOfRect matOfRect, MatOfInt matOfInt, MatOfDouble matOfDouble) {
        detectMultiScale3_1(this.nativeObj, mat.nativeObj, matOfRect.nativeObj, matOfInt.nativeObj, matOfDouble.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
