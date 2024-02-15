package com.tom_roush.pdfbox.pdmodel.common.function;

import android.util.Log;
import com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageInputStream;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;
import java.io.IOException;
import java.lang.reflect.Array;

/* loaded from: classes3.dex */
public class PDFunctionType0 extends PDFunction {
    private COSArray decode;
    private COSArray encode;
    private int[][] samples;
    private COSArray size;

    @Override // com.tom_roush.pdfbox.pdmodel.common.function.PDFunction
    public int getFunctionType() {
        return 0;
    }

    public PDFunctionType0(COSBase cOSBase) {
        super(cOSBase);
        this.encode = null;
        this.decode = null;
        this.size = null;
        this.samples = null;
    }

    public COSArray getSize() {
        if (this.size == null) {
            this.size = (COSArray) getCOSObject().getDictionaryObject(COSName.SIZE);
        }
        return this.size;
    }

    public int getBitsPerSample() {
        return getCOSObject().getInt(COSName.BITS_PER_SAMPLE);
    }

    public int getOrder() {
        return getCOSObject().getInt(COSName.ORDER, 1);
    }

    public void setBitsPerSample(int i) {
        getCOSObject().setInt(COSName.BITS_PER_SAMPLE, i);
    }

    private COSArray getEncodeValues() {
        if (this.encode == null) {
            COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.ENCODE);
            this.encode = cOSArray;
            if (cOSArray == null) {
                this.encode = new COSArray();
                COSArray size = getSize();
                int size2 = size.size();
                for (int i = 0; i < size2; i++) {
                    this.encode.add((COSBase) COSInteger.ZERO);
                    this.encode.add((COSBase) COSInteger.get(size.getInt(i) - 1));
                }
            }
        }
        return this.encode;
    }

    private COSArray getDecodeValues() {
        if (this.decode == null) {
            COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.DECODE);
            this.decode = cOSArray;
            if (cOSArray == null) {
                this.decode = getRangeValues();
            }
        }
        return this.decode;
    }

    public PDRange getEncodeForParameter(int i) {
        COSArray encodeValues = getEncodeValues();
        if (encodeValues == null || encodeValues.size() < (i * 2) + 1) {
            return null;
        }
        return new PDRange(encodeValues, i);
    }

    public void setEncodeValues(COSArray cOSArray) {
        this.encode = cOSArray;
        getCOSObject().setItem(COSName.ENCODE, (COSBase) cOSArray);
    }

    public PDRange getDecodeForParameter(int i) {
        COSArray decodeValues = getDecodeValues();
        if (decodeValues == null || decodeValues.size() < (i * 2) + 1) {
            return null;
        }
        return new PDRange(decodeValues, i);
    }

    public void setDecodeValues(COSArray cOSArray) {
        this.decode = cOSArray;
        getCOSObject().setItem(COSName.DECODE, (COSBase) cOSArray);
    }

    /* loaded from: classes3.dex */
    private class Rinterpol {
        private final float[] in;
        private final int[] inNext;
        private final int[] inPrev;
        private final int numberOfInputValues;
        private final int numberOfOutputValues;

        Rinterpol(float[] fArr, int[] iArr, int[] iArr2) {
            PDFunctionType0.this = r1;
            this.numberOfOutputValues = r1.getNumberOfOutputParameters();
            this.in = fArr;
            this.inPrev = iArr;
            this.inNext = iArr2;
            this.numberOfInputValues = fArr.length;
        }

        float[] rinterpolate() {
            return rinterpol(new int[this.numberOfInputValues], 0);
        }

        private float[] rinterpol(int[] iArr, int i) {
            float[] fArr = new float[this.numberOfOutputValues];
            int i2 = 0;
            if (i == this.in.length - 1) {
                int i3 = this.inPrev[i];
                if (i3 == this.inNext[i]) {
                    iArr[i] = i3;
                    int[] iArr2 = getSamples()[calcSampleIndex(iArr)];
                    while (i2 < this.numberOfOutputValues) {
                        fArr[i2] = iArr2[i2];
                        i2++;
                    }
                    return fArr;
                }
                iArr[i] = i3;
                int[] iArr3 = getSamples()[calcSampleIndex(iArr)];
                iArr[i] = this.inNext[i];
                int[] iArr4 = getSamples()[calcSampleIndex(iArr)];
                while (i2 < this.numberOfOutputValues) {
                    fArr[i2] = PDFunctionType0.this.interpolate(this.in[i], this.inPrev[i], this.inNext[i], iArr3[i2], iArr4[i2]);
                    i2++;
                }
                return fArr;
            }
            int i4 = this.inPrev[i];
            if (i4 == this.inNext[i]) {
                iArr[i] = i4;
                return rinterpol(iArr, i + 1);
            }
            iArr[i] = i4;
            int i5 = i + 1;
            float[] rinterpol = rinterpol(iArr, i5);
            iArr[i] = this.inNext[i];
            float[] rinterpol2 = rinterpol(iArr, i5);
            while (i2 < this.numberOfOutputValues) {
                fArr[i2] = PDFunctionType0.this.interpolate(this.in[i], this.inPrev[i], this.inNext[i], rinterpol[i2], rinterpol2[i2]);
                i2++;
            }
            return fArr;
        }

        private int calcSampleIndex(int[] iArr) {
            float[] floatArray = PDFunctionType0.this.getSize().toFloatArray();
            int length = iArr.length;
            int i = 1;
            for (int i2 = length - 2; i2 >= 0; i2--) {
                i = (int) (i * floatArray[i2]);
            }
            int i3 = 0;
            for (int i4 = length - 1; i4 >= 0; i4--) {
                i3 += iArr[i4] * i;
                int i5 = i4 - 1;
                if (i5 >= 0) {
                    i = (int) (i / floatArray[i5]);
                }
            }
            return i3;
        }

        private int[][] getSamples() {
            if (PDFunctionType0.this.samples == null) {
                int numberOfInputParameters = PDFunctionType0.this.getNumberOfInputParameters();
                int numberOfOutputParameters = PDFunctionType0.this.getNumberOfOutputParameters();
                COSArray size = PDFunctionType0.this.getSize();
                int i = 1;
                for (int i2 = 0; i2 < numberOfInputParameters; i2++) {
                    i *= size.getInt(i2);
                }
                PDFunctionType0.this.samples = (int[][]) Array.newInstance(Integer.TYPE, i, numberOfOutputParameters);
                int bitsPerSample = PDFunctionType0.this.getBitsPerSample();
                try {
                    COSInputStream createInputStream = PDFunctionType0.this.getPDStream().createInputStream();
                    MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(createInputStream);
                    int i3 = 0;
                    for (int i4 = 0; i4 < i; i4++) {
                        for (int i5 = 0; i5 < numberOfOutputParameters; i5++) {
                            PDFunctionType0.this.samples[i3][i5] = (int) memoryCacheImageInputStream.readBits(bitsPerSample);
                        }
                        i3++;
                    }
                    memoryCacheImageInputStream.close();
                    createInputStream.close();
                } catch (IOException e) {
                    Log.e("PdfBox-Android", "IOException while reading the sample values of this function.", e);
                }
            }
            return PDFunctionType0.this.samples;
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.function.PDFunction
    public float[] eval(float[] fArr) throws IOException {
        float[] floatArray = getSize().toFloatArray();
        float pow = (float) (Math.pow(2.0d, getBitsPerSample()) - 1.0d);
        int length = fArr.length;
        int numberOfOutputParameters = getNumberOfOutputParameters();
        int[] iArr = new int[length];
        int[] iArr2 = new int[length];
        float[] fArr2 = (float[]) fArr.clone();
        for (int i = 0; i < length; i++) {
            PDRange domainForInput = getDomainForInput(i);
            PDRange encodeForParameter = getEncodeForParameter(i);
            float clipToRange = clipToRange(fArr2[i], domainForInput.getMin(), domainForInput.getMax());
            fArr2[i] = clipToRange;
            float interpolate = interpolate(clipToRange, domainForInput.getMin(), domainForInput.getMax(), encodeForParameter.getMin(), encodeForParameter.getMax());
            fArr2[i] = interpolate;
            float clipToRange2 = clipToRange(interpolate, 0.0f, floatArray[i] - 1.0f);
            fArr2[i] = clipToRange2;
            iArr[i] = (int) Math.floor(clipToRange2);
            iArr2[i] = (int) Math.ceil(fArr2[i]);
        }
        float[] rinterpolate = new Rinterpol(fArr2, iArr, iArr2).rinterpolate();
        for (int i2 = 0; i2 < numberOfOutputParameters; i2++) {
            PDRange rangeForOutput = getRangeForOutput(i2);
            PDRange decodeForParameter = getDecodeForParameter(i2);
            if (decodeForParameter == null) {
                throw new IOException("Range missing in function /Decode entry");
            }
            float interpolate2 = interpolate(rinterpolate[i2], 0.0f, pow, decodeForParameter.getMin(), decodeForParameter.getMax());
            rinterpolate[i2] = interpolate2;
            rinterpolate[i2] = clipToRange(interpolate2, rangeForOutput.getMin(), rangeForOutput.getMax());
        }
        return rinterpolate;
    }
}
