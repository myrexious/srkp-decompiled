package com.tom_roush.pdfbox.multipdf;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.io.MemoryUsageSetting;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDDocumentCatalog;
import com.tom_roush.pdfbox.pdmodel.PDDocumentInformation;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageTree;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.PDStructureElementNameTreeNode;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDMetadata;
import com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode;
import com.tom_roush.pdfbox.pdmodel.common.PDNumberTreeNode;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDMarkInfo;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDParentTreeValue;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureElement;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureTreeRoot;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDOutputIntent;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDField;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDNonTerminalField;
import com.tom_roush.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class PDFMergerUtility {
    private String destinationFileName;
    private OutputStream destinationStream;
    private boolean ignoreAcroFormErrors = false;
    private PDDocumentInformation destinationDocumentInformation = null;
    private PDMetadata destinationMetadata = null;
    private DocumentMergeMode documentMergeMode = DocumentMergeMode.PDFBOX_LEGACY_MODE;
    private AcroFormMergeMode acroFormMergeMode = AcroFormMergeMode.PDFBOX_LEGACY_MODE;
    private int nextFieldNum = 1;
    private final List<Object> sources = new ArrayList();

    /* loaded from: classes3.dex */
    public enum AcroFormMergeMode {
        JOIN_FORM_FIELDS_MODE,
        PDFBOX_LEGACY_MODE
    }

    /* loaded from: classes3.dex */
    public enum DocumentMergeMode {
        OPTIMIZE_RESOURCES_MODE,
        PDFBOX_LEGACY_MODE
    }

    public AcroFormMergeMode getAcroFormMergeMode() {
        return this.acroFormMergeMode;
    }

    public void setAcroFormMergeMode(AcroFormMergeMode acroFormMergeMode) {
        this.acroFormMergeMode = acroFormMergeMode;
    }

    public void setDocumentMergeMode(DocumentMergeMode documentMergeMode) {
        this.documentMergeMode = documentMergeMode;
    }

    public DocumentMergeMode getDocumentMergeMode() {
        return this.documentMergeMode;
    }

    public String getDestinationFileName() {
        return this.destinationFileName;
    }

    public void setDestinationFileName(String str) {
        this.destinationFileName = str;
    }

    public OutputStream getDestinationStream() {
        return this.destinationStream;
    }

    public void setDestinationStream(OutputStream outputStream) {
        this.destinationStream = outputStream;
    }

    public PDDocumentInformation getDestinationDocumentInformation() {
        return this.destinationDocumentInformation;
    }

    public void setDestinationDocumentInformation(PDDocumentInformation pDDocumentInformation) {
        this.destinationDocumentInformation = pDDocumentInformation;
    }

    public PDMetadata getDestinationMetadata() {
        return this.destinationMetadata;
    }

    public void setDestinationMetadata(PDMetadata pDMetadata) {
        this.destinationMetadata = pDMetadata;
    }

    public void addSource(String str) throws FileNotFoundException {
        addSource(new File(str));
    }

    public void addSource(File file) throws FileNotFoundException {
        this.sources.add(file);
    }

    public void addSource(InputStream inputStream) {
        this.sources.add(inputStream);
    }

    public void addSources(List<InputStream> list) {
        this.sources.addAll(list);
    }

    @Deprecated
    public void mergeDocuments() throws IOException {
        mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    }

    public void mergeDocuments(MemoryUsageSetting memoryUsageSetting) throws IOException {
        if (this.documentMergeMode == DocumentMergeMode.PDFBOX_LEGACY_MODE) {
            legacyMergeDocuments(memoryUsageSetting);
        } else if (this.documentMergeMode == DocumentMergeMode.OPTIMIZE_RESOURCES_MODE) {
            optimizedMergeDocuments(memoryUsageSetting);
        }
    }

    private void optimizedMergeDocuments(MemoryUsageSetting memoryUsageSetting) throws IOException {
        PDDocument pDDocument;
        PDDocument load;
        PDDocument pDDocument2 = null;
        try {
            pDDocument = new PDDocument(memoryUsageSetting);
        } catch (Throwable th) {
            th = th;
        }
        try {
            PDFCloneUtility pDFCloneUtility = new PDFCloneUtility(pDDocument);
            PDPageTree pages = pDDocument.getPages();
            for (Object obj : this.sources) {
                try {
                    if (obj instanceof File) {
                        load = PDDocument.load((File) obj, memoryUsageSetting);
                    } else {
                        load = PDDocument.load((InputStream) obj, memoryUsageSetting);
                    }
                    try {
                        Iterator<PDPage> it = load.getPages().iterator();
                        while (it.hasNext()) {
                            PDPage next = it.next();
                            PDPage pDPage = new PDPage((COSDictionary) pDFCloneUtility.cloneForNewDocument(next.getCOSObject()));
                            pDPage.setCropBox(next.getCropBox());
                            pDPage.setMediaBox(next.getMediaBox());
                            pDPage.setRotation(next.getRotation());
                            PDResources resources = next.getResources();
                            if (resources != null) {
                                pDPage.setResources(new PDResources((COSDictionary) pDFCloneUtility.cloneForNewDocument(resources)));
                            } else {
                                pDPage.setResources(new PDResources());
                            }
                            pages.add(pDPage);
                        }
                        IOUtils.closeQuietly(load);
                    } catch (Throwable th2) {
                        th = th2;
                        pDDocument2 = load;
                        IOUtils.closeQuietly(pDDocument2);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            OutputStream outputStream = this.destinationStream;
            if (outputStream == null) {
                pDDocument.save(this.destinationFileName);
            } else {
                pDDocument.save(outputStream);
            }
            IOUtils.closeQuietly(pDDocument);
        } catch (Throwable th4) {
            th = th4;
            pDDocument2 = pDDocument;
            IOUtils.closeQuietly(pDDocument2);
            throw th;
        }
    }

    private void legacyMergeDocuments(MemoryUsageSetting memoryUsageSetting) throws IOException {
        PDDocument pDDocument;
        MemoryUsageSetting memoryUsageSetting2;
        PDDocument load;
        if (this.sources.size() <= 0) {
            return;
        }
        ArrayList<PDDocument> arrayList = new ArrayList(this.sources.size());
        try {
            if (memoryUsageSetting != null) {
                memoryUsageSetting2 = memoryUsageSetting.getPartitionedCopy(this.sources.size() + 1);
            } else {
                memoryUsageSetting2 = MemoryUsageSetting.setupMainMemoryOnly();
            }
            pDDocument = new PDDocument(memoryUsageSetting2);
            try {
                for (Object obj : this.sources) {
                    if (obj instanceof File) {
                        load = PDDocument.load((File) obj, memoryUsageSetting2);
                    } else {
                        load = PDDocument.load((InputStream) obj, memoryUsageSetting2);
                    }
                    arrayList.add(load);
                    appendDocument(pDDocument, load);
                }
                PDDocumentInformation pDDocumentInformation = this.destinationDocumentInformation;
                if (pDDocumentInformation != null) {
                    pDDocument.setDocumentInformation(pDDocumentInformation);
                }
                if (this.destinationMetadata != null) {
                    pDDocument.getDocumentCatalog().setMetadata(this.destinationMetadata);
                }
                OutputStream outputStream = this.destinationStream;
                if (outputStream == null) {
                    pDDocument.save(this.destinationFileName);
                } else {
                    pDDocument.save(outputStream);
                }
                IOUtils.closeAndLogException(pDDocument, "PDDocument", null);
                for (PDDocument pDDocument2 : arrayList) {
                    IOUtils.closeAndLogException(pDDocument2, "PDDocument", null);
                }
            } catch (Throwable th) {
                th = th;
                if (pDDocument != null) {
                    IOUtils.closeAndLogException(pDDocument, "PDDocument", null);
                }
                for (PDDocument pDDocument3 : arrayList) {
                    IOUtils.closeAndLogException(pDDocument3, "PDDocument", null);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            pDDocument = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:314:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:399:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void appendDocument(com.tom_roush.pdfbox.pdmodel.PDDocument r23, com.tom_roush.pdfbox.pdmodel.PDDocument r24) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.multipdf.PDFMergerUtility.appendDocument(com.tom_roush.pdfbox.pdmodel.PDDocument, com.tom_roush.pdfbox.pdmodel.PDDocument):void");
    }

    private void mergeViewerPreferences(PDDocumentCatalog pDDocumentCatalog, PDDocumentCatalog pDDocumentCatalog2) {
        PDViewerPreferences viewerPreferences = pDDocumentCatalog2.getViewerPreferences();
        if (viewerPreferences == null) {
            return;
        }
        PDViewerPreferences viewerPreferences2 = pDDocumentCatalog.getViewerPreferences();
        if (viewerPreferences2 == null) {
            viewerPreferences2 = new PDViewerPreferences(new COSDictionary());
            pDDocumentCatalog.setViewerPreferences(viewerPreferences2);
        }
        mergeInto(viewerPreferences.getCOSObject(), viewerPreferences2.getCOSObject(), Collections.emptySet());
        if (viewerPreferences.hideToolbar() || viewerPreferences2.hideToolbar()) {
            viewerPreferences2.setHideToolbar(true);
        }
        if (viewerPreferences.hideMenubar() || viewerPreferences2.hideMenubar()) {
            viewerPreferences2.setHideMenubar(true);
        }
        if (viewerPreferences.hideWindowUI() || viewerPreferences2.hideWindowUI()) {
            viewerPreferences2.setHideWindowUI(true);
        }
        if (viewerPreferences.fitWindow() || viewerPreferences2.fitWindow()) {
            viewerPreferences2.setFitWindow(true);
        }
        if (viewerPreferences.centerWindow() || viewerPreferences2.centerWindow()) {
            viewerPreferences2.setCenterWindow(true);
        }
        if (viewerPreferences.displayDocTitle() || viewerPreferences2.displayDocTitle()) {
            viewerPreferences2.setDisplayDocTitle(true);
        }
    }

    private void mergeLanguage(PDDocumentCatalog pDDocumentCatalog, PDDocumentCatalog pDDocumentCatalog2) {
        String language;
        if (pDDocumentCatalog.getLanguage() != null || (language = pDDocumentCatalog2.getLanguage()) == null) {
            return;
        }
        pDDocumentCatalog.setLanguage(language);
    }

    private void mergeMarkInfo(PDDocumentCatalog pDDocumentCatalog, PDDocumentCatalog pDDocumentCatalog2) {
        PDMarkInfo markInfo = pDDocumentCatalog.getMarkInfo();
        PDMarkInfo markInfo2 = pDDocumentCatalog2.getMarkInfo();
        if (markInfo == null) {
            markInfo = new PDMarkInfo();
        }
        if (markInfo2 == null) {
            markInfo2 = new PDMarkInfo();
        }
        boolean z = true;
        markInfo.setMarked(true);
        markInfo.setSuspect(markInfo2.isSuspect() || markInfo.isSuspect());
        if (!markInfo2.usesUserProperties() && !markInfo.usesUserProperties()) {
            z = false;
        }
        markInfo.setSuspect(z);
        pDDocumentCatalog.setMarkInfo(markInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeKEntries(PDFCloneUtility pDFCloneUtility, PDStructureTreeRoot pDStructureTreeRoot, PDStructureTreeRoot pDStructureTreeRoot2) throws IOException {
        COSArray cOSArray;
        COSBase k = pDStructureTreeRoot.getK();
        COSArray cOSArray2 = new COSArray();
        COSBase cloneForNewDocument = pDFCloneUtility.cloneForNewDocument(k);
        if (cloneForNewDocument instanceof COSArray) {
            cOSArray2.addAll((COSArray) cloneForNewDocument);
        } else if (cloneForNewDocument instanceof COSDictionary) {
            cOSArray2.add(cloneForNewDocument);
        }
        if (cOSArray2.size() == 0) {
            return;
        }
        COSArray cOSArray3 = new COSArray();
        COSBase k2 = pDStructureTreeRoot2.getK();
        if (k2 instanceof COSArray) {
            cOSArray3.addAll((COSArray) k2);
        } else if (k2 instanceof COSDictionary) {
            cOSArray3.add(k2);
        }
        if (cOSArray3.size() == 1 && (cOSArray3.getObject(0) instanceof COSDictionary)) {
            COSDictionary cOSDictionary = (COSDictionary) cOSArray3.getObject(0);
            if (COSName.DOCUMENT.equals(cOSDictionary.getCOSName(COSName.S)) && (cOSArray = cOSDictionary.getCOSArray(COSName.K)) != null && hasOnlyDocumentsOrParts(cOSArray)) {
                cOSArray.addAll(cOSArray2);
                updateParentEntry(cOSArray, cOSDictionary, COSName.PART);
                return;
            }
        }
        if (cOSArray3.size() == 0) {
            updateParentEntry(cOSArray2, pDStructureTreeRoot2.getCOSObject(), null);
            pDStructureTreeRoot2.setK(cOSArray2);
            return;
        }
        cOSArray3.addAll(cOSArray2);
        COSDictionary cOSDictionary2 = new COSDictionary();
        updateParentEntry(cOSArray3, cOSDictionary2, hasOnlyDocumentsOrParts(cOSArray3) ? COSName.PART : null);
        cOSDictionary2.setItem(COSName.K, (COSBase) cOSArray3);
        cOSDictionary2.setItem(COSName.P, pDStructureTreeRoot2);
        cOSDictionary2.setItem(COSName.S, COSName.DOCUMENT);
        pDStructureTreeRoot2.setK(cOSDictionary2);
    }

    private boolean hasOnlyDocumentsOrParts(COSArray cOSArray) {
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (!(object instanceof COSDictionary)) {
                return false;
            }
            COSName cOSName = ((COSDictionary) object).getCOSName(COSName.S);
            if (!COSName.DOCUMENT.equals(cOSName) && !COSName.PART.equals(cOSName)) {
                return false;
            }
        }
        return true;
    }

    private void updateParentEntry(COSArray cOSArray, COSDictionary cOSDictionary, COSName cOSName) {
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSDictionary) {
                COSDictionary cOSDictionary2 = (COSDictionary) object;
                cOSDictionary2.setItem(COSName.P, (COSBase) cOSDictionary);
                if (cOSName != null) {
                    cOSDictionary2.setItem(COSName.S, (COSBase) cOSName);
                }
            }
        }
    }

    private void mergeIDTree(PDFCloneUtility pDFCloneUtility, PDStructureTreeRoot pDStructureTreeRoot, PDStructureTreeRoot pDStructureTreeRoot2) throws IOException {
        PDNameTreeNode<PDStructureElement> iDTree = pDStructureTreeRoot.getIDTree();
        if (iDTree == null) {
            return;
        }
        PDNameTreeNode<PDStructureElement> iDTree2 = pDStructureTreeRoot2.getIDTree();
        if (iDTree2 == null) {
            iDTree2 = new PDStructureElementNameTreeNode();
        }
        Map<String, PDStructureElement> iDTreeAsMap = getIDTreeAsMap(iDTree);
        Map<String, PDStructureElement> iDTreeAsMap2 = getIDTreeAsMap(iDTree2);
        for (Map.Entry<String, PDStructureElement> entry : iDTreeAsMap.entrySet()) {
            if (iDTreeAsMap2.containsKey(entry.getKey())) {
                Log.w("PdfBox-Android", "key " + entry.getKey() + " already exists in destination IDTree");
            } else {
                iDTreeAsMap2.put(entry.getKey(), new PDStructureElement((COSDictionary) pDFCloneUtility.cloneForNewDocument(entry.getValue().getCOSObject())));
            }
        }
        PDNameTreeNode<PDStructureElement> pDStructureElementNameTreeNode = new PDStructureElementNameTreeNode();
        pDStructureElementNameTreeNode.setNames(iDTreeAsMap2);
        pDStructureTreeRoot2.setIDTree(pDStructureElementNameTreeNode);
    }

    static Map<String, PDStructureElement> getIDTreeAsMap(PDNameTreeNode<PDStructureElement> pDNameTreeNode) throws IOException {
        LinkedHashMap linkedHashMap;
        Map<String, PDStructureElement> names = pDNameTreeNode.getNames();
        if (names == null) {
            linkedHashMap = new LinkedHashMap();
        } else {
            linkedHashMap = new LinkedHashMap(names);
        }
        List<PDNameTreeNode<PDStructureElement>> kids = pDNameTreeNode.getKids();
        if (kids != null) {
            for (PDNameTreeNode<PDStructureElement> pDNameTreeNode2 : kids) {
                linkedHashMap.putAll(getIDTreeAsMap(pDNameTreeNode2));
            }
        }
        return linkedHashMap;
    }

    static Map<Integer, COSObjectable> getNumberTreeAsMap(PDNumberTreeNode pDNumberTreeNode) throws IOException {
        LinkedHashMap linkedHashMap;
        Map<Integer, COSObjectable> numbers = pDNumberTreeNode.getNumbers();
        if (numbers == null) {
            linkedHashMap = new LinkedHashMap();
        } else {
            linkedHashMap = new LinkedHashMap(numbers);
        }
        List<PDNumberTreeNode> kids = pDNumberTreeNode.getKids();
        if (kids != null) {
            for (PDNumberTreeNode pDNumberTreeNode2 : kids) {
                linkedHashMap.putAll(getNumberTreeAsMap(pDNumberTreeNode2));
            }
        }
        return linkedHashMap;
    }

    private void mergeRoleMap(PDStructureTreeRoot pDStructureTreeRoot, PDStructureTreeRoot pDStructureTreeRoot2) {
        COSDictionary cOSDictionary = pDStructureTreeRoot.getCOSObject().getCOSDictionary(COSName.ROLE_MAP);
        COSDictionary cOSDictionary2 = pDStructureTreeRoot2.getCOSObject().getCOSDictionary(COSName.ROLE_MAP);
        if (cOSDictionary == null) {
            return;
        }
        if (cOSDictionary2 == null) {
            pDStructureTreeRoot2.getCOSObject().setItem(COSName.ROLE_MAP, (COSBase) cOSDictionary);
            return;
        }
        for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
            COSBase dictionaryObject = cOSDictionary2.getDictionaryObject(entry.getKey());
            if (dictionaryObject == null || !dictionaryObject.equals(entry.getValue())) {
                if (cOSDictionary2.containsKey(entry.getKey())) {
                    Log.w("PdfBox-Android", "key " + entry.getKey() + " already exists in destination RoleMap");
                } else {
                    cOSDictionary2.setItem(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private void mergeOutputIntents(PDFCloneUtility pDFCloneUtility, PDDocumentCatalog pDDocumentCatalog, PDDocumentCatalog pDDocumentCatalog2) throws IOException {
        boolean z;
        List<PDOutputIntent> outputIntents = pDDocumentCatalog.getOutputIntents();
        List<PDOutputIntent> outputIntents2 = pDDocumentCatalog2.getOutputIntents();
        for (PDOutputIntent pDOutputIntent : outputIntents) {
            String outputConditionIdentifier = pDOutputIntent.getOutputConditionIdentifier();
            if (outputConditionIdentifier != null && !TypedValues.Custom.NAME.equals(outputConditionIdentifier)) {
                Iterator<PDOutputIntent> it = outputIntents2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getOutputConditionIdentifier().equals(outputConditionIdentifier)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                }
            }
            pDDocumentCatalog2.addOutputIntent(new PDOutputIntent((COSDictionary) pDFCloneUtility.cloneForNewDocument(pDOutputIntent)));
            outputIntents2.add(pDOutputIntent);
        }
    }

    private void mergeAcroForm(PDFCloneUtility pDFCloneUtility, PDDocumentCatalog pDDocumentCatalog, PDDocumentCatalog pDDocumentCatalog2) throws IOException {
        try {
            PDAcroForm acroForm = pDDocumentCatalog.getAcroForm();
            PDAcroForm acroForm2 = pDDocumentCatalog2.getAcroForm();
            if (acroForm == null && acroForm2 != null) {
                pDDocumentCatalog.getCOSObject().setItem(COSName.ACRO_FORM, pDFCloneUtility.cloneForNewDocument(acroForm2.getCOSObject()));
            } else if (acroForm2 != null) {
                if (this.acroFormMergeMode == AcroFormMergeMode.PDFBOX_LEGACY_MODE) {
                    acroFormLegacyMode(pDFCloneUtility, acroForm, acroForm2);
                } else if (this.acroFormMergeMode == AcroFormMergeMode.JOIN_FORM_FIELDS_MODE) {
                    acroFormJoinFieldsMode(pDFCloneUtility, acroForm, acroForm2);
                }
            }
        } catch (IOException e) {
            if (!this.ignoreAcroFormErrors) {
                throw new IOException(e);
            }
        }
    }

    private void acroFormJoinFieldsMode(PDFCloneUtility pDFCloneUtility, PDAcroForm pDAcroForm, PDAcroForm pDAcroForm2) throws IOException {
        COSArray cOSArray;
        List<PDField> fields = pDAcroForm2.getFields();
        if (fields == null || fields.isEmpty()) {
            return;
        }
        COSBase item = pDAcroForm.getCOSObject().getItem(COSName.FIELDS);
        if (item instanceof COSArray) {
            cOSArray = (COSArray) item;
        } else {
            cOSArray = new COSArray();
        }
        Iterator<PDField> it = pDAcroForm2.getFieldTree().iterator();
        while (it.hasNext()) {
            PDField next = it.next();
            PDField field = pDAcroForm.getField(next.getFullyQualifiedName());
            if (field == null) {
                cOSArray.add((COSBase) ((COSDictionary) pDFCloneUtility.cloneForNewDocument(next.getCOSObject())));
            } else {
                mergeFields(pDFCloneUtility, field, next);
            }
        }
        pDAcroForm.getCOSObject().setItem(COSName.FIELDS, (COSBase) cOSArray);
    }

    private void mergeFields(PDFCloneUtility pDFCloneUtility, PDField pDField, PDField pDField2) {
        if ((pDField instanceof PDNonTerminalField) && (pDField2 instanceof PDNonTerminalField)) {
            Log.i("PdfBox-Android", "Skipping non terminal field " + pDField2.getFullyQualifiedName());
        } else if (pDField.getFieldType() == "Tx" && pDField.getFieldType() == "Tx") {
            if (pDField.getCOSObject().containsKey(COSName.KIDS)) {
                COSArray cOSArray = pDField.getCOSObject().getCOSArray(COSName.KIDS);
                for (PDAnnotationWidget pDAnnotationWidget : pDField2.getWidgets()) {
                    try {
                        cOSArray.add(pDFCloneUtility.cloneForNewDocument(pDAnnotationWidget.getCOSObject()));
                    } catch (IOException unused) {
                        Log.w("PdfBox-Android", "Unable to clone widget for source field " + pDField2.getFullyQualifiedName());
                    }
                }
                return;
            }
            COSArray cOSArray2 = new COSArray();
            try {
                COSDictionary cOSDictionary = (COSDictionary) pDFCloneUtility.cloneForNewDocument(pDField.getWidgets().get(0));
                cleanupWidgetCOSDictionary(cOSDictionary, true);
                cOSDictionary.setItem(COSName.PARENT, pDField);
                cOSArray2.add((COSBase) cOSDictionary);
                for (PDAnnotationWidget pDAnnotationWidget2 : pDField2.getWidgets()) {
                    try {
                        COSDictionary cOSDictionary2 = (COSDictionary) pDFCloneUtility.cloneForNewDocument(pDAnnotationWidget2.getCOSObject());
                        cleanupWidgetCOSDictionary(cOSDictionary2, false);
                        cOSDictionary2.setItem(COSName.PARENT, pDField);
                        cOSArray2.add((COSBase) cOSDictionary2);
                    } catch (IOException unused2) {
                        Log.w("PdfBox-Android", "Unable to clone widget for source field " + pDField2.getFullyQualifiedName());
                    }
                }
                pDField.getCOSObject().setItem(COSName.KIDS, (COSBase) cOSArray2);
                cleanupFieldCOSDictionary(pDField.getCOSObject());
            } catch (IOException unused3) {
                Log.w("PdfBox-Android", "Unable to clone widget for destination field " + pDField.getFullyQualifiedName());
            }
        } else {
            Log.i("PdfBox-Android", "Only merging two text fields is currently supported");
            Log.i("PdfBox-Android", "Skipping merging of " + pDField2.getFullyQualifiedName() + " into " + pDField.getFullyQualifiedName());
        }
    }

    private void cleanupFieldCOSDictionary(COSDictionary cOSDictionary) {
        cOSDictionary.removeItem(COSName.F);
        cOSDictionary.removeItem(COSName.MK);
        cOSDictionary.removeItem(COSName.P);
        cOSDictionary.removeItem(COSName.RECT);
        cOSDictionary.removeItem(COSName.SUBTYPE);
        cOSDictionary.removeItem(COSName.TYPE);
    }

    private void cleanupWidgetCOSDictionary(COSDictionary cOSDictionary, boolean z) {
        if (z) {
            cOSDictionary.removeItem(COSName.DA);
        }
        cOSDictionary.removeItem(COSName.FT);
        cOSDictionary.removeItem(COSName.T);
        cOSDictionary.removeItem(COSName.V);
    }

    private void acroFormLegacyMode(PDFCloneUtility pDFCloneUtility, PDAcroForm pDAcroForm, PDAcroForm pDAcroForm2) throws IOException {
        COSArray cOSArray;
        List<PDField> fields = pDAcroForm2.getFields();
        if (fields == null || fields.isEmpty()) {
            return;
        }
        Iterator<PDField> it = pDAcroForm.getFieldTree().iterator();
        while (it.hasNext()) {
            String partialName = it.next().getPartialName();
            if (partialName.startsWith("dummyFieldName")) {
                String substring = partialName.substring(14);
                if (substring.matches("\\d+")) {
                    this.nextFieldNum = Math.max(this.nextFieldNum, Integer.parseInt(substring) + 1);
                }
            }
        }
        COSBase item = pDAcroForm.getCOSObject().getItem(COSName.FIELDS);
        if (item instanceof COSArray) {
            cOSArray = (COSArray) item;
        } else {
            cOSArray = new COSArray();
        }
        for (PDField pDField : pDAcroForm2.getFields()) {
            COSDictionary cOSDictionary = (COSDictionary) pDFCloneUtility.cloneForNewDocument(pDField.getCOSObject());
            if (pDAcroForm.getField(pDField.getFullyQualifiedName()) != null) {
                COSName cOSName = COSName.T;
                StringBuilder sb = new StringBuilder("dummyFieldName");
                int i = this.nextFieldNum;
                this.nextFieldNum = i + 1;
                cOSDictionary.setString(cOSName, sb.append(i).toString());
            }
            cOSArray.add((COSBase) cOSDictionary);
        }
        pDAcroForm.getCOSObject().setItem(COSName.FIELDS, (COSBase) cOSArray);
    }

    public boolean isIgnoreAcroFormErrors() {
        return this.ignoreAcroFormErrors;
    }

    public void setIgnoreAcroFormErrors(boolean z) {
        this.ignoreAcroFormErrors = z;
    }

    private void updatePageReferences(PDFCloneUtility pDFCloneUtility, Map<Integer, COSObjectable> map, Map<COSDictionary, COSDictionary> map2) throws IOException {
        for (COSObjectable cOSObjectable : map.values()) {
            if (cOSObjectable != null) {
                COSBase cOSObject = ((PDParentTreeValue) cOSObjectable).getCOSObject();
                if (cOSObject instanceof COSArray) {
                    updatePageReferences(pDFCloneUtility, (COSArray) cOSObject, map2);
                } else {
                    updatePageReferences(pDFCloneUtility, (COSDictionary) cOSObject, map2);
                }
            }
        }
    }

    private void updatePageReferences(PDFCloneUtility pDFCloneUtility, COSDictionary cOSDictionary, Map<COSDictionary, COSDictionary> map) throws IOException {
        COSDictionary cOSDictionary2 = cOSDictionary.getCOSDictionary(COSName.PG);
        if (map.containsKey(cOSDictionary2)) {
            cOSDictionary.setItem(COSName.PG, (COSBase) map.get(cOSDictionary2));
        }
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.OBJ);
        if (dictionaryObject instanceof COSDictionary) {
            COSDictionary cOSDictionary3 = (COSDictionary) dictionaryObject;
            if (map.containsKey(cOSDictionary3)) {
                cOSDictionary.setItem(COSName.OBJ, (COSBase) map.get(cOSDictionary3));
            } else {
                COSBase item = cOSDictionary.getItem(COSName.OBJ);
                if (item instanceof COSObject) {
                    Log.d("PdfBox-Android", "clone potential orphan object in structure tree: " + item + ", Type: " + cOSDictionary3.getNameAsString(COSName.TYPE) + ", Subtype: " + cOSDictionary3.getNameAsString(COSName.SUBTYPE) + ", T: " + cOSDictionary3.getNameAsString(COSName.T));
                } else {
                    Log.d("PdfBox-Android", "clone potential orphan object in structure tree, Type: " + cOSDictionary3.getNameAsString(COSName.TYPE) + ", Subtype: " + cOSDictionary3.getNameAsString(COSName.SUBTYPE) + ", T: " + cOSDictionary3.getNameAsString(COSName.T));
                }
                cOSDictionary.setItem(COSName.OBJ, pDFCloneUtility.cloneForNewDocument(dictionaryObject));
            }
        }
        COSBase dictionaryObject2 = cOSDictionary.getDictionaryObject(COSName.K);
        if (dictionaryObject2 instanceof COSArray) {
            updatePageReferences(pDFCloneUtility, (COSArray) dictionaryObject2, map);
        } else if (dictionaryObject2 instanceof COSDictionary) {
            updatePageReferences(pDFCloneUtility, (COSDictionary) dictionaryObject2, map);
        }
    }

    private void updatePageReferences(PDFCloneUtility pDFCloneUtility, COSArray cOSArray, Map<COSDictionary, COSDictionary> map) throws IOException {
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSArray) {
                updatePageReferences(pDFCloneUtility, (COSArray) object, map);
            } else if (object instanceof COSDictionary) {
                updatePageReferences(pDFCloneUtility, (COSDictionary) object, map);
            }
        }
    }

    private void updateStructParentEntries(PDPage pDPage, int i) throws IOException {
        int structParents = pDPage.getStructParents();
        if (structParents >= 0) {
            pDPage.setStructParents(structParents + i);
        }
        List<PDAnnotation> annotations = pDPage.getAnnotations();
        ArrayList arrayList = new ArrayList(annotations.size());
        for (PDAnnotation pDAnnotation : annotations) {
            int structParent = pDAnnotation.getStructParent();
            if (structParent >= 0) {
                pDAnnotation.setStructParent(structParent + i);
            }
            arrayList.add(pDAnnotation);
        }
        pDPage.setAnnotations(arrayList);
    }

    private boolean isDynamicXfa(PDAcroForm pDAcroForm) {
        return pDAcroForm != null && pDAcroForm.xfaIsDynamic();
    }

    private void mergeInto(COSDictionary cOSDictionary, COSDictionary cOSDictionary2, Set<COSName> set) {
        for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
            if (!set.contains(entry.getKey()) && !cOSDictionary2.containsKey(entry.getKey())) {
                cOSDictionary2.setItem(entry.getKey(), entry.getValue());
            }
        }
    }
}
