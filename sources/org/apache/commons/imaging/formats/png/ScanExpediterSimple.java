package org.apache.commons.imaging.formats.png;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.png.chunks.PngChunkPlte;
import org.apache.commons.imaging.formats.png.transparencyfilters.TransparencyFilter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ScanExpediterSimple extends ScanExpediter {
    public ScanExpediterSimple(int i, int i2, InputStream inputStream, BufferedImage bufferedImage, PngColorType pngColorType, int i3, int i4, PngChunkPlte pngChunkPlte, GammaCorrection gammaCorrection, TransparencyFilter transparencyFilter) {
        super(i, i2, inputStream, bufferedImage, pngColorType, i3, i4, pngChunkPlte, gammaCorrection, transparencyFilter);
    }

    @Override // org.apache.commons.imaging.formats.png.ScanExpediter
    public void drive() throws ImageReadException, IOException {
        int bitsToBytesRoundingUp = getBitsToBytesRoundingUp(this.bitsPerPixel * this.width);
        byte[] bArr = null;
        for (int i = 0; i < this.height; i++) {
            bArr = getNextScanline(this.is, bitsToBytesRoundingUp, bArr, this.bytesPerPixel);
            BitParser bitParser = new BitParser(bArr, this.bitsPerPixel, this.bitDepth);
            for (int i2 = 0; i2 < this.width; i2++) {
                this.bi.setRGB(i2, i, getRGB(bitParser, i2));
            }
        }
    }
}
