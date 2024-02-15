package com.tom_roush.harmony.javax.imageio.stream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteOrder;
import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes3.dex */
public abstract class ImageInputStreamImpl implements ImageInputStream {
    int currentByte;
    protected ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
    protected long streamPos = 0;
    protected long flushedPos = 0;
    protected int bitOffset = 0;
    private boolean closed = false;
    private final PositionStack posStack = new PositionStack();
    private final PositionStack offsetStack = new PositionStack();
    private final byte[] buff = new byte[8];

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public boolean isCached() {
        return false;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public boolean isCachedFile() {
        return false;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public boolean isCachedMemory() {
        return false;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public long length() {
        return -1L;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public abstract int read() throws IOException;

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public abstract int read(byte[] bArr, int i, int i2) throws IOException;

    public final void checkClosed() throws IOException {
        if (this.closed) {
            throw new IOException("stream is closed");
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void setByteOrder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public ByteOrder getByteOrder() {
        return this.byteOrder;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void readBytes(IIOByteBuffer iIOByteBuffer, int i) throws IOException {
        if (iIOByteBuffer == null) {
            throw new NullPointerException("buffer is NULL");
        }
        byte[] bArr = new byte[i];
        int read = read(bArr, 0, i);
        iIOByteBuffer.setData(bArr);
        iIOByteBuffer.setOffset(0);
        iIOByteBuffer.setLength(read);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public boolean readBoolean() throws IOException {
        int read = read();
        if (read >= 0) {
            return read != 0;
        }
        throw new EOFException("EOF reached");
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public byte readByte() throws IOException {
        int read = read();
        if (read >= 0) {
            return (byte) read;
        }
        throw new EOFException("EOF reached");
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public int readUnsignedByte() throws IOException {
        int read = read();
        if (read >= 0) {
            return read;
        }
        throw new EOFException("EOF reached");
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public short readShort() throws IOException {
        int i;
        byte b;
        if (read(this.buff, 0, 2) < 0) {
            throw new EOFException();
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.buff;
            i = bArr[0] << 8;
            b = bArr[1];
        } else {
            byte[] bArr2 = this.buff;
            i = bArr2[1] << 8;
            b = bArr2[0];
        }
        return (short) ((b & UByte.MAX_VALUE) | i);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public int readUnsignedShort() throws IOException {
        return readShort() & UShort.MAX_VALUE;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public char readChar() throws IOException {
        return (char) readShort();
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public int readInt() throws IOException {
        int i;
        byte b;
        if (read(this.buff, 0, 4) < 0) {
            throw new EOFException();
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.buff;
            i = ((bArr[0] & UByte.MAX_VALUE) << 24) | ((bArr[1] & UByte.MAX_VALUE) << 16) | ((bArr[2] & UByte.MAX_VALUE) << 8);
            b = bArr[3];
        } else {
            byte[] bArr2 = this.buff;
            i = ((bArr2[3] & UByte.MAX_VALUE) << 24) | ((bArr2[2] & UByte.MAX_VALUE) << 16) | ((bArr2[1] & UByte.MAX_VALUE) << 8);
            b = bArr2[0];
        }
        return (b & UByte.MAX_VALUE) | i;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public long readUnsignedInt() throws IOException {
        return readInt() & BodyPartID.bodyIdMax;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public long readLong() throws IOException {
        if (read(this.buff, 0, 8) < 0) {
            throw new EOFException();
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.buff;
            int i = ((bArr[0] & UByte.MAX_VALUE) << 24) | ((bArr[1] & UByte.MAX_VALUE) << 16) | ((bArr[2] & UByte.MAX_VALUE) << 8) | (bArr[3] & UByte.MAX_VALUE);
            int i2 = ((bArr[4] & UByte.MAX_VALUE) << 24) | ((bArr[5] & UByte.MAX_VALUE) << 16);
            return ((i & BodyPartID.bodyIdMax) << 32) | (((bArr[7] & UByte.MAX_VALUE) | ((bArr[6] & UByte.MAX_VALUE) << 8) | i2) & BodyPartID.bodyIdMax);
        }
        byte[] bArr2 = this.buff;
        int i3 = (bArr2[0] & UByte.MAX_VALUE) | ((bArr2[3] & UByte.MAX_VALUE) << 24) | ((bArr2[2] & UByte.MAX_VALUE) << 16) | ((bArr2[1] & UByte.MAX_VALUE) << 8);
        int i4 = (bArr2[5] & UByte.MAX_VALUE) << 8;
        int i5 = bArr2[4] & UByte.MAX_VALUE;
        return (i3 & BodyPartID.bodyIdMax) | (((i5 | (i4 | (((bArr2[7] & UByte.MAX_VALUE) << 24) | ((bArr2[6] & UByte.MAX_VALUE) << 16)))) & BodyPartID.bodyIdMax) << 32);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x002b, code lost:
        r1 = false;
     */
    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String readLine() throws java.io.IOException {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 80
            r0.<init>(r1)
            r1 = 1
        L8:
            int r2 = r7.read()
            r3 = -1
            if (r2 == r3) goto L33
            r1 = 10
            r4 = 0
            if (r2 != r1) goto L15
            goto L2b
        L15:
            r5 = 13
            if (r2 != r5) goto L2d
            int r2 = r7.read()
            if (r2 == r1) goto L2b
            if (r2 == r3) goto L2b
            long r1 = r7.getStreamPosition()
            r5 = 1
            long r1 = r1 - r5
            r7.seek(r1)
        L2b:
            r1 = r4
            goto L33
        L2d:
            char r1 = (char) r2
            r0.append(r1)
            r1 = r4
            goto L8
        L33:
            if (r1 == 0) goto L37
            r0 = 0
            goto L3b
        L37:
            java.lang.String r0 = r0.toString()
        L3b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.harmony.javax.imageio.stream.ImageInputStreamImpl.readLine():java.lang.String");
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public String readUTF() throws IOException {
        ByteOrder byteOrder = getByteOrder();
        setByteOrder(ByteOrder.BIG_ENDIAN);
        int readUnsignedShort = readUnsignedShort();
        char[] cArr = new char[readUnsignedShort];
        readFully(new byte[readUnsignedShort], 0, readUnsignedShort);
        setByteOrder(byteOrder);
        return new DataInputStream(new ByteArrayInputStream(this.buff)).readUTF();
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public void readFully(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        while (i2 > 0) {
            int read = read(bArr, i, i2);
            if (read == -1) {
                throw new EOFException();
            }
            i += read;
            i2 -= read;
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public void readFully(byte[] bArr) throws IOException {
        readFully(bArr, 0, bArr.length);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void readFully(short[] sArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > sArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = readShort();
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void readFully(char[] cArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = readChar();
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void readFully(int[] iArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > iArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = readInt();
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void readFully(long[] jArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > jArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = readLong();
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void readFully(float[] fArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > fArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = readFloat();
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void readFully(double[] dArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > dArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = readFloat();
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public long getStreamPosition() throws IOException {
        checkClosed();
        return this.streamPos;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public int getBitOffset() throws IOException {
        checkClosed();
        return this.bitOffset;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void setBitOffset(int i) throws IOException {
        checkClosed();
        if (i < 0 || i > 7) {
            throw new IllegalArgumentException();
        }
        this.bitOffset = i;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public int readBit() throws IOException {
        checkClosed();
        int i = this.bitOffset;
        int read = read();
        if (read == -1) {
            throw new EOFException();
        }
        int i2 = (i + 1) & 7;
        if (i2 != 0) {
            read >>= 8 - i2;
            seek(getStreamPosition() - 1);
        }
        this.bitOffset = i2;
        return read & 1;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public long readBits(int i) throws IOException {
        checkClosed();
        if (i < 0 || i > 64) {
            throw new IllegalArgumentException();
        }
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 1) | readBit();
        }
        return j;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream, java.io.DataInput
    public int skipBytes(int i) throws IOException {
        return (int) skipBytes(i);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public long skipBytes(long j) throws IOException {
        seek(getStreamPosition() + j);
        return j;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void seek(long j) throws IOException {
        checkClosed();
        if (j < getFlushedPosition()) {
            throw new IllegalArgumentException("trying to seek before flushed pos");
        }
        this.bitOffset = 0;
        this.streamPos = j;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void mark() {
        try {
            this.posStack.push(getStreamPosition());
            this.offsetStack.push(getBitOffset());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Stream marking error");
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void reset() throws IOException {
        if (this.posStack.isEmpty() || this.offsetStack.isEmpty()) {
            return;
        }
        long pop = this.posStack.pop();
        if (pop < this.flushedPos) {
            throw new IOException("marked position lies in the flushed portion of the stream");
        }
        seek(pop);
        setBitOffset((int) this.offsetStack.pop());
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void flushBefore(long j) throws IOException {
        if (j > getStreamPosition()) {
            throw new IndexOutOfBoundsException("Trying to flush outside of current position");
        }
        if (j < this.flushedPos) {
            throw new IndexOutOfBoundsException("Trying to flush within already flushed portion");
        }
        this.flushedPos = j;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void flush() throws IOException {
        flushBefore(getStreamPosition());
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public long getFlushedPosition() {
        return this.flushedPos;
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    public void close() throws IOException {
        checkClosed();
        this.closed = true;
    }

    protected void finalize() throws Throwable {
        if (this.closed) {
            return;
        }
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class PositionStack {
        private static final int SIZE = 10;
        private int pos;
        private long[] values;

        private PositionStack() {
            this.values = new long[10];
            this.pos = 0;
        }

        void push(long j) {
            int i = this.pos;
            if (i >= this.values.length) {
                ensure(i + 1);
            }
            long[] jArr = this.values;
            int i2 = this.pos;
            this.pos = i2 + 1;
            jArr[i2] = j;
        }

        long pop() {
            long[] jArr = this.values;
            int i = this.pos - 1;
            this.pos = i;
            return jArr[i];
        }

        boolean isEmpty() {
            return this.pos == 0;
        }

        private void ensure(int i) {
            long[] jArr = new long[Math.max(this.values.length * 2, i)];
            long[] jArr2 = this.values;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            this.values = jArr;
        }
    }
}
