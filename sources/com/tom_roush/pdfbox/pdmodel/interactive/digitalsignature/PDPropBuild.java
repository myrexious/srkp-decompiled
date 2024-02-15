package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes3.dex */
public class PDPropBuild implements COSObjectable {
    private COSDictionary dictionary;

    public PDPropBuild() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    public PDPropBuild(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public PDPropBuildDataDict getFilter() {
        COSDictionary cOSDictionary = this.dictionary.getCOSDictionary(COSName.FILTER);
        if (cOSDictionary != null) {
            return new PDPropBuildDataDict(cOSDictionary);
        }
        return null;
    }

    public void setPDPropBuildFilter(PDPropBuildDataDict pDPropBuildDataDict) {
        this.dictionary.setItem(COSName.FILTER, pDPropBuildDataDict);
    }

    public PDPropBuildDataDict getPubSec() {
        COSDictionary cOSDictionary = this.dictionary.getCOSDictionary(COSName.PUB_SEC);
        if (cOSDictionary != null) {
            return new PDPropBuildDataDict(cOSDictionary);
        }
        return null;
    }

    public void setPDPropBuildPubSec(PDPropBuildDataDict pDPropBuildDataDict) {
        this.dictionary.setItem(COSName.PUB_SEC, pDPropBuildDataDict);
    }

    public PDPropBuildDataDict getApp() {
        COSDictionary cOSDictionary = this.dictionary.getCOSDictionary(COSName.APP);
        if (cOSDictionary != null) {
            return new PDPropBuildDataDict(cOSDictionary);
        }
        return null;
    }

    public void setPDPropBuildApp(PDPropBuildDataDict pDPropBuildDataDict) {
        this.dictionary.setItem(COSName.APP, pDPropBuildDataDict);
    }
}
