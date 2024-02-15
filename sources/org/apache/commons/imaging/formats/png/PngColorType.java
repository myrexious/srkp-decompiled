package org.apache.commons.imaging.formats.png;

import java.util.Arrays;

/* loaded from: classes2.dex */
public enum PngColorType {
    GREYSCALE(0, true, false, 1, new int[]{1, 2, 4, 8, 16}),
    TRUE_COLOR(2, false, false, 3, new int[]{8, 16}),
    INDEXED_COLOR(3, false, false, 1, new int[]{1, 2, 4, 8}),
    GREYSCALE_WITH_ALPHA(4, true, true, 2, new int[]{8, 16}),
    TRUE_COLOR_WITH_ALPHA(6, false, true, 4, new int[]{8, 16});
    
    private final int[] allowedBitDepths;
    private final boolean alpha;
    private final boolean greyscale;
    private final int samplesPerPixel;
    private final int value;

    PngColorType(int i, boolean z, boolean z2, int i2, int[] iArr) {
        this.value = i;
        this.greyscale = z;
        this.alpha = z2;
        this.samplesPerPixel = i2;
        this.allowedBitDepths = iArr;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isGreyscale() {
        return this.greyscale;
    }

    public boolean hasAlpha() {
        return this.alpha;
    }

    public int getSamplesPerPixel() {
        return this.samplesPerPixel;
    }

    public boolean isBitDepthAllowed(int i) {
        return Arrays.binarySearch(this.allowedBitDepths, i) >= 0;
    }

    public static PngColorType getColorType(int i) {
        PngColorType[] values;
        for (PngColorType pngColorType : values()) {
            if (pngColorType.value == i) {
                return pngColorType;
            }
        }
        return null;
    }

    public static PngColorType getColorType(boolean z, boolean z2) {
        if (z2) {
            if (z) {
                return GREYSCALE_WITH_ALPHA;
            }
            return GREYSCALE;
        } else if (z) {
            return TRUE_COLOR_WITH_ALPHA;
        } else {
            return TRUE_COLOR;
        }
    }
}
