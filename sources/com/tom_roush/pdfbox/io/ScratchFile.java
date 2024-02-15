package com.tom_roush.pdfbox.io;

import android.util.Log;
import com.tom_roush.pdfbox.android.PDFBoxConfig;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

/* loaded from: classes3.dex */
public class ScratchFile implements Closeable {
    private static final int ENLARGE_PAGE_COUNT = 16;
    private static final int INIT_UNRESTRICTED_MAINMEM_PAGECOUNT = 100000;
    private static final int PAGE_SIZE = 4096;
    private File file;
    private final BitSet freePages;
    private final int inMemoryMaxPageCount;
    private volatile byte[][] inMemoryPages;
    private final Object ioLock;
    private volatile boolean isClosed;
    private final boolean maxMainMemoryIsRestricted;
    private final int maxPageCount;
    private volatile int pageCount;
    private java.io.RandomAccessFile raf;
    private final File scratchFileDirectory;
    private final boolean useScratchFile;

    public int getPageSize() {
        return 4096;
    }

    public ScratchFile(File file) throws IOException {
        this(MemoryUsageSetting.setupTempFileOnly().setTempDir(file));
    }

    public ScratchFile(MemoryUsageSetting memoryUsageSetting) throws IOException {
        this.ioLock = new Object();
        this.pageCount = 0;
        BitSet bitSet = new BitSet();
        this.freePages = bitSet;
        this.isClosed = false;
        boolean z = true;
        boolean z2 = !memoryUsageSetting.useMainMemory() || memoryUsageSetting.isMainMemoryRestricted();
        this.maxMainMemoryIsRestricted = z2;
        z = (z2 && memoryUsageSetting.useTempFile()) ? z : false;
        this.useScratchFile = z;
        File tempDir = z ? memoryUsageSetting.getTempDir() : null;
        this.scratchFileDirectory = tempDir;
        if (tempDir != null && !tempDir.isDirectory()) {
            throw new IOException("Scratch file directory does not exist: " + tempDir);
        }
        int i = Integer.MAX_VALUE;
        this.maxPageCount = memoryUsageSetting.isStorageRestricted() ? (int) Math.min(2147483647L, memoryUsageSetting.getMaxStorageBytes() / 4096) : Integer.MAX_VALUE;
        if (!memoryUsageSetting.useMainMemory()) {
            i = 0;
        } else if (memoryUsageSetting.isMainMemoryRestricted()) {
            i = (int) Math.min(2147483647L, memoryUsageSetting.getMaxMainMemoryBytes() / 4096);
        }
        this.inMemoryMaxPageCount = i;
        this.inMemoryPages = new byte[z2 ? i : INIT_UNRESTRICTED_MAINMEM_PAGECOUNT];
        bitSet.set(0, this.inMemoryPages.length);
    }

    public static ScratchFile getMainMemoryOnlyInstance() {
        try {
            return new ScratchFile(MemoryUsageSetting.setupMainMemoryOnly());
        } catch (IOException e) {
            Log.e("PdfBox-Android", "Unexpected exception occurred creating main memory scratch file instance: " + e.getMessage());
            return null;
        }
    }

    public int getNewPage() throws IOException {
        int nextSetBit;
        synchronized (this.freePages) {
            nextSetBit = this.freePages.nextSetBit(0);
            if (nextSetBit < 0) {
                enlarge();
                nextSetBit = this.freePages.nextSetBit(0);
                if (nextSetBit < 0) {
                    throw new IOException("Maximum allowed scratch file memory exceeded.");
                }
            }
            this.freePages.clear(nextSetBit);
            if (nextSetBit >= this.pageCount) {
                this.pageCount = nextSetBit + 1;
            }
        }
        return nextSetBit;
    }

    private void enlarge() throws IOException {
        synchronized (this.ioLock) {
            checkClosed();
            if (this.pageCount >= this.maxPageCount) {
                return;
            }
            if (this.useScratchFile) {
                if (this.raf == null) {
                    this.file = File.createTempFile("PDFBox", ".tmp", this.scratchFileDirectory);
                    try {
                        this.raf = new java.io.RandomAccessFile(this.file, "rw");
                    } catch (IOException e) {
                        if (!this.file.delete()) {
                            Log.w("PdfBox-Android", "Error deleting scratch file: " + this.file.getAbsolutePath());
                        }
                        throw e;
                    }
                }
                long length = this.raf.length();
                long j = (this.pageCount - this.inMemoryMaxPageCount) * 4096;
                if (j != length) {
                    throw new IOException("Expected scratch file size of " + j + " but found " + length + " in file " + this.file);
                }
                if (this.pageCount + 16 > this.pageCount) {
                    if (PDFBoxConfig.isDebugEnabled()) {
                        Log.d("PdfBox-Android", "file: " + this.file);
                        Log.d("PdfBox-Android", "fileLen before: " + length + ", raf length: " + this.raf.length() + ", file length: " + this.file.length());
                    }
                    long j2 = length + 65536;
                    this.raf.setLength(j2);
                    if (PDFBoxConfig.isDebugEnabled()) {
                        Log.d("PdfBox-Android", "fileLen after1: " + j2 + ", raf length: " + this.raf.length() + ", file length: " + this.file.length());
                    }
                    if (j2 != this.raf.length()) {
                        long filePointer = this.raf.getFilePointer();
                        this.raf.seek(j2 - 1);
                        this.raf.write(0);
                        this.raf.seek(filePointer);
                        Log.d("PdfBox-Android", "fileLen after2:  " + j2 + ", raf length: " + this.raf.length() + ", file length: " + this.file.length());
                    }
                    this.freePages.set(this.pageCount, this.pageCount + 16);
                }
            } else if (!this.maxMainMemoryIsRestricted) {
                int length2 = this.inMemoryPages.length;
                int min = (int) Math.min(length2 * 2, 2147483647L);
                if (min > length2) {
                    byte[][] bArr = new byte[min];
                    System.arraycopy(this.inMemoryPages, 0, bArr, 0, length2);
                    this.inMemoryPages = bArr;
                    this.freePages.set(length2, min);
                }
            }
        }
    }

    public byte[] readPage(int i) throws IOException {
        byte[] bArr;
        if (i < 0 || i >= this.pageCount) {
            checkClosed();
            throw new IOException("Page index out of range: " + i + ". Max value: " + (this.pageCount - 1));
        } else if (i < this.inMemoryMaxPageCount) {
            byte[] bArr2 = this.inMemoryPages[i];
            if (bArr2 != null) {
                return bArr2;
            }
            checkClosed();
            throw new IOException("Requested page with index " + i + " was not written before.");
        } else {
            synchronized (this.ioLock) {
                java.io.RandomAccessFile randomAccessFile = this.raf;
                if (randomAccessFile == null) {
                    checkClosed();
                    throw new IOException("Missing scratch file to read page with index " + i + " from.");
                }
                bArr = new byte[4096];
                randomAccessFile.seek((i - this.inMemoryMaxPageCount) * 4096);
                this.raf.readFully(bArr);
            }
            return bArr;
        }
    }

    public void writePage(int i, byte[] bArr) throws IOException {
        if (i < 0 || i >= this.pageCount) {
            checkClosed();
            throw new IOException("Page index out of range: " + i + ". Max value: " + (this.pageCount - 1));
        } else if (bArr.length != 4096) {
            throw new IOException("Wrong page size to write: " + bArr.length + ". Expected: 4096");
        } else {
            if (i < this.inMemoryMaxPageCount) {
                if (this.maxMainMemoryIsRestricted) {
                    this.inMemoryPages[i] = bArr;
                } else {
                    synchronized (this.ioLock) {
                        this.inMemoryPages[i] = bArr;
                    }
                }
                checkClosed();
                return;
            }
            synchronized (this.ioLock) {
                checkClosed();
                this.raf.seek((i - this.inMemoryMaxPageCount) * 4096);
                this.raf.write(bArr);
            }
        }
    }

    public void checkClosed() throws IOException {
        if (this.isClosed) {
            throw new IOException("Scratch file already closed");
        }
    }

    public RandomAccess createBuffer() throws IOException {
        return new ScratchFileBuffer(this);
    }

    public RandomAccess createBuffer(InputStream inputStream) throws IOException {
        ScratchFileBuffer scratchFileBuffer = new ScratchFileBuffer(this);
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > -1) {
                scratchFileBuffer.write(bArr, 0, read);
            } else {
                scratchFileBuffer.seek(0L);
                return scratchFileBuffer;
            }
        }
    }

    public void markPagesAsFree(int[] iArr, int i, int i2) {
        synchronized (this.freePages) {
            while (i < i2) {
                int i3 = iArr[i];
                if (i3 >= 0 && i3 < this.pageCount && !this.freePages.get(i3)) {
                    this.freePages.set(i3);
                    if (i3 < this.inMemoryMaxPageCount) {
                        this.inMemoryPages[i3] = null;
                    }
                }
                i++;
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.ioLock) {
            if (this.isClosed) {
                return;
            }
            this.isClosed = true;
            java.io.RandomAccessFile randomAccessFile = this.raf;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e = e;
                }
            }
            e = null;
            File file = this.file;
            if (file != null && !file.delete() && this.file.exists() && e == null) {
                e = new IOException("Error deleting scratch file: " + this.file.getAbsolutePath());
            }
            synchronized (this.freePages) {
                this.freePages.clear();
                this.pageCount = 0;
            }
            if (e != null) {
                throw e;
            }
        }
    }
}
