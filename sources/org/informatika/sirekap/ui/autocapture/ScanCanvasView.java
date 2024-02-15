package org.informatika.sirekap.ui.autocapture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScanCanvasView.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014J\u0006\u0010\u0016\u001a\u00020\u0010J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0014R&\u0010\u000b\u001a\u001a\u0012\b\u0012\u00060\rR\u00020\u00000\fj\f\u0012\b\u0012\u00060\rR\u00020\u0000`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/ScanCanvasView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "shapes", "Ljava/util/ArrayList;", "Lorg/informatika/sirekap/ui/autocapture/ScanCanvasView$ScanShape;", "Lkotlin/collections/ArrayList;", "addShape", "", "shape", "Landroid/graphics/drawable/shapes/Shape;", "paint", "Landroid/graphics/Paint;", "border", "clear", "onDraw", "canvas", "Landroid/graphics/Canvas;", "ScanShape", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ScanCanvasView extends View {
    private final ArrayList<ScanShape> shapes;

    public ScanCanvasView(Context context) {
        super(context);
        this.shapes = new ArrayList<>();
    }

    public ScanCanvasView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.shapes = new ArrayList<>();
    }

    public ScanCanvasView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.shapes = new ArrayList<>();
    }

    /* compiled from: ScanCanvasView.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/ScanCanvasView$ScanShape;", "", "shape", "Landroid/graphics/drawable/shapes/Shape;", "paint", "Landroid/graphics/Paint;", "border", "(Lorg/informatika/sirekap/ui/autocapture/ScanCanvasView;Landroid/graphics/drawable/shapes/Shape;Landroid/graphics/Paint;Landroid/graphics/Paint;)V", "getShape", "()Landroid/graphics/drawable/shapes/Shape;", "draw", "", "canvas", "Landroid/graphics/Canvas;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public final class ScanShape {
        private final Paint border;
        private final Paint paint;
        private final Shape shape;
        final /* synthetic */ ScanCanvasView this$0;

        public ScanShape(ScanCanvasView scanCanvasView, Shape shape, Paint paint, Paint paint2) {
            Intrinsics.checkNotNullParameter(shape, "shape");
            Intrinsics.checkNotNullParameter(paint, "paint");
            this.this$0 = scanCanvasView;
            this.shape = shape;
            this.paint = paint;
            this.border = paint2;
            if (paint2 == null) {
                return;
            }
            paint2.setStyle(Paint.Style.STROKE);
        }

        public final Shape getShape() {
            return this.shape;
        }

        public final void draw(Canvas canvas) {
            this.shape.draw(canvas, this.paint);
            Paint paint = this.border;
            if (paint != null) {
                this.shape.draw(canvas, paint);
            }
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int width = (getWidth() - paddingLeft) - paddingRight;
        int height = (getHeight() - paddingTop) - paddingBottom;
        for (ScanShape scanShape : this.shapes) {
            scanShape.getShape().resize(width, height);
            scanShape.draw(canvas);
        }
    }

    public final void addShape(Shape shape, Paint paint, Paint border) {
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        Intrinsics.checkNotNullParameter(border, "border");
        this.shapes.add(new ScanShape(this, shape, paint, border));
    }

    public final void clear() {
        this.shapes.clear();
    }
}
