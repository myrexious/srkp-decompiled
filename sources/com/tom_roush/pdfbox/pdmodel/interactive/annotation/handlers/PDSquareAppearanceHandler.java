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
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationSquareCircle;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderEffectDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDSquareAppearanceHandler extends PDAbstractAppearanceHandler {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDSquareAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDSquareAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateNormalAppearance() {
        float lineWidth = getLineWidth();
        PDAnnotationSquareCircle pDAnnotationSquareCircle = (PDAnnotationSquareCircle) getAnnotation();
        PDAppearanceContentStream pDAppearanceContentStream = null;
        try {
            try {
                pDAppearanceContentStream = getNormalAppearanceAsContentStream();
                boolean strokingColorOnDemand = pDAppearanceContentStream.setStrokingColorOnDemand(getColor());
                boolean nonStrokingColorOnDemand = pDAppearanceContentStream.setNonStrokingColorOnDemand(pDAnnotationSquareCircle.getInteriorColor());
                setOpacity(pDAppearanceContentStream, pDAnnotationSquareCircle.getConstantOpacity());
                pDAppearanceContentStream.setBorderLine(lineWidth, pDAnnotationSquareCircle.getBorderStyle(), pDAnnotationSquareCircle.getBorder());
                PDBorderEffectDictionary borderEffect = pDAnnotationSquareCircle.getBorderEffect();
                if (borderEffect != null && borderEffect.getStyle().equals("C")) {
                    CloudyBorder cloudyBorder = new CloudyBorder(pDAppearanceContentStream, borderEffect.getIntensity(), lineWidth, getRectangle());
                    cloudyBorder.createCloudyRectangle(pDAnnotationSquareCircle.getRectDifference());
                    pDAnnotationSquareCircle.setRectangle(cloudyBorder.getRectangle());
                    pDAnnotationSquareCircle.setRectDifference(cloudyBorder.getRectDifference());
                    PDAppearanceStream normalAppearanceStream = pDAnnotationSquareCircle.getNormalAppearanceStream();
                    normalAppearanceStream.setBBox(cloudyBorder.getBBox());
                    normalAppearanceStream.setMatrix(cloudyBorder.getMatrix());
                } else {
                    PDRectangle handleBorderBox = handleBorderBox(pDAnnotationSquareCircle, lineWidth);
                    pDAppearanceContentStream.addRect(handleBorderBox.getLowerLeftX(), handleBorderBox.getLowerLeftY(), handleBorderBox.getWidth(), handleBorderBox.getHeight());
                }
                pDAppearanceContentStream.drawShape(lineWidth, strokingColorOnDemand, nonStrokingColorOnDemand);
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
