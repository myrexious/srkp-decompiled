package org.apache.commons.imaging.formats.png.scanlinefilters;

import java.io.IOException;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;

/* loaded from: classes2.dex */
public class ScanlineFilterAverage implements ScanlineFilter {
    private final int bytesPerPixel;

    public ScanlineFilterAverage(int i) {
        this.bytesPerPixel = i;
    }

    @Override // org.apache.commons.imaging.formats.png.scanlinefilters.ScanlineFilter
    public void unfilter(byte[] bArr, byte[] bArr2, byte[] bArr3) throws ImageReadException, IOException {
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i - this.bytesPerPixel;
            bArr2[i] = (byte) ((bArr[i] + ((((i2 >= 0 ? bArr2[i2] : (byte) 0) & UByte.MAX_VALUE) + ((bArr3 != null ? bArr3[i] : (byte) 0) & UByte.MAX_VALUE)) / 2)) % 256);
        }
    }
}
