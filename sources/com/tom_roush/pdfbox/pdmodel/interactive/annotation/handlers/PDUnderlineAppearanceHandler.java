package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import android.util.Log;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationTextMarkup;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDUnderlineAppearanceHandler extends PDAbstractAppearanceHandler {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDUnderlineAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDUnderlineAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateNormalAppearance() {
        float[] quadPoints;
        PDAnnotationTextMarkup pDAnnotationTextMarkup = (PDAnnotationTextMarkup) getAnnotation();
        PDRectangle rectangle = pDAnnotationTextMarkup.getRectangle();
        if (rectangle == null || (quadPoints = pDAnnotationTextMarkup.getQuadPoints()) == null) {
            return;
        }
        AnnotationBorder annotationBorder = AnnotationBorder.getAnnotationBorder(pDAnnotationTextMarkup, pDAnnotationTextMarkup.getBorderStyle());
        PDColor color = pDAnnotationTextMarkup.getColor();
        if (color == null || color.getComponents().length == 0) {
            return;
        }
        float f = 0.0f;
        if (Float.compare(annotationBorder.width, 0.0f) == 0) {
            annotationBorder.width = 1.5f;
        }
        int i = 0;
        float f2 = Float.MIN_VALUE;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MAX_VALUE;
        float f5 = Float.MIN_VALUE;
        for (int i2 = 0; i2 < quadPoints.length / 2; i2++) {
            int i3 = i2 * 2;
            float f6 = quadPoints[i3];
            float f7 = quadPoints[i3 + 1];
            f3 = Math.min(f3, f6);
            f4 = Math.min(f4, f7);
            f2 = Math.max(f2, f6);
            f5 = Math.max(f5, f7);
        }
        rectangle.setLowerLeftX(Math.min(f3 - (annotationBorder.width / 2.0f), rectangle.getLowerLeftX()));
        rectangle.setLowerLeftY(Math.min(f4 - (annotationBorder.width / 2.0f), rectangle.getLowerLeftY()));
        rectangle.setUpperRightX(Math.max(f2 + (annotationBorder.width / 2.0f), rectangle.getUpperRightX()));
        rectangle.setUpperRightY(Math.max(f5 + (annotationBorder.width / 2.0f), rectangle.getUpperRightY()));
        pDAnnotationTextMarkup.setRectangle(rectangle);
        PDAppearanceContentStream pDAppearanceContentStream = null;
        try {
            try {
                pDAppearanceContentStream = getNormalAppearanceAsContentStream();
                setOpacity(pDAppearanceContentStream, pDAnnotationTextMarkup.getConstantOpacity());
                pDAppearanceContentStream.setStrokingColor(color);
                if (annotationBorder.dashArray != null) {
                    pDAppearanceContentStream.setLineDashPattern(annotationBorder.dashArray, 0.0f);
                }
                pDAppearanceContentStream.setLineWidth(annotationBorder.width);
                while (i < quadPoints.length / 8) {
                    int i4 = i * 8;
                    int i5 = i4 + 4;
                    int i6 = i4 + 1;
                    int i7 = i4 + 5;
                    float sqrt = (float) Math.sqrt(Math.pow(quadPoints[i4] - quadPoints[i5], 2.0d) + Math.pow(quadPoints[i6] - quadPoints[i7], 2.0d));
                    float f8 = quadPoints[i5];
                    float f9 = quadPoints[i7];
                    if (Float.compare(sqrt, f) != 0) {
                        f8 += (((quadPoints[i4] - quadPoints[i5]) / sqrt) * sqrt) / 7.0f;
                        f9 += ((quadPoints[i6] - quadPoints[i7]) / sqrt) * (sqrt / 7.0f);
                    }
                    int i8 = i4 + 2;
                    int i9 = i4 + 6;
                    int i10 = i4 + 3;
                    int i11 = i4 + 7;
                    float sqrt2 = (float) Math.sqrt(Math.pow(quadPoints[i8] - quadPoints[i9], 2.0d) + Math.pow(quadPoints[i10] - quadPoints[i11], 2.0d));
                    float f10 = quadPoints[i9];
                    float f11 = quadPoints[i11];
                    if (Float.compare(sqrt2, 0.0f) != 0) {
                        f10 += (((quadPoints[i8] - quadPoints[i9]) / sqrt2) * sqrt2) / 7.0f;
                        f11 += (((quadPoints[i10] - quadPoints[i11]) / sqrt2) * sqrt2) / 7.0f;
                    }
                    pDAppearanceContentStream.moveTo(f8, f9);
                    pDAppearanceContentStream.lineTo(f10, f11);
                    i++;
                    f = 0.0f;
                }
                pDAppearanceContentStream.stroke();
            } catch (IOException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
            }
        } finally {
            IOUtils.closeQuietly(pDAppearanceContentStream);
        }
    }
}
