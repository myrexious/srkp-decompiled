package org.apache.commons.imaging.common.bytesource;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import kotlin.UByte;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.io.FileUtils;

/* loaded from: classes2.dex */
public class ByteSourceInputStream extends ByteSource {
    private static final int BLOCK_SIZE = 1024;
    private CacheBlock cacheHead;
    private final InputStream is;
    private byte[] readBuffer;
    private long streamLength;

    public ByteSourceInputStream(InputStream inputStream, String str) {
        super(str);
        this.streamLength = -1L;
        this.is = new BufferedInputStream(inputStream);
    }

    /* loaded from: classes2.dex */
    public class CacheBlock {
        public final byte[] bytes;
        private CacheBlock next;
        private boolean triedNext;

        CacheBlock(byte[] bArr) {
            ByteSourceInputStream.this = r1;
            this.bytes = bArr;
        }

        public CacheBlock getNext() throws IOException {
            CacheBlock cacheBlock = this.next;
            if (cacheBlock != null) {
                return cacheBlock;
            }
            if (this.triedNext) {
                return null;
            }
            this.triedNext = true;
            CacheBlock readBlock = ByteSourceInputStream.this.readBlock();
            this.next = readBlock;
            return readBlock;
        }
    }

    public CacheBlock readBlock() throws IOException {
        if (this.readBuffer == null) {
            this.readBuffer = new byte[1024];
        }
        int read = this.is.read(this.readBuffer);
        if (read < 1) {
            return null;
        }
        if (read < 1024) {
            byte[] bArr = new byte[read];
            System.arraycopy(this.readBuffer, 0, bArr, 0, read);
            return new CacheBlock(bArr);
        }
        byte[] bArr2 = this.readBuffer;
        this.readBuffer = null;
        return new CacheBlock(bArr2);
    }

    public CacheBlock getFirstBlock() throws IOException {
        if (this.cacheHead == null) {
            this.cacheHead = readBlock();
        }
        return this.cacheHead;
    }

    /* loaded from: classes2.dex */
    public class CacheReadingInputStream extends InputStream {
        private CacheBlock block;
        private int blockIndex;
        private boolean readFirst;

        private CacheReadingInputStream() {
            ByteSourceInputStream.this = r1;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.block == null) {
                if (this.readFirst) {
                    return -1;
                }
                this.block = ByteSourceInputStream.this.getFirstBlock();
                this.readFirst = true;
            }
            CacheBlock cacheBlock = this.block;
            if (cacheBlock != null && this.blockIndex >= cacheBlock.bytes.length) {
                this.block = this.block.getNext();
                this.blockIndex = 0;
            }
            CacheBlock cacheBlock2 = this.block;
            if (cacheBlock2 != null && this.blockIndex < cacheBlock2.bytes.length) {
                byte[] bArr = this.block.bytes;
                int i = this.blockIndex;
                this.blockIndex = i + 1;
                return bArr[i] & UByte.MAX_VALUE;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3;
            Objects.requireNonNull(bArr, "array");
            if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (i2 == 0) {
                return 0;
            }
            if (this.block == null) {
                if (this.readFirst) {
                    return -1;
                }
                this.block = ByteSourceInputStream.this.getFirstBlock();
                this.readFirst = true;
            }
            CacheBlock cacheBlock = this.block;
            if (cacheBlock != null && this.blockIndex >= cacheBlock.bytes.length) {
                this.block = this.block.getNext();
                this.blockIndex = 0;
            }
            CacheBlock cacheBlock2 = this.block;
            if (cacheBlock2 != null && this.blockIndex < cacheBlock2.bytes.length) {
                int min = Math.min(i2, this.block.bytes.length - this.blockIndex);
                System.arraycopy(this.block.bytes, this.blockIndex, bArr, i, min);
                this.blockIndex += min;
                return min;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            if (j <= 0) {
                return 0L;
            }
            long j2 = j;
            while (j2 > 0) {
                if (this.block == null) {
                    if (this.readFirst) {
                        return -1L;
                    }
                    this.block = ByteSourceInputStream.this.getFirstBlock();
                    this.readFirst = true;
                }
                CacheBlock cacheBlock = this.block;
                if (cacheBlock != null && this.blockIndex >= cacheBlock.bytes.length) {
                    this.block = this.block.getNext();
                    this.blockIndex = 0;
                }
                CacheBlock cacheBlock2 = this.block;
                if (cacheBlock2 == null || this.blockIndex >= cacheBlock2.bytes.length) {
                    break;
                }
                int min = Math.min((int) Math.min((long) FileUtils.ONE_KB, j2), this.block.bytes.length - this.blockIndex);
                this.blockIndex += min;
                j2 -= min;
            }
            return j - j2;
        }
    }

    @Override // org.apache.commons.imaging.common.bytesource.ByteSource
    public InputStream getInputStream() throws IOException {
        return new CacheReadingInputStream();
    }

    @Override // org.apache.commons.imaging.common.bytesource.ByteSource
    public byte[] getBlock(long j, int i) throws IOException {
        if (j >= 0 && i >= 0) {
            long j2 = i + j;
            if (j2 >= 0 && j2 <= getLength()) {
                InputStream inputStream = getInputStream();
                BinaryFunctions.skipBytes(inputStream, j);
                byte[] bArr = new byte[i];
                int i2 = 0;
                do {
                    int read = inputStream.read(bArr, i2, i - i2);
                    if (read < 1) {
                        throw new IOException("Could not read block.");
                    }
                    i2 += read;
                } while (i2 < i);
                return bArr;
            }
        }
        throw new IOException("Could not read block (block start: " + j + ", block length: " + i + ", data length: " + this.streamLength + ").");
    }

    @Override // org.apache.commons.imaging.common.bytesource.ByteSource
    public long getLength() throws IOException {
        long j = this.streamLength;
        if (j >= 0) {
            return j;
        }
        InputStream inputStream = getInputStream();
        long j2 = 0;
        while (true) {
            long skip = inputStream.skip(FileUtils.ONE_KB);
            if (skip <= 0) {
                this.streamLength = j2;
                return j2;
            }
            j2 += skip;
        }
    }

    @Override // org.apache.commons.imaging.common.bytesource.ByteSource
    public byte[] getAll() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (CacheBlock firstBlock = getFirstBlock(); firstBlock != null; firstBlock = firstBlock.getNext()) {
            byteArrayOutputStream.write(firstBlock.bytes);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // org.apache.commons.imaging.common.bytesource.ByteSource
    public String getDescription() {
        return "Inputstream: '" + getFileName() + OperatorName.SHOW_TEXT_LINE;
    }
}
