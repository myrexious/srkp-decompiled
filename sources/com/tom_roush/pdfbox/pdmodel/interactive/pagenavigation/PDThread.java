package com.tom_roush.pdfbox.pdmodel.interactive.pagenavigation;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.PDDocumentInformation;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionThread;

/* loaded from: classes3.dex */
public class PDThread implements COSObjectable {
    private COSDictionary thread;

    public PDThread(COSDictionary cOSDictionary) {
        this.thread = cOSDictionary;
    }

    public PDThread() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.thread = cOSDictionary;
        cOSDictionary.setName("Type", PDActionThread.SUB_TYPE);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.thread;
    }

    public PDDocumentInformation getThreadInfo() {
        COSDictionary cOSDictionary = (COSDictionary) this.thread.getDictionaryObject("I");
        if (cOSDictionary != null) {
            return new PDDocumentInformation(cOSDictionary);
        }
        return null;
    }

    public void setThreadInfo(PDDocumentInformation pDDocumentInformation) {
        this.thread.setItem("I", pDDocumentInformation);
    }

    public PDThreadBead getFirstBead() {
        COSDictionary cOSDictionary = (COSDictionary) this.thread.getDictionaryObject("F");
        if (cOSDictionary != null) {
            return new PDThreadBead(cOSDictionary);
        }
        return null;
    }

    public void setFirstBead(PDThreadBead pDThreadBead) {
        if (pDThreadBead != null) {
            pDThreadBead.setThread(this);
        }
        this.thread.setItem("F", pDThreadBead);
    }
}
