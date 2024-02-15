package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes3.dex */
public class PDAppearanceDictionary implements COSObjectable {
    private final COSDictionary dictionary;

    public PDAppearanceDictionary() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.N, (COSBase) new COSDictionary());
    }

    public PDAppearanceDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public PDAppearanceEntry getNormalAppearance() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.N);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDAppearanceEntry(dictionaryObject);
        }
        return null;
    }

    public void setNormalAppearance(PDAppearanceEntry pDAppearanceEntry) {
        this.dictionary.setItem(COSName.N, pDAppearanceEntry);
    }

    public void setNormalAppearance(PDAppearanceStream pDAppearanceStream) {
        this.dictionary.setItem(COSName.N, pDAppearanceStream);
    }

    public PDAppearanceEntry getRolloverAppearance() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.R);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDAppearanceEntry(dictionaryObject);
        }
        return getNormalAppearance();
    }

    public void setRolloverAppearance(PDAppearanceEntry pDAppearanceEntry) {
        this.dictionary.setItem(COSName.R, pDAppearanceEntry);
    }

    public void setRolloverAppearance(PDAppearanceStream pDAppearanceStream) {
        this.dictionary.setItem(COSName.R, pDAppearanceStream);
    }

    public PDAppearanceEntry getDownAppearance() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.D);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDAppearanceEntry(dictionaryObject);
        }
        return getNormalAppearance();
    }

    public void setDownAppearance(PDAppearanceEntry pDAppearanceEntry) {
        this.dictionary.setItem(COSName.D, pDAppearanceEntry);
    }

    public void setDownAppearance(PDAppearanceStream pDAppearanceStream) {
        this.dictionary.setItem(COSName.D, pDAppearanceStream);
    }
}
