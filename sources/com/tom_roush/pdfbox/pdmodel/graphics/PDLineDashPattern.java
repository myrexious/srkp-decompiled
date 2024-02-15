package com.tom_roush.pdfbox.pdmodel.graphics;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.Arrays;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
public final class PDLineDashPattern implements COSObjectable {
    private final float[] array;
    private final int phase;

    public PDLineDashPattern() {
        this.array = new float[0];
        this.phase = 0;
    }

    public PDLineDashPattern(COSArray cOSArray, int i) {
        this.array = cOSArray.toFloatArray();
        this.phase = i;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        COSArray cOSArray = new COSArray();
        COSArray cOSArray2 = new COSArray();
        cOSArray2.setFloatArray(this.array);
        cOSArray.add((COSBase) cOSArray2);
        cOSArray.add((COSBase) COSInteger.get(this.phase));
        return cOSArray;
    }

    public int getPhase() {
        return this.phase;
    }

    public float[] getDashArray() {
        return (float[]) this.array.clone();
    }

    public String toString() {
        return "PDLineDashPattern{array=" + Arrays.toString(this.array) + ", phase=" + this.phase + StringSubstitutor.DEFAULT_VAR_END;
    }
}
