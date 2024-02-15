package com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDGamma;

/* loaded from: classes3.dex */
public abstract class PDStandardAttributeObject extends PDAttributeObject {
    protected static final float UNSPECIFIED = -1.0f;

    public PDStandardAttributeObject() {
    }

    public PDStandardAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public boolean isSpecified(String str) {
        return getCOSObject().getDictionaryObject(str) != null;
    }

    public String getString(String str) {
        return getCOSObject().getString(str);
    }

    public void setString(String str, String str2) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setString(str, str2);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    public String[] getArrayOfString(String str) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            String[] strArr = new String[cOSArray.size()];
            for (int i = 0; i < cOSArray.size(); i++) {
                strArr[i] = ((COSName) cOSArray.getObject(i)).getName();
            }
            return strArr;
        }
        return null;
    }

    public void setArrayOfString(String str, String[] strArr) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        COSArray cOSArray = new COSArray();
        for (String str2 : strArr) {
            cOSArray.add((COSBase) new COSString(str2));
        }
        getCOSObject().setItem(str, (COSBase) cOSArray);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    public String getName(String str) {
        return getCOSObject().getNameAsString(str);
    }

    public String getName(String str, String str2) {
        return getCOSObject().getNameAsString(str, str2);
    }

    public Object getNameOrArrayOfName(String str, String str2) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        if (!(dictionaryObject instanceof COSArray)) {
            return dictionaryObject instanceof COSName ? ((COSName) dictionaryObject).getName() : str2;
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        String[] strArr = new String[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSName) {
                strArr[i] = ((COSName) object).getName();
            }
        }
        return strArr;
    }

    public void setName(String str, String str2) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setName(str, str2);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    public void setArrayOfName(String str, String[] strArr) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        COSArray cOSArray = new COSArray();
        for (String str2 : strArr) {
            cOSArray.add((COSBase) COSName.getPDFName(str2));
        }
        getCOSObject().setItem(str, (COSBase) cOSArray);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    public Object getNumberOrName(String str, String str2) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        if (dictionaryObject instanceof COSNumber) {
            return Float.valueOf(((COSNumber) dictionaryObject).floatValue());
        }
        return dictionaryObject instanceof COSName ? ((COSName) dictionaryObject).getName() : str2;
    }

    public int getInteger(String str, int i) {
        return getCOSObject().getInt(str, i);
    }

    public void setInteger(String str, int i) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setInt(str, i);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    public float getNumber(String str, float f) {
        return getCOSObject().getFloat(str, f);
    }

    public float getNumber(String str) {
        return getCOSObject().getFloat(str);
    }

    public Object getNumberOrArrayOfNumber(String str, float f) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            float[] fArr = new float[cOSArray.size()];
            for (int i = 0; i < cOSArray.size(); i++) {
                COSBase object = cOSArray.getObject(i);
                if (object instanceof COSNumber) {
                    fArr[i] = ((COSNumber) object).floatValue();
                }
            }
            return fArr;
        } else if (dictionaryObject instanceof COSNumber) {
            return Float.valueOf(((COSNumber) dictionaryObject).floatValue());
        } else {
            if (f == UNSPECIFIED) {
                return null;
            }
            return Float.valueOf(f);
        }
    }

    public void setNumber(String str, float f) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setFloat(str, f);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    public void setNumber(String str, int i) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setInt(str, i);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    public void setArrayOfNumber(String str, float[] fArr) {
        COSArray cOSArray = new COSArray();
        for (float f : fArr) {
            cOSArray.add((COSBase) new COSFloat(f));
        }
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setItem(str, (COSBase) cOSArray);
        potentiallyNotifyChanged(dictionaryObject, getCOSObject().getDictionaryObject(str));
    }

    public PDGamma getColor(String str) {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(str);
        if (cOSArray != null) {
            return new PDGamma(cOSArray);
        }
        return null;
    }

    public Object getColorOrFourColors(String str) {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(str);
        if (cOSArray == null) {
            return null;
        }
        if (cOSArray.size() == 3) {
            return new PDGamma(cOSArray);
        }
        if (cOSArray.size() == 4) {
            return new PDFourColours(cOSArray);
        }
        return null;
    }

    public void setColor(String str, PDGamma pDGamma) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setItem(str, pDGamma);
        potentiallyNotifyChanged(dictionaryObject, pDGamma == null ? null : pDGamma.getCOSObject());
    }

    public void setFourColors(String str, PDFourColours pDFourColours) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(str);
        getCOSObject().setItem(str, pDFourColours);
        potentiallyNotifyChanged(dictionaryObject, pDFourColours == null ? null : pDFourColours.getCOSObject());
    }
}
