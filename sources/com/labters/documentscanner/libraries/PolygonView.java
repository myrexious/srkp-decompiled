package com.labters.documentscanner.libraries;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Magnifier;
import com.labters.documentscanner.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class PolygonView extends FrameLayout {
    protected Context context;
    private Magnifier magnifier;
    private ImageView midPointer12;
    private ImageView midPointer13;
    private ImageView midPointer24;
    private ImageView midPointer34;
    private Paint paint;
    private ImageView pointer1;
    private ImageView pointer2;
    private ImageView pointer3;
    private ImageView pointer4;
    private PolygonView polygonView;

    public PolygonView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PolygonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        init();
    }

    public PolygonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        init();
    }

    private void init() {
        this.polygonView = this;
        this.pointer1 = getImageView(0, 0);
        this.pointer2 = getImageView(getWidth(), 0);
        this.pointer3 = getImageView(0, getHeight());
        this.pointer4 = getImageView(getWidth(), getHeight());
        ImageView imageView = getImageView(0, getHeight() / 2);
        this.midPointer13 = imageView;
        imageView.setOnTouchListener(new MidPointTouchListenerImpl(this.pointer1, this.pointer3));
        if (Build.VERSION.SDK_INT >= 28) {
            this.magnifier = new Magnifier(this.polygonView);
        }
        ImageView imageView2 = getImageView(0, getWidth() / 2);
        this.midPointer12 = imageView2;
        imageView2.setOnTouchListener(new MidPointTouchListenerImpl(this.pointer1, this.pointer2));
        ImageView imageView3 = getImageView(0, getHeight() / 2);
        this.midPointer34 = imageView3;
        imageView3.setOnTouchListener(new MidPointTouchListenerImpl(this.pointer3, this.pointer4));
        ImageView imageView4 = getImageView(0, getHeight() / 2);
        this.midPointer24 = imageView4;
        imageView4.setOnTouchListener(new MidPointTouchListenerImpl(this.pointer2, this.pointer4));
        addView(this.pointer1);
        addView(this.pointer2);
        addView(this.midPointer13);
        addView(this.midPointer12);
        addView(this.midPointer34);
        addView(this.midPointer24);
        addView(this.pointer3);
        addView(this.pointer4);
        initPaint();
    }

    @Override // android.view.ViewGroup
    protected void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.attachViewToParent(view, i, layoutParams);
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(getResources().getColor(R.color.blue));
        this.paint.setStrokeWidth(2.0f);
        this.paint.setAntiAlias(true);
    }

    public Map<Integer, PointF> getPoints() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PointF(this.pointer1.getX(), this.pointer1.getY()));
        arrayList.add(new PointF(this.pointer2.getX(), this.pointer2.getY()));
        arrayList.add(new PointF(this.pointer3.getX(), this.pointer3.getY()));
        arrayList.add(new PointF(this.pointer4.getX(), this.pointer4.getY()));
        return getOrderedPoints(arrayList);
    }

    public Map<Integer, PointF> getOrderedPoints(List<PointF> list) {
        int i;
        PointF pointF = new PointF();
        int size = list.size();
        for (PointF pointF2 : list) {
            float f = size;
            pointF.x += pointF2.x / f;
            pointF.y += pointF2.y / f;
        }
        HashMap hashMap = new HashMap();
        for (PointF pointF3 : list) {
            if (pointF3.x < pointF.x && pointF3.y < pointF.y) {
                i = 0;
            } else if (pointF3.x > pointF.x && pointF3.y < pointF.y) {
                i = 1;
            } else if (pointF3.x >= pointF.x || pointF3.y <= pointF.y) {
                i = (pointF3.x <= pointF.x || pointF3.y <= pointF.y) ? -1 : 3;
            } else {
                i = 2;
            }
            hashMap.put(Integer.valueOf(i), pointF3);
        }
        return hashMap;
    }

    public void setPoints(Map<Integer, PointF> map) {
        if (map.size() == 4) {
            setPointsCoordinates(map);
        }
    }

    public void setPointColor(int i) {
        Paint paint = this.paint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    private void setPointsCoordinates(Map<Integer, PointF> map) {
        this.pointer1.setX(map.get(0).x);
        this.pointer1.setY(map.get(0).y);
        this.pointer2.setX(map.get(1).x);
        this.pointer2.setY(map.get(1).y);
        this.pointer3.setX(map.get(2).x);
        this.pointer3.setY(map.get(2).y);
        this.pointer4.setX(map.get(3).x);
        this.pointer4.setY(map.get(3).y);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawLine(this.pointer1.getX() + (this.pointer1.getWidth() / 2), this.pointer1.getY() + (this.pointer1.getHeight() / 2), this.pointer3.getX() + (this.pointer3.getWidth() / 2), this.pointer3.getY() + (this.pointer3.getHeight() / 2), this.paint);
        canvas.drawLine(this.pointer1.getX() + (this.pointer1.getWidth() / 2), this.pointer1.getY() + (this.pointer1.getHeight() / 2), this.pointer2.getX() + (this.pointer2.getWidth() / 2), this.pointer2.getY() + (this.pointer2.getHeight() / 2), this.paint);
        canvas.drawLine(this.pointer2.getX() + (this.pointer2.getWidth() / 2), this.pointer2.getY() + (this.pointer2.getHeight() / 2), this.pointer4.getX() + (this.pointer4.getWidth() / 2), this.pointer4.getY() + (this.pointer4.getHeight() / 2), this.paint);
        canvas.drawLine(this.pointer3.getX() + (this.pointer3.getWidth() / 2), this.pointer3.getY() + (this.pointer3.getHeight() / 2), this.pointer4.getX() + (this.pointer4.getWidth() / 2), this.pointer4.getY() + (this.pointer4.getHeight() / 2), this.paint);
        this.midPointer13.setX(this.pointer3.getX() - ((this.pointer3.getX() - this.pointer1.getX()) / 2.0f));
        this.midPointer13.setY(this.pointer3.getY() - ((this.pointer3.getY() - this.pointer1.getY()) / 2.0f));
        this.midPointer24.setX(this.pointer4.getX() - ((this.pointer4.getX() - this.pointer2.getX()) / 2.0f));
        this.midPointer24.setY(this.pointer4.getY() - ((this.pointer4.getY() - this.pointer2.getY()) / 2.0f));
        this.midPointer34.setX(this.pointer4.getX() - ((this.pointer4.getX() - this.pointer3.getX()) / 2.0f));
        this.midPointer34.setY(this.pointer4.getY() - ((this.pointer4.getY() - this.pointer3.getY()) / 2.0f));
        this.midPointer12.setX(this.pointer2.getX() - ((this.pointer2.getX() - this.pointer1.getX()) / 2.0f));
        this.midPointer12.setY(this.pointer2.getY() - ((this.pointer2.getY() - this.pointer1.getY()) / 2.0f));
    }

    public void drawMag(float f, float f2) {
        Magnifier magnifier;
        if (Build.VERSION.SDK_INT < 28 || (magnifier = this.magnifier) == null) {
            return;
        }
        magnifier.show(f, f2);
    }

    public void dismissMag() {
        Magnifier magnifier;
        if (Build.VERSION.SDK_INT < 28 || (magnifier = this.magnifier) == null) {
            return;
        }
        magnifier.dismiss();
    }

    private ImageView getImageView(int i, int i2) {
        ImageView imageView = new ImageView(this.context);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        imageView.setImageResource(R.drawable.circle);
        imageView.setX(i);
        imageView.setY(i2);
        imageView.setOnTouchListener(new TouchListenerImpl());
        return imageView;
    }

    /* loaded from: classes3.dex */
    public class MidPointTouchListenerImpl implements View.OnTouchListener {
        PointF DownPT = new PointF();
        PointF StartPT = new PointF();
        private ImageView mainPointer1;
        private ImageView mainPointer2;

        public MidPointTouchListenerImpl(ImageView imageView, ImageView imageView2) {
            PolygonView.this = r1;
            this.mainPointer1 = imageView;
            this.mainPointer2 = imageView2;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int color;
            int action = motionEvent.getAction();
            if (action == 0) {
                this.DownPT.x = motionEvent.getX();
                this.DownPT.y = motionEvent.getY();
                this.StartPT = new PointF(view.getX(), view.getY());
            } else if (action == 1) {
                PolygonView polygonView = PolygonView.this;
                if (polygonView.isValidShape(polygonView.getPoints())) {
                    color = PolygonView.this.getResources().getColor(R.color.blue);
                } else {
                    color = PolygonView.this.getResources().getColor(R.color.orange);
                }
                PolygonView.this.paint.setColor(color);
                PolygonView.this.dismissMag();
            } else if (action == 2) {
                PointF pointF = new PointF(motionEvent.getX() - this.DownPT.x, motionEvent.getY() - this.DownPT.y);
                if (Math.abs(this.mainPointer1.getX() - this.mainPointer2.getX()) > Math.abs(this.mainPointer1.getY() - this.mainPointer2.getY())) {
                    if (this.mainPointer2.getY() + pointF.y + view.getHeight() < PolygonView.this.polygonView.getHeight() && this.mainPointer2.getY() + pointF.y > 0.0f) {
                        view.setX((int) (this.StartPT.y + pointF.y));
                        this.StartPT = new PointF(view.getX(), view.getY());
                        ImageView imageView = this.mainPointer2;
                        imageView.setY((int) (imageView.getY() + pointF.y));
                    }
                    if (this.mainPointer1.getY() + pointF.y + view.getHeight() < PolygonView.this.polygonView.getHeight() && this.mainPointer1.getY() + pointF.y > 0.0f) {
                        view.setX((int) (this.StartPT.y + pointF.y));
                        this.StartPT = new PointF(view.getX(), view.getY());
                        ImageView imageView2 = this.mainPointer1;
                        imageView2.setY((int) (imageView2.getY() + pointF.y));
                    }
                } else {
                    if (this.mainPointer2.getX() + pointF.x + view.getWidth() < PolygonView.this.polygonView.getWidth() && this.mainPointer2.getX() + pointF.x > 0.0f) {
                        view.setX((int) (this.StartPT.x + pointF.x));
                        this.StartPT = new PointF(view.getX(), view.getY());
                        ImageView imageView3 = this.mainPointer2;
                        imageView3.setX((int) (imageView3.getX() + pointF.x));
                    }
                    if (this.mainPointer1.getX() + pointF.x + view.getWidth() < PolygonView.this.polygonView.getWidth() && this.mainPointer1.getX() + pointF.x > 0.0f) {
                        view.setX((int) (this.StartPT.x + pointF.x));
                        this.StartPT = new PointF(view.getX(), view.getY());
                        ImageView imageView4 = this.mainPointer1;
                        imageView4.setX((int) (imageView4.getX() + pointF.x));
                    }
                }
                PolygonView.this.drawMag(this.StartPT.x + 50.0f, this.StartPT.y + 50.0f);
            }
            PolygonView.this.polygonView.invalidate();
            return true;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public boolean isValidShape(Map<Integer, PointF> map) {
        return map.size() == 4;
    }

    /* loaded from: classes3.dex */
    public class TouchListenerImpl implements View.OnTouchListener {
        PointF DownPT;
        PointF StartPT;

        private TouchListenerImpl() {
            PolygonView.this = r1;
            this.DownPT = new PointF();
            this.StartPT = new PointF();
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int color;
            int action = motionEvent.getAction();
            if (action == 0) {
                this.DownPT.x = motionEvent.getX();
                this.DownPT.y = motionEvent.getY();
                this.StartPT = new PointF(view.getX(), view.getY());
            } else if (action == 1) {
                PolygonView polygonView = PolygonView.this;
                if (polygonView.isValidShape(polygonView.getPoints())) {
                    color = PolygonView.this.getResources().getColor(R.color.blue);
                } else {
                    color = PolygonView.this.getResources().getColor(R.color.orange);
                }
                PolygonView.this.paint.setColor(color);
                PolygonView.this.dismissMag();
            } else if (action == 2) {
                PointF pointF = new PointF(motionEvent.getX() - this.DownPT.x, motionEvent.getY() - this.DownPT.y);
                if (this.StartPT.x + pointF.x + view.getWidth() < PolygonView.this.polygonView.getWidth() && this.StartPT.y + pointF.y + view.getHeight() < PolygonView.this.polygonView.getHeight() && this.StartPT.x + pointF.x > 0.0f && this.StartPT.y + pointF.y > 0.0f) {
                    view.setX((int) (this.StartPT.x + pointF.x));
                    view.setY((int) (this.StartPT.y + pointF.y));
                    PointF pointF2 = new PointF(view.getX(), view.getY());
                    this.StartPT = pointF2;
                    PolygonView.this.drawMag(pointF2.x + 50.0f, this.StartPT.y + 50.0f);
                }
            }
            PolygonView.this.polygonView.invalidate();
            return true;
        }
    }
}
