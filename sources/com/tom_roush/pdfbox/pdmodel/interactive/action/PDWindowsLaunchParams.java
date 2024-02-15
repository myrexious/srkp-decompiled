package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes3.dex */
public class PDWindowsLaunchParams implements COSObjectable {
    public static final String OPERATION_OPEN = "open";
    public static final String OPERATION_PRINT = "print";
    protected COSDictionary params;

    public PDWindowsLaunchParams() {
        this.params = new COSDictionary();
    }

    public PDWindowsLaunchParams(COSDictionary cOSDictionary) {
        this.params = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.params;
    }

    public String getFilename() {
        return this.params.getString(COSName.F);
    }

    public void setFilename(String str) {
        this.params.setString(COSName.F, str);
    }

    public String getDirectory() {
        return this.params.getString(COSName.D);
    }

    public void setDirectory(String str) {
        this.params.setString(COSName.D, str);
    }

    public String getOperation() {
        return this.params.getString(COSName.O, "open");
    }

    public void setOperation(String str) {
        this.params.setString(COSName.D, str);
    }

    public String getExecuteParam() {
        return this.params.getString(COSName.P);
    }

    public void setExecuteParam(String str) {
        this.params.setString(COSName.P, str);
    }
}
