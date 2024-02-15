package org.apache.commons.imaging.common.bytesource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class ByteSourceArray extends ByteSource {
    private final byte[] bytes;

    public ByteSourceArray(String str, byte[] bArr) {
        super(str);
        this.bytes = bArr;
    }

    public ByteSourceArray(byte[] bArr) {
        this(null, bArr);
    }

    @Override // org.apache.commons.imaging.common.bytesource.ByteSource
    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.bytes);
    }

    @Override // org.apache.commons.imaging.common.bytesource.ByteSource
    public byte[] getBlock(long j, int i) throws IOException {
        int i2;
        int i3 = (int) j;
        if (i3 >= 0 && i >= 0 && (i2 = i3 + i) >= 0) {
            byte[] bArr = this.bytes;
            if (i2 <= bArr.length) {
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, i3, bArr2, 0, i);
                return bArr2;
            }
        }
        throw new IOException("Could not read block (block start: " + i3 + ", block length: " + i + ", data length: " + this.bytes.length + ").");
    }

    @Override // org.apache.commons.imaging.common.bytesource.ByteSource
    public long getLength() {
        return this.bytes.length;
    }

    @Override // org.apache.commons.imaging.common.bytesource.ByteSource
    public byte[] getAll() throws IOException {
        return this.bytes;
    }

    @Override // org.apache.commons.imaging.common.bytesource.ByteSource
    public String getDescription() {
        return this.bytes.length + " byte array";
    }
}
