package com.tom_roush.pdfbox.pdmodel.encryption;

/* loaded from: classes3.dex */
public abstract class ProtectionPolicy {
    private static final short DEFAULT_KEY_LENGTH = 40;
    private short encryptionKeyLength = DEFAULT_KEY_LENGTH;
    private boolean preferAES = false;

    public void setEncryptionKeyLength(int i) {
        if (i != 40 && i != 128 && i != 256) {
            throw new IllegalArgumentException("Invalid key length '" + i + "' value must be 40, 128 or 256!");
        }
        this.encryptionKeyLength = (short) i;
    }

    public int getEncryptionKeyLength() {
        return this.encryptionKeyLength;
    }

    public boolean isPreferAES() {
        return this.preferAES;
    }

    public void setPreferAES(boolean z) {
        this.preferAES = z;
    }
}
