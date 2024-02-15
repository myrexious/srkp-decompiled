package com.tom_roush.fontbox.ttf;

import androidx.core.view.InputDeviceCompat;
import com.tom_roush.fontbox.util.Charsets;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: classes3.dex */
abstract class TTFDataStream implements Closeable {
    public abstract long getCurrentPosition() throws IOException;

    public abstract InputStream getOriginalData() throws IOException;

    public abstract long getOriginalDataSize();

    public abstract int read() throws IOException;

    public abstract int read(byte[] bArr, int i, int i2) throws IOException;

    public abstract long readLong() throws IOException;

    public abstract short readSignedShort() throws IOException;

    public abstract int readUnsignedShort() throws IOException;

    public abstract void seek(long j) throws IOException;

    public float read32Fixed() throws IOException {
        return readSignedShort() + (readUnsignedShort() / 65536.0f);
    }

    public String readString(int i) throws IOException {
        return readString(i, Charsets.ISO_8859_1);
    }

    public String readString(int i, String str) throws IOException {
        return new String(read(i), str);
    }

    public String readString(int i, Charset charset) throws IOException {
        return new String(read(i), charset);
    }

    public int readSignedByte() throws IOException {
        int read = read();
        return read <= 127 ? read : read + InputDeviceCompat.SOURCE_ANY;
    }

    public int readUnsignedByte() throws IOException {
        int read = read();
        if (read != -1) {
            return read;
        }
        throw new EOFException("premature EOF");
    }

    public long readUnsignedInt() throws IOException {
        long read = read();
        long read2 = read();
        long read3 = read();
        long read4 = read();
        if (read4 >= 0) {
            return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
        }
        throw new EOFException();
    }

    public int[] readUnsignedByteArray(int i) throws IOException {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = read();
        }
        return iArr;
    }

    public int[] readUnsignedShortArray(int i) throws IOException {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readUnsignedShort();
        }
        return iArr;
    }

    public Calendar readInternationalDate() throws IOException {
        long readLong = readLong();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(1904, 0, 1, 0, 0, 0);
        calendar.set(14, 0);
        calendar.setTimeInMillis(calendar.getTimeInMillis() + (readLong * 1000));
        return calendar;
    }

    public String readTag() throws IOException {
        return new String(read(4), Charsets.US_ASCII);
    }

    public byte[] read(int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException("Unexpected end of TTF stream reached");
    }
}
