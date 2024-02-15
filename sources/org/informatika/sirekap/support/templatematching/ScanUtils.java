package org.informatika.sirekap.support.templatematching;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.support.BitmapUtilsKt;
import org.informatika.sirekap.support.MathUtils;
import org.informatika.sirekap.support.apriltag.ApriltagDetection;
import org.informatika.sirekap.support.apriltag.ApriltagNative;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.visp.core.VpImagePoint;
import org.visp.core.VpImageUChar;
import org.visp.detection.VpDetectorAprilTag;

/* compiled from: ScanUtils.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015J$\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000f2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ.\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000f2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0002J.\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&J\"\u0010'\u001a\b\u0018\u00010(R\u00020)2\b\u0010*\u001a\u0004\u0018\u00010)2\n\u0010+\u001a\u00060(R\u00020)J&\u0010,\u001a\b\u0018\u00010(R\u00020)2\b\u0010*\u001a\u0004\u0018\u00010)2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0007J\b\u0010/\u001a\u00020&H\u0002J\b\u00100\u001a\u00020&H\u0002J\u0016\u00101\u001a\u00020&2\u000e\u00102\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001fJB\u00103\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f2\b\u00104\u001a\u0004\u0018\u00010\u00152\u0006\u00105\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\"2\b\u00107\u001a\u0004\u0018\u00010\u00152\u0006\u0010%\u001a\u00020&J(\u00108\u001a\u0004\u0018\u0001092\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\n2\u0006\u0010;\u001a\u00020\nJ\u001c\u0010<\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0002J4\u0010>\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020?0\u000f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010@\u001a\u00020A2\u0006\u0010\u0018\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lorg/informatika/sirekap/support/templatematching/ScanUtils;", "", "()V", "APRIL_TAG_BLUR_SIGMA", "", "APRIL_TAG_DECIMATE_FACTOR", "APRIL_TAG_ERROR_BITS", "", "APRIL_TAG_NTHREADS", "APRIL_TAG_TAG_FAMILY", "", "MARKER_TEMPLATE_IMG_SAVE_PATH", "MIN_SIM_THRESHOLD", "TAG", "aprilTagNative", "Lkotlin/Pair;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "cameraWidth", "cameraHeight", "convertToGrayScale", "Lorg/opencv/core/Mat;", "colorImage", "detect", "mat", "bitmap", "Landroid/graphics/Bitmap;", "detectAprilTagId", "estimationMethod", "tagFamily", "detectEdgeMarker", "", "Lorg/opencv/core/Point;", "context", "Landroid/content/Context;", "inputMat", "templateMark", "savePic", "", "determinePictureSize", "Landroid/hardware/Camera$Size;", "Landroid/hardware/Camera;", "camera", "previewSize", "getOptimalPreviewSize", "w", "h", "initAprilTagNative", "initVisp", "isScanPointsValid", "points", "matchCheckerboard", "imageGray", "templateWidth", "templateHeight", "originalMat", "saveMatToGallery", "Landroid/net/Uri;", "filename", "watermarkText", "sortPoints", "src", "templateMatching", "", "toVpImageUChar", "Lorg/visp/core/VpImageUChar;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ScanUtils {
    private static final double APRIL_TAG_BLUR_SIGMA = 0.0d;
    private static final double APRIL_TAG_DECIMATE_FACTOR = 8.0d;
    private static final int APRIL_TAG_ERROR_BITS = 0;
    private static final int APRIL_TAG_NTHREADS = 8;
    private static final String APRIL_TAG_TAG_FAMILY = "tag36h10";
    public static final ScanUtils INSTANCE = new ScanUtils();
    private static final String MARKER_TEMPLATE_IMG_SAVE_PATH = "DCIM/Sirekap2024 Vision Annotation";
    private static final double MIN_SIM_THRESHOLD = 0.35d;
    private static final String TAG = "ScanUtils";

    private ScanUtils() {
    }

    private final boolean initVisp() {
        try {
            System.loadLibrary("visp_java3");
            return true;
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            return false;
        } catch (UnsatisfiedLinkError e2) {
            FirebaseCrashlytics.getInstance().recordException(e2);
            return false;
        }
    }

    private final boolean initAprilTagNative() {
        try {
            ApriltagNative.apriltag_init(APRIL_TAG_TAG_FAMILY, 0, APRIL_TAG_DECIMATE_FACTOR, 0.0d, 8);
            return true;
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            return false;
        } catch (UnsatisfiedLinkError e2) {
            FirebaseCrashlytics.getInstance().recordException(e2);
            return false;
        }
    }

    public final Pair<Integer, Double> detect(Mat mat, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(mat, "mat");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        try {
            if (initVisp()) {
                return detectAprilTagId(mat, 0, 1);
            }
            if (initAprilTagNative()) {
                return aprilTagNative(BitmapUtilsKt.convertRGBtoYV12(bitmap), bitmap.getWidth(), bitmap.getHeight());
            }
            return null;
        } catch (Exception e) {
            Log.wtf(TAG, e.getMessage());
            return null;
        }
    }

    public final Camera.Size determinePictureSize(Camera camera, Camera.Size previewSize) {
        Intrinsics.checkNotNullParameter(previewSize, "previewSize");
        Camera.Size size = null;
        if (camera == null) {
            return null;
        }
        List<Camera.Size> pictureSizeList = camera.getParameters().getSupportedPictureSizes();
        Intrinsics.checkNotNullExpressionValue(pictureSizeList, "pictureSizeList");
        final ScanUtils$determinePictureSize$1 scanUtils$determinePictureSize$1 = new Function2<Camera.Size, Camera.Size, Integer>() { // from class: org.informatika.sirekap.support.templatematching.ScanUtils$determinePictureSize$1
            @Override // kotlin.jvm.functions.Function2
            public final Integer invoke(Camera.Size size1, Camera.Size size2) {
                Intrinsics.checkNotNullParameter(size1, "size1");
                Intrinsics.checkNotNullParameter(size2, "size2");
                return Integer.valueOf(Double.compare(Math.sqrt((size2.width * size2.width) + (size2.height * size2.height)), Math.sqrt((size1.width * size1.width) + (size1.height * size1.height))));
            }
        };
        CollectionsKt.sortWith(pictureSizeList, new Comparator() { // from class: org.informatika.sirekap.support.templatematching.ScanUtils$$ExternalSyntheticLambda3
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int determinePictureSize$lambda$0;
                determinePictureSize$lambda$0 = ScanUtils.determinePictureSize$lambda$0(Function2.this, obj, obj2);
                return determinePictureSize$lambda$0;
            }
        });
        float f = previewSize.width / previewSize.height;
        float f2 = Float.MAX_VALUE;
        for (Camera.Size size2 : pictureSizeList) {
            float abs = Math.abs(f - (size2.width / size2.height));
            if (abs < f2) {
                size = size2;
                f2 = abs;
            }
            if (MathUtils.INSTANCE.areFloatsEqual(abs, 0.0d)) {
                break;
            }
        }
        return size;
    }

    public static final int determinePictureSize$lambda$0(Function2 tmp0, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(obj, obj2)).intValue();
    }

    public final Camera.Size getOptimalPreviewSize(Camera camera, int i, int i2) {
        if (camera == null) {
            return null;
        }
        final double d = i2 / i;
        List<Camera.Size> previewSizeList = camera.getParameters().getSupportedPreviewSizes();
        Intrinsics.checkNotNullExpressionValue(previewSizeList, "previewSizeList");
        final Function2<Camera.Size, Camera.Size, Integer> function2 = new Function2<Camera.Size, Camera.Size, Integer>() { // from class: org.informatika.sirekap.support.templatematching.ScanUtils$getOptimalPreviewSize$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Integer invoke(Camera.Size size1, Camera.Size size2) {
                Intrinsics.checkNotNullParameter(size1, "size1");
                Intrinsics.checkNotNullParameter(size2, "size2");
                double d2 = size1.width / size1.height;
                double d3 = size2.width / size2.height;
                double abs = Math.abs(d2 - d);
                double abs2 = Math.abs(d3 - d);
                if (MathUtils.INSTANCE.areFloatsEqual(abs, abs2)) {
                    Double.compare(Math.sqrt((size2.width * size2.width) + (size2.height * size2.height)), Math.sqrt((size1.width * size1.width) + (size1.height * size1.height)));
                }
                return Integer.valueOf(Double.compare(abs, abs2));
            }
        };
        CollectionsKt.sortWith(previewSizeList, new Comparator() { // from class: org.informatika.sirekap.support.templatematching.ScanUtils$$ExternalSyntheticLambda2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int optimalPreviewSize$lambda$1;
                optimalPreviewSize$lambda$1 = ScanUtils.getOptimalPreviewSize$lambda$1(Function2.this, obj, obj2);
                return optimalPreviewSize$lambda$1;
            }
        });
        return previewSizeList.get(0);
    }

    public static final int getOptimalPreviewSize$lambda$1(Function2 tmp0, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(obj, obj2)).intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<Point> sortPoints(List<? extends Point> list) {
        Point[] pointArr = {0, 0, 0, 0};
        Comparator comparator = new Comparator() { // from class: org.informatika.sirekap.support.templatematching.ScanUtils$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int sortPoints$lambda$2;
                sortPoints$lambda$2 = ScanUtils.sortPoints$lambda$2((Point) obj, (Point) obj2);
                return sortPoints$lambda$2;
            }
        };
        Comparator comparator2 = new Comparator() { // from class: org.informatika.sirekap.support.templatematching.ScanUtils$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int sortPoints$lambda$3;
                sortPoints$lambda$3 = ScanUtils.sortPoints$lambda$3((Point) obj, (Point) obj2);
                return sortPoints$lambda$3;
            }
        };
        List<? extends Point> list2 = list;
        pointArr[0] = Collections.min(list2, comparator);
        pointArr[1] = Collections.min(list2, comparator2);
        pointArr[2] = Collections.max(list2, comparator);
        pointArr[3] = Collections.max(list2, comparator2);
        return ArraysKt.toList(ArraysKt.requireNoNulls(pointArr));
    }

    public static final int sortPoints$lambda$2(Point lhs, Point rhs) {
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        return Double.compare(lhs.y + lhs.x, rhs.y + rhs.x);
    }

    public static final int sortPoints$lambda$3(Point lhs, Point rhs) {
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        return Double.compare(lhs.y - lhs.x, rhs.y - rhs.x);
    }

    public final Uri saveMatToGallery(Context context, Mat mat, String filename, String watermarkText) {
        OutputStream openOutputStream;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mat, "mat");
        Intrinsics.checkNotNullParameter(filename, "filename");
        Intrinsics.checkNotNullParameter(watermarkText, "watermarkText");
        Bitmap bmp = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat, bmp);
        Intrinsics.checkNotNullExpressionValue(bmp, "bmp");
        Bitmap addWatermark = BitmapUtilsKt.addWatermark(bmp, watermarkText);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            addWatermark.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "outputStream.toByteArray()");
            byteArrayOutputStream.close();
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", filename);
            contentValues.put("mime_type", "image/jpeg");
            contentValues.put("relative_path", MARKER_TEMPLATE_IMG_SAVE_PATH);
            ContentResolver contentResolver = context.getContentResolver();
            Uri insert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (insert == null || (openOutputStream = contentResolver.openOutputStream(insert)) == null) {
                return null;
            }
            openOutputStream.write(byteArray);
            openOutputStream.close();
            contentValues.clear();
            contentValues.put("is_pending", (Integer) 0);
            contentResolver.update(insert, contentValues, null, null);
            return insert;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private final Pair<Integer, Double> aprilTagNative(byte[] bArr, int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<ApriltagDetection> apriltag_detect_yuv = ApriltagNative.apriltag_detect_yuv(bArr, i, i2);
        if (apriltag_detect_yuv.size() > 0) {
            double d = 0.0d;
            int i3 = 0;
            while (i3 < 4) {
                int i4 = i3 * 2;
                i3++;
                int i5 = i3 * 2;
                d += Math.sqrt(Math.pow(apriltag_detect_yuv.get(0).p[i4] - apriltag_detect_yuv.get(0).p[i5 % 8], 2.0d) + Math.pow(apriltag_detect_yuv.get(0).p[i4 + 1] - apriltag_detect_yuv.get(0).p[(i5 + 1) % 8], 2.0d));
            }
            double d2 = d / 4;
            Log.wtf(TAG, "AprilTag execution time: " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            return new Pair<>(Integer.valueOf(apriltag_detect_yuv.get(0).f72id), Double.valueOf(d2));
        }
        return null;
    }

    public final List<Point> detectEdgeMarker(Context context, Mat inputMat, Mat mat, boolean z) {
        long j;
        float f;
        float f2;
        Iterator it;
        Point point;
        ScanUtils scanUtils = this;
        Context context2 = context;
        Mat templateMark = mat;
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(inputMat, "inputMat");
        Intrinsics.checkNotNullParameter(templateMark, "templateMark");
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        Mat clone = inputMat.clone();
        int cols = clone.cols() / 2;
        int rows = clone.rows() / 2;
        int i = 0;
        Iterator it2 = CollectionsKt.listOf((Object[]) new Mat[]{clone.submat(0, rows, 0, cols), clone.submat(0, rows, cols, clone.cols()), clone.submat(rows, clone.rows(), 0, cols), clone.submat(rows, clone.rows(), cols, clone.cols())}).iterator();
        float f3 = 10000.0f;
        float f4 = 0.0f;
        while (it2.hasNext()) {
            int i2 = i + 1;
            Mat subImg = (Mat) it2.next();
            Intrinsics.checkNotNullExpressionValue(subImg, "subImg");
            Pair<Point, Float> templateMatching = scanUtils.templateMatching(context2, subImg, templateMark, z);
            Point first = templateMatching.getFirst();
            float max = Float.max(templateMatching.getSecond().floatValue(), f4);
            float min = Math.min(templateMatching.getSecond().floatValue(), f3);
            if (i == 0) {
                j = currentTimeMillis;
                f = min;
                f2 = max;
                it = it2;
                point = new Point(first.x, first.y);
            } else if (i == 1) {
                j = currentTimeMillis;
                f = min;
                f2 = max;
                it = it2;
                point = new Point(first.x + cols, first.y);
            } else if (i == 2) {
                j = currentTimeMillis;
                f = min;
                f2 = max;
                it = it2;
                point = new Point(first.x, first.y + rows);
            } else if (i == 3) {
                f = min;
                f2 = max;
                j = currentTimeMillis;
                it = it2;
                point = new Point(first.x + cols, first.y + rows);
            } else {
                f = min;
                f2 = max;
                point = new Point(first.x, first.y);
                j = currentTimeMillis;
                it = it2;
            }
            Log.wtf(TAG, "Pattern found in sub-image " + i + " at point: x=" + point.x + ", y=" + point.y);
            arrayList.add(point);
            subImg.release();
            scanUtils = this;
            context2 = context;
            templateMark = mat;
            it2 = it;
            i = i2;
            f4 = f2;
            f3 = f;
            currentTimeMillis = j;
        }
        Log.wtf(TAG, "MinDist: " + f3 + "; MaxDist: " + f4);
        Log.wtf(TAG, "Template Matching execution time: " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        if (f3 < MIN_SIM_THRESHOLD) {
            return null;
        }
        return sortPoints(arrayList);
    }

    private final Pair<Point, Float> templateMatching(Context context, Mat mat, Mat mat2, boolean z) {
        Mat inputMatCopy = mat.clone();
        if (z) {
            saveMatToGallery(context, mat, "ori.jpg", "Original picture");
        }
        Mat mat3 = new Mat();
        Imgproc.cvtColor(mat, mat3, 6);
        Mat mat4 = new Mat();
        Imgproc.cvtColor(mat2, mat4, 6);
        if (z) {
            saveMatToGallery(context, mat3, "step0-1.jpg", "Convert image to black grey");
        }
        Mat mat5 = new Mat();
        Imgproc.matchTemplate(mat3, mat4, mat5, 5);
        Core.MinMaxLocResult minMaxLoc = Core.minMaxLoc(mat5);
        Point point = minMaxLoc.maxLoc;
        if (z) {
            Imgproc.circle(inputMatCopy, point, 10, new Scalar(0.0d, 255.0d, 0.0d), 5);
            Intrinsics.checkNotNullExpressionValue(inputMatCopy, "inputMatCopy");
            saveMatToGallery(context, inputMatCopy, "best_match.jpg", "Best Match Location - Template Matching");
        }
        mat.release();
        inputMatCopy.release();
        mat4.release();
        return new Pair<>(new Point(point.x + (mat2.width() / 2), point.y + (mat2.height() / 2)), Float.valueOf((float) minMaxLoc.maxVal));
    }

    public final boolean isScanPointsValid(List<? extends Point> list) {
        return list != null && list.size() == 4;
    }

    public final Mat convertToGrayScale(Mat mat) {
        Mat mat2 = new Mat();
        Imgproc.cvtColor(mat, mat2, 6);
        return mat2;
    }

    public final List<Point> matchCheckerboard(Mat mat, int i, int i2, Context context, Mat mat2, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Mat template = CheckerboardHelper.getTemplate(i, i2);
        List<Point> points = CheckerboardHelper.getTopLocations(mat, CheckerboardHelper.applyTemplateMatching(mat, template), template);
        if (points.size() == 4) {
            if (z) {
                Mat clone = mat2 != null ? mat2.clone() : null;
                if (mat2 != null) {
                    saveMatToGallery(context, mat2, "ori.jpg", "Original picture");
                }
                Intrinsics.checkNotNullExpressionValue(points, "points");
                for (Point point : points) {
                    Imgproc.circle(clone, point, 10, new Scalar(0.0d, 255.0d, 0.0d), 5);
                }
                if (clone != null) {
                    saveMatToGallery(context, clone, "best_match.jpg", "Best Match Location - Template Matching");
                }
            }
            Intrinsics.checkNotNullExpressionValue(points, "points");
            return sortPoints(points);
        }
        Log.wtf(TAG, "Detected Points: " + points);
        return null;
    }

    private final Pair<Integer, Double> detectAprilTagId(Mat mat, int i, int i2) {
        try {
            Log.wtf(TAG, "Start Detecting April Tag...");
            VpDetectorAprilTag vpDetectorAprilTag = new VpDetectorAprilTag();
            vpDetectorAprilTag.setAprilTagPoseEstimationMethod(i);
            vpDetectorAprilTag.setAprilTagFamily(i2);
            if (vpDetectorAprilTag.detect(toVpImageUChar(mat))) {
                int[] tagsId = vpDetectorAprilTag.getTagsId();
                Intrinsics.checkNotNullExpressionValue(tagsId, "localVpDetector.tagsId");
                if (tagsId.length != 1) {
                    int length = tagsId.length;
                    String arrays = Arrays.toString(tagsId);
                    Intrinsics.checkNotNullExpressionValue(arrays, "toString(this)");
                    Log.wtf(TAG, "Expected tagsId to have a length of 1, but got " + length + ". Detected IDs: " + arrays);
                    return null;
                }
                List<List<VpImagePoint>> tagsCorners = vpDetectorAprilTag.getTagsCorners();
                if (tagsCorners.size() != 1) {
                    Log.wtf(TAG, "Expected tags_corners to have a size of 1, but got " + tagsCorners.size());
                    return null;
                }
                List<VpImagePoint> list = tagsCorners.get(0);
                return new Pair<>(Integer.valueOf(tagsId[0]), Double.valueOf((Math.sqrt(Math.pow(list.get(0).get_i() - list.get(1).get_i(), 2.0d) + Math.pow(list.get(0).get_j() - list.get(1).get_j(), 2.0d)) + Math.sqrt(Math.pow(list.get(0).get_i() - list.get(3).get_i(), 2.0d) + Math.pow(list.get(0).get_j() - list.get(3).get_j(), 2.0d))) / 2.0d));
            }
            Log.wtf(TAG, "Detection failed!");
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final VpImageUChar toVpImageUChar(Mat mat) {
        Mat clone = mat.clone();
        if (clone.channels() > 1) {
            Imgproc.cvtColor(clone, clone, 6);
        }
        if (clone.depth() != 0) {
            clone.convertTo(clone, 0);
        }
        if (!clone.isContinuous()) {
            clone = clone.clone();
        }
        byte[] bArr = new byte[(int) (clone.total() * clone.elemSize())];
        clone.get(0, 0, bArr);
        return new VpImageUChar(bArr, clone.rows(), clone.cols(), false);
    }
}
