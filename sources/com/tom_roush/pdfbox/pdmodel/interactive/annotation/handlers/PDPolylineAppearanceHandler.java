package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDPolylineAppearanceHandler extends PDAbstractAppearanceHandler {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDPolylineAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDPolylineAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateNormalAppearance() {
        float[] vertices;
        String str;
        PDAnnotationMarkup pDAnnotationMarkup;
        boolean z;
        boolean z2;
        boolean z3;
        String str2 = "None";
        PDAnnotationMarkup pDAnnotationMarkup2 = (PDAnnotationMarkup) getAnnotation();
        PDRectangle rectangle = pDAnnotationMarkup2.getRectangle();
        if (rectangle == null || (vertices = pDAnnotationMarkup2.getVertices()) == null || vertices.length < 4) {
            return;
        }
        AnnotationBorder annotationBorder = AnnotationBorder.getAnnotationBorder(pDAnnotationMarkup2, pDAnnotationMarkup2.getBorderStyle());
        PDColor color = pDAnnotationMarkup2.getColor();
        if (color == null || color.getComponents().length == 0 || Float.compare(annotationBorder.width, 0.0f) == 0) {
            return;
        }
        float f = Float.MIN_VALUE;
        float f2 = Float.MAX_VALUE;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MIN_VALUE;
        for (int i = 0; i < vertices.length / 2; i++) {
            int i2 = i * 2;
            float f5 = vertices[i2];
            float f6 = vertices[i2 + 1];
            f2 = Math.min(f2, f5);
            f3 = Math.min(f3, f6);
            f = Math.max(f, f5);
            f4 = Math.max(f4, f6);
        }
        rectangle.setLowerLeftX(Math.min(f2 - (annotationBorder.width * 10.0f), rectangle.getLowerLeftX()));
        rectangle.setLowerLeftY(Math.min(f3 - (annotationBorder.width * 10.0f), rectangle.getLowerLeftY()));
        rectangle.setUpperRightX(Math.max(f + (annotationBorder.width * 10.0f), rectangle.getUpperRightX()));
        rectangle.setUpperRightY(Math.max(f4 + (annotationBorder.width * 10.0f), rectangle.getUpperRightY()));
        pDAnnotationMarkup2.setRectangle(rectangle);
        PDAppearanceContentStream pDAppearanceContentStream = null;
        try {
            try {
                pDAppearanceContentStream = getNormalAppearanceAsContentStream();
                boolean nonStrokingColorOnDemand = pDAppearanceContentStream.setNonStrokingColorOnDemand(pDAnnotationMarkup2.getInteriorColor());
                setOpacity(pDAppearanceContentStream, pDAnnotationMarkup2.getConstantOpacity());
                boolean strokingColorOnDemand = pDAppearanceContentStream.setStrokingColorOnDemand(color);
                if (annotationBorder.dashArray != null) {
                    pDAppearanceContentStream.setLineDashPattern(annotationBorder.dashArray, 0.0f);
                }
                pDAppearanceContentStream.setLineWidth(annotationBorder.width);
                int i3 = 0;
                while (i3 < vertices.length / 2) {
                    int i4 = i3 * 2;
                    float f7 = vertices[i4];
                    float f8 = vertices[i4 + 1];
                    if (i3 == 0) {
                        if (SHORT_STYLES.contains(pDAnnotationMarkup2.getStartPointEndingStyle())) {
                            float f9 = vertices[2];
                            float f10 = vertices[3];
                            z = strokingColorOnDemand;
                            z2 = nonStrokingColorOnDemand;
                            str = str2;
                            pDAnnotationMarkup = pDAnnotationMarkup2;
                            float sqrt = (float) Math.sqrt(Math.pow(f7 - f9, 2.0d) + Math.pow(f8 - f10, 2.0d));
                            if (Float.compare(sqrt, 0.0f) != 0) {
                                f7 += ((f9 - f7) / sqrt) * annotationBorder.width;
                                f8 += ((f10 - f8) / sqrt) * annotationBorder.width;
                            }
                        } else {
                            str = str2;
                            pDAnnotationMarkup = pDAnnotationMarkup2;
                            z = strokingColorOnDemand;
                            z2 = nonStrokingColorOnDemand;
                        }
                        pDAppearanceContentStream.moveTo(f7, f8);
                        z3 = false;
                    } else {
                        str = str2;
                        pDAnnotationMarkup = pDAnnotationMarkup2;
                        z = strokingColorOnDemand;
                        z2 = nonStrokingColorOnDemand;
                        if (i3 == (vertices.length / 2) - 1 && SHORT_STYLES.contains(pDAnnotationMarkup.getEndPointEndingStyle())) {
                            float f11 = vertices[vertices.length - 4];
                            float f12 = vertices[vertices.length - 3];
                            float sqrt2 = (float) Math.sqrt(Math.pow(f11 - f7, 2.0d) + Math.pow(f12 - f8, 2.0d));
                            z3 = false;
                            if (Float.compare(sqrt2, 0.0f) != 0) {
                                f7 -= ((f7 - f11) / sqrt2) * annotationBorder.width;
                                f8 -= ((f8 - f12) / sqrt2) * annotationBorder.width;
                            }
                        } else {
                            z3 = false;
                        }
                        pDAppearanceContentStream.lineTo(f7, f8);
                    }
                    i3++;
                    nonStrokingColorOnDemand = z2;
                    strokingColorOnDemand = z;
                    str2 = str;
                    pDAnnotationMarkup2 = pDAnnotationMarkup;
                }
                String str3 = str2;
                PDAnnotationMarkup pDAnnotationMarkup3 = pDAnnotationMarkup2;
                boolean z4 = strokingColorOnDemand;
                boolean z5 = nonStrokingColorOnDemand;
                pDAppearanceContentStream.stroke();
                if (!str3.equals(pDAnnotationMarkup3.getStartPointEndingStyle())) {
                    float f13 = vertices[2];
                    float f14 = vertices[3];
                    float f15 = vertices[0];
                    float f16 = vertices[1];
                    pDAppearanceContentStream.saveGraphicsState();
                    if (ANGLED_STYLES.contains(pDAnnotationMarkup3.getStartPointEndingStyle())) {
                        pDAppearanceContentStream.transform(Matrix.getRotateInstance(Math.atan2(f14 - f16, f13 - f15), f15, f16));
                    } else {
                        pDAppearanceContentStream.transform(Matrix.getTranslateInstance(f15, f16));
                    }
                    drawStyle(pDAnnotationMarkup3.getStartPointEndingStyle(), pDAppearanceContentStream, 0.0f, 0.0f, annotationBorder.width, z4, z5, false);
                    pDAppearanceContentStream.restoreGraphicsState();
                }
                if (!str3.equals(pDAnnotationMarkup3.getEndPointEndingStyle())) {
                    float f17 = vertices[vertices.length - 4];
                    float f18 = vertices[vertices.length - 3];
                    float f19 = vertices[vertices.length - 2];
                    float f20 = vertices[vertices.length - 1];
                    if (ANGLED_STYLES.contains(pDAnnotationMarkup3.getEndPointEndingStyle())) {
                        pDAppearanceContentStream.transform(Matrix.getRotateInstance(Math.atan2(f20 - f18, f19 - f17), f19, f20));
                    } else {
                        pDAppearanceContentStream.transform(Matrix.getTranslateInstance(f19, f20));
                    }
                    drawStyle(pDAnnotationMarkup3.getEndPointEndingStyle(), pDAppearanceContentStream, 0.0f, 0.0f, annotationBorder.width, z4, z5, true);
                }
            } catch (IOException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
            }
        } finally {
            IOUtils.closeQuietly(pDAppearanceContentStream);
        }
    }

    float getLineWidth() {
        PDAnnotationMarkup pDAnnotationMarkup = (PDAnnotationMarkup) getAnnotation();
        PDBorderStyleDictionary borderStyle = pDAnnotationMarkup.getBorderStyle();
        if (borderStyle != null) {
            return borderStyle.getWidth();
        }
        COSArray border = pDAnnotationMarkup.getBorder();
        if (border.size() >= 3) {
            COSBase object = border.getObject(2);
            if (object instanceof COSNumber) {
                return ((COSNumber) object).floatValue();
            }
            return 1.0f;
        }
        return 1.0f;
    }
}
