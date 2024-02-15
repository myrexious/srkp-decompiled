package com.tom_roush.pdfbox.filter;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
final class CCITTFaxEncoderStream extends OutputStream {
    private static final Code[] BLACK_NONTERMINATING_CODES;
    private static final Code[] BLACK_TERMINATING_CODES;
    private int[] changesCurrentRow;
    private int[] changesReferenceRow;
    private final int columns;
    private final int fillOrder;
    private final byte[] inputBuffer;
    private final int inputBufferLength;
    private final int rows;
    private final OutputStream stream;
    private static final Code[] WHITE_TERMINATING_CODES = new Code[64];
    private static final Code[] WHITE_NONTERMINATING_CODES = new Code[40];
    private int currentBufferLength = 0;
    private int currentRow = 0;
    private int changesCurrentRowLength = 0;
    private int changesReferenceRowLength = 0;
    private byte outputBuffer = 0;
    private byte outputBufferBitLength = 0;

    public CCITTFaxEncoderStream(OutputStream outputStream, int i, int i2, int i3) {
        this.stream = outputStream;
        this.columns = i;
        this.rows = i2;
        this.fillOrder = i3;
        this.changesReferenceRow = new int[i];
        this.changesCurrentRow = new int[i];
        int i4 = (i + 7) / 8;
        this.inputBufferLength = i4;
        this.inputBuffer = new byte[i4];
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.inputBuffer;
        int i2 = this.currentBufferLength;
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        this.currentBufferLength = i3;
        if (i3 == this.inputBufferLength) {
            encodeRow();
            this.currentBufferLength = 0;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.stream.flush();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.stream.close();
    }

    private void encodeRow() throws IOException {
        this.currentRow++;
        int[] iArr = this.changesReferenceRow;
        this.changesReferenceRow = this.changesCurrentRow;
        this.changesCurrentRow = iArr;
        this.changesReferenceRowLength = this.changesCurrentRowLength;
        this.changesCurrentRowLength = 0;
        boolean z = true;
        for (int i = 0; i < this.columns; i++) {
            if ((((this.inputBuffer[i / 8] >> (7 - (i % 8))) & 1) == 1) == z) {
                int[] iArr2 = this.changesCurrentRow;
                int i2 = this.changesCurrentRowLength;
                iArr2[i2] = i;
                this.changesCurrentRowLength = i2 + 1;
                z = !z;
            }
        }
        encodeRowType6();
        if (this.currentRow == this.rows) {
            writeEOL();
            writeEOL();
            fill();
        }
    }

    private void encodeRowType6() throws IOException {
        encode2D();
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x001e, code lost:
        r0[0] = r6;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0021, code lost:
        if (r1 >= r4) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0023, code lost:
        r0[1] = r5[r1];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int[] getNextChanges(int r8, boolean r9) {
        /*
            r7 = this;
            r0 = 2
            int[] r0 = new int[r0]
            int r1 = r7.columns
            r2 = 0
            r0[r2] = r1
            r3 = 1
            r0[r3] = r1
            r1 = r2
        Lc:
            int r4 = r7.changesCurrentRowLength
            if (r1 >= r4) goto L27
            int[] r5 = r7.changesCurrentRow
            r6 = r5[r1]
            if (r8 < r6) goto L1e
            if (r8 != 0) goto L1b
            if (r9 == 0) goto L1b
            goto L1e
        L1b:
            int r1 = r1 + 1
            goto Lc
        L1e:
            r0[r2] = r6
            int r1 = r1 + r3
            if (r1 >= r4) goto L27
            r8 = r5[r1]
            r0[r3] = r8
        L27:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.filter.CCITTFaxEncoderStream.getNextChanges(int, boolean):int[]");
    }

    private void writeRun(int i, boolean z) throws IOException {
        int i2 = i / 64;
        Code[] codeArr = z ? WHITE_NONTERMINATING_CODES : BLACK_NONTERMINATING_CODES;
        while (i2 > 0) {
            if (i2 >= codeArr.length) {
                write(codeArr[codeArr.length - 1].code, codeArr[codeArr.length - 1].length);
                i2 -= codeArr.length;
            } else {
                int i3 = i2 - 1;
                write(codeArr[i3].code, codeArr[i3].length);
                i2 = 0;
            }
        }
        Code code = z ? WHITE_TERMINATING_CODES[i % 64] : BLACK_TERMINATING_CODES[i % 64];
        write(code.code, code.length);
    }

    private void encode2D() throws IOException {
        boolean z = true;
        int i = 0;
        while (i < this.columns) {
            int[] nextChanges = getNextChanges(i, z);
            int[] nextRefChanges = getNextRefChanges(i, z);
            int i2 = nextChanges[0];
            int i3 = i2 - nextRefChanges[0];
            if (i2 > nextRefChanges[1]) {
                write(1, 4);
                i = nextRefChanges[1];
            } else if (i3 > 3 || i3 < -3) {
                write(1, 3);
                writeRun(nextChanges[0] - i, z);
                writeRun(nextChanges[1] - nextChanges[0], !z);
                i = nextChanges[1];
            } else {
                switch (i3) {
                    case -3:
                        write(2, 7);
                        break;
                    case -2:
                        write(2, 6);
                        break;
                    case -1:
                        write(2, 3);
                        break;
                    case 0:
                        write(1, 1);
                        break;
                    case 1:
                        write(3, 3);
                        break;
                    case 2:
                        write(3, 6);
                        break;
                    case 3:
                        write(3, 7);
                        break;
                }
                z = !z;
                i = nextRefChanges[0] + i3;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x001e, code lost:
        r0[0] = r5;
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0021, code lost:
        if (r8 >= r1) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0023, code lost:
        r0[1] = r4[r8];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int[] getNextRefChanges(int r7, boolean r8) {
        /*
            r6 = this;
            r0 = 2
            int[] r0 = new int[r0]
            int r1 = r6.columns
            r2 = 0
            r0[r2] = r1
            r3 = 1
            r0[r3] = r1
            r8 = r8 ^ r3
        Lc:
            int r1 = r6.changesReferenceRowLength
            if (r8 >= r1) goto L27
            int[] r4 = r6.changesReferenceRow
            r5 = r4[r8]
            if (r5 > r7) goto L1e
            if (r7 != 0) goto L1b
            if (r8 != 0) goto L1b
            goto L1e
        L1b:
            int r8 = r8 + 2
            goto Lc
        L1e:
            r0[r2] = r5
            int r8 = r8 + r3
            if (r8 >= r1) goto L27
            r7 = r4[r8]
            r0[r3] = r7
        L27:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.filter.CCITTFaxEncoderStream.getNextRefChanges(int, boolean):int[]");
    }

    private void write(int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            boolean z = ((i >> ((i2 - i3) - 1)) & 1) == 1;
            if (this.fillOrder == 1) {
                this.outputBuffer = (byte) ((z ? 1 << (7 - (this.outputBufferBitLength % 8)) : 0) | this.outputBuffer);
            } else {
                this.outputBuffer = (byte) ((z ? 1 << (this.outputBufferBitLength % 8) : 0) | this.outputBuffer);
            }
            byte b = (byte) (this.outputBufferBitLength + 1);
            this.outputBufferBitLength = b;
            if (b == 8) {
                this.stream.write(this.outputBuffer);
                clearOutputBuffer();
            }
        }
    }

    private void writeEOL() throws IOException {
        write(1, 12);
    }

    private void fill() throws IOException {
        if (this.outputBufferBitLength != 0) {
            this.stream.write(this.outputBuffer);
        }
        clearOutputBuffer();
    }

    private void clearOutputBuffer() {
        this.outputBuffer = (byte) 0;
        this.outputBufferBitLength = (byte) 0;
    }

    /* loaded from: classes3.dex */
    public static class Code {
        final int code;
        final int length;

        private Code(int i, int i2) {
            this.code = i;
            this.length = i2;
        }
    }

    static {
        for (int i = 0; i < CCITTFaxDecoderStream.WHITE_CODES.length; i++) {
            int i2 = i + 4;
            for (int i3 = 0; i3 < CCITTFaxDecoderStream.WHITE_CODES[i].length; i3++) {
                short s = CCITTFaxDecoderStream.WHITE_RUN_LENGTHS[i][i3];
                short s2 = CCITTFaxDecoderStream.WHITE_CODES[i][i3];
                if (s < 64) {
                    WHITE_TERMINATING_CODES[s] = new Code(s2, i2);
                } else {
                    WHITE_NONTERMINATING_CODES[(s / 64) - 1] = new Code(s2, i2);
                }
            }
        }
        BLACK_TERMINATING_CODES = new Code[64];
        BLACK_NONTERMINATING_CODES = new Code[40];
        for (int i4 = 0; i4 < CCITTFaxDecoderStream.BLACK_CODES.length; i4++) {
            int i5 = i4 + 2;
            for (int i6 = 0; i6 < CCITTFaxDecoderStream.BLACK_CODES[i4].length; i6++) {
                short s3 = CCITTFaxDecoderStream.BLACK_RUN_LENGTHS[i4][i6];
                short s4 = CCITTFaxDecoderStream.BLACK_CODES[i4][i6];
                if (s3 < 64) {
                    BLACK_TERMINATING_CODES[s3] = new Code(s4, i5);
                } else {
                    BLACK_NONTERMINATING_CODES[(s3 / 64) - 1] = new Code(s4, i5);
                }
            }
        }
    }
}
