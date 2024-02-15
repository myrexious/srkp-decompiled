package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFField;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import java.io.IOException;
import java.util.List;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
public abstract class PDField implements COSObjectable {
    private static final int FLAG_NO_EXPORT = 4;
    private static final int FLAG_READ_ONLY = 1;
    private static final int FLAG_REQUIRED = 2;
    private final PDAcroForm acroForm;
    private final COSDictionary dictionary;
    private final PDNonTerminalField parent;

    public abstract FDFField exportFDF() throws IOException;

    public abstract int getFieldFlags();

    public abstract String getFieldType();

    public abstract String getValueAsString();

    public abstract List<PDAnnotationWidget> getWidgets();

    public abstract void setValue(String str) throws IOException;

    public PDField(PDAcroForm pDAcroForm) {
        this(pDAcroForm, new COSDictionary(), null);
    }

    public PDField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        this.acroForm = pDAcroForm;
        this.dictionary = cOSDictionary;
        this.parent = pDNonTerminalField;
    }

    public static PDField fromDictionary(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        return PDFieldFactory.createField(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public COSBase getInheritableAttribute(COSName cOSName) {
        if (this.dictionary.containsKey(cOSName)) {
            return this.dictionary.getDictionaryObject(cOSName);
        }
        PDNonTerminalField pDNonTerminalField = this.parent;
        if (pDNonTerminalField != null) {
            return pDNonTerminalField.getInheritableAttribute(cOSName);
        }
        return this.acroForm.getCOSObject().getDictionaryObject(cOSName);
    }

    public void setReadOnly(boolean z) {
        this.dictionary.setFlag(COSName.FF, 1, z);
    }

    public boolean isReadOnly() {
        return this.dictionary.getFlag(COSName.FF, 1);
    }

    public void setRequired(boolean z) {
        this.dictionary.setFlag(COSName.FF, 2, z);
    }

    public boolean isRequired() {
        return this.dictionary.getFlag(COSName.FF, 2);
    }

    public void setNoExport(boolean z) {
        this.dictionary.setFlag(COSName.FF, 4, z);
    }

    public boolean isNoExport() {
        return this.dictionary.getFlag(COSName.FF, 4);
    }

    public void setFieldFlags(int i) {
        this.dictionary.setInt(COSName.FF, i);
    }

    public PDFormFieldAdditionalActions getActions() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.AA);
        if (cOSDictionary != null) {
            return new PDFormFieldAdditionalActions(cOSDictionary);
        }
        return null;
    }

    public void importFDF(FDFField fDFField) throws IOException {
        COSBase cOSValue = fDFField.getCOSValue();
        if (cOSValue != null && (this instanceof PDTerminalField)) {
            PDTerminalField pDTerminalField = (PDTerminalField) this;
            if (cOSValue instanceof COSName) {
                pDTerminalField.setValue(((COSName) cOSValue).getName());
            } else if (cOSValue instanceof COSString) {
                pDTerminalField.setValue(((COSString) cOSValue).getString());
            } else if (cOSValue instanceof COSStream) {
                pDTerminalField.setValue(((COSStream) cOSValue).toTextString());
            } else if ((cOSValue instanceof COSArray) && (this instanceof PDChoice)) {
                ((PDChoice) this).setValue(COSArrayList.convertCOSStringCOSArrayToList((COSArray) cOSValue));
            } else {
                throw new IOException("Error:Unknown type for field import" + cOSValue);
            }
        } else if (cOSValue != null) {
            this.dictionary.setItem(COSName.V, cOSValue);
        }
        Integer fieldFlags = fDFField.getFieldFlags();
        if (fieldFlags != null) {
            setFieldFlags(fieldFlags.intValue());
            return;
        }
        Integer setFieldFlags = fDFField.getSetFieldFlags();
        int fieldFlags2 = getFieldFlags();
        if (setFieldFlags != null) {
            fieldFlags2 |= setFieldFlags.intValue();
            setFieldFlags(fieldFlags2);
        }
        Integer clearFieldFlags = fDFField.getClearFieldFlags();
        if (clearFieldFlags != null) {
            setFieldFlags((~clearFieldFlags.intValue()) & fieldFlags2);
        }
    }

    public PDNonTerminalField getParent() {
        return this.parent;
    }

    PDField findKid(String[] strArr, int i) {
        int i2;
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.KIDS);
        PDField pDField = null;
        if (cOSArray != null) {
            for (int i3 = 0; pDField == null && i3 < cOSArray.size(); i3++) {
                COSDictionary cOSDictionary = (COSDictionary) cOSArray.getObject(i3);
                if (strArr[i].equals(cOSDictionary.getString(COSName.T)) && (pDField = fromDictionary(this.acroForm, cOSDictionary, (PDNonTerminalField) this)) != null && strArr.length > (i2 = i + 1)) {
                    pDField = pDField.findKid(strArr, i2);
                }
            }
        }
        return pDField;
    }

    public PDAcroForm getAcroForm() {
        return this.acroForm;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public String getPartialName() {
        return this.dictionary.getString(COSName.T);
    }

    public void setPartialName(String str) {
        if (str.contains(".")) {
            throw new IllegalArgumentException("A field partial name shall not contain a period character: " + str);
        }
        this.dictionary.setString(COSName.T, str);
    }

    public String getFullyQualifiedName() {
        String partialName = getPartialName();
        PDNonTerminalField pDNonTerminalField = this.parent;
        String fullyQualifiedName = pDNonTerminalField != null ? pDNonTerminalField.getFullyQualifiedName() : null;
        return fullyQualifiedName != null ? partialName != null ? fullyQualifiedName + "." + partialName : fullyQualifiedName : partialName;
    }

    public String getAlternateFieldName() {
        return this.dictionary.getString(COSName.TU);
    }

    public void setAlternateFieldName(String str) {
        this.dictionary.setString(COSName.TU, str);
    }

    public String getMappingName() {
        return this.dictionary.getString(COSName.TM);
    }

    public void setMappingName(String str) {
        this.dictionary.setString(COSName.TM, str);
    }

    public String toString() {
        return getFullyQualifiedName() + "{type: " + getClass().getSimpleName() + " value: " + getInheritableAttribute(COSName.V) + StringSubstitutor.DEFAULT_VAR_END;
    }
}
