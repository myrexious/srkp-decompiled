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
public class PDStrikeoutAppearanceHandler extends PDAbstractAppearanceHandler {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDStrikeoutAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDStrikeoutAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
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
        if (Float.compare(annotationBorder.width, 0.0f) == 0) {
            annotationBorder.width = 1.5f;
        }
        int i = 0;
        float f = Float.MIN_VALUE;
        float f2 = Float.MAX_VALUE;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MIN_VALUE;
        for (int i2 = 0; i2 < quadPoints.length / 2; i2++) {
            int i3 = i2 * 2;
            float f5 = quadPoints[i3];
            float f6 = quadPoints[i3 + 1];
            f2 = Math.min(f2, f5);
            f3 = Math.min(f3, f6);
            f = Math.max(f, f5);
            f4 = Math.max(f4, f6);
        }
        rectangle.setLowerLeftX(Math.min(f2 - (annotationBorder.width / 2.0f), rectangle.getLowerLeftX()));
        rectangle.setLowerLeftY(Math.min(f3 - (annotationBorder.width / 2.0f), rectangle.getLowerLeftY()));
        rectangle.setUpperRightX(Math.max(f + (annotationBorder.width / 2.0f), rectangle.getUpperRightX()));
        rectangle.setUpperRightY(Math.max(f4 + (annotationBorder.width / 2.0f), rectangle.getUpperRightY()));
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
                    float f7 = quadPoints[i5];
                    float f8 = quadPoints[i7];
                    if (Float.compare(sqrt, 0.0f) != 0) {
                        float f9 = sqrt / 2.0f;
                        f7 += ((quadPoints[i4] - quadPoints[i5]) / sqrt) * (f9 - annotationBorder.width);
                        f8 += ((quadPoints[i6] - quadPoints[i7]) / sqrt) * (f9 - annotationBorder.width);
                    }
                    int i8 = i4 + 2;
                    int i9 = i4 + 6;
                    int i10 = i4 + 3;
                    int i11 = i4 + 7;
                    int i12 = i;
                    float sqrt2 = (float) Math.sqrt(Math.pow(quadPoints[i8] - quadPoints[i9], 2.0d) + Math.pow(quadPoints[i10] - quadPoints[i11], 2.0d));
                    float f10 = quadPoints[i9];
                    float f11 = quadPoints[i11];
                    if (Float.compare(sqrt2, 0.0f) != 0) {
                        float f12 = sqrt2 / 2.0f;
                        f10 += ((quadPoints[i8] - quadPoints[i9]) / sqrt2) * (f12 - annotationBorder.width);
                        f11 += ((quadPoints[i10] - quadPoints[i11]) / sqrt2) * (f12 - annotationBorder.width);
                    }
                    pDAppearanceContentStream.moveTo(f7, f8);
                    pDAppearanceContentStream.lineTo(f10, f11);
                    i = i12 + 1;
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
