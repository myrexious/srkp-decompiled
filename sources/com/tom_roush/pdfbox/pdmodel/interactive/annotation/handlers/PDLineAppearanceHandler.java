package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import android.util.Log;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLine;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDLineAppearanceHandler extends PDAbstractAppearanceHandler {
    static final int FONT_SIZE = 9;

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDLineAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDLineAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r32v2 */
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateNormalAppearance() {
        float[] line;
        String str;
        float f;
        float f2;
        double d;
        float f3;
        boolean z;
        float f4;
        boolean z2;
        float f5;
        double d2;
        String str2;
        float f6;
        float f7;
        String str3 = "None";
        PDAnnotationLine pDAnnotationLine = (PDAnnotationLine) getAnnotation();
        PDRectangle rectangle = pDAnnotationLine.getRectangle();
        if (rectangle == null || (line = pDAnnotationLine.getLine()) == null) {
            return;
        }
        AnnotationBorder annotationBorder = AnnotationBorder.getAnnotationBorder(pDAnnotationLine, pDAnnotationLine.getBorderStyle());
        PDColor color = pDAnnotationLine.getColor();
        if (color == null || color.getComponents().length == 0) {
            return;
        }
        float leaderLineLength = pDAnnotationLine.getLeaderLineLength();
        float leaderLineExtensionLength = pDAnnotationLine.getLeaderLineExtensionLength();
        float leaderLineOffsetLength = pDAnnotationLine.getLeaderLineOffsetLength();
        float f8 = Float.MIN_VALUE;
        float f9 = Float.MAX_VALUE;
        float f10 = Float.MAX_VALUE;
        int i = 0;
        float f11 = Float.MIN_VALUE;
        while (i < line.length / 2) {
            int i2 = i * 2;
            String str4 = str3;
            float f12 = line[i2];
            float f13 = line[i2 + 1];
            f9 = Math.min(f9, f12);
            f10 = Math.min(f10, f13);
            f8 = Math.max(f8, f12);
            f11 = Math.max(f11, f13);
            i++;
            str3 = str4;
        }
        String str5 = str3;
        if (leaderLineLength < 0.0f) {
            leaderLineOffsetLength = -leaderLineOffsetLength;
            leaderLineExtensionLength = -leaderLineExtensionLength;
        }
        float f14 = ((double) annotationBorder.width) < 1.0E-5d ? 1.0f : annotationBorder.width;
        float f15 = leaderLineLength + leaderLineOffsetLength;
        float f16 = leaderLineExtensionLength + f15;
        float max = Math.max(10.0f * f14, Math.abs(f16));
        rectangle.setLowerLeftX(Math.min(f9 - max, rectangle.getLowerLeftX()));
        rectangle.setLowerLeftY(Math.min(f10 - max, rectangle.getLowerLeftY()));
        rectangle.setUpperRightX(Math.max(f8 + max, rectangle.getUpperRightX()));
        rectangle.setUpperRightY(Math.max(f11 + max, rectangle.getUpperRightY()));
        pDAnnotationLine.setRectangle(rectangle);
        PDAppearanceContentStream pDAppearanceContentStream = null;
        try {
            try {
                pDAppearanceContentStream = getNormalAppearanceAsContentStream();
                setOpacity(pDAppearanceContentStream, pDAnnotationLine.getConstantOpacity());
                boolean strokingColorOnDemand = pDAppearanceContentStream.setStrokingColorOnDemand(color);
                if (annotationBorder.dashArray != null) {
                    pDAppearanceContentStream.setLineDashPattern(annotationBorder.dashArray, 0.0f);
                }
                pDAppearanceContentStream.setLineWidth(annotationBorder.width);
                float f17 = line[0];
                float f18 = line[1];
                float f19 = line[2];
                float f20 = line[3];
                String contents = pDAnnotationLine.getContents();
                if (contents == null) {
                    contents = "";
                }
                pDAppearanceContentStream.saveGraphicsState();
                str = f15;
                double atan2 = Math.atan2(f20 - f18, f19 - f17);
                pDAppearanceContentStream.transform(Matrix.getRotateInstance(atan2, f17, f18));
                float sqrt = (float) Math.sqrt((f2 * f2) + (f * f));
                pDAppearanceContentStream.moveTo(0.0f, leaderLineOffsetLength);
                pDAppearanceContentStream.lineTo(0.0f, f16);
                pDAppearanceContentStream.moveTo(sqrt, leaderLineOffsetLength);
                pDAppearanceContentStream.lineTo(sqrt, f16);
                try {
                    if (pDAnnotationLine.getCaption() && !contents.isEmpty()) {
                        PDType1Font pDType1Font = PDType1Font.HELVETICA;
                        try {
                            f6 = (pDType1Font.getStringWidth(pDAnnotationLine.getContents()) / 1000.0f) * 9.0f;
                            str2 = "PdfBox-Android";
                        } catch (IllegalArgumentException e) {
                            str2 = "PdfBox-Android";
                            try {
                                Log.e(str2, "line text '" + pDAnnotationLine.getContents() + "' can't be shown", e);
                                f6 = 0.0f;
                            } catch (IOException e2) {
                                e = e2;
                                str = str2;
                                Log.e(str, e.getMessage(), e);
                            }
                        }
                        float f21 = (sqrt - f6) / 2.0f;
                        String captionPositioning = pDAnnotationLine.getCaptionPositioning();
                        if (SHORT_STYLES.contains(pDAnnotationLine.getStartPointEndingStyle())) {
                            f4 = str;
                            pDAppearanceContentStream.moveTo(f14, f4);
                        } else {
                            f4 = str;
                            pDAppearanceContentStream.moveTo(0.0f, f4);
                        }
                        if ("Top".equals(captionPositioning)) {
                            f7 = 1.908f;
                        } else {
                            pDAppearanceContentStream.lineTo(f21 - f14, f4);
                            pDAppearanceContentStream.moveTo((sqrt - f21) + f14, f4);
                            f7 = -2.6f;
                        }
                        if (SHORT_STYLES.contains(pDAnnotationLine.getEndPointEndingStyle())) {
                            pDAppearanceContentStream.lineTo(sqrt - f14, f4);
                        } else {
                            pDAppearanceContentStream.lineTo(sqrt, f4);
                        }
                        z = strokingColorOnDemand;
                        pDAppearanceContentStream.drawShape(f14, z, false);
                        float captionHorizontalOffset = pDAnnotationLine.getCaptionHorizontalOffset();
                        f3 = f19;
                        float captionVerticalOffset = pDAnnotationLine.getCaptionVerticalOffset();
                        if (f6 > 0.0f) {
                            pDAppearanceContentStream.beginText();
                            d = atan2;
                            pDAppearanceContentStream.setFont(pDType1Font, 9.0f);
                            pDAppearanceContentStream.newLineAtOffset(f21 + captionHorizontalOffset, f4 + f7 + captionVerticalOffset);
                            pDAppearanceContentStream.showText(pDAnnotationLine.getContents());
                            pDAppearanceContentStream.endText();
                        } else {
                            d = atan2;
                        }
                        if (Float.compare(captionVerticalOffset, 0.0f) != 0) {
                            float f22 = (sqrt / 2.0f) + 0.0f;
                            pDAppearanceContentStream.moveTo(f22, f4);
                            pDAppearanceContentStream.lineTo(f22, f4 + captionVerticalOffset);
                            pDAppearanceContentStream.drawShape(f14, z, false);
                        }
                        z2 = false;
                    } else {
                        d = atan2;
                        f3 = f19;
                        z = strokingColorOnDemand;
                        f4 = str;
                        if (SHORT_STYLES.contains(pDAnnotationLine.getStartPointEndingStyle())) {
                            pDAppearanceContentStream.moveTo(f14, f4);
                        } else {
                            pDAppearanceContentStream.moveTo(0.0f, f4);
                        }
                        if (SHORT_STYLES.contains(pDAnnotationLine.getEndPointEndingStyle())) {
                            pDAppearanceContentStream.lineTo(sqrt - f14, f4);
                        } else {
                            pDAppearanceContentStream.lineTo(sqrt, f4);
                        }
                        z2 = false;
                        pDAppearanceContentStream.drawShape(f14, z, false);
                    }
                    pDAppearanceContentStream.restoreGraphicsState();
                    boolean nonStrokingColorOnDemand = pDAppearanceContentStream.setNonStrokingColorOnDemand(pDAnnotationLine.getInteriorColor());
                    if (annotationBorder.width >= 1.0E-5d) {
                        z2 = z;
                    }
                    if (str5.equals(pDAnnotationLine.getStartPointEndingStyle())) {
                        f5 = f4;
                        d2 = d;
                    } else {
                        pDAppearanceContentStream.saveGraphicsState();
                        if (ANGLED_STYLES.contains(pDAnnotationLine.getStartPointEndingStyle())) {
                            d2 = d;
                            pDAppearanceContentStream.transform(Matrix.getRotateInstance(d2, f17, f18));
                            drawStyle(pDAnnotationLine.getStartPointEndingStyle(), pDAppearanceContentStream, 0.0f, f4, f14, z2, nonStrokingColorOnDemand, false);
                            f5 = f4;
                        } else {
                            d2 = d;
                            double d3 = f4;
                            f5 = f4;
                            drawStyle(pDAnnotationLine.getStartPointEndingStyle(), pDAppearanceContentStream, f17 - ((float) (d3 * Math.sin(d2))), f18 + ((float) (d3 * Math.cos(d2))), f14, z2, nonStrokingColorOnDemand, false);
                        }
                        pDAppearanceContentStream.restoreGraphicsState();
                    }
                    if (!str5.equals(pDAnnotationLine.getEndPointEndingStyle())) {
                        if (ANGLED_STYLES.contains(pDAnnotationLine.getEndPointEndingStyle())) {
                            pDAppearanceContentStream.transform(Matrix.getRotateInstance(d2, f3, f20));
                            drawStyle(pDAnnotationLine.getEndPointEndingStyle(), pDAppearanceContentStream, 0.0f, f5, f14, z2, nonStrokingColorOnDemand, true);
                        } else {
                            double d4 = f5;
                            drawStyle(pDAnnotationLine.getEndPointEndingStyle(), pDAppearanceContentStream, f3 - ((float) (Math.sin(d2) * d4)), f20 + ((float) (d4 * Math.cos(d2))), f14, z2, nonStrokingColorOnDemand, true);
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } finally {
                IOUtils.closeQuietly(null);
            }
        } catch (IOException e4) {
            e = e4;
            str = "PdfBox-Android";
        }
    }
}
