package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import org.apache.xmpbox.schema.XMPRightsManagementSchema;

/* loaded from: classes3.dex */
public class PDMarkInfo implements COSObjectable {
    private final COSDictionary dictionary;

    public PDMarkInfo() {
        this.dictionary = new COSDictionary();
    }

    public PDMarkInfo(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public boolean isMarked() {
        return this.dictionary.getBoolean(XMPRightsManagementSchema.MARKED, false);
    }

    public void setMarked(boolean z) {
        this.dictionary.setBoolean(XMPRightsManagementSchema.MARKED, z);
    }

    public boolean usesUserProperties() {
        return this.dictionary.getBoolean(PDUserAttributeObject.OWNER_USER_PROPERTIES, false);
    }

    public void setUserProperties(boolean z) {
        this.dictionary.setBoolean(PDUserAttributeObject.OWNER_USER_PROPERTIES, z);
    }

    public boolean isSuspect() {
        return this.dictionary.getBoolean("Suspects", false);
    }

    public void setSuspect(boolean z) {
        this.dictionary.setBoolean("Suspects", false);
    }
}
