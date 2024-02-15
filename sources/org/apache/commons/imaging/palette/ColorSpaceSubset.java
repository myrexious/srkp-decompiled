package org.apache.commons.imaging.palette;

import java.io.Serializable;
import java.util.Comparator;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class ColorSpaceSubset {
    private static final Logger LOGGER = Logger.getLogger(ColorSpaceSubset.class.getName());
    public static final RgbComparator RGB_COMPARATOR = new RgbComparator();
    private int index;
    final int[] maxs;
    final int[] mins;
    final int precision;
    final int precisionMask;
    int rgb;
    final int total;

    public ColorSpaceSubset(int i, int i2) {
        this.total = i;
        this.precision = i2;
        this.precisionMask = (1 << i2) - 1;
        this.mins = new int[3];
        this.maxs = new int[3];
        for (int i3 = 0; i3 < 3; i3++) {
            this.maxs[i3] = this.precisionMask;
        }
        this.rgb = -1;
    }

    public ColorSpaceSubset(int i, int i2, int[] iArr, int[] iArr2) {
        this.total = i;
        this.precision = i2;
        this.mins = iArr;
        this.maxs = iArr2;
        this.precisionMask = (1 << i2) - 1;
        this.rgb = -1;
    }

    public final boolean contains(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = this.precision;
        int i7 = i >> (8 - i6);
        int[] iArr = this.mins;
        if (iArr[0] > i7) {
            return false;
        }
        int[] iArr2 = this.maxs;
        return iArr2[0] >= i7 && iArr[1] <= (i4 = i2 >> (8 - i6)) && iArr2[1] >= i4 && iArr[2] <= (i5 = i3 >> (8 - i6)) && iArr2[2] >= i5;
    }

    public void dump(String str) {
        int[] iArr = this.maxs;
        int i = iArr[0];
        int[] iArr2 = this.mins;
        int i2 = (i - iArr2[0]) + 1;
        int i3 = (iArr[1] - iArr2[1]) + 1;
        int i4 = (iArr[2] - iArr2[2]) + 1;
        Logger logger = LOGGER;
        logger.fine(str + ": [" + Integer.toHexString(this.rgb) + "] total : " + this.total);
        logger.fine("\trgb: " + Integer.toHexString(this.rgb) + ", red: " + Integer.toHexString(this.mins[0] << (8 - this.precision)) + ", " + Integer.toHexString(this.maxs[0] << (8 - this.precision)) + ", green: " + Integer.toHexString(this.mins[1] << (8 - this.precision)) + ", " + Integer.toHexString(this.maxs[1] << (8 - this.precision)) + ", blue: " + Integer.toHexString(this.mins[2] << (8 - this.precision)) + ", " + Integer.toHexString(this.maxs[2] << (8 - this.precision)));
        logger.fine("\tred: " + this.mins[0] + ", " + this.maxs[0] + ", green: " + this.mins[1] + ", " + this.maxs[1] + ", blue: " + this.mins[2] + ", " + this.maxs[2]);
        logger.fine("\trdiff: " + i2 + ", gdiff: " + i3 + ", bdiff: " + i4 + ", colorArea: " + (i2 * i3 * i4));
    }

    public void dumpJustRGB(String str) {
        LOGGER.fine("\trgb: " + Integer.toHexString(this.rgb) + ", red: " + Integer.toHexString(this.mins[0] << (8 - this.precision)) + ", " + Integer.toHexString(this.maxs[0] << (8 - this.precision)) + ", green: " + Integer.toHexString(this.mins[1] << (8 - this.precision)) + ", " + Integer.toHexString(this.maxs[1] << (8 - this.precision)) + ", blue: " + Integer.toHexString(this.mins[2] << (8 - this.precision)) + ", " + Integer.toHexString(this.maxs[2] << (8 - this.precision)));
    }

    public int getArea() {
        int[] iArr = this.maxs;
        int i = iArr[0];
        int[] iArr2 = this.mins;
        return ((i - iArr2[0]) + 1) * ((iArr[1] - iArr2[1]) + 1) * ((iArr[2] - iArr2[2]) + 1);
    }

    public void setAverageRGB(int[] iArr) {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (int i = this.mins[0]; i <= this.maxs[0]; i++) {
            int i2 = this.mins[1];
            for (char c = 1; i2 <= this.maxs[c]; c = 1) {
                for (int i3 = this.mins[2]; i3 <= this.maxs[2]; i3++) {
                    int i4 = this.precision;
                    int i5 = iArr[(i3 << (i4 * 2)) | (i2 << (i4 * 1)) | (i << (i4 * 0))];
                    j += i5 * (i << (8 - i4));
                    j2 += (i2 << (8 - i4)) * i5;
                    j3 += i5 * (i3 << (8 - i4));
                }
                i2++;
            }
        }
        int i6 = this.total;
        this.rgb = (int) ((((j3 / i6) & 255) << 0) | (((j / i6) & 255) << 16) | (((j2 / i6) & 255) << 8));
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    /* loaded from: classes2.dex */
    public static class RgbComparator implements Comparator<ColorSpaceSubset>, Serializable {
        private static final long serialVersionUID = 509214838111679029L;

        @Override // java.util.Comparator
        public int compare(ColorSpaceSubset colorSpaceSubset, ColorSpaceSubset colorSpaceSubset2) {
            return colorSpaceSubset.rgb - colorSpaceSubset2.rgb;
        }
    }
}
