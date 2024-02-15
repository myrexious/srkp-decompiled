package com.tom_roush.pdfbox.filter;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
final class ASCII85InputStream extends FilterInputStream {
    private static final char NEWLINE = '\n';
    private static final char OFFSET = '!';
    private static final char PADDING_U = 'u';
    private static final char RETURN = '\r';
    private static final char SPACE = ' ';
    private static final char TERMINATOR = '~';
    private static final char Z = 'z';
    private byte[] ascii;
    private byte[] b;
    private boolean eof;
    private int index;
    private int n;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) {
        return 0L;
    }

    public ASCII85InputStream(InputStream inputStream) {
        super(inputStream);
        this.index = 0;
        this.n = 0;
        this.eof = false;
        this.ascii = new byte[5];
        this.b = new byte[4];
    }

    /* JADX WARN: Code restructure failed: missing block: B:121:0x0069, code lost:
        r13 = r14.ascii;
        r13[r2] = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x006d, code lost:
        if (r12 != 126) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x006f, code lost:
        r13[r2] = org.tensorflow.lite.schema.BuiltinOptions.DynamicUpdateSliceOptions;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0075, code lost:
        r4 = r2 - 1;
        r14.n = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0079, code lost:
        if (r4 != 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x007b, code lost:
        r14.eof = true;
        r14.ascii = null;
        r14.b = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0081, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0082, code lost:
        if (r2 >= 5) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0084, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0085, code lost:
        if (r2 >= 5) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0087, code lost:
        r14.ascii[r2] = org.tensorflow.lite.schema.BuiltinOptions.DynamicUpdateSliceOptions;
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x008e, code lost:
        r14.eof = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0090, code lost:
        r1 = 0;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0093, code lost:
        if (r4 >= 5) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x0095, code lost:
        r5 = (byte) (r14.ascii[r4] - 33);
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x009c, code lost:
        if (r5 < 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x00a0, code lost:
        if (r5 > 93) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x00a2, code lost:
        r1 = (r1 * 85) + r5;
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x00aa, code lost:
        r14.n = 0;
        r14.eof = true;
        r14.ascii = null;
        r14.b = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x00b9, code lost:
        throw new java.io.IOException("Invalid data in Ascii85 stream");
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x00ba, code lost:
        if (r10 < 0) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x00bc, code lost:
        r14.b[r10] = (byte) (255 & r1);
        r1 = r1 >>> 8;
        r10 = r10 - 1;
     */
    @Override // java.io.FilterInputStream, java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int read() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.filter.ASCII85InputStream.read():int");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!this.eof || this.index < this.n) {
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = this.index;
                if (i4 < this.n) {
                    byte[] bArr2 = this.b;
                    this.index = i4 + 1;
                    bArr[i3 + i] = bArr2[i4];
                } else {
                    int read = read();
                    if (read == -1) {
                        return i3;
                    }
                    bArr[i3 + i] = (byte) read;
                }
            }
            return i2;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.ascii = null;
        this.eof = true;
        this.b = null;
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        throw new IOException("Reset is not supported");
    }
}
