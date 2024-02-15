package org.opencv.dnn;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.utils.Converters;

/* loaded from: classes4.dex */
public class Dnn {
    public static final int DNN_BACKEND_DEFAULT = 0;
    public static final int DNN_BACKEND_HALIDE = 1;
    public static final int DNN_BACKEND_INFERENCE_ENGINE = 2;
    public static final int DNN_TARGET_CPU = 0;
    public static final int DNN_TARGET_OPENCL = 1;

    private static native void NMSBoxes_0(long j, long j2, float f, float f2, long j3, float f3, int i);

    private static native void NMSBoxes_1(long j, long j2, float f, float f2, long j3);

    private static native long blobFromImage_0(long j, double d, double d2, double d3, double d4, double d5, double d6, double d7, boolean z, boolean z2);

    private static native long blobFromImage_1(long j);

    private static native long blobFromImages_0(long j, double d, double d2, double d3, double d4, double d5, double d6, double d7, boolean z, boolean z2);

    private static native long blobFromImages_1(long j);

    private static native void imagesFromBlob_0(long j, long j2);

    private static native long readNetFromCaffe_0(String str, String str2);

    private static native long readNetFromCaffe_1(String str);

    private static native long readNetFromDarknet_0(String str, String str2);

    private static native long readNetFromDarknet_1(String str);

    private static native long readNetFromTensorflow_0(String str, String str2);

    private static native long readNetFromTensorflow_1(String str);

    private static native long readNetFromTorch_0(String str, boolean z);

    private static native long readNetFromTorch_1(String str);

    private static native long readTorchBlob_0(String str, boolean z);

    private static native long readTorchBlob_1(String str);

    private static native void shrinkCaffeModel_0(String str, String str2, List<String> list);

    private static native void shrinkCaffeModel_1(String str, String str2);

    public static Mat blobFromImage(Mat mat, double d, Size size, Scalar scalar, boolean z, boolean z2) {
        return new Mat(blobFromImage_0(mat.nativeObj, d, size.width, size.height, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3], z, z2));
    }

    public static Mat blobFromImage(Mat mat) {
        return new Mat(blobFromImage_1(mat.nativeObj));
    }

    public static Mat blobFromImages(List<Mat> list, double d, Size size, Scalar scalar, boolean z, boolean z2) {
        return new Mat(blobFromImages_0(Converters.vector_Mat_to_Mat(list).nativeObj, d, size.width, size.height, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3], z, z2));
    }

    public static Mat blobFromImages(List<Mat> list) {
        return new Mat(blobFromImages_1(Converters.vector_Mat_to_Mat(list).nativeObj));
    }

    public static Mat readTorchBlob(String str, boolean z) {
        return new Mat(readTorchBlob_0(str, z));
    }

    public static Mat readTorchBlob(String str) {
        return new Mat(readTorchBlob_1(str));
    }

    public static Net readNetFromCaffe(String str, String str2) {
        return new Net(readNetFromCaffe_0(str, str2));
    }

    public static Net readNetFromCaffe(String str) {
        return new Net(readNetFromCaffe_1(str));
    }

    public static Net readNetFromDarknet(String str, String str2) {
        return new Net(readNetFromDarknet_0(str, str2));
    }

    public static Net readNetFromDarknet(String str) {
        return new Net(readNetFromDarknet_1(str));
    }

    public static Net readNetFromTensorflow(String str, String str2) {
        return new Net(readNetFromTensorflow_0(str, str2));
    }

    public static Net readNetFromTensorflow(String str) {
        return new Net(readNetFromTensorflow_1(str));
    }

    public static Net readNetFromTorch(String str, boolean z) {
        return new Net(readNetFromTorch_0(str, z));
    }

    public static Net readNetFromTorch(String str) {
        return new Net(readNetFromTorch_1(str));
    }

    public static void NMSBoxes(MatOfRect matOfRect, MatOfFloat matOfFloat, float f, float f2, MatOfInt matOfInt, float f3, int i) {
        NMSBoxes_0(matOfRect.nativeObj, matOfFloat.nativeObj, f, f2, matOfInt.nativeObj, f3, i);
    }

    public static void NMSBoxes(MatOfRect matOfRect, MatOfFloat matOfFloat, float f, float f2, MatOfInt matOfInt) {
        NMSBoxes_1(matOfRect.nativeObj, matOfFloat.nativeObj, f, f2, matOfInt.nativeObj);
    }

    public static void imagesFromBlob(Mat mat, List<Mat> list) {
        Mat mat2 = new Mat();
        imagesFromBlob_0(mat.nativeObj, mat2.nativeObj);
        Converters.Mat_to_vector_Mat(mat2, list);
        mat2.release();
    }

    public static void shrinkCaffeModel(String str, String str2, List<String> list) {
        shrinkCaffeModel_0(str, str2, list);
    }

    public static void shrinkCaffeModel(String str, String str2) {
        shrinkCaffeModel_1(str, str2);
    }
}
