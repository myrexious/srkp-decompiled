package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes3.dex */
public class PDPageAdditionalActions implements COSObjectable {
    private final COSDictionary actions;

    public PDPageAdditionalActions() {
        this.actions = new COSDictionary();
    }

    public PDPageAdditionalActions(COSDictionary cOSDictionary) {
        this.actions = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.actions;
    }

    public PDAction getO() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(COSName.O);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setO(PDAction pDAction) {
        this.actions.setItem(COSName.O, pDAction);
    }

    public PDAction getC() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("C");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setC(PDAction pDAction) {
        this.actions.setItem("C", pDAction);
    }
}
