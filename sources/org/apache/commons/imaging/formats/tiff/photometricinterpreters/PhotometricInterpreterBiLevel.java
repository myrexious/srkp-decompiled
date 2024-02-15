package org.apache.commons.imaging.formats.tiff.photometricinterpreters;

import androidx.core.view.ViewCompat;
import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;

/* loaded from: classes2.dex */
public class PhotometricInterpreterBiLevel extends PhotometricInterpreter {
    private final boolean invert;

    public PhotometricInterpreterBiLevel(int i, int[] iArr, int i2, int i3, int i4, boolean z) {
        super(i, iArr, i2, i3, i4);
        this.invert = z;
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter
    public void interpretPixel(ImageBuilder imageBuilder, int[] iArr, int i, int i2) throws ImageReadException, IOException {
        int i3 = iArr[0];
        if (this.invert) {
            i3 = 255 - i3;
        }
        imageBuilder.setRGB(i, i2, (i3 << 0) | (i3 << 16) | ViewCompat.MEASURED_STATE_MASK | (i3 << 8));
    }
}
