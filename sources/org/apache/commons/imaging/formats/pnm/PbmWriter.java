package org.apache.commons.imaging.formats.pnm;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.imaging.ImageWriteException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PbmWriter implements PnmWriter {
    private final boolean rawBits;

    public PbmWriter(boolean z) {
        this.rawBits = z;
    }

    @Override // org.apache.commons.imaging.formats.pnm.PnmWriter
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, PnmImagingParameters pnmImagingParameters) throws ImageWriteException, IOException {
        outputStream.write(80);
        outputStream.write(this.rawBits ? 52 : 49);
        outputStream.write(32);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        outputStream.write(Integer.toString(width).getBytes(StandardCharsets.US_ASCII));
        outputStream.write(32);
        outputStream.write(Integer.toString(height).getBytes(StandardCharsets.US_ASCII));
        outputStream.write(10);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                int rgb = bufferedImage.getRGB(i4, i3);
                int i5 = ((((rgb >> 16) & 255) + ((rgb >> 8) & 255)) + ((rgb >> 0) & 255)) / 3 > 127 ? 0 : 1;
                if (this.rawBits) {
                    i = (i << 1) | (i5 & 1);
                    i2++;
                    if (i2 >= 8) {
                        outputStream.write((byte) i);
                        i = 0;
                        i2 = 0;
                    }
                } else {
                    outputStream.write(Integer.toString(i5).getBytes(StandardCharsets.US_ASCII));
                    outputStream.write(32);
                }
            }
            if (this.rawBits && i2 > 0) {
                outputStream.write((byte) (i << (8 - i2)));
                i = 0;
                i2 = 0;
            }
        }
    }
}
