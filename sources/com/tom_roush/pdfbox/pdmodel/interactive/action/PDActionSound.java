package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;

/* loaded from: classes3.dex */
public class PDActionSound extends PDAction {
    public static final String SUB_TYPE = "Sound";

    public PDActionSound() {
        setSubType("Sound");
    }

    public PDActionSound(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    @Deprecated
    public String getS() {
        return this.action.getNameAsString(COSName.S);
    }

    @Deprecated
    public void setS(String str) {
        this.action.setName(COSName.S, str);
    }

    public void setSound(COSStream cOSStream) {
        this.action.setItem(COSName.SOUND, (COSBase) cOSStream);
    }

    public COSStream getSound() {
        COSBase dictionaryObject = this.action.getDictionaryObject(COSName.SOUND);
        if (dictionaryObject instanceof COSStream) {
            return (COSStream) dictionaryObject;
        }
        return null;
    }

    public void setVolume(float f) {
        if (f < -1.0f || f > 1.0f) {
            throw new IllegalArgumentException("volume outside of the range −1.0 to 1.0");
        }
        this.action.setFloat(COSName.VOLUME, f);
    }

    public float getVolume() {
        COSBase dictionaryObject = this.action.getDictionaryObject(COSName.VOLUME);
        if (dictionaryObject instanceof COSNumber) {
            float floatValue = ((COSNumber) dictionaryObject).floatValue();
            if (floatValue < -1.0f || floatValue > 1.0f) {
                return 1.0f;
            }
            return floatValue;
        }
        return 1.0f;
    }

    public void setSynchronous(boolean z) {
        this.action.setBoolean(COSName.SYNCHRONOUS, z);
    }

    public boolean getSynchronous() {
        COSBase dictionaryObject = this.action.getDictionaryObject(COSName.SYNCHRONOUS);
        if (dictionaryObject instanceof COSBoolean) {
            return ((COSBoolean) dictionaryObject).getValue();
        }
        return false;
    }

    public void setRepeat(boolean z) {
        this.action.setBoolean(COSName.REPEAT, z);
    }

    public boolean getRepeat() {
        COSBase dictionaryObject = this.action.getDictionaryObject(COSName.REPEAT);
        if (dictionaryObject instanceof COSBoolean) {
            return ((COSBoolean) dictionaryObject).getValue();
        }
        return false;
    }

    public void setMix(boolean z) {
        this.action.setBoolean(COSName.MIX, z);
    }

    public boolean getMix() {
        COSBase dictionaryObject = this.action.getDictionaryObject(COSName.MIX);
        if (dictionaryObject instanceof COSBoolean) {
            return ((COSBoolean) dictionaryObject).getValue();
        }
        return false;
    }
}
