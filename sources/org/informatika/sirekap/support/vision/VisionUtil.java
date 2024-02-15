package org.informatika.sirekap.support.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Pair;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.informatika.sirekap.model.FormConfig;
import org.informatika.sirekap.support.FileUtil;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/* loaded from: classes2.dex */
public class VisionUtil {
    static Context globalContext;
    static float[] SpecDataAdmin = {600.0f, 2.32727f};
    static float[] SpecTally = {1000.0f, 1.6f};
    static int[] SpecAdditionalTallyMarker = {207, 487, 767};
    static float TallyMarkerTol = 50.0f;
    static float[][] SpecDataSuara = {new float[]{851.0f, 1.10781f}, new float[]{851.0f, 1.49595f}};
    static float ConfScoreThreshold = 0.7f;
    static int ThresholdBinary = 95;
    public static String VISION_UTIL_TYPE_ADMIN = "admin";
    public static String VISION_UTIL_TYPE_TABULASI = FormConfig.FORM_TALLY;
    public static String VISION_UTIL_TYPE_TALLY = FormConfig.VISION_TYPE_TALLY;

    /* loaded from: classes2.dex */
    public class PerspectiveCorrectionResult {
        public Bitmap bitmap;
        public Bitmap bitmapFullpage;
        public List<String> errors;

        PerspectiveCorrectionResult(Bitmap bitmap, List<String> errors, Bitmap bitmapFullpage) {
            VisionUtil.this = this$0;
            this.bitmap = bitmap;
            this.errors = errors;
            this.bitmapFullpage = bitmapFullpage;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:171:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x02f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.informatika.sirekap.support.vision.VisionUtil.PerspectiveCorrectionResult doCorrectPerspective(android.graphics.Bitmap r30, java.lang.String r31, java.lang.Integer r32, boolean r33, android.content.Context r34, java.io.File r35, java.lang.Integer r36) {
        /*
            Method dump skipped, instructions count: 1418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.vision.VisionUtil.doCorrectPerspective(android.graphics.Bitmap, java.lang.String, java.lang.Integer, boolean, android.content.Context, java.io.File, java.lang.Integer):org.informatika.sirekap.support.vision.VisionUtil$PerspectiveCorrectionResult");
    }

    public static List<Object> getTemplate(int H, int W) {
        int i;
        Mat zeros = Mat.zeros(H, W, CvType.CV_8UC1);
        Mat clone = zeros.clone();
        clone.setTo(new Scalar(255.0d));
        int i2 = 0;
        while (true) {
            i = H / 2;
            if (i2 >= i) {
                break;
            }
            for (int i3 = W / 2; i3 < W; i3++) {
                zeros.put(i2, i3, 255.0d);
                clone.put(i2, i3, 0.0d);
            }
            i2++;
        }
        while (i < H) {
            for (int i4 = 0; i4 < W / 2; i4++) {
                zeros.put(i, i4, 255.0d);
                clone.put(i, i4, 0.0d);
            }
            i++;
        }
        return Arrays.asList(zeros, clone);
    }

    public static Mat CorrectPerspective(Mat InImg, List<float[]> MarkerVal, float[] Spec, int[] Enlarge, int[] Trans) {
        float f = Spec[0];
        int i = (int) f;
        int i2 = (int) (f * Spec[1]);
        double d = i - 1;
        double d2 = i2 - 1;
        Mat perspectiveTransform = Imgproc.getPerspectiveTransform(new MatOfPoint2f(new Point(MarkerVal.get(0)[0], MarkerVal.get(0)[1]), new Point(MarkerVal.get(1)[0], MarkerVal.get(1)[1]), new Point(MarkerVal.get(2)[0], MarkerVal.get(2)[1]), new Point(MarkerVal.get(3)[0], MarkerVal.get(3)[1])), new MatOfPoint2f(new Point(0.0d, 0.0d), new Point(d, 0.0d), new Point(0.0d, d2), new Point(d, d2)));
        if (Enlarge[0] > 0 && Enlarge[1] > 0) {
            Mat eye = Mat.eye(3, 3, 6);
            System.out.println(eye);
            eye.put(0, 2, Trans[0]);
            System.out.println(eye);
            eye.put(1, 2, Trans[1]);
            System.out.println(eye);
            Core.gemm(eye, perspectiveTransform, 1.0d, new Mat(), 0.0d, perspectiveTransform, 0);
        }
        Mat zeros = Mat.zeros(i2, i, CvType.CV_8UC1);
        Imgproc.warpPerspective(InImg, zeros, perspectiveTransform, new Size(i + Enlarge[0], i2 + Enlarge[1]));
        perspectiveTransform.release();
        return zeros;
    }

    public static Pair<Boolean, List<float[]>> FormCimageValidation(Mat InputImg, Mat Template, float ConfThresh) {
        ArrayList arrayList = new ArrayList();
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, 4, 2);
        ArrayList arrayList2 = new ArrayList();
        char c = 0;
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            for (int i3 = 0; i3 < 2; i3++) {
                int floor = (int) Math.floor(InputImg.rows() / 2.0d);
                int floor2 = (int) Math.floor(InputImg.cols() / 2.0d);
                float[] fArr2 = fArr[i];
                int i4 = i3 * floor2;
                fArr2[0] = i4;
                int i5 = i2 * floor;
                fArr2[1] = i5;
                i++;
                if (i2 != 0) {
                    floor = InputImg.rows() - floor;
                }
                if (i2 != 0) {
                    floor2 = InputImg.cols() - floor2;
                }
                arrayList2.add(InputImg.submat(i5, floor + i5, i4, floor2 + i4));
            }
        }
        boolean booleanValue = Boolean.TRUE.booleanValue();
        int i6 = 0;
        while (true) {
            if ((i6 < 4 ? (char) 1 : c) == false || !booleanValue) {
                break;
            }
            Mat clone = ((Mat) arrayList2.get(i6)).clone();
            Mat clone2 = ((Mat) arrayList2.get(i6)).clone();
            Imgproc.threshold(clone2, clone2, 0.0d, 255.0d, 8);
            Imgproc.matchTemplate(clone2, Template, clone, 1);
            Core.MinMaxLocResult minMaxLoc = Core.minMaxLoc(clone);
            Point point = minMaxLoc.minLoc;
            boolean z = booleanValue;
            float rows = (float) (point.y + fArr[i6][1] + (Template.rows() / 2));
            float f = (float) (1.0d - minMaxLoc.minVal);
            float[] fArr3 = {(float) (point.x + fArr[i6][c] + (Template.cols() / 2)), rows, f};
            if (f >= ConfThresh) {
                arrayList.add(fArr3);
            }
            booleanValue = f < ConfThresh ? Boolean.FALSE.booleanValue() : z;
            clone.release();
            clone2.release();
            i6++;
            c = 0;
        }
        Boolean bool = Boolean.FALSE;
        if (arrayList.size() == 4) {
            bool = Boolean.TRUE;
        }
        return new Pair<>(bool, arrayList);
    }

    public static Pair<float[], Mat> TemplateMatching(Mat InputImage, Mat Template, String mode) {
        Mat clone = InputImage.clone();
        Mat clone2 = InputImage.clone();
        if (mode == "otsu") {
            Imgproc.threshold(InputImage, clone, 0.0d, 255.0d, 8);
        } else {
            Imgproc.threshold(clone, clone, ThresholdBinary, 255.0d, 0);
        }
        Imgproc.matchTemplate(clone, Template, clone2, 1);
        Core.MinMaxLocResult minMaxLoc = Core.minMaxLoc(clone2);
        Point point = minMaxLoc.minLoc;
        float[] fArr = {(float) (point.x + (Template.cols() / 2)), (float) (point.y + (Template.rows() / 2)), (float) (1.0d - minMaxLoc.minVal)};
        clone2.release();
        return new Pair<>(fArr, clone);
    }

    static Boolean IsBlackArea(Mat InputImg, int[] Area) {
        Mat submat = InputImg.submat(Area[0], Area[1], Area[2], Area[3]);
        double d = 0.0d;
        for (int i = 0; i < submat.rows(); i++) {
            for (int i2 = 0; i2 < submat.cols(); i2++) {
                d += submat.get(i, i2)[0];
            }
        }
        return Boolean.valueOf(d / ((double) (submat.rows() * submat.cols())) <= 5.0d);
    }

    static Pair<Boolean[], Mat> getFullLovelyPageUhuy(Mat InImg, List<float[]> MarkerVal, float[] Spec, int[] Enlarge, int[] Trans, Mat Template) {
        Mat CorrectPerspective = CorrectPerspective(InImg, MarkerVal, Spec, Enlarge, Trans);
        int rows = CorrectPerspective.rows();
        int cols = CorrectPerspective.cols();
        int[] iArr = {0, 1, 0, 1};
        iArr[0] = 0;
        iArr[1] = 10;
        int i = cols / 3;
        iArr[2] = i;
        int i2 = (cols * 2) / 3;
        iArr[3] = i2;
        Boolean IsBlackArea = IsBlackArea(CorrectPerspective, iArr);
        int i3 = rows / 3;
        iArr[0] = i3;
        int i4 = (rows * 2) / 3;
        iArr[1] = i4;
        iArr[2] = cols - 10;
        iArr[3] = cols;
        Boolean IsBlackArea2 = IsBlackArea(CorrectPerspective, iArr);
        iArr[0] = rows - 10;
        iArr[1] = rows;
        iArr[2] = i;
        iArr[3] = i2;
        Boolean IsBlackArea3 = IsBlackArea(CorrectPerspective, iArr);
        iArr[0] = i3;
        iArr[1] = i4;
        iArr[2] = 0;
        iArr[3] = 10;
        return new Pair<>(new Boolean[]{IsBlackArea, IsBlackArea2, IsBlackArea3, IsBlackArea(CorrectPerspective, iArr)}, CorrectPerspective);
    }

    static List<float[]> DetectTallyAdditionalMarker(Mat InputImage, Mat Template) {
        ArrayList arrayList = new ArrayList();
        Mat clone = InputImage.clone();
        int i = 0;
        int i2 = (int) (SpecTally[0] / 2.0f);
        Mat submat = clone.submat(0, clone.rows(), 0, i2);
        Mat submat2 = clone.submat(0, clone.rows(), i2, clone.cols());
        while (i <= 1) {
            Mat mat = i == 0 ? submat : submat2;
            new Pair(null, null);
            Pair<float[], Mat> TemplateMatching = TemplateMatching(mat, Template, "manual");
            if (((float[]) TemplateMatching.first)[2] < ConfScoreThreshold) {
                TemplateMatching = TemplateMatching(mat, Template, "otsu");
            }
            if (((float[]) TemplateMatching.first)[2] > ConfScoreThreshold) {
                arrayList.add((float[]) TemplateMatching.first);
            }
            mat.release();
            i++;
        }
        clone.release();
        submat.release();
        submat2.release();
        return arrayList;
    }

    public static Pair<Boolean, List<float[]>> FormCimageValidationFullPage(Mat InputImg, Mat Template, float ConfThresh, Context context, File originalFile) {
        int rows;
        int cols;
        Mat mat = Template;
        ArrayList arrayList = new ArrayList();
        String debugCorrectedImageFilename = FileUtil.getDebugCorrectedImageFilename(originalFile);
        Mat clone = InputImg.clone();
        Mat mat2 = new Mat();
        Mat clone2 = InputImg.clone();
        Imgproc.threshold(clone2, clone2, ThresholdBinary, 255.0d, 0);
        char c = 1;
        Imgproc.matchTemplate(clone2, mat, mat2, 1);
        Core.MinMaxLocResult minMaxLoc = Core.minMaxLoc(mat2);
        if (((float) (1.0d - minMaxLoc.minVal)) < ConfScoreThreshold) {
            Mat clone3 = InputImg.clone();
            Imgproc.threshold(clone3, clone3, 0.0d, 255.0d, 8);
            Imgproc.matchTemplate(clone3, mat, mat2, 1);
            Core.MinMaxLocResult minMaxLoc2 = Core.minMaxLoc(mat2);
            float f = (float) (1.0d - minMaxLoc2.minVal);
            if (f < ConfScoreThreshold) {
                Imgproc.cvtColor(clone, clone, 8);
                Imgproc.putText(clone, Float.toString(f), new Point((int) minMaxLoc2.minLoc.x, (int) (minMaxLoc2.minLoc.y + 3.0d)), 3, 1.0d, new Scalar(255.0d, 0.0d, 0.0d), 4);
                Bitmap createBitmap = Bitmap.createBitmap(clone.cols(), clone.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(clone, createBitmap);
                saveImage(context, debugCorrectedImageFilename, createBitmap);
                return new Pair<>(Boolean.FALSE, arrayList);
            }
            clone3.release();
            minMaxLoc = minMaxLoc2;
        }
        Point point = minMaxLoc.minLoc;
        int i = 2;
        int cols2 = (int) (point.x + (Template.cols() / 2));
        int rows2 = (int) (point.y + (Template.rows() / 2));
        if (rows2 > InputImg.rows() / 3) {
            rows = rows2 - (rows2 / 4);
        } else {
            rows = rows2 + ((InputImg.rows() - rows2) / 4);
        }
        if (cols2 > (InputImg.cols() * 3) / 4) {
            cols = cols2 - (cols2 / 4);
        } else {
            cols = cols2 + ((InputImg.cols() - cols2) / 4);
        }
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, 4, 2);
        ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = 0;
            while (i4 < i) {
                float[] fArr2 = fArr[i3];
                int i5 = i4 * cols;
                fArr2[0] = i5;
                int i6 = i2 * rows;
                Mat mat3 = clone2;
                fArr2[c] = i6;
                i3++;
                arrayList2.add(InputImg.submat(i6, (i2 == 0 ? rows : InputImg.rows() - rows) + i6, i5, i5 + (i4 == 0 ? cols : InputImg.cols() - cols)));
                i4++;
                rows = rows;
                clone2 = mat3;
                c = 1;
                i = 2;
            }
            i2++;
            rows = rows;
            c = 1;
            i = 2;
        }
        Mat mat4 = clone2;
        Boolean bool = Boolean.TRUE;
        Imgproc.cvtColor(clone, clone, 8);
        int i7 = 0;
        while (true) {
            if (!(i7 < 4) || !bool.booleanValue()) {
                break;
            }
            Mat clone4 = ((Mat) arrayList2.get(i7)).clone();
            new Pair(null, null);
            Pair<float[], Mat> TemplateMatching = TemplateMatching(clone4, mat, "manual");
            if (((float[]) TemplateMatching.first)[2] < ConfThresh) {
                TemplateMatching = TemplateMatching(clone4, mat, "otsu");
            }
            float[] fArr3 = (float[]) TemplateMatching.first;
            Mat clone5 = ((Mat) TemplateMatching.second).clone();
            Imgproc.cvtColor((Mat) TemplateMatching.second, clone5, 8);
            Mat mat5 = mat2;
            Boolean bool2 = bool;
            Imgproc.circle(clone5, new Point((int) fArr3[0], (int) fArr3[1]), 4, new Scalar(0.0d, 255.0d, 0.0d), 3);
            Imgproc.putText(clone5, Float.toString(fArr3[2]), new Point((int) fArr3[0], (int) (fArr3[1] + 3.0f)), 3, 1.0d, new Scalar(255.0d, 0.0d, 0.0d), 4);
            float[] fArr4 = fArr[i7];
            int i8 = (int) fArr4[0];
            int i9 = (int) fArr4[1];
            clone5.copyTo(clone.submat(i9, clone5.rows() + i9, i8, clone5.cols() + i8));
            float f2 = fArr3[0];
            float[] fArr5 = fArr[i7];
            fArr3[0] = f2 + fArr5[0];
            fArr3[1] = fArr3[1] + fArr5[1];
            if (fArr3[2] >= ConfThresh) {
                arrayList.add(fArr3);
            }
            clone4.release();
            clone5.release();
            bool = fArr3[2] < ConfThresh ? Boolean.FALSE : bool2;
            i7++;
            mat = Template;
            mat2 = mat5;
        }
        Mat mat6 = mat2;
        Bitmap createBitmap2 = Bitmap.createBitmap(clone.cols(), clone.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(clone, createBitmap2);
        saveImage(context, debugCorrectedImageFilename, createBitmap2);
        Boolean bool3 = Boolean.FALSE;
        if (arrayList.size() == 4) {
            bool3 = Boolean.TRUE;
        }
        clone.release();
        mat6.release();
        mat4.release();
        return new Pair<>(bool3, arrayList);
    }

    private static File saveImage(Context context, String filename, Bitmap bitmap) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), filename);
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static boolean checkAdditionalMark(Mat InImg, List<float[]> MarkerVal, float[] Spec, int[] Enlarge, int[] Trans, String type, Mat Template, int numbPaslon) {
        int i;
        int i2;
        int i3;
        boolean booleanValue = Boolean.FALSE.booleanValue();
        List<float[]> DetectAdminTabAdditionalMarker = DetectAdminTabAdditionalMarker(CorrectPerspective(InImg, MarkerVal, Spec, Enlarge, Trans), Template);
        if (type != FormConfig.FORM_TALLY) {
            i = 0;
            i2 = 0;
            i3 = 0;
        } else if (numbPaslon < 6 || numbPaslon > 10) {
            i = 466;
            i2 = 26;
            i3 = 968;
        } else {
            i = 463;
            i2 = 23;
            i3 = 1293;
        }
        if (type == "admin") {
            i = 344;
            i2 = 43;
            i3 = 1443;
        }
        if (DetectAdminTabAdditionalMarker.size() == 2) {
            int i4 = (int) DetectAdminTabAdditionalMarker.get(0)[1];
            if ((((float) Math.abs(i - ((int) DetectAdminTabAdditionalMarker.get(1)[0]))) <= 30.0f) & (((float) Math.abs(i - ((int) DetectAdminTabAdditionalMarker.get(0)[0]))) <= 30.0f) & (((float) Math.abs(i2 - i4)) <= 30.0f) & (((float) Math.abs(i3 - ((int) DetectAdminTabAdditionalMarker.get(1)[1]))) <= 30.0f)) {
                booleanValue = Boolean.TRUE.booleanValue();
            }
        }
        System.out.println(booleanValue);
        return booleanValue;
    }

    static List<float[]> DetectAdminTabAdditionalMarker(Mat InputImage, Mat Template) {
        ArrayList arrayList = new ArrayList();
        Mat clone = InputImage.clone();
        int rows = InputImage.rows() / 2;
        Mat submat = clone.submat(0, rows, 0, InputImage.cols());
        Mat submat2 = clone.submat(rows, clone.rows(), 0, clone.cols());
        int i = 0;
        while (i <= 1) {
            Mat mat = i == 0 ? submat : submat2;
            new Pair(null, null);
            Pair<float[], Mat> TemplateMatching = TemplateMatching(mat, Template, "manual");
            if (((float[]) TemplateMatching.first)[2] < ConfScoreThreshold) {
                TemplateMatching = TemplateMatching(mat, Template, "otsu");
            }
            if (((float[]) TemplateMatching.first)[2] > ConfScoreThreshold) {
                if (i == 1) {
                    ((float[]) TemplateMatching.first)[1] = ((float[]) TemplateMatching.first)[1] + rows;
                }
                arrayList.add((float[]) TemplateMatching.first);
                System.out.println("sub-" + Integer.toString(i) + ", x: " + Float.toString(((float[]) TemplateMatching.first)[0]) + ", y:" + Float.toString(((float[]) TemplateMatching.first)[1]) + ", conf.:" + Float.toString(((float[]) TemplateMatching.first)[2]));
            }
            mat.release();
            i++;
        }
        clone.release();
        submat.release();
        submat2.release();
        return arrayList;
    }
}
