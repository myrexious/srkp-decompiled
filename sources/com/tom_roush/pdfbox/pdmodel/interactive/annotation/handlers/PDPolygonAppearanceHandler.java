package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderEffectDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import java.io.IOException;
import java.lang.reflect.Array;

/* loaded from: classes3.dex */
public class PDPolygonAppearanceHandler extends PDAbstractAppearanceHandler {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDPolygonAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDPolygonAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateNormalAppearance() {
        float[][] pathArray;
        PDAnnotationMarkup pDAnnotationMarkup = (PDAnnotationMarkup) getAnnotation();
        float lineWidth = getLineWidth();
        PDRectangle rectangle = pDAnnotationMarkup.getRectangle();
        if (rectangle == null || (pathArray = getPathArray(pDAnnotationMarkup)) == null) {
            return;
        }
        float f = Float.MIN_VALUE;
        float f2 = Float.MAX_VALUE;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MIN_VALUE;
        for (float[] fArr : pathArray) {
            int i = 0;
            while (true) {
                if (i < fArr.length / 2) {
                    int i2 = i * 2;
                    float f5 = fArr[i2];
                    float f6 = fArr[i2 + 1];
                    f2 = Math.min(f2, f5);
                    f3 = Math.min(f3, f6);
                    f = Math.max(f, f5);
                    f4 = Math.max(f4, f6);
                    i++;
                }
            }
        }
        rectangle.setLowerLeftX(Math.min(f2 - lineWidth, rectangle.getLowerLeftX()));
        rectangle.setLowerLeftY(Math.min(f3 - lineWidth, rectangle.getLowerLeftY()));
        rectangle.setUpperRightX(Math.max(f + lineWidth, rectangle.getUpperRightX()));
        rectangle.setUpperRightY(Math.max(f4 + lineWidth, rectangle.getUpperRightY()));
        pDAnnotationMarkup.setRectangle(rectangle);
        PDAppearanceContentStream pDAppearanceContentStream = null;
        try {
            try {
                pDAppearanceContentStream = getNormalAppearanceAsContentStream();
                boolean strokingColorOnDemand = pDAppearanceContentStream.setStrokingColorOnDemand(getColor());
                boolean nonStrokingColorOnDemand = pDAppearanceContentStream.setNonStrokingColorOnDemand(pDAnnotationMarkup.getInteriorColor());
                setOpacity(pDAppearanceContentStream, pDAnnotationMarkup.getConstantOpacity());
                pDAppearanceContentStream.setBorderLine(lineWidth, pDAnnotationMarkup.getBorderStyle(), pDAnnotationMarkup.getBorder());
                PDBorderEffectDictionary borderEffect = pDAnnotationMarkup.getBorderEffect();
                if (borderEffect != null && borderEffect.getStyle().equals("C")) {
                    CloudyBorder cloudyBorder = new CloudyBorder(pDAppearanceContentStream, borderEffect.getIntensity(), lineWidth, getRectangle());
                    cloudyBorder.createCloudyPolygon(pathArray);
                    pDAnnotationMarkup.setRectangle(cloudyBorder.getRectangle());
                    PDAppearanceStream normalAppearanceStream = pDAnnotationMarkup.getNormalAppearanceStream();
                    normalAppearanceStream.setBBox(cloudyBorder.getBBox());
                    normalAppearanceStream.setMatrix(cloudyBorder.getMatrix());
                } else {
                    for (int i3 = 0; i3 < pathArray.length; i3++) {
                        float[] fArr2 = pathArray[i3];
                        if (i3 == 0 && fArr2.length == 2) {
                            pDAppearanceContentStream.moveTo(fArr2[0], fArr2[1]);
                        } else if (fArr2.length == 2) {
                            pDAppearanceContentStream.lineTo(fArr2[0], fArr2[1]);
                        } else if (fArr2.length == 6) {
                            pDAppearanceContentStream.curveTo(fArr2[0], fArr2[1], fArr2[2], fArr2[3], fArr2[4], fArr2[5]);
                        }
                    }
                    pDAppearanceContentStream.closePath();
                }
                pDAppearanceContentStream.drawShape(lineWidth, strokingColorOnDemand, nonStrokingColorOnDemand);
            } catch (IOException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
            }
        } finally {
            IOUtils.closeQuietly(pDAppearanceContentStream);
        }
    }

    private float[][] getPathArray(PDAnnotationMarkup pDAnnotationMarkup) {
        float[][] path = pDAnnotationMarkup.getPath();
        if (path == null) {
            float[] vertices = pDAnnotationMarkup.getVertices();
            if (vertices == null) {
                return null;
            }
            int length = vertices.length / 2;
            float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, length, 2);
            for (int i = 0; i < length; i++) {
                float[] fArr2 = fArr[i];
                int i2 = i * 2;
                fArr2[0] = vertices[i2];
                fArr2[1] = vertices[i2 + 1];
            }
            return fArr;
        }
        return path;
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
