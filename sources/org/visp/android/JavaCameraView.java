package org.visp.android;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import org.visp.android.CameraBridgeViewBase;
import org.visp.core.VpImageRGBa;
import org.visp.core.VpImageUChar;

/* loaded from: classes4.dex */
public class JavaCameraView extends CameraBridgeViewBase implements Camera.PreviewCallback {
    private static final int MAGIC_TEXTURE_ID = 10;
    private static final String TAG = "JavaCameraView";
    private byte[] mBuffer;
    protected Camera mCamera;
    protected JavaCameraFrame[] mCameraFrame;
    private boolean mCameraFrameReady;
    private int mChainIdx;
    private byte[][] mFrameChain;
    private int mPreviewFormat;
    private boolean mStopThread;
    private SurfaceTexture mSurfaceTexture;
    private Thread mThread;

    /* loaded from: classes4.dex */
    public static class JavaCameraSizeAccessor implements CameraBridgeViewBase.ListItemAccessor {
        @Override // org.visp.android.CameraBridgeViewBase.ListItemAccessor
        public int getWidth(Object obj) {
            return ((Camera.Size) obj).width;
        }

        @Override // org.visp.android.CameraBridgeViewBase.ListItemAccessor
        public int getHeight(Object obj) {
            return ((Camera.Size) obj).height;
        }
    }

    public JavaCameraView(Context context, int i) {
        super(context, i);
        this.mChainIdx = 0;
        this.mPreviewFormat = 17;
        this.mCameraFrameReady = false;
    }

    public JavaCameraView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChainIdx = 0;
        this.mPreviewFormat = 17;
        this.mCameraFrameReady = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:164:0x00ea A[Catch: all -> 0x02fa, TryCatch #1 {, blocks: (B:125:0x000b, B:127:0x0014, B:128:0x001b, B:132:0x0039, B:135:0x003f, B:137:0x0045, B:138:0x0067, B:144:0x009a, B:141:0x0070, B:172:0x014c, B:174:0x0150, B:176:0x0152, B:178:0x0163, B:180:0x0176, B:182:0x0180, B:184:0x018a, B:186:0x0194, B:188:0x019e, B:190:0x01a8, B:192:0x01b2, B:194:0x01bc, B:197:0x01c7, B:199:0x01d3, B:201:0x0210, B:202:0x0213, B:204:0x0219, B:206:0x0221, B:207:0x0226, B:209:0x0249, B:211:0x0251, B:213:0x0265, B:215:0x0269, B:216:0x0272, B:212:0x0262, B:198:0x01cd, B:220:0x02f8, B:219:0x02f5, B:131:0x0023, B:145:0x009d, B:147:0x00a7, B:148:0x00b4, B:150:0x00ba, B:153:0x00c2, B:164:0x00ea, B:166:0x00f4, B:167:0x00fc, B:168:0x011e, B:171:0x0126, B:154:0x00c5, B:156:0x00c9, B:157:0x00d6, B:159:0x00dc, B:162:0x00e5), top: B:227:0x000b, inners: #0, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0150 A[Catch: all -> 0x02fa, DONT_GENERATE, TRY_LEAVE, TryCatch #1 {, blocks: (B:125:0x000b, B:127:0x0014, B:128:0x001b, B:132:0x0039, B:135:0x003f, B:137:0x0045, B:138:0x0067, B:144:0x009a, B:141:0x0070, B:172:0x014c, B:174:0x0150, B:176:0x0152, B:178:0x0163, B:180:0x0176, B:182:0x0180, B:184:0x018a, B:186:0x0194, B:188:0x019e, B:190:0x01a8, B:192:0x01b2, B:194:0x01bc, B:197:0x01c7, B:199:0x01d3, B:201:0x0210, B:202:0x0213, B:204:0x0219, B:206:0x0221, B:207:0x0226, B:209:0x0249, B:211:0x0251, B:213:0x0265, B:215:0x0269, B:216:0x0272, B:212:0x0262, B:198:0x01cd, B:220:0x02f8, B:219:0x02f5, B:131:0x0023, B:145:0x009d, B:147:0x00a7, B:148:0x00b4, B:150:0x00ba, B:153:0x00c2, B:164:0x00ea, B:166:0x00f4, B:167:0x00fc, B:168:0x011e, B:171:0x0126, B:154:0x00c5, B:156:0x00c9, B:157:0x00d6, B:159:0x00dc, B:162:0x00e5), top: B:227:0x000b, inners: #0, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0210 A[Catch: Exception -> 0x02f4, all -> 0x02fa, TryCatch #3 {Exception -> 0x02f4, blocks: (B:176:0x0152, B:178:0x0163, B:180:0x0176, B:182:0x0180, B:184:0x018a, B:186:0x0194, B:188:0x019e, B:190:0x01a8, B:192:0x01b2, B:194:0x01bc, B:197:0x01c7, B:199:0x01d3, B:201:0x0210, B:202:0x0213, B:204:0x0219, B:206:0x0221, B:207:0x0226, B:209:0x0249, B:211:0x0251, B:213:0x0265, B:215:0x0269, B:216:0x0272, B:212:0x0262, B:198:0x01cd), top: B:230:0x0152, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0269 A[Catch: Exception -> 0x02f4, all -> 0x02fa, TryCatch #3 {Exception -> 0x02f4, blocks: (B:176:0x0152, B:178:0x0163, B:180:0x0176, B:182:0x0180, B:184:0x018a, B:186:0x0194, B:188:0x019e, B:190:0x01a8, B:192:0x01b2, B:194:0x01bc, B:197:0x01c7, B:199:0x01d3, B:201:0x0210, B:202:0x0213, B:204:0x0219, B:206:0x0221, B:207:0x0226, B:209:0x0249, B:211:0x0251, B:213:0x0265, B:215:0x0269, B:216:0x0272, B:212:0x0262, B:198:0x01cd), top: B:230:0x0152, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0152 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean initializeCamera(int r10, int r11) {
        /*
            Method dump skipped, instructions count: 765
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.visp.android.JavaCameraView.initializeCamera(int, int):boolean");
    }

    protected void releaseCamera() {
        synchronized (this) {
            Camera camera = this.mCamera;
            if (camera != null) {
                camera.stopPreview();
                this.mCamera.setPreviewCallback(null);
                this.mCamera.release();
            }
            this.mCamera = null;
        }
    }

    @Override // org.visp.android.CameraBridgeViewBase
    protected boolean connectCamera(int i, int i2) {
        Log.d(TAG, "Connecting to camera");
        if (initializeCamera(i, i2)) {
            this.mCameraFrameReady = false;
            Log.d(TAG, "Starting processing thread");
            this.mStopThread = false;
            Thread thread = new Thread(new CameraWorker());
            this.mThread = thread;
            thread.start();
            return true;
        }
        return false;
    }

    @Override // org.visp.android.CameraBridgeViewBase
    protected void disconnectCamera() {
        Log.d(TAG, "Disconnecting from camera");
        try {
            try {
                this.mStopThread = true;
                Log.d(TAG, "Notify thread");
                synchronized (this) {
                    notify();
                }
                Log.d(TAG, "Waiting for thread");
                Thread thread = this.mThread;
                if (thread != null) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.mThread = null;
            releaseCamera();
            this.mCameraFrameReady = false;
        } catch (Throwable th) {
            this.mThread = null;
            throw th;
        }
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Log.d(TAG, "Preview Frame received. Frame size: " + bArr.length);
        synchronized (this) {
            this.mFrameChain[this.mChainIdx] = bArr;
            this.mCameraFrameReady = true;
            notify();
        }
        Camera camera2 = this.mCamera;
        if (camera2 != null) {
            camera2.addCallbackBuffer(this.mBuffer);
        }
    }

    /* loaded from: classes4.dex */
    public class JavaCameraFrame implements CameraBridgeViewBase.VpCameraViewFrame {
        private byte[] data;
        private int h;
        private int w;
        private VpImageRGBa rgba = null;
        private VpImageUChar gray = null;

        public void release() {
        }

        @Override // org.visp.android.CameraBridgeViewBase.VpCameraViewFrame
        public VpImageUChar gray() {
            if (this.gray == null) {
                this.gray = new VpImageUChar(this.data, this.w, this.h, true);
            }
            return this.gray;
        }

        @Override // org.visp.android.CameraBridgeViewBase.VpCameraViewFrame
        public VpImageRGBa rgba() {
            if (this.rgba == null) {
                if (JavaCameraView.this.mPreviewFormat == 17) {
                    this.rgba = new VpImageRGBa(this.data, this.w, this.h, true);
                } else {
                    throw new IllegalArgumentException("Preview Format can be NV21");
                }
            }
            return this.rgba;
        }

        public JavaCameraFrame(byte[] bArr, int i, int i2) {
            JavaCameraView.this = r1;
            this.data = bArr;
        }
    }

    /* loaded from: classes4.dex */
    private class CameraWorker implements Runnable {
        private CameraWorker() {
            JavaCameraView.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            do {
                synchronized (JavaCameraView.this) {
                    while (!JavaCameraView.this.mCameraFrameReady && !JavaCameraView.this.mStopThread) {
                        try {
                            JavaCameraView.this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    z = false;
                    if (JavaCameraView.this.mCameraFrameReady) {
                        JavaCameraView javaCameraView = JavaCameraView.this;
                        javaCameraView.mChainIdx = 1 - javaCameraView.mChainIdx;
                        JavaCameraView.this.mCameraFrameReady = false;
                        z = true;
                    }
                }
                if (!JavaCameraView.this.mStopThread && z && JavaCameraView.this.mFrameChain[1 - JavaCameraView.this.mChainIdx].length > 0) {
                    JavaCameraView javaCameraView2 = JavaCameraView.this;
                    javaCameraView2.deliverAndDrawFrame(javaCameraView2.mCameraFrame[1 - JavaCameraView.this.mChainIdx]);
                }
            } while (!JavaCameraView.this.mStopThread);
            Log.d(JavaCameraView.TAG, "Finish processing thread");
        }
    }
}
