package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDAnnotationPopup extends PDAnnotation {
    public static final String SUB_TYPE = "Popup";

    public PDAnnotationPopup() {
        getCOSObject().setName(COSName.SUBTYPE, SUB_TYPE);
    }

    public PDAnnotationPopup(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setOpen(boolean z) {
        getCOSObject().setBoolean("Open", z);
    }

    public boolean getOpen() {
        return getCOSObject().getBoolean("Open", false);
    }

    public void setParent(PDAnnotationMarkup pDAnnotationMarkup) {
        getCOSObject().setItem(COSName.PARENT, (COSBase) pDAnnotationMarkup.getCOSObject());
    }

    public PDAnnotationMarkup getParent() {
        try {
            return (PDAnnotationMarkup) PDAnnotation.createAnnotation(getCOSObject().getDictionaryObject(COSName.PARENT, COSName.P));
        } catch (IOException unused) {
            return null;
        }
    }
}
