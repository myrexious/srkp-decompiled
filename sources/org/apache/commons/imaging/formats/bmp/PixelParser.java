package org.apache.commons.imaging.formats.bmp;

import androidx.core.view.ViewCompat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;

/* loaded from: classes2.dex */
abstract class PixelParser {
    final BmpHeaderInfo bhi;
    final byte[] colorTable;
    final byte[] imageData;
    final InputStream is;

    public abstract void processImage(ImageBuilder imageBuilder) throws ImageReadException, IOException;

    public PixelParser(BmpHeaderInfo bmpHeaderInfo, byte[] bArr, byte[] bArr2) {
        this.bhi = bmpHeaderInfo;
        this.colorTable = bArr;
        this.imageData = bArr2;
        this.is = new ByteArrayInputStream(bArr2);
    }

    public int getColorTableRGB(int i) {
        int i2 = i * 4;
        byte[] bArr = this.colorTable;
        int i3 = bArr[i2 + 0] & UByte.MAX_VALUE;
        return ((bArr[i2 + 2] & UByte.MAX_VALUE) << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[i2 + 1] & UByte.MAX_VALUE) << 8) | (i3 << 0);
    }
}
