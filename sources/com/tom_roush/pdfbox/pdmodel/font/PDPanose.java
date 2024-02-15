package com.tom_roush.pdfbox.pdmodel.font;

import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class PDPanose {
    public static final int LENGTH = 12;
    private final byte[] bytes;

    public PDPanose(byte[] bArr) {
        this.bytes = bArr;
    }

    public int getFamilyClass() {
        byte[] bArr = this.bytes;
        return (bArr[1] & UByte.MAX_VALUE) | (bArr[0] << 8);
    }

    public PDPanoseClassification getPanose() {
        return new PDPanoseClassification(Arrays.copyOfRange(this.bytes, 2, 12));
    }
}
