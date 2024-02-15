package org.apache.commons.imaging.palette;

import androidx.core.view.ViewCompat;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.internal.Debug;

/* loaded from: classes2.dex */
public class MedianCutQuantizer {
    private final boolean ignoreAlpha;

    public MedianCutQuantizer(boolean z) {
        this.ignoreAlpha = z;
    }

    private Map<Integer, ColorCount> groupColors1(BufferedImage bufferedImage, int i, int i2) {
        HashMap hashMap = new HashMap();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int[] iArr = new int[width];
        for (int i3 = 0; i3 < height; i3++) {
            bufferedImage.getRGB(0, i3, width, 1, iArr, 0, width);
            for (int i4 = 0; i4 < width; i4++) {
                int i5 = iArr[i4];
                if (this.ignoreAlpha) {
                    i5 &= ViewCompat.MEASURED_SIZE_MASK;
                }
                int i6 = i5 & i2;
                ColorCount colorCount = (ColorCount) hashMap.get(Integer.valueOf(i6));
                if (colorCount == null) {
                    colorCount = new ColorCount(i6);
                    hashMap.put(Integer.valueOf(i6), colorCount);
                    if (hashMap.size() > i) {
                        return null;
                    }
                }
                colorCount.count++;
            }
        }
        return hashMap;
    }

    public Map<Integer, ColorCount> groupColors(BufferedImage bufferedImage, int i) {
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = 255 & (255 << i2);
            int i4 = (i3 << 24) | (i3 << 8) | i3 | (i3 << 16);
            Debug.debug("mask(" + i2 + "): " + i4 + " (" + Integer.toHexString(i4) + ")");
            Map<Integer, ColorCount> groupColors1 = groupColors1(bufferedImage, Integer.MAX_VALUE, i4);
            if (groupColors1 != null) {
                return groupColors1;
            }
        }
        throw new Error("");
    }

    public Palette process(BufferedImage bufferedImage, int i, MedianCut medianCut) throws ImageWriteException {
        Map<Integer, ColorCount> groupColors = groupColors(bufferedImage, i);
        int size = groupColors.size();
        int i2 = 0;
        if (size <= i) {
            Debug.debug("lossless palette: " + size);
            int[] iArr = new int[size];
            ArrayList arrayList = new ArrayList(groupColors.values());
            while (i2 < arrayList.size()) {
                int i3 = ((ColorCount) arrayList.get(i2)).argb;
                iArr[i2] = i3;
                if (this.ignoreAlpha) {
                    iArr[i2] = i3 | ViewCompat.MEASURED_STATE_MASK;
                }
                i2++;
            }
            return new SimplePalette(iArr);
        }
        Debug.debug("discrete colors: " + size);
        ArrayList arrayList2 = new ArrayList();
        ColorGroup colorGroup = new ColorGroup(new ArrayList(groupColors.values()), this.ignoreAlpha);
        arrayList2.add(colorGroup);
        while (arrayList2.size() < i && medianCut.performNextMedianCut(arrayList2, this.ignoreAlpha)) {
        }
        int size2 = arrayList2.size();
        Debug.debug("palette size: " + size2);
        int[] iArr2 = new int[size2];
        while (i2 < arrayList2.size()) {
            ColorGroup colorGroup2 = arrayList2.get(i2);
            iArr2[i2] = colorGroup2.getMedianValue();
            colorGroup2.paletteIndex = i2;
            if (colorGroup2.getColorCounts().isEmpty()) {
                throw new ImageWriteException("empty color_group: " + colorGroup2);
            }
            i2++;
        }
        if (size2 > size) {
            throw new ImageWriteException("palette_size > discrete_colors");
        }
        return new MedianCutPalette(colorGroup, iArr2);
    }
}
