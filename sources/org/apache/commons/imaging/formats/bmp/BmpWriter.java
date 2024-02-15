package org.apache.commons.imaging.formats.bmp;

import java.awt.image.BufferedImage;
import java.io.IOException;
import org.apache.commons.imaging.common.BinaryOutputStream;

/* loaded from: classes2.dex */
interface BmpWriter {
    int getBitsPerPixel();

    byte[] getImageData(BufferedImage bufferedImage);

    int getPaletteSize();

    void writePalette(BinaryOutputStream binaryOutputStream) throws IOException;
}
