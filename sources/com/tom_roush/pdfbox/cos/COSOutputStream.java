package com.tom_roush.pdfbox.cos;

import com.tom_roush.pdfbox.filter.Filter;
import com.tom_roush.pdfbox.io.RandomAccess;
import com.tom_roush.pdfbox.io.RandomAccessInputStream;
import com.tom_roush.pdfbox.io.RandomAccessOutputStream;
import com.tom_roush.pdfbox.io.ScratchFile;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/* loaded from: classes3.dex */
public final class COSOutputStream extends FilterOutputStream {
    private RandomAccess buffer;
    private final List<Filter> filters;
    private final COSDictionary parameters;
    private final ScratchFile scratchFile;

    public COSOutputStream(List<Filter> list, COSDictionary cOSDictionary, OutputStream outputStream, ScratchFile scratchFile) throws IOException {
        super(outputStream);
        this.filters = list;
        this.parameters = cOSDictionary;
        this.scratchFile = scratchFile;
        if (list.isEmpty()) {
            this.buffer = null;
        } else {
            this.buffer = scratchFile.createBuffer();
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        RandomAccess randomAccess = this.buffer;
        if (randomAccess != null) {
            randomAccess.write(bArr);
        } else {
            super.write(bArr);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        RandomAccess randomAccess = this.buffer;
        if (randomAccess != null) {
            randomAccess.write(bArr, i, i2);
        } else {
            super.write(bArr, i, i2);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        RandomAccess randomAccess = this.buffer;
        if (randomAccess != null) {
            randomAccess.write(i);
        } else {
            super.write(i);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.buffer == null) {
            super.flush();
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (this.buffer != null) {
                for (int size = this.filters.size() - 1; size >= 0; size--) {
                    RandomAccessInputStream randomAccessInputStream = new RandomAccessInputStream(this.buffer);
                    if (size == 0) {
                        try {
                            this.filters.get(size).encode(randomAccessInputStream, this.out, this.parameters, size);
                        } finally {
                            randomAccessInputStream.close();
                        }
                    } else {
                        RandomAccess createBuffer = this.scratchFile.createBuffer();
                        try {
                            RandomAccessOutputStream randomAccessOutputStream = new RandomAccessOutputStream(createBuffer);
                            this.filters.get(size).encode(randomAccessInputStream, randomAccessOutputStream, this.parameters, size);
                            randomAccessOutputStream.close();
                            RandomAccess randomAccess = this.buffer;
                            try {
                                this.buffer = createBuffer;
                                randomAccess.close();
                            } catch (Throwable th) {
                                th = th;
                                createBuffer = randomAccess;
                                createBuffer.close();
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
                this.buffer.close();
                this.buffer = null;
            }
        } finally {
            super.close();
        }
    }
}
