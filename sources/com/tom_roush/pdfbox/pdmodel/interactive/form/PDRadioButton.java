package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public final class PDRadioButton extends PDButton {
    private static final int FLAG_NO_TOGGLE_TO_OFF = 16384;

    public PDRadioButton(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        getCOSObject().setFlag(COSName.FF, 32768, true);
    }

    public PDRadioButton(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public void setRadiosInUnison(boolean z) {
        getCOSObject().setFlag(COSName.FF, 33554432, z);
    }

    public boolean isRadiosInUnison() {
        return getCOSObject().getFlag(COSName.FF, 33554432);
    }

    public int getSelectedIndex() {
        int i = 0;
        for (PDAnnotationWidget pDAnnotationWidget : getWidgets()) {
            if (!COSName.Off.equals(pDAnnotationWidget.getAppearanceState())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public List<String> getSelectedExportValues() throws IOException {
        List<String> exportValues = getExportValues();
        ArrayList arrayList = new ArrayList();
        if (exportValues.isEmpty()) {
            arrayList.add(getValue());
            return arrayList;
        }
        String value = getValue();
        int i = 0;
        for (String str : getOnValues()) {
            if (str.compareTo(value) == 0) {
                arrayList.add(exportValues.get(i));
            }
            i++;
        }
        return arrayList;
    }
}
