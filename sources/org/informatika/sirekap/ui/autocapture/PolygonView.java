package org.informatika.sirekap.ui.autocapture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.PolygonPoints;
import org.informatika.sirekap.support.CameraUtilsKt;
import org.informatika.sirekap.ui.autocapture.AutoCaptureFragment;
import org.opencv.core.Point;

/* compiled from: PolygonView.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u000256B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0014J \u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020\tH\u0002J\u0018\u00100\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\tH\u0002J\"\u00101\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020!0 2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020!02H\u0002J\u001c\u00103\u001a\u00020\u00102\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020!0 H\u0002J\u001c\u00104\u001a\u00020)2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020!0 H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0011R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R<\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020!0 2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020!0 8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/PolygonView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "circleFillPaint", "Landroid/graphics/Paint;", "circleRadius", "Ljava/lang/Integer;", "isValidPointer1", "", "()Z", "isValidPointer2", "isValidPointer3", "isValidPointer4", "midPointer12", "Landroid/widget/ImageView;", "midPointer13", "midPointer24", "midPointer34", "paint", "pointer1", "pointer2", "pointer3", "pointer4", "pointMap", "", "Lorg/opencv/core/Point;", "points", "getPoints", "()Ljava/util/Map;", "setPoints", "(Ljava/util/Map;)V", "polygonView", "dispatchDraw", "", "canvas", "Landroid/graphics/Canvas;", "getImageView", "x", OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "pointIndex", "getImageViewTransparent", "getOrderedPoints", "", "isValidShape", "setPointsCoordinates", "MidPointTouchListenerImpl", "TouchListenerImpl", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class PolygonView extends FrameLayout {
    private Paint circleFillPaint;
    private final Integer circleRadius;
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PolygonView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.polygonView = this;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.circle);
        this.circleRadius = drawable != null ? Integer.valueOf(drawable.getIntrinsicHeight()) : null;
        this.pointer1 = getImageView(0, 0, 0);
        this.pointer2 = getImageView(getWidth(), 0, 1);
        this.pointer3 = getImageView(0, getHeight(), 2);
        this.pointer4 = getImageView(getWidth(), getHeight(), 3);
        ImageView imageViewTransparent = getImageViewTransparent(0, getHeight() / 2);
        this.midPointer13 = imageViewTransparent;
        imageViewTransparent.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer1, this.pointer3, 0, 2));
        ImageView imageViewTransparent2 = getImageViewTransparent(getWidth() / 2, 0);
        this.midPointer12 = imageViewTransparent2;
        imageViewTransparent2.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer1, this.pointer2, 0, 1));
        ImageView imageViewTransparent3 = getImageViewTransparent(getWidth() / 2, getHeight());
        this.midPointer34 = imageViewTransparent3;
        imageViewTransparent3.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer3, this.pointer4, 2, 3));
        ImageView imageViewTransparent4 = getImageViewTransparent(getWidth(), getHeight() / 2);
        this.midPointer24 = imageViewTransparent4;
        imageViewTransparent4.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer2, this.pointer4, 1, 3));
        addView(this.pointer1);
        addView(this.pointer2);
        addView(this.midPointer13);
        addView(this.midPointer12);
        addView(this.midPointer34);
        addView(this.midPointer24);
        addView(this.pointer3);
        addView(this.pointer4);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        this.paint.setStrokeWidth(7.0f);
        this.paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.circleFillPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.circleFillPaint.setStrokeWidth(5.0f);
        this.circleFillPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        this.circleFillPaint.setAntiAlias(true);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PolygonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.polygonView = this;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.circle);
        this.circleRadius = drawable != null ? Integer.valueOf(drawable.getIntrinsicHeight()) : null;
        this.pointer1 = getImageView(0, 0, 0);
        this.pointer2 = getImageView(getWidth(), 0, 1);
        this.pointer3 = getImageView(0, getHeight(), 2);
        this.pointer4 = getImageView(getWidth(), getHeight(), 3);
        ImageView imageViewTransparent = getImageViewTransparent(0, getHeight() / 2);
        this.midPointer13 = imageViewTransparent;
        imageViewTransparent.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer1, this.pointer3, 0, 2));
        ImageView imageViewTransparent2 = getImageViewTransparent(getWidth() / 2, 0);
        this.midPointer12 = imageViewTransparent2;
        imageViewTransparent2.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer1, this.pointer2, 0, 1));
        ImageView imageViewTransparent3 = getImageViewTransparent(getWidth() / 2, getHeight());
        this.midPointer34 = imageViewTransparent3;
        imageViewTransparent3.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer3, this.pointer4, 2, 3));
        ImageView imageViewTransparent4 = getImageViewTransparent(getWidth(), getHeight() / 2);
        this.midPointer24 = imageViewTransparent4;
        imageViewTransparent4.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer2, this.pointer4, 1, 3));
        addView(this.pointer1);
        addView(this.pointer2);
        addView(this.midPointer13);
        addView(this.midPointer12);
        addView(this.midPointer34);
        addView(this.midPointer24);
        addView(this.pointer3);
        addView(this.pointer4);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        this.paint.setStrokeWidth(7.0f);
        this.paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.circleFillPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.circleFillPaint.setStrokeWidth(5.0f);
        this.circleFillPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        this.circleFillPaint.setAntiAlias(true);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PolygonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.polygonView = this;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.circle);
        this.circleRadius = drawable != null ? Integer.valueOf(drawable.getIntrinsicHeight()) : null;
        this.pointer1 = getImageView(0, 0, 0);
        this.pointer2 = getImageView(getWidth(), 0, 1);
        this.pointer3 = getImageView(0, getHeight(), 2);
        this.pointer4 = getImageView(getWidth(), getHeight(), 3);
        ImageView imageViewTransparent = getImageViewTransparent(0, getHeight() / 2);
        this.midPointer13 = imageViewTransparent;
        imageViewTransparent.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer1, this.pointer3, 0, 2));
        ImageView imageViewTransparent2 = getImageViewTransparent(getWidth() / 2, 0);
        this.midPointer12 = imageViewTransparent2;
        imageViewTransparent2.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer1, this.pointer2, 0, 1));
        ImageView imageViewTransparent3 = getImageViewTransparent(getWidth() / 2, getHeight());
        this.midPointer34 = imageViewTransparent3;
        imageViewTransparent3.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer3, this.pointer4, 2, 3));
        ImageView imageViewTransparent4 = getImageViewTransparent(getWidth(), getHeight() / 2);
        this.midPointer24 = imageViewTransparent4;
        imageViewTransparent4.setOnTouchListener(new MidPointTouchListenerImpl(this, this.pointer2, this.pointer4, 1, 3));
        addView(this.pointer1);
        addView(this.pointer2);
        addView(this.midPointer13);
        addView(this.midPointer12);
        addView(this.midPointer34);
        addView(this.midPointer24);
        addView(this.pointer3);
        addView(this.pointer4);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        this.paint.setStrokeWidth(7.0f);
        this.paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.circleFillPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.circleFillPaint.setStrokeWidth(5.0f);
        this.circleFillPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        this.circleFillPaint.setAntiAlias(true);
    }

    public final Map<Integer, Point> getPoints() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Point(this.pointer1.getX(), this.pointer1.getY()));
        arrayList.add(new Point(this.pointer2.getX(), this.pointer2.getY()));
        arrayList.add(new Point(this.pointer3.getX(), this.pointer3.getY()));
        arrayList.add(new Point(this.pointer4.getX(), this.pointer4.getY()));
        return getOrderedPoints(arrayList);
    }

    public final void setPoints(Map<Integer, ? extends Point> pointMap) {
        Intrinsics.checkNotNullParameter(pointMap, "pointMap");
        if (pointMap.size() == 4) {
            setPointsCoordinates(pointMap);
        }
    }

    private final Map<Integer, Point> getOrderedPoints(List<? extends Point> list) {
        int i;
        Point point = new Point();
        int size = list.size();
        for (Point point2 : list) {
            double d = size;
            point.x += point2.x / d;
            point.y += point2.y / d;
        }
        HashMap hashMap = new HashMap();
        for (Point point3 : list) {
            if (point3.x < point.x && point3.y < point.y) {
                i = 0;
            } else if (point3.x > point.x && point3.y < point.y) {
                i = 1;
            } else if (point3.x >= point.x || point3.y <= point.y) {
                i = (point3.x <= point.x || point3.y <= point.y) ? -1 : 3;
            } else {
                i = 2;
            }
            hashMap.put(Integer.valueOf(i), point3);
        }
        return hashMap;
    }

    private final void setPointsCoordinates(Map<Integer, ? extends Point> map) {
        Integer num;
        Integer num2;
        Integer num3;
        Integer num4;
        Integer num5;
        Integer num6;
        Integer num7;
        Integer num8;
        ImageView imageView = this.pointer1;
        Point point = map.get(0);
        Intrinsics.checkNotNull(point);
        imageView.setX(((float) point.x) - (this.circleRadius != null ? num.intValue() / 2 : 0));
        ImageView imageView2 = this.pointer1;
        Point point2 = map.get(0);
        Intrinsics.checkNotNull(point2);
        imageView2.setY(((float) point2.y) - (this.circleRadius != null ? num2.intValue() / 2 : 0));
        ImageView imageView3 = this.pointer2;
        Point point3 = map.get(1);
        Intrinsics.checkNotNull(point3);
        imageView3.setX(((float) point3.x) - (this.circleRadius != null ? num3.intValue() / 2 : 0));
        ImageView imageView4 = this.pointer2;
        Point point4 = map.get(1);
        Intrinsics.checkNotNull(point4);
        imageView4.setY(((float) point4.y) - (this.circleRadius != null ? num4.intValue() / 2 : 0));
        ImageView imageView5 = this.pointer3;
        Point point5 = map.get(3);
        Intrinsics.checkNotNull(point5);
        imageView5.setX(((float) point5.x) - (this.circleRadius != null ? num5.intValue() / 2 : 0));
        ImageView imageView6 = this.pointer3;
        Point point6 = map.get(3);
        Intrinsics.checkNotNull(point6);
        imageView6.setY(((float) point6.y) - (this.circleRadius != null ? num6.intValue() / 2 : 0));
        ImageView imageView7 = this.pointer4;
        Point point7 = map.get(2);
        Intrinsics.checkNotNull(point7);
        imageView7.setX(((float) point7.x) - (this.circleRadius != null ? num7.intValue() / 2 : 0));
        ImageView imageView8 = this.pointer4;
        Point point8 = map.get(2);
        Intrinsics.checkNotNull(point8);
        imageView8.setY(((float) point8.y) - (this.circleRadius != null ? num8.intValue() / 2 : 0));
        float f = 2;
        this.midPointer13.setX(this.pointer3.getX() - ((this.pointer3.getX() - this.pointer1.getX()) / f));
        this.midPointer13.setY(this.pointer3.getY() - ((this.pointer3.getY() - this.pointer1.getY()) / f));
        this.midPointer24.setX(this.pointer4.getX() - ((this.pointer4.getX() - this.pointer2.getX()) / f));
        this.midPointer24.setY(this.pointer4.getY() - ((this.pointer4.getY() - this.pointer2.getY()) / f));
        this.midPointer34.setX(this.pointer4.getX() - ((this.pointer4.getX() - this.pointer3.getX()) / f));
        this.midPointer34.setY(this.pointer4.getY() - ((this.pointer4.getY() - this.pointer3.getY()) / f));
        this.midPointer12.setX(this.pointer2.getX() - ((this.pointer2.getX() - this.pointer1.getX()) / f));
        this.midPointer12.setY(this.pointer2.getY() - ((this.pointer2.getY() - this.pointer1.getY()) / f));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.dispatchDraw(canvas);
        canvas.drawLine(this.pointer1.getX() + (this.pointer1.getWidth() / 2), this.pointer1.getY() + (this.pointer1.getHeight() / 2), this.pointer3.getX() + (this.pointer3.getWidth() / 2), this.pointer3.getY() + (this.pointer3.getHeight() / 2), this.paint);
        canvas.drawLine(this.pointer1.getX() + (this.pointer1.getWidth() / 2), this.pointer1.getY() + (this.pointer1.getHeight() / 2), this.pointer2.getX() + (this.pointer2.getWidth() / 2), this.pointer2.getY() + (this.pointer2.getHeight() / 2), this.paint);
        canvas.drawLine(this.pointer2.getX() + (this.pointer2.getWidth() / 2), this.pointer2.getY() + (this.pointer2.getHeight() / 2), this.pointer4.getX() + (this.pointer4.getWidth() / 2), this.pointer4.getY() + (this.pointer4.getHeight() / 2), this.paint);
        canvas.drawLine(this.pointer3.getX() + (this.pointer3.getWidth() / 2), this.pointer3.getY() + (this.pointer3.getHeight() / 2), this.pointer4.getX() + (this.pointer4.getWidth() / 2), this.pointer4.getY() + (this.pointer4.getHeight() / 2), this.paint);
        float f = 2;
        this.midPointer13.setX(this.pointer3.getX() - ((this.pointer3.getX() - this.pointer1.getX()) / f));
        this.midPointer13.setY(this.pointer3.getY() - ((this.pointer3.getY() - this.pointer1.getY()) / f));
        this.midPointer24.setX(this.pointer4.getX() - ((this.pointer4.getX() - this.pointer2.getX()) / f));
        this.midPointer24.setY(this.pointer4.getY() - ((this.pointer4.getY() - this.pointer2.getY()) / f));
        this.midPointer34.setX(this.pointer4.getX() - ((this.pointer4.getX() - this.pointer3.getX()) / f));
        this.midPointer34.setY(this.pointer4.getY() - ((this.pointer4.getY() - this.pointer3.getY()) / f));
        this.midPointer12.setX(this.pointer2.getX() - ((this.pointer2.getX() - this.pointer1.getX()) / f));
        this.midPointer12.setY(this.pointer2.getY() - ((this.pointer2.getY() - this.pointer1.getY()) / f));
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        float dp2px = CameraUtilsKt.dp2px(context, 11.0f);
        canvas.drawCircle(this.pointer1.getX() + (this.pointer1.getWidth() / 2), this.pointer1.getY() + (this.pointer1.getHeight() / 2), dp2px, this.circleFillPaint);
        canvas.drawCircle(this.pointer2.getX() + (this.pointer2.getWidth() / 2), this.pointer2.getY() + (this.pointer2.getHeight() / 2), dp2px, this.circleFillPaint);
        canvas.drawCircle(this.pointer3.getX() + (this.pointer3.getWidth() / 2), this.pointer3.getY() + (this.pointer3.getHeight() / 2), dp2px, this.circleFillPaint);
        canvas.drawCircle(this.pointer4.getX() + (this.pointer4.getWidth() / 2), this.pointer4.getY() + (this.pointer4.getHeight() / 2), dp2px, this.circleFillPaint);
        canvas.drawCircle(this.midPointer13.getX() + (this.midPointer13.getWidth() / 2), this.midPointer13.getY() + (this.midPointer13.getHeight() / 2), dp2px, this.circleFillPaint);
        canvas.drawCircle(this.midPointer24.getX() + (this.midPointer24.getWidth() / 2), this.midPointer24.getY() + (this.midPointer24.getHeight() / 2), dp2px, this.circleFillPaint);
        canvas.drawCircle(this.midPointer34.getX() + (this.midPointer34.getWidth() / 2), this.midPointer34.getY() + (this.midPointer34.getHeight() / 2), dp2px, this.circleFillPaint);
        canvas.drawCircle(this.midPointer12.getX() + (this.midPointer12.getWidth() / 2), this.midPointer12.getY() + (this.midPointer12.getHeight() / 2), dp2px, this.circleFillPaint);
    }

    private final ImageView getImageView(int i, int i2, int i3) {
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        imageView.setImageResource(R.drawable.circle);
        imageView.setX(i);
        imageView.setY(i2);
        imageView.setOnTouchListener(new TouchListenerImpl(i3));
        return imageView;
    }

    private final ImageView getImageViewTransparent(int i, int i2) {
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        imageView.setImageResource(R.drawable.circle);
        imageView.setX(i);
        imageView.setY(i2);
        return imageView;
    }

    /* compiled from: PolygonView.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\f\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u0010¨\u0006 "}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/PolygonView$MidPointTouchListenerImpl;", "Landroid/view/View$OnTouchListener;", "mainPointer1", "Landroid/widget/ImageView;", "mainPointer2", "pointIndex1", "", "pointIndex2", "(Lorg/informatika/sirekap/ui/autocapture/PolygonView;Landroid/widget/ImageView;Landroid/widget/ImageView;II)V", "downPoint", "Landroid/graphics/PointF;", "getDownPoint", "()Landroid/graphics/PointF;", "latestPoint", "getLatestPoint", "setLatestPoint", "(Landroid/graphics/PointF;)V", "latestPoint1", "getLatestPoint1", "setLatestPoint1", "latestPoint2", "getLatestPoint2", "setLatestPoint2", "startPoint", "getStartPoint", "setStartPoint", "onTouch", "", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    private final class MidPointTouchListenerImpl implements View.OnTouchListener {
        private final PointF downPoint;
        private PointF latestPoint;
        private PointF latestPoint1;
        private PointF latestPoint2;
        private final ImageView mainPointer1;
        private final ImageView mainPointer2;
        private final int pointIndex1;
        private final int pointIndex2;
        private PointF startPoint;
        final /* synthetic */ PolygonView this$0;

        public MidPointTouchListenerImpl(PolygonView polygonView, ImageView mainPointer1, ImageView mainPointer2, int i, int i2) {
            Intrinsics.checkNotNullParameter(mainPointer1, "mainPointer1");
            Intrinsics.checkNotNullParameter(mainPointer2, "mainPointer2");
            this.this$0 = polygonView;
            this.mainPointer1 = mainPointer1;
            this.mainPointer2 = mainPointer2;
            this.pointIndex1 = i;
            this.pointIndex2 = i2;
            this.downPoint = new PointF();
            this.startPoint = new PointF();
            this.latestPoint = new PointF();
            this.latestPoint1 = new PointF();
            this.latestPoint2 = new PointF();
        }

        public final PointF getDownPoint() {
            return this.downPoint;
        }

        public final PointF getStartPoint() {
            return this.startPoint;
        }

        public final void setStartPoint(PointF pointF) {
            Intrinsics.checkNotNullParameter(pointF, "<set-?>");
            this.startPoint = pointF;
        }

        public final PointF getLatestPoint() {
            return this.latestPoint;
        }

        public final void setLatestPoint(PointF pointF) {
            Intrinsics.checkNotNullParameter(pointF, "<set-?>");
            this.latestPoint = pointF;
        }

        public final PointF getLatestPoint1() {
            return this.latestPoint1;
        }

        public final void setLatestPoint1(PointF pointF) {
            Intrinsics.checkNotNullParameter(pointF, "<set-?>");
            this.latestPoint1 = pointF;
        }

        public final PointF getLatestPoint2() {
            return this.latestPoint2;
        }

        public final void setLatestPoint2(PointF pointF) {
            Intrinsics.checkNotNullParameter(pointF, "<set-?>");
            this.latestPoint2 = pointF;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, MotionEvent event) {
            int color;
            Point point;
            Integer num;
            Integer num2;
            Integer num3;
            Integer num4;
            Point point2;
            Integer num5;
            Integer num6;
            Integer num7;
            Integer num8;
            Point point3;
            Integer num9;
            Integer num10;
            Integer num11;
            Integer num12;
            Point point4;
            Point point5;
            Integer num13;
            Integer num14;
            Integer num15;
            int i;
            Integer num16;
            Integer num17;
            Integer num18;
            Integer num19;
            Integer num20;
            Integer num21;
            Integer num22;
            Integer num23;
            Intrinsics.checkNotNullParameter(v, "v");
            Intrinsics.checkNotNullParameter(event, "event");
            int action = event.getAction();
            if (action == 0) {
                this.downPoint.x = event.getX();
                this.downPoint.y = event.getY();
                this.startPoint = new PointF(v.getX(), v.getY());
                this.latestPoint = new PointF(v.getX(), v.getY());
                this.latestPoint1 = new PointF(this.mainPointer1.getX(), this.mainPointer1.getY());
                this.latestPoint2 = new PointF(this.mainPointer2.getX(), this.mainPointer2.getY());
            } else if (action == 1) {
                PolygonView polygonView = this.this$0;
                if (polygonView.isValidShape(polygonView.getPoints()) && this.this$0.isValidPointer1() && this.this$0.isValidPointer2() && this.this$0.isValidPointer3() && this.this$0.isValidPointer4()) {
                    color = ContextCompat.getColor(this.this$0.getContext(), R.color.colorPrimary);
                    this.latestPoint.x = v.getX();
                    this.latestPoint.y = v.getY();
                    this.latestPoint1.x = this.mainPointer1.getX();
                    this.latestPoint1.y = this.mainPointer1.getY();
                    this.latestPoint2.x = this.mainPointer2.getX();
                    this.latestPoint2.y = this.mainPointer2.getY();
                    AutoCaptureFragment.Companion companion = AutoCaptureFragment.Companion;
                    if (this.pointIndex1 == 0) {
                        point = new Point(this.latestPoint1.x + (this.this$0.circleRadius != null ? num22.intValue() / 2 : 0), this.latestPoint1.y + (this.this$0.circleRadius != null ? num23.intValue() / 2 : 0));
                    } else {
                        if (this.pointIndex2 == 0) {
                            new Point(this.latestPoint2.x + (this.this$0.circleRadius != null ? num3.intValue() / 2 : 0), this.latestPoint2.y + (this.this$0.circleRadius != null ? num4.intValue() / 2 : 0));
                        }
                        Point point6 = this.this$0.getPoints().get(0);
                        Double valueOf = point6 != null ? Double.valueOf(point6.x) : null;
                        Intrinsics.checkNotNull(valueOf);
                        double doubleValue = valueOf.doubleValue() + (this.this$0.circleRadius != null ? num.intValue() / 2 : 0);
                        Point point7 = this.this$0.getPoints().get(0);
                        Double valueOf2 = point7 != null ? Double.valueOf(point7.y) : null;
                        Intrinsics.checkNotNull(valueOf2);
                        point = new Point(doubleValue, valueOf2.doubleValue() + (this.this$0.circleRadius != null ? num2.intValue() / 2 : 0));
                    }
                    if (this.pointIndex1 == 1) {
                        point2 = new Point(this.latestPoint1.x + (this.this$0.circleRadius != null ? num20.intValue() / 2 : 0), this.latestPoint1.y + (this.this$0.circleRadius != null ? num21.intValue() / 2 : 0));
                    } else {
                        if (this.pointIndex2 == 1) {
                            new Point(this.latestPoint2.x + (this.this$0.circleRadius != null ? num7.intValue() / 2 : 0), this.latestPoint2.y + (this.this$0.circleRadius != null ? num8.intValue() / 2 : 0));
                        }
                        Point point8 = this.this$0.getPoints().get(1);
                        Double valueOf3 = point8 != null ? Double.valueOf(point8.x) : null;
                        Intrinsics.checkNotNull(valueOf3);
                        double doubleValue2 = valueOf3.doubleValue() + (this.this$0.circleRadius != null ? num5.intValue() / 2 : 0);
                        Point point9 = this.this$0.getPoints().get(1);
                        Double valueOf4 = point9 != null ? Double.valueOf(point9.y) : null;
                        Intrinsics.checkNotNull(valueOf4);
                        point2 = new Point(doubleValue2, valueOf4.doubleValue() + (this.this$0.circleRadius != null ? num6.intValue() / 2 : 0));
                    }
                    if (this.pointIndex1 == 2) {
                        point3 = new Point(this.latestPoint1.x + (this.this$0.circleRadius != null ? num18.intValue() / 2 : 0), this.latestPoint1.y + (this.this$0.circleRadius != null ? num19.intValue() / 2 : 0));
                    } else {
                        if (this.pointIndex2 == 2) {
                            new Point(this.latestPoint2.x + (this.this$0.circleRadius != null ? num11.intValue() / 2 : 0), this.latestPoint2.y + (this.this$0.circleRadius != null ? num12.intValue() / 2 : 0));
                        }
                        Point point10 = this.this$0.getPoints().get(2);
                        Double valueOf5 = point10 != null ? Double.valueOf(point10.x) : null;
                        Intrinsics.checkNotNull(valueOf5);
                        double doubleValue3 = valueOf5.doubleValue() + (this.this$0.circleRadius != null ? num9.intValue() / 2 : 0);
                        Point point11 = this.this$0.getPoints().get(2);
                        Double valueOf6 = point11 != null ? Double.valueOf(point11.y) : null;
                        Intrinsics.checkNotNull(valueOf6);
                        point3 = new Point(doubleValue3, valueOf6.doubleValue() + (this.this$0.circleRadius != null ? num10.intValue() / 2 : 0));
                    }
                    if (this.pointIndex1 == 3) {
                        point5 = new Point(this.latestPoint1.x + (this.this$0.circleRadius != null ? num16.intValue() / 2 : 0), this.latestPoint1.y + (this.this$0.circleRadius != null ? num17.intValue() / 2 : 0));
                    } else {
                        if (this.pointIndex2 == 3) {
                            double intValue = this.latestPoint2.x + (this.this$0.circleRadius != null ? num15.intValue() / 2 : 0);
                            double d = this.latestPoint2.y;
                            Integer num24 = this.this$0.circleRadius;
                            if (num24 != null) {
                                i = num24.intValue() / 2;
                                point4 = point;
                            } else {
                                point4 = point;
                                i = 0;
                            }
                            new Point(intValue, d + i);
                        } else {
                            point4 = point;
                        }
                        Point point12 = this.this$0.getPoints().get(3);
                        Double valueOf7 = point12 != null ? Double.valueOf(point12.x) : null;
                        Intrinsics.checkNotNull(valueOf7);
                        double doubleValue4 = valueOf7.doubleValue() + (this.this$0.circleRadius != null ? num13.intValue() / 2 : 0);
                        Point point13 = this.this$0.getPoints().get(3);
                        Double valueOf8 = point13 != null ? Double.valueOf(point13.y) : null;
                        Intrinsics.checkNotNull(valueOf8);
                        point5 = new Point(doubleValue4, valueOf8.doubleValue() + (this.this$0.circleRadius != null ? num14.intValue() / 2 : 0));
                        point = point4;
                    }
                    companion.setManualCropPoints(new PolygonPoints(point, point2, point3, point5));
                } else {
                    color = ContextCompat.getColor(this.this$0.getContext(), R.color.colorPrimary);
                    v.setX(this.latestPoint.x);
                    v.setY(this.latestPoint.y);
                    this.mainPointer1.setX(this.latestPoint1.x);
                    this.mainPointer1.setY(this.latestPoint1.y);
                    this.mainPointer2.setX(this.latestPoint2.x);
                    this.mainPointer2.setY(this.latestPoint2.y);
                }
                this.this$0.paint.setColor(color);
            } else if (action == 2) {
                PointF pointF = new PointF(event.getX() - this.downPoint.x, event.getY() - this.downPoint.y);
                if (Math.abs(this.mainPointer1.getX() - this.mainPointer2.getX()) > Math.abs(this.mainPointer1.getY() - this.mainPointer2.getY())) {
                    if (this.mainPointer2.getY() + pointF.y + v.getHeight() < this.this$0.polygonView.getHeight() && this.mainPointer2.getY() + pointF.y > 0.0f) {
                        v.setX(this.startPoint.y + pointF.y);
                        this.startPoint = new PointF(v.getX(), v.getY());
                        ImageView imageView = this.mainPointer2;
                        imageView.setY(imageView.getY() + pointF.y);
                    }
                    if (this.mainPointer1.getY() + pointF.y + v.getHeight() < this.this$0.polygonView.getHeight() && this.mainPointer1.getY() + pointF.y > 0.0f) {
                        v.setX(this.startPoint.y + pointF.y);
                        this.startPoint = new PointF(v.getX(), v.getY());
                        ImageView imageView2 = this.mainPointer1;
                        imageView2.setY(imageView2.getY() + pointF.y);
                    }
                } else {
                    if (this.mainPointer2.getX() + pointF.x + v.getWidth() < this.this$0.polygonView.getWidth() && this.mainPointer2.getX() + pointF.x > 0.0f) {
                        v.setX(this.startPoint.x + pointF.x);
                        this.startPoint = new PointF(v.getX(), v.getY());
                        ImageView imageView3 = this.mainPointer2;
                        imageView3.setX(imageView3.getX() + pointF.x);
                    }
                    if (this.mainPointer1.getX() + pointF.x + v.getWidth() < this.this$0.polygonView.getWidth() && this.mainPointer1.getX() + pointF.x > 0.0f) {
                        v.setX(this.startPoint.x + pointF.x);
                        this.startPoint = new PointF(v.getX(), v.getY());
                        ImageView imageView4 = this.mainPointer1;
                        imageView4.setX(imageView4.getX() + pointF.x);
                    }
                }
            }
            this.this$0.polygonView.invalidate();
            return true;
        }
    }

    public final boolean isValidShape(Map<Integer, ? extends Point> map) {
        return map.size() == 4;
    }

    public final boolean isValidPointer4() {
        return this.pointer4.getY() > this.pointer2.getY() && this.pointer4.getX() > this.pointer3.getX();
    }

    public final boolean isValidPointer3() {
        return this.pointer3.getY() > this.pointer1.getY() && this.pointer3.getX() < this.pointer4.getX();
    }

    public final boolean isValidPointer2() {
        return this.pointer2.getY() < this.pointer4.getY() && this.pointer2.getX() > this.pointer1.getX();
    }

    public final boolean isValidPointer1() {
        return this.pointer1.getY() < this.pointer3.getY() && this.pointer1.getX() < this.pointer2.getX();
    }

    /* compiled from: PolygonView.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\b\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/PolygonView$TouchListenerImpl;", "Landroid/view/View$OnTouchListener;", "pointIndex", "", "(Lorg/informatika/sirekap/ui/autocapture/PolygonView;I)V", "downPoint", "Landroid/graphics/PointF;", "getDownPoint", "()Landroid/graphics/PointF;", "latestPoint", "getLatestPoint", "setLatestPoint", "(Landroid/graphics/PointF;)V", "startPoint", "getStartPoint", "setStartPoint", "onTouch", "", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public final class TouchListenerImpl implements View.OnTouchListener {
        private final int pointIndex;
        private final PointF downPoint = new PointF();
        private PointF startPoint = new PointF();
        private PointF latestPoint = new PointF();

        public TouchListenerImpl(int i) {
            PolygonView.this = r1;
            this.pointIndex = i;
        }

        public final PointF getDownPoint() {
            return this.downPoint;
        }

        public final PointF getStartPoint() {
            return this.startPoint;
        }

        public final void setStartPoint(PointF pointF) {
            Intrinsics.checkNotNullParameter(pointF, "<set-?>");
            this.startPoint = pointF;
        }

        public final PointF getLatestPoint() {
            return this.latestPoint;
        }

        public final void setLatestPoint(PointF pointF) {
            Intrinsics.checkNotNullParameter(pointF, "<set-?>");
            this.latestPoint = pointF;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, MotionEvent event) {
            int color;
            Point point;
            Integer num;
            Integer num2;
            Point point2;
            Integer num3;
            Integer num4;
            Point point3;
            Integer num5;
            Integer num6;
            Point point4;
            Integer num7;
            Integer num8;
            Integer num9;
            Integer num10;
            Integer num11;
            Integer num12;
            Integer num13;
            Integer num14;
            Integer num15;
            Integer num16;
            Intrinsics.checkNotNullParameter(v, "v");
            Intrinsics.checkNotNullParameter(event, "event");
            int action = event.getAction();
            if (action == 0) {
                this.downPoint.x = event.getX();
                this.downPoint.y = event.getY();
                this.startPoint = new PointF(v.getX(), v.getY());
                this.latestPoint = new PointF(v.getX(), v.getY());
            } else if (action == 1) {
                PolygonView polygonView = PolygonView.this;
                if (polygonView.isValidShape(polygonView.getPoints()) && PolygonView.this.isValidPointer4() && PolygonView.this.isValidPointer3() && PolygonView.this.isValidPointer2() && PolygonView.this.isValidPointer1()) {
                    color = ContextCompat.getColor(PolygonView.this.getContext(), R.color.colorPrimary);
                    this.latestPoint.x = v.getX();
                    this.latestPoint.y = v.getY();
                    AutoCaptureFragment.Companion companion = AutoCaptureFragment.Companion;
                    if (this.pointIndex != 0) {
                        Point point5 = PolygonView.this.getPoints().get(0);
                        Double valueOf = point5 != null ? Double.valueOf(point5.x) : null;
                        Intrinsics.checkNotNull(valueOf);
                        double doubleValue = valueOf.doubleValue() + (PolygonView.this.circleRadius != null ? num.intValue() / 2 : 0);
                        Point point6 = PolygonView.this.getPoints().get(0);
                        Double valueOf2 = point6 != null ? Double.valueOf(point6.y) : null;
                        Intrinsics.checkNotNull(valueOf2);
                        point = new Point(doubleValue, valueOf2.doubleValue() + (PolygonView.this.circleRadius != null ? num2.intValue() / 2 : 0));
                    } else {
                        point = new Point(this.latestPoint.x + (PolygonView.this.circleRadius != null ? num15.intValue() / 2 : 0), this.latestPoint.y + (PolygonView.this.circleRadius != null ? num16.intValue() / 2 : 0));
                    }
                    if (this.pointIndex != 1) {
                        Point point7 = PolygonView.this.getPoints().get(1);
                        Double valueOf3 = point7 != null ? Double.valueOf(point7.x) : null;
                        Intrinsics.checkNotNull(valueOf3);
                        double doubleValue2 = valueOf3.doubleValue() + (PolygonView.this.circleRadius != null ? num3.intValue() / 2 : 0);
                        Point point8 = PolygonView.this.getPoints().get(1);
                        Double valueOf4 = point8 != null ? Double.valueOf(point8.y) : null;
                        Intrinsics.checkNotNull(valueOf4);
                        point2 = new Point(doubleValue2, valueOf4.doubleValue() + (PolygonView.this.circleRadius != null ? num4.intValue() / 2 : 0));
                    } else {
                        point2 = new Point(this.latestPoint.x + (PolygonView.this.circleRadius != null ? num13.intValue() / 2 : 0), this.latestPoint.y + (PolygonView.this.circleRadius != null ? num14.intValue() / 2 : 0));
                    }
                    if (this.pointIndex != 2) {
                        Point point9 = PolygonView.this.getPoints().get(2);
                        Double valueOf5 = point9 != null ? Double.valueOf(point9.x) : null;
                        Intrinsics.checkNotNull(valueOf5);
                        double doubleValue3 = valueOf5.doubleValue() + (PolygonView.this.circleRadius != null ? num5.intValue() / 2 : 0);
                        Point point10 = PolygonView.this.getPoints().get(2);
                        Double valueOf6 = point10 != null ? Double.valueOf(point10.y) : null;
                        Intrinsics.checkNotNull(valueOf6);
                        point3 = new Point(doubleValue3, valueOf6.doubleValue() + (PolygonView.this.circleRadius != null ? num6.intValue() / 2 : 0));
                    } else {
                        point3 = new Point(this.latestPoint.x + (PolygonView.this.circleRadius != null ? num11.intValue() / 2 : 0), this.latestPoint.y + (PolygonView.this.circleRadius != null ? num12.intValue() / 2 : 0));
                    }
                    if (this.pointIndex != 3) {
                        Point point11 = PolygonView.this.getPoints().get(3);
                        Double valueOf7 = point11 != null ? Double.valueOf(point11.x) : null;
                        Intrinsics.checkNotNull(valueOf7);
                        double doubleValue4 = valueOf7.doubleValue() + (PolygonView.this.circleRadius != null ? num7.intValue() / 2 : 0);
                        Point point12 = PolygonView.this.getPoints().get(3);
                        Double valueOf8 = point12 != null ? Double.valueOf(point12.y) : null;
                        Intrinsics.checkNotNull(valueOf8);
                        point4 = new Point(doubleValue4, valueOf8.doubleValue() + (PolygonView.this.circleRadius != null ? num8.intValue() / 2 : 0));
                    } else {
                        point4 = new Point(this.latestPoint.x + (PolygonView.this.circleRadius != null ? num9.intValue() / 2 : 0), this.latestPoint.y + (PolygonView.this.circleRadius != null ? num10.intValue() / 2 : 0));
                    }
                    companion.setManualCropPoints(new PolygonPoints(point, point2, point3, point4));
                } else {
                    color = ContextCompat.getColor(PolygonView.this.getContext(), R.color.colorPrimary);
                    v.setX(this.latestPoint.x);
                    v.setY(this.latestPoint.y);
                }
                PolygonView.this.paint.setColor(color);
            } else if (action == 2) {
                PointF pointF = new PointF(event.getX() - this.downPoint.x, event.getY() - this.downPoint.y);
                if (this.startPoint.x + pointF.x + v.getWidth() < PolygonView.this.polygonView.getWidth() && this.startPoint.y + pointF.y + v.getHeight() < PolygonView.this.polygonView.getHeight() && this.startPoint.x + pointF.x > 0.0f && this.startPoint.y + pointF.y > 0.0f) {
                    v.setX(this.startPoint.x + pointF.x);
                    v.setY(this.startPoint.y + pointF.y);
                    this.startPoint = new PointF(v.getX(), v.getY());
                }
            }
            PolygonView.this.polygonView.invalidate();
            return true;
        }
    }
}
