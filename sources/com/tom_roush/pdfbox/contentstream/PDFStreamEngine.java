package com.tom_roush.pdfbox.contentstream;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.MissingOperandException;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.contentstream.operator.state.EmptyGraphicsStackException;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.filter.MissingImageReaderException;
import com.tom_roush.pdfbox.pdfparser.PDFStreamParser;
import com.tom_roush.pdfbox.pdmodel.MissingResourceException;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.pdmodel.font.PDType3CharProc;
import com.tom_roush.pdfbox.pdmodel.font.PDType3Font;
import com.tom_roush.pdfbox.pdmodel.graphics.PDLineDashPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.blend.BlendMode;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.pattern.PDTilingPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDGraphicsState;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDTextState;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class PDFStreamEngine {
    private PDPage currentPage;
    private Matrix initialMatrix;
    private boolean isProcessingPage;
    private PDResources resources;
    private Matrix textLineMatrix;
    private Matrix textMatrix;
    private final Map<String, OperatorProcessor> operators = new HashMap(80);
    private Deque<PDGraphicsState> graphicsStack = new ArrayDeque();
    private int level = 0;

    public void beginMarkedContentSequence(COSName cOSName, COSDictionary cOSDictionary) {
    }

    public void beginText() throws IOException {
    }

    public void endMarkedContentSequence() {
    }

    public void endText() throws IOException {
    }

    protected void showFontGlyph(Matrix matrix, PDFont pDFont, int i, String str, Vector vector) throws IOException {
    }

    protected void unsupportedOperator(Operator operator, List<COSBase> list) throws IOException {
    }

    @Deprecated
    public void registerOperatorProcessor(String str, OperatorProcessor operatorProcessor) {
        operatorProcessor.setContext(this);
        this.operators.put(str, operatorProcessor);
    }

    public final void addOperator(OperatorProcessor operatorProcessor) {
        operatorProcessor.setContext(this);
        this.operators.put(operatorProcessor.getName(), operatorProcessor);
    }

    private void initPage(PDPage pDPage) {
        if (pDPage == null) {
            throw new IllegalArgumentException("Page cannot be null");
        }
        this.currentPage = pDPage;
        this.graphicsStack.clear();
        this.graphicsStack.push(new PDGraphicsState(pDPage.getCropBox()));
        this.textMatrix = null;
        this.textLineMatrix = null;
        this.resources = null;
        this.initialMatrix = pDPage.getMatrix();
    }

    public void processPage(PDPage pDPage) throws IOException {
        initPage(pDPage);
        if (pDPage.hasContents()) {
            this.isProcessingPage = true;
            processStream(pDPage);
            this.isProcessingPage = false;
        }
    }

    public void showTransparencyGroup(PDTransparencyGroup pDTransparencyGroup) throws IOException {
        processTransparencyGroup(pDTransparencyGroup);
    }

    public void showForm(PDFormXObject pDFormXObject) throws IOException {
        if (this.currentPage == null) {
            throw new IllegalStateException("No current page, call #processChildStream(PDContentStream, PDPage) instead");
        }
        if (pDFormXObject.getCOSObject().getLength() > 0) {
            processStream(pDFormXObject);
        }
    }

    public void processSoftMask(PDTransparencyGroup pDTransparencyGroup) throws IOException {
        saveGraphicsState();
        getGraphicsState().setCurrentTransformationMatrix(getGraphicsState().getSoftMask().getInitialTransformationMatrix());
        processTransparencyGroup(pDTransparencyGroup);
        restoreGraphicsState();
    }

    public void processTransparencyGroup(PDTransparencyGroup pDTransparencyGroup) throws IOException {
        if (this.currentPage == null) {
            throw new IllegalStateException("No current page, call #processChildStream(PDContentStream, PDPage) instead");
        }
        PDResources pushResources = pushResources(pDTransparencyGroup);
        Deque<PDGraphicsState> saveGraphicsStack = saveGraphicsStack();
        Matrix matrix = this.initialMatrix;
        PDGraphicsState graphicsState = getGraphicsState();
        this.initialMatrix = graphicsState.getCurrentTransformationMatrix().m268clone();
        graphicsState.getCurrentTransformationMatrix().concatenate(pDTransparencyGroup.getMatrix());
        graphicsState.setBlendMode(BlendMode.NORMAL);
        graphicsState.setAlphaConstant(1.0d);
        graphicsState.setNonStrokeAlphaConstant(1.0d);
        graphicsState.setSoftMask(null);
        clipToRect(pDTransparencyGroup.getBBox());
        try {
            processStreamOperators(pDTransparencyGroup);
        } finally {
            this.initialMatrix = matrix;
            restoreGraphicsStack(saveGraphicsStack);
            popResources(pushResources);
        }
    }

    protected void processType3Stream(PDType3CharProc pDType3CharProc, Matrix matrix) throws IOException {
        if (this.currentPage == null) {
            throw new IllegalStateException("No current page, call #processChildStream(PDContentStream, PDPage) instead");
        }
        PDResources pushResources = pushResources(pDType3CharProc);
        Deque<PDGraphicsState> saveGraphicsStack = saveGraphicsStack();
        getGraphicsState().setCurrentTransformationMatrix(matrix);
        matrix.concatenate(pDType3CharProc.getMatrix());
        Matrix matrix2 = this.textMatrix;
        this.textMatrix = new Matrix();
        Matrix matrix3 = this.textLineMatrix;
        this.textLineMatrix = new Matrix();
        try {
            processStreamOperators(pDType3CharProc);
        } finally {
            this.textMatrix = matrix2;
            this.textLineMatrix = matrix3;
            restoreGraphicsStack(saveGraphicsStack);
            popResources(pushResources);
        }
    }

    protected void processAnnotation(PDAnnotation pDAnnotation, PDAppearanceStream pDAppearanceStream) throws IOException {
        PDRectangle bBox = pDAppearanceStream.getBBox();
        PDRectangle rectangle = pDAnnotation.getRectangle();
        if (rectangle == null || rectangle.getWidth() <= 0.0f || rectangle.getHeight() <= 0.0f || bBox == null || bBox.getWidth() <= 0.0f || bBox.getHeight() <= 0.0f) {
            return;
        }
        PDResources pushResources = pushResources(pDAppearanceStream);
        Deque<PDGraphicsState> saveGraphicsStack = saveGraphicsStack();
        Matrix matrix = pDAppearanceStream.getMatrix();
        RectF rectF = new RectF();
        bBox.transform(matrix).computeBounds(rectF, true);
        Matrix translateInstance = Matrix.getTranslateInstance(rectangle.getLowerLeftX(), rectangle.getLowerLeftY());
        translateInstance.concatenate(Matrix.getScaleInstance(rectangle.getWidth() / rectF.width(), rectangle.getHeight() / rectF.height()));
        translateInstance.concatenate(Matrix.getTranslateInstance(-rectF.left, -rectF.top));
        Matrix concatenate = Matrix.concatenate(translateInstance, matrix);
        getGraphicsState().setCurrentTransformationMatrix(concatenate);
        clipToRect(bBox);
        this.initialMatrix = concatenate.m268clone();
        try {
            processStreamOperators(pDAppearanceStream);
        } finally {
            restoreGraphicsStack(saveGraphicsStack);
            popResources(pushResources);
        }
    }

    protected final void processTilingPattern(PDTilingPattern pDTilingPattern, PDColor pDColor, PDColorSpace pDColorSpace) throws IOException {
        processTilingPattern(pDTilingPattern, pDColor, pDColorSpace, pDTilingPattern.getMatrix());
    }

    protected final void processTilingPattern(PDTilingPattern pDTilingPattern, PDColor pDColor, PDColorSpace pDColorSpace, Matrix matrix) throws IOException {
        PDResources pushResources = pushResources(pDTilingPattern);
        Matrix matrix2 = this.initialMatrix;
        this.initialMatrix = Matrix.concatenate(matrix2, matrix);
        Deque<PDGraphicsState> saveGraphicsStack = saveGraphicsStack();
        PDRectangle bBox = pDTilingPattern.getBBox();
        RectF rectF = new RectF();
        pDTilingPattern.getBBox().transform(matrix).computeBounds(rectF, true);
        this.graphicsStack.push(new PDGraphicsState(new PDRectangle(rectF.left, rectF.top, rectF.width(), rectF.height())));
        PDGraphicsState graphicsState = getGraphicsState();
        if (pDColorSpace != null) {
            PDColor pDColor2 = new PDColor(pDColor.getComponents(), pDColorSpace);
            graphicsState.setNonStrokingColorSpace(pDColorSpace);
            graphicsState.setNonStrokingColor(pDColor2);
            graphicsState.setStrokingColorSpace(pDColorSpace);
            graphicsState.setStrokingColor(pDColor2);
        }
        graphicsState.getCurrentTransformationMatrix().concatenate(matrix);
        clipToRect(bBox);
        Matrix matrix3 = this.textMatrix;
        Matrix matrix4 = this.textLineMatrix;
        try {
            processStreamOperators(pDTilingPattern);
        } finally {
            this.textMatrix = matrix3;
            this.textLineMatrix = matrix4;
            this.initialMatrix = matrix2;
            restoreGraphicsStack(saveGraphicsStack);
            popResources(pushResources);
        }
    }

    public void showAnnotation(PDAnnotation pDAnnotation) throws IOException {
        PDAppearanceStream appearance = getAppearance(pDAnnotation);
        if (appearance != null) {
            processAnnotation(pDAnnotation, appearance);
        }
    }

    public PDAppearanceStream getAppearance(PDAnnotation pDAnnotation) {
        return pDAnnotation.getNormalAppearanceStream();
    }

    protected void processChildStream(PDContentStream pDContentStream, PDPage pDPage) throws IOException {
        if (this.isProcessingPage) {
            throw new IllegalStateException("Current page has already been set via  #processPage(PDPage) call #processChildStream(PDContentStream) instead");
        }
        initPage(pDPage);
        processStream(pDContentStream);
        this.currentPage = null;
    }

    private void processStream(PDContentStream pDContentStream) throws IOException {
        PDResources pushResources = pushResources(pDContentStream);
        Deque<PDGraphicsState> saveGraphicsStack = saveGraphicsStack();
        Matrix matrix = this.initialMatrix;
        PDGraphicsState graphicsState = getGraphicsState();
        graphicsState.getCurrentTransformationMatrix().concatenate(pDContentStream.getMatrix());
        this.initialMatrix = graphicsState.getCurrentTransformationMatrix().m268clone();
        clipToRect(pDContentStream.getBBox());
        try {
            processStreamOperators(pDContentStream);
        } finally {
            this.initialMatrix = matrix;
            restoreGraphicsStack(saveGraphicsStack);
            popResources(pushResources);
        }
    }

    private void processStreamOperators(PDContentStream pDContentStream) throws IOException {
        ArrayList arrayList = new ArrayList();
        PDFStreamParser pDFStreamParser = new PDFStreamParser(pDContentStream);
        for (Object parseNextToken = pDFStreamParser.parseNextToken(); parseNextToken != null; parseNextToken = pDFStreamParser.parseNextToken()) {
            if (parseNextToken instanceof Operator) {
                processOperator((Operator) parseNextToken, arrayList);
                arrayList.clear();
            } else {
                arrayList.add((COSBase) parseNextToken);
            }
        }
    }

    private PDResources pushResources(PDContentStream pDContentStream) {
        PDResources pDResources = this.resources;
        PDResources resources = pDContentStream.getResources();
        if (resources != null) {
            this.resources = resources;
        } else if (this.resources == null) {
            PDResources resources2 = this.currentPage.getResources();
            this.resources = resources2;
            if (resources2 == null) {
                this.resources = new PDResources();
            }
        }
        return pDResources;
    }

    private void popResources(PDResources pDResources) {
        this.resources = pDResources;
    }

    private void clipToRect(PDRectangle pDRectangle) {
        if (pDRectangle != null) {
            PDGraphicsState graphicsState = getGraphicsState();
            graphicsState.intersectClippingPath(pDRectangle.transform(graphicsState.getCurrentTransformationMatrix()));
        }
    }

    public void showTextString(byte[] bArr) throws IOException {
        showText(bArr);
    }

    public void showTextStrings(COSArray cOSArray) throws IOException {
        float f;
        PDTextState textState = getGraphicsState().getTextState();
        float fontSize = textState.getFontSize();
        float horizontalScaling = textState.getHorizontalScaling() / 100.0f;
        PDFont font = textState.getFont();
        boolean isVertical = font != null ? font.isVertical() : false;
        Iterator<COSBase> it = cOSArray.iterator();
        while (it.hasNext()) {
            COSBase next = it.next();
            if (next instanceof COSNumber) {
                float floatValue = ((COSNumber) next).floatValue();
                float f2 = 0.0f;
                if (isVertical) {
                    f = ((-floatValue) / 1000.0f) * fontSize;
                } else {
                    f2 = ((-floatValue) / 1000.0f) * fontSize * horizontalScaling;
                    f = 0.0f;
                }
                applyTextAdjustment(f2, f);
            } else if (next instanceof COSString) {
                showText(((COSString) next).getBytes());
            } else if (next instanceof COSArray) {
                Log.e("PdfBox-Android", "Nested arrays are not allowed in an array for TJ operation: " + next);
            } else {
                Log.e("PdfBox-Android", "Unknown type " + next.getClass().getSimpleName() + " in array for TJ operation: " + next);
            }
        }
    }

    protected void applyTextAdjustment(float f, float f2) throws IOException {
        this.textMatrix.concatenate(Matrix.getTranslateInstance(f, f2));
    }

    protected void showText(byte[] bArr) throws IOException {
        float f;
        PDGraphicsState graphicsState = getGraphicsState();
        PDTextState textState = graphicsState.getTextState();
        PDFont font = textState.getFont();
        if (font == null) {
            Log.w("PdfBox-Android", "No current font, will use default");
            font = PDType1Font.HELVETICA;
        }
        float fontSize = textState.getFontSize();
        float horizontalScaling = textState.getHorizontalScaling() / 100.0f;
        float characterSpacing = textState.getCharacterSpacing();
        Matrix matrix = new Matrix(fontSize * horizontalScaling, 0.0f, 0.0f, fontSize, 0.0f, textState.getRise());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        while (byteArrayInputStream.available() > 0) {
            int available = byteArrayInputStream.available();
            int readCode = font.readCode(byteArrayInputStream);
            float f2 = 0.0f;
            float wordSpacing = (available - byteArrayInputStream.available() == 1 && readCode == 32) ? textState.getWordSpacing() + 0.0f : 0.0f;
            Matrix multiply = matrix.multiply(this.textMatrix).multiply(graphicsState.getCurrentTransformationMatrix());
            if (font.isVertical()) {
                multiply.translate(font.getPositionVector(readCode));
            }
            Vector displacement = font.getDisplacement(readCode);
            showGlyph(multiply, font, readCode, displacement);
            if (font.isVertical()) {
                f = (displacement.getY() * fontSize) + characterSpacing + wordSpacing;
            } else {
                f2 = ((displacement.getX() * fontSize) + characterSpacing + wordSpacing) * horizontalScaling;
                f = 0.0f;
            }
            this.textMatrix.concatenate(Matrix.getTranslateInstance(f2, f));
        }
    }

    protected void showGlyph(Matrix matrix, PDFont pDFont, int i, String str, Vector vector) throws IOException {
        if (pDFont instanceof PDType3Font) {
            showType3Glyph(matrix, (PDType3Font) pDFont, i, vector);
        } else {
            showFontGlyph(matrix, pDFont, i, vector);
        }
    }

    protected void showGlyph(Matrix matrix, PDFont pDFont, int i, Vector vector) throws IOException {
        showGlyph(matrix, pDFont, i, pDFont.toUnicode(i), vector);
    }

    protected void showFontGlyph(Matrix matrix, PDFont pDFont, int i, Vector vector) throws IOException {
        showFontGlyph(matrix, pDFont, i, pDFont.toUnicode(i), vector);
    }

    protected void showType3Glyph(Matrix matrix, PDType3Font pDType3Font, int i, String str, Vector vector) throws IOException {
        PDType3CharProc charProc = pDType3Font.getCharProc(i);
        if (charProc != null) {
            processType3Stream(charProc, matrix);
        }
    }

    public void showType3Glyph(Matrix matrix, PDType3Font pDType3Font, int i, Vector vector) throws IOException {
        showType3Glyph(matrix, pDType3Font, i, pDType3Font.toUnicode(i), vector);
    }

    public void processOperator(String str, List<COSBase> list) throws IOException {
        processOperator(Operator.getOperator(str), list);
    }

    protected void processOperator(Operator operator, List<COSBase> list) throws IOException {
        OperatorProcessor operatorProcessor = this.operators.get(operator.getName());
        if (operatorProcessor != null) {
            operatorProcessor.setContext(this);
            try {
                operatorProcessor.process(operator, list);
                return;
            } catch (IOException e) {
                operatorException(operator, list, e);
                return;
            }
        }
        unsupportedOperator(operator, list);
    }

    protected void operatorException(Operator operator, List<COSBase> list, IOException iOException) throws IOException {
        if ((iOException instanceof MissingOperandException) || (iOException instanceof MissingResourceException) || (iOException instanceof MissingImageReaderException)) {
            Log.e("PdfBox-Android", iOException.getMessage());
        } else if (iOException instanceof EmptyGraphicsStackException) {
            Log.w("PdfBox-Android", iOException.getMessage());
        } else if (operator.getName().equals(OperatorName.DRAW_OBJECT)) {
            Log.w("PdfBox-Android", iOException.getMessage());
        } else {
            throw iOException;
        }
    }

    public void saveGraphicsState() {
        Deque<PDGraphicsState> deque = this.graphicsStack;
        deque.push(deque.peek().m266clone());
    }

    public void restoreGraphicsState() {
        this.graphicsStack.pop();
    }

    protected final Deque<PDGraphicsState> saveGraphicsStack() {
        Deque<PDGraphicsState> deque = this.graphicsStack;
        ArrayDeque arrayDeque = new ArrayDeque(1);
        this.graphicsStack = arrayDeque;
        arrayDeque.add(deque.peek().m266clone());
        return deque;
    }

    protected final void restoreGraphicsStack(Deque<PDGraphicsState> deque) {
        this.graphicsStack = deque;
    }

    public int getGraphicsStackSize() {
        return this.graphicsStack.size();
    }

    public PDGraphicsState getGraphicsState() {
        return this.graphicsStack.peek();
    }

    public Matrix getTextLineMatrix() {
        return this.textLineMatrix;
    }

    public void setTextLineMatrix(Matrix matrix) {
        this.textLineMatrix = matrix;
    }

    public Matrix getTextMatrix() {
        return this.textMatrix;
    }

    public void setTextMatrix(Matrix matrix) {
        this.textMatrix = matrix;
    }

    public void setLineDashPattern(COSArray cOSArray, int i) {
        if (i < 0) {
            Log.w("PdfBox-Android", "Dash phase has negative value " + i + ", set to 0");
            i = 0;
        }
        getGraphicsState().setLineDashPattern(new PDLineDashPattern(cOSArray, i));
    }

    public PDResources getResources() {
        return this.resources;
    }

    public PDPage getCurrentPage() {
        return this.currentPage;
    }

    public Matrix getInitialMatrix() {
        return this.initialMatrix;
    }

    public PointF transformedPoint(float f, float f2) {
        float[] fArr = {f, f2};
        getGraphicsState().getCurrentTransformationMatrix().createAffineTransform().transform(fArr, 0, fArr, 0, 1);
        return new PointF(fArr[0], fArr[1]);
    }

    public float transformWidth(float f) {
        Matrix currentTransformationMatrix = getGraphicsState().getCurrentTransformationMatrix();
        float scaleX = currentTransformationMatrix.getScaleX() + currentTransformationMatrix.getShearX();
        float scaleY = currentTransformationMatrix.getScaleY() + currentTransformationMatrix.getShearY();
        return f * ((float) Math.sqrt(((scaleX * scaleX) + (scaleY * scaleY)) * 0.5d));
    }

    public int getLevel() {
        return this.level;
    }

    public void increaseLevel() {
        this.level++;
    }

    public void decreaseLevel() {
        int i = this.level - 1;
        this.level = i;
        if (i < 0) {
            Log.e("PdfBox-Android", "level is " + this.level);
        }
    }
}
