package com.tom_roush.pdfbox.pdmodel;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.PDContentStream;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDMetadata;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDPageAdditionalActions;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.AnnotationFilter;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.measurement.PDViewportDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.pagenavigation.PDThreadBead;
import com.tom_roush.pdfbox.pdmodel.interactive.pagenavigation.PDTransition;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class PDPage implements COSObjectable, PDContentStream {
    private PDRectangle mediaBox;
    private final COSDictionary page;
    private PDResources pageResources;
    private ResourceCache resourceCache;

    public PDPage() {
        this(PDRectangle.LETTER);
    }

    public PDPage(PDRectangle pDRectangle) {
        COSDictionary cOSDictionary = new COSDictionary();
        this.page = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.PAGE);
        cOSDictionary.setItem(COSName.MEDIA_BOX, pDRectangle);
    }

    public PDPage(COSDictionary cOSDictionary) {
        this.page = cOSDictionary;
    }

    public PDPage(COSDictionary cOSDictionary, ResourceCache resourceCache) {
        this.page = cOSDictionary;
        this.resourceCache = resourceCache;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.page;
    }

    public Iterator<PDStream> getContentStreams() {
        ArrayList arrayList = new ArrayList();
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.CONTENTS);
        if (dictionaryObject instanceof COSStream) {
            arrayList.add(new PDStream((COSStream) dictionaryObject));
        } else if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            for (int i = 0; i < cOSArray.size(); i++) {
                arrayList.add(new PDStream((COSStream) cOSArray.getObject(i)));
            }
        }
        return arrayList.iterator();
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public InputStream getContents() throws IOException {
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.CONTENTS);
        if (dictionaryObject instanceof COSStream) {
            return ((COSStream) dictionaryObject).createInputStream();
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            if (cOSArray.size() > 0) {
                byte[] bArr = {10};
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < cOSArray.size(); i++) {
                    COSBase object = cOSArray.getObject(i);
                    if (object instanceof COSStream) {
                        arrayList.add(((COSStream) object).createInputStream());
                        arrayList.add(new ByteArrayInputStream(bArr));
                    }
                }
                return new SequenceInputStream(Collections.enumeration(arrayList));
            }
        }
        return new ByteArrayInputStream(new byte[0]);
    }

    public boolean hasContents() {
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.CONTENTS);
        return dictionaryObject instanceof COSStream ? ((COSStream) dictionaryObject).size() > 0 : (dictionaryObject instanceof COSArray) && ((COSArray) dictionaryObject).size() > 0;
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public PDResources getResources() {
        if (this.pageResources == null) {
            COSBase inheritableAttribute = PDPageTree.getInheritableAttribute(this.page, COSName.RESOURCES);
            if (inheritableAttribute instanceof COSDictionary) {
                this.pageResources = new PDResources((COSDictionary) inheritableAttribute, this.resourceCache);
            }
        }
        return this.pageResources;
    }

    public void setResources(PDResources pDResources) {
        this.pageResources = pDResources;
        if (pDResources != null) {
            this.page.setItem(COSName.RESOURCES, pDResources);
        } else {
            this.page.removeItem(COSName.RESOURCES);
        }
    }

    public int getStructParents() {
        return this.page.getInt(COSName.STRUCT_PARENTS);
    }

    public void setStructParents(int i) {
        this.page.setInt(COSName.STRUCT_PARENTS, i);
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public PDRectangle getBBox() {
        return getCropBox();
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public Matrix getMatrix() {
        return new Matrix();
    }

    public PDRectangle getMediaBox() {
        if (this.mediaBox == null) {
            COSBase inheritableAttribute = PDPageTree.getInheritableAttribute(this.page, COSName.MEDIA_BOX);
            if (inheritableAttribute instanceof COSArray) {
                this.mediaBox = new PDRectangle((COSArray) inheritableAttribute);
            } else {
                Log.d("PdfBox-Android", "Can't find MediaBox, will use U.S. Letter");
                this.mediaBox = PDRectangle.LETTER;
            }
        }
        return this.mediaBox;
    }

    public void setMediaBox(PDRectangle pDRectangle) {
        this.mediaBox = pDRectangle;
        if (pDRectangle == null) {
            this.page.removeItem(COSName.MEDIA_BOX);
        } else {
            this.page.setItem(COSName.MEDIA_BOX, pDRectangle);
        }
    }

    public PDRectangle getCropBox() {
        COSBase inheritableAttribute = PDPageTree.getInheritableAttribute(this.page, COSName.CROP_BOX);
        if (inheritableAttribute instanceof COSArray) {
            return clipToMediaBox(new PDRectangle((COSArray) inheritableAttribute));
        }
        return getMediaBox();
    }

    public void setCropBox(PDRectangle pDRectangle) {
        if (pDRectangle == null) {
            this.page.removeItem(COSName.CROP_BOX);
        } else {
            this.page.setItem(COSName.CROP_BOX, (COSBase) pDRectangle.getCOSArray());
        }
    }

    public PDRectangle getBleedBox() {
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.BLEED_BOX);
        if (dictionaryObject instanceof COSArray) {
            return clipToMediaBox(new PDRectangle((COSArray) dictionaryObject));
        }
        return getCropBox();
    }

    public void setBleedBox(PDRectangle pDRectangle) {
        if (pDRectangle == null) {
            this.page.removeItem(COSName.BLEED_BOX);
        } else {
            this.page.setItem(COSName.BLEED_BOX, pDRectangle);
        }
    }

    public PDRectangle getTrimBox() {
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.TRIM_BOX);
        if (dictionaryObject instanceof COSArray) {
            return clipToMediaBox(new PDRectangle((COSArray) dictionaryObject));
        }
        return getCropBox();
    }

    public void setTrimBox(PDRectangle pDRectangle) {
        if (pDRectangle == null) {
            this.page.removeItem(COSName.TRIM_BOX);
        } else {
            this.page.setItem(COSName.TRIM_BOX, pDRectangle);
        }
    }

    public PDRectangle getArtBox() {
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.ART_BOX);
        if (dictionaryObject instanceof COSArray) {
            return clipToMediaBox(new PDRectangle((COSArray) dictionaryObject));
        }
        return getCropBox();
    }

    public void setArtBox(PDRectangle pDRectangle) {
        if (pDRectangle == null) {
            this.page.removeItem(COSName.ART_BOX);
        } else {
            this.page.setItem(COSName.ART_BOX, pDRectangle);
        }
    }

    private PDRectangle clipToMediaBox(PDRectangle pDRectangle) {
        PDRectangle mediaBox = getMediaBox();
        PDRectangle pDRectangle2 = new PDRectangle();
        pDRectangle2.setLowerLeftX(Math.max(mediaBox.getLowerLeftX(), pDRectangle.getLowerLeftX()));
        pDRectangle2.setLowerLeftY(Math.max(mediaBox.getLowerLeftY(), pDRectangle.getLowerLeftY()));
        pDRectangle2.setUpperRightX(Math.min(mediaBox.getUpperRightX(), pDRectangle.getUpperRightX()));
        pDRectangle2.setUpperRightY(Math.min(mediaBox.getUpperRightY(), pDRectangle.getUpperRightY()));
        return pDRectangle2;
    }

    public int getRotation() {
        COSBase inheritableAttribute = PDPageTree.getInheritableAttribute(this.page, COSName.ROTATE);
        if (inheritableAttribute instanceof COSNumber) {
            int intValue = ((COSNumber) inheritableAttribute).intValue();
            if (intValue % 90 == 0) {
                return ((intValue % 360) + 360) % 360;
            }
            return 0;
        }
        return 0;
    }

    public void setRotation(int i) {
        this.page.setInt(COSName.ROTATE, i);
    }

    public void setContents(PDStream pDStream) {
        this.page.setItem(COSName.CONTENTS, pDStream);
    }

    public void setContents(List<PDStream> list) {
        COSArray cOSArray = new COSArray();
        for (PDStream pDStream : list) {
            cOSArray.add(pDStream);
        }
        this.page.setItem(COSName.CONTENTS, (COSBase) cOSArray);
    }

    public List<PDThreadBead> getThreadBeads() {
        COSArray cOSArray = (COSArray) this.page.getDictionaryObject(COSName.B);
        if (cOSArray == null) {
            cOSArray = new COSArray();
        }
        ArrayList arrayList = new ArrayList(cOSArray.size());
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            arrayList.add(object instanceof COSDictionary ? new PDThreadBead((COSDictionary) object) : null);
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setThreadBeads(List<PDThreadBead> list) {
        this.page.setItem(COSName.B, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public PDMetadata getMetadata() {
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.METADATA);
        if (dictionaryObject instanceof COSStream) {
            return new PDMetadata((COSStream) dictionaryObject);
        }
        return null;
    }

    public void setMetadata(PDMetadata pDMetadata) {
        this.page.setItem(COSName.METADATA, pDMetadata);
    }

    public PDPageAdditionalActions getActions() {
        COSDictionary cOSDictionary;
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.AA);
        if (dictionaryObject instanceof COSDictionary) {
            cOSDictionary = (COSDictionary) dictionaryObject;
        } else {
            cOSDictionary = new COSDictionary();
            this.page.setItem(COSName.AA, (COSBase) cOSDictionary);
        }
        return new PDPageAdditionalActions(cOSDictionary);
    }

    public void setActions(PDPageAdditionalActions pDPageAdditionalActions) {
        this.page.setItem(COSName.AA, pDPageAdditionalActions);
    }

    public PDTransition getTransition() {
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.TRANS);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDTransition((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setTransition(PDTransition pDTransition) {
        this.page.setItem(COSName.TRANS, pDTransition);
    }

    public void setTransition(PDTransition pDTransition, float f) {
        this.page.setItem(COSName.TRANS, pDTransition);
        this.page.setItem(COSName.DUR, (COSBase) new COSFloat(f));
    }

    public List<PDAnnotation> getAnnotations() throws IOException {
        return getAnnotations(new AnnotationFilter() { // from class: com.tom_roush.pdfbox.pdmodel.PDPage.1
            @Override // com.tom_roush.pdfbox.pdmodel.interactive.annotation.AnnotationFilter
            public boolean accept(PDAnnotation pDAnnotation) {
                return true;
            }
        });
    }

    public List<PDAnnotation> getAnnotations(AnnotationFilter annotationFilter) throws IOException {
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.ANNOTS);
        if (!(dictionaryObject instanceof COSArray)) {
            return new COSArrayList(this.page, COSName.ANNOTS);
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object != null) {
                PDAnnotation createAnnotation = PDAnnotation.createAnnotation(object);
                if (annotationFilter.accept(createAnnotation)) {
                    arrayList.add(createAnnotation);
                }
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setAnnotations(List<PDAnnotation> list) {
        this.page.setItem(COSName.ANNOTS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public boolean equals(Object obj) {
        return (obj instanceof PDPage) && ((PDPage) obj).getCOSObject() == getCOSObject();
    }

    public int hashCode() {
        return this.page.hashCode();
    }

    public ResourceCache getResourceCache() {
        return this.resourceCache;
    }

    public List<PDViewportDictionary> getViewports() {
        COSBase dictionaryObject = this.page.getDictionaryObject(COSName.VP);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < cOSArray.size(); i++) {
                COSBase object = cOSArray.getObject(i);
                if (object instanceof COSDictionary) {
                    arrayList.add(new PDViewportDictionary((COSDictionary) object));
                } else {
                    Log.w("PdfBox-Android", "Array element " + object + " is skipped, must be a (viewport) dictionary");
                }
            }
            return arrayList;
        }
        return null;
    }

    public void setViewports(List<PDViewportDictionary> list) {
        if (list == null) {
            this.page.removeItem(COSName.VP);
            return;
        }
        COSArray cOSArray = new COSArray();
        for (PDViewportDictionary pDViewportDictionary : list) {
            cOSArray.add(pDViewportDictionary);
        }
        this.page.setItem(COSName.VP, (COSBase) cOSArray);
    }

    public float getUserUnit() {
        float f = this.page.getFloat(COSName.USER_UNIT, 1.0f);
        if (f > 0.0f) {
            return f;
        }
        return 1.0f;
    }

    public void setUserUnit(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("User unit must be positive");
        }
        this.page.setFloat(COSName.USER_UNIT, f);
    }
}
