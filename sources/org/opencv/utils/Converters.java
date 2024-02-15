package org.opencv.utils;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.opencv.core.CvType;
import org.opencv.core.DMatch;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfPoint3f;
import org.opencv.core.Point;
import org.opencv.core.Point3;
import org.opencv.core.Rect;
import org.opencv.core.Rect2d;

/* loaded from: classes4.dex */
public class Converters {
    public static Mat vector_Point_to_Mat(List<Point> list) {
        return vector_Point_to_Mat(list, 4);
    }

    public static Mat vector_Point2f_to_Mat(List<Point> list) {
        return vector_Point_to_Mat(list, 5);
    }

    public static Mat vector_Point2d_to_Mat(List<Point> list) {
        return vector_Point_to_Mat(list, 6);
    }

    public static Mat vector_Point_to_Mat(List<Point> list, int i) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            if (i == 4) {
                Mat mat = new Mat(size, 1, CvType.CV_32SC2);
                int[] iArr = new int[size * 2];
                for (int i2 = 0; i2 < size; i2++) {
                    Point point = list.get(i2);
                    int i3 = i2 * 2;
                    iArr[i3] = (int) point.x;
                    iArr[i3 + 1] = (int) point.y;
                }
                mat.put(0, 0, iArr);
                return mat;
            } else if (i == 5) {
                Mat mat2 = new Mat(size, 1, CvType.CV_32FC2);
                float[] fArr = new float[size * 2];
                for (int i4 = 0; i4 < size; i4++) {
                    Point point2 = list.get(i4);
                    int i5 = i4 * 2;
                    fArr[i5] = (float) point2.x;
                    fArr[i5 + 1] = (float) point2.y;
                }
                mat2.put(0, 0, fArr);
                return mat2;
            } else if (i == 6) {
                Mat mat3 = new Mat(size, 1, CvType.CV_64FC2);
                double[] dArr = new double[size * 2];
                for (int i6 = 0; i6 < size; i6++) {
                    Point point3 = list.get(i6);
                    int i7 = i6 * 2;
                    dArr[i7] = point3.x;
                    dArr[i7 + 1] = point3.y;
                }
                mat3.put(0, 0, dArr);
                return mat3;
            } else {
                throw new IllegalArgumentException("'typeDepth' can be CV_32S, CV_32F or CV_64F");
            }
        }
        return new Mat();
    }

    public static Mat vector_Point3i_to_Mat(List<Point3> list) {
        return vector_Point3_to_Mat(list, 4);
    }

    public static Mat vector_Point3f_to_Mat(List<Point3> list) {
        return vector_Point3_to_Mat(list, 5);
    }

    public static Mat vector_Point3d_to_Mat(List<Point3> list) {
        return vector_Point3_to_Mat(list, 6);
    }

    public static Mat vector_Point3_to_Mat(List<Point3> list, int i) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            if (i == 4) {
                Mat mat = new Mat(size, 1, CvType.CV_32SC3);
                int[] iArr = new int[size * 3];
                for (int i2 = 0; i2 < size; i2++) {
                    Point3 point3 = list.get(i2);
                    int i3 = i2 * 3;
                    iArr[i3] = (int) point3.x;
                    iArr[i3 + 1] = (int) point3.y;
                    iArr[i3 + 2] = (int) point3.z;
                }
                mat.put(0, 0, iArr);
                return mat;
            } else if (i == 5) {
                Mat mat2 = new Mat(size, 1, CvType.CV_32FC3);
                float[] fArr = new float[size * 3];
                for (int i4 = 0; i4 < size; i4++) {
                    Point3 point32 = list.get(i4);
                    int i5 = i4 * 3;
                    fArr[i5] = (float) point32.x;
                    fArr[i5 + 1] = (float) point32.y;
                    fArr[i5 + 2] = (float) point32.z;
                }
                mat2.put(0, 0, fArr);
                return mat2;
            } else if (i == 6) {
                Mat mat3 = new Mat(size, 1, CvType.CV_64FC3);
                double[] dArr = new double[size * 3];
                for (int i6 = 0; i6 < size; i6++) {
                    Point3 point33 = list.get(i6);
                    int i7 = i6 * 3;
                    dArr[i7] = point33.x;
                    dArr[i7 + 1] = point33.y;
                    dArr[i7 + 2] = point33.z;
                }
                mat3.put(0, 0, dArr);
                return mat3;
            } else {
                throw new IllegalArgumentException("'typeDepth' can be CV_32S, CV_32F or CV_64F");
            }
        }
        return new Mat();
    }

    public static void Mat_to_vector_Point2f(Mat mat, List<Point> list) {
        Mat_to_vector_Point(mat, list);
    }

    public static void Mat_to_vector_Point2d(Mat mat, List<Point> list) {
        Mat_to_vector_Point(mat, list);
    }

    public static void Mat_to_vector_Point(Mat mat, List<Point> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        int rows = mat.rows();
        int type = mat.type();
        if (mat.cols() != 1) {
            throw new IllegalArgumentException("Input Mat should have one column\n" + mat);
        }
        list.clear();
        int i = 0;
        if (type == CvType.CV_32SC2) {
            int[] iArr = new int[rows * 2];
            mat.get(0, 0, iArr);
            while (i < rows) {
                int i2 = i * 2;
                list.add(new Point(iArr[i2], iArr[i2 + 1]));
                i++;
            }
        } else if (type == CvType.CV_32FC2) {
            float[] fArr = new float[rows * 2];
            mat.get(0, 0, fArr);
            while (i < rows) {
                int i3 = i * 2;
                list.add(new Point(fArr[i3], fArr[i3 + 1]));
                i++;
            }
        } else if (type == CvType.CV_64FC2) {
            double[] dArr = new double[rows * 2];
            mat.get(0, 0, dArr);
            while (i < rows) {
                int i4 = i * 2;
                list.add(new Point(dArr[i4], dArr[i4 + 1]));
                i++;
            }
        } else {
            throw new IllegalArgumentException("Input Mat should be of CV_32SC2, CV_32FC2 or CV_64FC2 type\n" + mat);
        }
    }

    public static void Mat_to_vector_Point3i(Mat mat, List<Point3> list) {
        Mat_to_vector_Point3(mat, list);
    }

    public static void Mat_to_vector_Point3f(Mat mat, List<Point3> list) {
        Mat_to_vector_Point3(mat, list);
    }

    public static void Mat_to_vector_Point3d(Mat mat, List<Point3> list) {
        Mat_to_vector_Point3(mat, list);
    }

    public static void Mat_to_vector_Point3(Mat mat, List<Point3> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        int rows = mat.rows();
        int type = mat.type();
        if (mat.cols() != 1) {
            throw new IllegalArgumentException("Input Mat should have one column\n" + mat);
        }
        list.clear();
        int i = 0;
        if (type == CvType.CV_32SC3) {
            int[] iArr = new int[rows * 3];
            mat.get(0, 0, iArr);
            while (i < rows) {
                int i2 = i * 3;
                list.add(new Point3(iArr[i2], iArr[i2 + 1], iArr[i2 + 2]));
                i++;
            }
        } else if (type == CvType.CV_32FC3) {
            float[] fArr = new float[rows * 3];
            mat.get(0, 0, fArr);
            while (i < rows) {
                int i3 = i * 3;
                list.add(new Point3(fArr[i3], fArr[i3 + 1], fArr[i3 + 2]));
                i++;
            }
        } else if (type == CvType.CV_64FC3) {
            double[] dArr = new double[rows * 3];
            mat.get(0, 0, dArr);
            while (i < rows) {
                int i4 = i * 3;
                list.add(new Point3(dArr[i4], dArr[i4 + 1], dArr[i4 + 2]));
                i++;
            }
        } else {
            throw new IllegalArgumentException("Input Mat should be of CV_32SC3, CV_32FC3 or CV_64FC3 type\n" + mat);
        }
    }

    public static Mat vector_Mat_to_Mat(List<Mat> list) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            Mat mat = new Mat(size, 1, CvType.CV_32SC2);
            int[] iArr = new int[size * 2];
            for (int i = 0; i < size; i++) {
                long j = list.get(i).nativeObj;
                int i2 = i * 2;
                iArr[i2] = (int) (j >> 32);
                iArr[i2 + 1] = (int) (j & (-1));
            }
            mat.put(0, 0, iArr);
            return mat;
        }
        return new Mat();
    }

    public static void Mat_to_vector_Mat(Mat mat, List<Mat> list) {
        if (list == null) {
            throw new IllegalArgumentException("mats == null");
        }
        int rows = mat.rows();
        if (CvType.CV_32SC2 != mat.type() || mat.cols() != 1) {
            throw new IllegalArgumentException("CvType.CV_32SC2 != m.type() ||  m.cols()!=1\n" + mat);
        }
        list.clear();
        int[] iArr = new int[rows * 2];
        mat.get(0, 0, iArr);
        for (int i = 0; i < rows; i++) {
            int i2 = i * 2;
            list.add(new Mat((iArr[i2] << 32) | (iArr[i2 + 1] & BodyPartID.bodyIdMax)));
        }
    }

    public static Mat vector_float_to_Mat(List<Float> list) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            Mat mat = new Mat(size, 1, CvType.CV_32FC1);
            float[] fArr = new float[size];
            for (int i = 0; i < size; i++) {
                fArr[i] = list.get(i).floatValue();
            }
            mat.put(0, 0, fArr);
            return mat;
        }
        return new Mat();
    }

    public static void Mat_to_vector_float(Mat mat, List<Float> list) {
        if (list == null) {
            throw new IllegalArgumentException("fs == null");
        }
        int rows = mat.rows();
        if (CvType.CV_32FC1 != mat.type() || mat.cols() != 1) {
            throw new IllegalArgumentException("CvType.CV_32FC1 != m.type() ||  m.cols()!=1\n" + mat);
        }
        list.clear();
        float[] fArr = new float[rows];
        mat.get(0, 0, fArr);
        for (int i = 0; i < rows; i++) {
            list.add(Float.valueOf(fArr[i]));
        }
    }

    public static Mat vector_uchar_to_Mat(List<Byte> list) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            Mat mat = new Mat(size, 1, CvType.CV_8UC1);
            byte[] bArr = new byte[size];
            for (int i = 0; i < size; i++) {
                bArr[i] = list.get(i).byteValue();
            }
            mat.put(0, 0, bArr);
            return mat;
        }
        return new Mat();
    }

    public static void Mat_to_vector_uchar(Mat mat, List<Byte> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        int rows = mat.rows();
        if (CvType.CV_8UC1 != mat.type() || mat.cols() != 1) {
            throw new IllegalArgumentException("CvType.CV_8UC1 != m.type() ||  m.cols()!=1\n" + mat);
        }
        list.clear();
        byte[] bArr = new byte[rows];
        mat.get(0, 0, bArr);
        for (int i = 0; i < rows; i++) {
            list.add(Byte.valueOf(bArr[i]));
        }
    }

    public static Mat vector_char_to_Mat(List<Byte> list) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            Mat mat = new Mat(size, 1, CvType.CV_8SC1);
            byte[] bArr = new byte[size];
            for (int i = 0; i < size; i++) {
                bArr[i] = list.get(i).byteValue();
            }
            mat.put(0, 0, bArr);
            return mat;
        }
        return new Mat();
    }

    public static Mat vector_int_to_Mat(List<Integer> list) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            Mat mat = new Mat(size, 1, CvType.CV_32SC1);
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                iArr[i] = list.get(i).intValue();
            }
            mat.put(0, 0, iArr);
            return mat;
        }
        return new Mat();
    }

    public static void Mat_to_vector_int(Mat mat, List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("is == null");
        }
        int rows = mat.rows();
        if (CvType.CV_32SC1 != mat.type() || mat.cols() != 1) {
            throw new IllegalArgumentException("CvType.CV_32SC1 != m.type() ||  m.cols()!=1\n" + mat);
        }
        list.clear();
        int[] iArr = new int[rows];
        mat.get(0, 0, iArr);
        for (int i = 0; i < rows; i++) {
            list.add(Integer.valueOf(iArr[i]));
        }
    }

    public static void Mat_to_vector_char(Mat mat, List<Byte> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        int rows = mat.rows();
        if (CvType.CV_8SC1 != mat.type() || mat.cols() != 1) {
            throw new IllegalArgumentException("CvType.CV_8SC1 != m.type() ||  m.cols()!=1\n" + mat);
        }
        list.clear();
        byte[] bArr = new byte[rows];
        mat.get(0, 0, bArr);
        for (int i = 0; i < rows; i++) {
            list.add(Byte.valueOf(bArr[i]));
        }
    }

    public static Mat vector_Rect_to_Mat(List<Rect> list) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            Mat mat = new Mat(size, 1, CvType.CV_32SC4);
            int[] iArr = new int[size * 4];
            for (int i = 0; i < size; i++) {
                Rect rect = list.get(i);
                int i2 = i * 4;
                iArr[i2] = rect.x;
                iArr[i2 + 1] = rect.y;
                iArr[i2 + 2] = rect.width;
                iArr[i2 + 3] = rect.height;
            }
            mat.put(0, 0, iArr);
            return mat;
        }
        return new Mat();
    }

    public static void Mat_to_vector_Rect(Mat mat, List<Rect> list) {
        if (list == null) {
            throw new IllegalArgumentException("rs == null");
        }
        int rows = mat.rows();
        if (CvType.CV_32SC4 != mat.type() || mat.cols() != 1) {
            throw new IllegalArgumentException("CvType.CV_32SC4 != m.type() ||  m.rows()!=1\n" + mat);
        }
        list.clear();
        int[] iArr = new int[rows * 4];
        mat.get(0, 0, iArr);
        for (int i = 0; i < rows; i++) {
            int i2 = i * 4;
            list.add(new Rect(iArr[i2], iArr[i2 + 1], iArr[i2 + 2], iArr[i2 + 3]));
        }
    }

    public static Mat vector_Rect2d_to_Mat(List<Rect2d> list) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            Mat mat = new Mat(size, 1, CvType.CV_64FC4);
            double[] dArr = new double[size * 4];
            for (int i = 0; i < size; i++) {
                Rect2d rect2d = list.get(i);
                int i2 = i * 4;
                dArr[i2] = rect2d.x;
                dArr[i2 + 1] = rect2d.y;
                dArr[i2 + 2] = rect2d.width;
                dArr[i2 + 3] = rect2d.height;
            }
            mat.put(0, 0, dArr);
            return mat;
        }
        return new Mat();
    }

    public static void Mat_to_vector_Rect2d(Mat mat, List<Rect2d> list) {
        if (list == null) {
            throw new IllegalArgumentException("rs == null");
        }
        int rows = mat.rows();
        if (CvType.CV_64FC4 != mat.type() || mat.cols() != 1) {
            throw new IllegalArgumentException("CvType.CV_64FC4 != m.type() ||  m.rows()!=1\n" + mat);
        }
        list.clear();
        double[] dArr = new double[rows * 4];
        mat.get(0, 0, dArr);
        for (int i = 0; i < rows; i++) {
            int i2 = i * 4;
            list.add(new Rect2d(dArr[i2], dArr[i2 + 1], dArr[i2 + 2], dArr[i2 + 3]));
        }
    }

    public static Mat vector_KeyPoint_to_Mat(List<KeyPoint> list) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            Mat mat = new Mat(size, 1, CvType.CV_64FC(7));
            double[] dArr = new double[size * 7];
            for (int i = 0; i < size; i++) {
                KeyPoint keyPoint = list.get(i);
                int i2 = i * 7;
                dArr[i2] = keyPoint.pt.x;
                dArr[i2 + 1] = keyPoint.pt.y;
                dArr[i2 + 2] = keyPoint.size;
                dArr[i2 + 3] = keyPoint.angle;
                dArr[i2 + 4] = keyPoint.response;
                dArr[i2 + 5] = keyPoint.octave;
                dArr[i2 + 6] = keyPoint.class_id;
            }
            mat.put(0, 0, dArr);
            return mat;
        }
        return new Mat();
    }

    public static void Mat_to_vector_KeyPoint(Mat mat, List<KeyPoint> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        int rows = mat.rows();
        if (CvType.CV_64FC(7) != mat.type() || mat.cols() != 1) {
            throw new IllegalArgumentException("CvType.CV_64FC(7) != m.type() ||  m.cols()!=1\n" + mat);
        }
        list.clear();
        double[] dArr = new double[rows * 7];
        mat.get(0, 0, dArr);
        for (int i = 0; i < rows; i++) {
            int i2 = i * 7;
            list.add(new KeyPoint((float) dArr[i2], (float) dArr[i2 + 1], (float) dArr[i2 + 2], (float) dArr[i2 + 3], (float) dArr[i2 + 4], (int) dArr[i2 + 5], (int) dArr[i2 + 6]));
        }
    }

    public static Mat vector_vector_Point_to_Mat(List<MatOfPoint> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) > 0) {
            for (MatOfPoint matOfPoint : list) {
                list2.add(matOfPoint);
            }
            return vector_Mat_to_Mat(list2);
        }
        return new Mat();
    }

    public static void Mat_to_vector_vector_Point(Mat mat, List<MatOfPoint> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        if (mat == null) {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
        ArrayList<Mat> arrayList = new ArrayList(mat.rows());
        Mat_to_vector_Mat(mat, arrayList);
        for (Mat mat2 : arrayList) {
            list.add(new MatOfPoint(mat2));
            mat2.release();
        }
        arrayList.clear();
    }

    public static void Mat_to_vector_vector_Point2f(Mat mat, List<MatOfPoint2f> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        if (mat == null) {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
        ArrayList<Mat> arrayList = new ArrayList(mat.rows());
        Mat_to_vector_Mat(mat, arrayList);
        for (Mat mat2 : arrayList) {
            list.add(new MatOfPoint2f(mat2));
            mat2.release();
        }
        arrayList.clear();
    }

    public static Mat vector_vector_Point2f_to_Mat(List<MatOfPoint2f> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) > 0) {
            for (MatOfPoint2f matOfPoint2f : list) {
                list2.add(matOfPoint2f);
            }
            return vector_Mat_to_Mat(list2);
        }
        return new Mat();
    }

    public static void Mat_to_vector_vector_Point3f(Mat mat, List<MatOfPoint3f> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        if (mat == null) {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
        ArrayList<Mat> arrayList = new ArrayList(mat.rows());
        Mat_to_vector_Mat(mat, arrayList);
        for (Mat mat2 : arrayList) {
            list.add(new MatOfPoint3f(mat2));
            mat2.release();
        }
        arrayList.clear();
    }

    public static Mat vector_vector_Point3f_to_Mat(List<MatOfPoint3f> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) > 0) {
            for (MatOfPoint3f matOfPoint3f : list) {
                list2.add(matOfPoint3f);
            }
            return vector_Mat_to_Mat(list2);
        }
        return new Mat();
    }

    public static Mat vector_vector_KeyPoint_to_Mat(List<MatOfKeyPoint> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) > 0) {
            for (MatOfKeyPoint matOfKeyPoint : list) {
                list2.add(matOfKeyPoint);
            }
            return vector_Mat_to_Mat(list2);
        }
        return new Mat();
    }

    public static void Mat_to_vector_vector_KeyPoint(Mat mat, List<MatOfKeyPoint> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        if (mat == null) {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
        ArrayList<Mat> arrayList = new ArrayList(mat.rows());
        Mat_to_vector_Mat(mat, arrayList);
        for (Mat mat2 : arrayList) {
            list.add(new MatOfKeyPoint(mat2));
            mat2.release();
        }
        arrayList.clear();
    }

    public static Mat vector_double_to_Mat(List<Double> list) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            Mat mat = new Mat(size, 1, CvType.CV_64FC1);
            double[] dArr = new double[size];
            for (int i = 0; i < size; i++) {
                dArr[i] = list.get(i).doubleValue();
            }
            mat.put(0, 0, dArr);
            return mat;
        }
        return new Mat();
    }

    public static void Mat_to_vector_double(Mat mat, List<Double> list) {
        if (list == null) {
            throw new IllegalArgumentException("ds == null");
        }
        int rows = mat.rows();
        if (CvType.CV_64FC1 != mat.type() || mat.cols() != 1) {
            throw new IllegalArgumentException("CvType.CV_64FC1 != m.type() ||  m.cols()!=1\n" + mat);
        }
        list.clear();
        double[] dArr = new double[rows];
        mat.get(0, 0, dArr);
        for (int i = 0; i < rows; i++) {
            list.add(Double.valueOf(dArr[i]));
        }
    }

    public static Mat vector_DMatch_to_Mat(List<DMatch> list) {
        int size = list != null ? list.size() : 0;
        if (size > 0) {
            Mat mat = new Mat(size, 1, CvType.CV_64FC4);
            double[] dArr = new double[size * 4];
            for (int i = 0; i < size; i++) {
                DMatch dMatch = list.get(i);
                int i2 = i * 4;
                dArr[i2] = dMatch.queryIdx;
                dArr[i2 + 1] = dMatch.trainIdx;
                dArr[i2 + 2] = dMatch.imgIdx;
                dArr[i2 + 3] = dMatch.distance;
            }
            mat.put(0, 0, dArr);
            return mat;
        }
        return new Mat();
    }

    public static void Mat_to_vector_DMatch(Mat mat, List<DMatch> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        int rows = mat.rows();
        if (CvType.CV_64FC4 != mat.type() || mat.cols() != 1) {
            throw new IllegalArgumentException("CvType.CV_64FC4 != m.type() ||  m.cols()!=1\n" + mat);
        }
        list.clear();
        double[] dArr = new double[rows * 4];
        mat.get(0, 0, dArr);
        for (int i = 0; i < rows; i++) {
            int i2 = i * 4;
            list.add(new DMatch((int) dArr[i2], (int) dArr[i2 + 1], (int) dArr[i2 + 2], (float) dArr[i2 + 3]));
        }
    }

    public static Mat vector_vector_DMatch_to_Mat(List<MatOfDMatch> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) > 0) {
            for (MatOfDMatch matOfDMatch : list) {
                list2.add(matOfDMatch);
            }
            return vector_Mat_to_Mat(list2);
        }
        return new Mat();
    }

    public static void Mat_to_vector_vector_DMatch(Mat mat, List<MatOfDMatch> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        if (mat == null) {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
        ArrayList<Mat> arrayList = new ArrayList(mat.rows());
        Mat_to_vector_Mat(mat, arrayList);
        list.clear();
        for (Mat mat2 : arrayList) {
            list.add(new MatOfDMatch(mat2));
            mat2.release();
        }
        arrayList.clear();
    }

    public static Mat vector_vector_char_to_Mat(List<MatOfByte> list, List<Mat> list2) {
        if ((list != null ? list.size() : 0) > 0) {
            for (MatOfByte matOfByte : list) {
                list2.add(matOfByte);
            }
            return vector_Mat_to_Mat(list2);
        }
        return new Mat();
    }

    public static void Mat_to_vector_vector_char(Mat mat, List<List<Byte>> list) {
        if (list == null) {
            throw new IllegalArgumentException("Output List can't be null");
        }
        if (mat == null) {
            throw new IllegalArgumentException("Input Mat can't be null");
        }
        ArrayList<Mat> arrayList = new ArrayList(mat.rows());
        Mat_to_vector_Mat(mat, arrayList);
        for (Mat mat2 : arrayList) {
            ArrayList arrayList2 = new ArrayList();
            Mat_to_vector_char(mat2, arrayList2);
            list.add(arrayList2);
            mat2.release();
        }
        arrayList.clear();
    }
}
