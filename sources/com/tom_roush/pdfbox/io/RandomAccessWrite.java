package com.tom_roush.pdfbox.io;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes3.dex */
public interface RandomAccessWrite extends Closeable {
    void clear() throws IOException;

    void write(int i) throws IOException;

    void write(byte[] bArr) throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;
}
