package com.tom_roush.pdfbox.filter;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes3.dex */
public final class Predictor {
    static int calcSetBitSeq(int i, int i2, int i3, int i4) {
        int i5 = (1 << i3) - 1;
        return (i & (~(i5 << i2))) | ((i4 & i5) << i2);
    }

    static int getBitSeq(int i, int i2, int i3) {
        return (i >>> i2) & ((1 << i3) - 1);
    }

    private Predictor() {
    }

    static void decodePredictorRow(int i, int i2, int i3, int i4, byte[] bArr, byte[] bArr2) {
        if (i == 1) {
            return;
        }
        int i5 = ((i2 * i3) + 7) / 8;
        int length = bArr.length;
        int i6 = 0;
        if (i != 2) {
            switch (i) {
                case 11:
                    for (int i7 = i5; i7 < length; i7++) {
                        bArr[i7] = (byte) (bArr[i7] + bArr[i7 - i5]);
                    }
                    return;
                case 12:
                    break;
                case 13:
                    for (int i8 = 0; i8 < length; i8++) {
                        int i9 = i8 - i5;
                        bArr[i8] = (byte) (((bArr[i8] & UByte.MAX_VALUE) + (((i9 >= 0 ? bArr[i9] & UByte.MAX_VALUE : 0) + (bArr2[i8] & UByte.MAX_VALUE)) / 2)) & 255);
                    }
                    return;
                case 14:
                    for (int i10 = 0; i10 < length; i10++) {
                        int i11 = bArr[i10] & UByte.MAX_VALUE;
                        int i12 = i10 - i5;
                        int i13 = i12 >= 0 ? bArr[i12] & UByte.MAX_VALUE : 0;
                        int i14 = bArr2[i10] & UByte.MAX_VALUE;
                        int i15 = i12 >= 0 ? bArr2[i12] & UByte.MAX_VALUE : 0;
                        int i16 = (i13 + i14) - i15;
                        int abs = Math.abs(i16 - i13);
                        int abs2 = Math.abs(i16 - i14);
                        int abs3 = Math.abs(i16 - i15);
                        if (abs <= abs2 && abs <= abs3) {
                            bArr[i10] = (byte) ((i11 + i13) & 255);
                        } else if (abs2 <= abs3) {
                            bArr[i10] = (byte) ((i11 + i14) & 255);
                        } else {
                            bArr[i10] = (byte) ((i11 + i15) & 255);
                        }
                    }
                    return;
                default:
                    return;
            }
            while (i6 < length) {
                bArr[i6] = (byte) (((bArr[i6] & UByte.MAX_VALUE) + (bArr2[i6] & UByte.MAX_VALUE)) & 255);
                i6++;
            }
        } else if (i3 == 8) {
            for (int i17 = i5; i17 < length; i17++) {
                bArr[i17] = (byte) ((bArr[i17] & UByte.MAX_VALUE) + (bArr[i17 - i5] & UByte.MAX_VALUE));
            }
        } else if (i3 == 16) {
            for (int i18 = i5; i18 < length - 1; i18 += 2) {
                int i19 = i18 + 1;
                int i20 = i18 - i5;
                int i21 = ((bArr[i18] & UByte.MAX_VALUE) << 8) + (bArr[i19] & UByte.MAX_VALUE) + ((bArr[i20] & UByte.MAX_VALUE) << 8) + (bArr[i20 + 1] & UByte.MAX_VALUE);
                bArr[i18] = (byte) ((i21 >> 8) & 255);
                bArr[i19] = (byte) (i21 & 255);
            }
        } else if (i3 == 1 && i2 == 1) {
            while (i6 < length) {
                int i22 = 7;
                while (i22 >= 0) {
                    byte b = bArr[i6];
                    int i23 = (b >> i22) & 1;
                    if (i6 != 0 || i22 != 7) {
                        if (((i23 + ((i22 == 7 ? bArr[i6 - 1] : b >> (i22 + 1)) & 1)) & 1) == 0) {
                            bArr[i6] = (byte) (b & (~(1 << i22)));
                        } else {
                            bArr[i6] = (byte) (b | (1 << i22));
                        }
                    }
                    i22--;
                }
                i6++;
            }
        } else {
            int i24 = i4 * i2;
            for (int i25 = i2; i25 < i24; i25++) {
                int i26 = i25 * i3;
                int i27 = i26 / 8;
                int i28 = (8 - (i26 % 8)) - i3;
                int i29 = (i25 - i2) * i3;
                bArr[i27] = (byte) calcSetBitSeq(bArr[i27], i28, i3, getBitSeq(bArr[i27], i28, i3) + getBitSeq(bArr[i29 / 8], (8 - (i29 % 8)) - i3, i3));
            }
        }
    }

    static int calculateRowLength(int i, int i2, int i3) {
        return ((i3 * (i * i2)) + 7) / 8;
    }

    public static OutputStream wrapPredictor(OutputStream outputStream, COSDictionary cOSDictionary) {
        int i = cOSDictionary.getInt(COSName.PREDICTOR);
        return i > 1 ? new PredictorOutputStream(outputStream, i, Math.min(cOSDictionary.getInt(COSName.COLORS, 1), 32), cOSDictionary.getInt(COSName.BITS_PER_COMPONENT, 8), cOSDictionary.getInt(COSName.COLUMNS, 1)) : outputStream;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class PredictorOutputStream extends FilterOutputStream {
        private final int bitsPerComponent;
        private final int colors;
        private final int columns;
        private byte[] currentRow;
        private int currentRowData;
        private byte[] lastRow;
        private int predictor;
        private final boolean predictorPerRow;
        private boolean predictorRead;
        private final int rowLength;

        PredictorOutputStream(OutputStream outputStream, int i, int i2, int i3, int i4) {
            super(outputStream);
            this.currentRowData = 0;
            this.predictorRead = false;
            this.predictor = i;
            this.colors = i2;
            this.bitsPerComponent = i3;
            this.columns = i4;
            int calculateRowLength = Predictor.calculateRowLength(i2, i3, i4);
            this.rowLength = calculateRowLength;
            this.predictorPerRow = i >= 10;
            this.currentRow = new byte[calculateRowLength];
            this.lastRow = new byte[calculateRowLength];
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            int i3 = i2 + i;
            while (i < i3) {
                if (this.predictorPerRow && this.currentRowData == 0 && !this.predictorRead) {
                    this.predictor = bArr[i] + 10;
                    i++;
                    this.predictorRead = true;
                } else {
                    int min = Math.min(this.rowLength - this.currentRowData, i3 - i);
                    System.arraycopy(bArr, i, this.currentRow, this.currentRowData, min);
                    int i4 = this.currentRowData + min;
                    this.currentRowData = i4;
                    i += min;
                    if (i4 == this.currentRow.length) {
                        decodeAndWriteRow();
                    }
                }
            }
        }

        private void decodeAndWriteRow() throws IOException {
            Predictor.decodePredictorRow(this.predictor, this.colors, this.bitsPerComponent, this.columns, this.currentRow, this.lastRow);
            this.out.write(this.currentRow);
            flipRows();
        }

        private void flipRows() {
            byte[] bArr = this.lastRow;
            this.lastRow = this.currentRow;
            this.currentRow = bArr;
            this.currentRowData = 0;
            this.predictorRead = false;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            int i = this.currentRowData;
            if (i > 0) {
                Arrays.fill(this.currentRow, i, this.rowLength, (byte) 0);
                decodeAndWriteRow();
            }
            super.flush();
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            throw new UnsupportedOperationException("Not supported");
        }
    }
}
