package org.informatika.sirekap.support.templatematching;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

/* loaded from: classes2.dex */
public class CheckerboardCropping {
    public static Mat cropCoordinate(Mat image, List<Point> cropCoordinates, Double aspectRatio) {
        if (cropCoordinates.size() < 4) {
            throw new IllegalArgumentException("There must be at least 4 coordinates to crop.");
        }
        double d = cropCoordinates.get(1).x - cropCoordinates.get(0).x;
        double doubleValue = d / aspectRatio.doubleValue();
        Mat vector_Point2f_to_Mat = Converters.vector_Point2f_to_Mat(cropCoordinates);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Point(0.0d, 0.0d));
        arrayList.add(new Point(d, 0.0d));
        arrayList.add(new Point(d, doubleValue));
        arrayList.add(new Point(0.0d, doubleValue));
        Mat vector_Point2f_to_Mat2 = Converters.vector_Point2f_to_Mat(arrayList);
        Mat perspectiveTransform = Imgproc.getPerspectiveTransform(vector_Point2f_to_Mat, vector_Point2f_to_Mat2);
        vector_Point2f_to_Mat.release();
        vector_Point2f_to_Mat2.release();
        Mat mat = new Mat((int) d, (int) doubleValue, CvType.CV_8UC1);
        Imgproc.warpPerspective(image, mat, perspectiveTransform, new Size(d, doubleValue));
        perspectiveTransform.release();
        return mat;
    }

    private static Rect calculateBoundingRectangle(List<Point> cropCoordinates) {
        return new Rect(new Point(Math.min(Math.min(cropCoordinates.get(0).x, cropCoordinates.get(1).x), Math.min(cropCoordinates.get(2).x, cropCoordinates.get(3).x)), Math.min(Math.min(cropCoordinates.get(0).y, cropCoordinates.get(1).y), Math.min(cropCoordinates.get(2).y, cropCoordinates.get(3).y))), new Point(Math.max(Math.max(cropCoordinates.get(0).x, cropCoordinates.get(1).x), Math.max(cropCoordinates.get(2).x, cropCoordinates.get(3).x)), Math.max(Math.max(cropCoordinates.get(0).y, cropCoordinates.get(1).y), Math.max(cropCoordinates.get(2).y, cropCoordinates.get(3).y))));
    }
}
