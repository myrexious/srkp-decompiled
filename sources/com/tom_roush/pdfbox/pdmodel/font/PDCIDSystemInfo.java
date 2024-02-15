package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes3.dex */
public final class PDCIDSystemInfo implements COSObjectable {
    private final COSDictionary dictionary;

    PDCIDSystemInfo(String str, String str2, int i) {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setString(COSName.REGISTRY, str);
        cOSDictionary.setString(COSName.ORDERING, str2);
        cOSDictionary.setInt(COSName.SUPPLEMENT, i);
    }

    public PDCIDSystemInfo(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    public String getRegistry() {
        return this.dictionary.getNameAsString(COSName.REGISTRY);
    }

    public String getOrdering() {
        return this.dictionary.getNameAsString(COSName.ORDERING);
    }

    public int getSupplement() {
        return this.dictionary.getInt(COSName.SUPPLEMENT);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public String toString() {
        return getRegistry() + "-" + getOrdering() + "-" + getSupplement();
    }
}
