package com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

/* loaded from: classes3.dex */
public final class PDDocumentOutline extends PDOutlineNode {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode
    public void closeNode() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode
    public boolean isNodeOpen() {
        return true;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode
    public void openNode() {
    }

    public PDDocumentOutline() {
        getCOSObject().setName(COSName.TYPE, COSName.OUTLINES.getName());
    }

    public PDDocumentOutline(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        getCOSObject().setName(COSName.TYPE, COSName.OUTLINES.getName());
    }
}
