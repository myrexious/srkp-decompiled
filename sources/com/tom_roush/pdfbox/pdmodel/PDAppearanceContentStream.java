package com.tom_roush.pdfbox.pdmodel;

import com.tom_roush.harmony.awt.AWTColor;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDInlineImage;
import com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import com.tom_roush.pdfbox.pdmodel.graphics.state.RenderingMode;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public final class PDAppearanceContentStream extends PDAbstractContentStream implements Closeable {
    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void addComment(String str) throws IOException {
        super.addComment(str);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void addRect(float f, float f2, float f3, float f4) throws IOException {
        super.addRect(f, f2, f3, f4);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void beginMarkedContent(COSName cOSName) throws IOException {
        super.beginMarkedContent(cOSName);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void beginMarkedContent(COSName cOSName, PDPropertyList pDPropertyList) throws IOException {
        super.beginMarkedContent(cOSName, pDPropertyList);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void beginText() throws IOException {
        super.beginText();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void clip() throws IOException {
        super.clip();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void clipEvenOdd() throws IOException {
        super.clipEvenOdd();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void closeAndFillAndStroke() throws IOException {
        super.closeAndFillAndStroke();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void closeAndFillAndStrokeEvenOdd() throws IOException {
        super.closeAndFillAndStrokeEvenOdd();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void closeAndStroke() throws IOException {
        super.closeAndStroke();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void closePath() throws IOException {
        super.closePath();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void curveTo(float f, float f2, float f3, float f4, float f5, float f6) throws IOException {
        super.curveTo(f, f2, f3, f4, f5, f6);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void curveTo1(float f, float f2, float f3, float f4) throws IOException {
        super.curveTo1(f, f2, f3, f4);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void curveTo2(float f, float f2, float f3, float f4) throws IOException {
        super.curveTo2(f, f2, f3, f4);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void drawForm(PDFormXObject pDFormXObject) throws IOException {
        super.drawForm(pDFormXObject);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void drawImage(PDImageXObject pDImageXObject, float f, float f2) throws IOException {
        super.drawImage(pDImageXObject, f, f2);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void drawImage(PDImageXObject pDImageXObject, float f, float f2, float f3, float f4) throws IOException {
        super.drawImage(pDImageXObject, f, f2, f3, f4);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void drawImage(PDImageXObject pDImageXObject, Matrix matrix) throws IOException {
        super.drawImage(pDImageXObject, matrix);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void drawImage(PDInlineImage pDInlineImage, float f, float f2) throws IOException {
        super.drawImage(pDInlineImage, f, f2);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void drawImage(PDInlineImage pDInlineImage, float f, float f2, float f3, float f4) throws IOException {
        super.drawImage(pDInlineImage, f, f2, f3, f4);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void endMarkedContent() throws IOException {
        super.endMarkedContent();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void endText() throws IOException {
        super.endText();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void fill() throws IOException {
        super.fill();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void fillAndStroke() throws IOException {
        super.fillAndStroke();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void fillAndStrokeEvenOdd() throws IOException {
        super.fillAndStrokeEvenOdd();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void fillEvenOdd() throws IOException {
        super.fillEvenOdd();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void lineTo(float f, float f2) throws IOException {
        super.lineTo(f, f2);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void moveTo(float f, float f2) throws IOException {
        super.moveTo(f, f2);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void newLine() throws IOException {
        super.newLine();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void newLineAtOffset(float f, float f2) throws IOException {
        super.newLineAtOffset(f, f2);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void restoreGraphicsState() throws IOException {
        super.restoreGraphicsState();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void saveGraphicsState() throws IOException {
        super.saveGraphicsState();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setCharacterSpacing(float f) throws IOException {
        super.setCharacterSpacing(f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setFont(PDFont pDFont, float f) throws IOException {
        super.setFont(pDFont, f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setGraphicsStateParameters(PDExtendedGraphicsState pDExtendedGraphicsState) throws IOException {
        super.setGraphicsStateParameters(pDExtendedGraphicsState);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setHorizontalScaling(float f) throws IOException {
        super.setHorizontalScaling(f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setLeading(float f) throws IOException {
        super.setLeading(f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setLineCapStyle(int i) throws IOException {
        super.setLineCapStyle(i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setLineDashPattern(float[] fArr, float f) throws IOException {
        super.setLineDashPattern(fArr, f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setLineJoinStyle(int i) throws IOException {
        super.setLineJoinStyle(i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setLineWidth(float f) throws IOException {
        super.setLineWidth(f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setMiterLimit(float f) throws IOException {
        super.setMiterLimit(f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setNonStrokingColor(float f) throws IOException {
        super.setNonStrokingColor(f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setNonStrokingColor(float f, float f2, float f3) throws IOException {
        super.setNonStrokingColor(f, f2, f3);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setNonStrokingColor(float f, float f2, float f3, float f4) throws IOException {
        super.setNonStrokingColor(f, f2, f3, f4);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setNonStrokingColor(int i) throws IOException {
        super.setNonStrokingColor(i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    @Deprecated
    public /* bridge */ /* synthetic */ void setNonStrokingColor(int i, int i2, int i3) throws IOException {
        super.setNonStrokingColor(i, i2, i3);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    @Deprecated
    public /* bridge */ /* synthetic */ void setNonStrokingColor(int i, int i2, int i3, int i4) throws IOException {
        super.setNonStrokingColor(i, i2, i3, i4);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setNonStrokingColor(AWTColor aWTColor) throws IOException {
        super.setNonStrokingColor(aWTColor);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setNonStrokingColor(PDColor pDColor) throws IOException {
        super.setNonStrokingColor(pDColor);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setRenderingMode(RenderingMode renderingMode) throws IOException {
        super.setRenderingMode(renderingMode);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setStrokingColor(float f) throws IOException {
        super.setStrokingColor(f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setStrokingColor(float f, float f2, float f3) throws IOException {
        super.setStrokingColor(f, f2, f3);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setStrokingColor(float f, float f2, float f3, float f4) throws IOException {
        super.setStrokingColor(f, f2, f3, f4);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    @Deprecated
    public /* bridge */ /* synthetic */ void setStrokingColor(int i, int i2, int i3) throws IOException {
        super.setStrokingColor(i, i2, i3);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setStrokingColor(AWTColor aWTColor) throws IOException {
        super.setStrokingColor(aWTColor);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setStrokingColor(PDColor pDColor) throws IOException {
        super.setStrokingColor(pDColor);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setTextMatrix(Matrix matrix) throws IOException {
        super.setTextMatrix(matrix);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setTextRise(float f) throws IOException {
        super.setTextRise(f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void setWordSpacing(float f) throws IOException {
        super.setWordSpacing(f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void shadingFill(PDShading pDShading) throws IOException {
        super.shadingFill(pDShading);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void showText(String str) throws IOException {
        super.showText(str);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void showTextWithPositioning(Object[] objArr) throws IOException {
        super.showTextWithPositioning(objArr);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void stroke() throws IOException {
        super.stroke();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.PDAbstractContentStream
    public /* bridge */ /* synthetic */ void transform(Matrix matrix) throws IOException {
        super.transform(matrix);
    }

    public PDAppearanceContentStream(PDAppearanceStream pDAppearanceStream) throws IOException {
        this(pDAppearanceStream, pDAppearanceStream.getStream().createOutputStream());
    }

    public PDAppearanceContentStream(PDAppearanceStream pDAppearanceStream, boolean z) throws IOException {
        this(pDAppearanceStream, pDAppearanceStream.getStream().createOutputStream(z ? COSName.FLATE_DECODE : null));
    }

    public PDAppearanceContentStream(PDAppearanceStream pDAppearanceStream, OutputStream outputStream) {
        super(null, outputStream, pDAppearanceStream.getResources());
    }

    public boolean setStrokingColorOnDemand(PDColor pDColor) throws IOException {
        if (pDColor != null) {
            float[] components = pDColor.getComponents();
            if (components.length > 0) {
                setStrokingColor(components);
                return true;
            }
            return false;
        }
        return false;
    }

    public void setStrokingColor(float[] fArr) throws IOException {
        for (float f : fArr) {
            writeOperand(f);
        }
        int length = fArr.length;
        if (length == 1) {
            writeOperator(OperatorName.STROKING_COLOR_GRAY);
        } else if (length == 3) {
            writeOperator(OperatorName.STROKING_COLOR_RGB);
        } else if (length == 4) {
            writeOperator("K");
        }
    }

    public boolean setNonStrokingColorOnDemand(PDColor pDColor) throws IOException {
        if (pDColor != null) {
            float[] components = pDColor.getComponents();
            if (components.length > 0) {
                setNonStrokingColor(components);
                return true;
            }
            return false;
        }
        return false;
    }

    public void setNonStrokingColor(float[] fArr) throws IOException {
        for (float f : fArr) {
            writeOperand(f);
        }
        int length = fArr.length;
        if (length == 1) {
            writeOperator(OperatorName.NON_STROKING_GRAY);
        } else if (length == 3) {
            writeOperator(OperatorName.NON_STROKING_RGB);
        } else if (length == 4) {
            writeOperator(OperatorName.NON_STROKING_CMYK);
        }
    }

    public void setBorderLine(float f, PDBorderStyleDictionary pDBorderStyleDictionary, COSArray cOSArray) throws IOException {
        if (pDBorderStyleDictionary != null && pDBorderStyleDictionary.getCOSObject().containsKey(COSName.D) && pDBorderStyleDictionary.getStyle().equals("D")) {
            setLineDashPattern(pDBorderStyleDictionary.getDashStyle().getDashArray(), 0.0f);
        } else if (pDBorderStyleDictionary == null && cOSArray.size() > 3) {
            if (cOSArray.getObject(3) instanceof COSArray) {
                setLineDashPattern(((COSArray) cOSArray.getObject(3)).toFloatArray(), 0.0f);
            } else {
                setLineDashPattern(new float[1], 0.0f);
            }
        }
        setLineWidthOnDemand(f);
    }

    public void setLineWidthOnDemand(float f) throws IOException {
        if (Math.abs(f - 1.0f) >= 1.0E-6d) {
            setLineWidth(f);
        }
    }

    public void drawShape(float f, boolean z, boolean z2) throws IOException {
        if (f < 1.0E-6d) {
            z = false;
        }
        if (z2 && z) {
            fillAndStroke();
        } else if (z) {
            stroke();
        } else if (z2) {
            fill();
        } else {
            writeOperator(OperatorName.ENDPATH);
        }
    }
}
