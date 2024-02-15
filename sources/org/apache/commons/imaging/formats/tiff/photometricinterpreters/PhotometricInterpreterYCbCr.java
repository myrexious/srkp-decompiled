package org.apache.commons.imaging.formats.tiff.photometricinterpreters;

import androidx.core.view.ViewCompat;
import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;

/* loaded from: classes2.dex */
public class PhotometricInterpreterYCbCr extends PhotometricInterpreter {
    public PhotometricInterpreterYCbCr(int i, int[] iArr, int i2, int i3, int i4) {
        super(i, iArr, i2, i3, i4);
    }

    public static int limit(int i, int i2, int i3) {
        return Math.min(i3, Math.max(i2, i));
    }

    public static int convertYCbCrtoRGB(int i, int i2, int i3) {
        double d = (i - 16.0d) * 1.164d;
        double d2 = i3 - 128.0d;
        double d3 = (1.596d * d2) + d;
        double d4 = i2 - 128.0d;
        double d5 = d + (d4 * 2.017d);
        return (limit((int) d3, 0, 255) << 16) | ViewCompat.MEASURED_STATE_MASK | (limit((int) ((d - (d2 * 0.813d)) - (0.392d * d4)), 0, 255) << 8) | (limit((int) d5, 0, 255) << 0);
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter
    public void interpretPixel(ImageBuilder imageBuilder, int[] iArr, int i, int i2) throws ImageReadException, IOException {
        int i3 = iArr[0];
        double d = i3;
        double d2 = iArr[2] - 128.0d;
        double d3 = (1.402d * d2) + d;
        double d4 = iArr[1] - 128.0d;
        imageBuilder.setRGB(i, i2, (limit((int) (d + (d4 * 1.772d)), 0, 255) << 0) | (limit((int) d3, 0, 255) << 16) | ViewCompat.MEASURED_STATE_MASK | (limit((int) ((d - (0.34414d * d4)) - (d2 * 0.71414d)), 0, 255) << 8));
    }
}
