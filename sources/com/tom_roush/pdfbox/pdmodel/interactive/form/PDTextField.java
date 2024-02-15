package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class PDTextField extends PDVariableText {
    private static final int FLAG_COMB = 16777216;
    private static final int FLAG_DO_NOT_SCROLL = 8388608;
    private static final int FLAG_DO_NOT_SPELL_CHECK = 4194304;
    private static final int FLAG_FILE_SELECT = 1048576;
    private static final int FLAG_MULTILINE = 4096;
    private static final int FLAG_PASSWORD = 8192;
    private static final int FLAG_RICH_TEXT = 33554432;

    public PDTextField(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        getCOSObject().setItem(COSName.FT, (COSBase) COSName.TX);
    }

    public PDTextField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public boolean isMultiline() {
        return getCOSObject().getFlag(COSName.FF, 4096);
    }

    public void setMultiline(boolean z) {
        getCOSObject().setFlag(COSName.FF, 4096, z);
    }

    public boolean isPassword() {
        return getCOSObject().getFlag(COSName.FF, 8192);
    }

    public void setPassword(boolean z) {
        getCOSObject().setFlag(COSName.FF, 8192, z);
    }

    public boolean isFileSelect() {
        return getCOSObject().getFlag(COSName.FF, 1048576);
    }

    public void setFileSelect(boolean z) {
        getCOSObject().setFlag(COSName.FF, 1048576, z);
    }

    public boolean doNotSpellCheck() {
        return getCOSObject().getFlag(COSName.FF, 4194304);
    }

    public void setDoNotSpellCheck(boolean z) {
        getCOSObject().setFlag(COSName.FF, 4194304, z);
    }

    public boolean doNotScroll() {
        return getCOSObject().getFlag(COSName.FF, 8388608);
    }

    public void setDoNotScroll(boolean z) {
        getCOSObject().setFlag(COSName.FF, 8388608, z);
    }

    public boolean isComb() {
        return getCOSObject().getFlag(COSName.FF, 16777216);
    }

    public void setComb(boolean z) {
        getCOSObject().setFlag(COSName.FF, 16777216, z);
    }

    public boolean isRichText() {
        return getCOSObject().getFlag(COSName.FF, FLAG_RICH_TEXT);
    }

    public void setRichText(boolean z) {
        getCOSObject().setFlag(COSName.FF, FLAG_RICH_TEXT, z);
    }

    public int getMaxLen() {
        return getCOSObject().getInt(COSName.MAX_LEN);
    }

    public void setMaxLen(int i) {
        getCOSObject().setInt(COSName.MAX_LEN, i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public void setValue(String str) throws IOException {
        getCOSObject().setString(COSName.V, str);
        applyChange();
    }

    public void setDefaultValue(String str) throws IOException {
        getCOSObject().setString(COSName.DV, str);
    }

    public String getValue() {
        return getStringOrStream(getInheritableAttribute(COSName.V));
    }

    public String getDefaultValue() {
        return getStringOrStream(getInheritableAttribute(COSName.DV));
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDField
    public String getValueAsString() {
        return getValue();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.form.PDTerminalField
    public void constructAppearances() throws IOException {
        new AppearanceGeneratorHelper(this).setAppearanceValue(getValue());
    }
}
