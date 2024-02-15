package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.interactive.form.FieldUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class PDChoice extends PDVariableText {
    static final int FLAG_COMBO = 131072;
    private static final int FLAG_COMMIT_ON_SEL_CHANGE = 67108864;
    private static final int FLAG_DO_NOT_SPELL_CHECK = 4194304;
    private static final int FLAG_MULTI_SELECT = 2097152;
    private static final int FLAG_SORT = 524288;

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDTerminalField
    public abstract void constructAppearances() throws IOException;

    public PDChoice(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        getCOSObject().setItem(COSName.FT, (COSBase) COSName.CH);
    }

    public PDChoice(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public List<String> getOptions() {
        return FieldUtils.getPairableItems(getCOSObject().getDictionaryObject(COSName.OPT), 0);
    }

    public void setOptions(List<String> list) {
        if (list != null && !list.isEmpty()) {
            if (isSort()) {
                Collections.sort(list);
            }
            getCOSObject().setItem(COSName.OPT, (COSBase) COSArrayList.convertStringListToCOSStringCOSArray(list));
            return;
        }
        getCOSObject().removeItem(COSName.OPT);
    }

    public void setOptions(List<String> list, List<String> list2) {
        if (list != null && list2 != null && !list.isEmpty() && !list2.isEmpty()) {
            if (list.size() != list2.size()) {
                throw new IllegalArgumentException("The number of entries for exportValue and displayValue shall be the same.");
            }
            List<FieldUtils.KeyValue> keyValueList = FieldUtils.toKeyValueList(list, list2);
            if (isSort()) {
                FieldUtils.sortByValue(keyValueList);
            }
            COSArray cOSArray = new COSArray();
            for (int i = 0; i < list.size(); i++) {
                COSArray cOSArray2 = new COSArray();
                cOSArray2.add((COSBase) new COSString(keyValueList.get(i).getKey()));
                cOSArray2.add((COSBase) new COSString(keyValueList.get(i).getValue()));
                cOSArray.add((COSBase) cOSArray2);
            }
            getCOSObject().setItem(COSName.OPT, (COSBase) cOSArray);
            return;
        }
        getCOSObject().removeItem(COSName.OPT);
    }

    public List<String> getOptionsDisplayValues() {
        return FieldUtils.getPairableItems(getCOSObject().getDictionaryObject(COSName.OPT), 1);
    }

    public List<String> getOptionsExportValues() {
        return getOptions();
    }

    public List<Integer> getSelectedOptionsIndex() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.I);
        if (dictionaryObject != null) {
            return COSArrayList.convertIntegerCOSArrayToList((COSArray) dictionaryObject);
        }
        return Collections.emptyList();
    }

    public void setSelectedOptionsIndex(List<Integer> list) {
        if (list != null && !list.isEmpty()) {
            if (!isMultiSelect()) {
                throw new IllegalArgumentException("Setting the indices is not allowed for choice fields not allowing multiple selections.");
            }
            getCOSObject().setItem(COSName.I, (COSBase) COSArrayList.converterToCOSArray(list));
            return;
        }
        getCOSObject().removeItem(COSName.I);
    }

    public boolean isSort() {
        return getCOSObject().getFlag(COSName.FF, 524288);
    }

    public void setSort(boolean z) {
        getCOSObject().setFlag(COSName.FF, 524288, z);
    }

    public boolean isMultiSelect() {
        return getCOSObject().getFlag(COSName.FF, 2097152);
    }

    public void setMultiSelect(boolean z) {
        getCOSObject().setFlag(COSName.FF, 2097152, z);
    }

    public boolean isDoNotSpellCheck() {
        return getCOSObject().getFlag(COSName.FF, 4194304);
    }

    public void setDoNotSpellCheck(boolean z) {
        getCOSObject().setFlag(COSName.FF, 4194304, z);
    }

    public boolean isCommitOnSelChange() {
        return getCOSObject().getFlag(COSName.FF, FLAG_COMMIT_ON_SEL_CHANGE);
    }

    public void setCommitOnSelChange(boolean z) {
        getCOSObject().setFlag(COSName.FF, FLAG_COMMIT_ON_SEL_CHANGE, z);
    }

    public boolean isCombo() {
        return getCOSObject().getFlag(COSName.FF, 131072);
    }

    public void setCombo(boolean z) {
        getCOSObject().setFlag(COSName.FF, 131072, z);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public void setValue(String str) throws IOException {
        getCOSObject().setString(COSName.V, str);
        setSelectedOptionsIndex(null);
        applyChange();
    }

    public void setDefaultValue(String str) throws IOException {
        getCOSObject().setString(COSName.DV, str);
    }

    public void setValue(List<String> list) throws IOException {
        if (list != null && !list.isEmpty()) {
            if (!isMultiSelect()) {
                throw new IllegalArgumentException("The list box does not allow multiple selections.");
            }
            if (!getOptions().containsAll(list)) {
                throw new IllegalArgumentException("The values are not contained in the selectable options.");
            }
            getCOSObject().setItem(COSName.V, (COSBase) COSArrayList.convertStringListToCOSStringCOSArray(list));
            updateSelectedOptionsIndex(list);
        } else {
            getCOSObject().removeItem(COSName.V);
            getCOSObject().removeItem(COSName.I);
        }
        applyChange();
    }

    public List<String> getValue() {
        return getValueFor(COSName.V);
    }

    public List<String> getDefaultValue() {
        return getValueFor(COSName.DV);
    }

    private List<String> getValueFor(COSName cOSName) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(cOSName);
        if (dictionaryObject instanceof COSString) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(((COSString) dictionaryObject).getString());
            return arrayList;
        } else if (dictionaryObject instanceof COSArray) {
            return COSArrayList.convertCOSStringCOSArrayToList((COSArray) dictionaryObject);
        } else {
            return Collections.emptyList();
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public String getValueAsString() {
        return Arrays.toString(getValue().toArray());
    }

    private void updateSelectedOptionsIndex(List<String> list) {
        List<String> options = getOptions();
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            arrayList.add(Integer.valueOf(options.indexOf(str)));
        }
        Collections.sort(arrayList);
        setSelectedOptionsIndex(arrayList);
    }
}
