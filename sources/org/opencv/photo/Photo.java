package org.opencv.photo;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Point;
import org.opencv.utils.Converters;

/* loaded from: classes4.dex */
public class Photo {
    private static final int CV_INPAINT_NS = 0;
    private static final int CV_INPAINT_TELEA = 1;
    public static final int INPAINT_NS = 0;
    public static final int INPAINT_TELEA = 1;
    public static final int LDR_SIZE = 256;
    public static final int MIXED_CLONE = 2;
    public static final int MONOCHROME_TRANSFER = 3;
    public static final int NORMAL_CLONE = 1;
    public static final int NORMCONV_FILTER = 2;
    public static final int RECURS_FILTER = 1;

    private static native void colorChange_0(long j, long j2, long j3, float f, float f2, float f3);

    private static native void colorChange_1(long j, long j2, long j3);

    private static native long createAlignMTB_0(int i, int i2, boolean z);

    private static native long createAlignMTB_1();

    private static native long createCalibrateDebevec_0(int i, float f, boolean z);

    private static native long createCalibrateDebevec_1();

    private static native long createCalibrateRobertson_0(int i, float f);

    private static native long createCalibrateRobertson_1();

    private static native long createMergeDebevec_0();

    private static native long createMergeMertens_0(float f, float f2, float f3);

    private static native long createMergeMertens_1();

    private static native long createMergeRobertson_0();

    private static native long createTonemapDrago_0(float f, float f2, float f3);

    private static native long createTonemapDrago_1();

    private static native long createTonemapDurand_0(float f, float f2, float f3, float f4, float f5);

    private static native long createTonemapDurand_1();

    private static native long createTonemapMantiuk_0(float f, float f2, float f3);

    private static native long createTonemapMantiuk_1();

    private static native long createTonemapReinhard_0(float f, float f2, float f3, float f4);

    private static native long createTonemapReinhard_1();

    private static native long createTonemap_0(float f);

    private static native long createTonemap_1();

    private static native void decolor_0(long j, long j2, long j3);

    private static native void denoise_TVL1_0(long j, long j2, double d, int i);

    private static native void denoise_TVL1_1(long j, long j2);

    private static native void detailEnhance_0(long j, long j2, float f, float f2);

    private static native void detailEnhance_1(long j, long j2);

    private static native void edgePreservingFilter_0(long j, long j2, int i, float f, float f2);

    private static native void edgePreservingFilter_1(long j, long j2);

    private static native void fastNlMeansDenoisingColoredMulti_0(long j, long j2, int i, int i2, float f, float f2, int i3, int i4);

    private static native void fastNlMeansDenoisingColoredMulti_1(long j, long j2, int i, int i2);

    private static native void fastNlMeansDenoisingColored_0(long j, long j2, float f, float f2, int i, int i2);

    private static native void fastNlMeansDenoisingColored_1(long j, long j2);

    private static native void fastNlMeansDenoisingMulti_0(long j, long j2, int i, int i2, float f, int i3, int i4);

    private static native void fastNlMeansDenoisingMulti_1(long j, long j2, int i, int i2);

    private static native void fastNlMeansDenoisingMulti_2(long j, long j2, int i, int i2, long j3, int i3, int i4, int i5);

    private static native void fastNlMeansDenoisingMulti_3(long j, long j2, int i, int i2, long j3);

    private static native void fastNlMeansDenoising_0(long j, long j2, float f, int i, int i2);

    private static native void fastNlMeansDenoising_1(long j, long j2);

    private static native void fastNlMeansDenoising_2(long j, long j2, long j3, int i, int i2, int i3);

    private static native void fastNlMeansDenoising_3(long j, long j2, long j3);

    private static native void illuminationChange_0(long j, long j2, long j3, float f, float f2);

    private static native void illuminationChange_1(long j, long j2, long j3);

    private static native void inpaint_0(long j, long j2, long j3, double d, int i);

    private static native void pencilSketch_0(long j, long j2, long j3, float f, float f2, float f3);

    private static native void pencilSketch_1(long j, long j2, long j3);

    private static native void seamlessClone_0(long j, long j2, long j3, double d, double d2, long j4, int i);

    private static native void stylization_0(long j, long j2, float f, float f2);

    private static native void stylization_1(long j, long j2);

    private static native void textureFlattening_0(long j, long j2, long j3, float f, float f2, int i);

    private static native void textureFlattening_1(long j, long j2, long j3);

    public static AlignMTB createAlignMTB(int i, int i2, boolean z) {
        return AlignMTB.__fromPtr__(createAlignMTB_0(i, i2, z));
    }

    public static AlignMTB createAlignMTB() {
        return AlignMTB.__fromPtr__(createAlignMTB_1());
    }

    public static CalibrateDebevec createCalibrateDebevec(int i, float f, boolean z) {
        return CalibrateDebevec.__fromPtr__(createCalibrateDebevec_0(i, f, z));
    }

    public static CalibrateDebevec createCalibrateDebevec() {
        return CalibrateDebevec.__fromPtr__(createCalibrateDebevec_1());
    }

    public static CalibrateRobertson createCalibrateRobertson(int i, float f) {
        return CalibrateRobertson.__fromPtr__(createCalibrateRobertson_0(i, f));
    }

    public static CalibrateRobertson createCalibrateRobertson() {
        return CalibrateRobertson.__fromPtr__(createCalibrateRobertson_1());
    }

    public static MergeDebevec createMergeDebevec() {
        return MergeDebevec.__fromPtr__(createMergeDebevec_0());
    }

    public static MergeMertens createMergeMertens(float f, float f2, float f3) {
        return MergeMertens.__fromPtr__(createMergeMertens_0(f, f2, f3));
    }

    public static MergeMertens createMergeMertens() {
        return MergeMertens.__fromPtr__(createMergeMertens_1());
    }

    public static MergeRobertson createMergeRobertson() {
        return MergeRobertson.__fromPtr__(createMergeRobertson_0());
    }

    public static Tonemap createTonemap(float f) {
        return Tonemap.__fromPtr__(createTonemap_0(f));
    }

    public static Tonemap createTonemap() {
        return Tonemap.__fromPtr__(createTonemap_1());
    }

    public static TonemapDrago createTonemapDrago(float f, float f2, float f3) {
        return TonemapDrago.__fromPtr__(createTonemapDrago_0(f, f2, f3));
    }

    public static TonemapDrago createTonemapDrago() {
        return TonemapDrago.__fromPtr__(createTonemapDrago_1());
    }

    public static TonemapDurand createTonemapDurand(float f, float f2, float f3, float f4, float f5) {
        return TonemapDurand.__fromPtr__(createTonemapDurand_0(f, f2, f3, f4, f5));
    }

    public static TonemapDurand createTonemapDurand() {
        return TonemapDurand.__fromPtr__(createTonemapDurand_1());
    }

    public static TonemapMantiuk createTonemapMantiuk(float f, float f2, float f3) {
        return TonemapMantiuk.__fromPtr__(createTonemapMantiuk_0(f, f2, f3));
    }

    public static TonemapMantiuk createTonemapMantiuk() {
        return TonemapMantiuk.__fromPtr__(createTonemapMantiuk_1());
    }

    public static TonemapReinhard createTonemapReinhard(float f, float f2, float f3, float f4) {
        return TonemapReinhard.__fromPtr__(createTonemapReinhard_0(f, f2, f3, f4));
    }

    public static TonemapReinhard createTonemapReinhard() {
        return TonemapReinhard.__fromPtr__(createTonemapReinhard_1());
    }

    public static void colorChange(Mat mat, Mat mat2, Mat mat3, float f, float f2, float f3) {
        colorChange_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, f2, f3);
    }

    public static void colorChange(Mat mat, Mat mat2, Mat mat3) {
        colorChange_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void decolor(Mat mat, Mat mat2, Mat mat3) {
        decolor_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void denoise_TVL1(List<Mat> list, Mat mat, double d, int i) {
        denoise_TVL1_0(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, d, i);
    }

    public static void denoise_TVL1(List<Mat> list, Mat mat) {
        denoise_TVL1_1(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj);
    }

    public static void detailEnhance(Mat mat, Mat mat2, float f, float f2) {
        detailEnhance_0(mat.nativeObj, mat2.nativeObj, f, f2);
    }

    public static void detailEnhance(Mat mat, Mat mat2) {
        detailEnhance_1(mat.nativeObj, mat2.nativeObj);
    }

    public static void edgePreservingFilter(Mat mat, Mat mat2, int i, float f, float f2) {
        edgePreservingFilter_0(mat.nativeObj, mat2.nativeObj, i, f, f2);
    }

    public static void edgePreservingFilter(Mat mat, Mat mat2) {
        edgePreservingFilter_1(mat.nativeObj, mat2.nativeObj);
    }

    public static void fastNlMeansDenoising(Mat mat, Mat mat2, float f, int i, int i2) {
        fastNlMeansDenoising_0(mat.nativeObj, mat2.nativeObj, f, i, i2);
    }

    public static void fastNlMeansDenoising(Mat mat, Mat mat2) {
        fastNlMeansDenoising_1(mat.nativeObj, mat2.nativeObj);
    }

    public static void fastNlMeansDenoising(Mat mat, Mat mat2, MatOfFloat matOfFloat, int i, int i2, int i3) {
        fastNlMeansDenoising_2(mat.nativeObj, mat2.nativeObj, matOfFloat.nativeObj, i, i2, i3);
    }

    public static void fastNlMeansDenoising(Mat mat, Mat mat2, MatOfFloat matOfFloat) {
        fastNlMeansDenoising_3(mat.nativeObj, mat2.nativeObj, matOfFloat.nativeObj);
    }

    public static void fastNlMeansDenoisingColored(Mat mat, Mat mat2, float f, float f2, int i, int i2) {
        fastNlMeansDenoisingColored_0(mat.nativeObj, mat2.nativeObj, f, f2, i, i2);
    }

    public static void fastNlMeansDenoisingColored(Mat mat, Mat mat2) {
        fastNlMeansDenoisingColored_1(mat.nativeObj, mat2.nativeObj);
    }

    public static void fastNlMeansDenoisingColoredMulti(List<Mat> list, Mat mat, int i, int i2, float f, float f2, int i3, int i4) {
        fastNlMeansDenoisingColoredMulti_0(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, i, i2, f, f2, i3, i4);
    }

    public static void fastNlMeansDenoisingColoredMulti(List<Mat> list, Mat mat, int i, int i2) {
        fastNlMeansDenoisingColoredMulti_1(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, i, i2);
    }

    public static void fastNlMeansDenoisingMulti(List<Mat> list, Mat mat, int i, int i2, float f, int i3, int i4) {
        fastNlMeansDenoisingMulti_0(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, i, i2, f, i3, i4);
    }

    public static void fastNlMeansDenoisingMulti(List<Mat> list, Mat mat, int i, int i2) {
        fastNlMeansDenoisingMulti_1(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, i, i2);
    }

    public static void fastNlMeansDenoisingMulti(List<Mat> list, Mat mat, int i, int i2, MatOfFloat matOfFloat, int i3, int i4, int i5) {
        fastNlMeansDenoisingMulti_2(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, i, i2, matOfFloat.nativeObj, i3, i4, i5);
    }

    public static void fastNlMeansDenoisingMulti(List<Mat> list, Mat mat, int i, int i2, MatOfFloat matOfFloat) {
        fastNlMeansDenoisingMulti_3(Converters.vector_Mat_to_Mat(list).nativeObj, mat.nativeObj, i, i2, matOfFloat.nativeObj);
    }

    public static void illuminationChange(Mat mat, Mat mat2, Mat mat3, float f, float f2) {
        illuminationChange_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, f2);
    }

    public static void illuminationChange(Mat mat, Mat mat2, Mat mat3) {
        illuminationChange_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void inpaint(Mat mat, Mat mat2, Mat mat3, double d, int i) {
        inpaint_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, d, i);
    }

    public static void pencilSketch(Mat mat, Mat mat2, Mat mat3, float f, float f2, float f3) {
        pencilSketch_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, f2, f3);
    }

    public static void pencilSketch(Mat mat, Mat mat2, Mat mat3) {
        pencilSketch_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public static void seamlessClone(Mat mat, Mat mat2, Mat mat3, Point point, Mat mat4, int i) {
        seamlessClone_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, point.x, point.y, mat4.nativeObj, i);
    }

    public static void stylization(Mat mat, Mat mat2, float f, float f2) {
        stylization_0(mat.nativeObj, mat2.nativeObj, f, f2);
    }

    public static void stylization(Mat mat, Mat mat2) {
        stylization_1(mat.nativeObj, mat2.nativeObj);
    }

    public static void textureFlattening(Mat mat, Mat mat2, Mat mat3, float f, float f2, int i) {
        textureFlattening_0(mat.nativeObj, mat2.nativeObj, mat3.nativeObj, f, f2, i);
    }

    public static void textureFlattening(Mat mat, Mat mat2, Mat mat3) {
        textureFlattening_1(mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }
}
