package com.tom_roush.pdfbox.pdmodel.graphics.form;

import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.contentstream.PDContentStream;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.ResourceCache;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class PDFormXObject extends PDXObject implements PDContentStream {
    private final ResourceCache cache;
    private PDTransparencyGroupAttributes group;

    public PDFormXObject(PDStream pDStream) {
        super(pDStream, COSName.FORM);
        this.cache = null;
    }

    public PDFormXObject(COSStream cOSStream) {
        super(cOSStream, COSName.FORM);
        this.cache = null;
    }

    public PDFormXObject(COSStream cOSStream, ResourceCache resourceCache) {
        super(cOSStream, COSName.FORM);
        this.cache = resourceCache;
    }

    public PDFormXObject(PDDocument pDDocument) {
        super(pDDocument, COSName.FORM);
        this.cache = null;
    }

    public int getFormType() {
        return getCOSObject().getInt(COSName.FORMTYPE, 1);
    }

    public void setFormType(int i) {
        getCOSObject().setInt(COSName.FORMTYPE, i);
    }

    public PDTransparencyGroupAttributes getGroup() {
        COSDictionary cOSDictionary;
        if (this.group == null && (cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.GROUP)) != null) {
            this.group = new PDTransparencyGroupAttributes(cOSDictionary);
        }
        return this.group;
    }

    public PDStream getContentStream() {
        return new PDStream(getCOSObject());
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public InputStream getContents() throws IOException {
        return getCOSObject().createInputStream();
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public PDResources getResources() {
        COSDictionary cOSDictionary = getCOSObject().getCOSDictionary(COSName.RESOURCES);
        if (cOSDictionary != null) {
            return new PDResources(cOSDictionary, this.cache);
        }
        if (getCOSObject().containsKey(COSName.RESOURCES)) {
            return new PDResources();
        }
        return null;
    }

    public void setResources(PDResources pDResources) {
        getCOSObject().setItem(COSName.RESOURCES, pDResources);
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public PDRectangle getBBox() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.BBOX);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setBBox(PDRectangle pDRectangle) {
        if (pDRectangle == null) {
            getCOSObject().removeItem(COSName.BBOX);
        } else {
            getCOSObject().setItem(COSName.BBOX, (COSBase) pDRectangle.getCOSArray());
        }
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public Matrix getMatrix() {
        return Matrix.createMatrix(getCOSObject().getDictionaryObject(COSName.MATRIX));
    }

    public void setMatrix(AffineTransform affineTransform) {
        COSArray cOSArray = new COSArray();
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        for (int i = 0; i < 6; i++) {
            cOSArray.add((COSBase) new COSFloat((float) dArr[i]));
        }
        getCOSObject().setItem(COSName.MATRIX, (COSBase) cOSArray);
    }

    public int getStructParents() {
        return getCOSObject().getInt(COSName.STRUCT_PARENTS);
    }

    public void setStructParents(int i) {
        getCOSObject().setInt(COSName.STRUCT_PARENTS, i);
    }

    public PDPropertyList getOptionalContent() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.OC);
        if (dictionaryObject instanceof COSDictionary) {
            return PDPropertyList.create((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setOptionalContent(PDPropertyList pDPropertyList) {
        getCOSObject().setItem(COSName.OC, pDPropertyList);
    }
}
