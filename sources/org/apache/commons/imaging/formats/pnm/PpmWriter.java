package org.apache.commons.imaging.formats.pnm;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.imaging.ImageWriteException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PpmWriter implements PnmWriter {
    private final boolean rawbits;

    public PpmWriter(boolean z) {
        this.rawbits = z;
    }

    @Override // org.apache.commons.imaging.formats.pnm.PnmWriter
    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream, PnmImagingParameters pnmImagingParameters) throws ImageWriteException, IOException {
        outputStream.write(80);
        outputStream.write(this.rawbits ? 54 : 51);
        outputStream.write(32);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        outputStream.write(Integer.toString(width).getBytes(StandardCharsets.US_ASCII));
        outputStream.write(32);
        outputStream.write(Integer.toString(height).getBytes(StandardCharsets.US_ASCII));
        outputStream.write(32);
        outputStream.write(Integer.toString(255).getBytes(StandardCharsets.US_ASCII));
        outputStream.write(10);
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                int rgb = bufferedImage.getRGB(i2, i);
                int i3 = (rgb >> 16) & 255;
                int i4 = (rgb >> 8) & 255;
                int i5 = (rgb >> 0) & 255;
                if (this.rawbits) {
                    outputStream.write((byte) i3);
                    outputStream.write((byte) i4);
                    outputStream.write((byte) i5);
                } else {
                    outputStream.write(Integer.toString(i3).getBytes(StandardCharsets.US_ASCII));
                    outputStream.write(32);
                    outputStream.write(Integer.toString(i4).getBytes(StandardCharsets.US_ASCII));
                    outputStream.write(32);
                    outputStream.write(Integer.toString(i5).getBytes(StandardCharsets.US_ASCII));
                    outputStream.write(32);
                }
            }
        }
    }
}
