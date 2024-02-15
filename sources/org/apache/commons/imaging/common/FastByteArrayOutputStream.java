package org.apache.commons.imaging.common;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
class FastByteArrayOutputStream extends OutputStream {
    private final byte[] bytes;
    private int count;

    public FastByteArrayOutputStream(int i) {
        this.bytes = new byte[i];
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        int i2 = this.count;
        byte[] bArr = this.bytes;
        if (i2 >= bArr.length) {
            throw new IOException("Write exceeded expected length (" + this.count + ", " + this.bytes.length + ")");
        }
        bArr[i2] = (byte) i;
        this.count = i2 + 1;
    }

    public byte[] toByteArray() {
        int i = this.count;
        byte[] bArr = this.bytes;
        if (i < bArr.length) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            return bArr2;
        }
        return bArr;
    }

    public int getBytesWritten() {
        return this.count;
    }
}
