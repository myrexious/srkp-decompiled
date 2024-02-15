package com.tom_roush.pdfbox.pdmodel.graphics.form;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class PDTransparencyGroupAttributes implements COSObjectable {
    private PDColorSpace colorSpace;
    private final COSDictionary dictionary;

    public PDTransparencyGroupAttributes() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.S, (COSBase) COSName.TRANSPARENCY);
    }

    public PDTransparencyGroupAttributes(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public PDColorSpace getColorSpace() throws IOException {
        return getColorSpace(null);
    }

    public PDColorSpace getColorSpace(PDResources pDResources) throws IOException {
        if (this.colorSpace == null && getCOSObject().containsKey(COSName.CS)) {
            this.colorSpace = PDColorSpace.create(getCOSObject().getDictionaryObject(COSName.CS), pDResources);
        }
        return this.colorSpace;
    }

    public boolean isIsolated() {
        return getCOSObject().getBoolean(COSName.I, false);
    }

    public boolean isKnockout() {
        return getCOSObject().getBoolean(COSName.K, false);
    }
}
