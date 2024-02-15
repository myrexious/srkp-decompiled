package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDAppearanceContentStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLine;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationSquareCircle;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceEntry;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class PDAbstractAppearanceHandler implements PDAppearanceHandler {
    private final PDAnnotation annotation;
    protected PDDocument document;
    protected static final Set<String> SHORT_STYLES = createShortStyles();
    static final double ARROW_ANGLE = Math.toRadians(30.0d);
    protected static final Set<String> INTERIOR_COLOR_STYLES = createInteriorColorStyles();
    protected static final Set<String> ANGLED_STYLES = createAngledStyles();

    public PDAbstractAppearanceHandler(PDAnnotation pDAnnotation) {
        this(pDAnnotation, null);
    }

    public PDAbstractAppearanceHandler(PDAnnotation pDAnnotation, PDDocument pDDocument) {
        this.annotation = pDAnnotation;
        this.document = pDDocument;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler
    public void generateAppearanceStreams() {
        generateNormalAppearance();
        generateRolloverAppearance();
        generateDownAppearance();
    }

    public PDAnnotation getAnnotation() {
        return this.annotation;
    }

    public PDColor getColor() {
        return this.annotation.getColor();
    }

    public PDRectangle getRectangle() {
        return this.annotation.getRectangle();
    }

    public COSStream createCOSStream() {
        PDDocument pDDocument = this.document;
        return pDDocument == null ? new COSStream() : pDDocument.getDocument().createCOSStream();
    }

    PDAppearanceDictionary getAppearance() {
        PDAppearanceDictionary appearance = this.annotation.getAppearance();
        if (appearance == null) {
            PDAppearanceDictionary pDAppearanceDictionary = new PDAppearanceDictionary();
            this.annotation.setAppearance(pDAppearanceDictionary);
            return pDAppearanceDictionary;
        }
        return appearance;
    }

    public PDAppearanceContentStream getNormalAppearanceAsContentStream() throws IOException {
        return getNormalAppearanceAsContentStream(false);
    }

    public PDAppearanceContentStream getNormalAppearanceAsContentStream(boolean z) throws IOException {
        return getAppearanceEntryAsContentStream(getNormalAppearance(), z);
    }

    PDAppearanceEntry getDownAppearance() {
        PDAppearanceDictionary appearance = getAppearance();
        PDAppearanceEntry downAppearance = appearance.getDownAppearance();
        if (downAppearance.isSubDictionary()) {
            PDAppearanceEntry pDAppearanceEntry = new PDAppearanceEntry(createCOSStream());
            appearance.setDownAppearance(pDAppearanceEntry);
            return pDAppearanceEntry;
        }
        return downAppearance;
    }

    PDAppearanceEntry getRolloverAppearance() {
        PDAppearanceDictionary appearance = getAppearance();
        PDAppearanceEntry rolloverAppearance = appearance.getRolloverAppearance();
        if (rolloverAppearance.isSubDictionary()) {
            PDAppearanceEntry pDAppearanceEntry = new PDAppearanceEntry(createCOSStream());
            appearance.setRolloverAppearance(pDAppearanceEntry);
            return pDAppearanceEntry;
        }
        return rolloverAppearance;
    }

    public PDRectangle getPaddedRectangle(PDRectangle pDRectangle, float f) {
        float lowerLeftX = pDRectangle.getLowerLeftX() + f;
        float lowerLeftY = pDRectangle.getLowerLeftY() + f;
        float f2 = f * 2.0f;
        return new PDRectangle(lowerLeftX, lowerLeftY, pDRectangle.getWidth() - f2, pDRectangle.getHeight() - f2);
    }

    PDRectangle addRectDifferences(PDRectangle pDRectangle, float[] fArr) {
        return (fArr == null || fArr.length != 4) ? pDRectangle : new PDRectangle(pDRectangle.getLowerLeftX() - fArr[0], pDRectangle.getLowerLeftY() - fArr[1], pDRectangle.getWidth() + fArr[0] + fArr[2], pDRectangle.getHeight() + fArr[1] + fArr[3]);
    }

    public PDRectangle applyRectDifferences(PDRectangle pDRectangle, float[] fArr) {
        return (fArr == null || fArr.length != 4) ? pDRectangle : new PDRectangle(pDRectangle.getLowerLeftX() + fArr[0], pDRectangle.getLowerLeftY() + fArr[1], (pDRectangle.getWidth() - fArr[0]) - fArr[2], (pDRectangle.getHeight() - fArr[1]) - fArr[3]);
    }

    public void setOpacity(PDAppearanceContentStream pDAppearanceContentStream, float f) throws IOException {
        if (f < 1.0f) {
            PDExtendedGraphicsState pDExtendedGraphicsState = new PDExtendedGraphicsState();
            pDExtendedGraphicsState.setStrokingAlphaConstant(Float.valueOf(f));
            pDExtendedGraphicsState.setNonStrokingAlphaConstant(Float.valueOf(f));
            pDAppearanceContentStream.setGraphicsStateParameters(pDExtendedGraphicsState);
        }
    }

    public void drawStyle(String str, PDAppearanceContentStream pDAppearanceContentStream, float f, float f2, float f3, boolean z, boolean z2, boolean z3) throws IOException {
        boolean z4 = true;
        int i = z3 ? -1 : 1;
        if (PDAnnotationLine.LE_OPEN_ARROW.equals(str) || PDAnnotationLine.LE_CLOSED_ARROW.equals(str)) {
            float f4 = i * f3;
            drawArrow(pDAppearanceContentStream, f + f4, f2, f4 * 9.0f);
        } else if (PDAnnotationLine.LE_BUTT.equals(str)) {
            float f5 = f3 * 3.0f;
            pDAppearanceContentStream.moveTo(f, f2 - f5);
            pDAppearanceContentStream.lineTo(f, f2 + f5);
        } else if (PDAnnotationLine.LE_DIAMOND.equals(str)) {
            drawDiamond(pDAppearanceContentStream, f, f2, f3 * 3.0f);
        } else if ("Square".equals(str)) {
            float f6 = f3 * 3.0f;
            float f7 = f - f6;
            float f8 = f2 - f6;
            float f9 = 6.0f * f3;
            pDAppearanceContentStream.addRect(f7, f8, f9, f9);
        } else if ("Circle".equals(str)) {
            drawCircle(pDAppearanceContentStream, f, f2, f3 * 3.0f);
        } else if (PDAnnotationLine.LE_R_OPEN_ARROW.equals(str) || PDAnnotationLine.LE_R_CLOSED_ARROW.equals(str)) {
            float f10 = (-i) * f3;
            drawArrow(pDAppearanceContentStream, f + f10, f2, f10 * 9.0f);
        } else if (PDAnnotationLine.LE_SLASH.equals(str)) {
            double d = f3 * 9.0f;
            pDAppearanceContentStream.moveTo(((float) (Math.cos(Math.toRadians(60.0d)) * d)) + f, ((float) (Math.sin(Math.toRadians(60.0d)) * d)) + f2);
            pDAppearanceContentStream.lineTo(f + ((float) (Math.cos(Math.toRadians(240.0d)) * d)), f2 + ((float) (Math.sin(Math.toRadians(240.0d)) * d)));
        }
        if (PDAnnotationLine.LE_R_CLOSED_ARROW.equals(str) || PDAnnotationLine.LE_CLOSED_ARROW.equals(str)) {
            pDAppearanceContentStream.closePath();
        }
        pDAppearanceContentStream.drawShape(f3, z, (INTERIOR_COLOR_STYLES.contains(str) && z2) ? false : false);
    }

    void drawArrow(PDAppearanceContentStream pDAppearanceContentStream, float f, float f2, float f3) throws IOException {
        double d = ARROW_ANGLE;
        double d2 = f3;
        float cos = ((float) (Math.cos(d) * d2)) + f;
        float sin = (float) (Math.sin(d) * d2);
        pDAppearanceContentStream.moveTo(cos, f2 + sin);
        pDAppearanceContentStream.lineTo(f, f2);
        pDAppearanceContentStream.lineTo(cos, f2 - sin);
    }

    void drawDiamond(PDAppearanceContentStream pDAppearanceContentStream, float f, float f2, float f3) throws IOException {
        pDAppearanceContentStream.moveTo(f - f3, f2);
        pDAppearanceContentStream.lineTo(f, f2 + f3);
        pDAppearanceContentStream.lineTo(f + f3, f2);
        pDAppearanceContentStream.lineTo(f, f2 - f3);
        pDAppearanceContentStream.closePath();
    }

    public void drawCircle(PDAppearanceContentStream pDAppearanceContentStream, float f, float f2, float f3) throws IOException {
        float f4 = f3 * 0.551784f;
        float f5 = f2 + f3;
        pDAppearanceContentStream.moveTo(f, f5);
        float f6 = f + f4;
        float f7 = f + f3;
        float f8 = f2 + f4;
        pDAppearanceContentStream.curveTo(f6, f5, f7, f8, f7, f2);
        float f9 = f2 - f4;
        float f10 = f2 - f3;
        pDAppearanceContentStream.curveTo(f7, f9, f6, f10, f, f10);
        float f11 = f - f4;
        float f12 = f - f3;
        pDAppearanceContentStream.curveTo(f11, f10, f12, f9, f12, f2);
        pDAppearanceContentStream.curveTo(f12, f8, f11, f5, f, f5);
        pDAppearanceContentStream.closePath();
    }

    public void drawCircle2(PDAppearanceContentStream pDAppearanceContentStream, float f, float f2, float f3) throws IOException {
        float f4 = f3 * 0.551784f;
        float f5 = f2 + f3;
        pDAppearanceContentStream.moveTo(f, f5);
        float f6 = f - f4;
        float f7 = f - f3;
        float f8 = f2 + f4;
        pDAppearanceContentStream.curveTo(f6, f5, f7, f8, f7, f2);
        float f9 = f2 - f4;
        float f10 = f2 - f3;
        pDAppearanceContentStream.curveTo(f7, f9, f6, f10, f, f10);
        float f11 = f4 + f;
        float f12 = f + f3;
        pDAppearanceContentStream.curveTo(f11, f10, f12, f9, f12, f2);
        pDAppearanceContentStream.curveTo(f12, f8, f11, f5, f, f5);
        pDAppearanceContentStream.closePath();
    }

    private static Set<String> createShortStyles() {
        HashSet hashSet = new HashSet();
        hashSet.add(PDAnnotationLine.LE_OPEN_ARROW);
        hashSet.add(PDAnnotationLine.LE_CLOSED_ARROW);
        hashSet.add("Square");
        hashSet.add("Circle");
        hashSet.add(PDAnnotationLine.LE_DIAMOND);
        return Collections.unmodifiableSet(hashSet);
    }

    private static Set<String> createInteriorColorStyles() {
        HashSet hashSet = new HashSet();
        hashSet.add(PDAnnotationLine.LE_CLOSED_ARROW);
        hashSet.add("Circle");
        hashSet.add(PDAnnotationLine.LE_DIAMOND);
        hashSet.add(PDAnnotationLine.LE_R_CLOSED_ARROW);
        hashSet.add("Square");
        return Collections.unmodifiableSet(hashSet);
    }

    private static Set<String> createAngledStyles() {
        HashSet hashSet = new HashSet();
        hashSet.add(PDAnnotationLine.LE_CLOSED_ARROW);
        hashSet.add(PDAnnotationLine.LE_OPEN_ARROW);
        hashSet.add(PDAnnotationLine.LE_R_CLOSED_ARROW);
        hashSet.add(PDAnnotationLine.LE_R_OPEN_ARROW);
        hashSet.add(PDAnnotationLine.LE_BUTT);
        hashSet.add(PDAnnotationLine.LE_SLASH);
        return Collections.unmodifiableSet(hashSet);
    }

    private PDAppearanceEntry getNormalAppearance() {
        PDAppearanceDictionary appearance = getAppearance();
        PDAppearanceEntry normalAppearance = appearance.getNormalAppearance();
        if (normalAppearance == null || normalAppearance.isSubDictionary()) {
            PDAppearanceEntry pDAppearanceEntry = new PDAppearanceEntry(createCOSStream());
            appearance.setNormalAppearance(pDAppearanceEntry);
            return pDAppearanceEntry;
        }
        return normalAppearance;
    }

    private PDAppearanceContentStream getAppearanceEntryAsContentStream(PDAppearanceEntry pDAppearanceEntry, boolean z) throws IOException {
        PDAppearanceStream appearanceStream = pDAppearanceEntry.getAppearanceStream();
        setTransformationMatrix(appearanceStream);
        if (appearanceStream.getResources() == null) {
            appearanceStream.setResources(new PDResources());
        }
        return new PDAppearanceContentStream(appearanceStream, z);
    }

    private void setTransformationMatrix(PDAppearanceStream pDAppearanceStream) {
        PDRectangle rectangle = getRectangle();
        pDAppearanceStream.setBBox(rectangle);
        pDAppearanceStream.setMatrix(AffineTransform.getTranslateInstance(-rectangle.getLowerLeftX(), -rectangle.getLowerLeftY()));
    }

    public PDRectangle handleBorderBox(PDAnnotationSquareCircle pDAnnotationSquareCircle, float f) {
        float[] rectDifferences = pDAnnotationSquareCircle.getRectDifferences();
        if (rectDifferences.length == 0) {
            float f2 = f / 2.0f;
            PDRectangle paddedRectangle = getPaddedRectangle(getRectangle(), f2);
            pDAnnotationSquareCircle.setRectDifferences(f2);
            pDAnnotationSquareCircle.setRectangle(addRectDifferences(getRectangle(), pDAnnotationSquareCircle.getRectDifferences()));
            PDRectangle rectangle = getRectangle();
            PDAppearanceStream normalAppearanceStream = pDAnnotationSquareCircle.getNormalAppearanceStream();
            AffineTransform translateInstance = AffineTransform.getTranslateInstance(-rectangle.getLowerLeftX(), -rectangle.getLowerLeftY());
            normalAppearanceStream.setBBox(rectangle);
            normalAppearanceStream.setMatrix(translateInstance);
            return paddedRectangle;
        }
        return getPaddedRectangle(applyRectDifferences(getRectangle(), rectDifferences), f / 2.0f);
    }
}
