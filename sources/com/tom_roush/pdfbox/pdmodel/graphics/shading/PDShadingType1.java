package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.util.Matrix;

/* loaded from: classes3.dex */
public class PDShadingType1 extends PDShading {
    private COSArray domain;

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading
    public int getShadingType() {
        return 1;
    }

    public PDShadingType1(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        this.domain = null;
    }

    public Matrix getMatrix() {
        return Matrix.createMatrix(getCOSObject().getDictionaryObject(COSName.MATRIX));
    }

    public void setMatrix(AffineTransform affineTransform) {
        COSArray cOSArray = new COSArray();
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        for (int i = 0; i < 6; i++) {
            cOSArray.add((COSBase) new COSFloat((float) dArr[i]));
        }
        getCOSObject().setItem(COSName.MATRIX, (COSBase) cOSArray);
    }

    public COSArray getDomain() {
        if (this.domain == null) {
            this.domain = (COSArray) getCOSObject().getDictionaryObject(COSName.DOMAIN);
        }
        return this.domain;
    }

    public void setDomain(COSArray cOSArray) {
        this.domain = cOSArray;
        getCOSObject().setItem(COSName.DOMAIN, (COSBase) cOSArray);
    }
}
