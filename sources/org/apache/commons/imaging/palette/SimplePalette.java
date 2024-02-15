package org.apache.commons.imaging.palette;

/* loaded from: classes2.dex */
public class SimplePalette implements Palette {
    private final int[] palette;

    public SimplePalette(int[] iArr) {
        this.palette = iArr;
    }

    @Override // org.apache.commons.imaging.palette.Palette
    public int getPaletteIndex(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.palette;
            if (i2 >= iArr.length) {
                return -1;
            }
            if (iArr[i2] == i) {
                return i2;
            }
            i2++;
        }
    }

    @Override // org.apache.commons.imaging.palette.Palette
    public int getEntry(int i) {
        return this.palette[i];
    }

    @Override // org.apache.commons.imaging.palette.Palette
    public int length() {
        return this.palette.length;
    }
}
