package com.labters.documentscanner.libraries;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/* loaded from: classes3.dex */
public class PerspectiveTransformation {
    private static final String DEBUG_TAG = "PerspectiveTransformation";

    public Mat transform(Mat mat, MatOfPoint2f matOfPoint2f) {
        MatOfPoint2f sortCorners = sortCorners(matOfPoint2f);
        Size rectangleSize = getRectangleSize(sortCorners);
        Mat zeros = Mat.zeros(rectangleSize, mat.type());
        Imgproc.warpPerspective(mat, zeros, Imgproc.getPerspectiveTransform(sortCorners, getOutline(zeros)), rectangleSize);
        return zeros;
    }

    private Size getRectangleSize(MatOfPoint2f matOfPoint2f) {
        Point[] array = matOfPoint2f.toArray();
        double distance = getDistance(array[0], array[1]);
        double distance2 = getDistance(array[1], array[2]);
        return new Size(new Point((distance + getDistance(array[2], array[3])) / 2.0d, (distance2 + getDistance(array[3], array[0])) / 2.0d));
    }

    private double getDistance(Point point, Point point2) {
        double d = point2.x - point.x;
        double d2 = point2.y - point.y;
        return Math.sqrt((d * d) + (d2 * d2));
    }

    private MatOfPoint2f getOutline(Mat mat) {
        Point[] pointArr = {new Point(0.0d, 0.0d), new Point(mat.cols(), 0.0d), new Point(mat.cols(), mat.rows()), new Point(0.0d, mat.rows())};
        MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
        matOfPoint2f.fromArray(pointArr);
        return matOfPoint2f;
    }

    private MatOfPoint2f sortCorners(MatOfPoint2f matOfPoint2f) {
        Point massCenter = getMassCenter(matOfPoint2f);
        List<Point> list = matOfPoint2f.toList();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Point point : list) {
            if (point.y < massCenter.y) {
                arrayList.add(point);
            } else {
                arrayList2.add(point);
            }
        }
        Point point2 = (Point) (((Point) arrayList.get(0)).x > ((Point) arrayList.get(1)).x ? arrayList.get(1) : arrayList.get(0));
        Point point3 = (Point) (((Point) arrayList.get(0)).x > ((Point) arrayList.get(1)).x ? arrayList.get(0) : arrayList.get(1));
        Point point4 = (Point) (((Point) arrayList2.get(0)).x > ((Point) arrayList2.get(1)).x ? arrayList2.get(1) : arrayList2.get(0));
        Object obj = ((Point) arrayList2.get(0)).x > ((Point) arrayList2.get(1)).x ? arrayList2.get(0) : arrayList2.get(1);
        MatOfPoint2f matOfPoint2f2 = new MatOfPoint2f();
        matOfPoint2f2.fromArray(point2, point3, (Point) obj, point4);
        return matOfPoint2f2;
    }

    private Point getMassCenter(MatOfPoint2f matOfPoint2f) {
        List<Point> list = matOfPoint2f.toList();
        int size = list.size();
        double d = 0.0d;
        double d2 = 0.0d;
        for (Point point : list) {
            d += point.x;
            d2 += point.y;
        }
        double d3 = size;
        return new Point(d / d3, d2 / d3);
    }
}
