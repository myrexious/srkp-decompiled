package org.apache.commons.imaging.formats.pcx;

import org.apache.commons.imaging.ImagingParameters;

/* loaded from: classes2.dex */
public class PcxImagingParameters extends ImagingParameters {
    private int planes = -1;
    private int bitDepth = -1;
    private int compression = 0;

    public int getPlanes() {
        return this.planes;
    }

    public void setPlanes(int i) {
        this.planes = i;
    }

    public int getBitDepth() {
        return this.bitDepth;
    }

    public void setBitDepth(int i) {
        this.bitDepth = i;
    }

    public int getCompression() {
        return this.compression;
    }

    public void setCompression(int i) {
        this.compression = i;
    }
}
