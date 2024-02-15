package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.PDDestinationOrAction;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class PDAction implements PDDestinationOrAction {
    public static final String TYPE = "Action";
    protected COSDictionary action;

    public PDAction() {
        this.action = new COSDictionary();
        setType(TYPE);
    }

    public PDAction(COSDictionary cOSDictionary) {
        this.action = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.action;
    }

    public String getType() {
        return this.action.getNameAsString(COSName.TYPE);
    }

    public final void setType(String str) {
        this.action.setName(COSName.TYPE, str);
    }

    public String getSubType() {
        return this.action.getNameAsString(COSName.S);
    }

    public void setSubType(String str) {
        this.action.setName(COSName.S, str);
    }

    public List<PDAction> getNext() {
        COSBase dictionaryObject = this.action.getDictionaryObject(COSName.NEXT);
        if (dictionaryObject instanceof COSDictionary) {
            return new COSArrayList(PDActionFactory.createAction((COSDictionary) dictionaryObject), dictionaryObject, this.action, COSName.NEXT);
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            ArrayList arrayList = new ArrayList(cOSArray.size());
            for (int i = 0; i < cOSArray.size(); i++) {
                arrayList.add(PDActionFactory.createAction((COSDictionary) cOSArray.getObject(i)));
            }
            return new COSArrayList(arrayList, cOSArray);
        }
        return null;
    }

    public void setNext(List<?> list) {
        this.action.setItem(COSName.NEXT, (COSBase) COSArrayList.converterToCOSArray(list));
    }
}
