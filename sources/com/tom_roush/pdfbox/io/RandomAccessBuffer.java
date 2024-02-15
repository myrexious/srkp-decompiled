package com.tom_roush.pdfbox.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class RandomAccessBuffer implements RandomAccess, Cloneable {
    private static final int DEFAULT_CHUNK_SIZE = 1024;
    private List<byte[]> bufferList;
    private int bufferListIndex;
    private int bufferListMaxIndex;
    private int chunkSize;
    private byte[] currentBuffer;
    private int currentBufferPointer;
    private long pointer;
    private long size;

    public RandomAccessBuffer() {
        this(1024);
    }

    private RandomAccessBuffer(int i) {
        this.chunkSize = 1024;
        this.bufferList = null;
        ArrayList arrayList = new ArrayList();
        this.bufferList = arrayList;
        this.chunkSize = i;
        byte[] bArr = new byte[i];
        this.currentBuffer = bArr;
        arrayList.add(bArr);
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = 0L;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
    }

    public RandomAccessBuffer(byte[] bArr) {
        this.chunkSize = 1024;
        this.bufferList = null;
        ArrayList arrayList = new ArrayList(1);
        this.bufferList = arrayList;
        this.chunkSize = bArr.length;
        this.currentBuffer = bArr;
        arrayList.add(bArr);
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = this.chunkSize;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
    }

    public RandomAccessBuffer(InputStream inputStream) throws IOException {
        this();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > -1) {
                write(bArr, 0, read);
            } else {
                seek(0L);
                return;
            }
        }
    }

    /* renamed from: clone */
    public RandomAccessBuffer m265clone() {
        RandomAccessBuffer randomAccessBuffer = new RandomAccessBuffer(this.chunkSize);
        randomAccessBuffer.bufferList = new ArrayList(this.bufferList.size());
        for (byte[] bArr : this.bufferList) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            randomAccessBuffer.bufferList.add(bArr2);
        }
        if (this.currentBuffer != null) {
            List<byte[]> list = randomAccessBuffer.bufferList;
            randomAccessBuffer.currentBuffer = list.get(list.size() - 1);
        } else {
            randomAccessBuffer.currentBuffer = null;
        }
        randomAccessBuffer.pointer = this.pointer;
        randomAccessBuffer.currentBufferPointer = this.currentBufferPointer;
        randomAccessBuffer.size = this.size;
        randomAccessBuffer.bufferListIndex = this.bufferListIndex;
        randomAccessBuffer.bufferListMaxIndex = this.bufferListMaxIndex;
        return randomAccessBuffer;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.currentBuffer = null;
        this.bufferList.clear();
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = 0L;
        this.bufferListIndex = 0;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void clear() {
        this.bufferList.clear();
        byte[] bArr = new byte[this.chunkSize];
        this.currentBuffer = bArr;
        this.bufferList.add(bArr);
        this.pointer = 0L;
        this.currentBufferPointer = 0;
        this.size = 0L;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public void seek(long j) throws IOException {
        checkClosed();
        if (j < 0) {
            throw new IOException("Invalid position " + j);
        }
        this.pointer = j;
        if (j < this.size) {
            int i = this.chunkSize;
            int i2 = (int) (j / i);
            this.bufferListIndex = i2;
            this.currentBufferPointer = (int) (j % i);
            this.currentBuffer = this.bufferList.get(i2);
            return;
        }
        int i3 = this.bufferListMaxIndex;
        this.bufferListIndex = i3;
        this.currentBuffer = this.bufferList.get(i3);
        this.currentBufferPointer = (int) (this.size % this.chunkSize);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public long getPosition() throws IOException {
        checkClosed();
        return this.pointer;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int read() throws IOException {
        checkClosed();
        if (this.pointer >= this.size) {
            return -1;
        }
        if (this.currentBufferPointer >= this.chunkSize) {
            int i = this.bufferListIndex;
            if (i >= this.bufferListMaxIndex) {
                return -1;
            }
            List<byte[]> list = this.bufferList;
            int i2 = i + 1;
            this.bufferListIndex = i2;
            this.currentBuffer = list.get(i2);
            this.currentBufferPointer = 0;
        }
        this.pointer++;
        byte[] bArr = this.currentBuffer;
        int i3 = this.currentBufferPointer;
        this.currentBufferPointer = i3 + 1;
        return bArr[i3] & UByte.MAX_VALUE;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int read(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        if (this.pointer >= this.size) {
            return -1;
        }
        int readRemainingBytes = readRemainingBytes(bArr, i, i2);
        while (readRemainingBytes < i2 && available() > 0) {
            readRemainingBytes += readRemainingBytes(bArr, i + readRemainingBytes, i2 - readRemainingBytes);
            if (this.currentBufferPointer == this.chunkSize) {
                nextBuffer();
            }
        }
        return readRemainingBytes;
    }

    private int readRemainingBytes(byte[] bArr, int i, int i2) {
        int min = (int) Math.min(i2, this.size - this.pointer);
        int i3 = this.chunkSize;
        int i4 = this.currentBufferPointer;
        int i5 = i3 - i4;
        if (i5 == 0) {
            return 0;
        }
        if (min >= i5) {
            System.arraycopy(this.currentBuffer, i4, bArr, i, i5);
            this.currentBufferPointer += i5;
            this.pointer += i5;
            return i5;
        }
        System.arraycopy(this.currentBuffer, i4, bArr, i, min);
        this.currentBufferPointer += min;
        this.pointer += min;
        return min;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        checkClosed();
        return this.size;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void write(int i) throws IOException {
        checkClosed();
        int i2 = this.currentBufferPointer;
        int i3 = this.chunkSize;
        if (i2 >= i3) {
            if (this.pointer + i3 >= 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            expandBuffer();
        }
        byte[] bArr = this.currentBuffer;
        int i4 = this.currentBufferPointer;
        int i5 = i4 + 1;
        this.currentBufferPointer = i5;
        bArr[i4] = (byte) i;
        long j = this.pointer + 1;
        this.pointer = j;
        if (j > this.size) {
            this.size = j;
        }
        int i6 = this.chunkSize;
        if (i5 >= i6) {
            if (j + i6 >= 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            expandBuffer();
        }
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessWrite
    public void write(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        long j = i2;
        long j2 = this.pointer + j;
        int i3 = this.chunkSize;
        int i4 = this.currentBufferPointer;
        int i5 = i3 - i4;
        if (i2 < i5) {
            System.arraycopy(bArr, i, this.currentBuffer, i4, i2);
            this.currentBufferPointer += i2;
        } else if (j2 > 2147483647L) {
            throw new IOException("RandomAccessBuffer overflow");
        } else {
            System.arraycopy(bArr, i, this.currentBuffer, i4, i5);
            int i6 = i + i5;
            long j3 = i2 - i5;
            int i7 = ((int) j3) / this.chunkSize;
            for (int i8 = 0; i8 < i7; i8++) {
                expandBuffer();
                System.arraycopy(bArr, i6, this.currentBuffer, this.currentBufferPointer, this.chunkSize);
                i6 += this.chunkSize;
            }
            long j4 = j3 - (i7 * this.chunkSize);
            int i9 = (j4 > 0L ? 1 : (j4 == 0L ? 0 : -1));
            if (i9 >= 0) {
                expandBuffer();
                if (i9 > 0) {
                    System.arraycopy(bArr, i6, this.currentBuffer, this.currentBufferPointer, (int) j4);
                }
                this.currentBufferPointer = (int) j4;
            }
        }
        long j5 = this.pointer + j;
        this.pointer = j5;
        if (j5 > this.size) {
            this.size = j5;
        }
    }

    private void expandBuffer() throws IOException {
        if (this.bufferListMaxIndex > this.bufferListIndex) {
            nextBuffer();
            return;
        }
        byte[] bArr = new byte[this.chunkSize];
        this.currentBuffer = bArr;
        this.bufferList.add(bArr);
        this.currentBufferPointer = 0;
        this.bufferListMaxIndex++;
        this.bufferListIndex++;
    }

    private void nextBuffer() throws IOException {
        int i = this.bufferListIndex;
        if (i == this.bufferListMaxIndex) {
            throw new IOException("No more chunks available, end of buffer reached");
        }
        this.currentBufferPointer = 0;
        List<byte[]> list = this.bufferList;
        int i2 = i + 1;
        this.bufferListIndex = i2;
        this.currentBuffer = list.get(i2);
    }

    private void checkClosed() throws IOException {
        if (this.currentBuffer == null) {
            throw new IOException("RandomAccessBuffer already closed");
        }
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.currentBuffer == null;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public boolean isEOF() throws IOException {
        checkClosed();
        return this.pointer >= this.size;
    }

    @Override // com.tom_roush.pdfbox.io.RandomAccessRead
    public int available() throws IOException {
        return (int) Math.min(length() - getPosition(), 2147483647L);
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
        checkClosed();
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
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }
}
