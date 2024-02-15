package com.tom_roush.pdfbox.pdmodel;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDEmbeddedFilesNameTreeNode extends PDNameTreeNode<PDComplexFileSpecification> {
    public PDEmbeddedFilesNameTreeNode() {
    }

    public PDEmbeddedFilesNameTreeNode(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode
    public PDComplexFileSpecification convertCOSToPD(COSBase cOSBase) throws IOException {
        return new PDComplexFileSpecification((COSDictionary) cOSBase);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode
    protected PDNameTreeNode<PDComplexFileSpecification> createChildNode(COSDictionary cOSDictionary) {
        return new PDEmbeddedFilesNameTreeNode(cOSDictionary);
    }
}
