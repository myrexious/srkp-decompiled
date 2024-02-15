package com.tom_roush.pdfbox.pdmodel.fixup.processor;

import android.util.Log;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import java.io.IOException;

/* loaded from: classes3.dex */
public class AcroFormGenerateAppearancesProcessor extends AbstractProcessor {
    public AcroFormGenerateAppearancesProcessor(PDDocument pDDocument) {
        super(pDDocument);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.fixup.processor.PDDocumentProcessor
    public void process() {
        PDAcroForm acroForm = this.document.getDocumentCatalog().getAcroForm(null);
        if (acroForm == null || !acroForm.getNeedAppearances()) {
            return;
        }
        try {
            Log.d("PdfBox-Android", "trying to generate appearance streams for fields as NeedAppearances is true()");
            acroForm.refreshAppearances();
            acroForm.setNeedAppearances(false);
        } catch (IOException e) {
            Log.d("PdfBox-Android", "couldn't generate appearance stream for some fields - check output");
            Log.d("PdfBox-Android", e.getMessage());
        } catch (IllegalArgumentException e2) {
            Log.d("PdfBox-Android", "couldn't generate appearance stream for some fields - check output");
            Log.d("PdfBox-Android", e2.getMessage());
        }
    }
}
