package org.apache.commons.imaging.formats.jpeg.segments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;

/* loaded from: classes2.dex */
public class DqtSegment extends Segment {
    public final List<QuantizationTable> quantizationTables;

    /* loaded from: classes2.dex */
    public static class QuantizationTable {
        public final int destinationIdentifier;
        private final int[] elements;
        public final int precision;

        public QuantizationTable(int i, int i2, int[] iArr) {
            this.precision = i;
            this.destinationIdentifier = i2;
            this.elements = iArr;
        }

        public int[] getElements() {
            return this.elements;
        }
    }

    public DqtSegment(int i, byte[] bArr) throws ImageReadException, IOException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public DqtSegment(int i, int i2, InputStream inputStream) throws ImageReadException, IOException {
        super(i, i2);
        this.quantizationTables = new ArrayList();
        while (i2 > 0) {
            byte readByte = BinaryFunctions.readByte("QuantizationTablePrecisionAndDestination", inputStream, "Not a Valid JPEG File");
            i2--;
            int i3 = (readByte >> 4) & 15;
            int i4 = readByte & 15;
            int[] iArr = new int[64];
            for (int i5 = 0; i5 < 64; i5++) {
                if (i3 == 0) {
                    iArr[i5] = BinaryFunctions.readByte("QuantizationTableElement", inputStream, "Not a Valid JPEG File") & UByte.MAX_VALUE;
                    i2--;
                } else if (i3 == 1) {
                    iArr[i5] = BinaryFunctions.read2Bytes("QuantizationTableElement", inputStream, "Not a Valid JPEG File", getByteOrder());
                    i2 -= 2;
                } else {
                    throw new ImageReadException("Quantization table precision '" + i3 + "' is invalid");
                }
            }
            this.quantizationTables.add(new QuantizationTable(i3, i4, iArr));
        }
    }

    @Override // org.apache.commons.imaging.formats.jpeg.segments.Segment
    public String getDescription() {
        return "DQT (" + getSegmentType() + ")";
    }
}
