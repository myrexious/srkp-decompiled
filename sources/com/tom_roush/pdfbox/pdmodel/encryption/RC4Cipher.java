package com.tom_roush.pdfbox.pdmodel.encryption;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class RC4Cipher {
    private int b;
    private int c;
    private final int[] salt = new int[256];

    private static int fixByte(byte b) {
        return b < 0 ? b + 256 : b;
    }

    public void setKey(byte[] bArr) {
        this.b = 0;
        this.c = 0;
        if (bArr.length < 1 || bArr.length > 32) {
            throw new IllegalArgumentException("number of bytes must be between 1 and 32");
        }
        int i = 0;
        while (true) {
            int[] iArr = this.salt;
            if (i >= iArr.length) {
                break;
            }
            iArr[i] = i;
            i++;
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.salt.length; i4++) {
            int fixByte = fixByte(bArr[i2]);
            int[] iArr2 = this.salt;
            i3 = ((fixByte + iArr2[i4]) + i3) % 256;
            swap(iArr2, i4, i3);
            i2 = (i2 + 1) % bArr.length;
        }
    }

    private static void swap(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    public void write(byte b, OutputStream outputStream) throws IOException {
        int i = (this.b + 1) % 256;
        this.b = i;
        int[] iArr = this.salt;
        int i2 = (iArr[i] + this.c) % 256;
        this.c = i2;
        swap(iArr, i, i2);
        int[] iArr2 = this.salt;
        outputStream.write(b ^ ((byte) iArr2[(iArr2[this.b] + iArr2[this.c]) % 256]));
    }

    public void write(byte[] bArr, OutputStream outputStream) throws IOException {
        for (byte b : bArr) {
            write(b, outputStream);
        }
    }

    public void write(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            }
            write(bArr, 0, read, outputStream);
        }
    }

    public void write(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        for (int i3 = i; i3 < i + i2; i3++) {
            write(bArr[i3], outputStream);
        }
    }
}
