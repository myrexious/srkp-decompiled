package org.apache.commons.imaging.formats.pnm;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.common.ImageBuilder;

/* loaded from: classes2.dex */
abstract class FileInfo {
    final int height;
    final boolean rawBits;
    final int width;

    public abstract int getBitDepth();

    public abstract ImageInfo.ColorType getColorType();

    public abstract ImageFormat getImageType();

    public abstract String getImageTypeDescription();

    public abstract String getMIMEType();

    public abstract int getNumComponents();

    abstract int getRGB(InputStream inputStream) throws IOException;

    abstract int getRGB(WhiteSpaceReader whiteSpaceReader) throws IOException;

    public abstract boolean hasAlpha();

    void newline() {
    }

    public FileInfo(int i, int i2, boolean z) {
        this.width = i;
        this.height = i2;
        this.rawBits = z;
    }

    public static int readSample(InputStream inputStream, int i) throws IOException {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int read = inputStream.read();
            if (read < 0) {
                throw new IOException("PNM: Unexpected EOF");
            }
            i2 = (i2 << 8) | read;
        }
        return i2;
    }

    public static int scaleSample(int i, float f, int i2) throws IOException {
        if (i >= 0) {
            if (i > i2) {
                i = 0;
            }
            return (int) (((i * f) / i2) + 0.5f);
        }
        throw new IOException("Negative pixel values are invalid in PNM files");
    }

    public void readImage(ImageBuilder imageBuilder, InputStream inputStream) throws IOException {
        if (!this.rawBits) {
            WhiteSpaceReader whiteSpaceReader = new WhiteSpaceReader(inputStream);
            for (int i = 0; i < this.height; i++) {
                for (int i2 = 0; i2 < this.width; i2++) {
                    imageBuilder.setRGB(i2, i, getRGB(whiteSpaceReader));
                }
                newline();
            }
            return;
        }
        for (int i3 = 0; i3 < this.height; i3++) {
            for (int i4 = 0; i4 < this.width; i4++) {
                imageBuilder.setRGB(i4, i3, getRGB(inputStream));
            }
            newline();
        }
    }
}
