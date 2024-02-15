package com.tom_roush.pdfbox.io;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public class RandomAccessOutputStream extends OutputStream {
    private final RandomAccessWrite writer;

    public RandomAccessOutputStream(RandomAccessWrite randomAccessWrite) {
        this.writer = randomAccessWrite;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.writer.write(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.writer.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.writer.write(i);
    }
}
