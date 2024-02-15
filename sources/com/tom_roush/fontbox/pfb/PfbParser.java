package com.tom_roush.fontbox.pfb;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class PfbParser {
    private static final int ASCII_MARKER = 1;
    private static final int BINARY_MARKER = 2;
    private static final int BUFFER_SIZE = 65535;
    private static final int PFB_HEADER_LENGTH = 18;
    private static final int[] PFB_RECORDS = {1, 2, 1};
    private static final int START_MARKER = 128;
    private int[] lengths;
    private byte[] pfbdata;

    public PfbParser(String str) throws IOException {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(str), 65535);
        } catch (Throwable th) {
            th = th;
        }
        try {
            parsePfb(readFully(bufferedInputStream));
            bufferedInputStream.close();
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            throw th;
        }
    }

    public PfbParser(InputStream inputStream) throws IOException {
        parsePfb(readFully(inputStream));
    }

    public PfbParser(byte[] bArr) throws IOException {
        parsePfb(bArr);
    }

    private void parsePfb(byte[] bArr) throws IOException {
        if (bArr.length < 18) {
            throw new IOException("PFB header missing");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        this.pfbdata = new byte[bArr.length - 18];
        this.lengths = new int[PFB_RECORDS.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = PFB_RECORDS;
            if (i >= iArr.length) {
                return;
            }
            if (byteArrayInputStream.read() != 128) {
                throw new IOException("Start marker missing");
            }
            if (byteArrayInputStream.read() != iArr[i]) {
                throw new IOException("Incorrect record type");
            }
            int read = byteArrayInputStream.read() + (byteArrayInputStream.read() << 8) + (byteArrayInputStream.read() << 16) + (byteArrayInputStream.read() << 24);
            if (read < 0) {
                throw new IOException("PFB record size is negative: " + read);
            }
            this.lengths[i] = read;
            byte[] bArr2 = this.pfbdata;
            if (i2 >= bArr2.length) {
                throw new EOFException("attempted to read past EOF");
            }
            if (read > bArr2.length - i2) {
                throw new EOFException("attempted to read " + read + " bytes at position " + i2 + " into array of size " + this.pfbdata.length + ", but only space for " + (this.pfbdata.length - i2) + " bytes left");
            }
            int read2 = byteArrayInputStream.read(bArr2, i2, read);
            if (read2 < 0) {
                throw new EOFException();
            }
            i2 += read2;
            i++;
        }
    }

    private byte[] readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[65535];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public int[] getLengths() {
        return this.lengths;
    }

    public byte[] getPfbdata() {
        return this.pfbdata;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.pfbdata);
    }

    public int size() {
        return this.pfbdata.length;
    }

    public byte[] getSegment1() {
        return Arrays.copyOfRange(this.pfbdata, 0, this.lengths[0]);
    }

    public byte[] getSegment2() {
        byte[] bArr = this.pfbdata;
        int[] iArr = this.lengths;
        int i = iArr[0];
        return Arrays.copyOfRange(bArr, i, iArr[1] + i);
    }
}
