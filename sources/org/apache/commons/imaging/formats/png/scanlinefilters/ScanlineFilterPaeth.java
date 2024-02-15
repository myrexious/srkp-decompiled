package org.apache.commons.imaging.formats.png.scanlinefilters;

import java.io.IOException;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;

/* loaded from: classes2.dex */
public class ScanlineFilterPaeth implements ScanlineFilter {
    private final int bytesPerPixel;

    public ScanlineFilterPaeth(int i) {
        this.bytesPerPixel = i;
    }

    private int paethPredictor(int i, int i2, int i3) {
        int i4 = (i + i2) - i3;
        int abs = Math.abs(i4 - i);
        int abs2 = Math.abs(i4 - i2);
        int abs3 = Math.abs(i4 - i3);
        return (abs > abs2 || abs > abs3) ? abs2 <= abs3 ? i2 : i3 : i;
    }

    @Override // org.apache.commons.imaging.formats.png.scanlinefilters.ScanlineFilter
    public void unfilter(byte[] bArr, byte[] bArr2, byte[] bArr3) throws ImageReadException, IOException {
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i - this.bytesPerPixel;
            bArr2[i] = (byte) ((bArr[i] + paethPredictor((i2 >= 0 ? bArr2[i2] : (byte) 0) & UByte.MAX_VALUE, (bArr3 != null ? bArr3[i] : (byte) 0) & UByte.MAX_VALUE, ((i2 < 0 || bArr3 == null) ? (byte) 0 : bArr3[i2]) & UByte.MAX_VALUE)) % 256);
        }
    }
}
