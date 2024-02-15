package org.informatika.sirekap.ui.autocapture;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.apache.xmpbox.type.ThumbnailType;
import org.informatika.sirekap.model.PolygonPoints;
import org.informatika.sirekap.support.templatematching.CheckerboardCropping;
import org.informatika.sirekap.support.templatematching.ScanUtils;
import org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel;
import org.informatika.sirekap.ui.imageCrop.ImageCropConstants;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Point;

/* compiled from: ImageProcessingUseCase.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0012\n\u0002\u0010\u0006\n\u0002\b\u0003\u0018\u0000 B2\u00020\u0001:\u0002BCB0\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\u0010\u000bJ\u0006\u00101\u001a\u00020\nJ\u0006\u00102\u001a\u00020\nJ&\u00103\u001a\u00020\n2\u0006\u00104\u001a\u00020\u001e2\u0006\u00105\u001a\u00020\r2\u0006\u00106\u001a\u00020\r2\u0006\u00107\u001a\u00020\u0014J\b\u00108\u001a\u00020\nH\u0002J\u0006\u00109\u001a\u00020\nJ\u000e\u0010:\u001a\u00020\n2\u0006\u0010;\u001a\u00020\u0014J\u001e\u0010<\u001a\u00020\n2\u0006\u0010=\u001a\u00020\u00182\u0006\u0010>\u001a\u00020\u00182\u0006\u0010?\u001a\u00020\u0018J\u0018\u0010@\u001a\u00020\n2\u0006\u00105\u001a\u00020A2\u0006\u00106\u001a\u00020AH\u0002R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u001c\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010!R\u0019\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\u0013¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016R\u0010\u0010%\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010&\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010)\u001a\u0010\u0012\f\u0012\n **\u0004\u0018\u00010\u00140\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0016R\u000e\u0010+\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R%\u0010-\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001b\u0018\u00010.0\u0013¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0016R)\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/ImageProcessingUseCase;", "", "context", "Landroid/content/Context;", "showHint", "Lkotlin/Function1;", "Lorg/informatika/sirekap/ui/autocapture/AutoCaptureViewModel$ScanHint;", "Lkotlin/ParameterName;", "name", "scanHint", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "aprilTagCode", "", "Ljava/lang/Integer;", "aprilTagResult", "Lkotlin/Pair;", "", "autoCropResult", "Landroidx/lifecycle/MutableLiveData;", "", "getAutoCropResult", "()Landroidx/lifecycle/MutableLiveData;", "correctedImageUri", "Landroid/net/Uri;", "correctedPoints", "", "Lorg/opencv/core/Point;", "croppedImageUri", "displayImageBitmap", "Landroid/graphics/Bitmap;", "getDisplayImageBitmap", "setDisplayImageBitmap", "(Landroidx/lifecycle/MutableLiveData;)V", "finishPhotoResult", "Lorg/informatika/sirekap/ui/autocapture/ImageProcessingUseCase$FinishPhotoResult;", "getFinishPhotoResult", "fullSizeBitmap", "fullSizePoints", "heightRatio", "", "isPictureTaken", "kotlin.jvm.PlatformType", "markerSize", "originalImageUri", "polygonPoints", "", "getPolygonPoints", "widthRatio", "finishAutoCrop", "finishPhoto", "onPictureCaptured", "bitmap", ThumbnailType.WIDTH, ThumbnailType.HEIGHT, "skipManualCrop", "pickOuterPoints", "processFinalImage", "setupCropView", TypedValues.Custom.S_BOOLEAN, "setupOriginalImagePath", "uri", "croppedUri", "correctedUri", "transformPointsOutsideMarker", "", "Companion", "FinishPhotoResult", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ImageProcessingUseCase {
    public static final Companion Companion = new Companion(null);
    private static final boolean SAVE_DETECTION_RESULT = false;
    public static final String TAG = "ImageProcessingUseCase";
    private Integer aprilTagCode;
    private Pair<String, Integer> aprilTagResult;
    private final MutableLiveData<Boolean> autoCropResult;
    private final Context context;
    private Uri correctedImageUri;
    private List<? extends Point> correctedPoints;
    private Uri croppedImageUri;
    private MutableLiveData<Bitmap> displayImageBitmap;
    private final MutableLiveData<FinishPhotoResult> finishPhotoResult;
    private Bitmap fullSizeBitmap;
    private List<? extends Point> fullSizePoints;
    private float heightRatio;
    private final MutableLiveData<Boolean> isPictureTaken;
    private int markerSize;
    private Uri originalImageUri;
    private final MutableLiveData<Map<Integer, Point>> polygonPoints;
    private final Function1<AutoCaptureViewModel.ScanHint, Unit> showHint;
    private float widthRatio;

    /* JADX WARN: Multi-variable type inference failed */
    public ImageProcessingUseCase(Context context, Function1<? super AutoCaptureViewModel.ScanHint, Unit> showHint) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(showHint, "showHint");
        this.context = context;
        this.showHint = showHint;
        this.displayImageBitmap = new MutableLiveData<>(null);
        this.isPictureTaken = new MutableLiveData<>(false);
        this.polygonPoints = new MutableLiveData<>(null);
        this.autoCropResult = new MutableLiveData<>(null);
        this.finishPhotoResult = new MutableLiveData<>(null);
    }

    public final void setupOriginalImagePath(Uri uri, Uri croppedUri, Uri correctedUri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(croppedUri, "croppedUri");
        Intrinsics.checkNotNullParameter(correctedUri, "correctedUri");
        this.originalImageUri = uri;
        this.croppedImageUri = croppedUri;
        this.correctedImageUri = correctedUri;
    }

    public final MutableLiveData<Bitmap> getDisplayImageBitmap() {
        return this.displayImageBitmap;
    }

    public final void setDisplayImageBitmap(MutableLiveData<Bitmap> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.displayImageBitmap = mutableLiveData;
    }

    public final MutableLiveData<Boolean> isPictureTaken() {
        return this.isPictureTaken;
    }

    public final void setupCropView(boolean z) {
        this.isPictureTaken.postValue(Boolean.valueOf(z));
    }

    public final MutableLiveData<Map<Integer, Point>> getPolygonPoints() {
        return this.polygonPoints;
    }

    public final MutableLiveData<Boolean> getAutoCropResult() {
        return this.autoCropResult;
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x0233 A[Catch: Exception -> 0x0289, LOOP:0: B:138:0x022d->B:140:0x0233, LOOP_END, TryCatch #2 {Exception -> 0x0289, blocks: (B:81:0x0012, B:83:0x0018, B:85:0x002b, B:87:0x003d, B:89:0x0047, B:92:0x004d, B:93:0x0071, B:95:0x0075, B:97:0x0079, B:99:0x0085, B:101:0x0095, B:110:0x00a2, B:112:0x00c0, B:114:0x00cd, B:116:0x0151, B:120:0x017e, B:124:0x01a9, B:126:0x01f7, B:128:0x01fd, B:131:0x0204, B:133:0x020a, B:137:0x021c, B:138:0x022d, B:140:0x0233, B:141:0x0259, B:144:0x0270, B:145:0x0274, B:135:0x0214, B:147:0x027e, B:100:0x0087), top: B:152:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x026e A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onPictureCaptured(android.graphics.Bitmap r32, int r33, int r34, boolean r35) {
        /*
            Method dump skipped, instructions count: 670
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.autocapture.ImageProcessingUseCase.onPictureCaptured(android.graphics.Bitmap, int, int, boolean):void");
    }

    public final void finishAutoCrop() {
        this.autoCropResult.postValue(null);
    }

    /* compiled from: ImageProcessingUseCase.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000bJ<\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/ImageProcessingUseCase$FinishPhotoResult;", "", "isSuccess", "", "pemilihan", "", "pageNum", "", "aprilTagCode", "(ZLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getAprilTagCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "getPageNum", "getPemilihan", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "(ZLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/informatika/sirekap/ui/autocapture/ImageProcessingUseCase$FinishPhotoResult;", "equals", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class FinishPhotoResult {
        private final Integer aprilTagCode;
        private final boolean isSuccess;
        private final Integer pageNum;
        private final String pemilihan;

        public static /* synthetic */ FinishPhotoResult copy$default(FinishPhotoResult finishPhotoResult, boolean z, String str, Integer num, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = finishPhotoResult.isSuccess;
            }
            if ((i & 2) != 0) {
                str = finishPhotoResult.pemilihan;
            }
            if ((i & 4) != 0) {
                num = finishPhotoResult.pageNum;
            }
            if ((i & 8) != 0) {
                num2 = finishPhotoResult.aprilTagCode;
            }
            return finishPhotoResult.copy(z, str, num, num2);
        }

        public final boolean component1() {
            return this.isSuccess;
        }

        public final String component2() {
            return this.pemilihan;
        }

        public final Integer component3() {
            return this.pageNum;
        }

        public final Integer component4() {
            return this.aprilTagCode;
        }

        public final FinishPhotoResult copy(boolean z, String str, Integer num, Integer num2) {
            return new FinishPhotoResult(z, str, num, num2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof FinishPhotoResult) {
                FinishPhotoResult finishPhotoResult = (FinishPhotoResult) obj;
                return this.isSuccess == finishPhotoResult.isSuccess && Intrinsics.areEqual(this.pemilihan, finishPhotoResult.pemilihan) && Intrinsics.areEqual(this.pageNum, finishPhotoResult.pageNum) && Intrinsics.areEqual(this.aprilTagCode, finishPhotoResult.aprilTagCode);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        public int hashCode() {
            boolean z = this.isSuccess;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.pemilihan;
            int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
            Integer num = this.pageNum;
            int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.aprilTagCode;
            return hashCode2 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            boolean z = this.isSuccess;
            String str = this.pemilihan;
            Integer num = this.pageNum;
            return "FinishPhotoResult(isSuccess=" + z + ", pemilihan=" + str + ", pageNum=" + num + ", aprilTagCode=" + this.aprilTagCode + ")";
        }

        public FinishPhotoResult(boolean z, String str, Integer num, Integer num2) {
            this.isSuccess = z;
            this.pemilihan = str;
            this.pageNum = num;
            this.aprilTagCode = num2;
        }

        public /* synthetic */ FinishPhotoResult(boolean z, String str, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2);
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }

        public final String getPemilihan() {
            return this.pemilihan;
        }

        public final Integer getPageNum() {
            return this.pageNum;
        }

        public final Integer getAprilTagCode() {
            return this.aprilTagCode;
        }
    }

    public final MutableLiveData<FinishPhotoResult> getFinishPhotoResult() {
        return this.finishPhotoResult;
    }

    public final void processFinalImage() {
        OutputStream openOutputStream;
        OutputStream openOutputStream2;
        try {
            if (ScanUtils.INSTANCE.isScanPointsValid(this.fullSizePoints)) {
                if (this.fullSizeBitmap != null) {
                    Mat mat = new Mat();
                    Utils.bitmapToMat(this.fullSizeBitmap, mat);
                    double cols = mat.cols();
                    double rows = mat.rows();
                    transformPointsOutsideMarker(cols, rows);
                    double d = cols / rows;
                    Mat cropResultMat = CheckerboardCropping.cropCoordinate(mat.clone(), this.fullSizePoints, Double.valueOf(d));
                    Intrinsics.checkNotNullExpressionValue(cropResultMat, "cropResultMat");
                    Mat correctedResultMat = CheckerboardCropping.cropCoordinate(mat.clone(), this.correctedPoints, Double.valueOf(d));
                    Intrinsics.checkNotNullExpressionValue(correctedResultMat, "correctedResultMat");
                    mat.release();
                    Bitmap createBitmap = Bitmap.createBitmap(cropResultMat.cols(), cropResultMat.rows(), Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(cropResultMat, createBitmap);
                    ImageCropConstants.croppedImageBitmap = createBitmap;
                    Uri uri = this.croppedImageUri;
                    if (uri != null && (openOutputStream2 = this.context.getContentResolver().openOutputStream(uri)) != null) {
                        OutputStream outputStream = openOutputStream2;
                        Boolean.valueOf(createBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream));
                        CloseableKt.closeFinally(outputStream, null);
                    }
                    cropResultMat.release();
                    Bitmap createBitmap2 = Bitmap.createBitmap(correctedResultMat.cols(), correctedResultMat.rows(), Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(correctedResultMat, createBitmap2);
                    ImageCropConstants.correctedImageBitmap = createBitmap2;
                    Uri uri2 = this.correctedImageUri;
                    if (uri2 != null && (openOutputStream = this.context.getContentResolver().openOutputStream(uri2)) != null) {
                        OutputStream outputStream2 = openOutputStream;
                        Boolean.valueOf(createBitmap2.compress(Bitmap.CompressFormat.JPEG, 100, outputStream2));
                        CloseableKt.closeFinally(outputStream2, null);
                    }
                    correctedResultMat.release();
                    MutableLiveData<FinishPhotoResult> mutableLiveData = this.finishPhotoResult;
                    Pair<String, Integer> pair = this.aprilTagResult;
                    String first = pair != null ? pair.getFirst() : null;
                    Pair<String, Integer> pair2 = this.aprilTagResult;
                    mutableLiveData.postValue(new FinishPhotoResult(true, first, pair2 != null ? pair2.getSecond() : null, this.aprilTagCode));
                    return;
                }
                this.finishPhotoResult.postValue(new FinishPhotoResult(false, null, null, null, 14, null));
                return;
            }
            this.finishPhotoResult.postValue(new FinishPhotoResult(false, null, null, null, 14, null));
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            this.finishPhotoResult.postValue(new FinishPhotoResult(false, null, null, null, 14, null));
        }
    }

    public final void finishPhoto() {
        this.finishPhotoResult.postValue(null);
    }

    private final void transformPointsOutsideMarker(double d, double d2) {
        double d3;
        int i;
        double d4;
        Point point;
        double d5;
        double d6;
        Point point2;
        double d7;
        double d8;
        Point point3;
        double d9;
        double d10;
        Point point4;
        double d11;
        double d12;
        PolygonPoints manualCropPoints = AutoCaptureFragment.Companion.getManualCropPoints();
        ArrayList arrayList = new ArrayList();
        List<? extends Point> list = this.fullSizePoints;
        if (list != null && (point4 = list.get(0)) != null) {
            if (manualCropPoints != null) {
                double d13 = manualCropPoints.getTopLeftPoint().x / this.widthRatio;
                d12 = manualCropPoints.getTopLeftPoint().y / this.heightRatio;
                d11 = d13;
            } else {
                d11 = point4.x;
                d12 = point4.y;
            }
            arrayList.add(new Point(Math.max(0.0d, d11), Math.max(0.0d, d12)));
            point4.x = Math.max(0.0d, d11 - (this.markerSize * 2));
            point4.y = Math.max(0.0d, d12 - (this.markerSize * 2));
        }
        List<? extends Point> list2 = this.fullSizePoints;
        if (list2 == null || (point3 = list2.get(1)) == null) {
            d3 = d;
            i = 2;
        } else {
            if (manualCropPoints != null) {
                d9 = manualCropPoints.getTopRightPoint().x / this.widthRatio;
                d10 = manualCropPoints.getTopRightPoint().y / this.heightRatio;
            } else {
                d9 = point3.x;
                d10 = point3.y;
            }
            arrayList.add(new Point(Math.min(d, d9), Math.max(0.0d, d10)));
            i = 2;
            d3 = d;
            point3.x = Math.min(d3, d9 + (this.markerSize * 2));
            point3.y = Math.max(0.0d, d10 - (this.markerSize * 2));
        }
        List<? extends Point> list3 = this.fullSizePoints;
        if (list3 == null || (point2 = list3.get(i)) == null) {
            d4 = d2;
        } else {
            if (manualCropPoints != null) {
                d7 = manualCropPoints.getBottomRightPoint().x / this.widthRatio;
                d8 = manualCropPoints.getBottomRightPoint().y / this.heightRatio;
            } else {
                d7 = point2.x;
                d8 = point2.y;
            }
            d4 = d2;
            arrayList.add(new Point(Math.min(d3, d7), Math.min(d4, d8)));
            point2.x = Math.min(d, d7 + (this.markerSize * 2));
            point2.y = Math.min(d4, d8 + (this.markerSize * 2));
        }
        List<? extends Point> list4 = this.fullSizePoints;
        if (list4 != null && (point = list4.get(3)) != null) {
            if (manualCropPoints != null) {
                d5 = manualCropPoints.getBottomLeftPoint().x / this.widthRatio;
                d6 = manualCropPoints.getBottomLeftPoint().y / this.heightRatio;
            } else {
                d5 = point.x;
                d6 = point.y;
            }
            arrayList.add(new Point(Math.max(0.0d, d5), Math.min(d4, d6)));
            point.x = Math.max(0.0d, d5 - (this.markerSize * 2));
            point.y = Math.min(d4, d6 + (this.markerSize * 2));
        }
        this.correctedPoints = arrayList;
        Log.wtf(TAG, "Adjusted Crop Points : " + this.fullSizePoints);
        AutoCaptureFragment.Companion.setManualCropPoints(null);
    }

    private final void pickOuterPoints() {
        List<? extends Point> list;
        List<? extends Point> list2 = this.fullSizePoints;
        if (list2 != null) {
            double coerceAtMost = RangesKt.coerceAtMost(RangesKt.coerceAtMost(list2.get(0).x, list2.get(1).x), RangesKt.coerceAtMost(list2.get(2).x, list2.get(3).x));
            double coerceAtMost2 = RangesKt.coerceAtMost(RangesKt.coerceAtMost(list2.get(0).y, list2.get(1).y), RangesKt.coerceAtMost(list2.get(2).y, list2.get(3).y));
            double coerceAtLeast = RangesKt.coerceAtLeast(RangesKt.coerceAtLeast(list2.get(0).x, list2.get(1).x), RangesKt.coerceAtLeast(list2.get(2).x, list2.get(3).x));
            double coerceAtLeast2 = RangesKt.coerceAtLeast(RangesKt.coerceAtLeast(list2.get(0).y, list2.get(1).y), RangesKt.coerceAtLeast(list2.get(2).y, list2.get(3).y));
            list = CollectionsKt.listOf((Object[]) new Point[]{new Point(coerceAtMost, coerceAtMost2), new Point(coerceAtLeast, coerceAtMost2), new Point(coerceAtLeast, coerceAtLeast2), new Point(coerceAtMost, coerceAtLeast2)});
        } else {
            list = null;
        }
        this.fullSizePoints = list;
    }

    /* compiled from: ImageProcessingUseCase.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/ImageProcessingUseCase$Companion;", "", "()V", "SAVE_DETECTION_RESULT", "", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
