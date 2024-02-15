package org.apache.commons.imaging.palette;

import androidx.core.view.ViewCompat;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.UByte;
import org.apache.commons.imaging.ImageWriteException;

/* loaded from: classes2.dex */
public class PaletteFactory {
    public static final int COMPONENTS = 3;
    private static final Logger LOGGER = Logger.getLogger(PaletteFactory.class.getName());

    private int pixelToQuantizationTableIndex(int i, int i2) {
        int i3 = (1 << i2) - 1;
        int i4 = 0;
        for (int i5 = 0; i5 < 3; i5++) {
            int i6 = i & 255;
            i >>= 8;
            i4 = (i4 << i2) | ((i6 >> (8 - i2)) & i3);
        }
        return i4;
    }

    public Palette makeExactRgbPaletteFancy(BufferedImage bufferedImage) {
        byte[] bArr = new byte[2097152];
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                int rgb = bufferedImage.getRGB(i2, i);
                int i3 = 2097151 & rgb;
                bArr[i3] = (byte) ((1 << ((rgb >> 21) & 7)) | bArr[i3]);
            }
        }
        int i4 = 0;
        for (int i5 = 0; i5 < 2097152; i5++) {
            i4 += Integer.bitCount(bArr[i5] & UByte.MAX_VALUE);
        }
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Used colors: " + i4);
        }
        int[] iArr = new int[i4];
        int i6 = 0;
        for (int i7 = 0; i7 < 2097152; i7++) {
            int i8 = bArr[i7] & UByte.MAX_VALUE;
            int i9 = 128;
            for (int i10 = 0; i10 < 8; i10++) {
                int i11 = i8 & i9;
                i9 >>>= 1;
                if (i11 > 0) {
                    iArr[i6] = ((7 - i10) << 21) | i7;
                    i6++;
                }
            }
        }
        Arrays.sort(iArr);
        return new SimplePalette(iArr);
    }

    private int getFrequencyTotal(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        int i2 = 0;
        for (int i3 = iArr2[2]; i3 <= iArr3[2]; i3++) {
            int i4 = i3 << (i * 2);
            for (int i5 = iArr2[1]; i5 <= iArr3[1]; i5++) {
                int i6 = i5 << (i * 1);
                for (int i7 = iArr2[0]; i7 <= iArr3[0]; i7++) {
                    i2 += iArr[i4 | i6 | i7];
                }
            }
        }
        return i2;
    }

    private DivisionCandidate finishDivision(ColorSpaceSubset colorSpaceSubset, int i, int i2, int i3, int i4) {
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            colorSpaceSubset.dump("trying (" + i + "): ");
        }
        int i5 = colorSpaceSubset.total;
        if (i4 < colorSpaceSubset.mins[i] || i4 >= colorSpaceSubset.maxs[i] || i3 < 1 || i3 >= i5) {
            return null;
        }
        int[] iArr = new int[colorSpaceSubset.mins.length];
        System.arraycopy(colorSpaceSubset.mins, 0, iArr, 0, colorSpaceSubset.mins.length);
        int[] iArr2 = new int[colorSpaceSubset.maxs.length];
        System.arraycopy(colorSpaceSubset.maxs, 0, iArr2, 0, colorSpaceSubset.maxs.length);
        iArr2[i] = i4;
        iArr[i] = i4 + 1;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("total: " + i5);
            logger.finest("first total: " + i3);
            logger.finest("second total: " + (i5 - i3));
            logger.finest("slice: " + i4);
        }
        return new DivisionCandidate(new ColorSpaceSubset(i3, i2, colorSpaceSubset.mins, iArr2), new ColorSpaceSubset(i5 - i3, i2, iArr, colorSpaceSubset.maxs));
    }

    private List<DivisionCandidate> divideSubset2(int[] iArr, ColorSpaceSubset colorSpaceSubset, int i, int i2) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            colorSpaceSubset.dump("trying (" + i + "): ");
        }
        int i3 = colorSpaceSubset.total;
        int[] iArr2 = new int[colorSpaceSubset.mins.length];
        int i4 = 0;
        System.arraycopy(colorSpaceSubset.mins, 0, iArr2, 0, colorSpaceSubset.mins.length);
        int[] iArr3 = new int[colorSpaceSubset.maxs.length];
        System.arraycopy(colorSpaceSubset.maxs, 0, iArr3, 0, colorSpaceSubset.maxs.length);
        int i5 = colorSpaceSubset.mins[i];
        int i6 = 0;
        while (i5 != colorSpaceSubset.maxs[i] + 1) {
            iArr2[i] = i5;
            iArr3[i] = i5;
            i6 = getFrequencyTotal(iArr, iArr2, iArr3, i2);
            i4 += i6;
            if (i4 >= i3 / 2) {
                break;
            }
            i5++;
        }
        DivisionCandidate finishDivision = finishDivision(colorSpaceSubset, i, i2, i4, i5);
        DivisionCandidate finishDivision2 = finishDivision(colorSpaceSubset, i, i2, i4 - i6, i5 - 1);
        ArrayList arrayList = new ArrayList();
        if (finishDivision != null) {
            arrayList.add(finishDivision);
        }
        if (finishDivision2 != null) {
            arrayList.add(finishDivision2);
        }
        return arrayList;
    }

    private DivisionCandidate divideSubset2(int[] iArr, ColorSpaceSubset colorSpaceSubset, int i) {
        ArrayList<DivisionCandidate> arrayList = new ArrayList();
        arrayList.addAll(divideSubset2(iArr, colorSpaceSubset, 0, i));
        arrayList.addAll(divideSubset2(iArr, colorSpaceSubset, 1, i));
        arrayList.addAll(divideSubset2(iArr, colorSpaceSubset, 2, i));
        DivisionCandidate divisionCandidate = null;
        double d = Double.MAX_VALUE;
        for (DivisionCandidate divisionCandidate2 : arrayList) {
            ColorSpaceSubset colorSpaceSubset2 = divisionCandidate2.dst_a;
            ColorSpaceSubset colorSpaceSubset3 = divisionCandidate2.dst_b;
            int i2 = colorSpaceSubset2.total;
            int i3 = colorSpaceSubset3.total;
            double abs = Math.abs(i2 - i3) / Math.max(i2, i3);
            if (divisionCandidate == null || abs < d) {
                divisionCandidate = divisionCandidate2;
                d = abs;
            }
        }
        return divisionCandidate;
    }

    /* loaded from: classes2.dex */
    public static class DivisionCandidate {
        private final ColorSpaceSubset dst_a;
        private final ColorSpaceSubset dst_b;

        DivisionCandidate(ColorSpaceSubset colorSpaceSubset, ColorSpaceSubset colorSpaceSubset2) {
            this.dst_a = colorSpaceSubset;
            this.dst_b = colorSpaceSubset2;
        }
    }

    private void divide(List<ColorSpaceSubset> list, int i, int[] iArr, int i2) {
        ArrayList arrayList = new ArrayList();
        do {
            int i3 = -1;
            ColorSpaceSubset colorSpaceSubset = null;
            for (ColorSpaceSubset colorSpaceSubset2 : list) {
                if (!arrayList.contains(colorSpaceSubset2)) {
                    int i4 = colorSpaceSubset2.total;
                    if (colorSpaceSubset == null || i4 > i3) {
                        colorSpaceSubset = colorSpaceSubset2;
                        i3 = i4;
                    }
                }
            }
            if (colorSpaceSubset == null) {
                return;
            }
            Logger logger = LOGGER;
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest("\tarea: " + i3);
            }
            DivisionCandidate divideSubset2 = divideSubset2(iArr, colorSpaceSubset, i2);
            if (divideSubset2 != null) {
                list.remove(colorSpaceSubset);
                list.add(divideSubset2.dst_a);
                list.add(divideSubset2.dst_b);
            } else {
                arrayList.add(colorSpaceSubset);
            }
        } while (list.size() != i);
    }

    public Palette makeQuantizedRgbPalette(BufferedImage bufferedImage, int i) {
        ColorSpaceSubset colorSpaceSubset;
        int[] iArr = new int[262144];
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        ArrayList arrayList = new ArrayList();
        int i2 = width * height;
        arrayList.add(new ColorSpaceSubset(i2, 6));
        Logger logger = LOGGER;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("pre total: " + getFrequencyTotal(iArr, colorSpaceSubset.mins, colorSpaceSubset.maxs, 6));
        }
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                int pixelToQuantizationTableIndex = pixelToQuantizationTableIndex(bufferedImage.getRGB(i4, i3), 6);
                iArr[pixelToQuantizationTableIndex] = iArr[pixelToQuantizationTableIndex] + 1;
            }
        }
        Logger logger2 = LOGGER;
        if (logger2.isLoggable(Level.FINEST)) {
            logger2.finest("all total: " + getFrequencyTotal(iArr, colorSpaceSubset.mins, colorSpaceSubset.maxs, 6));
            logger2.finest("width * height: " + i2);
        }
        divide(arrayList, i, iArr, 6);
        if (logger2.isLoggable(Level.FINEST)) {
            logger2.finest("subsets: " + arrayList.size());
            logger2.finest("width*height: " + i2);
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            ColorSpaceSubset colorSpaceSubset2 = arrayList.get(i5);
            colorSpaceSubset2.setAverageRGB(iArr);
            if (LOGGER.isLoggable(Level.FINEST)) {
                colorSpaceSubset2.dump(i5 + ": ");
            }
        }
        arrayList.sort(ColorSpaceSubset.RGB_COMPARATOR);
        return new QuantizedPalette(arrayList, 6);
    }

    public Palette makeQuantizedRgbaPalette(BufferedImage bufferedImage, boolean z, int i) throws ImageWriteException {
        return new MedianCutQuantizer(!z).process(bufferedImage, i, new LongestAxisMedianCut());
    }

    public SimplePalette makeExactRgbPaletteSimple(BufferedImage bufferedImage, int i) {
        HashSet<Integer> hashSet = new HashSet();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int i2 = 0;
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                if (hashSet.add(Integer.valueOf(bufferedImage.getRGB(i4, i3) & ViewCompat.MEASURED_SIZE_MASK)) && hashSet.size() > i) {
                    return null;
                }
            }
        }
        int[] iArr = new int[hashSet.size()];
        for (Integer num : hashSet) {
            iArr[i2] = num.intValue();
            i2++;
        }
        Arrays.sort(iArr);
        return new SimplePalette(iArr);
    }

    public boolean isGrayscale(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (6 == bufferedImage.getColorModel().getColorSpace().getType()) {
            return true;
        }
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                int rgb = bufferedImage.getRGB(i2, i);
                int i3 = (rgb >> 16) & 255;
                int i4 = (rgb >> 8) & 255;
                int i5 = (rgb >> 0) & 255;
                if (i3 != i4 || i3 != i5) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasTransparency(BufferedImage bufferedImage) {
        return hasTransparency(bufferedImage, 255);
    }

    public boolean hasTransparency(BufferedImage bufferedImage, int i) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (bufferedImage.getColorModel().hasAlpha()) {
            for (int i2 = 0; i2 < height; i2++) {
                for (int i3 = 0; i3 < width; i3++) {
                    if (((bufferedImage.getRGB(i3, i2) >> 24) & 255) < i) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public int countTrasparentColors(int[] iArr) {
        int i = -1;
        for (int i2 : iArr) {
            if (((i2 >> 24) & 255) < 255) {
                if (i < 0) {
                    i = i2;
                } else if (i2 != i) {
                    return 2;
                }
            }
        }
        return i < 0 ? 0 : 1;
    }

    public int countTransparentColors(BufferedImage bufferedImage) {
        if (bufferedImage.getColorModel().hasAlpha()) {
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            int i = -1;
            for (int i2 = 0; i2 < height; i2++) {
                for (int i3 = 0; i3 < width; i3++) {
                    int rgb = bufferedImage.getRGB(i3, i2);
                    if (((rgb >> 24) & 255) < 255) {
                        if (i < 0) {
                            i = rgb;
                        } else if (rgb != i) {
                            return 2;
                        }
                    }
                }
            }
            return i < 0 ? 0 : 1;
        }
        return 0;
    }
}
