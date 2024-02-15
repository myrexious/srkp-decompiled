package com.tom_roush.pdfbox.pdmodel.common;

import com.tom_roush.pdfbox.cos.COSDictionary;

/* loaded from: classes3.dex */
public class PDDictionaryWrapper implements COSObjectable {
    private final COSDictionary dictionary;

    public PDDictionaryWrapper() {
        this.dictionary = new COSDictionary();
    }

    public PDDictionaryWrapper(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PDDictionaryWrapper) {
            return this.dictionary.equals(((PDDictionaryWrapper) obj).dictionary);
        }
        return false;
    }

    public int hashCode() {
        return this.dictionary.hashCode();
    }
}
