package org.apache.commons.imaging.formats.png.transparencyfilters;

import androidx.core.view.ViewCompat;
import java.io.IOException;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;

/* loaded from: classes2.dex */
public class TransparencyFilterIndexedColor extends TransparencyFilter {
    public TransparencyFilterIndexedColor(byte[] bArr) {
        super(bArr);
    }

    @Override // org.apache.commons.imaging.formats.png.transparencyfilters.TransparencyFilter
    public int filter(int i, int i2) throws ImageReadException, IOException {
        int length = getLength();
        if (i2 >= length) {
            return i;
        }
        if (i2 < 0 || i2 > length) {
            throw new ImageReadException("TransparencyFilterIndexedColor index: " + i2 + ", bytes.length: " + length);
        }
        return (i & ViewCompat.MEASURED_SIZE_MASK) | ((getByte(i2) & UByte.MAX_VALUE) << 24);
    }
}
