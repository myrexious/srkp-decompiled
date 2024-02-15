package com.labters.documentscanner.helpers;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/* loaded from: classes3.dex */
public class ImageUtils {
    public static Bitmap rotateBitmap(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(90.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Mat bitmapToMat(Bitmap bitmap) {
        Mat mat = new Mat(bitmap.getHeight(), bitmap.getWidth(), 0, new Scalar(4.0d));
        Utils.bitmapToMat(bitmap.copy(Bitmap.Config.ARGB_8888, true), mat);
        return mat;
    }

    public static Bitmap matToBitmap(Mat mat) {
        Bitmap createBitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat, createBitmap);
        return createBitmap;
    }
}
