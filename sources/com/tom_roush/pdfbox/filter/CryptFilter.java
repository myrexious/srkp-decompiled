package com.tom_roush.pdfbox.filter;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public final class CryptFilter extends Filter {
    @Override // com.tom_roush.pdfbox.filter.Filter
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        COSName cOSName = (COSName) cOSDictionary.getDictionaryObject(COSName.NAME);
        if (cOSName == null || cOSName.equals(COSName.IDENTITY)) {
            new IdentityFilter().decode(inputStream, outputStream, cOSDictionary, i);
            return new DecodeResult(cOSDictionary);
        }
        throw new IOException("Unsupported crypt filter " + cOSName.getName());
    }

    @Override // com.tom_roush.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        COSName cOSName = (COSName) cOSDictionary.getDictionaryObject(COSName.NAME);
        if (cOSName == null || cOSName.equals(COSName.IDENTITY)) {
            new IdentityFilter().encode(inputStream, outputStream, cOSDictionary);
            return;
        }
        throw new IOException("Unsupported crypt filter " + cOSName.getName());
    }
}
