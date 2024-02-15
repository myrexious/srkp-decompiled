package org.opencv.android;

import android.hardware.Camera;
import android.util.Log;

/* loaded from: classes4.dex */
public class CameraRenderer extends CameraGLRendererBase {
    public static final String LOGTAG = "CameraRenderer";
    private Camera mCamera;
    private boolean mPreviewStarted;

    CameraRenderer(CameraGLSurfaceView cameraGLSurfaceView) {
        super(cameraGLSurfaceView);
        this.mPreviewStarted = false;
    }

    @Override // org.opencv.android.CameraGLRendererBase
    protected synchronized void closeCamera() {
        Log.i("CameraRenderer", "closeCamera");
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.stopPreview();
            this.mPreviewStarted = false;
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:129:0x00e1 A[Catch: all -> 0x018f, TryCatch #4 {, blocks: (B:90:0x0003, B:92:0x0012, B:93:0x0019, B:97:0x0037, B:100:0x003c, B:102:0x0042, B:103:0x0060, B:109:0x0093, B:106:0x0069, B:137:0x013f, B:139:0x0143, B:142:0x014c, B:144:0x0156, B:146:0x015e, B:147:0x0163, B:148:0x0168, B:151:0x0171, B:96:0x0021, B:110:0x0096, B:112:0x00a0, B:113:0x00ac, B:115:0x00b2, B:118:0x00ba, B:129:0x00e1, B:131:0x00eb, B:132:0x00f3, B:133:0x0111, B:136:0x0119, B:119:0x00bd, B:121:0x00c1, B:122:0x00cd, B:124:0x00d3, B:127:0x00dc), top: B:165:0x0003, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0143 A[Catch: all -> 0x018f, TRY_LEAVE, TryCatch #4 {, blocks: (B:90:0x0003, B:92:0x0012, B:93:0x0019, B:97:0x0037, B:100:0x003c, B:102:0x0042, B:103:0x0060, B:109:0x0093, B:106:0x0069, B:137:0x013f, B:139:0x0143, B:142:0x014c, B:144:0x0156, B:146:0x015e, B:147:0x0163, B:148:0x0168, B:151:0x0171, B:96:0x0021, B:110:0x0096, B:112:0x00a0, B:113:0x00ac, B:115:0x00b2, B:118:0x00ba, B:129:0x00e1, B:131:0x00eb, B:132:0x00f3, B:133:0x0111, B:136:0x0119, B:119:0x00bd, B:121:0x00c1, B:122:0x00cd, B:124:0x00d3, B:127:0x00dc), top: B:165:0x0003, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x014c A[Catch: all -> 0x018f, TRY_ENTER, TryCatch #4 {, blocks: (B:90:0x0003, B:92:0x0012, B:93:0x0019, B:97:0x0037, B:100:0x003c, B:102:0x0042, B:103:0x0060, B:109:0x0093, B:106:0x0069, B:137:0x013f, B:139:0x0143, B:142:0x014c, B:144:0x0156, B:146:0x015e, B:147:0x0163, B:148:0x0168, B:151:0x0171, B:96:0x0021, B:110:0x0096, B:112:0x00a0, B:113:0x00ac, B:115:0x00b2, B:118:0x00ba, B:129:0x00e1, B:131:0x00eb, B:132:0x00f3, B:133:0x0111, B:136:0x0119, B:119:0x00bd, B:121:0x00c1, B:122:0x00cd, B:124:0x00d3, B:127:0x00dc), top: B:165:0x0003, inners: #0, #1, #2, #3 }] */
    @Override // org.opencv.android.CameraGLRendererBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected synchronized void openCamera(int r7) {
        /*
            Method dump skipped, instructions count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.opencv.android.CameraRenderer.openCamera(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0108 A[Catch: all -> 0x012c, TryCatch #0 {, blocks: (B:65:0x0003, B:67:0x0023, B:70:0x002c, B:72:0x0030, B:74:0x0034, B:75:0x0036, B:77:0x003a, B:79:0x003e, B:80:0x0040, B:82:0x0050, B:83:0x005a, B:85:0x0060, B:90:0x0094, B:96:0x00af, B:98:0x0104, B:100:0x0108, B:101:0x010f, B:97:0x00d2, B:102:0x0116), top: B:108:0x0003 }] */
    @Override // org.opencv.android.CameraGLRendererBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void setCameraPreviewSize(int r14, int r15) {
        /*
            Method dump skipped, instructions count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.opencv.android.CameraRenderer.setCameraPreviewSize(int, int):void");
    }
}
