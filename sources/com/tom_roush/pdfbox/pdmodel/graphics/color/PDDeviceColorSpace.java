package com.tom_roush.pdfbox.pdmodel.graphics.color;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;

/* loaded from: classes3.dex */
public abstract class PDDeviceColorSpace extends PDColorSpace {
    public String toString() {
        return getName();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace, com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return COSName.getPDFName(getName());
    }
}
