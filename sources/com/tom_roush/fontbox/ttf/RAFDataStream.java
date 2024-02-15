package com.tom_roush.fontbox.ttf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class RAFDataStream extends TTFDataStream {
    private static final int BUFFERSIZE = 16384;
    private RandomAccessFile raf;
    private File ttfFile;

    RAFDataStream(String str, String str2) throws IOException {
        this(new File(str), str2);
    }

    public RAFDataStream(File file, String str) throws IOException {
        this.raf = null;
        this.ttfFile = null;
        this.raf = new BufferedRandomAccessFile(file, str, 16384);
        this.ttfFile = file;
    }

    @Override // com.tom_roush.fontbox.ttf.TTFDataStream
    public short readSignedShort() throws IOException {
        return this.raf.readShort();
    }

    @Override // com.tom_roush.fontbox.ttf.TTFDataStream
    public long getCurrentPosition() throws IOException {
        return this.raf.getFilePointer();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            randomAccessFile.close();
            this.raf = null;
        }
    }

    @Override // com.tom_roush.fontbox.ttf.TTFDataStream
    public int read() throws IOException {
        return this.raf.read();
    }

    @Override // com.tom_roush.fontbox.ttf.TTFDataStream
    public int readUnsignedShort() throws IOException {
        return this.raf.readUnsignedShort();
    }

    @Override // com.tom_roush.fontbox.ttf.TTFDataStream
    public long readLong() throws IOException {
        return this.raf.readLong();
    }

    @Override // com.tom_roush.fontbox.ttf.TTFDataStream
    public void seek(long j) throws IOException {
        this.raf.seek(j);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFDataStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.raf.read(bArr, i, i2);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFDataStream
    public InputStream getOriginalData() throws IOException {
        return new FileInputStream(this.ttfFile);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFDataStream
    public long getOriginalDataSize() {
        return this.ttfFile.length();
    }
}
