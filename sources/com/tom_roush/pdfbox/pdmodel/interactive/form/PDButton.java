package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class PDButton extends PDTerminalField {
    static final int FLAG_PUSHBUTTON = 65536;
    static final int FLAG_RADIO = 32768;
    static final int FLAG_RADIOS_IN_UNISON = 33554432;

    public PDButton(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        getCOSObject().setItem(COSName.FT, (COSBase) COSName.BTN);
    }

    public PDButton(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public boolean isPushButton() {
        return getCOSObject().getFlag(COSName.FF, 65536);
    }

    @Deprecated
    public void setPushButton(boolean z) {
        getCOSObject().setFlag(COSName.FF, 65536, z);
        if (z) {
            setRadioButton(false);
        }
    }

    public boolean isRadioButton() {
        return getCOSObject().getFlag(COSName.FF, 32768);
    }

    @Deprecated
    public void setRadioButton(boolean z) {
        getCOSObject().setFlag(COSName.FF, 32768, z);
        if (z) {
            setPushButton(false);
        }
    }

    public String getValue() {
        COSBase inheritableAttribute = getInheritableAttribute(COSName.V);
        if (inheritableAttribute instanceof COSName) {
            String name = ((COSName) inheritableAttribute).getName();
            List<String> exportValues = getExportValues();
            if (!exportValues.isEmpty()) {
                try {
                    int parseInt = Integer.parseInt(name, 10);
                    if (parseInt >= 0 && parseInt < exportValues.size()) {
                        return exportValues.get(parseInt);
                    }
                } catch (NumberFormatException unused) {
                }
            }
            return name;
        }
        return "Off";
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public void setValue(String str) throws IOException {
        checkValue(str);
        if (getExportValues().size() > 0) {
            updateByOption(str);
        } else {
            updateByValue(str);
        }
        applyChange();
    }

    public void setValue(int i) throws IOException {
        if (getExportValues().isEmpty() || i < 0 || i >= getExportValues().size()) {
            throw new IllegalArgumentException("index '" + i + "' is not a valid index for the field " + getFullyQualifiedName() + ", valid indices are from 0 to " + (getExportValues().size() - 1));
        }
        updateByValue(String.valueOf(i));
        applyChange();
    }

    public String getDefaultValue() {
        COSBase inheritableAttribute = getInheritableAttribute(COSName.DV);
        return inheritableAttribute instanceof COSName ? ((COSName) inheritableAttribute).getName() : "";
    }

    public void setDefaultValue(String str) {
        checkValue(str);
        getCOSObject().setName(COSName.DV, str);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public String getValueAsString() {
        return getValue();
    }

    public List<String> getExportValues() {
        COSBase inheritableAttribute = getInheritableAttribute(COSName.OPT);
        if (inheritableAttribute instanceof COSString) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(((COSString) inheritableAttribute).getString());
            return arrayList;
        } else if (inheritableAttribute instanceof COSArray) {
            return COSArrayList.convertCOSStringCOSArrayToList((COSArray) inheritableAttribute);
        } else {
            return Collections.emptyList();
        }
    }

    public void setExportValues(List<String> list) {
        if (list != null && !list.isEmpty()) {
            getCOSObject().setItem(COSName.OPT, (COSBase) COSArrayList.convertStringListToCOSStringCOSArray(list));
            return;
        }
        getCOSObject().removeItem(COSName.OPT);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDTerminalField
    public void constructAppearances() throws IOException {
        List<String> exportValues = getExportValues();
        if (exportValues.size() > 0) {
            try {
                int parseInt = Integer.parseInt(getValue());
                if (parseInt < exportValues.size()) {
                    updateByOption(exportValues.get(parseInt));
                    return;
                }
                return;
            } catch (NumberFormatException unused) {
                return;
            }
        }
        updateByValue(getValue());
    }

    public Set<String> getOnValues() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (getExportValues().size() > 0) {
            linkedHashSet.addAll(getExportValues());
            return linkedHashSet;
        }
        for (PDAnnotationWidget pDAnnotationWidget : getWidgets()) {
            linkedHashSet.add(getOnValueForWidget(pDAnnotationWidget));
        }
        return linkedHashSet;
    }

    private String getOnValue(int i) {
        List<PDAnnotationWidget> widgets = getWidgets();
        return i < widgets.size() ? getOnValueForWidget(widgets.get(i)) : "";
    }

    private String getOnValueForWidget(PDAnnotationWidget pDAnnotationWidget) {
        PDAppearanceEntry normalAppearance;
        PDAppearanceDictionary appearance = pDAnnotationWidget.getAppearance();
        if (appearance == null || (normalAppearance = appearance.getNormalAppearance()) == null) {
            return "";
        }
        for (COSName cOSName : normalAppearance.getSubDictionary().keySet()) {
            if (COSName.Off.compareTo(cOSName) != 0) {
                return cOSName.getName();
            }
        }
        return "";
    }

    void checkValue(String str) {
        Set<String> onValues = getOnValues();
        if (COSName.Off.getName().compareTo(str) != 0 && !onValues.contains(str)) {
            throw new IllegalArgumentException("value '" + str + "' is not a valid option for the field " + getFullyQualifiedName() + ", valid values are: " + onValues + " and " + COSName.Off.getName());
        }
    }

    private void updateByValue(String str) throws IOException {
        getCOSObject().setName(COSName.V, str);
        for (PDAnnotationWidget pDAnnotationWidget : getWidgets()) {
            if (pDAnnotationWidget.getAppearance() != null) {
                if (((COSDictionary) pDAnnotationWidget.getAppearance().getNormalAppearance().getCOSObject()).containsKey(str)) {
                    pDAnnotationWidget.setAppearanceState(str);
                } else {
                    pDAnnotationWidget.setAppearanceState(COSName.Off.getName());
                }
            }
        }
    }

    private void updateByOption(String str) throws IOException {
        List<PDAnnotationWidget> widgets = getWidgets();
        List<String> exportValues = getExportValues();
        if (widgets.size() != exportValues.size()) {
            throw new IllegalArgumentException("The number of options doesn't match the number of widgets");
        }
        if (str.equals(COSName.Off.getName())) {
            updateByValue(str);
            return;
        }
        int indexOf = exportValues.indexOf(str);
        if (indexOf != -1) {
            updateByValue(getOnValue(indexOf));
        }
    }
}
