package org.apache.commons.imaging.formats.png.scanlinefilters;

import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;

/* loaded from: classes2.dex */
public class ScanlineFilterNone implements ScanlineFilter {
    @Override // org.apache.commons.imaging.formats.png.scanlinefilters.ScanlineFilter
    public void unfilter(byte[] bArr, byte[] bArr2, byte[] bArr3) throws ImageReadException, IOException {
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }
}
