package com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class PDOptionalContentMembershipDictionary extends PDPropertyList {
    public PDOptionalContentMembershipDictionary() {
        this.dict.setItem(COSName.TYPE, (COSBase) COSName.OCMD);
    }

    public PDOptionalContentMembershipDictionary(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        if (!cOSDictionary.getItem(COSName.TYPE).equals(COSName.OCMD)) {
            throw new IllegalArgumentException("Provided dictionary is not of type '" + COSName.OCMD + OperatorName.SHOW_TEXT_LINE);
        }
    }

    public List<PDPropertyList> getOCGs() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.OCGS);
        if (dictionaryObject instanceof COSDictionary) {
            return Collections.singletonList(PDPropertyList.create((COSDictionary) dictionaryObject));
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < cOSArray.size(); i++) {
                COSBase object = cOSArray.getObject(i);
                if (object instanceof COSDictionary) {
                    arrayList.add(PDPropertyList.create((COSDictionary) object));
                }
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public void setOCGs(List<PDPropertyList> list) {
        COSArray cOSArray = new COSArray();
        for (PDPropertyList pDPropertyList : list) {
            cOSArray.add(pDPropertyList);
        }
        this.dict.setItem(COSName.OCGS, (COSBase) cOSArray);
    }

    public COSName getVisibilityPolicy() {
        return this.dict.getCOSName(COSName.P, COSName.ANY_ON);
    }

    public void setVisibilityPolicy(COSName cOSName) {
        this.dict.setItem(COSName.P, (COSBase) cOSName);
    }
}
