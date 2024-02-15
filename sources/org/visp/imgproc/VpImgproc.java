package org.visp.imgproc;

import org.visp.core.VpImagePoint;
import org.visp.core.VpImageRGBa;
import org.visp.core.VpImageUChar;

/* loaded from: classes4.dex */
public class VpImgproc {
    public final long nativeObj;

    private static native void adjust_0(long j, long j2, double d, double d2);

    private static native void adjust_1(long j, double d, double d2);

    private static native void adjust_2(long j, long j2, double d, double d2);

    private static native void adjust_3(long j, double d, double d2);

    private static native void clahe_0(long j, long j2, int i, int i2, float f, boolean z);

    private static native void clahe_1(long j, long j2);

    private static native void clahe_2(long j, long j2, int i, int i2, float f, boolean z);

    private static native void clahe_3(long j, long j2);

    private static native void equalizeHistogram_0(long j, long j2);

    private static native void equalizeHistogram_1(long j);

    private static native void equalizeHistogram_2(long j, long j2, boolean z);

    private static native void equalizeHistogram_3(long j, long j2);

    private static native void equalizeHistogram_4(long j, boolean z);

    private static native void equalizeHistogram_5(long j);

    private static native void fillHoles(long j);

    private static native void floodFill(long j, double d, double d2, char c, char c2);

    private static native void gammaCorrection_0(long j, long j2, double d);

    private static native void gammaCorrection_1(long j, double d);

    private static native void gammaCorrection_2(long j, long j2, double d);

    private static native void gammaCorrection_3(long j, double d);

    private static native void reconstruct(long j, long j2, long j3);

    private static native void retinex_0(long j, long j2, int i, int i2, int i3, double d, int i4);

    private static native void retinex_1(long j, long j2);

    private static native void retinex_2(long j, int i, int i2, int i3, double d, int i4);

    private static native void retinex_3(long j);

    private static native void stretchContrastHSV_0(long j, long j2);

    private static native void stretchContrastHSV_1(long j);

    private static native void stretchContrast_0(long j, long j2);

    private static native void stretchContrast_1(long j);

    private static native void stretchContrast_2(long j, long j2);

    private static native void stretchContrast_3(long j);

    private static native void unsharpMask_0(long j, float f, double d);

    private static native void unsharpMask_1(long j, float f);

    private static native void unsharpMask_2(long j, long j2, float f, double d);

    private static native void unsharpMask_3(long j, long j2, float f);

    private static native void unsharpMask_4(long j, float f, double d);

    private static native void unsharpMask_5(long j, float f);

    private static native void unsharpMask_6(long j, long j2, float f, double d);

    private static native void unsharpMask_7(long j, long j2, float f);

    public VpImgproc(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpImgproc __fromPtr__(long j) {
        return new VpImgproc(j);
    }

    public static void adjust(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, double d, double d2) {
        adjust_0(vpImageUChar.nativeObj, vpImageUChar2.nativeObj, d, d2);
    }

    public static void adjust(VpImageUChar vpImageUChar, double d, double d2) {
        adjust_1(vpImageUChar.nativeObj, d, d2);
    }

    public static void adjust(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, double d, double d2) {
        adjust_2(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, d, d2);
    }

    public static void adjust(VpImageRGBa vpImageRGBa, double d, double d2) {
        adjust_3(vpImageRGBa.nativeObj, d, d2);
    }

    public static void clahe(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, int i, int i2, float f, boolean z) {
        clahe_0(vpImageUChar.nativeObj, vpImageUChar2.nativeObj, i, i2, f, z);
    }

    public static void clahe(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2) {
        clahe_1(vpImageUChar.nativeObj, vpImageUChar2.nativeObj);
    }

    public static void clahe(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, int i, int i2, float f, boolean z) {
        clahe_2(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, i, i2, f, z);
    }

    public static void clahe(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2) {
        clahe_3(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj);
    }

    public static void equalizeHistogram(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2) {
        equalizeHistogram_0(vpImageUChar.nativeObj, vpImageUChar2.nativeObj);
    }

    public static void equalizeHistogram(VpImageUChar vpImageUChar) {
        equalizeHistogram_1(vpImageUChar.nativeObj);
    }

    public static void equalizeHistogram(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, boolean z) {
        equalizeHistogram_2(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, z);
    }

    public static void equalizeHistogram(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2) {
        equalizeHistogram_3(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj);
    }

    public static void equalizeHistogram(VpImageRGBa vpImageRGBa, boolean z) {
        equalizeHistogram_4(vpImageRGBa.nativeObj, z);
    }

    public static void equalizeHistogram(VpImageRGBa vpImageRGBa) {
        equalizeHistogram_5(vpImageRGBa.nativeObj);
    }

    public static void gammaCorrection(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, double d) {
        gammaCorrection_0(vpImageUChar.nativeObj, vpImageUChar2.nativeObj, d);
    }

    public static void gammaCorrection(VpImageUChar vpImageUChar, double d) {
        gammaCorrection_1(vpImageUChar.nativeObj, d);
    }

    public static void gammaCorrection(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, double d) {
        gammaCorrection_2(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, d);
    }

    public static void gammaCorrection(VpImageRGBa vpImageRGBa, double d) {
        gammaCorrection_3(vpImageRGBa.nativeObj, d);
    }

    public static void retinex(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, int i, int i2, int i3, double d, int i4) {
        retinex_0(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, i, i2, i3, d, i4);
    }

    public static void retinex(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2) {
        retinex_1(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj);
    }

    public static void retinex(VpImageRGBa vpImageRGBa, int i, int i2, int i3, double d, int i4) {
        retinex_2(vpImageRGBa.nativeObj, i, i2, i3, d, i4);
    }

    public static void retinex(VpImageRGBa vpImageRGBa) {
        retinex_3(vpImageRGBa.nativeObj);
    }

    public static void stretchContrast(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2) {
        stretchContrast_0(vpImageUChar.nativeObj, vpImageUChar2.nativeObj);
    }

    public static void stretchContrast(VpImageUChar vpImageUChar) {
        stretchContrast_1(vpImageUChar.nativeObj);
    }

    public static void stretchContrast(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2) {
        stretchContrast_2(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj);
    }

    public static void stretchContrast(VpImageRGBa vpImageRGBa) {
        stretchContrast_3(vpImageRGBa.nativeObj);
    }

    public static void stretchContrastHSV(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2) {
        stretchContrastHSV_0(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj);
    }

    public static void stretchContrastHSV(VpImageRGBa vpImageRGBa) {
        stretchContrastHSV_1(vpImageRGBa.nativeObj);
    }

    public static void unsharpMask(VpImageUChar vpImageUChar, float f, double d) {
        unsharpMask_0(vpImageUChar.nativeObj, f, d);
    }

    public static void unsharpMask(VpImageUChar vpImageUChar, float f) {
        unsharpMask_1(vpImageUChar.nativeObj, f);
    }

    public static void unsharpMask(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, float f, double d) {
        unsharpMask_2(vpImageUChar.nativeObj, vpImageUChar2.nativeObj, f, d);
    }

    public static void unsharpMask(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, float f) {
        unsharpMask_3(vpImageUChar.nativeObj, vpImageUChar2.nativeObj, f);
    }

    public static void unsharpMask(VpImageRGBa vpImageRGBa, float f, double d) {
        unsharpMask_4(vpImageRGBa.nativeObj, f, d);
    }

    public static void unsharpMask(VpImageRGBa vpImageRGBa, float f) {
        unsharpMask_5(vpImageRGBa.nativeObj, f);
    }

    public static void unsharpMask(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, float f, double d) {
        unsharpMask_6(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, f, d);
    }

    public static void unsharpMask(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, float f) {
        unsharpMask_7(vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, f);
    }

    public static void reconstruct(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, VpImageUChar vpImageUChar3) {
        reconstruct(vpImageUChar.nativeObj, vpImageUChar2.nativeObj, vpImageUChar3.nativeObj);
    }

    public static void floodFill(VpImageUChar vpImageUChar, VpImagePoint vpImagePoint, char c, char c2) {
        floodFill(vpImageUChar.nativeObj, vpImagePoint.get_i(), vpImagePoint.get_j(), c, c2);
    }

    public static void fillHoles(VpImageUChar vpImageUChar) {
        fillHoles(vpImageUChar.nativeObj);
    }
}
