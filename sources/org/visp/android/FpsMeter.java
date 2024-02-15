package org.visp.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import java.text.DecimalFormat;

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

    public void init(int i, long j) {
        this.mFramesCouner = 0;
        this.mFrequency = i;
        this.mprevFrameTime = j;
        this.mStrfps = "";
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(-16776961);
        this.mPaint.setTextSize(20.0f);
    }

    public void measure(int i, long j) {
        if (!this.mIsInitialized) {
            init(i, j);
            this.mIsInitialized = true;
            return;
        }
        int i2 = this.mFramesCouner + 1;
        this.mFramesCouner = i2;
        if (i2 % 20 == 0) {
            double d = (this.mFrequency * 20.0d) / (j - this.mprevFrameTime);
            this.mprevFrameTime = j;
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
