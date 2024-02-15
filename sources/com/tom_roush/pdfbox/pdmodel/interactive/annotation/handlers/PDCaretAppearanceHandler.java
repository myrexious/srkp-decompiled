package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDCaretAppearanceHandler extends PDAbstractAppearanceHandler {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDCaretAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDCaretAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateNormalAppearance() {
        PDAnnotationMarkup pDAnnotationMarkup = (PDAnnotationMarkup) getAnnotation();
        PDAppearanceContentStream pDAppearanceContentStream = null;
        try {
            try {
                pDAppearanceContentStream = getNormalAppearanceAsContentStream();
                pDAppearanceContentStream.setStrokingColor(getColor());
                pDAppearanceContentStream.setNonStrokingColor(getColor());
                setOpacity(pDAppearanceContentStream, pDAnnotationMarkup.getConstantOpacity());
                PDRectangle rectangle = getRectangle();
                PDRectangle pDRectangle = new PDRectangle(rectangle.getWidth(), rectangle.getHeight());
                PDAppearanceStream normalAppearanceStream = pDAnnotationMarkup.getNormalAppearanceStream();
                if (!pDAnnotationMarkup.getCOSObject().containsKey(COSName.RD)) {
                    float min = Math.min(rectangle.getHeight() / 10.0f, 5.0f);
                    pDAnnotationMarkup.setRectDifferences(min);
                    float f = -min;
                    float f2 = min * 2.0f;
                    PDRectangle pDRectangle2 = new PDRectangle(f, f, rectangle.getWidth() + f2, rectangle.getHeight() + f2);
                    normalAppearanceStream.setMatrix(normalAppearanceStream.getMatrix().createAffineTransform());
                    pDAnnotationMarkup.setRectangle(new PDRectangle(rectangle.getLowerLeftX() - min, rectangle.getLowerLeftY() - min, rectangle.getWidth() + f2, rectangle.getHeight() + f2));
                    pDRectangle = pDRectangle2;
                }
                normalAppearanceStream.setBBox(pDRectangle);
                float width = rectangle.getWidth() / 2.0f;
                float height = rectangle.getHeight() / 2.0f;
                pDAppearanceContentStream.moveTo(0.0f, 0.0f);
                pDAppearanceContentStream.curveTo(width, 0.0f, width, height, width, rectangle.getHeight());
                pDAppearanceContentStream.curveTo(width, height, width, 0.0f, rectangle.getWidth(), 0.0f);
                pDAppearanceContentStream.closePath();
                pDAppearanceContentStream.fill();
            } catch (IOException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
            }
        } finally {
            IOUtils.closeQuietly(pDAppearanceContentStream);
        }
    }
}
