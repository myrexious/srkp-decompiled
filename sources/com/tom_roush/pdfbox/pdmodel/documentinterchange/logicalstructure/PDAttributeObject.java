package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDDictionaryWrapper;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDExportFormatAttributeObject;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDPrintFieldAttributeObject;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDTableAttributeObject;

/* loaded from: classes3.dex */
public abstract class PDAttributeObject extends PDDictionaryWrapper {
    private PDStructureElement structureElement;

    public PDAttributeObject() {
    }

    public PDAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public static PDAttributeObject create(COSDictionary cOSDictionary) {
        String nameAsString = cOSDictionary.getNameAsString(COSName.O);
        if (PDUserAttributeObject.OWNER_USER_PROPERTIES.equals(nameAsString)) {
            return new PDUserAttributeObject(cOSDictionary);
        }
        if (PDListAttributeObject.OWNER_LIST.equals(nameAsString)) {
            return new PDListAttributeObject(cOSDictionary);
        }
        if (PDPrintFieldAttributeObject.OWNER_PRINT_FIELD.equals(nameAsString)) {
            return new PDPrintFieldAttributeObject(cOSDictionary);
        }
        if ("Table".equals(nameAsString)) {
            return new PDTableAttributeObject(cOSDictionary);
        }
        if (PDLayoutAttributeObject.OWNER_LAYOUT.equals(nameAsString)) {
            return new PDLayoutAttributeObject(cOSDictionary);
        }
        if (PDExportFormatAttributeObject.OWNER_XML_1_00.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_HTML_3_20.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_HTML_4_01.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_OEB_1_00.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_RTF_1_05.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_CSS_1_00.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_CSS_2_00.equals(nameAsString)) {
            return new PDExportFormatAttributeObject(cOSDictionary);
        }
        return new PDDefaultAttributeObject(cOSDictionary);
    }

    private PDStructureElement getStructureElement() {
        return this.structureElement;
    }

    public void setStructureElement(PDStructureElement pDStructureElement) {
        this.structureElement = pDStructureElement;
    }

    public String getOwner() {
        return getCOSObject().getNameAsString(COSName.O);
    }

    public void setOwner(String str) {
        getCOSObject().setName(COSName.O, str);
    }

    public boolean isEmpty() {
        return getCOSObject().size() == 1 && getOwner() != null;
    }

    public void potentiallyNotifyChanged(COSBase cOSBase, COSBase cOSBase2) {
        if (isValueChanged(cOSBase, cOSBase2)) {
            notifyChanged();
        }
    }

    private boolean isValueChanged(COSBase cOSBase, COSBase cOSBase2) {
        if (cOSBase == null) {
            return cOSBase2 != null;
        }
        return !cOSBase.equals(cOSBase2);
    }

    public void notifyChanged() {
        if (getStructureElement() != null) {
            getStructureElement().attributeChanged(this);
        }
    }

    public String toString() {
        return "O=" + getOwner();
    }

    public static String arrayToString(Object[] objArr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < objArr.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(objArr[i]);
        }
        return sb.append(']').toString();
    }

    public static String arrayToString(float[] fArr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < fArr.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(fArr[i]);
        }
        return sb.append(']').toString();
    }
}
