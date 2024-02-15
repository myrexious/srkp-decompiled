package com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf;

import com.tom_roush.pdfbox.cos.COSDictionary;

/* loaded from: classes3.dex */
public class PDTableAttributeObject extends PDStandardAttributeObject {
    protected static final String COL_SPAN = "ColSpan";
    protected static final String HEADERS = "Headers";
    public static final String OWNER_TABLE = "Table";
    protected static final String ROW_SPAN = "RowSpan";
    protected static final String SCOPE = "Scope";
    public static final String SCOPE_BOTH = "Both";
    public static final String SCOPE_COLUMN = "Column";
    public static final String SCOPE_ROW = "Row";
    protected static final String SUMMARY = "Summary";

    public PDTableAttributeObject() {
        setOwner("Table");
    }

    public PDTableAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public int getRowSpan() {
        return getInteger(ROW_SPAN, 1);
    }

    public void setRowSpan(int i) {
        setInteger(ROW_SPAN, i);
    }

    public int getColSpan() {
        return getInteger(COL_SPAN, 1);
    }

    public void setColSpan(int i) {
        setInteger(COL_SPAN, i);
    }

    public String[] getHeaders() {
        return getArrayOfString(HEADERS);
    }

    public void setHeaders(String[] strArr) {
        setArrayOfString(HEADERS, strArr);
    }

    public String getScope() {
        return getName(SCOPE);
    }

    public void setScope(String str) {
        setName(SCOPE, str);
    }

    public String getSummary() {
        return getString(SUMMARY);
    }

    public void setSummary(String str) {
        setString(SUMMARY, str);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject
    public String toString() {
        StringBuilder append = new StringBuilder().append(super.toString());
        if (isSpecified(ROW_SPAN)) {
            append.append(", RowSpan=").append(getRowSpan());
        }
        if (isSpecified(COL_SPAN)) {
            append.append(", ColSpan=").append(getColSpan());
        }
        if (isSpecified(HEADERS)) {
            append.append(", Headers=").append(arrayToString(getHeaders()));
        }
        if (isSpecified(SCOPE)) {
            append.append(", Scope=").append(getScope());
        }
        if (isSpecified(SUMMARY)) {
            append.append(", Summary=").append(getSummary());
        }
        return append.toString();
    }
}
