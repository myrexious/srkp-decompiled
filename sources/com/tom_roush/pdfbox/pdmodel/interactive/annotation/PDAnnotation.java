package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import java.io.IOException;
import java.util.Calendar;

/* loaded from: classes3.dex */
public abstract class PDAnnotation implements COSObjectable {
    private static final int FLAG_HIDDEN = 2;
    private static final int FLAG_INVISIBLE = 1;
    private static final int FLAG_LOCKED = 128;
    private static final int FLAG_LOCKED_CONTENTS = 512;
    private static final int FLAG_NO_ROTATE = 16;
    private static final int FLAG_NO_VIEW = 32;
    private static final int FLAG_NO_ZOOM = 8;
    private static final int FLAG_PRINTED = 4;
    private static final int FLAG_READ_ONLY = 64;
    private static final int FLAG_TOGGLE_NO_VIEW = 256;
    private final COSDictionary dictionary;

    public void constructAppearances() {
    }

    public void constructAppearances(PDDocument pDDocument) {
    }

    public static PDAnnotation createAnnotation(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSDictionary) {
            COSDictionary cOSDictionary = (COSDictionary) cOSBase;
            String nameAsString = cOSDictionary.getNameAsString(COSName.SUBTYPE);
            if ("FileAttachment".equals(nameAsString)) {
                return new PDAnnotationFileAttachment(cOSDictionary);
            }
            if ("Line".equals(nameAsString)) {
                return new PDAnnotationLine(cOSDictionary);
            }
            if ("Link".equals(nameAsString)) {
                return new PDAnnotationLink(cOSDictionary);
            }
            if (PDAnnotationPopup.SUB_TYPE.equals(nameAsString)) {
                return new PDAnnotationPopup(cOSDictionary);
            }
            if ("Stamp".equals(nameAsString)) {
                return new PDAnnotationRubberStamp(cOSDictionary);
            }
            if ("Square".equals(nameAsString) || "Circle".equals(nameAsString)) {
                return new PDAnnotationSquareCircle(cOSDictionary);
            }
            if ("Text".equals(nameAsString)) {
                return new PDAnnotationText(cOSDictionary);
            }
            if ("Highlight".equals(nameAsString) || "Underline".equals(nameAsString) || "Squiggly".equals(nameAsString) || "StrikeOut".equals(nameAsString)) {
                return new PDAnnotationTextMarkup(cOSDictionary);
            }
            if (PDAnnotationWidget.SUB_TYPE.equals(nameAsString)) {
                return new PDAnnotationWidget(cOSDictionary);
            }
            if ("FreeText".equals(nameAsString) || "Polygon".equals(nameAsString) || PDAnnotationMarkup.SUB_TYPE_POLYLINE.equals(nameAsString) || "Caret".equals(nameAsString) || "Ink".equals(nameAsString) || "Sound".equals(nameAsString)) {
                return new PDAnnotationMarkup(cOSDictionary);
            }
            PDAnnotationUnknown pDAnnotationUnknown = new PDAnnotationUnknown(cOSDictionary);
            Log.d("PdfBox-Android", "Unknown or unsupported annotation subtype " + nameAsString);
            return pDAnnotationUnknown;
        }
        throw new IOException("Error: Unknown annotation type " + cOSBase);
    }

    public PDAnnotation() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.ANNOT);
    }

    public PDAnnotation(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.TYPE);
        if (dictionaryObject == null) {
            cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.ANNOT);
        } else if (COSName.ANNOT.equals(dictionaryObject)) {
        } else {
            Log.w("PdfBox-Android", "Annotation has type " + dictionaryObject + ", further mayhem may follow");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PDAnnotation) {
            return ((PDAnnotation) obj).getCOSObject().equals(getCOSObject());
        }
        return false;
    }

    public int hashCode() {
        return this.dictionary.hashCode();
    }

    public PDRectangle getRectangle() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.RECT);
        if (cOSArray != null) {
            if (cOSArray.size() == 4 && (cOSArray.getObject(0) instanceof COSNumber) && (cOSArray.getObject(1) instanceof COSNumber) && (cOSArray.getObject(2) instanceof COSNumber) && (cOSArray.getObject(3) instanceof COSNumber)) {
                return new PDRectangle(cOSArray);
            }
            Log.w("PdfBox-Android", cOSArray + " is not a rectangle array, returning null");
        }
        return null;
    }

    public void setRectangle(PDRectangle pDRectangle) {
        this.dictionary.setItem(COSName.RECT, (COSBase) pDRectangle.getCOSArray());
    }

    public int getAnnotationFlags() {
        return getCOSObject().getInt(COSName.F, 0);
    }

    public void setAnnotationFlags(int i) {
        getCOSObject().setInt(COSName.F, i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public COSName getAppearanceState() {
        return getCOSObject().getCOSName(COSName.AS);
    }

    public void setAppearanceState(String str) {
        getCOSObject().setName(COSName.AS, str);
    }

    public PDAppearanceDictionary getAppearance() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.AP);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDAppearanceDictionary((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setAppearance(PDAppearanceDictionary pDAppearanceDictionary) {
        this.dictionary.setItem(COSName.AP, pDAppearanceDictionary);
    }

    public PDAppearanceStream getNormalAppearanceStream() {
        PDAppearanceEntry normalAppearance;
        PDAppearanceDictionary appearance = getAppearance();
        if (appearance == null || (normalAppearance = appearance.getNormalAppearance()) == null) {
            return null;
        }
        if (normalAppearance.isSubDictionary()) {
            return normalAppearance.getSubDictionary().get(getAppearanceState());
        }
        return normalAppearance.getAppearanceStream();
    }

    public boolean isInvisible() {
        return getCOSObject().getFlag(COSName.F, 1);
    }

    public void setInvisible(boolean z) {
        getCOSObject().setFlag(COSName.F, 1, z);
    }

    public boolean isHidden() {
        return getCOSObject().getFlag(COSName.F, 2);
    }

    public void setHidden(boolean z) {
        getCOSObject().setFlag(COSName.F, 2, z);
    }

    public boolean isPrinted() {
        return getCOSObject().getFlag(COSName.F, 4);
    }

    public void setPrinted(boolean z) {
        getCOSObject().setFlag(COSName.F, 4, z);
    }

    public boolean isNoZoom() {
        return getCOSObject().getFlag(COSName.F, 8);
    }

    public void setNoZoom(boolean z) {
        getCOSObject().setFlag(COSName.F, 8, z);
    }

    public boolean isNoRotate() {
        return getCOSObject().getFlag(COSName.F, 16);
    }

    public void setNoRotate(boolean z) {
        getCOSObject().setFlag(COSName.F, 16, z);
    }

    public boolean isNoView() {
        return getCOSObject().getFlag(COSName.F, 32);
    }

    public void setNoView(boolean z) {
        getCOSObject().setFlag(COSName.F, 32, z);
    }

    public boolean isReadOnly() {
        return getCOSObject().getFlag(COSName.F, 64);
    }

    public void setReadOnly(boolean z) {
        getCOSObject().setFlag(COSName.F, 64, z);
    }

    public boolean isLocked() {
        return getCOSObject().getFlag(COSName.F, 128);
    }

    public void setLocked(boolean z) {
        getCOSObject().setFlag(COSName.F, 128, z);
    }

    public boolean isToggleNoView() {
        return getCOSObject().getFlag(COSName.F, 256);
    }

    public void setToggleNoView(boolean z) {
        getCOSObject().setFlag(COSName.F, 256, z);
    }

    public boolean isLockedContents() {
        return getCOSObject().getFlag(COSName.F, 512);
    }

    public void setLockedContents(boolean z) {
        getCOSObject().setFlag(COSName.F, 512, z);
    }

    public String getContents() {
        return this.dictionary.getString(COSName.CONTENTS);
    }

    public void setContents(String str) {
        this.dictionary.setString(COSName.CONTENTS, str);
    }

    public String getModifiedDate() {
        return getCOSObject().getString(COSName.M);
    }

    public void setModifiedDate(String str) {
        getCOSObject().setString(COSName.M, str);
    }

    public void setModifiedDate(Calendar calendar) {
        getCOSObject().setDate(COSName.M, calendar);
    }

    public String getAnnotationName() {
        return getCOSObject().getString(COSName.NM);
    }

    public void setAnnotationName(String str) {
        getCOSObject().setString(COSName.NM, str);
    }

    public int getStructParent() {
        return getCOSObject().getInt(COSName.STRUCT_PARENT);
    }

    public void setStructParent(int i) {
        getCOSObject().setInt(COSName.STRUCT_PARENT, i);
    }

    public PDPropertyList getOptionalContent() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.OC);
        if (dictionaryObject instanceof COSDictionary) {
            return PDPropertyList.create((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setOptionalContent(PDPropertyList pDPropertyList) {
        getCOSObject().setItem(COSName.OC, pDPropertyList);
    }

    public COSArray getBorder() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.BORDER);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            if (cOSArray.size() < 3) {
                COSArray cOSArray2 = new COSArray();
                cOSArray2.addAll(cOSArray);
                while (cOSArray2.size() < 3) {
                    cOSArray2.add((COSBase) COSInteger.ZERO);
                }
                return cOSArray2;
            }
            return cOSArray;
        }
        COSArray cOSArray3 = new COSArray();
        cOSArray3.add((COSBase) COSInteger.ZERO);
        cOSArray3.add((COSBase) COSInteger.ZERO);
        cOSArray3.add((COSBase) COSInteger.ONE);
        return cOSArray3;
    }

    public void setBorder(COSArray cOSArray) {
        getCOSObject().setItem(COSName.BORDER, (COSBase) cOSArray);
    }

    public void setColor(PDColor pDColor) {
        getCOSObject().setItem(COSName.C, (COSBase) pDColor.toCOSArray());
    }

    public PDColor getColor() {
        return getColor(COSName.C);
    }

    public PDColor getColor(COSName cOSName) {
        COSBase item = getCOSObject().getItem(cOSName);
        PDColorSpace pDColorSpace = null;
        if (item instanceof COSArray) {
            COSArray cOSArray = (COSArray) item;
            int size = cOSArray.size();
            if (size == 1) {
                pDColorSpace = PDDeviceGray.INSTANCE;
            } else if (size == 3) {
                pDColorSpace = PDDeviceRGB.INSTANCE;
            }
            return new PDColor(cOSArray, pDColorSpace);
        }
        return null;
    }

    public String getSubtype() {
        return getCOSObject().getNameAsString(COSName.SUBTYPE);
    }

    public void setPage(PDPage pDPage) {
        getCOSObject().setItem(COSName.P, pDPage);
    }

    public PDPage getPage() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.P);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDPage((COSDictionary) dictionaryObject);
        }
        return null;
    }
}
