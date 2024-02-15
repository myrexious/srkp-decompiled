package com.tom_roush.harmony.javax.imageio.stream;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public abstract class ImageOutputStreamImpl extends ImageInputStreamImpl implements ImageOutputStream {
    private final byte[] buff = new byte[8];

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public abstract void write(int i) throws IOException;

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeBoolean(boolean z) throws IOException {
        write(z ? 1 : 0);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeByte(int i) throws IOException {
        write(i);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeShort(int i) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.buff;
            bArr[0] = (byte) (i >> 8);
            bArr[1] = (byte) i;
        } else {
            byte[] bArr2 = this.buff;
            bArr2[1] = (byte) (i >> 8);
            bArr2[0] = (byte) i;
        }
        write(this.buff, 0, 2);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeChar(int i) throws IOException {
        writeShort(i);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeInt(int i) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.buff;
            bArr[0] = (byte) (i >> 24);
            bArr[1] = (byte) (i >> 16);
            bArr[2] = (byte) (i >> 8);
            bArr[3] = (byte) i;
        } else {
            byte[] bArr2 = this.buff;
            bArr2[3] = (byte) (i >> 24);
            bArr2[2] = (byte) (i >> 16);
            bArr2[1] = (byte) (i >> 8);
            bArr2[0] = (byte) i;
        }
        write(this.buff, 0, 4);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeLong(long j) throws IOException {
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.buff;
            bArr[0] = (byte) (j >> 56);
            bArr[1] = (byte) (j >> 48);
            bArr[2] = (byte) (j >> 40);
            bArr[3] = (byte) (j >> 32);
            bArr[4] = (byte) (j >> 24);
            bArr[5] = (byte) (j >> 16);
            bArr[6] = (byte) (j >> 8);
            bArr[7] = (byte) j;
        } else {
            byte[] bArr2 = this.buff;
            bArr2[7] = (byte) (j >> 56);
            bArr2[6] = (byte) (j >> 48);
            bArr2[5] = (byte) (j >> 40);
            bArr2[4] = (byte) (j >> 32);
            bArr2[3] = (byte) (j >> 24);
            bArr2[2] = (byte) (j >> 16);
            bArr2[1] = (byte) (j >> 8);
            bArr2[0] = (byte) j;
        }
        write(this.buff, 0, 8);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeFloat(float f) throws IOException {
        writeInt(Float.floatToIntBits(f));
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeDouble(double d) throws IOException {
        writeLong(Double.doubleToLongBits(d));
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeBytes(String str) throws IOException {
        write(str.getBytes());
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeChars(String str) throws IOException {
        char[] charArray = str.toCharArray();
        writeChars(charArray, 0, charArray.length);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream, java.io.DataOutput
    public void writeUTF(String str) throws IOException {
        ByteOrder byteOrder = getByteOrder();
        setByteOrder(ByteOrder.BIG_ENDIAN);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new DataOutputStream(byteArrayOutputStream).writeUTF(str);
        write(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
        setByteOrder(byteOrder);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream
    public void writeShorts(short[] sArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > sArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            writeShort(sArr[i + i3]);
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream
    public void writeChars(char[] cArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            writeShort(cArr[i + i3]);
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream
    public void writeInts(int[] iArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > iArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            writeInt(iArr[i + i3]);
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream
    public void writeLongs(long[] jArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > jArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            writeLong(jArr[i + i3]);
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream
    public void writeFloats(float[] fArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > fArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            writeFloat(fArr[i + i3]);
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream
    public void writeDoubles(double[] dArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > dArr.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            writeDouble(dArr[i + i3]);
        }
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream
    public void writeBit(int i) throws IOException {
        writeBits(i, 1);
    }

    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageOutputStream
    public void writeBits(long j, int i) throws IOException {
        int i2;
        int i3;
        checkClosed();
        if (this.bitOffset > 0) {
            int i4 = this.bitOffset;
            int read = read();
            if (read == -1) {
                read = 0;
            } else {
                seek(getStreamPosition() - 1);
            }
            int i5 = 8 - i4;
            if (i >= i5) {
                i -= i5;
                write((int) ((read & (~i3)) | ((j >> i) & ((-1) >>> (32 - i5)))));
            } else {
                int i6 = i4 + i;
                int i7 = 8 - i6;
                write((int) ((read & (~(i2 << i7))) | ((((-1) >>> i) & j) << i7)));
                seek(getStreamPosition() - 1);
                this.bitOffset = i6;
                i = 0;
            }
        }
        while (i > 7) {
            write((int) ((j >> (i - 8)) & 255));
            i -= 8;
        }
        if (i > 0) {
            write((int) ((j << (8 - i)) & 255));
            seek(getStreamPosition() - 1);
            this.bitOffset = i;
        }
    }

    public final void flushBits() throws IOException {
        int i;
        checkClosed();
        if (this.bitOffset == 0) {
            return;
        }
        int i2 = this.bitOffset;
        int read = read();
        if (read == -1) {
            i = 0;
            this.bitOffset = 0;
        } else {
            seek(getStreamPosition() - 1);
            i = ((-1) << (8 - i2)) & read;
        }
        write(i);
    }
}
