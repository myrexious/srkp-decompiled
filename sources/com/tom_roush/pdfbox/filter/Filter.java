package com.tom_roush.pdfbox.filter;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public abstract class Filter {
    public static final String SYSPROP_DEFLATELEVEL = "com.tom_roush.pdfbox.filter.deflatelevel";

    public abstract DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException;

    public abstract void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException;

    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i, DecodeOptions decodeOptions) throws IOException {
        return decode(inputStream, outputStream, cOSDictionary, i);
    }

    public final void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        encode(inputStream, outputStream, cOSDictionary.asUnmodifiableDictionary());
    }

    public COSDictionary getDecodeParams(COSDictionary cOSDictionary, int i) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.F, COSName.FILTER);
        COSBase dictionaryObject2 = cOSDictionary.getDictionaryObject(COSName.DP, COSName.DECODE_PARMS);
        if ((dictionaryObject instanceof COSName) && (dictionaryObject2 instanceof COSDictionary)) {
            return (COSDictionary) dictionaryObject2;
        }
        boolean z = dictionaryObject instanceof COSArray;
        if (z && (dictionaryObject2 instanceof COSArray)) {
            COSArray cOSArray = (COSArray) dictionaryObject2;
            if (i < cOSArray.size()) {
                COSBase object = cOSArray.getObject(i);
                if (object instanceof COSDictionary) {
                    return (COSDictionary) object;
                }
            }
        } else if (dictionaryObject2 != null && !z && !(dictionaryObject2 instanceof COSArray)) {
            Log.e("PdfBox-Android", "Expected DecodeParams to be an Array or Dictionary but found " + dictionaryObject2.getClass().getName());
        }
        return new COSDictionary();
    }

    public static int getCompressionLevel() {
        int i;
        try {
            i = Integer.parseInt(System.getProperty(SYSPROP_DEFLATELEVEL, "-1"));
        } catch (NumberFormatException e) {
            Log.w("PdfBox-Android", e.getMessage(), e);
            i = -1;
        }
        return Math.max(-1, Math.min(9, i));
    }
}
