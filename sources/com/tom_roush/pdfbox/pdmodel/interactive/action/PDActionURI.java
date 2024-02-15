package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.util.Charsets;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class PDActionURI extends PDAction {
    public static final String SUB_TYPE = "URI";

    public PDActionURI() {
        setSubType(SUB_TYPE);
    }

    public PDActionURI(COSDictionary cOSDictionary) {
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

    public String getURI() {
        COSBase dictionaryObject = this.action.getDictionaryObject(COSName.URI);
        if (dictionaryObject instanceof COSString) {
            byte[] bytes = ((COSString) dictionaryObject).getBytes();
            if (bytes.length >= 2) {
                byte b = bytes[0];
                if ((b & UByte.MAX_VALUE) == 254 && (bytes[1] & UByte.MAX_VALUE) == 255) {
                    return this.action.getString(COSName.URI);
                }
                if ((b & UByte.MAX_VALUE) == 255 && (bytes[1] & UByte.MAX_VALUE) == 254) {
                    return this.action.getString(COSName.URI);
                }
            }
            return new String(bytes, Charsets.UTF_8);
        }
        return null;
    }

    public void setURI(String str) {
        this.action.setString(COSName.URI, str);
    }

    public boolean shouldTrackMousePosition() {
        return this.action.getBoolean("IsMap", false);
    }

    public void setTrackMousePosition(boolean z) {
        this.action.setBoolean("IsMap", z);
    }
}
