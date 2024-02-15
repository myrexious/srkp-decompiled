package com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentMembershipDictionary;

/* loaded from: classes3.dex */
public class PDPropertyList implements COSObjectable {
    protected final COSDictionary dict;

    public static PDPropertyList create(COSDictionary cOSDictionary) {
        COSBase item = cOSDictionary.getItem(COSName.TYPE);
        if (COSName.OCG.equals(item)) {
            return new PDOptionalContentGroup(cOSDictionary);
        }
        if (COSName.OCMD.equals(item)) {
            return new PDOptionalContentMembershipDictionary(cOSDictionary);
        }
        return new PDPropertyList(cOSDictionary);
    }

    public PDPropertyList() {
        this.dict = new COSDictionary();
    }

    public PDPropertyList(COSDictionary cOSDictionary) {
        this.dict = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dict;
    }
}
