package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import android.util.Log;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationFileAttachment;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDFileAttachmentAppearanceHandler extends PDAbstractAppearanceHandler {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDFileAttachmentAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDFileAttachmentAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateNormalAppearance() {
        PDAnnotationFileAttachment pDAnnotationFileAttachment = (PDAnnotationFileAttachment) getAnnotation();
        PDAppearanceContentStream pDAppearanceContentStream = null;
        try {
            try {
                pDAppearanceContentStream = getNormalAppearanceAsContentStream();
                setOpacity(pDAppearanceContentStream, pDAnnotationFileAttachment.getConstantOpacity());
                PDRectangle rectangle = getRectangle();
                float f = 18;
                rectangle.setUpperRightX(rectangle.getLowerLeftX() + f);
                rectangle.setLowerLeftY(rectangle.getUpperRightY() - f);
                pDAnnotationFileAttachment.setRectangle(rectangle);
                pDAnnotationFileAttachment.getNormalAppearanceStream().setBBox(new PDRectangle(f, f));
                drawPaperclip(pDAppearanceContentStream);
            } catch (IOException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
            }
        } finally {
            IOUtils.closeQuietly(pDAppearanceContentStream);
        }
    }

    private void drawPaperclip(PDAppearanceContentStream pDAppearanceContentStream) throws IOException {
        pDAppearanceContentStream.moveTo(13.574f, 9.301f);
        pDAppearanceContentStream.lineTo(8.926f, 13.949f);
        pDAppearanceContentStream.curveTo(7.648f, 15.227f, 5.625f, 15.227f, 4.426f, 13.949f);
        pDAppearanceContentStream.curveTo(3.148f, 12.676f, 3.148f, 10.648f, 4.426f, 9.449f);
        pDAppearanceContentStream.lineTo(10.426f, 3.449f);
        pDAppearanceContentStream.curveTo(11.176f, 2.773f, 12.301f, 2.773f, 13.051f, 3.449f);
        pDAppearanceContentStream.curveTo(13.801f, 4.199f, 13.801f, 5.398f, 13.051f, 6.074f);
        pDAppearanceContentStream.lineTo(7.875f, 11.25f);
        pDAppearanceContentStream.curveTo(7.648f, 11.477f, 7.273f, 11.477f, 7.051f, 11.25f);
        pDAppearanceContentStream.curveTo(6.824f, 11.023f, 6.824f, 10.648f, 7.051f, 10.426f);
        pDAppearanceContentStream.lineTo(10.875f, 6.602f);
        pDAppearanceContentStream.curveTo(11.176f, 6.301f, 11.176f, 5.852f, 10.875f, 5.551f);
        pDAppearanceContentStream.curveTo(10.574f, 5.25f, 10.125f, 5.25f, 9.824f, 5.551f);
        pDAppearanceContentStream.lineTo(6.0f, 9.449f);
        pDAppearanceContentStream.curveTo(5.176f, 10.273f, 5.176f, 11.551f, 6.0f, 12.375f);
        pDAppearanceContentStream.curveTo(6.824f, 13.125f, 8.102f, 13.125f, 8.926f, 12.375f);
        pDAppearanceContentStream.lineTo(14.102f, 7.199f);
        pDAppearanceContentStream.curveTo(15.449f, 5.852f, 15.449f, 3.75f, 14.102f, 2.398f);
        pDAppearanceContentStream.curveTo(12.75f, 1.051f, 10.648f, 1.051f, 9.301f, 2.398f);
        pDAppearanceContentStream.lineTo(3.301f, 8.398f);
        pDAppearanceContentStream.curveTo(2.398f, 9.301f, 1.949f, 10.5f, 1.949f, 11.699f);
        pDAppearanceContentStream.curveTo(1.949f, 14.324f, 4.051f, 16.352f, 6.676f, 16.352f);
        pDAppearanceContentStream.curveTo(7.949f, 16.352f, 9.074f, 15.824f, 9.977f, 15.0f);
        pDAppearanceContentStream.lineTo(14.625f, 10.352f);
        pDAppearanceContentStream.curveTo(14.926f, 10.051f, 14.926f, 9.602f, 14.625f, 9.301f);
        pDAppearanceContentStream.curveTo(14.324f, 9.0f, 13.875f, 9.0f, 13.574f, 9.301f);
        pDAppearanceContentStream.closePath();
        pDAppearanceContentStream.fill();
    }
}
