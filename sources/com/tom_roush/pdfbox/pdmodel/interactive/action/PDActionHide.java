package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

/* loaded from: classes3.dex */
public class PDActionHide extends PDAction {
    public static final String SUB_TYPE = "Hide";

    public PDActionHide() {
        setSubType(SUB_TYPE);
    }

    public PDActionHide(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public COSBase getT() {
        return this.action.getDictionaryObject(COSName.T);
    }

    public void setT(COSBase cOSBase) {
        this.action.setItem(COSName.T, cOSBase);
    }

    public boolean getH() {
        return this.action.getBoolean(COSName.H, true);
    }

    public void setH(boolean z) {
        this.action.setItem(COSName.H, (COSBase) COSBoolean.getBoolean(z));
    }
}
