package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;

/* loaded from: classes3.dex */
public class PDDocumentCatalogAdditionalActions implements COSObjectable {
    private final COSDictionary actions;

    public PDDocumentCatalogAdditionalActions() {
        this.actions = new COSDictionary();
    }

    public PDDocumentCatalogAdditionalActions(COSDictionary cOSDictionary) {
        this.actions = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.actions;
    }

    public PDAction getWC() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("WC");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setWC(PDAction pDAction) {
        this.actions.setItem("WC", pDAction);
    }

    public PDAction getWS() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("WS");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setWS(PDAction pDAction) {
        this.actions.setItem("WS", pDAction);
    }

    public PDAction getDS() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("DS");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setDS(PDAction pDAction) {
        this.actions.setItem("DS", pDAction);
    }

    public PDAction getWP() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(StandardStructureTypes.WP);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setWP(PDAction pDAction) {
        this.actions.setItem(StandardStructureTypes.WP, pDAction);
    }

    public PDAction getDP() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(OperatorName.MARKED_CONTENT_POINT_WITH_PROPS);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setDP(PDAction pDAction) {
        this.actions.setItem(OperatorName.MARKED_CONTENT_POINT_WITH_PROPS, pDAction);
    }
}
