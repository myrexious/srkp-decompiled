package org.apache.commons.imaging.common.itu_t4;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
class BitInputStreamFlexible extends InputStream {
    private long bytesRead;
    private int cache;
    private int cacheBitsRemaining;
    private final InputStream is;

    public BitInputStreamFlexible(InputStream inputStream) {
        this.is = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.cacheBitsRemaining > 0) {
            throw new IOException("BitInputStream: incomplete bit read");
        }
        return this.is.read();
    }

    public final int readBits(int i) throws IOException {
        if (i <= 32) {
            int i2 = this.cacheBitsRemaining;
            int i3 = 0;
            if (i2 > 0) {
                if (i >= i2) {
                    i -= i2;
                    this.cacheBitsRemaining = 0;
                    i3 = ((1 << i2) - 1) & this.cache;
                } else {
                    int i4 = i2 - i;
                    this.cacheBitsRemaining = i4;
                    i3 = ((1 << i) - 1) & (this.cache >> i4);
                    i = 0;
                }
            }
            while (i >= 8) {
                int read = this.is.read();
                this.cache = read;
                if (read < 0) {
                    throw new IOException("couldn't read bits");
                }
                this.bytesRead++;
                i3 = (read & 255) | (i3 << 8);
                i -= 8;
            }
            if (i > 0) {
                int read2 = this.is.read();
                this.cache = read2;
                if (read2 < 0) {
                    throw new IOException("couldn't read bits");
                }
                this.bytesRead++;
                int i5 = 8 - i;
                this.cacheBitsRemaining = i5;
                return (i3 << i) | (((1 << i) - 1) & (read2 >> i5));
            }
            return i3;
        }
        throw new IOException("BitInputStream: unknown error");
    }

    public void flushCache() {
        this.cacheBitsRemaining = 0;
    }

    public long getBytesRead() {
        return this.bytesRead;
    }
}
