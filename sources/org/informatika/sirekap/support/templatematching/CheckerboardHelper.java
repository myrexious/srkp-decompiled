package org.informatika.sirekap.support.templatematching;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/* loaded from: classes2.dex */
public class CheckerboardHelper {
    public static void visualizeMatches(Mat image, Mat template, List<Point> points, Scalar color) {
        for (Point point : points) {
            Rect rect = new Rect(point, new Size(template.cols(), template.rows()));
            Imgproc.rectangle(image, rect.tl(), rect.br(), color, 2);
        }
        displayResizedImage(image);
    }

    public static void displayResizedImage(Mat image) {
        double cols;
        double d;
        double d2 = 800;
        int cols2 = (int) (d2 / (image.cols() / image.rows()));
        Imgproc.resize(image, image, new Size(d2, cols2));
        if (cols2 > 600) {
            Imgproc.resize(image, image, new Size((int) (cols * d), 600));
        }
    }

    public static Mat getTemplate(int width, int height) {
        Mat zeros = Mat.zeros(height, width, 0);
        int i = height / 2;
        for (int i2 = i; i2 < height; i2++) {
            for (int i3 = 0; i3 < width / 2; i3++) {
                zeros.put(i2, i3, 255.0d);
            }
        }
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = width / 2; i5 < width; i5++) {
                zeros.put(i4, i5, 255.0d);
            }
        }
        return zeros;
    }

    public static Mat applyTemplateMatching(Mat image, Mat template) {
        Mat mat = new Mat();
        Imgproc.matchTemplate(image, template, mat, 5);
        return mat;
    }

    public static List<Point> getTopLocations(Mat image, Mat result, Mat template) {
        return (List) filterMatches(image, result, template).stream().limit(4L).collect(Collectors.toList());
    }

    private static List<Point> getAllPointsAboveThreshold(Mat res) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < res.rows(); i++) {
            for (int i2 = 0; i2 < res.cols(); i2++) {
                if (res.get(i, i2)[0] >= 0.8d) {
                    arrayList.add(new Point(i2, i));
                }
            }
        }
        return arrayList;
    }

    private static List<Point> filterMatches(Mat image, Mat res, Mat template) {
        List<Point> allPointsAboveThreshold = getAllPointsAboveThreshold(res);
        visualizeMatches(image.clone(), template, allPointsAboveThreshold, new Scalar(0.0d, 0.0d, 255.0d));
        return removeDuplicates(allPointsAboveThreshold, template);
    }

    private static List<Point> removeDuplicates(List<Point> points, Mat template) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        for (Point point : points) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (calculateIou(point, (Point) it.next(), new Size(template.cols(), template.rows())) > 0.5d) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                arrayList.add(point);
            }
            if (arrayList.size() >= 4) {
                break;
            }
        }
        return arrayList;
    }

    private static double calculateIou(Point box1, Point box2, Size shape) {
        Rect convertToBbox = convertToBbox(box1, shape);
        Rect convertToBbox2 = convertToBbox(box2, shape);
        int max = Math.max(convertToBbox.x, convertToBbox2.x);
        int max2 = Math.max(convertToBbox.y, convertToBbox2.y);
        int min = Math.min(convertToBbox.x + convertToBbox.width, convertToBbox2.x + convertToBbox2.width);
        int min2 = Math.min(convertToBbox.y + convertToBbox.height, convertToBbox2.y + convertToBbox2.height);
        int max3 = Math.max(0, min - max);
        int max4 = Math.max(0, min2 - max2);
        double d = (convertToBbox.width * convertToBbox.height) + (convertToBbox2.width * convertToBbox2.height);
        double d2 = max3 * max4;
        return d2 / (d - d2);
    }

    private static Rect convertToBbox(Point coord, Size shape) {
        return new Rect(coord, new Size(shape.width, shape.height));
    }
}
