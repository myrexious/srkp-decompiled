package org.informatika.sirekap.ui.autocapture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.shapes.PathShape;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.JobKt__JobKt;
import org.apache.xmpbox.type.ResourceEventType;
import org.apache.xmpbox.type.ThumbnailType;
import org.informatika.sirekap.support.BitmapUtilsKt;
import org.informatika.sirekap.support.CameraUtilsKt;
import org.informatika.sirekap.support.templatematching.AprilTagConfig;
import org.informatika.sirekap.support.templatematching.ImageDetectionProperties;
import org.informatika.sirekap.support.templatematching.ScanUtils;
import org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel;
import org.opencv.core.Point;
import org.opencv.core.Size;

/* compiled from: ScanSurfaceView.kt */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 a2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001aB%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u000e\u00100\u001a\u0002012\u0006\u00102\u001a\u000203J\b\u00104\u001a\u000201H\u0002J\b\u00105\u001a\u000201H\u0002J\b\u00106\u001a\u000201H\u0002J&\u00107\u001a\u0002012\f\u00108\u001a\b\u0012\u0004\u0012\u00020:092\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\tH\u0002J\b\u0010>\u001a\u000201H\u0002J\u0006\u0010?\u001a\u000201J\u001a\u0010@\u001a\u0002012\b\u0010A\u001a\u0004\u0018\u00010\r2\u0006\u0010B\u001a\u00020\tH\u0016J0\u0010C\u001a\u0002012\u0006\u0010D\u001a\u00020\u00172\u0006\u0010E\u001a\u00020\t2\u0006\u0010F\u001a\u00020\t2\u0006\u0010G\u001a\u00020\t2\u0006\u0010H\u001a\u00020\tH\u0014J\u0018\u0010I\u001a\u0002012\u0006\u0010J\u001a\u00020\t2\u0006\u0010K\u001a\u00020\tH\u0014J\u0010\u0010L\u001a\u0002012\u0006\u0010M\u001a\u00020NH\u0016J\b\u0010O\u001a\u000201H\u0002J\u0010\u0010P\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002J \u0010Q\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020SH\u0002J\u0006\u0010U\u001a\u000201J\b\u0010V\u001a\u000201H\u0002J\b\u0010W\u001a\u000201H\u0002J\b\u0010X\u001a\u000201H\u0002J\b\u0010Y\u001a\u000201H\u0002J\b\u0010Z\u001a\u000201H\u0002J(\u0010[\u001a\u0002012\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010_\u001a\u0002012\u0006\u0010\\\u001a\u00020]H\u0016J\u0010\u0010`\u001a\u0002012\u0006\u0010\\\u001a\u00020]H\u0016R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0018\u00010'R\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006b"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/ScanSurfaceView;", "Landroid/widget/FrameLayout;", "Landroid/view/SurfaceHolder$Callback;", "Landroid/hardware/SensorEventListener;", "activity", "Landroid/app/Activity;", "autoCaptureViewModel", "Lorg/informatika/sirekap/ui/autocapture/AutoCaptureViewModel;", ThumbnailType.WIDTH, "", ThumbnailType.HEIGHT, "(Landroid/app/Activity;Lorg/informatika/sirekap/ui/autocapture/AutoCaptureViewModel;II)V", "accelerometerSensor", "Landroid/hardware/Sensor;", "autoCaptureTimer", "Landroid/os/CountDownTimer;", "camera", "Landroid/hardware/Camera;", "cameraScope", "Lkotlinx/coroutines/CoroutineScope;", "deviceAcceleration", "", "isAutoCaptureScheduled", "", "isCapturing", "isProcessing", "lastDetectedAprilTag", "Lkotlin/Pair;", "", "mShutterCallBack", "Landroid/hardware/Camera$ShutterCallback;", "pictureCallback", "Landroid/hardware/Camera$PictureCallback;", "prevAccX", "prevAccY", "prevAccZ", "previewCallback", "Landroid/hardware/Camera$PreviewCallback;", "previewSize", "Landroid/hardware/Camera$Size;", "scanCanvasView", "Lorg/informatika/sirekap/ui/autocapture/ScanCanvasView;", "sensorManager", "Landroid/hardware/SensorManager;", "surfaceView", "Landroid/view/SurfaceView;", "vHeight", "vWidth", "autoCapture", "", "scanHint", "Lorg/informatika/sirekap/ui/autocapture/AutoCaptureViewModel$ScanHint;", "cancelAutoCapture", "clearAndInvalidateCanvas", "clearCanvas", "drawEdgeOMR", "points", "", "Lorg/opencv/core/Point;", "stdSize", "Lorg/opencv/core/Size;", "previewArea", "invalidateCanvas", "manualCapture", "onAccuracyChanged", "sensor", "accuracy", "onLayout", ResourceEventType.CHANGED, OperatorName.LINE_TO, "t", PDPageLabelRange.STYLE_ROMAN_LOWER, OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "openCamera", "scheduleAutoCapture", "setHintPaintAndBorder", "paint", "Landroid/graphics/Paint;", "border", "setPreviewCallback", "showAprilTagHint", "showOmrNotDetected", "startListeningAcceleration", "stopListeningAcceleration", "stopPreviewAndFreeCamera", "surfaceChanged", "holder", "Landroid/view/SurfaceHolder;", "format", "surfaceCreated", "surfaceDestroyed", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ScanSurfaceView extends FrameLayout implements SurfaceHolder.Callback, SensorEventListener {
    private static final int BITMAP_DECODING_THRESH = 2200;
    public static final Companion Companion = new Companion(null);
    public static final long DEFAULT_TIME_POST_PICTURE = 4000;
    private static final boolean IS_NEED_APRIL_TAG_VALIDATION = true;
    public static final String TAG = "ScanSurfaceView";
    private static final long TIME_POST_PICTURE = 1900;
    private Sensor accelerometerSensor;
    private CountDownTimer autoCaptureTimer;
    private final AutoCaptureViewModel autoCaptureViewModel;
    private Camera camera;
    private final CoroutineScope cameraScope;
    private float deviceAcceleration;
    private boolean isAutoCaptureScheduled;
    private boolean isCapturing;
    private boolean isProcessing;
    private Pair<Integer, Double> lastDetectedAprilTag;
    private final Camera.ShutterCallback mShutterCallBack;
    private final Camera.PictureCallback pictureCallback;
    private float prevAccX;
    private float prevAccY;
    private float prevAccZ;
    private final Camera.PreviewCallback previewCallback;
    private Camera.Size previewSize;
    private final ScanCanvasView scanCanvasView;
    private SensorManager sensorManager;
    private SurfaceView surfaceView;
    private int vHeight;
    private int vWidth;

    /* compiled from: ScanSurfaceView.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AutoCaptureViewModel.ScanHint.values().length];
            try {
                iArr[AutoCaptureViewModel.ScanHint.MOVE_CLOSER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AutoCaptureViewModel.ScanHint.MOVE_AWAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AutoCaptureViewModel.ScanHint.ADJUST_ANGLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AutoCaptureViewModel.ScanHint.DO_NOT_MOVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AutoCaptureViewModel.ScanHint.CAPTURING_IMAGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[AutoCaptureViewModel.ScanHint.OMR_EDGE_DETECTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ScanSurfaceView(final android.app.Activity r4, org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel r5, final int r6, final int r7) {
        /*
            r3 = this;
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "autoCaptureViewModel"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r0 = r4
            android.content.Context r0 = (android.content.Context) r0
            r3.<init>(r0)
            r3.autoCaptureViewModel = r5
            android.view.SurfaceView r5 = new android.view.SurfaceView
            r5.<init>(r0)
            r3.surfaceView = r5
            kotlin.Pair r5 = new kotlin.Pair
            r0 = -1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1 = 0
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r5.<init>(r0, r1)
            r3.lastDetectedAprilTag = r5
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getDefault()
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            kotlinx.coroutines.CoroutineScope r5 = kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(r5)
            r3.cameraScope = r5
            android.view.SurfaceView r5 = r3.surfaceView
            android.view.View r5 = (android.view.View) r5
            r3.addView(r5)
            org.informatika.sirekap.ui.autocapture.ScanCanvasView r5 = new org.informatika.sirekap.ui.autocapture.ScanCanvasView
            android.content.Context r0 = r3.getContext()
            r5.<init>(r0)
            r3.scanCanvasView = r5
            android.view.View r5 = (android.view.View) r5
            r3.addView(r5)
            android.view.SurfaceView r5 = r3.surfaceView
            android.view.SurfaceHolder r5 = r5.getHolder()
            r0 = r3
            android.view.SurfaceHolder$Callback r0 = (android.view.SurfaceHolder.Callback) r0
            r5.addCallback(r0)
            java.lang.String r5 = "sensor"
            java.lang.Object r5 = r4.getSystemService(r5)
            java.lang.String r0 = "null cannot be cast to non-null type android.hardware.SensorManager"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r0)
            android.hardware.SensorManager r5 = (android.hardware.SensorManager) r5
            r3.sensorManager = r5
            r0 = 1
            android.hardware.Sensor r5 = r5.getDefaultSensor(r0)
            java.lang.String r0 = "sensorManager.getDefault…ensor.TYPE_ACCELEROMETER)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            r3.accelerometerSensor = r5
            r3.startListeningAcceleration()
            r3.showAprilTagHint()
            r3.showOmrNotDetected()
            org.informatika.sirekap.ui.autocapture.ScanSurfaceView$$ExternalSyntheticLambda0 r5 = new org.informatika.sirekap.ui.autocapture.ScanSurfaceView$$ExternalSyntheticLambda0
            r5.<init>()
            r3.previewCallback = r5
            org.informatika.sirekap.ui.autocapture.ScanSurfaceView$$ExternalSyntheticLambda1 r5 = new org.informatika.sirekap.ui.autocapture.ScanSurfaceView$$ExternalSyntheticLambda1
            r5.<init>()
            r3.pictureCallback = r5
            org.informatika.sirekap.ui.autocapture.ScanSurfaceView$$ExternalSyntheticLambda2 r5 = new org.informatika.sirekap.ui.autocapture.ScanSurfaceView$$ExternalSyntheticLambda2
            r5.<init>()
            r3.mShutterCallBack = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.autocapture.ScanSurfaceView.<init>(android.app.Activity, org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel, int, int):void");
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        try {
            requestLayout();
            openCamera();
            Camera camera = this.camera;
            Intrinsics.checkNotNull(camera);
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            Log.wtf(TAG, e);
        }
    }

    public final void clearAndInvalidateCanvas() {
        clearCanvas();
        invalidateCanvas();
    }

    private final void clearCanvas() {
        this.scanCanvasView.clear();
    }

    private final void invalidateCanvas() {
        this.scanCanvasView.invalidate();
    }

    private final void openCamera() {
        if (this.camera == null) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            int numberOfCameras = Camera.getNumberOfCameras();
            int i = 0;
            for (int i2 = 0; i2 < numberOfCameras; i2++) {
                Camera.getCameraInfo(i2, cameraInfo);
                if (cameraInfo.facing == 0) {
                    i = i2;
                }
            }
            Camera open = Camera.open(i);
            this.camera = open;
            Camera.Parameters parameters = open != null ? open.getParameters() : null;
            List<String> supportedFlashModes = parameters != null ? parameters.getSupportedFlashModes() : null;
            if (supportedFlashModes != null && supportedFlashModes.contains(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
                parameters.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
            }
            Camera camera = this.camera;
            if (camera == null) {
                return;
            }
            camera.setParameters(parameters);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.vWidth == this.vHeight) {
            return;
        }
        if (this.previewSize == null) {
            this.previewSize = ScanUtils.INSTANCE.getOptimalPreviewSize(this.camera, this.vWidth, this.vHeight);
        }
        Camera camera = this.camera;
        Intrinsics.checkNotNull(camera);
        Camera.Parameters parameters = camera.getParameters();
        Camera camera2 = this.camera;
        Intrinsics.checkNotNull(camera2);
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        camera2.setDisplayOrientation(CameraUtilsKt.configureCameraAngle((Activity) context));
        Camera.Size size = this.previewSize;
        Intrinsics.checkNotNull(size);
        int i4 = size.width;
        Camera.Size size2 = this.previewSize;
        Intrinsics.checkNotNull(size2);
        parameters.setPreviewSize(i4, size2.height);
        if (parameters.getSupportedFocusModes() != null && parameters.getSupportedFocusModes().contains("continuous-picture")) {
            parameters.setFocusMode("continuous-picture");
        } else if (parameters.getSupportedFocusModes() != null && parameters.getSupportedFocusModes().contains(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
            parameters.setFocusMode(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
        }
        ScanUtils scanUtils = ScanUtils.INSTANCE;
        Camera camera3 = this.camera;
        Camera.Size previewSize = parameters.getPreviewSize();
        Intrinsics.checkNotNullExpressionValue(previewSize, "parameters.previewSize");
        Camera.Size determinePictureSize = scanUtils.determinePictureSize(camera3, previewSize);
        if (determinePictureSize != null) {
            parameters.setPictureSize(determinePictureSize.width, determinePictureSize.height);
            parameters.setPictureFormat(256);
            Camera camera4 = this.camera;
            Intrinsics.checkNotNull(camera4);
            camera4.setParameters(parameters);
        }
        requestLayout();
        setPreviewCallback();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        stopPreviewAndFreeCamera();
        stopListeningAcceleration();
        CoroutineScopeKt.cancel$default(this.cameraScope, null, 1, null);
        Camera camera = this.camera;
        if (camera != null) {
            camera.stopPreview();
        }
        Camera camera2 = this.camera;
        if (camera2 != null) {
            camera2.release();
        }
        this.camera = null;
    }

    private final void stopPreviewAndFreeCamera() {
        Camera camera = this.camera;
        if (camera != null) {
            Intrinsics.checkNotNull(camera);
            camera.stopPreview();
            Camera camera2 = this.camera;
            Intrinsics.checkNotNull(camera2);
            camera2.setPreviewCallback(null);
            Camera camera3 = this.camera;
            Intrinsics.checkNotNull(camera3);
            camera3.release();
            this.camera = null;
        }
    }

    public final void setPreviewCallback() {
        Camera camera = this.camera;
        Intrinsics.checkNotNull(camera);
        camera.startPreview();
    }

    public static final void previewCallback$lambda$1(ScanSurfaceView this$0, Activity activity, byte[] bArr, Camera camera) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        if (camera == null || this$0.isProcessing || this$0.isCapturing) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(this$0.cameraScope, null, null, new ScanSurfaceView$previewCallback$1$1(this$0, camera, bArr, activity, null), 3, null);
    }

    public final void drawEdgeOMR(List<? extends Point> list, Size size, int i) {
        AutoCaptureViewModel.ScanHint scanHint;
        float f = (float) size.height;
        float f2 = (float) size.width;
        Path path = new Path();
        path.moveTo(f - ((float) list.get(0).y), (float) list.get(0).x);
        path.lineTo(f - ((float) list.get(1).y), (float) list.get(1).x);
        path.lineTo(f - ((float) list.get(2).y), (float) list.get(2).x);
        path.lineTo(f - ((float) list.get(3).y), (float) list.get(3).x);
        path.close();
        new PathShape(path, f, f2);
        double d = list.get(1).x - list.get(0).x;
        double d2 = list.get(2).x - list.get(3).x;
        double d3 = d2 > d ? d2 : d;
        double d4 = list.get(3).y - list.get(0).y;
        double d5 = list.get(2).y - list.get(1).y;
        ImageDetectionProperties imageDetectionProperties = new ImageDetectionProperties(f, f2, d5 > d4 ? d5 : d4, d3, i, 0.0d, list.get(0), list.get(1), list.get(2), list.get(3), this.deviceAcceleration);
        if (!this.autoCaptureViewModel.isAutoCapture()) {
            if (!this.isAutoCaptureScheduled && !this.isCapturing) {
                this.autoCaptureViewModel.setHintOmr(AutoCaptureViewModel.ScanHint.OMR_EDGE_DETECTED);
            }
            if (imageDetectionProperties.isCameraMoving()) {
                this.autoCaptureViewModel.setHintOmr(AutoCaptureViewModel.ScanHint.DO_NOT_MOVE);
                return;
            } else if (this.lastDetectedAprilTag.getFirst().intValue() != -1) {
                AprilTagConfig.INSTANCE.isFormValid(this.lastDetectedAprilTag.getFirst().intValue());
                return;
            } else {
                return;
            }
        }
        if (imageDetectionProperties.isCameraMoving()) {
            scanHint = AutoCaptureViewModel.ScanHint.DO_NOT_MOVE;
            cancelAutoCapture();
        } else if (imageDetectionProperties.isDetectedHeightAboveLimit() || imageDetectionProperties.isDetectedWidthAboveLimit() || imageDetectionProperties.isDetectedAreaAboveLimit()) {
            scanHint = AutoCaptureViewModel.ScanHint.MOVE_AWAY;
            cancelAutoCapture();
        } else if (imageDetectionProperties.isAnyEdgeDistorted()) {
            scanHint = AutoCaptureViewModel.ScanHint.ADJUST_ANGLE;
            cancelAutoCapture();
        } else {
            clearAndInvalidateCanvas();
            if (!this.isCapturing && !this.isAutoCaptureScheduled && this.lastDetectedAprilTag.getFirst().intValue() != -1 && AprilTagConfig.INSTANCE.isFormValid(this.lastDetectedAprilTag.getFirst().intValue())) {
                scanHint = AutoCaptureViewModel.ScanHint.CAPTURING_IMAGE;
                scheduleAutoCapture(scanHint);
            } else {
                scanHint = AutoCaptureViewModel.ScanHint.OMR_EDGE_DETECTED;
                if (this.lastDetectedAprilTag.getFirst().intValue() == -1) {
                    cancelAutoCapture();
                }
            }
        }
        if (this.isAutoCaptureScheduled || this.isCapturing) {
            return;
        }
        this.autoCaptureViewModel.setHintOmr(scanHint);
    }

    private final void scheduleAutoCapture(final AutoCaptureViewModel.ScanHint scanHint) {
        this.isAutoCaptureScheduled = true;
        this.autoCaptureViewModel.setHintOmr(AutoCaptureViewModel.ScanHint.PREPARING_TO_CAPTURE);
        final Ref.LongRef longRef = new Ref.LongRef();
        CountDownTimer countDownTimer = new CountDownTimer() { // from class: org.informatika.sirekap.ui.autocapture.ScanSurfaceView$scheduleAutoCapture$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(ScanSurfaceView.DEFAULT_TIME_POST_PICTURE, 100L);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (j != Ref.LongRef.this.element) {
                    Ref.LongRef.this.element = j;
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                this.isAutoCaptureScheduled = false;
                this.autoCapture(scanHint);
            }
        };
        this.autoCaptureTimer = countDownTimer;
        countDownTimer.start();
    }

    public final void autoCapture(AutoCaptureViewModel.ScanHint scanHint) {
        Intrinsics.checkNotNullParameter(scanHint, "scanHint");
        if (this.isCapturing) {
            return;
        }
        cancelAutoCapture();
        if (scanHint == AutoCaptureViewModel.ScanHint.CAPTURING_IMAGE) {
            try {
                this.isCapturing = true;
                JobKt__JobKt.cancelChildren$default(this.cameraScope.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
                this.isProcessing = false;
                this.autoCaptureViewModel.setHintOmr(AutoCaptureViewModel.ScanHint.CAPTURING_IMAGE);
                Camera camera = this.camera;
                Intrinsics.checkNotNull(camera);
                camera.takePicture(this.mShutterCallBack, null, this.pictureCallback);
                Camera camera2 = this.camera;
                Intrinsics.checkNotNull(camera2);
                camera2.setPreviewCallback(null);
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
                throw e;
            }
        }
    }

    public final void manualCapture() {
        try {
            scheduleAutoCapture(AutoCaptureViewModel.ScanHint.CAPTURING_IMAGE);
        } catch (Exception e) {
            Log.wtf(TAG, e);
        }
    }

    public final void cancelAutoCapture() {
        if (this.isAutoCaptureScheduled) {
            this.isAutoCaptureScheduled = false;
            CountDownTimer countDownTimer = this.autoCaptureTimer;
            if (countDownTimer != null) {
                Intrinsics.checkNotNull(countDownTimer);
                countDownTimer.cancel();
            }
        }
    }

    public final void showOmrNotDetected() {
        this.autoCaptureViewModel.setHintOmr(AutoCaptureViewModel.ScanHint.OMR_EDGE_NOT_DETECTED);
        clearAndInvalidateCanvas();
    }

    public final void showAprilTagHint() {
        this.autoCaptureViewModel.setHintAprilTag(this.lastDetectedAprilTag.getFirst().intValue());
    }

    private final void setHintPaintAndBorder(AutoCaptureViewModel.ScanHint scanHint, Paint paint, Paint paint2) {
        int argb;
        int rgb;
        int i;
        int i2 = 0;
        switch (WhenMappings.$EnumSwitchMapping$0[scanHint.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                argb = Color.argb(30, 255, 38, 0);
                rgb = Color.rgb(255, 38, 0);
                i = rgb;
                i2 = argb;
                break;
            case 5:
            case 6:
                argb = Color.argb(30, 38, 216, 76);
                rgb = Color.rgb(38, 216, 76);
                i = rgb;
                i2 = argb;
                break;
            default:
                i = 0;
                break;
        }
        paint.setColor(i2);
        paint2.setColor(i);
        paint2.setStrokeWidth(8.0f);
    }

    public static final void pictureCallback$lambda$3(ScanSurfaceView this$0, int i, int i2, byte[] data, Camera camera) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        camera.stopPreview();
        this$0.clearAndInvalidateCanvas();
        this$0.autoCaptureViewModel.setHintOmr(AutoCaptureViewModel.ScanHint.NO_MESSAGE);
        Intrinsics.checkNotNullExpressionValue(data, "data");
        Bitmap decodeBitmapFromByteArray = BitmapUtilsKt.decodeBitmapFromByteArray(data, 2200, 2200);
        Matrix matrix = new Matrix();
        matrix.postRotate(90.0f);
        Bitmap createBitmap = Bitmap.createBitmap(decodeBitmapFromByteArray, 0, 0, decodeBitmapFromByteArray.getWidth(), decodeBitmapFromByteArray.getHeight(), matrix, true);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(bitmap, 0, …map.height, matrix, true)");
        this$0.autoCaptureViewModel.processImage(createBitmap, i, i2);
        this$0.postDelayed(new Runnable() { // from class: org.informatika.sirekap.ui.autocapture.ScanSurfaceView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ScanSurfaceView.pictureCallback$lambda$3$lambda$2(ScanSurfaceView.this);
            }
        }, TIME_POST_PICTURE);
    }

    public static final void pictureCallback$lambda$3$lambda$2(ScanSurfaceView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isCapturing = false;
    }

    public static final void mShutterCallBack$lambda$4(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Object systemService = activity.getSystemService("audio");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        ((AudioManager) systemService).playSoundEffect(0);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        this.vWidth = View.resolveSize(getSuggestedMinimumWidth(), i);
        int resolveSize = View.resolveSize(getSuggestedMinimumHeight(), i2);
        this.vHeight = resolveSize;
        setMeasuredDimension(this.vWidth, resolveSize);
        this.previewSize = ScanUtils.INSTANCE.getOptimalPreviewSize(this.camera, this.vWidth, this.vHeight);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        if (getChildCount() > 0) {
            int i12 = i3 - i;
            int i13 = i4 - i2;
            Camera.Size size = this.previewSize;
            if (size != null) {
                Intrinsics.checkNotNull(size);
                i5 = size.width;
                Camera.Size size2 = this.previewSize;
                Intrinsics.checkNotNull(size2);
                i6 = size2.height;
                Context context = getContext();
                Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
                int configureCameraAngle = CameraUtilsKt.configureCameraAngle((Activity) context);
                if (configureCameraAngle == 90 || configureCameraAngle == 270) {
                    Camera.Size size3 = this.previewSize;
                    Intrinsics.checkNotNull(size3);
                    i5 = size3.height;
                    Camera.Size size4 = this.previewSize;
                    Intrinsics.checkNotNull(size4);
                    i6 = size4.width;
                }
            } else {
                i5 = i12;
                i6 = i13;
            }
            int i14 = 0;
            if (i12 * i6 < i13 * i5) {
                int i15 = (int) ((i8 / i6) * 1.0f);
                i9 = (i12 + i15) / 2;
                i10 = (int) (i13 * 1.0f);
                i11 = 0;
                i14 = (i12 - i15) / 2;
            } else {
                int i16 = (int) ((i7 / i5) * 1.0f);
                i9 = (int) (i12 * 1.0f);
                i10 = (i13 + i16) / 2;
                i11 = (i13 - i16) / 2;
            }
            this.surfaceView.layout(i14, i11, i9, i10);
            this.scanCanvasView.layout(i14, i11, i9, i10);
        }
    }

    private final void startListeningAcceleration() {
        this.sensorManager.registerListener(this, this.accelerometerSensor, 3);
    }

    private final void stopListeningAcceleration() {
        this.sensorManager.unregisterListener(this);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.deviceAcceleration = Math.abs(event.values[0] - this.prevAccX) + Math.abs(event.values[1] - this.prevAccY) + Math.abs(event.values[2] - this.prevAccZ);
        this.prevAccX = event.values[0];
        this.prevAccY = event.values[1];
        this.prevAccZ = event.values[2];
    }

    /* compiled from: ScanSurfaceView.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/ScanSurfaceView$Companion;", "", "()V", "BITMAP_DECODING_THRESH", "", "DEFAULT_TIME_POST_PICTURE", "", "IS_NEED_APRIL_TAG_VALIDATION", "", "TAG", "", "TIME_POST_PICTURE", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
