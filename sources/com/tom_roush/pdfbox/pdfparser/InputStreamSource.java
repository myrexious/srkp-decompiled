package com.tom_roush.pdfbox.pdfparser;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import org.apache.commons.imaging.formats.jpeg.iptc.IptcConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class InputStreamSource implements SequentialSource {
    private final PushbackInputStream input;
    private int position = 0;

    public InputStreamSource(InputStream inputStream) {
        this.input = new PushbackInputStream(inputStream, IptcConstants.IPTC_NON_EXTENDED_RECORD_MAXIMUM_SIZE);
    }

    @Override // com.tom_roush.pdfbox.pdfparser.SequentialSource
    public int read() throws IOException {
        int read = this.input.read();
        this.position++;
        return read;
    }

    @Override // com.tom_roush.pdfbox.pdfparser.SequentialSource
    public int read(byte[] bArr) throws IOException {
        int read = this.input.read(bArr);
        if (read > 0) {
            this.position += read;
            return read;
        }
        return -1;
    }

    @Override // com.tom_roush.pdfbox.pdfparser.SequentialSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.input.read(bArr, i, i2);
        if (read > 0) {
            this.position += read;
            return read;
        }
        return -1;
    }

    @Override // com.tom_roush.pdfbox.pdfparser.SequentialSource
    public long getPosition() throws IOException {
        return this.position;
    }

    @Override // com.tom_roush.pdfbox.pdfparser.SequentialSource
    public int peek() throws IOException {
        int read = this.input.read();
        if (read != -1) {
            this.input.unread(read);
        }
        return read;
    }

    @Override // com.tom_roush.pdfbox.pdfparser.SequentialSource
    public void unread(int i) throws IOException {
        this.input.unread(i);
        this.position--;
    }

    @Override // com.tom_roush.pdfbox.pdfparser.SequentialSource
    public void unread(byte[] bArr) throws IOException {
        this.input.unread(bArr);
        this.position -= bArr.length;
    }

    @Override // com.tom_roush.pdfbox.pdfparser.SequentialSource
    public void unread(byte[] bArr, int i, int i2) throws IOException {
        this.input.unread(bArr, i, i2);
        this.position -= i2;
    }

    @Override // com.tom_roush.pdfbox.pdfparser.SequentialSource
    public byte[] readFully(int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        do {
            int read = read(bArr, i2, i - i2);
            if (read < 0) {
                throw new EOFException();
            }
            i2 += read;
        } while (i2 < i);
        return bArr;
    }

    @Override // com.tom_roush.pdfbox.pdfparser.SequentialSource
    public boolean isEOF() throws IOException {
        return peek() == -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.input.close();
    }
}
