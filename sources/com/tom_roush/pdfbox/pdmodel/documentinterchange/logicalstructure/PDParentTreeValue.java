package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes3.dex */
public class PDParentTreeValue implements COSObjectable {
    COSObjectable obj;

    public PDParentTreeValue(COSArray cOSArray) {
        this.obj = cOSArray;
    }

    public PDParentTreeValue(COSDictionary cOSDictionary) {
        this.obj = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.obj.getCOSObject();
    }

    public String toString() {
        return this.obj.toString();
    }
}
