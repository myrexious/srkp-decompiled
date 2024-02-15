package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;

/* loaded from: classes3.dex */
public class PDAppearanceCharacteristicsDictionary implements COSObjectable {
    private final COSDictionary dictionary;

    public PDAppearanceCharacteristicsDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public int getRotation() {
        return getCOSObject().getInt(COSName.R, 0);
    }

    public void setRotation(int i) {
        getCOSObject().setInt(COSName.R, i);
    }

    public PDColor getBorderColour() {
        return getColor(COSName.BC);
    }

    public void setBorderColour(PDColor pDColor) {
        getCOSObject().setItem(COSName.BC, (COSBase) pDColor.toCOSArray());
    }

    public PDColor getBackground() {
        return getColor(COSName.BG);
    }

    public void setBackground(PDColor pDColor) {
        getCOSObject().setItem(COSName.BG, (COSBase) pDColor.toCOSArray());
    }

    public String getNormalCaption() {
        return getCOSObject().getString(COSName.CA);
    }

    public void setNormalCaption(String str) {
        getCOSObject().setString(COSName.CA, str);
    }

    public String getRolloverCaption() {
        return getCOSObject().getString(COSName.RC);
    }

    public void setRolloverCaption(String str) {
        getCOSObject().setString(COSName.RC, str);
    }

    public String getAlternateCaption() {
        return getCOSObject().getString(COSName.AC);
    }

    public void setAlternateCaption(String str) {
        getCOSObject().setString(COSName.AC, str);
    }

    public PDFormXObject getNormalIcon() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.I);
        if (dictionaryObject instanceof COSStream) {
            return new PDFormXObject((COSStream) dictionaryObject);
        }
        return null;
    }

    public PDFormXObject getRolloverIcon() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.RI);
        if (dictionaryObject instanceof COSStream) {
            return new PDFormXObject((COSStream) dictionaryObject);
        }
        return null;
    }

    public PDFormXObject getAlternateIcon() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.IX);
        if (dictionaryObject instanceof COSStream) {
            return new PDFormXObject((COSStream) dictionaryObject);
        }
        return null;
    }

    private PDColor getColor(COSName cOSName) {
        PDColorSpace pDColorSpace;
        COSBase item = getCOSObject().getItem(cOSName);
        if (item instanceof COSArray) {
            COSArray cOSArray = (COSArray) item;
            int size = cOSArray.size();
            if (size == 1) {
                pDColorSpace = PDDeviceGray.INSTANCE;
            } else if (size != 3) {
                return null;
            } else {
                pDColorSpace = PDDeviceRGB.INSTANCE;
            }
            return new PDColor(cOSArray, pDColorSpace);
        }
        return null;
    }
}
