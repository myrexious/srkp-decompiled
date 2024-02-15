package org.bouncycastle.operator;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.Arrays;

/* loaded from: classes2.dex */
public class MacCaptureStream extends OutputStream {
    private final OutputStream cOut;
    private final byte[] mac;
    int macIndex = 0;

    public MacCaptureStream(OutputStream outputStream, int i) {
        this.cOut = outputStream;
        this.mac = new byte[i];
    }

    public byte[] getMac() {
        return Arrays.clone(this.mac);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        int i2 = this.macIndex;
        byte[] bArr = this.mac;
        if (i2 != bArr.length) {
            this.macIndex = i2 + 1;
            bArr[i2] = (byte) i;
            return;
        }
        byte b = bArr[0];
        System.arraycopy(bArr, 1, bArr, 0, bArr.length - 1);
        byte[] bArr2 = this.mac;
        bArr2[bArr2.length - 1] = (byte) i;
        this.cOut.write(b);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = this.mac;
        if (i2 < bArr2.length) {
            for (int i3 = 0; i3 != i2; i3++) {
                write(bArr[i + i3]);
            }
            return;
        }
        this.cOut.write(bArr2, 0, this.macIndex);
        byte[] bArr3 = this.mac;
        this.macIndex = bArr3.length;
        System.arraycopy(bArr, (i + i2) - bArr3.length, bArr3, 0, bArr3.length);
        this.cOut.write(bArr, i, i2 - this.mac.length);
    }
}
