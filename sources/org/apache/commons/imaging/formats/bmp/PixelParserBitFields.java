package org.apache.commons.imaging.formats.bmp;

import java.io.IOException;
import java.nio.ByteOrder;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PixelParserBitFields extends PixelParserSimple {
    private final int alphaMask;
    private final int alphaShift;
    private final int blueMask;
    private final int blueShift;
    private int byteCount;
    private final int greenMask;
    private final int greenShift;
    private final int redMask;
    private final int redShift;

    private int getMaskShift(int i) {
        int i2 = 0;
        if (i == 0) {
            return 0;
        }
        int i3 = 0;
        while ((i & 1) == 0) {
            i = (i >> 1) & Integer.MAX_VALUE;
            i3++;
        }
        while ((i & 1) == 1) {
            i = (i >> 1) & Integer.MAX_VALUE;
            i2++;
        }
        return i3 - (8 - i2);
    }

    public PixelParserBitFields(BmpHeaderInfo bmpHeaderInfo, byte[] bArr, byte[] bArr2) {
        super(bmpHeaderInfo, bArr, bArr2);
        int i = bmpHeaderInfo.redMask;
        this.redMask = i;
        int i2 = bmpHeaderInfo.greenMask;
        this.greenMask = i2;
        int i3 = bmpHeaderInfo.blueMask;
        this.blueMask = i3;
        int i4 = bmpHeaderInfo.alphaMask;
        this.alphaMask = i4;
        this.redShift = getMaskShift(i);
        this.greenShift = getMaskShift(i2);
        this.blueShift = getMaskShift(i3);
        this.alphaShift = i4 != 0 ? getMaskShift(i4) : 0;
    }

    @Override // org.apache.commons.imaging.formats.bmp.PixelParserSimple
    public int getNextRGB() throws ImageReadException, IOException {
        int i;
        int i2 = this.bhi.bitsPerPixel;
        if (i2 == 8) {
            byte[] bArr = this.imageData;
            int i3 = this.byteCount;
            i = bArr[i3 + 0] & UByte.MAX_VALUE;
            this.byteCount = i3 + 1;
        } else if (i2 == 16) {
            i = BinaryFunctions.read2Bytes("Pixel", this.is, "BMP Image Data", ByteOrder.LITTLE_ENDIAN);
            this.byteCount += 2;
        } else if (i2 == 24) {
            i = BinaryFunctions.read3Bytes("Pixel", this.is, "BMP Image Data", ByteOrder.LITTLE_ENDIAN);
            this.byteCount += 3;
        } else if (i2 == 32) {
            i = BinaryFunctions.read4Bytes("Pixel", this.is, "BMP Image Data", ByteOrder.LITTLE_ENDIAN);
            this.byteCount += 4;
        } else {
            throw new ImageReadException("Unknown BitsPerPixel: " + this.bhi.bitsPerPixel);
        }
        int i4 = this.redMask & i;
        int i5 = this.greenMask & i;
        int i6 = this.blueMask & i;
        int i7 = this.alphaMask;
        int i8 = i7 != 0 ? i7 & i : 255;
        int i9 = this.redShift;
        int i10 = i9 >= 0 ? i4 >> i9 : i4 << (-i9);
        int i11 = this.greenShift;
        int i12 = i11 >= 0 ? i5 >> i11 : i5 << (-i11);
        int i13 = this.blueShift;
        int i14 = i13 >= 0 ? i6 >> i13 : i6 << (-i13);
        int i15 = this.alphaShift;
        return (i10 << 16) | ((i15 >= 0 ? i8 >> i15 : i8 << (-i15)) << 24) | (i12 << 8) | (i14 << 0);
    }

    @Override // org.apache.commons.imaging.formats.bmp.PixelParserSimple
    public void newline() throws ImageReadException, IOException {
        while (this.byteCount % 4 != 0) {
            BinaryFunctions.readByte("Pixel", this.is, "BMP Image Data");
            this.byteCount++;
        }
    }
}
