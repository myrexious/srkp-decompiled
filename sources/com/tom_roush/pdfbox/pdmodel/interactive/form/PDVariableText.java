package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import java.io.IOException;

/* loaded from: classes3.dex */
public abstract class PDVariableText extends PDTerminalField {
    public static final int QUADDING_CENTERED = 1;
    public static final int QUADDING_LEFT = 0;
    public static final int QUADDING_RIGHT = 2;

    public PDVariableText(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
    }

    public PDVariableText(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDNonTerminalField pDNonTerminalField) {
        super(pDAcroForm, cOSDictionary, pDNonTerminalField);
    }

    public String getDefaultAppearance() {
        COSBase inheritableAttribute = getInheritableAttribute(COSName.DA);
        if (inheritableAttribute instanceof COSString) {
            return ((COSString) inheritableAttribute).getString();
        }
        return null;
    }

    public PDDefaultAppearanceString getDefaultAppearanceString() throws IOException {
        COSBase inheritableAttribute = getInheritableAttribute(COSName.DA);
        return new PDDefaultAppearanceString(inheritableAttribute instanceof COSString ? (COSString) inheritableAttribute : null, getAcroForm().getDefaultResources());
    }

    public void setDefaultAppearance(String str) {
        getCOSObject().setString(COSName.DA, str);
    }

    public String getDefaultStyleString() {
        return ((COSString) getCOSObject().getDictionaryObject(COSName.DS)).getString();
    }

    public void setDefaultStyleString(String str) {
        if (str != null) {
            getCOSObject().setItem(COSName.DS, (COSBase) new COSString(str));
        } else {
            getCOSObject().removeItem(COSName.DS);
        }
    }

    public int getQ() {
        COSNumber cOSNumber = (COSNumber) getInheritableAttribute(COSName.Q);
        if (cOSNumber != null) {
            return cOSNumber.intValue();
        }
        return 0;
    }

    public void setQ(int i) {
        getCOSObject().setInt(COSName.Q, i);
    }

    public String getRichTextValue() throws IOException {
        return getStringOrStream(getInheritableAttribute(COSName.RV));
    }

    public void setRichTextValue(String str) {
        if (str != null) {
            getCOSObject().setItem(COSName.RV, (COSBase) new COSString(str));
        } else {
            getCOSObject().removeItem(COSName.RV);
        }
    }

    public final String getStringOrStream(COSBase cOSBase) {
        if (cOSBase == null) {
            return "";
        }
        if (cOSBase instanceof COSString) {
            return ((COSString) cOSBase).getString();
        }
        return cOSBase instanceof COSStream ? ((COSStream) cOSBase).toTextString() : "";
    }
}
