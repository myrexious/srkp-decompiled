package com.tom_roush.pdfbox.pdmodel.encryption;

/* loaded from: classes3.dex */
public class StandardDecryptionMaterial extends DecryptionMaterial {
    private final String password;

    public StandardDecryptionMaterial(String str) {
        this.password = str;
    }

    public String getPassword() {
        return this.password;
    }
}
