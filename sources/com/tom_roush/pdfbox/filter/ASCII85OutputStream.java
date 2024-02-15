package com.tom_roush.pdfbox.filter;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.UByte;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
final class ASCII85OutputStream extends FilterOutputStream {
    private static final char NEWLINE = '\n';
    private static final char OFFSET = '!';
    private static final char Z = 'z';
    private int count;
    private boolean flushed;
    private byte[] indata;
    private int lineBreak;
    private int maxline;
    private byte[] outdata;
    private char terminator;

    public ASCII85OutputStream(OutputStream outputStream) {
        super(outputStream);
        this.lineBreak = 72;
        this.maxline = 72;
        this.count = 0;
        this.indata = new byte[4];
        this.outdata = new byte[5];
        this.flushed = true;
        this.terminator = '~';
    }

    public void setTerminator(char c) {
        if (c < 'v' || c > '~' || c == 'z') {
            throw new IllegalArgumentException("Terminator must be 118-126 excluding z");
        }
        this.terminator = c;
    }

    public char getTerminator() {
        return this.terminator;
    }

    public void setLineLength(int i) {
        if (this.lineBreak > i) {
            this.lineBreak = i;
        }
        this.maxline = i;
    }

    public int getLineLength() {
        return this.maxline;
    }

    private void transformASCII85() {
        byte[] bArr = this.indata;
        long j = ((bArr[3] & UByte.MAX_VALUE) | (((bArr[0] << 8) | (bArr[1] & UByte.MAX_VALUE)) << 16) | ((bArr[2] & UByte.MAX_VALUE) << 8)) & BodyPartID.bodyIdMax;
        if (j == 0) {
            byte[] bArr2 = this.outdata;
            bArr2[0] = 122;
            bArr2[1] = 0;
            return;
        }
        long j2 = j / 52200625;
        byte[] bArr3 = this.outdata;
        bArr3[0] = (byte) (j2 + 33);
        long j3 = j - ((((j2 * 85) * 85) * 85) * 85);
        long j4 = j3 / 614125;
        bArr3[1] = (byte) (j4 + 33);
        long j5 = j3 - (((j4 * 85) * 85) * 85);
        long j6 = j5 / 7225;
        bArr3[2] = (byte) (j6 + 33);
        long j7 = j5 - ((j6 * 85) * 85);
        bArr3[3] = (byte) ((j7 / 85) + 33);
        bArr3[4] = (byte) ((j7 % 85) + 33);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        this.flushed = false;
        byte[] bArr = this.indata;
        int i2 = this.count;
        int i3 = i2 + 1;
        this.count = i3;
        bArr[i2] = (byte) i;
        if (i3 < 4) {
            return;
        }
        transformASCII85();
        for (int i4 = 0; i4 < 5 && this.outdata[i4] != 0; i4++) {
            this.out.write(this.outdata[i4]);
            int i5 = this.lineBreak - 1;
            this.lineBreak = i5;
            if (i5 == 0) {
                this.out.write(10);
                this.lineBreak = this.maxline;
            }
        }
        this.count = 0;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.flushed) {
            return;
        }
        int i = this.count;
        if (i > 0) {
            while (i < 4) {
                this.indata[i] = 0;
                i++;
            }
            transformASCII85();
            if (this.outdata[0] == 122) {
                for (int i2 = 0; i2 < 5; i2++) {
                    this.outdata[i2] = BuiltinOptions.ExpOptions;
                }
            }
            for (int i3 = 0; i3 < this.count + 1; i3++) {
                this.out.write(this.outdata[i3]);
                int i4 = this.lineBreak - 1;
                this.lineBreak = i4;
                if (i4 == 0) {
                    this.out.write(10);
                    this.lineBreak = this.maxline;
                }
            }
        }
        int i5 = this.lineBreak - 1;
        this.lineBreak = i5;
        if (i5 == 0) {
            this.out.write(10);
        }
        this.out.write(this.terminator);
        this.out.write(62);
        this.out.write(10);
        this.count = 0;
        this.lineBreak = this.maxline;
        this.flushed = true;
        super.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
            super.close();
        } finally {
            this.outdata = null;
            this.indata = null;
        }
    }
}
