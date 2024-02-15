package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationUnknown;

/* loaded from: classes3.dex */
public class PDObjectReference implements COSObjectable {
    public static final String TYPE = "OBJR";
    private final COSDictionary dictionary;

    public PDObjectReference() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, TYPE);
    }

    public PDObjectReference(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public COSObjectable getReferencedObject() {
        PDXObject createXObject;
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.OBJ);
        if (dictionaryObject instanceof COSDictionary) {
            if (!(dictionaryObject instanceof COSStream) || (createXObject = PDXObject.createXObject(dictionaryObject, null)) == null) {
                COSDictionary cOSDictionary = (COSDictionary) dictionaryObject;
                PDAnnotation createAnnotation = PDAnnotation.createAnnotation(dictionaryObject);
                if (createAnnotation instanceof PDAnnotationUnknown) {
                    if (!COSName.ANNOT.equals(cOSDictionary.getDictionaryObject(COSName.TYPE))) {
                        return null;
                    }
                }
                return createAnnotation;
            }
            return createXObject;
        }
        return null;
    }

    public void setReferencedObject(PDAnnotation pDAnnotation) {
        getCOSObject().setItem(COSName.OBJ, pDAnnotation);
    }

    public void setReferencedObject(PDXObject pDXObject) {
        getCOSObject().setItem(COSName.OBJ, pDXObject);
    }
}
