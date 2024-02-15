package com.tom_roush.pdfbox.pdmodel.common.function;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import java.io.IOException;

/* loaded from: classes3.dex */
public abstract class PDFunction implements COSObjectable {
    private COSDictionary functionDictionary;
    private PDStream functionStream;
    private COSArray domain = null;
    private COSArray range = null;
    private int numberOfInputValues = -1;
    private int numberOfOutputValues = -1;

    public float clipToRange(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    public abstract float[] eval(float[] fArr) throws IOException;

    public abstract int getFunctionType();

    public float interpolate(float f, float f2, float f3, float f4, float f5) {
        return f4 + (((f - f2) * (f5 - f4)) / (f3 - f2));
    }

    public PDFunction(COSBase cOSBase) {
        this.functionStream = null;
        this.functionDictionary = null;
        if (cOSBase instanceof COSStream) {
            PDStream pDStream = new PDStream((COSStream) cOSBase);
            this.functionStream = pDStream;
            pDStream.getCOSObject().setItem(COSName.TYPE, (COSBase) COSName.FUNCTION);
        } else if (cOSBase instanceof COSDictionary) {
            this.functionDictionary = (COSDictionary) cOSBase;
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        PDStream pDStream = this.functionStream;
        if (pDStream != null) {
            return pDStream.getCOSObject();
        }
        return this.functionDictionary;
    }

    public PDStream getPDStream() {
        return this.functionStream;
    }

    public static PDFunction create(COSBase cOSBase) throws IOException {
        if (cOSBase == COSName.IDENTITY) {
            return new PDFunctionTypeIdentity(null);
        }
        if (cOSBase instanceof COSObject) {
            cOSBase = ((COSObject) cOSBase).getObject();
        }
        if (!(cOSBase instanceof COSDictionary)) {
            throw new IOException("Error: Function must be a Dictionary, but is " + (cOSBase == null ? "(null)" : cOSBase.getClass().getSimpleName()));
        }
        COSDictionary cOSDictionary = (COSDictionary) cOSBase;
        int i = cOSDictionary.getInt(COSName.FUNCTION_TYPE);
        if (i != 0) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return new PDFunctionType4(cOSDictionary);
                    }
                    throw new IOException("Error: Unknown function type " + i);
                }
                return new PDFunctionType3(cOSDictionary);
            }
            return new PDFunctionType2(cOSDictionary);
        }
        return new PDFunctionType0(cOSDictionary);
    }

    public int getNumberOfOutputParameters() {
        if (this.numberOfOutputValues == -1) {
            COSArray rangeValues = getRangeValues();
            if (rangeValues == null) {
                this.numberOfOutputValues = 0;
            } else {
                this.numberOfOutputValues = rangeValues.size() / 2;
            }
        }
        return this.numberOfOutputValues;
    }

    public PDRange getRangeForOutput(int i) {
        return new PDRange(getRangeValues(), i);
    }

    public void setRangeValues(COSArray cOSArray) {
        this.range = cOSArray;
        getCOSObject().setItem(COSName.RANGE, (COSBase) cOSArray);
    }

    public int getNumberOfInputParameters() {
        if (this.numberOfInputValues == -1) {
            this.numberOfInputValues = getDomainValues().size() / 2;
        }
        return this.numberOfInputValues;
    }

    public PDRange getDomainForInput(int i) {
        return new PDRange(getDomainValues(), i);
    }

    public void setDomainValues(COSArray cOSArray) {
        this.domain = cOSArray;
        getCOSObject().setItem(COSName.DOMAIN, (COSBase) cOSArray);
    }

    @Deprecated
    public COSArray eval(COSArray cOSArray) throws IOException {
        float[] eval = eval(cOSArray.toFloatArray());
        COSArray cOSArray2 = new COSArray();
        cOSArray2.setFloatArray(eval);
        return cOSArray2;
    }

    public COSArray getRangeValues() {
        if (this.range == null) {
            this.range = (COSArray) getCOSObject().getDictionaryObject(COSName.RANGE);
        }
        return this.range;
    }

    private COSArray getDomainValues() {
        if (this.domain == null) {
            this.domain = (COSArray) getCOSObject().getDictionaryObject(COSName.DOMAIN);
        }
        return this.domain;
    }

    public float[] clipToRange(float[] fArr) {
        COSArray rangeValues = getRangeValues();
        if (rangeValues == null || rangeValues.size() <= 0) {
            return fArr;
        }
        float[] floatArray = rangeValues.toFloatArray();
        int length = floatArray.length / 2;
        float[] fArr2 = new float[length];
        for (int i = 0; i < length; i++) {
            int i2 = i << 1;
            fArr2[i] = clipToRange(fArr[i], floatArray[i2], floatArray[i2 + 1]);
        }
        return fArr2;
    }

    public String toString() {
        return "FunctionType" + getFunctionType();
    }
}
