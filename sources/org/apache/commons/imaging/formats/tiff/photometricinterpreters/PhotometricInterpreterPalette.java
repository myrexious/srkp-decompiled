package org.apache.commons.imaging.formats.tiff.photometricinterpreters;

import androidx.core.view.ViewCompat;
import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;

/* loaded from: classes2.dex */
public class PhotometricInterpreterPalette extends PhotometricInterpreter {
    private final int bitsPerPixelMask;
    private final int[] indexColorMap;

    public PhotometricInterpreterPalette(int i, int[] iArr, int i2, int i3, int i4, int[] iArr2) {
        super(i, iArr, i2, i3, i4);
        int bitsPerSample = getBitsPerSample(0);
        int i5 = 1 << bitsPerSample;
        this.indexColorMap = new int[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            this.indexColorMap[i6] = (((iArr2[i6] >> 8) & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | (((iArr2[i6 + i5] >> 8) & 255) << 8) | ((iArr2[(i5 * 2) + i6] >> 8) & 255);
        }
        int i7 = 0;
        for (int i8 = 0; i8 < bitsPerSample; i8++) {
            i7 = (i7 << 1) | 1;
        }
        this.bitsPerPixelMask = i7;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter
    public void interpretPixel(ImageBuilder imageBuilder, int[] iArr, int i, int i2) throws ImageReadException, IOException {
        imageBuilder.setRGB(i, i2, this.indexColorMap[iArr[0] & this.bitsPerPixelMask]);
    }
}
