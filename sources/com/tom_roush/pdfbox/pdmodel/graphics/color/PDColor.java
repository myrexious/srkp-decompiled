package com.tom_roush.pdfbox.pdmodel.graphics.color;

import android.util.Log;
import com.tom_roush.fontbox.ttf.OpenTypeScript;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
public final class PDColor {
    private final PDColorSpace colorSpace;
    private final float[] components;
    private final COSName patternName;

    public PDColor(COSArray cOSArray, PDColorSpace pDColorSpace) {
        if (cOSArray.size() > 0 && (cOSArray.get(cOSArray.size() - 1) instanceof COSName)) {
            this.components = new float[cOSArray.size() - 1];
            initComponents(cOSArray);
            COSBase cOSBase = cOSArray.get(cOSArray.size() - 1);
            if (cOSBase instanceof COSName) {
                this.patternName = (COSName) cOSBase;
            } else {
                Log.w("PdfBox-Android", "pattern name in " + cOSArray + " isn't a name, ignored");
                this.patternName = COSName.getPDFName(OpenTypeScript.UNKNOWN);
            }
        } else {
            this.components = new float[cOSArray.size()];
            initComponents(cOSArray);
            this.patternName = null;
        }
        this.colorSpace = pDColorSpace;
    }

    private void initComponents(COSArray cOSArray) {
        for (int i = 0; i < this.components.length; i++) {
            COSBase cOSBase = cOSArray.get(i);
            if (cOSBase instanceof COSNumber) {
                this.components[i] = ((COSNumber) cOSBase).floatValue();
            } else {
                Log.w("PdfBox-Android", "color component " + i + " in " + cOSArray + " isn't a number, ignored");
            }
        }
    }

    public PDColor(float[] fArr, PDColorSpace pDColorSpace) {
        this.components = (float[]) fArr.clone();
        this.patternName = null;
        this.colorSpace = pDColorSpace;
    }

    public PDColor(COSName cOSName, PDColorSpace pDColorSpace) {
        this.components = new float[0];
        this.patternName = cOSName;
        this.colorSpace = pDColorSpace;
    }

    public PDColor(float[] fArr, COSName cOSName, PDColorSpace pDColorSpace) {
        this.components = (float[]) fArr.clone();
        this.patternName = cOSName;
        this.colorSpace = pDColorSpace;
    }

    public float[] getComponents() {
        PDColorSpace pDColorSpace = this.colorSpace;
        if (pDColorSpace == null) {
            return (float[]) this.components.clone();
        }
        return Arrays.copyOf(this.components, pDColorSpace.getNumberOfComponents());
    }

    public COSName getPatternName() {
        return this.patternName;
    }

    public boolean isPattern() {
        return this.patternName != null;
    }

    public int toRGB() throws IOException {
        float[] rgb = this.colorSpace.toRGB(this.components);
        int round = Math.round(rgb[0] * 255.0f);
        return (((round << 8) + Math.round(rgb[1] * 255.0f)) << 8) + Math.round(rgb[2] * 255.0f);
    }

    public COSArray toCOSArray() {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(this.components);
        COSName cOSName = this.patternName;
        if (cOSName != null) {
            cOSArray.add((COSBase) cOSName);
        }
        return cOSArray;
    }

    public PDColorSpace getColorSpace() {
        return this.colorSpace;
    }

    public String toString() {
        return "PDColor{components=" + Arrays.toString(this.components) + ", patternName=" + this.patternName + StringSubstitutor.DEFAULT_VAR_END;
    }
}
