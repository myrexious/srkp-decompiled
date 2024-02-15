package com.tom_roush.pdfbox.pdmodel.interactive.measurement;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class PDNumberFormatDictionary implements COSObjectable {
    public static final String FRACTIONAL_DISPLAY_DECIMAL = "D";
    public static final String FRACTIONAL_DISPLAY_FRACTION = "F";
    public static final String FRACTIONAL_DISPLAY_ROUND = "R";
    public static final String FRACTIONAL_DISPLAY_TRUNCATE = "T";
    public static final String LABEL_PREFIX_TO_VALUE = "P";
    public static final String LABEL_SUFFIX_TO_VALUE = "S";
    public static final String TYPE = "NumberFormat";
    private COSDictionary numberFormatDictionary;

    public String getType() {
        return TYPE;
    }

    public PDNumberFormatDictionary() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.numberFormatDictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, TYPE);
    }

    public PDNumberFormatDictionary(COSDictionary cOSDictionary) {
        this.numberFormatDictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.numberFormatDictionary;
    }

    public String getUnits() {
        return getCOSObject().getString("U");
    }

    public void setUnits(String str) {
        getCOSObject().setString("U", str);
    }

    public float getConversionFactor() {
        return getCOSObject().getFloat("C");
    }

    public void setConversionFactor(float f) {
        getCOSObject().setFloat("C", f);
    }

    public String getFractionalDisplay() {
        return getCOSObject().getString("F", "D");
    }

    public void setFractionalDisplay(String str) {
        if (str == null || "D".equals(str) || "F".equals(str) || "R".equals(str) || "T".equals(str)) {
            getCOSObject().setString("F", str);
            return;
        }
        throw new IllegalArgumentException("Value must be \"D\", \"F\", \"R\", or \"T\", (or null).");
    }

    public int getDenominator() {
        return getCOSObject().getInt("D");
    }

    public void setDenominator(int i) {
        getCOSObject().setInt("D", i);
    }

    public boolean isFD() {
        return getCOSObject().getBoolean("FD", false);
    }

    public void setFD(boolean z) {
        getCOSObject().setBoolean("FD", z);
    }

    public String getThousandsSeparator() {
        return getCOSObject().getString(StandardStructureTypes.RT, ",");
    }

    public void setThousandsSeparator(String str) {
        getCOSObject().setString(StandardStructureTypes.RT, str);
    }

    public String getDecimalSeparator() {
        return getCOSObject().getString("RD", ".");
    }

    public void setDecimalSeparator(String str) {
        getCOSObject().setString("RD", str);
    }

    public String getLabelPrefixString() {
        return getCOSObject().getString("PS", StringUtils.SPACE);
    }

    public void setLabelPrefixString(String str) {
        getCOSObject().setString("PS", str);
    }

    public String getLabelSuffixString() {
        return getCOSObject().getString("SS", StringUtils.SPACE);
    }

    public void setLabelSuffixString(String str) {
        getCOSObject().setString("SS", str);
    }

    public String getLabelPositionToValue() {
        return getCOSObject().getString(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE, "S");
    }

    public void setLabelPositionToValue(String str) {
        if (str == null || "P".equals(str) || "S".equals(str)) {
            getCOSObject().setString(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE, str);
            return;
        }
        throw new IllegalArgumentException("Value must be \"S\", or \"P\" (or null).");
    }
}
