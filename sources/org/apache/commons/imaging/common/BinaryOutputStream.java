package org.apache.commons.imaging.common;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public class BinaryOutputStream extends OutputStream {
    private ByteOrder byteOrder;
    private int count;
    private final OutputStream os;

    public BinaryOutputStream(OutputStream outputStream, ByteOrder byteOrder) {
        ByteOrder byteOrder2 = ByteOrder.BIG_ENDIAN;
        this.byteOrder = byteOrder;
        this.os = outputStream;
    }

    public BinaryOutputStream(OutputStream outputStream) {
        this.byteOrder = ByteOrder.BIG_ENDIAN;
        this.os = outputStream;
    }

    protected void setByteOrder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public ByteOrder getByteOrder() {
        return this.byteOrder;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.os.write(i);
        this.count++;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) throws IOException {
        this.os.write(bArr, 0, bArr.length);
        this.count += bArr.length;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        this.os.write(bArr, i, i2);
        this.count += i2;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.os.flush();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.os.close();
    }

    public int getByteCount() {
        return this.count;
    }

    public final void write4Bytes(int i) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            write((i >> 24) & 255);
            write((i >> 16) & 255);
            write((i >> 8) & 255);
            write(i & 255);
            return;
        }
        write(i & 255);
        write((i >> 8) & 255);
        write((i >> 16) & 255);
        write((i >> 24) & 255);
    }

    public final void write3Bytes(int i) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            write((i >> 16) & 255);
            write((i >> 8) & 255);
            write(i & 255);
            return;
        }
        write(i & 255);
        write((i >> 8) & 255);
        write((i >> 16) & 255);
    }

    public final void write2Bytes(int i) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            write((i >> 8) & 255);
            write(i & 255);
            return;
        }
        write(i & 255);
        write((i >> 8) & 255);
    }
}
