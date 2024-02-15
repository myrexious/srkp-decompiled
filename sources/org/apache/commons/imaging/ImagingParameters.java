package org.apache.commons.imaging;

import org.apache.commons.imaging.common.BufferedImageFactory;

/* loaded from: classes2.dex */
public class ImagingParameters {
    private PixelDensity pixelDensity;
    private boolean strict = false;
    private String fileName = null;
    private BufferedImageFactory bufferedImageFactory = null;

    public boolean isStrict() {
        return this.strict;
    }

    public void setStrict(boolean z) {
        this.strict = z;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public BufferedImageFactory getBufferedImageFactory() {
        return this.bufferedImageFactory;
    }

    public void setBufferedImageFactory(BufferedImageFactory bufferedImageFactory) {
        this.bufferedImageFactory = bufferedImageFactory;
    }

    public PixelDensity getPixelDensity() {
        return this.pixelDensity;
    }

    public void setPixelDensity(PixelDensity pixelDensity) {
        this.pixelDensity = pixelDensity;
    }
}
