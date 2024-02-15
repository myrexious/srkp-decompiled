package com.tom_roush.pdfbox.pdmodel.encryption;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes3.dex */
public class PDCryptFilterDictionary implements COSObjectable {
    protected COSDictionary cryptFilterDictionary;

    public PDCryptFilterDictionary() {
        this.cryptFilterDictionary = null;
        this.cryptFilterDictionary = new COSDictionary();
    }

    public PDCryptFilterDictionary(COSDictionary cOSDictionary) {
        this.cryptFilterDictionary = cOSDictionary;
    }

    @Deprecated
    public COSDictionary getCOSDictionary() {
        return this.cryptFilterDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.cryptFilterDictionary;
    }

    public void setLength(int i) {
        this.cryptFilterDictionary.setInt(COSName.LENGTH, i);
    }

    public int getLength() {
        return this.cryptFilterDictionary.getInt(COSName.LENGTH, 40);
    }

    public void setCryptFilterMethod(COSName cOSName) {
        this.cryptFilterDictionary.setItem(COSName.CFM, (COSBase) cOSName);
    }

    public COSName getCryptFilterMethod() {
        return (COSName) this.cryptFilterDictionary.getDictionaryObject(COSName.CFM);
    }

    public boolean isEncryptMetaData() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.ENCRYPT_META_DATA);
        if (dictionaryObject instanceof COSBoolean) {
            return ((COSBoolean) dictionaryObject).getValue();
        }
        return true;
    }

    public void setEncryptMetaData(boolean z) {
        getCOSObject().setBoolean(COSName.ENCRYPT_META_DATA, z);
    }
}
