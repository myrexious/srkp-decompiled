package com.tom_roush.harmony.javax.imageio.stream;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public class MemoryCacheImageOutputStream extends ImageOutputStreamImpl {
    OutputStream os;
    RandomAccessMemoryCache ramc = new RandomAccessMemoryCache();

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStreamImpl, com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public boolean isCached() {
        return true;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStreamImpl, com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public boolean isCachedFile() {
        return false;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStreamImpl, com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public boolean isCachedMemory() {
        return true;
    }

    public MemoryCacheImageOutputStream(OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("stream == null!");
        }
        this.os = outputStream;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStreamImpl, com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void write(int i) throws IOException {
        flushBits();
        this.ramc.putData(i, this.streamPos);
        this.streamPos++;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStreamImpl, com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void write(byte[] bArr, int i, int i2) throws IOException {
        flushBits();
        this.ramc.putData(bArr, i, i2, this.streamPos);
        this.streamPos += i2;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStreamImpl, com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public int read() throws IOException {
        this.bitOffset = 0;
        int data = this.ramc.getData(this.streamPos);
        if (data >= 0) {
            this.streamPos++;
        }
        return data;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStreamImpl, com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        this.bitOffset = 0;
        int data = this.ramc.getData(bArr, i, i2, this.streamPos);
        if (data > 0) {
            this.streamPos += data;
        }
        return data;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStreamImpl, com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public long length() {
        return this.ramc.length();
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStreamImpl, com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void close() throws IOException {
        long length = length();
        seek(length);
        flushBefore(length);
        super.close();
        this.ramc.close();
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStreamImpl, com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void flushBefore(long j) throws IOException {
        long flushedPosition = getFlushedPosition();
        super.flushBefore(j);
        long flushedPosition2 = getFlushedPosition();
        this.ramc.getData(this.os, (int) (flushedPosition2 - flushedPosition), flushedPosition);
        this.ramc.freeBefore(flushedPosition2);
        this.os.flush();
    }
}
