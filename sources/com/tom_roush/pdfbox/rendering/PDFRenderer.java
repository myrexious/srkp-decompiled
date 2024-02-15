package com.tom_roush.pdfbox.rendering;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageTree;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentProperties;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.AnnotationFilter;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDFRenderer {
    private RenderDestination defaultDestination;
    protected final PDDocument document;
    private Bitmap pageImage;
    private final PDPageTree pageTree;
    private AnnotationFilter annotationFilter = new AnnotationFilter() { // from class: com.tom_roush.pdfbox.rendering.PDFRenderer.1
        @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.AnnotationFilter
        public boolean accept(PDAnnotation pDAnnotation) {
            return true;
        }
    };
    private boolean subsamplingAllowed = false;
    private float imageDownscalingOptimizationThreshold = 0.5f;

    public PDFRenderer(PDDocument pDDocument) {
        this.document = pDDocument;
        this.pageTree = pDDocument.getPages();
    }

    public AnnotationFilter getAnnotationsFilter() {
        return this.annotationFilter;
    }

    public void setAnnotationsFilter(AnnotationFilter annotationFilter) {
        this.annotationFilter = annotationFilter;
    }

    public boolean isSubsamplingAllowed() {
        return this.subsamplingAllowed;
    }

    public void setSubsamplingAllowed(boolean z) {
        this.subsamplingAllowed = z;
    }

    public RenderDestination getDefaultDestination() {
        return this.defaultDestination;
    }

    public void setDefaultDestination(RenderDestination renderDestination) {
        this.defaultDestination = renderDestination;
    }

    public float getImageDownscalingOptimizationThreshold() {
        return this.imageDownscalingOptimizationThreshold;
    }

    public void setImageDownscalingOptimizationThreshold(float f) {
        this.imageDownscalingOptimizationThreshold = f;
    }

    public Bitmap renderImage(int i) throws IOException {
        return renderImage(i, 1.0f);
    }

    public Bitmap renderImage(int i, float f) throws IOException {
        return renderImage(i, f, ImageType.RGB);
    }

    public Bitmap renderImageWithDPI(int i, float f) throws IOException {
        return renderImage(i, f / 72.0f, ImageType.RGB);
    }

    public Bitmap renderImageWithDPI(int i, float f, ImageType imageType) throws IOException {
        return renderImage(i, f / 72.0f, imageType);
    }

    public Bitmap renderImage(int i, float f, ImageType imageType) throws IOException {
        RenderDestination renderDestination = this.defaultDestination;
        if (renderDestination == null) {
            renderDestination = RenderDestination.EXPORT;
        }
        return renderImage(i, f, imageType, renderDestination);
    }

    public Bitmap renderImage(int i, float f, ImageType imageType, RenderDestination renderDestination) throws IOException {
        Bitmap.Config bitmapConfig;
        Bitmap createBitmap;
        PDPage pDPage = this.pageTree.get(i);
        PDRectangle cropBox = pDPage.getCropBox();
        float width = cropBox.getWidth();
        float height = cropBox.getHeight();
        int max = (int) Math.max(Math.floor(width * f), 1.0d);
        int max2 = (int) Math.max(Math.floor(height * f), 1.0d);
        if (max * max2 > 2147483647L) {
            throw new IOException("Maximum size of image exceeded (w * h * scale ^ 2) = " + width + " * " + height + " * " + f + " ^ 2 > 2147483647");
        }
        int rotation = pDPage.getRotation();
        if (imageType != ImageType.ARGB && hasBlendMode(pDPage)) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        } else {
            bitmapConfig = imageType.toBitmapConfig();
        }
        if (rotation == 90 || rotation == 270) {
            createBitmap = Bitmap.createBitmap(max2, max, bitmapConfig);
        } else {
            createBitmap = Bitmap.createBitmap(max, max2, bitmapConfig);
        }
        Bitmap bitmap = createBitmap;
        this.pageImage = bitmap;
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap);
        if (imageType == ImageType.ARGB) {
            paint.setColor(0);
        } else {
            paint.setColor(-1);
        }
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight(), paint);
        paint.reset();
        transform(canvas, pDPage.getRotation(), cropBox, f, f);
        createPageDrawer(new PageDrawerParameters(this, pDPage, this.subsamplingAllowed, renderDestination, this.imageDownscalingOptimizationThreshold)).drawPage(paint, canvas, cropBox);
        if (bitmap.getConfig() != imageType.toBitmapConfig()) {
            Bitmap createBitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), imageType.toBitmapConfig());
            Canvas canvas2 = new Canvas(createBitmap2);
            paint.setColor(-1);
            paint.setStyle(Paint.Style.FILL);
            canvas2.drawRect(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight(), paint);
            canvas2.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            return createBitmap2;
        }
        return bitmap;
    }

    public void renderPageToGraphics(int i, Paint paint, Canvas canvas) throws IOException {
        renderPageToGraphics(i, paint, canvas, 1.0f);
    }

    public void renderPageToGraphics(int i, Paint paint, Canvas canvas, float f) throws IOException {
        renderPageToGraphics(i, paint, canvas, f, f);
    }

    public void renderPageToGraphics(int i, Paint paint, Canvas canvas, float f, float f2) throws IOException {
        RenderDestination renderDestination = this.defaultDestination;
        if (renderDestination == null) {
            renderDestination = RenderDestination.VIEW;
        }
        renderPageToGraphics(i, paint, canvas, f, f2, renderDestination);
    }

    public void renderPageToGraphics(int i, Paint paint, Canvas canvas, float f, float f2, RenderDestination renderDestination) throws IOException {
        PDPage pDPage = this.pageTree.get(i);
        PDRectangle cropBox = pDPage.getCropBox();
        transform(canvas, pDPage.getRotation(), cropBox, f, f2);
        canvas.drawRect(0.0f, 0.0f, cropBox.getWidth(), cropBox.getHeight(), paint);
        createPageDrawer(new PageDrawerParameters(this, pDPage, this.subsamplingAllowed, renderDestination, this.imageDownscalingOptimizationThreshold)).drawPage(paint, canvas, cropBox);
    }

    public boolean isGroupEnabled(PDOptionalContentGroup pDOptionalContentGroup) {
        PDOptionalContentProperties oCProperties = this.document.getDocumentCatalog().getOCProperties();
        return oCProperties == null || oCProperties.isGroupEnabled(pDOptionalContentGroup);
    }

    private void transform(Canvas canvas, int i, PDRectangle pDRectangle, float f, float f2) {
        float f3;
        canvas.scale(f, f2);
        if (i != 0) {
            float f4 = 0.0f;
            if (i == 90) {
                f4 = pDRectangle.getHeight();
                f3 = 0.0f;
            } else if (i != 180) {
                f3 = i != 270 ? 0.0f : pDRectangle.getWidth();
            } else {
                f4 = pDRectangle.getWidth();
                f3 = pDRectangle.getHeight();
            }
            canvas.translate(f4, f3);
            canvas.rotate(i);
        }
    }

    protected PageDrawer createPageDrawer(PageDrawerParameters pageDrawerParameters) throws IOException {
        PageDrawer pageDrawer = new PageDrawer(pageDrawerParameters);
        pageDrawer.setAnnotationFilter(this.annotationFilter);
        return pageDrawer;
    }

    private boolean hasBlendMode(PDPage pDPage) {
        PDResources resources = pDPage.getResources();
        if (resources == null) {
            return false;
        }
        for (COSName cOSName : resources.getExtGStateNames()) {
            PDExtendedGraphicsState extGState = resources.getExtGState(cOSName);
            if (extGState != null && extGState.getBlendMode() != BlendMode.NORMAL) {
                return true;
            }
        }
        return false;
    }

    public Bitmap getPageImage() {
        return this.pageImage;
    }
}
