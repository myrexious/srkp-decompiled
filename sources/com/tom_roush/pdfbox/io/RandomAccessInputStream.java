package com.tom_roush.pdfbox.io;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class RandomAccessInputStream extends InputStream {
    private final RandomAccessRead input;
    private long position = 0;

    public RandomAccessInputStream(RandomAccessRead randomAccessRead) {
        this.input = randomAccessRead;
    }

    void restorePosition() throws IOException {
        this.input.seek(this.position);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        restorePosition();
        long length = this.input.length() - this.input.getPosition();
        if (length > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) length;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        restorePosition();
        if (this.input.isEOF()) {
            return -1;
        }
        int read = this.input.read();
        if (read != -1) {
            this.position++;
        } else {
            Log.e("PdfBox-Android", "read() returns -1, assumed position: " + this.position + ", actual position: " + this.input.getPosition());
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        restorePosition();
        if (this.input.isEOF()) {
            return -1;
        }
        int read = this.input.read(bArr, i, i2);
        if (read != -1) {
            this.position += read;
        } else {
            Log.e("PdfBox-Android", "read() returns -1, assumed position: " + this.position + ", actual position: " + this.input.getPosition());
        }
        return read;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        restorePosition();
        this.input.seek(this.position + j);
        this.position += j;
        return j;
    }
}
