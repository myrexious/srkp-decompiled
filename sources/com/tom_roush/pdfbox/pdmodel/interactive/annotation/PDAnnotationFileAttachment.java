package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDAnnotationFileAttachment extends PDAnnotationMarkup {
    public static final String ATTACHMENT_NAME_GRAPH = "Graph";
    public static final String ATTACHMENT_NAME_PAPERCLIP = "Paperclip";
    public static final String ATTACHMENT_NAME_PUSH_PIN = "PushPin";
    public static final String ATTACHMENT_NAME_TAG = "Tag";
    public static final String SUB_TYPE = "FileAttachment";

    public PDAnnotationFileAttachment() {
        getCOSObject().setName(COSName.SUBTYPE, "FileAttachment");
    }

    public PDAnnotationFileAttachment(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(getCOSObject().getDictionaryObject("FS"));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        getCOSObject().setItem("FS", pDFileSpecification);
    }

    public String getAttachmentName() {
        return getCOSObject().getNameAsString(COSName.NAME, ATTACHMENT_NAME_PUSH_PIN);
    }

    @Deprecated
    public void setAttachementName(String str) {
        getCOSObject().setName(COSName.NAME, str);
    }

    public void setAttachmentName(String str) {
        getCOSObject().setName(COSName.NAME, str);
    }
}
