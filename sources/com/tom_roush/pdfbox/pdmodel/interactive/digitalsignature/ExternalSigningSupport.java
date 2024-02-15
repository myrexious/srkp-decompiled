package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public interface ExternalSigningSupport {
    InputStream getContent() throws IOException;

    void setSignature(byte[] bArr) throws IOException;
}
