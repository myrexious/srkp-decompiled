package com.labters.documentscanner.libraries;

import android.graphics.Bitmap;
import com.labters.documentscanner.helpers.ImageUtils;
import com.labters.documentscanner.helpers.MathUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/* loaded from: classes3.dex */
public class NativeClass {
    private static final double AREA_LOWER_THRESHOLD = 0.2d;
    private static final double AREA_UPPER_THRESHOLD = 0.98d;
    private static Comparator<MatOfPoint2f> AreaDescendingComparator = null;
    private static final double DOWNSCALE_IMAGE_SIZE = 600.0d;
    private static final int THRESHOLD_LEVEL = 2;

    static {
        System.loadLibrary("opencv_java3");
        AreaDescendingComparator = new Comparator<MatOfPoint2f>() { // from class: com.labters.documentscanner.libraries.NativeClass.1
            @Override // java.util.Comparator
            public int compare(MatOfPoint2f matOfPoint2f, MatOfPoint2f matOfPoint2f2) {
                return (int) Math.ceil(Imgproc.contourArea(matOfPoint2f2) - Imgproc.contourArea(matOfPoint2f));
            }
        };
    }

    public Bitmap getScannedBitmap(Bitmap bitmap, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        PerspectiveTransformation perspectiveTransformation = new PerspectiveTransformation();
        MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
        matOfPoint2f.fromArray(new Point(f, f2), new Point(f3, f4), new Point(f5, f6), new Point(f7, f8));
        return ImageUtils.matToBitmap(perspectiveTransformation.transform(ImageUtils.bitmapToMat(bitmap), matOfPoint2f));
    }

    public MatOfPoint2f getPoint(Bitmap bitmap) {
        Mat bitmapToMat = ImageUtils.bitmapToMat(bitmap);
        double max = DOWNSCALE_IMAGE_SIZE / Math.max(bitmapToMat.width(), bitmapToMat.height());
        Size size = new Size(bitmapToMat.width() * max, bitmapToMat.height() * max);
        Mat mat = new Mat(size, bitmapToMat.type());
        Imgproc.resize(bitmapToMat, mat, size);
        List<MatOfPoint2f> points = getPoints(mat);
        if (points.size() == 0) {
            return null;
        }
        Collections.sort(points, AreaDescendingComparator);
        return MathUtils.scaleRectangle(points.get(0), 1.0d / max);
    }

    public List<MatOfPoint2f> getPoints(Mat mat) {
        int i;
        int i2;
        Mat mat2 = new Mat();
        Imgproc.medianBlur(mat, mat2, 9);
        int i3 = 0;
        Mat mat3 = new Mat(mat2.size(), 0);
        Mat mat4 = new Mat();
        ArrayList<MatOfPoint> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(mat2);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(mat3);
        int rows = mat.rows() * mat.cols();
        int i4 = 0;
        while (i4 < 3) {
            int i5 = 2;
            int[] iArr = new int[2];
            iArr[i3] = i4;
            iArr[1] = i3;
            Core.mixChannels(arrayList3, arrayList4, new MatOfInt(iArr));
            int i6 = i3;
            while (i6 < i5) {
                if (i6 == 0) {
                    i = i6;
                    Imgproc.Canny(mat3, mat4, 10.0d, 20.0d);
                    Imgproc.dilate(mat4, mat4, Mat.ones(new Size(3.0d, 3.0d), i3));
                    i2 = i5;
                } else {
                    i = i6;
                    i2 = i5;
                    Imgproc.threshold(mat3, mat4, ((i + 1) * 255) / i5, 255.0d, 0);
                }
                Imgproc.findContours(mat4, arrayList, new Mat(), 1, i2);
                for (MatOfPoint matOfPoint : arrayList) {
                    MatOfPoint2f matOfPointFloat = MathUtils.toMatOfPointFloat(matOfPoint);
                    MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
                    Imgproc.approxPolyDP(matOfPointFloat, matOfPoint2f, Imgproc.arcLength(matOfPointFloat, true) * 0.02d, true);
                    if (isRectangle(matOfPoint2f, rows)) {
                        arrayList2.add(matOfPoint2f);
                    }
                }
                i6 = i + 1;
                i5 = i2;
                i3 = 0;
            }
            i4++;
            i3 = 0;
        }
        return arrayList2;
    }

    private boolean isRectangle(MatOfPoint2f matOfPoint2f, int i) {
        MatOfPoint matOfPointInt = MathUtils.toMatOfPointInt(matOfPoint2f);
        if (matOfPoint2f.rows() != 4) {
            return false;
        }
        double abs = Math.abs(Imgproc.contourArea(matOfPoint2f));
        double d = i;
        if (abs < AREA_LOWER_THRESHOLD * d || abs > d * AREA_UPPER_THRESHOLD || !Imgproc.isContourConvex(matOfPointInt)) {
            return false;
        }
        Point[] array = matOfPoint2f.toArray();
        double d2 = 0.0d;
        for (int i2 = 2; i2 < 5; i2++) {
            d2 = Math.max(Math.abs(MathUtils.angle(array[i2 % 4], array[i2 - 2], array[i2 - 1])), d2);
        }
        return d2 < 0.3d;
    }
}
