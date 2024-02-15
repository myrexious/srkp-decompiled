package org.apache.commons.imaging.formats.pnm;

import androidx.core.view.ViewCompat;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PpmFileInfo extends FileInfo {
    private final int bytesPerSample;
    private final int max;
    private final float scale;

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public String getImageTypeDescription() {
        return "PPM: portable pixmap file format";
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public String getMIMEType() {
        return "image/x-portable-pixmap";
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public int getNumComponents() {
        return 3;
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public boolean hasAlpha() {
        return false;
    }

    public PpmFileInfo(int i, int i2, boolean z, int i3) throws ImageReadException {
        super(i, i2, z);
        if (i3 <= 0) {
            throw new ImageReadException("PPM maxVal " + i3 + " is out of range [1;65535]");
        }
        if (i3 <= 255) {
            this.scale = 255.0f;
            this.bytesPerSample = 1;
        } else if (i3 <= 65535) {
            this.scale = 65535.0f;
            this.bytesPerSample = 2;
        } else {
            throw new ImageReadException("PPM maxVal " + i3 + " is out of range [1;65535]");
        }
        this.max = i3;
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public int getBitDepth() {
        return this.max;
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public ImageFormat getImageType() {
        return ImageFormats.PPM;
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public ImageInfo.ColorType getColorType() {
        return ImageInfo.ColorType.RGB;
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public int getRGB(InputStream inputStream) throws IOException {
        int readSample = readSample(inputStream, this.bytesPerSample);
        int readSample2 = readSample(inputStream, this.bytesPerSample);
        int readSample3 = readSample(inputStream, this.bytesPerSample);
        int scaleSample = scaleSample(readSample, this.scale, this.max);
        int scaleSample2 = scaleSample(readSample2, this.scale, this.max);
        return ((scaleSample(readSample3, this.scale, this.max) & 255) << 0) | ((scaleSample & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((scaleSample2 & 255) << 8);
    }

    @Override // org.apache.commons.imaging.formats.pnm.FileInfo
    public int getRGB(WhiteSpaceReader whiteSpaceReader) throws IOException {
        int parseInt = Integer.parseInt(whiteSpaceReader.readtoWhiteSpace());
        int parseInt2 = Integer.parseInt(whiteSpaceReader.readtoWhiteSpace());
        int parseInt3 = Integer.parseInt(whiteSpaceReader.readtoWhiteSpace());
        int scaleSample = scaleSample(parseInt, this.scale, this.max);
        int scaleSample2 = scaleSample(parseInt2, this.scale, this.max);
        return ((scaleSample(parseInt3, this.scale, this.max) & 255) << 0) | ((scaleSample & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((scaleSample2 & 255) << 8);
    }
}
