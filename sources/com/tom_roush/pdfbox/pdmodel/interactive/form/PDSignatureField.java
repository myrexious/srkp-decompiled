package com.tom_roush.pdfbox.pdmodel.interactive.form;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSeedValue;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class PDSignatureField extends PDTerminalField {
    public PDSignatureField(PDAcroForm pDAcroForm) throws IOException {
        super(pDAcroForm);
        getCOSObject().setItem(COSName.FT, (COSBase) COSName.SIG);
        PDAnnotationWidget pDAnnotationWidget = getWidgets().get(0);
        pDAnnotationWidget.setLocked(true);
        pDAnnotationWidget.setPrinted(true);
        setPartialName(generatePartialName());
    }

    public PDSignatureField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    private String generatePartialName() {
        HashSet hashSet = new HashSet();
        Iterator<PDField> it = getAcroForm().getFieldTree().iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getPartialName());
        }
        int i = 1;
        while (hashSet.contains("Signature" + i)) {
            i++;
        }
        return "Signature" + i;
    }

    @Deprecated
    public void setSignature(PDSignature pDSignature) throws IOException {
        setValue(pDSignature);
    }

    public PDSignature getSignature() {
        return getValue();
    }

    public void setValue(PDSignature pDSignature) throws IOException {
        getCOSObject().setItem(COSName.V, pDSignature);
        applyChange();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public void setValue(String str) {
        throw new UnsupportedOperationException("Signature fields don't support setting the value as String - use setValue(PDSignature value) instead");
    }

    public void setDefaultValue(PDSignature pDSignature) throws IOException {
        getCOSObject().setItem(COSName.DV, pDSignature);
    }

    public PDSignature getValue() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.V);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDSignature((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public PDSignature getDefaultValue() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.DV);
        if (dictionaryObject == null) {
            return null;
        }
        return new PDSignature((COSDictionary) dictionaryObject);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public String getValueAsString() {
        PDSignature value = getValue();
        return value != null ? value.toString() : "";
    }

    public PDSeedValue getSeedValue() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.SV);
        if (cOSDictionary != null) {
            return new PDSeedValue(cOSDictionary);
        }
        return null;
    }

    public void setSeedValue(PDSeedValue pDSeedValue) {
        if (pDSeedValue != null) {
            getCOSObject().setItem(COSName.SV, pDSeedValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDTerminalField
    public void constructAppearances() throws IOException {
        PDAnnotationWidget pDAnnotationWidget = getWidgets().get(0);
        if (pDAnnotationWidget == null || pDAnnotationWidget.getRectangle() == null) {
            return;
        }
        if ((pDAnnotationWidget.getRectangle().getHeight() == 0.0f && pDAnnotationWidget.getRectangle().getWidth() == 0.0f) || pDAnnotationWidget.isNoView() || pDAnnotationWidget.isHidden()) {
            return;
        }
        Log.w("PdfBox-Android", "Appearance generation for signature fields not implemented here. You need to generate/update that manually, see the CreateVisibleSignature*.java files in the examples subproject of the source code download");
    }
}
