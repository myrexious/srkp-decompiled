package org.apache.commons.imaging.common.mylzw;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public class MyBitInputStream extends InputStream {
    private int bitCache;
    private int bitsInCache;
    private final ByteOrder byteOrder;
    private long bytesRead;
    private final InputStream is;
    private boolean tiffLZWMode;

    public MyBitInputStream(InputStream inputStream, ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
        this.is = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return readBits(8);
    }

    public void setTiffLZWMode() {
        this.tiffLZWMode = true;
    }

    public int readBits(int i) throws IOException {
        int i2;
        while (this.bitsInCache < i) {
            int read = this.is.read();
            if (read < 0) {
                return this.tiffLZWMode ? 257 : -1;
            }
            int i3 = read & 255;
            if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
                this.bitCache = i3 | (this.bitCache << 8);
            } else {
                this.bitCache = (i3 << this.bitsInCache) | this.bitCache;
            }
            this.bytesRead++;
            this.bitsInCache += 8;
        }
        int i4 = (1 << i) - 1;
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            i2 = i4 & (this.bitCache >> (this.bitsInCache - i));
        } else {
            int i5 = this.bitCache;
            i2 = i4 & i5;
            this.bitCache = i5 >> i;
        }
        int i6 = this.bitsInCache - i;
        this.bitsInCache = i6;
        this.bitCache = ((1 << i6) - 1) & this.bitCache;
        return i2;
    }

    public void flushCache() {
        this.bitsInCache = 0;
        this.bitCache = 0;
    }

    public long getBytesRead() {
        return this.bytesRead;
    }
}
