package com.tom_roush.pdfbox.filter;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDJPXColorSpace;

/* loaded from: classes3.dex */
public final class DecodeResult {
    public static final DecodeResult DEFAULT = new DecodeResult(new COSDictionary());
    private PDJPXColorSpace colorSpace;
    private final COSDictionary parameters;

    public DecodeResult(COSDictionary cOSDictionary) {
        this.parameters = cOSDictionary;
    }

    DecodeResult(COSDictionary cOSDictionary, PDJPXColorSpace pDJPXColorSpace) {
        this.parameters = cOSDictionary;
        this.colorSpace = pDJPXColorSpace;
    }

    public COSDictionary getParameters() {
        return this.parameters;
    }

    public PDJPXColorSpace getJPXColorSpace() {
        return this.colorSpace;
    }

    public void setColorSpace(PDJPXColorSpace pDJPXColorSpace) {
        this.colorSpace = pDJPXColorSpace;
    }
}
