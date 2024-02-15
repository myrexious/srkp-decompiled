package org.opencv.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import java.text.DecimalFormat;
import org.opencv.core.Core;

/* loaded from: classes4.dex */
public class FpsMeter {
    private static final DecimalFormat FPS_FORMAT = new DecimalFormat("0.00");
    private static final int STEP = 20;
    private static final String TAG = "FpsMeter";
    private int mFramesCouner;
    private double mFrequency;
    Paint mPaint;
    private String mStrfps;
    private long mprevFrameTime;
    boolean mIsInitialized = false;
    int mWidth = 0;
    int mHeight = 0;

    public void init() {
        this.mFramesCouner = 0;
        this.mFrequency = Core.getTickFrequency();
        this.mprevFrameTime = Core.getTickCount();
        this.mStrfps = "";
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(-16776961);
        this.mPaint.setTextSize(20.0f);
    }

    public void measure() {
        if (!this.mIsInitialized) {
            init();
            this.mIsInitialized = true;
            return;
        }
        int i = this.mFramesCouner + 1;
        this.mFramesCouner = i;
        if (i % 20 == 0) {
            long tickCount = Core.getTickCount();
            double d = (this.mFrequency * 20.0d) / (tickCount - this.mprevFrameTime);
            this.mprevFrameTime = tickCount;
            if (this.mWidth != 0 && this.mHeight != 0) {
                this.mStrfps = FPS_FORMAT.format(d) + " FPS@" + Integer.valueOf(this.mWidth) + "x" + Integer.valueOf(this.mHeight);
            } else {
                this.mStrfps = FPS_FORMAT.format(d) + " FPS";
            }
            Log.i(TAG, this.mStrfps);
        }
    }

    public void setResolution(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public void draw(Canvas canvas, float f, float f2) {
        Log.d(TAG, this.mStrfps);
        canvas.drawText(this.mStrfps, f, f2, this.mPaint);
    }
}
