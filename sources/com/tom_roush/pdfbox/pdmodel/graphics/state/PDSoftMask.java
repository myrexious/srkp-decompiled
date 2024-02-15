package com.tom_roush.pdfbox.pdmodel.graphics.state;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.function.PDFunction;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class PDSoftMask implements COSObjectable {
    private Matrix ctm;
    private final COSDictionary dictionary;
    private COSName subType = null;
    private PDTransparencyGroup group = null;
    private COSArray backdropColor = null;
    private PDFunction transferFunction = null;

    public static PDSoftMask create(COSBase cOSBase) {
        if (cOSBase instanceof COSName) {
            if (COSName.NONE.equals(cOSBase)) {
                return null;
            }
            Log.w("PdfBox-Android", "Invalid SMask " + cOSBase);
            return null;
        } else if (cOSBase instanceof COSDictionary) {
            return new PDSoftMask((COSDictionary) cOSBase);
        } else {
            Log.w("PdfBox-Android", "Invalid SMask " + cOSBase);
            return null;
        }
    }

    public PDSoftMask(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public COSName getSubType() {
        if (this.subType == null) {
            this.subType = (COSName) getCOSObject().getDictionaryObject(COSName.S);
        }
        return this.subType;
    }

    public PDTransparencyGroup getGroup() throws IOException {
        COSBase dictionaryObject;
        if (this.group == null && (dictionaryObject = getCOSObject().getDictionaryObject(COSName.G)) != null) {
            PDXObject createXObject = PDXObject.createXObject(dictionaryObject, null);
            if (createXObject instanceof PDTransparencyGroup) {
                this.group = (PDTransparencyGroup) createXObject;
            }
        }
        return this.group;
    }

    public COSArray getBackdropColor() {
        if (this.backdropColor == null) {
            this.backdropColor = (COSArray) getCOSObject().getDictionaryObject(COSName.BC);
        }
        return this.backdropColor;
    }

    public PDFunction getTransferFunction() throws IOException {
        COSBase dictionaryObject;
        if (this.transferFunction == null && (dictionaryObject = getCOSObject().getDictionaryObject(COSName.TR)) != null) {
            this.transferFunction = PDFunction.create(dictionaryObject);
        }
        return this.transferFunction;
    }

    public void setInitialTransformationMatrix(Matrix matrix) {
        this.ctm = matrix;
    }

    public Matrix getInitialTransformationMatrix() {
        return this.ctm;
    }
}
