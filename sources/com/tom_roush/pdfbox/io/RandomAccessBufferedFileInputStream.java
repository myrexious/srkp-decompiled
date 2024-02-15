package com.tom_roush.pdfbox.io;

import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class RandomAccessBufferedFileInputStream extends InputStream implements RandomAccessRead {
    private static final String TMP_FILE_PREFIX = "tmpPDFBox";
    private byte[] curPage;
    private long curPageOffset;
    private final long fileLength;
    private long fileOffset;
    private boolean isClosed;
    private byte[] lastRemovedCachePage;
    private int maxCachedPages;
    private int offsetWithinPage;
    private final Map<Long, byte[]> pageCache;
    private long pageOffsetMask;
    private int pageSize;
    private int pageSizeShift;
    private final java.io.RandomAccessFile raFile;
    private File tempFile;

    public RandomAccessBufferedFileInputStream(String str) throws IOException {
        this(new File(str));
    }

    public RandomAccessBufferedFileInputStream(File file) throws IOException {
        this.pageSizeShift = 12;
        this.pageSize = 1 << 12;
        this.pageOffsetMask = (-1) << 12;
        this.maxCachedPages = 1000;
        this.lastRemovedCachePage = null;
        this.pageCache = new LinkedHashMap<Long, byte[]>(this.maxCachedPages, 0.75f, true) { // from class: com.tom_roush.pdfbox.io.RandomAccessBufferedFileInputStream.1
            private static final long serialVersionUID = -6302488539257741101L;

            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<Long, byte[]> entry) {
                boolean z = size() > RandomAccessBufferedFileInputStream.this.maxCachedPages;
                if (z) {
                    RandomAccessBufferedFileInputStream.this.lastRemovedCachePage = entry.getValue();
                }
                return z;
            }
        };
        this.curPageOffset = -1L;
        this.curPage = new byte[this.pageSize];
        this.offsetWithinPage = 0;
        this.fileOffset = 0L;
        this.raFile = new java.io.RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER);
        this.fileLength = file.length();
        seek(0L);
    }

    public RandomAccessBufferedFileInputStream(InputStream inputStream) throws IOException {
        this.pageSizeShift = 12;
        this.pageSize = 1 << 12;
        this.pageOffsetMask = (-1) << 12;
        this.maxCachedPages = 1000;
        this.lastRemovedCachePage = null;
        this.pageCache = new LinkedHashMap<Long, byte[]>(this.maxCachedPages, 0.75f, true) { // from class: com.tom_roush.pdfbox.io.RandomAccessBufferedFileInputStream.1
            private static final long serialVersionUID = -6302488539257741101L;

            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<Long, byte[]> entry) {
                boolean z = size() > RandomAccessBufferedFileInputStream.this.maxCachedPages;
                if (z) {
                    RandomAccessBufferedFileInputStream.this.lastRemovedCachePage = entry.getValue();
                }
                return z;
            }
        };
        this.curPageOffset = -1L;
        this.curPage = new byte[this.pageSize];
        this.offsetWithinPage = 0;
        this.fileOffset = 0L;
        File createTmpFile = createTmpFile(inputStream);
        this.tempFile = createTmpFile;
        this.fileLength = createTmpFile.length();
        this.raFile = new java.io.RandomAccessFile(this.tempFile, PDPageLabelRange.STYLE_ROMAN_LOWER);
        seek(0L);
    }

    private File createTmpFile(InputStream inputStream) throws IOException {
        FileOutputStream fileOutputStream;
        Throwable th;
        File createTempFile;
        try {
            createTempFile = File.createTempFile(TMP_FILE_PREFIX, ".pdf");
            fileOutputStream = new FileOutputStream(createTempFile);
        } catch (Throwable th2) {
            fileOutputStream = null;
            th = th2;
        }
        try {
            IOUtils.copy(inputStream, fileOutputStream);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(fileOutputStream);
            return createTempFile;
        } catch (Throwable th3) {
            th = th3;
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(fileOutputStream);
            throw th;
        }
    }

    private void deleteTempFile() {
        File file = this.tempFile;
        if (file != null) {
            file.delete();
        }
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public long getPosition() {
        return this.fileOffset;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public void seek(long j) throws IOException {
        long j2 = this.pageOffsetMask & j;
        if (j2 != this.curPageOffset) {
            byte[] bArr = this.pageCache.get(Long.valueOf(j2));
            if (bArr == null) {
                this.raFile.seek(j2);
                bArr = readPage();
                this.pageCache.put(Long.valueOf(j2), bArr);
            }
            this.curPageOffset = j2;
            this.curPage = bArr;
        }
        this.offsetWithinPage = (int) (j - this.curPageOffset);
        this.fileOffset = j;
    }

    private byte[] readPage() throws IOException {
        int read;
        byte[] bArr = this.lastRemovedCachePage;
        if (bArr != null) {
            this.lastRemovedCachePage = null;
        } else {
            bArr = new byte[this.pageSize];
        }
        int i = 0;
        while (true) {
            int i2 = this.pageSize;
            if (i >= i2 || (read = this.raFile.read(bArr, i, i2 - i)) < 0) {
                break;
            }
            i += read;
        }
        return bArr;
    }

    @Override // java.io.InputStream, com.tom_roush.pdfbox.io.RandomAccessRead
    public int read() throws IOException {
        long j = this.fileOffset;
        if (j >= this.fileLength) {
            return -1;
        }
        if (this.offsetWithinPage == this.pageSize) {
            seek(j);
        }
        this.fileOffset++;
        byte[] bArr = this.curPage;
        int i = this.offsetWithinPage;
        this.offsetWithinPage = i + 1;
        return bArr[i] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream, com.tom_roush.pdfbox.io.RandomAccessRead
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream, com.tom_roush.pdfbox.io.RandomAccessRead
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.fileOffset;
        if (j >= this.fileLength) {
            return -1;
        }
        if (this.offsetWithinPage == this.pageSize) {
            seek(j);
        }
        int min = Math.min(this.pageSize - this.offsetWithinPage, i2);
        long j2 = this.fileLength;
        long j3 = this.fileOffset;
        if (j2 - j3 < this.pageSize) {
            min = Math.min(min, (int) (j2 - j3));
        }
        System.arraycopy(this.curPage, this.offsetWithinPage, bArr, i, min);
        this.offsetWithinPage += min;
        this.fileOffset += min;
        return min;
    }

    @Override // java.io.InputStream, com.tom_roush.pdfbox.io.RandomAccessRead
    public int available() throws IOException {
        return (int) Math.min(this.fileLength - this.fileOffset, 2147483647L);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = this.fileLength;
        long j3 = this.fileOffset;
        if (j2 - j3 < j) {
            j = j2 - j3;
        }
        int i = this.pageSize;
        if (j < i) {
            int i2 = this.offsetWithinPage;
            if (i2 + j <= i) {
                this.offsetWithinPage = (int) (i2 + j);
                this.fileOffset = j3 + j;
                return j;
            }
        }
        seek(j3 + j);
        return j;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        return this.fileLength;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.raFile.close();
        deleteTempFile();
        this.pageCache.clear();
        this.isClosed = true;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.isClosed;
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
        seek(getPosition() - i);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
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

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public boolean isEOF() throws IOException {
        return peek() == -1;
    }
}
