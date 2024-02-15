package org.visp.android;

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
import org.visp.android.CameraBridgeViewBase;
import org.visp.core.VpImageRGBa;
import org.visp.core.VpImageUChar;

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
        this.mStateCallback = new CameraDevice.StateCallback() { // from class: org.visp.android.JavaCamera2View.1
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
        this.mStateCallback = new CameraDevice.StateCallback() { // from class: org.visp.android.JavaCamera2View.1
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
        HandlerThread handlerThread = new HandlerThread("ViSPCameraBackground");
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
                newInstance.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: org.visp.android.JavaCamera2View.2
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
                        int i = height;
                        int i2 = width;
                        byte[] bArr = new byte[i * i2];
                        byte[] bArr2 = new byte[(i * i2) / 4];
                        buffer.get(bArr);
                        buffer2.get(bArr2);
                        JavaCamera2Frame javaCamera2Frame = new JavaCamera2Frame(bArr, bArr2, width, height);
                        JavaCamera2View.this.deliverAndDrawFrame(javaCamera2Frame);
                        javaCamera2Frame.release();
                        acquireLatestImage.close();
                    }
                }, this.mBackgroundHandler);
                Surface surface = this.mImageReader.getSurface();
                CaptureRequest.Builder createCaptureRequest = this.mCameraDevice.createCaptureRequest(1);
                this.mPreviewRequestBuilder = createCaptureRequest;
                createCaptureRequest.addTarget(surface);
                this.mCameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() { // from class: org.visp.android.JavaCamera2View.3
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

    @Override // org.visp.android.CameraBridgeViewBase
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

    @Override // org.visp.android.CameraBridgeViewBase
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
    private class JavaCamera2Frame implements CameraBridgeViewBase.VpCameraViewFrame {
        private VpImageUChar gray;
        private int h;
        private byte[] mUVFrameData;
        private byte[] mYuvFrameData;
        private VpImageRGBa rgba;
        private int w;

        public void release() {
        }

        @Override // org.visp.android.CameraBridgeViewBase.VpCameraViewFrame
        public VpImageUChar gray() {
            if (this.gray == null) {
                this.gray = new VpImageUChar(this.mYuvFrameData, this.w, this.h, true);
            }
            return this.gray;
        }

        @Override // org.visp.android.CameraBridgeViewBase.VpCameraViewFrame
        public VpImageRGBa rgba() {
            if (this.rgba == null) {
                if (JavaCamera2View.this.mPreviewFormat == 17) {
                    this.rgba = new VpImageRGBa(this.mYuvFrameData, this.w, this.h, true);
                } else {
                    throw new IllegalArgumentException("Preview Format can be NV21 only for now");
                }
            }
            return this.rgba;
        }

        public JavaCamera2Frame(byte[] bArr, int i, int i2) {
            JavaCamera2View.this = r1;
            this.w = i;
            this.h = i2;
            this.mYuvFrameData = bArr;
            this.mUVFrameData = null;
            this.rgba = null;
            this.gray = null;
        }

        public JavaCamera2Frame(byte[] bArr, byte[] bArr2, int i, int i2) {
            JavaCamera2View.this = r1;
            this.w = i;
            this.h = i2;
            this.mYuvFrameData = bArr;
            this.mUVFrameData = bArr2;
            this.rgba = null;
            this.gray = null;
        }
    }
}
