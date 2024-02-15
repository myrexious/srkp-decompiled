package org.apache.commons.imaging.common.itu_t4;

import java.io.OutputStream;

/* loaded from: classes2.dex */
class BitArrayOutputStream extends OutputStream {
    private byte[] buffer;
    private int bytesWritten;
    private int cache;
    private int cacheMask;

    public BitArrayOutputStream() {
        this.cacheMask = 128;
        this.buffer = new byte[16];
    }

    BitArrayOutputStream(int i) {
        this.cacheMask = 128;
        this.buffer = new byte[i];
    }

    public int size() {
        return this.bytesWritten;
    }

    public byte[] toByteArray() {
        flush();
        int i = this.bytesWritten;
        byte[] bArr = this.buffer;
        if (i == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        flush();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        if (this.cacheMask != 128) {
            writeByte(this.cache);
            this.cache = 0;
            this.cacheMask = 128;
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        flush();
        writeByte(i);
    }

    public void writeBit(int i) {
        if (i != 0) {
            this.cache |= this.cacheMask;
        }
        int i2 = this.cacheMask >>> 1;
        this.cacheMask = i2;
        if (i2 == 0) {
            flush();
        }
    }

    public int getBitsAvailableInCurrentByte() {
        int i = 0;
        for (int i2 = this.cacheMask; i2 != 0; i2 >>>= 1) {
            i++;
        }
        return i;
    }

    private void writeByte(int i) {
        int i2 = this.bytesWritten;
        byte[] bArr = this.buffer;
        if (i2 >= bArr.length) {
            byte[] bArr2 = new byte[bArr.length * 2];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            this.buffer = bArr2;
        }
        byte[] bArr3 = this.buffer;
        int i3 = this.bytesWritten;
        this.bytesWritten = i3 + 1;
        bArr3[i3] = (byte) i;
    }
}
