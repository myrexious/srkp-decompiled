package com.tom_roush.pdfbox.rendering;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.function.PDFunction;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType0;
import com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType2;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDTrueTypeFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType0Font;
import com.tom_roush.pdfbox.pdmodel.font.PDType1CFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.pdmodel.font.PDType3Font;
import com.tom_roush.pdfbox.pdmodel.graphics.PDLineDashPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentMembershipDictionary;
import com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDGraphicsState;
import com.tom_roush.pdfbox.pdmodel.graphics.state.RenderingMode;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.AnnotationFilter;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationUnknown;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class PageDrawer extends PDFGraphicsStreamEngine {
    private AnnotationFilter annotationFilter;
    private Canvas canvas;
    private Path.FillType clipWindingRule;
    private PointF currentPoint;
    private final RenderDestination destination;
    private boolean flipTG;
    private final Map<PDFont, Glyph2D> fontGlyph2D;
    private final float imageDownscalingOptimizationThreshold;
    private Path initialClip;
    private Region lastClip;
    private int lastStackSize;
    private Path linePath;
    private int nestedHiddenOCGCount;
    private PDRectangle pageSize;
    private Paint paint;
    private final PDFRenderer renderer;
    private final boolean subsamplingAllowed;
    private List<Path> textClippings;
    private final Deque<TransparencyGroup> transparencyGroupStack;
    private AffineTransform xform;
    private float xformScalingFactorX;
    private float xformScalingFactorY;

    private float clampColor(float f) {
        float f2 = 0.0f;
        if (f >= 0.0f) {
            f2 = 1.0f;
            if (f <= 1.0f) {
                return f;
            }
        }
        return f2;
    }

    public PageDrawer(PageDrawerParameters pageDrawerParameters) throws IOException {
        super(pageDrawerParameters.getPage());
        this.flipTG = false;
        this.clipWindingRule = null;
        this.linePath = new Path();
        this.lastStackSize = 0;
        this.fontGlyph2D = new HashMap();
        this.currentPoint = new PointF();
        this.transparencyGroupStack = new ArrayDeque();
        this.annotationFilter = new AnnotationFilter() { // from class: com.tom_roush.pdfbox.rendering.PageDrawer.1
            @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.AnnotationFilter
            public boolean accept(PDAnnotation pDAnnotation) {
                return true;
            }
        };
        this.renderer = pageDrawerParameters.getRenderer();
        this.subsamplingAllowed = pageDrawerParameters.isSubsamplingAllowed();
        this.destination = pageDrawerParameters.getDestination();
        this.imageDownscalingOptimizationThreshold = pageDrawerParameters.getImageDownscalingOptimizationThreshold();
    }

    public AnnotationFilter getAnnotationFilter() {
        return this.annotationFilter;
    }

    public void setAnnotationFilter(AnnotationFilter annotationFilter) {
        this.annotationFilter = annotationFilter;
    }

    public final PDFRenderer getRenderer() {
        return this.renderer;
    }

    protected final Canvas getCanvas() {
        return this.canvas;
    }

    protected final Path getLinePath() {
        return this.linePath;
    }

    public void setRenderingHints() {
        this.paint.setAntiAlias(true);
    }

    public void drawPage(Paint paint, Canvas canvas, PDRectangle pDRectangle) throws IOException {
        this.paint = paint;
        this.canvas = canvas;
        this.xform = new AffineTransform(this.canvas.getMatrix());
        Matrix matrix = new Matrix(this.xform);
        this.xformScalingFactorX = Math.abs(matrix.getScalingFactorX());
        this.xformScalingFactorY = Math.abs(matrix.getScalingFactorY());
        this.canvas.save();
        this.pageSize = pDRectangle;
        setRenderingHints();
        this.canvas.translate(0.0f, pDRectangle.getHeight());
        this.canvas.scale(1.0f, -1.0f);
        this.canvas.translate(-pDRectangle.getLowerLeftX(), -pDRectangle.getLowerLeftY());
        processPage(getPage());
        for (PDAnnotation pDAnnotation : getPage().getAnnotations(this.annotationFilter)) {
            showAnnotation(pDAnnotation);
        }
        this.canvas.restore();
    }

    private int getColor(PDColor pDColor) throws IOException {
        double alphaConstant = getGraphicsState().getAlphaConstant();
        float[] rgb = pDColor.getColorSpace().toRGB(pDColor.getComponents());
        return Color.argb(Long.valueOf(Math.round(alphaConstant * 255.0d)).intValue(), Math.round(rgb[0] * 255.0f), Math.round(rgb[1] * 255.0f), Math.round(rgb[2] * 255.0f));
    }

    protected final void setClip() {
        Region currentClippingPath = getGraphicsState().getCurrentClippingPath();
        if (currentClippingPath != this.lastClip) {
            int i = this.lastStackSize;
            if (i >= 1) {
                this.canvas.restoreToCount(i);
            }
            this.lastStackSize = this.canvas.save();
            if (!currentClippingPath.isEmpty()) {
                this.canvas.clipPath(currentClippingPath.getBoundaryPath());
            }
            this.lastClip = currentClippingPath;
        }
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void beginText() throws IOException {
        setClip();
        beginTextClip();
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void endText() throws IOException {
        endTextClip();
    }

    private void beginTextClip() {
        this.textClippings = new ArrayList();
    }

    private void endTextClip() {
        PDGraphicsState graphicsState = getGraphicsState();
        if (!graphicsState.getTextState().getRenderingMode().isClip() || this.textClippings.isEmpty()) {
            return;
        }
        Path path = new Path();
        path.setFillType(Path.FillType.WINDING);
        for (Path path2 : this.textClippings) {
            path.addPath(path2);
        }
        graphicsState.intersectClippingPath(path);
        this.textClippings = new ArrayList();
        this.lastClip = null;
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    protected void showFontGlyph(Matrix matrix, PDFont pDFont, int i, Vector vector) throws IOException {
        AffineTransform createAffineTransform = matrix.createAffineTransform();
        createAffineTransform.concatenate(pDFont.getFontMatrix().createAffineTransform());
        try {
            drawGlyph2D(createGlyph2D(pDFont), pDFont, i, vector, createAffineTransform);
        } catch (IOException e) {
            Log.e("PdfBox-Android", "Could not draw glyph for code " + i + " at position (" + createAffineTransform.getTranslateX() + "," + createAffineTransform.getTranslateY() + ")", e);
        }
    }

    private void drawGlyph2D(Glyph2D glyph2D, PDFont pDFont, int i, Vector vector, AffineTransform affineTransform) throws IOException {
        RenderingMode renderingMode = getGraphicsState().getTextState().getRenderingMode();
        Path pathForCharacterCode = glyph2D.getPathForCharacterCode(i);
        if (pathForCharacterCode != null) {
            if (!pDFont.isEmbedded() && !pDFont.isVertical() && !pDFont.isStandard14() && pDFont.hasExplicitWidth(i)) {
                float widthFromFont = pDFont.getWidthFromFont(i);
                if (widthFromFont > 0.0f && Math.abs(widthFromFont - (vector.getX() * 1000.0f)) > 1.0E-4d) {
                    affineTransform.scale((vector.getX() * 1000.0f) / widthFromFont, 1.0d);
                }
            }
            pathForCharacterCode.transform(affineTransform.toMatrix());
            if (isContentRendered()) {
                if (renderingMode.isFill()) {
                    this.paint.setColor(getNonStrokingColor());
                    setClip();
                    this.paint.setStyle(Paint.Style.FILL);
                    this.canvas.drawPath(pathForCharacterCode, this.paint);
                }
                if (renderingMode.isStroke()) {
                    this.paint.setColor(getStrokingColor());
                    setStroke();
                    setClip();
                    this.paint.setStyle(Paint.Style.STROKE);
                    this.canvas.drawPath(pathForCharacterCode, this.paint);
                }
            }
            renderingMode.isClip();
        }
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void showType3Glyph(Matrix matrix, PDType3Font pDType3Font, int i, Vector vector) throws IOException {
        if (RenderingMode.NEITHER.equals(getGraphicsState().getTextState().getRenderingMode())) {
            return;
        }
        super.showType3Glyph(matrix, pDType3Font, i, vector);
    }

    private Glyph2D createGlyph2D(PDFont pDFont) throws IOException {
        Glyph2D glyph2D;
        Glyph2D glyph2D2 = this.fontGlyph2D.get(pDFont);
        if (glyph2D2 != null) {
            return glyph2D2;
        }
        if (pDFont instanceof PDTrueTypeFont) {
            glyph2D = new TTFGlyph2D((PDTrueTypeFont) pDFont);
        } else if (pDFont instanceof PDType1Font) {
            glyph2D = new Type1Glyph2D((PDType1Font) pDFont);
        } else if (pDFont instanceof PDType1CFont) {
            glyph2D = new Type1Glyph2D((PDType1CFont) pDFont);
        } else if (pDFont instanceof PDType0Font) {
            PDType0Font pDType0Font = (PDType0Font) pDFont;
            if (pDType0Font.getDescendantFont() instanceof PDCIDFontType2) {
                glyph2D2 = new TTFGlyph2D(pDType0Font);
            } else if (pDType0Font.getDescendantFont() instanceof PDCIDFontType0) {
                glyph2D2 = new CIDType0Glyph2D((PDCIDFontType0) pDType0Font.getDescendantFont());
            }
            glyph2D = glyph2D2;
        } else {
            throw new IllegalStateException("Bad font type: " + pDFont.getClass().getSimpleName());
        }
        if (glyph2D != null) {
            this.fontGlyph2D.put(pDFont, glyph2D);
        }
        if (glyph2D != null) {
            return glyph2D;
        }
        throw new UnsupportedOperationException("No font for " + pDFont.getName());
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void appendRectangle(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4) {
        this.linePath.moveTo(pointF.x, pointF.y);
        this.linePath.lineTo(pointF2.x, pointF2.y);
        this.linePath.lineTo(pointF3.x, pointF3.y);
        this.linePath.lineTo(pointF4.x, pointF4.y);
        this.linePath.close();
    }

    private int getStrokingColor() throws IOException {
        return getColor(getGraphicsState().getStrokingColor());
    }

    protected final int getNonStrokingColor() throws IOException {
        return getColor(getGraphicsState().getNonStrokingColor());
    }

    private void setStroke() {
        PDGraphicsState graphicsState = getGraphicsState();
        float transformWidth = transformWidth(graphicsState.getLineWidth());
        if (transformWidth < 0.25d) {
            transformWidth = 0.25f;
        }
        PDLineDashPattern lineDashPattern = graphicsState.getLineDashPattern();
        if (isAllZeroDash(lineDashPattern.getDashArray())) {
            return;
        }
        float[] dashArray = getDashArray(lineDashPattern);
        float transformWidth2 = transformWidth(lineDashPattern.getPhase());
        this.paint.setStrokeWidth(transformWidth);
        this.paint.setStrokeCap(graphicsState.getLineCap());
        this.paint.setStrokeJoin(graphicsState.getLineJoin());
        float miterLimit = graphicsState.getMiterLimit();
        if (miterLimit < 1.0f) {
            Log.w("PdfBox-Android", "Miter limit must be >= 1, value " + miterLimit + " is ignored");
            miterLimit = 10.0f;
        }
        this.paint.setStrokeMiter(miterLimit);
        if (dashArray != null) {
            this.paint.setPathEffect(new DashPathEffect(dashArray, transformWidth2));
        }
    }

    private boolean isAllZeroDash(float[] fArr) {
        if (fArr.length > 0) {
            for (float f : fArr) {
                if (f != 0.0f) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private float[] getDashArray(PDLineDashPattern pDLineDashPattern) {
        float[] dashArray = pDLineDashPattern.getDashArray();
        int phase = pDLineDashPattern.getPhase();
        if (dashArray.length != 0) {
            float f = phase;
            if (!Float.isInfinite(f) && !Float.isNaN(f)) {
                for (int i = 0; i < dashArray.length; i++) {
                    if (Float.isInfinite(dashArray[i]) || Float.isNaN(dashArray[i])) {
                        return null;
                    }
                }
                for (int i2 = 0; i2 < dashArray.length; i2++) {
                    float transformWidth = transformWidth(dashArray[i2]);
                    if (this.xformScalingFactorX < 0.5f) {
                        dashArray[i2] = Math.max(transformWidth, 0.2f);
                    } else {
                        dashArray[i2] = Math.max(transformWidth, 0.062f);
                    }
                }
                return dashArray;
            }
        }
        return null;
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void strokePath() throws IOException {
        if (isContentRendered()) {
            setStroke();
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setColor(getStrokingColor());
            setClip();
            this.canvas.drawPath(this.linePath, this.paint);
        }
        this.linePath.reset();
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void fillPath(Path.FillType fillType) throws IOException {
        getGraphicsState();
        this.paint.setColor(getNonStrokingColor());
        setClip();
        this.linePath.setFillType(fillType);
        RectF rectF = new RectF();
        boolean z = true;
        this.linePath.computeBounds(rectF, true);
        if (!isRectangular(this.linePath) || rectF.width() <= 1.0f || rectF.height() <= 1.0f) {
            z = false;
        }
        if (z) {
            this.paint.setAntiAlias(false);
        }
        if (isContentRendered()) {
            this.paint.setStyle(Paint.Style.FILL);
            this.canvas.drawPath(this.linePath, this.paint);
        }
        this.linePath.reset();
        if (z) {
            setRenderingHints();
        }
    }

    private boolean isRectangular(Path path) {
        return path.isRect(new RectF());
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void fillAndStrokePath(Path.FillType fillType) throws IOException {
        Path path = new Path(this.linePath);
        fillPath(fillType);
        this.linePath = path;
        strokePath();
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void clip(Path.FillType fillType) {
        this.clipWindingRule = fillType;
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void moveTo(float f, float f2) {
        this.currentPoint.set(f, f2);
        this.linePath.moveTo(f, f2);
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void lineTo(float f, float f2) {
        this.currentPoint.set(f, f2);
        this.linePath.lineTo(f, f2);
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void curveTo(float f, float f2, float f3, float f4, float f5, float f6) {
        this.currentPoint.set(f5, f6);
        this.linePath.cubicTo(f, f2, f3, f4, f5, f6);
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public PointF getCurrentPoint() {
        return this.currentPoint;
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void closePath() {
        this.linePath.close();
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void endPath() {
        this.linePath.reset();
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void drawImage(PDImage pDImage) throws IOException {
        Bitmap image;
        if (!((pDImage instanceof PDImageXObject) && isHiddenOCG(((PDImageXObject) pDImage).getOptionalContent())) && isContentRendered()) {
            AffineTransform createAffineTransform = getGraphicsState().getCurrentTransformationMatrix().createAffineTransform();
            if (!pDImage.getInterpolate()) {
                if (this.subsamplingAllowed) {
                    image = pDImage.getImage(null, getSubsampling(pDImage, createAffineTransform));
                } else {
                    image = pDImage.getImage();
                }
                Matrix matrix = new Matrix(createAffineTransform);
                if (image.getWidth() >= Math.abs(Math.round(matrix.getScalingFactorX()))) {
                    image.getHeight();
                    Math.abs(Math.round(matrix.getScalingFactorY()));
                }
            }
            setClip();
            if (!pDImage.isStencil()) {
                if (this.subsamplingAllowed) {
                    drawBitmap(pDImage.getImage(null, getSubsampling(pDImage, createAffineTransform)), createAffineTransform);
                } else {
                    drawBitmap(pDImage.getImage(), createAffineTransform);
                }
            }
            if (pDImage.getInterpolate()) {
                return;
            }
            setRenderingHints();
        }
    }

    private int getSubsampling(PDImage pDImage, AffineTransform affineTransform) {
        int floor = (int) Math.floor(Math.sqrt((pDImage.getWidth() * pDImage.getHeight()) / Math.abs(affineTransform.getDeterminant() * this.xform.getDeterminant())));
        if (floor > 8) {
            floor = 8;
        }
        if (floor < 1) {
            floor = 1;
        }
        return (floor > pDImage.getWidth() || floor > pDImage.getHeight()) ? Math.min(pDImage.getWidth(), pDImage.getHeight()) : floor;
    }

    private void drawBitmap(Bitmap bitmap, AffineTransform affineTransform) throws IOException {
        setClip();
        AffineTransform affineTransform2 = new AffineTransform(affineTransform);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        affineTransform2.scale(1.0d / width, (-1.0d) / height);
        affineTransform2.translate(0.0d, -height);
        if (getGraphicsState().getSoftMask() != null) {
            new RectF(0.0f, 0.0f, width, height);
            return;
        }
        COSBase transfer = getGraphicsState().getTransfer();
        if ((transfer instanceof COSArray) || (transfer instanceof COSDictionary)) {
            bitmap = applyTransferFunction(bitmap, transfer);
        }
        this.canvas.drawBitmap(bitmap, affineTransform2.toMatrix(), this.paint);
    }

    private Bitmap applyTransferFunction(Bitmap bitmap, COSBase cOSBase) throws IOException {
        PDFunction create;
        Integer[] numArr;
        PDFunction pDFunction;
        PDFunction pDFunction2;
        Integer[] numArr2;
        Integer[] numArr3;
        int i;
        int i2;
        int i3;
        int i4;
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        int i5 = 0;
        if (cOSBase instanceof COSArray) {
            COSArray cOSArray = (COSArray) cOSBase;
            create = PDFunction.create(cOSArray.getObject(0));
            pDFunction2 = PDFunction.create(cOSArray.getObject(1));
            pDFunction = PDFunction.create(cOSArray.getObject(2));
            numArr = new Integer[256];
            numArr3 = new Integer[256];
            numArr2 = new Integer[256];
        } else {
            create = PDFunction.create(cOSBase);
            numArr = new Integer[256];
            pDFunction = create;
            pDFunction2 = pDFunction;
            numArr2 = numArr;
            numArr3 = numArr2;
        }
        float[] fArr = new float[1];
        for (int i6 = 0; i6 < bitmap.getWidth(); i6++) {
            int i7 = i5;
            while (i7 < bitmap.getHeight()) {
                int pixel = bitmap.getPixel(i6, i7);
                int i8 = (pixel >> 16) & 255;
                int i9 = (pixel >> 8) & 255;
                int i10 = pixel & 255;
                Integer num = numArr[i8];
                if (num != null) {
                    i = num.intValue();
                } else {
                    fArr[0] = (i8 & 255) / 255.0f;
                    int i11 = (int) (create.eval(fArr)[0] * 255.0f);
                    numArr[i8] = Integer.valueOf(i11);
                    i = i11;
                }
                Integer num2 = numArr3[i9];
                if (num2 != null) {
                    i2 = num2.intValue();
                } else {
                    fArr[0] = (i9 & 255) / 255.0f;
                    i2 = (int) (pDFunction2.eval(fArr)[0] * 255.0f);
                    numArr3[i9] = Integer.valueOf(i2);
                }
                Integer num3 = numArr2[i10];
                if (num3 != null) {
                    i4 = num3.intValue();
                    i3 = 0;
                } else {
                    i3 = 0;
                    fArr[0] = (i10 & 255) / 255.0f;
                    i4 = (int) (pDFunction.eval(fArr)[0] * 255.0f);
                    numArr2[i10] = Integer.valueOf(i4);
                }
                createBitmap.setPixel(i6, i7, (i2 << 8) | (pixel & ViewCompat.MEASURED_STATE_MASK) | (i << 16) | i4);
                i7++;
                i5 = i3;
            }
        }
        return createBitmap;
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFGraphicsStreamEngine
    public void shadingFill(COSName cOSName) throws IOException {
        RectF bounds;
        if (isContentRendered()) {
            PDShading shading = getResources().getShading(cOSName);
            if (shading == null) {
                Log.e("PdfBox-Android", "shading " + cOSName + " does not exist in resources dictionary");
                return;
            }
            Matrix currentTransformationMatrix = getGraphicsState().getCurrentTransformationMatrix();
            if (shading.getBBox() == null && (bounds = shading.getBounds(new AffineTransform(), currentTransformationMatrix)) != null) {
                bounds.union((float) Math.floor(bounds.left - 1.0f), (float) Math.floor(bounds.top - 1.0f));
                bounds.union((float) Math.ceil(bounds.right + 1.0f), (float) Math.ceil(bounds.bottom + 1.0f));
            }
        }
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void showAnnotation(PDAnnotation pDAnnotation) throws IOException {
        this.lastClip = null;
        if (pDAnnotation.isNoView() || pDAnnotation.isHidden()) {
            return;
        }
        if ((pDAnnotation.isInvisible() && (pDAnnotation instanceof PDAnnotationUnknown)) || isHiddenOCG(pDAnnotation.getOptionalContent())) {
            return;
        }
        PDAppearanceDictionary appearance = pDAnnotation.getAppearance();
        if (appearance == null || appearance.getNormalAppearance() == null) {
            pDAnnotation.constructAppearances(this.renderer.document);
        }
        if (pDAnnotation.isNoRotate() && getCurrentPage().getRotation() != 0) {
            PDRectangle rectangle = pDAnnotation.getRectangle();
            android.graphics.Matrix matrix = this.canvas.getMatrix();
            this.canvas.rotate(getCurrentPage().getRotation(), rectangle.getLowerLeftX(), rectangle.getUpperRightY());
            super.showAnnotation(pDAnnotation);
            this.canvas.setMatrix(matrix);
            return;
        }
        super.showAnnotation(pDAnnotation);
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void showForm(PDFormXObject pDFormXObject) throws IOException {
        if (!isHiddenOCG(pDFormXObject.getOptionalContent()) && isContentRendered()) {
            Path path = new Path(this.linePath);
            this.linePath = new Path();
            super.showForm(pDFormXObject);
            this.linePath = path;
        }
    }

    public void setStroke(Paint paint, float f, Paint.Cap cap, Paint.Join join, float f2, float[] fArr, float f3) {
        paint.setStrokeWidth(f);
        paint.setStrokeCap(cap);
        paint.setStrokeJoin(join);
        paint.setStrokeMiter(f2);
        if (fArr != null) {
            paint.setPathEffect(new DashPathEffect(fArr, f3));
        }
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void showTransparencyGroup(PDTransparencyGroup pDTransparencyGroup) throws IOException {
        showTransparencyGroupOnCanvas(pDTransparencyGroup, this.canvas);
    }

    protected void showTransparencyGroupOnCanvas(PDTransparencyGroup pDTransparencyGroup, Canvas canvas) throws IOException {
        if (!isHiddenOCG(pDTransparencyGroup.getOptionalContent()) && isContentRendered()) {
            new TransparencyGroup(pDTransparencyGroup, false, getGraphicsState().getCurrentTransformationMatrix(), null);
            setClip();
            new AffineTransform(this.xform).scale(1.0d / this.xformScalingFactorX, 1.0d / this.xformScalingFactorY);
            getGraphicsState().getSoftMask();
        }
    }

    /* loaded from: classes3.dex */
    public final class TransparencyGroup {
        private TransparencyGroup(PDTransparencyGroup pDTransparencyGroup, boolean z, Matrix matrix, PDColor pDColor) throws IOException {
            PageDrawer.this = r5;
            Matrix concatenate = Matrix.concatenate(matrix, pDTransparencyGroup.getMatrix());
            PDRectangle bBox = pDTransparencyGroup.getBBox();
            if (bBox == null) {
                Log.w("PdfBox-Android", "transparency group ignored because BBox is null");
                bBox = new PDRectangle();
            }
            bBox.transform(concatenate);
            AffineTransform affineTransform = r5.xform;
            r5.xform = AffineTransform.getScaleInstance(r5.xformScalingFactorX, r5.xformScalingFactorY);
            isGray(pDTransparencyGroup.getGroup().getColorSpace(pDTransparencyGroup.getResources()));
            if ((z || pDTransparencyGroup.getGroup().isIsolated() || !r5.hasBlendMode(pDTransparencyGroup, new HashSet())) ? false : true) {
                if (r5.transparencyGroupStack.isEmpty()) {
                    r5.renderer.getPageImage();
                } else {
                    TransparencyGroup transparencyGroup = (TransparencyGroup) r5.transparencyGroupStack.peek();
                }
            }
            boolean z2 = r5.flipTG;
            r5.flipTG = false;
            PDRectangle pDRectangle = r5.pageSize;
            Path.FillType fillType = r5.clipWindingRule;
            r5.clipWindingRule = null;
            Path path = r5.linePath;
            r5.linePath = new Path();
            r5.setRenderingHints();
            try {
                if (z) {
                    r5.processSoftMask(pDTransparencyGroup);
                } else {
                    r5.transparencyGroupStack.push(this);
                    r5.processTransparencyGroup(pDTransparencyGroup);
                    if (!r5.transparencyGroupStack.isEmpty()) {
                        r5.transparencyGroupStack.pop();
                    }
                }
            } finally {
                r5.flipTG = z2;
                r5.clipWindingRule = fillType;
                r5.linePath = path;
                r5.pageSize = pDRectangle;
                r5.xform = affineTransform;
            }
        }

        private boolean isGray(PDColorSpace pDColorSpace) {
            return pDColorSpace instanceof PDDeviceGray;
        }
    }

    public boolean hasBlendMode(PDTransparencyGroup pDTransparencyGroup, Set<COSBase> set) {
        if (set.contains(pDTransparencyGroup.getCOSObject())) {
            return false;
        }
        set.add(pDTransparencyGroup.getCOSObject());
        PDResources resources = pDTransparencyGroup.getResources();
        if (resources == null) {
            return false;
        }
        for (COSName cOSName : resources.getExtGStateNames()) {
            PDExtendedGraphicsState extGState = resources.getExtGState(cOSName);
            if (extGState != null && extGState.getBlendMode() != BlendMode.NORMAL) {
                return true;
            }
        }
        for (COSName cOSName2 : resources.getXObjectNames()) {
            try {
                PDXObject xObject = resources.getXObject(cOSName2);
                if ((xObject instanceof PDTransparencyGroup) && hasBlendMode((PDTransparencyGroup) xObject, set)) {
                    return true;
                }
            } catch (IOException unused) {
            }
        }
        return false;
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void beginMarkedContentSequence(COSName cOSName, COSDictionary cOSDictionary) {
        int i = this.nestedHiddenOCGCount;
        if (i > 0) {
            this.nestedHiddenOCGCount = i + 1;
        } else if (cOSName == null || getPage().getResources() == null || !isHiddenOCG(getPage().getResources().getProperties(cOSName))) {
        } else {
            this.nestedHiddenOCGCount = 1;
        }
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDFStreamEngine
    public void endMarkedContentSequence() {
        int i = this.nestedHiddenOCGCount;
        if (i > 0) {
            this.nestedHiddenOCGCount = i - 1;
        }
    }

    private boolean isContentRendered() {
        return this.nestedHiddenOCGCount <= 0;
    }

    private boolean isHiddenOCG(PDPropertyList pDPropertyList) {
        if (pDPropertyList instanceof PDOptionalContentGroup) {
            PDOptionalContentGroup pDOptionalContentGroup = (PDOptionalContentGroup) pDPropertyList;
            PDOptionalContentGroup.RenderState renderState = pDOptionalContentGroup.getRenderState(this.destination);
            return renderState == null ? !getRenderer().isGroupEnabled(pDOptionalContentGroup) : PDOptionalContentGroup.RenderState.OFF.equals(renderState);
        } else if (pDPropertyList instanceof PDOptionalContentMembershipDictionary) {
            return isHiddenOCMD((PDOptionalContentMembershipDictionary) pDPropertyList);
        } else {
            return false;
        }
    }

    private boolean isHiddenOCMD(PDOptionalContentMembershipDictionary pDOptionalContentMembershipDictionary) {
        if (pDOptionalContentMembershipDictionary.getCOSObject().getCOSArray(COSName.VE) != null) {
            Log.i("PdfBox-Android", "/VE entry ignored in Optional Content Membership Dictionary");
        }
        List<PDPropertyList> oCGs = pDOptionalContentMembershipDictionary.getOCGs();
        if (oCGs.isEmpty()) {
            return false;
        }
        ArrayList<Boolean> arrayList = new ArrayList();
        for (PDPropertyList pDPropertyList : oCGs) {
            arrayList.add(Boolean.valueOf(!isHiddenOCG(pDPropertyList)));
        }
        COSName visibilityPolicy = pDOptionalContentMembershipDictionary.getVisibilityPolicy();
        if (COSName.ANY_OFF.equals(visibilityPolicy)) {
            for (Boolean bool : arrayList) {
                if (!bool.booleanValue()) {
                    return false;
                }
            }
            return true;
        } else if (COSName.ALL_ON.equals(visibilityPolicy)) {
            for (Boolean bool2 : arrayList) {
                if (!bool2.booleanValue()) {
                    return true;
                }
            }
            return false;
        } else if (COSName.ALL_OFF.equals(visibilityPolicy)) {
            for (Boolean bool3 : arrayList) {
                if (bool3.booleanValue()) {
                    return true;
                }
            }
            return false;
        } else {
            for (Boolean bool4 : arrayList) {
                if (bool4.booleanValue()) {
                    return false;
                }
            }
            return true;
        }
    }
}
