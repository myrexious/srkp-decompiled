package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import com.tom_roush.pdfbox.pdfwriter.COSWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class SigningSupport implements ExternalSigningSupport, Closeable {
    private COSWriter cosWriter;

    public SigningSupport(COSWriter cOSWriter) {
        this.cosWriter = cOSWriter;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport
    public InputStream getContent() throws IOException {
        return this.cosWriter.getDataToSign();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport
    public void setSignature(byte[] bArr) throws IOException {
        this.cosWriter.writeExternalSignature(bArr);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        COSWriter cOSWriter = this.cosWriter;
        if (cOSWriter != null) {
            try {
                cOSWriter.close();
            } finally {
                this.cosWriter = null;
            }
        }
    }
}
