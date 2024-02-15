package org.informatika.sirekap.support.vision;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.apache.xmpbox.type.PDFATypeType;
import org.apache.xmpbox.type.ThumbnailType;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.FormConfig;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.templatematching.AprilTagConfig;
import org.informatika.sirekap.support.templatematching.ScanUtils;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.CLAHE;
import org.opencv.imgproc.Imgproc;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.Tensor;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* compiled from: Vision.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 i2\u00020\u0001:\u0002hiB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J,\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\n2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J\u0016\u0010\u001c\u001a\u00020\b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J>\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180\n2\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0018H\u0002J(\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00132\u0006\u0010*\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J6\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\n2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\b2\u0006\u00101\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J&\u00102\u001a\b\u0012\u0004\u0012\u00020,0\n2\u0006\u00103\u001a\u00020\u00102\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u0010H\u0002JF\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u0002062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020,072\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00180\n2\u0006\u00109\u001a\u00020\u00062\u0010\b\u0002\u0010:\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\nH\u0002J\u001c\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00180\n2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00180\nH\u0002J\u0010\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0006H\u0002J\b\u0010@\u001a\u00020\u001fH\u0002J&\u0010A\u001a\b\u0012\u0004\u0012\u00020,0\n2\u0006\u00103\u001a\u00020\u00102\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J$\u0010B\u001a\b\u0012\u0004\u0012\u00020\u0018072\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00180\n2\u0006\u0010D\u001a\u00020\bH\u0002JF\u0010E\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\n2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u00062\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020K2\u0006\u0010M\u001a\u00020\u00132\u0006\u0010N\u001a\u00020\u0013J6\u0010O\u001a\u00020\u001f2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020,0\n2\u0006\u00103\u001a\u00020\u00102\u0006\u0010Q\u001a\u00020\u00102\u0006\u0010R\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J\u001e\u0010S\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00102\f\u0010P\u001a\b\u0012\u0004\u0012\u00020,0\nH\u0002J~\u0010T\u001a\u00020\u001f2\u0006\u00105\u001a\u0002062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020,072\u0006\u0010/\u001a\u00020\u00102\u0006\u0010U\u001a\u00020\u00132\u0006\u0010V\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u00132\u0006\u00100\u001a\u00020\b2\u0006\u00101\u001a\u00020\b2\u0006\u00109\u001a\u00020\u00062\u0006\u0010W\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J(\u0010X\u001a\u00020\u001f2\u0006\u0010Y\u001a\u00020,2\u0006\u00103\u001a\u00020\u00102\u0006\u0010Q\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J0\u0010Z\u001a\u00020\u001f2\u0006\u0010Y\u001a\u00020,2\u0006\u0010[\u001a\u00020\u00182\u0006\u0010Q\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020,H\u0002JF\u0010]\u001a\u00020\u001f2\u0006\u0010^\u001a\u00020_2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020,072\u0006\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\b2\u0006\u00101\u001a\u00020\b2\u0006\u00109\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J(\u0010`\u001a\u00020\u001f2\u0006\u0010Y\u001a\u00020,2\u0006\u0010a\u001a\u00020\u00182\u0006\u00103\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J\u0010\u0010b\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J(\u0010c\u001a\u00020\u00132\u0006\u0010d\u001a\u00020\u00102\u0006\u0010e\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010a\u001a\u00020\u0018H\u0002J\u0016\u0010f\u001a\u00020\u00132\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Lorg/informatika/sirekap/support/vision/Vision;", "Ljava/lang/AutoCloseable;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "chosenMethodTabulation", "", "fontScale", "", "interpreters", "", "Lorg/tensorflow/lite/Interpreter;", "interpretersBlank", "adjustClipLimit", "contrast", "applyAdaptiveThreshold", "Lorg/opencv/core/Mat;", ThumbnailType.IMAGE, "applyHeuristic", "", "probabilities", "", "calculateImageContrast", "calculateOmrBoxes", "Lorg/opencv/core/Rect;", "boxesDict", "multiplier", "imageToDraw", "calculateStandardDeviation", "values", "close", "", "detectBoxesInRoi", "roiMat", "minWidth", "maxWidth", "minHeight", "maxHeight", "roi", "drawPrediction", "prediction", "x", OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "extractBoxesDict", "Lorg/informatika/sirekap/support/vision/Vision$BoxGroup;", "pageConfig", "Lorg/informatika/sirekap/model/FormConfig;", "threshImg", "widthScaleFactor", "heightScaleFactor", "getBoxesCoordinates", "croppedImage", "groupAndAddBoxesByField", PDFATypeType.FIELD, "Lorg/informatika/sirekap/model/FormConfig$Field;", "", "fieldBoxes", "boxType", "omrBoxes", "inferMissingCircles", "detectedBounds", "loadModelFileDescriptor", "Ljava/nio/ByteBuffer;", "modelPath", "loadModels", "locateBoxesInRegion", "nonMaxSuppression", "boxes", "overlapThresh", "predict", "correctedBitmap", "Landroid/graphics/Bitmap;", "formType", "electionType", "isLn", "", "isPos", "candidateNum", "maxCandidatesNum", "predictOcr", "boxGroups", "preprocessedImage", "numPaslon", "preprocessImage", "processField", "rWidth", "rHeight", "omrMultiplier", "processGroup", "boxGroup", "processOcr", "rect", "tempBoxGroup", "processOcrRegion", "region", "Lorg/informatika/sirekap/model/FormConfig$ROI;", "processOmr", "omrRect", "scanAreaOcr", "scanAreaOmr", "roiOmr", "grayCroppedImage", "selectCircle", "intensityValues", "BoxGroup", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Vision implements AutoCloseable {
    private static final double BOX_SIZE_TOLERANCE_OFFSET = 0.15d;
    private static final double CIRCLE_TO_CROPPED_WIDTH_RATIO = 0.016949152542373d;
    private static final double OMR_BOX_SIZE_TOLERANCE_OFFSET = 0.7d;
    private static final double ROI_SIZE_NEGATIVE_OFFSET = 0.3d;
    private static final double ROI_SIZE_POSITIVE_OFFSET = 1.3d;
    private static final String TAG = "VISION";
    private String chosenMethodTabulation;
    private final Context context;
    private double fontScale;
    private List<Interpreter> interpreters;
    private Interpreter interpretersBlank;
    public static final Companion Companion = new Companion(null);
    private static final Scalar COLOR_GREEN = new Scalar(0.0d, 255.0d, 0.0d);
    private static final Scalar COLOR_BLUE = new Scalar(255.0d, 0.0d, 0.0d);
    private static final Scalar COLOR_RED = new Scalar(0.0d, 0.0d, 255.0d);

    private final double adjustClipLimit(double d) {
        if (d < 50.0d) {
            return 3.0d;
        }
        return d < 100.0d ? 2.0d : 1.0d;
    }

    public Vision(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.fontScale = 1.0d;
        this.chosenMethodTabulation = "";
    }

    private final void loadModels() {
        ArrayList arrayList = new ArrayList(15);
        for (int i = 0; i < 15; i++) {
            arrayList.add("ensemble_model_" + i + ".tflite");
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add("vision/ensemble-15-mnist/" + ((String) it.next()));
        }
        ArrayList<String> arrayList4 = arrayList3;
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
        for (String str : arrayList4) {
            arrayList5.add(new Interpreter(loadModelFileDescriptor(str), new Interpreter.Options()));
        }
        ArrayList<Interpreter> arrayList6 = arrayList5;
        this.interpreters = arrayList6;
        for (Interpreter interpreter : arrayList6) {
            interpreter.allocateTensors();
        }
        Interpreter interpreter2 = new Interpreter(loadModelFileDescriptor("vision/blank-dash-mult/blank_detection_model.tflite"), new Interpreter.Options());
        this.interpretersBlank = interpreter2;
        interpreter2.allocateTensors();
    }

    private final ByteBuffer loadModelFileDescriptor(String str) {
        AssetFileDescriptor openFd = this.context.getAssets().openFd(str);
        Intrinsics.checkNotNullExpressionValue(openFd, "assetManager.openFd(modelPath)");
        FileDescriptor fileDescriptor = openFd.getFileDescriptor();
        long startOffset = openFd.getStartOffset();
        ByteBuffer modelByteBuffer = ByteBuffer.allocateDirect((int) openFd.getDeclaredLength());
        FileChannel channel = new FileInputStream(fileDescriptor).getChannel();
        try {
            FileChannel fileChannel = channel;
            fileChannel.position(startOffset);
            fileChannel.read(modelByteBuffer);
            CloseableKt.closeFinally(channel, null);
            Intrinsics.checkNotNullExpressionValue(modelByteBuffer, "modelByteBuffer");
            return modelByteBuffer;
        } finally {
        }
    }

    public final List<Integer> predict(Bitmap correctedBitmap, String formType, String electionType, boolean z, boolean z2, int i, int i2) throws Exception {
        int i3;
        Intrinsics.checkNotNullParameter(correctedBitmap, "correctedBitmap");
        Intrinsics.checkNotNullParameter(formType, "formType");
        Intrinsics.checkNotNullParameter(electionType, "electionType");
        if (BuildConfig.IS_USE_LOCAL_OCR.booleanValue() || (Intrinsics.areEqual(electionType, Election.ELECTION_PEMILIHAN_PRESIDEN) && Intrinsics.areEqual(formType, FormConfig.FORM_TALLY))) {
            if (!OpenCVLoader.initDebug()) {
                throw new Exception("Unable to load OpenCV");
            }
            loadModels();
            Mat mat = new Mat(correctedBitmap.getHeight(), correctedBitmap.getWidth(), CvType.CV_8UC1);
            Utils.bitmapToMat(correctedBitmap, mat);
            this.fontScale = correctedBitmap.getWidth() / 2000.0d;
            Mat convertToGrayScale = ScanUtils.INSTANCE.convertToGrayScale(mat);
            Mat imageToDraw = mat.clone();
            Pair<Integer, Double> detect = ScanUtils.INSTANCE.detect(mat, correctedBitmap);
            if (detect != null) {
                i3 = AprilTagConfig.INSTANCE.getCandidateNum(detect.getFirst().intValue());
            } else if (Intrinsics.areEqual(electionType, Election.ELECTION_PEMILIHAN_DPD)) {
                boolean z3 = true;
                if ((1 <= i2 && i2 < 9) || (13 <= i2 && i2 < 17)) {
                    i3 = 8;
                } else {
                    if (!((((9 <= i2 && i2 < 11) || (17 <= i2 && i2 < 21)) || (25 <= i2 && i2 < 31)) || (37 <= i2 && i2 < 41)) && (49 > i2 || i2 >= 51)) {
                        z3 = false;
                    }
                    i3 = z3 ? 10 : 12;
                }
            } else {
                i3 = i;
            }
            FormConfig config = FormConfig.Companion.getConfig(this.context, formType, electionType, z, z2, i3);
            Intrinsics.checkNotNullExpressionValue(imageToDraw, "imageToDraw");
            List<BoxGroup> locateBoxesInRegion = locateBoxesInRegion(convertToGrayScale, config, imageToDraw);
            Mat preprocessImage = preprocessImage(convertToGrayScale, locateBoxesInRegion);
            this.chosenMethodTabulation = "";
            predictOcr(locateBoxesInRegion, convertToGrayScale, preprocessImage, i, imageToDraw);
            Boolean IS_SAVE_VISION_ANNOTATION = BuildConfig.IS_SAVE_VISION_ANNOTATION;
            Intrinsics.checkNotNullExpressionValue(IS_SAVE_VISION_ANNOTATION, "IS_SAVE_VISION_ANNOTATION");
            if (IS_SAVE_VISION_ANNOTATION.booleanValue()) {
                ScanUtils.INSTANCE.saveMatToGallery(this.context, imageToDraw, electionType + "_" + formType + "_" + (z ? "LN" : z2 ? "POS" : "DN") + "_" + (detect != null ? Integer.valueOf(detect.getFirst().intValue()) : "NONE"), "Vision Lokal (" + this.chosenMethodTabulation + ")");
            }
            ArrayList arrayList = new ArrayList();
            for (BoxGroup boxGroup : locateBoxesInRegion) {
                arrayList.add(Integer.valueOf(ElectionUtil.joinThreeNumbers(boxGroup.getPredictions())));
            }
            imageToDraw.release();
            mat.release();
            convertToGrayScale.release();
            return arrayList;
        }
        return null;
    }

    private final List<BoxGroup> locateBoxesInRegion(Mat mat, FormConfig formConfig, Mat mat2) {
        List<BoxGroup> boxesCoordinates = getBoxesCoordinates(mat, formConfig, mat2);
        for (BoxGroup boxGroup : boxesCoordinates) {
            int i = 0;
            for (Rect rect : boxGroup.getCoordinates()) {
                int i2 = i + 1;
                if (i == 0) {
                    Imgproc.putText(mat2, boxGroup.getName(), new Point(rect.x, rect.y - (Math.ceil(1.0d) * 12)), 0, 0.5d, COLOR_BLUE, (int) Math.ceil(1.0d));
                }
                i = i2;
            }
        }
        return boxesCoordinates;
    }

    private final Mat preprocessImage(Mat mat, List<BoxGroup> list) {
        Mat mat2 = new Mat(mat.size(), mat.type(), new Scalar(255.0d));
        for (BoxGroup boxGroup : list) {
            for (Rect rect : boxGroup.getCoordinates()) {
                int i = rect.x;
                int i2 = rect.y;
                int i3 = rect.width;
                int i4 = rect.height;
                int min = Math.min((int) (i3 * BOX_SIZE_TOLERANCE_OFFSET), i3 / 2);
                int min2 = Math.min((int) (i4 * BOX_SIZE_TOLERANCE_OFFSET), i4 / 2);
                int i5 = i2 + min2;
                int i6 = (i2 + i4) - min2;
                int i7 = i + min;
                int i8 = (i + i3) - min;
                mat.submat(i5, i6, i7, i8).copyTo(mat2.submat(i5, i6, i7, i8));
            }
        }
        return mat2;
    }

    private final void predictOcr(List<BoxGroup> list, Mat mat, Mat mat2, int i, Mat mat3) {
        for (BoxGroup boxGroup : list) {
            if (StringsKt.startsWith$default(boxGroup.getName(), "suara_paslon_", false, 2, (Object) null)) {
                Integer intOrNull = StringsKt.toIntOrNull((String) StringsKt.split$default((CharSequence) boxGroup.getName(), new String[]{"suara_paslon_"}, false, 0, 6, (Object) null).get(1));
                if (intOrNull == null || intOrNull.intValue() <= i || i == 0) {
                    processGroup(boxGroup, mat, mat2, mat3);
                }
            } else {
                processGroup(boxGroup, mat, mat2, mat3);
            }
        }
    }

    private final void processGroup(BoxGroup boxGroup, Mat mat, Mat mat2, Mat mat3) {
        BoxGroup copy$default = BoxGroup.copy$default(boxGroup, null, null, null, null, 15, null);
        for (Rect rect : boxGroup.getCoordinates()) {
            processOcr(boxGroup, rect, mat2, mat3, copy$default);
        }
        if (Intrinsics.areEqual(boxGroup.getType(), "omr")) {
            try {
                for (Rect rect2 : boxGroup.getCoordinatesOmr()) {
                    processOmr(boxGroup, rect2, mat, mat3);
                }
                this.chosenMethodTabulation += "OMR,";
            } catch (Exception unused) {
                boxGroup.deletePredictions();
                for (Integer num : copy$default.getPredictions()) {
                    boxGroup.addPrediction(num.intValue());
                }
                this.chosenMethodTabulation += "OCR,";
            }
        }
    }

    private final void processOcr(BoxGroup boxGroup, Rect rect, Mat mat, Mat mat2, BoxGroup boxGroup2) {
        int coerceAtMost = RangesKt.coerceAtMost((int) (rect.width * BOX_SIZE_TOLERANCE_OFFSET), rect.width / 2);
        int coerceAtMost2 = RangesKt.coerceAtMost((int) (rect.height * BOX_SIZE_TOLERANCE_OFFSET), rect.height / 2);
        Mat roi = mat.submat(rect.y + coerceAtMost2, (rect.y + rect.height) - coerceAtMost2, rect.x + coerceAtMost, (rect.x + rect.width) - coerceAtMost);
        Intrinsics.checkNotNullExpressionValue(roi, "roi");
        int scanAreaOcr = scanAreaOcr(roi);
        if (Intrinsics.areEqual(boxGroup.getType(), "ocr")) {
            boxGroup.addPrediction(scanAreaOcr);
        } else {
            boxGroup2.addPrediction(scanAreaOcr);
        }
        drawPrediction(scanAreaOcr, rect.x, rect.y, mat2);
    }

    private final int scanAreaOcr(Mat mat) {
        Mat clone = mat.clone();
        Mat mat2 = new Mat();
        Imgproc.resize(clone, mat2, new Size(28.0d, 28.0d));
        Imgproc.adaptiveThreshold(mat2, mat2, 255.0d, 1, 0, 11, 7.0d);
        Mat mat3 = new Mat();
        Core.bitwise_not(mat2, mat3);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(mat3.width() * 4 * mat3.height());
        allocateDirect.order(ByteOrder.nativeOrder());
        int height = mat3.height();
        for (int i = 0; i < height; i++) {
            int width = mat3.width();
            for (int i2 = 0; i2 < width; i2++) {
                allocateDirect.putFloat((float) (mat3.get(i, i2)[0] / 255.0f));
            }
        }
        Interpreter interpreter = this.interpretersBlank;
        List<Interpreter> list = null;
        if (interpreter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("interpretersBlank");
            interpreter = null;
        }
        Tensor inputTensor = interpreter.getInputTensor(0);
        Interpreter interpreter2 = this.interpretersBlank;
        if (interpreter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("interpretersBlank");
            interpreter2 = null;
        }
        Tensor outputTensor = interpreter2.getOutputTensor(0);
        TensorBuffer createFixedSize = TensorBuffer.createFixedSize(inputTensor.shape(), inputTensor.dataType());
        Intrinsics.checkNotNullExpressionValue(createFixedSize, "createFixedSize(blankInp…kInputDetails.dataType())");
        createFixedSize.loadBuffer(allocateDirect);
        TensorBuffer createFixedSize2 = TensorBuffer.createFixedSize(outputTensor.shape(), outputTensor.dataType());
        Intrinsics.checkNotNullExpressionValue(createFixedSize2, "createFixedSize(blankOut…OutputDetails.dataType())");
        Interpreter interpreter3 = this.interpretersBlank;
        if (interpreter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("interpretersBlank");
            interpreter3 = null;
        }
        interpreter3.run(createFixedSize.getBuffer(), createFixedSize2.getBuffer());
        float[] floatArray = createFixedSize2.getFloatArray();
        Intrinsics.checkNotNullExpressionValue(floatArray, "blankOutputData.floatArray");
        Float maxOrNull = ArraysKt.maxOrNull(floatArray);
        if ((maxOrNull != null ? maxOrNull.floatValue() : 0.0f) < 1.0f) {
            float[] fArr = new float[10];
            List<Interpreter> list2 = this.interpreters;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("interpreters");
            } else {
                list = list2;
            }
            for (Interpreter interpreter4 : list) {
                Tensor inputTensor2 = interpreter4.getInputTensor(0);
                Tensor outputTensor2 = interpreter4.getOutputTensor(0);
                TensorBuffer createFixedSize3 = TensorBuffer.createFixedSize(inputTensor2.shape(), inputTensor2.dataType());
                Intrinsics.checkNotNullExpressionValue(createFixedSize3, "createFixedSize(inputDet… inputDetails.dataType())");
                createFixedSize3.loadBuffer(allocateDirect);
                TensorBuffer createFixedSize4 = TensorBuffer.createFixedSize(outputTensor2.shape(), outputTensor2.dataType());
                Intrinsics.checkNotNullExpressionValue(createFixedSize4, "createFixedSize(outputDe…outputDetails.dataType())");
                interpreter4.run(createFixedSize3.getBuffer(), createFixedSize4.getBuffer());
                for (int i3 = 0; i3 < 10; i3++) {
                    fArr[i3] = fArr[i3] + createFixedSize4.getFloatArray()[i3];
                }
            }
            return applyHeuristic(fArr);
        }
        return 0;
    }

    private final int applyHeuristic(final float[] fArr) {
        Integer num;
        Iterator<Integer> it = ArraysKt.getIndices(fArr).iterator();
        if (it.hasNext()) {
            Integer next = it.next();
            if (it.hasNext()) {
                float f = fArr[next.intValue()];
                do {
                    Integer next2 = it.next();
                    float f2 = fArr[next2.intValue()];
                    if (Float.compare(f, f2) < 0) {
                        next = next2;
                        f = f2;
                    }
                } while (it.hasNext());
                num = next;
            } else {
                num = next;
            }
        } else {
            num = null;
        }
        Integer num2 = num;
        int intValue = num2 != null ? num2.intValue() : 0;
        int intValue2 = ((Number) CollectionsKt.sortedWith(ArraysKt.getIndices(fArr), new Comparator() { // from class: org.informatika.sirekap.support.vision.Vision$applyHeuristic$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Float.valueOf(fArr[((Number) t2).intValue()]), Float.valueOf(fArr[((Number) t).intValue()]));
            }
        }).get(1)).intValue();
        if (intValue != 0 || fArr[0] >= 10.0d) {
            if (intValue != 1 || fArr[1] >= 14.5d) {
                if (intValue == 3 && fArr[3] < 10.0d && fArr[9] > 3.0d) {
                    return 9;
                }
            } else if (fArr[intValue2] > 0.0d) {
                return intValue2;
            }
        } else if (intValue2 == 8) {
            return intValue2;
        }
        return intValue;
    }

    private final void processOmr(BoxGroup boxGroup, Rect rect, Mat mat, Mat mat2) {
        Mat roiOmr = mat.submat(rect.y, rect.y + rect.height, rect.x, rect.x + rect.width);
        Intrinsics.checkNotNullExpressionValue(roiOmr, "roiOmr");
        int scanAreaOmr = scanAreaOmr(roiOmr, mat, mat2, rect);
        boxGroup.addPrediction(scanAreaOmr);
        drawPrediction(scanAreaOmr, rect.x, rect.y, mat2);
    }

    private final void drawPrediction(int i, int i2, int i3, Mat mat) {
        double d = this.fontScale;
        Imgproc.putText(mat, String.valueOf(i), new Point(i2 + (7 * d), i3 + (d * 37)), 0, 0.5d, COLOR_BLUE, 1);
    }

    private final int scanAreaOmr(Mat mat, Mat mat2, Mat mat3, Rect rect) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Mat clone = mat.clone();
        int i7 = rect.x;
        int i8 = rect.y;
        int ceil = (int) Math.ceil(mat.width() / 63.5d);
        int max = (int) Math.max(Math.min(clone.width(), clone.height()) * 0.4d, 3.0d);
        if (max % 2 == 0) {
            max++;
        }
        Mat mat4 = new Mat();
        Mat mat5 = mat4;
        Imgproc.adaptiveThreshold(clone, mat4, 255.0d, 1, 1, max, (int) (i * 0.2d));
        ArrayList arrayList = new ArrayList();
        Imgproc.findContours(mat5, arrayList, new Mat(), 3, 2);
        double d = mat2.size().width * CIRCLE_TO_CROPPED_WIDTH_RATIO;
        double pow = Math.pow(d * 0.5d, 2.0d) * 3.141592653589793d;
        double d2 = pow * 0.30000000000000004d;
        double d3 = pow * 1.7d;
        double d4 = 0.30000000000000004d * d;
        double d5 = d * 1.7d;
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Rect boundingRect = Imgproc.boundingRect((MatOfPoint) it.next());
            int i9 = boundingRect.width;
            int i10 = boundingRect.height;
            Iterator it2 = it;
            int i11 = i8;
            int i12 = ceil;
            double pow2 = Math.pow(((i9 + i10) / 2.0d) * 0.5d, 2.0d) * 3.141592653589793d;
            if (d2 < pow2 && pow2 < d3) {
                double d6 = i9;
                if (d4 <= d6 && d6 <= d5) {
                    double d7 = i10;
                    if (d4 <= d7 && d7 <= d5) {
                        Intrinsics.checkNotNullExpressionValue(boundingRect, "boundingRect");
                        arrayList2.add(boundingRect);
                    }
                }
            }
            it = it2;
            i8 = i11;
            ceil = i12;
        }
        int i13 = i8;
        int i14 = ceil;
        List<Rect> nonMaxSuppression = nonMaxSuppression(arrayList2, 0.2d);
        List<Rect> list = nonMaxSuppression;
        CollectionsKt.sortedWith(list, new Comparator() { // from class: org.informatika.sirekap.support.vision.Vision$scanAreaOmr$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Double.valueOf(((Rect) t).y), Double.valueOf(((Rect) t2).y));
            }
        });
        if (nonMaxSuppression.size() < 10) {
            throw new IllegalArgumentException("Insufficient data to perform OMR circle inference. Detected count: " + nonMaxSuppression.size());
        }
        List take = CollectionsKt.take(list, 10);
        ArrayList arrayList3 = new ArrayList();
        int size = take.size();
        int i15 = 0;
        while (i15 < size) {
            int i16 = ((Rect) take.get(i15)).x;
            int i17 = ((Rect) take.get(i15)).y;
            int i18 = ((Rect) take.get(i15)).width;
            Mat submat = mat5.submat(i17, i17 + ((Rect) take.get(i15)).height, i16, i16 + i18);
            Imgproc.rectangle(mat3, new Point(i16 + i7, i17 + i13), new Point(i5 + i18, i6 + i4), COLOR_GREEN, i14);
            arrayList3.add(Double.valueOf(Core.mean(submat).val[0]));
            nonMaxSuppression = nonMaxSuppression;
            i15++;
            mat5 = mat5;
        }
        int selectCircle = selectCircle(arrayList3);
        Rect rect2 = nonMaxSuppression.get(selectCircle);
        int i19 = rect2.x;
        Imgproc.rectangle(mat3, new Point(i19 + i7, rect2.y + i13), new Point(i2 + rect2.width, i3 + rect2.height), COLOR_RED, i14);
        return 9 - selectCircle;
    }

    private final int selectCircle(List<Double> list) {
        if (list.isEmpty()) {
            return 0;
        }
        double averageOfDouble = CollectionsKt.averageOfDouble(list);
        double calculateStandardDeviation = calculateStandardDeviation(list) + averageOfDouble;
        double d = averageOfDouble * 0.2d;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            double doubleValue = list.get(i).doubleValue();
            if (doubleValue > calculateStandardDeviation) {
                if (i == list.size() - 1) {
                    if (Math.abs(list.get(i - 1).doubleValue() - doubleValue) > d) {
                        return i;
                    }
                } else if (Math.abs(list.get(i + 1).doubleValue() - doubleValue) > d) {
                    return i;
                }
            }
        }
        return 0;
    }

    private final double calculateStandardDeviation(List<Double> list) {
        List<Double> list2 = list;
        double averageOfDouble = CollectionsKt.averageOfDouble(list2);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (Number number : list2) {
            arrayList.add(Double.valueOf(Math.pow(number.doubleValue() - averageOfDouble, 2.0d)));
        }
        return Math.sqrt(CollectionsKt.averageOfDouble(arrayList));
    }

    private final List<Rect> inferMissingCircles(List<? extends Rect> list) {
        List subList;
        ArrayList arrayList = new ArrayList();
        IntRange until = RangesKt.until(0, list.size() - 1);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            arrayList2.add(Integer.valueOf(list.get(nextInt + 1).y - list.get(nextInt).y));
        }
        ArrayList arrayList3 = arrayList2;
        int size = arrayList3.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            int size2 = arrayList3.size();
            int i3 = i2;
            while (i3 < size2) {
                int i4 = i3 + 1;
                if (inferMissingCircles$isConsistent(0.2d, arrayList3.subList(i, i4))) {
                    double sumOfInt = CollectionsKt.sumOfInt(subList) / subList.size();
                    if (1 <= i) {
                        int i5 = 1;
                        while (true) {
                            arrayList.add(0, new Rect(list.get(i).x, (int) (list.get(i).y - (i5 * sumOfInt)), list.get(i).width, list.get(i).height));
                            if (i5 == i) {
                                break;
                            }
                            i5++;
                        }
                    }
                    int size3 = arrayList3.size() - i3;
                    for (int i6 = 1; i6 < size3; i6++) {
                        arrayList.add(new Rect(list.get(i3).x, (int) (list.get(i3).y + (i6 * sumOfInt)), list.get(i3).width, list.get(i3).height));
                    }
                    return arrayList;
                }
                i3 = i4;
            }
            i = i2;
        }
        return arrayList;
    }

    private static final boolean inferMissingCircles$isConsistent(double d, List<Integer> list) {
        boolean z;
        if (list.size() < 2) {
            return false;
        }
        List<Integer> list2 = list;
        double averageOfInt = CollectionsKt.averageOfInt(list2);
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            for (Number number : list2) {
                if (Math.abs(number.intValue() - averageOfInt) <= d * averageOfInt) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (!z) {
                    return false;
                }
            }
        }
        return true;
    }

    private final List<BoxGroup> getBoxesCoordinates(Mat mat, FormConfig formConfig, Mat mat2) {
        int width = mat.width();
        int width2 = formConfig.getWidth();
        int height = mat.height();
        return extractBoxesDict(formConfig, applyAdaptiveThreshold(mat), width / width2, height / formConfig.getHeight(), mat2);
    }

    private final List<BoxGroup> extractBoxesDict(FormConfig formConfig, Mat mat, double d, double d2, Mat mat2) {
        ArrayList arrayList = new ArrayList();
        for (FormConfig.ROI roi : formConfig.getRegionOfInterest()) {
            processOcrRegion(roi, arrayList, mat, d, d2, roi.getType(), mat2);
        }
        return arrayList;
    }

    private final void processOcrRegion(FormConfig.ROI roi, List<BoxGroup> list, Mat mat, double d, double d2, String str, Mat mat2) {
        double height = roi.getHeight() * d2;
        double width = roi.getWidth() * d;
        double d3 = 1;
        double d4 = d3 - BOX_SIZE_TOLERANCE_OFFSET;
        double d5 = d4 * width;
        double d6 = d3 + BOX_SIZE_TOLERANCE_OFFSET;
        double d7 = d6 * width;
        double d8 = d4 * height;
        double d9 = d6 * height;
        Double omrBoundMultiplier = roi.getOmrBoundMultiplier();
        double doubleValue = omrBoundMultiplier != null ? omrBoundMultiplier.doubleValue() : 0.0d;
        Mat combinedLines = mat.clone();
        for (FormConfig.Field field : roi.getFields()) {
            Intrinsics.checkNotNullExpressionValue(combinedLines, "combinedLines");
            double d10 = height;
            double d11 = d9;
            Mat mat3 = combinedLines;
            processField(field, list, mat3, (int) width, (int) height, (int) d5, (int) d7, (int) d8, (int) d11, d, d2, str, doubleValue, mat2);
            combinedLines = mat3;
            height = d10;
            d8 = d8;
            d7 = d7;
            d5 = d5;
            width = width;
            d9 = d11;
        }
    }

    private final void processField(FormConfig.Field field, List<BoxGroup> list, Mat mat, int i, int i2, int i3, int i4, int i5, int i6, double d, double d2, String str, double d3, Mat mat2) {
        Rect rect;
        List<List<Integer>> coordinates = field.getCoordinates();
        ArrayList arrayList = new ArrayList();
        for (List<Integer> list2 : coordinates) {
            int doubleValue = (int) (list2.get(0).doubleValue() * d);
            int doubleValue2 = (int) (list2.get(1).doubleValue() * d2);
            double d4 = i;
            int max = Math.max(0, doubleValue - ((int) (d4 * ROI_SIZE_NEGATIVE_OFFSET)));
            double d5 = i2;
            int max2 = Math.max(0, doubleValue2 - ((int) (ROI_SIZE_NEGATIVE_OFFSET * d5)));
            Rect rect2 = new Rect(max, max2, Math.min(mat.width(), ((int) (d4 * ROI_SIZE_POSITIVE_OFFSET)) + doubleValue) - max, Math.min(mat.height(), ((int) (d5 * ROI_SIZE_POSITIVE_OFFSET)) + doubleValue2) - max2);
            List<Rect> detectBoxesInRoi = detectBoxesInRoi(new Mat(mat, rect2), i3, i4, i5, i6, rect2);
            if (detectBoxesInRoi.isEmpty()) {
                arrayList.add(new Rect(doubleValue, doubleValue2, i, i2));
                Imgproc.rectangle(mat2, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), COLOR_RED, 1);
            } else {
                arrayList.addAll(detectBoxesInRoi);
                Imgproc.rectangle(mat2, new Point(detectBoxesInRoi.get(0).x, detectBoxesInRoi.get(0).y), new Point(detectBoxesInRoi.get(0).x + detectBoxesInRoi.get(0).width, detectBoxesInRoi.get(0).y + detectBoxesInRoi.get(0).height), COLOR_BLUE, 1);
            }
        }
        if (Intrinsics.areEqual(str, "ocr")) {
            groupAndAddBoxesByField$default(this, field, list, arrayList, str, null, 16, null);
        } else {
            groupAndAddBoxesByField(field, list, arrayList, str, calculateOmrBoxes(arrayList, d3, mat2));
        }
    }

    private final List<Rect> calculateOmrBoxes(List<? extends Rect> list, double d, Mat mat) {
        ArrayList arrayList = new ArrayList();
        for (Rect rect : list) {
            int i = rect.x;
            int i2 = rect.y;
            int i3 = rect.width;
            int i4 = rect.height;
            Rect rect2 = new Rect(i, i2 + i4, i3, (int) (i4 * d));
            arrayList.add(rect2);
            Imgproc.rectangle(mat, rect2.tl(), rect2.br(), COLOR_BLUE, 1);
        }
        return arrayList;
    }

    private final List<Rect> detectBoxesInRoi(Mat mat, int i, int i2, int i3, int i4, Rect rect) {
        ArrayList<MatOfPoint> arrayList = new ArrayList();
        Imgproc.findContours(mat, arrayList, new Mat(), 3, 2);
        ArrayList arrayList2 = new ArrayList();
        for (MatOfPoint matOfPoint : arrayList) {
            Rect boundingRect = Imgproc.boundingRect(matOfPoint);
            int i5 = boundingRect.width;
            boolean z = false;
            if (i <= i5 && i5 <= i2) {
                z = true;
            }
            if (z && i3 <= boundingRect.height && boundingRect.height <= i4) {
                arrayList2.add(new Rect(boundingRect.x + rect.x, boundingRect.y + rect.y, boundingRect.width, boundingRect.height));
            }
        }
        return nonMaxSuppression(arrayList2, ROI_SIZE_NEGATIVE_OFFSET);
    }

    private final List<Rect> nonMaxSuppression(List<? extends Rect> list, double d) {
        List list2;
        if (list.isEmpty()) {
            return new ArrayList();
        }
        List<? extends Rect> list3 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        for (Rect rect : list3) {
            arrayList.add(Double.valueOf(rect.x));
        }
        double[] doubleArray = CollectionsKt.toDoubleArray(arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        for (Rect rect2 : list3) {
            arrayList2.add(Double.valueOf(rect2.y));
        }
        double[] doubleArray2 = CollectionsKt.toDoubleArray(arrayList2);
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        for (Rect rect3 : list3) {
            arrayList3.add(Double.valueOf(rect3.x + rect3.width));
        }
        double[] doubleArray3 = CollectionsKt.toDoubleArray(arrayList3);
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        for (Rect rect4 : list3) {
            arrayList4.add(Double.valueOf(rect4.y + rect4.height));
        }
        final double[] doubleArray4 = CollectionsKt.toDoubleArray(arrayList4);
        int[] intArray = CollectionsKt.toIntArray(CollectionsKt.sortedWith(CollectionsKt.getIndices(list), new Comparator() { // from class: org.informatika.sirekap.support.vision.Vision$nonMaxSuppression$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Double.valueOf(doubleArray4[((Number) t).intValue()]), Double.valueOf(doubleArray4[((Number) t2).intValue()]));
            }
        }));
        ArrayList arrayList5 = new ArrayList();
        List<Integer> mutableList = ArraysKt.toMutableList(intArray);
        while (!mutableList.isEmpty()) {
            int size = mutableList.size() - 1;
            int intValue = mutableList.get(size).intValue();
            arrayList5.add(list.get(intValue));
            int i = 0;
            List mutableListOf = CollectionsKt.mutableListOf(Integer.valueOf(size));
            int i2 = 0;
            while (i2 < size) {
                int intValue2 = mutableList.get(i2).intValue();
                ArrayList arrayList6 = arrayList5;
                int i3 = size;
                List list4 = mutableListOf;
                int i4 = i2;
                List<Integer> list5 = mutableList;
                double[] dArr = doubleArray2;
                int i5 = intValue;
                if ((Math.max(0.0d, Math.min(doubleArray3[intValue], doubleArray3[intValue2]) - Math.max(doubleArray[intValue], doubleArray[intValue2])) * Math.max(0.0d, Math.min(doubleArray4[intValue], doubleArray4[intValue2]) - Math.max(doubleArray2[intValue], doubleArray2[intValue2]))) / ((doubleArray3[intValue2] - doubleArray[intValue2]) * (doubleArray4[intValue2] - dArr[intValue2])) > d) {
                    list2 = list4;
                    list2.add(Integer.valueOf(i4));
                } else {
                    list2 = list4;
                }
                i2 = i4 + 1;
                mutableListOf = list2;
                arrayList5 = arrayList6;
                size = i3;
                doubleArray2 = dArr;
                mutableList = list5;
                intValue = i5;
            }
            double[] dArr2 = doubleArray2;
            ArrayList arrayList7 = arrayList5;
            List list6 = mutableListOf;
            ArrayList arrayList8 = new ArrayList();
            for (Object obj : mutableList) {
                int i6 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ((Number) obj).intValue();
                if (!list6.contains(Integer.valueOf(i))) {
                    arrayList8.add(obj);
                }
                i = i6;
            }
            mutableList = CollectionsKt.toMutableList((Collection) arrayList8);
            arrayList5 = arrayList7;
            doubleArray2 = dArr2;
        }
        return arrayList5;
    }

    /* compiled from: Vision.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\tJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0011J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J=\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0006\u0010\u001c\u001a\u00020\u0015J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0011HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000e¨\u0006\""}, d2 = {"Lorg/informatika/sirekap/support/vision/Vision$BoxGroup;", "", "name", "", "coordinates", "", "Lorg/opencv/core/Rect;", "type", "coordinatesOmr", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;)V", "getCoordinates", "()Ljava/util/List;", "getCoordinatesOmr", "getName", "()Ljava/lang/String;", "predictions", "", "", "getPredictions", "getType", "addPrediction", "", "prediction", "component1", "component2", "component3", "component4", "copy", "deletePredictions", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class BoxGroup {
        private final List<Rect> coordinates;
        private final List<Rect> coordinatesOmr;
        private final String name;
        private final List<Integer> predictions;
        private final String type;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BoxGroup copy$default(BoxGroup boxGroup, String str, List list, String str2, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = boxGroup.name;
            }
            if ((i & 2) != 0) {
                list = boxGroup.coordinates;
            }
            if ((i & 4) != 0) {
                str2 = boxGroup.type;
            }
            if ((i & 8) != 0) {
                list2 = boxGroup.coordinatesOmr;
            }
            return boxGroup.copy(str, list, str2, list2);
        }

        public final String component1() {
            return this.name;
        }

        public final List<Rect> component2() {
            return this.coordinates;
        }

        public final String component3() {
            return this.type;
        }

        public final List<Rect> component4() {
            return this.coordinatesOmr;
        }

        public final BoxGroup copy(String name, List<? extends Rect> coordinates, String type, List<? extends Rect> coordinatesOmr) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(coordinates, "coordinates");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(coordinatesOmr, "coordinatesOmr");
            return new BoxGroup(name, coordinates, type, coordinatesOmr);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof BoxGroup) {
                BoxGroup boxGroup = (BoxGroup) obj;
                return Intrinsics.areEqual(this.name, boxGroup.name) && Intrinsics.areEqual(this.coordinates, boxGroup.coordinates) && Intrinsics.areEqual(this.type, boxGroup.type) && Intrinsics.areEqual(this.coordinatesOmr, boxGroup.coordinatesOmr);
            }
            return false;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.coordinates.hashCode()) * 31) + this.type.hashCode()) * 31) + this.coordinatesOmr.hashCode();
        }

        public String toString() {
            String str = this.name;
            List<Rect> list = this.coordinates;
            String str2 = this.type;
            return "BoxGroup(name=" + str + ", coordinates=" + list + ", type=" + str2 + ", coordinatesOmr=" + this.coordinatesOmr + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public BoxGroup(String name, List<? extends Rect> coordinates, String type, List<? extends Rect> coordinatesOmr) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(coordinates, "coordinates");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(coordinatesOmr, "coordinatesOmr");
            this.name = name;
            this.coordinates = coordinates;
            this.type = type;
            this.coordinatesOmr = coordinatesOmr;
            this.predictions = new ArrayList();
        }

        public final String getName() {
            return this.name;
        }

        public final List<Rect> getCoordinates() {
            return this.coordinates;
        }

        public final String getType() {
            return this.type;
        }

        public final List<Rect> getCoordinatesOmr() {
            return this.coordinatesOmr;
        }

        public final List<Integer> getPredictions() {
            return this.predictions;
        }

        public final void addPrediction(int i) {
            this.predictions.add(Integer.valueOf(i));
        }

        public final void deletePredictions() {
            this.predictions.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void groupAndAddBoxesByField$default(Vision vision, FormConfig.Field field, List list, List list2, String str, List list3, int i, Object obj) {
        List<? extends Rect> list4 = list3;
        if ((i & 16) != 0) {
            list4 = null;
        }
        vision.groupAndAddBoxesByField(field, list, list2, str, list4);
    }

    private final void groupAndAddBoxesByField(FormConfig.Field field, List<BoxGroup> list, List<? extends Rect> list2, String str, List<? extends Rect> list3) {
        BoxGroup boxGroup;
        String name = field.getName();
        int size = field.getCoordinates().size();
        if (list2.size() < size) {
            throw new IllegalArgumentException("Insufficient number of '" + str + "' boxes found for field: " + name);
        }
        boolean z = false;
        List<? extends Rect> subList = list2.subList(0, size);
        List<? extends Rect> list4 = list3;
        if ((list4 == null || list4.isEmpty()) ? true : true) {
            boxGroup = new BoxGroup(name, subList, str, CollectionsKt.emptyList());
        } else {
            boxGroup = new BoxGroup(name, subList, str, list3);
        }
        list.add(boxGroup);
    }

    private final Mat applyAdaptiveThreshold(Mat mat) {
        CLAHE createCLAHE = Imgproc.createCLAHE(adjustClipLimit(calculateImageContrast(mat)), new Size(RangesKt.coerceAtLeast(mat.width() * 0.02d, 1.0d), RangesKt.coerceAtLeast(mat.width() * 0.02d, 1.0d)));
        Mat mat2 = new Mat();
        createCLAHE.apply(mat, mat2);
        int max = Math.max((int) (Math.min(mat2.rows(), mat2.cols()) * 0.01d), 3);
        if (max % 2 == 0) {
            max++;
        }
        int i = max;
        int max2 = Math.max((int) (i * 0.1d), 1);
        Mat mat3 = new Mat();
        Imgproc.adaptiveThreshold(mat2, mat3, 255.0d, 1, 1, i, max2);
        return mat3;
    }

    private final double calculateImageContrast(Mat mat) {
        MatOfDouble matOfDouble = new MatOfDouble();
        Core.meanStdDev(mat, new MatOfDouble(), matOfDouble);
        return matOfDouble.toArray()[0];
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        List<Interpreter> list = this.interpreters;
        Interpreter interpreter = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("interpreters");
            list = null;
        }
        for (Interpreter interpreter2 : list) {
            interpreter2.close();
        }
        Interpreter interpreter3 = this.interpretersBlank;
        if (interpreter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("interpretersBlank");
        } else {
            interpreter = interpreter3;
        }
        interpreter.close();
    }

    /* compiled from: Vision.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/support/vision/Vision$Companion;", "", "()V", "BOX_SIZE_TOLERANCE_OFFSET", "", "CIRCLE_TO_CROPPED_WIDTH_RATIO", "COLOR_BLUE", "Lorg/opencv/core/Scalar;", "COLOR_GREEN", "COLOR_RED", "OMR_BOX_SIZE_TOLERANCE_OFFSET", "ROI_SIZE_NEGATIVE_OFFSET", "ROI_SIZE_POSITIVE_OFFSET", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
