package com.tom_roush.pdfbox.pdfparser;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObjectKey;
import com.tom_roush.pdfbox.cos.COSStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes3.dex */
public class PDFXrefStreamParser extends BaseParser {
    private ObjectNumbers objectNumbers;
    private final int[] w;
    private final XrefTrailerResolver xrefTrailerResolver;

    public PDFXrefStreamParser(COSStream cOSStream, COSDocument cOSDocument, XrefTrailerResolver xrefTrailerResolver) throws IOException {
        super(new InputStreamSource(cOSStream.createInputStream()));
        this.w = new int[3];
        this.objectNumbers = null;
        this.document = cOSDocument;
        this.xrefTrailerResolver = xrefTrailerResolver;
        try {
            initParserValues(cOSStream);
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    private void initParserValues(COSStream cOSStream) throws IOException {
        COSArray cOSArray = cOSStream.getCOSArray(COSName.W);
        if (cOSArray == null) {
            throw new IOException("/W array is missing in Xref stream");
        }
        if (cOSArray.size() != 3) {
            throw new IOException("Wrong number of values for /W array in XRef: " + Arrays.toString(this.w));
        }
        for (int i = 0; i < 3; i++) {
            this.w[i] = cOSArray.getInt(i, 0);
        }
        int[] iArr = this.w;
        if (iArr[0] < 0 || iArr[1] < 0 || iArr[2] < 0) {
            throw new IOException("Incorrect /W array in XRef: " + Arrays.toString(this.w));
        }
        COSArray cOSArray2 = cOSStream.getCOSArray(COSName.INDEX);
        if (cOSArray2 == null) {
            cOSArray2 = new COSArray();
            cOSArray2.add((COSBase) COSInteger.ZERO);
            cOSArray2.add((COSBase) COSInteger.get(cOSStream.getInt(COSName.SIZE, 0)));
        }
        if (cOSArray2.size() == 0 || cOSArray2.size() % 2 == 1) {
            throw new IOException("Wrong number of values for /Index array in XRef: " + Arrays.toString(this.w));
        }
        this.objectNumbers = new ObjectNumbers(cOSArray2);
    }

    private void close() throws IOException {
        if (this.seqSource != null) {
            this.seqSource.close();
        }
        this.document = null;
    }

    public void parse() throws IOException {
        int i;
        int[] iArr = this.w;
        byte[] bArr = new byte[iArr[0] + iArr[1] + iArr[2]];
        while (!this.seqSource.isEOF() && this.objectNumbers.hasNext()) {
            this.seqSource.read(bArr);
            long longValue = this.objectNumbers.next().longValue();
            int i2 = this.w[0];
            int parseValue = i2 == 0 ? 1 : (int) parseValue(bArr, 0, i2);
            if (parseValue != 0) {
                int[] iArr2 = this.w;
                long parseValue2 = parseValue(bArr, iArr2[0], iArr2[1]);
                if (parseValue == 1) {
                    int[] iArr3 = this.w;
                    i = (int) parseValue(bArr, iArr3[0] + iArr3[1], iArr3[2]);
                } else {
                    i = 0;
                }
                COSObjectKey cOSObjectKey = new COSObjectKey(longValue, i);
                if (parseValue == 1) {
                    this.xrefTrailerResolver.setXRef(cOSObjectKey, parseValue2);
                } else {
                    this.xrefTrailerResolver.setXRef(cOSObjectKey, -parseValue2);
                }
            }
        }
        close();
    }

    private long parseValue(byte[] bArr, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j += (bArr[i3 + i] & 255) << (((i2 - i3) - 1) * 8);
        }
        return j;
    }

    /* loaded from: classes3.dex */
    public static class ObjectNumbers implements Iterator<Long> {
        private long currentEnd;
        private long currentNumber;
        private int currentRange;
        private final long[] end;
        private long maxValue;
        private final long[] start;

        private ObjectNumbers(COSArray cOSArray) throws IOException {
            this.currentRange = 0;
            this.currentEnd = 0L;
            this.currentNumber = 0L;
            this.maxValue = 0L;
            long[] jArr = new long[cOSArray.size() / 2];
            this.start = jArr;
            this.end = new long[jArr.length];
            Iterator<COSBase> it = cOSArray.iterator();
            int i = 0;
            while (it.hasNext()) {
                COSBase next = it.next();
                if (!(next instanceof COSInteger)) {
                    throw new IOException("Xref stream must have integer in /Index array");
                }
                long longValue = ((COSInteger) next).longValue();
                if (!it.hasNext()) {
                    break;
                }
                COSBase next2 = it.next();
                if (!(next2 instanceof COSInteger)) {
                    throw new IOException("Xref stream must have integer in /Index array");
                }
                long longValue2 = ((COSInteger) next2).longValue();
                this.start[i] = longValue;
                this.end[i] = longValue + longValue2;
                i++;
            }
            this.currentNumber = this.start[0];
            long[] jArr2 = this.end;
            this.currentEnd = jArr2[0];
            this.maxValue = jArr2[i - 1];
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.currentNumber < this.maxValue;
        }

        @Override // java.util.Iterator
        public Long next() {
            long j = this.currentNumber;
            if (j >= this.maxValue) {
                throw new NoSuchElementException();
            }
            if (j < this.currentEnd) {
                this.currentNumber = 1 + j;
                return Long.valueOf(j);
            }
            long[] jArr = this.start;
            int i = this.currentRange + 1;
            this.currentRange = i;
            long j2 = jArr[i];
            this.currentEnd = this.end[i];
            this.currentNumber = 1 + j2;
            return Long.valueOf(j2);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
