package org.apache.commons.imaging.formats.bmp;

import java.io.IOException;
import java.util.logging.Logger;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.ImageBuilder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PixelParserRle extends PixelParser {
    private static final Logger LOGGER = Logger.getLogger(PixelParserRle.class.getName());

    public PixelParserRle(BmpHeaderInfo bmpHeaderInfo, byte[] bArr, byte[] bArr2) {
        super(bmpHeaderInfo, bArr, bArr2);
    }

    private int getSamplesPerByte() throws ImageReadException {
        if (this.bhi.bitsPerPixel == 8) {
            return 1;
        }
        if (this.bhi.bitsPerPixel == 4) {
            return 2;
        }
        throw new ImageReadException("BMP RLE: bad BitsPerPixel: " + this.bhi.bitsPerPixel);
    }

    private int[] convertDataToSamples(int i) throws ImageReadException {
        if (this.bhi.bitsPerPixel == 8) {
            return new int[]{getColorTableRGB(i)};
        }
        if (this.bhi.bitsPerPixel == 4) {
            return new int[]{getColorTableRGB(i >> 4), getColorTableRGB(i & 15)};
        }
        throw new ImageReadException("BMP RLE: bad BitsPerPixel: " + this.bhi.bitsPerPixel);
    }

    private int processByteOfData(int[] iArr, int i, int i2, int i3, int i4, int i5, ImageBuilder imageBuilder) {
        int i6 = 0;
        for (int i7 = 0; i7 < i; i7++) {
            if (i2 >= 0 && i2 < i4 && i3 >= 0 && i3 < i5) {
                imageBuilder.setRGB(i2, i3, iArr[i7 % iArr.length]);
            } else {
                LOGGER.fine("skipping bad pixel (" + i2 + "," + i3 + ")");
            }
            i2++;
            i6++;
        }
        return i6;
    }

    @Override // org.apache.commons.imaging.formats.bmp.PixelParser
    public void processImage(ImageBuilder imageBuilder) throws ImageReadException, IOException {
        int i = this.bhi.width;
        int i2 = this.bhi.height;
        int i3 = i2 - 1;
        boolean z = false;
        int i4 = 0;
        while (!z) {
            int readByte = BinaryFunctions.readByte("RLE (" + i4 + "," + i3 + ") a", this.is, "BMP: Bad RLE") & UByte.MAX_VALUE;
            int readByte2 = BinaryFunctions.readByte("RLE (" + i4 + "," + i3 + ") b", this.is, "BMP: Bad RLE") & UByte.MAX_VALUE;
            if (readByte != 0) {
                i4 += processByteOfData(convertDataToSamples(readByte2), readByte, i4, i3, i, i2, imageBuilder);
            } else if (readByte2 == 0) {
                i3--;
                i4 = 0;
            } else if (readByte2 == 1) {
                z = true;
            } else if (readByte2 == 2) {
                i4 += BinaryFunctions.readByte("RLE deltaX", this.is, "BMP: Bad RLE") & UByte.MAX_VALUE;
                i3 -= BinaryFunctions.readByte("RLE deltaY", this.is, "BMP: Bad RLE") & UByte.MAX_VALUE;
            } else {
                int samplesPerByte = getSamplesPerByte();
                int i5 = readByte2 / samplesPerByte;
                if (readByte2 % samplesPerByte > 0) {
                    i5++;
                }
                if (i5 % 2 != 0) {
                    i5++;
                }
                byte[] readBytes = BinaryFunctions.readBytes("bytes", this.is, i5, "RLE: Absolute Mode");
                int i6 = 0;
                int i7 = i4;
                int i8 = readByte2;
                while (i8 > 0) {
                    int processByteOfData = processByteOfData(convertDataToSamples(readBytes[i6] & UByte.MAX_VALUE), Math.min(i8, samplesPerByte), i7, i3, i, i2, imageBuilder);
                    i7 += processByteOfData;
                    i8 -= processByteOfData;
                    i6++;
                    samplesPerByte = samplesPerByte;
                }
                i4 = i7;
            }
        }
    }
}
