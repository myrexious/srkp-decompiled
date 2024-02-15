package com.tom_roush.pdfbox.pdmodel.fixup.processor;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;

/* loaded from: classes3.dex */
public class AcroFormDefaultsProcessor extends AbstractProcessor {
    public AcroFormDefaultsProcessor(PDDocument pDDocument) {
        super(pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.fixup.processor.PDDocumentProcessor
    public void process() {
        PDAcroForm acroForm = this.document.getDocumentCatalog().getAcroForm(null);
        if (acroForm != null) {
            verifyOrCreateDefaults(acroForm);
        }
    }

    private void verifyOrCreateDefaults(PDAcroForm pDAcroForm) {
        if (pDAcroForm.getDefaultAppearance().length() == 0) {
            pDAcroForm.setDefaultAppearance("/Helv 0 Tf 0 g ");
            pDAcroForm.getCOSObject().setNeedToBeUpdated(true);
        }
        PDResources defaultResources = pDAcroForm.getDefaultResources();
        if (defaultResources == null) {
            defaultResources = new PDResources();
            pDAcroForm.setDefaultResources(defaultResources);
            pDAcroForm.getCOSObject().setNeedToBeUpdated(true);
        }
        COSDictionary cOSDictionary = defaultResources.getCOSObject().getCOSDictionary(COSName.FONT);
        if (cOSDictionary == null) {
            cOSDictionary = new COSDictionary();
            defaultResources.getCOSObject().setItem(COSName.FONT, (COSBase) cOSDictionary);
        }
        if (!cOSDictionary.containsKey(COSName.HELV)) {
            defaultResources.put(COSName.HELV, PDType1Font.HELVETICA);
            defaultResources.getCOSObject().setNeedToBeUpdated(true);
            cOSDictionary.setNeedToBeUpdated(true);
        }
        if (cOSDictionary.containsKey(COSName.ZA_DB)) {
            return;
        }
        defaultResources.put(COSName.ZA_DB, PDType1Font.ZAPF_DINGBATS);
        defaultResources.getCOSObject().setNeedToBeUpdated(true);
        cOSDictionary.setNeedToBeUpdated(true);
    }
}
