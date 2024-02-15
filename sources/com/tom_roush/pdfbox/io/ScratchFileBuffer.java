package com.tom_roush.pdfbox.io;

import android.util.Log;
import com.tom_roush.pdfbox.android.PDFBoxConfig;
import java.io.EOFException;
import java.io.IOException;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class ScratchFileBuffer implements RandomAccess {
    private byte[] currentPage;
    private long currentPageOffset;
    private int currentPagePositionInPageIndexes;
    private ScratchFile pageHandler;
    private final int pageSize;
    private int positionInPage;
    private long size = 0;
    private boolean currentPageContentChanged = false;
    private int[] pageIndexes = new int[16];
    private int pageCount = 0;

    public ScratchFileBuffer(ScratchFile scratchFile) throws IOException {
        scratchFile.checkClosed();
        this.pageHandler = scratchFile;
        this.pageSize = scratchFile.getPageSize();
        addPage();
    }

    private void checkClosed() throws IOException {
        ScratchFile scratchFile = this.pageHandler;
        if (scratchFile == null) {
            throw new IOException("Buffer already closed");
        }
        scratchFile.checkClosed();
    }

    private void addPage() throws IOException {
        int i = this.pageCount;
        int i2 = i + 1;
        int[] iArr = this.pageIndexes;
        if (i2 >= iArr.length) {
            int length = iArr.length * 2;
            if (length < iArr.length) {
                if (iArr.length == Integer.MAX_VALUE) {
                    throw new IOException("Maximum buffer size reached.");
                }
                length = Integer.MAX_VALUE;
            }
            int[] iArr2 = new int[length];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.pageIndexes = iArr2;
        }
        int newPage = this.pageHandler.getNewPage();
        int[] iArr3 = this.pageIndexes;
        int i3 = this.pageCount;
        iArr3[i3] = newPage;
        this.currentPagePositionInPageIndexes = i3;
        int i4 = this.pageSize;
        this.currentPageOffset = i3 * i4;
        this.pageCount = i3 + 1;
        this.currentPage = new byte[i4];
        this.positionInPage = 0;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        return this.size;
    }

    private boolean ensureAvailableBytesInPage(boolean z) throws IOException {
        if (this.positionInPage >= this.pageSize) {
            if (this.currentPageContentChanged) {
                this.pageHandler.writePage(this.pageIndexes[this.currentPagePositionInPageIndexes], this.currentPage);
                this.currentPageContentChanged = false;
            }
            int i = this.currentPagePositionInPageIndexes;
            if (i + 1 < this.pageCount) {
                ScratchFile scratchFile = this.pageHandler;
                int[] iArr = this.pageIndexes;
                int i2 = i + 1;
                this.currentPagePositionInPageIndexes = i2;
                this.currentPage = scratchFile.readPage(iArr[i2]);
                this.currentPageOffset = this.currentPagePositionInPageIndexes * this.pageSize;
                this.positionInPage = 0;
            } else if (!z) {
                return false;
            } else {
                addPage();
            }
        }
        return true;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void write(int i) throws IOException {
        checkClosed();
        ensureAvailableBytesInPage(true);
        byte[] bArr = this.currentPage;
        int i2 = this.positionInPage;
        int i3 = i2 + 1;
        this.positionInPage = i3;
        bArr[i2] = (byte) i;
        this.currentPageContentChanged = true;
        long j = this.currentPageOffset;
        if (i3 + j > this.size) {
            this.size = j + i3;
        }
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void write(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        while (i2 > 0) {
            ensureAvailableBytesInPage(true);
            int min = Math.min(i2, this.pageSize - this.positionInPage);
            System.arraycopy(bArr, i, this.currentPage, this.positionInPage, min);
            this.positionInPage += min;
            this.currentPageContentChanged = true;
            i += min;
            i2 -= min;
        }
        long j = this.currentPageOffset;
        int i3 = this.positionInPage;
        if (i3 + j > this.size) {
            this.size = j + i3;
        }
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public final void clear() throws IOException {
        checkClosed();
        this.pageHandler.markPagesAsFree(this.pageIndexes, 1, this.pageCount - 1);
        this.pageCount = 1;
        if (this.currentPagePositionInPageIndexes > 0) {
            this.currentPage = this.pageHandler.readPage(this.pageIndexes[0]);
            this.currentPagePositionInPageIndexes = 0;
            this.currentPageOffset = 0L;
        }
        this.positionInPage = 0;
        this.size = 0L;
        this.currentPageContentChanged = false;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public long getPosition() throws IOException {
        checkClosed();
        return this.currentPageOffset + this.positionInPage;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public void seek(long j) throws IOException {
        checkClosed();
        if (j > this.size) {
            throw new EOFException();
        }
        if (j < 0) {
            throw new IOException("Negative seek offset: " + j);
        }
        long j2 = this.currentPageOffset;
        if (j >= j2 && j <= this.pageSize + j2) {
            this.positionInPage = (int) (j - j2);
            return;
        }
        if (this.currentPageContentChanged) {
            this.pageHandler.writePage(this.pageIndexes[this.currentPagePositionInPageIndexes], this.currentPage);
            this.currentPageContentChanged = false;
        }
        int i = this.pageSize;
        int i2 = (int) (j / i);
        if (j % i == 0 && j == this.size) {
            i2--;
        }
        this.currentPage = this.pageHandler.readPage(this.pageIndexes[i2]);
        this.currentPagePositionInPageIndexes = i2;
        long j3 = i2 * this.pageSize;
        this.currentPageOffset = j3;
        this.positionInPage = (int) (j - j3);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.pageHandler == null;
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
        seek((this.currentPageOffset + this.positionInPage) - i);
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
        checkClosed();
        return this.currentPageOffset + ((long) this.positionInPage) >= this.size;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int available() throws IOException {
        checkClosed();
        return (int) Math.min(this.size - (this.currentPageOffset + this.positionInPage), 2147483647L);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int read() throws IOException {
        checkClosed();
        if (this.currentPageOffset + this.positionInPage >= this.size) {
            return -1;
        }
        if (!ensureAvailableBytesInPage(false)) {
            throw new IOException("Unexpectedly no bytes available for read in buffer.");
        }
        byte[] bArr = this.currentPage;
        int i = this.positionInPage;
        this.positionInPage = i + 1;
        return bArr[i] & UByte.MAX_VALUE;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int read(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        long j = this.currentPageOffset;
        int i3 = this.positionInPage;
        long j2 = this.size;
        if (i3 + j >= j2) {
            return -1;
        }
        int min = (int) Math.min(i2, j2 - (j + i3));
        int i4 = 0;
        while (min > 0) {
            if (!ensureAvailableBytesInPage(false)) {
                throw new IOException("Unexpectedly no bytes available for read in buffer.");
            }
            int min2 = Math.min(min, this.pageSize - this.positionInPage);
            System.arraycopy(this.currentPage, this.positionInPage, bArr, i, min2);
            this.positionInPage += min2;
            i4 += min2;
            i += min2;
            min -= min2;
        }
        return i4;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ScratchFile scratchFile = this.pageHandler;
        if (scratchFile != null) {
            scratchFile.markPagesAsFree(this.pageIndexes, 0, this.pageCount);
            this.pageHandler = null;
            this.pageIndexes = null;
            this.currentPage = null;
            this.currentPageOffset = 0L;
            this.currentPagePositionInPageIndexes = -1;
            this.positionInPage = 0;
            this.size = 0L;
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (this.pageHandler != null && PDFBoxConfig.isDebugEnabled()) {
                Log.d("PdfBox-Android", "ScratchFileBuffer not closed!");
            }
            close();
        } finally {
            super.finalize();
        }
    }
}
