package com.tom_roush.pdfbox.pdmodel;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDDestinationOrAction;
import com.tom_roush.pdfbox.pdmodel.common.PDMetadata;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabels;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDMarkInfo;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureTreeRoot;
import com.tom_roush.pdfbox.pdmodel.fixup.AcroFormDefaultFixup;
import com.tom_roush.pdfbox.pdmodel.fixup.PDDocumentFixup;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDOutputIntent;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentProperties;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionFactory;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDDocumentCatalogAdditionalActions;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDURIDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.pagenavigation.PDThread;
import com.tom_roush.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class PDDocumentCatalog implements COSObjectable {
    private PDDocumentFixup acroFormFixupApplied;
    private PDAcroForm cachedAcroForm;
    private final PDDocument document;
    private final COSDictionary root;

    public PDDocumentCatalog(PDDocument pDDocument) {
        this.document = pDDocument;
        COSDictionary cOSDictionary = new COSDictionary();
        this.root = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.CATALOG);
        pDDocument.getDocument().getTrailer().setItem(COSName.ROOT, (COSBase) cOSDictionary);
    }

    public PDDocumentCatalog(PDDocument pDDocument, COSDictionary cOSDictionary) {
        this.document = pDDocument;
        this.root = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.root;
    }

    public PDAcroForm getAcroForm() {
        return getAcroForm(new AcroFormDefaultFixup(this.document));
    }

    public PDAcroForm getAcroForm(PDDocumentFixup pDDocumentFixup) {
        if (pDDocumentFixup != null && pDDocumentFixup != this.acroFormFixupApplied) {
            pDDocumentFixup.apply();
            this.cachedAcroForm = null;
            this.acroFormFixupApplied = pDDocumentFixup;
        } else if (this.acroFormFixupApplied != null) {
            Log.d("PdfBox-Android", "AcroForm content has already been retrieved with fixes applied - original content changed because of that");
        }
        if (this.cachedAcroForm == null) {
            COSDictionary cOSDictionary = this.root.getCOSDictionary(COSName.ACRO_FORM);
            this.cachedAcroForm = cOSDictionary != null ? new PDAcroForm(this.document, cOSDictionary) : null;
        }
        return this.cachedAcroForm;
    }

    public void setAcroForm(PDAcroForm pDAcroForm) {
        this.root.setItem(COSName.ACRO_FORM, pDAcroForm);
        this.cachedAcroForm = null;
    }

    public PDPageTree getPages() {
        return new PDPageTree((COSDictionary) this.root.getDictionaryObject(COSName.PAGES), this.document);
    }

    public PDViewerPreferences getViewerPreferences() {
        COSBase dictionaryObject = this.root.getDictionaryObject(COSName.VIEWER_PREFERENCES);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDViewerPreferences((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setViewerPreferences(PDViewerPreferences pDViewerPreferences) {
        this.root.setItem(COSName.VIEWER_PREFERENCES, pDViewerPreferences);
    }

    public PDDocumentOutline getDocumentOutline() {
        COSBase dictionaryObject = this.root.getDictionaryObject(COSName.OUTLINES);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDDocumentOutline((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setDocumentOutline(PDDocumentOutline pDDocumentOutline) {
        this.root.setItem(COSName.OUTLINES, pDDocumentOutline);
    }

    public List<PDThread> getThreads() {
        COSArray cOSArray = (COSArray) this.root.getDictionaryObject(COSName.THREADS);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            this.root.setItem(COSName.THREADS, (COSBase) cOSArray);
        }
        ArrayList arrayList = new ArrayList(cOSArray.size());
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(new PDThread((COSDictionary) cOSArray.getObject(i)));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setThreads(List<PDThread> list) {
        this.root.setItem(COSName.THREADS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public PDMetadata getMetadata() {
        COSBase dictionaryObject = this.root.getDictionaryObject(COSName.METADATA);
        if (dictionaryObject instanceof COSStream) {
            return new PDMetadata((COSStream) dictionaryObject);
        }
        return null;
    }

    public void setMetadata(PDMetadata pDMetadata) {
        this.root.setItem(COSName.METADATA, pDMetadata);
    }

    public void setOpenAction(PDDestinationOrAction pDDestinationOrAction) {
        this.root.setItem(COSName.OPEN_ACTION, pDDestinationOrAction);
    }

    public PDDestinationOrAction getOpenAction() throws IOException {
        COSBase dictionaryObject = this.root.getDictionaryObject(COSName.OPEN_ACTION);
        if (dictionaryObject instanceof COSDictionary) {
            return PDActionFactory.createAction((COSDictionary) dictionaryObject);
        }
        if (dictionaryObject instanceof COSArray) {
            return PDDestination.create(dictionaryObject);
        }
        return null;
    }

    public PDDocumentCatalogAdditionalActions getActions() {
        COSDictionary cOSDictionary = (COSDictionary) this.root.getDictionaryObject(COSName.AA);
        if (cOSDictionary == null) {
            cOSDictionary = new COSDictionary();
            this.root.setItem(COSName.AA, (COSBase) cOSDictionary);
        }
        return new PDDocumentCatalogAdditionalActions(cOSDictionary);
    }

    public void setActions(PDDocumentCatalogAdditionalActions pDDocumentCatalogAdditionalActions) {
        this.root.setItem(COSName.AA, pDDocumentCatalogAdditionalActions);
    }

    public PDDocumentNameDictionary getNames() {
        COSDictionary cOSDictionary = (COSDictionary) this.root.getDictionaryObject(COSName.NAMES);
        if (cOSDictionary == null) {
            return null;
        }
        return new PDDocumentNameDictionary(this, cOSDictionary);
    }

    public PDDocumentNameDestinationDictionary getDests() {
        COSDictionary cOSDictionary = (COSDictionary) this.root.getDictionaryObject(COSName.DESTS);
        if (cOSDictionary != null) {
            return new PDDocumentNameDestinationDictionary(cOSDictionary);
        }
        return null;
    }

    public PDPageDestination findNamedDestinationPage(PDNamedDestination pDNamedDestination) throws IOException {
        PDDocumentNameDestinationDictionary dests;
        PDDestinationNameTreeNode dests2;
        PDDocumentNameDictionary names = getNames();
        PDPageDestination value = (names == null || (dests2 = names.getDests()) == null) ? null : dests2.getValue(pDNamedDestination.getNamedDestination());
        return (value != null || (dests = getDests()) == null) ? value : (PDPageDestination) dests.getDestination(pDNamedDestination.getNamedDestination());
    }

    public void setNames(PDDocumentNameDictionary pDDocumentNameDictionary) {
        this.root.setItem(COSName.NAMES, pDDocumentNameDictionary);
    }

    public PDMarkInfo getMarkInfo() {
        COSDictionary cOSDictionary = (COSDictionary) this.root.getDictionaryObject(COSName.MARK_INFO);
        if (cOSDictionary == null) {
            return null;
        }
        return new PDMarkInfo(cOSDictionary);
    }

    public void setMarkInfo(PDMarkInfo pDMarkInfo) {
        this.root.setItem(COSName.MARK_INFO, pDMarkInfo);
    }

    public List<PDOutputIntent> getOutputIntents() {
        ArrayList arrayList = new ArrayList();
        COSArray cOSArray = (COSArray) this.root.getDictionaryObject(COSName.OUTPUT_INTENTS);
        if (cOSArray != null) {
            Iterator<COSBase> it = cOSArray.iterator();
            while (it.hasNext()) {
                COSBase next = it.next();
                if (next instanceof COSObject) {
                    next = ((COSObject) next).getObject();
                }
                arrayList.add(new PDOutputIntent((COSDictionary) next));
            }
        }
        return arrayList;
    }

    public void addOutputIntent(PDOutputIntent pDOutputIntent) {
        COSArray cOSArray = (COSArray) this.root.getDictionaryObject(COSName.OUTPUT_INTENTS);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            this.root.setItem(COSName.OUTPUT_INTENTS, (COSBase) cOSArray);
        }
        cOSArray.add(pDOutputIntent.getCOSObject());
    }

    public void setOutputIntents(List<PDOutputIntent> list) {
        COSArray cOSArray = new COSArray();
        for (PDOutputIntent pDOutputIntent : list) {
            cOSArray.add(pDOutputIntent.getCOSObject());
        }
        this.root.setItem(COSName.OUTPUT_INTENTS, (COSBase) cOSArray);
    }

    public PageMode getPageMode() {
        String nameAsString = this.root.getNameAsString(COSName.PAGE_MODE);
        if (nameAsString != null) {
            try {
                return PageMode.fromString(nameAsString);
            } catch (IllegalArgumentException unused) {
                return PageMode.USE_NONE;
            }
        }
        return PageMode.USE_NONE;
    }

    public void setPageMode(PageMode pageMode) {
        this.root.setName(COSName.PAGE_MODE, pageMode.stringValue());
    }

    public PageLayout getPageLayout() {
        String nameAsString = this.root.getNameAsString(COSName.PAGE_LAYOUT);
        if (nameAsString != null && !nameAsString.isEmpty()) {
            try {
                return PageLayout.fromString(nameAsString);
            } catch (IllegalArgumentException e) {
                Log.w("PdfBox-Android", "Invalid PageLayout used '" + nameAsString + "' - returning PageLayout.SINGLE_PAGE", e);
            }
        }
        return PageLayout.SINGLE_PAGE;
    }

    public void setPageLayout(PageLayout pageLayout) {
        this.root.setName(COSName.PAGE_LAYOUT, pageLayout.stringValue());
    }

    public PDURIDictionary getURI() {
        COSDictionary cOSDictionary = (COSDictionary) this.root.getDictionaryObject(COSName.URI);
        if (cOSDictionary == null) {
            return null;
        }
        return new PDURIDictionary(cOSDictionary);
    }

    public void setURI(PDURIDictionary pDURIDictionary) {
        this.root.setItem(COSName.URI, pDURIDictionary);
    }

    public PDStructureTreeRoot getStructureTreeRoot() {
        COSDictionary cOSDictionary = this.root.getCOSDictionary(COSName.STRUCT_TREE_ROOT);
        if (cOSDictionary == null) {
            return null;
        }
        return new PDStructureTreeRoot(cOSDictionary);
    }

    public void setStructureTreeRoot(PDStructureTreeRoot pDStructureTreeRoot) {
        this.root.setItem(COSName.STRUCT_TREE_ROOT, pDStructureTreeRoot);
    }

    public String getLanguage() {
        return this.root.getString(COSName.LANG);
    }

    public void setLanguage(String str) {
        this.root.setString(COSName.LANG, str);
    }

    public String getVersion() {
        return this.root.getNameAsString(COSName.VERSION);
    }

    public void setVersion(String str) {
        this.root.setName(COSName.VERSION, str);
    }

    public PDPageLabels getPageLabels() throws IOException {
        COSDictionary cOSDictionary = (COSDictionary) this.root.getDictionaryObject(COSName.PAGE_LABELS);
        if (cOSDictionary == null) {
            return null;
        }
        return new PDPageLabels(this.document, cOSDictionary);
    }

    public void setPageLabels(PDPageLabels pDPageLabels) {
        this.root.setItem(COSName.PAGE_LABELS, pDPageLabels);
    }

    public PDOptionalContentProperties getOCProperties() {
        COSDictionary cOSDictionary = (COSDictionary) this.root.getDictionaryObject(COSName.OCPROPERTIES);
        if (cOSDictionary == null) {
            return null;
        }
        return new PDOptionalContentProperties(cOSDictionary);
    }

    public void setOCProperties(PDOptionalContentProperties pDOptionalContentProperties) {
        this.root.setItem(COSName.OCPROPERTIES, pDOptionalContentProperties);
        if (pDOptionalContentProperties == null || this.document.getVersion() >= 1.5d) {
            return;
        }
        this.document.setVersion(1.5f);
    }
}
