package org.apache.commons.imaging.formats.png;

import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;

/* loaded from: classes2.dex */
class BitParser {
    private final int bitDepth;
    private final int bitsPerPixel;
    private final byte[] bytes;

    public BitParser(byte[] bArr, int i, int i2) {
        this.bytes = (byte[]) bArr.clone();
        this.bitsPerPixel = i;
        this.bitDepth = i2;
    }

    public int getSample(int i, int i2) throws ImageReadException {
        int i3 = this.bitsPerPixel * i;
        int i4 = this.bitDepth;
        int i5 = ((i2 * i4) + i3) >> 3;
        if (i4 == 8) {
            return this.bytes[i5] & UByte.MAX_VALUE;
        }
        if (i4 < 8) {
            return ((1 << i4) - 1) & ((this.bytes[i5] & UByte.MAX_VALUE) >> (8 - ((i3 & 7) + i4)));
        } else if (i4 == 16) {
            byte[] bArr = this.bytes;
            return (bArr[i5 + 1] & UByte.MAX_VALUE) | ((bArr[i5] & UByte.MAX_VALUE) << 8);
        } else {
            throw new ImageReadException("PNG: bad BitDepth: " + this.bitDepth);
        }
    }

    public int getSampleAsByte(int i, int i2) throws ImageReadException {
        int sample = getSample(i, i2);
        int i3 = this.bitDepth;
        int i4 = 8 - i3;
        if (i4 > 0) {
            sample = (sample * 255) / ((1 << i3) - 1);
        } else if (i4 < 0) {
            sample >>= -i4;
        }
        return sample & 255;
    }
}
