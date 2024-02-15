package com.tom_roush.pdfbox.pdmodel.interactive.form;

import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.PDPageTree;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFCatalog;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFDictionary;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFDocument;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFField;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public final class PDAcroForm implements COSObjectable {
    private static final int FLAG_APPEND_ONLY = 2;
    private static final int FLAG_SIGNATURES_EXIST = 1;
    private final COSDictionary dictionary;
    private final PDDocument document;
    private Map<String, PDField> fieldCache;
    private ScriptingHandler scriptingHandler;

    public PDAcroForm(PDDocument pDDocument) {
        this.document = pDDocument;
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.FIELDS, (COSBase) new COSArray());
    }

    public PDAcroForm(PDDocument pDDocument, COSDictionary cOSDictionary) {
        this.document = pDDocument;
        this.dictionary = cOSDictionary;
    }

    public PDDocument getDocument() {
        return this.document;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public void importFDF(FDFDocument fDFDocument) throws IOException {
        List<FDFField> fields = fDFDocument.getCatalog().getFDF().getFields();
        if (fields != null) {
            for (FDFField fDFField : fields) {
                PDField field = getField(fDFField.getPartialFieldName());
                if (field != null) {
                    field.importFDF(fDFField);
                }
            }
        }
    }

    public FDFDocument exportFDF() throws IOException {
        FDFDocument fDFDocument = new FDFDocument();
        FDFCatalog catalog = fDFDocument.getCatalog();
        FDFDictionary fDFDictionary = new FDFDictionary();
        catalog.setFDF(fDFDictionary);
        List<PDField> fields = getFields();
        ArrayList arrayList = new ArrayList(fields.size());
        for (PDField pDField : fields) {
            arrayList.add(pDField.exportFDF());
        }
        fDFDictionary.setID(this.document.getDocument().getDocumentID());
        if (!arrayList.isEmpty()) {
            fDFDictionary.setFields(arrayList);
        }
        return fDFDocument;
    }

    public void flatten() throws IOException {
        if (xfaIsDynamic()) {
            Log.w("PdfBox-Android", "Flatten for a dynamix XFA form is not supported");
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<PDField> it = getFieldTree().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        flatten(arrayList, false);
    }

    public void flatten(List<PDField> list, boolean z) throws IOException {
        if (list.isEmpty()) {
            return;
        }
        if (!z && getNeedAppearances()) {
            Log.w("PdfBox-Android", "acroForm.getNeedAppearances() returns true, visual field appearances may not have been set");
            Log.w("PdfBox-Android", "call acroForm.refreshAppearances() or use the flatten() method with refreshAppearances parameter");
        }
        if (xfaIsDynamic()) {
            Log.w("PdfBox-Android", "Flatten for a dynamix XFA form is not supported");
            return;
        }
        if (z) {
            refreshAppearances(list);
        }
        PDPageTree pages = this.document.getPages();
        Map<COSDictionary, Set<COSDictionary>> buildPagesWidgetsMap = buildPagesWidgetsMap(list, pages);
        Iterator<PDPage> it = pages.iterator();
        while (it.hasNext()) {
            PDPage next = it.next();
            Set<COSDictionary> set = buildPagesWidgetsMap.get(next.getCOSObject());
            ArrayList arrayList = new ArrayList();
            boolean z2 = false;
            for (PDAnnotation pDAnnotation : next.getAnnotations()) {
                if (set == null || !set.contains(pDAnnotation.getCOSObject())) {
                    arrayList.add(pDAnnotation);
                } else if (isVisibleAnnotation(pDAnnotation)) {
                    PDPageContentStream pDPageContentStream = new PDPageContentStream(this.document, next, PDPageContentStream.AppendMode.APPEND, true, !z2);
                    try {
                        PDAppearanceStream normalAppearanceStream = pDAnnotation.getNormalAppearanceStream();
                        PDFormXObject pDFormXObject = new PDFormXObject(normalAppearanceStream.getCOSObject());
                        pDPageContentStream.saveGraphicsState();
                        pDPageContentStream.transform(resolveTransformationMatrix(pDAnnotation, normalAppearanceStream));
                        pDPageContentStream.drawForm(pDFormXObject);
                        pDPageContentStream.restoreGraphicsState();
                        pDPageContentStream.close();
                        z2 = true;
                    } catch (Throwable th) {
                        pDPageContentStream.close();
                        throw th;
                    }
                } else {
                    continue;
                }
            }
            next.setAnnotations(arrayList);
        }
        removeFields(list);
        this.dictionary.removeItem(COSName.XFA);
        if (this.document.getSignatureDictionaries().isEmpty()) {
            getCOSObject().removeItem(COSName.SIG_FLAGS);
        }
    }

    private boolean isVisibleAnnotation(PDAnnotation pDAnnotation) {
        PDAppearanceStream normalAppearanceStream;
        PDRectangle bBox;
        return (pDAnnotation.isInvisible() || pDAnnotation.isHidden() || (normalAppearanceStream = pDAnnotation.getNormalAppearanceStream()) == null || (bBox = normalAppearanceStream.getBBox()) == null || bBox.getWidth() <= 0.0f || bBox.getHeight() <= 0.0f) ? false : true;
    }

    public void refreshAppearances() throws IOException {
        Iterator<PDField> it = getFieldTree().iterator();
        while (it.hasNext()) {
            PDField next = it.next();
            if (next instanceof PDTerminalField) {
                ((PDTerminalField) next).constructAppearances();
            }
        }
    }

    public void refreshAppearances(List<PDField> list) throws IOException {
        for (PDField pDField : list) {
            if (pDField instanceof PDTerminalField) {
                ((PDTerminalField) pDField).constructAppearances();
            }
        }
    }

    public List<PDField> getFields() {
        PDField fromDictionary;
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.FIELDS);
        if (cOSArray == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if ((object instanceof COSDictionary) && (fromDictionary = PDField.fromDictionary(this, (COSDictionary) object, null)) != null) {
                arrayList.add(fromDictionary);
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setFields(List<PDField> list) {
        this.dictionary.setItem(COSName.FIELDS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public Iterator<PDField> getFieldIterator() {
        return new PDFieldTree(this).iterator();
    }

    public PDFieldTree getFieldTree() {
        return new PDFieldTree(this);
    }

    public void setCacheFields(boolean z) {
        if (z) {
            this.fieldCache = new HashMap();
            Iterator<PDField> it = getFieldTree().iterator();
            while (it.hasNext()) {
                PDField next = it.next();
                this.fieldCache.put(next.getFullyQualifiedName(), next);
            }
            return;
        }
        this.fieldCache = null;
    }

    public boolean isCachingFields() {
        return this.fieldCache != null;
    }

    public PDField getField(String str) {
        Map<String, PDField> map = this.fieldCache;
        if (map != null) {
            return map.get(str);
        }
        Iterator<PDField> it = getFieldTree().iterator();
        while (it.hasNext()) {
            PDField next = it.next();
            if (next.getFullyQualifiedName().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public String getDefaultAppearance() {
        return this.dictionary.getString(COSName.DA, "");
    }

    public void setDefaultAppearance(String str) {
        this.dictionary.setString(COSName.DA, str);
    }

    public boolean getNeedAppearances() {
        return this.dictionary.getBoolean(COSName.NEED_APPEARANCES, false);
    }

    public void setNeedAppearances(Boolean bool) {
        this.dictionary.setBoolean(COSName.NEED_APPEARANCES, bool.booleanValue());
    }

    public PDResources getDefaultResources() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.DR);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDResources((COSDictionary) dictionaryObject, this.document.getResourceCache());
        }
        return null;
    }

    public void setDefaultResources(PDResources pDResources) {
        this.dictionary.setItem(COSName.DR, pDResources);
    }

    public boolean hasXFA() {
        return this.dictionary.containsKey(COSName.XFA);
    }

    public boolean xfaIsDynamic() {
        return hasXFA() && getFields().isEmpty();
    }

    public PDXFAResource getXFA() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.XFA);
        if (dictionaryObject != null) {
            return new PDXFAResource(dictionaryObject);
        }
        return null;
    }

    public void setXFA(PDXFAResource pDXFAResource) {
        this.dictionary.setItem(COSName.XFA, pDXFAResource);
    }

    public int getQ() {
        COSNumber cOSNumber = (COSNumber) this.dictionary.getDictionaryObject(COSName.Q);
        if (cOSNumber != null) {
            return cOSNumber.intValue();
        }
        return 0;
    }

    public void setQ(int i) {
        this.dictionary.setInt(COSName.Q, i);
    }

    public boolean isSignaturesExist() {
        return this.dictionary.getFlag(COSName.SIG_FLAGS, 1);
    }

    public void setSignaturesExist(boolean z) {
        this.dictionary.setFlag(COSName.SIG_FLAGS, 1, z);
    }

    public boolean isAppendOnly() {
        return this.dictionary.getFlag(COSName.SIG_FLAGS, 2);
    }

    public void setAppendOnly(boolean z) {
        this.dictionary.setFlag(COSName.SIG_FLAGS, 2, z);
    }

    public ScriptingHandler getScriptingHandler() {
        return this.scriptingHandler;
    }

    public void setScriptingHandler(ScriptingHandler scriptingHandler) {
        this.scriptingHandler = scriptingHandler;
    }

    private Matrix resolveTransformationMatrix(PDAnnotation pDAnnotation, PDAppearanceStream pDAppearanceStream) {
        RectF transformedAppearanceBBox = getTransformedAppearanceBBox(pDAppearanceStream);
        PDRectangle rectangle = pDAnnotation.getRectangle();
        Matrix matrix = new Matrix();
        matrix.translate(rectangle.getLowerLeftX() - transformedAppearanceBBox.left, rectangle.getLowerLeftY() - transformedAppearanceBBox.top);
        matrix.scale(rectangle.getWidth() / transformedAppearanceBBox.width(), rectangle.getHeight() / transformedAppearanceBBox.height());
        return matrix;
    }

    private RectF getTransformedAppearanceBBox(PDAppearanceStream pDAppearanceStream) {
        Path transform = pDAppearanceStream.getBBox().transform(pDAppearanceStream.getMatrix());
        RectF rectF = new RectF();
        transform.computeBounds(rectF, true);
        return rectF;
    }

    private Map<COSDictionary, Set<COSDictionary>> buildPagesWidgetsMap(List<PDField> list, PDPageTree pDPageTree) throws IOException {
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (PDField pDField : list) {
            for (PDAnnotationWidget pDAnnotationWidget : pDField.getWidgets()) {
                PDPage page = pDAnnotationWidget.getPage();
                if (page != null) {
                    fillPagesAnnotationMap(hashMap, page, pDAnnotationWidget);
                } else {
                    z = true;
                }
            }
        }
        if (z) {
            Log.w("PdfBox-Android", "There has been a widget with a missing page reference, will check all page annotations");
            Iterator<PDPage> it = pDPageTree.iterator();
            while (it.hasNext()) {
                PDPage next = it.next();
                for (PDAnnotation pDAnnotation : next.getAnnotations()) {
                    if (pDAnnotation instanceof PDAnnotationWidget) {
                        fillPagesAnnotationMap(hashMap, next, (PDAnnotationWidget) pDAnnotation);
                    }
                }
            }
            return hashMap;
        }
        return hashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void fillPagesAnnotationMap(Map<COSDictionary, Set<COSDictionary>> map, PDPage pDPage, PDAnnotationWidget pDAnnotationWidget) {
        Set<COSDictionary> set = map.get(pDPage.getCOSObject());
        if (set == null) {
            HashSet hashSet = new HashSet();
            hashSet.add(pDAnnotationWidget.getCOSObject());
            map.put(pDPage.getCOSObject(), hashSet);
            return;
        }
        set.add(pDAnnotationWidget.getCOSObject());
    }

    private void removeFields(List<PDField> list) {
        COSBase dictionaryObject;
        for (PDField pDField : list) {
            if (pDField.getParent() == null) {
                dictionaryObject = this.dictionary.getDictionaryObject(COSName.FIELDS);
            } else {
                dictionaryObject = pDField.getParent().getCOSObject().getDictionaryObject(COSName.KIDS);
            }
            ((COSArray) dictionaryObject).removeObject(pDField.getCOSObject());
        }
    }
}
