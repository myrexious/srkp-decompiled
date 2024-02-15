package org.apache.commons.imaging.formats.pcx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.PixelDensity;
import org.apache.commons.imaging.common.BinaryOutputStream;
import org.apache.commons.imaging.palette.PaletteFactory;
import org.apache.commons.imaging.palette.SimplePalette;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PcxWriter {
    private int bitDepthWanted;
    private int encoding;
    private PixelDensity pixelDensity;
    private int planesWanted;
    private final RleWriter rleWriter;

    public PcxWriter(PcxImagingParameters pcxImagingParameters) throws ImageWriteException {
        this.bitDepthWanted = -1;
        this.planesWanted = -1;
        pcxImagingParameters = pcxImagingParameters == null ? new PcxImagingParameters() : pcxImagingParameters;
        this.encoding = 1;
        if (pcxImagingParameters.getCompression() == 0) {
            this.encoding = 0;
        }
        if (this.encoding == 0) {
            this.rleWriter = new RleWriter(false);
        } else {
            this.rleWriter = new RleWriter(true);
        }
        this.bitDepthWanted = pcxImagingParameters.getBitDepth();
        this.planesWanted = pcxImagingParameters.getPlanes();
        PixelDensity pixelDensity = pcxImagingParameters.getPixelDensity();
        this.pixelDensity = pixelDensity;
        if (pixelDensity == null) {
            this.pixelDensity = PixelDensity.createFromPixelsPerInch(72.0d, 72.0d);
        }
    }

    public void writeImage(BufferedImage bufferedImage, OutputStream outputStream) throws IOException {
        int i;
        int i2;
        int i3;
        int entry;
        int entry2;
        SimplePalette makeExactRgbPaletteSimple = new PaletteFactory().makeExactRgbPaletteSimple(bufferedImage, 256);
        BinaryOutputStream binaryOutputStream = new BinaryOutputStream(outputStream, ByteOrder.LITTLE_ENDIAN);
        if (makeExactRgbPaletteSimple == null || (i3 = this.bitDepthWanted) == 24 || i3 == 32) {
            if (this.bitDepthWanted == 32) {
                i2 = 32;
                i = 1;
            } else {
                i = 3;
                i2 = 8;
            }
        } else {
            if (makeExactRgbPaletteSimple.length() > 16 || this.bitDepthWanted == 8) {
                i2 = 8;
            } else if (makeExactRgbPaletteSimple.length() > 8 || this.bitDepthWanted == 4) {
                if (this.planesWanted == 1) {
                    i2 = 4;
                } else {
                    i = 4;
                    i2 = 1;
                }
            } else {
                if (makeExactRgbPaletteSimple.length() > 4 || this.bitDepthWanted == 3) {
                    i = 3;
                } else {
                    if (makeExactRgbPaletteSimple.length() > 2 || this.bitDepthWanted == 2) {
                        if (this.planesWanted != 2) {
                            i2 = 2;
                        }
                    } else {
                        boolean z = makeExactRgbPaletteSimple.length() < 1 || (entry2 = makeExactRgbPaletteSimple.getEntry(0)) == 0 || entry2 == 16777215;
                        if (makeExactRgbPaletteSimple.length() == 2 && (entry = makeExactRgbPaletteSimple.getEntry(1)) != 0 && entry != 16777215) {
                            z = false;
                        }
                        if (z) {
                            i2 = 1;
                            i = 1;
                        }
                    }
                    i = 2;
                }
                i2 = 1;
            }
            i = 1;
        }
        int width = ((bufferedImage.getWidth() * i2) + 7) / 8;
        if (width % 2 != 0) {
            width++;
        }
        byte[] bArr = new byte[48];
        int i4 = 0;
        for (int i5 = 16; i4 < i5; i5 = 16) {
            int entry3 = i4 < makeExactRgbPaletteSimple.length() ? makeExactRgbPaletteSimple.getEntry(i4) : 0;
            int i6 = i4 * 3;
            bArr[i6 + 0] = (byte) ((entry3 >> 16) & 255);
            bArr[i6 + 1] = (byte) ((entry3 >> 8) & 255);
            bArr[i6 + 2] = (byte) (entry3 & 255);
            i4++;
        }
        binaryOutputStream.write(10);
        binaryOutputStream.write((i2 == 1 && i == 1) ? 3 : 5);
        binaryOutputStream.write(this.encoding);
        binaryOutputStream.write(i2);
        binaryOutputStream.write2Bytes(0);
        binaryOutputStream.write2Bytes(0);
        binaryOutputStream.write2Bytes(bufferedImage.getWidth() - 1);
        binaryOutputStream.write2Bytes(bufferedImage.getHeight() - 1);
        binaryOutputStream.write2Bytes((short) Math.round(this.pixelDensity.horizontalDensityInches()));
        binaryOutputStream.write2Bytes((short) Math.round(this.pixelDensity.verticalDensityInches()));
        binaryOutputStream.write(bArr);
        binaryOutputStream.write(0);
        binaryOutputStream.write(i);
        binaryOutputStream.write2Bytes(width);
        binaryOutputStream.write2Bytes(1);
        binaryOutputStream.write2Bytes(0);
        binaryOutputStream.write2Bytes(0);
        binaryOutputStream.write(new byte[54]);
        if (i2 == 32) {
            writePixels32(bufferedImage, width, binaryOutputStream);
        } else {
            writePixels(bufferedImage, i2, i, width, makeExactRgbPaletteSimple, binaryOutputStream);
        }
        if (i2 == 8 && i == 1) {
            binaryOutputStream.write(12);
            int i7 = 0;
            while (i7 < 256) {
                int entry4 = i7 < makeExactRgbPaletteSimple.length() ? makeExactRgbPaletteSimple.getEntry(i7) : 0;
                binaryOutputStream.write((entry4 >> 16) & 255);
                binaryOutputStream.write((entry4 >> 8) & 255);
                binaryOutputStream.write(entry4 & 255);
                i7++;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:187:0x0203 A[LOOP:3: B:186:0x0201->B:187:0x0203, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void writePixels(java.awt.image.BufferedImage r23, int r24, int r25, int r26, org.apache.commons.imaging.palette.SimplePalette r27, org.apache.commons.imaging.common.BinaryOutputStream r28) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 539
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.pcx.PcxWriter.writePixels(java.awt.image.BufferedImage, int, int, int, org.apache.commons.imaging.palette.SimplePalette, org.apache.commons.imaging.common.BinaryOutputStream):void");
    }

    private void writePixels32(BufferedImage bufferedImage, int i, BinaryOutputStream binaryOutputStream) throws IOException {
        int width = bufferedImage.getWidth();
        int[] iArr = new int[width];
        byte[] bArr = new byte[i * 4];
        for (int i2 = 0; i2 < bufferedImage.getHeight(); i2++) {
            bufferedImage.getRGB(0, i2, bufferedImage.getWidth(), 1, iArr, 0, bufferedImage.getWidth());
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = i3 * 4;
                int i5 = iArr[i3];
                bArr[i4 + 0] = (byte) i5;
                bArr[i4 + 1] = (byte) (i5 >> 8);
                bArr[i4 + 2] = (byte) (i5 >> 16);
                bArr[i4 + 3] = 0;
            }
            this.rleWriter.write(binaryOutputStream, bArr);
        }
        this.rleWriter.flush(binaryOutputStream);
    }
}
