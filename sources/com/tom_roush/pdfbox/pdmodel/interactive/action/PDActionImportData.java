package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDActionImportData extends PDAction {
    public static final String SUB_TYPE = "ImportData";

    public PDActionImportData() {
        setSubType(SUB_TYPE);
    }

    public PDActionImportData(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(this.action.getDictionaryObject(COSName.F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        this.action.setItem(COSName.F, pDFileSpecification);
    }
}
