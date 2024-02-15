package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import android.util.Log;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDInkAppearanceHandler extends PDAbstractAppearanceHandler {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDInkAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDInkAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateNormalAppearance() {
        float[][] inkList;
        float[][] inkList2;
        PDAnnotationMarkup pDAnnotationMarkup = (PDAnnotationMarkup) getAnnotation();
        PDColor color = pDAnnotationMarkup.getColor();
        if (color == null || color.getComponents().length == 0) {
            return;
        }
        AnnotationBorder annotationBorder = AnnotationBorder.getAnnotationBorder(pDAnnotationMarkup, pDAnnotationMarkup.getBorderStyle());
        if (Float.compare(annotationBorder.width, 0.0f) == 0) {
            return;
        }
        float f = Float.MIN_VALUE;
        float f2 = Float.MAX_VALUE;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MIN_VALUE;
        for (float[] fArr : pDAnnotationMarkup.getInkList()) {
            int length = fArr.length / 2;
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                float f5 = fArr[i2];
                float f6 = fArr[i2 + 1];
                f2 = Math.min(f2, f5);
                f3 = Math.min(f3, f6);
                f = Math.max(f, f5);
                f4 = Math.max(f4, f6);
            }
        }
        PDRectangle rectangle = pDAnnotationMarkup.getRectangle();
        if (rectangle == null) {
            return;
        }
        rectangle.setLowerLeftX(Math.min(f2 - (annotationBorder.width * 2.0f), rectangle.getLowerLeftX()));
        rectangle.setLowerLeftY(Math.min(f3 - (annotationBorder.width * 2.0f), rectangle.getLowerLeftY()));
        rectangle.setUpperRightX(Math.max(f + (annotationBorder.width * 2.0f), rectangle.getUpperRightX()));
        rectangle.setUpperRightY(Math.max(f4 + (annotationBorder.width * 2.0f), rectangle.getUpperRightY()));
        pDAnnotationMarkup.setRectangle(rectangle);
        PDAppearanceContentStream pDAppearanceContentStream = null;
        try {
            try {
                pDAppearanceContentStream = getNormalAppearanceAsContentStream();
            } catch (IOException e) {
                e = e;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(pDAppearanceContentStream);
                throw th;
            }
            try {
                setOpacity(pDAppearanceContentStream, pDAnnotationMarkup.getConstantOpacity());
                pDAppearanceContentStream.setStrokingColor(color);
                if (annotationBorder.dashArray != null) {
                    pDAppearanceContentStream.setLineDashPattern(annotationBorder.dashArray, 0.0f);
                }
                pDAppearanceContentStream.setLineWidth(annotationBorder.width);
                for (float[] fArr2 : pDAnnotationMarkup.getInkList()) {
                    int length2 = fArr2.length / 2;
                    for (int i3 = 0; i3 < length2; i3++) {
                        int i4 = i3 * 2;
                        float f7 = fArr2[i4];
                        float f8 = fArr2[i4 + 1];
                        if (i3 == 0) {
                            pDAppearanceContentStream.moveTo(f7, f8);
                        } else {
                            pDAppearanceContentStream.lineTo(f7, f8);
                        }
                    }
                    pDAppearanceContentStream.stroke();
                }
            } catch (IOException e2) {
                e = e2;
                Log.e("PdfBox-Android", e.getMessage(), e);
                IOUtils.closeQuietly(pDAppearanceContentStream);
            }
            IOUtils.closeQuietly(pDAppearanceContentStream);
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly(pDAppearanceContentStream);
            throw th;
        }
    }
}
