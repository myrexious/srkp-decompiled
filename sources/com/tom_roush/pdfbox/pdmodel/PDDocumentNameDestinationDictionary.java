package com.tom_roush.pdfbox.pdmodel;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDDocumentNameDestinationDictionary implements COSObjectable {
    private final COSDictionary nameDictionary;

    public PDDocumentNameDestinationDictionary(COSDictionary cOSDictionary) {
        this.nameDictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.nameDictionary;
    }

    public PDDestination getDestination(String str) throws IOException {
        COSBase dictionaryObject = this.nameDictionary.getDictionaryObject(str);
        if (dictionaryObject instanceof COSArray) {
            return PDDestination.create(dictionaryObject);
        }
        if (dictionaryObject instanceof COSDictionary) {
            COSDictionary cOSDictionary = (COSDictionary) dictionaryObject;
            if (cOSDictionary.containsKey(COSName.D)) {
                return PDDestination.create(cOSDictionary.getDictionaryObject(COSName.D));
            }
            return null;
        }
        return null;
    }
}
