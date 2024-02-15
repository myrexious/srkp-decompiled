package com.tom_roush.pdfbox.pdmodel.common;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

/* loaded from: classes3.dex */
public class PDTypedDictionaryWrapper extends PDDictionaryWrapper {
    public PDTypedDictionaryWrapper(String str) {
        getCOSObject().setName(COSName.TYPE, str);
    }

    public PDTypedDictionaryWrapper(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getType() {
        return getCOSObject().getNameAsString(COSName.TYPE);
    }
}
