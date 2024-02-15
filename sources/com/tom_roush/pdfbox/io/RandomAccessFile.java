package com.tom_roush.pdfbox.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes3.dex */
public class RandomAccessFile implements RandomAccess {
    private boolean isClosed;
    private final java.io.RandomAccessFile ras;

    public RandomAccessFile(File file, String str) throws FileNotFoundException {
        this.ras = new java.io.RandomAccessFile(file, str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.ras.close();
        this.isClosed = true;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void clear() throws IOException {
        checkClosed();
        this.ras.seek(0L);
        this.ras.setLength(0L);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public void seek(long j) throws IOException {
        checkClosed();
        this.ras.seek(j);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public long getPosition() throws IOException {
        checkClosed();
        return this.ras.getFilePointer();
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int read() throws IOException {
        checkClosed();
        return this.ras.read();
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int read(byte[] bArr) throws IOException {
        checkClosed();
        return this.ras.read(bArr);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int read(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        return this.ras.read(bArr, i, i2);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        checkClosed();
        return this.ras.length();
    }

    private void checkClosed() throws IOException {
        if (this.isClosed) {
            throw new IOException("RandomAccessFile already closed");
        }
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.isClosed;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void write(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        this.ras.write(bArr, i, i2);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void write(int i) throws IOException {
        checkClosed();
        this.ras.write(i);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int peek() throws IOException {
        int read = read();
        if (read != -1) {
            rewind(1);
        }
        return read;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public void rewind(int i) throws IOException {
        checkClosed();
        java.io.RandomAccessFile randomAccessFile = this.ras;
        randomAccessFile.seek(randomAccessFile.getFilePointer() - i);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public byte[] readFully(int i) throws IOException {
        checkClosed();
        byte[] bArr = new byte[i];
        this.ras.readFully(bArr);
        return bArr;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public boolean isEOF() throws IOException {
        return peek() == -1;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int available() throws IOException {
        checkClosed();
        return (int) Math.min(this.ras.length() - getPosition(), 2147483647L);
    }
}
