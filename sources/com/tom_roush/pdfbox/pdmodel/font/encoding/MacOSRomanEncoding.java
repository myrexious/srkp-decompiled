package com.tom_roush.pdfbox.pdmodel.font.encoding;

import com.tom_roush.pdfbox.cos.COSBase;

/* loaded from: classes3.dex */
public class MacOSRomanEncoding extends MacRomanEncoding {
    private static final Object[][] MAC_OS_ROMAN_ENCODING_TABLE = {new Object[]{255, "notequal"}, new Object[]{260, "infinity"}, new Object[]{262, "lessequal"}, new Object[]{263, "greaterequal"}, new Object[]{266, "partialdiff"}, new Object[]{267, "summation"}, new Object[]{270, "product"}, new Object[]{271, "pi"}, new Object[]{272, "integral"}, new Object[]{275, "Omega"}, new Object[]{303, "radical"}, new Object[]{305, "approxequal"}, new Object[]{306, "Delta"}, new Object[]{327, "lozenge"}, new Object[]{333, "Euro"}, new Object[]{360, "apple"}};
    public static final MacOSRomanEncoding INSTANCE = new MacOSRomanEncoding();

    @Override // com.tom_roush.pdfbox.pdmodel.font.encoding.MacRomanEncoding, com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return null;
    }

    public MacOSRomanEncoding() {
        Object[][] objArr;
        for (Object[] objArr2 : MAC_OS_ROMAN_ENCODING_TABLE) {
            add(((Integer) objArr2[0]).intValue(), objArr2[1].toString());
        }
    }
}
