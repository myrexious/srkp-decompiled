package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

/* loaded from: classes3.dex */
public class PDActionMovie extends PDAction {
    public static final String SUB_TYPE = "Movie";

    public PDActionMovie() {
        setSubType(SUB_TYPE);
    }

    public PDActionMovie(COSDictionary cOSDictionary) {
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
}
