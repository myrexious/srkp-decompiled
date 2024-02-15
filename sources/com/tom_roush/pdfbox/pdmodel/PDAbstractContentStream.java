package com.tom_roush.pdfbox.pdmodel;

import android.util.Log;
import com.tom_roush.harmony.awt.AWTColor;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdfwriter.COSWriter;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDInlineImage;
import com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import com.tom_roush.pdfbox.pdmodel.graphics.state.RenderingMode;
import com.tom_roush.pdfbox.util.Charsets;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.NumberFormatUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class PDAbstractContentStream implements Closeable {
    protected final PDDocument document;
    private final byte[] formatBuffer;
    private final NumberFormat formatDecimal;
    protected final OutputStream outputStream;
    protected final PDResources resources;
    protected boolean inTextMode = false;
    protected final Deque<PDFont> fontStack = new ArrayDeque();
    protected final Deque<PDColorSpace> nonStrokingColorSpaceStack = new ArrayDeque();
    protected final Deque<PDColorSpace> strokingColorSpaceStack = new ArrayDeque();

    private boolean isOutsideOneInterval(double d) {
        return d < 0.0d || d > 1.0d;
    }

    protected boolean isOutside255Interval(int i) {
        return i < 0 || i > 255;
    }

    public PDAbstractContentStream(PDDocument pDDocument, OutputStream outputStream, PDResources pDResources) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.formatDecimal = numberInstance;
        this.formatBuffer = new byte[32];
        this.document = pDDocument;
        this.outputStream = outputStream;
        this.resources = pDResources;
        numberInstance.setMaximumFractionDigits(4);
        numberInstance.setGroupingUsed(false);
    }

    protected void setMaximumFractionDigits(int i) {
        this.formatDecimal.setMaximumFractionDigits(i);
    }

    public void beginText() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: Nested beginText() calls are not allowed.");
        }
        writeOperator(OperatorName.BEGIN_TEXT);
        this.inTextMode = true;
    }

    public void endText() throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Error: You must call beginText() before calling endText.");
        }
        writeOperator(OperatorName.END_TEXT);
        this.inTextMode = false;
    }

    public void setFont(PDFont pDFont, float f) throws IOException {
        if (this.fontStack.isEmpty()) {
            this.fontStack.add(pDFont);
        } else {
            this.fontStack.pop();
            this.fontStack.push(pDFont);
        }
        if (pDFont.willBeSubset()) {
            PDDocument pDDocument = this.document;
            if (pDDocument != null) {
                pDDocument.getFontsToSubset().add(pDFont);
            } else {
                Log.w("PdfBox-Android", "Using the subsetted font '" + pDFont.getName() + "' without a PDDocument context; call subset() before saving");
            }
        }
        writeOperand(this.resources.add(pDFont));
        writeOperand(f);
        writeOperator(OperatorName.SET_FONT_AND_SIZE);
    }

    public void showTextWithPositioning(Object[] objArr) throws IOException {
        write("[");
        for (Object obj : objArr) {
            if (obj instanceof String) {
                showTextInternal((String) obj);
            } else if (obj instanceof Float) {
                writeOperand(((Float) obj).floatValue());
            } else {
                throw new IllegalArgumentException("Argument must consist of array of Float and String types");
            }
        }
        write("] ");
        writeOperator(OperatorName.SHOW_TEXT_ADJUSTED);
    }

    public void showText(String str) throws IOException {
        showTextInternal(str);
        write(StringUtils.SPACE);
        writeOperator(OperatorName.SHOW_TEXT);
    }

    protected void showTextInternal(String str) throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Must call beginText() before showText()");
        }
        if (this.fontStack.isEmpty()) {
            throw new IllegalStateException("Must call setFont() before showText()");
        }
        PDFont peek = this.fontStack.peek();
        byte[] encode = peek.encode(str);
        if (peek.willBeSubset()) {
            int i = 0;
            while (i < str.length()) {
                int codePointAt = str.codePointAt(i);
                peek.addToSubset(codePointAt);
                i += Character.charCount(codePointAt);
            }
        }
        COSWriter.writeString(encode, this.outputStream);
    }

    public void setLeading(float f) throws IOException {
        writeOperand(f);
        writeOperator(OperatorName.SET_TEXT_LEADING);
    }

    public void newLine() throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Must call beginText() before newLine()");
        }
        writeOperator(OperatorName.NEXT_LINE);
    }

    public void newLineAtOffset(float f, float f2) throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Error: must call beginText() before newLineAtOffset()");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperator(OperatorName.MOVE_TEXT);
    }

    public void setTextMatrix(Matrix matrix) throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Error: must call beginText() before setTextMatrix");
        }
        writeAffineTransform(matrix.createAffineTransform());
        writeOperator(OperatorName.SET_MATRIX);
    }

    public void drawImage(PDImageXObject pDImageXObject, float f, float f2) throws IOException {
        drawImage(pDImageXObject, f, f2, pDImageXObject.getWidth(), pDImageXObject.getHeight());
    }

    public void drawImage(PDImageXObject pDImageXObject, float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
        }
        saveGraphicsState();
        transform(new Matrix(new AffineTransform(f3, 0.0f, 0.0f, f4, f, f2)));
        writeOperand(this.resources.add(pDImageXObject));
        writeOperator(OperatorName.DRAW_OBJECT);
        restoreGraphicsState();
    }

    public void drawImage(PDImageXObject pDImageXObject, Matrix matrix) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
        }
        saveGraphicsState();
        transform(new Matrix(matrix.createAffineTransform()));
        writeOperand(this.resources.add(pDImageXObject));
        writeOperator(OperatorName.DRAW_OBJECT);
        restoreGraphicsState();
    }

    public void drawImage(PDInlineImage pDInlineImage, float f, float f2) throws IOException {
        drawImage(pDInlineImage, f, f2, pDInlineImage.getWidth(), pDInlineImage.getHeight());
    }

    public void drawImage(PDInlineImage pDInlineImage, float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
        }
        saveGraphicsState();
        transform(new Matrix(f3, 0.0f, 0.0f, f4, f, f2));
        StringBuilder sb = new StringBuilder("BI\n /W ");
        sb.append(pDInlineImage.getWidth());
        sb.append("\n /H ");
        sb.append(pDInlineImage.getHeight());
        sb.append("\n /CS /");
        sb.append(pDInlineImage.getColorSpace().getName());
        COSArray decode = pDInlineImage.getDecode();
        if (decode != null && decode.size() > 0) {
            sb.append("\n /D [");
            Iterator<COSBase> it = decode.iterator();
            while (it.hasNext()) {
                sb.append(((COSNumber) it.next()).intValue());
                sb.append(StringUtils.SPACE);
            }
            sb.append("]");
        }
        if (pDInlineImage.isStencil()) {
            sb.append("\n /IM true");
        }
        sb.append("\n /BPC ");
        sb.append(pDInlineImage.getBitsPerComponent());
        write(sb.toString());
        writeLine();
        writeOperator(OperatorName.BEGIN_INLINE_IMAGE_DATA);
        writeBytes(pDInlineImage.getData());
        writeLine();
        writeOperator(OperatorName.END_INLINE_IMAGE);
        restoreGraphicsState();
    }

    public void drawForm(PDFormXObject pDFormXObject) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawForm is not allowed within a text block.");
        }
        writeOperand(this.resources.add(pDFormXObject));
        writeOperator(OperatorName.DRAW_OBJECT);
    }

    public void transform(Matrix matrix) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: Modifying the current transformation matrix is not allowed within text objects.");
        }
        writeAffineTransform(matrix.createAffineTransform());
        writeOperator(OperatorName.CONCAT);
    }

    public void saveGraphicsState() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: Saving the graphics state is not allowed within text objects.");
        }
        if (!this.fontStack.isEmpty()) {
            Deque<PDFont> deque = this.fontStack;
            deque.push(deque.peek());
        }
        if (!this.strokingColorSpaceStack.isEmpty()) {
            Deque<PDColorSpace> deque2 = this.strokingColorSpaceStack;
            deque2.push(deque2.peek());
        }
        if (!this.nonStrokingColorSpaceStack.isEmpty()) {
            Deque<PDColorSpace> deque3 = this.nonStrokingColorSpaceStack;
            deque3.push(deque3.peek());
        }
        writeOperator(OperatorName.SAVE);
    }

    public void restoreGraphicsState() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: Restoring the graphics state is not allowed within text objects.");
        }
        if (!this.fontStack.isEmpty()) {
            this.fontStack.pop();
        }
        if (!this.strokingColorSpaceStack.isEmpty()) {
            this.strokingColorSpaceStack.pop();
        }
        if (!this.nonStrokingColorSpaceStack.isEmpty()) {
            this.nonStrokingColorSpaceStack.pop();
        }
        writeOperator(OperatorName.RESTORE);
    }

    protected COSName getName(PDColorSpace pDColorSpace) {
        if ((pDColorSpace instanceof PDDeviceGray) || (pDColorSpace instanceof PDDeviceRGB)) {
            return COSName.getPDFName(pDColorSpace.getName());
        }
        return this.resources.add(pDColorSpace);
    }

    public void setStrokingColor(PDColor pDColor) throws IOException {
        if (this.strokingColorSpaceStack.isEmpty() || this.strokingColorSpaceStack.peek() != pDColor.getColorSpace()) {
            writeOperand(getName(pDColor.getColorSpace()));
            writeOperator(OperatorName.STROKING_COLORSPACE);
            setStrokingColorSpaceStack(pDColor.getColorSpace());
        }
        for (float f : pDColor.getComponents()) {
            writeOperand(f);
        }
        writeOperator(OperatorName.STROKING_COLOR);
    }

    public void setStrokingColor(AWTColor aWTColor) throws IOException {
        setStrokingColor(new PDColor(new float[]{aWTColor.getRed() / 255.0f, aWTColor.getGreen() / 255.0f, aWTColor.getBlue() / 255.0f}, PDDeviceRGB.INSTANCE));
    }

    public void setStrokingColor(float f, float f2, float f3) throws IOException {
        if (isOutsideOneInterval(f) || isOutsideOneInterval(f2) || isOutsideOneInterval(f3)) {
            throw new IllegalArgumentException("Parameters must be within 0..1, but are " + String.format("(%.2f,%.2f,%.2f)", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)));
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperator(OperatorName.STROKING_COLOR_RGB);
        setStrokingColorSpaceStack(PDDeviceRGB.INSTANCE);
    }

    @Deprecated
    public void setStrokingColor(int i, int i2, int i3) throws IOException {
        if (isOutside255Interval(i) || isOutside255Interval(i2) || isOutside255Interval(i3)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        setStrokingColor(i / 255.0f, i2 / 255.0f, i3 / 255.0f);
    }

    public void setStrokingColor(float f, float f2, float f3, float f4) throws IOException {
        if (isOutsideOneInterval(f) || isOutsideOneInterval(f2) || isOutsideOneInterval(f3) || isOutsideOneInterval(f4)) {
            throw new IllegalArgumentException("Parameters must be within 0..1, but are " + String.format("(%.2f,%.2f,%.2f,%.2f)", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4)));
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator("K");
    }

    public void setStrokingColor(float f) throws IOException {
        if (isOutsideOneInterval(f)) {
            throw new IllegalArgumentException("Parameter must be within 0..1, but is " + f);
        }
        writeOperand(f);
        writeOperator(OperatorName.STROKING_COLOR_GRAY);
        setStrokingColorSpaceStack(PDDeviceGray.INSTANCE);
    }

    public void setNonStrokingColor(PDColor pDColor) throws IOException {
        if (this.nonStrokingColorSpaceStack.isEmpty() || this.nonStrokingColorSpaceStack.peek() != pDColor.getColorSpace()) {
            writeOperand(getName(pDColor.getColorSpace()));
            writeOperator(OperatorName.NON_STROKING_COLORSPACE);
            setNonStrokingColorSpaceStack(pDColor.getColorSpace());
        }
        for (float f : pDColor.getComponents()) {
            writeOperand(f);
        }
        writeOperator(OperatorName.NON_STROKING_COLOR);
    }

    public void setNonStrokingColor(AWTColor aWTColor) throws IOException {
        setNonStrokingColor(new PDColor(new float[]{aWTColor.getRed() / 255.0f, aWTColor.getGreen() / 255.0f, aWTColor.getBlue() / 255.0f}, PDDeviceRGB.INSTANCE));
    }

    public void setNonStrokingColor(float f, float f2, float f3) throws IOException {
        if (isOutsideOneInterval(f) || isOutsideOneInterval(f2) || isOutsideOneInterval(f3)) {
            throw new IllegalArgumentException("Parameters must be within 0..1, but are " + String.format("(%.2f,%.2f,%.2f)", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)));
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperator(OperatorName.NON_STROKING_RGB);
        setNonStrokingColorSpaceStack(PDDeviceRGB.INSTANCE);
    }

    @Deprecated
    public void setNonStrokingColor(int i, int i2, int i3) throws IOException {
        if (isOutside255Interval(i) || isOutside255Interval(i2) || isOutside255Interval(i3)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        setNonStrokingColor(i / 255.0f, i2 / 255.0f, i3 / 255.0f);
    }

    @Deprecated
    public void setNonStrokingColor(int i, int i2, int i3, int i4) throws IOException {
        if (isOutside255Interval(i) || isOutside255Interval(i2) || isOutside255Interval(i3) || isOutside255Interval(i4)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        }
        setNonStrokingColor(i / 255.0f, i2 / 255.0f, i3 / 255.0f, i4 / 255.0f);
    }

    public void setNonStrokingColor(float f, float f2, float f3, float f4) throws IOException {
        if (isOutsideOneInterval(f) || isOutsideOneInterval(f2) || isOutsideOneInterval(f3) || isOutsideOneInterval(f4)) {
            throw new IllegalArgumentException("Parameters must be within 0..1, but are " + String.format("(%.2f,%.2f,%.2f,%.2f)", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4)));
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator(OperatorName.NON_STROKING_CMYK);
    }

    public void setNonStrokingColor(int i) throws IOException {
        if (isOutside255Interval(i)) {
            throw new IllegalArgumentException("Parameter must be within 0..255, but is " + i);
        }
        setNonStrokingColor(i / 255.0f);
    }

    public void setNonStrokingColor(float f) throws IOException {
        if (isOutsideOneInterval(f)) {
            throw new IllegalArgumentException("Parameter must be within 0..1, but is " + f);
        }
        writeOperand(f);
        writeOperator(OperatorName.NON_STROKING_GRAY);
        setNonStrokingColorSpaceStack(PDDeviceGray.INSTANCE);
    }

    public void addRect(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: addRect is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator(OperatorName.APPEND_RECT);
    }

    public void curveTo(float f, float f2, float f3, float f4, float f5, float f6) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: curveTo is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperand(f5);
        writeOperand(f6);
        writeOperator(OperatorName.CURVE_TO);
    }

    public void curveTo2(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: curveTo2 is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator(OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT);
    }

    public void curveTo1(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: curveTo1 is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator(OperatorName.CURVE_TO_REPLICATE_FINAL_POINT);
    }

    public void moveTo(float f, float f2) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: moveTo is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperator(OperatorName.MOVE_TO);
    }

    public void lineTo(float f, float f2) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: lineTo is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperator(OperatorName.LINE_TO);
    }

    public void stroke() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: stroke is not allowed within a text block.");
        }
        writeOperator("S");
    }

    public void closeAndStroke() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: closeAndStroke is not allowed within a text block.");
        }
        writeOperator(OperatorName.CLOSE_AND_STROKE);
    }

    public void fill() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fill is not allowed within a text block.");
        }
        writeOperator(OperatorName.FILL_NON_ZERO);
    }

    public void fillEvenOdd() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fillEvenOdd is not allowed within a text block.");
        }
        writeOperator(OperatorName.FILL_EVEN_ODD);
    }

    public void fillAndStroke() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fillAndStroke is not allowed within a text block.");
        }
        writeOperator("B");
    }

    public void fillAndStrokeEvenOdd() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fillAndStrokeEvenOdd is not allowed within a text block.");
        }
        writeOperator(OperatorName.FILL_EVEN_ODD_AND_STROKE);
    }

    public void closeAndFillAndStroke() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: closeAndFillAndStroke is not allowed within a text block.");
        }
        writeOperator(OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE);
    }

    public void closeAndFillAndStrokeEvenOdd() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: closeAndFillAndStrokeEvenOdd is not allowed within a text block.");
        }
        writeOperator(OperatorName.CLOSE_FILL_EVEN_ODD_AND_STROKE);
    }

    public void shadingFill(PDShading pDShading) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: shadingFill is not allowed within a text block.");
        }
        writeOperand(this.resources.add(pDShading));
        writeOperator(OperatorName.SHADING_FILL);
    }

    public void closePath() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: closePath is not allowed within a text block.");
        }
        writeOperator("h");
    }

    public void clip() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: clip is not allowed within a text block.");
        }
        writeOperator("W");
        writeOperator(OperatorName.ENDPATH);
    }

    public void clipEvenOdd() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: clipEvenOdd is not allowed within a text block.");
        }
        writeOperator(OperatorName.CLIP_EVEN_ODD);
        writeOperator(OperatorName.ENDPATH);
    }

    public void setLineWidth(float f) throws IOException {
        writeOperand(f);
        writeOperator("w");
    }

    public void setLineJoinStyle(int i) throws IOException {
        if (i >= 0 && i <= 2) {
            writeOperand(i);
            writeOperator(OperatorName.SET_LINE_JOINSTYLE);
            return;
        }
        throw new IllegalArgumentException("Error: unknown value for line join style");
    }

    public void setLineCapStyle(int i) throws IOException {
        if (i >= 0 && i <= 2) {
            writeOperand(i);
            writeOperator(OperatorName.SET_LINE_CAPSTYLE);
            return;
        }
        throw new IllegalArgumentException("Error: unknown value for line cap style");
    }

    public void setLineDashPattern(float[] fArr, float f) throws IOException {
        write("[");
        for (float f2 : fArr) {
            writeOperand(f2);
        }
        write("] ");
        writeOperand(f);
        writeOperator(OperatorName.SET_LINE_DASHPATTERN);
    }

    public void setMiterLimit(float f) throws IOException {
        if (f <= 0.0d) {
            throw new IllegalArgumentException("A miter limit <= 0 is invalid and will not render in Acrobat Reader");
        }
        writeOperand(f);
        writeOperator("M");
    }

    public void beginMarkedContent(COSName cOSName) throws IOException {
        writeOperand(cOSName);
        writeOperator(OperatorName.BEGIN_MARKED_CONTENT);
    }

    public void beginMarkedContent(COSName cOSName, PDPropertyList pDPropertyList) throws IOException {
        writeOperand(cOSName);
        writeOperand(this.resources.add(pDPropertyList));
        writeOperator(OperatorName.BEGIN_MARKED_CONTENT_SEQ);
    }

    public void endMarkedContent() throws IOException {
        writeOperator(OperatorName.END_MARKED_CONTENT);
    }

    public void setGraphicsStateParameters(PDExtendedGraphicsState pDExtendedGraphicsState) throws IOException {
        writeOperand(this.resources.add(pDExtendedGraphicsState));
        writeOperator(OperatorName.SET_GRAPHICS_STATE_PARAMS);
    }

    public void addComment(String str) throws IOException {
        if (str.indexOf(10) >= 0 || str.indexOf(13) >= 0) {
            throw new IllegalArgumentException("comment should not include a newline");
        }
        this.outputStream.write(37);
        this.outputStream.write(str.getBytes(Charsets.US_ASCII));
        this.outputStream.write(10);
    }

    public void writeOperand(float f) throws IOException {
        if (Float.isInfinite(f) || Float.isNaN(f)) {
            throw new IllegalArgumentException(f + " is not a finite number");
        }
        int formatFloatFast = NumberFormatUtil.formatFloatFast(f, this.formatDecimal.getMaximumFractionDigits(), this.formatBuffer);
        if (formatFloatFast == -1) {
            write(this.formatDecimal.format(f));
        } else {
            this.outputStream.write(this.formatBuffer, 0, formatFloatFast);
        }
        this.outputStream.write(32);
    }

    protected void writeOperand(int i) throws IOException {
        write(this.formatDecimal.format(i));
        this.outputStream.write(32);
    }

    protected void writeOperand(COSName cOSName) throws IOException {
        cOSName.writePDF(this.outputStream);
        this.outputStream.write(32);
    }

    public void writeOperator(String str) throws IOException {
        this.outputStream.write(str.getBytes(Charsets.US_ASCII));
        this.outputStream.write(10);
    }

    protected void write(String str) throws IOException {
        this.outputStream.write(str.getBytes(Charsets.US_ASCII));
    }

    protected void write(byte[] bArr) throws IOException {
        this.outputStream.write(bArr);
    }

    protected void writeLine() throws IOException {
        this.outputStream.write(10);
    }

    protected void writeBytes(byte[] bArr) throws IOException {
        this.outputStream.write(bArr);
    }

    private void writeAffineTransform(AffineTransform affineTransform) throws IOException {
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        for (int i = 0; i < 6; i++) {
            writeOperand((float) dArr[i]);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.inTextMode) {
            Log.w("PdfBox-Android", "You did not call endText(), some viewers won't display your text");
        }
        this.outputStream.close();
    }

    protected void setStrokingColorSpaceStack(PDColorSpace pDColorSpace) {
        if (this.strokingColorSpaceStack.isEmpty()) {
            this.strokingColorSpaceStack.add(pDColorSpace);
            return;
        }
        this.strokingColorSpaceStack.pop();
        this.strokingColorSpaceStack.push(pDColorSpace);
    }

    protected void setNonStrokingColorSpaceStack(PDColorSpace pDColorSpace) {
        if (this.nonStrokingColorSpaceStack.isEmpty()) {
            this.nonStrokingColorSpaceStack.add(pDColorSpace);
            return;
        }
        this.nonStrokingColorSpaceStack.pop();
        this.nonStrokingColorSpaceStack.push(pDColorSpace);
    }

    public void setCharacterSpacing(float f) throws IOException {
        writeOperand(f);
        writeOperator(OperatorName.SET_CHAR_SPACING);
    }

    public void setWordSpacing(float f) throws IOException {
        writeOperand(f);
        writeOperator(OperatorName.SET_WORD_SPACING);
    }

    public void setHorizontalScaling(float f) throws IOException {
        writeOperand(f);
        writeOperator(OperatorName.SET_TEXT_HORIZONTAL_SCALING);
    }

    public void setRenderingMode(RenderingMode renderingMode) throws IOException {
        writeOperand(renderingMode.intValue());
        writeOperator(OperatorName.SET_TEXT_RENDERINGMODE);
    }

    public void setTextRise(float f) throws IOException {
        writeOperand(f);
        writeOperator(OperatorName.SET_TEXT_RISE);
    }
}
