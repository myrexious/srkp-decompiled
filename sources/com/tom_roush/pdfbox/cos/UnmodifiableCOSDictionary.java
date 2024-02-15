package com.tom_roush.pdfbox.cos;

import java.util.Collections;

/* loaded from: classes3.dex */
public final class UnmodifiableCOSDictionary extends COSDictionary {
    public UnmodifiableCOSDictionary(COSDictionary cOSDictionary) {
        this.items = Collections.unmodifiableMap(cOSDictionary.items);
    }

    @Override // com.tom_roush.pdfbox.cos.COSDictionary
    public void mergeInto(COSDictionary cOSDictionary) {
        throw new UnsupportedOperationException();
    }

    @Override // com.tom_roush.pdfbox.cos.COSDictionary, com.tom_roush.pdfbox.cos.COSUpdateInfo
    public void setNeedToBeUpdated(boolean z) {
        throw new UnsupportedOperationException();
    }
}
