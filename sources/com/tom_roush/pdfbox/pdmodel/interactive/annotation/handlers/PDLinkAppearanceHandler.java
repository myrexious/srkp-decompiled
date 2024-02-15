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
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDLinkAppearanceHandler extends PDAbstractAppearanceHandler {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateDownAppearance() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateRolloverAppearance() {
    }

    public PDLinkAppearanceHandler(PDAnnotation pDAnnotation) {
        super(pDAnnotation);
    }

    public PDLinkAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        super(pDAnnotation, pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateNormalAppearance() {
        PDAppearanceContentStream pDAppearanceContentStream;
        boolean strokingColorOnDemand;
        float[] fArr;
        PDBorderStyleDictionary borderStyle;
        int i;
        PDAnnotationLink pDAnnotationLink = (PDAnnotationLink) getAnnotation();
        PDRectangle rectangle = pDAnnotationLink.getRectangle();
        if (rectangle == null) {
            return;
        }
        float lineWidth = getLineWidth();
        try {
            PDAppearanceContentStream normalAppearanceAsContentStream = getNormalAppearanceAsContentStream();
            try {
                PDColor color = pDAnnotationLink.getColor();
                if (color == null) {
                    color = new PDColor(new float[]{0.0f}, PDDeviceGray.INSTANCE);
                }
                strokingColorOnDemand = normalAppearanceAsContentStream.setStrokingColorOnDemand(color);
                normalAppearanceAsContentStream.setBorderLine(lineWidth, pDAnnotationLink.getBorderStyle(), pDAnnotationLink.getBorder());
            } catch (IOException e) {
                e = e;
            } catch (Throwable th) {
                th = th;
            }
            try {
                PDRectangle paddedRectangle = getPaddedRectangle(getRectangle(), lineWidth / 2.0f);
                float[] quadPoints = pDAnnotationLink.getQuadPoints();
                if (quadPoints != null) {
                    for (int i2 = 0; i2 < quadPoints.length / 2; i2++) {
                        int i3 = i2 * 2;
                        if (!rectangle.contains(quadPoints[i3], quadPoints[i3 + 1])) {
                            Log.w("PdfBox-Android", "At least one /QuadPoints entry (" + quadPoints[i3] + ";" + quadPoints[i] + ") is outside of rectangle, " + rectangle + ", /QuadPoints are ignored and /Rect is used instead");
                            fArr = null;
                            break;
                        }
                    }
                }
                fArr = quadPoints;
                if (fArr == null) {
                    fArr = new float[]{paddedRectangle.getLowerLeftX(), paddedRectangle.getLowerLeftY(), paddedRectangle.getUpperRightX(), paddedRectangle.getLowerLeftY(), paddedRectangle.getUpperRightX(), paddedRectangle.getUpperRightY(), paddedRectangle.getLowerLeftX(), paddedRectangle.getUpperRightY()};
                }
                boolean equals = (fArr.length < 8 || (borderStyle = pDAnnotationLink.getBorderStyle()) == null) ? false : "U".equals(borderStyle.getStyle());
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 7;
                    if (i5 < fArr.length) {
                        normalAppearanceAsContentStream.moveTo(fArr[i4], fArr[i4 + 1]);
                        normalAppearanceAsContentStream.lineTo(fArr[i4 + 2], fArr[i4 + 3]);
                        if (!equals) {
                            normalAppearanceAsContentStream.lineTo(fArr[i4 + 4], fArr[i4 + 5]);
                            normalAppearanceAsContentStream.lineTo(fArr[i4 + 6], fArr[i5]);
                            normalAppearanceAsContentStream.closePath();
                        }
                        i4 += 8;
                    } else {
                        normalAppearanceAsContentStream.drawShape(lineWidth, strokingColorOnDemand, false);
                        IOUtils.closeQuietly(normalAppearanceAsContentStream);
                        return;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                pDAppearanceContentStream = normalAppearanceAsContentStream;
                try {
                    Log.e("PdfBox-Android", e.getMessage(), e);
                    IOUtils.closeQuietly(pDAppearanceContentStream);
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.closeQuietly(pDAppearanceContentStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                pDAppearanceContentStream = normalAppearanceAsContentStream;
                IOUtils.closeQuietly(pDAppearanceContentStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            pDAppearanceContentStream = null;
        } catch (Throwable th4) {
            th = th4;
            pDAppearanceContentStream = null;
        }
    }

    float getLineWidth() {
        PDAnnotationLink pDAnnotationLink = (PDAnnotationLink) getAnnotation();
        PDBorderStyleDictionary borderStyle = pDAnnotationLink.getBorderStyle();
        if (borderStyle != null) {
            return borderStyle.getWidth();
        }
        COSArray border = pDAnnotationLink.getBorder();
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
