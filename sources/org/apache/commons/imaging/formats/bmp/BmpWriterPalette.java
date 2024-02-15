package org.apache.commons.imaging.formats.bmp;

import androidx.core.view.ViewCompat;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.palette.SimplePalette;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class BmpWriterPalette implements BmpWriter {
    private final int bitsPerSample;
    private final SimplePalette palette;

    public BmpWriterPalette(SimplePalette simplePalette) {
        this.palette = simplePalette;
        if (simplePalette.length() <= 2) {
            this.bitsPerSample = 1;
        } else if (simplePalette.length() <= 16) {
            this.bitsPerSample = 4;
        } else {
            this.bitsPerSample = 8;
        }
    }

    @Override // org.apache.commons.imaging.formats.bmp.BmpWriter
    public int getPaletteSize() {
        return this.palette.length();
    }

    @Override // org.apache.commons.imaging.formats.bmp.BmpWriter
    public int getBitsPerPixel() {
        return this.bitsPerSample;
    }

    @Override // org.apache.commons.imaging.formats.bmp.BmpWriter
    public void writePalette(BinaryOutputStream binaryOutputStream) throws IOException {
        for (int i = 0; i < this.palette.length(); i++) {
            int entry = this.palette.getEntry(i);
            binaryOutputStream.write((entry >> 0) & 255);
            binaryOutputStream.write((entry >> 8) & 255);
            binaryOutputStream.write((entry >> 16) & 255);
            binaryOutputStream.write(0);
        }
    }

    @Override // org.apache.commons.imaging.formats.bmp.BmpWriter
    public byte[] getImageData(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = height - 1; i4 >= 0; i4--) {
            for (int i5 = 0; i5 < width; i5++) {
                int paletteIndex = this.palette.getPaletteIndex(bufferedImage.getRGB(i5, i4) & ViewCompat.MEASURED_SIZE_MASK);
                int i6 = this.bitsPerSample;
                if (i6 == 8) {
                    byteArrayOutputStream.write(paletteIndex & 255);
                    i3++;
                } else {
                    i = (i << i6) | paletteIndex;
                    i2 += i6;
                    if (i2 >= 8) {
                        byteArrayOutputStream.write(i & 255);
                        i3++;
                        i = 0;
                        i2 = 0;
                    }
                }
            }
            if (i2 > 0) {
                byteArrayOutputStream.write((i << (8 - i2)) & 255);
                i3++;
                i = 0;
                i2 = 0;
            }
            while (i3 % 4 != 0) {
                byteArrayOutputStream.write(0);
                i3++;
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
