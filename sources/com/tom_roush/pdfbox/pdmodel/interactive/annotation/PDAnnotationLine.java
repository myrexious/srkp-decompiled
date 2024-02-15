package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers.PDLineAppearanceHandler;

/* loaded from: classes3.dex */
public class PDAnnotationLine extends PDAnnotationMarkup {
    public static final String IT_LINE_ARROW = "LineArrow";
    public static final String IT_LINE_DIMENSION = "LineDimension";
    public static final String LE_BUTT = "Butt";
    public static final String LE_CIRCLE = "Circle";
    public static final String LE_CLOSED_ARROW = "ClosedArrow";
    public static final String LE_DIAMOND = "Diamond";
    public static final String LE_NONE = "None";
    public static final String LE_OPEN_ARROW = "OpenArrow";
    public static final String LE_R_CLOSED_ARROW = "RClosedArrow";
    public static final String LE_R_OPEN_ARROW = "ROpenArrow";
    public static final String LE_SLASH = "Slash";
    public static final String LE_SQUARE = "Square";
    public static final String SUB_TYPE = "Line";
    private PDAppearanceHandler customAppearanceHandler;

    public PDAnnotationLine() {
        getCOSObject().setName(COSName.SUBTYPE, "Line");
        setLine(new float[]{0.0f, 0.0f, 0.0f, 0.0f});
    }

    public PDAnnotationLine(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setLine(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        getCOSObject().setItem(COSName.L, (COSBase) cOSArray);
    }

    public float[] getLine() {
        return ((COSArray) getCOSObject().getDictionaryObject(COSName.L)).toFloatArray();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup
    public void setStartPointEndingStyle(String str) {
        if (str == null) {
            str = "None";
        }
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.LE);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            if (cOSArray.size() != 0) {
                cOSArray.setName(0, str);
                return;
            }
        }
        COSArray cOSArray2 = new COSArray();
        cOSArray2.add((COSBase) COSName.getPDFName(str));
        cOSArray2.add((COSBase) COSName.getPDFName("None"));
        getCOSObject().setItem(COSName.LE, (COSBase) cOSArray2);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup
    public String getStartPointEndingStyle() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.LE);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            return cOSArray.size() >= 2 ? cOSArray.getName(0) : "None";
        }
        return "None";
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup
    public void setEndPointEndingStyle(String str) {
        if (str == null) {
            str = "None";
        }
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.LE);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            if (cOSArray.size() >= 2) {
                cOSArray.setName(1, str);
                return;
            }
        }
        COSArray cOSArray2 = new COSArray();
        cOSArray2.add((COSBase) COSName.getPDFName("None"));
        cOSArray2.add((COSBase) COSName.getPDFName(str));
        getCOSObject().setItem(COSName.LE, (COSBase) cOSArray2);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup
    public String getEndPointEndingStyle() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.LE);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            return cOSArray.size() >= 2 ? cOSArray.getName(1) : "None";
        }
        return "None";
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup
    public void setInteriorColor(PDColor pDColor) {
        getCOSObject().setItem(COSName.IC, (COSBase) pDColor.toCOSArray());
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup
    public PDColor getInteriorColor() {
        return getColor(COSName.IC);
    }

    public void setCaption(boolean z) {
        getCOSObject().setBoolean(COSName.CAP, z);
    }

    public boolean getCaption() {
        return getCOSObject().getBoolean(COSName.CAP, false);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup
    public void setBorderStyle(PDBorderStyleDictionary pDBorderStyleDictionary) {
        getCOSObject().setItem(COSName.BS, pDBorderStyleDictionary);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup
    public PDBorderStyleDictionary getBorderStyle() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.BS);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDBorderStyleDictionary((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public float getLeaderLineLength() {
        return getCOSObject().getFloat(COSName.LL, 0.0f);
    }

    public void setLeaderLineLength(float f) {
        getCOSObject().setFloat(COSName.LL, f);
    }

    public float getLeaderLineExtensionLength() {
        return getCOSObject().getFloat(COSName.LLE, 0.0f);
    }

    public void setLeaderLineExtensionLength(float f) {
        getCOSObject().setFloat(COSName.LLE, f);
    }

    public float getLeaderLineOffsetLength() {
        return getCOSObject().getFloat(COSName.LLO, 0.0f);
    }

    public void setLeaderLineOffsetLength(float f) {
        getCOSObject().setFloat(COSName.LLO, f);
    }

    public String getCaptionPositioning() {
        return getCOSObject().getNameAsString(COSName.CP);
    }

    public void setCaptionPositioning(String str) {
        getCOSObject().setName(COSName.CP, str);
    }

    public void setCaptionHorizontalOffset(float f) {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.CO);
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.setFloatArray(new float[]{f, 0.0f});
            getCOSObject().setItem(COSName.CO, (COSBase) cOSArray2);
            return;
        }
        cOSArray.set(0, (COSBase) new COSFloat(f));
    }

    public float getCaptionHorizontalOffset() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.CO);
        if (cOSArray != null) {
            return cOSArray.toFloatArray()[0];
        }
        return 0.0f;
    }

    public void setCaptionVerticalOffset(float f) {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.CO);
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.setFloatArray(new float[]{0.0f, f});
            getCOSObject().setItem(COSName.CO, (COSBase) cOSArray2);
            return;
        }
        cOSArray.set(1, (COSBase) new COSFloat(f));
    }

    public float getCaptionVerticalOffset() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.CO);
        if (cOSArray != null) {
            return cOSArray.toFloatArray()[1];
        }
        return 0.0f;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup
    public void setCustomAppearanceHandler(PDAppearanceHandler pDAppearanceHandler) {
        this.customAppearanceHandler = pDAppearanceHandler;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup, com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation
    public void constructAppearances() {
        constructAppearances(null);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup, com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation
    public void constructAppearances(PDDocument pDDocument) {
        PDAppearanceHandler pDAppearanceHandler = this.customAppearanceHandler;
        if (pDAppearanceHandler == null) {
            new PDLineAppearanceHandler(this, pDDocument).generateAppearanceStreams();
        } else {
            pDAppearanceHandler.generateAppearanceStreams();
        }
    }
}
