package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

/* loaded from: classes3.dex */
public final class PDFieldFactory {
    private static final String FIELD_TYPE_BUTTON = "Btn";
    private static final String FIELD_TYPE_CHOICE = "Ch";
    private static final String FIELD_TYPE_SIGNATURE = "Sig";
    private static final String FIELD_TYPE_TEXT = "Tx";

    private PDFieldFactory() {
    }

    public static PDField createField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        COSArray cOSArray;
        if (cOSDictionary.containsKey(COSName.KIDS) && (cOSArray = (COSArray) cOSDictionary.getDictionaryObject(COSName.KIDS)) != null && cOSArray.size() > 0) {
            for (int i = 0; i < cOSArray.size(); i++) {
                COSBase object = cOSArray.getObject(i);
                if ((object instanceof COSDictionary) && ((COSDictionary) object).getString(COSName.T) != null) {
                    return new PDNonTerminalField(pDAcroForm, cOSDictionary, pDNonTerminalField);
                }
            }
        }
        String findFieldType = findFieldType(cOSDictionary);
        if (FIELD_TYPE_CHOICE.equals(findFieldType)) {
            return createChoiceSubType(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        if (FIELD_TYPE_TEXT.equals(findFieldType)) {
            return new PDTextField(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        if (FIELD_TYPE_SIGNATURE.equals(findFieldType)) {
            return new PDSignatureField(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        if (FIELD_TYPE_BUTTON.equals(findFieldType)) {
            return createButtonSubType(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        return null;
    }

    private static PDField createChoiceSubType(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        if ((cOSDictionary.getInt(COSName.FF, 0) & 131072) != 0) {
            return new PDComboBox(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        return new PDListBox(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    private static PDField createButtonSubType(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        int i = cOSDictionary.getInt(COSName.FF, 0);
        if ((32768 & i) != 0) {
            return new PDRadioButton(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        if ((i & 65536) != 0) {
            return new PDPushButton(pDAcroForm, cOSDictionary, pDNonTerminalField);
        }
        return new PDCheckBox(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    private static String findFieldType(COSDictionary cOSDictionary) {
        String nameAsString = cOSDictionary.getNameAsString(COSName.FT);
        if (nameAsString == null) {
            COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.P);
            return dictionaryObject instanceof COSDictionary ? findFieldType((COSDictionary) dictionaryObject) : nameAsString;
        }
        return nameAsString;
    }
}
