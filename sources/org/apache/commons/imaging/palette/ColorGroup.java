package org.apache.commons.imaging.palette;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes2.dex */
public class ColorGroup {
    final int alphaDiff;
    final int blueDiff;
    private final List<ColorCount> colorCounts;
    ColorGroupCut cut;
    final int diffTotal;
    final int greenDiff;
    final boolean ignoreAlpha;
    int maxAlpha;
    int maxBlue;
    final int maxDiff;
    int maxGreen;
    int maxRed;
    int minAlpha;
    int minBlue;
    int minGreen;
    int minRed;
    int paletteIndex = -1;
    final int redDiff;
    final int totalPoints;

    public ColorGroup(List<ColorCount> list, boolean z) throws ImageWriteException {
        this.minRed = Integer.MAX_VALUE;
        this.maxRed = Integer.MIN_VALUE;
        this.minGreen = Integer.MAX_VALUE;
        this.maxGreen = Integer.MIN_VALUE;
        this.minBlue = Integer.MAX_VALUE;
        this.maxBlue = Integer.MIN_VALUE;
        this.minAlpha = Integer.MAX_VALUE;
        this.maxAlpha = Integer.MIN_VALUE;
        this.colorCounts = list;
        this.ignoreAlpha = z;
        if (list.isEmpty()) {
            throw new ImageWriteException("empty color_group");
        }
        int i = 0;
        for (ColorCount colorCount : list) {
            i += colorCount.count;
            this.minAlpha = Math.min(this.minAlpha, colorCount.alpha);
            this.maxAlpha = Math.max(this.maxAlpha, colorCount.alpha);
            this.minRed = Math.min(this.minRed, colorCount.red);
            this.maxRed = Math.max(this.maxRed, colorCount.red);
            this.minGreen = Math.min(this.minGreen, colorCount.green);
            this.maxGreen = Math.max(this.maxGreen, colorCount.green);
            this.minBlue = Math.min(this.minBlue, colorCount.blue);
            this.maxBlue = Math.max(this.maxBlue, colorCount.blue);
        }
        this.totalPoints = i;
        int i2 = this.maxAlpha - this.minAlpha;
        this.alphaDiff = i2;
        int i3 = this.maxRed - this.minRed;
        this.redDiff = i3;
        int i4 = this.maxGreen - this.minGreen;
        this.greenDiff = i4;
        int i5 = this.maxBlue - this.minBlue;
        this.blueDiff = i5;
        this.maxDiff = Math.max(z ? i3 : Math.max(i2, i3), Math.max(i4, i5));
        this.diffTotal = (z ? 0 : i2) + i3 + i4 + i5;
    }

    boolean contains(int i) {
        int i2 = (i >> 24) & 255;
        int i3 = (i >> 16) & 255;
        int i4 = (i >> 8) & 255;
        int i5 = (i >> 0) & 255;
        return (this.ignoreAlpha || (i2 >= this.minAlpha && i2 <= this.maxAlpha)) && i3 >= this.minRed && i3 <= this.maxRed && i4 >= this.minGreen && i4 <= this.maxGreen && i5 >= this.minBlue && i5 <= this.maxBlue;
    }

    public int getMedianValue() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        for (ColorCount colorCount : this.colorCounts) {
            j += colorCount.count;
            j2 += colorCount.count * colorCount.alpha;
            j3 += colorCount.count * colorCount.red;
            j4 += colorCount.count * colorCount.green;
            j5 += colorCount.count * colorCount.blue;
        }
        int round = this.ignoreAlpha ? 255 : (int) Math.round(j2 / j);
        double d = j;
        return (round << 24) | (((int) Math.round(j3 / d)) << 16) | (((int) Math.round(j4 / d)) << 8) | ((int) Math.round(j5 / d));
    }

    public List<ColorCount> getColorCounts() {
        return new ArrayList(this.colorCounts);
    }

    public String toString() {
        return "{ColorGroup. minRed: " + Integer.toHexString(this.minRed) + ", maxRed: " + Integer.toHexString(this.maxRed) + ", minGreen: " + Integer.toHexString(this.minGreen) + ", maxGreen: " + Integer.toHexString(this.maxGreen) + ", minBlue: " + Integer.toHexString(this.minBlue) + ", maxBlue: " + Integer.toHexString(this.maxBlue) + ", minAlpha: " + Integer.toHexString(this.minAlpha) + ", maxAlpha: " + Integer.toHexString(this.maxAlpha) + ", maxDiff: " + Integer.toHexString(this.maxDiff) + ", diffTotal: " + this.diffTotal + StringSubstitutor.DEFAULT_VAR_END;
    }
}
