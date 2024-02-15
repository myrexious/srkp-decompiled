package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSDictionaryMap;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class PDAppearanceEntry implements COSObjectable {
    private COSBase entry;

    private PDAppearanceEntry() {
    }

    public PDAppearanceEntry(COSBase cOSBase) {
        this.entry = cOSBase;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.entry;
    }

    public boolean isSubDictionary() {
        return !(this.entry instanceof COSStream);
    }

    public boolean isStream() {
        return this.entry instanceof COSStream;
    }

    public PDAppearanceStream getAppearanceStream() {
        if (!isStream()) {
            throw new IllegalStateException("This entry is not an appearance stream");
        }
        return new PDAppearanceStream((COSStream) this.entry);
    }

    public Map<COSName, PDAppearanceStream> getSubDictionary() {
        if (!isSubDictionary()) {
            throw new IllegalStateException("This entry is not an appearance subdictionary");
        }
        COSDictionary cOSDictionary = (COSDictionary) this.entry;
        HashMap hashMap = new HashMap();
        for (COSName cOSName : cOSDictionary.keySet()) {
            COSBase dictionaryObject = cOSDictionary.getDictionaryObject(cOSName);
            if (dictionaryObject instanceof COSStream) {
                hashMap.put(cOSName, new PDAppearanceStream((COSStream) dictionaryObject));
            }
        }
        return new COSDictionaryMap(hashMap, cOSDictionary);
    }
}
