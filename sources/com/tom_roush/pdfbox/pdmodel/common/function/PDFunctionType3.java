package com.tom_roush.pdfbox.pdmodel.common.function;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDFunctionType3 extends PDFunction {
    private COSArray bounds;
    private float[] boundsValues;
    private COSArray encode;
    private COSArray functions;
    private PDFunction[] functionsArray;

    @Override // com.tom_roush.pdfbox.pdmodel.common.function.PDFunction
    public int getFunctionType() {
        return 3;
    }

    public PDFunctionType3(COSBase cOSBase) {
        super(cOSBase);
        this.functions = null;
        this.encode = null;
        this.bounds = null;
        this.functionsArray = null;
        this.boundsValues = null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.function.PDFunction
    public float[] eval(float[] fArr) throws IOException {
        PDFunction pDFunction;
        float f = fArr[0];
        PDRange domainForInput = getDomainForInput(0);
        float clipToRange = clipToRange(f, domainForInput.getMin(), domainForInput.getMax());
        if (this.functionsArray == null) {
            COSArray functions = getFunctions();
            this.functionsArray = new PDFunction[functions.size()];
            for (int i = 0; i < functions.size(); i++) {
                this.functionsArray[i] = PDFunction.create(functions.getObject(i));
            }
        }
        PDFunction[] pDFunctionArr = this.functionsArray;
        if (pDFunctionArr.length == 1) {
            pDFunction = pDFunctionArr[0];
            PDRange encodeForParameter = getEncodeForParameter(0);
            clipToRange = interpolate(clipToRange, domainForInput.getMin(), domainForInput.getMax(), encodeForParameter.getMin(), encodeForParameter.getMax());
        } else {
            if (this.boundsValues == null) {
                this.boundsValues = getBounds().toFloatArray();
            }
            int length = this.boundsValues.length;
            int i2 = length + 2;
            float[] fArr2 = new float[i2];
            fArr2[0] = domainForInput.getMin();
            int i3 = i2 - 1;
            fArr2[i3] = domainForInput.getMax();
            System.arraycopy(this.boundsValues, 0, fArr2, 1, length);
            for (int i4 = 0; i4 < i3; i4++) {
                if (clipToRange >= fArr2[i4]) {
                    int i5 = i4 + 1;
                    float f2 = fArr2[i5];
                    if (clipToRange < f2 || (i4 == i2 - 2 && clipToRange == f2)) {
                        PDFunction pDFunction2 = this.functionsArray[i4];
                        PDRange encodeForParameter2 = getEncodeForParameter(i4);
                        clipToRange = interpolate(clipToRange, fArr2[i4], fArr2[i5], encodeForParameter2.getMin(), encodeForParameter2.getMax());
                        pDFunction = pDFunction2;
                        break;
                    }
                }
            }
            pDFunction = null;
        }
        if (pDFunction == null) {
            throw new IOException("partition not found in type 3 function");
        }
        return clipToRange(pDFunction.eval(new float[]{clipToRange}));
    }

    public COSArray getFunctions() {
        if (this.functions == null) {
            this.functions = (COSArray) getCOSObject().getDictionaryObject(COSName.FUNCTIONS);
        }
        return this.functions;
    }

    public COSArray getBounds() {
        if (this.bounds == null) {
            this.bounds = (COSArray) getCOSObject().getDictionaryObject(COSName.BOUNDS);
        }
        return this.bounds;
    }

    public COSArray getEncode() {
        if (this.encode == null) {
            this.encode = (COSArray) getCOSObject().getDictionaryObject(COSName.ENCODE);
        }
        return this.encode;
    }

    private PDRange getEncodeForParameter(int i) {
        return new PDRange(getEncode(), i);
    }
}
