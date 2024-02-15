package com.tom_roush.pdfbox.pdmodel;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDDestinationNameTreeNode extends PDNameTreeNode<PDPageDestination> {
    public PDDestinationNameTreeNode() {
    }

    public PDDestinationNameTreeNode(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode
    public PDPageDestination convertCOSToPD(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSDictionary) {
            cOSBase = ((COSDictionary) cOSBase).getDictionaryObject(COSName.D);
        }
        return (PDPageDestination) PDDestination.create(cOSBase);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode
    protected PDNameTreeNode<PDPageDestination> createChildNode(COSDictionary cOSDictionary) {
        return new PDDestinationNameTreeNode(cOSDictionary);
    }
}
