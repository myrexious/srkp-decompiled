package org.apache.commons.imaging.formats.jpeg.segments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.UByte;
import org.apache.commons.imaging.common.BinaryFunctions;

/* loaded from: classes2.dex */
public class DhtSegment extends Segment {
    public final List<HuffmanTable> huffmanTables;

    /* loaded from: classes2.dex */
    public static class HuffmanTable {
        public final int destinationIdentifier;
        private final int[] huffCode;
        private final int[] huffVal;
        public final int tableClass;
        private final int[] huffSize = new int[4096];
        private final int[] minCode = new int[17];
        private final int[] maxCode = new int[17];
        private final int[] valPtr = new int[17];

        HuffmanTable(int i, int i2, int[] iArr, int[] iArr2) {
            this.tableClass = i;
            this.destinationIdentifier = i2;
            this.huffVal = iArr2;
            int i3 = 0;
            int i4 = 0;
            int i5 = 1;
            int i6 = 1;
            while (true) {
                if (i5 > iArr[i6]) {
                    i6++;
                    if (i6 > 16) {
                        break;
                    }
                    i5 = 1;
                } else {
                    this.huffSize[i4] = i6;
                    i4++;
                    i5++;
                }
            }
            int[] iArr3 = this.huffSize;
            iArr3[i4] = 0;
            int i7 = iArr3[0];
            this.huffCode = new int[i4];
            int i8 = 0;
            int i9 = 0;
            while (i8 < i4) {
                this.huffCode[i8] = i9;
                i9++;
                i8++;
                int i10 = this.huffSize[i8];
                if (i10 != i7) {
                    if (i10 == 0) {
                        break;
                    }
                    do {
                        i9 <<= 1;
                        i7++;
                    } while (this.huffSize[i8] != i7);
                }
            }
            int i11 = 0;
            while (true) {
                i3++;
                if (i3 > 16) {
                    return;
                }
                if (iArr[i3] == 0) {
                    this.maxCode[i3] = -1;
                } else {
                    this.valPtr[i3] = i11;
                    int[] iArr4 = this.minCode;
                    int[] iArr5 = this.huffCode;
                    iArr4[i3] = iArr5[i11];
                    int i12 = i11 + (iArr[i3] - 1);
                    this.maxCode[i3] = iArr5[i12];
                    i11 = i12 + 1;
                }
            }
        }

        public int getHuffVal(int i) {
            return this.huffVal[i];
        }

        public int getMinCode(int i) {
            return this.minCode[i];
        }

        public int getMaxCode(int i) {
            return this.maxCode[i];
        }

        public int getValPtr(int i) {
            return this.valPtr[i];
        }
    }

    public DhtSegment(int i, byte[] bArr) throws IOException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public DhtSegment(int i, int i2, InputStream inputStream) throws IOException {
        super(i, i2);
        ArrayList arrayList = new ArrayList();
        while (i2 > 0) {
            int readByte = BinaryFunctions.readByte("TableClassAndDestinationId", inputStream, "Not a Valid JPEG File") & UByte.MAX_VALUE;
            i2--;
            int i3 = (readByte >> 4) & 15;
            int i4 = readByte & 15;
            int[] iArr = new int[17];
            int i5 = 0;
            for (int i6 = 1; i6 < 17; i6++) {
                int readByte2 = BinaryFunctions.readByte("Li", inputStream, "Not a Valid JPEG File") & UByte.MAX_VALUE;
                iArr[i6] = readByte2;
                i2--;
                i5 += readByte2;
            }
            int[] iArr2 = new int[i5];
            for (int i7 = 0; i7 < i5; i7++) {
                iArr2[i7] = BinaryFunctions.readByte("Vij", inputStream, "Not a Valid JPEG File") & UByte.MAX_VALUE;
                i2--;
            }
            arrayList.add(new HuffmanTable(i3, i4, iArr, iArr2));
        }
        this.huffmanTables = Collections.unmodifiableList(arrayList);
    }

    @Override // org.apache.commons.imaging.formats.jpeg.segments.Segment
    public String getDescription() {
        return "DHT (" + getSegmentType() + ")";
    }
}
