package org.apache.commons.imaging.formats.jpeg.decoder;

/* loaded from: classes2.dex */
final class YCbCrConverter {
    private static final int[] REDS = new int[65536];
    private static final int[] BLUES = new int[65536];
    private static final int[] GREENS1 = new int[65536];
    private static final int[] GREENS2 = new int[131072];

    private static int fastRound(float f) {
        return (int) (f + 0.5f);
    }

    static {
        for (int i = 0; i < 256; i++) {
            for (int i2 = 0; i2 < 256; i2++) {
                int fastRound = fastRound((i2 - 128) * 1.402f) + i;
                if (fastRound < 0) {
                    fastRound = 0;
                }
                if (fastRound > 255) {
                    fastRound = 255;
                }
                REDS[(i2 << 8) | i] = fastRound << 16;
            }
        }
        for (int i3 = 0; i3 < 256; i3++) {
            for (int i4 = 0; i4 < 256; i4++) {
                int fastRound2 = fastRound((i4 - 128) * 1.772f) + i3;
                if (fastRound2 < 0) {
                    fastRound2 = 0;
                }
                if (fastRound2 > 255) {
                    fastRound2 = 255;
                }
                BLUES[(i4 << 8) | i3] = fastRound2;
            }
        }
        for (int i5 = 0; i5 < 256; i5++) {
            for (int i6 = 0; i6 < 256; i6++) {
                GREENS1[(i5 << 8) | i6] = fastRound(((i5 - 128) * 0.34414f) + ((i6 - 128) * 0.71414f)) + 135;
            }
        }
        for (int i7 = 0; i7 < 256; i7++) {
            for (int i8 = 0; i8 < 270; i8++) {
                int i9 = i7 - (i8 - 135);
                if (i9 < 0) {
                    i9 = 0;
                } else if (i9 > 255) {
                    i9 = 255;
                }
                GREENS2[(i8 << 8) | i7] = i9 << 8;
            }
        }
    }

    private YCbCrConverter() {
    }

    public static int convertYCbCrToRGB(int i, int i2, int i3) {
        int i4 = i2 << 8;
        return BLUES[i | i4] | REDS[(i3 << 8) | i] | GREENS2[(GREENS1[i3 | i4] << 8) | i];
    }
}
