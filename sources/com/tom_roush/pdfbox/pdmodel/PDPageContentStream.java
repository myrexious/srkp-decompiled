package com.tom_roush.pdfbox.pdmodel;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.harmony.awt.AWTColor;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdfwriter.COSWriter;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDInlineImage;
import com.tom_roush.pdfbox.pdmodel.graphics.pattern.PDTilingPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import com.tom_roush.pdfbox.pdmodel.graphics.state.RenderingMode;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.util.Charsets;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.NumberFormatUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Stack;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public final class PDPageContentStream implements Closeable {
    private final PDDocument document;
    private final Stack<PDFont> fontStack;
    private final byte[] formatBuffer;
    private final NumberFormat formatDecimal;
    private boolean inTextMode;
    private final Stack<PDColorSpace> nonStrokingColorSpaceStack;
    private OutputStream output;
    private PDResources resources;
    private boolean sourcePageHadContents;
    private final Stack<PDColorSpace> strokingColorSpaceStack;

    private boolean isOutside255Interval(int i) {
        return i < 0 || i > 255;
    }

    private boolean isOutsideOneInterval(double d) {
        return d < 0.0d || d > 1.0d;
    }

    /* loaded from: classes3.dex */
    public enum AppendMode {
        OVERWRITE,
        APPEND,
        PREPEND;

        public boolean isOverwrite() {
            return this == OVERWRITE;
        }

        public boolean isPrepend() {
            return this == PREPEND;
        }
    }

    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage) throws IOException {
        this(pDDocument, pDPage, AppendMode.OVERWRITE, true, false);
        if (this.sourcePageHadContents) {
            Log.w("PdfBox-Android", "You are overwriting an existing content, you should use the append mode");
        }
    }

    @Deprecated
    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage, boolean z, boolean z2) throws IOException {
        this(pDDocument, pDPage, z, z2, false);
    }

    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage, AppendMode appendMode, boolean z) throws IOException {
        this(pDDocument, pDPage, appendMode, z, false);
    }

    @Deprecated
    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage, boolean z, boolean z2, boolean z3) throws IOException {
        this(pDDocument, pDPage, z ? AppendMode.APPEND : AppendMode.OVERWRITE, z2, z3);
    }

    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage, AppendMode appendMode, boolean z, boolean z2) throws IOException {
        COSArray cOSArray;
        this.inTextMode = false;
        this.fontStack = new Stack<>();
        this.nonStrokingColorSpaceStack = new Stack<>();
        this.strokingColorSpaceStack = new Stack<>();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.formatDecimal = numberInstance;
        this.formatBuffer = new byte[32];
        this.sourcePageHadContents = false;
        this.document = pDDocument;
        COSName cOSName = z ? COSName.FLATE_DECODE : null;
        if (!appendMode.isOverwrite() && pDPage.hasContents()) {
            PDStream pDStream = new PDStream(pDDocument);
            COSBase dictionaryObject = pDPage.getCOSObject().getDictionaryObject(COSName.CONTENTS);
            if (dictionaryObject instanceof COSArray) {
                cOSArray = (COSArray) dictionaryObject;
            } else {
                COSArray cOSArray2 = new COSArray();
                cOSArray2.add(dictionaryObject);
                cOSArray = cOSArray2;
            }
            if (appendMode.isPrepend()) {
                cOSArray.add(0, pDStream.getCOSObject());
            } else {
                cOSArray.add(pDStream);
            }
            if (z2) {
                PDStream pDStream2 = new PDStream(pDDocument);
                this.output = pDStream2.createOutputStream(cOSName);
                saveGraphicsState();
                close();
                cOSArray.add(0, pDStream2.getCOSObject());
            }
            pDPage.getCOSObject().setItem(COSName.CONTENTS, (COSBase) cOSArray);
            this.output = pDStream.createOutputStream(cOSName);
            if (z2) {
                restoreGraphicsState();
            }
        } else {
            this.sourcePageHadContents = pDPage.hasContents();
            PDStream pDStream3 = new PDStream(pDDocument);
            pDPage.setContents(pDStream3);
            this.output = pDStream3.createOutputStream(cOSName);
        }
        PDResources resources = pDPage.getResources();
        this.resources = resources;
        if (resources == null) {
            PDResources pDResources = new PDResources();
            this.resources = pDResources;
            pDPage.setResources(pDResources);
        }
        numberInstance.setMaximumFractionDigits(5);
        numberInstance.setGroupingUsed(false);
    }

    public PDPageContentStream(PDDocument pDDocument, PDAppearanceStream pDAppearanceStream) throws IOException {
        this(pDDocument, pDAppearanceStream, pDAppearanceStream.getStream().createOutputStream());
    }

    public PDPageContentStream(PDDocument pDDocument, PDAppearanceStream pDAppearanceStream, OutputStream outputStream) throws IOException {
        this.inTextMode = false;
        this.fontStack = new Stack<>();
        this.nonStrokingColorSpaceStack = new Stack<>();
        this.strokingColorSpaceStack = new Stack<>();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.formatDecimal = numberInstance;
        this.formatBuffer = new byte[32];
        this.sourcePageHadContents = false;
        this.document = pDDocument;
        this.output = outputStream;
        this.resources = pDAppearanceStream.getResources();
        numberInstance.setMaximumFractionDigits(4);
        numberInstance.setGroupingUsed(false);
    }

    public PDPageContentStream(PDDocument pDDocument, PDFormXObject pDFormXObject, OutputStream outputStream) throws IOException {
        this.inTextMode = false;
        this.fontStack = new Stack<>();
        this.nonStrokingColorSpaceStack = new Stack<>();
        this.strokingColorSpaceStack = new Stack<>();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.formatDecimal = numberInstance;
        this.formatBuffer = new byte[32];
        this.sourcePageHadContents = false;
        this.document = pDDocument;
        this.output = outputStream;
        this.resources = pDFormXObject.getResources();
        numberInstance.setMaximumFractionDigits(4);
        numberInstance.setGroupingUsed(false);
    }

    public PDPageContentStream(PDDocument pDDocument, PDTilingPattern pDTilingPattern, OutputStream outputStream) throws IOException {
        this.inTextMode = false;
        this.fontStack = new Stack<>();
        this.nonStrokingColorSpaceStack = new Stack<>();
        this.strokingColorSpaceStack = new Stack<>();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.formatDecimal = numberInstance;
        this.formatBuffer = new byte[32];
        this.sourcePageHadContents = false;
        this.document = pDDocument;
        this.output = outputStream;
        this.resources = pDTilingPattern.getResources();
        numberInstance.setMaximumFractionDigits(4);
        numberInstance.setGroupingUsed(false);
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
            Stack<PDFont> stack = this.fontStack;
            stack.setElementAt(pDFont, stack.size() - 1);
        }
        if (pDFont.willBeSubset()) {
            this.document.getFontsToSubset().add(pDFont);
        }
        writeOperand(this.resources.add(pDFont));
        writeOperand(f);
        writeOperator(OperatorName.SET_FONT_AND_SIZE);
    }

    @Deprecated
    public void drawString(String str) throws IOException {
        showText(str);
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
        if (peek.willBeSubset()) {
            int i = 0;
            while (i < str.length()) {
                int codePointAt = str.codePointAt(i);
                peek.addToSubset(codePointAt);
                i += Character.charCount(codePointAt);
            }
        }
        COSWriter.writeString(peek.encode(str), this.output);
    }

    @Deprecated
    public void setLeading(double d) throws IOException {
        setLeading((float) d);
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

    @Deprecated
    public void moveTextPositionByAmount(float f, float f2) throws IOException {
        newLineAtOffset(f, f2);
    }

    public void newLineAtOffset(float f, float f2) throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Error: must call beginText() before newLineAtOffset()");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperator(OperatorName.MOVE_TEXT);
    }

    @Deprecated
    public void setTextMatrix(double d, double d2, double d3, double d4, double d5, double d6) throws IOException {
        setTextMatrix(new Matrix((float) d, (float) d2, (float) d3, (float) d4, (float) d5, (float) d6));
    }

    @Deprecated
    public void setTextMatrix(AffineTransform affineTransform) throws IOException {
        setTextMatrix(new Matrix(affineTransform));
    }

    public void setTextMatrix(Matrix matrix) throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Error: must call beginText() before setTextMatrix");
        }
        writeAffineTransform(matrix.createAffineTransform());
        writeOperator(OperatorName.SET_MATRIX);
    }

    @Deprecated
    public void setTextScaling(double d, double d2, double d3, double d4) throws IOException {
        setTextMatrix(new Matrix((float) d, 0.0f, 0.0f, (float) d2, (float) d3, (float) d4));
    }

    @Deprecated
    public void setTextTranslation(double d, double d2) throws IOException {
        setTextMatrix(Matrix.getTranslateInstance((float) d, (float) d2));
    }

    @Deprecated
    public void setTextRotation(double d, double d2, double d3) throws IOException {
        setTextMatrix(Matrix.getRotateInstance(d, (float) d2, (float) d3));
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

    @Deprecated
    public void drawInlineImage(PDInlineImage pDInlineImage, float f, float f2) throws IOException {
        drawImage(pDInlineImage, f, f2, pDInlineImage.getWidth(), pDInlineImage.getHeight());
    }

    public void drawImage(PDInlineImage pDInlineImage, float f, float f2) throws IOException {
        drawImage(pDInlineImage, f, f2, pDInlineImage.getWidth(), pDInlineImage.getHeight());
    }

    @Deprecated
    public void drawInlineImage(PDInlineImage pDInlineImage, float f, float f2, float f3, float f4) throws IOException {
        drawImage(pDInlineImage, f, f2, f3, f4);
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

    @Deprecated
    public void drawXObject(PDXObject pDXObject, float f, float f2, float f3, float f4) throws IOException {
        drawXObject(pDXObject, new AffineTransform(f3, 0.0f, 0.0f, f4, f, f2));
    }

    @Deprecated
    public void drawXObject(PDXObject pDXObject, AffineTransform affineTransform) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawXObject is not allowed within a text block.");
        }
        COSName add = this.resources.add(pDXObject, pDXObject instanceof PDImageXObject ? "Im" : StandardStructureTypes.FORM);
        saveGraphicsState();
        transform(new Matrix(affineTransform));
        writeOperand(add);
        writeOperator(OperatorName.DRAW_OBJECT);
        restoreGraphicsState();
    }

    public void drawForm(PDFormXObject pDFormXObject) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawForm is not allowed within a text block.");
        }
        writeOperand(this.resources.add(pDFormXObject));
        writeOperator(OperatorName.DRAW_OBJECT);
    }

    @Deprecated
    public void concatenate2CTM(double d, double d2, double d3, double d4, double d5, double d6) throws IOException {
        transform(new Matrix((float) d, (float) d2, (float) d3, (float) d4, (float) d5, (float) d6));
    }

    @Deprecated
    public void concatenate2CTM(AffineTransform affineTransform) throws IOException {
        transform(new Matrix(affineTransform));
    }

    public void transform(Matrix matrix) throws IOException {
        if (this.inTextMode) {
            Log.w("PdfBox-Android", "Modifying the current transformation matrix is not allowed within text objects.");
        }
        writeAffineTransform(matrix.createAffineTransform());
        writeOperator(OperatorName.CONCAT);
    }

    public void saveGraphicsState() throws IOException {
        if (this.inTextMode) {
            Log.w("PdfBox-Android", "Saving the graphics state is not allowed within text objects.");
        }
        if (!this.fontStack.isEmpty()) {
            Stack<PDFont> stack = this.fontStack;
            stack.push(stack.peek());
        }
        if (!this.strokingColorSpaceStack.isEmpty()) {
            Stack<PDColorSpace> stack2 = this.strokingColorSpaceStack;
            stack2.push(stack2.peek());
        }
        if (!this.nonStrokingColorSpaceStack.isEmpty()) {
            Stack<PDColorSpace> stack3 = this.nonStrokingColorSpaceStack;
            stack3.push(stack3.peek());
        }
        writeOperator(OperatorName.SAVE);
    }

    public void restoreGraphicsState() throws IOException {
        if (this.inTextMode) {
            Log.w("PdfBox-Android", "Restoring the graphics state is not allowed within text objects.");
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

    @Deprecated
    public void setStrokingColorSpace(PDColorSpace pDColorSpace) throws IOException {
        setStrokingColorSpaceStack(pDColorSpace);
        writeOperand(getName(pDColorSpace));
        writeOperator(OperatorName.STROKING_COLORSPACE);
    }

    @Deprecated
    public void setNonStrokingColorSpace(PDColorSpace pDColorSpace) throws IOException {
        setNonStrokingColorSpaceStack(pDColorSpace);
        writeOperand(getName(pDColorSpace));
        writeOperator(OperatorName.NON_STROKING_COLORSPACE);
    }

    private COSName getName(PDColorSpace pDColorSpace) throws IOException {
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

    @Deprecated
    public void setStrokingColor(float[] fArr) throws IOException {
        if (this.strokingColorSpaceStack.isEmpty()) {
            throw new IllegalStateException("The color space must be set before setting a color");
        }
        for (float f : fArr) {
            writeOperand(f);
        }
        this.strokingColorSpaceStack.peek();
        writeOperator(OperatorName.STROKING_COLOR);
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

    @Deprecated
    public void setStrokingColor(int i, int i2, int i3, int i4) throws IOException {
        if (isOutside255Interval(i) || isOutside255Interval(i2) || isOutside255Interval(i3) || isOutside255Interval(i4)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        }
        setStrokingColor(i / 255.0f, i2 / 255.0f, i3 / 255.0f, i4 / 255.0f);
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

    @Deprecated
    public void setStrokingColor(int i) throws IOException {
        if (isOutside255Interval(i)) {
            throw new IllegalArgumentException("Parameter must be within 0..255, but is " + i);
        }
        setStrokingColor(i / 255.0f);
    }

    @Deprecated
    public void setStrokingColor(double d) throws IOException {
        setStrokingColor((float) d);
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

    @Deprecated
    public void setNonStrokingColor(float[] fArr) throws IOException {
        if (this.nonStrokingColorSpaceStack.isEmpty()) {
            throw new IllegalStateException("The color space must be set before setting a color");
        }
        for (float f : fArr) {
            writeOperand(f);
        }
        this.nonStrokingColorSpaceStack.peek();
        writeOperator(OperatorName.NON_STROKING_COLOR);
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

    public void setNonStrokingColor(int i, int i2, int i3, int i4) throws IOException {
        if (isOutside255Interval(i) || isOutside255Interval(i2) || isOutside255Interval(i3) || isOutside255Interval(i4)) {
            throw new IllegalArgumentException("Parameters must be within 0..255, but are " + String.format("(%d,%d,%d,%d)", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        }
        setNonStrokingColor(i / 255.0f, i2 / 255.0f, i3 / 255.0f, i4 / 255.0f);
    }

    @Deprecated
    public void setNonStrokingColor(double d, double d2, double d3, double d4) throws IOException {
        setNonStrokingColor((float) d, (float) d2, (float) d3, (float) d4);
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

    @Deprecated
    public void setNonStrokingColor(double d) throws IOException {
        setNonStrokingColor((float) d);
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

    @Deprecated
    public void fillRect(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fillRect is not allowed within a text block.");
        }
        addRect(f, f2, f3, f4);
        fill();
    }

    @Deprecated
    public void addBezier312(float f, float f2, float f3, float f4, float f5, float f6) throws IOException {
        curveTo(f, f2, f3, f4, f5, f6);
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

    @Deprecated
    public void addBezier32(float f, float f2, float f3, float f4) throws IOException {
        curveTo2(f, f2, f3, f4);
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

    @Deprecated
    public void addBezier31(float f, float f2, float f3, float f4) throws IOException {
        curveTo1(f, f2, f3, f4);
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

    @Deprecated
    public void addLine(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: addLine is not allowed within a text block.");
        }
        moveTo(f, f2);
        lineTo(f3, f4);
    }

    @Deprecated
    public void drawLine(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawLine is not allowed within a text block.");
        }
        moveTo(f, f2);
        lineTo(f3, f4);
        stroke();
    }

    @Deprecated
    public void addPolygon(float[] fArr, float[] fArr2) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: addPolygon is not allowed within a text block.");
        }
        if (fArr.length != fArr2.length) {
            throw new IllegalArgumentException("Error: some points are missing coordinate");
        }
        for (int i = 0; i < fArr.length; i++) {
            if (i == 0) {
                moveTo(fArr[i], fArr2[i]);
            } else {
                lineTo(fArr[i], fArr2[i]);
            }
        }
        closeSubPath();
    }

    @Deprecated
    public void drawPolygon(float[] fArr, float[] fArr2) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: drawPolygon is not allowed within a text block.");
        }
        addPolygon(fArr, fArr2);
        stroke();
    }

    @Deprecated
    public void fillPolygon(float[] fArr, float[] fArr2) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: fillPolygon is not allowed within a text block.");
        }
        addPolygon(fArr, fArr2);
        fill();
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

    @Deprecated
    public void fill(Path.FillType fillType) throws IOException {
        if (fillType == Path.FillType.WINDING) {
            fill();
        } else if (fillType == Path.FillType.EVEN_ODD) {
            fillEvenOdd();
        } else {
            throw new IllegalArgumentException("Error: unknown value for winding rule");
        }
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

    @Deprecated
    public void closeSubPath() throws IOException {
        closePath();
    }

    public void closePath() throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: closePath is not allowed within a text block.");
        }
        writeOperator("h");
    }

    @Deprecated
    public void clipPath(Path.FillType fillType) throws IOException {
        if (this.inTextMode) {
            throw new IllegalStateException("Error: clipPath is not allowed within a text block.");
        }
        if (fillType == Path.FillType.WINDING) {
            writeOperator("W");
        } else if (fillType == Path.FillType.EVEN_ODD) {
            writeOperator(OperatorName.CLIP_EVEN_ODD);
        } else {
            throw new IllegalArgumentException("Error: unknown value for winding rule");
        }
        writeOperator(OperatorName.ENDPATH);
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

    @Deprecated
    public void beginMarkedContentSequence(COSName cOSName) throws IOException {
        beginMarkedContent(cOSName);
    }

    public void beginMarkedContent(COSName cOSName) throws IOException {
        writeOperand(cOSName);
        writeOperator(OperatorName.BEGIN_MARKED_CONTENT);
    }

    @Deprecated
    public void beginMarkedContentSequence(COSName cOSName, COSName cOSName2) throws IOException {
        writeOperand(cOSName);
        writeOperand(cOSName2);
        writeOperator(OperatorName.BEGIN_MARKED_CONTENT_SEQ);
    }

    public void beginMarkedContent(COSName cOSName, PDPropertyList pDPropertyList) throws IOException {
        writeOperand(cOSName);
        writeOperand(this.resources.add(pDPropertyList));
        writeOperator(OperatorName.BEGIN_MARKED_CONTENT_SEQ);
    }

    @Deprecated
    public void endMarkedContentSequence() throws IOException {
        endMarkedContent();
    }

    public void endMarkedContent() throws IOException {
        writeOperator(OperatorName.END_MARKED_CONTENT);
    }

    @Deprecated
    public void appendRawCommands(String str) throws IOException {
        this.output.write(str.getBytes(Charsets.US_ASCII));
    }

    @Deprecated
    public void appendRawCommands(byte[] bArr) throws IOException {
        this.output.write(bArr);
    }

    @Deprecated
    public void appendRawCommands(int i) throws IOException {
        this.output.write(i);
    }

    @Deprecated
    public void appendRawCommands(double d) throws IOException {
        this.output.write(this.formatDecimal.format(d).getBytes(Charsets.US_ASCII));
    }

    @Deprecated
    public void appendRawCommands(float f) throws IOException {
        this.output.write(this.formatDecimal.format(f).getBytes(Charsets.US_ASCII));
    }

    @Deprecated
    public void appendCOSName(COSName cOSName) throws IOException {
        cOSName.writePDF(this.output);
    }

    public void setGraphicsStateParameters(PDExtendedGraphicsState pDExtendedGraphicsState) throws IOException {
        writeOperand(this.resources.add(pDExtendedGraphicsState));
        writeOperator(OperatorName.SET_GRAPHICS_STATE_PARAMS);
    }

    public void addComment(String str) throws IOException {
        if (str.indexOf(10) >= 0 || str.indexOf(13) >= 0) {
            throw new IllegalArgumentException("comment should not include a newline");
        }
        this.output.write(37);
        this.output.write(str.getBytes(Charsets.US_ASCII));
        this.output.write(10);
    }

    protected void writeOperand(float f) throws IOException {
        if (Float.isInfinite(f) || Float.isNaN(f)) {
            throw new IllegalArgumentException(f + " is not a finite number");
        }
        int formatFloatFast = NumberFormatUtil.formatFloatFast(f, this.formatDecimal.getMaximumFractionDigits(), this.formatBuffer);
        if (formatFloatFast == -1) {
            write(this.formatDecimal.format(f));
        } else {
            this.output.write(this.formatBuffer, 0, formatFloatFast);
        }
        this.output.write(32);
    }

    private void writeOperand(int i) throws IOException {
        write(this.formatDecimal.format(i));
        this.output.write(32);
    }

    private void writeOperand(COSName cOSName) throws IOException {
        cOSName.writePDF(this.output);
        this.output.write(32);
    }

    private void writeOperator(String str) throws IOException {
        this.output.write(str.getBytes(Charsets.US_ASCII));
        this.output.write(10);
    }

    private void write(String str) throws IOException {
        this.output.write(str.getBytes(Charsets.US_ASCII));
    }

    private void writeLine() throws IOException {
        this.output.write(10);
    }

    private void writeBytes(byte[] bArr) throws IOException {
        this.output.write(bArr);
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
        OutputStream outputStream = this.output;
        if (outputStream != null) {
            outputStream.close();
            this.output = null;
        }
    }

    private void setStrokingColorSpaceStack(PDColorSpace pDColorSpace) {
        if (this.strokingColorSpaceStack.isEmpty()) {
            this.strokingColorSpaceStack.add(pDColorSpace);
            return;
        }
        Stack<PDColorSpace> stack = this.strokingColorSpaceStack;
        stack.setElementAt(pDColorSpace, stack.size() - 1);
    }

    private void setNonStrokingColorSpaceStack(PDColorSpace pDColorSpace) {
        if (this.nonStrokingColorSpaceStack.isEmpty()) {
            this.nonStrokingColorSpaceStack.add(pDColorSpace);
            return;
        }
        Stack<PDColorSpace> stack = this.nonStrokingColorSpaceStack;
        stack.setElementAt(pDColorSpace, stack.size() - 1);
    }

    public void setRenderingMode(RenderingMode renderingMode) throws IOException {
        writeOperand(renderingMode.intValue());
        writeOperator(OperatorName.SET_TEXT_RENDERINGMODE);
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

    public void setTextRise(float f) throws IOException {
        writeOperand(f);
        writeOperator(OperatorName.SET_TEXT_RISE);
    }
}
