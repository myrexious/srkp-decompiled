package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class FDFPage implements COSObjectable {
    private final COSDictionary page;

    public FDFPage() {
        this.page = new COSDictionary();
    }

    public FDFPage(COSDictionary cOSDictionary) {
        this.page = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.page;
    }

    public List<FDFTemplate> getTemplates() {
        COSArray cOSArray = (COSArray) this.page.getDictionaryObject(COSName.TEMPLATES);
        if (cOSArray != null) {
            ArrayList arrayList = new ArrayList(cOSArray.size());
            for (int i = 0; i < cOSArray.size(); i++) {
                arrayList.add(new FDFTemplate((COSDictionary) cOSArray.getObject(i)));
            }
            return new COSArrayList(arrayList, cOSArray);
        }
        return null;
    }

    public void setTemplates(List<FDFTemplate> list) {
        this.page.setItem(COSName.TEMPLATES, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public FDFPageInfo getPageInfo() {
        COSDictionary cOSDictionary = this.page.getCOSDictionary(COSName.INFO);
        if (cOSDictionary != null) {
            return new FDFPageInfo(cOSDictionary);
        }
        return null;
    }

    public void setPageInfo(FDFPageInfo fDFPageInfo) {
        this.page.setItem(COSName.INFO, fDFPageInfo);
    }
}
