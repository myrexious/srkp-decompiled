package org.opencv.android;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/* loaded from: classes4.dex */
public class JavaCamera2View extends CameraBridgeViewBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String LOGTAG = "JavaCamera2View";
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;
    private CameraDevice mCameraDevice;
    private String mCameraID;
    private CameraCaptureSession mCaptureSession;
    private ImageReader mImageReader;
    private int mPreviewFormat;
    private CaptureRequest.Builder mPreviewRequestBuilder;
    private Size mPreviewSize;
    private final CameraDevice.StateCallback mStateCallback;

    public JavaCamera2View(Context context, int i) {
        super(context, i);
        this.mPreviewFormat = 35;
        this.mPreviewSize = new Size(-1, -1);
        this.mStateCallback = new CameraDevice.StateCallback() { // from class: org.opencv.android.JavaCamera2View.1
            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onOpened(CameraDevice cameraDevice) {
                JavaCamera2View.this.mCameraDevice = cameraDevice;
                JavaCamera2View.this.createCameraPreviewSession();
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onDisconnected(CameraDevice cameraDevice) {
                cameraDevice.close();
                JavaCamera2View.this.mCameraDevice = null;
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onError(CameraDevice cameraDevice, int i2) {
                cameraDevice.close();
                JavaCamera2View.this.mCameraDevice = null;
            }
        };
    }

    public JavaCamera2View(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPreviewFormat = 35;
        this.mPreviewSize = new Size(-1, -1);
        this.mStateCallback = new CameraDevice.StateCallback() { // from class: org.opencv.android.JavaCamera2View.1
            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onOpened(CameraDevice cameraDevice) {
                JavaCamera2View.this.mCameraDevice = cameraDevice;
                JavaCamera2View.this.createCameraPreviewSession();
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onDisconnected(CameraDevice cameraDevice) {
                cameraDevice.close();
                JavaCamera2View.this.mCameraDevice = null;
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onError(CameraDevice cameraDevice, int i2) {
                cameraDevice.close();
                JavaCamera2View.this.mCameraDevice = null;
            }
        };
    }

    private void startBackgroundThread() {
        Log.i(LOGTAG, "startBackgroundThread");
        stopBackgroundThread();
        HandlerThread handlerThread = new HandlerThread("OpenCVCameraBackground");
        this.mBackgroundThread = handlerThread;
        handlerThread.start();
        this.mBackgroundHandler = new Handler(this.mBackgroundThread.getLooper());
    }

    private void stopBackgroundThread() {
        Log.i(LOGTAG, "stopBackgroundThread");
        HandlerThread handlerThread = this.mBackgroundThread;
        if (handlerThread == null) {
            return;
        }
        handlerThread.quitSafely();
        try {
            this.mBackgroundThread.join();
            this.mBackgroundThread = null;
            this.mBackgroundHandler = null;
        } catch (InterruptedException e) {
            Log.e(LOGTAG, "stopBackgroundThread", e);
        }
    }

    protected boolean initializeCamera() {
        Log.i(LOGTAG, "initializeCamera");
        CameraManager cameraManager = (CameraManager) getContext().getSystemService("camera");
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (cameraIdList.length == 0) {
                Log.e(LOGTAG, "Error: camera isn't detected.");
                return false;
            }
            if (this.mCameraIndex == -1) {
                this.mCameraID = cameraIdList[0];
            } else {
                for (String str : cameraIdList) {
                    CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
                    if ((this.mCameraIndex == 99 && ((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 1) || (this.mCameraIndex == 98 && ((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0)) {
                        this.mCameraID = str;
                        break;
                    }
                }
            }
            if (this.mCameraID != null) {
                Log.i(LOGTAG, "Opening camera: " + this.mCameraID);
                cameraManager.openCamera(this.mCameraID, this.mStateCallback, this.mBackgroundHandler);
            }
            return true;
        } catch (CameraAccessException e) {
            Log.e(LOGTAG, "OpenCamera - Camera Access Exception", e);
            return false;
        } catch (IllegalArgumentException e2) {
            Log.e(LOGTAG, "OpenCamera - Illegal Argument Exception", e2);
            return false;
        } catch (SecurityException e3) {
            Log.e(LOGTAG, "OpenCamera - Security Exception", e3);
            return false;
        }
    }

    public void createCameraPreviewSession() {
        final int width = this.mPreviewSize.getWidth();
        final int height = this.mPreviewSize.getHeight();
        Log.i(LOGTAG, "createCameraPreviewSession(" + width + "x" + height + ")");
        if (width < 0 || height < 0) {
            return;
        }
        try {
            if (this.mCameraDevice == null) {
                Log.e(LOGTAG, "createCameraPreviewSession: camera isn't opened");
            } else if (this.mCaptureSession != null) {
                Log.e(LOGTAG, "createCameraPreviewSession: mCaptureSession is already started");
            } else {
                ImageReader newInstance = ImageReader.newInstance(width, height, this.mPreviewFormat, 2);
                this.mImageReader = newInstance;
                newInstance.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: org.opencv.android.JavaCamera2View.2
                    static final /* synthetic */ boolean $assertionsDisabled = false;

                    @Override // android.media.ImageReader.OnImageAvailableListener
                    public void onImageAvailable(ImageReader imageReader) {
                        Image acquireLatestImage = imageReader.acquireLatestImage();
                        if (acquireLatestImage == null) {
                            return;
                        }
                        Image.Plane[] planes = acquireLatestImage.getPlanes();
                        ByteBuffer buffer = planes[0].getBuffer();
                        ByteBuffer buffer2 = planes[1].getBuffer();
                        JavaCamera2Frame javaCamera2Frame = new JavaCamera2Frame(new Mat(height, width, CvType.CV_8UC1, buffer), new Mat(height / 2, width / 2, CvType.CV_8UC2, buffer2), width, height);
                        JavaCamera2View.this.deliverAndDrawFrame(javaCamera2Frame);
                        javaCamera2Frame.release();
                        acquireLatestImage.close();
                    }
                }, this.mBackgroundHandler);
                Surface surface = this.mImageReader.getSurface();
                CaptureRequest.Builder createCaptureRequest = this.mCameraDevice.createCaptureRequest(1);
                this.mPreviewRequestBuilder = createCaptureRequest;
                createCaptureRequest.addTarget(surface);
                this.mCameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() { // from class: org.opencv.android.JavaCamera2View.3
                    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                        Log.i(JavaCamera2View.LOGTAG, "createCaptureSession::onConfigured");
                        if (JavaCamera2View.this.mCameraDevice == null) {
                            return;
                        }
                        JavaCamera2View.this.mCaptureSession = cameraCaptureSession;
                        try {
                            JavaCamera2View.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 4);
                            JavaCamera2View.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 2);
                            JavaCamera2View.this.mCaptureSession.setRepeatingRequest(JavaCamera2View.this.mPreviewRequestBuilder.build(), null, JavaCamera2View.this.mBackgroundHandler);
                            Log.i(JavaCamera2View.LOGTAG, "CameraPreviewSession has been started");
                        } catch (Exception e) {
                            Log.e(JavaCamera2View.LOGTAG, "createCaptureSession failed", e);
                        }
                    }

                    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                    public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                        Log.e(JavaCamera2View.LOGTAG, "createCameraPreviewSession failed");
                    }
                }, null);
            }
        } catch (CameraAccessException e) {
            Log.e(LOGTAG, "createCameraPreviewSession", e);
        }
    }

    @Override // org.opencv.android.CameraBridgeViewBase
    protected void disconnectCamera() {
        Log.i(LOGTAG, "closeCamera");
        try {
            CameraDevice cameraDevice = this.mCameraDevice;
            this.mCameraDevice = null;
            CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.close();
                this.mCaptureSession = null;
            }
            if (cameraDevice != null) {
                cameraDevice.close();
            }
            ImageReader imageReader = this.mImageReader;
            if (imageReader != null) {
                imageReader.close();
                this.mImageReader = null;
            }
        } finally {
            stopBackgroundThread();
        }
    }

    boolean calcPreviewSize(int i, int i2) {
        Log.i(LOGTAG, "calcPreviewSize: " + i + "x" + i2);
        if (this.mCameraID == null) {
            Log.e(LOGTAG, "Camera isn't initialized!");
            return false;
        }
        try {
            float f = i / i2;
            Size[] outputSizes = ((StreamConfigurationMap) ((CameraManager) getContext().getSystemService("camera")).getCameraCharacteristics(this.mCameraID).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(ImageReader.class);
            int width = outputSizes[0].getWidth();
            int height = outputSizes[0].getHeight();
            for (Size size : outputSizes) {
                int width2 = size.getWidth();
                int height2 = size.getHeight();
                Log.d(LOGTAG, "trying size: " + width2 + "x" + height2);
                if (i >= width2 && i2 >= height2 && width <= width2 && height <= height2 && Math.abs(f - (width2 / height2)) < 0.2d) {
                    height = height2;
                    width = width2;
                }
            }
            Log.i(LOGTAG, "best size: " + width + "x" + height);
            if (this.mPreviewSize.getWidth() == width && this.mPreviewSize.getHeight() == height) {
                return false;
            }
            this.mPreviewSize = new Size(width, height);
            return true;
        } catch (CameraAccessException e) {
            Log.e(LOGTAG, "calcPreviewSize - Camera Access Exception", e);
            return false;
        } catch (IllegalArgumentException e2) {
            Log.e(LOGTAG, "calcPreviewSize - Illegal Argument Exception", e2);
            return false;
        } catch (SecurityException e3) {
            Log.e(LOGTAG, "calcPreviewSize - Security Exception", e3);
            return false;
        }
    }

    @Override // org.opencv.android.CameraBridgeViewBase
    protected boolean connectCamera(int i, int i2) {
        Log.i(LOGTAG, "setCameraPreviewSize(" + i + "x" + i2 + ")");
        startBackgroundThread();
        initializeCamera();
        try {
            boolean calcPreviewSize = calcPreviewSize(i, i2);
            this.mFrameWidth = this.mPreviewSize.getWidth();
            this.mFrameHeight = this.mPreviewSize.getHeight();
            if (getLayoutParams().width == -1 && getLayoutParams().height == -1) {
                this.mScale = Math.min(i2 / this.mFrameHeight, i / this.mFrameWidth);
            } else {
                this.mScale = 0.0f;
            }
            AllocateCache();
            if (calcPreviewSize) {
                if (this.mCaptureSession != null) {
                    Log.d(LOGTAG, "closing existing previewSession");
                    this.mCaptureSession.close();
                    this.mCaptureSession = null;
                }
                createCameraPreviewSession();
                return true;
            }
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException("Interrupted while setCameraPreviewSize.", e);
        }
    }

    /* loaded from: classes4.dex */
    private class JavaCamera2Frame implements CameraBridgeViewBase.CvCameraViewFrame {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int mHeight;
        private Mat mRgba;
        private Mat mUVFrameData;
        private int mWidth;
        private Mat mYuvFrameData;

        @Override // org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame
        public Mat gray() {
            return this.mYuvFrameData.submat(0, this.mHeight, 0, this.mWidth);
        }

        @Override // org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame
        public Mat rgba() {
            if (JavaCamera2View.this.mPreviewFormat != 17) {
                if (JavaCamera2View.this.mPreviewFormat != 842094169) {
                    if (JavaCamera2View.this.mPreviewFormat == 35) {
                        Imgproc.cvtColorTwoPlane(this.mYuvFrameData, this.mUVFrameData, this.mRgba, 96);
                    } else {
                        throw new IllegalArgumentException("Preview Format can be NV21 or YV12");
                    }
                } else {
                    Imgproc.cvtColor(this.mYuvFrameData, this.mRgba, 100, 4);
                }
            } else {
                Imgproc.cvtColor(this.mYuvFrameData, this.mRgba, 96, 4);
            }
            return this.mRgba;
        }

        public JavaCamera2Frame(Mat mat, int i, int i2) {
            JavaCamera2View.this = r1;
            this.mWidth = i;
            this.mHeight = i2;
            this.mYuvFrameData = mat;
            this.mUVFrameData = null;
            this.mRgba = new Mat();
        }

        public JavaCamera2Frame(Mat mat, Mat mat2, int i, int i2) {
            JavaCamera2View.this = r1;
            this.mWidth = i;
            this.mHeight = i2;
            this.mYuvFrameData = mat;
            this.mUVFrameData = mat2;
            this.mRgba = new Mat();
        }

        public void release() {
            this.mRgba.release();
        }
    }
}
