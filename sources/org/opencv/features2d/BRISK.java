package org.opencv.features2d;

import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;

/* loaded from: classes4.dex */
public class BRISK extends Feature2D {
    private static native long create_0(int i, int i2, long j, long j2, float f, float f2, long j3);

    private static native long create_1(int i, int i2, long j, long j2);

    private static native long create_2(int i, int i2, float f);

    private static native long create_3();

    private static native long create_4(long j, long j2, float f, float f2, long j3);

    private static native long create_5(long j, long j2);

    private static native void delete(long j);

    private static native String getDefaultName_0(long j);

    protected BRISK(long j) {
        super(j);
    }

    public static BRISK __fromPtr__(long j) {
        return new BRISK(j);
    }

    public static BRISK create(int i, int i2, MatOfFloat matOfFloat, MatOfInt matOfInt, float f, float f2, MatOfInt matOfInt2) {
        return __fromPtr__(create_0(i, i2, matOfFloat.nativeObj, matOfInt.nativeObj, f, f2, matOfInt2.nativeObj));
    }

    public static BRISK create(int i, int i2, MatOfFloat matOfFloat, MatOfInt matOfInt) {
        return __fromPtr__(create_1(i, i2, matOfFloat.nativeObj, matOfInt.nativeObj));
    }

    public static BRISK create(int i, int i2, float f) {
        return __fromPtr__(create_2(i, i2, f));
    }

    public static BRISK create() {
        return __fromPtr__(create_3());
    }

    public static BRISK create(MatOfFloat matOfFloat, MatOfInt matOfInt, float f, float f2, MatOfInt matOfInt2) {
        return __fromPtr__(create_4(matOfFloat.nativeObj, matOfInt.nativeObj, f, f2, matOfInt2.nativeObj));
    }

    public static BRISK create(MatOfFloat matOfFloat, MatOfInt matOfInt) {
        return __fromPtr__(create_5(matOfFloat.nativeObj, matOfInt.nativeObj));
    }

    @Override // org.opencv.features2d.Feature2D, org.opencv.core.Algorithm
    public String getDefaultName() {
        return getDefaultName_0(this.nativeObj);
    }

    @Override // org.opencv.features2d.Feature2D, org.opencv.core.Algorithm
    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
