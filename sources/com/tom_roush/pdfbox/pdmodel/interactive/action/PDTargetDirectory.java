package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;

/* loaded from: classes3.dex */
public class PDTargetDirectory implements COSObjectable {
    private final COSDictionary dict;

    public PDTargetDirectory() {
        this.dict = new COSDictionary();
    }

    public PDTargetDirectory(COSDictionary cOSDictionary) {
        this.dict = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dict;
    }

    public COSName getRelationship() {
        COSBase item = this.dict.getItem(COSName.R);
        if (item instanceof COSName) {
            return (COSName) item;
        }
        return null;
    }

    public void setRelationship(COSName cOSName) {
        if (!COSName.P.equals(cOSName) && !COSName.C.equals(cOSName)) {
            throw new IllegalArgumentException("The only valid are P or C, not " + cOSName.getName());
        }
        this.dict.setItem(COSName.R, (COSBase) cOSName);
    }

    public String getFilename() {
        return this.dict.getString(COSName.N);
    }

    public void setFilename(String str) {
        this.dict.setString(COSName.N, str);
    }

    public PDTargetDirectory getTargetDirectory() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.T);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDTargetDirectory((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setTargetDirectory(PDTargetDirectory pDTargetDirectory) {
        this.dict.setItem(COSName.T, pDTargetDirectory);
    }

    public int getPageNumber() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.P);
        if (dictionaryObject instanceof COSInteger) {
            return ((COSInteger) dictionaryObject).intValue();
        }
        return -1;
    }

    public void setPageNumber(int i) {
        if (i < 0) {
            this.dict.removeItem(COSName.P);
        } else {
            this.dict.setInt(COSName.P, i);
        }
    }

    public PDNamedDestination getNamedDestination() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.P);
        if (dictionaryObject instanceof COSString) {
            return new PDNamedDestination((COSString) dictionaryObject);
        }
        return null;
    }

    public void setNamedDestination(PDNamedDestination pDNamedDestination) {
        if (pDNamedDestination == null) {
            this.dict.removeItem(COSName.P);
        } else {
            this.dict.setItem(COSName.P, pDNamedDestination);
        }
    }

    public int getAnnotationIndex() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.A);
        if (dictionaryObject instanceof COSInteger) {
            return ((COSInteger) dictionaryObject).intValue();
        }
        return -1;
    }

    public void setAnnotationIndex(int i) {
        if (i < 0) {
            this.dict.removeItem(COSName.A);
        } else {
            this.dict.setInt(COSName.A, i);
        }
    }

    public String getAnnotationName() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.A);
        if (dictionaryObject instanceof COSString) {
            return ((COSString) dictionaryObject).getString();
        }
        return null;
    }

    public void setAnnotationName(String str) {
        this.dict.setString(COSName.A, str);
    }
}
