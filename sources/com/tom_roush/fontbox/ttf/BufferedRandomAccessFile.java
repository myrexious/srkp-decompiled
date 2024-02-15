package com.tom_roush.fontbox.ttf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes3.dex */
public class BufferedRandomAccessFile extends RandomAccessFile {
    private int bufend;
    private final byte[] buffer;
    private int bufpos;
    private long realpos;

    public BufferedRandomAccessFile(String str, String str2, int i) throws FileNotFoundException {
        super(str, str2);
        this.bufend = 0;
        this.bufpos = 0;
        this.realpos = 0L;
        this.buffer = new byte[i];
    }

    public BufferedRandomAccessFile(File file, String str, int i) throws FileNotFoundException {
        super(file, str);
        this.bufend = 0;
        this.bufpos = 0;
        this.realpos = 0L;
        this.buffer = new byte[i];
    }

    @Override // java.io.RandomAccessFile
    public final int read() throws IOException {
        if ((this.bufpos < this.bufend || fillBuffer() >= 0) && this.bufend != 0) {
            byte[] bArr = this.buffer;
            int i = this.bufpos;
            this.bufpos = i + 1;
            return (bArr[i] + 256) & 255;
        }
        return -1;
    }

    private int fillBuffer() throws IOException {
        int read = super.read(this.buffer);
        if (read >= 0) {
            this.realpos += read;
            this.bufend = read;
            this.bufpos = 0;
        }
        return read;
    }

    private void invalidate() throws IOException {
        this.bufend = 0;
        this.bufpos = 0;
        this.realpos = super.getFilePointer();
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (true) {
            int i4 = this.bufend;
            int i5 = this.bufpos;
            int i6 = i4 - i5;
            if (i2 <= i6) {
                System.arraycopy(this.buffer, i5, bArr, i, i2);
                this.bufpos += i2;
                return i3 + i2;
            }
            System.arraycopy(this.buffer, i5, bArr, i, i6);
            i3 += i6;
            this.bufpos += i6;
            if (fillBuffer() <= 0) {
                if (i3 == 0) {
                    return -1;
                }
                return i3;
            }
            i += i6;
            i2 -= i6;
        }
    }

    @Override // java.io.RandomAccessFile
    public long getFilePointer() throws IOException {
        return (this.realpos - this.bufend) + this.bufpos;
    }

    @Override // java.io.RandomAccessFile
    public void seek(long j) throws IOException {
        int i;
        int i2 = (int) (this.realpos - j);
        if (i2 >= 0 && i2 <= (i = this.bufend)) {
            this.bufpos = i - i2;
            return;
        }
        super.seek(j);
        invalidate();
    }
}
