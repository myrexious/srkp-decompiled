package com.tom_roush.harmony.javax.imageio.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import kotlin.UByte;

/* loaded from: classes3.dex */
public final class RandomAccessMemoryCache {
    private static final int BLOCK_MASK = 511;
    private static final int BLOCK_SHIFT = 9;
    private static final int BLOCK_SIZE = 512;
    private long length;
    private int firstUndisposed = 0;
    private ArrayList<byte[]> blocks = new ArrayList<>();

    public long length() {
        return this.length;
    }

    public void close() {
        this.blocks.clear();
        this.length = 0L;
    }

    private void grow(long j) {
        int size = (((int) (j >> 9)) - this.blocks.size()) + 1;
        for (int i = 0; i < size; i++) {
            this.blocks.add(new byte[512]);
        }
        this.length = j + 1;
    }

    public void putData(int i, long j) {
        if (j >= this.length) {
            grow(j);
        }
        this.blocks.get((int) (j >> 9))[(int) (j & 511)] = (byte) i;
    }

    public void putData(byte[] bArr, int i, int i2, long j) {
        if (i2 > bArr.length - i || i2 < 0 || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return;
        }
        long j2 = (i2 + j) - 1;
        if (j2 >= this.length) {
            grow(j2);
        }
        while (i2 > 0) {
            int i3 = (int) (511 & j);
            int min = Math.min(512 - i3, i2);
            System.arraycopy(bArr, i, this.blocks.get((int) (j >> 9)), i3, min);
            j += min;
            i2 -= min;
            i += min;
        }
    }

    public int getData(long j) {
        if (j >= this.length) {
            return -1;
        }
        return this.blocks.get((int) (j >> 9))[(int) (j & 511)] & UByte.MAX_VALUE;
    }

    public int getData(byte[] bArr, int i, int i2, long j) {
        if (i2 > bArr.length - i || i2 < 0 || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        long j2 = this.length;
        if (j >= j2) {
            return -1;
        }
        if (i2 + j > j2) {
            i2 = (int) (j2 - j);
        }
        int i3 = (int) (j & 511);
        int min = Math.min(i2, 512 - i3);
        System.arraycopy(this.blocks.get((int) (j >> 9)), i3, bArr, i, min);
        return min;
    }

    public void freeBefore(long j) {
        int i = (int) (j >> 9);
        int i2 = this.firstUndisposed;
        if (i <= i2) {
            return;
        }
        while (i2 < i) {
            this.blocks.set(i2, null);
            i2++;
        }
        this.firstUndisposed = i;
    }

    public int appendData(InputStream inputStream, int i) throws IOException {
        if (i <= 0) {
            return 0;
        }
        long j = this.length;
        grow((i + j) - 1);
        int i2 = (int) (j >> 9);
        int i3 = (int) (j & 511);
        int i4 = 0;
        while (i > 0) {
            byte[] bArr = this.blocks.get(i2);
            int min = Math.min(512 - i3, i);
            i -= min;
            i4 += min;
            while (min > 0) {
                int read = inputStream.read(bArr, i3, min);
                if (read < 0) {
                    this.length -= i - i4;
                    return i4;
                }
                min -= read;
                i3 += read;
            }
            i2++;
            i3 = 0;
        }
        return i4;
    }

    public void getData(OutputStream outputStream, int i, long j) throws IOException {
        if (i + j > this.length) {
            throw new IndexOutOfBoundsException("Argument out of cache");
        }
        int i2 = (int) (j >> 9);
        int i3 = (int) (j & 511);
        if (i2 < this.firstUndisposed) {
            throw new IndexOutOfBoundsException("The requested data are already disposed");
        }
        while (i > 0) {
            int min = Math.min(512 - i3, i);
            outputStream.write(this.blocks.get(i2), i3, min);
            i2++;
            i -= min;
            i3 = 0;
        }
    }
}
