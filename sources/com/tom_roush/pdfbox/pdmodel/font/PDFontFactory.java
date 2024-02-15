package com.tom_roush.pdfbox.pdmodel.font;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.ResourceCache;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class PDFontFactory {
    private PDFontFactory() {
    }

    public static PDFont createFont(COSDictionary cOSDictionary) throws IOException {
        return createFont(cOSDictionary, null);
    }

    public static PDFont createFont(COSDictionary cOSDictionary, ResourceCache resourceCache) throws IOException {
        COSName cOSName = cOSDictionary.getCOSName(COSName.TYPE, COSName.FONT);
        if (!COSName.FONT.equals(cOSName)) {
            Log.e("PdfBox-Android", "Expected 'Font' dictionary but found '" + cOSName.getName() + OperatorName.SHOW_TEXT_LINE);
        }
        COSName cOSName2 = cOSDictionary.getCOSName(COSName.SUBTYPE);
        if (COSName.TYPE1.equals(cOSName2)) {
            COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.FONT_DESC);
            if ((dictionaryObject instanceof COSDictionary) && ((COSDictionary) dictionaryObject).containsKey(COSName.FONT_FILE3)) {
                return new PDType1CFont(cOSDictionary);
            }
            return new PDType1Font(cOSDictionary);
        } else if (COSName.MM_TYPE1.equals(cOSName2)) {
            COSBase dictionaryObject2 = cOSDictionary.getDictionaryObject(COSName.FONT_DESC);
            if ((dictionaryObject2 instanceof COSDictionary) && ((COSDictionary) dictionaryObject2).containsKey(COSName.FONT_FILE3)) {
                return new PDType1CFont(cOSDictionary);
            }
            return new PDMMType1Font(cOSDictionary);
        } else if (COSName.TRUE_TYPE.equals(cOSName2)) {
            return new PDTrueTypeFont(cOSDictionary);
        } else {
            if (COSName.TYPE3.equals(cOSName2)) {
                return new PDType3Font(cOSDictionary, resourceCache);
            }
            if (COSName.TYPE0.equals(cOSName2)) {
                return new PDType0Font(cOSDictionary);
            }
            if (COSName.CID_FONT_TYPE0.equals(cOSName2)) {
                throw new IOException("Type 0 descendant font not allowed");
            }
            if (COSName.CID_FONT_TYPE2.equals(cOSName2)) {
                throw new IOException("Type 2 descendant font not allowed");
            }
            Log.w("PdfBox-Android", "Invalid font subtype '" + cOSName2 + OperatorName.SHOW_TEXT_LINE);
            return new PDType1Font(cOSDictionary);
        }
    }

    public static PDCIDFont createDescendantFont(COSDictionary cOSDictionary, PDType0Font pDType0Font) throws IOException {
        COSName cOSName = cOSDictionary.getCOSName(COSName.TYPE, COSName.FONT);
        if (!COSName.FONT.equals(cOSName)) {
            throw new IOException("Expected 'Font' dictionary but found '" + cOSName.getName() + OperatorName.SHOW_TEXT_LINE);
        }
        COSName cOSName2 = cOSDictionary.getCOSName(COSName.SUBTYPE);
        if (COSName.CID_FONT_TYPE0.equals(cOSName2)) {
            return new PDCIDFontType0(cOSDictionary, pDType0Font);
        }
        if (COSName.CID_FONT_TYPE2.equals(cOSName2)) {
            return new PDCIDFontType2(cOSDictionary, pDType0Font);
        }
        throw new IOException("Invalid font type: " + cOSName);
    }

    @Deprecated
    public static PDFont createDefaultFont() throws IOException {
        return PDType1Font.HELVETICA;
    }
}
