package org.apache.commons.imaging.formats.tiff.constants;

/* loaded from: classes2.dex */
public enum TiffPlanarConfiguration {
    CHUNKY(1),
    PLANAR(2);
    
    public final int codeValue;

    TiffPlanarConfiguration(int i) {
        this.codeValue = i;
    }

    public static TiffPlanarConfiguration lenientValueOf(int i) {
        if (i != 1) {
            if (i == 2) {
                return PLANAR;
            }
            return CHUNKY;
        }
        return CHUNKY;
    }
}
