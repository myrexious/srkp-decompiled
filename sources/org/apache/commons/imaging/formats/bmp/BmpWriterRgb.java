package org.apache.commons.imaging.formats.bmp;

import androidx.core.view.ViewCompat;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.imaging.common.BinaryOutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class BmpWriterRgb implements BmpWriter {
    @Override // org.apache.commons.imaging.formats.bmp.BmpWriter
    public int getBitsPerPixel() {
        return 24;
    }

    @Override // org.apache.commons.imaging.formats.bmp.BmpWriter
    public int getPaletteSize() {
        return 0;
    }

    @Override // org.apache.commons.imaging.formats.bmp.BmpWriter
    public void writePalette(BinaryOutputStream binaryOutputStream) throws IOException {
    }

    @Override // org.apache.commons.imaging.formats.bmp.BmpWriter
    public byte[] getImageData(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        for (int i2 = height - 1; i2 >= 0; i2--) {
            for (int i3 = 0; i3 < width; i3++) {
                int rgb = bufferedImage.getRGB(i3, i2) & ViewCompat.MEASURED_SIZE_MASK;
                byteArrayOutputStream.write((rgb >> 0) & 255);
                byteArrayOutputStream.write((rgb >> 8) & 255);
                byteArrayOutputStream.write((rgb >> 16) & 255);
                i += 3;
            }
            while (i % 4 != 0) {
                byteArrayOutputStream.write(0);
                i++;
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
