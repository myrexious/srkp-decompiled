package com.tom_roush.pdfbox.cos;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
public final class COSInteger extends COSNumber {
    private static final int HIGH = 256;
    private static final int LOW = -100;
    private final boolean isValid;
    private final long value;
    private static final COSInteger[] STATIC = new COSInteger[357];
    public static final COSInteger ZERO = get(0);
    public static final COSInteger ONE = get(1);
    public static final COSInteger TWO = get(2);
    public static final COSInteger THREE = get(3);
    protected static final COSInteger OUT_OF_RANGE_MAX = getInvalid(true);
    protected static final COSInteger OUT_OF_RANGE_MIN = getInvalid(false);

    public static COSInteger get(long j) {
        if (-100 <= j && j <= 256) {
            int i = ((int) j) + 100;
            COSInteger[] cOSIntegerArr = STATIC;
            if (cOSIntegerArr[i] == null) {
                cOSIntegerArr[i] = new COSInteger(j, true);
            }
            return cOSIntegerArr[i];
        }
        return new COSInteger(j, true);
    }

    private static COSInteger getInvalid(boolean z) {
        if (z) {
            return new COSInteger(Long.MAX_VALUE, false);
        }
        return new COSInteger(Long.MIN_VALUE, false);
    }

    private COSInteger(long j, boolean z) {
        this.value = j;
        this.isValid = z;
    }

    public boolean equals(Object obj) {
        return (obj instanceof COSInteger) && ((COSInteger) obj).intValue() == intValue();
    }

    public int hashCode() {
        long j = this.value;
        return (int) (j ^ (j >> 32));
    }

    public String toString() {
        return "COSInt{" + this.value + StringSubstitutor.DEFAULT_VAR_END;
    }

    @Override // com.tom_roush.pdfbox.cos.COSNumber
    public float floatValue() {
        return (float) this.value;
    }

    @Override // com.tom_roush.pdfbox.cos.COSNumber
    public double doubleValue() {
        return this.value;
    }

    @Override // com.tom_roush.pdfbox.cos.COSNumber
    public int intValue() {
        return (int) this.value;
    }

    @Override // com.tom_roush.pdfbox.cos.COSNumber
    public long longValue() {
        return this.value;
    }

    public boolean isValid() {
        return this.isValid;
    }

    @Override // com.tom_roush.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromInt(this);
    }

    public void writePDF(OutputStream outputStream) throws IOException {
        outputStream.write(String.valueOf(this.value).getBytes("ISO-8859-1"));
    }
}
