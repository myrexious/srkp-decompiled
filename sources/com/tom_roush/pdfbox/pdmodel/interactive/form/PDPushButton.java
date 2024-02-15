package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class PDPushButton extends PDButton {
    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDButton, com.tom_roush.pdfbox.pdmodel.interactive.form.PDTerminalField
    public void constructAppearances() throws IOException {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDButton
    public String getDefaultValue() {
        return "";
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDButton
    public String getValue() {
        return "";
    }

    public PDPushButton(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        getCOSObject().setFlag(COSName.FF, 65536, true);
    }

    public PDPushButton(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDButton
    public List<String> getExportValues() {
        return Collections.emptyList();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDButton
    public void setExportValues(List<String> list) {
        if (list != null && !list.isEmpty()) {
            throw new IllegalArgumentException("A PDPushButton shall not use the Opt entry in the field dictionary");
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDButton, com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public String getValueAsString() {
        return getValue();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDButton
    public Set<String> getOnValues() {
        return Collections.emptySet();
    }
}
