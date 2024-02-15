package com.tom_roush.pdfbox.io;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes3.dex */
public interface SequentialRead extends Closeable {
    int read() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;
}
