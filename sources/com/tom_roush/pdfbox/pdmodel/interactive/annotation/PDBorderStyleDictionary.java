package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.PDLineDashPattern;

/* loaded from: classes3.dex */
public class PDBorderStyleDictionary implements COSObjectable {
    public static final String STYLE_BEVELED = "B";
    public static final String STYLE_DASHED = "D";
    public static final String STYLE_INSET = "I";
    public static final String STYLE_SOLID = "S";
    public static final String STYLE_UNDERLINE = "U";
    private final COSDictionary dictionary;

    public PDBorderStyleDictionary() {
        this.dictionary = new COSDictionary();
    }

    public PDBorderStyleDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public void setWidth(float f) {
        int i = (int) f;
        if (f == i) {
            getCOSObject().setInt(COSName.W, i);
        } else {
            getCOSObject().setFloat(COSName.W, f);
        }
    }

    public float getWidth() {
        if (getCOSObject().getDictionaryObject(COSName.W) instanceof COSName) {
            return 0.0f;
        }
        return getCOSObject().getFloat(COSName.W, 1.0f);
    }

    public void setStyle(String str) {
        getCOSObject().setName(COSName.S, str);
    }

    public String getStyle() {
        return getCOSObject().getNameAsString(COSName.S, "S");
    }

    public void setDashStyle(COSArray cOSArray) {
        getCOSObject().setItem(COSName.D, (COSBase) cOSArray);
    }

    public PDLineDashPattern getDashStyle() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.D);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) COSInteger.THREE);
            getCOSObject().setItem(COSName.D, (COSBase) cOSArray);
        }
        return new PDLineDashPattern(cOSArray, 0);
    }
}
