package com.tom_roush.harmony.javax.imageio.stream;

import java.io.DataOutput;
import java.io.IOException;

/* loaded from: classes3.dex */
public interface ImageOutputStream extends DataOutput, ImageInputStream {
    @Override // com.tom_roush.harmony.javax.imageio.stream.ImageInputStream
    void flushBefore(long j) throws IOException;

    @Override // java.io.DataOutput
    void write(int i) throws IOException;

    @Override // java.io.DataOutput
    void write(byte[] bArr) throws IOException;

    @Override // java.io.DataOutput
    void write(byte[] bArr, int i, int i2) throws IOException;

    void writeBit(int i) throws IOException;

    void writeBits(long j, int i) throws IOException;

    @Override // java.io.DataOutput
    void writeBoolean(boolean z) throws IOException;

    @Override // java.io.DataOutput
    void writeByte(int i) throws IOException;

    @Override // java.io.DataOutput
    void writeBytes(String str) throws IOException;

    @Override // java.io.DataOutput
    void writeChar(int i) throws IOException;

    @Override // java.io.DataOutput
    void writeChars(String str) throws IOException;

    void writeChars(char[] cArr, int i, int i2) throws IOException;

    @Override // java.io.DataOutput
    void writeDouble(double d) throws IOException;

    void writeDoubles(double[] dArr, int i, int i2) throws IOException;

    @Override // java.io.DataOutput
    void writeFloat(float f) throws IOException;

    void writeFloats(float[] fArr, int i, int i2) throws IOException;

    @Override // java.io.DataOutput
    void writeInt(int i) throws IOException;

    void writeInts(int[] iArr, int i, int i2) throws IOException;

    @Override // java.io.DataOutput
    void writeLong(long j) throws IOException;

    void writeLongs(long[] jArr, int i, int i2) throws IOException;

    @Override // java.io.DataOutput
    void writeShort(int i) throws IOException;

    void writeShorts(short[] sArr, int i, int i2) throws IOException;

    @Override // java.io.DataOutput
    void writeUTF(String str) throws IOException;
}
