package com.tom_roush.pdfbox.pdmodel.interactive.pagenavigation;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDDictionaryWrapper;

/* loaded from: classes3.dex */
public final class PDTransition extends PDDictionaryWrapper {
    public PDTransition() {
        this(PDTransitionStyle.R);
    }

    public PDTransition(PDTransitionStyle pDTransitionStyle) {
        getCOSObject().setName(COSName.TYPE, COSName.TRANS.getName());
        getCOSObject().setName(COSName.S, pDTransitionStyle.name());
    }

    public PDTransition(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getStyle() {
        return getCOSObject().getNameAsString(COSName.S, PDTransitionStyle.R.name());
    }

    public String getDimension() {
        return getCOSObject().getNameAsString(COSName.DM, PDTransitionDimension.H.name());
    }

    public void setDimension(PDTransitionDimension pDTransitionDimension) {
        getCOSObject().setName(COSName.DM, pDTransitionDimension.name());
    }

    public String getMotion() {
        return getCOSObject().getNameAsString(COSName.M, PDTransitionMotion.I.name());
    }

    public void setMotion(PDTransitionMotion pDTransitionMotion) {
        getCOSObject().setName(COSName.M, pDTransitionMotion.name());
    }

    public COSBase getDirection() {
        COSBase item = getCOSObject().getItem(COSName.DI);
        return item == null ? COSInteger.ZERO : item;
    }

    public void setDirection(PDTransitionDirection pDTransitionDirection) {
        getCOSObject().setItem(COSName.DI, pDTransitionDirection.getCOSBase());
    }

    public float getDuration() {
        return getCOSObject().getFloat(COSName.D, 1.0f);
    }

    public void setDuration(float f) {
        getCOSObject().setItem(COSName.D, (COSBase) new COSFloat(f));
    }

    public float getFlyScale() {
        return getCOSObject().getFloat(COSName.SS, 1.0f);
    }

    public void setFlyScale(float f) {
        getCOSObject().setItem(COSName.SS, (COSBase) new COSFloat(f));
    }

    public boolean isFlyAreaOpaque() {
        return getCOSObject().getBoolean(COSName.B, false);
    }

    public void setFlyAreaOpaque(boolean z) {
        getCOSObject().setItem(COSName.B, (COSBase) COSBoolean.getBoolean(z));
    }
}
