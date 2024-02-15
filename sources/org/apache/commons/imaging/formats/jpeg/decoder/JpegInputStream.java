package org.apache.commons.imaging.formats.jpeg.decoder;

import java.util.Arrays;
import org.apache.commons.imaging.ImageReadException;

/* loaded from: classes2.dex */
class JpegInputStream {
    private int b;
    private int cnt;
    private final int[] interval;
    private int nextPos = 0;

    public JpegInputStream(int[] iArr) {
        this.interval = Arrays.copyOf(iArr, iArr.length);
    }

    public boolean hasNext() {
        return this.nextPos < this.interval.length;
    }

    public int nextBit() throws ImageReadException {
        if (this.cnt == 0) {
            int read = read();
            this.b = read;
            if (read < 0) {
                throw new ImageReadException("Premature End of File");
            }
            this.cnt = 8;
            if (read == 255) {
                int read2 = read();
                if (read2 < 0) {
                    throw new ImageReadException("Premature End of File");
                }
                if (read2 != 0) {
                    if (read2 == 220) {
                        throw new ImageReadException("DNL not yet supported");
                    }
                    throw new ImageReadException("Invalid marker found in entropy data: 0xFF " + Integer.toHexString(read2));
                }
            }
        }
        int i = this.b;
        int i2 = (i >> 7) & 1;
        this.cnt--;
        this.b = i << 1;
        return i2;
    }

    int read() {
        if (!hasNext()) {
            throw new IllegalStateException("This stream hasn't any other value, all values were already read.");
        }
        int[] iArr = this.interval;
        int i = this.nextPos;
        int i2 = iArr[i];
        this.nextPos = i + 1;
        return i2;
    }
}
