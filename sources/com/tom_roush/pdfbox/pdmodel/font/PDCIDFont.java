package com.tom_roush.pdfbox.pdmodel.font;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.util.Vector;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kotlin.UByte;

/* loaded from: classes3.dex */
public abstract class PDCIDFont implements COSObjectable, PDFontLike, PDVectorFont {
    private float averageWidth;
    private float defaultWidth;
    protected final COSDictionary dict;
    private PDFontDescriptor fontDescriptor;
    protected final PDType0Font parent;
    private Map<Integer, Float> widths;
    private final Map<Integer, Float> verticalDisplacementY = new HashMap();
    private final Map<Integer, Vector> positionVectors = new HashMap();
    private float[] dw2 = {880.0f, -1000.0f};

    public abstract int codeToCID(int i);

    public abstract int codeToGID(int i) throws IOException;

    public abstract byte[] encode(int i) throws IOException;

    public PDCIDFont(COSDictionary cOSDictionary, PDType0Font pDType0Font) {
        this.dict = cOSDictionary;
        this.parent = pDType0Font;
        readWidths();
        readVerticalDisplacements();
    }

    private void readWidths() {
        this.widths = new HashMap();
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.W);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            int size = cOSArray.size();
            int i = 0;
            while (i < size - 1) {
                int i2 = i + 1;
                COSBase object = cOSArray.getObject(i);
                if (!(object instanceof COSNumber)) {
                    Log.w("PdfBox-Android", "Expected a number array member, got " + object);
                    i = i2;
                } else {
                    COSNumber cOSNumber = (COSNumber) object;
                    int i3 = i2 + 1;
                    COSBase object2 = cOSArray.getObject(i2);
                    if (object2 instanceof COSArray) {
                        COSArray cOSArray2 = (COSArray) object2;
                        int intValue = cOSNumber.intValue();
                        int size2 = cOSArray2.size();
                        for (int i4 = 0; i4 < size2; i4++) {
                            COSBase object3 = cOSArray2.getObject(i4);
                            if (object3 instanceof COSNumber) {
                                this.widths.put(Integer.valueOf(intValue + i4), Float.valueOf(((COSNumber) object3).floatValue()));
                            } else {
                                Log.w("PdfBox-Android", "Expected a number array member, got " + object3);
                            }
                        }
                        i = i3;
                    } else if (i3 >= size) {
                        Log.w("PdfBox-Android", "premature end of widths array");
                        return;
                    } else {
                        int i5 = i3 + 1;
                        COSBase object4 = cOSArray.getObject(i3);
                        if (!(object2 instanceof COSNumber) || !(object4 instanceof COSNumber)) {
                            Log.w("PdfBox-Android", "Expected two numbers, got " + object2 + " and " + object4);
                        } else {
                            int intValue2 = ((COSNumber) object2).intValue();
                            float floatValue = ((COSNumber) object4).floatValue();
                            for (int intValue3 = cOSNumber.intValue(); intValue3 <= intValue2; intValue3++) {
                                this.widths.put(Integer.valueOf(intValue3), Float.valueOf(floatValue));
                            }
                        }
                        i = i5;
                    }
                }
            }
        }
    }

    private void readVerticalDisplacements() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.DW2);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            COSBase object = cOSArray.getObject(0);
            COSBase object2 = cOSArray.getObject(1);
            if ((object instanceof COSNumber) && (object2 instanceof COSNumber)) {
                this.dw2[0] = ((COSNumber) object).floatValue();
                this.dw2[1] = ((COSNumber) object2).floatValue();
            }
        }
        COSBase dictionaryObject2 = this.dict.getDictionaryObject(COSName.W2);
        if (dictionaryObject2 instanceof COSArray) {
            COSArray cOSArray2 = (COSArray) dictionaryObject2;
            int i = 0;
            while (i < cOSArray2.size()) {
                COSNumber cOSNumber = (COSNumber) cOSArray2.getObject(i);
                int i2 = i + 1;
                COSBase object3 = cOSArray2.getObject(i2);
                if (object3 instanceof COSArray) {
                    COSArray cOSArray3 = (COSArray) object3;
                    int i3 = 0;
                    while (i3 < cOSArray3.size()) {
                        int intValue = cOSNumber.intValue() + (i3 / 3);
                        int i4 = i3 + 1;
                        int i5 = i4 + 1;
                        this.verticalDisplacementY.put(Integer.valueOf(intValue), Float.valueOf(((COSNumber) cOSArray3.getObject(i3)).floatValue()));
                        this.positionVectors.put(Integer.valueOf(intValue), new Vector(((COSNumber) cOSArray3.getObject(i4)).floatValue(), ((COSNumber) cOSArray3.getObject(i5)).floatValue()));
                        i3 = i5 + 1;
                    }
                } else {
                    int intValue2 = ((COSNumber) object3).intValue();
                    int i6 = i2 + 1;
                    COSNumber cOSNumber2 = (COSNumber) cOSArray2.getObject(i6);
                    int i7 = i6 + 1;
                    COSNumber cOSNumber3 = (COSNumber) cOSArray2.getObject(i7);
                    i2 = i7 + 1;
                    COSNumber cOSNumber4 = (COSNumber) cOSArray2.getObject(i2);
                    for (int intValue3 = cOSNumber.intValue(); intValue3 <= intValue2; intValue3++) {
                        this.verticalDisplacementY.put(Integer.valueOf(intValue3), Float.valueOf(cOSNumber2.floatValue()));
                        this.positionVectors.put(Integer.valueOf(intValue3), new Vector(cOSNumber3.floatValue(), cOSNumber4.floatValue()));
                    }
                }
                i = i2 + 1;
            }
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dict;
    }

    public String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public String getName() {
        return getBaseFont();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public PDFontDescriptor getFontDescriptor() {
        COSDictionary cOSDictionary;
        if (this.fontDescriptor == null && (cOSDictionary = (COSDictionary) this.dict.getDictionaryObject(COSName.FONT_DESC)) != null) {
            this.fontDescriptor = new PDFontDescriptor(cOSDictionary);
        }
        return this.fontDescriptor;
    }

    public final PDType0Font getParent() {
        return this.parent;
    }

    private float getDefaultWidth() {
        if (this.defaultWidth == 0.0f) {
            COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.DW);
            if (dictionaryObject instanceof COSNumber) {
                this.defaultWidth = ((COSNumber) dictionaryObject).floatValue();
            } else {
                this.defaultWidth = 1000.0f;
            }
        }
        return this.defaultWidth;
    }

    private Vector getDefaultPositionVector(int i) {
        return new Vector(getWidthForCID(i) / 2.0f, this.dw2[0]);
    }

    private float getWidthForCID(int i) {
        Float f = this.widths.get(Integer.valueOf(i));
        if (f == null) {
            f = Float.valueOf(getDefaultWidth());
        }
        return f.floatValue();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean hasExplicitWidth(int i) throws IOException {
        return this.widths.get(Integer.valueOf(codeToCID(i))) != null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public Vector getPositionVector(int i) {
        int codeToCID = codeToCID(i);
        Vector vector = this.positionVectors.get(Integer.valueOf(codeToCID));
        return vector == null ? getDefaultPositionVector(codeToCID) : vector;
    }

    public float getVerticalDisplacementVectorY(int i) {
        Float f = this.verticalDisplacementY.get(Integer.valueOf(codeToCID(i)));
        if (f == null) {
            f = Float.valueOf(this.dw2[1]);
        }
        return f.floatValue();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getWidth(int i) throws IOException {
        return getWidthForCID(codeToCID(i));
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        float f;
        if (this.averageWidth == 0.0f) {
            Map<Integer, Float> map = this.widths;
            int i = 0;
            if (map != null) {
                f = 0.0f;
                for (Float f2 : map.values()) {
                    if (f2.floatValue() > 0.0f) {
                        f += f2.floatValue();
                        i++;
                    }
                }
            } else {
                f = 0.0f;
            }
            if (i != 0) {
                this.averageWidth = f / i;
            }
            float f3 = this.averageWidth;
            if (f3 <= 0.0f || Float.isNaN(f3)) {
                this.averageWidth = getDefaultWidth();
            }
        }
        return this.averageWidth;
    }

    public PDCIDSystemInfo getCIDSystemInfo() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.CIDSYSTEMINFO);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDCIDSystemInfo((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public final int[] readCIDToGIDMap() throws IOException {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.CID_TO_GID_MAP);
        if (dictionaryObject instanceof COSStream) {
            COSInputStream createInputStream = ((COSStream) dictionaryObject).createInputStream();
            byte[] byteArray = IOUtils.toByteArray(createInputStream);
            IOUtils.closeQuietly(createInputStream);
            int length = byteArray.length / 2;
            int[] iArr = new int[length];
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = ((byteArray[i] & UByte.MAX_VALUE) << 8) | (byteArray[i + 1] & UByte.MAX_VALUE);
                i += 2;
            }
            return iArr;
        }
        return null;
    }
}
