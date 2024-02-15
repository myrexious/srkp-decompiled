package com.tom_roush.pdfbox.cos;

import java.io.IOException;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
public class COSObject extends COSBase implements COSUpdateInfo {
    private COSBase baseObject;
    private boolean dereferencingInProgress = false;
    private int generationNumber;
    private boolean needToBeUpdated;
    private long objectNumber;

    public COSObject(COSBase cOSBase) throws IOException {
        setObject(cOSBase);
    }

    public COSBase getDictionaryObject(COSName cOSName) {
        COSBase cOSBase = this.baseObject;
        if (cOSBase instanceof COSDictionary) {
            return ((COSDictionary) cOSBase).getDictionaryObject(cOSName);
        }
        return null;
    }

    public COSBase getItem(COSName cOSName) {
        COSBase cOSBase = this.baseObject;
        if (cOSBase instanceof COSDictionary) {
            return ((COSDictionary) cOSBase).getItem(cOSName);
        }
        return null;
    }

    public COSBase getObject() {
        return this.baseObject;
    }

    public final void setObject(COSBase cOSBase) throws IOException {
        this.baseObject = cOSBase;
    }

    public boolean derefencingInProgress() {
        return this.dereferencingInProgress;
    }

    public void dereferencingStarted() {
        this.dereferencingInProgress = true;
    }

    public void dereferencingFinished() {
        this.dereferencingInProgress = false;
    }

    public String toString() {
        return "COSObject{" + this.objectNumber + ", " + this.generationNumber + StringSubstitutor.DEFAULT_VAR_END;
    }

    public long getObjectNumber() {
        return this.objectNumber;
    }

    public void setObjectNumber(long j) {
        this.objectNumber = j;
    }

    public int getGenerationNumber() {
        return this.generationNumber;
    }

    public void setGenerationNumber(int i) {
        this.generationNumber = i;
    }

    @Override // com.tom_roush.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        COSBase object = getObject();
        return object != null ? object.accept(iCOSVisitor) : COSNull.NULL.accept(iCOSVisitor);
    }

    @Override // com.tom_roush.pdfbox.cos.COSUpdateInfo
    public boolean isNeedToBeUpdated() {
        return this.needToBeUpdated;
    }

    @Override // com.tom_roush.pdfbox.cos.COSUpdateInfo
    public void setNeedToBeUpdated(boolean z) {
        this.needToBeUpdated = z;
    }
}
