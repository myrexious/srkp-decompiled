package com.tom_roush.pdfbox.multipdf;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PageExtractor {
    private int endPage;
    private final PDDocument sourceDocument;
    private int startPage;

    public PageExtractor(PDDocument pDDocument) {
        this.startPage = 1;
        this.sourceDocument = pDDocument;
        this.endPage = pDDocument.getNumberOfPages();
    }

    public PageExtractor(PDDocument pDDocument, int i, int i2) {
        this.sourceDocument = pDDocument;
        this.startPage = i;
        this.endPage = i2;
    }

    public PDDocument extract() throws IOException {
        PDDocument pDDocument = new PDDocument();
        pDDocument.setDocumentInformation(this.sourceDocument.getDocumentInformation());
        pDDocument.getDocumentCatalog().setViewerPreferences(this.sourceDocument.getDocumentCatalog().getViewerPreferences());
        for (int i = this.startPage; i <= this.endPage; i++) {
            PDPage page = this.sourceDocument.getPage(i - 1);
            PDPage importPage = pDDocument.importPage(page);
            if (page.getResources() != null && !page.getCOSObject().containsKey(COSName.RESOURCES)) {
                importPage.setResources(page.getResources());
                Log.i("PdfBox-Android", "Done in PageExtractor");
            }
        }
        return pDDocument;
    }

    public int getStartPage() {
        return this.startPage;
    }

    public void setStartPage(int i) {
        this.startPage = i;
    }

    public int getEndPage() {
        return this.endPage;
    }

    public void setEndPage(int i) {
        this.endPage = i;
    }
}
