package org.visp.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.List;
import org.visp.R;
import org.visp.core.VpImageRGBa;
import org.visp.core.VpImageUChar;

/* loaded from: classes4.dex */
public abstract class CameraBridgeViewBase extends SurfaceView implements SurfaceHolder.Callback {
    public static final int CAMERA_ID_ANY = -1;
    public static final int CAMERA_ID_BACK = 99;
    public static final int CAMERA_ID_FRONT = 98;
    public static final int GRAY = 2;
    private static final int MAX_UNSPECIFIED = -1;
    public static final int RGBA = 1;
    private static final int STARTED = 1;
    private static final int STOPPED = 0;
    private static final String TAG = "CameraBridge";
    private Bitmap mCacheBitmap;
    protected int mCameraIndex;
    protected boolean mEnabled;
    protected FpsMeter mFpsMeter;
    protected int mFrameHeight;
    protected int mFrameWidth;
    private VpCameraViewListener2 mListener;
    protected int mMaxHeight;
    protected int mMaxWidth;
    protected int mPreviewFormat;
    protected float mScale;
    private int mState;
    private boolean mSurfaceExist;
    private final Object mSyncObject;

    /* loaded from: classes4.dex */
    public interface ListItemAccessor {
        int getHeight(Object obj);

        int getWidth(Object obj);
    }

    /* loaded from: classes4.dex */
    public interface VpCameraViewFrame {
        VpImageUChar gray();

        VpImageRGBa rgba();
    }

    /* loaded from: classes4.dex */
    public interface VpCameraViewListener {
        VpImageRGBa onCameraFrame(VpImageRGBa vpImageRGBa);

        VpImageUChar onCameraFrame(VpImageUChar vpImageUChar);

        void onCameraViewStarted(int i, int i2);

        void onCameraViewStopped();
    }

    /* loaded from: classes4.dex */
    public interface VpCameraViewListener2 {
        long onCameraFrame(VpCameraViewFrame vpCameraViewFrame);

        void onCameraViewStarted(int i, int i2);

        void onCameraViewStopped();
    }

    private void onEnterStoppedState() {
    }

    private void onExitStoppedState() {
    }

    protected abstract boolean connectCamera(int i, int i2);

    protected abstract void disconnectCamera();

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    public CameraBridgeViewBase(Context context, int i) {
        super(context);
        this.mState = 0;
        this.mSyncObject = new Object();
        this.mScale = 0.0f;
        this.mPreviewFormat = 1;
        this.mFpsMeter = null;
        this.mCameraIndex = i;
        getHolder().addCallback(this);
        this.mMaxWidth = -1;
        this.mMaxHeight = -1;
    }

    public CameraBridgeViewBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mState = 0;
        this.mSyncObject = new Object();
        this.mScale = 0.0f;
        this.mPreviewFormat = 1;
        this.mCameraIndex = -1;
        this.mFpsMeter = null;
        Log.d(TAG, "Attr count: " + Integer.valueOf(attributeSet.getAttributeCount()));
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CameraBridgeViewBase);
        if (obtainStyledAttributes.getBoolean(R.styleable.CameraBridgeViewBase_show_fps, false)) {
            enableFpsMeter();
        }
        this.mCameraIndex = obtainStyledAttributes.getInt(R.styleable.CameraBridgeViewBase_camera_id, -1);
        getHolder().addCallback(this);
        this.mMaxWidth = -1;
        this.mMaxHeight = -1;
        obtainStyledAttributes.recycle();
    }

    public void setCameraIndex(int i) {
        this.mCameraIndex = i;
    }

    /* loaded from: classes4.dex */
    protected class VpCameraViewListenerAdapter implements VpCameraViewListener2 {
        private VpCameraViewListener mOldStyleListener;
        private int mPreviewFormat = 1;

        public VpCameraViewListenerAdapter(VpCameraViewListener vpCameraViewListener) {
            CameraBridgeViewBase.this = r1;
            this.mOldStyleListener = vpCameraViewListener;
        }

        @Override // org.visp.android.CameraBridgeViewBase.VpCameraViewListener2
        public void onCameraViewStarted(int i, int i2) {
            this.mOldStyleListener.onCameraViewStarted(i, i2);
        }

        @Override // org.visp.android.CameraBridgeViewBase.VpCameraViewListener2
        public void onCameraViewStopped() {
            this.mOldStyleListener.onCameraViewStopped();
        }

        @Override // org.visp.android.CameraBridgeViewBase.VpCameraViewListener2
        public long onCameraFrame(VpCameraViewFrame vpCameraViewFrame) {
            int i = this.mPreviewFormat;
            if (i != 1) {
                if (i == 2) {
                    return this.mOldStyleListener.onCameraFrame(vpCameraViewFrame.gray()).nativeObj;
                }
                Log.e(CameraBridgeViewBase.TAG, "Invalid frame format! Only RGBA and Gray Scale are supported!");
                return -1L;
            }
            return this.mOldStyleListener.onCameraFrame(vpCameraViewFrame.rgba()).nativeObj;
        }

        public void setFrameFormat(int i) {
            this.mPreviewFormat = i;
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Log.d(TAG, "call surfaceChanged event");
        synchronized (this.mSyncObject) {
            if (!this.mSurfaceExist) {
                this.mSurfaceExist = true;
                checkCurrentState();
            } else {
                this.mSurfaceExist = false;
                checkCurrentState();
                this.mSurfaceExist = true;
                checkCurrentState();
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        synchronized (this.mSyncObject) {
            this.mSurfaceExist = false;
            checkCurrentState();
        }
    }

    public void enableView() {
        synchronized (this.mSyncObject) {
            this.mEnabled = true;
            checkCurrentState();
        }
    }

    public void disableView() {
        synchronized (this.mSyncObject) {
            this.mEnabled = false;
            checkCurrentState();
        }
    }

    public void enableFpsMeter() {
        if (this.mFpsMeter == null) {
            FpsMeter fpsMeter = new FpsMeter();
            this.mFpsMeter = fpsMeter;
            fpsMeter.setResolution(this.mFrameWidth, this.mFrameHeight);
        }
    }

    public void disableFpsMeter() {
        this.mFpsMeter = null;
    }

    public void setVpCameraViewListener(VpCameraViewListener2 vpCameraViewListener2) {
        this.mListener = vpCameraViewListener2;
    }

    public void setVpCameraViewListener(VpCameraViewListener vpCameraViewListener) {
        VpCameraViewListenerAdapter vpCameraViewListenerAdapter = new VpCameraViewListenerAdapter(vpCameraViewListener);
        vpCameraViewListenerAdapter.setFrameFormat(this.mPreviewFormat);
        this.mListener = vpCameraViewListenerAdapter;
    }

    public void setMaxFrameSize(int i, int i2) {
        this.mMaxWidth = i;
        this.mMaxHeight = i2;
    }

    public void SetCaptureFormat(int i) {
        this.mPreviewFormat = i;
        VpCameraViewListener2 vpCameraViewListener2 = this.mListener;
        if (vpCameraViewListener2 instanceof VpCameraViewListenerAdapter) {
            ((VpCameraViewListenerAdapter) vpCameraViewListener2).setFrameFormat(i);
        }
    }

    private void checkCurrentState() {
        Log.d(TAG, "call checkCurrentState");
        int i = (this.mEnabled && this.mSurfaceExist && getVisibility() == 0) ? 1 : 0;
        int i2 = this.mState;
        if (i != i2) {
            processExitState(i2);
            this.mState = i;
            processEnterState(i);
        }
    }

    private void processEnterState(int i) {
        Log.d(TAG, "call processEnterState: " + i);
        if (i == 0) {
            onEnterStoppedState();
            VpCameraViewListener2 vpCameraViewListener2 = this.mListener;
            if (vpCameraViewListener2 != null) {
                vpCameraViewListener2.onCameraViewStopped();
            }
        } else if (i != 1) {
        } else {
            onEnterStartedState();
            VpCameraViewListener2 vpCameraViewListener22 = this.mListener;
            if (vpCameraViewListener22 != null) {
                vpCameraViewListener22.onCameraViewStarted(this.mFrameWidth, this.mFrameHeight);
            }
        }
    }

    private void processExitState(int i) {
        Log.d(TAG, "call processExitState: " + i);
        if (i == 0) {
            onExitStoppedState();
        } else if (i != 1) {
        } else {
            onExitStartedState();
        }
    }

    private void onEnterStartedState() {
        Log.d(TAG, "call onEnterStartedState");
        if (connectCamera(getWidth(), getHeight())) {
            return;
        }
        AlertDialog create = new AlertDialog.Builder(getContext()).create();
        create.setCancelable(false);
        create.setMessage("It seems that you device does not support camera (or it is locked). Application will be closed.");
        create.setButton(-3, "OK", new DialogInterface.OnClickListener() { // from class: org.visp.android.CameraBridgeViewBase.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ((Activity) CameraBridgeViewBase.this.getContext()).finish();
            }
        });
        create.show();
    }

    private void onExitStartedState() {
        disconnectCamera();
        Bitmap bitmap = this.mCacheBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void deliverAndDrawFrame(org.visp.android.CameraBridgeViewBase.VpCameraViewFrame r12) {
        /*
            Method dump skipped, instructions count: 503
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.visp.android.CameraBridgeViewBase.deliverAndDrawFrame(org.visp.android.CameraBridgeViewBase$VpCameraViewFrame):void");
    }

    public void AllocateCache() {
        this.mCacheBitmap = Bitmap.createBitmap(this.mFrameWidth, this.mFrameHeight, Bitmap.Config.ARGB_8888);
    }

    public int[] calculateCameraFrameSize(List<?> list, ListItemAccessor listItemAccessor, int i, int i2) {
        int i3 = this.mMaxWidth;
        if (i3 != -1 && i3 < i) {
            i = i3;
        }
        int i4 = this.mMaxHeight;
        if (i4 != -1 && i4 < i2) {
            i2 = i4;
        }
        int i5 = 0;
        int i6 = 0;
        for (Object obj : list) {
            int width = listItemAccessor.getWidth(obj);
            int height = listItemAccessor.getHeight(obj);
            if (width <= i && height <= i2 && width >= i5 && height >= i6) {
                i6 = height;
                i5 = width;
            }
        }
        return new int[]{i5, i6};
    }
}
