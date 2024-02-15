package com.tom_roush.pdfbox.multipdf;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.io.MemoryUsageSetting;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDDocumentInformation;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDAction;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class Splitter {
    private PDDocument currentDestinationDocument;
    private int currentPageNumber;
    private List<PDDocument> destinationDocuments;
    private PDDocument sourceDocument;
    private int splitLength = 1;
    private int startPage = Integer.MIN_VALUE;
    private int endPage = Integer.MAX_VALUE;
    private MemoryUsageSetting memoryUsageSetting = null;

    public MemoryUsageSetting getMemoryUsageSetting() {
        return this.memoryUsageSetting;
    }

    public void setMemoryUsageSetting(MemoryUsageSetting memoryUsageSetting) {
        this.memoryUsageSetting = memoryUsageSetting;
    }

    public List<PDDocument> split(PDDocument pDDocument) throws IOException {
        this.currentPageNumber = 0;
        this.destinationDocuments = new ArrayList();
        this.sourceDocument = pDDocument;
        processPages();
        return this.destinationDocuments;
    }

    public void setSplitAtPage(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Number of pages is smaller than one");
        }
        this.splitLength = i;
    }

    public void setStartPage(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Start page is smaller than one");
        }
        this.startPage = i;
    }

    public void setEndPage(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("End page is smaller than one");
        }
        this.endPage = i;
    }

    private void processPages() throws IOException {
        Iterator<PDPage> it = this.sourceDocument.getPages().iterator();
        while (it.hasNext()) {
            PDPage next = it.next();
            int i = this.currentPageNumber;
            if (i + 1 >= this.startPage && i + 1 <= this.endPage) {
                processPage(next);
                this.currentPageNumber++;
            } else if (i > this.endPage) {
                return;
            } else {
                this.currentPageNumber = i + 1;
            }
        }
    }

    private void createNewDocumentIfNecessary() throws IOException {
        if (splitAtPage(this.currentPageNumber) || this.currentDestinationDocument == null) {
            PDDocument createNewDocument = createNewDocument();
            this.currentDestinationDocument = createNewDocument;
            this.destinationDocuments.add(createNewDocument);
        }
    }

    protected boolean splitAtPage(int i) {
        return ((i + 1) - Math.max(1, this.startPage)) % this.splitLength == 0;
    }

    protected PDDocument createNewDocument() throws IOException {
        PDDocument pDDocument = this.memoryUsageSetting == null ? new PDDocument() : new PDDocument(this.memoryUsageSetting);
        pDDocument.getDocument().setVersion(getSourceDocument().getVersion());
        PDDocumentInformation documentInformation = getSourceDocument().getDocumentInformation();
        if (documentInformation != null) {
            COSDictionary cOSObject = documentInformation.getCOSObject();
            COSDictionary cOSDictionary = new COSDictionary();
            for (COSName cOSName : cOSObject.keySet()) {
                COSBase dictionaryObject = cOSObject.getDictionaryObject(cOSName);
                if (dictionaryObject instanceof COSDictionary) {
                    Log.w("PdfBox-Android", "Nested entry for key '" + cOSName.getName() + "' skipped in document information dictionary");
                    if (this.sourceDocument.getDocumentCatalog().getCOSObject() == this.sourceDocument.getDocumentInformation().getCOSObject()) {
                        Log.w("PdfBox-Android", "/Root and /Info share the same dictionary");
                    }
                } else if (!COSName.TYPE.equals(cOSName)) {
                    cOSDictionary.setItem(cOSName, dictionaryObject);
                }
            }
            pDDocument.setDocumentInformation(new PDDocumentInformation(cOSDictionary));
        }
        pDDocument.getDocumentCatalog().setViewerPreferences(getSourceDocument().getDocumentCatalog().getViewerPreferences());
        return pDDocument;
    }

    protected void processPage(PDPage pDPage) throws IOException {
        createNewDocumentIfNecessary();
        PDPage importPage = getDestinationDocument().importPage(pDPage);
        if (pDPage.getResources() != null && !pDPage.getCOSObject().containsKey(COSName.RESOURCES)) {
            importPage.setResources(pDPage.getResources());
            Log.i("PdfBox-Android", "Resources imported in Splitter");
        }
        processAnnotations(importPage);
    }

    private void processAnnotations(PDPage pDPage) throws IOException {
        for (PDAnnotation pDAnnotation : pDPage.getAnnotations()) {
            if (pDAnnotation instanceof PDAnnotationLink) {
                PDAnnotationLink pDAnnotationLink = (PDAnnotationLink) pDAnnotation;
                PDDestination destination = pDAnnotationLink.getDestination();
                PDAction action = pDAnnotationLink.getAction();
                if (destination == null && (action instanceof PDActionGoTo)) {
                    destination = ((PDActionGoTo) action).getDestination();
                }
                if (destination instanceof PDPageDestination) {
                    ((PDPageDestination) destination).setPage(null);
                }
            }
            pDAnnotation.setPage(null);
        }
    }

    protected final PDDocument getSourceDocument() {
        return this.sourceDocument;
    }

    protected final PDDocument getDestinationDocument() {
        return this.currentDestinationDocument;
    }
}
