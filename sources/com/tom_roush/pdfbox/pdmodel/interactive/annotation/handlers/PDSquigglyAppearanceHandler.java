package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import android.util.Log;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDFormContentStream;
import com.tom_roush.pdfbox.pdmodel.PDPatternContentStream;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.pattern.PDTilingPattern;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationTextMarkup;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDSquigglyAppearanceHandler extends PDAbstractAppearanceHandler {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDSquigglyAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDSquigglyAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateNormalAppearance() {
        float[] quadPoints;
        int i;
        PDAppearanceContentStream pDAppearanceContentStream;
        PDAppearanceContentStream normalAppearanceAsContentStream;
        PDFormContentStream pDFormContentStream;
        PDFormContentStream pDFormContentStream2;
        PDPatternContentStream pDPatternContentStream;
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
        int i2 = 0;
        float f = Float.MIN_VALUE;
        int i3 = 0;
        float f2 = Float.MAX_VALUE;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MIN_VALUE;
        while (true) {
            i = 2;
            if (i3 >= quadPoints.length / 2) {
                break;
            }
            int i4 = i3 * 2;
            float f5 = quadPoints[i4];
            float f6 = quadPoints[i4 + 1];
            f2 = Math.min(f2, f5);
            f3 = Math.min(f3, f6);
            f = Math.max(f, f5);
            f4 = Math.max(f4, f6);
            i3++;
        }
        rectangle.setLowerLeftX(Math.min(f2 - (annotationBorder.width / 2.0f), rectangle.getLowerLeftX()));
        rectangle.setLowerLeftY(Math.min(f3 - (annotationBorder.width / 2.0f), rectangle.getLowerLeftY()));
        rectangle.setUpperRightX(Math.max(f + (annotationBorder.width / 2.0f), rectangle.getUpperRightX()));
        rectangle.setUpperRightY(Math.max(f4 + (annotationBorder.width / 2.0f), rectangle.getUpperRightY()));
        pDAnnotationTextMarkup.setRectangle(rectangle);
        try {
            normalAppearanceAsContentStream = getNormalAppearanceAsContentStream();
            try {
            } catch (IOException e) {
                e = e;
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
            pDAppearanceContentStream = null;
        } catch (Throwable th2) {
            th = th2;
            pDAppearanceContentStream = null;
        }
        try {
            setOpacity(normalAppearanceAsContentStream, pDAnnotationTextMarkup.getConstantOpacity());
            normalAppearanceAsContentStream.setStrokingColor(color);
            while (i2 < quadPoints.length / 8) {
                int i5 = i2 * 8;
                int i6 = i5 + 5;
                float f7 = quadPoints[i5 + 1] - quadPoints[i6];
                float f8 = f7 / 40.0f;
                normalAppearanceAsContentStream.transform(new Matrix(f8, 0.0f, 0.0f, f8 / 1.8f, quadPoints[i5 + 4], quadPoints[i6]));
                PDFormXObject pDFormXObject = new PDFormXObject(createCOSStream());
                int i7 = i5 + 2;
                pDFormXObject.setBBox(new PDRectangle(-0.5f, -0.5f, (((quadPoints[i7] - quadPoints[i5]) / f7) * 40.0f) + 0.5f, 13.0f));
                pDFormXObject.setResources(new PDResources());
                pDFormXObject.setMatrix(AffineTransform.getTranslateInstance(0.5d, 0.5d));
                normalAppearanceAsContentStream.drawForm(pDFormXObject);
                try {
                    pDFormContentStream2 = new PDFormContentStream(pDFormXObject);
                } catch (Throwable th3) {
                    th = th3;
                    pDFormContentStream = null;
                }
                try {
                    PDTilingPattern pDTilingPattern = new PDTilingPattern();
                    pDTilingPattern.setBBox(new PDRectangle(0.0f, 0.0f, 10.0f, 12.0f));
                    pDTilingPattern.setXStep(10.0f);
                    pDTilingPattern.setYStep(13.0f);
                    pDTilingPattern.setTilingType(3);
                    pDTilingPattern.setPaintType(i);
                    try {
                        PDPatternContentStream pDPatternContentStream2 = new PDPatternContentStream(pDTilingPattern);
                        try {
                            pDPatternContentStream2.setLineCapStyle(1);
                            pDPatternContentStream2.setLineJoinStyle(1);
                            pDPatternContentStream2.setLineWidth(1.0f);
                            pDPatternContentStream2.setMiterLimit(10.0f);
                            pDPatternContentStream2.moveTo(0.0f, 1.0f);
                            pDPatternContentStream2.lineTo(5.0f, 11.0f);
                            pDPatternContentStream2.lineTo(10.0f, 1.0f);
                            pDPatternContentStream2.stroke();
                            IOUtils.closeQuietly(pDPatternContentStream2);
                            pDFormXObject.getResources().add(pDTilingPattern);
                            pDFormContentStream2.addRect(0.0f, 0.0f, ((quadPoints[i7] - quadPoints[i5]) / f7) * 40.0f, 12.0f);
                            pDFormContentStream2.fill();
                            IOUtils.closeQuietly(pDFormContentStream2);
                            i2++;
                            i = 2;
                        } catch (Throwable th4) {
                            th = th4;
                            pDPatternContentStream = pDPatternContentStream2;
                            IOUtils.closeQuietly(pDPatternContentStream);
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        pDPatternContentStream = null;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    pDFormContentStream = pDFormContentStream2;
                    IOUtils.closeQuietly(pDFormContentStream);
                    throw th;
                }
            }
            IOUtils.closeQuietly(normalAppearanceAsContentStream);
        } catch (IOException e3) {
            e = e3;
            pDAppearanceContentStream = normalAppearanceAsContentStream;
            try {
                Log.e("PdfBox-Android", e.getMessage(), e);
                IOUtils.closeQuietly(pDAppearanceContentStream);
            } catch (Throwable th7) {
                th = th7;
                IOUtils.closeQuietly(pDAppearanceContentStream);
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            pDAppearanceContentStream = normalAppearanceAsContentStream;
            IOUtils.closeQuietly(pDAppearanceContentStream);
            throw th;
        }
    }
}
